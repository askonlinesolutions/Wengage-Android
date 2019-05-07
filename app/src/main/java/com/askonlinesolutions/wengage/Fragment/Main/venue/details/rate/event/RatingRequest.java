package com.askonlinesolutions.wengage.Fragment.Main.venue.details.rate.event;

import java.io.Serializable;

/**
 * Created by Rakhi on 12/11/2018.
 */
public class RatingRequest implements Serializable{

    /**
     * id : 1Ae0Z4oGkSQzZx4
     * type : event
     * userId : 93
     * rating : 3.5
     * review : Nice Event
     */

    private String id;
    private String type;
    private int userId;
    private float rating;
    private String review;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
