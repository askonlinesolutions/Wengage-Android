package com.askonlinesolutions.wengage.Model;

import java.util.List;

public class ChatListModal {
    /**
     * status : 1
     * message : Chat Lists.
     * myChats : [{"chatTitle":"harsh","chatIcon":"https://s3.amazonaws.com/wengageapp/profile/1548078677405_950293.jpg","chatType":"SIN","members":[{"userId":310,"knownByName":"harsh","email":"harshtanwar29@gmail.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1548078677405_950293.jpg"},{"userId":289,"knownByName":"Rohit Verma ","email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1548079108323_043075.jpg"}],"chatId":12,"lastMsg":"","lastMsgTime":"07 Feb 19 7:32 AM","unreadStatus":0,"unreadCount":0},{"chatTitle":"Test Chat","chatIcon":"","chatType":"GRP","members":[{"userId":289,"knownByName":"Rohit Verma ","email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1548079108323_043075.jpg"},{"userId":310,"knownByName":"harsh","email":"harshtanwar29@gmail.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1548078677405_950293.jpg"}],"chatId":13,"lastMsg":"","lastMsgTime":"07 Feb 19 7:32 AM","unreadStatus":0,"unreadCount":0},{"chatTitle":"RohitTest2","chatIcon":"","chatType":"GRP","members":[{"userId":310,"knownByName":"harsh","email":"harshtanwar29@gmail.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1548078677405_950293.jpg"},{"userId":289,"knownByName":"Rohit Verma ","email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1548079108323_043075.jpg"}],"chatId":27,"lastMsg":"","lastMsgTime":"07 Feb 19 7:32 AM","unreadStatus":0,"unreadCount":0},{"chatTitle":"Mark","chatIcon":"https://s3.amazonaws.com/wengageapp/profile/1547663526156_435018.jpg","chatType":"SIN","members":[{"userId":298,"knownByName":"Mark","email":"mark.grinshpun@gmail.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1547663526156_435018.jpg"},{"userId":289,"knownByName":"Rohit Verma ","email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1548079108323_043075.jpg"}],"chatId":36,"lastMsg":"","lastMsgTime":"07 Feb 19 7:32 AM","unreadStatus":0,"unreadCount":0},{"chatTitle":"Harry","chatIcon":"https://s3.amazonaws.com/wengageapp/profile/1548067656614_475556.jpg","chatType":"SIN","members":[{"userId":313,"knownByName":"Harry","email":"harsh@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1548067656614_475556.jpg"},{"userId":289,"knownByName":"Rohit Verma ","email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1548079108323_043075.jpg"}],"chatId":38,"lastMsg":"","lastMsgTime":"07 Feb 19 7:32 AM","unreadStatus":0,"unreadCount":0}]
     */

    private int status;
    private String message;
    private List<MyChatsBean> myChats;

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

    public List<MyChatsBean> getMyChats() {
        return myChats;
    }

    public void setMyChats(List<MyChatsBean> myChats) {
        this.myChats = myChats;
    }

    public static class MyChatsBean {
        /**
         * chatTitle : harsh
         * chatIcon : https://s3.amazonaws.com/wengageapp/profile/1548078677405_950293.jpg
         * chatType : SIN
         * members : [{"userId":310,"knownByName":"harsh","email":"harshtanwar29@gmail.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1548078677405_950293.jpg"},{"userId":289,"knownByName":"Rohit Verma ","email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1548079108323_043075.jpg"}]
         * chatId : 12
         * lastMsg :
         * lastMsgTime : 07 Feb 19 7:32 AM
         * unreadStatus : 0
         * unreadCount : 0
         */

        private String chatTitle;
        private String chatIcon;
        private String chatType;
        private int chatId;
        private String lastMsg;
        private String lastMsgTime;
        private int unreadStatus;
        private int unreadCount;
        private List<MembersBean> members;

        public String getChatTitle() {
            return chatTitle;
        }

        public void setChatTitle(String chatTitle) {
            this.chatTitle = chatTitle;
        }

        public String getChatIcon() {
            return chatIcon;
        }

        public void setChatIcon(String chatIcon) {
            this.chatIcon = chatIcon;
        }

        public String getChatType() {
            return chatType;
        }

        public void setChatType(String chatType) {
            this.chatType = chatType;
        }

        public int getChatId() {
            return chatId;
        }

        public void setChatId(int chatId) {
            this.chatId = chatId;
        }

        public String getLastMsg() {
            return lastMsg;
        }

        public void setLastMsg(String lastMsg) {
            this.lastMsg = lastMsg;
        }

        public String getLastMsgTime() {
            return lastMsgTime;
        }

        public void setLastMsgTime(String lastMsgTime) {
            this.lastMsgTime = lastMsgTime;
        }

        public int getUnreadStatus() {
            return unreadStatus;
        }

        public void setUnreadStatus(int unreadStatus) {
            this.unreadStatus = unreadStatus;
        }

        public int getUnreadCount() {
            return unreadCount;
        }

        public void setUnreadCount(int unreadCount) {
            this.unreadCount = unreadCount;
        }

        public List<MembersBean> getMembers() {
            return members;
        }

        public void setMembers(List<MembersBean> members) {
            this.members = members;
        }

        public static class MembersBean {
            /**
             * userId : 310
             * knownByName : harsh
             * email : harshtanwar29@gmail.com
             * photoURL : https://s3.amazonaws.com/wengageapp/profile/1548078677405_950293.jpg
             */

            private int userId;
            private String knownByName;
            private String email;
            private String photoURL;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
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
}
