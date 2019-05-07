package com.askonlinesolutions.wengage.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("userName")
    @Expose
    public String userName;
    @SerializedName("password")
    @Expose
    public String password;


    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
