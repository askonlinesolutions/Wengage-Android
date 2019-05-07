package com.askonlinesolutions.wengage.Model.Request;

import java.io.Serializable;

/**
 * Created by Rakhi on 12/4/2018.
 */
public class UpdateCustProfileRequest implements Serializable{
    /*[paramDict setValue:objModel.strOwnDescription forKey:@"description"];
    [paramDict setValue:objModel.strHomeTown forKey:@"hometown"];
    [paramDict setValue:objModel.strFavCity forKey:@"favoutiteCity"];
    [paramDict setValue:objModel.strFavRest forKey:@"favoutiteRestaurant"];
    [paramDict setValue:objModel.strExtraHrsDetail forKey:@"shortDesc"];
    [paramDict setValue:objModel.strYourWorkPassion forKey:@"work"];
    [paramDict setValue:objModel.strSocialMedia forKey:@"socialMedia"];*/

    private String description;
    private String hometown;
    private String favoutiteCity;
    private String favoutiteRestaurant;
    private String shortDesc;
    private String work;
    private String socialMedia;

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

    public String getFavoutiteCity() {
        return favoutiteCity;
    }

    public void setFavoutiteCity(String favoutiteCity) {
        this.favoutiteCity = favoutiteCity;
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
