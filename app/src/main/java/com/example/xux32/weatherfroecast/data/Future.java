package com.example.xux32.weatherfroecast.data;

/**
 * Created by xux32 on 2016/8/11.
 */
public class Future {
    public String date;
    public String dayTime;
    public String night;
    public String temperature;
    public String week;
    public String wind;

    public Future() {

    }

    public Future(String date, String dayTime, String night, String temperature, String week, String wind) {
        this.date = date;
        this.dayTime = dayTime;
        this.night = night;
        this.temperature = temperature;
        this.week = week;
        this.wind = wind;
    }
}
