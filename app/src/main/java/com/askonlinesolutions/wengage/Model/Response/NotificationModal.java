package com.askonlinesolutions.wengage.Model.Response;

import java.util.List;

public class NotificationModal {
    /**
     * status : 1
     * message : User Notifications
     * totalCount : 1
     * pageNum : 1
     * next : 0
     * totalPages : 1
     * notifications : [{"image":"","isRead":1,"notificationId":1,"userId":93,"title":"Test","content":"Test Notification","type":"WengageAdmin","updatedAt":"2018-12-03T10:42:16.306Z"}]
     */

    private int status;
    private String message;
    private int totalCount;
    private String pageNum;
    private int next;
    private int totalPages;
    private List<NotificationsBean> notifications;

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

    public List<NotificationsBean> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationsBean> notifications) {
        this.notifications = notifications;
    }

    public static class NotificationsBean {
        /**
         * image :
         * isRead : 1
         * notificationId : 1
         * userId : 93
         * title : Test
         * content : Test Notification
         * type : WengageAdmin
         * updatedAt : 2018-12-03T10:42:16.306Z
         */

        private String image;
        private int isRead;
        private int notificationId;
        private int userId;
        private String title;
        private String content;
        private String type;
        private String updatedAt;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public int getNotificationId() {
            return notificationId;
        }

        public void setNotificationId(int notificationId) {
            this.notificationId = notificationId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}
