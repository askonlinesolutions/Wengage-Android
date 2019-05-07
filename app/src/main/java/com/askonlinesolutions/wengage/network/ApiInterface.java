package com.askonlinesolutions.wengage.network;

import com.askonlinesolutions.wengage.Activity.Main.dashboard.ProfilePerResposne;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.InfluencerResponse;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.InviteResponse;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.NotificationCountResponse;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.NotificationReadResponse;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.VenueResponse;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventDetailResponse;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventListResponse;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventSubCatDetailResponse;
import com.askonlinesolutions.wengage.Fragment.Main.profile.UpdateProfileModal;
import com.askonlinesolutions.wengage.Fragment.Main.profile.UserProfile;
import com.askonlinesolutions.wengage.Fragment.Main.venue.UserAllListResposne;
import com.askonlinesolutions.wengage.Fragment.Main.venue.details.rate.event.RateResponse;
import com.askonlinesolutions.wengage.Fragment.Main.venue.details.rate.event.RatingRequest;
import com.askonlinesolutions.wengage.Fragment.Main.vo.EventsSearchResponse;
import com.askonlinesolutions.wengage.Fragment.Main.vo.VeneuHomeListResponse;
import com.askonlinesolutions.wengage.Fragment.Sub.eventsInYourCity.vo.BookmarkRequest;
import com.askonlinesolutions.wengage.Fragment.Sub.eventsInYourCity.vo.InterestedRequest;
import com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.vo.GeneralSearchResponse;
import com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.vo.InterestedEventsResponse;
import com.askonlinesolutions.wengage.Model.CityModal;
import com.askonlinesolutions.wengage.Model.EventDetailEventResponse;
import com.askonlinesolutions.wengage.Model.GetContatctModal;
import com.askonlinesolutions.wengage.Model.Request.CategoryResponse;
import com.askonlinesolutions.wengage.Model.Request.CodeVerifyRequest;
import com.askonlinesolutions.wengage.Model.Request.LoginRequest;
import com.askonlinesolutions.wengage.Model.Request.OnlineProfileRequest;
import com.askonlinesolutions.wengage.Model.Request.ProfileRequest;
import com.askonlinesolutions.wengage.Model.Request.SignupRequest;
import com.askonlinesolutions.wengage.Model.Request.UpdateCustProfileRequest;
import com.askonlinesolutions.wengage.Model.Request.UpdateProfileRequest;
import com.askonlinesolutions.wengage.Model.Request.UserProfileRequest;
import com.askonlinesolutions.wengage.Model.Response.CodeVerifyResponse;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
import com.askonlinesolutions.wengage.Model.Response.NotificationModal;
import com.askonlinesolutions.wengage.Model.Response.SignupResponse;
import com.askonlinesolutions.wengage.Model.Response.SubCategoryResponse;
import com.askonlinesolutions.wengage.Model.Response.UpdateProfileResponse;
import com.askonlinesolutions.wengage.Model.Response.UserListResponse;
import com.askonlinesolutions.wengage.Model.Response.VenueDetailsResponse;
import com.askonlinesolutions.wengage.Model.Response.VenueListResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("user/login")
    Call<LoginResponse> getLoginData(@Body LoginRequest loginRequest);

    @POST("user/signup")
    Call<SignupResponse> getSignUp(@Body SignupRequest signupRequest);

    @POST("user/verifyCode")
    Call<CodeVerifyResponse> getVerify(@Body CodeVerifyRequest codeVerifyRequest);

    @FormUrlEncoded
    @POST("user/forgotPassword")
    Call<ApiResponse> getNewPassword(@Field("email") String email);

    @PUT("user/resendCode/{userId}")
    Call<ApiResponse> resendCode(@Path("userId") String resendCode);

    @GET("getCities/{countryId}")
    Call<CityModal> getCountryies(@Path("countryId") int countryId);

    @FormUrlEncoded
    @PUT("respondToRequest")
    Call<ApiResponse> respondToInvite(@Field("userId") int userId,
                                       @Field("inviteId") String contactId,
                                       @Field("status") String status);
    @FormUrlEncoded
    @PUT("respondToInvite")
    Call<ApiResponse> respondToInvites(@Field("userId") int userId,
                                       @Field("inviteId") String contactId,
                                       @Field("status") String status);
    @FormUrlEncoded
    @PUT("contact/respondToRequest")
    Call<ApiResponse> respondToRequest(@Field("userId") int userId,
                                       @Field("contactId") String contactId,
                                       @Field("status") String status);

    @DELETE("contact/deleteRequestOrContact/{userId}/{contactId}")
    Call<ApiResponse> respondToRequestDelete(@Path("userId") int userId,
                                             @Path("contactId") String contactId);

    @GET("category")
    Call<com.askonlinesolutions.wengage.Model.Response.CategoryResponse> getAllCategories(@Query("type") String type);

    @GET("subcategoryByCategory/{id}")
    Call<SubCategoryResponse> getAllSubCategories(@Path("id") int categoryid, @Query("userId") int pageNum,
                                                  @Query("signup") String type,
                                                  @Query("lat") String lat,
                                                  @Query("long") String lang);


    @GET("users")
    Call<UserAllListResposne> getAllUsers(@Query("userId") int userId);

    @GET("contact/getAllInvitationsAndContacts/")
    Call<GetContatctModal> getAllContact(@Path("id") int userId);


    @GET("users/")
    Call<UserAllListResposne> getUsersByCategory(@Query("userId") int userId,@Query("type") String type,
                                                 @Query("categoryId") int categoryId);


    @GET("users/")
    Call<UserAllListResposne> getUsersBySubCategory(@Query("userId") int userId,
                                                    @Query("categoryId") int categoryId,
                                                    @Query("subCategoryId") int subCategoryId,
                                                    @Query("lat") String lat,
                                                    @Query("long") String lang);

    @GET("users/")
    Call<UserAllListResposne> getUsersBySubCategoryWithoutLatLong(@Query("userId") int userId,
                                                                  @Query("categoryId") int categoryId,
                                                                  @Query("subCategoryId") int subCategoryId);
    //  Venues API

    @GET("venue")
    Call<VenueListResponse> getVenueList(@Query("pageNum") int page, @Query("categoryId") int cateId,
                                         @Query("subCategoryId") int subCatId);

    @GET("venue")
    Call<VeneuHomeListResponse> getHomeVenueList(@Query("pageNum") int page,
                                                 @Query("userId") int userId,
                                                 @Query("lat") String lat,
                                                 @Query("long") String lang);


    @GET("venue")
    Call<VeneuHomeListResponse> getSubCatVenueList(@Query("pageNum") int page,
                                                   @Query("userId") int userId,
                                                   @Query("categoryId") int cateId,
                                                   @Query("type") String type,
                                                   @Query("lat") String lat,
                                                   @Query("long") String lang);

    @GET("venue")
    Call<VeneuHomeListResponse> getSubCatVenueListWithoutLatLong(@Query("pageNum") int page,
                                                                 @Query("userId") int userId,
                                                                 @Query("categoryId") int cateId,
                                                                 @Query("type") String type);

    @GET("venue")
    Call<VeneuHomeListResponse> getSubCatSubVenueList(@Query("pageNum") int page, @Query("userId") int userId,
                                                      @Query("categoryId") int cateId,
                                                      @Query("subCategoryId") int subCatId,
                                                      @Query("lat") String lat,
                                                      @Query("long") String lang);

    @GET("venue")
    Call<VeneuHomeListResponse> getSubCatSubVenueListWithoutLatLong(@Query("pageNum") int page, @Query("userId") int userId,
                                                                    @Query("categoryId") int cateId,
                                                                    @Query("subCategoryId") int subCatId);

    @GET("venue")
    Call<VeneuHomeListResponse> getSubCatSubVenueListWithTag(@Query("pageNum") int page, @Query("userId") int userId,
                                                             @Query("categoryId") int cateId,
                                                             @Query("subCategoryId") int subCatId,
                                                             @Query("lat") double lat,
                                                             @Query("long") double lang,
                                                             @Query("tagId") int tagId);


    @GET("notifications")
    Call<NotificationModal> getNotifications(@Query("userId") int userId,
                                             @Query("type") String type,
                                             @Query("pageNum") int page);

    @GET("notifications")
    Call<InviteResponse> getInviteNotifiation(@Query("userId") int userId,
                                              @Query("type") String type,
                                              @Query("pageNum") int page);

    @GET("notifications")
    Call<VenueResponse> getVenueNotifiation(@Query("userId") int userId,
                                             @Query("type") String type,
                                             @Query("pageNum") int page);
    @GET("notifications")
    Call<InfluencerResponse> getInfluenserNotifiation(@Query("userId") int userId,
                                                      @Query("type") String type,
                                                      @Query("pageNum") int page);



    @GET("venue/{venueId}")
    Call<VenueDetailsResponse> getVenueDetail(@Path("venueId") String venue_id,
                                              @Query("userId") int userId);


    @POST("rate")
    Call<RateResponse> rateUsEvent(@Body RatingRequest ratingRequest);

    @PUT("user/updateProfile/{user_id}")
    Call<LoginResponse> updateProfile(@Path("user_id") int userId, @Body UpdateProfileRequest updateProfileRequest);

    @PUT("user/updateProfile/{user_id}")
    Call<UpdateProfileResponse> onlineProfile(@Path("user_id") int userId, @Body OnlineProfileRequest updateProfileRequest);


    @PUT("user/updateProfile/{user_id}")
    Call<CategoryResponse> updateCategory(@Path("user_id") int userId,
                                          @Body JSONObject jsonObject);

    @PUT("user/updateProfile/{user_id}")
    Call<UpdateProfileResponse> updateProfiles(@Path("user_id") int userId, @Body UpdateProfileModal updateProfileRequest);

    @PUT("user/updateProfile/{user_id}")
    Call<LoginResponse> updateCustome(@Path("user_id") int userId,
                                      @Body UpdateCustProfileRequest custProfileRequest);

    @FormUrlEncoded
    @POST("venue/interested")
    Call<ApiResponse> markVenueInterest(@Field("userId") int userId,
                                        @Field("venueId") String venue_id,
                                        @Field("isInterested") String status);


    @FormUrlEncoded
    @POST("venue/bookmark")
    Call<ApiResponse> markVenueBookmarked(@Field("userId") int userId, @Field("venueId") String venue_id,
                                          @Field("isBookmarked") String status);

    @FormUrlEncoded
    @POST("contact/sendRequest")
    Call<ApiResponse> sendRequest(@Field("senderId") int senderId, @Field("receiverId") int receiverId);

    @GET("users")
    Call<UserListResponse> getUserListDetails(@Query("userId") int userId);

    @POST("user/profile")
    Call<UserProfile> getUserDetails(@Body ProfileRequest profile_request);

    @PUT("user/updateProfile/{user_id}")
    Call<APIClient> updateProfileForVenue(@Path("user_id") int userId, @Body JsonObject updateProfileRequest);

    @POST("user/profile")
    Call<UserProfileRequest> getUserDetailss(@Body UserProfileRequest profile_request);

//    call event api

    @GET("event")
    Call<EventListResponse> getHomeEventList(@Query("pageNum") int page, @Query("userId") int userId,
                                             @Query("lat") String lat,
                                             @Query("long") String lang);


    @GET("event")
    Call<EventSubCatDetailResponse> getSubCatSubEventList(@Query("pageNum") int page,
                                                          @Query("userId") int userId,
                                                          @Query("categoryId") int cateId,
                                                          @Query("subCategoryId") int subCatId,
                                                          @Query("lat") String lat,
                                                          @Query("long") String lang);

    @GET("event")
    Call<EventSubCatDetailResponse> getSubCatSubEventListWithoutLatLong(@Query("pageNum") int page,
                                                                        @Query("userId") int userId,
                                                                        @Query("categoryId") int cateId,
                                                                        @Query("subCategoryId") int subCatId);


    @GET("event")
    Call<EventListResponse> getSubCatSubEventLists(@Query("pageNum") int page,
                                                   @Query("userId") int userId,
                                                   @Query("categoryId") int cateId,
                                                   @Query("lat") String lat,
                                                   @Query("long") String lang);

    @GET("event")
    Call<EventListResponse> getSubCatSubEventListsWithoutLatLong(@Query("pageNum") int page,
                                                                 @Query("userId") int userId,
                                                                 @Query("categoryId") int cateId);

    @GET("user/interestedEvents")
    Call<InterestedEventsResponse> getInterestedEventList(@Query("pageNum") int i,
                                                          @Query("userId") int userId);

    @GET("user/eventsInYourCity")
    Call<InterestedEventsResponse> getEventList(@Query("pageNum") int page, @Query("userId") int userId);

    @GET("events/influencerPick")
    Call<InterestedEventsResponse> getInfluencerPick(@Query("pageNum") int page, @Query("userId") int userId);


    @POST("event/bookmark")
    Call<ApiResponse> eventBookmarked(@Body BookmarkRequest bookmarkRequest);

    @POST("event/interested")
    Call<ApiResponse> eventInterested(@Body InterestedRequest interestedRequest);

    @GET("event/{eventId}")
    Call<EventDetailResponse> getEventDetail(@Path("eventId") String venue_id,
                                             @Query("userId") int userId);

    //    dashboard
    @GET("event/{eventId}")
    Call<EventDetailEventResponse> getEventDetails(@Path("eventId") String venue_id,
                                                   @Query("userId") int userId);

//
//    @GET("venue")
//    Call<VenueDetailsResponse> getVenueDetail(@Query("venueId") String venue_id,
//                                              @Query("userId") int userId);

//    dashboard

    @GET("user/profilePercentage/{user_id}")
    Call<ProfilePerResposne> getProfilePer(@Path("user_id") int user_id);

    @GET("search")
    Call<GeneralSearchResponse> getSearchesList(@Query("keyword") String keyword,
                                                @Query("pageNum") int page);

    @GET("events/search")
    Call<EventsSearchResponse> getEventSearchesList(@Query("keyword") String keyword);


    @GET("getAllNotificationsCount")
    Call<NotificationCountResponse> getNotifiationCount(@Query("userId") int userId);

    @FormUrlEncoded
    @POST("notification/read")
    Call<NotificationReadResponse> getReadNotification(@Field("userId") int userId, @Field("notificationId") int notificationId);



}