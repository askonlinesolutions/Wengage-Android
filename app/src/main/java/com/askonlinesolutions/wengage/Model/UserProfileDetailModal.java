package com.askonlinesolutions.wengage.Model;

import java.util.List;

public class UserProfileDetailModal {


    private int status;
    private String message;
    private UserDataBean userData;

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

    public UserDataBean getUserData() {
        return userData;
    }

    public void setUserData(UserDataBean userData) {
        this.userData = userData;
    }

    public static class UserDataBean {

        private int influencer;
        private int resetPasswordKey;
        private int isOnline;
        private int isEmailVerified;
        private int status;
        private String email;
        private String phone;
        private String countryCode;
        private String fullName;
        private String userName;
        private String verificationCode;
        private String verificationTime;
        private String photoURL;
        private String createdAt;
        private String updatedAt;
        private int userId;
        private String gender;
        private String knownByName;
        private String DOB;
        private String city;
        private String incomeLevel;
        private String userToken;
        private String tokenTime;
        private int tokenStatus;
        private String favoutiteCity;
        private String shortDesc;
        private String favoutiteRestaurant;
        private String work;
        private String hometown;
        private String description;
        private String socialMedia;
        private int profileComplete;
        private int followers;
        private String connect;
        private String reqStatus;
        private List<EventCategoryBean> eventCategory;
        private List<VenueCategoryBean> venueCategory;
        private List<EventPreferencesBean> eventPreferences;
        private List<VenuePreferencesBean> venuePreferences;
        private List<VenuesBean> venues;
        private List<EventsBean> events;
        private List<LanguageBean> language;
        private List<FavouritesBean> favourites;
        public List<LanguageBean> getLanguage() {
            return language;
        }

        public void setLanguage(List<LanguageBean> language) {
            this.language = language;
        }


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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
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

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getKnownByName() {
            return knownByName;
        }

        public void setKnownByName(String knownByName) {
            this.knownByName = knownByName;
        }

        public String getDOB() {
            return DOB;
        }

        public void setDOB(String DOB) {
            this.DOB = DOB;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
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

        public String getFavoutiteCity() {
            return favoutiteCity;
        }

        public void setFavoutiteCity(String favoutiteCity) {
            this.favoutiteCity = favoutiteCity;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
        }

        public String getFavoutiteRestaurant() {
            return favoutiteRestaurant;
        }

        public void setFavoutiteRestaurant(String favoutiteRestaurant) {
            this.favoutiteRestaurant = favoutiteRestaurant;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public String getHometown() {
            return hometown;
        }

        public void setHometown(String hometown) {
            this.hometown = hometown;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSocialMedia() {
            return socialMedia;
        }

        public void setSocialMedia(String socialMedia) {
            this.socialMedia = socialMedia;
        }

        public int getProfileComplete() {
            return profileComplete;
        }

        public void setProfileComplete(int profileComplete) {
            this.profileComplete = profileComplete;
        }

        public int getFollowers() {
            return followers;
        }

        public void setFollowers(int followers) {
            this.followers = followers;
        }

        public String getConnect() {
            return connect;
        }

        public void setConnect(String connect) {
            this.connect = connect;
        }

        public String getReqStatus() {
            return reqStatus;
        }

        public void setReqStatus(String reqStatus) {
            this.reqStatus = reqStatus;
        }

        public List<EventCategoryBean> getEventCategory() {
            return eventCategory;
        }

        public void setEventCategory(List<EventCategoryBean> eventCategory) {
            this.eventCategory = eventCategory;
        }

        public List<VenueCategoryBean> getVenueCategory() {
            return venueCategory;
        }

        public void setVenueCategory(List<VenueCategoryBean> venueCategory) {
            this.venueCategory = venueCategory;
        }

        public List<EventPreferencesBean> getEventPreferences() {
            return eventPreferences;
        }

        public void setEventPreferences(List<EventPreferencesBean> eventPreferences) {
            this.eventPreferences = eventPreferences;
        }

        public List<VenuePreferencesBean> getVenuePreferences() {
            return venuePreferences;
        }

        public void setVenuePreferences(List<VenuePreferencesBean> venuePreferences) {
            this.venuePreferences = venuePreferences;
        }

        public List<VenuesBean> getVenues() {
            return venues;
        }

        public void setVenues(List<VenuesBean> venues) {
            this.venues = venues;
        }

        public List<EventsBean> getEvents() {
            return events;
        }

        public void setEvents(List<EventsBean> events) {
            this.events = events;
        }

        public List<FavouritesBean> getFavourites() {
            return favourites;
        }

        public void setFavourites(List<FavouritesBean> favourites) {
            this.favourites = favourites;
        }

        public static class EventCategoryBean {
            /**
             * categoryId : 1
             * categoryName : THE CITY
             * icon : https://s3.amazonaws.com/wengageapp/category/City.png
             * subCategory : [{"subCategoryId":5,"subCategoryName":"Special Events","icon":"https://s3.amazonaws.com/wengageapp/subcategory/SPECIAL-EVENTS.png","type":"event","optionalName":"","selected":1},{"subCategoryId":77,"subCategoryName":"Hotel Promotions","icon":"https://s3.amazonaws.com/wengageapp/subcategory/HOTEL-PROMOTIONS.png","type":"event","optionalName":"","selected":0}]
             */

            private int categoryId;
            private String categoryName;
            private String icon;
            private List<SubCategoryBean> subCategory;

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

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public List<SubCategoryBean> getSubCategory() {
                return subCategory;
            }

            public void setSubCategory(List<SubCategoryBean> subCategory) {
                this.subCategory = subCategory;
            }

            public static class SubCategoryBean {
                /**
                 * subCategoryId : 5
                 * subCategoryName : Special Events
                 * icon : https://s3.amazonaws.com/wengageapp/subcategory/SPECIAL-EVENTS.png
                 * type : event
                 * optionalName :
                 * selected : 1
                 */

                private int subCategoryId;
                private String subCategoryName;
                private String icon;
                private String type;
                private String optionalName;
                private int selected;

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

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getOptionalName() {
                    return optionalName;
                }

                public void setOptionalName(String optionalName) {
                    this.optionalName = optionalName;
                }

                public int getSelected() {
                    return selected;
                }

                public void setSelected(int selected) {
                    this.selected = selected;
                }
            }
        }

        public static class VenueCategoryBean {
            /**
             * categoryId : 1
             * categoryName : THE CITY
             * icon : https://s3.amazonaws.com/wengageapp/category/City.png
             * subCategory : [{"subCategoryId":1,"subCategoryName":"In The Know","icon":"https://s3.amazonaws.com/wengageapp/subcategory/IN-THE-KNOW.png","type":"venue","optionalName":"","selected":1},{"subCategoryId":2,"subCategoryName":"Getting Around","icon":"https://s3.amazonaws.com/wengageapp/subcategory/GETTING-AROUND.png","type":"venue","optionalName":"","selected":0},{"subCategoryId":3,"subCategoryName":"Top Attractions","icon":"https://s3.amazonaws.com/wengageapp/subcategory/TOP-ATTRACTIONS.png","type":"venue","optionalName":"","selected":1},{"subCategoryId":4,"subCategoryName":"Places To Stay","icon":"https://s3.amazonaws.com/wengageapp/subcategory/PLACES-TO-STAY.png","type":"venue","optionalName":"","selected":0},{"subCategoryId":78,"subCategoryName":"More","icon":"https://s3.amazonaws.com/wengageapp/subcategory/MORE.png","type":"venue","optionalName":"","selected":0}]
             */

            private int categoryId;
            private String categoryName;
            private String icon;
            private List<SubCategoryBeanX> subCategory;

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

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public List<SubCategoryBeanX> getSubCategory() {
                return subCategory;
            }

            public void setSubCategory(List<SubCategoryBeanX> subCategory) {
                this.subCategory = subCategory;
            }

            public static class SubCategoryBeanX {
                /**
                 * subCategoryId : 1
                 * subCategoryName : In The Know
                 * icon : https://s3.amazonaws.com/wengageapp/subcategory/IN-THE-KNOW.png
                 * type : venue
                 * optionalName :
                 * selected : 1
                 */

                private int subCategoryId;
                private String subCategoryName;
                private String icon;
                private String type;
                private String optionalName;
                private int selected;

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

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getOptionalName() {
                    return optionalName;
                }

                public void setOptionalName(String optionalName) {
                    this.optionalName = optionalName;
                }

                public int getSelected() {
                    return selected;
                }

                public void setSelected(int selected) {
                    this.selected = selected;
                }
            }
        }

        public static class EventPreferencesBean {
            /**
             * subCategoryId : 5
             * subCategoryName : Special Events
             * icon : https://s3.amazonaws.com/wengageapp/subcategory/SPECIAL-EVENTS.png
             */

            private int subCategoryId;
            private String subCategoryName;
            private String icon;

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

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

        public static class VenuePreferencesBean {
            /**
             * subCategoryId : 1
             * subCategoryName : In The Know
             * icon : https://s3.amazonaws.com/wengageapp/subcategory/IN-THE-KNOW.png
             */

            private int subCategoryId;
            private String subCategoryName;
            private String icon;

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

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

        public static class VenuesBean {
            /**
             * venueId : KovZpZAFlF1A
             * name : Mod Club
             * imageURL : https://s1.ticketm.net/dbimages/13837v.
             * avgRating : 0
             * reviewCount : 0
             */

            private String venueId;
            private String name;
            private String imageURL;
            private int avgRating;
            private int reviewCount;

            public String getVenueId() {
                return venueId;
            }

            public void setVenueId(String venueId) {
                this.venueId = venueId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImageURL() {
                return imageURL;
            }

            public void setImageURL(String imageURL) {
                this.imageURL = imageURL;
            }

            public int getAvgRating() {
                return avgRating;
            }

            public void setAvgRating(int avgRating) {
                this.avgRating = avgRating;
            }

            public int getReviewCount() {
                return reviewCount;
            }

            public void setReviewCount(int reviewCount) {
                this.reviewCount = reviewCount;
            }
        }

        public static class EventsBean {
            /**
             * eventId : 16tZA4S7dZACk8vu
             * name : Countdown New Year's Eve At Liberty Grand
             * imageURL : https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg
             * avgRating : 0
             * reviewCount : 0
             */

            private String eventId;
            private String name;
            private String imageURL;
            private int avgRating;
            private int reviewCount;

            public String getEventId() {
                return eventId;
            }

            public void setEventId(String eventId) {
                this.eventId = eventId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImageURL() {
                return imageURL;
            }

            public void setImageURL(String imageURL) {
                this.imageURL = imageURL;
            }

            public int getAvgRating() {
                return avgRating;
            }

            public void setAvgRating(int avgRating) {
                this.avgRating = avgRating;
            }

            public int getReviewCount() {
                return reviewCount;
            }

            public void setReviewCount(int reviewCount) {
                this.reviewCount = reviewCount;
            }
        }

        public static class LanguageBean {

            private String languageId;
            private String languageTitle;
            private int selected;

            public String getLanguageId() {
                return languageId;
            }

            public void setLanguageId(String languageId) {
                this.languageId = languageId;
            }

            public String getLanguageTitle() {
                return languageTitle;
            }

            public void setLanguageTitle(String languageTitle) {
                this.languageTitle = languageTitle;
            }

            public int getSelected() {
                return selected;
            }

            public void setSelected(int selected) {
                this.selected = selected;
            }

            /**
             * eventId : 16tZA4S7dZACk8vu
             * name : Countdown New Year's Eve At Liberty Grand
             * imageURL : https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg
             * avgRating : 0
             * reviewCount : 0
             */


        }

        public static class FavouritesBean {
            /**
             * venueId : KovZpZAFlF1A
             * name : Mod Club
             * imageURL : https://s1.ticketm.net/dbimages/13837v.
             * type : VENUE
             * eventId : 1k18v4_SGA26omf
             */

            private String venueId;
            private String name;
            private String imageURL;
            private String type;
            private String eventId;

            public String getVenueId() {
                return venueId;
            }

            public void setVenueId(String venueId) {
                this.venueId = venueId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImageURL() {
                return imageURL;
            }

            public void setImageURL(String imageURL) {
                this.imageURL = imageURL;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getEventId() {
                return eventId;
            }

            public void setEventId(String eventId) {
                this.eventId = eventId;
            }
        }
    }
}
