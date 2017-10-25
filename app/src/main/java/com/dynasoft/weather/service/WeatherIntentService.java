package com.dynasoft.weather.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;

import com.dynasoft.weather.activity.MainActivity;
import com.dynasoft.weather.client.RetrofitAPIClient;
import com.dynasoft.weather.helper.Constants;
import com.dynasoft.weather.helper.Helper;
import com.dynasoft.weather.interfaces.ApiInterface;
import com.dynasoft.weather.model.City;
import com.dynasoft.weather.model.OpenWeatherMap;
import com.dynasoft.weather.model.WeatherList;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sundeep on 10/24/17.
 */

public class WeatherIntentService extends IntentService {

    public static String TAG = WeatherIntentService.class.getSimpleName();

    private OpenWeatherMap mOpenWeatherMap;

    public WeatherIntentService() {
        super("WeatherIntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        String datString = intent.getStringExtra(Constants.EXTENDED_DATA_STATUS);
        Log.d(TAG, "string from activity are ..."+ datString+ " JHD" );
        getWeatherByCity(datString);

    }


    private void getWeatherByCity(String query){

        ApiInterface apiInterface = RetrofitAPIClient.getClient().create(ApiInterface.class);
        Call call = apiInterface.doGetWeatherByCityName(query, RetrofitAPIClient.API_KEY);

        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Call<OpenWeatherMap> call, Response<OpenWeatherMap> response) {
                if (response.code() == 200) {
                    mOpenWeatherMap = response.body();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Constants.EXTENDED_DATA_STATUS, mOpenWeatherMap);
                    Intent localIntent = new Intent();
                    localIntent.setAction(Constants.ACTION_RESP);
                    localIntent.addCategory(Intent.CATEGORY_DEFAULT);
                    localIntent.putExtra(Constants.EXTENDED_DATA_STATUS,bundle);
                    sendBroadcast(localIntent);

                    // Broadcasts the Intent to receivers in this app.
                    //LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(localIntent);

                }else {
                    Log.d("TAG", call.toString() + " Server response failed::: ");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("TAG", call.toString() + " Server response failed::: ");
                call.cancel();

            }
        });
    }
}
