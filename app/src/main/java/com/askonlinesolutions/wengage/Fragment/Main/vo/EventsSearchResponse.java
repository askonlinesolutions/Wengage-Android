package com.askonlinesolutions.wengage.Fragment.Main.vo;

import java.util.List;

public class EventsSearchResponse {


    /**
     * status : 1
     * message : Events List
     * totalCount : 456
     * pageNum : 1
     * next : 1
     * totalPages : 46
     * eventsList : [{"name":"Didirri with Myles Castello","eventId":"1Ae0Z4oGkV-Gx8o","avgRating":0,"reviewCount":0},{"name":"Toronto Raptors vs. Golden State Warriors","eventId":"168ZAf9GIZA51aFu","avgRating":0,"reviewCount":0},{"name":"Toronto Marlies vs. Wilkes Barre Scranton Penguins","eventId":"1k7Zv4kaGA5ZSnE","avgRating":0,"reviewCount":0},{"name":"Harry Hudson: Can Cowboys Cry Tour with JP Saxe","eventId":"1k18v4vOGACgUrG","avgRating":0,"reviewCount":0},{"name":"The Barra MacNeils - Celtic Christmas","eventId":"1AvZZ4fGknP6N_o","avgRating":0,"reviewCount":0},{"name":"Big Sugar with Special Guests celebrate the life of Garry Lowe","eventId":"168ZA46vgZACF8Ck","avgRating":0,"reviewCount":0},{"name":"Terra Lightfoot with Lindi Ortega and Begonia","eventId":"1AvZZ4pGkwXXSvx","avgRating":0,"reviewCount":0},{"name":"Cherry Glazerr","eventId":"16tZA4EZAZACeC88","avgRating":0,"reviewCount":0},{"name":"Krewe of Carrollton, King Arthur & Alla","eventId":"1118v4k8uv4Mv5","avgRating":0,"reviewCount":0},{"name":"The Interrupters","eventId":"16vZZ4pO8G7Vzkr","avgRating":0,"reviewCount":0},{"name":"Toronto Maple Leafs vs. Carolina Hurricanes","eventId":"177ZvfG62kwaPwm","avgRating":0,"reviewCount":0},{"name":"Carrie Underwood: The Cry Pretty Tour 360","eventId":"1A8ZAfdGkd-JF0m","avgRating":0,"reviewCount":0},{"name":"Corey Hart - Never Surrender Tour 2019","eventId":"1AvZZ49GkM9t7dn","avgRating":0,"reviewCount":0}]
     */

    private int status;
    private String message;
    private int totalCount;
    private String pageNum;
    private int next;
    private int totalPages;
    private List<EventsListBean> eventsList;

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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<EventsListBean> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<EventsListBean> eventsList) {
        this.eventsList = eventsList;
    }

    public static class EventsListBean {
        /**
         * name : Didirri with Myles Castello
         * eventId : 1Ae0Z4oGkV-Gx8o
         * avgRating : 0
         * reviewCount : 0
         */

        private String name;
        private String eventId;
        private int avgRating;
        private int reviewCount;

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

        public int getAvgRating() {
            return avgRating;
        }

        public void setAvgRating(int avgRating) {
            this.avgRating = avgRating;
        }

        public int getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(int reviewCount) {
            this.reviewCount = reviewCount;
        }
    }
}
