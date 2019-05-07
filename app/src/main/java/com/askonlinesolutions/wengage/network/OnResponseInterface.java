package com.askonlinesolutions.wengage.network;

import retrofit2.Response;

public interface OnResponseInterface {
    public void onApiResponse(Object response);
    public void onApiFailure(String message);
}
