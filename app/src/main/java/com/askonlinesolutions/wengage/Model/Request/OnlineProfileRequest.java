package com.askonlinesolutions.wengage.Model.Request;

import java.io.Serializable;


public class OnlineProfileRequest implements Serializable{

    private int isOnline;


    public OnlineProfileRequest( int isOnline) {
        this.isOnline = isOnline;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

}

