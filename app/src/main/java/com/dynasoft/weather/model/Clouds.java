package com.dynasoft.weather.model;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sundeep on 10/11/17.
 */

public class Clouds
{

    @SerializedName("all")
    @Expose
    private Integer all;

    protected Clouds(Parcel in) {
        this.all = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Clouds() {
    }

    /**
     *
     * @param all
     */
    public Clouds(Integer all) {
        super();
        this.all = all;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public int describeContents() {
        return 0;
    }

}
