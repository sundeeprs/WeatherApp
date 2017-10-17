package com.dynasoft.weather.client;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sundeep on 10/12/17.
 */

/*
 * ApiClient class provided Retrofit instance.
 */
public class RetrofitAPIClient {

    private static Retrofit retrofit = null;
    public static String API_KEY = "92917bbd4843d3585278df79c6b8223a";
    public static String BASE_URL = "http://api.openweathermap.org";

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    public static void setClient() {

        retrofit = getClient();
    }
}
