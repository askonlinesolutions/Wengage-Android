package com.askonlinesolutions.wengage.Activity.Main.dashboard.vo;

import java.util.List;

public class InfluencerResponse {


    /**
     * next : 0
     * totalPages : 1
     * message : User Notifications
     * totalCount : 6
     * pageNum : 1
     * notifications : [{"sourceId":"16vZZ4bFSG7cdWB","image":"https://s1.ticketm.net/dam/c/fbc/b293c0ad-c904-4215-bc59-8d7f2414dfbc_106141_RETINA_PORTRAIT_16_9.jpg","distance":0,"city":"Toronto","isRead":1,"title":"Influencer Event Interest","type":"influencer","userId":11,"content":"Sandy is interested event Spiritualized.","createdAt":"Fri, Apr 12, 2019 5:29 AM","reviewCount":0,"name":"Spiritualized","notificationId":521,"avgRating":0,"updatedAt":"2019-04-12T07:25:01.651Z"},{"sourceId":"75B839J3KFKKYbR8BR00xA","image":"https://s3.amazonaws.com/wengageapp/venue/mnwzM8rwjirdDwD8KhfR6A.jpg","distance":0,"city":"Toronto","isRead":0,"title":"Influencer Venue Interest","type":"influencer","userId":11,"content":"Sandy is interested venue Urban Capers.","createdAt":"Fri, Apr 12, 2019 5:29 AM","reviewCount":0,"name":"Urban Capers","notificationId":520,"avgRating":0,"updatedAt":"2019-04-12T05:29:16.916Z"},{"sourceId":"yW_pQ6kq_8v4QkfJHmJVww","image":"https://s3.amazonaws.com/wengageapp/Image+not+found.png","distance":0,"city":"Toronto","isRead":0,"title":"Influencer Venue Interest","type":"influencer","userId":11,"content":"Sandy is interested venue Shaker's Studio.","createdAt":"Fri, Apr 12, 2019 5:29 AM","reviewCount":0,"name":"Shaker's Studio","notificationId":519,"avgRating":0,"updatedAt":"2019-04-12T05:29:11.815Z"},{"sourceId":"S1otvo2B2QpOyU7iHeKLEQ","image":"https://s3.amazonaws.com/wengageapp/venue/Cr5LupXnqS0fFWL512Mf9g.jpg","distance":0,"city":"Toronto","isRead":0,"title":"Influencer Venue Interest","type":"influencer","userId":11,"content":"abc is interested venue Mrs Field & Pretzelmaker.","createdAt":"Wed, Apr 10, 2019 1:15 PM","reviewCount":0,"name":"Mrs Field & Pretzelmaker","notificationId":365,"avgRating":0,"updatedAt":"2019-04-10T13:15:37.051Z"},{"sourceId":"yW_pQ6kq_8v4QkfJHmJVww","image":"https://s3.amazonaws.com/wengageapp/Image+not+found.png","distance":0,"city":"Toronto","isRead":0,"title":"Influencer Venue Interest","type":"influencer","userId":11,"content":"abc is interested venue Shaker's Studio.","createdAt":"Wed, Apr 10, 2019 1:10 PM","reviewCount":0,"name":"Shaker's Studio","notificationId":364,"avgRating":0,"updatedAt":"2019-04-10T13:10:54.411Z"},{"sourceId":"i78im_1r1ZQbwqLjVxZxpg","image":"https://s3.amazonaws.com/wengageapp/venue/tIjEqsxCe0SEDB6LXX6UIw.jpg","distance":0,"city":"Toronto","isRead":0,"title":"Influencer Venue Interest","type":"influencer","userId":11,"content":"abc is interested venue Aroma Espresso Bar.","createdAt":"Wed, Apr 10, 2019 12:27 PM","reviewCount":0,"name":"Aroma Espresso Bar","notificationId":363,"avgRating":0,"updatedAt":"2019-04-10T12:27:17.527Z"}]
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
         * sourceId : 16vZZ4bFSG7cdWB
         * image : https://s1.ticketm.net/dam/c/fbc/b293c0ad-c904-4215-bc59-8d7f2414dfbc_106141_RETINA_PORTRAIT_16_9.jpg
         * distance : 0
         * city : Toronto
         * isRead : 1
         * title : Influencer Event Interest
         * type : influencer
         * userId : 11
         * content : Sandy is interested event Spiritualized.
         * createdAt : Fri, Apr 12, 2019 5:29 AM
         * reviewCount : 0
         * name : Spiritualized
         * notificationId : 521
         * avgRating : 0
         * updatedAt : 2019-04-12T07:25:01.651Z
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
