package com.askonlinesolutions.wengage.Fragment.Sub.eventsInYourCity.vo;

import java.io.Serializable;

public class BookmarkRequest implements Serializable {


    /**
     * userId : 88
     * eventId : 1Ae0Z4oGkSQzZx4
     * isBookmarked : 1
     */

    private int userId;
    private String eventId;
    private int isBookmarked;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public int getIsBookmarked() {
        return isBookmarked;
    }

    public void setIsBookmarked(int isBookmarked) {
        this.isBookmarked = isBookmarked;
    }
}
