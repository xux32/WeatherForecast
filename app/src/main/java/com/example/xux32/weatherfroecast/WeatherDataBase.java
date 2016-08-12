package com.example.xux32.weatherfroecast;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.xux32.weatherfroecast.data.GetData;
import com.example.xux32.weatherfroecast.data.WeatherInfo;
import com.example.xux32.weatherfroecast.database.DBManger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherDataBase extends AppCompatActivity {

    TextView mTodayWeather;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView mList;
    List<Map<String,String>> fWeather = new ArrayList<Map<String, String>>();
    //List<Map<String,String>> tWeather = new ArrayList<Map<String, String>>();
    String todayWeather = "";
    DBManger manger;
    SharedPreferences sharedPreferences;

    SimpleAdapter adapter;

    private Handler mHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
               // fWeather = ( List<Map<String,String>>) msg.obj;
                System.out.println("----->" + fWeather);
                mSwipeRefreshLayout.setRefreshing(false);
                //mList.setAdapter(new SimpleAdapter(WeatherDataBase.this,fWeather,R.layout.item_weather_database,new String[]{"weather"}, new int[]{R.id.id_item}));
                mTodayWeather.setText(todayWeather);
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_data_base);

        mTodayWeather = (TextView) findViewById(R.id.id_today) ;
        mList = (ListView) findViewById(R.id.id_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe);

        manger = new DBManger(WeatherDataBase.this);
        sharedPreferences = getSharedPreferences("todayweather", Activity.MODE_PRIVATE);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright),getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_dark),getResources().getColor(android.R.color.holo_purple));

        initData();
        mTodayWeather.setText(todayWeather);
        adapter =  new SimpleAdapter(WeatherDataBase.this,fWeather,R.layout.item_weather_database,new String[]{"date","daytime","night","temperature","week","wind"},
                new int[]{R.id.id_date,R.id.id_daytime,R.id.id_night,R.id.id_temperature,R.id.id_week,R.id.id_wind});

        mList.setAdapter(adapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                        WeatherInfo weatherInfo = new WeatherInfo();
                        try {
                            weatherInfo = GetData.getPackage();
                        }catch (IOException e){
                            System.out.println("未获取到数据");
                        }
                        List<Map<String,String>> tWeather = new ArrayList<Map<String, String>>();
                        int size = weatherInfo.getResult().get(0).getFuture().size();
                        if(fWeather.size() != 0){
                            fWeather.clear();
                        }
                        for(int i=0; i<size; i++){
                            if(weatherInfo.getResult().get(0).getFuture().get(i).getWeek().equals("今天")){
                                Map<String,String> mapToday = new HashMap<String, String>();
                                mapToday.put("date",weatherInfo.getResult().get(0).getFuture().get(i).getDate());
                                mapToday.put("daytime",weatherInfo.getResult().get(0).getFuture().get(i).getDayTime());
                                mapToday.put("night",weatherInfo.getResult().get(0).getFuture().get(i).getNight());
                                mapToday.put("temperature",weatherInfo.getResult().get(0).getFuture().get(i).getTemperature());
                                mapToday.put("week",weatherInfo.getResult().get(0).getFuture().get(i).getWeek());
                                mapToday.put("wind",weatherInfo.getResult().get(0).getFuture().get(i).getWind());
                                tWeather.add(mapToday);
                            }else {
                                Map<String,String> mapFuture = new HashMap<String, String>();
                                mapFuture.put("date", weatherInfo.getResult().get(0).getFuture().get(i).getDate());
                                mapFuture.put("daytime", weatherInfo.getResult().get(0).getFuture().get(i).getDayTime());
                                mapFuture.put("night", weatherInfo.getResult().get(0).getFuture().get(i).getNight());
                                mapFuture.put("temperature", weatherInfo.getResult().get(0).getFuture().get(i).getTemperature());
                                mapFuture.put("week", weatherInfo.getResult().get(0).getFuture().get(i).getWeek());
                                mapFuture.put("wind", weatherInfo.getResult().get(0).getFuture().get(i).getWind());
                                fWeather.add(mapFuture);
                            }
                        }
                        /////当天天气存放在sharedperferences数据库中
                        todayWeather = tWeather.get(0).toString();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("todayweather",todayWeather);
                        editor.commit();
                        //System.out.println("---------->" + fWeather);
                        ////未来几天天气存放在SQLite数据库中
                        manger.add(fWeather);

                        mHandle.sendEmptyMessage(0);
                        //
                    }
                }).start();
            }
        });
    }
    private void initData(){

        List<WeatherInfo.ResultBean.FutureBean> list = new ArrayList<WeatherInfo.ResultBean.FutureBean>();
        list = manger.query();
        if (list.size() != 0){
            for(WeatherInfo.ResultBean.FutureBean weather : list){
                Map<String,String> map = new HashMap<String,String>();
                map.put("date",weather.getDate());
                map.put("daytime",weather.getDayTime());
                map.put("night",weather.getNight());
                map.put("temperature",weather.getTemperature());
                map.put("week",weather.getWeek());
                map.put("wind",weather.getWind());
                fWeather.add(map);
            }
        }

        ///轻量级数据库
        todayWeather = sharedPreferences.getString("todayweather","");

    }
}
