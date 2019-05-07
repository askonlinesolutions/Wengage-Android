package com.askonlinesolutions.wengage.Activity.Main.dashboard.vo;

import java.util.List;

public class VenueResponse {


    /**
     * next : 0
     * totalPages : 1
     * message : User Notifications
     * totalCount : 1
     * pageNum : 1
     * notifications : [{"sourceId":"EsavKbM2kC8A7_2G5LQQwg","image":"https://s3.amazonaws.com/wengageapp/venue/0Mown4aW6DsQWWeNyrvOXQ.jpg","distance":"11659.44","city":"Toronto","isRead":0,"title":"Venue Interest","type":"venueactivity","userId":4,"content":"Marwin is interested in venue Safeway Tours.","createdAt":"Mon, Apr 1, 2019 9:28 AM","reviewCount":0,"name":"Safeway Tours","notificationId":40,"avgRating":0,"updatedAt":"2019-04-01T09:28:40.954Z"}]
     * status : 1
     */
    private int next;
    private int totalPages;
    private String message;
    private int totalCount;
    private String pageNum;
    private List<NotificationsEntity> notifications;
    private int status;

    public void setNext(int next) {
        this.next = next;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public void setNotifications(List<NotificationsEntity> notifications) {
        this.notifications = notifications;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNext() {
        return next;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public String getMessage() {
        return message;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public String getPageNum() {
        return pageNum;
    }

    public List<NotificationsEntity> getNotifications() {
        return notifications;
    }

    public int getStatus() {
        return status;
    }

    public class NotificationsEntity {
        /**
         * sourceId : EsavKbM2kC8A7_2G5LQQwg
         * image : https://s3.amazonaws.com/wengageapp/venue/0Mown4aW6DsQWWeNyrvOXQ.jpg
         * distance : 11659.44
         * city : Toronto
         * isRead : 0
         * title : Venue Interest
         * type : venueactivity
         * userId : 4
         * content : Marwin is interested in venue Safeway Tours.
         * createdAt : Mon, Apr 1, 2019 9:28 AM
         * reviewCount : 0
         * name : Safeway Tours
         * notificationId : 40
         * avgRating : 0
         * updatedAt : 2019-04-01T09:28:40.954Z
         */
        private String sourceId;
        private String image;
        private String distance;
        private String city;
        private int isRead;
        private String title;
        private String type;
        private int userId;
        private String content;
        private String createdAt;
        private int reviewCount;
        private String name;
        private int notificationId;
        private int avgRating;
        private String updatedAt;

        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setReviewCount(int reviewCount) {
            this.reviewCount = reviewCount;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNotificationId(int notificationId) {
            this.notificationId = notificationId;
        }

        public void setAvgRating(int avgRating) {
            this.avgRating = avgRating;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getSourceId() {
            return sourceId;
        }

        public String getImage() {
            return image;
        }

        public String getDistance() {
            return distance;
        }

        public String getCity() {
            return city;
        }

        public int getIsRead() {
            return isRead;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

        public int getUserId() {
            return userId;
        }

        public String getContent() {
            return content;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public int getReviewCount() {
            return reviewCount;
        }

        public String getName() {
            return name;
        }

        public int getNotificationId() {
            return notificationId;
        }

        public int getAvgRating() {
            return avgRating;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }
    }
}
