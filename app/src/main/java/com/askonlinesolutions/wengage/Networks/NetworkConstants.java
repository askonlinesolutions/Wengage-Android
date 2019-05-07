package com.askonlinesolutions.wengage.Networks;

public interface NetworkConstants {
    /***************************------Base url for rest apis------***************************/
    String BASE_URL = "http://107.21.193.184/";
    String USER_PROFILE = BASE_URL + "user/profile";
    String GET_COUNTRIES = BASE_URL + "getCountries";
    String GET_CITIES = BASE_URL + "getCities?countryId=";
    String GET_TAGS = BASE_URL + "tags/";
    String GET_LANGUAGES = BASE_URL + "getLanguages";
    String PUT_UPDATE_VENUE = BASE_URL + "user/updateProfile/";
    String POST_INTERESTED = BASE_URL + "venue/interested";
    String PUT_UPDATE_CURRENT_CITY = BASE_URL + "user/updateCurrentCity/";
    String PUT_UPDATE_RADIUS = BASE_URL + "user/updateRadius/";
    String MY_CHATS = BASE_URL + "myChats";
    String ALL_INVITATION = BASE_URL + "getAllInvitations";
    String CHAT_ROOMS = BASE_URL + "chatrooms";
    /***************************-------request constants for each url-***********************/

    int REQUEST_TYPE_USER_PROFILE = 1;
    int REQUEST_TYPE_MY_CHATS = 2;
    int REQUEST_TYPE_ALL_INVITATION = 3;
    int REQUEST_TYPE_CHAT_ROOMS = 4;


}
