package com.askonlinesolutions.wengage.Fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.CallBacks.NetworkCallback;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventDetailsFragment;
import com.askonlinesolutions.wengage.Fragment.Main.profile.ProfileCategoryListAdapter;
import com.askonlinesolutions.wengage.Fragment.Main.profile.UpdateProfileModal;
import com.askonlinesolutions.wengage.Fragment.Main.profile.UserProfile;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Helper.CustomLayoutManager;
import com.askonlinesolutions.wengage.Model.EventPreferenceModals;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
import com.askonlinesolutions.wengage.Model.Response.UpdateProfileResponse;
import com.askonlinesolutions.wengage.Model.UserProfileDetailModal;
import com.askonlinesolutions.wengage.Networks.Network;
import com.askonlinesolutions.wengage.Networks.NetworkConstants;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.ApiResponse;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
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
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mukesh.tinydb.TinyDB;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment implements View.OnClickListener, OnResponseInterface, NetworkCallback {
    private LinearLayout home3_arrow_2, arrowDown, home3_bottom_four, home3_bottom_three, tab_three, profileLayout,
            tab_four, infoLayout, layout_middle_arrow, layout_middle, createLayout;
    private RelativeLayout customLayout, edit_and_save_layout;
    private ImageView iv_arrow_1, iv_arrow_2, iv_heart, userImage, iv_check, edt_profile, edt_prefereces,
            camera_edit, iv_middle_arrow, iv_middle_image;
    private TextView tv_venue, tv_event, tv_custom, connectTv, folowers, connect, userName, userLocation,
            userNamesTv, dobTv, incomeLevelTv, residenceCityTv, inYourOwnWordTv, homeTownTv,
            languagesTv, favCityTv, favRestaurentTv, extraHourTv, yourWorkTv, socialMediaTv, headerProfileTv,home_3_profile_text;
    private RecyclerView user_pref_recycler, rv_heart, rv_venues, rv_events, home3_recycler_custom, languagesRV;
    private String str_check, type;
    private LoginResponse loginResponse = new LoginResponse();
    private TinyDB tinyDB;
    private ArrayAdapter<String> spinnerArrayAdapter;
    private Progress progress;
    private int senderId, receiverId;
    private boolean status = false;
    private boolean status_middle = false;
    private boolean status_connect = false;
    private ProfileCategoryListAdapter venueAdapter;

    private List<EventPreferenceModals> preferencesBeans = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile_home, container, false);
        tinyDB = new TinyDB(getActivity());
        receiverId = tinyDB.getInt(Constants.VENUES_USER_ID);
        senderId = tinyDB.getInt(Constants.USER_ID);
        initUI(view);
        callUserProfileDetail();
        return view;
    }

    private void callUserProfileDetail() {
        if (Utility.isNetworkConnected(getActivity())) {
            progress.show();
            int profileId = tinyDB.getInt(Constants.VENUES_USER_ID);
            int userId = tinyDB.getInt(Constants.USER_ID);
            JsonObject object = new JsonObject();
            object.addProperty("userId", userId);
            object.addProperty("profileId", profileId);
            Network.getNetworkResponseWithOutAuth(getActivity(), object, 60 * 1000, NetworkConstants.USER_PROFILE,
                    this, NetworkConstants.REQUEST_TYPE_USER_PROFILE);
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    private void initUI(View view) {
        UpdateProfileModal updateProfileModal = new UpdateProfileModal();

        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);


        TextView languageTv = view.findViewById(R.id.languageTv);
        home_3_profile_text = view.findViewById(R.id.home_3_profile_text);
        home3_bottom_three = view.findViewById(R.id.home3_bottom_three);
        home3_bottom_four = view.findViewById(R.id.home3_bottom_four);
        userImage = view.findViewById(R.id.userImage);
        profileLayout = view.findViewById(R.id.profileLayout);
        tv_venue = view.findViewById(R.id.tv_venue);
        tv_venue.setOnClickListener(this);
        tv_event = view.findViewById(R.id.tv_event);
        tv_event.setOnClickListener(this);
        tv_custom = view.findViewById(R.id.tv_custom);
        tv_custom.setOnClickListener(this);
        home3_bottom_four.setOnClickListener(this);
        home3_bottom_three.setOnClickListener(this);
        home3_bottom_three = view.findViewById(R.id.home3_bottom_three);
        home3_bottom_three.setOnClickListener(this);

        headerProfileTv = view.findViewById(R.id.headerProfileTv);
        connectTv = view.findViewById(R.id.connectTv);
        arrowDown = view.findViewById(R.id.arrowDown);
        folowers = view.findViewById(R.id.folowers);
//        connect = view.findViewById(R.id.connect);
        userName = view.findViewById(R.id.userName);
        userLocation = view.findViewById(R.id.userLocation);
        userNamesTv = view.findViewById(R.id.userNamesTv);
        dobTv = view.findViewById(R.id.dobTv);
        incomeLevelTv = view.findViewById(R.id.incomeLevelTv);
        residenceCityTv = view.findViewById(R.id.residenceCityTv);
        inYourOwnWordTv = view.findViewById(R.id.inYourOwnWordTv);
        homeTownTv = view.findViewById(R.id.homeTownTv);
        languagesTv = view.findViewById(R.id.languagesTv);
        favCityTv = view.findViewById(R.id.favCityTv);
        favRestaurentTv = view.findViewById(R.id.favRestaurentTv);
        extraHourTv = view.findViewById(R.id.extraHourTv);
        yourWorkTv = view.findViewById(R.id.yourWorkTv);
        socialMediaTv = view.findViewById(R.id.socialMediaTv);
        iv_heart = view.findViewById(R.id.iv_heart);
        iv_heart.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorites_pink));


        LinearLayout cross = view.findViewById(R.id.home3_cross);
        cross.setOnClickListener(this);
        connectTv.setOnClickListener(this);
        arrowDown.setOnClickListener(this);

        /////Set Recycler View/////
        user_pref_recycler = view.findViewById(R.id.home3_favorites);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        user_pref_recycler.setLayoutManager(linearLayoutManager);

        rv_heart = view.findViewById(R.id.home3_recycler_heart);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        rv_heart.setLayoutManager(gridLayoutManager);
        rv_heart.setHasFixedSize(true);


        RecyclerView home3_favoritesEvent = view.findViewById(R.id.home3_favoritesEvent);
        LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        home3_favoritesEvent.setLayoutManager(linearLayoutManagers);

        home3_recycler_custom = view.findViewById(R.id.home3_recycler_custom);

        rv_venues = view.findViewById(R.id.profile_home_recycler_venues);
        LinearLayoutManager layoutManagaer
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_venues.setLayoutManager(layoutManagaer);

        rv_events = view.findViewById(R.id.home3_recycler_events);
        LinearLayoutManager gLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        CustomLayoutManager manager2 = new CustomLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv_events.setLayoutManager(manager2);

    }


    private void setMyAdapter(ArrayList<EventPreferenceModals> preferences) {
        preferencesBeans = preferences;
        if (preferences == null) {
        } else {
            if (preferences.size() > 0) {
                AdapterPrefrences adapter = new AdapterPrefrences();
                user_pref_recycler.setAdapter(adapter);
            }
        }


    }

    private void setVenueAdapter(List<UserProfileDetailModal.UserDataBean.VenuesBean> venues) {
        setBottomColors();
        tv_venue.setTextColor(getResources().getColor(R.color.colorAccent));
        ProfileVenueAdapter adapterHomeList = new ProfileVenueAdapter(getActivity(), venues);
        rv_venues.setAdapter(adapterHomeList);
        rv_venues.setVisibility(View.VISIBLE);
        rv_heart.setVisibility(View.GONE);
    }

    private void setEventAdapter(List<UserProfileDetailModal.UserDataBean.EventsBean> venues) {
        ProfileEventAdapter adapterHomeList = new ProfileEventAdapter(getActivity(), venues);
        rv_events.setAdapter(adapterHomeList);
    }

    private void setFavAdapter(List<UserProfileDetailModal.UserDataBean.FavouritesBean> venues) {
        ProfileFavAdapter adapterHomeList = new ProfileFavAdapter(getActivity(), venues);
        rv_heart.setAdapter(adapterHomeList);
    }

    private void setCustomAdapter(List<UserProfileDetailModal.UserDataBean.VenuesBean> venues) {
        EventSubCatListAdapter adapterHomeList = new EventSubCatListAdapter(getActivity(), venues);
        LinearLayoutManager gLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        home3_recycler_custom.setLayoutManager(gLayoutManager);
        home3_recycler_custom.setAdapter(adapterHomeList);
    }

    private void setBottomColors() {
        tv_venue.setTextColor(getResources().getColor(R.color.text_color));
        tv_event.setTextColor(getResources().getColor(R.color.text_color));
        iv_heart.setImageResource(R.drawable.ic_favorites);
        tv_custom.setTextColor(getResources().getColor(R.color.text_color));
        // iv_recycle.setImageResource(R.drawable.ic_back);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
       /* if (id == R.id.createLayout) {

            ((HomeActivity) getActivity()).replaceCustomCreateEventFragment();
        }*/
        if (id == R.id.home3_arrow_2) {

            ((HomeActivity) getActivity()).replaceEditPreferenceFragment();
        }
        if (id == R.id.home_3_image) {
//            takePhoto();
        }
        if (id == R.id.home3_arrow_1) {
//            if (!comingFromProfileIconClick) {
//                if (!status) {
//                    tv_description.setSingleLine(false);
//                    status = true;
//                    iv_arrow_1.setImageResource(R.drawable.arrow_up);
//                } else {
//                    tv_description.setSingleLine(true);
//                    status = false;
//                    iv_arrow_1.setImageResource(R.drawable.arrow_down);
//                }
//            }dfgdfg
        } else if (id == R.id.arrowDown) {
            if (profileLayout.getVisibility() == View.VISIBLE) {
                profileLayout.setVisibility(View.GONE);
            } else {
                profileLayout.setVisibility(View.VISIBLE);
            }

        } else if (id == R.id.home3_cross) {
            getFragmentManager().popBackStackImmediate();
        } else if (id == R.id.tv_venue) {
            setBottomColors();
            tv_venue.setTextColor(getResources().getColor(R.color.colorAccent));
            rv_heart.setVisibility(View.GONE);
            rv_venues.setVisibility(View.VISIBLE);
            rv_events.setVisibility(View.GONE);
            home3_recycler_custom.setVisibility(View.GONE);
        } else if (id == R.id.tv_event) {
            setBottomColors();
            tv_event.setTextColor(getResources().getColor(R.color.colorAccent));
            rv_heart.setVisibility(View.GONE);
            rv_events.setVisibility(View.VISIBLE);
            rv_venues.setVisibility(View.GONE);
        } else if (id == R.id.home3_bottom_three) {
            setBottomColors();
            iv_heart.setImageResource(R.drawable.ic_favorites_pink);
            rv_heart.setVisibility(View.VISIBLE);
            rv_venues.setVisibility(View.GONE);
            rv_events.setVisibility(View.GONE);
        } else if (id == R.id.home3_bottom_four) {
//            ((HomeActivity) getActivity()).replaceCustomCreateEventFragment();
//            customLayout.setVisibility(View.VISIBLE);
            tv_venue.setTextColor(getResources().getColor(R.color.text_color));
            tv_event.setTextColor(getResources().getColor(R.color.text_color));
            iv_heart.setImageResource(R.drawable.ic_favorites);
//                setBottomColors();
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
        } else if (id == R.id.connectTv) {
            if (connectTv.getText().toString().trim().equalsIgnoreCase("connect")) {
                callConnectApi();
            } else {
                Toast.makeText(getActivity(), "You have already sent request.", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.fragment_profile_home_txt_user_info) {
//            if (!isInfoVisible) {
//                infoLayout.setVisibility(View.VISIBLE);
//                tv_user_info.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);
//                isInfoVisible = true;
//            } else {
//                infoLayout.setVisibility(View.GONE);
//                tv_user_info.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
//                isInfoVisible = false;
//            }

        } else {

        }
    }

    private void callConnectApi() {
        if (Utility.isNetworkConnected(getActivity())) {
            progress.show();
            Call<ApiResponse> call = APIClient.getInstance().getApiInterface()
                    .sendRequest(senderId, receiverId);
            new ResponseListner(this).getResponse(getContext(), call);
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    @Override
    public void onApiResponse(Object response) {
        progress.dismiss();
        if (response != null) {
            if (response instanceof ApiResponse) {
                connectTv.setText("Pending");
            }
        }
    }

    @Override
    public void onApiFailure(String message) {
        progress.dismiss();
        Utility.hideDialog();
        new BaseClass(getActivity()).showToast(message);
    }


    @Override
    public void onNetworkSuccess(JsonObject result, int status, int reqeustCode) {
        progress.dismiss();
        if (NetworkConstants.REQUEST_TYPE_USER_PROFILE == reqeustCode) {
            switch (status) {
                case 200:
                    UserProfileDetailModal userListResponse = new Gson().fromJson(result, UserProfileDetailModal.class);
                    if (userListResponse.getStatus() == 1) {
                        callDataSetMethod(userListResponse);
                    } else {

                    }
            }
        }
    }

    private void callDataSetMethod(UserProfileDetailModal userListResponse) {
        if (userListResponse.getUserData().getInfluencer() == 1) {
            connectTv.setVisibility(View.GONE);
        } else {
            connectTv.setVisibility(View.VISIBLE);
        }
        if (userListResponse.getUserData().getConnect().equals("Y")) {
            connectTv.setText("Connected");
        } else {
            if (userListResponse.getUserData().getReqStatus().equals("RECEIVED")) {
                connectTv.setText("Pending");
            } else {
                if (userListResponse.getUserData().getReqStatus().equals("SENT")) {
                    connectTv.setText("Pending");
                } else {
                    connectTv.setText("Connect");
                }
            }
        }

        folowers.setText(userListResponse.getUserData().getFollowers() + " " + "Follower");
        if (userListResponse.getUserData().getUserName().equals("")) {
            userName.setText("NA");
        } else {
            userName.setText(userListResponse.getUserData().getUserName());
            home_3_profile_text.setText(userListResponse.getUserData().getUserName()+"'s Preferences");
        }
        if (userListResponse.getUserData().getCity().equals("")) {
            userLocation.setText("NA");
        } else {
            userLocation.setText(", " + userListResponse.getUserData().getCity());
        }

        headerProfileTv.setText("About" + " " + userListResponse.getUserData().getUserName());
        if (userListResponse.getUserData().getKnownByName().equals("")) {
            userNamesTv.setText("NA");
        } else {
            userNamesTv.setText(userListResponse.getUserData().getKnownByName());
        }
        if (userListResponse.getUserData().getDOB().equals("")) {
            dobTv.setText("NA");
        } else {
            dobTv.setText(userListResponse.getUserData().getDOB());
        }
        if (userListResponse.getUserData().getIncomeLevel().equals("")) {
            incomeLevelTv.setText("NA");
        } else {
            incomeLevelTv.setText(userListResponse.getUserData().getIncomeLevel());
        }
        if (userListResponse.getUserData().getCity().equals("")) {
            residenceCityTv.setText("NA");
        } else {
            residenceCityTv.setText(userListResponse.getUserData().getCity());
        }
        if (userListResponse.getUserData().getDescription().equals("")) {
            inYourOwnWordTv.setText("NA");
        } else {
            inYourOwnWordTv.setText(userListResponse.getUserData().getDescription());
        }
        if (userListResponse.getUserData().getHometown().equals("")) {
            homeTownTv.setText("NA");
        } else {
            homeTownTv.setText(userListResponse.getUserData().getHometown());
        }

        if (userListResponse.getUserData().getHometown().equals("")) {
            homeTownTv.setText("NA");
        } else {
            homeTownTv.setText(userListResponse.getUserData().getHometown());
        }

        ArrayList<String> language = new ArrayList<>();
        for (int i = 0; i < userListResponse.getUserData().getLanguage().size(); i++) {
            if (userListResponse.getUserData().getLanguage().get(i).getSelected() == 1) {
                language.add(userListResponse.getUserData().getLanguage().get(i).getLanguageTitle());
            }

        }
        String s = String.valueOf(language);
        String second = s.substring(1, s.length() - 1);
        languagesTv.setText(second);
        if (userListResponse.getUserData().getFavoutiteCity().equals("")) {
            favCityTv.setText("NA");
        } else {
            favCityTv.setText(userListResponse.getUserData().getFavoutiteCity());
        }
        if (userListResponse.getUserData().getFavoutiteRestaurant().equals("")) {
            favRestaurentTv.setText("NA");
        } else {
            favRestaurentTv.setText(userListResponse.getUserData().getFavoutiteRestaurant());
        }

        if (userListResponse.getUserData().getFavoutiteRestaurant().equals("")) {
            favRestaurentTv.setText("NA");
        } else {
            favRestaurentTv.setText(userListResponse.getUserData().getFavoutiteRestaurant());
        }

        extraHourTv.setText("NA");
        if (userListResponse.getUserData().getWork().equals("")) {
            yourWorkTv.setText("NA");
        } else {
            yourWorkTv.setText(userListResponse.getUserData().getWork());
        }

        if (userListResponse.getUserData().getSocialMedia().equals("")) {
            socialMediaTv.setText("NA");
        } else {
            socialMediaTv.setText(userListResponse.getUserData().getSocialMedia());
        }


        Glide.with(getContext())
                .asBitmap()
                .load(userListResponse.getUserData().getPhotoURL())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .apply(RequestOptions.placeholderOf(getResources().getDrawable(R.drawable.ic_loading)))
                .into(new BitmapImageViewTarget(userImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        userImage.setImageBitmap(resource);
                        super.setResource(resource);
                    }
                });

        setVenueAdapter(userListResponse.getUserData().getVenues());
        setEventAdapter(userListResponse.getUserData().getEvents());
        setFavAdapter(userListResponse.getUserData().getFavourites());
        setCustomAdapter(userListResponse.getUserData().getVenues());

        ////For Preference////
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

    }


    @Override
    public void onNetworkTimeout(String message) {
        progress.dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    class EventSubCatListAdapter extends RecyclerView.Adapter<EventSubCatListAdapter.MyViewHolder> {
        private Context context;
        List<UserProfileDetailModal.UserDataBean.VenuesBean> venues;

        public EventSubCatListAdapter(FragmentActivity activity, List<UserProfileDetailModal.UserDataBean.VenuesBean> venues) {
            this.context = activity;
            this.venues = venues;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView iv_image, iv_circle, iv_butterfly, iv_check, iv_bookmarks;
            TextView tv_ad, tv_name, tv_review, /*tv_distance*/
                    tv_interested, no_venue;
            LinearLayout restaurant_layout;
            int isBookMarked = 0, isInterested = 0;
            RatingBar ratingBar;

            public MyViewHolder(View view) {
                super(view);
                ratingBar = view.findViewById(R.id.item_res_list_rating);
                iv_image = view.findViewById(R.id.restaurant_list_events_image);
                iv_check = view.findViewById(R.id.restaurant_list_events_check);
                iv_butterfly = view.findViewById(R.id.restaurant_list_events_butterfly);
                iv_circle = view.findViewById(R.id.restaurant_list_events_circle);
                iv_bookmarks = view.findViewById(R.id.restaurant_list_events_bookmarks);
                tv_name = view.findViewById(R.id.restaurant_list_events_name);
                tv_review = view.findViewById(R.id.restaurant_list_events_reviews);
                tv_interested = view.findViewById(R.id.restaurant_list_events_interested);
            }
        }

        @Override
        public EventSubCatListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_restaurant_list_events, parent, false);

            return new EventSubCatListAdapter.MyViewHolder(itemView);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(final EventSubCatListAdapter.MyViewHolder holder, final int position) {


        }

        @Override
        public int getItemCount() {
            return 0;
        }


    }


    class ProfileEventAdapter extends RecyclerView.Adapter<ProfileEventAdapter.MyViewHolder> {

        private Context context;
        private List<UserProfileDetailModal.UserDataBean.EventsBean> venues;

        public ProfileEventAdapter(Context context, List<UserProfileDetailModal.UserDataBean.EventsBean> venues) {
            this.context = context;
            this.venues = venues;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public ImageView iv, imgProgress;
            private RelativeLayout venueLayout;

            public MyViewHolder(View view) {
                super(view);

                iv = (ImageView) view.findViewById(R.id.home_venue_image);
                imgProgress = view.findViewById(R.id.item_home_list_progress);
                venueLayout = view.findViewById(R.id.venueLayout);
                Glide.with(context)
                        .asGif()
                        .load(R.drawable.load)
                        .into(imgProgress);
            }
        }


        @Override
        public ProfileEventAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_home_list, parent, false);

            return new ProfileEventAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ProfileEventAdapter.MyViewHolder holder, final int position) {
            if (venues.size() != 0) {
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
                        EventDetailsFragment fragment = new EventDetailsFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("event_id", venues.get(position).getEventId());
                        bundle.putString("type", "event");
                        fragment.setArguments(bundle);
                        ((HomeActivity) getActivity()).replaceEventDetailsFragment(fragment);
                    }
                });

            }
        }

        @Override
        public int getItemCount() {
            return venues.size();
        }
    }

    class ProfileVenueAdapter extends RecyclerView.Adapter<ProfileVenueAdapter.MyViewHolder> {

        private Context context;
        List<UserProfileDetailModal.UserDataBean.VenuesBean> venues;

        public ProfileVenueAdapter(Context context, List<UserProfileDetailModal.UserDataBean.VenuesBean> venues) {
            this.context = context;
            this.venues = venues;
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {

            public ImageView iv, imgProgress;
            TextView txtName;
            private RelativeLayout venueLayout;

            public MyViewHolder(View view) {
                super(view);
                venueLayout = view.findViewById(R.id.venueLayout);
                txtName = view.findViewById(R.id.venue_city_name);
                iv = (ImageView) view.findViewById(R.id.home_venue_image);
                imgProgress = view.findViewById(R.id.item_home_list_progress);
                Glide.with(context)
                        .asGif()
                        .load(R.drawable.load)
                        .into(imgProgress);
            }
        }


        @Override
        public ProfileVenueAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_home_list, parent, false);

            return new ProfileVenueAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ProfileVenueAdapter.MyViewHolder holder, final int position) {
            if (venues.size() != 0 && venues.get(position).getImageURL() != null) {
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
                holder.txtName.setText(venues.get(position).getName());
                holder.venueLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RestaurantVenueDetails fragment = new RestaurantVenueDetails();
                        Bundle bundle = new Bundle();
                        bundle.putString("venue_id", venues.get(position).getVenueId());
                        fragment.setArguments(bundle);
                        ((HomeActivity) context).replaceVenueDetailFragment(fragment);
                    }
                });
            } else {

            }
        }

        @Override
        public int getItemCount() {
            return venues.size();
        }
    }

    class ProfileFavAdapter extends RecyclerView.Adapter<ProfileFavAdapter.MyViewHolder> {

        private Context context;
        List<UserProfileDetailModal.UserDataBean.FavouritesBean> venues;

        public ProfileFavAdapter(Context context, List<UserProfileDetailModal.UserDataBean.FavouritesBean> venues) {
            this.context = context;
            this.venues = venues;
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {

            public ImageView iv;

            public MyViewHolder(View view) {
                super(view);

                iv = (ImageView) view.findViewById(R.id.home_list_image);
            }
        }


        @Override
        public ProfileFavAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_home_list3, parent, false);

            return new ProfileFavAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ProfileFavAdapter.MyViewHolder holder, final int position) {
            if (venues.size() != 0 && venues.get(position).getImageURL() != null) {
                if (!venues.get(position).getImageURL().equalsIgnoreCase("")) {
                    Picasso.with(context).load(venues.get(position).getImageURL())
                            .placeholder(R.drawable.ic_loading).into(holder.iv);
                }

            } else {

            }
        }

        @Override
        public int getItemCount() {
            return venues.size();
        }
    }

    class AdapterPrefrences extends RecyclerView.Adapter<AdapterPrefrences.MyViewHolder> {
        List<UserProfile.UserDataBean.PreferencesBean> preferences;

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


}
