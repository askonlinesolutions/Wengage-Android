package com.askonlinesolutions.wengage.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupRequest {
    @SerializedName("email")
    @Expose
    private String email;

    public Double getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(Double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public Double getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(Double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @SerializedName("currentLatitude")
    @Expose
    private Double currentLatitude;
    @SerializedName("currentLongitude")
    @Expose
    private Double currentLongitude;
    @SerializedName("currentCity")
    @Expose
    private String currentCity;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("password")
    @Expose
    private String password;

    public SignupRequest(String email, String fullName, String userName, String password, String phone, String countrycode,
                         Double latitude, Double longitude, String cityLocation) {
        this.email = email;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.countryCode = countrycode;
        this.currentLatitude = latitude;
        this.currentLongitude = longitude;
        this.currentCity = cityLocation;
    }

    @SerializedName("phone")
    @Expose
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
