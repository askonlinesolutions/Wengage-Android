package com.askonlinesolutions.wengage.Activity.Main.dashboard.vo;

public class NotificationCountResponse {


    /**
     * notificationsCount : {"totalNotifications":0,"totalUnreadChatNotifications":0,"totalUnreadNewsNotifications":0,"totalVenueNotifications":0,"totalChatNotifications":0,"totalUnreadInfluencerNotifications":0,"totalInviteNotifications":0,"totalInfluencerNotifications":0,"totalUnreadVenueNotifications":0,"totalUnreadNotifications":0,"totalUnreadInviteNotifications":0,"totalNewsNotifications":0}
     * message : Notifications Count
     * status : 1
     */
    private NotificationsCountEntity notificationsCount;
    private String message;
    private int status;

    public void setNotificationsCount(NotificationsCountEntity notificationsCount) {
        this.notificationsCount = notificationsCount;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public NotificationsCountEntity getNotificationsCount() {
        return notificationsCount;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public class NotificationsCountEntity {
        /**
         * totalNotifications : 0
         * totalUnreadChatNotifications : 0
         * totalUnreadNewsNotifications : 0
         * totalVenueNotifications : 0
         * totalChatNotifications : 0
         * totalUnreadInfluencerNotifications : 0
         * totalInviteNotifications : 0
         * totalInfluencerNotifications : 0
         * totalUnreadVenueNotifications : 0
         * totalUnreadNotifications : 0
         * totalUnreadInviteNotifications : 0
         * totalNewsNotifications : 0
         */
        private int totalNotifications;
        private int totalUnreadChatNotifications;
        private int totalUnreadNewsNotifications;
        private int totalVenueNotifications;
        private int totalChatNotifications;
        private int totalUnreadInfluencerNotifications;
        private int totalInviteNotifications;
        private int totalInfluencerNotifications;
        private int totalUnreadVenueNotifications;
        private int totalUnreadNotifications;
        private int totalUnreadInviteNotifications;
        private int totalNewsNotifications;

        public void setTotalNotifications(int totalNotifications) {
            this.totalNotifications = totalNotifications;
        }

        public void setTotalUnreadChatNotifications(int totalUnreadChatNotifications) {
            this.totalUnreadChatNotifications = totalUnreadChatNotifications;
        }

        public void setTotalUnreadNewsNotifications(int totalUnreadNewsNotifications) {
            this.totalUnreadNewsNotifications = totalUnreadNewsNotifications;
        }

        public void setTotalVenueNotifications(int totalVenueNotifications) {
            this.totalVenueNotifications = totalVenueNotifications;
        }

        public void setTotalChatNotifications(int totalChatNotifications) {
            this.totalChatNotifications = totalChatNotifications;
        }

        public void setTotalUnreadInfluencerNotifications(int totalUnreadInfluencerNotifications) {
            this.totalUnreadInfluencerNotifications = totalUnreadInfluencerNotifications;
        }

        public void setTotalInviteNotifications(int totalInviteNotifications) {
            this.totalInviteNotifications = totalInviteNotifications;
        }

        public void setTotalInfluencerNotifications(int totalInfluencerNotifications) {
            this.totalInfluencerNotifications = totalInfluencerNotifications;
        }

        public void setTotalUnreadVenueNotifications(int totalUnreadVenueNotifications) {
            this.totalUnreadVenueNotifications = totalUnreadVenueNotifications;
        }

        public void setTotalUnreadNotifications(int totalUnreadNotifications) {
            this.totalUnreadNotifications = totalUnreadNotifications;
        }

        public void setTotalUnreadInviteNotifications(int totalUnreadInviteNotifications) {
            this.totalUnreadInviteNotifications = totalUnreadInviteNotifications;
        }

        public void setTotalNewsNotifications(int totalNewsNotifications) {
            this.totalNewsNotifications = totalNewsNotifications;
        }

        public int getTotalNotifications() {
            return totalNotifications;
        }

        public int getTotalUnreadChatNotifications() {
            return totalUnreadChatNotifications;
        }

        public int getTotalUnreadNewsNotifications() {
            return totalUnreadNewsNotifications;
        }

        public int getTotalVenueNotifications() {
            return totalVenueNotifications;
        }

        public int getTotalChatNotifications() {
            return totalChatNotifications;
        }

        public int getTotalUnreadInfluencerNotifications() {
            return totalUnreadInfluencerNotifications;
        }

        public int getTotalInviteNotifications() {
            return totalInviteNotifications;
        }

        public int getTotalInfluencerNotifications() {
            return totalInfluencerNotifications;
        }

        public int getTotalUnreadVenueNotifications() {
            return totalUnreadVenueNotifications;
        }

        public int getTotalUnreadNotifications() {
            return totalUnreadNotifications;
        }

        public int getTotalUnreadInviteNotifications() {
            return totalUnreadInviteNotifications;
        }

        public int getTotalNewsNotifications() {
            return totalNewsNotifications;
        }
    }
}
