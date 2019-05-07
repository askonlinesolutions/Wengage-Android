package com.askonlinesolutions.wengage.Model;

import java.util.List;

public class CityModal {

    /**
     * status : 1
     * message : City List
     * cities : [{"cityId":1,"cityName":"United States"},{"cityId":2,"cityName":"Canada"}]
     */

    private int status;
    private String message;
    private List<CitiesBean> cities;

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

    public List<CitiesBean> getCities() {
        return cities;
    }

    public void setCities(List<CitiesBean> cities) {
        this.cities = cities;
    }

    public static class CitiesBean {
        /**
         * cityId : 1
         * cityName : United States
         */

        private int cityId;
        private String cityName;

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }
    }
}
