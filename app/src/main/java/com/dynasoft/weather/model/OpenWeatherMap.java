package com.dynasoft.weather.model;

import java.util.List;

/**
 * Created by sundeep on 10/11/17.
 */

public class OpenWeatherMap {

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
}
