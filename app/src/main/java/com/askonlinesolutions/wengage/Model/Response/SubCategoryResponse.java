

package com.askonlinesolutions.wengage.Model.Response;

import com.askonlinesolutions.wengage.Model.EventCategoryModal;
import com.askonlinesolutions.wengage.Model.MappingBean;
import com.askonlinesolutions.wengage.Model.VenueSubCategoryListBean;

import java.util.List;

public class SubCategoryResponse {

    /**
     * status : 1
     * message : Sub Category List
     * eventPreferences : [{"status":1,"subCategoryId":5,"subCategoryName":"Special Events","categoryId":1,"icon":"https://s3.amazonaws.com/wengageapp/subcategory/SPECIAL-EVENTS.png","createdAt":"2018-09-24T07:01:18.192Z","updatedAt":"2018-09-24T07:01:18.192Z","type":"event","optionalName":"","categoryName":"THE CITY"},{"status":1,"subCategoryId":77,"subCategoryName":"Hotel Promotions","categoryId":1,"icon":"https://s3.amazonaws.com/wengageapp/subcategory/HOTEL-PROMOTIONS.png","createdAt":"2018-09-24T07:01:18.192Z","updatedAt":"2018-09-24T07:01:18.192Z","type":"event","optionalName":"","categoryName":"THE CITY"}]
     * venuePreferences : [{"status":1,"subCategoryId":1,"subCategoryName":"In The Know","categoryId":1,"icon":"https://s3.amazonaws.com/wengageapp/subcategory/IN-THE-KNOW.png","createdAt":"2018-09-24T07:01:18.192Z","updatedAt":"2018-09-24T07:01:18.192Z","type":"venue","optionalName":"","categoryName":"THE CITY"},{"status":1,"subCategoryId":2,"subCategoryName":"Getting Around","categoryId":1,"icon":"https://s3.amazonaws.com/wengageapp/subcategory/GETTING-AROUND.png","createdAt":"2018-09-24T07:01:18.192Z","updatedAt":"2018-09-24T07:01:18.192Z","type":"venue","optionalName":"","categoryName":"THE CITY"},{"status":1,"subCategoryId":3,"subCategoryName":"Top Attractions","categoryId":1,"icon":"https://s3.amazonaws.com/wengageapp/subcategory/TOP-ATTRACTIONS.png","createdAt":"2018-09-24T07:01:18.192Z","updatedAt":"2018-09-24T07:01:18.192Z","type":"venue","optionalName":"","categoryName":"THE CITY"},{"status":1,"subCategoryId":4,"subCategoryName":"Places To Stay","categoryId":1,"icon":"https://s3.amazonaws.com/wengageapp/subcategory/PLACES-TO-STAY.png","createdAt":"2018-09-24T07:01:18.192Z","updatedAt":"2018-09-24T07:01:18.192Z","type":"venue","optionalName":"","categoryName":"THE CITY"}]
     * eventSubCategoryList : [{"status":1,"subCategoryId":5,"subCategoryName":"Special Events","categoryId":1,"icon":"https://s3.amazonaws.com/wengageapp/subcategory/SPECIAL-EVENTS.png","createdAt":"2018-09-24T07:01:18.192Z","updatedAt":"2018-09-24T07:01:18.192Z","type":"event","optionalName":"","categoryName":"THE CITY"},{"status":1,"subCategoryId":77,"subCategoryName":"Hotel Promotions","categoryId":1,"icon":"https://s3.amazonaws.com/wengageapp/subcategory/HOTEL-PROMOTIONS.png","createdAt":"2018-09-24T07:01:18.192Z","updatedAt":"2018-09-24T07:01:18.192Z","type":"event","optionalName":"","categoryName":"THE CITY"}]
     * venueSubCategoryList : [{"status":1,"subCategoryId":1,"subCategoryName":"In The Know","categoryId":1,"icon":"https://s3.amazonaws.com/wengageapp/subcategory/IN-THE-KNOW.png","createdAt":"2018-09-24T07:01:18.192Z","updatedAt":"2018-09-24T07:01:18.192Z","type":"venue","optionalName":"","categoryName":"THE CITY"},{"status":1,"subCategoryId":2,"subCategoryName":"Getting Around","categoryId":1,"icon":"https://s3.amazonaws.com/wengageapp/subcategory/GETTING-AROUND.png","createdAt":"2018-09-24T07:01:18.192Z","updatedAt":"2018-09-24T07:01:18.192Z","type":"venue","optionalName":"","categoryName":"THE CITY"},{"status":1,"subCategoryId":3,"subCategoryName":"Top Attractions","categoryId":1,"icon":"https://s3.amazonaws.com/wengageapp/subcategory/TOP-ATTRACTIONS.png","createdAt":"2018-09-24T07:01:18.192Z","updatedAt":"2018-09-24T07:01:18.192Z","type":"venue","optionalName":"","categoryName":"THE CITY"},{"status":1,"subCategoryId":4,"subCategoryName":"Places To Stay","categoryId":1,"icon":"https://s3.amazonaws.com/wengageapp/subcategory/PLACES-TO-STAY.png","createdAt":"2018-09-24T07:01:18.192Z","updatedAt":"2018-09-24T07:01:18.192Z","type":"venue","optionalName":"","categoryName":"THE CITY"},{"status":1,"subCategoryId":78,"subCategoryName":"More","categoryId":1,"icon":"https://s3.amazonaws.com/wengageapp/subcategory/MORE.png","createdAt":"2018-09-24T07:01:18.192Z","updatedAt":"2018-09-24T07:01:18.192Z","type":"venue","optionalName":"","categoryName":"THE CITY"}]
     * mapping: [{"eventSubCategoryId": 49,"venueSubCategoryId": 49}]
     */

    private int status;
    private String message;
    private List<EventPreferencesBean> eventPreferences;
    private List<VenuePreferencesBean> venuePreferences;
    private List<EventCategoryModal> eventSubCategoryList;
    private List<VenueSubCategoryListBean> venueSubCategoryList;

    private List<MappingBean> mapping;
    private List<PreferencesBean> preferences;

    public List<MappingBean> getMapping() {
        return mapping;
    }

    public void setMapping(List<MappingBean> mapping) {
        this.mapping = mapping;
    }


    public List<PreferencesBean> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<PreferencesBean> preferences) {
        this.preferences = preferences;
    }

    public List<SubCategoryListBean> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<SubCategoryListBean> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    private List<SubCategoryListBean> subCategoryList;

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

    public List<EventCategoryModal> getEventSubCategoryList() {
        return eventSubCategoryList;
    }

    public void setEventSubCategoryList(List<EventCategoryModal> eventSubCategoryList) {
        this.eventSubCategoryList = eventSubCategoryList;
    }

    public List<VenueSubCategoryListBean> getVenueSubCategoryList() {
        return venueSubCategoryList;
    }

    public void setVenueSubCategoryList(List<VenueSubCategoryListBean> venueSubCategoryList) {
        this.venueSubCategoryList = venueSubCategoryList;
    }

    public static class EventPreferencesBean {
        /**
         * status : 1
         * subCategoryId : 5
         * subCategoryName : Special Events
         * categoryId : 1
         * icon : https://s3.amazonaws.com/wengageapp/subcategory/SPECIAL-EVENTS.png
         * createdAt : 2018-09-24T07:01:18.192Z
         * updatedAt : 2018-09-24T07:01:18.192Z
         * type : event
         * optionalName :
         * categoryName : THE CITY
         */

        private int status;
        private int subCategoryId;
        private String subCategoryName;
        private int categoryId;
        private String icon;
        private String createdAt;
        private String updatedAt;
        private String type;
        private String optionalName;
        private String categoryName;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

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

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
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

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }
    }

    public static class VenuePreferencesBean {
        /**
         * status : 1
         * subCategoryId : 1
         * subCategoryName : In The Know
         * categoryId : 1
         * icon : https://s3.amazonaws.com/wengageapp/subcategory/IN-THE-KNOW.png
         * createdAt : 2018-09-24T07:01:18.192Z
         * updatedAt : 2018-09-24T07:01:18.192Z
         * type : venue
         * optionalName :
         * categoryName : THE CITY
         */

        private int status;
        private int subCategoryId;
        private String subCategoryName;
        private int categoryId;
        private String icon;
        private String createdAt;
        private String updatedAt;
        private String type;
        private String optionalName;
        private String categoryName;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

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

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
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

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }
    }


    public static class PreferencesBean {
        /**
         * status : 1
         * subCategoryId : 1
         * subCategoryName : In The Know
         * categoryId : 1
         * icon : https://s3.amazonaws.com/wengageapp/subcategory/IN-THE-KNOW.png
         * createdAt : 2018-09-24T07:01:18.192Z
         * updatedAt : 2018-09-24T07:01:18.192Z
         * categoryName : THE CITY
         */

        private int status;
        private int subCategoryId;
        private String subCategoryName;
        private int categoryId;
        private String icon;
        private String createdAt;
        private String updatedAt;
        private String categoryName;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

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

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
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

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }
    }


    public static class SubCategoryListBean {
        /**
         * categoryId : 1
         * categoryName : THE CITY
         * subCategoryId : 5
         * subCategoryName : Special Events
         * icon : https://s3.amazonaws.com/wengageapp/subcategory/SPECIAL-EVENTS.png
         * status : 1
         */

        private int categoryId;
        private String categoryName;
        private int subCategoryId;
        private String subCategoryName;
        private String icon;
        private int status;

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
