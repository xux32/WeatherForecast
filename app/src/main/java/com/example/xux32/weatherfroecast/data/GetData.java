package com.example.xux32.weatherfroecast.data;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xux32 on 2016/8/9.
 */
public class GetData {

    public static String url = "http://apicloud.mob.com/v1/weather/query?province=%20%E5%8C%97%E4%BA%AC&key=14b24cbee99f0&city=%E5%8C%97%E4%BA%AC";
  //  public static GetData install = new GetData();
    private String result;
   // private Datadelegate mDatadelegate;

   // public void setmDatadelegate(Datadelegate mDatadelegate) {
 //       this.mDatadelegate = mDatadelegate;
  //  }
    //  public static GetData getInstall(){
  //      return install;
  //  }

    public static WeatherInfo getPackage() throws IOException{
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        Response response = mOkHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            Gson gson = new Gson();
            WeatherInfo weatherInfo = gson.fromJson(response.body().string(),WeatherInfo.class);
            return weatherInfo;
        }else{
            throw new IOException("Uexpection code" + response);
        }
//        mOkHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println("获取信息失败：");
//                Log.d("GetData",e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                result = response.body().string();
//                //System.out.println(result);
//                if(mDatadelegate != null)
//                    mDatadelegate.pass(result);
//
//            }
//        });
    }

}
