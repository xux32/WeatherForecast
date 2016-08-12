package com.example.xux32.weatherfroecast.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xux32.weatherfroecast.data.WeatherInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.xux32.weatherfroecast.data.WeatherInfo.*;

/**
 * Created by xux32 on 2016/8/12.
 */
public class DBManger {
    DBHelper dbHelper;
    SQLiteDatabase db;

    public DBManger(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public void add(List<Map<String,String>> weathers) {

        deleteWeather();
        db.beginTransaction();
        try {

            for (int i = 0; i< weathers.size(); i++) {
                db.execSQL("INSERT INTO weather VALUES(null,?,?,?,?,?,?)", new Object[]{weathers.get(i).get("date"),weathers.get(i).get("daytime"), weathers.get(i).get("night"),weathers.get(i).get("temperature"), weathers.get(i).get("week"),weathers.get(i).get("wind")});
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();

        }
    }
//
//    public void updateAge(WeatherInfo.ResultBean.FutureBean weather){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("age",person.age);
//        db.update("weather",contentValues,"name = ?",new String[]{person.name});
//    }

    public void deleteWeather(){

        db.delete("weather",null,null);
    }

    public List<WeatherInfo.ResultBean.FutureBean> query(){

        List<WeatherInfo.ResultBean.FutureBean> weathers = new ArrayList<WeatherInfo.ResultBean.FutureBean>();
        Cursor cursor = queryTheCursor();
        while(cursor.moveToNext()){
            ResultBean.FutureBean weather = new ResultBean.FutureBean();
            weather.setDate(cursor.getString(cursor.getColumnIndex("date"))) ;
            weather.setDayTime(cursor.getString(cursor.getColumnIndex("daytime")));
            weather.setNight(cursor.getString(cursor.getColumnIndex("night")));
            weather.setTemperature(cursor.getString(cursor.getColumnIndex("temperature")));
            weather.setWeek(cursor.getString(cursor.getColumnIndex("week")));
            weather.setWind(cursor.getString(cursor.getColumnIndex("wind")));
            weathers.add(weather);
        }
        cursor.close();
        return weathers;
    }

    public Cursor queryTheCursor(){
        Cursor cursor = db.rawQuery("SELECT * FROM weather", null);
        return cursor;
    }
    public void closeDB(){
        db.close();
    }
}
