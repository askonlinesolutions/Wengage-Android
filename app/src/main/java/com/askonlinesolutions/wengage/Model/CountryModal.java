package com.askonlinesolutions.wengage.Model;

import java.util.List;

public class CountryModal {

    /**
     * status : 1
     * message : Country List
     * countries : [{"countryId":1,"countryName":"United States"},{"countryId":2,"countryName":"Canada"}]
     */

    private int status;
    private String message;
    private List<CountriesBean> countries;

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

    public List<CountriesBean> getCountries() {
        return countries;
    }

    public void setCountries(List<CountriesBean> countries) {
        this.countries = countries;
    }

    public static class CountriesBean {
        /**
         * countryId : 1
         * countryName : United States
         */

        private int countryId;
        private String countryName;

        public int getCountryId() {
            return countryId;
        }

        public void setCountryId(int countryId) {
            this.countryId = countryId;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }
    }
}
