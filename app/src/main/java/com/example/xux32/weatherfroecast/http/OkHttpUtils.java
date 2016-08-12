package com.example.xux32.weatherfroecast.http;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by xux32 on 2016/8/9.
 */
public class OkHttpUtils {
    //保证只有一个okhttpclient对象
    private static final OkHttpClient okhttpUtils = new OkHttpClient();

    private static Request getRequestFromUrl(String url) {
        Request request = new Request.Builder().url(url).build();
        return request;
    }

    private static Response getResponseFromUrl(String url) {
        Request request = getRequestFromUrl(url);
        Response response = null;
        try {
            response = okhttpUtils.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static ResponseBody getResponseBodyFromUrl(String url) {
        Response response = getResponseFromUrl(url);
        if (response.isSuccessful()) {
            return response.body();
        }
        return null;
    }

    //定义共有方法,获取字符串
    public static String loadStringFromUrl(String url) {
        ResponseBody responseBody = getResponseBodyFromUrl(url);
        if (responseBody != null) {
            System.out.println(responseBody.toString());
            return responseBody.toString();
        }
        return null;
    }

    //定义共有方法,获取字节数组
    public static byte[] loadByteFromUrl(String url) {
        ResponseBody responseBody = getResponseBodyFromUrl(url);
        if (responseBody != null) {
            try {
                return responseBody.bytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
}
