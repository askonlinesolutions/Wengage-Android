package com.askonlinesolutions.wengage.Fragment.Main.profile;

import com.askonlinesolutions.wengage.Model.EventCategoryBean;
import com.askonlinesolutions.wengage.Model.UserProfileDetailModal;
import com.askonlinesolutions.wengage.Model.VenueCategoryBean;

import java.util.List;

/**
 * Created by Rakhi on 12/5/2018.
 */
public class UserProfile {


    private int status;
    private String message;
    private UserDataBean userData;

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

    public UserDataBean getUserData() {
        return userData;
    }

    public void setUserData(UserDataBean userData) {
        this.userData = userData;
    }

    public static class UserDataBean {


        /**
         * influencer : 1
         * resetPasswordKey : 0
         * isOnline : 1
         * <p>
         * isEmailVerified : 1
         * email : rohit@askonlinesolutions.com
         * phone : 9555748964
         * countryCode : +91
         * fullName : Rohit Verma
         * userName : Rohit123
         * verificationCode :
         * verificationTime : 2018-11-27T12:16:21.000Z
         * photoURL : https://s3.amazonaws.com/wengageapp/profile/1543321144489_442860.jpg
         * createdAt : 2018-11-27T12:16:21.006Z
         * updatedAt : 2018-12-05T12:32:43.376Z
         * googlePlaceId : 2018-12-05T12:32:43.376Z
         * userId : 165
         * gender : Female
         * knownByName : Rohit Verma
         * DOB : 1992-01-16
         * city : Toronto
         * incomeLevel : $200 +
         * userToken : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjE2NSwiZW1haWwiOiJyb2hpdEBhc2tvbmxpbmVzb2x1dGlvbnMuY29tIiwiaWF0IjoxNTQzOTg3OTI3LCJleHAiOjE1NDM5ODk3Mjd9.NIyGHCggy_3nxLW9d69jJ12-WqbvB99uOn1uV02UW0s
         * tokenTime : 2018-12-05T05:32:07.000Z
         * tokenStatus : 1
         * favoutiteCity :
         * shortDesc :
         * favoutiteRestaurant :
         * work :
         * hometown :
         * description :
         * socialMedia :
         * profileComplete : 79
         * preferences : [{"subCategoryId":1,"subCategoryName":"In The Know","icon":"https://s3.amazonaws.com/wengageapp/subcategory/IN-THE-KNOW.png"},{"subCategoryId":2,"subCategoryName":"Getting Around","icon":"https://s3.amazonaws.com/wengageapp/subcategory/GETTING-AROUND.png"},{"subCategoryId":3,"subCategoryName":"Top Attractions","icon":"https://s3.amazonaws.com/wengageapp/subcategory/TOP-ATTRACTIONS.png"},{"subCategoryId":4,"subCategoryName":"Places To Stay","icon":"https://s3.amazonaws.com/wengageapp/subcategory/PLACES-TO-STAY.png"},{"subCategoryId":5,"subCategoryName":"More","icon":"https://s3.amazonaws.com/wengageapp/subcategory/MORE.png"},{"subCategoryId":6,"subCategoryName":"Tea","icon":"https://s3.amazonaws.com/wengageapp/subcategory/TEA.png"},{"subCategoryId":7,"subCategoryName":"Bakeries & Patisseries","icon":"https://s3.amazonaws.com/wengageapp/subcategory/BAKERIES_LATISSERIES.png"},{"subCategoryId":8,"subCategoryName":"Cafes & Coffee Shops","icon":"https://s3.amazonaws.com/wengageapp/subcategory/CAFES_COFFEE-SHOPS.png"},{"subCategoryId":9,"subCategoryName":"Dessert","icon":"https://s3.amazonaws.com/wengageapp/subcategory/DESSERT.png"},{"subCategoryId":11,"subCategoryName":"Just Opened","icon":"https://s3.amazonaws.com/wengageapp/subcategory/JUST+OPENED.png"},{"subCategoryId":12,"subCategoryName":"Vegetarian","icon":"https://s3.amazonaws.com/wengageapp/subcategory/VEGETARIAN.png"},{"subCategoryId":13,"subCategoryName":"Seafood","icon":"https://s3.amazonaws.com/wengageapp/subcategory/SEAFOOD.png"},{"subCategoryId":14,"subCategoryName":"Steakhouse","icon":"https://s3.amazonaws.com/wengageapp/subcategory/STEAKHOUSE.png"},{"subCategoryId":15,"subCategoryName":"Juice & Smoothies","icon":"https://s3.amazonaws.com/wengageapp/subcategory/JUICE_LSMOOTHIES.png"},{"subCategoryId":16,"subCategoryName":"Food Halls & Markets","icon":"https://s3.amazonaws.com/wengageapp/subcategory/FOOD_HALLS_MARKETS.png"},{"subCategoryId":17,"subCategoryName":"African","icon":"https://s3.amazonaws.com/wengageapp/category/City.png"},{"subCategoryId":18,"subCategoryName":"Argentinian","icon":"https://s3.amazonaws.com/wengageapp/category/City.png"},{"subCategoryId":19,"subCategoryName":"Brazilian","icon":"https://s3.amazonaws.com/wengageapp/category/City.png"},{"subCategoryId":44,"subCategoryName":"Bars","icon":"https://s3.amazonaws.com/wengageapp/subcategory/BARS.png"},{"subCategoryId":45,"subCategoryName":"Lounges","icon":"https://s3.amazonaws.com/wengageapp/subcategory/LOUNGES.png"},{"subCategoryId":46,"subCategoryName":"Pubs","icon":"https://s3.amazonaws.com/wengageapp/subcategory/PUBS.png"},{"subCategoryId":47,"subCategoryName":"Wine Bars","icon":"https://s3.amazonaws.com/wengageapp/subcategory/WINE-BARS.png"},{"subCategoryId":49,"subCategoryName":"Concert Halls","icon":"https://s3.amazonaws.com/wengageapp/subcategory/CONCERT-HALLS.png"},{"subCategoryId":50,"subCategoryName":"Local Clubs","icon":"https://s3.amazonaws.com/wengageapp/subcategory/LOCAL-CLUBS.png"},{"subCategoryId":51,"subCategoryName":"Jazz & Piano Bars","icon":"https://s3.amazonaws.com/wengageapp/subcategory/JAZZ_PIANO-BARS.png"},{"subCategoryId":52,"subCategoryName":"Comedy Clubs","icon":"https://s3.amazonaws.com/wengageapp/subcategory/COMEDY-CLUBS.png"},{"subCategoryId":53,"subCategoryName":"Nightclubs","icon":"https://s3.amazonaws.com/wengageapp/subcategory/NIGHTCLUBS.png"},{"subCategoryId":55,"subCategoryName":"Art Galleries","icon":"https://s3.amazonaws.com/wengageapp/subcategory/ART-GALLERIES.png"},{"subCategoryId":56,"subCategoryName":"Dance","icon":"https://s3.amazonaws.com/wengageapp/subcategory/DANCE.png"},{"subCategoryId":57,"subCategoryName":"Museums","icon":"https://s3.amazonaws.com/wengageapp/subcategory/MUSEUMS.png"},{"subCategoryId":58,"subCategoryName":"Opera & Symphony","icon":"https://s3.amazonaws.com/wengageapp/subcategory/OPERA-&-SYMPHONY.png"},{"subCategoryId":76,"subCategoryName":"More","icon":"https://s3.amazonaws.com/wengageapp/subcategory/MORE.png"},{"subCategoryId":61,"subCategoryName":"Barre & Dance","icon":"https://s3.amazonaws.com/wengageapp/subcategory/BARRE-&-DANCE.png"},{"subCategoryId":62,"subCategoryName":"Cycling","icon":"https://s3.amazonaws.com/wengageapp/subcategory/CYCLING.png"},{"subCategoryId":63,"subCategoryName":"Fitness Clubs & Gyms","icon":"https://s3.amazonaws.com/wengageapp/subcategory/FITNESS-CLUBS.png"},{"subCategoryId":64,"subCategoryName":"Golf","icon":"https://s3.amazonaws.com/wengageapp/subcategory/GOLF.png"},{"subCategoryId":65,"subCategoryName":"Pilates & Yoga","icon":"https://s3.amazonaws.com/wengageapp/subcategory/PILATES_YOGA.png"},{"subCategoryId":66,"subCategoryName":"Racquet Clubs","icon":"https://s3.amazonaws.com/wengageapp/subcategory/RACQUET-CLUBS.png"},{"subCategoryId":67,"subCategoryName":"Running & Walking","icon":"https://s3.amazonaws.com/wengageapp/subcategory/RUNNING_WALKING.png"},{"subCategoryId":68,"subCategoryName":"Sailing & Rowing","icon":"https://s3.amazonaws.com/wengageapp/subcategory/SAILING_ROLLING.png"},{"subCategoryId":72,"subCategoryName":"Chambers of Commerce","icon":"https://s3.amazonaws.com/wengageapp/subcategory/CHAMBERS-OF-COMMERCE.png"},{"subCategoryId":73,"subCategoryName":"Conference Centre","icon":"https://s3.amazonaws.com/wengageapp/subcategory/CONFERENCE_CENTRES.png"},{"subCategoryId":74,"subCategoryName":"Professional Association","icon":"https://s3.amazonaws.com/wengageapp/subcategory/PROFESSIONAL_ASSOCIATIONS.png"}]
         * eventCategory : [{
         * "categoryId": 1,
         * "categoryName": "THE CITY",
         * "icon": "https://s3.amazonaws.com/wengageapp/category/City.png",
         * "subCategory": [
         * {
         * "subCategoryId": 5,
         * "subCategoryName": "Special Events",
         * "icon": "https://s3.amazonaws.com/wengageapp/subcategory/SPECIAL-EVENTS.png",
         * "type": "event",
         * "optionalName": "",
         * "selected": 1
         * },
         * {
         * "subCategoryId": 77,
         * "subCategoryName": "Hotel Promotions",
         * "icon": "https://s3.amazonaws.com/wengageapp/subcategory/HOTEL-PROMOTIONS.png",
         * "type": "event",
         * "optionalName": "",
         * "selected": 0
         * },
         * {
         * "subCategoryId": 78,
         * "subCategoryName": "More",
         * "icon": "https://s3.amazonaws.com/wengageapp/subcategory/MORE.png",
         * "type": "common",
         * "optionalName": "",
         * "selected": 0
         * }
         * ]
         * }]
         * venueCategory : [{"subCategoryId":1,"subCategoryName":"In The Know","icon":"https://s3.amazonaws.com/wengageapp/subcategory/IN-THE-KNOW.png"},{"subCategoryId":2,"subCategoryName":"Getting Around","icon":"https://s3.amazonaws.com/wengageapp/subcategory/GETTING-AROUND.png"},{"subCategoryId":3,"subCategoryName":"Top Attractions","icon":"https://s3.amazonaws.com/wengageapp/subcategory/TOP-ATTRACTIONS.png"},{"subCategoryId":4,"subCategoryName":"Places To Stay","icon":"https://s3.amazonaws.com/wengageapp/subcategory/PLACES-TO-STAY.png"},{"subCategoryId":5,"subCategoryName":"More","icon":"https://s3.amazonaws.com/wengageapp/subcategory/MORE.png"},{"subCategoryId":6,"subCategoryName":"Tea","icon":"https://s3.amazonaws.com/wengageapp/subcategory/TEA.png"},{"subCategoryId":7,"subCategoryName":"Bakeries & Patisseries","icon":"https://s3.amazonaws.com/wengageapp/subcategory/BAKERIES_LATISSERIES.png"},{"subCategoryId":8,"subCategoryName":"Cafes & Coffee Shops","icon":"https://s3.amazonaws.com/wengageapp/subcategory/CAFES_COFFEE-SHOPS.png"},{"subCategoryId":9,"subCategoryName":"Dessert","icon":"https://s3.amazonaws.com/wengageapp/subcategory/DESSERT.png"},{"subCategoryId":11,"subCategoryName":"Just Opened","icon":"https://s3.amazonaws.com/wengageapp/subcategory/JUST+OPENED.png"},{"subCategoryId":12,"subCategoryName":"Vegetarian","icon":"https://s3.amazonaws.com/wengageapp/subcategory/VEGETARIAN.png"},{"subCategoryId":13,"subCategoryName":"Seafood","icon":"https://s3.amazonaws.com/wengageapp/subcategory/SEAFOOD.png"},{"subCategoryId":14,"subCategoryName":"Steakhouse","icon":"https://s3.amazonaws.com/wengageapp/subcategory/STEAKHOUSE.png"},{"subCategoryId":15,"subCategoryName":"Juice & Smoothies","icon":"https://s3.amazonaws.com/wengageapp/subcategory/JUICE_LSMOOTHIES.png"},{"subCategoryId":16,"subCategoryName":"Food Halls & Markets","icon":"https://s3.amazonaws.com/wengageapp/subcategory/FOOD_HALLS_MARKETS.png"},{"subCategoryId":17,"subCategoryName":"African","icon":"https://s3.amazonaws.com/wengageapp/category/City.png"},{"subCategoryId":18,"subCategoryName":"Argentinian","icon":"https://s3.amazonaws.com/wengageapp/category/City.png"},{"subCategoryId":19,"subCategoryName":"Brazilian","icon":"https://s3.amazonaws.com/wengageapp/category/City.png"},{"subCategoryId":44,"subCategoryName":"Bars","icon":"https://s3.amazonaws.com/wengageapp/subcategory/BARS.png"},{"subCategoryId":45,"subCategoryName":"Lounges","icon":"https://s3.amazonaws.com/wengageapp/subcategory/LOUNGES.png"},{"subCategoryId":46,"subCategoryName":"Pubs","icon":"https://s3.amazonaws.com/wengageapp/subcategory/PUBS.png"},{"subCategoryId":47,"subCategoryName":"Wine Bars","icon":"https://s3.amazonaws.com/wengageapp/subcategory/WINE-BARS.png"},{"subCategoryId":49,"subCategoryName":"Concert Halls","icon":"https://s3.amazonaws.com/wengageapp/subcategory/CONCERT-HALLS.png"},{"subCategoryId":50,"subCategoryName":"Local Clubs","icon":"https://s3.amazonaws.com/wengageapp/subcategory/LOCAL-CLUBS.png"},{"subCategoryId":51,"subCategoryName":"Jazz & Piano Bars","icon":"https://s3.amazonaws.com/wengageapp/subcategory/JAZZ_PIANO-BARS.png"},{"subCategoryId":52,"subCategoryName":"Comedy Clubs","icon":"https://s3.amazonaws.com/wengageapp/subcategory/COMEDY-CLUBS.png"},{"subCategoryId":53,"subCategoryName":"Nightclubs","icon":"https://s3.amazonaws.com/wengageapp/subcategory/NIGHTCLUBS.png"},{"subCategoryId":55,"subCategoryName":"Art Galleries","icon":"https://s3.amazonaws.com/wengageapp/subcategory/ART-GALLERIES.png"},{"subCategoryId":56,"subCategoryName":"Dance","icon":"https://s3.amazonaws.com/wengageapp/subcategory/DANCE.png"},{"subCategoryId":57,"subCategoryName":"Museums","icon":"https://s3.amazonaws.com/wengageapp/subcategory/MUSEUMS.png"},{"subCategoryId":58,"subCategoryName":"Opera & Symphony","icon":"https://s3.amazonaws.com/wengageapp/subcategory/OPERA-&-SYMPHONY.png"},{"subCategoryId":76,"subCategoryName":"More","icon":"https://s3.amazonaws.com/wengageapp/subcategory/MORE.png"},{"subCategoryId":61,"subCategoryName":"Barre & Dance","icon":"https://s3.amazonaws.com/wengageapp/subcategory/BARRE-&-DANCE.png"},{"subCategoryId":62,"subCategoryName":"Cycling","icon":"https://s3.amazonaws.com/wengageapp/subcategory/CYCLING.png"},{"subCategoryId":63,"subCategoryName":"Fitness Clubs & Gyms","icon":"https://s3.amazonaws.com/wengageapp/subcategory/FITNESS-CLUBS.png"},{"subCategoryId":64,"subCategoryName":"Golf","icon":"https://s3.amazonaws.com/wengageapp/subcategory/GOLF.png"},{"subCategoryId":65,"subCategoryName":"Pilates & Yoga","icon":"https://s3.amazonaws.com/wengageapp/subcategory/PILATES_YOGA.png"},{"subCategoryId":66,"subCategoryName":"Racquet Clubs","icon":"https://s3.amazonaws.com/wengageapp/subcategory/RACQUET-CLUBS.png"},{"subCategoryId":67,"subCategoryName":"Running & Walking","icon":"https://s3.amazonaws.com/wengageapp/subcategory/RUNNING_WALKING.png"},{"subCategoryId":68,"subCategoryName":"Sailing & Rowing","icon":"https://s3.amazonaws.com/wengageapp/subcategory/SAILING_ROLLING.png"},{"subCategoryId":72,"subCategoryName":"Chambers of Commerce","icon":"https://s3.amazonaws.com/wengageapp/subcategory/CHAMBERS-OF-COMMERCE.png"},{"subCategoryId":73,"subCategoryName":"Conference Centre","icon":"https://s3.amazonaws.com/wengageapp/subcategory/CONFERENCE_CENTRES.png"},{"subCategoryId":74,"subCategoryName":"Professional Association","icon":"https://s3.amazonaws.com/wengageapp/subcategory/PROFESSIONAL_ASSOCIATIONS.png"}]
         * venues : [{"venueId":"KovZpZAEkkIA","name":"Budweiser Stage","imageURL":"https://s1.ticketm.net/dbimages/18921v.png"},{"venueId":"KovZpZAdtAeA","name":"The Garrison","imageURL":"https://s3.amazonaws.com/wengageapp/Image+not+found.png"},{"venueId":"KovZpZAdtAeA","name":"The Garrison","imageURL":"https://s3.amazonaws.com/wengageapp/Image+not+found.png"},{"venueId":"KovZpZAknFdA","name":"Brassaii","imageURL":"https://s3.amazonaws.com/wengageapp/Image+not+found.png"},{"venueId":"KovZpZAdtAeA","name":"The Garrison","imageURL":"https://s3.amazonaws.com/wengageapp/Image+not+found.png"},{"venueId":"KovZpZAdtAeA","name":"The Garrison","imageURL":"https://s3.amazonaws.com/wengageapp/Image+not+found.png"},{"venueId":"KovZpZAdtAeA","name":"The Garrison","imageURL":"https://s3.amazonaws.com/wengageapp/Image+not+found.png"},{"venueId":"KovZpZAFlFAA","name":"Velvet Underground","imageURL":"https://s1.ticketm.net/dbimages/5752v.gif"},{"venueId":"KovZpZAJt7FA","name":"Coca-Cola Coliseum","imageURL":"https://s3.amazonaws.com/wengageapp/Image+not+found.png"}]
         * events : [{"eventId":"1718vfG6CGqWYWI","name":"The Marcus King Band with Special Guest Ida Mae","imageURL":"https://s1.ticketm.net/dam/a/66c/d0b5bdc4-0b7b-4a35-acea-124e0b60966c_735621_RECOMENDATION_16_9.jpg"},{"eventId":"1Ae0Z48GkI7E7w7","name":"Oliver Francis: The Overdrive Tour","imageURL":"https://s1.ticketm.net/dam/c/80b/f3cd8d24-c3ae-4fa0-b4bc-1ba99f9b380b_106091_TABLET_LANDSCAPE_16_9.jpg"},{"eventId":"16e0Z4K7EG7iJZP","name":"Playboi Carti","imageURL":"https://s1.ticketm.net/dam/a/9ec/5c7626ea-608a-4f9b-8a98-7902c787c9ec_845831_CUSTOM.jpg"},{"eventId":"1AvZZf4GkiegjlA","name":"2019 Season Tickets","imageURL":"https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg"},{"eventId":"1k18v4j-GACKP8F","name":"The Yule Ball","imageURL":"https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg"},{"eventId":"1AvZZ43GkMeepm5","name":"Disney On Ice celebrates 100 Years of Magic","imageURL":"https://s1.ticketm.net/dam/a/37e/02c52b2e-3661-4caa-81d7-1ddba74ae37e_678831_TABLET_LANDSCAPE_16_9.jpg"},{"eventId":"1k18v4QeGAG9xT0","name":"Spice Route Nye 2019","imageURL":"https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg"},{"eventId":"1AvZZf4GkiegjlA","name":"2019 Season Tickets","imageURL":"https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg"},{"eventId":"1AvZZf4GkiegjlA","name":"2019 Season Tickets","imageURL":"https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg"},{"eventId":"1AvZZf4GkiegjlA","name":"2019 Season Tickets","imageURL":"https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg"},{"eventId":"1AvZZf4GkiegjlA","name":"2019 Season Tickets","imageURL":"https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg"},{"eventId":"16tZA4S7dZACk8vu","name":"Countdown New Year's Eve At Liberty Grand","imageURL":"https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg"},{"eventId":"1A8ZAf4Gkd__gKh","name":"Aminé","imageURL":"https://s1.ticketm.net/dam/a/812/49dd5108-6064-4ee2-b940-7b4fbc9d9812_865411_RECOMENDATION_16_9.jpg"},{"eventId":"177ZvfG62ksXgsx","name":"Toronto Maple Leafs vs. Boston Bruins","imageURL":"https://s1.ticketm.net/dam/a/4b8/3a97529b-7b1a-4409-a080-7aec61ecd4b8_121711_EVENT_DETAIL_PAGE_16_9.jpg"}]
         * favourites : [{"venueId":"KovZpZAEkkIA","name":"Budweiser Stage","imageURL":"https://s1.ticketm.net/dbimages/18921v.png","type":"VENUE"},{"venueId":"KovZpZAknFdA","name":"Brassaii","imageURL":"https://s3.amazonaws.com/wengageapp/Image+not+found.png","type":"VENUE"},{"venueId":"KovZpZAFlFAA","name":"Velvet Underground","imageURL":"https://s1.ticketm.net/dbimages/5752v.gif","type":"VENUE"},{"venueId":"KovZpZAJt7FA","name":"Coca-Cola Coliseum","imageURL":"https://s3.amazonaws.com/wengageapp/Image+not+found.png","type":"VENUE"},{"eventId":"1Ae0Z4oGkV-Gx8o","name":"Didirri with Myles Castello","imageURL":"https://s1.ticketm.net/dam/c/fbc/b293c0ad-c904-4215-bc59-8d7f2414dfbc_106141_CUSTOM.jpg","type":"EVENT"},{"eventId":"177ZvfG62ksXgsx","name":"Toronto Maple Leafs vs. Boston Bruins","imageURL":"https://s1.ticketm.net/dam/a/4b8/3a97529b-7b1a-4409-a080-7aec61ecd4b8_121711_EVENT_DETAIL_PAGE_16_9.jpg","type":"EVENT"},{"eventId":"1k18v4j-GACKP8F","name":"The Yule Ball","imageURL":"https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg","type":"EVENT"},{"eventId":"177ZvfG62kwZ4sm","name":"Toronto Maple Leafs vs. Detroit Red Wings","imageURL":"https://s1.ticketm.net/dam/a/4b8/3a97529b-7b1a-4409-a080-7aec61ecd4b8_121711_EVENT_DETAIL_PAGE_16_9.jpg","type":"EVENT"},{"eventId":"15e0Z4oP87Ajk","name":"Krewe of Oshun and Cleopatra","imageURL":"https://s1.ticketm.net/dam/c/368/b70b1d5a-ce61-4ca9-a3c7-3381694ae368_105351_TABLET_LANDSCAPE_16_9.jpg","type":"EVENT"},{"eventId":"1AvZZf4GkiegjlA","name":"2019 Season Tickets","imageURL":"https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg","type":"EVENT"},{"eventId":"1AvZZf4GkiegjlA","name":"2019 Season Tickets","imageURL":"https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg","type":"EVENT"},{"eventId":"1AvZZf4GkiegjlA","name":"2019 Season Tickets","imageURL":"https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg","type":"EVENT"},{"eventId":"1AvZZf4GkiegjlA","name":"2019 Season Tickets","imageURL":"https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg","type":"EVENT"},{"eventId":"1k18v4QeGAG9xT0","name":"Spice Route Nye 2019","imageURL":"https://s1.ticketm.net/dam/c/c09/cbee274e-eb99-4431-bfb0-21dee7684c09_105321_RETINA_PORTRAIT_16_9.jpg","type":"EVENT"},{"eventId":"1A8ZAf4Gkd__gKh","name":"Aminé","imageURL":"https://s1.ticketm.net/dam/a/812/49dd5108-6064-4ee2-b940-7b4fbc9d9812_865411_RECOMENDATION_16_9.jpg","type":"EVENT"},{"eventId":"1A8ZAf6GkdPP_1m","name":"Toronto Wolfpack 2019 Season Package","imageURL":"https://s1.ticketm.net/dam/a/ed8/a9f522b6-191d-4cd9-a1f6-871f3d95ded8_607631_TABLET_LANDSCAPE_3_2.jpg","type":"EVENT"},{"eventId":"1AvZZ4kGkl57Nz6","name":"Elton John: Farewell Yellow Brick Road","imageURL":"https://s1.ticketm.net/dam/a/58c/c023b7ce-e8fb-4bb0-8996-7ff4b4be258c_858341_EVENT_DETAIL_PAGE_16_9.jpg","type":"EVENT"}]
         */

        private List<EventCategoryBean> eventCategory;


        private List<CustomBean> custom;
        private List<VenueCategoryBean> venueCategory;
        private int influencer;
        private int resetPasswordKey;
        private int isOnline;
        private int isEmailVerified;
        private String email;
        private String phone;
        private String countryCode;
        private String fullName;

        private String googlePlaceId;
        private String userName;
        private String verificationCode;
        private String verificationTime;
        private String photoURL;
        private String createdAt;
        private String updatedAt;
        private int userId;
        private String gender;
        private String knownByName;
        private String DOB;
        private String city;
        private String incomeLevel;
        private String userToken;
        private String tokenTime;
        private int tokenStatus;
        private String favoutiteCity;
        private String shortDesc;
        private String favoutiteRestaurant;
        private String work;
        private String hometown;
        private String description;
        private String socialMedia;
        private int profileComplete;
        private List<PreferencesBean> preferences;
        private List<VenuesBean> venues;
        private List<EventsBean> events;
        private List<FavouritesBean> favourites;

        public List<CustomBean> getCustom() {
            return custom;
        }

        public void setCustom(List<CustomBean> custom) {
            this.custom = custom;
        }

        public List<UserProfileDetailModal.UserDataBean.LanguageBean> getLanguage() {
            return language;
        }

        public void setLanguage(List<UserProfileDetailModal.UserDataBean.LanguageBean> language) {
            this.language = language;
        }

        public String getGooglePlaceId() {
            return googlePlaceId;
        }

        public void setGooglePlaceId(String googlePlaceId) {
            this.googlePlaceId = googlePlaceId;
        }

        private List<UserProfileDetailModal.UserDataBean.LanguageBean> language;
        private List<EventPreferencesBean> eventPreferences;
        private List<VenuePreferencesBean> venuePreferences;

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

        public List<EventCategoryBean> getEventCategory() {
            return eventCategory;
        }

        public void setEventCategory(List<EventCategoryBean> eventCategory) {
            this.eventCategory = eventCategory;
        }

        public List<VenueCategoryBean> getVenueCategory() {
            return venueCategory;
        }

        public void setVenueCategory(List<VenueCategoryBean> venueCategory) {
            this.venueCategory = venueCategory;
        }

        public int getInfluencer() {
            return influencer;
        }

        public void setInfluencer(int influencer) {
            this.influencer = influencer;
        }

        public int getResetPasswordKey() {
            return resetPasswordKey;
        }

        public void setResetPasswordKey(int resetPasswordKey) {
            this.resetPasswordKey = resetPasswordKey;
        }

        public int getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(int isOnline) {
            this.isOnline = isOnline;
        }

        public int getIsEmailVerified() {
            return isEmailVerified;
        }

        public void setIsEmailVerified(int isEmailVerified) {
            this.isEmailVerified = isEmailVerified;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getVerificationCode() {
            return verificationCode;
        }

        public void setVerificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
        }

        public String getVerificationTime() {
            return verificationTime;
        }

        public void setVerificationTime(String verificationTime) {
            this.verificationTime = verificationTime;
        }

        public String getPhotoURL() {
            return photoURL;
        }

        public void setPhotoURL(String photoURL) {
            this.photoURL = photoURL;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getKnownByName() {
            return knownByName;
        }

        public void setKnownByName(String knownByName) {
            this.knownByName = knownByName;
        }

        public String getDOB() {
            return DOB;
        }

        public void setDOB(String DOB) {
            this.DOB = DOB;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getIncomeLevel() {
            return incomeLevel;
        }

        public void setIncomeLevel(String incomeLevel) {
            this.incomeLevel = incomeLevel;
        }

        public String getUserToken() {
            return userToken;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }

        public String getTokenTime() {
            return tokenTime;
        }

        public void setTokenTime(String tokenTime) {
            this.tokenTime = tokenTime;
        }

        public int getTokenStatus() {
            return tokenStatus;
        }

        public void setTokenStatus(int tokenStatus) {
            this.tokenStatus = tokenStatus;
        }

        public String getFavoutiteCity() {
            return favoutiteCity;
        }

        public void setFavoutiteCity(String favoutiteCity) {
            this.favoutiteCity = favoutiteCity;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
        }

        public String getFavoutiteRestaurant() {
            return favoutiteRestaurant;
        }

        public void setFavoutiteRestaurant(String favoutiteRestaurant) {
            this.favoutiteRestaurant = favoutiteRestaurant;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public String getHometown() {
            return hometown;
        }

        public void setHometown(String hometown) {
            this.hometown = hometown;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSocialMedia() {
            return socialMedia;
        }

        public void setSocialMedia(String socialMedia) {
            this.socialMedia = socialMedia;
        }

        public int getProfileComplete() {
            return profileComplete;
        }

        public void setProfileComplete(int profileComplete) {
            this.profileComplete = profileComplete;
        }

        public List<PreferencesBean> getPreferences() {
            return preferences;
        }

        public void setPreferences(List<PreferencesBean> preferences) {
            this.preferences = preferences;
        }

        public List<VenuesBean> getVenues() {
            return venues;
        }

        public void setVenues(List<VenuesBean> venues) {
            this.venues = venues;
        }

        public List<EventsBean> getEvents() {
            return events;
        }

        public void setEvents(List<EventsBean> events) {
            this.events = events;
        }

        public List<FavouritesBean> getFavourites() {
            return favourites;
        }

        public void setFavourites(List<FavouritesBean> favourites) {
            this.favourites = favourites;
        }

        public static class PreferencesBean {
            /**
             * subCategoryId : 1
             * subCategoryName : In The Know
             * icon : https://s3.amazonaws.com/wengageapp/subcategory/IN-THE-KNOW.png
             */

            private int subCategoryId;
            private String subCategoryName;
            private String icon;

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
        }

        public static class VenuesBean {
            /**
             * venueId : KovZpZAEkkIA
             * name : Budweiser Stage
             * imageURL : https://s1.ticketm.net/dbimages/18921v.png
             */

            private String venueId;
            private String name;
            private String imageURL;

            public String getVenueId() {
                return venueId;
            }

            public void setVenueId(String venueId) {
                this.venueId = venueId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImageURL() {
                return imageURL;
            }

            public void setImageURL(String imageURL) {
                this.imageURL = imageURL;
            }
        }

        public static class EventsBean {
            /**
             * eventId : 1718vfG6CGqWYWI
             * name : The Marcus King Band with Special Guest Ida Mae
             * imageURL : https://s1.ticketm.net/dam/a/66c/d0b5bdc4-0b7b-4a35-acea-124e0b60966c_735621_RECOMENDATION_16_9.jpg
             */

            private String eventId;
            private String name;
            private String imageURL;

            public String getEventId() {
                return eventId;
            }

            public void setEventId(String eventId) {
                this.eventId = eventId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImageURL() {
                return imageURL;
            }

            public void setImageURL(String imageURL) {
                this.imageURL = imageURL;
            }
        }

        public static class CustomBean {
            /**
             * categoryId : 1
             * categoryName : THE CITY
             * icon : https://s3.amazonaws.com/wengageapp/category/City.png
             * subCategory : [{"subCategoryId":5,"subCategoryName":"Special Events","icon":"https://s3.amazonaws.com/wengageapp/subcategory/SPECIAL-EVENTS.png","type":"event","optionalName":"","selected":1},{"subCategoryId":77,"subCategoryName":"Hotel Promotions","icon":"https://s3.amazonaws.com/wengageapp/subcategory/HOTEL-PROMOTIONS.png","type":"event","optionalName":"","selected":1}]
             */
            private String name;
            private String imageURL;
            private String address;
            private String description;
            private String status;
            private String startDate;
            private String userId;
            private String createdAt;
            private String updatedAt;
            private String eventId;
            private String sendCount;
            private String acceptedCount;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImageURL() {
                return imageURL;
            }

            public void setImageURL(String imageURL) {
                this.imageURL = imageURL;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
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

            public String getEventId() {
                return eventId;
            }

            public void setEventId(String eventId) {
                this.eventId = eventId;
            }

            public String getSendCount() {
                return sendCount;
            }

            public void setSendCount(String sendCount) {
                this.sendCount = sendCount;
            }

            public String getAcceptedCount() {
                return acceptedCount;
            }

            public void setAcceptedCount(String acceptedCount) {
                this.acceptedCount = acceptedCount;
            }

        }

        public static class FavouritesBean {
            /**
             * venueId : KovZpZAEkkIA
             * name : Budweiser Stage
             * imageURL : https://s1.ticketm.net/dbimages/18921v.png
             * type : VENUE
             * eventId : 1Ae0Z4oGkV-Gx8o
             */

            private String venueId;
            private String name;
            private String imageURL;
            private String type;
            private String eventId;

            public String getVenueId() {
                return venueId;
            }

            public void setVenueId(String venueId) {
                this.venueId = venueId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImageURL() {
                return imageURL;
            }

            public void setImageURL(String imageURL) {
                this.imageURL = imageURL;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getEventId() {
                return eventId;
            }

            public void setEventId(String eventId) {
                this.eventId = eventId;
            }
        }


        public static class EventPreferencesBean {
            /**
             * subCategoryId : 5
             * subCategoryName : Special Events
             * icon : https://s3.amazonaws.com/wengageapp/subcategory/SPECIAL-EVENTS.png
             */

            private int subCategoryId;
            private String subCategoryName;
            private String icon;

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
        }

        public static class VenuePreferencesBean {
            /**
             * subCategoryId : 1
             * subCategoryName : In The Know
             * icon : https://s3.amazonaws.com/wengageapp/subcategory/IN-THE-KNOW.png
             */

            private int subCategoryId;
            private String subCategoryName;
            private String icon;

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
        }

    }
}
