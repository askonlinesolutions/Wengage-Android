package com.askonlinesolutions.wengage.Fragment.Sub.eventsInYourCity.vo;

import java.io.Serializable;

public class InterestedRequest implements Serializable{


    /**
     * userId : 88
     * eventId : 1AtZA4YGkdjldsi
     * isInterested : 1
     */

    private int userId;
    private String eventId;
    private int isInterested;

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

    public int getIsInterested() {
        return isInterested;
    }

    public void setIsInterested(int isInterested) {
        this.isInterested = isInterested;
    }
}
