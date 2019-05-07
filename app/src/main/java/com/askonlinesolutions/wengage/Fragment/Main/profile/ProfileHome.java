package com.askonlinesolutions.wengage.Fragment.Main.profile;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.LanguageActivity;
import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Adapter.AdapterCategoryListBottom1;
import com.askonlinesolutions.wengage.Adapter.AdapterCategoryListBottom1Events;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Helper.CustomLayoutManager;
import com.askonlinesolutions.wengage.Model.EventPreferenceModals;
import com.askonlinesolutions.wengage.Model.Request.ProfileRequest;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
import com.askonlinesolutions.wengage.Model.Response.UpdateProfileResponse;
import com.askonlinesolutions.wengage.Model.VenueCategoryBean;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.database.DatabaseHelper;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.PermissionUtil;
import com.askonlinesolutions.wengage.utils.Progress;
import com.askonlinesolutions.wengage.utils.Utility;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mukesh.tinydb.TinyDB;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;

import static com.askonlinesolutions.wengage.Activity.LanguageActivity.languageModal;
import static com.askonlinesolutions.wengage.Activity.Main.HomeActivity.imgProfile;
import static com.askonlinesolutions.wengage.Activity.Main.HomeActivity.imgProgressTop;
import static com.askonlinesolutions.wengage.Activity.Main.HomeActivity.profile_image;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileHome extends Fragment implements View.OnClickListener, AdapterCategoryListBottom1
        .Interface_RestaurantList1, AdapterCategoryListBottom1Events.Interface_RestaurantListEvents, OnResponseInterface {
    ProgressDialog progressDialog;
    private ImageView iv_arrow_1, iv_arrow_2, iv_heart, iv_dp, iv_check, imgProfileEdit, imgProfileSave;
    private LinearLayout /*arrow_1,*/ arrow_2, cross, tab_three, tab_four, infoLayout, layout_middle_arrow, layout_middle;
    private ImageView edt_profile, edt_prefereces, camera_edit, iv_middle_arrow, iv_middle_image;
    private RecyclerView user_pref_recycler, rv_heart, rv_venues, rv_events, languagesRV;
    private TextView tv_venue, tv_event, tv_custom, tv_description, tv_name, tv_country, tv_profile_text,
            tv_user_info, tv_followers, tv_connect, tvEarning;
    private UpdateProfileModal updateProfileModal;
    private String str_check, type;
    private LinearLayout firstProfileLayout, secondProfileLayout;
    private String placename;
    private String isEditable = "";
    private RecyclerView home3_recycler_custom;
    private Place place;
    private JsonArray jsonArrayLanguages = new JsonArray();
    public static int SELECT_PICTURE_CAMERA = 103;
    private TextView userLanguagesTv;
    private String bitmapString;
    private Dialog errorDialog;
    public static final int CAMERA_REQUEST_FOR_PROFILE = 1;
    //    private RelativeLayout customLayout;
    ArrayList<String> languageStringList = new ArrayList<>();
    DatePickerDialog.OnDateSetListener date;
    private LoginResponse loginResponse = new LoginResponse();
    private TinyDB tinyDB;
    private UserProfile userListResponse;
    private Calendar myCalendar;
    private boolean comingFromProfileIconClick, isInfoVisible;
    private EditText edtFName, edtInOwn, edtHome, edtFavCity, edtFavRes,
            edtExtraHr, edtWork, edtSocial;
    //    private TextView edtCity;
    private ArrayAdapter<String> spinnerArrayAdapter;
    private Progress progress;
    private List<EventPreferenceModals> preferencesBeans = new ArrayList<>();

    public static final int PLACE_PICKER_REQUEST_FOR_PROFILE = 1003;
    private GoogleApiClient mClient;

    private ProfileCategoryListAdapter venueAdapter;
    private AdapterCategoryListBottom1Events adapterCategoryListBottom1Events;
    private boolean status = false;
    private boolean status_middle = false;
    private boolean status_connect = false;
    private TextView userNamesTv, dobTv, incomeLevelTv, residenceCityTv, inYourOwnWordTv, homeTownTv, languageTv,
            favCityTv, favRestaurentTv, extraHourTv, yourWorkTv, dobEditTv, socialMediaTv, languagesEditTv, residenceCityEt, incomeLevelEditTv;
    private EditText userNamesEt, inYourOwnWordEt, homeTownEt, favCityEt,
            favRestaurentEt, extraHourEt, yourWorkEt, socialMediaEt;

    public ProfileHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_home, container, false);
        getMyIntent();
        init(view);

        if (comingFromProfileIconClick) {
            edt_prefereces.setVisibility(View.VISIBLE);
            //   edt_profile.setVisibility(View.VISIBLE);
            iv_arrow_1.setVisibility(View.GONE);
            iv_arrow_2.setVisibility(View.GONE);
            tv_description.setSingleLine(false);
            iv_check.setVisibility(View.GONE);
            camera_edit.setVisibility(View.VISIBLE);
            tv_connect.setVisibility(View.GONE);
            iv_check.setImageResource(R.drawable.ic_photo_camera_outline);
        }
        return view;
    }


    private void getMyIntent() {

        tinyDB = new TinyDB(getContext());
        type = tinyDB.getString("bottom_click");
        loginResponse = new Gson().fromJson(tinyDB.getString("login_data"), LoginResponse.class);
        str_check = getArguments().getString("check");
        comingFromProfileIconClick = getArguments().getBoolean("comingFromProfileIconClick");
    }


    private void init(View view) {

        mClient = new GoogleApiClient
                .Builder(getActivity())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        updateProfileModal = new UpdateProfileModal();
        progressDialog = new BaseClass(getActivity()).creatProgressDialog();
        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        languagesEditTv = view.findViewById(R.id.languagesEditTv);
        home3_recycler_custom = view.findViewById(R.id.home3_recycler_custom);
        LinearLayoutManager layoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        home3_recycler_custom.setLayoutManager(layoutManagaer);
        home3_recycler_custom.setHasFixedSize(true);
        socialMediaEt = view.findViewById(R.id.socialMediaEt);
        yourWorkEt = view.findViewById(R.id.yourWorkEt);
        extraHourEt = view.findViewById(R.id.extraHourEt);
        favRestaurentEt = view.findViewById(R.id.favRestaurentEt);
        favCityEt = view.findViewById(R.id.favCityEt);
        homeTownEt = view.findViewById(R.id.homeTownEt);
        inYourOwnWordEt = view.findViewById(R.id.inYourOwnWordEt);
        residenceCityEt = view.findViewById(R.id.residenceCityEt);
        incomeLevelEditTv = view.findViewById(R.id.incomeLevelEditTv);
        dobEditTv = view.findViewById(R.id.dobEditTv);
        userNamesEt = view.findViewById(R.id.userNamesEt);
        socialMediaTv = view.findViewById(R.id.socialMediaTv);
        yourWorkTv = view.findViewById(R.id.yourWorkTv);
        extraHourTv = view.findViewById(R.id.extraHourTv);
        favRestaurentTv = view.findViewById(R.id.favRestaurentTv);
        favCityTv = view.findViewById(R.id.favCityTv);
        languageTv = view.findViewById(R.id.languageTv);
        homeTownTv = view.findViewById(R.id.homeTownTv);
        inYourOwnWordTv = view.findViewById(R.id.inYourOwnWordTv);
        residenceCityTv = view.findViewById(R.id.residenceCityTv);
        incomeLevelTv = view.findViewById(R.id.incomeLevelTv);
        dobTv = view.findViewById(R.id.dobTv);
        userNamesTv = view.findViewById(R.id.userNamesTv);
//        customLayout = view.findViewById(R.id.customLayout);
//        createLayout = view.findViewById(R.id.createLayout);
        imgProfileEdit = view.findViewById(R.id.fragment_profile_home_img_edit);
        secondProfileLayout = view.findViewById(R.id.secondProfileLayout);
        firstProfileLayout = view.findViewById(R.id.firstProfileLayout);
        imgProfileSave = view.findViewById(R.id.fragment_profile_home_img_save);
        tv_followers = view.findViewById(R.id.home_3_folowers);
        infoLayout = view.findViewById(R.id.fragment_profile_home_info_layout);
        tv_connect = view.findViewById(R.id.home_3_connect);
        layout_middle_arrow = view.findViewById(R.id.home_3_middle_arrow_layout);
        tv_user_info = view.findViewById(R.id.fragment_profile_home_txt_user_info);
        iv_middle_arrow = view.findViewById(R.id.home_3_middle_arrow);
        iv_middle_image = view.findViewById(R.id.home_3_middle_image);
        layout_middle = view.findViewById(R.id.fragment_home3_arrow_down);
        tv_custom = view.findViewById(R.id.fragment_profile_txt_custom);
        edt_prefereces = view.findViewById(R.id.home3_edit_prefrences);
        edt_profile = view.findViewById(R.id.home3_edit_profile);
        camera_edit = view.findViewById(R.id.home_3_camera);
        tv_name = view.findViewById(R.id.home_3_name);
        tv_country = view.findViewById(R.id.home_3_country);
        tv_profile_text = view.findViewById(R.id.home_3_profile_text);
        iv_dp = view.findViewById(R.id.home_3_image);
        iv_check = view.findViewById(R.id.home_3_check);
        tv_venue = view.findViewById(R.id.fragment_profile_home_txt_venue);
        tv_event = view.findViewById(R.id.fragment_profile_home_txt_event);
        tab_three = view.findViewById(R.id.home3_bottom_three);
        tab_four = view.findViewById(R.id.home3_bottom_four);



        /*iv_dp.setImageResource(image);*/
        if (str_check.equals("1")) {
            iv_check.setVisibility(View.VISIBLE);
            tv_followers.setVisibility(View.VISIBLE);
            layout_middle.setVisibility(View.VISIBLE);
            tv_connect.setText("FOLLOW");
            tv_connect.setTextColor(getResources().getColor(R.color.theme_blue));
            tv_connect.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_border_blue));
            status_connect = false;
        } else {
            iv_check.setVisibility(View.GONE);
            tv_followers.setVisibility(View.GONE);
            layout_middle.setVisibility(View.GONE);
            tv_connect.setText("CONNECT");
            tv_connect.setTextColor(getResources().getColor(R.color.colorAccent));
            tv_connect.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_border_pink));
            status_connect = true;
        }
        iv_heart = view.findViewById(R.id.home3_heart);
        iv_heart.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorites_pink));
        cross = view.findViewById(R.id.home3_cross);
        cross.setFocusable(true);
        arrow_2 = view.findViewById(R.id.home3_arrow_2);
        iv_arrow_1 = view.findViewById(R.id.home3_image_arrow_1);
        iv_arrow_2 = view.findViewById(R.id.home3_image_arrow_2);

        user_pref_recycler = view.findViewById(R.id.home3_favorites);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        user_pref_recycler.setLayoutManager(linearLayoutManager);

        rv_heart = (RecyclerView) view.findViewById(R.id.home3_recycler_heart);
        GridLayoutManager gridLayoutManagers = new GridLayoutManager(getContext(), 4);
        rv_heart.setLayoutManager(gridLayoutManagers);
        rv_heart.setHasFixedSize(true);

        rv_venues = view.findViewById(R.id.profile_home_recycler_venues);
        LinearLayoutManager layoutManagaers = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_venues.setLayoutManager(layoutManagaers);

        rv_events = view.findViewById(R.id.home3_recycler_events);
        LinearLayoutManager gLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        CustomLayoutManager manager2 = new CustomLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv_events.setLayoutManager(manager2);

        tv_description = view.findViewById(R.id.home3_description);
//        customLayout.setVisibility(View.GONE);
        incomeLevelEditTv.setOnClickListener(this);
        languageTv.setOnClickListener(this);
        languagesEditTv.setOnClickListener(this);
        dobEditTv.setOnClickListener(this);
        residenceCityEt.setOnClickListener(this);
        onClickMethod();
        getUserDetails();
        //setMyAdapter();
    }

    private void onClickMethod() {
        tv_venue.setOnClickListener(this);
        tv_event.setOnClickListener(this);
        tab_three.setOnClickListener(this);
        tab_four.setOnClickListener(this);
        tv_user_info.setOnClickListener(this);
//        edtDOB.setOnClickListener(this);
        tv_connect.setOnClickListener(this);
        imgProfileEdit.setOnClickListener(this);
        imgProfileSave.setOnClickListener(this);
//        edtCity.setOnClickListener(this);
        layout_middle_arrow.setOnClickListener(this);
        iv_dp.setOnClickListener(this);
//        edtCity.setOnClickListener(this);
        cross.setOnClickListener(this);
//        createLayout.setOnClickListener(this);
//        customLayout.setOnClickListener(this);
        arrow_2.setOnClickListener(this);
    }


    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dobEditTv.setText(sdf.format(myCalendar.getTime()));
    }


    private void setMyAdapter(List<EventPreferenceModals> preferences) {
        preferencesBeans = preferences;
        if (preferences == null) {
        } else {
            if (preferences.size() > 0) {
                AdapterPrefrences adapter = new AdapterPrefrences();
                user_pref_recycler.setAdapter(adapter);
            }
        }
    }

    private void setVenueAdapter(List<UserProfile.UserDataBean.VenuesBean> venues) {
        setBottomColors();
        tv_venue.setTextColor(getResources().getColor(R.color.colorAccent));
        ProfileVenueAdapter adapterHomeList = new ProfileVenueAdapter(getActivity(), venues);
        rv_venues.setAdapter(adapterHomeList);
        rv_venues.setVisibility(View.VISIBLE);
        rv_heart.setVisibility(View.GONE);

    }

    private void setEventAdapter(List<UserProfile.UserDataBean.EventsBean> venues) {
        ProfileEventAdapter adapterHomeList = new ProfileEventAdapter(getActivity(), venues);
        rv_events.setAdapter(adapterHomeList);
    }

    private void setFavAdapter(List<UserProfile.UserDataBean.FavouritesBean> venues) {
        ProfileFavAdapter adapterHomeList = new ProfileFavAdapter(getActivity(), venues);
        rv_heart.setAdapter(adapterHomeList);
    }

    private void setCustomAdapter(List<UserProfile.UserDataBean.CustomBean> customBeans) {
        ProfileCustomAdapter profileCustomAdapter = new ProfileCustomAdapter(getActivity(), customBeans);
        home3_recycler_custom.setAdapter(profileCustomAdapter);
    }


    private void takePhoto() {

        PermissionUtil permissionUtil = new PermissionUtil();
        if (permissionUtil.checkMarshMellowPermission()) {
            if (permissionUtil.verifyPermissions(getActivity(), permissionUtil.getCameraPermissions())
                    && permissionUtil.verifyPermissions(getActivity(), permissionUtil.getGalleryPermissions())) {
                //showAlertDialog();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                getActivity().startActivityForResult(cameraIntent, CAMERA_REQUEST_FOR_PROFILE);
            } else {
                ActivityCompat.requestPermissions(getActivity(), permissionUtil.getCameraPermissions(), SELECT_PICTURE_CAMERA);
            }
        } else {
            // showAlertDialog();
        }
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
       /* if (id == R.id.createLayout) {
            ((HomeActivity) getActivity()).replaceCustomCreateEventFragment();
        } else*/
        if (id == R.id.home3_arrow_2) {

            tinyDB.putString("edit_preference_data", new Gson().toJson(userListResponse));

            ((HomeActivity) getActivity()).replaceEditPreferenceFragment();
        } else if (id == R.id.residenceCityEt) {
            Intent intent = null;
            try {
                intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                        .build(getActivity());
                getActivity().startActivityForResult(intent, PLACE_PICKER_REQUEST_FOR_PROFILE);
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
        } else if (id == R.id.incomeLevelEditTv) {
            openIncomeDialog();
        } else if (id == R.id.languagesEditTv) {
            Intent intent = new Intent(getActivity(), LanguageActivity.class);
            intent.putExtra("languageList", languageStringList);
            startActivity(intent);
        } else if (id == R.id.home_3_image) {
            takePhoto();
        } else if (id == R.id.home3_arrow_1) {
            if (!comingFromProfileIconClick) {
                if (!status) {
                    tv_description.setSingleLine(false);
                    status = true;
                    iv_arrow_1.setImageResource(R.drawable.arrow_up);
                } else {
                    tv_description.setSingleLine(true);
                    status = false;
                    iv_arrow_1.setImageResource(R.drawable.arrow_down);
                }
            }
        } else if (id == R.id.home3_arrow_2) {
            if (!comingFromProfileIconClick) {
                if (user_pref_recycler.getVisibility() == View.VISIBLE) {
                    user_pref_recycler.setVisibility(View.GONE);
                    iv_arrow_2.setImageResource(R.drawable.arrow_down);
                } else {
                    user_pref_recycler.setVisibility(View.VISIBLE);
                    //   edt_prefereces.setVisibility(View.VISIBLE);
                    iv_arrow_2.setImageResource(R.drawable.arrow_up);
                }
            }
        } else if (id == R.id.home3_cross) {
            getActivity().onBackPressed();
        } else if (id == R.id.fragment_profile_home_txt_venue) {
//            customLayout.setVisibility(View.GONE);
            setBottomColors();
            tv_venue.setTextColor(getResources().getColor(R.color.colorAccent));
            rv_heart.setVisibility(View.GONE);
            rv_venues.setVisibility(View.VISIBLE);
            rv_events.setVisibility(View.GONE);
            home3_recycler_custom.setVisibility(View.GONE);
        } else if (id == R.id.fragment_profile_home_txt_event) {
//            customLayout.setVisibility(View.GONE);
            setBottomColors();
            tv_event.setTextColor(getResources().getColor(R.color.colorAccent));
            rv_heart.setVisibility(View.GONE);
            rv_events.setVisibility(View.VISIBLE);
            rv_venues.setVisibility(View.GONE);
            home3_recycler_custom.setVisibility(View.GONE);
        } else if (id == R.id.home3_bottom_three) {
//            customLayout.setVisibility(View.GONE);
            setBottomColors();
            iv_heart.setImageResource(R.drawable.ic_favorites_pink);
            rv_heart.setVisibility(View.VISIBLE);
            rv_venues.setVisibility(View.GONE);
            rv_events.setVisibility(View.GONE);
            home3_recycler_custom.setVisibility(View.GONE);
        } else if (id == R.id.home3_bottom_four) {
//            ((HomeActivity) getActivity()).replaceCustomCreateEventFragment();
//            customLayout.setVisibility(View.GONE);
            setBottomColors();
            iv_heart.setImageResource(R.drawable.ic_favorites);
            tv_custom.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
            rv_heart.setVisibility(View.GONE);
            rv_venues.setVisibility(View.GONE);
            rv_events.setVisibility(View.GONE);
            home3_recycler_custom.setVisibility(View.VISIBLE);

//            customLayout = view.findViewById(R.id.customLayout);

        } else if (id == R.id.home_3_middle_arrow_layout) {
            if (!status_middle) {
                iv_middle_image.setVisibility(View.VISIBLE);
                iv_middle_arrow.setImageResource(R.drawable.arrow_up);
                status_middle = true;
            } else {
                iv_middle_image.setVisibility(View.GONE);
                iv_middle_arrow.setImageResource(R.drawable.arrow_down);
                status_middle = false;
            }
        } else if (id == R.id.dobEditTv) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);  //date is dateSetListener as per your code in question
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        } else if (id == R.id.fragment_profile_home_txt_user_info) {
            if (!isInfoVisible) {
                infoLayout.setVisibility(View.VISIBLE);
                tv_user_info.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);
                isInfoVisible = true;
            } else {
                infoLayout.setVisibility(View.GONE);
                tv_user_info.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
                isInfoVisible = false;
            }

        } else if (id == R.id.fragment_profile_home_img_edit) {
            isEditable = "1";
            secondProfileLayout.setVisibility(View.VISIBLE);
            firstProfileLayout.setVisibility(View.GONE);
            imgProfileEdit.setVisibility(View.GONE);
            imgProfileSave.setVisibility(View.VISIBLE);
//            edtSelectIncome.setEnabled(false);

        } else if (id == R.id.fragment_profile_home_img_save) {

//            private TextView userNamesTv, dobTv, incomeLevelTv, residenceCityTv, inYourOwnWordTv, homeTownTv, languageTv,
//                    favCityTv, favRestaurentTv, extraHourTv, yourWorkTv, dobEditTv, socialMediaTv, languagesEditTv,
// , ;
//            private EditText , , , ,
//                    , , , ;
            isEditable = "0";
            if (userNamesEt.getText().toString().trim().isEmpty()) {
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter your name");
                errorDialog.show();
            } else if (dobEditTv.getText().toString().trim().isEmpty()) {
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter your DOB");
                errorDialog.show();
            } else if (incomeLevelEditTv.getText().toString().trim().isEmpty()) {
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter your income level");
                errorDialog.show();
            } else if (residenceCityEt.getText().toString().trim().isEmpty()) {
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter your residence city");
                errorDialog.show();
            } else if (inYourOwnWordEt.getText().toString().trim().isEmpty()) {
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter your description");
                errorDialog.show();
            } else if (homeTownEt.getText().toString().trim().isEmpty()) {
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter your home town");
                errorDialog.show();
            } else if (languagesEditTv.getText().toString().trim().isEmpty()) {
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter your spoken languages");
                errorDialog.show();
            } else if (favCityEt.getText().toString().trim().isEmpty()) {
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter your favorite city");
                errorDialog.show();
            } else if (favRestaurentEt.getText().toString().trim().isEmpty()) {
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter your favorite restaurent");
                errorDialog.show();
            } else if (extraHourEt.getText().toString().trim().isEmpty()) {
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter your extra hour");
                errorDialog.show();
            } else if (yourWorkEt.getText().toString().trim().isEmpty()) {
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter your work or passion");
                errorDialog.show();
            } else if (socialMediaEt.getText().toString().trim().isEmpty()) {
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter your social media");
                errorDialog.show();
            } else if (jsonArrayLanguages.size() == 0) {
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please select some languages");
                errorDialog.show();
            } else {
                imgProfileEdit.setVisibility(View.VISIBLE);
                imgProfileSave.setVisibility(View.GONE);
                updateProfileModal.setKnownByName(userNamesEt.getText().toString().trim());
                updateProfileModal.setDOB(dobEditTv.getText().toString().trim());
                updateProfileModal.setIncomeLevel(incomeLevelEditTv.getText().toString().trim());
                updateProfileModal.setCity(residenceCityEt.getText().toString().trim());
                updateProfileModal.setDescription(inYourOwnWordEt.getText().toString().trim());
                updateProfileModal.setHometown(homeTownEt.getText().toString().trim());
                updateProfileModal.setLanguage(jsonArrayLanguages);
                updateProfileModal.setFavoutiteCity(favCityEt.getText().toString().trim());
                updateProfileModal.setFavoutiteRestaurant(favRestaurentEt.getText().toString().trim());
                updateProfileModal.setShortDesc(extraHourEt.getText().toString().trim());
                updateProfileModal.setWork(yourWorkEt.getText().toString().trim());
                updateProfileModal.setSocialMedia(socialMediaEt.getText().toString().trim());
                updateProfileModal.setGoogleAddress(residenceCityEt.getText().toString().trim());
                callSetMethod();
                if (place == null) {
                    updateProfileModal.setGooglePlaceId(userListResponse.getUserData().getGooglePlaceId());
                } else {
                    if (place.getId().equals("") || place.getId() == null) {

                    } else {
                        updateProfileModal.setGooglePlaceId(place.getId());
                    }
                }

                if (Utility.isNetworkConnected(getActivity())) {
                    Call<UpdateProfileResponse> call = APIClient.getInstance().getApiInterface()
                            .updateProfiles(tinyDB.getInt(Constants.USER_ID), updateProfileModal);
                    new ResponseListner(this).getResponse(getActivity(), call);
                    progress.show();
                } else {
                    Dialog errorDialog = null;
                    errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
                    errorDialog.show();
                }
            }


        } else {

        }
    }

    private void callSetMethod() {
        userNamesTv.setText(userNamesEt.getText().toString().trim());
        dobTv.setText(dobEditTv.getText().toString().trim());
        incomeLevelTv.setText(incomeLevelEditTv.getText().toString().trim());
        residenceCityTv.setText(residenceCityEt.getText().toString().trim());
        inYourOwnWordTv.setText(inYourOwnWordEt.getText().toString().trim());
        homeTownTv.setText(homeTownEt.getText().toString().trim());
        languageTv.setText(languagesEditTv.getText().toString().trim());
        favCityTv.setText(favCityEt.getText().toString().trim());
        favRestaurentTv.setText(favRestaurentEt.getText().toString().trim());
        extraHourTv.setText(extraHourEt.getText().toString().trim());
        yourWorkTv.setText(yourWorkEt.getText().toString().trim());
        socialMediaTv.setText(socialMediaEt.getText().toString().trim());
        residenceCityTv.setText(residenceCityEt.getText().toString().trim());
    }

    private void openIncomeDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.income_dialog);
        dialog.setTitle("Title...");
        // set the custom dialog components - text, image and button
        TextView first = dialog.findViewById(R.id.first);
        TextView second = dialog.findViewById(R.id.second);
        TextView third = dialog.findViewById(R.id.third);
        TextView fourth = dialog.findViewById(R.id.fourth);
        TextView fifith = dialog.findViewById(R.id.fifith);
        TextView sixth = dialog.findViewById(R.id.sixth);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                incomeLevelEditTv.setText("$0 - $50");
                incomeLevelTv.setText("$0 - $50");
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                incomeLevelEditTv.setText("$50 - $100K");
                incomeLevelTv.setText("$50 - $100K");
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                incomeLevelEditTv.setText("$100 - $200K");
                incomeLevelTv.setText("$100 - $200K");
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                incomeLevelTv.setText("$200 +");
                incomeLevelEditTv.setText("$200 +");
            }
        });
        fifith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                incomeLevelEditTv.setText("Doesn't matter");
                incomeLevelTv.setText("Doesn't matter");
            }
        });
        sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                incomeLevelTv.setText("Rather not say");
                incomeLevelEditTv.setText("Rather not say");
            }
        });
        dialog.show();
    }

    private void setBottomColors() {
        tv_venue.setTextColor(getResources().getColor(R.color.text_color));
        tv_event.setTextColor(getResources().getColor(R.color.text_color));
        iv_heart.setImageResource(R.drawable.ic_favorites);
        tv_custom.setTextColor(getResources().getColor(R.color.text_color));
    }

    @Override
    public void click_interface_restaurant_list_1(int position, String type, int status) {
    }

    @Override
    public void viewItemDetails(int pos) {
    }

    @Override
    public void click_interface_restaurant_list_events(int position, String type) {

    }

    private void getUserDetails() {
        if (Utility.isNetworkConnected(getActivity())) {
            progress.show();
            String profileId = "";
            ProfileRequest profileRequest = new ProfileRequest();
            profileRequest.setProfileId(profileId);
            profileRequest.setUserId(tinyDB.getInt(Constants.USER_ID)/*165*/);
            Call<UserProfile> call = APIClient.getInstance().getApiInterface()
                    .getUserDetails(profileRequest);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.d("TAG", "MyUrl: " + call.request().url());
            Log.d("TAG", "MyRequest: " + new Gson().toJson(profileRequest));
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    private String TAG = ProfileHome.class.getName();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    @Override
    public void onApiResponse(Object response) {
        progress.dismiss();
        if (response != null) {
            if (response instanceof UserProfile) {
                userListResponse = (UserProfile) response;
                if (userListResponse.getStatus() == 1) {
                    userNamesEt.setText(userListResponse.getUserData().getKnownByName());
                    userNamesTv.setText(userListResponse.getUserData().getKnownByName());
                    if (userListResponse.getUserData().getCity() != null && userListResponse.getUserData().getCity().length() > 0) {
                        tv_country.setText(userListResponse.getUserData().getCity());
                    }
                    Glide.with(getContext())
                            .asBitmap()
                            .load(userListResponse.getUserData().getPhotoURL())
                            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                            .apply(RequestOptions.skipMemoryCacheOf(true))
                            .apply(RequestOptions.placeholderOf(getResources().getDrawable(R.drawable.ic_loading)))
                            .into(new BitmapImageViewTarget(iv_dp) {
                                @Override
                                protected void setResource(Bitmap resource) {
                                    iv_dp.setImageBitmap(resource);
                                    super.setResource(resource);
                                }
                            });
                    tv_name.setText(userListResponse.getUserData().getUserName());
                    if (userListResponse.getUserData().getDescription().equals("")) {
                        tv_description.setHint("NA");
                    } else {
                        tv_description.setText(userListResponse.getUserData().getDescription());
                    }

                    tv_user_info.setText("About" + " " + userListResponse.getUserData().getKnownByName());
                    tv_followers.setVisibility(View.GONE);
                    tv_profile_text.setText(userListResponse.getUserData().getKnownByName() + "'s Preferences");
                    if (userListResponse.getUserData().getDOB().equals("")) {
                        dobTv.setHint("DOB");
                        dobEditTv.setHint("DOB");
                    } else {
                        dobTv.setText(userListResponse.getUserData().getDOB());
                        dobEditTv.setText(userListResponse.getUserData().getDOB());
                    }
                    if (userListResponse.getUserData().getIncomeLevel().equals("")) {
                        incomeLevelTv.setHint("Income level");
                        incomeLevelEditTv.setHint("Income level");
                    } else {
                        incomeLevelTv.setText(userListResponse.getUserData().getIncomeLevel());
                        incomeLevelEditTv.setText(userListResponse.getUserData().getIncomeLevel());
                    }
                    if (userListResponse.getUserData().getCity().equals("")) {
                        residenceCityEt.setHint("Residence city");
                        residenceCityTv.setHint("Residence city");
                    } else {
                        residenceCityEt.setText(userListResponse.getUserData().getCity());
                        residenceCityTv.setText(userListResponse.getUserData().getCity());
                    }
                    if (userListResponse.getUserData().getHometown().equals("")) {
                        homeTownTv.setHint("Home town");
                        homeTownEt.setHint("Home town");
                    } else {
                        homeTownTv.setText(userListResponse.getUserData().getHometown());
                        homeTownEt.setText(userListResponse.getUserData().getHometown());
                    }
                    if (userListResponse.getUserData().getDescription().equals("")) {
                        inYourOwnWordTv.setHint("Own word");
                        inYourOwnWordEt.setHint("Own word");
                    } else {
                        inYourOwnWordTv.setText(userListResponse.getUserData().getDescription());
                        inYourOwnWordEt.setText(userListResponse.getUserData().getDescription());
                    }
                    ArrayList<String> language = new ArrayList<>();
                    for (int i = 0; i < userListResponse.getUserData().getLanguage().size(); i++) {
                        if (userListResponse.getUserData().getLanguage().get(i).getSelected() == 1) {
                            languageStringList.add(userListResponse.getUserData().getLanguage().get(i).getLanguageId());
                            jsonArrayLanguages.add(userListResponse.getUserData().getLanguage().get(i).getLanguageId());
                            language.add(userListResponse.getUserData().getLanguage().get(i).getLanguageTitle());
                        }
                    }
                    if (language.size() > 0) {
                        String s = String.valueOf(language);
                        String second = s.substring(1, s.length() - 1);
                        languageTv.setText(second);
                        languagesEditTv.setText(second);
                    } else {
                        languageTv.setHint("Spoken language");
                        languagesEditTv.setHint("Spoken language");
                    }

                    if (userListResponse.getUserData().getFavoutiteCity().equals("")) {
                        favCityTv.setHint("Enter Favorite city");
                        favCityEt.setHint("Enter Favorite city");
                    } else {
                        favCityTv.setText(userListResponse.getUserData().getFavoutiteCity());
                        favCityEt.setText(userListResponse.getUserData().getFavoutiteCity());
                    }
                    if (userListResponse.getUserData().getFavoutiteRestaurant().equals("")) {
                        favRestaurentTv.setHint("Favorite restaurant");
                        favRestaurentEt.setHint("Favorite restaurant");
                    } else {
                        favRestaurentTv.setText(userListResponse.getUserData().getFavoutiteRestaurant());
                        favRestaurentEt.setText(userListResponse.getUserData().getFavoutiteRestaurant());
                    }
                    if (userListResponse.getUserData().getHometown().equals("")) {
                        extraHourTv.setHint("Extra hour");
                        extraHourEt.setHint("Extra hour");
                    } else {
                        extraHourTv.setText(userListResponse.getUserData().getShortDesc());
                        extraHourEt.setText(userListResponse.getUserData().getShortDesc());
                    }
                    if (userListResponse.getUserData().getWork().equals("")) {
                        yourWorkTv.setHint("Work hour");
                        yourWorkEt.setHint("Work hour");
                    } else {
                        yourWorkTv.setText(userListResponse.getUserData().getWork());
                        yourWorkEt.setText(userListResponse.getUserData().getWork());
                    }

                    if (userListResponse.getUserData().getSocialMedia().equals("")) {
                        socialMediaTv.setHint("Social media link");
                        socialMediaEt.setHint("Social media link");
                    } else {
                        socialMediaTv.setText(userListResponse.getUserData().getSocialMedia());
                        socialMediaEt.setText(userListResponse.getUserData().getSocialMedia());
                    }
                    setVenueAdapter(userListResponse.getUserData().getVenues());
                    setEventAdapter(userListResponse.getUserData().getEvents());
                    setFavAdapter(userListResponse.getUserData().getFavourites());
                    setCustomAdapter(userListResponse.getUserData().getCustom());
                    ArrayList<EventPreferenceModals> preferenceModalsList = new ArrayList<>();
                    EventPreferenceModals modal = null;
                    for (int i = 0; i < userListResponse.getUserData().getEventPreferences().size(); i++) {
                        modal = new EventPreferenceModals();
                        modal.setSubCategoryName(userListResponse.getUserData().getEventPreferences().get(i).getSubCategoryName());
                        modal.setIcon(userListResponse.getUserData().getEventPreferences().get(i).getIcon());
                        preferenceModalsList.add(modal);
                    }
                    for (int i = 0; i < userListResponse.getUserData().getVenuePreferences().size(); i++) {
                        modal = new EventPreferenceModals();
//                            VenuePreferenceModal modal = new VenuePreferenceModal();
                        modal.setSubCategoryName(userListResponse.getUserData().getVenuePreferences().get(i).getSubCategoryName());
                        modal.setIcon(userListResponse.getUserData().getVenuePreferences().get(i).getIcon());
                        preferenceModalsList.add(modal);
                    }
                    Log.e("completePreference", preferenceModalsList + "");
                    if (preferenceModalsList.size() > 0) {
                        setMyAdapter(preferenceModalsList);
                    }

                } else {
                    errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, userListResponse.getMessage());
                    errorDialog.show();
                }
            } else {

                UpdateProfileResponse userListResponses = (UpdateProfileResponse) response;
                if (userListResponses.getStatus() == 1) {

                    secondProfileLayout.setVisibility(View.GONE);
                    firstProfileLayout.setVisibility(View.VISIBLE);

                    Glide.with(this)
                            .asGif()
                            .load(R.drawable.load)
                            .into(imgProgressTop);
                    errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, userListResponses.getMessage());
                    errorDialog.show();
                    Glide.with(getActivity())
                            .asBitmap()
                            .load(userListResponses.getProfileInfo().getPhotoURL())
                            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                            .apply(RequestOptions.skipMemoryCacheOf(true))
                            .into(new BitmapImageViewTarget(imgProfile) {
                                @Override
                                protected void setResource(Bitmap resource) {
                                    imgProfile.setImageBitmap(resource);
                                    super.setResource(resource);
                                }
                            });
                    Glide.with(this)
                            .load(userListResponses.getProfileInfo().getPhotoURL())
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
                                    profile_image.setVisibility(View.VISIBLE);
                                    imgProgressTop.setVisibility(View.GONE);
                                    return false;
                                }
                            })
                            .into(profile_image);

                } else {
                    errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, userListResponses.getMessage());
                    errorDialog.show();
                }

            }
           /* } else {

            }*/
        }
    }

    private void setlanguageAdapter() {
        LanguageAdapter languageAdapter = new LanguageAdapter();
        LinearLayoutManager gLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        languagesRV.setLayoutManager(gLayoutManager);
        languagesRV.setAdapter(languageAdapter);
    }

    private void callUpdateDB(UserProfile userListResponse) {
        final DatabaseHelper db = DatabaseHelper.getInstance(getActivity());
        for (int i = 0; i < userListResponse.getUserData().getVenueCategory().size(); i++) {
            db.insertVenueMainCategory(userListResponse.getUserData().getVenueCategory());
        }
        List<VenueCategoryBean> categoryListsDatabase = new ArrayList<>();
        categoryListsDatabase = db.getVenueMainCategory();

    }

    @Override
    public void onApiFailure(String message) {
        Utility.hideDialog();
        if (!message.equals("") && message == null) {
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, userListResponse.getMessage());
            errorDialog.show();
        }

    }


    public void setlocation(Place place) {
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

        residenceCityEt.setText(address);
        residenceCityTv.setText(address);

    }

    public void setProfilePic(Bitmap photo) {
        if (Utility.isNetworkConnected(getActivity())) {
            iv_dp.setImageBitmap(photo);
            bitmapString = BaseClass.BitMapToString(photo);
            //   tinyDB.putObject(Constants.IMAGE_BITMAP,photo);
            updateProfileModal.setPhoto(bitmapString);
            tinyDB.putImage(Constants.IMAGE_BITMAP, "profile_pic", photo);
            callUpdateProfilePicApi(bitmapString, photo);
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    private void callUpdateProfilePicApi(final String bitmapString, final Bitmap photo) {
        if (Utility.isNetworkConnected(getActivity())) {
            progress.show();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("photo", bitmapString);
            Ion.with(this)
                    .load("PUT", "http://107.21.193.184/user/updateProfile/" + tinyDB.getInt(Constants.USER_ID))
                    .setJsonObjectBody(jsonObject).asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            imgProfile.setImageBitmap(photo);
                            profile_image.setImageBitmap(photo);
                            progress.dismiss();
                        }
                    });
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    class AdapterPrefrences extends RecyclerView.Adapter<AdapterPrefrences.MyViewHolder> {


        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView name;
            public ImageView image;

            public MyViewHolder(View view) {
                super(view);

                name = (TextView) itemView.findViewById(R.id.category_name);
                image = (ImageView) itemView.findViewById(R.id.category_image);
            }
        }


        @Override
        public AdapterPrefrences.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_profile1, parent, false);

            AdapterPrefrences.MyViewHolder vh = new AdapterPrefrences.MyViewHolder(v);
            return vh;
        }


        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override

        public void onBindViewHolder(@NonNull AdapterPrefrences.MyViewHolder holder, int position) {
            if (preferencesBeans.size() != 0) {
                Picasso.with(getActivity())
                        .load(preferencesBeans.get(position).getIcon())
                        .placeholder(getActivity().getDrawable(R.drawable.ic_loading))
                        .into(holder.image);
                holder.name.setText(preferencesBeans.get(position).getSubCategoryName());
            }
        }

        @Override
        public int getItemCount() {
            return preferencesBeans.size();
        }


    }

    class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.MyViewHolder> {


        public class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView sub_category_name;
            private LinearLayout item_layout;

            public MyViewHolder(View view) {
                super(view);
                sub_category_name = view.findViewById(R.id.sub_category_name);
                item_layout = view.findViewById(R.id.item_layout);
            }
        }

        //        userListResponse
        @Override
        public LanguageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.subcategory_item_layout, parent, false);

            return new LanguageAdapter.MyViewHolder(itemView);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(final LanguageAdapter.MyViewHolder holder, final int position) {
            holder.sub_category_name.setText(userListResponse.getUserData().getLanguage().get(position).getLanguageTitle());
            if (userListResponse.getUserData().getLanguage().get(position).getSelected() == 1) {
                holder.item_layout.setBackground(getActivity().getResources().getDrawable(R.drawable.shape_filled_pink));
                holder.sub_category_name.setTextColor(getActivity().getResources().getColor(R.color.white));
            } else {
                holder.item_layout.setBackground(getActivity().getResources().getDrawable(R.drawable.shape_border_grey));
                holder.sub_category_name.setTextColor(getActivity().getResources().getColor(R.color.black));
            }
            holder.item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isEditable.equals("1")) {
                        if (userListResponse.getUserData().getLanguage().get(position).getSelected() == 1) {
                            for (int i = 0; i < jsonArrayLanguages.size(); i++) {
                                if (jsonArrayLanguages.get(i).equals(userListResponse.getUserData().getLanguage().get(position).getLanguageId())) {
                                    jsonArrayLanguages.remove(i);
                                }
                            }
                            holder.item_layout.setBackground(getActivity().getResources().getDrawable(R.drawable.shape_border_grey));
                            holder.sub_category_name.setTextColor(getActivity().getResources().getColor(R.color.black));
                        } else {
                            jsonArrayLanguages.add(userListResponse.getUserData().getLanguage().get(position).getLanguageId());
                            holder.item_layout.setBackground(getActivity().getResources().getDrawable(R.drawable.shape_filled_pink));
                            holder.sub_category_name.setTextColor(getActivity().getResources().getColor(R.color.white));
                        }

                    } else {

                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return userListResponse.getUserData().getLanguage().size();
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        callResumeMethod();
    }

    private void callResumeMethod() {
        ArrayList strings = new ArrayList<>();
        if (languageModal != null) {
            jsonArrayLanguages = new JsonArray();
            for (int i = 0; i < languageModal.getLanguages().size(); i++) {
                if (languageModal.getLanguages().get(i).isSelected()) {
                    jsonArrayLanguages.add(languageModal.getLanguages().get(i).getLanguageId());
                    strings.add(languageModal.getLanguages().get(i).getLanguageTitle());

                }
            }
            String s = String.valueOf(strings);
            String second = s.substring(1, s.length() - 1);
            languagesEditTv.setText(second);
        }

    }

    class ProfileCustomAdapter extends RecyclerView.Adapter<ProfileCustomAdapter.MyViewHolder> {

        private Context context;
        private List<UserProfile.UserDataBean.CustomBean> venues;

        public ProfileCustomAdapter(Context context, List<UserProfile.UserDataBean.CustomBean> venues) {
            this.context = context;
            this.venues = venues;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public ImageView iv, imgProgress, butterfly;
            private RelativeLayout venueLayout;
            private TextView venue_city_name, date;
            private RatingBar venue_rating;

            public MyViewHolder(View view) {
                super(view);

                iv = (ImageView) view.findViewById(R.id.home_venue_image);
                imgProgress = view.findViewById(R.id.item_home_list_progress);
                butterfly = view.findViewById(R.id.butterfly);
                venue_city_name = view.findViewById(R.id.venue_city_name);
                venue_rating = view.findViewById(R.id.venue_rating);
                date = view.findViewById(R.id.date);
                venueLayout = view.findViewById(R.id.venueLayout);
                Glide.with(context)
                        .asGif()
                        .load(R.drawable.load)
                        .into(imgProgress);
            }
        }


        @Override
        public ProfileCustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_home_list, parent, false);

            return new ProfileCustomAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ProfileCustomAdapter.MyViewHolder holder, final int position) {
            if (venues.size() != 0) {
                holder.venue_rating.setVisibility(View.GONE);
                holder.butterfly.setVisibility(View.GONE);
                holder.venue_city_name.setText(venues.get(position).getName());
                holder.date.setText(venues.get(position).getStartDate());
                if (venues.get(position).getImageURL() != null) {
                    if (!venues.get(position).getImageURL().equalsIgnoreCase("")) {
                        Glide.with(context)
                                .load(venues.get(position).getImageURL())
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                                Target<Drawable> target, boolean isFirstResource) {
                                        holder.imgProgress.setVisibility(View.GONE);

                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                                   DataSource dataSource, boolean isFirstResource) {
                                        holder.imgProgress.setVisibility(View.GONE);
                                        return false;
                                    }
                                })
                                .into(holder.iv);
                    }
                }
                holder.venueLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        EventDetailsFragment fragment = new EventDetailsFragment();
//                        Bundle bundle = new Bundle();
//                        bundle.putString("event_id", venues.get(position).getEventId());
//                        bundle.putString("type", "event");
//                        fragment.setArguments(bundle);
//                        ((HomeActivity) context).replaceEventDetailsFragment(fragment);
                    }
                });

            }
        }

        @Override
        public int getItemCount() {
            return venues.size();
        }
    }
}