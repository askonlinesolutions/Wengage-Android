package com.askonlinesolutions.wengage.Activity.Main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Activity.Main.dashboard.DashboadrFragment;
import com.askonlinesolutions.wengage.CreateGroupFragment;
import com.askonlinesolutions.wengage.Fragment.ContactFragment;
import com.askonlinesolutions.wengage.Fragment.CreateGroupIndividualFragment;
import com.askonlinesolutions.wengage.Fragment.CustomCreateEventFragment;
import com.askonlinesolutions.wengage.Fragment.DiningFragment;
import com.askonlinesolutions.wengage.Fragment.EditPreferenceFragment;
import com.askonlinesolutions.wengage.Fragment.Main.ChatFragment;
import com.askonlinesolutions.wengage.Fragment.Main.InTheKnowFragment;
import com.askonlinesolutions.wengage.Fragment.Main.SearchFragment;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventDetailsFragment;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventHomeFragment;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventSubCatDetailFragment;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventSubCatFragment;
import com.askonlinesolutions.wengage.Fragment.Main.profile.ProfileHome;
import com.askonlinesolutions.wengage.Fragment.Main.profile.UserProfile;
import com.askonlinesolutions.wengage.Fragment.Main.venue.RestaurantList;
import com.askonlinesolutions.wengage.Fragment.Main.venue.VenueHomeFragment;
import com.askonlinesolutions.wengage.Fragment.Main.venue.VenueSubCatFragment;
import com.askonlinesolutions.wengage.Fragment.Main.venue.details.RestaurantDetails;
import com.askonlinesolutions.wengage.Fragment.NotificationFragment;
import com.askonlinesolutions.wengage.Fragment.RestaurantVenueDetails;
import com.askonlinesolutions.wengage.Fragment.Sub.ChatRoomsFragment2;
import com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.vo.GeneralSearchResponse;
import com.askonlinesolutions.wengage.Fragment.TheCityFragment;
import com.askonlinesolutions.wengage.Fragment.UserProfileFragment;
import com.askonlinesolutions.wengage.Fragment.WebViewsFragment;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Model.Request.OnlineProfileRequest;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
import com.askonlinesolutions.wengage.Model.Response.UpdateProfileResponse;
import com.askonlinesolutions.wengage.Networks.NetworkConstants;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.utils.Constant;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.FCViewPager;
import com.askonlinesolutions.wengage.utils.Utility;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
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
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mukesh.tinydb.TinyDB;
import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Callback;
import retrofit2.Response;

import static com.askonlinesolutions.wengage.CreateGroupFragment.REQUEST_CAMERA_FOR_GROUP;
import static com.askonlinesolutions.wengage.Fragment.Main.event.EventHomeFragment.PLACE_PICKER_EVENT_REQUEST;
import static com.askonlinesolutions.wengage.Fragment.Main.event.EventSubCatFragment.PLACE_PICKER_EVENT_SUB_REQUEST;
import static com.askonlinesolutions.wengage.Fragment.Main.profile.ProfileHome.CAMERA_REQUEST_FOR_PROFILE;
import static com.askonlinesolutions.wengage.Fragment.Main.profile.ProfileHome.PLACE_PICKER_REQUEST_FOR_PROFILE;
import static com.askonlinesolutions.wengage.Fragment.Main.venue.VenueHomeFragment.PLACE_PICKER_VENUE_REQUEST;
import static com.askonlinesolutions.wengage.Fragment.Main.venue.VenueSubCatFragment.PLACE_PICKER_VENUE_SUB_REQUEST;
import static com.askonlinesolutions.wengage.Fragment.Sub.ChatRoomsFragment2.REQUEST_CAMERA_FOR_CHAT;
import static com.askonlinesolutions.wengage.Helper.BaseClass.hideSoftKeyboard;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, OnResponseInterface, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, AdapterView.OnItemSelectedListener {
    private Toolbar toolbar;
    private FCViewPager viewPager;
    private TextView txtName, txtCity, tabOne, tabTwo, tabFour, tabfIVE;
    public static ImageView imgProfile;
    public static ImageView profile_image;
    private ImageView online_image;
    private Location locations;
    private ImageView offline_image;
    private LinearLayout radiusLayout;
    public static ImageView imgProgressTop;
    private TinyDB tinyDB;
    //    private ProgressDialog progressDialog;
    private TextView bottom_chat, bottom_venue, bottom_events, bottom_intheknow;
    private LoginResponse loginResponse;
    private Gson gson = new Gson();
    private int user_id, isOffline = 1;
    private String keyword, placename;
    private ImageView location_trace, reset_location;
    private long mBackPressed = 0;
    private RelativeLayout profileLayout, frameLayout;
    private LinearLayout layout_toolbar;
    private Spinner spinner;
    private String city;
    private static final int PLACE_PICKER_REQUEST = 1000;
    private GoogleApiClient mClient;
    private LinearLayout search_layout;
    public static LinearLayout bottomTab;
    private TextView location;
    private AutoCompleteTextView search_main;
    private ArrayList<com.askonlinesolutions.wengage.Model.Search> list = new ArrayList<>();
    private List<GeneralSearchResponse.SearchedDataList> searchListResponses = new ArrayList<>();
    private GoogleApiClient mLocationclient;
    private Place place;
    private View header;
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
    private Bundle bundle;
    private Dialog errorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bundle = savedInstanceState;
        tinyDB = new TinyDB(getApplicationContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            BaseClass.setLightStatusBar(getWindow().getDecorView(), HomeActivity.this);
        }

        if (chekPermission()) {
            callLocationMethod(bundle);
            initUI();

        }
        if (tinyDB.getString(Constants.KILL_TIME_LOCATION).equals(tinyDB.getString(Constants.CURRENT_LOCATION))) {

//            errorDialog = createDialog(this, errorDialog, "Same Location");
//            errorDialog.show();
        } else {
            errorDialog = createDialog(this, errorDialog, "Different Location");
            errorDialog.show();
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
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude(), 1);
//                    String address = addresses.get(0).getAddressLine(0);
                    city = addresses.get(0).getLocality();
                    tinyDB.putString(Constants.CURRENT_LOCATION, city);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
            initUI();
            //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }

    }

    private void initUI() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        header = navigationView.getHeaderView(0);

        location = header.findViewById(R.id.location);
        location.setText(tinyDB.getString(Constants.CITY));
        reset_location = header.findViewById(R.id.reset_location);
        location_trace = header.findViewById(R.id.location_trace);
        location = header.findViewById(R.id.location);
        location_trace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                try {
                    intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(HomeActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        reset_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocationUpdates(1);

            }
        });
        if (tinyDB.getString(Constants.IS_CALL).equals("1")) {
            location.setText(tinyDB.getString(Constants.CITY));
        } else {
            callLocationMethod(bundle);
            startLocationUpdates(0);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tinyDB.putString(Constants.IS_LOGIN, "true");
        loginResponse = new LoginResponse();
        loginResponse = gson.fromJson(tinyDB.getString("login_data"), LoginResponse.class);
        if (loginResponse != null && loginResponse.getProfileInfo() != null)
            user_id = loginResponse.getProfileInfo().getUserId();
//        user_id=tinyDB.getInt(Constants.USER_ID);

        init();
        replaceDashboardFragment();
    }


    private void fetchAddressFromLatLong(double latitude, double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            String address = addresses.get(0).getAddressLine(0);
            city = addresses.get(0).getLocality();
            location.setText(city);
//            String state = addresses.get(0).getAdminArea();
//            String country = addresses.get(0).getCountryName();
//            String postalCode = addresses.get(0).getPostalCode();
//            String knownName = addresses.get(0).getFeatureName();
            tinyDB.putString(Constants.CITY, city);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void callSearchFragment() {
        Intent intent1 = getIntent();
        String name = intent1.getStringExtra("name");
        if (name != null && name.equals("inTheKnow")) {
            frameLayout.setVisibility(View.VISIBLE);
//            viewPager.setVisibility(View.GONE);
            SearchFragment fragment = new SearchFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", "inTheKnow");
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter,
                    R.animator.fade_out,
                    0,
                    R.animator.fragment_slide_right_exit)
                    .replace(R.id.frame, fragment).addToBackStack(SearchFragment.class.getName()).commit();
        } else {
            frameLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.VISIBLE);
        }

    }

    public void search(View view) {
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.frame);
        if (visibleFragment instanceof SearchFragment) {
        } else {
            frameLayout.setVisibility(View.VISIBLE);
            replaceSearchFragment();
        }

    }

    private void init() {
        mClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();

        bottom_intheknow = findViewById(R.id.bottom_intheknow);
        bottom_events = findViewById(R.id.bottom_events);
        bottom_venue = findViewById(R.id.bottom_venue);
        bottom_chat = findViewById(R.id.bottom_chat);
        LinearLayout dashBoardLayout = findViewById(R.id.dashBoardLayout);
        bottomTab = findViewById(R.id.bottomTab);

        imgProgressTop = findViewById(R.id.activity_home_img_progress);
        frameLayout = findViewById(R.id.frame);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setEnableSwipe(false);
        TabLayout tabLayout = findViewById(R.id.tabs);


        viewPager.setVisibility(View.VISIBLE);

        Glide.with(this)
                .asGif()
                .load(R.drawable.load)
                .into(imgProgressTop);


        spinner = header.findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("5 km");
        categories.add("10 km");
        categories.add("15 km");
        categories.add("25 km");
        categories.add("50 km");
        categories.add("75 km");
        categories.add("100 km");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (loginResponse.getProfileInfo().getRadius().equals("")) {
            int spinnerPosition = dataAdapter.getPosition("50 km");
            spinner.setAdapter(dataAdapter);
            spinner.setSelection(spinnerPosition);
        } else {
            int spinnerPosition = dataAdapter.getPosition(loginResponse.getProfileInfo().getRadius() + " km");
            spinner.setAdapter(dataAdapter);
        }


        // attaching data adapter to spinner

        txtName = header.findViewById(R.id.nav_header_main_txtName);
        txtCity = header.findViewById(R.id.nav_header_main_txtCity);
        imgProfile = header.findViewById(R.id.nav_header_main_img);

        radiusLayout = header.findViewById(R.id.radiusLayout);
        LinearLayout toolbar_menu = findViewById(R.id.tool_menu);
        layout_toolbar = findViewById(R.id.layout_toolbar);
//        progressDialog = new BaseClass(HomeActivity.this).creatProgressDialog();
        profileLayout = findViewById(R.id.profile);
        toolbar_menu.setOnClickListener(this);
        profileLayout.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                replaceOnlinefragment();
            }

            @Override
            public void onDoubleClick(View view) {

                ProfileHome fragment = new ProfileHome();
                Bundle bundle = new Bundle();
                bundle.putString("check", "1");
                bundle.putBoolean("comingFromProfileIconClick", true);
                fragment.setArguments(bundle);
                frameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter,
                        R.animator.fade_out,
                        0,
                        R.animator.fragment_slide_right_exit)
                        .replace(R.id.frame, fragment).addToBackStack(ProfileHome.class.getName()).commit();

                bottom_chat.setTextColor(getResources().getColor(R.color.text_color));
                bottom_venue.setTextColor(getResources().getColor(R.color.text_color));
                bottom_events.setTextColor(getResources().getColor(R.color.text_color));
                bottom_intheknow.setTextColor(getResources().getColor(R.color.text_color));
            }
        }));
        profile_image = findViewById(R.id.profile_icon);
        ImageView search_cross = findViewById(R.id.search_cross);
        search_layout = findViewById(R.id.layout_search);
        ImageView searchBarIv = findViewById(R.id.search_bar);
        search_cross.setOnClickListener(this);
//        search_icon = (LinearLayout) findViewById(R.id.search_icon);
        search_main = findViewById(R.id.search_main);
        search_main.setOnClickListener(this);

        bottom_intheknow.setOnClickListener(this);
        bottom_events.setOnClickListener(this);
        bottom_venue.setOnClickListener(this);
        bottom_chat.setOnClickListener(this);
        dashBoardLayout.setOnClickListener(this);

        LinearLayout profieLay = findViewById(R.id.favourite);
        profieLay.setOnClickListener(this);
        bottomTab.setVisibility(View.VISIBLE);
      /*  profile_image.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return gestureDetector.onTouchEvent(event);
            }

        });*/

        online_image = findViewById(R.id.online_dot);
        offline_image = findViewById(R.id.offline_dot);
        //   profile_image.setOnClickListener(this);

        search_main.setThreshold(1);//will start working from first character

        getUserDetails();

    }

    private void getUserDetails() {
        txtCity.setText(loginResponse.getProfileInfo().getCity());
        txtName.setText(loginResponse.getProfileInfo().getKnownByName());

        if (loginResponse.getProfileInfo().getInfluencer() == 0) {
            profile_image.setBackgroundResource(R.drawable.pink_circle);
        } else profile_image.setBackgroundResource(R.drawable.blue_circle);

        Glide.with(this)
                .load(loginResponse.getProfileInfo().getPhotoURL())
                .apply(RequestOptions.circleCropTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        imgProgressTop.setVisibility(View.GONE);

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        imgProgressTop.setVisibility(View.GONE);
                        profile_image.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(profile_image);


        Glide.with(getApplicationContext())
                .asBitmap()
                .load(loginResponse.getProfileInfo().getPhotoURL())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .into(new BitmapImageViewTarget(imgProfile) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        imgProfile.setImageBitmap(resource);
                        super.setResource(resource);
                    }
                });

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.reset_location) {

        }
        if (id == R.id.location_trace) {


        }
        if (id == R.id.nav_edit_profile) {
            Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.frame);
            if (visibleFragment instanceof ProfileHome) {
            } else {
                profileLayout.setEnabled(false);
                ProfileHome fragment = new ProfileHome();
                Bundle bundle = new Bundle();
                bundle.putString("check", "1");
                bundle.putBoolean("comingFromProfileIconClick", true);
                fragment.setArguments(bundle);
                frameLayout.setVisibility(View.VISIBLE);
//            setDefaultTabColor();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter,
                        R.animator.fade_out,
                        0,
                        R.animator.fragment_slide_right_exit)
                        .replace(R.id.frame, fragment).addToBackStack(null).commit();
            }

            // Handle the camera action
        } else if (id == R.id.nav_demo) {

            tinyDB.putString(Constants.FROM_COMING, "Demo");
            replaceWebViewFragment();
        } else if (id == R.id.nav_about) {
            tinyDB.putString(Constants.FROM_COMING, "About us");
            replaceWebViewFragment();
        } else if (id == R.id.nav_faq) {
            tinyDB.putString(Constants.FROM_COMING, "Faq");
            replaceWebViewFragment();
        } else if (id == R.id.nav_policies) {
            tinyDB.putString(Constants.FROM_COMING, "Policy");
            replaceWebViewFragment();
        } else if (id == R.id.nav_terms) {
            tinyDB.putString(Constants.FROM_COMING, "Terms & Conditions");
            replaceWebViewFragment();
        } else if (id == R.id.nav_terms_guidlines) {
            tinyDB.putString(Constants.FROM_COMING, "Terms & Guidelines");
            replaceWebViewFragment();
        } else if (id == R.id.nav_logout) {
            showLogoutDialog();

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    private void showLogoutDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.error_logout_dialog);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        final TextView txtOk = dialog.findViewById(R.id.dialog_error_ok);
        final TextView dialog_error_no = dialog.findViewById(R.id.dialog_error_no);
        final TextView txtMsg = dialog.findViewById(R.id.dialog_error_msg);
        final Dialog finalDialog = dialog;
        dialog_error_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalDialog.dismiss();
            }
        });
        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinyDB.remove("login_data");
                tinyDB.putString(Constants.IS_LOGIN, "false");
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
                finalDialog.dismiss();
            }
        });
        txtMsg.setText("Do you want to logout ?");
        finalDialog.show();
    }

    //    down = findViewById(R.id.down);
//    up = findViewById(R.id.up);
//    radiusLayout = findViewById(R.id.radiusLayout);
//    first = findViewById(R.id.first);
//    second = findViewById(R.id.second);
//    third = findViewById(R.id.third);
//    radiusMain = findViewById(R.id.radiusMain);
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.profile:
                Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.frame);
                long TIME_INTERVAL = 2000;
                if (visibleFragment instanceof ProfileHome) {
                } else {
                    if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {

                        return;
                    } else {

//                    Toast.makeText(getApplicationContext(), "Tap back button in order to Open your Profile", Toast.LENGTH_SHORT).show();
                    }
                    mBackPressed = System.currentTimeMillis();
                }
                break;

            case R.id.favourite:
                replaceProfilefavFragment();

                bottom_chat.setTextColor(getResources().getColor(R.color.text_color));
                bottom_venue.setTextColor(getResources().getColor(R.color.text_color));
                bottom_events.setTextColor(getResources().getColor(R.color.text_color));
                bottom_intheknow.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.bottom_chat:

//                tinyDB.putDouble(Constants.LATITUDE, 0.0);
//                tinyDB.putDouble(Constants.LONGITUDE, 0.0);
                replaceChatFragment();

                bottom_chat.setTextColor(getResources().getColor(R.color.colorAccent));
                bottom_venue.setTextColor(getResources().getColor(R.color.text_color));
                bottom_events.setTextColor(getResources().getColor(R.color.text_color));
                bottom_intheknow.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.bottom_venue:

//                tinyDB.putDouble(Constants.LATITUDE, 0.0);
//                tinyDB.putDouble(Constants.LONGITUDE, 0.0);
                replaceVenueFragment();

                bottom_chat.setTextColor(getResources().getColor(R.color.text_color));
                bottom_venue.setTextColor(getResources().getColor(R.color.colorAccent));
                bottom_events.setTextColor(getResources().getColor(R.color.text_color));
                bottom_intheknow.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.dashBoardLayout:
                replaceDashboardFragment();

//                tinyDB.putDouble(Constants.LATITUDE, 0.0);
//                tinyDB.putDouble(Constants.LONGITUDE, 0.0);
                bottom_chat.setTextColor(getResources().getColor(R.color.text_color));
                bottom_venue.setTextColor(getResources().getColor(R.color.text_color));
                bottom_events.setTextColor(getResources().getColor(R.color.text_color));
                bottom_intheknow.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.bottom_events:

//                tinyDB.putDouble(Constants.LATITUDE, 0.0);
//                tinyDB.putDouble(Constants.LONGITUDE, 0.0);
                replaceEventFragment();

                bottom_chat.setTextColor(getResources().getColor(R.color.text_color));
                bottom_venue.setTextColor(getResources().getColor(R.color.text_color));
                bottom_events.setTextColor(getResources().getColor(R.color.colorAccent));
                bottom_intheknow.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.bottom_intheknow:
                replaceIntheknowFragment();
                bottom_chat.setTextColor(getResources().getColor(R.color.text_color));
                bottom_venue.setTextColor(getResources().getColor(R.color.text_color));
                bottom_events.setTextColor(getResources().getColor(R.color.text_color));
                bottom_intheknow.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case R.id.search_cross:
                search_main.setText("");
                search_layout.setVisibility(View.GONE);
                layout_toolbar.setVisibility(View.VISIBLE);
                hideSoftKeyboard(this);
                break;
            case R.id.search_main:

                search_main.showDropDown();
//                search_main.enoughToFilter();
//                search_main.requestFocus();
                //InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                //imm.showSoftInput(search_main, InputMethodManager.SHOW_IMPLICIT);
                break;

            case R.id.tool_menu:
//                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.END);
                break;

            default:
                break;

        }

    }

    private void replaceOnlinefragment() {
        if (Utility.isNetworkConnected(getApplicationContext())) {
//        frameLayout.setVisibility(View.GONE);
            OnlineProfileRequest updateProfileRequest =
                    new OnlineProfileRequest(isOffline);
            retrofit2.Call<UpdateProfileResponse> call = APIClient.getInstance().getApiInterface()
                    .onlineProfile(user_id, updateProfileRequest);
            //new ResponseListner().getResponse(MainActivity.this, call);
            call.enqueue(new Callback<UpdateProfileResponse>() {
                @Override
                public void onResponse(retrofit2.Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {

                    UpdateProfileResponse updateProfileResponse;
                    Log.e("response", response.toString());

//                if (progressDialog != null && progressDialog.isShowing())
//                    progressDialog.hide();
                    if (response != null) {
                        updateProfileResponse = response.body();
                        if (updateProfileResponse.getStatus() == 1) {
                            //new BaseClass(context).showToast(updateProfileResponse.getMessage());
                            if (isOffline == 1) {
                                online_image.setVisibility(View.GONE);
                                offline_image.setVisibility(View.VISIBLE);
                                isOffline = 0;
                            } else {
                                online_image.setVisibility(View.VISIBLE);
                                offline_image.setVisibility(View.GONE);
                                isOffline = 1;
                            }
//                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        }
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<UpdateProfileResponse> call, Throwable t) {

                }
            });
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getApplicationContext(), errorDialog, "Network error");
            errorDialog.show();
        }
//        progressDialog.show();
    }

    private void replaceProfilefavFragment() {
        bottomTab.setVisibility(View.VISIBLE);
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.frame);
        if (visibleFragment instanceof ProfileHome) {
        } else {
            ProfileHome fragment = new ProfileHome();
            Bundle bundle = new Bundle();
            bundle.putString("check", "1");
            bundle.putBoolean("comingFromProfileIconClick", true);
            fragment.setArguments(bundle);
            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter,
                    R.animator.fade_out,
                    0,
                    R.animator.fragment_slide_right_exit)
                    .replace(R.id.frame, fragment).addToBackStack(null).commit();
        }


    }

    public void replaceChatFragment() {
        bottomTab.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new ChatFragment()).addToBackStack(null).commit();
    }

    public void replaceCreateGroupFragment() {
        bottomTab.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new CreateGroupFragment()).addToBackStack(null).commit();
    }

    public void replaceCreateGroupIndividualFragment() {
        bottomTab.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new CreateGroupIndividualFragment()).addToBackStack(null).commit();
    }

    public void replaceCreateContactFragment() {
        if (tinyDB.getString(Constant.FROM_GROUP).equals("1")) {
            bottomTab.setVisibility(View.VISIBLE);
        } else {
            bottomTab.setVisibility(View.GONE);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new ContactFragment()).addToBackStack(null).commit();
    }

    public void replaceSearchFragment() {
        bottomTab.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new SearchFragment()).addToBackStack(null).commit();
    }

    public void replaceWebViewFragment() {
        bottomTab.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new WebViewsFragment()).addToBackStack(null).commit();
    }

    public void replaceUserProfileFragment(UserProfileFragment fragment) {
        bottomTab.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fade_out,
                0, R.animator.fragment_slide_right_exit).replace(R.id.frame, fragment).addToBackStack(null).commit();
    }

    public void replaceEventDetailFragment(EventDetailsFragment fragment) {
        bottomTab.setVisibility(View.VISIBLE);

        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fade_out,
                0, R.animator.fragment_slide_right_exit).replace(R.id.frame, fragment).addToBackStack(null).commit();
    }

    public void replaceVenueFragment() {

        bottomTab.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new VenueHomeFragment()).addToBackStack(null).commit();
    }

    public void replaceEventFragment() {
        bottomTab.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new EventHomeFragment()).addToBackStack(null).commit();
    }

    public void replaceIntheknowFragment() {
        bottomTab.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new InTheKnowFragment()).addToBackStack(null).commit();
    }

    public void replaceTheCityFragment() {
        bottomTab.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new TheCityFragment()).addToBackStack(null).commit();
    }

    public void replaceDiningFragment() {
        bottomTab.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new DiningFragment()).addToBackStack(null).commit();
    }

    public void replaceDashboardFragment() {
        bottomTab.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new DashboadrFragment()).addToBackStack(null).commit();
    }

    public void replaceChatsFragment(ChatRoomsFragment2 fragment) {
        bottomTab.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).addToBackStack(null).commit();
    }

    public void replaceEventSearchFragment(SearchFragment fragment) {
        frameLayout.setVisibility(View.VISIBLE);
        bottomTab.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).addToBackStack(null).commit();
    }

    public void replaceNotificationFragment() {
        bottomTab.setVisibility(View.GONE);
        bottomTab.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new NotificationFragment()).addToBackStack(null).commit();
    }

    public void replaceEventDetailsFragment(EventDetailsFragment fragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fade_out,
                0, R.animator.fragment_slide_right_exit).replace(R.id.frame, fragment).addToBackStack(null).commit();
    }

    public void replaceVenueDetailFragment(RestaurantVenueDetails fragment) {
//        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter,
//                R.animator.fade_out,
//                0,
//                R.animator.fragment_slide_right_exit)
//                .replace(R.id.fragment_venue_home_fragment_container, fragment).addToBackStack(RestaurantDetails.class.getName()).commit();

        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fade_out,
                0, R.animator.fragment_slide_right_exit).replace(R.id.frame, fragment).addToBackStack(null).commit();

    }

    public void replaceEventSubCatFragment(EventSubCatFragment fragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fade_out,
                0, R.animator.fragment_slide_right_exit).replace(R.id.frame, fragment).addToBackStack(null).commit();
    }

    public void replaceVenueSubCatFragment(VenueSubCatFragment fragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fade_out,
                0, R.animator.fragment_slide_right_exit).replace(R.id.frame, fragment).addToBackStack(null).commit();
    }

    public void replaceEventSubCatDetailFragment(EventSubCatDetailFragment fragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fade_out,
                0, R.animator.fragment_slide_right_exit).replace(R.id.frame, fragment).addToBackStack(null).commit();
    }

    public void replaceRestaurantListFragment(RestaurantList fragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fade_out,
                0, R.animator.fragment_slide_right_exit).replace(R.id.frame, fragment).addToBackStack(null).commit();
    }

    public void replaceRestaurantDetails(RestaurantDetails fragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fade_out,
                0, R.animator.fragment_slide_right_exit).replace(R.id.frame, fragment).addToBackStack(null).commit();
    }

    public void replaceRestaurantVenueDetails(RestaurantVenueDetails fragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fade_out,
                0, R.animator.fragment_slide_right_exit).replace(R.id.frame, fragment).addToBackStack(null).commit();
    }

    public void replaceEditPreferenceFragment() {
        bottomTab.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new EditPreferenceFragment()).addToBackStack(null).commit();
    }

    public void replaceCustomCreateEventFragment() {
        bottomTab.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new CustomCreateEventFragment()).addToBackStack(null).commit();
    }

    @Override
    public void onApiResponse(Object response) {
        if (response != null) {
            try {
                if (response instanceof UserProfile) {
                    Log.d(TAG, "MyResponse: " + new Gson().toJson(response));
                    UserProfile userListResponse = (UserProfile) response;
                    if (userListResponse.getStatus() == 1) {
                        txtCity.setText(userListResponse.getUserData().getCity());
                        txtName.setText(userListResponse.getUserData().getKnownByName());

                        if (userListResponse.getUserData().getInfluencer() == 0) {
                            profile_image.setBackgroundResource(R.drawable.pink_circle);
                        } else profile_image.setBackgroundResource(R.drawable.blue_circle);

                        Glide.with(this)
                                .load(loginResponse.getProfileInfo().getPhotoURL())
                                .apply(RequestOptions.circleCropTransform())
                                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                                .apply(RequestOptions.skipMemoryCacheOf(true))
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                                Target<Drawable> target, boolean isFirstResource) {
                                        imgProgressTop.setVisibility(View.GONE);

                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                                   DataSource dataSource, boolean isFirstResource) {
                                        imgProgressTop.setVisibility(View.GONE);
                                        profile_image.setVisibility(View.VISIBLE);
                                        return false;
                                    }
                                })
                                .into(profile_image);


                        Glide.with(getApplicationContext())
                                .asBitmap()
                                .load(userListResponse.getUserData().getPhotoURL())
                                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                                .apply(RequestOptions.skipMemoryCacheOf(true))
                                .into(new BitmapImageViewTarget(imgProfile) {
                                    @Override
                                    protected void setResource(Bitmap resource) {
                                        imgProfile.setImageBitmap(resource);
                                        super.setResource(resource);
                                    }
                                });
                    }
                }

            } catch (Exception e) {
                Log.d(TAG, "onApiResponse: " + e.getMessage());
            }

        }

    }

    @Override
    public void onApiFailure(String message) {
        new BaseClass(getApplicationContext()).showToast(message);

    }


    @Override
    public void onBackPressed() {
        bottomTab.setVisibility(View.VISIBLE);
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.frame);
        if (visibleFragment instanceof ProfileHome) {
            replaceDashboardFragment();
        } else if (visibleFragment instanceof ChatFragment) {
            closeActivityWithDoubleClick();
        } else if (visibleFragment instanceof WebViewsFragment) {
            replaceDashboardFragment();
        } else if (visibleFragment instanceof VenueHomeFragment) {
            closeActivityWithDoubleClick();
        } else if (visibleFragment instanceof DashboadrFragment) {
            closeActivityWithDoubleClick();
        } else if (visibleFragment instanceof EventHomeFragment) {
            closeActivityWithDoubleClick();
        } else if (visibleFragment instanceof InTheKnowFragment) {
            closeActivityWithDoubleClick();
        } else {
            super.onBackPressed();
        }
    }

    private void closeActivityWithDoubleClick() {
        long TIME_INTERVAL = 2000;
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            finishAffinity();
            return;
        } else {
            Toast.makeText(getApplicationContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initUI();
                } else {
                    if (chekPermission()) {
                        initUI();
                    }
                }
                return;
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String item = parent.getItemAtPosition(position).toString();
//        spinner.setSelection(position);
        item = item.replace(" km", "");
        callUpdateRadiusApi(item, loginResponse.getProfileInfo().getUserId());
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void callUpdateRadiusApi(String radius, int userId) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("radius", radius);

        Ion.with(this)
                .load("PUT", NetworkConstants.PUT_UPDATE_RADIUS + userId)
                .setJsonObjectBody(jsonObject).asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
//                        tinyDB.putDouble(Constants.LATITUDE, 0.0);
//                        tinyDB.putDouble(Constants.LONGITUDE, 0.0);
//                        Toast.makeText(getActivity(), "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {

                place = PlacePicker.getPlace(data, this);
                Log.e("PlaceId", place.getId());
                StringBuilder stBuilder = new StringBuilder();

                placename = String.format("%s", place.getName());
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                String address = String.format("%s", place.getAddress());
                stBuilder.append("Name: ");
                stBuilder.append(placename);
                stBuilder.append("\n");
                stBuilder.append("Latitude: ");
                stBuilder.append(latitude);
                stBuilder.append("\n");
                stBuilder.append("Logitude: ");
                stBuilder.append(longitude);
                stBuilder.append("\n");
                stBuilder.append("Address: ");
                stBuilder.append(address);
                location.setText(placename);
                tinyDB.putString(Constants.CITY, placename);
                tinyDB.putDouble(Constants.LATITUDE, place.getLatLng().latitude);
                tinyDB.putDouble(Constants.LONGITUDE, place.getLatLng().longitude);
                callUpdateLocationApi(placename, latitude, longitude, loginResponse.getProfileInfo().getUserId());
//                textView.setText(stBuilder.toString());
            }
        } else if (requestCode == PLACE_PICKER_VENUE_REQUEST) {
            if (resultCode == RESULT_OK) {
                place = PlacePicker.getPlace(data, this);
                ((VenueHomeFragment) getSupportFragmentManager().findFragmentById(R.id.frame)).setLocation(place);
            }
        } else if (requestCode == PLACE_PICKER_EVENT_SUB_REQUEST) {
            if (resultCode == RESULT_OK) {
                place = PlacePicker.getPlace(data, this);
                ((EventSubCatFragment) getSupportFragmentManager().findFragmentById(R.id.frame)).setLocation(place);
            }
        } else if (requestCode == PLACE_PICKER_VENUE_SUB_REQUEST) {
            if (resultCode == RESULT_OK) {
                place = PlacePicker.getPlace(data, this);
                ((VenueSubCatFragment) getSupportFragmentManager().findFragmentById(R.id.frame)).setLocation(place);
            }
        } else if (requestCode == REQUEST_CAMERA_FOR_CHAT && resultCode == RESULT_OK) {
            if (resultCode == RESULT_OK) {

                ((ChatRoomsFragment2) getSupportFragmentManager().findFragmentById(R.id.frame)).setProfilePic((Bitmap) data.getExtras().get("data"));
            }
        } else if (requestCode == REQUEST_CAMERA_FOR_GROUP && resultCode == RESULT_OK) {
            if (resultCode == RESULT_OK) {

                ((CreateGroupFragment) getSupportFragmentManager().findFragmentById(R.id.frame)).setProfilePic((Bitmap) data.getExtras().get("data"));
            }
        } else if (requestCode == PLACE_PICKER_EVENT_REQUEST) {
            if (resultCode == RESULT_OK) {
                place = PlacePicker.getPlace(data, this);
                ((EventHomeFragment) getSupportFragmentManager().findFragmentById(R.id.frame)).setLocation(place);
            }
        } else if (requestCode == CAMERA_REQUEST_FOR_PROFILE && resultCode == RESULT_OK) {
            ((ProfileHome) getSupportFragmentManager().findFragmentById(R.id.frame)).setProfilePic((Bitmap) data.getExtras().get("data"));

        }else if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contactData = data.getData();
                Cursor c =  getContentResolver().query(contactData, null, null, null, null);
                if (c.moveToFirst()) {
                    String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    // TODO Whatever you want to do with the selected contact name.
                }
            }
        } else if (requestCode == PLACE_PICKER_REQUEST_FOR_PROFILE) {
            if (resultCode == RESULT_OK) {
                place = PlacePicker.getPlace(data, this);
                ((ProfileHome) getSupportFragmentManager().findFragmentById(R.id.frame)).setlocation(place);

//                textView.setText(stBuilder.toString());
            }
        }
    }


    private void callUpdateLocationApi(String placename, String latitude, String longitude, int userId) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("currentCity", placename);
        jsonObject.addProperty("currentLatitude", latitude);
        jsonObject.addProperty("currentLongitude", longitude);

        Ion.with(this)
                .load("PUT", NetworkConstants.PUT_UPDATE_CURRENT_CITY + userId)
                .setJsonObjectBody(jsonObject).asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
//                        tinyDB.putDouble(Constants.LATITUDE, 0.0);
//                        tinyDB.putDouble(Constants.LONGITUDE, 0.0);
//                        Toast.makeText(getActivity(), "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void startLocationUpdates(final int i) {
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
                            if (i == 1) {
                                tinyDB.putDouble(Constants.LATITUDE, mCurrentLocation.getLatitude());
                                tinyDB.putDouble(Constants.LONGITUDE, mCurrentLocation.getLongitude());
                                fetchAddressFromLatLong(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
//                location.setText(city);
//                tinyDB.putString(Constants.CITY, city);
                                callUpdateLocationApi(placename, String.valueOf(mCurrentLocation.getLatitude()), String.valueOf(mCurrentLocation.getLongitude()),
                                        loginResponse.getProfileInfo().getUserId());
                            } else {
                                tinyDB.putDouble(Constants.LATITUDE, mCurrentLocation.getLatitude());
                                tinyDB.putDouble(Constants.LONGITUDE, mCurrentLocation.getLongitude());
                                fetchAddressFromLatLong(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                            }


                            // location last updated time
//            txtUpdatedOn.setText("Last updated on: " + mLastUpdateTime);
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
                                    rae.startResolutionForResult(HomeActivity.this, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);

                                Toast.makeText(HomeActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                        }


                    }
                });
    }


    @Override
    protected void onPause() {
//        Toast.makeText(this, "Pause App", Toast.LENGTH_LONG).show();
        tinyDB.putString(Constants.KILL_TIME_LOCATION, location.getText().toString().trim());
        super.onPause();
    }

    private Dialog createDialog(HomeActivity homeActivity, Dialog errorDialog, String msg) {

        if (errorDialog == null) {
            errorDialog = new Dialog(homeActivity);
            errorDialog.setContentView(R.layout.error_alert_dialogs);
            errorDialog.setCancelable(false);
            errorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            errorDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            final TextView txtOk = errorDialog.findViewById(R.id.dialog_error_ok);
            final TextView txtNo = errorDialog.findViewById(R.id.dialog_error_no);
            final TextView txtMsg = errorDialog.findViewById(R.id.dialog_error_msg);
            final Dialog finalDialog = errorDialog;
            txtNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finalDialog.dismiss();

                }
            });
            txtOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startLocationUpdates(1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            location.setText(tinyDB.getString(Constants.CURRENT_LOCATION));
                            tinyDB.putString(Constants.KILL_TIME_LOCATION, tinyDB.getString(Constants.CURRENT_LOCATION));
                            finalDialog.dismiss();

                        }
                    }, 2000);


                }
            });
//            txtMsg.setText(msg);
        }

        return errorDialog;
    }


}
