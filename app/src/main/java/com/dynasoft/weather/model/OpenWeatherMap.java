package com.dynasoft.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/*
 * Created by sundeep on 10/11/17.
 */

public class OpenWeatherMap implements Parcelable{

    private Coord coord;
    private List<WeatherList> weather;
    private String base;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private int dt;
    private Sys sys;
    private String visibility;
    private int id;
    private String name;
    private int cod;

    public OpenWeatherMap(Coord coord, List<WeatherList> weather, String base, Main main, Wind wind, Clouds clouds, int dt, Sys sys, String visibility, int id, String name, int cod) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.visibility = visibility;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }


    public OpenWeatherMap(){

    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
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

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        visibility = visibility;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.coord, flags);
        dest.writeTypedList(this.weather);
        dest.writeString(this.base);
        dest.writeParcelable(this.main, flags);
        dest.writeParcelable(this.wind, flags);
        dest.writeParcelable(this.clouds, flags);
        dest.writeInt(this.dt);
        dest.writeParcelable(this.sys, flags);
        dest.writeString(this.visibility);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.cod);
    }

    protected OpenWeatherMap(Parcel in) {
        this.coord = in.readParcelable(Coord.class.getClassLoader());
        this.weather = in.createTypedArrayList(WeatherList.CREATOR);
        this.base = in.readString();
        this.main = in.readParcelable(Main.class.getClassLoader());
        this.wind = in.readParcelable(Wind.class.getClassLoader());
        this.clouds = in.readParcelable(Clouds.class.getClassLoader());
        this.dt = in.readInt();
        this.sys = in.readParcelable(Sys.class.getClassLoader());
        this.visibility = in.readString();
        this.id = in.readInt();
        this.name = in.readString();
        this.cod = in.readInt();
    }

    public static final Creator<OpenWeatherMap> CREATOR = new Creator<OpenWeatherMap>() {
        @Override
        public OpenWeatherMap createFromParcel(Parcel source) {
            return new OpenWeatherMap(source);
        }

        @Override
        public OpenWeatherMap[] newArray(int size) {
            return new OpenWeatherMap[size];
        }
    };
}
