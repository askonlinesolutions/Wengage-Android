package com.askonlinesolutions.wengage.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CodeVerifyRequest {

    @SerializedName("userId")
    @Expose
    public String userId;
    @SerializedName("verificationCode")
    @Expose
    public String verificationCode;

    public CodeVerifyRequest(String userId, String verificationCode) {
        this.userId = userId;
        this.verificationCode = verificationCode;
    }

}
