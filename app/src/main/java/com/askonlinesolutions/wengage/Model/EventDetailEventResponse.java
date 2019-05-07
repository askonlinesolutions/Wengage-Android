package com.askonlinesolutions.wengage.Model;

import java.util.List;

public class EventDetailEventResponse {

    /**
     * status : 1
     * message : Event Data
     * eventData : {"name":"Sloan","eventURL":"https://www.ticketmaster.ca/sloan-toronto-ontario-12-01-2018/event/10005533B2A16F44","locale":"en-us","imageURL":"https://s1.ticketm.net/dam/a/093/218f75b4-5691-4812-8a0a-50ed5dcdb093_665241_TABLET_LANDSCAPE_3_2.jpg","address":"410 Sherbourne Street","city":"Toronto","state":"Ontario","country":"Canada","longitude":"-79.3735993","latitude":"43.6644615","postalCode":"M4X 1K2","description":"","shortDesc":"Net Capacity: 1,300 Balcony private seating box is a premium seating area for up to 4 people, at the front of the balcony, featuring a prime unobstructed view & designated cocktail server. Box seats can not be sold individually. Complete booth (4 tickets) must be purchased together. For more information visit Live Nation","presales":[{"startDateTime":"2018-09-27T14:00:00Z","endDateTime":"2018-09-28T02:00:00Z","name":"Live Nation Presale / LEGAL AGE 19+","description":"Not a Live Nation Member? Sign up to receive Live Nation presale passwords.","url":"http://bit.ly/NGEM8y"},{"startDateTime":"2018-09-27T14:00:00Z","endDateTime":"2018-09-28T02:00:00Z","name":"Live Nation Mobile App Presale","description":"Download the Live Nation iPhone App now to access Live Nation Mobile App Presales. Browse, search, and discover concerts for your favorite artists near you; get alerts on presales, onsales, and last minute tickets; and easily and quickly purchase tickets while in the app. Check out set lists on the go, check in with your friends, view your ticket info, seating charts, exclusive photos, videos and more.","url":"http://www.livenation.com/mobile/"},{"startDateTime":"2018-09-27T14:00:00Z","endDateTime":"2018-09-28T02:00:00Z","name":"Facebook Presale / LEGAL AGE 19+"},{"startDateTime":"2018-09-25T14:00:00Z","endDateTime":"2018-09-28T02:00:00Z","name":"Front Of The Line by American Express","description":"For Front Of The Line Tickets, purchase must be charged in full to an American Express Card. Subject to availability and to event and ticketing agent terms, conditions and fees. Please note: All American Express Cards are eligible for American Express Invites tickets including Cardmembers from other countries outside of Canada."},{"startDateTime":"2018-10-05T21:00:00Z","endDateTime":"2018-10-06T21:00:00Z","name":"AIR MILES OFFER"},{"startDateTime":"2018-10-02T21:00:00Z","endDateTime":"2018-10-03T21:00:00Z","name":"GLOBAL CITIZEN"}],"seatMapURL":"https://s1.ticketm.net/tmimages/venue/maps/tor/62390s.gif","ticketLimit":"There is an 8 ticket limit except for the private booths where all 4 tickets must be purchased.","priceRange":{"currency":"CAD","min":29.5,"max":90},"promoter":{"id":"850","name":"LIVE NATION CANADA (LN)","description":"LIVE NATION CANADA (LN) / NTL / CAN"},"venueId":"KovZpZAFFEEA","influencerPick":0,"recommendByWengage":1,"eventId":"1A8ZAfSGkewIpW7","startDate":"2018-12-02T00:30:00.000Z","generateType":"TM","categoryId":5,"subCategoryId":50,"createdAt":"2018-11-27T04:36:44.042Z","updatedAt":"2018-12-21T06:43:53.050Z","distance":0,"venue":{"name":"The Phoenix Concert Theatre","venueURL":"https://www.ticketmaster.ca/the-phoenix-concert-theatre-tickets-toronto/venue/131109","locale":"en-us","imageURL":"https://s1.ticketm.net/dbimages/5753v.gif","address":"410 Sherbourne Street","city":"Toronto","state":"Ontario","country":"Canada","longitude":"-79.3735993","latitude":"43.6644615","postalCode":"M4X 1K2","timezone":"America/Toronto","description":"","shortDesc":"Most events are Legal Age 19 and over, with valid photo I.D. required. Check your tickets.","phoneNumberDetail":"General Info Line: 416.323.1251","openHoursDetail":"","acceptedPaymentDetail":"Cash only accepted at the Box Office.","willCallDetail":"THIS VENUE DOES NOT OPERATE A WILL-CALL FOR EVERY EVENT. Please pick up your tickets in advance from a Ticketmaster Ticket Centre.","parkingDetail":"There is a public parking lot across the street available at a cost.","accessibleSeatingDetail":"Accessible Seating: The Phoenix is not wheelchair accessible. Hearing Devices: None","reservable":1,"influencerPick":0,"recommendByWengage":1,"isOpen":1,"hours":{"Monday":"","Tuesday":"","Wednesday":"","Thursday":"","Friday":"","Saturday":""},"venueId":"KovZpZAFFEEA","generateType":"TM","categoryId":5,"subCategoryId":50,"createdAt":"2018-11-27T04:36:44.339Z","updatedAt":"2018-11-27T04:36:44.339Z"},"favouriteUsers":[{"influencer":0,"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1545847298593_829345.jpg","userId":218,"knownByName":"Rohit","city":"Toronto"}],"interestedUsers":[{"influencer":0,"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1545847298593_829345.jpg","userId":218,"knownByName":"Rohit","city":"Toronto"}],"avgRating":5,"reviewCount":0,"ratings":[{"rating":5,"review":"","userId":218,"knownByName":"Rohit","email":"rohit@askonlinesolutions.com","city":"Toronto","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1545847298593_829345.jpg","influencer":0}],"isBookmarked":1,"isInterested":1}
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
         * name : Sloan
         * eventURL : https://www.ticketmaster.ca/sloan-toronto-ontario-12-01-2018/event/10005533B2A16F44
         * locale : en-us
         * imageURL : https://s1.ticketm.net/dam/a/093/218f75b4-5691-4812-8a0a-50ed5dcdb093_665241_TABLET_LANDSCAPE_3_2.jpg
         * address : 410 Sherbourne Street
         * city : Toronto
         * state : Ontario
         * country : Canada
         * longitude : -79.3735993
         * latitude : 43.6644615
         * postalCode : M4X 1K2
         * description :
         * shortDesc : Net Capacity: 1,300 Balcony private seating box is a premium seating area for up to 4 people, at the front of the balcony, featuring a prime unobstructed view & designated cocktail server. Box seats can not be sold individually. Complete booth (4 tickets) must be purchased together. For more information visit Live Nation
         * presales : [{"startDateTime":"2018-09-27T14:00:00Z","endDateTime":"2018-09-28T02:00:00Z","name":"Live Nation Presale / LEGAL AGE 19+","description":"Not a Live Nation Member? Sign up to receive Live Nation presale passwords.","url":"http://bit.ly/NGEM8y"},{"startDateTime":"2018-09-27T14:00:00Z","endDateTime":"2018-09-28T02:00:00Z","name":"Live Nation Mobile App Presale","description":"Download the Live Nation iPhone App now to access Live Nation Mobile App Presales. Browse, search, and discover concerts for your favorite artists near you; get alerts on presales, onsales, and last minute tickets; and easily and quickly purchase tickets while in the app. Check out set lists on the go, check in with your friends, view your ticket info, seating charts, exclusive photos, videos and more.","url":"http://www.livenation.com/mobile/"},{"startDateTime":"2018-09-27T14:00:00Z","endDateTime":"2018-09-28T02:00:00Z","name":"Facebook Presale / LEGAL AGE 19+"},{"startDateTime":"2018-09-25T14:00:00Z","endDateTime":"2018-09-28T02:00:00Z","name":"Front Of The Line by American Express","description":"For Front Of The Line Tickets, purchase must be charged in full to an American Express Card. Subject to availability and to event and ticketing agent terms, conditions and fees. Please note: All American Express Cards are eligible for American Express Invites tickets including Cardmembers from other countries outside of Canada."},{"startDateTime":"2018-10-05T21:00:00Z","endDateTime":"2018-10-06T21:00:00Z","name":"AIR MILES OFFER"},{"startDateTime":"2018-10-02T21:00:00Z","endDateTime":"2018-10-03T21:00:00Z","name":"GLOBAL CITIZEN"}]
         * seatMapURL : https://s1.ticketm.net/tmimages/venue/maps/tor/62390s.gif
         * ticketLimit : There is an 8 ticket limit except for the private booths where all 4 tickets must be purchased.
         * priceRange : {"currency":"CAD","min":29.5,"max":90}
         * promoter : {"id":"850","name":"LIVE NATION CANADA (LN)","description":"LIVE NATION CANADA (LN) / NTL / CAN"}
         * venueId : KovZpZAFFEEA
         * influencerPick : 0
         * recommendByWengage : 1
         * eventId : 1A8ZAfSGkewIpW7
         * startDate : 2018-12-02T00:30:00.000Z
         * generateType : TM
         * categoryId : 5
         * subCategoryId : 50
         * createdAt : 2018-11-27T04:36:44.042Z
         * updatedAt : 2018-12-21T06:43:53.050Z
         * distance : 0
         * venue : {"name":"The Phoenix Concert Theatre","venueURL":"https://www.ticketmaster.ca/the-phoenix-concert-theatre-tickets-toronto/venue/131109","locale":"en-us","imageURL":"https://s1.ticketm.net/dbimages/5753v.gif","address":"410 Sherbourne Street","city":"Toronto","state":"Ontario","country":"Canada","longitude":"-79.3735993","latitude":"43.6644615","postalCode":"M4X 1K2","timezone":"America/Toronto","description":"","shortDesc":"Most events are Legal Age 19 and over, with valid photo I.D. required. Check your tickets.","phoneNumberDetail":"General Info Line: 416.323.1251","openHoursDetail":"","acceptedPaymentDetail":"Cash only accepted at the Box Office.","willCallDetail":"THIS VENUE DOES NOT OPERATE A WILL-CALL FOR EVERY EVENT. Please pick up your tickets in advance from a Ticketmaster Ticket Centre.","parkingDetail":"There is a public parking lot across the street available at a cost.","accessibleSeatingDetail":"Accessible Seating: The Phoenix is not wheelchair accessible. Hearing Devices: None","reservable":1,"influencerPick":0,"recommendByWengage":1,"isOpen":1,"hours":{"Monday":"","Tuesday":"","Wednesday":"","Thursday":"","Friday":"","Saturday":""},"venueId":"KovZpZAFFEEA","generateType":"TM","categoryId":5,"subCategoryId":50,"createdAt":"2018-11-27T04:36:44.339Z","updatedAt":"2018-11-27T04:36:44.339Z"}
         * favouriteUsers : [{"influencer":0,"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1545847298593_829345.jpg","userId":218,"knownByName":"Rohit","city":"Toronto"}]
         * interestedUsers : [{"influencer":0,"email":"rohit@askonlinesolutions.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1545847298593_829345.jpg","userId":218,"knownByName":"Rohit","city":"Toronto"}]
         * avgRating : 5
         * reviewCount : 0
         * ratings : [{"rating":5,"review":"","userId":218,"knownByName":"Rohit","email":"rohit@askonlinesolutions.com","city":"Toronto","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1545847298593_829345.jpg","influencer":0}]
         * isBookmarked : 1
         * isInterested : 1
         */

        private String name;
        private String categoryName;


        private String subCategoryName;
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
        private int subCategoryId;
        private String createdAt;
        private String updatedAt;
        private int distance;
        private VenueBean venue;
        private int avgRating;
        private int reviewCount;
        private int isBookmarked;
        private int isInterested;
        private List<PresalesBean> presales;
        private List<FavouriteUsersBean> favouriteUsers;
        private List<InterestedUsersBean> interestedUsers;
        private List<RatingsBean> ratings;

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
             * min : 29.5
             * max : 90
             */

            private String currency;
            private double min;
            private double max;

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }

            public double getMin() {
                return min;
            }

            public void setMin(double min) {
                this.min = min;
            }

            public double getMax() {
                return max;
            }

            public void setMax(double max) {
                this.max = max;
            }
        }

        public static class PromoterBean {
            /**
             * id : 850
             * name : LIVE NATION CANADA (LN)
             * description : LIVE NATION CANADA (LN) / NTL / CAN
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
             * isOpen : 1
             * hours : {"Monday":"","Tuesday":"","Wednesday":"","Thursday":"","Friday":"","Saturday":""}
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
            private int isOpen;
            private HoursBean hours;
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

            public int getIsOpen() {
                return isOpen;
            }

            public void setIsOpen(int isOpen) {
                this.isOpen = isOpen;
            }

            public HoursBean getHours() {
                return hours;
            }

            public void setHours(HoursBean hours) {
                this.hours = hours;
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

            public static class HoursBean {
                /**
                 * Monday :
                 * Tuesday :
                 * Wednesday :
                 * Thursday :
                 * Friday :
                 * Saturday :
                 */

                private String Monday;
                private String Tuesday;
                private String Wednesday;
                private String Thursday;
                private String Friday;
                private String Saturday;

                public String getMonday() {
                    return Monday;
                }

                public void setMonday(String Monday) {
                    this.Monday = Monday;
                }

                public String getTuesday() {
                    return Tuesday;
                }

                public void setTuesday(String Tuesday) {
                    this.Tuesday = Tuesday;
                }

                public String getWednesday() {
                    return Wednesday;
                }

                public void setWednesday(String Wednesday) {
                    this.Wednesday = Wednesday;
                }

                public String getThursday() {
                    return Thursday;
                }

                public void setThursday(String Thursday) {
                    this.Thursday = Thursday;
                }

                public String getFriday() {
                    return Friday;
                }

                public void setFriday(String Friday) {
                    this.Friday = Friday;
                }

                public String getSaturday() {
                    return Saturday;
                }

                public void setSaturday(String Saturday) {
                    this.Saturday = Saturday;
                }
            }
        }

        public static class PresalesBean {
            /**
             * startDateTime : 2018-09-27T14:00:00Z
             * endDateTime : 2018-09-28T02:00:00Z
             * name : Live Nation Presale / LEGAL AGE 19+
             * description : Not a Live Nation Member? Sign up to receive Live Nation presale passwords.
             * url : http://bit.ly/NGEM8y
             */

            private String startDateTime;
            private String endDateTime;
            private String name;
            private String description;
            private String url;

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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class FavouriteUsersBean {
            /**
             * influencer : 0
             * email : rohit@askonlinesolutions.com
             * photoURL : https://s3.amazonaws.com/wengageapp/profile/1545847298593_829345.jpg
             * userId : 218
             * knownByName : Rohit
             * city : Toronto
             */

            private int influencer;
            private String email;
            private String photoURL;
            private int userId;
            private String knownByName;
            private String city;

            public int getInfluencer() {
                return influencer;
            }

            public void setInfluencer(int influencer) {
                this.influencer = influencer;
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
             * influencer : 0
             * email : rohit@askonlinesolutions.com
             * photoURL : https://s3.amazonaws.com/wengageapp/profile/1545847298593_829345.jpg
             * userId : 218
             * knownByName : Rohit
             * city : Toronto
             */

            private int influencer;
            private String email;
            private String photoURL;
            private int userId;
            private String knownByName;
            private String city;

            public int getInfluencer() {
                return influencer;
            }

            public void setInfluencer(int influencer) {
                this.influencer = influencer;
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
             * rating : 5
             * review :
             * userId : 218
             * knownByName : Rohit
             * email : rohit@askonlinesolutions.com
             * city : Toronto
             * photoURL : https://s3.amazonaws.com/wengageapp/profile/1545847298593_829345.jpg
             * influencer : 0
             */

            private int rating;
            private String review;
            private int userId;
            private String knownByName;
            private String email;
            private String city;
            private String photoURL;
            private int influencer;

            public int getRating() {
                return rating;
            }

            public void setRating(int rating) {
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

            public int getInfluencer() {
                return influencer;
            }

            public void setInfluencer(int influencer) {
                this.influencer = influencer;
            }
        }
    }
}
