package com.askonlinesolutions.wengage.Fragment.Main.event;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rakhi on 12/11/2018.
 */
public class EventListResponse implements Serializable{


    private int status;
    private String message;
    private int totalCount;
    private String pageNum;
    private int next;
    private int totalPages;
    private List<EventsListBean> eventsList;

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

    public List<EventsListBean> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<EventsListBean> eventsList) {
        this.eventsList = eventsList;
    }

    public static class EventsListBean {

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
        private String distance;
        private String avgRating;
        private String reviewCount;
        private int isBookmarked;
        private int isInterested;
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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getAvgRating() {
            return avgRating;
        }

        public void setAvgRating(String avgRating) {
            this.avgRating = avgRating;
        }

        public String getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(String reviewCount) {
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
             * id : 653
             * name : LIVE NATION MUSIC
             * description : LIVE NATION MUSIC / NTL / USA
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
}
