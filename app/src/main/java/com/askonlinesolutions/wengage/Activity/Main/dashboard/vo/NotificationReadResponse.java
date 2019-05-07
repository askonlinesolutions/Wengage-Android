package com.askonlinesolutions.wengage.Activity.Main.dashboard.vo;

public class NotificationReadResponse {


    /**
     * notificationData : {"image":"","isRead":1,"notificationId":1,"_id":"5c04c7cc0015ea0c8f1b5e48","title":"Test","type":"WengageAdmin","userId":93,"content":"Test Notification","updatedAt":"2018-12-03T10:42:16.306Z"}
     * message : Notification Read Successfully
     * status : 1
     */
    private NotificationDataEntity notificationData;
    private String message;
    private int status;

    public void setNotificationData(NotificationDataEntity notificationData) {
        this.notificationData = notificationData;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public NotificationDataEntity getNotificationData() {
        return notificationData;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public class NotificationDataEntity {
        /**
         * image :
         * isRead : 1
         * notificationId : 1
         * _id : 5c04c7cc0015ea0c8f1b5e48
         * title : Test
         * type : WengageAdmin
         * userId : 93
         * content : Test Notification
         * updatedAt : 2018-12-03T10:42:16.306Z
         */
        private String image;
        private int isRead;
        private int notificationId;
        private String _id;
        private String title;
        private String type;
        private int userId;
        private String content;
        private String updatedAt;

        public void setImage(String image) {
            this.image = image;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public void setNotificationId(int notificationId) {
            this.notificationId = notificationId;
        }

        public void set_id(String _id) {
            this._id = _id;
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

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getImage() {
            return image;
        }

        public int getIsRead() {
            return isRead;
        }

        public int getNotificationId() {
            return notificationId;
        }

        public String get_id() {
            return _id;
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

        public String getUpdatedAt() {
            return updatedAt;
        }
    }
}
