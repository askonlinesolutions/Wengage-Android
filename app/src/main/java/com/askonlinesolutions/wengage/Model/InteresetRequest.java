package com.askonlinesolutions.wengage.Model;

public class InteresetRequest {
    private int userId;
    private String venueId;
    private String isInterested;

    public InteresetRequest(int userId, String venue_id, String status) {
        this.userId = userId;
        this.venueId = venue_id;
        this.isInterested = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getIsInterested() {
        return isInterested;
    }

    public void setIsInterested(String isInterested) {
        this.isInterested = isInterested;
    }


}
