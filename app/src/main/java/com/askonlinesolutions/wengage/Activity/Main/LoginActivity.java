package com.askonlinesolutions.wengage.Activity.Main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Activity.CompleteProfile1;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Helper.MySharedPreference;
import com.askonlinesolutions.wengage.Model.Request.LoginRequest;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Utility;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.mukesh.tinydb.TinyDB;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {

    EditText userName, password;
    TextView signup, forgot_password, login;
    //    ImageView back;
    LoginResponse.ProfileInfoBean profileInfo;
    ProgressDialog progressDialog;
    private TinyDB tinyDB;
    private Dialog errorDialog;
    private Dialog dialog_error;
    private TextView dialog_error_tv, dialog_error_btn;

    private static final String TAG = MainActivity.class.getSimpleName();
    // location updates interval - 10sec
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private String mLastUpdateTime;
    // fastest updates interval - 5 sec
    // location updates will be received if another app is requesting the locations
    // than your app can handle
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;

    private static final int REQUEST_CHECK_SETTINGS = 100;


    // bunch of location related apis
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;
    private Boolean mRequestingLocationUpdates;
    Bundle bundles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            BaseClass.setLightStatusBar(getWindow().getDecorView(), LoginActivity.this);
        }
        bundles = savedInstanceState;
        if (chekPermission()) {
            callLocationMethod(bundles);
            init();
        }

    }

    private boolean chekPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) +
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) +
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) +
                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_NETWORK_STATE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.CAMERA
                        , android.Manifest.permission.ACCESS_NETWORK_STATE}, 1);
                return false;
            }
        } else {
            callLocationMethod(bundles);
            init();
            //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }

    }

    private void init() {
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.logIn_button);
        signup = findViewById(R.id.SignUp);
//        back =findViewById(R.id.back);
//        back.setOnClickListener(this);
        tinyDB = new TinyDB(getApplicationContext());
        forgot_password = findViewById(R.id.forgot_password);
        forgot_password.setOnClickListener(this);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
//        fetchLatLong();
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };

        userName.setFilters(new InputFilter[]{filter});
        progressDialog = new BaseClass(this).creatProgressDialog();

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.SignUp) {
            this.deleteDatabase("wengage_db");

            if (progressDialog == null) {
                progressDialog = new ProgressDialog(getApplicationContext(), R.style.MyProgressDialogStyle);
            }
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            progressDialog.setMessage("Please wait we are fetching your location...");
            callLocationMethod(bundles);
            fetchLatLong();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                    Intent i = new Intent(LoginActivity.this, FirstIntroScreen.class);
                    startActivity(i);
                    finish();
                }
            }, 6000);


          /*  Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
//            Intent intent = new Intent(LoginActivity.this, FaceDetection.class);
            startActivity(intent);*/

        } else if (view.getId() == R.id.logIn_button) {
            if (userName.getText().toString().isEmpty()) {
//                new BaseClass(LoginActivity.this).showToast("Please enter username");
                errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter username");
                errorDialog.show();
            } else if (password.getText().toString().isEmpty()) {
//                new BaseClass(LoginActivity.this).showToast("Please enter password");

                errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter Password");
                errorDialog.show();
            } else {
                if (Utility.isNetworkConnected(LoginActivity.this)) {
                    fetchLoginData();
                } else {
                    Dialog errorDialog = null;
                    errorDialog = Utility.createErrorDialog(getApplicationContext(), errorDialog, "Network error");
                    errorDialog.show();
                }
            }
        } else if (view.getId() == R.id.forgot_password) {
            Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
            startActivity(intent);
        } else if (view.getId() == R.id.dialog_error_btn) {
            click_retry();
//            dialog_error_tv.setText(getResources().getString(R.string.something_went_wrong));
        } else {

        }
    }

    private void fetchLoginData() {
        Utility.showProgressDialog(LoginActivity.this);
        callLocationMethod(bundles);
        fetchLatLong();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(LoginActivity.this, tinyDB.getDouble(Constants.LATITUDE, 0.0) + "", Toast.LENGTH_SHORT).show();

                LoginRequest loginRequest = new LoginRequest(userName.getText().toString(), password.getText().toString());
                Call<LoginResponse> call = APIClient.getInstance().getApiInterface().getLoginData(loginRequest);
                new ResponseListner(LoginActivity.this).getResponse(LoginActivity.this, call);

                Log.e("MyRequest", new Gson().toJson(call.request().body()));
                Log.e("MyRequest", call.request().method());
                Log.e("MyRequest", call.request().body().toString());
                Log.e("MyRequest", new Gson().toJson(loginRequest));

            }
        }, 6000);

    }

    @Override
    public void onApiResponse(Object response) {
        Utility.hideDialog();
        if (response instanceof LoginResponse) {
            LoginResponse loginResponse = (LoginResponse) response;
            Log.e("MyResponse", new Gson().toJson(loginResponse));
            if (loginResponse.getStatus() == 1) {

                new BaseClass(LoginActivity.this).showToast(loginResponse.getMessage().toString());

                profileInfo = loginResponse.getProfileInfo();

                tinyDB.putInt(Constants.USER_ID, profileInfo.getUserId());
                tinyDB.putString(Constants.USER_NAME, profileInfo.getUserName());

                MySharedPreference.getInstance(getApplicationContext()).saveUserData(loginResponse.toString());
                if (profileInfo.getIsFirstLogin() == 0) {
                    if (profileInfo.getIsProfileCompleted() == 1) {
                        if (profileInfo.getIsVenueCategory() == 1) {
                            //    Utility.showToast(getApplicationContext(), "Login Successfull");
                            tinyDB.putString("login_data", new Gson().toJson(loginResponse));
//                            startActivity(new Intent(this, HomeActivity.class));
//                            startActivity(new Intent(this, HomeActivity.class));
                            startActivity(new Intent(this, HomeActivity.class));
                            finishAffinity();
                        } else {
                            tinyDB.putString("login_data", new Gson().toJson(loginResponse));
                            // Select Category
                            startActivity(new Intent(this, ControlActivity.class));
                        }
                    } else {
                        // Fill Profile
                        startActivity(new Intent(this, CompleteProfile1.class));
                    }
                } else {
                    // Enter OTP
                    startActivity(new Intent(this, VerifyCodeActivity.class));
                }
            } else {
                errorDialog = Utility.createErrorDialog(this, errorDialog, loginResponse.getMessage());
                errorDialog.show();
            }
        } else {

        }
    }

    @Override
    public void onApiFailure(String message) {
        Utility.hideDialog();
        if (message.equals("null") || message == null) {
            errorDialog = Utility.createErrorDialog(this, errorDialog, "Server under maintenance");
            errorDialog.show();
        } else {
            errorDialog = Utility.createErrorDialog(this, errorDialog, message);
            errorDialog.show();
        }

//        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
//        createDailogError();
//        dialog_error_tv.setText(message);
//        new BaseClass(LoginActivity.this).showToast(message);

    }

    private void createDailogError() {
        if (dialog_error == null) {
            dialog_error = new Dialog(LoginActivity.this);
            dialog_error.setContentView(R.layout.dialog_error);

            dialog_error.setCancelable(true);
            dialog_error.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog_error.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        dialog_error.show();

            dialog_error_tv = dialog_error.findViewById(R.id.dialog_error_text);
            dialog_error_btn = dialog_error.findViewById(R.id.dialog_error_btn);

//            dialog_error_tv.setTe

            dialog_error_btn.setOnClickListener(this);
            dialog_error.show();
        } else {
            dialog_error.show();
        }
    }

    private void click_retry() {
        dialog_error.dismiss();
        if (Utility.isNetworkConnected(getApplicationContext())) {
            fetchLoginData();
        } else {

//            createDailogError();
//            dialog_error_tv.setText(getResources().getString(R.string.no_internet));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    init();
//                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
//                    call_action();
                } else {
                    if (chekPermission()) {
                        init();
                    }
//                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void fetchLatLong() {
        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "All location settings are satisfied.");

//                        Toast.makeText(getApplicationContext(), "Started location updates!", Toast.LENGTH_SHORT).show();

                        //noinspection MissingPermission
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());

                        if (mCurrentLocation != null) {
//                            txtLocationResult.setText(
//                                    "Lat: " + mCurrentLocation.getLatitude() + ", " +
//                                            "Lng: " + mCurrentLocation.getLongitude()
                            if (mCurrentLocation.getLatitude() == 0.0 && mCurrentLocation.getLongitude() == 0.0) {

                            } else {
                                tinyDB.putDouble(Constants.LATITUDE, mCurrentLocation.getLatitude());
                                tinyDB.putDouble(Constants.LONGITUDE, mCurrentLocation.getLongitude());
                                fetchAddressFromLatLong(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                            }


                            // location last updated time
//            txtUpdatedOn.setText("Last updated on: " + mLastUpdateTime);
                        } else {
//                            tinyDB.putDouble(Constants.LATITUDE, 28.5355);
//                            tinyDB.putDouble(Constants.LONGITUDE, 77.3910);
//                            fetchAddressFromLatLong(28.5355,77.3910);
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(LoginActivity.this, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);

                                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                        }


                    }
                });


    }

    private void fetchAddressFromLatLong(double latitude, double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            String address = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getLocality();
//            String state = addresses.get(0).getAdminArea();
//            String country = addresses.get(0).getCountryName();
//            String postalCode = addresses.get(0).getPostalCode();
//            String knownName = addresses.get(0).getFeatureName();
            tinyDB.putString(Constants.CITY, city);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @SuppressLint("RestrictedApi")
    private void callLocationMethod(Bundle savedInstanceState) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                // location is received
                mCurrentLocation = locationResult.getLastLocation();
                mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
                tinyDB.putDouble(Constants.LATITUDE, locationResult.getLastLocation().getLatitude());
                tinyDB.putDouble(Constants.LONGITUDE, locationResult.getLastLocation().getLongitude());
                fetchAddressFromLatLong(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude());
            }
        };
        mRequestingLocationUpdates = false;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
        restoreValuesFromBundle(savedInstanceState);
    }

    private void restoreValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("is_requesting_updates")) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");
            }

            if (savedInstanceState.containsKey("last_known_location")) {
                mCurrentLocation = savedInstanceState.getParcelable("last_known_location");
            }

            if (savedInstanceState.containsKey("last_updated_on")) {
                mLastUpdateTime = savedInstanceState.getString("last_updated_on");
            }
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
//        fetchLatLong();
    }
}