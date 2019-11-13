package com.zww149.shoppingmall149;

import android.app.Application;
import android.content.Context;


import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 作用：整个软件
 */
public class MyApplication extends Application {

    public static Context getContext() {
        return mcontext;
    }

    private static Context mcontext;
    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = this;
        /**
         * 初始化okhttpUtils
         */
        initOkhttpClient();
    }

    private void initOkhttpClient() {
      OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
