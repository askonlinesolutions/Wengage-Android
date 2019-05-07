package com.askonlinesolutions.wengage.network;

import android.app.Activity;
import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponseListner {

    OnResponseInterface onResponseInterface;
    String message;
    private Context context;

    public ResponseListner(OnResponseInterface onResponseInterface) {
        this.onResponseInterface = onResponseInterface;
    }

    public void getResponse(Context context, Call call) {


        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

               message = response.message();

               onResponseInterface.onApiResponse(response.body());
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                onResponseInterface.onApiFailure(t.getMessage());

            }
        });

    }

    public void getResponse1(Activity context, Call call) {

        onResponseInterface = (OnResponseInterface) context;

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                message = response.message();

                onResponseInterface.onApiResponse(response.body());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                onResponseInterface.onApiFailure(message);
            }
        });

    }
}
