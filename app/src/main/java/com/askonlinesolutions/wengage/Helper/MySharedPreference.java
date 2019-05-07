package com.askonlinesolutions.wengage.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.askonlinesolutions.wengage.utils.Constant;

public class MySharedPreference {

    private static MySharedPreference instance;
    private transient SharedPreferences prefs;

    private MySharedPreference(Context context) {
        prefs = context.getSharedPreferences(context.getPackageName(),
                Context.MODE_PRIVATE);
    }

    public static MySharedPreference getInstance(Context context) {
        if (instance == null)
            instance = new MySharedPreference(context);
        return instance;
    }

    public boolean isLogin(){

        if(prefs.contains(Constant.LOGIN_DATA)){
            return true;
        } else {
            return false;
        }
    }
    public void saveUserData(String login_data) {

        prefs.edit().putString(Constant.LOGIN_DATA, login_data).commit();
    }
    public String getUserData(){

        return prefs.getString(Constant.LOGIN_DATA, "");
    }

    public void clearUserData(){
        prefs.edit().remove(Constant.LOGIN_DATA);
    }

    public void saveNote(String str){
        prefs.edit().putString(Constant.ADD_NOTE, str).commit();
    }

    public String getNote(){
        return prefs.getString(Constant.ADD_NOTE, "");
    }

    public void clearNote(){
        prefs.edit().remove(Constant.ADD_NOTE);
    }

    public void saveDiscount(String str){
        prefs.edit().putString(Constant.ADD_DISCOUNT, str).commit();
    }

    public String getDiscount(){
        return prefs.getString(Constant.ADD_DISCOUNT, "");
    }

    public void clearDiscount(){
        prefs.edit().remove(Constant.ADD_DISCOUNT);
    }
}