package com.dynasoft.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sundeep on 10/11/17.
 */

public class Rain {

    @SerializedName("3h")
    @Expose
    private String rainHours;

    public Rain(String rainHours) {
        this.rainHours = rainHours;
    }
    public String getRainHours() {
        return rainHours;
    }

    public void setRainHours(String rainHours) {
        this.rainHours = rainHours;
    }


}
