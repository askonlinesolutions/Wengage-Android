package com.askonlinesolutions.wengage.Model.Response;

public class LoginResponse {

    /**
     * message : Login Successfull
     * profileInfo : {"city":"Toronto","email":"s.rakhi@askonlinesolutions.com","influencer":1,"isCategory":1,"isFirstLogin":0,"isOnline":1,"isProfileCompleted":1,"knownByName":"s.rakhi","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543926342774_877588.jpg","userId":162,"userName":"rk","userToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjE2MiwiZW1haWwiOiJzLnJha2hpQGFza29ubGluZXNvbHV0aW9ucy5jb20iLCJpYXQiOjE1NDM5OTIyNjUsImV4cCI6MTU0Mzk5NDA2NX0.zjuSwxh6LFtnD5hD95mbxd4xdSB_8Dx4Cd0H23gNHhw"}
     * status : 1
     */

    private String message;
    private ProfileInfoBean profileInfo;
    private int status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProfileInfoBean getProfileInfo() {
        return profileInfo;
    }

    public void setProfileInfo(ProfileInfoBean profileInfo) {
        this.profileInfo = profileInfo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ProfileInfoBean {
        /**
         * city : Toronto
         * email : s.rakhi@askonlinesolutions.com
         * influencer : 1
         * isCategory : 1
         * isFirstLogin : 0
         * isOnline : 1
         * isProfileCompleted : 1
         * knownByName : s.rakhi
         * photoURL : https://s3.amazonaws.com/wengageapp/profile/1543926342774_877588.jpg
         * userId : 162
         * userName : rk
         * radius : rk
         * userToken : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjE2MiwiZW1haWwiOiJzLnJha2hpQGFza29ubGluZXNvbHV0aW9ucy5jb20iLCJpYXQiOjE1NDM5OTIyNjUsImV4cCI6MTU0Mzk5NDA2NX0.zjuSwxh6LFtnD5hD95mbxd4xdSB_8Dx4Cd0H23gNHhw
         */

        private String city;
        private String email;

        public String getRadius() {
            return radius;
        }

        public void setRadius(String radius) {
            this.radius = radius;
        }

        private String radius;
        private int influencer;

        public int getIsVenueCategory() {
            return isVenueCategory;
        }

        public void setIsVenueCategory(int isVenueCategory) {
            this.isVenueCategory = isVenueCategory;
        }

        private int isVenueCategory;
        private int isFirstLogin;
        private int isOnline;
        private int isProfileCompleted;
        private String knownByName;
        private String photoURL;
        private int userId;
        private String userName;
        private String userToken;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getInfluencer() {
            return influencer;
        }

        public void setInfluencer(int influencer) {
            this.influencer = influencer;
        }
/*
        public int getIsCategory() {
            return isCategory;
        }

        public void setIsCategory(int isCategory) {
            this.isCategory = isCategory;
        }*/

        public int getIsFirstLogin() {
            return isFirstLogin;
        }

        public void setIsFirstLogin(int isFirstLogin) {
            this.isFirstLogin = isFirstLogin;
        }

        public int getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(int isOnline) {
            this.isOnline = isOnline;
        }

        public int getIsProfileCompleted() {
            return isProfileCompleted;
        }

        public void setIsProfileCompleted(int isProfileCompleted) {
            this.isProfileCompleted = isProfileCompleted;
        }

        public String getKnownByName() {
            return knownByName;
        }

        public void setKnownByName(String knownByName) {
            this.knownByName = knownByName;
        }

        public String getPhotoURL() {
            return photoURL;
        }

        public void setPhotoURL(String photoURL) {
            this.photoURL = photoURL;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserToken() {
            return userToken;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }
    }
}


