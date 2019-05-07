package com.askonlinesolutions.wengage.Model;

import java.util.List;

public class TagModal {
    /**
     * status : 1
     * message : Tags List
     * tags : [{"tagId":1,"tagName":"Our Picks","categoryId":3},{"tagId":2,"tagName":"Your Table","categoryId":3},{"tagId":3,"tagName":"All","categoryId":3},{"tagId":4,"tagName":"Casual","categoryId":3},{"tagId":5,"tagName":"Patio","categoryId":3},{"tagId":6,"tagName":"Live Music","categoryId":3},{"tagId":7,"tagName":"Gluten Free","categoryId":3},{"tagId":8,"tagName":"Halal","categoryId":3},{"tagId":9,"tagName":"Kosher","categoryId":3},{"tagId":10,"tagName":"Raw","categoryId":3},{"tagId":11,"tagName":"Vegan","categoryId":3},{"tagId":12,"tagName":"Vegetarian","categoryId":3}]
     */

    private int status;
    private String message;
    private List<TagsBean> tags;

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

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class TagsBean {
        /**
         * tagId : 1
         * tagName : Our Picks
         * categoryId : 3
         */

        private int tagId;
        private String tagName;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        private boolean isSelected;
        private int categoryId;

        public int getTagId() {
            return tagId;
        }

        public void setTagId(int tagId) {
            this.tagId = tagId;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }
    }
}
