package com.dynasoft.weather.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dynasoft.weather.client.RetrofitAPIClient;
import com.dynasoft.weather.helper.AppSQLiteHelper;
import com.dynasoft.weather.helper.Helper;
import com.dynasoft.weather.interfaces.ApiInterface;
import com.dynasoft.weather.model.City;
import com.dynasoft.weather.model.OpenWeatherMap;
import com.dynasoft.weather.model.WeatherList;
import com.dynasoft.weather.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public TextView mTxtCity;
    public TextView mTxtDescription;
    public TextView mTxtCelsius;
    public TextView mTxtLastUpdate;
    public TextView mTxtHumidity;
    public TextView mTxtTime;
    public ImageView mImageView;
    private ApiInterface apiInterface;
    private String query = "";
    private OpenWeatherMap mOpenWeatherMap;
    private AppSQLiteHelper mAppSQLiteHelper;
    private TextView mTxtViewNoCity;
    private List<City> cityList = new ArrayList<City>();

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create Regrofit API interface instance
        apiInterface = RetrofitAPIClient.getClient().create(ApiInterface.class);

        //Create Views
        mTxtCity = (TextView) findViewById(R.id.txtCity);
        mTxtDescription = (TextView) findViewById(R.id.txtDescription);
        mTxtCelsius = (TextView) findViewById(R.id.txtCelsius);
        mTxtLastUpdate = (TextView) findViewById(R.id.txtLastUpdate);
        mTxtHumidity = (TextView) findViewById(R.id.txtHumidity);
        mTxtTime = (TextView) findViewById(R.id.txtTime);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mTxtViewNoCity = (TextView) findViewById(R.id.txtNoCity);

        searchView =(SearchView) findViewById(R.id.searchView);

        //Creating Database instance
        mAppSQLiteHelper = new AppSQLiteHelper(getApplicationContext());
        // get all city f
        cityList = mAppSQLiteHelper.getAllCities();
        int cityListSize = cityList.size()-1;



        if(cityList.size() > 0) {
            mTxtViewNoCity.setVisibility(View.GONE);
            City lastCity = cityList.get(cityListSize);
            getWeatherByCity(lastCity.getCityName());

        } else {
            mTxtViewNoCity.setText(getResources().getText(R.string.search_result));
            mTxtViewNoCity.setVisibility(View.VISIBLE);
        }
    }


    private void getWeatherByCity(String query){
        ApiInterface apiInterface = RetrofitAPIClient.getClient().create(ApiInterface.class);
        Call call = apiInterface.doGetWeatherByCityName(query, RetrofitAPIClient.API_KEY);
        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Call<OpenWeatherMap> call, Response<OpenWeatherMap> response) {
                if (response.code() == 200) {
                    clearAllViews();
                    mTxtViewNoCity.setVisibility(View.GONE);
                    mOpenWeatherMap = response.body();
                    mTxtCity.setText(String.format("%s , %s", mOpenWeatherMap.getName(), mOpenWeatherMap.getSys().getCountry()));
                    mTxtDescription.setText(String.format("Desc - %s", mOpenWeatherMap.getWeather().get(0).describeContents()));
                    mTxtLastUpdate.setText(String.format("Last updated: %s", Helper.getDateNow()));
                    mTxtHumidity.setText(String.format("Humidity - %s", mOpenWeatherMap.getMain().getHumidity()));
                    mTxtTime.setText(String.format("Sunrise - %s/ Sunset - %s", Helper.unixTimeStampToTime(mOpenWeatherMap.getSys().getSunrise()),
                            Helper.unixTimeStampToTime(mOpenWeatherMap.getSys().getSunset())));
                    mTxtCelsius.setText(String.format("Temprature - %s.2f", mOpenWeatherMap.getMain().getTemp()));
                    List<WeatherList> weatherList = mOpenWeatherMap.getWeather();
                    RequestCreator requestCreator = Picasso.with(getApplicationContext()).load(Helper.getIcon(weatherList.get(0).getIcon()));
                    requestCreator.into(mImageView);

                    City city = new City();
                    city.setCityName(mOpenWeatherMap.getName());
                    //remove old record from db
                    mAppSQLiteHelper.deleteAll();
                    // add City to database;
                    mAppSQLiteHelper.insertCity(city);

                }else if (response.code() == 404){
                    mTxtDescription.setText("City not found, please try with full city name..");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("TAG", call.toString() + " Server response failed::: ");
                mTxtCity.setText("Error while getting weather info..");
                call.cancel();

            }
        });
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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                getWeatherByCity(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    public void clearAllViews(){

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
}
