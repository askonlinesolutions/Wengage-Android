package com.askonlinesolutions.wengage.utils;

/*
 * Created by Sandeep Kushwah on 29-10-2016.
 */

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import static com.askonlinesolutions.wengage.utils.Image.getDataColumn;
import static com.askonlinesolutions.wengage.utils.Image.isDownloadsDocument;
import static com.askonlinesolutions.wengage.utils.Image.isExternalStorageDocument;
import static com.askonlinesolutions.wengage.utils.Image.isMediaDocument;

public class Helper {
    /**
     * this methods checks whether device is connected to internet or not
     *
     * @param ctx #Context
     * @return true if device is connected to internet else false
     */
    public static boolean isConnected(Context ctx) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) ctx.getSystemService ( Context.CONNECTIVITY_SERVICE );
        NetworkInfo ni = connectivityManager.getActiveNetworkInfo ();
        return ni != null && ni.isAvailable () && ni.isConnected ();
    }

    /**
     * Hide the keyboard if opened
     *
     * @param ctx #Context
     */
    public static void hideSoftKeyBoard(Activity ctx) {
        View focusedView = ctx.getCurrentFocus ();
        if (focusedView != null) {
            InputMethodManager imm = (InputMethodManager) ctx.getSystemService ( Context.INPUT_METHOD_SERVICE );
            imm.hideSoftInputFromWindow ( focusedView.getWindowToken (), InputMethodManager.HIDE_NOT_ALWAYS );
        }
    }

    public static void toast(Context context, String message) {
        Toast.makeText ( context, message, Toast.LENGTH_SHORT ).show ();
    }

    public static void snakeBar(Context context, View view, String message) {
        Toast.makeText ( context, message, Toast.LENGTH_SHORT ).show ();
        /*Snackbar snackbar = Snackbar
                .make ( view, message, Snackbar.LENGTH_LONG );
        View sbView = snackbar.getView ();
        sbView.setBackgroundColor ( ContextCompat.getColor ( context, R.color.blue ) );
        TextView textView =sbView.findViewById ( android.support.design.R.id.snackbar_text );
        textView.setTextColor ( ContextCompat.getColor(context, R.color.white));
        snackbar.show ();*/
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

/*

    public static void setProfilePic(Context context, final String pic_url, final ImageView img) {
        Ion.with(context).load(pic_url).asBitmap().setCallback((e, bitmap) -> {
            if (e == null) {
                img.setImageBitmap(bitmap);
            } else {
                e.printStackTrace();
            }
        });
    }

    public static String returnEmptyString(JsonElement s) {
        if (s.isJsonNull()) {
            return "";
        } else {
            return s.getAsString();
        }
    }

    public static int returnDefaultInteger(JsonElement s) {
        if (s.isJsonNull()) {
            return 0;
        } else {
            return s.getAsInt();
        }

    }

    public static void showSessionDialog(final Activity activity) {
        String alert = activity.getResources().getString(R.string.alert);
        final Dialog otpSendDialog = new Dialog(activity);
        otpSendDialog.setContentView(R.layout.mail_otp_verificaiton_dialog);
        otpSendDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        otpSendDialog.setTitle(alert);
        otpSendDialog.setCancelable(false);
        TextView tv_dissmiss = otpSendDialog.findViewById(R.id.tv_dissmiss);
        tv_dissmiss.setOnClickListener(v -> {
            otpSendDialog.dismiss();
            logoutUser(activity);
        });
        otpSendDialog.show();
    }

    private static void logoutUser(Activity activity) {
        cancelPreviousAlarms(activity);
        SharedPreference preference = SharedPreference.getInstance(activity);
        preference.deletePreference();
        activity.startActivity(new Intent(activity, LoginActivity.class));
        activity.finishAffinity();
    }

    public static void getLocationName(double lat, double lng, Context activity) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(activity, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            SharedPreference sharedPreference = SharedPreference.getInstance(activity);
            sharedPreference.putString(Constant.CITY, city);
            sharedPreference.putString(Constant.ADDRESSS, String.valueOf(address));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static String returnSimpleString(String date, String dateFormat) {
        String simpleDateFormat = "dd/MM/yyyy hh:mm a";
        try {
            TimeZone timeZone = TimeZone.getDefault();
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            sdf.setTimeZone(timeZone);
            Date d = sdf.parse(date);
            String simpleDate = new SimpleDateFormat(simpleDateFormat).format(d);
            return simpleDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return "null";
        }
    }

    public static String returnEventDate(String date, String dateFormat) {
        String simpleDateFormat = "yyyy-MM-dd";
        try {
            TimeZone timeZone = TimeZone.getDefault();
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            sdf.setTimeZone(timeZone);
            Date d = sdf.parse(date);
            String simpleDate = new SimpleDateFormat(simpleDateFormat).format(d);
            return simpleDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return "null";
        }
    }

    public static String returnSimpleDate(String date, String dateFormat) {
        String simpleDateFormat = "dd/MM/yyyy";
        try {
            TimeZone timeZone = TimeZone.getDefault();
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            sdf.setTimeZone(timeZone);
            Date d = sdf.parse(date);
            String simpleDate = new SimpleDateFormat(simpleDateFormat).format(d);
            return simpleDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return "null";
        }
    }

    public static void inviteFriends(Activity activity, String referalCode) {
        //String shareBody = "https://play.google.com/store/apps/details?id=com.ican.quiz";
        String shareBody = "I'm giving you referral points.To accept,use code : " + referalCode;
        Intent sharingIntent = new Intent( Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra( Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra( Intent.EXTRA_TEXT, shareBody);
        activity.startActivity(Intent.createChooser(sharingIntent, "Share Using"));
    }

    public static ArrayList<Integer> getColorList(Activity activity, int size) {
        ArrayList<Integer> colorList = new ArrayList<>();
        switch (size) {
            case 1:
                colorList.add( ContextCompat.getColor(activity, R.color.pie_1));
                break;
            case 2:
                colorList.add( ContextCompat.getColor(activity, R.color.pie_1));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_2));
                break;
            case 3:
                colorList.add( ContextCompat.getColor(activity, R.color.pie_1));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_2));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_3));
                break;
            case 4:
                colorList.add( ContextCompat.getColor(activity, R.color.pie_1));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_2));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_3));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_4));
                break;
            case 5:
                colorList.add( ContextCompat.getColor(activity, R.color.pie_1));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_2));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_3));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_4));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_5));
                break;
            case 6:
                colorList.add( ContextCompat.getColor(activity, R.color.pie_1));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_2));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_3));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_4));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_5));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_8));
                break;
            case 7:
                colorList.add( ContextCompat.getColor(activity, R.color.pie_1));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_2));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_3));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_4));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_5));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_6));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_7));
                break;
            case 8:
                colorList.add( ContextCompat.getColor(activity, R.color.pie_1));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_2));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_3));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_4));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_5));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_6));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_7));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_8));
                break;
            case 9:
                colorList.add( ContextCompat.getColor(activity, R.color.pie_1));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_2));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_3));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_4));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_5));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_6));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_7));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_8));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_9));
                break;
            case 10:
                colorList.add( ContextCompat.getColor(activity, R.color.pie_1));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_2));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_3));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_4));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_5));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_6));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_7));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_8));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_9));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_10));
                break;
            case 11:
                colorList.add( ContextCompat.getColor(activity, R.color.pie_1));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_2));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_3));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_4));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_5));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_6));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_7));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_8));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_9));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_10));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_11));
                break;
            case 12:
                colorList.add( ContextCompat.getColor(activity, R.color.pie_1));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_2));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_3));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_4));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_5));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_6));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_7));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_8));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_9));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_10));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_11));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_12));
                break;
            case 13:
                colorList.add( ContextCompat.getColor(activity, R.color.pie_1));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_2));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_3));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_4));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_5));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_6));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_7));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_8));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_9));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_10));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_11));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_12));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_13));
                break;
            case 14:
                colorList.add( ContextCompat.getColor(activity, R.color.pie_1));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_2));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_3));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_4));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_5));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_6));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_7));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_8));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_9));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_10));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_11));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_12));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_13));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_14));
                break;
            case 15:
                colorList.add( ContextCompat.getColor(activity, R.color.pie_1));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_2));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_3));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_4));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_5));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_6));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_7));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_8));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_9));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_10));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_11));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_12));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_13));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_14));
                colorList.add( ContextCompat.getColor(activity, R.color.pie_15));
                break;

        }
        return colorList;
    }

    public static String getTestResultText(int marksObtained) {
        if (marksObtained >= 0 && marksObtained <= 33) {
            return "Oops ! Work Hard";
        } else if (marksObtained > 33 && marksObtained <= 60) {
            return "Need Improvement";
        } else if (marksObtained > 60 && marksObtained <= 80) {
            return "Well done";
        } else if (marksObtained > 80 && marksObtained <= 95) {
            return "Excellent";
        } else if (marksObtained > 95 && marksObtained <= 100) {
            return "Outstanding";
        } else {
            return "Oops ! Work Hard";
        }
    }

    public static void setImageForWellDone(int marksObtained, ImageView iv_well_done, Activity activity) {
        if (marksObtained >= 0 && marksObtained <= 33) {
            iv_well_done.setImageDrawable(activity.getResources().getDrawable(R.mipmap.work_hard));
        } else if (marksObtained > 33 && marksObtained <= 60) {
            iv_well_done.setImageDrawable(activity.getResources().getDrawable(R.mipmap.need_improvement));
        } else if (marksObtained > 60 && marksObtained <= 80) {
            iv_well_done.setImageDrawable(activity.getResources().getDrawable(R.mipmap.well_done));
        } else if (marksObtained > 80 && marksObtained <= 95) {
            iv_well_done.setImageDrawable(activity.getResources().getDrawable(R.mipmap.excellent));
        } else if (marksObtained > 95 && marksObtained <= 100) {
            iv_well_done.setImageDrawable(activity.getResources().getDrawable(R.mipmap.outstanding));
        } else {
            iv_well_done.setImageDrawable(activity.getResources().getDrawable(R.mipmap.work_hard));
        }
    }


    public static void setAlarmas(JsonObject result, Activity activity) {
        MyCheckListModel myCheckListModel = new Gson().fromJson(result, MyCheckListModel.class);
        cancelPreviousAlarms(activity);
        if (myCheckListModel.getResponse().getCurrentTask().size() != 0) {
            for (int i = 0; i < myCheckListModel.getResponse().getCurrentTask().get(0).getChecklist().size(); i++) {
                String label = myCheckListModel.getResponse().getCurrentTask().get(0).getChecklist().get(i).getChecklistName();
                String date = myCheckListModel.getResponse().getCurrentTask().get(0).getChecklist().get(i).getDate();
                String time = myCheckListModel.getResponse().getCurrentTask().get(0).getChecklist().get(i).getTime();
                int id =  myCheckListModel.getResponse().getCurrentTask().get(0).getChecklist().get(i).getChecklistId();
                int isCompleted = myCheckListModel.getResponse().getCurrentTask().get(0).getChecklist().get(i).getIsCompleted();
                if (isCompleted != 1) {
                    if (date != null && time != null) {
                        long timeStamp = getTimeStamp(date, time);
                        Date currentDate = new Date();
                        long currentTimeStamp = currentDate.getTime();
                        Intent intent = new Intent(activity, AlarmReceiver.class);
                        intent.putExtra(Constant.ALARM_ID, id);
                        intent.putExtra(Constant.ALARM_TIME, time);
                        intent.putExtra(Constant.ALARM_DATE, date);
                        intent.putExtra(Constant.ALARM_LABEL, label);
                        intent.putExtra(Constant.ALARM_IS_COMPLETED, isCompleted);
                        intent.setAction(Long.toString(System.currentTimeMillis()));
                        final Intent notifIntent = new Intent(activity, DiaryActivity.class);
                        notifIntent.putExtra(Constant.IS_FROM_ALARM, true);
                        notifIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        final PendingIntent pIntent = PendingIntent.getActivity(activity,Constant.REQUEST_CODE_ALARM,
                                notifIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                        PendingIntent pendingIntent = PendingIntent.getBroadcast(activity,
                                Constant.REQUEST_CODE_ALARM, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
                        if (currentTimeStamp < timeStamp) {
                            AlarmManager.AlarmClockInfo info = new AlarmManager.AlarmClockInfo(toCalendar(getDate(date, time)).getTimeInMillis()+(30*1000), pIntent);
                            alarmManager.setAlarmClock(info, pendingIntent);
                        } else {
                            long updatedTime = currentTimeStamp + (i * 10000);
                            AlarmManager.AlarmClockInfo info = new AlarmManager.AlarmClockInfo(millisToCalendar(updatedTime).getTimeInMillis(), pIntent);
                            alarmManager.setAlarmClock(info, pendingIntent);
                         }
                    }
                }
            }
        }
    }

    private static Calendar millisToCalendar(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar;
    }

    private static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    private static long getTimeStamp(String date, String time) {
        String dateTime = date.concat(" ").concat(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        try {
            d = format.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d.getTime();
    }

    public static Date getDate(String date, String time) {
        String dateTime = date.concat(" ").concat(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        try {
            d = format.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static void cancelPreviousAlarms(Activity activity) {
        AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(activity, AlarmReceiver.class);
        PendingIntent mPendingIntent = PendingIntent.getActivity(activity, Constant.REQUEST_CODE_ALARM, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(mPendingIntent);
    }

*/

}

