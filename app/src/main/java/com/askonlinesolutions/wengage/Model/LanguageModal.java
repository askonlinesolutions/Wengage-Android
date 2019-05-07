package com.askonlinesolutions.wengage.Model;

import java.io.Serializable;
import java.util.List;

public class LanguageModal implements Serializable {
    /**
     * status : 1
     * message : Language List
     * languages : [{"languageId":1,"languageTitle":"Hindi"},{"languageId":2,"languageTitle":"English"},{"languageId":3,"languageTitle":"Chinese"},{"languageId":4,"languageTitle":"Spanish"},{"languageId":5,"languageTitle":"French"},{"languageId":6,"languageTitle":"Russian"},{"languageId":7,"languageTitle":"Japanese"}]
     */

    private int status;
    private String message;
    private List<LanguagesBean> languages;

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

    public List<LanguagesBean> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguagesBean> languages) {
        this.languages = languages;
    }

    public static class LanguagesBean  implements Serializable{
        /**
         * languageId : 1
         * languageTitle : Hindi
         */

        private int languageId;
        private String languageTitle;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        private boolean isSelected;

        public int getLanguageId() {
            return languageId;
        }

        public void setLanguageId(int languageId) {
            this.languageId = languageId;
        }

        public String getLanguageTitle() {
            return languageTitle;
        }

        public void setLanguageTitle(String languageTitle) {
            this.languageTitle = languageTitle;
        }
    }
}
