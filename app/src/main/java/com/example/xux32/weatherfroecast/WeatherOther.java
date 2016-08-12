package com.example.xux32.weatherfroecast;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xux32.weatherfroecast.adapter.WeatherOtherAdapter;
import com.example.xux32.weatherfroecast.data.GetData;
import com.example.xux32.weatherfroecast.data.WeatherInfo;

import org.w3c.dom.Text;

import java.io.IOException;

public class WeatherOther extends AppCompatActivity {

    TextView mText;
    TextView mTextInfo;
    ExpandableListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_other);

        mText = (TextView) findViewById(R.id.id_today);
        mList = (ExpandableListView) findViewById(R.id.id_future);
        mTextInfo = (TextView) findViewById(R.id.id_todayinfo);

        MyTask myTask = new MyTask();
        myTask.execute();
    }

    private class MyTask extends AsyncTask<String,Integer,WeatherInfo>{


        @Override
        protected WeatherInfo doInBackground(String... strings) {

            WeatherInfo data = new WeatherInfo();
           try {
               data = GetData.getPackage();
           }catch (IOException e){
               System.out.println("未获取到数据");
           }
            return data;
        }

        @Override
        protected void onPostExecute(WeatherInfo s) {
            super.onPostExecute(s);
            mList.setAdapter(new WeatherOtherAdapter(s,mText,mTextInfo,WeatherOther.this));
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onCancelled(WeatherInfo s) {
            super.onCancelled(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
