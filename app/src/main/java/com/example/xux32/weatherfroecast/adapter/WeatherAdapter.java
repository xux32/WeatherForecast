package com.example.xux32.weatherfroecast.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xux32.weatherfroecast.R;
import com.example.xux32.weatherfroecast.Weather;
import com.example.xux32.weatherfroecast.data.WeatherInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xux32 on 2016/8/9.
 */
public class WeatherAdapter extends BaseAdapter {

    TextView mToday;
    String str;
    WeatherInfo info;

    public WeatherAdapter(WeatherInfo weaturInfo, TextView mToday) {
        // this.weaturInfo = weaturInfo;
        this.mToday = mToday;
        this.info = weaturInfo;
        //parse(weaturInfo);
    }

    @Override
    public int getCount() {
        return info.getResult().get(0).getFuture().size()-1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.item_weather, null);
            holder.mWeather = (TextView) view.findViewById(R.id.id_weather);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        //if (info.getResult().get(0).getFuture().get(i).getWeek().equals("今天")) {
            mToday.setText(info.getResult().get(0).getFuture().get(0).toString());
       // } else {
            holder.mWeather.setText(info.getResult().get(0).getFuture().get(i+1).toString());
       // }
        return view;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    static class ViewHolder {
        TextView mWeather;
    }

//    private void parse(String str) {
//        Gson gson = new Gson();
//
//    }
}
