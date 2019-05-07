package com.askonlinesolutions.wengage.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AllUsersResponse implements Serializable {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("usersList")
    @Expose
    private UsersList usersList;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UsersList getUsersList() {
        return usersList;
    }

    public void setUsersList(UsersList usersList) {
        this.usersList = usersList;
    }

    public class Influencer implements Serializable {

        @SerializedName("influencer")
        @Expose
        private Integer influencer;
        @SerializedName("category")
        @Expose
        private List<Category> category = null;
        @SerializedName("resetPasswordKey")
        @Expose
        private Integer resetPasswordKey;
        @SerializedName("isOnline")
        @Expose
        private Integer isOnline;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("verificationCode")
        @Expose
        private String verificationCode;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("userId")
        @Expose
        private Integer userId;
        @SerializedName("userToken")
        @Expose
        private String userToken;
        @SerializedName("tokenTime")
        @Expose
        private String tokenTime;
        @SerializedName("tokenStatus")
        @Expose
        private Integer tokenStatus;
        @SerializedName("fullName")
        @Expose
        private String fullName;
        @SerializedName("DOB")
        @Expose
        private String dOB;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("location0")
        @Expose
        private String location0;
        @SerializedName("location1")
        @Expose
        private String location1;
        @SerializedName("categoryId")
        @Expose
        private Object categoryId;
        @SerializedName("subCategoryId")
        @Expose
        private Object subCategoryId;
        @SerializedName("categoryName")
        @Expose
        private String categoryName;
        @SerializedName("subCategoryName")
        @Expose
        private String subCategoryName;
        @SerializedName("photoURL")
        @Expose
        private String photoURL;
        @SerializedName("bookmarks")
        @Expose
        private Bookmarks bookmarks;
        @SerializedName("interests")
        @Expose
        private Interests interests;

        public Integer getInfluencer() {
            return influencer;
        }

        public void setInfluencer(Integer influencer) {
            this.influencer = influencer;
        }

        public List<Category> getCategory() {
            return category;
        }

        public void setCategory(List<Category> category) {
            this.category = category;
        }

        public Integer getResetPasswordKey() {
            return resetPasswordKey;
        }

        public void setResetPasswordKey(Integer resetPasswordKey) {
            this.resetPasswordKey = resetPasswordKey;
        }

        public Integer getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(Integer isOnline) {
            this.isOnline = isOnline;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getVerificationCode() {
            return verificationCode;
        }

        public void setVerificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
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

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
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

        public Integer getTokenStatus() {
            return tokenStatus;
        }

        public void setTokenStatus(Integer tokenStatus) {
            this.tokenStatus = tokenStatus;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getDOB() {
            return dOB;
        }

        public void setDOB(String dOB) {
            this.dOB = dOB;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getLocation0() {
            return location0;
        }

        public void setLocation0(String location0) {
            this.location0 = location0;
        }

        public String getLocation1() {
            return location1;
        }

        public void setLocation1(String location1) {
            this.location1 = location1;
        }

        public Object getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Object categoryId) {
            this.categoryId = categoryId;
        }

        public Object getSubCategoryId() {
            return subCategoryId;
        }

        public void setSubCategoryId(Object subCategoryId) {
            this.subCategoryId = subCategoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getSubCategoryName() {
            return subCategoryName;
        }

        public void setSubCategoryName(String subCategoryName) {
            this.subCategoryName = subCategoryName;
        }

        public String getPhotoURL() {
            return photoURL;
        }

        public void setPhotoURL(String photoURL) {
            this.photoURL = photoURL;
        }

        public Bookmarks getBookmarks() {
            return bookmarks;
        }

        public void setBookmarks(Bookmarks bookmarks) {
            this.bookmarks = bookmarks;
        }

        public Interests getInterests() {
            return interests;
        }

        public void setInterests(Interests interests) {
            this.interests = interests;
        }

    }

    public class Bookmarks {

        @SerializedName("venue")
        @Expose
        private List<Integer> venue = null;
        @SerializedName("event")
        @Expose
        private List<Integer> event = null;

        public List<Integer> getVenue() {
            return venue;
        }

        public void setVenue(List<Integer> venue) {
            this.venue = venue;
        }

        public List<Integer> getEvent() {
            return event;
        }

        public void setEvent(List<Integer> event) {
            this.event = event;
        }

    }

    public class Interests {

        @SerializedName("venue")
        @Expose
        private List<Integer> venue = null;
        @SerializedName("event")
        @Expose
        private List<Integer> event = null;

        public List<Integer> getVenue() {
            return venue;
        }

        public void setVenue(List<Integer> venue) {
            this.venue = venue;
        }

        public List<Integer> getEvent() {
            return event;
        }

        public void setEvent(List<Integer> event) {
            this.event = event;
        }

    }

    public class OnlineUser implements Serializable {

        @SerializedName("influencer")
        @Expose
        private Integer influencer;
        @SerializedName("category")
        @Expose
        private List<Category> category = null;
        @SerializedName("resetPasswordKey")
        @Expose
        private Integer resetPasswordKey;
        @SerializedName("isOnline")
        @Expose
        private Integer isOnline;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("verificationCode")
        @Expose
        private String verificationCode;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("userId")
        @Expose
        private Integer userId;
        @SerializedName("userToken")
        @Expose
        private String userToken;
        @SerializedName("tokenTime")
        @Expose
        private String tokenTime;
        @SerializedName("tokenStatus")
        @Expose
        private Integer tokenStatus;
        @SerializedName("DOB")
        @Expose
        private String dOB;
        @SerializedName("fullName")
        @Expose
        private String fullName;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("location0")
        @Expose
        private String location0;
        @SerializedName("location1")
        @Expose
        private String location1;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("verificationTime")
        @Expose
        private String verificationTime;
        @SerializedName("photoURL")
        @Expose
        private String photoURL;

        public Integer getInfluencer() {
            return influencer;
        }

        public void setInfluencer(Integer influencer) {
            this.influencer = influencer;
        }

        public List<Category> getCategory() {
            return category;
        }

        public void setCategory(List<Category> category) {
            this.category = category;
        }

        public Integer getResetPasswordKey() {
            return resetPasswordKey;
        }

        public void setResetPasswordKey(Integer resetPasswordKey) {
            this.resetPasswordKey = resetPasswordKey;
        }

        public Integer getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(Integer isOnline) {
            this.isOnline = isOnline;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getVerificationCode() {
            return verificationCode;
        }

        public void setVerificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
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

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
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

        public Integer getTokenStatus() {
            return tokenStatus;
        }

        public void setTokenStatus(Integer tokenStatus) {
            this.tokenStatus = tokenStatus;
        }

        public String getDOB() {
            return dOB;
        }

        public void setDOB(String dOB) {
            this.dOB = dOB;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getLocation0() {
            return location0;
        }

        public void setLocation0(String location0) {
            this.location0 = location0;
        }

        public String getLocation1() {
            return location1;
        }

        public void setLocation1(String location1) {
            this.location1 = location1;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

    }


    public class SubCategory {

        @SerializedName("subCategoryId")
        @Expose
        private Integer subCategoryId;
        @SerializedName("subCategoryName")
        @Expose
        private String subCategoryName;

        public Integer getSubCategoryId() {
            return subCategoryId;
        }

        public void setSubCategoryId(Integer subCategoryId) {
            this.subCategoryId = subCategoryId;
        }

        public String getSubCategoryName() {
            return subCategoryName;
        }

        public void setSubCategoryName(String subCategoryName) {
            this.subCategoryName = subCategoryName;
        }

    }

    public class Category {

        @SerializedName("categoryId")
        @Expose
        private Integer categoryId;
        @SerializedName("categoryName")
        @Expose
        private String categoryName;
        @SerializedName("subCategory")
        @Expose
        private List<SubCategory> subCategory = null;

        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<SubCategory> getSubCategory() {
            return subCategory;
        }

        public void setSubCategory(List<SubCategory> subCategory) {
            this.subCategory = subCategory;
        }

    }


    public class UsersList {

        @SerializedName("onlineUsers")
        @Expose
        private List<OnlineUser> onlineUsers = null;
        @SerializedName("influencer")
        @Expose
        private List<Influencer> influencer = null;

        public List<OnlineUser> getOnlineUsers() {
            return onlineUsers;
        }

        public void setOnlineUsers(List<OnlineUser> onlineUsers) {
            this.onlineUsers = onlineUsers;
        }

        public List<Influencer> getInfluencer() {
            return influencer;
        }

        public void setInfluencer(List<Influencer> influencer) {
            this.influencer = influencer;
        }

    }


















/*


    public class Category {

        @SerializedName("categoryId")
        @Expose
        private Integer categoryId;
        @SerializedName("categoryName")
        @Expose
        private String categoryName;
        @SerializedName("subCategory")
        @Expose
        private List<SubCategory> subCategory = null;

        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<SubCategory> getSubCategory() {
            return subCategory;
        }

        public void setSubCategory(List<SubCategory> subCategory) {
            this.subCategory = subCategory;
        }

    }

    public class OfflineUser {

        @SerializedName("influencer")
        @Expose
        private Integer influencer;
        @SerializedName("category")
        @Expose
        private List<Category> category = null;
        @SerializedName("resetPasswordKey")
        @Expose
        private Integer resetPasswordKey;
        @SerializedName("isOnline")
        @Expose
        private Integer isOnline;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("verificationCode")
        @Expose
        private String verificationCode;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("userId")
        @Expose
        private Integer userId;
        @SerializedName("userToken")
        @Expose
        private String userToken;
        @SerializedName("tokenTime")
        @Expose
        private String tokenTime;
        @SerializedName("tokenStatus")
        @Expose
        private Integer tokenStatus;
        @SerializedName("fullName")
        @Expose
        private String fullName;
        @SerializedName("DOB")
        @Expose
        private String dOB;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("location0")
        @Expose
        private String location0;
        @SerializedName("location1")
        @Expose
        private String location1;
        @SerializedName("categoryId")
        @Expose
        private Object categoryId;
        @SerializedName("subCategoryId")
        @Expose
        private Object subCategoryId;
        @SerializedName("categoryName")
        @Expose
        private String categoryName;
        @SerializedName("subCategoryName")
        @Expose
        private String subCategoryName;
        @SerializedName("photoURL")
        @Expose
        private String photoURL;

        public Integer getInfluencer() {
            return influencer;
        }

        public void setInfluencer(Integer influencer) {
            this.influencer = influencer;
        }

        public List<Category> getCategory() {
            return category;
        }

        public void setCategory(List<Category> category) {
            this.category = category;
        }

        public Integer getResetPasswordKey() {
            return resetPasswordKey;
        }

        public void setResetPasswordKey(Integer resetPasswordKey) {
            this.resetPasswordKey = resetPasswordKey;
        }

        public Integer getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(Integer isOnline) {
            this.isOnline = isOnline;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getVerificationCode() {
            return verificationCode;
        }

        public void setVerificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
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

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
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

        public Integer getTokenStatus() {
            return tokenStatus;
        }

        public void setTokenStatus(Integer tokenStatus) {
            this.tokenStatus = tokenStatus;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getDOB() {
            return dOB;
        }

        public void setDOB(String dOB) {
            this.dOB = dOB;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getLocation0() {
            return location0;
        }

        public void setLocation0(String location0) {
            this.location0 = location0;
        }

        public String getLocation1() {
            return location1;
        }

        public void setLocation1(String location1) {
            this.location1 = location1;
        }

        public Object getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Object categoryId) {
            this.categoryId = categoryId;
        }

        public Object getSubCategoryId() {
            return subCategoryId;
        }

        public void setSubCategoryId(Object subCategoryId) {
            this.subCategoryId = subCategoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getSubCategoryName() {
            return subCategoryName;
        }

        public void setSubCategoryName(String subCategoryName) {
            this.subCategoryName = subCategoryName;
        }

        public String getPhotoURL() {
            return photoURL;
        }

        public void setPhotoURL(String photoURL) {
            this.photoURL = photoURL;
        }

    }


    public class OnlineUser {

        @SerializedName("influencer")
        @Expose
        private Integer influencer;
        @SerializedName("category")
        @Expose
        private List<Object> category = null;
        @SerializedName("resetPasswordKey")
        @Expose
        private Integer resetPasswordKey;
        @SerializedName("isOnline")
        @Expose
        private Integer isOnline;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("verificationCode")
        @Expose
        private String verificationCode;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("userId")
        @Expose
        private Integer userId;
        @SerializedName("userToken")
        @Expose
        private String userToken;
        @SerializedName("tokenTime")
        @Expose
        private String tokenTime;
        @SerializedName("tokenStatus")
        @Expose
        private Integer tokenStatus;
        @SerializedName("verificationTime")
        @Expose
        private String verificationTime;
        @SerializedName("photoURL")
        @Expose
        private String photoURL;
        @SerializedName("DOB")
        @Expose
        private String dOB;
        @SerializedName("fullName")
        @Expose
        private String fullName;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("location0")
        @Expose
        private String location0;
        @SerializedName("location1")
        @Expose
        private String location1;
        @SerializedName("phone")
        @Expose
        private String phone;

        public Integer getInfluencer() {
            return influencer;
        }

        public void setInfluencer(Integer influencer) {
            this.influencer = influencer;
        }

        public List<Object> getCategory() {
            return category;
        }

        public void setCategory(List<Object> category) {
            this.category = category;
        }

        public Integer getResetPasswordKey() {
            return resetPasswordKey;
        }

        public void setResetPasswordKey(Integer resetPasswordKey) {
            this.resetPasswordKey = resetPasswordKey;
        }

        public Integer getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(Integer isOnline) {
            this.isOnline = isOnline;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getVerificationCode() {
            return verificationCode;
        }

        public void setVerificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
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

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
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

        public Integer getTokenStatus() {
            return tokenStatus;
        }

        public void setTokenStatus(Integer tokenStatus) {
            this.tokenStatus = tokenStatus;
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

        public String getDOB() {
            return dOB;
        }

        public void setDOB(String dOB) {
            this.dOB = dOB;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getLocation0() {
            return location0;
        }

        public void setLocation0(String location0) {
            this.location0 = location0;
        }

        public String getLocation1() {
            return location1;
        }

        public void setLocation1(String location1) {
            this.location1 = location1;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

    }


    public class SubCategory {

        @SerializedName("subCategoryId")
        @Expose
        private Integer subCategoryId;
        @SerializedName("subCategoryName")
        @Expose
        private String subCategoryName;

        public Integer getSubCategoryId() {
            return subCategoryId;
        }

        public void setSubCategoryId(Integer subCategoryId) {
            this.subCategoryId = subCategoryId;
        }

        public String getSubCategoryName() {
            return subCategoryName;
        }

        public void setSubCategoryName(String subCategoryName) {
            this.subCategoryName = subCategoryName;
        }

    }

    public class UsersList {

        @SerializedName("onlineUsers")
        @Expose
        private List<OnlineUser> onlineUsers = null;
        @SerializedName("offlineUsers")
        @Expose
        private List<OfflineUser> offlineUsers = null;

        public List<OnlineUser> getOnlineUsers() {
            return onlineUsers;
        }

        public void setOnlineUsers(List<OnlineUser> onlineUsers) {
            this.onlineUsers = onlineUsers;
        }

        public List<OfflineUser> getOfflineUsers() {
            return offlineUsers;
        }

        public void setOfflineUsers(List<OfflineUser> offlineUsers) {
            this.offlineUsers = offlineUsers;
        }

    }
*/
}
