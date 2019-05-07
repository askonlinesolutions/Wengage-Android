package com.askonlinesolutions.wengage.Fragment.Main.profile;

import com.google.gson.JsonArray;

/**
 * Created by Vaibhav on 12/14/2018.
 */
public class UpdateProfileModal {
    /*[paramDict setValue:objModel.strOwnDescription forKey:@"description"];
   [paramDict setValue:objModel.strHomeTown forKey:@"hometown"];
   [paramDict setValue:objModel.strFavCity forKey:@"favoutiteCity"];
   [paramDict setValue:objModel.strFavRest forKey:@"favoutiteRestaurant"];
   [paramDict setValue:objModel.strExtraHrsDetail forKey:@"shortDesc"];
   [paramDict setValue:objModel.strYourWorkPassion forKey:@"work"];
   [paramDict setValue:objModel.strSocialMedia forKey:@"socialMedia"];*/

    private String knownByName;
    private String DOB;
    private String gender;
    private String city;
    private String photo;
    private String incomeLevel;
    private String description;
    private String hometown;
    private String favoutiteCity;
    private String favoutiteRestaurant;
    private String shortDesc;
    private String work;
    private String socialMedia;
    private JsonArray language;

    private String googlePlaceId;
    private String googleAddress;

    public JsonArray getLanguage() {
        return language;
    }

    public void setLanguage(JsonArray language) {
        this.language = language;
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

    public JsonArray getJsonArrayLanguage() {
        return language;
    }

    public void setJsonArrayLanguage(JsonArray jsonArrayLanguage) {
        this.language = jsonArrayLanguage;
    }


    public String getFavoutiteCity() {
        return favoutiteCity;
    }

    public void setFavoutiteCity(String favoutiteCity) {
        this.favoutiteCity = favoutiteCity;
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

    public String getIncomeLevel() {
        return incomeLevel;
    }

    public void setIncomeLevel(String incomeLevel) {
        this.incomeLevel = incomeLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getFavoutiteRestaurant() {
        return favoutiteRestaurant;
    }

    public void setFavoutiteRestaurant(String favoutiteRestaurant) {
        this.favoutiteRestaurant = favoutiteRestaurant;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }
}
