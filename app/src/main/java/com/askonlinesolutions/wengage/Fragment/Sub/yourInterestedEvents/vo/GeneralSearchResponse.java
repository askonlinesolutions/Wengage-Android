package com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.vo;

import java.util.List;

public class GeneralSearchResponse {


    /**
     * status : 1
     * message : Searched Data
     * searchedData : [{"name":"James Zabiela","eventId":"1AtZA4YGkdjldsi","type":"EVENT","avgRating":0,"reviewCount":0},{"name":"Morgan James","eventId":"1A8ZAfwGkd1IESb","type":"EVENT","avgRating":0,"reviewCount":0},{"name":"James Bay","eventId":"177ZvfG6CG6Tyig","type":"EVENT","avgRating":0,"reviewCount":0},{"name":"Colin James - Miles To Go Tour","eventId":"168ZAfVG8ZAC2e5G","type":"EVENT","avgRating":0,"reviewCount":0},{"name":"The Jam Factory","venueId":"KovZ917AO7i","type":"VENUE","avgRating":0,"reviewCount":0}]
     */

    private int status;
    private String message;
    private List<SearchedDataList> searchedData;

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

    public List<SearchedDataList> getSearchedData() {
        return searchedData;
    }

    public void setSearchedData(List<SearchedDataList> searchedData) {
        this.searchedData = searchedData;
    }

    public static class SearchedDataList {
        /**
         * name : James Zabiela
         * eventId : 1AtZA4YGkdjldsi
         * type : EVENT
         * avgRating : 0
         * reviewCount : 0
         * venueId : KovZ917AO7i
         */

        private String name;
        private String eventId;
        private String type;
        private String avgRating;
        private int reviewCount;
        private String venueId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAvgRating() {
            return avgRating;
        }

        public void setAvgRating(String avgRating) {
            this.avgRating = avgRating;
        }

        public int getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(int reviewCount) {
            this.reviewCount = reviewCount;
        }

        public String getVenueId() {
            return venueId;
        }

        public void setVenueId(String venueId) {
            this.venueId = venueId;
        }
    }
}
