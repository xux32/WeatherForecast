package com.example.xux32.weatherfroecast;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.WrapperListAdapter;

import com.example.xux32.weatherfroecast.adapter.WeatherAdapter;
import com.example.xux32.weatherfroecast.data.Datadelegate;
import com.example.xux32.weatherfroecast.data.GetData;
import com.example.xux32.weatherfroecast.data.WeatherInfo;
import com.example.xux32.weatherfroecast.http.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Weather extends AppCompatActivity implements Datadelegate {

    ListView listFuture;
    TextView mWeather;
    Button mButton;
    WeatherInfo resultStr;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
               // resultStr = (WeatherInfo) msg.obj;
                System.out.println(resultStr);
                listFuture.setAdapter(new WeatherAdapter(resultStr, mWeather));
                // mWeather.setText(resultStr);
            }

        }
    };

    private class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            startActivity(new Intent(Weather.this,WeatherDataBase.class));
        }
    }

    //
//    class GetDataThread implements Callable {
//        @Override
//        public Object call() throws Exception {
//            String url = "http://apicloud.mob.com/v1/weather/query?province=%20%E5%8C%97%E4%BA%AC&key=14b24cbee99f0&city=%E5%8C%97%E4%BA%AC";
//            OkHttpUtils mOkHttpUtils = new OkHttpUtils();
//            String result = mOkHttpUtils.loadStringFromUrl(url);
//            return result;
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        listFuture = (ListView) findViewById(R.id.id_future);
        mWeather = (TextView) findViewById(R.id.id_weather);
        mButton = (Button) findViewById(R.id.id_button);
        mButton.setOnClickListener(new MyClickListener() );

        new Thread(new MyThread()).start();
        //System.out.println(resultStr);

        //   mGetData.setmDatadelegate(this);
//        try{
//            resultStr = mGetData.getPackage();
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        System.out.println(resultStr);


//
//        String result = "";
//        try{
//            result = getPackage();
//        }catch(ExecutionException e){
//            e.printStackTrace();
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//
//        System.out.println(result);

//         String url = "http://apicloud.mob.com/v1/weather/query?province=%20%E5%8C%97%E4%BA%AC&key=14b24cbee99f0&city=%E5%8C%97%E4%BA%AC";
//         String result;
//        OkHttpUtils mOkHttpUtils = new OkHttpUtils();
//        result = mOkHttpUtils.loadStringFromUrl(url);
//        System.out.println(result);
    }

    @Override
    public void pass(String result) {
        // resultStr = result;
        System.out.print(result);
    }

    public class MyThread implements Runnable {

        @Override
        public void run() {
            try {
                resultStr = GetData.getPackage();
                // System.out.println(data);
                Message message = Message.obtain();
               // message.obj = data;
                message.what = 1;
                handler.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //
//    private String getPackage()throws ExecutionException, InterruptedException{
//        ExecutorService pool = Executors.newFixedThreadPool(1);
//        //创建两个有返回值的任务
//        Callable c1 = new GetDataThread();
//        //执行任务并获取Future对象
//        Future f1 = pool.submit(c1);
//        String str = f1.get().toString();
//        return str;
//    }
    public class abc extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {


            super.onPostExecute(s);
        }
    }
}
