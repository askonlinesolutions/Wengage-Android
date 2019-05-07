package com.askonlinesolutions.wengage.Model;

import java.util.List;

public class InvitationModal {
    /**
     * status : 1
     * message : Your Invitations
     * totalCount : 2
     * pageNum : 1
     * next : 0
     * totalPages : 1
     * eventInvitations : [{"inviteStatus":"P","custom":1,"eventId":"19","invitedById":289,"inviteeId":310,"inviteId":"inv:289&310&19","eventName":"Test Event","startDate":"Sat, Feb 2, 2019 6:51 PM","address":"Noida","userName":"harsh","knownByName":"harsh","email":"harshtanwar29@gmail.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1548078677405_950293.jpg"},{"inviteStatus":"P","custom":1,"eventId":"22","invitedById":289,"inviteeId":310,"inviteId":"inv:289&310&22","eventName":"Birthday Party","startDate":"Sun, Feb 3, 2019 7:52 PM","address":"Noida GIP","userName":"harsh","knownByName":"harsh","email":"harshtanwar29@gmail.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1548078677405_950293.jpg"}]
     */

    private int status;
    private String message;
    private int totalCount;
    private String pageNum;
    private int next;
    private int totalPages;
    private List<EventInvitationsBean> eventInvitations;

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

    public List<EventInvitationsBean> getEventInvitations() {
        return eventInvitations;
    }

    public void setEventInvitations(List<EventInvitationsBean> eventInvitations) {
        this.eventInvitations = eventInvitations;
    }

    public static class EventInvitationsBean {
        /**
         * inviteStatus : P
         * custom : 1
         * eventId : 19
         * invitedById : 289
         * inviteeId : 310
         * inviteId : inv:289&310&19
         * eventName : Test Event
         * startDate : Sat, Feb 2, 2019 6:51 PM
         * address : Noida
         * userName : harsh
         * knownByName : harsh
         * email : harshtanwar29@gmail.com
         * photoURL : https://s3.amazonaws.com/wengageapp/profile/1548078677405_950293.jpg
         */

        private String inviteStatus;
        private int custom;
        private String eventId;
        private int invitedById;
        private int inviteeId;
        private String inviteId;
        private String eventName;
        private String startDate;
        private String address;
        private String userName;
        private String knownByName;
        private String email;
        private String photoURL;

        public String getInviteStatus() {
            return inviteStatus;
        }

        public void setInviteStatus(String inviteStatus) {
            this.inviteStatus = inviteStatus;
        }

        public int getCustom() {
            return custom;
        }

        public void setCustom(int custom) {
            this.custom = custom;
        }

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public int getInvitedById() {
            return invitedById;
        }

        public void setInvitedById(int invitedById) {
            this.invitedById = invitedById;
        }

        public int getInviteeId() {
            return inviteeId;
        }

        public void setInviteeId(int inviteeId) {
            this.inviteeId = inviteeId;
        }

        public String getInviteId() {
            return inviteId;
        }

        public void setInviteId(String inviteId) {
            this.inviteId = inviteId;
        }

        public String getEventName() {
            return eventName;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getKnownByName() {
            return knownByName;
        }

        public void setKnownByName(String knownByName) {
            this.knownByName = knownByName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhotoURL() {
            return photoURL;
        }

        public void setPhotoURL(String photoURL) {
            this.photoURL = photoURL;
        }
    }
}
