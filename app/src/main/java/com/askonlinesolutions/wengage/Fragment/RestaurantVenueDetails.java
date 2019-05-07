package com.askonlinesolutions.wengage.Fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.NotificationReadResponse;
import com.askonlinesolutions.wengage.Adapter.AdapterCategoryListBottom1;
import com.askonlinesolutions.wengage.Adapter.AdapterCategoryListBottom1Events;
import com.askonlinesolutions.wengage.Fragment.Main.venue.details.InterestedUserAdapter;
import com.askonlinesolutions.wengage.Fragment.Main.venue.details.SelectedFeatureAdapter;
import com.askonlinesolutions.wengage.Fragment.Main.venue.details.rate.event.RateResponse;
import com.askonlinesolutions.wengage.Fragment.Main.venue.details.rate.event.RatingAdapter;
import com.askonlinesolutions.wengage.Fragment.Main.venue.details.rate.event.RatingRequest;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Helper.CustomLayoutManager;
import com.askonlinesolutions.wengage.Model.InteresetRequest;
import com.askonlinesolutions.wengage.Model.Response.VenueDetailsResponse;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.ApiResponse;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Progress;
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
public class RestaurantVenueDetails extends Fragment implements View.OnClickListener,
        AdapterCategoryListBottom1Events.Interface_RestaurantListEvents,
        AdapterCategoryListBottom1.Interface_RestaurantList1, OnResponseInterface {

    public RestaurantVenueDetails() {
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
        View view = inflater.inflate(R.layout.fragment_restaurant_venue_details, container, false);
        getMyIntent();
        init(view);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        return view;
    }


    public static StringBuffer stringBuffer = new StringBuffer();
    double latitude, longitude;
    private int user_id;
    private Dialog errorDialog;
    private VenueDetailsResponse venueDetailsResponse;
    private ImageView mapIcon, pathIcon;
    private LinearLayout mainss;

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

    //    private ImageView progressBar;
    private LinearLayout layout_ratingbar, mainLayout;
    private RecyclerView interested_recycler, rv_venues, rv_rating, featureRecyclerView;
    private LinearLayout cross, bottom_one, bottom_two, bottom_three, bottom_four, arrow;
    private ImageView iv_one, iv_four, iv_arrow;
    private TextView iv_two;
    private TextView rest_desc, iv_three, categoryName;
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
    private TextView tv_ad, tv_name, tv_review, tv_km, tv_interested, monday, tuesday, wednesday, thrusday, friday, saturday, sunday;
    private int str_image, str_image_2,notificationId;
    private String str_circle, str_ad, str_name, str_review, str_km, str_butterfly,
            str_check, str_type, venueId, eventId, status_bookmark,notification;
    private float rate;
    private Progress progress;
    private TinyDB tinyDB;
    private RatingBar ratingBar, rateUsRatingBar;

    private void getMyIntent() {
        venueId = getArguments().getString("venue_id");
        notificationId=getArguments().getInt("notificationId");
        notification=String.valueOf(notificationId);
    }

    private RelativeLayout layout_venues, layout_events, desLayout;

    // Events
    private ImageView iv_image_events, iv_circle_events, iv_bookmarks_events, iv_check_events,
            iv_butterfly_events, iv_description_arrow_events;
    private LinearLayout layout_description_arrow_events, infoLinearLayout;
    private TextView tv_name_events, tv_interested_events, tv_reviews_events, tv_description_events,
            tvTimeInfo, txtAddress, txtParking, txtContact, txtPayment, txtSubmitRate;
    private EditText reviewEt;
    private TextView tv_title;

    private void init(View view) {
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        mapIcon = view.findViewById(R.id.mapIcon);
        reviewEt = view.findViewById(R.id.reviewEt);
        mainss = view.findViewById(R.id.mainss);
        categoryName = view.findViewById(R.id.categoryName);
        desLayout = view.findViewById(R.id.desLayout);
        monday = view.findViewById(R.id.monday);
        tuesday = view.findViewById(R.id.tuesday);
        wednesday = view.findViewById(R.id.wednesday);
        thrusday = view.findViewById(R.id.thrusday);
        friday = view.findViewById(R.id.friday);
        saturday = view.findViewById(R.id.saturday);
        pathIcon = view.findViewById(R.id.pathIcon);
        sunday = view.findViewById(R.id.sunday);
        ratingBar = view.findViewById(R.id.fragment_res_detail_rating);
        mainLayout = view.findViewById(R.id.fragment_res_detail_layout);
//        progressBar = view.findViewById(R.id.fragment_res_detail_progress);
        layout_venues = view.findViewById(R.id.restaurant_details_layout_venues);
        layout_events = view.findViewById(R.id.restaurant_details_layout_events);

        tinyDB = new TinyDB(getContext());
        user_id = tinyDB.getInt(Constants.USER_ID);
        tv_title = view.findViewById(R.id.category_list_title);
//        Glide.with(getActivity()).asGif().load(R.drawable.load).into(progressBar);
        tv_title.setText("Venue");
        featureRecyclerView = view.findViewById(R.id.fragment_venue_detail_info_features_list);
        rv_rating = view.findViewById(R.id.fragment_venue_detail_info_rating_recycler);
        iv_circle = view.findViewById(R.id.restaurant_details_circle);
        iv_image = view.findViewById(R.id.restaurant_details_image);
        iv_butterfly = view.findViewById(R.id.restaurant_details_butterfy);
        iv_check = view.findViewById(R.id.restaurant_details_check);
        tv_ad = view.findViewById(R.id.restaurant_details_ad);
        tv_name = view.findViewById(R.id.restaurant_details_name);
        tv_review = view.findViewById(R.id.restaurant_details_review);
        tv_km = view.findViewById(R.id.restaurant_details_km);
        iv_bookmarks = view.findViewById(R.id.restaurant_details_bookmarks_venues);
        tv_interested = view.findViewById(R.id.restaurant_details_interested_venues);

        iv_bookmarks.setOnClickListener(this);
        pathIcon.setOnClickListener(this);
        tv_title.setOnClickListener(this);
        tv_interested.setOnClickListener(this);

        //Events
        txtPayment = view.findViewById(R.id.fragment_venue_detail_info_payment);
        tvTimeInfo = view.findViewById(R.id.fragment_venue_detail_info_time);
        txtParking = view.findViewById(R.id.fragment_venue_detail_info_parking);
        txtContact = view.findViewById(R.id.fragment_venue_detail_info_contact);
        txtAddress = view.findViewById(R.id.fragment_venue_detail_info_address);
        rateUsRatingBar = view.findViewById(R.id.fragment_venue_detail_info_rateus);
        txtSubmitRate = view.findViewById(R.id.fragment_venue_detail_info_rate_btn);

        infoLinearLayout = view.findViewById(R.id.fragment_venue_detail_info_layout);
        iv_image_events = view.findViewById(R.id.restaurant_details_events_image);
        iv_circle_events = view.findViewById(R.id.restaurant_details_events_circle);
        iv_bookmarks_events = view.findViewById(R.id.restaurant_details_events_bookmarks);
        iv_check_events = view.findViewById(R.id.restaurant_details_events_check);
        iv_butterfly_events = view.findViewById(R.id.restaurant_details_events_butterfly);
        iv_description_arrow_events = view.findViewById(R.id.restaurant_details_description_arrow);
        tv_name_events = view.findViewById(R.id.restaurant_details_events_name);
        tv_interested_events = view.findViewById(R.id.restaurant_details_events_interested);
        tv_reviews_events = view.findViewById(R.id.restaurant_details_events_reviews);
        tv_description_events = view.findViewById(R.id.restaurant_details_description_events);
        layout_description_arrow_events = view.findViewById(R.id.restaurant_details_description_arrow_layout);

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

        iv_two = view.findViewById(R.id.restaurant_detail_image_two);
        // Bottom
        rest_desc = view.findViewById(R.id.restaurant_details_description);

        tv_above_description = view.findViewById(R.id.restaurant_details_above_description);
        Log.d("RestaurantDetailsText", tv_above_description.getText().toString());

        layout_ratingbar = view.findViewById(R.id.restaurant_details_rating_bar);

        cross = view.findViewById(R.id.restaurant_detail_cross);
        cross.setOnClickListener(this);

        bottom_one = view.findViewById(R.id.restaurant_detail_bottom_one);
        bottom_two = view.findViewById(R.id.restaurant_detail_bottom_two);
        bottom_three = view.findViewById(R.id.restaurant_detail_bottom_three);
        bottom_four = view.findViewById(R.id.restaurant_detail_bottom_four);

        bottom_one.setOnClickListener(this);
        bottom_two.setOnClickListener(this);
        bottom_three.setOnClickListener(this);
        bottom_four.setOnClickListener(this);

        iv_one = view.findViewById(R.id.restaurant_detail_image_one);

        iv_three = view.findViewById(R.id.restaurant_detail_image_three);
        iv_four = view.findViewById(R.id.restaurant_detail_image_four);

        arrow = view.findViewById(R.id.restaurant_detail_arrow);
        arrow.setOnClickListener(this);
        mapIcon.setOnClickListener(this);

        iv_arrow = view.findViewById(R.id.restaurant_details_image_arrow);

        interested_recycler = view.findViewById(R.id.fragment_venue_detail_interested_recycler);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        interested_recycler.setLayoutManager(horizontalLayoutManagaer);

        LinearLayoutManager ratingLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_rating.setLayoutManager(ratingLayoutManager);

        rv_venues = (RecyclerView) view.findViewById(R.id.restaurant_details_recycler_venues);

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
        mapView = view.findViewById(R.id.restaurant_details_mapView);

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
        getVenueDetail();

        if (notification!=null)
        {
            callReadNotificationApi();
        }
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
//            rest_desc.setVisibility(View.VISIBLE);
            infoLinearLayout.setVisibility(View.VISIBLE);
            mapView.setVisibility(View.GONE);
            featureRecyclerView.setVisibility(View.GONE);
            rv_venues.setVisibility(View.GONE);
            layout_ratingbar.setVisibility(View.GONE);
        } else if (view.getId() == R.id.pathIcon) {
            Double SourceLat = tinyDB.getDouble(Constants.LATITUDE, 28.5355);
            if (SourceLat.equals(0.0)) {
                SourceLat = 28.586800;
            }
            Double SourceLong = tinyDB.getDouble(Constants.LONGITUDE, 28.5355);
            if (SourceLong.equals(0.0)) {
                SourceLong = 77.338290;
            }
            Double DestiLat = Double.valueOf(venueDetailsResponse.getVenueData().getLatitude());
            Double DestiLong = Double.valueOf(venueDetailsResponse.getVenueData().getLongitude());
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr=" + SourceLat + "," + SourceLong + "&daddr=" + DestiLat + "," + DestiLong));
            startActivity(intent);
        } else if (view.getId() == R.id.restaurant_detail_bottom_two) {
            setBotomColors();
            infoLinearLayout.setVisibility(View.GONE);
            iv_two.setTextColor(getResources().getColor(R.color.colorAccent));
            rest_desc.setVisibility(View.GONE);
            mapView.setVisibility(View.GONE);
            featureRecyclerView.setVisibility(View.GONE);
            rv_venues.setVisibility(View.VISIBLE);
            layout_ratingbar.setVisibility(View.GONE);
        } else if (view.getId() == R.id.category_list_title) {
            getActivity().onBackPressed();
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
                status_bookmark = "1";
                setBookmark(venueId, status_bookmark);

            } else {
                iv_bookmarks_events.setImageResource(R.drawable.ic_favorites_1);
                status_bookmark_events = false;
                status_bookmark = "0";
                setBookmark(venueId, status_bookmark);
            }
        } else if (view.getId() == R.id.restaurant_details_events_interested) {
            if (!status_interested_events) {
                tv_interested_events.setTextColor(getResources().getColor(R.color.colorAccent));
                status_interested_events = true;
                status_bookmark = "1";
                setinterest(venueId, status_bookmark);
            } else {
                tv_interested_events.setTextColor(getResources().getColor(R.color.text_color));
                status_interested_events = false;
                status_bookmark = "0";
                setinterest(venueId, status_bookmark);

            }
        } else if (view.getId() == R.id.fragment_venue_detail_info_rate_btn) {
            rateUsApi();
        } else if (view.getId() == R.id.mapIcon) {
            openDialog();
        } else {

        }
    }

    private void openDialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.map_dialog);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        final LinearLayout close = dialog.findViewById(R.id.close);
        final GoogleMap[] googleMap = new GoogleMap[1];


        MapView mMapView;
        MapsInitializer.initialize(getActivity());

        mMapView = (MapView) dialog.findViewById(R.id.mapView);
        mMapView.onCreate(dialog.onSaveInstanceState());
        mMapView.onResume();// needed to get the map to display immediately
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                googleMap[0] = map;

                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    /*Toast.makeText(getContext(), "...", Toast.LENGTH_SHORT).show();*/
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            1);
                    return;
                }
                Double lat = new Double(venueDetailsResponse.getVenueData().getLatitude());
                Double longg = new Double(venueDetailsResponse.getVenueData().getLongitude());
                map.addMarker(new MarkerOptions().position(new LatLng(
                        lat, longg)));
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(
                                lat, longg))
                        .zoom(17).build();
                //Zoom in and animate the camera.
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//                map.setMyLocationEnabled(true);
                map.setOnMyLocationChangeListener(onMyLocationChangeListener);
            }
        });
        final Dialog finalDialog = dialog;
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalDialog.dismiss();
            }
        });
        dialog.show();
    }

    private void setBotomColors() {
        iv_one.setImageResource(R.drawable.ic_dot);
        iv_two.setTextColor(getResources().getColor(R.color.text_color));
        iv_three.setTextColor(getResources().getColor(R.color.text_color));
        iv_four.setImageResource(R.drawable.ic_edit_b);
    }

    private void callReadNotificationApi() {
        Call<NotificationReadResponse> call = APIClient.getInstance().getApiInterface().getReadNotification(user_id, notificationId);
        Log.e("MyUrl", call.request().url().toString());
        new ResponseListner(this).getResponse(getActivity(), call);

    }



    private void getVenueDetail() {
        if (Utility.isNetworkConnected(getActivity())) {

            progress.show();
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

        if (reviewEt.getText().toString().trim().isEmpty() && rate == 0.0) {
            Toast.makeText(getActivity(), "Please submit review or rating", Toast.LENGTH_SHORT).show();
        } else {
            if (reviewEt.getText().toString().trim().length() > 200) {
                Toast.makeText(getActivity(), "You cannot exceed character limit (200 character)", Toast.LENGTH_SHORT).show();
            } else {
                if (Utility.isNetworkConnected(getActivity())) {
                    progress.show();
//                getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    mainLayout.setVisibility(View.GONE);
                    RatingRequest ratingRequest = new RatingRequest();
                    ratingRequest.setId(venueId);
                    ratingRequest.setRating(rate);
                    ratingRequest.setUserId(user_id);
                    ratingRequest.setReview(reviewEt.getText().toString().trim());
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
        progress.dismiss();
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        mainLayout.setVisibility(View.VISIBLE);
        Log.d(TAG, "onApiResponse: " + new Gson().toJson(response));
        if (response != null) {
            try {
                if (response instanceof VenueDetailsResponse) {

                    venueDetailsResponse = (VenueDetailsResponse) response;
                    if (venueDetailsResponse.getStatus() == 1) {
                        if (venueDetailsResponse.getVenueData().getRecommendByWengage() == 1) {
                            iv_butterfly_events.setVisibility(View.VISIBLE);
                        } else {
                            iv_butterfly_events.setVisibility(View.GONE);
                        }
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
                        else rest_desc.setVisibility(View.GONE);

                        if (venueDetailsResponse.getVenueData().getAddress() != null && venueDetailsResponse
                                .getVenueData().getAddress().length() > 0)
                            txtAddress.setText(venueDetailsResponse.getVenueData().getAddress());
                        else txtAddress.setText("NA");

                        if (venueDetailsResponse.getVenueData().getParkingDetail() != null && venueDetailsResponse
                                .getVenueData().getParkingDetail().length() > 0)
                            txtParking.setText(venueDetailsResponse.getVenueData().getParkingDetail());
                        else txtParking.setText("NA");

                        if (venueDetailsResponse.getVenueData().getPhoneNumberDetail() != null && venueDetailsResponse
                                .getVenueData().getPhoneNumberDetail().length() > 0)
                            txtContact.setText(venueDetailsResponse.getVenueData().getPhoneNumberDetail());
                        else txtContact.setText("NA");
                        if (venueDetailsResponse.getVenueData().getAcceptedPaymentDetail() != null && venueDetailsResponse.getVenueData().getAcceptedPaymentDetail().length() > 0) {
                            txtPayment.setText(venueDetailsResponse.getVenueData().getAcceptedPaymentDetail());
                        } else txtPayment.setText("NA");

                        if (venueDetailsResponse.getVenueData().getIsInterested() == 1) {
                            iv_circle_events.setVisibility(View.VISIBLE);
                        } else {
                            iv_circle_events.setVisibility(View.GONE);
                        }
                        if (venueDetailsResponse.getVenueData().getShortDesc().equals("")) {
                            desLayout.setVisibility(View.GONE);
                        } else {
                            if (venueDetailsResponse.getVenueData().getShortDesc() != null) {
                                tv_description_events.setText(venueDetailsResponse.getVenueData().getShortDesc());
                                desLayout.setVisibility(View.VISIBLE);
                            } else {
                                desLayout.setVisibility(View.GONE);
                            }
                        }

                        categoryName.setText(venueDetailsResponse.getVenueData().getCategoryName() + "/" + venueDetailsResponse.getVenueData().getSubCategoryName());
//                        if (venueDetailsResponse.getVenueData().getTimezone())
                        setInterestedRecycler(venueDetailsResponse.getVenueData().getInterestedUsers());
                        setEventAdapter(venueDetailsResponse.getVenueData().getEvents());
                        setRatingAdapter(venueDetailsResponse.getVenueData().getRatings());
                        setFeatureAdapter(venueDetailsResponse.getVenueData().getFavouriteUsers());
                        if (venueDetailsResponse.getVenueData().getHoursBean().getMonday() == null ||
                                venueDetailsResponse.getVenueData().getHoursBean().getMonday().equals("")) {
                            monday.setText("NA");
                        } else {
                            monday.setText(venueDetailsResponse.getVenueData().getHoursBean().getMonday());
                        }
                        if (venueDetailsResponse.getVenueData().getHoursBean().getTuesday() == null ||
                                venueDetailsResponse.getVenueData().getHoursBean().getTuesday().equals("")) {
                            tuesday.setText("NA");
                        } else {
                            tuesday.setText(venueDetailsResponse.getVenueData().getHoursBean().getTuesday());
                        }
                        if (venueDetailsResponse.getVenueData().getHoursBean().getWednesday() == null ||
                                venueDetailsResponse.getVenueData().getHoursBean().getWednesday().equals("")) {
                            wednesday.setText("NA");
                        } else {
                            wednesday.setText(venueDetailsResponse.getVenueData().getHoursBean().getWednesday());
                        }
                        if (venueDetailsResponse.getVenueData().getHoursBean().getThrusday() == null ||
                                venueDetailsResponse.getVenueData().getHoursBean().getThrusday().equals("")) {
                            thrusday.setText("NA");
                        } else {
                            thrusday.setText(venueDetailsResponse.getVenueData().getHoursBean().getThrusday());
                        }
                        if (venueDetailsResponse.getVenueData().getHoursBean().getFriday() == null ||
                                venueDetailsResponse.getVenueData().getHoursBean().getFriday().equals("")) {
                            friday.setText("NA");
                        } else {
                            friday.setText(venueDetailsResponse.getVenueData().getHoursBean().getFriday());
                        }
                        if (venueDetailsResponse.getVenueData().getHoursBean().getSaturday() == null ||
                                venueDetailsResponse.getVenueData().getHoursBean().getSaturday().equals("")) {
                            saturday.setText("NA");
                        } else {
                            saturday.setText(venueDetailsResponse.getVenueData().getHoursBean().getSaturday());
                        }
                        if (venueDetailsResponse.getVenueData().getHoursBean().getSunday() == null) {
                            sunday.setText("NA");
                        } else {
                            sunday.setText(venueDetailsResponse.getVenueData().getHoursBean().getSunday());
                        }
                    } else

                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, venueDetailsResponse.getMessage());
                    errorDialog.show();
                } else if (response instanceof ApiResponse) {
                    ApiResponse apiResponse = (ApiResponse) response;
                    if (apiResponse.getStatus() == 1) {

                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, apiResponse.getMessage());
                        errorDialog.show();
                    } else {

                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, apiResponse.getMessage());
                        errorDialog.show();
                    }
                } else if (response instanceof RateResponse) {
                    RateResponse rateResponse = (RateResponse) response;
                    if (rateResponse.getStatus() == 1) {
                        getVenueDetail();
                        new BaseClass(getActivity()).showToast(rateResponse.getMessage());
                    } else {

                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, rateResponse.getMessage());
                        errorDialog.show();
                    }

                }
                else if (response instanceof NotificationReadResponse) {
                    progress.dismiss();
                    NotificationReadResponse readResponse = (NotificationReadResponse) response;
                    if (readResponse.getStatus() == 1) {

                        if (readResponse.getNotificationData() != null) {

//                            Toast.makeText(getActivity(), "Notification Readed", Toast.LENGTH_SHORT).show();

                        } else {

                        }


                    }
                }

            } catch (Exception e) {
                Log.d(TAG, "onApiResponse: " + e.getMessage());
            }
        }
    }

    private String TAG = RestaurantVenueDetails.class.getName();

    private void setBookmark(String venue_id, String status) {
        if (Utility.isNetworkConnected(getActivity())) {
            progress.show();
//        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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


    private void setinterest(String venue_id, String status) {
        if (Utility.isNetworkConnected(getActivity())) {
            progress.show();
//        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            String statusss = String.valueOf(status);
            InteresetRequest loginRequest = new InteresetRequest(tinyDB.getInt(Constants.USER_ID), venue_id, status);
            Call<ApiResponse> call = APIClient
                    .getInstance()
                    .getApiInterface()
                    .markVenueInterest(tinyDB.getInt(Constants.USER_ID), venue_id, status);
            new ResponseListner(this).getResponse(getActivity(), call);


            Log.e("URL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }

    @Override
    public void onApiFailure(String message) {
        progress.dismiss();
        if (!message.equals("") && message != null) {
//            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, message);
//            errorDialog.show();
        }
    }

}