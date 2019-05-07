package com.askonlinesolutions.wengage.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateProfileResponse {


    /**
     * status : 1
     * message : User Profile Updated Successfully
     * profileInfo : {"influencer":1,"category":[{"categoryId":1,"categoryName":"THE CITY","subCategory":[{"subCategoryId":1,"subCategoryName":"In The Know"},{"subCategoryId":2,"subCategoryName":"Getting Around"},{"subCategoryId":3,"subCategoryName":"Top Attractions"}]},{"categoryId":3,"categoryName":"DINNING","subCategory":[{"subCategoryId":11,"subCategoryName":"Just Opened"},{"subCategoryId":12,"subCategoryName":"Vegetarian"},{"subCategoryId":13,"subCategoryName":"Seafood"}]}],"resetPasswordKey":0,"isOnline":1,"isEmailVerified":1,"_id":"5bf7ab4ddd4d0b0f9e7f702c","countryCode":"+91","email":"s.rakhi@askonlinesolutions.com","fullName":"rakhi","password":"$2b$10$HidsPrSonqNm9AfUbfsxjOwbcb7hK7bZED7cfcrdxgwUJVS/Kxyh2","phone":"9958187463","userName":"rk","verificationCode":"","verificationTime":"2018-11-23T07:25:01.000Z","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543926342774_877588.jpg","createdAt":"2018-11-23T07:25:01.306Z","updatedAt":"2018-12-05T05:32:21.463Z","userId":162,"__v":0,"city":"Toronto","DOB":"1995-12-4","gender":"Female","incomeLevel":"$100 - $200K","userToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjE2MiwiZW1haWwiOiJzLnJha2hpQGFza29ubGluZXNvbHV0aW9ucy5jb20iLCJpYXQiOjE1NDM5MjYzMDMsImV4cCI6MTU0MzkyODEwM30.PQzQCcInsky6ru-m6AfI2tpzYE8mg8jZEZPCbaD-AeE","tokenTime":"2018-12-04T12:25:03.000Z","tokenStatus":1,"knownByName":"s.rakhi","description":"Tt","hometown":"Tyy","favoutiteCity":"Tt","favoutiteRestaurant":"Uhgg","shortDesc":"Eetff","work":"Ertuu","socialMedia":"Werrrrrr"}
     */

    private int status;
    private String message;
    private ProfileInfoBean profileInfo;

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

    public ProfileInfoBean getProfileInfo() {
        return profileInfo;
    }

    public void setProfileInfo(ProfileInfoBean profileInfo) {
        this.profileInfo = profileInfo;
    }

    public static class ProfileInfoBean {
        /**
         * influencer : 1
         * category : [{"categoryId":1,"categoryName":"THE CITY","subCategory":[{"subCategoryId":1,"subCategoryName":"In The Know"},{"subCategoryId":2,"subCategoryName":"Getting Around"},{"subCategoryId":3,"subCategoryName":"Top Attractions"}]},{"categoryId":3,"categoryName":"DINNING","subCategory":[{"subCategoryId":11,"subCategoryName":"Just Opened"},{"subCategoryId":12,"subCategoryName":"Vegetarian"},{"subCategoryId":13,"subCategoryName":"Seafood"}]}]
         * resetPasswordKey : 0
         * isOnline : 1
         * isEmailVerified : 1
         * _id : 5bf7ab4ddd4d0b0f9e7f702c
         * countryCode : +91
         * email : s.rakhi@askonlinesolutions.com
         * fullName : rakhi
         * password : $2b$10$HidsPrSonqNm9AfUbfsxjOwbcb7hK7bZED7cfcrdxgwUJVS/Kxyh2
         * phone : 9958187463
         * userName : rk
         * verificationCode :
         * verificationTime : 2018-11-23T07:25:01.000Z
         * photoURL : https://s3.amazonaws.com/wengageapp/profile/1543926342774_877588.jpg
         * createdAt : 2018-11-23T07:25:01.306Z
         * updatedAt : 2018-12-05T05:32:21.463Z
         * userId : 162
         * __v : 0
         * city : Toronto
         * DOB : 1995-12-4
         * gender : Female
         * incomeLevel : $100 - $200K
         * userToken : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjE2MiwiZW1haWwiOiJzLnJha2hpQGFza29ubGluZXNvbHV0aW9ucy5jb20iLCJpYXQiOjE1NDM5MjYzMDMsImV4cCI6MTU0MzkyODEwM30.PQzQCcInsky6ru-m6AfI2tpzYE8mg8jZEZPCbaD-AeE
         * tokenTime : 2018-12-04T12:25:03.000Z
         * tokenStatus : 1
         * knownByName : s.rakhi
         * description : Tt
         * hometown : Tyy
         * favoutiteCity : Tt
         * favoutiteRestaurant : Uhgg
         * shortDesc : Eetff
         * work : Ertuu
         * socialMedia : Werrrrrr
         */

        private int influencer;
        private int resetPasswordKey;
        private int isOnline;
        private int isEmailVerified;
        private String _id;
        private String countryCode;
        private String email;
        private String fullName;
        private String password;
        private String phone;
        private String userName;
        private String verificationCode;
        private String verificationTime;
        private String photoURL;
        private String createdAt;
        private String updatedAt;
        private int userId;
        private int __v;
        private String city;
        private String DOB;
        private String gender;
        private String incomeLevel;
        private String userToken;
        private String tokenTime;
        private int tokenStatus;
        private String knownByName;
        private String description;
        private String hometown;
        private String favoutiteCity;
        private String favoutiteRestaurant;
        private String shortDesc;
        private String work;
        private String socialMedia;
        private List<CustomizedProfileResponse.ProfileInfoBean.CategoryBean> category;

        public int getInfluencer() {
            return influencer;
        }

        public void setInfluencer(int influencer) {
            this.influencer = influencer;
        }

        public int getResetPasswordKey() {
            return resetPasswordKey;
        }

        public void setResetPasswordKey(int resetPasswordKey) {
            this.resetPasswordKey = resetPasswordKey;
        }

        public int getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(int isOnline) {
            this.isOnline = isOnline;
        }

        public int getIsEmailVerified() {
            return isEmailVerified;
        }

        public void setIsEmailVerified(int isEmailVerified) {
            this.isEmailVerified = isEmailVerified;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getVerificationCode() {
            return verificationCode;
        }

        public void setVerificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
        }

        public String getVerificationTime() {
            return verificationTime;
        }

        public void setVerificationTime(String verificationTime) {
            this.verificationTime = verificationTime;
        }

        public String getPhotoURL() {
            return photoURL;
        }

        public void setPhotoURL(String photoURL) {
            this.photoURL = photoURL;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDOB() {
            return DOB;
        }

        public void setDOB(String DOB) {
            this.DOB = DOB;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getIncomeLevel() {
            return incomeLevel;
        }

        public void setIncomeLevel(String incomeLevel) {
            this.incomeLevel = incomeLevel;
        }

        public String getUserToken() {
            return userToken;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }

        public String getTokenTime() {
            return tokenTime;
        }

        public void setTokenTime(String tokenTime) {
            this.tokenTime = tokenTime;
        }

        public int getTokenStatus() {
            return tokenStatus;
        }

        public void setTokenStatus(int tokenStatus) {
            this.tokenStatus = tokenStatus;
        }

        public String getKnownByName() {
            return knownByName;
        }

        public void setKnownByName(String knownByName) {
            this.knownByName = knownByName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getHometown() {
            return hometown;
        }

        public void setHometown(String hometown) {
            this.hometown = hometown;
        }

        public String getFavoutiteCity() {
            return favoutiteCity;
        }

        public void setFavoutiteCity(String favoutiteCity) {
            this.favoutiteCity = favoutiteCity;
        }

        public String getFavoutiteRestaurant() {
            return favoutiteRestaurant;
        }

        public void setFavoutiteRestaurant(String favoutiteRestaurant) {
            this.favoutiteRestaurant = favoutiteRestaurant;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public String getSocialMedia() {
            return socialMedia;
        }

        public void setSocialMedia(String socialMedia) {
            this.socialMedia = socialMedia;
        }

        public List<CustomizedProfileResponse.ProfileInfoBean.CategoryBean> getCategory() {
            return category;
        }

        public void setCategory(List<CustomizedProfileResponse.ProfileInfoBean.CategoryBean> category) {
            this.category = category;
        }

        public static class CategoryBean {
            /**
             * categoryId : 1
             * categoryName : THE CITY
             * subCategory : [{"subCategoryId":1,"subCategoryName":"In The Know"},{"subCategoryId":2,"subCategoryName":"Getting Around"},{"subCategoryId":3,"subCategoryName":"Top Attractions"}]
             */

            private int categoryId;
            private String categoryName;
            private List<CustomizedProfileResponse.ProfileInfoBean.CategoryBean.SubCategoryBean> subCategory;

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public List<CustomizedProfileResponse.ProfileInfoBean.CategoryBean.SubCategoryBean> getSubCategory() {
                return subCategory;
            }

            public void setSubCategory(List<CustomizedProfileResponse.ProfileInfoBean.CategoryBean.SubCategoryBean> subCategory) {
                this.subCategory = subCategory;
            }

            public static class SubCategoryBean {
                /**
                 * subCategoryId : 1
                 * subCategoryName : In The Know
                 */

                private int subCategoryId;
                private String subCategoryName;

                public int getSubCategoryId() {
                    return subCategoryId;
                }

                public void setSubCategoryId(int subCategoryId) {
                    this.subCategoryId = subCategoryId;
                }

                public String getSubCategoryName() {
                    return subCategoryName;
                }

                public void setSubCategoryName(String subCategoryName) {
                    this.subCategoryName = subCategoryName;
                }
            }
        }
    }

}


