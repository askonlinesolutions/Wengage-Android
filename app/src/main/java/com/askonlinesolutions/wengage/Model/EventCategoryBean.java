package com.askonlinesolutions.wengage.Model;

import java.util.List;

public  class EventCategoryBean {
    /**
     * categoryId : 1
     * categoryName : THE CITY
     * icon : https://s3.amazonaws.com/wengageapp/category/City.png
     * subCategory : [{"subCategoryId":5,"subCategoryName":"Special Events","icon":"https://s3.amazonaws.com/wengageapp/subcategory/SPECIAL-EVENTS.png","type":"event","optionalName":"","selected":1},{"subCategoryId":77,"subCategoryName":"Hotel Promotions","icon":"https://s3.amazonaws.com/wengageapp/subcategory/HOTEL-PROMOTIONS.png","type":"event","optionalName":"","selected":1}]
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

