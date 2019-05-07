package com.askonlinesolutions.wengage.Networks;

import android.content.Context;

import com.askonlinesolutions.wengage.CallBacks.NetworkCallback;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.koushikdutta.ion.builder.Builders;

public class Network {

    public static final int INT = 1;
    static String responce;
    private static int status;
    private static String url;
    private static NetworkCallback callback;

    public Network(NetworkCallback callback, String url) {
        Network.url = url;
        Network.callback = callback;
    }

    private static Builders.Any.B getIon(Context context, String url, int timeout) {
        return Ion.with ( context ).load ( url ).setTimeout ( timeout );
    }

    public static void getNetworkResponseWithOutAuth(final Context context, JsonObject jsonObject, int timeout,
                                                     final String url, final NetworkCallback callback,
                                                     final int requestCode) {
//        sharedPreference = SharedPreference.getInstance ( context );
//        String access_token = sharedPreference.getString ( Constants.ACCESS_TOKEN, "" );
        Builders.Any.B ion = getIon ( context, url, timeout );
        ion.setJsonObjectBody ( jsonObject )
                .asJsonObject ().withResponse ()
                .setCallback ( new FutureCallback <Response <JsonObject>> () {
                    @Override
                    public void onCompleted(Exception e, Response <JsonObject> result) {
                        if (e != null) {
//                            DialogUtils.alertDialog ( context );
                            callback.onNetworkTimeout ( "" );
                            return;
                        }
                        status = result.getHeaders ().code ();
                        JsonObject resultObject = result.getResult ();
                        callback.onNetworkSuccess ( resultObject, status, requestCode );
                    }
                } );
    }
//
//    public static void getNetworkResponseWithOutAuthForSocial(final Context context, JsonObject jsonObject, int timeout,
//                                                              final String url, final NetworkCallback callback,
//                                                              final int requestCode) {
//        Builders.Any.B ion = getIon ( context, url, timeout );
//        ion.setJsonObjectBody ( jsonObject )
//                .asJsonObject ().withResponse ()
//                .setCallback ( new FutureCallback <Response <JsonObject>> () {
//                    @Override
//                    public void onCompleted(Exception e, Response <JsonObject> result) {
//                        if (e != null) {
////                            DialogUtils.alertDialog ( context );
//                            callback.onNetworkTimeout ( "" );
//                            return;
//                        }
//                        status = result.getHeaders ().code ();
//                        JsonObject resultObject = result.getResult ();
//                        callback.onNetworkSuccess ( resultObject, status, requestCode );
//                    }
//                } );
//    }
//
//    public static void getNetworkResponseGetWithoutHeader(final Context context, int timeout,
//                                                          final String url,
//                                                          final NetworkCallback callback,
//                                                          final int requestCode) {
//        Builders.Any.B ion = getIon ( context, url, timeout );
//        ion.asJsonObject ().withResponse ()
//                .setCallback ( new FutureCallback <Response <JsonObject>> () {
//                    @Override
//                    public void onCompleted(Exception e, Response <JsonObject> result) {
//                        if (e != null) {
//                            DialogUtils.alertDialog ( context );
//                            callback.onNetworkTimeout ( "" );
//                            return;
//                        }
//                        status = result.getHeaders ().code ();
//                        JsonObject resultObject = result.getResult ();
//                        callback.onNetworkSuccess ( resultObject, status, requestCode );
//
//                    }
//                } );
//    }
//
//    public static void hitPostApi(final Context context, final JsonObject object, final NetworkCallBack listener,
//                                  final String networkUrl, final int requestCode) {
//        String accessToke = new SharedPreference ( context ).getString ( Constants.ACCESS_TOKEN, "" );
//        Ion.with ( context ).load ( networkUrl ).setTimeout ( 20 * 1000 )
//                .setJsonObjectBody ( object )
//                .asJsonObject ()
//                .withResponse ()
//                .setCallback ( new FutureCallback <Response <JsonObject>> () {
//                    @Override
//                    public void onCompleted(Exception e, Response <JsonObject> result) {
//                        if (e == null) {
//                            if (result != null) {
//                                //do your stuff here
//                            } else {
//                                listener.onError ( "Network Error" );
//                            }
//                        } else {
//                            if (e instanceof com.google.gson.JsonParseException) {
//                                listener.onError ( "PHP Controller Exceptions" );
//                            }
//                            if (e instanceof java.util.concurrent.TimeoutException) {
//                                listener.onError ( "Oops! Time Out" );
//                            } else {
//                                listener.onError ( "Network Error Occured" );
//                            }
//                        }
//                    }
//                } );
//    }
//
//    public static void hitGetApi(final Context context, final NetworkCallBack listener,
//                                 final String networkUrl, final int requestCode) {
//        String accessToke = new SharedPreference ( context ).getString ( Constants.ACCESS_TOKEN, "" );
//        Ion.with ( context ).load ( networkUrl ).setTimeout ( 20 * 1000 )
//                .asJsonObject ()
//                .withResponse ()
//                .setCallback ( new FutureCallback <Response <JsonObject>> () {
//                    @Override
//                    public void onCompleted(Exception e, Response <JsonObject> result) {
//                        if (e == null) {
//                            if (result != null) {
//                                //do your stuff here
//                            } else {
//                                listener.onError ( "Bad Request" );
//                            }
//                        } else {
//                            if (e instanceof com.google.gson.JsonParseException) {
//                                listener.onError ( "PHP Controller Exceptions" );
//                            }
//                            if (e instanceof java.util.concurrent.TimeoutException) {
//                                listener.onError ( "Oops! Time Out" );
//                            } else {
//                                listener.onError ( "Network Error" );
//                            }
//                        }
//                    }
//                } );
//    }
//
//    public static void hitCreateQuizApiForPremium(final Context context, String userId, String QuizType,
//                                                  String type, String category_id, String title,
//                                                  String genders, String ranges, String is_subscribers,
//                                                  String end_date_times, String max_play_limit,
//                                                  String max_time, String description, JsonArray tagArray,
//                                                  String start_date_times, String languages,
//                                                  String media_types, String score_per_ques, File imgFile,
//                                                  File imgFileFormVideo, File thumbnail, String percentage_required,
//                                                  String amount_per_quiz, String total_budget, String gamer_prize, String add_credit,
//                                                  String is_campaign, String campaign_id, String campaign_name, String winning_coupon, String min_age,
//                                                  String max_age, String amount_donate, String group_id, String quiz_id,
//                                                  final NetworkCallBack listener, final String networkUrl,
//                                                  final int requestCode) {
//        String accessToke = new SharedPreference ( context ).getString ( Constants.ACCESS_TOKEN, "" );
//        if (imgFile == null && thumbnail == null) {
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            if (!quiz_id.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", quiz_id );
//            }
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "gamer_prize", gamer_prize );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", "0" );
//            builder.setMultipartParameter ( "group_id", group_id );
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//            builder.setMultipartParameter ( "is_campaign", is_campaign );
//            builder.setMultipartParameter ( "amount_per_quiz", amount_per_quiz );
//            builder.setMultipartParameter ( "total_budget", total_budget );
//            builder.setMultipartParameter ( "add_credit", add_credit );
//            builder.setMultipartParameter ( "winning_coupon", winning_coupon );
//            builder.setMultipartParameter ( "amount_donating", amount_donate );
//            builder.setMultipartParameter ( "campaign_id", campaign_id );
//            builder.setMultipartParameter ( "campaign_name", campaign_name );
//            builder.setMultipartParameter ( "media_type", "0" );
//            builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else if (imgFile != null) {
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartFile ( "thumbnail", imgFile );
//            if (!quiz_id.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", quiz_id );
//            }
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "group_id", group_id );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "gamer_prize", gamer_prize );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", media_types );
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//            builder.setMultipartParameter ( "is_campaign", is_campaign );
//            builder.setMultipartParameter ( "amount_per_quiz", amount_per_quiz );
//            builder.setMultipartParameter ( "total_budget", total_budget );
//            builder.setMultipartParameter ( "add_credit", add_credit );
//            builder.setMultipartParameter ( "winning_coupon", winning_coupon );
//            builder.setMultipartParameter ( "amount_donating", amount_donate );
//            builder.setMultipartParameter ( "campaign_id", "1" );
//            builder.setMultipartParameter ( "campaign_name", "abc" );
//            builder.setMultipartParameter ( "score_per_ques", score_per_ques );
//
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else if (thumbnail != null) {
//
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartFile ( "thumbnail", thumbnail );
//            builder.setMultipartFile ( "video", imgFileFormVideo );
//            builder.setMultipartParameter ( "user_id", userId );
//            if (!quiz_id.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", quiz_id );
//            }
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "group_id", group_id );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", media_types );
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//            builder.setMultipartParameter ( "is_campaign", is_campaign );
//            builder.setMultipartParameter ( "amount_per_quiz", amount_per_quiz );
//            builder.setMultipartParameter ( "total_budget", total_budget );
//            builder.setMultipartParameter ( "add_credit", add_credit );
//            builder.setMultipartParameter ( "gamer_prize", gamer_prize );
//            builder.setMultipartParameter ( "winning_coupon", winning_coupon );
//            builder.setMultipartParameter ( "score_per_ques", score_per_ques );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else {
//
//        }
//
//    }
//
//    public static void hitCreateQuizApiForPremiums(final Context context, String userId, String QuizType,
//                                                   String type, String category_id, String title,
//                                                   String genders, String ranges, String is_subscribers,
//                                                   String end_date_times, String max_play_limit,
//                                                   String max_time, String description, JsonArray tagArray,
//                                                   String start_date_times, String languages,
//                                                   String media_types, String score_per_ques, File imgFile,
//                                                   File imgFileFormVideo, File thumbnail, String percentage_required,
//                                                   String amount_per_quiz, String total_budget, String add_credit,
//                                                   String is_campaign, String campaign_id, String campaign_name, String winning_coupon, String gamer_prize,
//                                                   String min_age,
//                                                   String max_age, String amount_donate, String group_id, String quiz_id,
//                                                   final NetworkCallBack listener, final String networkUrl,
//                                                   final int requestCode) {
//        String accessToke = new SharedPreference ( context ).getString ( Constants.ACCESS_TOKEN, "" );
//        if (imgFile == null && thumbnail == null) {
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            if (!quiz_id.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", quiz_id );
//            }
//
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "gamer_prize", gamer_prize );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", "0" );
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//            builder.setMultipartParameter ( "is_campaign", is_campaign );
//            builder.setMultipartParameter ( "amount_per_quiz", amount_per_quiz );
//            builder.setMultipartParameter ( "total_budget", total_budget );
//            builder.setMultipartParameter ( "add_credit", add_credit );
//            builder.setMultipartParameter ( "winning_coupon", winning_coupon );
//            builder.setMultipartParameter ( "amount_donating", amount_donate );
//            builder.setMultipartParameter ( "campaign_id", campaign_id );
//            builder.setMultipartParameter ( "media_type", "0" );
//            builder.setMultipartParameter ( "campaign_name", campaign_name );
//            builder.setMultipartParameter ( "group_id", group_id );
//            builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else if (imgFile != null) {
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartFile ( "thumbnail", imgFile );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            if (!quiz_id.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", quiz_id );
//            }
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "gamer_prize", gamer_prize );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "group_id", group_id );
//            builder.setMultipartParameter ( "media_type", media_types );
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//            builder.setMultipartParameter ( "is_campaign", is_campaign );
//            builder.setMultipartParameter ( "amount_per_quiz", amount_per_quiz );
//            builder.setMultipartParameter ( "total_budget", total_budget );
//            builder.setMultipartParameter ( "add_credit", add_credit );
//            builder.setMultipartParameter ( "winning_coupon", winning_coupon );
//            builder.setMultipartParameter ( "amount_donating", amount_donate );
//            builder.setMultipartParameter ( "campaign_id", campaign_id );
//            builder.setMultipartParameter ( "campaign_name", campaign_name );
//            builder.setMultipartParameter ( "score_per_ques", score_per_ques );
//
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else if (thumbnail != null) {
//
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartFile ( "thumbnail", thumbnail );
//            builder.setMultipartFile ( "video", imgFileFormVideo );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            if (!quiz_id.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", quiz_id );
//            }
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", media_types );
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//            builder.setMultipartParameter ( "is_campaign", is_campaign );
//            builder.setMultipartParameter ( "amount_per_quiz", amount_per_quiz );
//            builder.setMultipartParameter ( "total_budget", total_budget );
//            builder.setMultipartParameter ( "add_credit", add_credit );
//            builder.setMultipartParameter ( "group_id", group_id );
//            builder.setMultipartParameter ( "campaign_id", campaign_id );
//            if (!gamer_prize.equals ( "" )) {
//                builder.setMultipartParameter ( "gamer_prize", gamer_prize );
//            }
//            if (!amount_donate.equals ( "" )) {
//                builder.setMultipartParameter ( "amount_donating", amount_donate );
//            }
//
//            builder.setMultipartParameter ( "winning_coupon", winning_coupon );
//            builder.setMultipartParameter ( "score_per_ques", score_per_ques );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else {
//
//        }
//
//    }
//
//    public static void hitCreateQuizApiForPremiumWithoutImage(final Context context, String userId, String QuizType,
//                                                              String type, String category_id, String title,
//                                                              String genders, String ranges, String is_subscribers,
//                                                              String end_date_times, String max_play_limit,
//                                                              String max_time, String description, JsonArray tagArray,
//                                                              String start_date_times, String languages,
//                                                              String media_types, String score_per_ques,
//                                                              String editQuizId, String percentage_required,
//                                                              String amount_per_quiz, String total_budget, String gamer_prize,
//                                                              String add_credit, String is_campaign,
//                                                              String campaign_id, String campaign_name,
//                                                              String winning_coupon, String min_age,
//                                                              String max_age, String amount_donate,
//                                                              String group_id,
//                                                              String quiz_id,
//                                                              final NetworkCallBack listener,
//                                                              final String networkUrl,
//                                                              final int requestCode) {
//        String accessToke = new SharedPreference ( context ).getString ( Constants.ACCESS_TOKEN, "" );
////        if (imgFile == null && thumbnail == null) {
////            Toast.makeText(context, "Please capture image/Video.", Toast.LENGTH_SHORT).show();
////        } else if (imgFile != null) {gh;lfg[ph,lyhjmghmj
//        Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//        for (int i = 0; i < tagArray.size (); i++) {
//            builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//        }
//        builder.setHeader ( "Authorization", accessToke );
//        if (!editQuizId.equals ( "0" )) {
//            builder.setMultipartParameter ( "quiz_id", editQuizId );
//        }
//        builder.setMultipartParameter ( "user_id", userId );
//        builder.setMultipartParameter ( "quiz_type", QuizType );
//        builder.setMultipartParameter ( "min_age", min_age );
//        builder.setMultipartParameter ( "max_age", max_age );
//        builder.setMultipartParameter ( "type", type );
//        builder.setMultipartParameter ( "category_id", category_id );
//        builder.setMultipartParameter ( "title", title );
//        builder.setMultipartParameter ( "group_id", group_id );
//        builder.setMultipartParameter ( "description", description );
//        builder.setMultipartParameter ( "gender", genders );
//        builder.setMultipartParameter ( "gamer_prize", gamer_prize );
//        builder.setMultipartParameter ( "range", ranges );
//        builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//        builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//        builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//        builder.setMultipartParameter ( "start_date_time", start_date_times );
//        builder.setMultipartParameter ( "end_date_time", end_date_times );
//        builder.setMultipartParameter ( "language", languages );
//        builder.setMultipartParameter ( "media_type", "0" );
//        builder.setMultipartParameter ( "percentage_required", percentage_required );
//        builder.setMultipartParameter ( "is_campaign", is_campaign );
//        builder.setMultipartParameter ( "amount_per_quiz", amount_per_quiz );
//        builder.setMultipartParameter ( "total_budget", total_budget );
//        builder.setMultipartParameter ( "add_credit", add_credit );
//        builder.setMultipartParameter ( "winning_coupon", winning_coupon );
//        builder.setMultipartParameter ( "amount_donating", amount_donate );
//        builder.setMultipartParameter ( "campaign_id", campaign_id );
//        builder.setMultipartParameter ( "campaign_name", campaign_name );
//        builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//
//        builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//            @Override
//            public void onCompleted(Exception e, Response <JsonObject> result) {
//                if (e != null) {
//                    DialogUtils.alertDialog ( context );
//                    listener.onError ( "" );
//                    return;
//                }
//                status = result.getHeaders ().code ();
//                JsonObject resultObject = result.getResult ();
//                listener.onSuccess ( resultObject, status, requestCode );
//            }
//        } );
//    }
//
//    public static void hitCreateQuizApiForPremiumWithoutImages(final Context context, String userId, String QuizType,
//                                                               String type, String category_id, String title,
//                                                               String genders, String ranges, String is_subscribers,
//                                                               String end_date_times, String max_play_limit,
//                                                               String max_time, String description, JsonArray tagArray,
//                                                               String start_date_times, String languages,
//                                                               String media_types, String score_per_ques,
//                                                               String editQuizId, String percentage_required,
//                                                               String amount_per_quiz, String total_budget,
//                                                               String add_credit, String is_campaign,
//                                                               String campaign_id, String campaign_name,
//                                                               String winning_coupon, String gamer_prize, String min_age,
//                                                               String max_age, String amount_donate, String group_id, String quiz_id,
//                                                               final NetworkCallBack listener,
//                                                               final String networkUrl,
//                                                               final int requestCode) {
//        String accessToke = new SharedPreference ( context ).getString ( Constants.ACCESS_TOKEN, "" );
//        Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//        for (int i = 0; i < tagArray.size (); i++) {
//            builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//        }
//        builder.setHeader ( "Authorization", accessToke );
//        if (!editQuizId.equals ( "0" )) {
//            builder.setMultipartParameter ( "quiz_id", editQuizId );
//        }
//        builder.setMultipartParameter ( "user_id", userId );
//        builder.setMultipartParameter ( "quiz_type", QuizType );
//        builder.setMultipartParameter ( "min_age", min_age );
//        builder.setMultipartParameter ( "max_age", max_age );
//        builder.setMultipartParameter ( "type", type );
//        builder.setMultipartParameter ( "group_id", group_id );
//        builder.setMultipartParameter ( "category_id", category_id );
//        builder.setMultipartParameter ( "title", title );
//        builder.setMultipartParameter ( "description", description );
//        builder.setMultipartParameter ( "gender", genders );
//        builder.setMultipartParameter ( "gamer_prize", gamer_prize );
//        builder.setMultipartParameter ( "range", ranges );
//        builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//        builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//        builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//        builder.setMultipartParameter ( "start_date_time", start_date_times );
//        builder.setMultipartParameter ( "end_date_time", end_date_times );
//        builder.setMultipartParameter ( "language", languages );
//        builder.setMultipartParameter ( "media_type", "0" );
//        builder.setMultipartParameter ( "percentage_required", percentage_required );
//        builder.setMultipartParameter ( "is_campaign", is_campaign );
//        builder.setMultipartParameter ( "amount_per_quiz", amount_per_quiz );
//        builder.setMultipartParameter ( "total_budget", total_budget );
//        builder.setMultipartParameter ( "add_credit", add_credit );
//        builder.setMultipartParameter ( "winning_coupon", winning_coupon );
//        builder.setMultipartParameter ( "amount_donating", amount_donate );
//        builder.setMultipartParameter ( "campaign_id", campaign_id );
//        builder.setMultipartParameter ( "campaign_name", campaign_name );
//        builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//
//        builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//            @Override
//            public void onCompleted(Exception e, Response <JsonObject> result) {
//                if (e != null) {
//                    DialogUtils.alertDialog ( context );
//                    listener.onError ( "" );
//                    return;
//                }
//                status = result.getHeaders ().code ();
//                JsonObject resultObject = result.getResult ();
//                listener.onSuccess ( resultObject, status, requestCode );
//            }
//        } );
//    }
//
//    public static void hitCreateQuizApiForStandard(final Context context, String userId, String QuizType,
//                                                   String type, String category_id, String title,
//                                                   String genders, String ranges, String is_subscribers,
//                                                   String end_date_times, String max_play_limit,
//                                                   String max_time, String description, JsonArray tagArray,
//                                                   String start_date_times, String languages,
//                                                   String media_types, String score_per_ques, File imgFile,
//                                                   File imgFileFormVideo, File thumbnail, String min_age,
//                                                   String max_age, String percentage_required, final NetworkCallBack listener,
//                                                   final String networkUrl,
//                                                   final int requestCode) {
//        String accessToke = new SharedPreference ( context ).getString ( Constants.ACCESS_TOKEN, "" );
//
//        if (imgFile == null && thumbnail == null) {
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", "0" );
//            builder.setMultipartParameter ( "score_per_ques", score_per_ques );
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else if (imgFile != null) {
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//            if (tagArray.size () > 0) {
//                for (int i = 0; i < tagArray.size (); i++) {
//                    builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//                }
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartFile ( "thumbnail", imgFile );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", media_types );
//            builder.setMultipartParameter ( "score_per_ques", score_per_ques );
//            builder.setMultipartParameter ( "percentage_required", percentage_required /*score_per_ques*/ );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else if (thumbnail != null) {
//
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartFile ( "thumbnail", thumbnail );
//            builder.setMultipartFile ( "video", imgFileFormVideo );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", media_types );
//            builder.setMultipartParameter ( "percentage_required", percentage_required /*score_per_ques*/ );
//            builder.setMultipartParameter ( "score_per_ques", score_per_ques );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else {
//
//        }
//
//    }
//
//    public static void hitCreateQuizApiForStandardForEditWithimages(final Context context, String userId,
//                                                                    String QuizType, String type,
//                                                                    String category_id, String title, String genders,
//                                                                    String ranges, String is_subscribers,
//                                                                    String end_date_times, String max_play_limit,
//                                                                    String max_time, String description,
//                                                                    JsonArray tagArray, String start_date_times,
//                                                                    String languages, String media_types,
//                                                                    String score_per_ques, File imgFile,
//                                                                    File imgFileFormVideo, File thumbnail,
//                                                                    String editQuizId, String min_age,
//                                                                    String max_age, String percentage_required,
//                                                                    final NetworkCallBack listener,
//                                                                    final String networkUrl,
//                                                                    final int requestCode) {
//        String accessToke = new SharedPreference ( context ).getString ( Constants.ACCESS_TOKEN, "" );
//
//        if (imgFile == null && thumbnail == null) {
//            Toast.makeText ( context, "Please capture image/Video.", Toast.LENGTH_SHORT ).show ();
//        } else if (imgFile != null) {
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//            if (tagArray.size () > 0) {
//                for (int i = 0; i < tagArray.size (); i++) {
//                    builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//                }
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartFile ( "thumbnail", imgFile );
//            builder.setMultipartParameter ( "user_id", userId );
//            if (!editQuizId.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", editQuizId );
//            }
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", media_types );
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//
//            builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else if (thumbnail != null) {
//
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartFile ( "thumbnail", thumbnail );
//            builder.setMultipartFile ( "video", imgFileFormVideo );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            if (!editQuizId.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", editQuizId );
//            }
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            if (!editQuizId.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", editQuizId );
//            }
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", media_types );
//            builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else {
//
//        }
//
//    }
//
//    public static void hitCreateQuizApiForPremiumForEditWithimages(final Context context, String userId,
//                                                                   String QuizType, String type,
//                                                                   String category_id, String title, String genders,
//                                                                   String ranges, String is_subscribers,
//                                                                   String end_date_times, String max_play_limit,
//                                                                   String max_time, String description,
//                                                                   JsonArray tagArray, String start_date_times,
//                                                                   String languages, String media_types,
//                                                                   String score_per_ques, File imgFile,
//                                                                   File imgFileFormVideo, File thumbnail,
//                                                                   String editQuizId, String min_age,
//                                                                   String max_age, String amount_donate, final NetworkCallBack listener,
//                                                                   final String networkUrl,
//                                                                   final int requestCode) {
//        String accessToke = new SharedPreference ( context ).getString ( Constants.ACCESS_TOKEN, "" );
//
//        if (imgFile == null && thumbnail == null) {
//            Toast.makeText ( context, "Please capture image/Video.", Toast.LENGTH_SHORT ).show ();
//        } else if (imgFile != null) {
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//            if (tagArray.size () > 0) {
//                for (int i = 0; i < tagArray.size (); i++) {
//                    builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//                }
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartFile ( "thumbnail", imgFile );
//            builder.setMultipartParameter ( "user_id", userId );
//            if (!editQuizId.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", editQuizId );
//            }
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", media_types );
//            builder.setMultipartParameter ( "amount_donating", amount_donate );
//
//            builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else if (thumbnail != null) {
//
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartFile ( "thumbnail", thumbnail );
//            builder.setMultipartFile ( "video", imgFileFormVideo );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "amount_donating", amount_donate );
//            builder.setMultipartParameter ( "media_type", media_types );
//            builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else {
//
//        }
//
//    }
//
//    public static void hitCreateQuizApiForPremiumForEditWithimage(final Context context, String userId,
//                                                                  String QuizType, String type,
//                                                                  String category_id, String title, String genders,
//                                                                  String ranges, String is_subscribers,
//                                                                  String end_date_times, String max_play_limit,
//                                                                  String max_time, String description,
//                                                                  JsonArray tagArray, String start_date_times,
//                                                                  String languages, String media_types,
//                                                                  String score_per_ques, File imgFile,
//                                                                  File imgFileFormVideo, File thumbnail,
//                                                                  String editQuizId, String min_age,
//                                                                  String max_age, String amount_donate,
//                                                                  String percentage_required, String is_campaign,
//                                                                  String campaign_id, String campaign_name,
//                                                                  String amount_per_quiz, String total_budget,
//                                                                  String group_id,
//                                                                  String quiz_id,
//                                                                  String gamer_pize,
//                                                                  String winning_coupon,
//                                                                  final NetworkCallBack listener,
//                                                                  final String networkUrl,
//                                                                  final int requestCode) {
//        String accessToke = new SharedPreference ( context ).getString ( Constants.ACCESS_TOKEN, "" );
//
//        if (imgFile == null && thumbnail == null) {
//            Toast.makeText ( context, "Please capture image/Video.", Toast.LENGTH_SHORT ).show ();
//        } else if (imgFile != null) {
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//            if (tagArray.size () > 0) {
//                for (int i = 0; i < tagArray.size (); i++) {
//                    builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//                }
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartFile ( "thumbnail", imgFile );
//            builder.setMultipartParameter ( "user_id", userId );
//            if (!editQuizId.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", editQuizId );
//            }
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "winning_coupon", winning_coupon );
//            builder.setMultipartParameter ( "gamer_prize", gamer_pize );
//            builder.setMultipartParameter ( "group_id", group_id );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", media_types );
//            builder.setMultipartParameter ( "amount_donating", amount_donate );
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//            builder.setMultipartParameter ( "is_campaign", is_campaign );
//            builder.setMultipartParameter ( "amount_per_quiz", amount_per_quiz );
//            builder.setMultipartParameter ( "total_budget", total_budget );
//
//            builder.setMultipartParameter ( "campaign_id", /*"1"*/ campaign_id );
//            builder.setMultipartParameter ( "campaign_name", campaign_name );
//            builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else if (thumbnail != null) {
//
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartFile ( "thumbnail", thumbnail );
//            builder.setMultipartFile ( "video", imgFileFormVideo );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "winning_coupon", winning_coupon );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "gamer_prize", gamer_pize );
//            if (!editQuizId.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", editQuizId );
//            }
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_campaign", is_campaign );
//            builder.setMultipartParameter ( "group_id", group_id );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "amount_donating", amount_donate );
//            builder.setMultipartParameter ( "media_type", media_types );
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//            builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//            builder.setMultipartParameter ( "amount_per_quiz", amount_per_quiz );
//            builder.setMultipartParameter ( "total_budget", total_budget );
//            builder.setMultipartParameter ( "campaign_id", /*"1"*/ campaign_id );
//            builder.setMultipartParameter ( "campaign_name", campaign_name );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else {
//
//        }
//
//    }
//
//    public static void hitCreateQuizApiForStandardEdit(final Context context, String userId, String QuizType,
//                                                       String type, String category_id, String title,
//                                                       String genders, String ranges, String is_subscribers,
//                                                       String end_date_times, String max_play_limit,
//                                                       String max_time, String description, JsonArray tagArray,
//                                                       String start_date_times, String languages,
//                                                       String media_types, String score_per_ques, String editQuizId, String min_age,
//                                                       String max_age, String percentage_required,
//                                                       final NetworkCallBack listener,
//                                                       final String networkUrl,
//                                                       final int requestCode) {
//        sharedPreference = SharedPreference.getInstance ( context );
//        String access_token = sharedPreference.getString ( Constants.ACCESS_TOKEN, "" );
//        Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//        if (tagArray.size () > 0) {
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//        }
//        builder.setHeader ( "Authorization", access_token );
//        builder.setMultipartParameter ( "user_id", userId );
//        if (!editQuizId.equals ( "0" )) {
//            builder.setMultipartParameter ( "quiz_id", editQuizId );
//        }
//        builder.setMultipartParameter ( "quiz_type", QuizType );
//        builder.setMultipartParameter ( "min_age", min_age );
//        builder.setMultipartParameter ( "max_age", max_age );
//        builder.setMultipartParameter ( "type", type );
//        builder.setMultipartParameter ( "category_id", category_id );
//        builder.setMultipartParameter ( "title", title );
//        builder.setMultipartParameter ( "description", description );
//        builder.setMultipartParameter ( "gender", genders );
//        builder.setMultipartParameter ( "range", ranges );
//        builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//        builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//        builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//        builder.setMultipartParameter ( "start_date_time", start_date_times );
//        builder.setMultipartParameter ( "end_date_time", end_date_times );
//        builder.setMultipartParameter ( "language", languages );
//        builder.setMultipartParameter ( "media_type", media_types );
//        builder.setMultipartParameter ( "score_per_ques", score_per_ques );
//        builder.setMultipartParameter ( "percentage_required", percentage_required );
//        builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//            @Override
//            public void onCompleted(Exception e, Response <JsonObject> result) {
//                if (e != null) {
//                    DialogUtils.alertDialog ( context );
//                    listener.onError ( "" );
//                    return;
//                }
//                status = result.getHeaders ().code ();
//                JsonObject resultObject = result.getResult ();
//                listener.onSuccess ( resultObject, status, requestCode );
//            }
//        } );
//
//    }
//
//    public static void hitCreateQuizApiForStandardForGroup(final Context context, String userId, String QuizType,
//                                                           String type, String category_id, String title,
//                                                           String genders, String ranges, String is_subscribers,
//                                                           String end_date_times, String max_play_limit,
//                                                           String max_time, String description, JsonArray tagArray,
//                                                           String start_date_times, String languages,
//                                                           String media_types, String score_per_ques, File imgFile,
//                                                           File imgFileFormVideo, File thumbnail, String group_id, String min_age,
//                                                           String max_age, String percentage_required, final NetworkCallBack listener, final String networkUrl,
//                                                           final int requestCode) {
//        String accessToke = new SharedPreference ( context ).getString ( Constants.ACCESS_TOKEN, "" );
//
//        if (imgFile == null && thumbnail == null) {
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "group_id", group_id );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", "0" );
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//            builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//
////            Toast.makeText(context, "Please capture image/Video.", Toast.LENGTH_SHORT).show();
//        } else if (imgFile != null) {
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartFile ( "thumbnail", imgFile );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "group_id", group_id );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//            builder.setMultipartParameter ( "media_type", media_types );
//            builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else if (thumbnail != null) {
//
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToke );
//            builder.setMultipartFile ( "thumbnail", thumbnail );
//            builder.setMultipartParameter ( "group_id", group_id );
//            builder.setMultipartFile ( "video", imgFileFormVideo );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", QuizType );
//            builder.setMultipartParameter ( "min_age", min_age );
//            builder.setMultipartParameter ( "max_age", max_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", /*"1" */max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_times );
//            builder.setMultipartParameter ( "end_date_time", end_date_times );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", media_types );
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//            builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else {
//
//        }
//
//    }
//
//    public static void hitCreateQuizApiForAddQuestion(final Activity activity, String quiz_id, JsonArray correctAnswerModalArrayList,
//                                                      ArrayList <InCorrectAnswerModal> inCorrectAnswerModalArrayList,
//                                                      ArrayList <Photo_Upload_Modal> docList, String question,
//                                                      String question_type, final int requestTypeAddQuizQuestion,
//                                                      String addQuizQuestion, final NetworkCallBack listener) {
//        if (correctAnswerModalArrayList == null) {
//            Toast.makeText ( activity, "Please fill correct answer.", Toast.LENGTH_SHORT ).show ();
//        } else if (inCorrectAnswerModalArrayList == null) {
//            Toast.makeText ( activity, "Please fill incorrect answer.", Toast.LENGTH_SHORT ).show ();
//        } else if (docList == null) {
//            Toast.makeText ( activity, "Please click images.", Toast.LENGTH_SHORT ).show ();
//        } else {
//            Builders.Any.B builder = Ion.with ( activity ).load ( "POST", addQuizQuestion ).setLogging ( "", Log.ERROR );
//            for (int i = 0; i < correctAnswerModalArrayList.size (); i++) {
//                builder.setMultipartParameter ( "answers[]", String.valueOf ( correctAnswerModalArrayList.get ( i ) ) );
//            }
//            for (int i = 0; i < inCorrectAnswerModalArrayList.size (); i++) {
////                builder.setMultipartParameter("answers[]", "");/*
////                builder.setMultipartParameter("answers[]", inCorrectAnswerModalArrayList.get(i).getIncorrectType());
////                builder.setMultipartParameter("answers[]", inCorrectAnswerModalArrayList.get(i).getOption());*/
//            }
//            for (int i = 0; i < docList.size (); i++) {
//                builder.setMultipartFile ( "related_images[]", new File ( docList.get ( i ).getImage () ) );
//            }
//            builder.setMultipartParameter ( "question_type", question_type );
//            builder.setMultipartParameter ( "question", question );
//            builder.setMultipartParameter ( "quiz_id", "32" );
//
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( activity );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestTypeAddQuizQuestion );
//                }
//            } );
//        }
//
//    }
//
//    public static void hitCreateQuizApiForAddImage(final Activity activity, String quiz_id,
//                                                   ArrayList <Photo_Upload_Modal> docList, String question,
//                                                   String question_type, final int requestTypeAddQuizQuestion,
//                                                   String addQuizQuestion, final NetworkCallBack listener) {
//
//        sharedPreference = SharedPreference.getInstance ( activity );
//        String accessToken = sharedPreference.getString ( Constants.ACCESS_TOKEN, "" );
//        if (docList == null) {
//            Toast.makeText ( activity, "Please click images.", Toast.LENGTH_SHORT ).show ();
//        } else {
//            Builders.Any.B builder = Ion.with ( activity ).load ( "POST", addQuizQuestion ).setLogging ( "", Log.ERROR );
//            for (int i = 0; i < docList.size (); i++) {
//                builder.setMultipartFile ( "related_images[]", new File ( docList.get ( i ).getImage () ) );
//            }
//            builder.setHeader ( "Authorization", accessToken );
//            builder.setMultipartParameter ( "question_type", question_type );
//            builder.setMultipartParameter ( "question", question );
//            if (!quiz_id.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", quiz_id );
//            }
//
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( activity );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestTypeAddQuizQuestion );
//                }
//            } );
//        }
//
//    }
//
//    public static void hitCreateQuizApiForEditQuestion(final Activity activity, String question_id,
//                                                       ArrayList <Photo_Upload_Modal> docList, String question, ArrayList <String> deleteArrayList,
//                                                       final int requestTypeAddQuizQuestion,
//                                                       String addQuizQuestion, final NetworkCallBack listener) {
//
//        sharedPreference = SharedPreference.getInstance ( activity );
//        String accessToken = sharedPreference.getString ( Constants.ACCESS_TOKEN, "" );
//        if (docList == null) {
//            Toast.makeText ( activity, "Please click images.", Toast.LENGTH_SHORT ).show ();
//        } else {
//            Builders.Any.B builder = Ion.with ( activity ).load ( "POST", addQuizQuestion ).setLogging ( "", Log.ERROR );
//            for (int i = 0; i < docList.size (); i++) {
//                if (!docList.get ( i ).getImage ().contains ( NetworkConstants.BASE_URLFORIMAGE ))
//                    builder.setMultipartFile ( "related_images[]", new File ( docList.get ( i ).getImage () ) );
//            }
//            builder.setMultipartParameter ( "question", question );
//            builder.setMultipartParameter ( "question_id", question_id );
//            if (deleteArrayList.size () > 0) {
//                for (int i = 0; i < deleteArrayList.size (); i++) {
//                    builder.setMultipartParameter ( "removed_question[]", deleteArrayList.get ( i ) );
//                }
//            }
//
//            builder.setHeader ( "Authorization", accessToken );
//
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( activity );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestTypeAddQuizQuestion );
//                }
//            } );
//        }
//
//    }
//
//    public static void hitCreateQuizApiForEditQuestionWithImage(final Activity activity, String question_id,
//                                                                String question, final int requestTypeAddQuizQuestion,
//                                                                String addQuizQuestion, final NetworkCallBack listener) {
//
//        sharedPreference = SharedPreference.getInstance ( activity );
//        String accessToken = sharedPreference.getString ( Constants.ACCESS_TOKEN, "" );
//
//        Builders.Any.B builder = Ion.with ( activity ).load ( "POST", addQuizQuestion ).setLogging ( "", Log.ERROR );
//        builder.setMultipartParameter ( "question", question );
//        builder.setMultipartParameter ( "question_id", question_id );
//        builder.setHeader ( "Authorization", accessToken );
//        builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//            @Override
//            public void onCompleted(Exception e, Response <JsonObject> result) {
//                if (e != null) {
//                    DialogUtils.alertDialog ( activity );
//                    listener.onError ( "" );
//                    return;
//                }
//                status = result.getHeaders ().code ();
//                JsonObject resultObject = result.getResult ();
//                listener.onSuccess ( resultObject, status, requestTypeAddQuizQuestion );
//            }
//        } );
//    }
//
//    public static void hitCreateQuizApiForCreateQuizForGroup(final Activity activity, String title,
//                                                             ArrayList <QuizListForInviteModal> arrayList,
//                                                             String description, File imgFile, final int requestTypeAddQuizQuestion,
//                                                             String addQuizQuestion, String AccessToken,
//                                                             final NetworkCallBack listener) {
//        if (arrayList == null) {
//            Toast.makeText ( activity, "Please invite atleast one people.", Toast.LENGTH_SHORT ).show ();
//        } else {
//            Builders.Any.B builder = Ion.with ( activity ).load ( "POST", addQuizQuestion ).setLogging ( "", Log.ERROR );
//            for (int i = 0; i < arrayList.size (); i++) {
//                builder.setMultipartParameter ( "host_ids[]", arrayList.get ( i ).getUser_id () );
//            }
//            builder.setHeader ( "Authorization", AccessToken );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            if (imgFile == null) {
//                builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                    @Override
//                    public void onCompleted(Exception e, Response <JsonObject> result) {
//                        if (e != null) {
//                            DialogUtils.alertDialog ( activity );
//                            listener.onError ( "" );
//                            return;
//                        }
//                        status = result.getHeaders ().code ();
//                        JsonObject resultObject = result.getResult ();
//                        listener.onSuccess ( resultObject, status, requestTypeAddQuizQuestion );
//                    }
//                } );
//            } else {
//                builder.setMultipartFile ( "thumbnail", imgFile );
//                builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                    @Override
//                    public void onCompleted(Exception e, Response <JsonObject> result) {
//                        if (e != null) {
//                            DialogUtils.alertDialog ( activity );
//                            listener.onError ( "" );
//                            return;
//                        }
//                        status = result.getHeaders ().code ();
//                        JsonObject resultObject = result.getResult ();
//                        listener.onSuccess ( resultObject, status, requestTypeAddQuizQuestion );
//                    }
//                } );
//            }
//
//        }
//
//    }
//
//    public static void hitCreateQuizApiForCreateQuizForGroupEditForStr(final Activity activity, String title,
//                                                                       ArrayList <QuizListForInviteModal> arrayList,
//                                                                       String group_id, String description,
//                                                                       File imgFile, final int requestTypeAddQuizQuestion,
//                                                                       String addQuizQuestion, String AccessToken,
//                                                                       final NetworkCallBack listener) {
//        if (arrayList == null) {
//            Toast.makeText ( activity, "Please invite atleast one people.", Toast.LENGTH_SHORT ).show ();
//        } else {
//            Builders.Any.B builder = Ion.with ( activity ).load ( "POST", addQuizQuestion ).setLogging ( "", Log.ERROR );
//            for (int i = 0; i < arrayList.size (); i++) {
//                builder.setMultipartParameter ( "host_ids[]", arrayList.get ( i ).getUser_id () );
//            }
//            builder.setHeader ( "Authorization", AccessToken );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "group_id", group_id );
//            if (imgFile == null) {
//                builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                    @Override
//                    public void onCompleted(Exception e, Response <JsonObject> result) {
//                        if (e != null) {
//                            DialogUtils.alertDialog ( activity );
//                            listener.onError ( "" );
//                            return;
//                        }
//                        status = result.getHeaders ().code ();
//                        JsonObject resultObject = result.getResult ();
//                        listener.onSuccess ( resultObject, status, requestTypeAddQuizQuestion );
//                    }
//                } );
//            } else {
//                builder.setMultipartFile ( "thumbnail", imgFile );
//                builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                    @Override
//                    public void onCompleted(Exception e, Response <JsonObject> result) {
//                        if (e != null) {
//                            DialogUtils.alertDialog ( activity );
//                            listener.onError ( "" );
//                            return;
//                        }
//                        status = result.getHeaders ().code ();
//                        JsonObject resultObject = result.getResult ();
//                        listener.onSuccess ( resultObject, status, requestTypeAddQuizQuestion );
//                    }
//                } );
//            }
//
//        }
//
//    }
//
//    public static void hitCreateQuizApiForCreateQuizForGroupEdit(final Activity activity, String title,
//                                                                 List <GroupDetailModal.ResultBean.GroupMemberBean> arrayList,
//                                                                 String group_id, String description, final int requestTypeAddQuizQuestion,
//                                                                 String addQuizQuestion, String AccessToken,
//                                                                 final NetworkCallBack listener) {
//        if (arrayList == null) {
//            Toast.makeText ( activity, "Please invite atleast one people.", Toast.LENGTH_SHORT ).show ();
//        } else {
//            Builders.Any.B builder = Ion.with ( activity ).load ( "POST", addQuizQuestion ).setLogging ( "", Log.ERROR );
//            for (int i = 0; i < arrayList.size (); i++) {
//                builder.setMultipartParameter ( "host_ids[]", arrayList.get ( i ).getUser_id () );
//            }
//            builder.setHeader ( "Authorization", AccessToken );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "group_id", group_id );
//
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( activity );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestTypeAddQuizQuestion );
//                }
//            } );
//        }
//
//    }
//
//    public static void hitCreateQuizApiForAddImagesAndAnswer(final Activity activity, String question_id,
//                                                             String type, File imgFile,
//                                                             String saveQuestionAnswer,
//                                                             final int requestTypeSaveQuestionAnswer,
//                                                             final NetworkCallBack listener) {
//        sharedPreference = SharedPreference.getInstance ( activity );
//        String accessToken = sharedPreference.getString ( Constants.ACCESS_TOKEN, "" );
//        if (imgFile == null) {
//            Toast.makeText ( activity, "Please click images.", Toast.LENGTH_SHORT ).show ();
//        } else {
//            Builders.Any.B builder = Ion.with ( activity ).load ( "POST", saveQuestionAnswer ).setLogging ( "", Log.ERROR );
//
//            builder.setMultipartFile ( "thumbnail", imgFile );
//            builder.setHeader ( "Authorization", accessToken );
//            builder.setMultipartParameter ( "question_id", question_id );
//            builder.setMultipartParameter ( "type", type );
//
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( activity );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestTypeSaveQuestionAnswer );
//                }
//            } );
//        }
//
//    }
//
//    public static void hitAddOptionLayout(final Activity activity, String text, File imgFile, String option_id,
//                                          String poll_id, String poll_user_id, String getAddOrEditPollOption,
//                                          final int requestTypeGetAddOrEditPollOption, final NetworkCallBack listener) {
//        sharedPreference = SharedPreference.getInstance ( activity );
//        String accessToken = sharedPreference.getString ( Constants.ACCESS_TOKEN, "" );
//
//        Builders.Any.B builder = Ion.with ( activity ).load ( "POST", getAddOrEditPollOption ).setLogging ( "", Log.ERROR );
//        builder.setHeader ( "Authorization", accessToken );
//        if (imgFile == null) {
//            String is_thumbnail = "0";
//            if (!option_id.equals ( "" )) {
//                builder.setMultipartParameter ( "option_id", option_id );
//            }
//            if (!poll_id.equals ( "" )) {
//                builder.setMultipartParameter ( "poll_id", poll_id );
//            }
//            builder.setMultipartParameter ( "text", text );
//            builder.setMultipartParameter ( "poll_user_id", poll_user_id );
//            builder.setMultipartParameter ( "is_thumbnail", is_thumbnail );
//        } else {
//            String is_thumbnail = "1";
//            builder.setMultipartFile ( "thumbnail", imgFile );
//            if (!option_id.equals ( "" )) {
//                builder.setMultipartParameter ( "option_id", option_id );
//            }
//            if (!poll_id.equals ( "" )) {
//                builder.setMultipartParameter ( "poll_id", poll_id );
//            }
//            builder.setMultipartParameter ( "text", text );
//            builder.setMultipartParameter ( "poll_user_id", poll_user_id );
//            builder.setMultipartParameter ( "is_thumbnail", is_thumbnail );
//        }
//        builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//            @Override
//            public void onCompleted(Exception e, Response <JsonObject> result) {
//                if (e != null) {
//                    DialogUtils.alertDialog ( activity );
//                    listener.onError ( "" );
//                    return;
//                }
//                status = result.getHeaders ().code ();
//                JsonObject resultObject = result.getResult ();
//                listener.onSuccess ( resultObject, status, requestTypeGetAddOrEditPollOption );
//            }
//        } );
//    }
//
//    public static void hitForReport(final Activity activity, String question_id,String text, File imgFile,
//                                    String getAddOrEditPollOption, final int requestTypeGetAddOrEditPollOption,
//                                    final NetworkCallBack listener) {
//        sharedPreference = SharedPreference.getInstance ( activity );
//        String accessToken = sharedPreference.getString ( Constants.ACCESS_TOKEN, "" );
//
//        Builders.Any.B builder = Ion.with ( activity ).load ( "POST", getAddOrEditPollOption ).setLogging ( "", Log.ERROR );
//        builder.setHeader ( "Authorization", accessToken );
//        if (imgFile == null) {
//            builder.setMultipartParameter ( "description", text );
//            builder.setMultipartParameter ( "question_id", question_id );
//
//        } else {
//            builder.setMultipartFile ( "thumbnail", imgFile );
//            builder.setMultipartParameter ( "description", text );
//            builder.setMultipartParameter ( "question_id", question_id );
//        }
//        builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//            @Override
//            public void onCompleted(Exception e, Response <JsonObject> result) {
//                if (e != null) {
//                    DialogUtils.alertDialog ( activity );
//                    listener.onError ( "" );
//                    return;
//                }
//                status = result.getHeaders ().code ();
//                JsonObject resultObject = result.getResult ();
//                listener.onSuccess ( resultObject, status, requestTypeGetAddOrEditPollOption );
//            }
//        } );
//    }
//
//    public static void hitCreatePollApi(final Activity activity, String category_id, String title,
//                                        String media_type, File imgFile, File thumbnail, File imgFileFormVideo,
//                                        String description, String start_date_time, String end_date_time,
//                                        String gender, String ranges, String answer_type,
//                                        String is_subscriber, String language, String is_voter_location,
//                                        String is_voter_commentable, String createPoll,
//                                        final NetworkCallBack listener, final int requestTypeGetCreatePoll) {
//        sharedPreference = SharedPreference.getInstance ( activity );
//        String accessToken = sharedPreference.getString ( Constants.ACCESS_TOKEN, "" );
//        String poll_id = sharedPreference.getString ( Constants.POLL_ID, "" );
//      /*  if (thumbnail == null) {
//            Toast.makeText(activity, "Please click images.", Toast.LENGTH_SHORT).show();
//        } else {*/
//        Builders.Any.B builder = Ion.with ( activity ).load ( "POST", createPoll ).setLogging ( "", Log.ERROR );
//        if (imgFile == null && thumbnail == null) {
//            builder.setHeader ( "Authorization", accessToken );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "media_type", media_type );
//            if (!description.equals ( "" )) {
//                builder.setMultipartParameter ( "description", description );
//            }
//            if (!start_date_time.equals ( "" )) {
//                builder.setMultipartParameter ( "start_date_time", start_date_time );
//            }
//            if (!poll_id.equals ( "" )) {
//                builder.setMultipartParameter ( "poll_id", poll_id );
//            }
//            if (!end_date_time.equals ( "" )) {
//                builder.setMultipartParameter ( "end_date_time", end_date_time );
//            }
//            builder.setMultipartParameter ( "gender", gender );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "answer_type", answer_type );
//            builder.setMultipartParameter ( "is_subscriber", is_subscriber );
//            builder.setMultipartParameter ( "language", language );
//            builder.setMultipartParameter ( "is_voter_location", is_voter_location );
//
//            builder.setMultipartParameter ( "is_voter_commentable", is_voter_commentable );
//        } else if (imgFile != null) {
//            builder.setMultipartFile ( "thumbnail", imgFile );
//            builder.setHeader ( "Authorization", accessToken );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "media_type", media_type );
//            if (!description.equals ( "" )) {
//                builder.setMultipartParameter ( "description", description );
//            }
//            if (!poll_id.equals ( "" )) {
//                builder.setMultipartParameter ( "poll_id", poll_id );
//            }
//            if (!start_date_time.equals ( "" )) {
//                builder.setMultipartParameter ( "start_date_time", start_date_time );
//            }
//            if (!end_date_time.equals ( "" )) {
//                builder.setMultipartParameter ( "end_date_time", end_date_time );
//            }
//            builder.setMultipartParameter ( "gender", gender );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "answer_type", answer_type );
//            builder.setMultipartParameter ( "is_subscriber", is_subscriber );
//            builder.setMultipartParameter ( "language", language );
//            builder.setMultipartParameter ( "is_voter_location", is_voter_location );
//            builder.setMultipartParameter ( "is_voter_commentable", is_voter_commentable );
//        } else if (thumbnail != null) {
//            builder.setMultipartFile ( "thumbnail", thumbnail );
//            builder.setHeader ( "Authorization", accessToken );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "media_type", media_type );
//            if (!description.equals ( "" )) {
//                builder.setMultipartParameter ( "description", description );
//            }
//            if (!imgFileFormVideo.equals ( "" )) {
//                builder.setMultipartFile ( "video", imgFileFormVideo );
//            }
//            if (!poll_id.equals ( "" )) {
//                builder.setMultipartParameter ( "poll_id", poll_id );
//            }
//            if (!start_date_time.equals ( "" )) {
//                builder.setMultipartParameter ( "start_date_time", start_date_time );
//            }
//            if (!end_date_time.equals ( "" )) {
//                builder.setMultipartParameter ( "end_date_time", end_date_time );
//            }
//            builder.setMultipartParameter ( "gender", gender );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "answer_type", answer_type );
//            builder.setMultipartParameter ( "is_subscriber", is_subscriber );
//            builder.setMultipartParameter ( "language", language );
//            builder.setMultipartParameter ( "is_voter_location", is_voter_location );
//            builder.setMultipartParameter ( "is_voter_commentable", is_voter_commentable );
//        }
//
//        builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//            @Override
//            public void onCompleted(Exception e, Response <JsonObject> result) {
//                if (e != null) {
//                    DialogUtils.alertDialog ( activity );
//                    listener.onError ( "" );
//                    return;
//                }
//                status = result.getHeaders ().code ();
//                JsonObject resultObject = result.getResult ();
//                listener.onSuccess ( resultObject, status, requestTypeGetCreatePoll );
//            }
//        } );
////        }
//
//    }
//
//    public static void hitUpdateProfile(final Activity activity, String names,
//                                        String mobile, String dobs, String maleOrFemale, File imgFile,
//                                        String saveQuestionAnswer,
//                                        final int editProfileApi,
//                                        final NetworkCallBack listener) {
//        sharedPreference = SharedPreference.getInstance ( activity );
//        String accessToken = sharedPreference.getString ( Constants.ACCESS_TOKEN, "" );
////        if (imgFile == null) {
////            Toast.makeText(activity, "Please click images.", Toast.LENGTH_SHORT).show();
////        } else {
//        Builders.Any.B builder = Ion.with ( activity ).load ( "POST", saveQuestionAnswer ).setLogging ( "", Log.ERROR );
//        builder.setHeader ( "Authorization", accessToken );
//        if (imgFile == null) {
//
//            if (!names.equals ( "" )) {
//                builder.setMultipartParameter ( "name", names );
//            }
//            if (!mobile.equals ( "" )) {
//                builder.setMultipartParameter ( "phone", mobile );
//            }
//            if (!dobs.equals ( "" )) {
//                builder.setMultipartParameter ( "dob", dobs );
//            }
//            if (!maleOrFemale.equals ( "" )) {
//                builder.setMultipartParameter ( "gender", maleOrFemale );
//            }
//
//        } else {
//            builder.setMultipartFile ( "profile_pic", imgFile );
//            if (!names.equals ( "" )) {
//                builder.setMultipartParameter ( "name", names );
//            }
//            if (!mobile.equals ( "" )) {
//                builder.setMultipartParameter ( "phone", mobile );
//            } else if (!dobs.equals ( "" )) {
//                builder.setMultipartParameter ( "dob", dobs );
//            }
//            if (!maleOrFemale.equals ( "" )) {
//                builder.setMultipartParameter ( "gender", maleOrFemale );
//            }
//        }
////            builder.setMultipartFile("profile_pic", imgFile);
////            builder.setHeader("Authorization", accessToken);
//
//        builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//            @Override
//            public void onCompleted(Exception e, Response <JsonObject> result) {
//                if (e != null) {
//                    DialogUtils.alertDialog ( activity );
//                    listener.onError ( "" );
//                    return;
//                }
//                status = result.getHeaders ().code ();
//                JsonObject resultObject = result.getResult ();
//                listener.onSuccess ( resultObject, status, editProfileApi );
//            }
//        } );
////        }
//
//    }
//
//    public static void hitCreateQuizApiForStandardForGroupQuiz(final Context context, String userId,
//                                                               String quizType, String type,
//                                                               String category_id, String title,
//                                                               String genders, String ranges,
//                                                               String is_subscribers,
//                                                               String end_date_time,
//                                                               String max_play_limit, String max_time,
//                                                               String description, JsonArray tagArray,
//                                                               String start_date_time, String languages,
//                                                               String media_types, String score_per_ques,
//                                                               File imgFile, File imgFileFormVideo,
//                                                               File thumbnail, String group_id,
//                                                               String minimum_age, String maxiumu_age,
//                                                               String quiz_id, String percentage_required,
//                                                               final NetworkCallBack listener, final String networkUrl,
//                                                               final int requestCode) {
//        sharedPreference = SharedPreference.getInstance ( context );
//        String accessToken = sharedPreference.getString ( Constants.ACCESS_TOKEN, "" );
//        if (imgFile == null && thumbnail == null) {
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToken );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", quizType );
//            builder.setMultipartParameter ( "min_age", minimum_age );
//            builder.setMultipartParameter ( "max_age", maxiumu_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "group_id", group_id );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_time );
//            builder.setMultipartParameter ( "end_date_time", end_date_time );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", "0" );
//            if (!quiz_id.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", quiz_id );
//            }
//            builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//            builder.setMultipartParameter ( "percentage_required", /*"1"*/ percentage_required );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//
////            Toast.makeText(context, "Please capture image/Video.", Toast.LENGTH_SHORT).show();
//        } else if (imgFile != null) {
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToken );
//            builder.setMultipartFile ( "thumbnail", imgFile );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", quizType );
//            builder.setMultipartParameter ( "min_age", minimum_age );
//            builder.setMultipartParameter ( "max_age", maxiumu_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "group_id", group_id );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", /*"1"*/ max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_time );
//            builder.setMultipartParameter ( "end_date_time", end_date_time );
//            builder.setMultipartParameter ( "language", languages );
//            builder.setMultipartParameter ( "media_type", media_types );
//            if (!quiz_id.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", quiz_id );
//            }
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//            builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else if (thumbnail != null) {
//
//            Builders.Any.B builder = Ion.with ( context ).load ( "POST", networkUrl ).setLogging ( "", Log.ERROR );
//
//            for (int i = 0; i < tagArray.size (); i++) {
//                builder.setMultipartParameter ( "tag[]", tagArray.get ( i ).getAsString () );
//            }
//            builder.setHeader ( "Authorization", accessToken );
//            builder.setMultipartFile ( "thumbnail", thumbnail );
//            builder.setMultipartFile ( "video", imgFileFormVideo );
//            builder.setMultipartParameter ( "user_id", userId );
//            builder.setMultipartParameter ( "quiz_type", quizType );
//            builder.setMultipartParameter ( "min_age", minimum_age );
//            builder.setMultipartParameter ( "max_age", maxiumu_age );
//            builder.setMultipartParameter ( "type", type );
//            builder.setMultipartParameter ( "category_id", category_id );
//            builder.setMultipartParameter ( "title", title );
//            builder.setMultipartParameter ( "description", description );
//            builder.setMultipartParameter ( "gender", genders );
//            builder.setMultipartParameter ( "range", ranges );
//            builder.setMultipartParameter ( "is_subscriber", is_subscribers );
//            builder.setMultipartParameter ( "max_play_limit", max_play_limit );
//            builder.setMultipartParameter ( "max_time", /*"1" */max_time );
//            builder.setMultipartParameter ( "start_date_time", start_date_time );
//            builder.setMultipartParameter ( "end_date_time", end_date_time );
//            builder.setMultipartParameter ( "language", languages );
//            if (!quiz_id.equals ( "0" )) {
//                builder.setMultipartParameter ( "quiz_id", quiz_id );
//            }
//            builder.setMultipartParameter ( "group_id", group_id );
//            builder.setMultipartParameter ( "media_type", media_types );
//            builder.setMultipartParameter ( "percentage_required", percentage_required );
//            builder.setMultipartParameter ( "score_per_ques", /*"1"*/ score_per_ques );
//            builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//                @Override
//                public void onCompleted(Exception e, Response <JsonObject> result) {
//                    if (e != null) {
//                        DialogUtils.alertDialog ( context );
//                        listener.onError ( "" );
//                        return;
//                    }
//                    status = result.getHeaders ().code ();
//                    JsonObject resultObject = result.getResult ();
//                    listener.onSuccess ( resultObject, status, requestCode );
//                }
//            } );
//        } else {
//
//        }
//    }
//
//    public static void getNetworkResponseWithAuthFromGet(final Context context, String accessToken, JsonObject jsonObject,
//                                                         int timeout, final String url, final NetworkCallback callback,
//                                                         final int requestCode) {
//        Ion.with ( context ).load ( url ).setHeader ( "Authorization", accessToken )
//                .asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//            @Override
//            public void onCompleted(Exception e, Response <JsonObject> result) {
//                if (e != null) {
//                    DialogUtils.alertDialog ( context );
//                    callback.onNetworkTimeout ( "" );
//                    return;
//                }
//                status = result.getHeaders ().code ();
//                JsonObject resultObject = result.getResult ();
//                callback.onNetworkSuccess ( resultObject, status, requestCode );
//
//            }
//        } );
//
//    }
//
//    public static void getNetworkResponseWithAuth(final Context context, String accessToken,
//                                                  JsonObject jsonObject,
//                                                  int timeout, final String url,
//                                                  final NetworkCallback callback,
//                                                  final int requestCode) {
//        Builders.Any.B ion = getIon ( context, url, timeout );
//        ion.addHeader ( "Authorization", accessToken ).setJsonObjectBody ( jsonObject )
//                .asJsonObject ().withResponse ()
//                .setCallback ( new FutureCallback <Response <JsonObject>> () {
//                    @Override
//                    public void onCompleted(Exception e, Response <JsonObject> result) {
//                        if (e != null) {
//                            DialogUtils.alertDialog ( context );
//                            callback.onNetworkTimeout ( "" );
//                            return;
//                        }
//                        status = result.getHeaders ().code ();
//                        JsonObject resultObject = result.getResult ();
//                        callback.onNetworkSuccess ( resultObject, status, requestCode );
//                    }
//                } );
//    }
//
//    public static void hitVotePollAPI(final Context activity, String is_changed, String poll_id, String user_id, String anonymous,
//                                      ArrayList <String> stringArrayList, String location,
//                                      int timeout, String url,
//                                      final NetworkCallBack listener,
//                                      final int requestCode) {
//        SharedPreference sharedPreference = SharedPreference.getInstance ( activity );
//
//        Builders.Any.B builder = Ion.with ( activity ).load ( "POST", url ).setLogging ( "", Log.ERROR );
//        builder.setHeader ( "Authorization", sharedPreference.getString ( Constants.ACCESS_TOKEN, "" ) );
//        builder.setMultipartParameter ( "poll_id", poll_id );
//        builder.setMultipartParameter ( "user_id", user_id );
//        builder.setMultipartParameter ( "is_change", is_changed );
//        builder.setMultipartParameter ( "is_anonymous", anonymous );
//        if (location.equals ( "" )) {
//        } else {
//            builder.setMultipartParameter ( "location", location );
//        }
//        for (int i = 0; i < stringArrayList.size (); i++) {
//            builder.setMultipartParameter ( "option_ids[]", stringArrayList.get ( i ) );
//        }
//        builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//            @Override
//            public void onCompleted(Exception e, Response <JsonObject> result) {
//                if (e != null) {
//                    DialogUtils.alertDialog ( activity );
//                    listener.onError ( "" );
//                    return;
//                }
//                status = result.getHeaders ().code ();
//                JsonObject resultObject = result.getResult ();
//                listener.onSuccess ( resultObject, status, requestCode );
//            }
//        } );
//    }
//
//    public static void getNetworkResponseWithAuthNewFundraise(final Activity activity, String access_token,
//                                                              String category_id, String title, String description,
//                                                              String target_amount, String selected_currency,
//                                                              String city, JsonArray media, String number,
//                                                              String language, String thank_you_msg,
//                                                              ArrayList <CreateFundModal> createFundModalArrayList,
//                                                              String start_date_time, String end_date_time,
//                                                              String campaign_id,
//                                                              List <String> removed_media, int timeout, String getCreateCampaign,
//                                                              final NetworkCallBack listener,
//                                                              final int requestCode) {
//        SharedPreference sharedPreference = SharedPreference.getInstance ( activity );
//        Builders.Any.B builder = Ion.with ( activity ).load ( "POST", getCreateCampaign ).setLogging ( "", Log.ERROR );
//        builder.setHeader ( "Authorization", sharedPreference.getString ( Constants.ACCESS_TOKEN, "" ) );
//        builder.setMultipartParameter ( "category_id", category_id );
//        builder.setMultipartParameter ( "user_id", sharedPreference.getString ( Constants.USER_ID, "" ) );
//        builder.setMultipartParameter ( "title", title );
//        if (!description.equals ( "" )) {
//            builder.setMultipartParameter ( "description", description );
//        }
//        if (removed_media.size () > 0) {
//            for (int i = 0; i < removed_media.size (); i++) {
//                builder.setMultipartParameter ( "removed_media[]", removed_media.get ( i ).toString () );
//            }
//        }
//        if (!campaign_id.equals ( "" )) {
//            builder.setMultipartParameter ( "campaign_id", campaign_id );
//        }
//        builder.setMultipartParameter ( "target_amount", target_amount );
//        builder.setMultipartParameter ( "currency", selected_currency );
//        builder.setMultipartParameter ( "start_date", start_date_time );
//        builder.setMultipartParameter ( "end_date", end_date_time );
//        builder.setMultipartParameter ( "language", language );
//        builder.setMultipartParameter ( "city", city );
//        if (!media.equals ( "{}" )) {
//            for (int i = 0; i < media.size (); i++) {
//                /*if (media.get(i).c) {
//                } else {*/
//                if (!media.get ( i ).toString ().equals ( "{}" )) {
//                    builder.setMultipartParameter ( "media[]", media.get ( i ).toString () );
//
//                }
////                }
//
//            }
//        }
//
//        if (!number.equals ( "" )) {
//            builder.setMultipartParameter ( "phone", number );
//        }
//
//        builder.setMultipartParameter ( "language", language );
//        if (!thank_you_msg.equals ( "" )) {
//            builder.setMultipartParameter ( "thanking_msg", thank_you_msg );
//        }
//        if (createFundModalArrayList.size () > 0) {
//            for (int i = 0; i < createFundModalArrayList.size (); i++) {
//                if (createFundModalArrayList.get ( i ).getVideo () == null) {
//                    if (createFundModalArrayList.get ( i ).getThumbnail () != null) {
//                        builder.setMultipartFile ( "files[]", createFundModalArrayList.get ( i ).getThumbnail () );
//                    }
//
//                } else {
//                    builder.setMultipartFile ( "files[]", createFundModalArrayList.get ( i ).getVideo () );
//                    builder.setMultipartFile ( "files[]", createFundModalArrayList.get ( i ).getThumbnail () );
//                }
//
//            }
//        }
//
//
//        /*void writeFile(String fileName, String data) {
//            File outFile = new File(Environment.getExternalStorageDirectory(), fileName);
//            FileOutputStream out = new FileOutputStream(outFile, false);
//            byte[] contents = data.getBytes();
//            out.write(contents);
//            out.flush();
//            out.close();
//        }
//*/
//        builder.asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//            @Override
//            public void onCompleted(Exception e, Response <JsonObject> result) {
//                if (e != null) {
//                    DialogUtils.alertDialog ( activity );
//                    listener.onError ( "" );
//                    return;
//                }
//                status = result.getHeaders ().code ();
//                JsonObject resultObject = result.getResult ();
//                listener.onSuccess ( resultObject, status, requestCode );
//            }
//        } );
//    }
//
//    public static void hitSubmitAnswerAPI(final Context context, String quiz_id,
//                                          String attempted_question, String correct_question,
//                                          String skipped_question, String incorrect_question,
//                                          String pending_question, String percentage_scorred,
//                                          String player_latitude, String player_longitude,
//                                          String time_spent, String statuss, JsonArray jsonArray,
//                                          int timeout, String url,
//                                          final NetworkCallBack listener,
//                                          final int requestCode) {
//
//        String accessToke = new SharedPreference ( context ).getString ( Constants.ACCESS_TOKEN, "" );
//        Builders.Any.B builder = Ion.with ( context ).load ( "POST", url ).setLogging ( "", Log.ERROR );
//        builder.addHeader ( "Authorization", accessToke ).setJsonObjectBody ( getJsonObject ( quiz_id,
//                attempted_question, correct_question, skipped_question, incorrect_question, pending_question
//                , percentage_scorred, player_latitude, player_longitude, time_spent, statuss, jsonArray ) )
//                .asJsonObject ().withResponse ().setCallback ( new FutureCallback <Response <JsonObject>> () {
//            @Override
//            public void onCompleted(Exception e, Response <JsonObject> result) {
//
//                if (e != null) {
//                    DialogUtils.alertDialog ( context );
//                    listener.onError ( "" );
//                    return;
//                }
//                status = result.getHeaders ().code ();
//                JsonObject resultObject = result.getResult ();
//                listener.onSuccess ( resultObject, status, requestCode );
//            }
//        } );
//    }
//
//    private static JsonObject getJsonObject(String quiz_id, String attempted_question, String correct_question,
//                                            String skipped_question, String incorrect_question, String pending_question,
//                                            String percentage_scorred, String player_latitude, String player_longitude,
//                                            String time_spent, String statuss, JsonArray jsonArray) {
//
//        JsonObject jsonObject = new JsonObject ();
//        JsonArray jsonArray1 = new JsonArray ();
//        JsonObject jsonObject1 = new JsonObject ();
//        for (int i = 0; i < jsonArray.size (); i++) {
//            jsonObject1 = new JsonObject ();
//            jsonObject = jsonArray.get ( i ).getAsJsonObject ();
//            jsonObject1.addProperty ( "question_id", jsonObject.get ( "question_id" ).getAsString () );
//
//            jsonObject1.addProperty ( "answer_id", jsonObject.get ( "answer_id" ).getAsString () );
//
//            jsonObject1.addProperty ( "is_correct", jsonObject.get ( "is_correct" ).getAsString () );
//            jsonObject1.addProperty ( "attempt_status", jsonObject.get ( "attempt_status" ).getAsString () );
//            jsonObject1.addProperty ( "question", jsonObject.get ( "question" ).getAsString () );
//            jsonObject1.addProperty ( "correct_answer_id", jsonObject.get ( "correct_answer_id" ).getAsString () );
//            jsonArray1.add ( jsonObject1 );
//        }
//
////        /////////////////////
//        jsonObject.addProperty ( "quiz_id", quiz_id );
//        jsonObject.addProperty ( "no_of_ques_attempted", attempted_question );
//        jsonObject.addProperty ( "correct_questions", String.valueOf ( correct_question ) );
//        jsonObject.addProperty ( "questions_skipped", skipped_question );
//        jsonObject.addProperty ( "incorrect_questions", String.valueOf ( incorrect_question ) );
//        jsonObject.addProperty ( "pending_questions", pending_question );
//        jsonObject.addProperty ( "percentage_scored", percentage_scorred );
//        jsonObject.addProperty ( "player_latitude", player_latitude );
//        jsonObject.addProperty ( "player_longitude", player_longitude );
//        jsonObject.addProperty ( "time_spent", time_spent );
//        jsonObject.addProperty ( "status", statuss );
////        jsonObject.add ( "answers", jsonArray1 );
//        return jsonObject;
//    }
//
//    public void getNetworkResponseGet(final Context context, String header, int timeout) {
//        Builders.Any.B ion = getIon ( context, url, timeout );
//        ion.addHeader ( "accessToken", header )
//                .asJsonObject ().withResponse ()
//                .setCallback ( new FutureCallback <Response <JsonObject>> () {
//                    @Override
//                    public void onCompleted(Exception e, Response <JsonObject> result) {
//                        if (e != null) {
////                            DialogUtils.alertDialog ( context );
//                            callback.onNetworkTimeout ( "" );
//                            return;
//                        }
//                        status = result.getHeaders ().code ();
//                        JsonObject resultObject = result.getResult ();
//                        callback.onNetworkSuccess ( resultObject, status, 1 );
//
//                    }
//                } );
//    }
//
//    public void getNetworkResponseForm(final Context context, String header, JsonObject jsonObject, int timeout) {
//        Builders.Any.B ion = getIon ( context, url, timeout );
//        ion.addHeader ( "Content-Type", "form-data" ).addHeader ( "accessToken", header ).setJsonObjectBody ( jsonObject )
//                .asJsonObject ().withResponse ()
//                .setCallback ( new FutureCallback<Response <JsonObject>>() {
//                    @Override
//                    public void onCompleted(Exception e, Response<JsonObject> result) {
//                        if (e != null) {
////                            DialogUtils.alertDialog ( context );
//                            callback.onNetworkTimeout ( "" );
//                            return;
//                        }
//                        status = result.getHeaders ().code ();
//                        JsonObject resultObject = result.getResult ();
//                        callback.onNetworkSuccess ( resultObject, status, 1 );
//
//                    }
//                } );
//    }
//
//    public void getNetworkResponseForImage(final Context context, int timeout) {
//        Builders.Any.B ion = getIon ( context, url, timeout );
//        ion.asJsonObject ().withResponse ()
//                .setCallback ( new FutureCallback <Response <JsonObject>> () {
//                    @Override
//                    public void onCompleted(Exception e, Response <JsonObject> result) {
//                        if (e != null) {
////                            DialogUtils.alertDialog ( context );
//                            callback.onNetworkTimeout ( "" );
//                            return;
//                        }
//                        status = result.getHeaders ().code ();
//                        JsonObject resultObject = result.getResult ();
//                        callback.onNetworkSuccess ( resultObject, status, 1 );
//                    }
//
//                } );
//    }
}
