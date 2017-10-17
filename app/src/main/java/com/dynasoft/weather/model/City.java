package com.dynasoft.weather.model;

/**
 * Created by sundeep on 10/16/17.
 */

public class City {

    public int cityID;

    public String cityName;

    public City() {

    }

    public City(int cityID, String cityName) {
        this.cityID = cityID;
        this.cityName = cityName;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                '}';
    }


}
