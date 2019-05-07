package com.askonlinesolutions.wengage.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignupResponse {

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("profileInfo")
        @Expose
        private ProfileInfo profileInfo;

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

        public ProfileInfo getProfileInfo() {
            return profileInfo;
        }

        public void setProfileInfo(ProfileInfo profileInfo) {
            this.profileInfo = profileInfo;
        }

        public class ProfileInfo {

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
            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("fullName")
            @Expose
            private String fullName;
            @SerializedName("userName")
            @Expose
            private String userName;
            @SerializedName("password")
            @Expose
            private String password;
            @SerializedName("phone")
            @Expose
            private String phone;
            @SerializedName("verificationCode")
            @Expose
            private String verificationCode;
            @SerializedName("verificationTime")
            @Expose
            private String verificationTime;
            @SerializedName("createdAt")
            @Expose
            private String createdAt;
            @SerializedName("updatedAt")
            @Expose
            private String updatedAt;
            @SerializedName("userId")
            @Expose
            private Integer userId;
            @SerializedName("__v")
            @Expose
            private Integer v;

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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
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

            public Integer getV() {
                return v;
            }

            public void setV(Integer v) {
                this.v = v;
            }


        }
    }

