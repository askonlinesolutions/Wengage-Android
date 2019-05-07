package com.askonlinesolutions.wengage.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VenueListResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("pageNum")
    @Expose
    private String pageNum;
    @SerializedName("next")
    @Expose
    private Integer next;
    @SerializedName("venuesList")
    @Expose
    private List<VenuesList> venuesList = null;

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

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public List<VenuesList> getVenuesList() {
        return venuesList;
    }

    public void setVenuesList(List<VenuesList> venuesList) {
        this.venuesList = venuesList;
    }

    public class VenuesList {

        @SerializedName("reservable")
        @Expose
        private Integer reservable;
        @SerializedName("influencerPick")
        @Expose
        private Integer influencerPick;
        @SerializedName("recommendByWengage")
        @Expose
        private Integer recommendByWengage;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("shortDesc")
        @Expose
        private String shortDesc;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("justOpened")
        @Expose
        private String justOpened;
        @SerializedName("ourPick")
        @Expose
        private Integer ourPick;
        @SerializedName("postalCode")
        @Expose
        private String postalCode;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("territory")
        @Expose
        private String territory;
        @SerializedName("rating")
        @Expose
        private Integer rating;
        @SerializedName("venueType")
        @Expose
        private String venueType;
        @SerializedName("categoryId")
        @Expose
        private Integer categoryId;
        @SerializedName("categoryName")
        @Expose
        private String categoryName;
        @SerializedName("subCategoryId")
        @Expose
        private Integer subCategoryId;
        @SerializedName("subCategoryName")
        @Expose
        private String subCategoryName;
        @SerializedName("location0")
        @Expose
        private String location0;
        @SerializedName("location1")
        @Expose
        private String location1;
        @SerializedName("imageURL")
        @Expose
        private String imageURL;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("venueId")
        @Expose
        private Integer venueId;

        public Integer getReservable() {
            return reservable;
        }

        public void setReservable(Integer reservable) {
            this.reservable = reservable;
        }

        public Integer getInfluencerPick() {
            return influencerPick;
        }

        public void setInfluencerPick(Integer influencerPick) {
            this.influencerPick = influencerPick;
        }

        public Integer getRecommendByWengage() {
            return recommendByWengage;
        }

        public void setRecommendByWengage(Integer recommendByWengage) {
            this.recommendByWengage = recommendByWengage;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getJustOpened() {
            return justOpened;
        }

        public void setJustOpened(String justOpened) {
            this.justOpened = justOpened;
        }

        public Integer getOurPick() {
            return ourPick;
        }

        public void setOurPick(Integer ourPick) {
            this.ourPick = ourPick;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTerritory() {
            return territory;
        }

        public void setTerritory(String territory) {
            this.territory = territory;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public String getVenueType() {
            return venueType;
        }

        public void setVenueType(String venueType) {
            this.venueType = venueType;
        }

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

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
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

        public Integer getVenueId() {
            return venueId;
        }

        public void setVenueId(Integer venueId) {
            this.venueId = venueId;
        }

    }
}
