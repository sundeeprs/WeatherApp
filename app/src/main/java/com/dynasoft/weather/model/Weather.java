package com.dynasoft.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sundeep on 10/11/17.
 */

public class Weather
{

    @SerializedName("weather")
    @Expose
    private List<WeatherList> weather = null;
    @SerializedName("base")
    @Expose
    private String base;

    /**
     * No args constructor for use in serialization
     *
     */
    public Weather() {
    }

    /**
     *
     * @param id
     * @param dt
     * @param clouds
     * @param coord
     * @param wind
     * @param cod
     * @param sys
     * @param name
     * @param base
     * @param weather
     * @param main
     */
    public Weather(List<WeatherList> weather, String base) {
        super();

        this.weather = weather;
        this.base = base;
    }

    public List<WeatherList> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherList> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public int describeContents() {
        return 0;
    }
}
