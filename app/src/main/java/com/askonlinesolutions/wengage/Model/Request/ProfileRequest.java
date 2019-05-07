package com.askonlinesolutions.wengage.Model.Request;

import java.io.Serializable;

/**
 * Created by Rakhi on 12/5/2018.
 */
public class ProfileRequest implements Serializable{

    /**
     * userId : 162
     * profileId :
     */

    private int userId;
    private String profileId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
}
