package com.askonlinesolutions.wengage.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;


import com.askonlinesolutions.wengage.R;

import java.util.regex.Pattern;
public class Utility {

    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Context context){
        try {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(context, R.style.MyProgressDialogStyle);
            }
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            progressDialog.setMessage("Please wait...");
        } catch (Exception e) {
            Log.e("MyException", e.getMessage() );
        }
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

    public static Dialog createErrorDialog(Context context,Dialog dialog, String msg){
        if (dialog==null){
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.error_alert_dialog);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                final TextView txtOk = dialog.findViewById(R.id.dialog_error_ok);
                final TextView txtMsg = dialog.findViewById(R.id.dialog_error_msg);
                final Dialog finalDialog = dialog;
                txtOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finalDialog.dismiss();
                    }
                });
                txtMsg.setText(msg);
            } else {
                final TextView txtOk = dialog.findViewById(R.id.dialog_error_ok);
                final TextView txtMsg = dialog.findViewById(R.id.dialog_error_msg);
                final Dialog finalDialog = dialog;
                txtOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finalDialog.dismiss();
                    }
                });
                txtMsg.setText(msg);
        }


        return dialog;
    }

    public static void hideDialog(){
        if(progressDialog != null)
            progressDialog.dismiss();
    }

    private static Toast t;
    public static void showToast(Context context, String msg){
        if(t != null)
            t.cancel();
        t = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        t.show();
    }

    public static Boolean checkemail(final String email) {
        if(email!=null)
        {
            Pattern pattern = Patterns.EMAIL_ADDRESS;
            if(pattern.matcher(email).matches())
            {
                return pattern.matcher(email).matches();
            }
        }
        return false;
    }


    public static boolean isValidMobile(String phone) {
        if(phone!=null)
        {
            Pattern pattern = Patterns.PHONE;
            if(pattern.matcher(phone).matches())
            {
                return pattern.matcher(phone).matches();
            }
        }
        return false;
    }

    public static Boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnectedOrConnecting())
            return true;

        return false;
    }

    public static void callFragment(Fragment fragment, String tag, FragmentManager manager  ){

        if(manager.getBackStackEntryCount() != 0) {
            String tag1 = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1).getName();
            if (tag1.equals(tag)) {

            } else {
                if (manager.findFragmentByTag(tag) == null) { //fragment not in back stack, create it.
//                    manager.beginTransaction().replace(R.id.frame, fragment).addToBackStack(tag).commit();
                } else {
//                    manager.beginTransaction().replace(R.id.frame, fragment).commit();
                    //              manager.executePendingTransactions();
                }
            }
        } else{
//            manager.beginTransaction().replace(R.id.frame, fragment).addToBackStack(tag).commit();
        }
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus()!=null)
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void hideSoftKeyDialog(Activity activity, View view){
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
