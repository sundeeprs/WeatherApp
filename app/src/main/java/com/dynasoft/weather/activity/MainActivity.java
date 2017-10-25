package com.dynasoft.weather.activity;

import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dynasoft.weather.R;
import com.dynasoft.weather.client.RetrofitAPIClient;
import com.dynasoft.weather.helper.AppSQLiteHelper;
import com.dynasoft.weather.helper.Constants;
import com.dynasoft.weather.helper.Helper;
import com.dynasoft.weather.interfaces.ApiInterface;
import com.dynasoft.weather.model.City;
import com.dynasoft.weather.model.OpenWeatherMap;
import com.dynasoft.weather.model.WeatherList;
import com.dynasoft.weather.service.WeatherIntentService;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtCity)
    public TextView mTxtCity;
    @BindView(R.id.txtDescription)
    public TextView mTxtDescription;
    @BindView(R.id.txtCelsius)
    public TextView mTxtCelsius;
    @BindView(R.id.txtLastUpdate)
    public TextView mTxtLastUpdate;
    @BindView(R.id.txtHumidity)
    public TextView mTxtHumidity;
    @BindView(R.id.txtTime)
    public TextView mTxtTime;
    @BindView(R.id.imageView)
    public ImageView mImageView;
    @BindView(R.id.searchView)
    public SearchView searchView;
    @BindView(R.id.txtNoCity)
    public TextView mTxtViewNoCity;

    private String query = "";
    private ApiInterface apiInterface;
    private AppSQLiteHelper mAppSQLiteHelper;
    private List<City> cityList = new ArrayList<City>();
    private Intent mWeatherIntent;
    private OpenWeatherMap mOpenWeatherMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        //Create Regrofit API interface instance
        apiInterface = RetrofitAPIClient.getClient().create(ApiInterface.class);


        WeatherReceiver weatherReceiver = new WeatherReceiver();
        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_RESP);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(weatherReceiver, intentFilter);

        //Creating Database instance
        mAppSQLiteHelper = new AppSQLiteHelper(getApplicationContext());
        // get all city f
        cityList = mAppSQLiteHelper.getAllCities();
        int cityListSize = cityList.size() - 1;


        if (cityList.size() > 0) {
            mTxtViewNoCity.setVisibility(View.GONE);
            City lastCity = cityList.get(cityListSize);
            callIntentService(lastCity.getCityName());

        } else {
            mTxtViewNoCity.setText(getResources().getText(R.string.search_result));
            mTxtViewNoCity.setVisibility(View.VISIBLE);
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.main_menu, menu);
        //MenuItem ourSearchItem = menu.findItem(R.id.menu_search);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callIntentService(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    public void callIntentService(String query) {
        String queryCity = query;
        mWeatherIntent = new Intent(getApplicationContext(), WeatherIntentService.class);
        mWeatherIntent.putExtra(Constants.EXTENDED_DATA_STATUS, queryCity);
        startService(mWeatherIntent);
    }

    public void setWeatherInfoToUI(OpenWeatherMap openWeatherMap) {
        mOpenWeatherMap = openWeatherMap;
        mTxtViewNoCity.setVisibility(View.GONE);
        clearAllViews();

        mTxtCity.setText(String.format("%s , %s", mOpenWeatherMap.getName(),
                mOpenWeatherMap.getSys().getCountry()));
        mTxtDescription.setText(String.format("Desc - %s",
                mOpenWeatherMap.getWeather().get(0).describeContents()));
        mTxtLastUpdate.setText(String.format("Last updated: %s", Helper.getDateNow()));
        mTxtHumidity.setText(String.format("Humidity - %s",
                mOpenWeatherMap.getMain().getHumidity()));
        mTxtTime.setText(String.format("Sunrise - %s/ Sunset - %s",
                Helper.unixTimeStampToTime(mOpenWeatherMap.getSys().getSunrise()),
                Helper.unixTimeStampToTime(mOpenWeatherMap.getSys().getSunset())));
        mTxtCelsius.setText(String.format("Temprature - %s.2f",
                mOpenWeatherMap.getMain().getTemp()));
        List<WeatherList> weatherList = mOpenWeatherMap.getWeather();
        RequestCreator requestCreator = Picasso.with(getApplicationContext()).load(Helper.getIcon(weatherList.get(0).getIcon()));
        requestCreator.into(mImageView);

        City city = new City();
        city.setCityName(mOpenWeatherMap.getName());
        //remove old record from db
        mAppSQLiteHelper.deleteAll();
        // add City to database;
        mAppSQLiteHelper.insertCity(city);
    }

    public void clearAllViews() {

        mTxtCity.setText("");
        mTxtDescription.setText("");
        mTxtLastUpdate.setText("");
        mTxtHumidity.setText("");
        mTxtTime.setText("");
        mTxtCelsius.setText("");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mImageView.setImageIcon(null);
        }

    }


    class WeatherReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            Bundle bundle = new Bundle();
            bundle = intent.getParcelableExtra(Constants.EXTENDED_DATA_STATUS);

            OpenWeatherMap mOpenWeatherMap = bundle.getParcelable(Constants.EXTENDED_DATA_STATUS);
            //Log.d(""," JHD City name is... "+mOpenWeatherMap.getName());
            setWeatherInfoToUI(mOpenWeatherMap);

        }
    }
}