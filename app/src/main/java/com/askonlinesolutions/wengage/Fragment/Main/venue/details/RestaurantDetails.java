package com.askonlinesolutions.wengage.Fragment.Main.venue.details;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Adapter.AdapterCategoryListBottom1;
import com.askonlinesolutions.wengage.Adapter.AdapterCategoryListBottom1Events;
import com.askonlinesolutions.wengage.Fragment.Main.venue.details.rate.event.RateResponse;
import com.askonlinesolutions.wengage.Fragment.Main.venue.details.rate.event.RatingAdapter;
import com.askonlinesolutions.wengage.Fragment.Main.venue.details.rate.event.RatingRequest;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Helper.CustomLayoutManager;
import com.askonlinesolutions.wengage.Model.Response.VenueDetailsResponse;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.ApiResponse;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Utility;
import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.mukesh.tinydb.TinyDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantDetails extends Fragment implements View.OnClickListener,
        AdapterCategoryListBottom1Events.Interface_RestaurantListEvents,
        AdapterCategoryListBottom1.Interface_RestaurantList1, OnResponseInterface {

    public RestaurantDetails() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {

            /*Toast.makeText(getContext(), "...", Toast.LENGTH_SHORT).show();*/
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
            return;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_details, container, false);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getMyIntent();
        init();
        mapView = (MapView) getView().findViewById(R.id.restaurant_details_mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        try {
            MapsInitializer.initialize(this.getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                googleMap = map;

                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    /*Toast.makeText(getContext(), "...", Toast.LENGTH_SHORT).show();*/
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            1);
                    return;
                }
                googleMap.setMyLocationEnabled(true);
                googleMap.setOnMyLocationChangeListener(onMyLocationChangeListener);
            }
        });

    }

    public static StringBuffer stringBuffer = new StringBuffer();
    double latitude, longitude;
    private int user_id;

    public StringBuffer getAddress(LatLng latLng) throws IOException {
        Geocoder geocoder;
        List<Address> addresses;
        StringBuffer result = new StringBuffer();
        geocoder = new Geocoder(getContext(), Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            /*String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();*/
            result.append(city);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private ImageView progressBar;
    private LinearLayout layout_ratingbar, mainLayout;
    private RecyclerView interested_recycler, rv_venues, rv_rating, featureRecyclerView;
    private LinearLayout cross, bottom_one, bottom_two, bottom_three, bottom_four, arrow;
    private ImageView iv_one, iv_four, iv_arrow;
    private TextView iv_two, monday, tuesday, wednesday, thrusday, friday, saturday, sunday;
    private TextView rest_desc, iv_three;

    private boolean status = true;

    private TextView tv_above_description;

    private ArrayList<Integer> profile_images = new ArrayList<>();
    private ArrayList<Integer> profile_images_2 = new ArrayList<>();
    private ArrayList<String> profile_names = new ArrayList<>();
    private ArrayList<String> profile_country = new ArrayList<>();
    private ArrayList<String> profile_check = new ArrayList<>();

    private MapView mapView;
    private GoogleMap googleMap;

    private GoogleMap map;

    private ImageView iv_circle, iv_image, iv_butterfly, iv_check, iv_bookmarks;
    private TextView tv_ad, tv_name, tv_review, tv_km, tv_interested;
    private int str_image, str_image_2, status_bookmark;
    private String str_circle, str_ad, str_name, str_review, str_km, str_butterfly,
            str_check, str_type, venueId, eventId;
    private float rate;
    private TinyDB tinyDB;
    private RatingBar ratingBar, rateUsRatingBar;

    private void getMyIntent() {
        venueId = getArguments().getString("venue_id");
//        eventId=getArguments().getString("event_Id");
    }

    private RelativeLayout layout_venues, layout_events;

    // Events
    private ImageView iv_image_events, iv_circle_events, iv_bookmarks_events, iv_check_events,
            iv_butterfly_events, iv_description_arrow_events;
    private LinearLayout layout_description_arrow_events, infoLinearLayout;
    private TextView tv_name_events, tv_interested_events, tv_reviews_events, tv_description_events,
            tvTimeInfo, txtAddress, txtParking, txtContact, txtPayment, txtSubmitRate;

    private TextView tv_title;

    private void init() {
        monday = getView().findViewById(R.id.monday);
        tuesday = getView().findViewById(R.id.tuesday);
        wednesday = getView().findViewById(R.id.wednesday);
        thrusday = getView().findViewById(R.id.thrusday);
        friday = getView().findViewById(R.id.friday);
        saturday = getView().findViewById(R.id.saturday);
        sunday = getView().findViewById(R.id.sunday);
        ratingBar = getView().findViewById(R.id.fragment_res_detail_rating);
        mainLayout = getView().findViewById(R.id.fragment_res_detail_layout);
        progressBar = getView().findViewById(R.id.fragment_res_detail_progress);
        layout_venues = getView().findViewById(R.id.restaurant_details_layout_venues);
        layout_events = getView().findViewById(R.id.restaurant_details_layout_events);

        tinyDB = new TinyDB(getContext());
        user_id = tinyDB.getInt(Constants.USER_ID);
        tv_title = getView().findViewById(R.id.category_list_title);
        Glide.with(getActivity()).asGif().load(R.drawable.load).into(progressBar);
        tv_title.setText("Venue");
        featureRecyclerView = getView().findViewById(R.id.fragment_venue_detail_info_features_list);
        rv_rating = getView().findViewById(R.id.fragment_venue_detail_info_rating_recycler);
        iv_circle = getView().findViewById(R.id.restaurant_details_circle);
        iv_image = getView().findViewById(R.id.restaurant_details_image);
        iv_butterfly = getView().findViewById(R.id.restaurant_details_butterfy);
        iv_check = getView().findViewById(R.id.restaurant_details_check);
        tv_ad = getView().findViewById(R.id.restaurant_details_ad);
        tv_name = getView().findViewById(R.id.restaurant_details_name);
        tv_review = getView().findViewById(R.id.restaurant_details_review);
        tv_km = getView().findViewById(R.id.restaurant_details_km);
        iv_bookmarks = getView().findViewById(R.id.restaurant_details_bookmarks_venues);
        tv_interested = getView().findViewById(R.id.restaurant_details_interested_venues);

        iv_bookmarks.setOnClickListener(this);
        tv_interested.setOnClickListener(this);

        //Events
        txtPayment = getView().findViewById(R.id.fragment_venue_detail_info_payment);
        tvTimeInfo = getView().findViewById(R.id.fragment_venue_detail_info_time);
        txtParking = getView().findViewById(R.id.fragment_venue_detail_info_parking);
        txtContact = getView().findViewById(R.id.fragment_venue_detail_info_contact);
        txtAddress = getView().findViewById(R.id.fragment_venue_detail_info_address);
        rateUsRatingBar = getView().findViewById(R.id.fragment_venue_detail_info_rateus);
        txtSubmitRate = getView().findViewById(R.id.fragment_venue_detail_info_rate_btn);

        infoLinearLayout = getView().findViewById(R.id.fragment_venue_detail_info_layout);
        iv_image_events = getView().findViewById(R.id.restaurant_details_events_image);
        iv_circle_events = getView().findViewById(R.id.restaurant_details_events_circle);
        iv_bookmarks_events = getView().findViewById(R.id.restaurant_details_events_bookmarks);
        iv_check_events = getView().findViewById(R.id.restaurant_details_events_check);
        iv_butterfly_events = getView().findViewById(R.id.restaurant_details_events_butterfly);
        iv_description_arrow_events = getView().findViewById(R.id.restaurant_details_description_arrow);
        tv_name_events = getView().findViewById(R.id.restaurant_details_events_name);
        tv_interested_events = getView().findViewById(R.id.restaurant_details_events_interested);
        tv_reviews_events = getView().findViewById(R.id.restaurant_details_events_reviews);
        tv_description_events = getView().findViewById(R.id.restaurant_details_description_events);
        layout_description_arrow_events = getView().findViewById(R.id.restaurant_details_description_arrow_layout);

        layout_description_arrow_events.setOnClickListener(this);
        iv_bookmarks_events.setOnClickListener(this);
        tv_interested_events.setOnClickListener(this);
        txtSubmitRate.setOnClickListener(this);

        rateUsRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rate = v;
            }
        });

        iv_two = getView().findViewById(R.id.restaurant_detail_image_two);
        // Bottom
        rest_desc = getView().findViewById(R.id.restaurant_details_description);

        tv_above_description = getView().findViewById(R.id.restaurant_details_above_description);
        Log.d("RestaurantDetailsText", tv_above_description.getText().toString());

        layout_ratingbar = getView().findViewById(R.id.restaurant_details_rating_bar);

        cross = getView().findViewById(R.id.restaurant_detail_cross);
        cross.setOnClickListener(this);

        bottom_one = getView().findViewById(R.id.restaurant_detail_bottom_one);
        bottom_two = getView().findViewById(R.id.restaurant_detail_bottom_two);
        bottom_three = getView().findViewById(R.id.restaurant_detail_bottom_three);
        bottom_four = getView().findViewById(R.id.restaurant_detail_bottom_four);

        bottom_one.setOnClickListener(this);
        bottom_two.setOnClickListener(this);
        bottom_three.setOnClickListener(this);
        bottom_four.setOnClickListener(this);

        iv_one = getView().findViewById(R.id.restaurant_detail_image_one);

        iv_three = getView().findViewById(R.id.restaurant_detail_image_three);
        iv_four = getView().findViewById(R.id.restaurant_detail_image_four);

        arrow = getView().findViewById(R.id.restaurant_detail_arrow);
        arrow.setOnClickListener(this);

        iv_arrow = getView().findViewById(R.id.restaurant_details_image_arrow);

        interested_recycler = getView().findViewById(R.id.fragment_venue_detail_interested_recycler);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        interested_recycler.setLayoutManager(horizontalLayoutManagaer);

        LinearLayoutManager ratingLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_rating.setLayoutManager(ratingLayoutManager);

        rv_venues = (RecyclerView) getView().findViewById(R.id.restaurant_details_recycler_venues);

        CustomLayoutManager manager1 = new CustomLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv_venues.setLayoutManager(manager1);

        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext());
        // Set flex direction.
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);

        featureRecyclerView.setLayoutManager(flexboxLayoutManager);
        setBotomColors();
        iv_one.setImageResource(R.drawable.ic_dot_1);
        rest_desc.setVisibility(View.VISIBLE);
        infoLinearLayout.setVisibility(View.VISIBLE);
//        mapView.setVisibility(View.GONE);
        featureRecyclerView.setVisibility(View.GONE);
        rv_venues.setVisibility(View.GONE);
        layout_ratingbar.setVisibility(View.GONE);
        getVenueDetail();
    }

    private AdapterCategoryListBottom1Events adapterCategoryListBottom1Events;

    private void setFeatureAdapter(List<VenueDetailsResponse.VenueDataBean.FavouriteUsersBean> favouriteUsers) {
        if (favouriteUsers != null && favouriteUsers.size() > 0) {
            SelectedFeatureAdapter selectedFeatureAdapter = new SelectedFeatureAdapter(favouriteUsers, getContext());
            featureRecyclerView.setAdapter(selectedFeatureAdapter);
        }
    }

    private void setInterestedRecycler(List<VenueDetailsResponse.VenueDataBean.InterestedUsersBean> interestedUsers) {
        interested_recycler.setVisibility(View.VISIBLE);
        InterestedUserAdapter adapter = new InterestedUserAdapter(getContext(), interestedUsers);
        interested_recycler.setAdapter(adapter);
    }


    private void setRatingAdapter(List<VenueDetailsResponse.VenueDataBean.RatingsBean> ratingsBeans) {
        RatingAdapter adapter = new RatingAdapter(getContext(), ratingsBeans);
        rv_rating.setAdapter(adapter);
    }

    private void setEventAdapter(List<VenueDetailsResponse.VenueDataBean.EventsBean> events) {
        adapterCategoryListBottom1Events = new AdapterCategoryListBottom1Events(getContext(), this, events);
        rv_venues.setAdapter(adapterCategoryListBottom1Events);
    }

    private boolean status_desc = false;
    private boolean status_bookmark_venues = true, status_interested_venues = false;
    private boolean status_bookmark_events = false, status_interested_events = false;

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.restaurant_detail_cross) {
            getFragmentManager().popBackStackImmediate();
        } else if (view.getId() == R.id.restaurant_detail_bottom_one) {
            setBotomColors();
            iv_one.setImageResource(R.drawable.ic_dot_1);
            rest_desc.setVisibility(View.VISIBLE);
            infoLinearLayout.setVisibility(View.VISIBLE);
            mapView.setVisibility(View.GONE);
            featureRecyclerView.setVisibility(View.GONE);
            rv_venues.setVisibility(View.GONE);
            layout_ratingbar.setVisibility(View.GONE);
        } else if (view.getId() == R.id.restaurant_detail_bottom_two) {
            setBotomColors();
            infoLinearLayout.setVisibility(View.GONE);
            iv_two.setTextColor(getResources().getColor(R.color.colorAccent));
            rest_desc.setVisibility(View.GONE);
            mapView.setVisibility(View.GONE);
            featureRecyclerView.setVisibility(View.GONE);
            rv_venues.setVisibility(View.VISIBLE);
            layout_ratingbar.setVisibility(View.GONE);
        } else if (view.getId() == R.id.restaurant_detail_bottom_three) {
            setBotomColors();
            infoLinearLayout.setVisibility(View.GONE);
            iv_three.setTextColor(getResources().getColor(R.color.colorAccent));
            rest_desc.setVisibility(View.GONE);
            mapView.setVisibility(View.GONE);
            featureRecyclerView.setVisibility(View.VISIBLE);
            rv_venues.setVisibility(View.GONE);
            layout_ratingbar.setVisibility(View.GONE);
        } else if (view.getId() == R.id.restaurant_detail_bottom_four) {
            setBotomColors();
            infoLinearLayout.setVisibility(View.GONE);
            iv_four.setImageResource(R.drawable.ic_edit_1);
            rest_desc.setVisibility(View.GONE);
            mapView.setVisibility(View.GONE);
            featureRecyclerView.setVisibility(View.GONE);
            rv_venues.setVisibility(View.GONE);
            layout_ratingbar.setVisibility(View.VISIBLE);
        } else if (view.getId() == R.id.restaurant_detail_arrow) {
            if (status) {
                iv_arrow.setImageResource(R.drawable.arrow_down);
                interested_recycler.setVisibility(View.GONE);
                interested_recycler.animate().translationX(0);
                status = false;
            } else {
                iv_arrow.setImageResource(R.drawable.arrow_up);
                interested_recycler.animate().translationX(0);
                interested_recycler.setVisibility(View.VISIBLE);
                status = true;
            }
        } else if (view.getId() == R.id.restaurant_details_description_arrow_layout) {
            if (!status_desc) {
                iv_description_arrow_events.setImageResource(R.drawable.arrow_up);
                tv_description_events.setVisibility(View.VISIBLE);
                status_desc = true;
            } else {
                iv_description_arrow_events.setImageResource(R.drawable.arrow_down);
                tv_description_events.setVisibility(View.GONE);
                status_desc = false;
            }
        } else if (view.getId() == R.id.restaurant_details_bookmarks_venues) {
            if (status_bookmark_venues) {
                iv_bookmarks.setImageResource(R.drawable.ic_fav_1);
                status_bookmark_venues = false;
            } else {
                iv_bookmarks.setImageResource(R.drawable.ic_fav);
                status_bookmark_venues = true;
            }
        } else if (view.getId() == R.id.restaurant_details_interested_venues) {
            if (!status_interested_venues) {
                tv_interested.setTextColor(getResources().getColor(R.color.colorAccent));
                status_interested_venues = true;
            } else {
                tv_interested.setTextColor(getResources().getColor(R.color.text_color));
                status_interested_venues = false;
            }
        } else if (view.getId() == R.id.restaurant_details_events_bookmarks) {
            if (!status_bookmark_events) {
                iv_bookmarks_events.setImageResource(R.drawable.ic_favorites);
                status_bookmark_events = true;
                status_bookmark = 1;
                setBookmark(venueId, status_bookmark);

            } else {
                iv_bookmarks_events.setImageResource(R.drawable.ic_favorites_1);
                status_bookmark_events = false;
                status_bookmark = 0;
                setBookmark(venueId, status_bookmark);
            }
        } else if (view.getId() == R.id.restaurant_details_events_interested) {
            if (!status_interested_events) {
                tv_interested_events.setTextColor(getResources().getColor(R.color.colorAccent));
                status_interested_events = true;
                status_bookmark = 1;
                setinterest(venueId, status_bookmark);
            } else {
                tv_interested_events.setTextColor(getResources().getColor(R.color.text_color));
                status_interested_events = false;
                status_bookmark = 0;
                setinterest(venueId, status_bookmark);

            }
        } else if (view.getId() == R.id.fragment_venue_detail_info_rate_btn) {
            rateUsApi();
        } else {

        }
    }

    private void setBotomColors() {
        iv_one.setImageResource(R.drawable.ic_dot);
        iv_two.setTextColor(getResources().getColor(R.color.text_color));
        iv_three.setTextColor(getResources().getColor(R.color.text_color));
        iv_four.setImageResource(R.drawable.ic_edit_b);
    }

    private void getVenueDetail() {
        if (Utility.isNetworkConnected(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            //  user_id = 165;
            Call<VenueDetailsResponse> call = APIClient.getInstance().getApiInterface().getVenueDetail(venueId, user_id);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("VenueURL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }

    private void rateUsApi() {
        if (Utility.isNetworkConnected(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            mainLayout.setVisibility(View.GONE);
            RatingRequest ratingRequest = new RatingRequest();
            ratingRequest.setId(venueId);
            ratingRequest.setRating(rate);
            ratingRequest.setUserId(user_id);
            ratingRequest.setReview("");
            ratingRequest.setType("venue");
            Log.d("MyRequest: ", new Gson().toJson(ratingRequest));

            Call<RateResponse> call = APIClient.getInstance().getApiInterface().rateUsEvent(ratingRequest);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("URL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:

                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    /*Toast.makeText(getContext(), "...", Toast.LENGTH_SHORT).show();*/
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            1);
                    return;
                }
                googleMap.setMyLocationEnabled(true);
                googleMap.setOnMyLocationChangeListener(onMyLocationChangeListener);
                break;
        }
    }

    private GoogleMap.OnMyLocationChangeListener onMyLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());

            googleMap.clear();
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(loc).zoom(19f).tilt(70).build();
            googleMap.addMarker(new MarkerOptions().position(loc));
            if (googleMap != null) {
                googleMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(cameraPosition));
            }
        }
    };

    @Override
    public void click_interface_restaurant_list_events(int position, String type) {

      /*  if(type.equals("bookmarks")){

            if(bottom_list_bookmarks.get(position).equals("0")){
                bottom_list_bookmarks.set(position, "1");
            } else{
                bottom_list_bookmarks.set(position, "0");
            }
            adapterCategoryListBottom1Events.notifyDataSetChanged();
        } else {
            if(bottom_list_interested.get(position).equals("0")){
                bottom_list_interested.set(position, "1");
            } else{
                bottom_list_interested.set(position, "0");
            }
            adapterCategoryListBottom1Events.notifyDataSetChanged();
        }*/
    }

    @Override
    public void click_interface_restaurant_list_1(int position, String type, int status) {
        /*if (type.equals("bookmarks")) {
            if (bottom_list_bookmarks.get(position).equals("0")) {
                bottom_list_bookmarks.set(position, "1");
            } else {
                bottom_list_bookmarks.set(position, "0");
            }
            adapte1.notifyDataSetChanged();
        } else {
            if (bottom_list_interested.get(position).equals("0")) {
                bottom_list_interested.set(position, "1");
            } else {
                bottom_list_interested.set(position, "0");
            }
            adapte1.notifyDataSetChanged();

        }*/
    }

    @Override
    public void viewItemDetails(int pos) {

    }

    @Override
    public void onApiResponse(Object response) {
        progressBar.setVisibility(View.GONE);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        mainLayout.setVisibility(View.VISIBLE);
        Log.d(TAG, "onApiResponse: " + new Gson().toJson(response));
        if (response != null) {
            try {
                if (response instanceof VenueDetailsResponse) {
                    VenueDetailsResponse venueDetailsResponse = (VenueDetailsResponse) response;
                    if (venueDetailsResponse.getStatus() == 1) {
                        tv_name_events.setText(venueDetailsResponse.getVenueData().getName());
                        Glide.with(getActivity())
                                .load(venueDetailsResponse.getVenueData().getImageURL())
                                .into(iv_image_events);
                        ratingBar.setRating((float) venueDetailsResponse.getVenueData().getAvgRating());
                        if (venueDetailsResponse.getVenueData().getReviewCount() > 0) {
                            tv_reviews_events.setText(venueDetailsResponse.getVenueData().getReviewCount() + " Reviews");
                        } else tv_reviews_events.setText(" ");
                        if (venueDetailsResponse.getVenueData().getIsBookmarked() == 1) {
                            status_bookmark_events = true;
                            iv_bookmarks_events.setImageResource(R.drawable.ic_favorites);
                        } else {
                            status_bookmark_events = false;
                            iv_bookmarks_events.setImageResource(R.drawable.ic_favorites_1);
                        }
                        if (venueDetailsResponse.getVenueData().getIsInterested() == 1) {
                            tv_interested_events.setTextColor(getResources().getColor(R.color.colorAccent));
                        } else {
                            tv_interested_events.setTextColor(getResources().getColor(R.color.text_color));
                        }
                        if (venueDetailsResponse.getVenueData().getDescription() != null && venueDetailsResponse
                                .getVenueData().getDescription().length() > 0)
                            rest_desc.setText(venueDetailsResponse.getVenueData().getDescription());
                        else rest_desc.setText("Not Available");

                        if (venueDetailsResponse.getVenueData().getAddress() != null && venueDetailsResponse
                                .getVenueData().getAddress().length() > 0)
                            txtAddress.setText(venueDetailsResponse.getVenueData().getAddress());
                        else txtAddress.setText("Not Available");

                        if (venueDetailsResponse.getVenueData().getParkingDetail() != null && venueDetailsResponse
                                .getVenueData().getParkingDetail().length() > 0)
                            txtParking.setText(venueDetailsResponse.getVenueData().getParkingDetail());
                        else txtParking.setText("Not Available");

                        if (venueDetailsResponse.getVenueData().getPhoneNumberDetail() != null && venueDetailsResponse
                                .getVenueData().getPhoneNumberDetail().length() > 0)
                            txtContact.setText(venueDetailsResponse.getVenueData().getPhoneNumberDetail());
                        else txtContact.setText("Not Available");
                        if (venueDetailsResponse.getVenueData().getAcceptedPaymentDetail() != null && venueDetailsResponse.getVenueData().getAcceptedPaymentDetail().length() > 0) {
                            txtPayment.setText(venueDetailsResponse.getVenueData().getAcceptedPaymentDetail());
                        } else txtPayment.setText("Not Available");

                        if (venueDetailsResponse.getVenueData().getShortDesc() != null)
                            tv_description_events.setText(venueDetailsResponse.getVenueData().getShortDesc());
//                        if (venueDetailsResponse.getVenueData().getTimezone())
                        setInterestedRecycler(venueDetailsResponse.getVenueData().getInterestedUsers());
                        setEventAdapter(venueDetailsResponse.getVenueData().getEvents());
                        setRatingAdapter(venueDetailsResponse.getVenueData().getRatings());
                        setFeatureAdapter(venueDetailsResponse.getVenueData().getFavouriteUsers());

                    } else
                        new BaseClass(getActivity()).showToast(venueDetailsResponse.getMessage());
                } else if (response instanceof ApiResponse) {
                    ApiResponse apiResponse = (ApiResponse) response;
                    if (apiResponse.getStatus() == 1) {
                        new BaseClass(getActivity()).showToast(apiResponse.message);
                    } else {
                        new BaseClass(getActivity()).showToast(apiResponse.message);
                    }
                } else if (response instanceof RateResponse) {
                    RateResponse rateResponse = (RateResponse) response;
                    if (rateResponse.getStatus() == 1) {
                        new BaseClass(getActivity()).showToast(rateResponse.getMessage());
                    } else new BaseClass(getActivity()).showToast(rateResponse.getMessage());

                }

            } catch (Exception e) {
                Log.d(TAG, "onApiResponse: " + e.getMessage());
            }
        }
    }

    private String TAG = RestaurantDetails.class.getName();

    private void setBookmark(String venue_id, int status) {
        if (Utility.isNetworkConnected(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);

            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            Call<ApiResponse> call = APIClient.getInstance().getApiInterface()
                    .markVenueBookmarked(tinyDB.getInt(Constants.USER_ID), venue_id, String.valueOf(status));
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("URL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }


    private void setinterest(String venue_id, int status) {
        if (Utility.isNetworkConnected(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            Call<ApiResponse> call = APIClient
                    .getInstance()
                    .getApiInterface()
                    .markVenueInterest(tinyDB.getInt(Constants.USER_ID), venue_id, String.valueOf(status));
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("URL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }


/*
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                progressBar.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                if (response != null) {
                    if (response.body().getStatus() == 1) {
                        new BaseClass(getActivity()).showToast(response.body().message);
                    }
                    else
                    {
                        new BaseClass(getActivity()).showToast(response.body().message);
                    }
                }
              */
/*  else
                {
                    progressBar.setVisibility(View.GONE);
                    new BaseClass(getActivity()).showToast("Data not found");
                }*//*

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
*/


    }

    @Override
    public void onApiFailure(String message) {
        progressBar.setVisibility(View.GONE);
        new BaseClass(getActivity()).showToast(message);
    }
}