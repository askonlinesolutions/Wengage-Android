package com.askonlinesolutions.wengage.Helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Fragment.Main.venue.VenueHomeFragment;
import com.askonlinesolutions.wengage.R;

import java.io.ByteArrayOutputStream;

//a

/**
 * Created by user on 6/2/2017.
 */

public class BaseClass {

    Context context;

    public BaseClass(Context c) {
        context = c;
    }

    public void callFragment(Fragment fragment, String tag, FragmentManager manager) {


        if (tag.equals("home")) {
            manager.beginTransaction().replace(R.id.frame, fragment)/*.addToBackStack("home")*/.commit();
        } else {
            if (manager.findFragmentByTag(tag) == null) { //fragment not in back stack, create it.

                manager.beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter,
                        R.animator.fade_out,
                        0,
                        R.animator.fragment_slide_right_exit)
                        .replace(R.id.frame, fragment).addToBackStack(tag).commit();
            } else {
                manager.beginTransaction().replace(R.id.frame, fragment).commit();
            }
        }
    }

    public void callFragment1(Fragment fragment, String tag, FragmentManager manager) {


        if (tag.equals(new VenueHomeFragment().getClass().getName())) {
            manager.beginTransaction().replace(R.id.frame, fragment)/*.addToBackStack("home")*/.commit();
        } else {
            if (manager.findFragmentByTag(tag) == null) { //fragment not in back stack, create it.

                manager.beginTransaction().setCustomAnimations(R.animator.slide_up, R.animator.fade_out,
                        0, R.animator.slide_down)
                        .replace(R.id.frame, fragment).addToBackStack(tag).commit();
            } else {
                manager.beginTransaction().replace(R.id.frame, fragment).commit();
            }
        }
    }

    public void showWengageDialog(String message) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle("Wengage")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                // .setIcon(a
                // ndroid.R.drawable.ic_dialog_alert)
                .show();
    }


    public static void setLightStatusBar(View view, Activity activity) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    public static Boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting())
            return true;

        return false;
    }

    private static Toast t = null;

    public void showToast(String msg) {
        if (t != null)
            t.cancel();
        t = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        t.show();
    }

    public void showToast(Context c, String msg) {

    }

    public ProgressDialog creatProgressDialog() {
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        return progressDialog;

    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }


    public static String BitMapToString(Bitmap bitmap) {
        String temp = "";
        if (bitmap != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            temp = Base64.encodeToString(b, Base64.DEFAULT);
        }
        return temp;
    }


/*
    public void recentApps(){
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.proicon);

        ActivityManager.TaskDescription taskDescription = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            taskDescription = new ActivityManager.TaskDescription("ProLifeAyurveda", icon, context.getResources().getColor(R.color.ToolbarBackgroundColor));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((Activity)context).setTaskDescription(taskDescription);
        }
    }
*/

    public String replaceSpace(String str) {
        String temp = str.replaceAll(" ", "%20");
        return temp;
    }
}