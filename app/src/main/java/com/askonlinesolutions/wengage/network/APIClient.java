package com.askonlinesolutions.wengage.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private final static String BASE_URL = "http://107.21.193.184/";
    private static APIClient sApiClient = new APIClient();
    private ApiInterface mApiInterface;
    private Retrofit retrofit;

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public APIClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().readTimeout(45, TimeUnit.SECONDS)
                .connectTimeout(45, TimeUnit.SECONDS);
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Content-Type", "application/json").build();
                return chain.proceed(request);
            }
        });
/* if (MainApp.isDebuggable) {
            okHttpClientBuilder.addNetworkInterceptor(new StethoInterceptor());
        }*/


        //  okHttpClientBuilder.addNetworkInterceptor(new AuthorizationInterceptor());

        //  OkHttpClient okHttpClient = okHttpClientBuilder.build();
/*
        Picasso.Builder picassoBuilder = new Picasso.Builder(MainApp.instance);
        picassoBuilder.downloader(new OkHttp3Downloader(okHttpClient));
//        if (MainApp.isDebuggable) picassoBuilder.memoryCache(Cache.NONE);
        Picasso picasso = picassoBuilder.build();
        Picasso.setSingletonInstance(picasso);*/

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClientBuilder.build())
                .build();
        mApiInterface = retrofit.create(ApiInterface.class);
    }

    public static APIClient getInstance() {
        return sApiClient;
    }

    public ApiInterface getApiInterface() {
        return mApiInterface;
    }

}
