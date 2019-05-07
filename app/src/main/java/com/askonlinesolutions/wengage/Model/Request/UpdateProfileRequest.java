package com.askonlinesolutions.wengage.Model.Request;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.io.Serializable;


public class UpdateProfileRequest implements Serializable {


    /**
     * knownByName : Prerna Sonkar
     * DOB : 1994-08-12
     * gender : Female
     * city : Noida
     * photo :
     */

    private String knownByName;
    private String DOB;
    private String gender;
    private String city;
    private String photo;
    private String incomeLevel;
    private String googleAddress;
    private String googlePlaceId;

    public JSONArray getLanguage() {
        return language;
    }

    public void setLanguage(JSONArray language) {
        this.language = language;
    }

    private JSONArray language;




    public UpdateProfileRequest(String knownByName, String DOB, String gender, String city, String photo,
                                String incomeLevel,String place,String placeId) {
        this.knownByName = knownByName;
        this.DOB = DOB;
        this.gender = gender;
        this.city = city;
        this.photo = photo;
        this.incomeLevel = incomeLevel;
        this.googleAddress = place;
        this.googlePlaceId = placeId;
    }

    protected UpdateProfileRequest(Parcel in) {
        knownByName = in.readString();
        DOB = in.readString();
        gender = in.readString();
        city = in.readString();
        photo = in.readString();
        incomeLevel = in.readString();
        googleAddress = in.readString();
        googlePlaceId = in.readString();

    }


    public String getGoogleAddress() {
        return googleAddress;
    }

    public void setGoogleAddress(String googleAddress) {
        this.googleAddress = googleAddress;
    }

    public String getGooglePlaceId() {
        return googlePlaceId;
    }

    public void setGooglePlaceId(String googlePlaceId) {
        this.googlePlaceId = googlePlaceId;
    }
    public String getIncomeLevel() {
        return incomeLevel;
    }

    public void setIncomeLevel(String incomeLevel) {
        this.incomeLevel = incomeLevel;
    }

    public String getKnownByName() {
        return knownByName;
    }

    public void setKnownByName(String knownByName) {
        this.knownByName = knownByName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}

