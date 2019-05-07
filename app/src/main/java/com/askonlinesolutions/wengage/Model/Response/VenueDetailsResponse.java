package com.askonlinesolutions.wengage.Model.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rakhi on 12/10/2018.
 */
public class VenueDetailsResponse implements Serializable {

    /**
     * status : 1
     * message : Venue Data
     * venueData : {"name":"The Garrison","venueURL":"https://www.ticketmaster.ca/the-garrison-tickets-toronto/venue/131922","locale":"en-us","imageURL":"https://s3.amazonaws.com/wengageapp/Image+not+found.png","address":"1197 Dundas Street West","city":"Toronto","state":"Ontario","country":"Canada","longitude":"-79.4223075","latitude":"43.6492732","postalCode":"M6J 1X3","timezone":"America/Toronto","description":"","shortDesc":"","phoneNumberDetail":"","openHoursDetail":"","acceptedPaymentDetail":"","willCallDetail":"","parkingDetail":"","accessibleSeatingDetail":"","reservable":1,"influencerPick":0,"recommendByWengage":0,"venueId":"KovZpZAdtAeA","generateType":"TM","categoryId":5,"subCategoryId":53,"createdAt":"2018-11-27T04:36:44.338Z","updatedAt":"2018-11-27T04:36:44.338Z","distance":0,"events":[{"name":"Noodles with Jess Connelly","eventURL":"https://www.ticketweb.ca/event/noodles-with-jess-connelly-the-garrison-tickets/8849195?REFERRAL_ID=tmfeed","locale":"en-us","imageURL":"https://s1.ticketm.net/dam/c/df8/81eadad8-4449-412e-a2b1-3d8bbb78edf8_106181_RETINA_PORTRAIT_3_2.jpg","address":"1197 Dundas Street West","city":"Toronto","state":"Ontario","country":"Canada","longitude":"-79.4223075","latitude":"43.6492732","postalCode":"M6J 1X3","description":"","shortDesc":"","presales":[],"seatMapURL":"","ticketLimit":"","priceRange":{"currency":"","min":0,"max":0},"promoter":{"id":"","name":"","description":""},"venueId":"KovZpZAdtAeA","influencerPick":0,"recommendByWengage":0,"eventId":"1Ae0Z44GklswdIB","startDate":"2018-12-13T01:00:00.000Z","generateType":"TM","categoryId":5,"createdAt":"2018-11-27T04:36:44.038Z","updatedAt":"2018-11-27T04:36:44.038Z"},{"name":"Brasstracks - The Vibrant Tour","eventURL":"https://www.ticketmaster.ca/brasstracks-the-vibrant-tour-toronto-ontario-02-09-2019/event/10005569DF0F51EF","locale":"en-us","imageURL":"https://s1.ticketm.net/dam/a/39a/14587b40-0035-4326-a03b-292b02d0e39a_894921_RETINA_PORTRAIT_16_9.jpg","address":"1197 Dundas Street West","city":"Toronto","state":"Ontario","country":"Canada","longitude":"-79.4223075","latitude":"43.6492732","postalCode":"M6J 1X3","description":"Doors: 6:30pm Show: 7:30pm This is a LEGAL AGE 19+ event.","shortDesc":"Net Capacity: 300 For more information visit Live Nation","presales":[{"startDateTime":"2018-11-15T15:00:00Z","endDateTime":"2018-11-16T03:00:00Z","name":"Live Nation Presale / LEGAL AGE 19+","description":"Not a Live Nation Member? Sign up to receive Live Nation presale passwords.","url":"http://bit.ly/GMW9Ni"},{"startDateTime":"2018-11-15T15:00:00Z","endDateTime":"2018-11-16T03:00:00Z","name":"Live Nation Mobile App Presale","description":"Download the Live Nation iPhone App now to access Live Nation Mobile App Presales. Browse, search, and discover concerts for your favorite artists near you; get alerts on presales, onsales, and last minute tickets; and easily and quickly purchase tickets while in the app. Check out set lists on the go, check in with your friends, view your ticket info, seating charts, exclusive photos, videos and more.","url":"http://www.livenation.com/mobile/"},{"startDateTime":"2018-11-15T15:00:00Z","endDateTime":"2018-11-16T03:00:00Z","name":"Facebook Presale / LEGAL AGE 19+"},{"startDateTime":"2018-11-23T15:00:00Z","endDateTime":"2018-11-24T15:00:00Z","name":"GPP"}],"seatMapURL":"","ticketLimit":"There is an 8 ticket limit for this event.","priceRange":{"currency":"CAD","min":25,"max":85},"promoter":{"id":"850","name":"LIVE NATION CANADA (LN)","description":"LIVE NATION CANADA (LN) / NTL / CAN"},"venueId":"KovZpZAdtAeA","influencerPick":0,"recommendByWengage":1,"eventId":"1A8ZA4tGkdLdg6R","startDate":"2019-02-09T23:30:00.000Z","generateType":"TM","categoryId":5,"createdAt":"2018-11-27T04:36:44.038Z","updatedAt":"2018-11-27T04:36:44.038Z"}],"favouriteUsers":[{"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg","userId":165,"knownByName":"Rohit Verma","city":"Toronto"}],"interestedUsers":[{"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg","userId":165,"knownByName":"Rohit Verma","city":"Toronto"},{"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg","userId":165,"knownByName":"Rohit Verma","city":"Toronto"},{"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg","userId":165,"knownByName":"Rohit Verma","city":"Toronto"},{"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg","userId":165,"knownByName":"Rohit Verma","city":"Toronto"},{"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg","userId":165,"knownByName":"Rohit Verma","city":"Toronto"}],"avgRating":3.5,"reviewCount":0,"ratings":[{"rating":3.5,"review":"","userId":165,"knownByName":"Rohit Verma","email":"rohit@askonlinesolutions.com","city":"Toronto","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg"}],"isBookmarked":1,"isInterested":1}
     */

    private int status;
    private String message;
    private VenueDataBean venueData;

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

    public VenueDataBean getVenueData() {
        return venueData;
    }

    public void setVenueData(VenueDataBean venueData) {
        this.venueData = venueData;
    }

    public static class VenueDataBean {
        /**
         * name : The Garrison
         * venueURL : https://www.ticketmaster.ca/the-garrison-tickets-toronto/venue/131922
         * locale : en-us
         * imageURL : https://s3.amazonaws.com/wengageapp/Image+not+found.png
         * address : 1197 Dundas Street West
         * city : Toronto
         * state : Ontario
         * country : Canada
         * longitude : -79.4223075
         * latitude : 43.6492732
         * postalCode : M6J 1X3
         * timezone : America/Toronto
         * description :
         * shortDesc :
         * phoneNumberDetail :
         * openHoursDetail :
         * acceptedPaymentDetail :
         * willCallDetail :
         * parkingDetail :
         * accessibleSeatingDetail :
         * reservable : 1
         * influencerPick : 0
         * recommendByWengage : 0
         * venueId : KovZpZAdtAeA
         * generateType : TM
         * categoryId : 5
         * subCategoryId : 53
         * createdAt : 2018-11-27T04:36:44.338Z
         * updatedAt : 2018-11-27T04:36:44.338Z
         * distance : 0
         * events : [{"name":"Noodles with Jess Connelly","eventURL":"https://www.ticketweb.ca/event/noodles-with-jess-connelly-the-garrison-tickets/8849195?REFERRAL_ID=tmfeed","locale":"en-us","imageURL":"https://s1.ticketm.net/dam/c/df8/81eadad8-4449-412e-a2b1-3d8bbb78edf8_106181_RETINA_PORTRAIT_3_2.jpg","address":"1197 Dundas Street West","city":"Toronto","state":"Ontario","country":"Canada","longitude":"-79.4223075","latitude":"43.6492732","postalCode":"M6J 1X3","description":"","shortDesc":"","presales":[],"seatMapURL":"","ticketLimit":"","priceRange":{"currency":"","min":0,"max":0},"promoter":{"id":"","name":"","description":""},"venueId":"KovZpZAdtAeA","influencerPick":0,"recommendByWengage":0,"eventId":"1Ae0Z44GklswdIB","startDate":"2018-12-13T01:00:00.000Z","generateType":"TM","categoryId":5,"createdAt":"2018-11-27T04:36:44.038Z","updatedAt":"2018-11-27T04:36:44.038Z"},{"name":"Brasstracks - The Vibrant Tour","eventURL":"https://www.ticketmaster.ca/brasstracks-the-vibrant-tour-toronto-ontario-02-09-2019/event/10005569DF0F51EF","locale":"en-us","imageURL":"https://s1.ticketm.net/dam/a/39a/14587b40-0035-4326-a03b-292b02d0e39a_894921_RETINA_PORTRAIT_16_9.jpg","address":"1197 Dundas Street West","city":"Toronto","state":"Ontario","country":"Canada","longitude":"-79.4223075","latitude":"43.6492732","postalCode":"M6J 1X3","description":"Doors: 6:30pm Show: 7:30pm This is a LEGAL AGE 19+ event.","shortDesc":"Net Capacity: 300 For more information visit Live Nation","presales":[{"startDateTime":"2018-11-15T15:00:00Z","endDateTime":"2018-11-16T03:00:00Z","name":"Live Nation Presale / LEGAL AGE 19+","description":"Not a Live Nation Member? Sign up to receive Live Nation presale passwords.","url":"http://bit.ly/GMW9Ni"},{"startDateTime":"2018-11-15T15:00:00Z","endDateTime":"2018-11-16T03:00:00Z","name":"Live Nation Mobile App Presale","description":"Download the Live Nation iPhone App now to access Live Nation Mobile App Presales. Browse, search, and discover concerts for your favorite artists near you; get alerts on presales, onsales, and last minute tickets; and easily and quickly purchase tickets while in the app. Check out set lists on the go, check in with your friends, view your ticket info, seating charts, exclusive photos, videos and more.","url":"http://www.livenation.com/mobile/"},{"startDateTime":"2018-11-15T15:00:00Z","endDateTime":"2018-11-16T03:00:00Z","name":"Facebook Presale / LEGAL AGE 19+"},{"startDateTime":"2018-11-23T15:00:00Z","endDateTime":"2018-11-24T15:00:00Z","name":"GPP"}],"seatMapURL":"","ticketLimit":"There is an 8 ticket limit for this event.","priceRange":{"currency":"CAD","min":25,"max":85},"promoter":{"id":"850","name":"LIVE NATION CANADA (LN)","description":"LIVE NATION CANADA (LN) / NTL / CAN"},"venueId":"KovZpZAdtAeA","influencerPick":0,"recommendByWengage":1,"eventId":"1A8ZA4tGkdLdg6R","startDate":"2019-02-09T23:30:00.000Z","generateType":"TM","categoryId":5,"createdAt":"2018-11-27T04:36:44.038Z","updatedAt":"2018-11-27T04:36:44.038Z"}]
         * favouriteUsers : [{"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg","userId":165,"knownByName":"Rohit Verma","city":"Toronto"}]
         * interestedUsers : [{"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg","userId":165,"knownByName":"Rohit Verma","city":"Toronto"},{"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg","userId":165,"knownByName":"Rohit Verma","city":"Toronto"},{"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg","userId":165,"knownByName":"Rohit Verma","city":"Toronto"},{"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg","userId":165,"knownByName":"Rohit Verma","city":"Toronto"},{"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg","userId":165,"knownByName":"Rohit Verma","city":"Toronto"}]
         * avgRating : 3.5
         * reviewCount : 0
         * ratings : [{"rating":3.5,"review":"","userId":165,"knownByName":"Rohit Verma","email":"rohit@askonlinesolutions.com","city":"Toronto","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg"}]
         * isBookmarked : 1
         * isInterested : 1
         */

        private String name;
        private String categoryName;
        private String subCategoryName;
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
        private int distance;
        private double avgRating;
        private int reviewCount;
        private int isBookmarked;
        private int isInterested;
        private List<EventsBean> events;
        private List<FavouriteUsersBean> favouriteUsers;
        private List<InterestedUsersBean> interestedUsers;
        private List<RatingsBean> ratings;
        private HoursBean hours;
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

        public HoursBean getHours() {
            return hours;
        }

        public void setHours(HoursBean hours) {
            this.hours = hours;
        }
        public HoursBean getHoursBean() {
            return hours;
        }

        public void setHoursBean(HoursBean hoursBean) {
            this.hours = hoursBean;
        }


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

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public double getAvgRating() {
            return avgRating;
        }

        public void setAvgRating(double avgRating) {
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

        public List<EventsBean> getEvents() {
            return events;
        }

        public void setEvents(List<EventsBean> events) {
            this.events = events;
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

        public static class EventsBean {
            /**
             * name : Noodles with Jess Connelly
             * eventURL : https://www.ticketweb.ca/event/noodles-with-jess-connelly-the-garrison-tickets/8849195?REFERRAL_ID=tmfeed
             * locale : en-us
             * imageURL : https://s1.ticketm.net/dam/c/df8/81eadad8-4449-412e-a2b1-3d8bbb78edf8_106181_RETINA_PORTRAIT_3_2.jpg
             * address : 1197 Dundas Street West
             * city : Toronto
             * state : Ontario
             * country : Canada
             * longitude : -79.4223075
             * latitude : 43.6492732
             * postalCode : M6J 1X3
             * description :
             * shortDesc :
             * presales : []
             * seatMapURL :
             * ticketLimit :
             * priceRange : {"currency":"","min":0,"max":0}
             * promoter : {"id":"","name":"","description":""}
             * venueId : KovZpZAdtAeA
             * influencerPick : 0
             * recommendByWengage : 0
             * eventId : 1Ae0Z44GklswdIB
             * startDate : 2018-12-13T01:00:00.000Z
             * generateType : TM
             * categoryId : 5
             * createdAt : 2018-11-27T04:36:44.038Z
             * updatedAt : 2018-11-27T04:36:44.038Z
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
            private String startDate;
            private String generateType;
            private int categoryId;
            private String createdAt;
            private String updatedAt;
            private List<?> presales;

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

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
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

            public List<?> getPresales() {
                return presales;
            }

            public void setPresales(List<?> presales) {
                this.presales = presales;
            }

            public static class PriceRangeBean {
                /**
                 * currency :
                 * min : 0
                 * max : 0
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
                 * id :
                 * name :
                 * description :
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
        }

        public static class FavouriteUsersBean {
            /**
             * email : rohit@askonlinesolutions.com
             * photoURL : https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg
             * userId : 165
             * knownByName : Rohit Verma
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
             * email : rohit@askonlinesolutions.com
             * photoURL : https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg
             * userId : 165
             * knownByName : Rohit Verma
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

        public static class RatingsBean {
            /**
             * rating : 3.5
             * review :
             * userId : 165
             * knownByName : Rohit Verma
             * email : rohit@askonlinesolutions.com
             * city : Toronto
             * photoURL : https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg
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
        public static class HoursBean {/*
 "Monday": "19:0-20:30",
         "Sunday": "9:0-13:0"*/

            private String Sunday;
            private String Monday;
            private String Tuesday;
            private String Wednesday;
            private String Thursday;
            private String Friday;
            private String Saturday;

            public String getSunday() {
                return Sunday;
            }

            public void setSunday(String sunday) {
                Sunday = sunday;
            }

            public String getMonday() {
                return Monday;
            }

            public void setMonday(String monday) {
                Monday = monday;
            }

            public String getTuesday() {
                return Tuesday;
            }

            public void setTuesday(String tuesday) {
                Tuesday = tuesday;
            }

            public String getWednesday() {
                return Wednesday;
            }

            public void setWednesday(String wednesday) {
                Wednesday = wednesday;
            }

            public String getThrusday() {
                return Thursday;
            }

            public void setThrusday(String thrusday) {
                Thursday = thrusday;
            }

            public String getFriday() {
                return Friday;
            }

            public void setFriday(String friday) {
                Friday = friday;
            }

            public String getSaturday() {
                return Saturday;
            }

            public void setSaturday(String saturday) {
                Saturday = saturday;
            }


        }

    }

}
