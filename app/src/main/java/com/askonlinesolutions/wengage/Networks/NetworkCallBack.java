package com.askonlinesolutions.wengage.Networks;

public interface NetworkCallBack<E> {

    void onSuccess(E data, int requestCode, int statusCode);

    void onError(String msg);
}
