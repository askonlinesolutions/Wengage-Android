package com.askonlinesolutions.wengage.Model;

public class EventPreferenceModal {

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
