package com.askonlinesolutions.wengage.Activity.Main;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.dashboard.DashboadrFragment;
import com.askonlinesolutions.wengage.Adapter.SearchAdapter;
import com.askonlinesolutions.wengage.Fragment.Main.ChatFragment;

import com.askonlinesolutions.wengage.Fragment.Main.venue.VenueHomeFragment;
import com.askonlinesolutions.wengage.Fragment.Main.profile.ProfileHome;
import com.askonlinesolutions.wengage.Fragment.Main.InTheKnowFragment;
import com.askonlinesolutions.wengage.Fragment.Main.profile.UserProfile;
import com.askonlinesolutions.wengage.Fragment.Sub.ChatRooms1Fragment;
import com.askonlinesolutions.wengage.Fragment.Sub.ChatRoomsFragment;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Model.Request.OnlineProfileRequest;
import com.askonlinesolutions.wengage.Model.Request.ProfileRequest;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
import com.askonlinesolutions.wengage.Model.Response.UpdateProfileResponse;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.mukesh.tinydb.TinyDB;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.askonlinesolutions.wengage.Helper.BaseClass.hideSoftKeyboard;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, OnResponseInterface {

    private RecyclerView rv_top;
    private BottomNavigationView bottomNavigationView;
    private TinyDB tinyDB;
    private ImageView profile_image, online_image, offline_image;
    private int isOffline = 1;
    private LinearLayout favourite;
    private RelativeLayout profileLayout;
    private GestureDetector gestureDetector;
    private boolean tapped;
    private ProgressDialog progressDialog;
    private Context context;
    private LoginResponse loginResponse;
    private ImageView notificationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        tinyDB = new TinyDB(getApplicationContext());

        loginResponse = new LoginResponse();
        loginResponse = gson.fromJson(tinyDB.getString("login_data"), LoginResponse.class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        notificationBtn = (ImageView) findViewById(R.id.notification_btn);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            BaseClass.setLightStatusBar(getWindow().getDecorView(), MainActivity.this);
        }
        new BaseClass(getApplicationContext()).callFragment(new DashboadrFragment(), "dashboard",
                getSupportFragmentManager());

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        txtName = header.findViewById(R.id.nav_header_main_txtName);
        txtCity = header.findViewById(R.id.nav_header_main_txtCity);
        imgProfile = header.findViewById(R.id.nav_header_main_img);
//        nav_edit_profile = header.findViewById(R.id.nav_edit_profile);

        getUserDetails();
        init();
//        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);

    }

    private TextView txtName, txtCity;
    private ImageView imgProfile;

    private void getUserDetails() {
       /* progressDialog = new BaseClass(context).creatProgressDialog();

        progressDialog.show();*/

        String profileId = "";
        ProfileRequest profileRequest = new ProfileRequest();
        profileRequest.setProfileId(profileId);
        profileRequest.setUserId(tinyDB.getInt(Constants.USER_ID)/*165*/);
        Call<UserProfile> call = APIClient.getInstance().getApiInterface()
                .getUserDetails(profileRequest);
        new ResponseListner(this).getResponse(getApplicationContext(), call);
        Log.d("TAG", "MyUrl: " + call.request().url());
        Log.d("TAG", "MyRequest: " + new Gson().toJson(profileRequest));
    }


    private ArrayList<com.askonlinesolutions.wengage.Model.Search> list = new ArrayList<>();
    private Gson gson = new Gson();

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        setBottomColors();
        favourite = findViewById(R.id.favourite);

        tinyDB.putString("bottom_click", "venues");
        toolbar_menu = (LinearLayout) findViewById(R.id.tool_menu);
        layout_toolbar = (LinearLayout) findViewById(R.id.layout_toolbar);
        progressDialog = new BaseClass(context).creatProgressDialog();
        profileLayout = findViewById(R.id.profile);
        toolbar_menu.setOnClickListener(this);
        notificationBtn.setOnClickListener(this);

        profile_image = findViewById(R.id.profile_icon);
        gestureDetector = new GestureDetector(MainActivity.this, new GestureListener());
/*

       String login_data = tinyDB.getString("login_data");
       loginResponse = gson.fromJson(login_data,LoginResponse.class);
*/


        search_cross = (ImageView) findViewById(R.id.search_cross);
        search_layout = (LinearLayout) findViewById(R.id.layout_search);
        search_cross.setOnClickListener(this);
//        search_icon = (LinearLayout) findViewById(R.id.search_icon);
        search_main = (AutoCompleteTextView) findViewById(R.id.search_main);
        search_main.setOnClickListener(this);

        if (loginResponse.getProfileInfo().getPhotoURL() != null)
            Picasso.with(getApplicationContext())
                    .load(loginResponse.getProfileInfo().getPhotoURL())
                    .into(profile_image);

        profile_image.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return gestureDetector.onTouchEvent(event);
            }

        });

        search_main.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Toast.makeText(MainActivity.this, "changed", Toast.LENGTH_SHORT).show();
                setMyAdapter();
//                search_main.showDropDown();
//                search_main.enoughToFilter();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        online_image = findViewById(R.id.online_dot);
        offline_image = findViewById(R.id.offline_dot);
        //   profile_image.setOnClickListener(this);

        search_main.setThreshold(1);//will start working from first character
        setMyAdapter();
    }

    private void setMyAdapter() {

        list.clear();
        list.add(new com.askonlinesolutions.wengage.Model.Search("Restaurants"));
        list.add(new com.askonlinesolutions.wengage.Model.Search("Bars"));
        list.add(new com.askonlinesolutions.wengage.Model.Search("Live Music"));
        list.add(new com.askonlinesolutions.wengage.Model.Search("Movies"));
        list.add(new com.askonlinesolutions.wengage.Model.Search("Theater"));
        list.add(new com.askonlinesolutions.wengage.Model.Search("Sight Seeing"));
        list.add(new com.askonlinesolutions.wengage.Model.Search("Sports"));
        list.add(new com.askonlinesolutions.wengage.Model.Search("Walking"));
        list.add(new com.askonlinesolutions.wengage.Model.Search("Steak House"));
        list.add(new com.askonlinesolutions.wengage.Model.Search("Safe Food"));
        list.add(new com.askonlinesolutions.wengage.Model.Search("Breakfast"));
        list.add(new com.askonlinesolutions.wengage.Model.Search("Food Food Markets"));

        SearchAdapter adapter1 = new SearchAdapter(MainActivity.this, R.layout.layout_search, list);
        search_main.setAdapter(adapter1);//setting the adapter data into the AutoCompleteTextView

    }

    LinearLayout layout_toolbar, search_layout, toolbar_menu;
    ImageView search_cross;
    AutoCompleteTextView search_main;

    public void search(View view) {

        search_layout.setVisibility(View.VISIBLE);
        layout_toolbar.setVisibility(View.GONE);
        search_main.showDropDown();
        search_main.enoughToFilter();
        search_main.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(search_main, InputMethodManager.SHOW_IMPLICIT);
//        startActivity(new Intent(MainActivity.this, Search.class));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            try {
                if (getSupportFragmentManager().findFragmentById(R.id.frame).getClass().getName()
                        .equals(ChatFragment.class.getName())) {
/*
                if (new ChatRoomsFragment2() != null) {
                    if (!ChatRoomsFragment2.isBottomViewUp) {
                        ChatRoomsFragment2.layout_bottom_emoji.setVisibility(View.GONE);
                        ChatRoomsFragment2.layout_bottom_bookmark.setVisibility(View.GONE);
                    }
//                    return false;
                } else {
*/

                    if (ChatFragment.page_position == 0) {
                        super.onBackPressed();
                    } else if (ChatFragment.page_position == 2) {
                        if (ChatFragment.adapter.mFragmentList.get(2).getClass().getName()
                                .equals(new ChatRooms1Fragment().getClass().getName())) {
                            ChatFragment.adapter.replaceFragment(new ChatRoomsFragment(), "CHAT ROOMS", 2);
                            ChatFragment.adapter.notifyDataSetChanged();
                        } else {
                            ChatFragment.viewPager.setCurrentItem(0);
                        }
                    } else {
                        ChatFragment.viewPager.setCurrentItem(0);
                    }
//                }
                } else {
                    super.onBackPressed();
                }
            } catch (Exception e) {

            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_edit_profile) {
            ProfileHome fragment = new ProfileHome();
            Bundle bundle = new Bundle();
            bundle.putString("name", "Sansa");
            bundle.putString("country", "USA");
            bundle.putString("check", "1");
            bundle.putInt("image", R.drawable.icon_profile);
            bundle.putInt("image2", R.drawable.icon_profile);
            bundle.putBoolean("comingFromProfileIconClick", true);
            fragment.setArguments(bundle);
            new BaseClass(getApplicationContext()).callFragment(fragment, fragment.getClass().getName(),
                    getSupportFragmentManager());
            // Handle the camera action
        }/* else if (id == R.id.nav_venues) {

        } else if (id == R.id.nav_events) {

        }*/ else if (id == R.id.nav_logout) {
            tinyDB.remove("login_data");
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    private void setNavigationViewListner() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_views);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.search_cross:
                search_main.setText("");
                search_layout.setVisibility(View.GONE);
                layout_toolbar.setVisibility(View.VISIBLE);
                hideSoftKeyboard(this);
                break;
            case R.id.search_main:
                setMyAdapter();

                search_main.showDropDown();
//                search_main.enoughToFilter();
//                search_main.requestFocus();
                //InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                //imm.showSoftInput(search_main, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.notification_btn:
//                new BaseClass(getApplicationContext()).callFragment(new DashboadrFragment(), "dashboard",
//                        getSupportFragmentManager());
              /*  new BaseClass(getApplicationContext()).callFragment(new DashboadrFragment(),
                        new DashboadrFragment().getClass().getName(),
                        getSupportFragmentManager());
*/
                break;
            case R.id.tool_menu:
//                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.END);
                break;

            default:
                break;

        }
    }

    public void bottom_click(View view) {
        int id = view.getId();

        setBottomColors();

        TextView tv = (TextView) findViewById(id);
        tv.setTextColor(getResources().getColor(R.color.colorAccent));

        if (tv.getText().toString().equals("VENUES")) {
            tinyDB.remove("bottom_click");
            tinyDB.putString("bottom_click", "venue");
            new BaseClass(getApplicationContext()).callFragment(new VenueHomeFragment(), new VenueHomeFragment().getClass().getName(),
                    getSupportFragmentManager());

        } else if (tv.getText().toString().equals("EVENTS")) {
            tinyDB.remove("bottom_click");
            tinyDB.putString("bottom_click", "event");
            new BaseClass(getApplicationContext()).callFragment(new VenueHomeFragment(), new VenueHomeFragment().getClass().getName(),
                    getSupportFragmentManager());

        } else if (id == R.id.bottom_four) {
            new BaseClass(getApplicationContext()).callFragment(new InTheKnowFragment(), new InTheKnowFragment().getClass()
                    .getName(), getSupportFragmentManager());
        } else if (id == R.id.bottom_one) {
            new BaseClass(getApplicationContext()).callFragment(new ChatFragment(), new ChatFragment().getClass().getName(),
                    getSupportFragmentManager());
        } else if (id == R.id.notification_btn) {
        /*    new BaseClass(getApplicationContext()).callFragment(new DashboadrFragment(), new DashboadrFragment().getClass().getName(),
                    getSupportFragmentManager());*/
//            new BaseClass(getApplicationContext()).callFragment(new DashboadrFragment(), "dashboard", getSupportFragmentManager());

        }
    }

    public void setBottomColors() {
        TextView tv1 = (TextView) findViewById(R.id.bottom_one);
        TextView tv2 = (TextView) findViewById(R.id.bottom_two);
        TextView tv3 = (TextView) findViewById(R.id.bottom_three);
        TextView tv4 = (TextView) findViewById(R.id.bottom_four);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tv1.setLetterSpacing((float) 0.15);
            tv2.setLetterSpacing((float) 0.15);
            tv3.setLetterSpacing((float) 0.15);
            tv4.setLetterSpacing((float) 0.15);
        }

        tv1.setTextColor(getResources().getColor(R.color.text_color));
        tv2.setTextColor(getResources().getColor(R.color.text_color));
        tv3.setTextColor(getResources().getColor(R.color.text_color));
        tv4.setTextColor(getResources().getColor(R.color.text_color));

    }

    @Override
    public void onApiResponse(Object response) {
       /* if (progressDialog != null && progressDialog.isShowing()){

            progressDialog.hide();
            progressDialog.dismiss();
        }*/
        if (response != null) {
            Log.d(TAG, "MyResponse: " + new Gson().toJson(response));
            UserProfile userListResponse = (UserProfile) response;
            if (userListResponse.getStatus() == 1) {
                txtCity.setText(userListResponse.getUserData().getCity());
                txtName.setText(userListResponse.getUserData().getKnownByName());

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

                Glide.with(getApplicationContext())
                        .asBitmap()
                        .load(userListResponse.getUserData().getPhotoURL())
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                        .apply(RequestOptions.skipMemoryCacheOf(true))
                        .into(new BitmapImageViewTarget(profile_image) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                imgProfile.setImageBitmap(resource);
                                super.setResource(resource);
                            }
                        });

            }
        }
    }

    private String TAG = MainActivity.class.getName();

    @Override
    public void onApiFailure(String message) {
        /*progressDialog.dismiss();*/
        new BaseClass(getApplicationContext()).showToast(message);
    }

    public class GestureListener extends
            GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {

            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {

            OnlineProfileRequest updateProfileRequest =
                    new OnlineProfileRequest(isOffline);
            retrofit2.Call<UpdateProfileResponse> call = APIClient.getInstance().getApiInterface()
                    .onlineProfile(tinyDB.getInt(Constants.USER_ID), updateProfileRequest);
            //new ResponseListner().getResponse(MainActivity.this, call);
            call.enqueue(new Callback<UpdateProfileResponse>() {
                @Override
                public void onResponse(retrofit2.Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {

                    UpdateProfileResponse updateProfileResponse = null;
                    Log.e("response", response.toString());

                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.hide();
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
                        }
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<UpdateProfileResponse> call, Throwable t) {

                }
            });
            progressDialog.show();
            return super.onSingleTapConfirmed(e);
        }

        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {

            ProfileHome fragment = new ProfileHome();
            Bundle bundle = new Bundle();
            bundle.putString("name", "Sansa");
            bundle.putString("country", "USA");
            bundle.putString("check", "1");
            bundle.putInt("image", R.drawable.icon_profile);
            bundle.putInt("image2", R.drawable.icon_profile);
            bundle.putBoolean("comingFromProfileIconClick", true);
            fragment.setArguments(bundle);
            new BaseClass(getApplicationContext()).callFragment(fragment, fragment.getClass().getName(),
                    getSupportFragmentManager());
            //   Toast.makeText(MainActivity.this, "double tap", Toast.LENGTH_SHORT).show();

            return true;
        }
    }

/*    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (new ChatRoomsFragment2() != null) {
            if (!ChatRoomsFragment2.isBottomViewUp) {
                ChatRoomsFragment2.layout_bottom_emoji.setVisibility(View.GONE);
                ChatRoomsFragment2.layout_bottom_bookmark.setVisibility(View.GONE);
            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }*/
}