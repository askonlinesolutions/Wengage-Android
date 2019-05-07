package com.askonlinesolutions.wengage.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VenueCategoryBean {
    /**
     * categoryId : 1
     * categoryName : THE CITY
     * icon : https://s3.amazonaws.com/wengageapp/category/City.png
     * subCategory : [{"subCategoryId":1,"subCategoryName":"In The Know","icon":"https://s3.amazonaws.com/wengageapp/subcategory/IN-THE-KNOW.png","type":"venue","optionalName":"","selected":1},{"subCategoryId":2,"subCategoryName":"Getting Around","icon":"https://s3.amazonaws.com/wengageapp/subcategory/GETTING-AROUND.png","type":"venue","optionalName":"","selected":1},{"subCategoryId":3,"subCategoryName":"Top Attractions","icon":"https://s3.amazonaws.com/wengageapp/subcategory/TOP-ATTRACTIONS.png","type":"venue","optionalName":"","selected":1},{"subCategoryId":4,"subCategoryName":"Places To Stay","icon":"https://s3.amazonaws.com/wengageapp/subcategory/PLACES-TO-STAY.png","type":"venue","optionalName":"","selected":1},{"subCategoryId":78,"subCategoryName":"More","icon":"https://s3.amazonaws.com/wengageapp/subcategory/MORE.png","type":"venue","optionalName":"","selected":0}]
     */

    @SerializedName("categoryId")
    @Expose
    private int categoryId;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("icon")
    @Expose
    private String icon;
    private List<SubCategoryBeanX> subCategory;

    public VenueCategoryBean(int id, String name, String image) {
        this.categoryName = categoryName;
        this.icon = icon;
        this.categoryId = categoryId;
    }

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

