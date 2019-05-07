package com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.vo;

import java.util.List;

public class InterestedEventsResponse {


    /**
     * status : 1
     * message : Events List
     * totalCount : 470
     * pageNum : 1
     * next : 1
     * totalPages : 48
     * eventsList : [{"name":"Parquet Courts","eventURL":"https://www.ticketmaster.ca/parquet-courts-toronto-ontario-12-05-2018/event/100054FC0C098299","locale":"en-us","imageURL":"https://s1.ticketm.net/dam/a/c69/7d7d3242-0eca-4ab5-9a04-ad5a5041ec69_64491_RECOMENDATION_16_9.jpg","address":"147 Danforth Ave.","city":"Toronto","state":"Ontario","country":"Canada","longitude":"-79.3571014","latitude":"43.6764492","postalCode":"M4K 1N2","description":"19+ Event/Valid Photo ID Required Doors - 7:00pm","shortDesc":"Net capacity:1427 This is a 19+ Event, everyone must be over 19 and show valid Government issued Photo ID to enter. The Floor is General Admission (Standing, NO SEATS), Reserved Seats are in the Mezzanine/Balcony For more event or venue information visit The Danforth Music Hall","presales":[{"startDateTime":"2018-08-02T14:00:00Z","endDateTime":"2018-08-03T02:00:00Z","name":"Collective Concerts Presale (19+)"}],"seatMapURL":"https://s1.ticketm.net/tmimages/venue/maps/tor/47465s.gif","ticketLimit":"There is an overall 8 ticket limit for this event.","priceRange":{"currency":"CAD","min":22.5,"max":35},"promoter":{"id":"494","name":"PROMOTED BY VENUE","description":"PROMOTED BY VENUE / NTL / USA"},"venueId":"KovZpa3yBe","influencerPick":0,"recommendByWengage":1,"eventId":"177ZvfG62YGeb1_","startDate":"2018-12-06T00:00:00.000Z","generateType":"TM","categoryId":5,"subCategoryId":50,"createdAt":"2018-11-27T04:36:44.040Z","updatedAt":"2018-11-27T04:36:44.040Z","avgRating":0,"reviewCount":0,"isInterested":1,"isBookmarked":0}]
     */

    private int status;
    private String message;
    private int totalCount;
    private String pageNum;
    private int next;
    private int totalPages;
    private List<EventsListResponse> eventsList;

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

    public List<EventsListResponse> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<EventsListResponse> eventsList) {
        this.eventsList = eventsList;
    }

    public static class EventsListResponse {
        /**
         * name : Parquet Courts
         * eventURL : https://www.ticketmaster.ca/parquet-courts-toronto-ontario-12-05-2018/event/100054FC0C098299
         * locale : en-us
         * imageURL : https://s1.ticketm.net/dam/a/c69/7d7d3242-0eca-4ab5-9a04-ad5a5041ec69_64491_RECOMENDATION_16_9.jpg
         * address : 147 Danforth Ave.
         * city : Toronto
         * state : Ontario
         * country : Canada
         * longitude : -79.3571014
         * latitude : 43.6764492
         * postalCode : M4K 1N2
         * description : 19+ Event/Valid Photo ID Required Doors - 7:00pm
         * shortDesc : Net capacity:1427 This is a 19+ Event, everyone must be over 19 and show valid Government issued Photo ID to enter. The Floor is General Admission (Standing, NO SEATS), Reserved Seats are in the Mezzanine/Balcony For more event or venue information visit The Danforth Music Hall
         * presales : [{"startDateTime":"2018-08-02T14:00:00Z","endDateTime":"2018-08-03T02:00:00Z","name":"Collective Concerts Presale (19+)"}]
         * seatMapURL : https://s1.ticketm.net/tmimages/venue/maps/tor/47465s.gif
         * ticketLimit : There is an overall 8 ticket limit for this event.
         * priceRange : {"currency":"CAD","min":22.5,"max":35}
         * promoter : {"id":"494","name":"PROMOTED BY VENUE","description":"PROMOTED BY VENUE / NTL / USA"}
         * venueId : KovZpa3yBe
         * influencerPick : 0
         * recommendByWengage : 1
         * eventId : 177ZvfG62YGeb1_
         * startDate : 2018-12-06T00:00:00.000Z
         * generateType : TM
         * categoryId : 5
         * subCategoryId : 50
         * createdAt : 2018-11-27T04:36:44.040Z
         * updatedAt : 2018-11-27T04:36:44.040Z
         * avgRating : 0
         * reviewCount : 0
         * isInterested : 1
         * isBookmarked : 0
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
        private int subCategoryId;
        private String createdAt;
        private String updatedAt;
        private String avgRating;
        private int reviewCount;
        private int isInterested;
        private int isBookmarked;
        private List<PresalesBean> presales;

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

        public int getIsInterested() {
            return isInterested;
        }

        public void setIsInterested(int isInterested) {
            this.isInterested = isInterested;
        }

        public int getIsBookmarked() {
            return isBookmarked;
        }

        public void setIsBookmarked(int isBookmarked) {
            this.isBookmarked = isBookmarked;
        }

        public List<PresalesBean> getPresales() {
            return presales;
        }

        public void setPresales(List<PresalesBean> presales) {
            this.presales = presales;
        }

        public static class PriceRangeBean {
            /**
             * currency : CAD
             * min : 22.5
             * max : 35
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
             * id : 494
             * name : PROMOTED BY VENUE
             * description : PROMOTED BY VENUE / NTL / USA
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
             * startDateTime : 2018-08-02T14:00:00Z
             * endDateTime : 2018-08-03T02:00:00Z
             * name : Collective Concerts Presale (19+)
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
    }
}
