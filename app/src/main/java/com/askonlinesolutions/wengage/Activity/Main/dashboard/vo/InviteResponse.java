package com.askonlinesolutions.wengage.Activity.Main.dashboard.vo;

import java.util.List;

public class InviteResponse {


    /**
     * next : 0
     * totalPages : 1
     * message : User Notifications
     * totalCount : 3
     * pageNum : 1
     * notifications : [{"sourceId":"13","image":"https://s3.amazonaws.com/wengageapp/event/1554728861700_721672.jpg","distance":0,"city":"Noida 15","isRead":0,"title":"Invite","type":"invite","userId":4,"content":"Aftaab IOS invited you to Test New.","createdAt":"Mon, Apr 8, 2019 1:07 PM","reviewCount":0,"name":"Test New","notificationId":152,"avgRating":0,"updatedAt":"2019-04-08T13:07:42.244Z"},{"sourceId":"12","image":"https://s3.amazonaws.com/wengageapp/event/1554728423857_819734.jpg","distance":0,"city":"Mnh Tech","isRead":0,"title":"Invite","type":"invite","userId":4,"content":"Aftaab IOS invited you to Test 2.","createdAt":"Mon, Apr 8, 2019 1:00 PM","reviewCount":0,"name":"Test 2","notificationId":151,"avgRating":0,"updatedAt":"2019-04-08T13:00:24.148Z"},{"sourceId":"11","image":"https://s3.amazonaws.com/wengageapp/event/1554727856680_526769.jpg","distance":0,"city":"Noida MnH ","isRead":0,"title":"Invite","type":"invite","userId":4,"content":"Aftaab IOS invited you to Test Push.","createdAt":"Mon, Apr 8, 2019 12:50 PM","reviewCount":0,"name":"Test Push","notificationId":150,"avgRating":0,"updatedAt":"2019-04-08T12:50:57.113Z"}]
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
         * sourceId : 13
         * image : https://s3.amazonaws.com/wengageapp/event/1554728861700_721672.jpg
         * distance : 0
         * city : Noida 15
         * isRead : 0
         * title : Invite
         * type : invite
         * userId : 4
         * content : Aftaab IOS invited you to Test New.
         * createdAt : Mon, Apr 8, 2019 1:07 PM
         * reviewCount : 0
         * name : Test New
         * notificationId : 152
         * avgRating : 0
         * updatedAt : 2019-04-08T13:07:42.244Z
         */
        private String sourceId;
        private String image;
        private int distance;
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

        public void setDistance(int distance) {
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

        public int getDistance() {
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
