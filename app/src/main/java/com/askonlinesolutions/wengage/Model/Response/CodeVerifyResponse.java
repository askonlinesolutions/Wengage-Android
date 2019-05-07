package com.askonlinesolutions.wengage.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CodeVerifyResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("isProfileCompleted")
    @Expose
    private Integer isProfileCompleted;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getIsProfileCompleted() {
        return isProfileCompleted;
    }

    public void setIsProfileCompleted(Integer isProfileCompleted) {
        this.isProfileCompleted = isProfileCompleted;
    }
}
