package com.dynasoft.weather.model;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sundeep on 10/11/17.
 */

public class Sys
{
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("sunrise")
    @Expose
    private Integer sunrise;
    @SerializedName("sunset")
    @Expose
    private Integer sunset;


    /**
     * No args constructor for use in serialization
     *
     */
    public Sys() {
    }

    public Sys(int type, String id, Double message, String country, Integer sunrise, Integer sunset) {
        this.type = type;
        this.id = id;
        this.message = message;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(country);
        dest.writeValue(sunrise);
        dest.writeValue(sunset);
    }

    public int describeContents() {
        return 0;
    }
}
