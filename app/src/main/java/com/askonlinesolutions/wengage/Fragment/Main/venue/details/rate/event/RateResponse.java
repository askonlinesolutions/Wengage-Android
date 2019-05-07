package com.askonlinesolutions.wengage.Fragment.Main.venue.details.rate.event;

import java.io.Serializable;

/**
 * Created by Rakhi on 12/11/2018.
 */
public class RateResponse implements Serializable{

    /**
     * status : 1
     * message : Your Rating Saved Successfully
     * ratingData : {"rating":3.5,"review":"Nice Event","id":"1Ae0Z4oGkSQzZx4","type":"event","userId":93,"createdAt":"2018-11-29T07:08:21.828Z","updatedAt":"2018-11-29T07:08:21.828Z"}
     */

    private int status;
    private String message;
    private RatingDataBean ratingData;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RatingDataBean getRatingData() {
        return ratingData;
    }

    public void setRatingData(RatingDataBean ratingData) {
        this.ratingData = ratingData;
    }

    public static class RatingDataBean {
        /**
         * rating : 3.5
         * review : Nice Event
         * id : 1Ae0Z4oGkSQzZx4
         * type : event
         * userId : 93
         * createdAt : 2018-11-29T07:08:21.828Z
         * updatedAt : 2018-11-29T07:08:21.828Z
         */

        private double rating;
        private String review;
        private String id;
        private String type;
        private int userId;
        private String createdAt;
        private String updatedAt;

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }

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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}
