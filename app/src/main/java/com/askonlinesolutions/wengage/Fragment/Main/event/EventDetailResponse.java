package com.askonlinesolutions.wengage.Fragment.Main.event;

import java.util.List;

/**
 * Created by Rakhi on 12/17/2018.
 */
public class EventDetailResponse {

    /**
     * status : 1
     * message : Event Data
     * eventData : {"name":"160th Queen's Plate Racing Festival: Garden Social Pass","eventURL":"https://www.ticketmaster.ca/160th-queens-plate-racing-festival-garden-toronto-ontario-06-28-2019/event/1000556EF0B75845","locale":"en-us","imageURL":"https://s1.ticketm.net/dam/a/9f8/3b7c8d9c-a06f-4d0e-a5ef-1b17239269f8_585661_TABLET_LANDSCAPE_LARGE_16_9.jpg","address":"555 Rexdale Blvd.","city":"Toronto","state":"Ontario","country":"Canada","longitude":"-79.596441","latitude":"43.714728","postalCode":"M9W 5L2","description":"","shortDesc":"Immerse yourself in an enchanting garden social experience. Rub elbows with the fashion friendly while indulging in delectable gourmet treats, live music and much more in a relaxed, botanical setting. With your Garden Social Pass you will receive: - Exclusive access to the Garden Social tent on Saturday, June 29th - Gourmet food stations and delectable hors d'oeuvres - Access to the fabulous Hats & Horseshoes Party on Friday, June 28th and Saturday, June 29th - Trackside access to watch the 160th running of the Queen's Plate and Canada's best in thoroughbred horse racing, right at the rail NOTE - Hats and Horseshoes Party is not track facing Access to select areas may be restricted Net Capacity: 400","presales":[{"startDateTime":"2018-12-01T15:00:00Z","endDateTime":"2018-12-06T04:59:00Z","name":"VIP Presale"}],"seatMapURL":"","ticketLimit":"","priceRange":{"currency":"CAD","min":125,"max":125},"promoter":{"id":"320","name":"PROMOTER NOT DEFINED","description":"PROMOTER NOT DEFINED / NTL / CAN"},"venueId":"KovZpZAFlvEA","influencerPick":0,"recommendByWengage":0,"eventId":"1AvZZ4pGkDYcg-A","startDate":null,"generateType":"TM","categoryId":7,"subCategoryId":76,"createdAt":"2018-12-02T19:00:08.000Z","updatedAt":"2018-12-02T19:00:08.000Z","distance":0,"venue":null,"favouriteUsers":[{"email":"sandra.pellegrini@rogers.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1544479736042_705531.jpg","userId":187,"knownByName":"Sandra","city":"Toronto"},{"email":"akhileshkumar4994@gmail.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1544688191844_723217.jpg","userId":191,"city":"Toronto","knownByName":"Aagyi"},{"email":"s.rakhi@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1544687112799_067740.jpg","userId":195,"city":"Torontss","knownByName":"Rakhi Singh"}],"interestedUsers":[{"email":"sandra.pellegrini@rogers.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1544479736042_705531.jpg","userId":187,"knownByName":"Sandra","city":"Toronto"},{"email":"s.rakhi@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1544687112799_067740.jpg","userId":195,"city":"Torontss","knownByName":"Rakhi Singh"}],"avgRating":0,"reviewCount":0,"ratings":[],"isBookmarked":1,"isInterested":0}
     */

    private int status;
    private String message;
    private EventDataBean eventData;

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

    public EventDataBean getEventData() {
        return eventData;
    }

    public void setEventData(EventDataBean eventData) {
        this.eventData = eventData;
    }

    public static class EventDataBean {
        /**
         * name : 160th Queen's Plate Racing Festival: Garden Social Pass
         * eventURL : https://www.ticketmaster.ca/160th-queens-plate-racing-festival-garden-toronto-ontario-06-28-2019/event/1000556EF0B75845
         * locale : en-us
         * imageURL : https://s1.ticketm.net/dam/a/9f8/3b7c8d9c-a06f-4d0e-a5ef-1b17239269f8_585661_TABLET_LANDSCAPE_LARGE_16_9.jpg
         * address : 555 Rexdale Blvd.
         * city : Toronto
         * state : Ontario
         * country : Canada
         * longitude : -79.596441
         * latitude : 43.714728
         * postalCode : M9W 5L2
         * description :
         * shortDesc : Immerse yourself in an enchanting garden social experience. Rub elbows with the fashion friendly while indulging in delectable gourmet treats, live music and much more in a relaxed, botanical setting. With your Garden Social Pass you will receive: - Exclusive access to the Garden Social tent on Saturday, June 29th - Gourmet food stations and delectable hors d'oeuvres - Access to the fabulous Hats & Horseshoes Party on Friday, June 28th and Saturday, June 29th - Trackside access to watch the 160th running of the Queen's Plate and Canada's best in thoroughbred horse racing, right at the rail NOTE - Hats and Horseshoes Party is not track facing Access to select areas may be restricted Net Capacity: 400
         * presales : [{"startDateTime":"2018-12-01T15:00:00Z","endDateTime":"2018-12-06T04:59:00Z","name":"VIP Presale"}]
         * seatMapURL :
         * ticketLimit :
         * priceRange : {"currency":"CAD","min":125,"max":125}
         * promoter : {"id":"320","name":"PROMOTER NOT DEFINED","description":"PROMOTER NOT DEFINED / NTL / CAN"}
         * venueId : KovZpZAFlvEA
         * influencerPick : 0
         * recommendByWengage : 0
         * eventId : 1AvZZ4pGkDYcg-A
         * startDate : null
         * generateType : TM
         * categoryId : 7
         * subCategoryId : 76
         * createdAt : 2018-12-02T19:00:08.000Z
         * updatedAt : 2018-12-02T19:00:08.000Z
         * distance : 0
         * venue : null
         * favouriteUsers : [{"email":"sandra.pellegrini@rogers.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1544479736042_705531.jpg","userId":187,"knownByName":"Sandra","city":"Toronto"},{"email":"akhileshkumar4994@gmail.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1544688191844_723217.jpg","userId":191,"city":"Toronto","knownByName":"Aagyi"},{"email":"s.rakhi@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1544687112799_067740.jpg","userId":195,"city":"Torontss","knownByName":"Rakhi Singh"}]
         * interestedUsers : [{"email":"sandra.pellegrini@rogers.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1544479736042_705531.jpg","userId":187,"knownByName":"Sandra","city":"Toronto"},{"email":"s.rakhi@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1544687112799_067740.jpg","userId":195,"city":"Torontss","knownByName":"Rakhi Singh"}]
         * avgRating : 0
         * reviewCount : 0
         * ratings : []
         * isBookmarked : 1
         * isInterested : 0
         */

        private String name;
        private String eventURL;
        private String locale;
        private String imageURL;
        private String address;
        private String city;
        private String state;
        private String country;
        private String longitude;
        private String latitude;
        private String postalCode;
        private String description;
        private String shortDesc;
        private String seatMapURL;
        private String ticketLimit;
        private PriceRangeBean priceRange;
        private PromoterBean promoter;
        private String venueId;
        private int influencerPick;
        private int recommendByWengage;
        private String eventId;
        private Object startDate;
        private String generateType;
        private int categoryId;
        private int subCategoryId;
        private String createdAt;
        private String updatedAt;
        private int distance;
        private VenueBean venue;
        private String avgRating;
        private int reviewCount;
        private int isBookmarked;
        private int isInterested;
        private List<PresalesBean> presales;
        private List<FavouriteUsersBean> favouriteUsers;
        private List<InterestedUsersBean> interestedUsers;
        private List<RatingsBean> ratings;


        public static class RatingsBean {
            /**
             * rating : 3.5
             * review :
             * userId : 191
             * knownByName : Rakhi Singh
             * email : akhileshkumar4994@gmail.com
             * city : Toronto
             * photoURL : https://s3.amazonaws.com/wengageapp/profile/1544688191844_723217.jpg
             */

            private double rating;
            private String review;
            private int userId;
            private String knownByName;
            private String email;
            private String city;
            private String photoURL;

            public double getRating() {
                return rating;
            }

            public void setRating(double rating) {
                this.rating = rating;
            }

            public String getReview() {
                return review;
            }

            public void setReview(String review) {
                this.review = review;
            }

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

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPhotoURL() {
                return photoURL;
            }

            public void setPhotoURL(String photoURL) {
                this.photoURL = photoURL;
            }
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEventURL() {
            return eventURL;
        }

        public void setEventURL(String eventURL) {
            this.eventURL = eventURL;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
        }

        public String getSeatMapURL() {
            return seatMapURL;
        }

        public void setSeatMapURL(String seatMapURL) {
            this.seatMapURL = seatMapURL;
        }

        public String getTicketLimit() {
            return ticketLimit;
        }

        public void setTicketLimit(String ticketLimit) {
            this.ticketLimit = ticketLimit;
        }

        public PriceRangeBean getPriceRange() {
            return priceRange;
        }

        public void setPriceRange(PriceRangeBean priceRange) {
            this.priceRange = priceRange;
        }

        public PromoterBean getPromoter() {
            return promoter;
        }

        public void setPromoter(PromoterBean promoter) {
            this.promoter = promoter;
        }

        public String getVenueId() {
            return venueId;
        }

        public void setVenueId(String venueId) {
            this.venueId = venueId;
        }

        public int getInfluencerPick() {
            return influencerPick;
        }

        public void setInfluencerPick(int influencerPick) {
            this.influencerPick = influencerPick;
        }

        public int getRecommendByWengage() {
            return recommendByWengage;
        }

        public void setRecommendByWengage(int recommendByWengage) {
            this.recommendByWengage = recommendByWengage;
        }

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public Object getStartDate() {
            return startDate;
        }

        public void setStartDate(Object startDate) {
            this.startDate = startDate;
        }

        public String getGenerateType() {
            return generateType;
        }

        public void setGenerateType(String generateType) {
            this.generateType = generateType;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getSubCategoryId() {
            return subCategoryId;
        }

        public void setSubCategoryId(int subCategoryId) {
            this.subCategoryId = subCategoryId;
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

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public VenueBean getVenue() {
            return venue;
        }

        public void setVenue(VenueBean venue) {
            this.venue = venue;
        }

        public String getAvgRating() {
            return avgRating;
        }

        public void setAvgRating(String avgRating) {
            this.avgRating = avgRating;
        }

        public int getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(int reviewCount) {
            this.reviewCount = reviewCount;
        }

        public int getIsBookmarked() {
            return isBookmarked;
        }

        public void setIsBookmarked(int isBookmarked) {
            this.isBookmarked = isBookmarked;
        }

        public int getIsInterested() {
            return isInterested;
        }

        public void setIsInterested(int isInterested) {
            this.isInterested = isInterested;
        }

        public List<PresalesBean> getPresales() {
            return presales;
        }

        public void setPresales(List<PresalesBean> presales) {
            this.presales = presales;
        }

        public List<FavouriteUsersBean> getFavouriteUsers() {
            return favouriteUsers;
        }

        public void setFavouriteUsers(List<FavouriteUsersBean> favouriteUsers) {
            this.favouriteUsers = favouriteUsers;
        }

        public List<InterestedUsersBean> getInterestedUsers() {
            return interestedUsers;
        }

        public void setInterestedUsers(List<InterestedUsersBean> interestedUsers) {
            this.interestedUsers = interestedUsers;
        }

        public List<RatingsBean> getRatings() {
            return ratings;
        }

        public void setRatings(List<RatingsBean> ratings) {
            this.ratings = ratings;
        }

        public static class PriceRangeBean {
            /**
             * currency : CAD
             * min : 125
             * max : 125
             */

            private String currency;
            private String min;
            private String max;

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }

            public String getMin() {
                return min;
            }

            public void setMin(String min) {
                this.min = min;
            }

            public String getMax() {
                return max;
            }

            public void setMax(String max) {
                this.max = max;
            }
        }

        public static class PromoterBean {
            /**
             * id : 320
             * name : PROMOTER NOT DEFINED
             * description : PROMOTER NOT DEFINED / NTL / CAN
             */

            private String id;
            private String name;
            private String description;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public static class PresalesBean {
            /**
             * startDateTime : 2018-12-01T15:00:00Z
             * endDateTime : 2018-12-06T04:59:00Z
             * name : VIP Presale
             */

            private String startDateTime;
            private String endDateTime;
            private String name;

            public String getStartDateTime() {
                return startDateTime;
            }

            public void setStartDateTime(String startDateTime) {
                this.startDateTime = startDateTime;
            }

            public String getEndDateTime() {
                return endDateTime;
            }

            public void setEndDateTime(String endDateTime) {
                this.endDateTime = endDateTime;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
        public static class VenueBean {
            /**
             * name : The Phoenix Concert Theatre
             * venueURL : https://www.ticketmaster.ca/the-phoenix-concert-theatre-tickets-toronto/venue/131109
             * locale : en-us
             * imageURL : https://s1.ticketm.net/dbimages/5753v.gif
             * address : 410 Sherbourne Street
             * city : Toronto
             * state : Ontario
             * country : Canada
             * longitude : -79.3735993
             * latitude : 43.6644615
             * postalCode : M4X 1K2
             * timezone : America/Toronto
             * description :
             * shortDesc : Most events are Legal Age 19 and over, with valid photo I.D. required. Check your tickets.
             * phoneNumberDetail : General Info Line: 416.323.1251
             * openHoursDetail :
             * acceptedPaymentDetail : Cash only accepted at the Box Office.
             * willCallDetail : THIS VENUE DOES NOT OPERATE A WILL-CALL FOR EVERY EVENT. Please pick up your tickets in advance from a Ticketmaster Ticket Centre.
             * parkingDetail : There is a public parking lot across the street available at a cost.
             * accessibleSeatingDetail : Accessible Seating: The Phoenix is not wheelchair accessible. Hearing Devices: None
             * reservable : 1
             * influencerPick : 0
             * recommendByWengage : 1
             * venueId : KovZpZAFFEEA
             * generateType : TM
             * categoryId : 5
             * subCategoryId : 50
             * createdAt : 2018-11-27T04:36:44.339Z
             * updatedAt : 2018-11-27T04:36:44.339Z
             */

            private String name;
            private String venueURL;
            private String locale;
            private String imageURL;
            private String address;
            private String city;
            private String state;
            private String country;
            private String longitude;
            private String latitude;
            private String postalCode;
            private String timezone;
            private String description;
            private String shortDesc;
            private String phoneNumberDetail;
            private String openHoursDetail;
            private String acceptedPaymentDetail;
            private String willCallDetail;
            private String parkingDetail;
            private String accessibleSeatingDetail;
            private int reservable;
            private int influencerPick;
            private int recommendByWengage;
            private String venueId;
            private String generateType;
            private int categoryId;
            private int subCategoryId;
            private String createdAt;
            private String updatedAt;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getVenueURL() {
                return venueURL;
            }

            public void setVenueURL(String venueURL) {
                this.venueURL = venueURL;
            }

            public String getLocale() {
                return locale;
            }

            public void setLocale(String locale) {
                this.locale = locale;
            }

            public String getImageURL() {
                return imageURL;
            }

            public void setImageURL(String imageURL) {
                this.imageURL = imageURL;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getPostalCode() {
                return postalCode;
            }

            public void setPostalCode(String postalCode) {
                this.postalCode = postalCode;
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getShortDesc() {
                return shortDesc;
            }

            public void setShortDesc(String shortDesc) {
                this.shortDesc = shortDesc;
            }

            public String getPhoneNumberDetail() {
                return phoneNumberDetail;
            }

            public void setPhoneNumberDetail(String phoneNumberDetail) {
                this.phoneNumberDetail = phoneNumberDetail;
            }

            public String getOpenHoursDetail() {
                return openHoursDetail;
            }

            public void setOpenHoursDetail(String openHoursDetail) {
                this.openHoursDetail = openHoursDetail;
            }

            public String getAcceptedPaymentDetail() {
                return acceptedPaymentDetail;
            }

            public void setAcceptedPaymentDetail(String acceptedPaymentDetail) {
                this.acceptedPaymentDetail = acceptedPaymentDetail;
            }

            public String getWillCallDetail() {
                return willCallDetail;
            }

            public void setWillCallDetail(String willCallDetail) {
                this.willCallDetail = willCallDetail;
            }

            public String getParkingDetail() {
                return parkingDetail;
            }

            public void setParkingDetail(String parkingDetail) {
                this.parkingDetail = parkingDetail;
            }

            public String getAccessibleSeatingDetail() {
                return accessibleSeatingDetail;
            }

            public void setAccessibleSeatingDetail(String accessibleSeatingDetail) {
                this.accessibleSeatingDetail = accessibleSeatingDetail;
            }

            public int getReservable() {
                return reservable;
            }

            public void setReservable(int reservable) {
                this.reservable = reservable;
            }

            public int getInfluencerPick() {
                return influencerPick;
            }

            public void setInfluencerPick(int influencerPick) {
                this.influencerPick = influencerPick;
            }

            public int getRecommendByWengage() {
                return recommendByWengage;
            }

            public void setRecommendByWengage(int recommendByWengage) {
                this.recommendByWengage = recommendByWengage;
            }

            public String getVenueId() {
                return venueId;
            }

            public void setVenueId(String venueId) {
                this.venueId = venueId;
            }

            public String getGenerateType() {
                return generateType;
            }

            public void setGenerateType(String generateType) {
                this.generateType = generateType;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public int getSubCategoryId() {
                return subCategoryId;
            }

            public void setSubCategoryId(int subCategoryId) {
                this.subCategoryId = subCategoryId;
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
        }


        public static class FavouriteUsersBean {
            /**
             * email : sandra.pellegrini@rogers.com
             * photoURL : https://s3.amazonaws.com/wengageapp/profile/1544479736042_705531.jpg
             * userId : 187
             * knownByName : Sandra
             * city : Toronto
             */

            private String email;
            private String photoURL;
            private int userId;
            private String knownByName;
            private String city;

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

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }
        }

        public static class InterestedUsersBean {
            /**
             * email : sandra.pellegrini@rogers.com
             * photoURL : https://s3.amazonaws.com/wengageapp/profile/1544479736042_705531.jpg
             * userId : 187
             * knownByName : Sandra
             * city : Toronto
             */

            private String email;
            private String photoURL;
            private int userId;
            private String knownByName;
            private String city;

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

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }
        }
    }
}
