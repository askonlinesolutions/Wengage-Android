package com.askonlinesolutions.wengage.CallBacks;

import com.google.gson.JsonObject;

public interface NetworkCallback {
    void onNetworkSuccess(JsonObject result, int status, int reqeustCode);
    void onNetworkTimeout(String message);
}
