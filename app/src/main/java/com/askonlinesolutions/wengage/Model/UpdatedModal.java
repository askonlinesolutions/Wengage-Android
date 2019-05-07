package com.askonlinesolutions.wengage.Model;

public class UpdatedModal {
    /**
     * contactId : con:175&218
     * userId : 175
     * fullName : Anthony Scalzo12
     * knownByName : Anthony
     * email : anthony@myconxn.com
     * photoURL : https://s3.amazonaws.com/wengageapp/profile/1543952159589_668922.jpg
     * influencer : 0
     */

    private String contactId;


    private boolean isSelected;
    private int userId;
    private String fullName;
    private String knownByName;
    private String email;
    private String photoURL;
    private int influencer;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getKnownByName() {
        return knownByName;
    }

    public void setKnownByName(String knownByName) {
        this.knownByName = knownByName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public int getInfluencer() {
        return influencer;
    }

    public void setInfluencer(int influencer) {
        this.influencer = influencer;
    }
}
