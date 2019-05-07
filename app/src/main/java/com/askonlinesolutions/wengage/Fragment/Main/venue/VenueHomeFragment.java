package com.askonlinesolutions.wengage.Fragment.Main.venue;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Adapter.AdapterHomeList;
import com.askonlinesolutions.wengage.Adapter.AdapterHomeViewPager;
import com.askonlinesolutions.wengage.Adapter.AdapterTopProfiles;
import com.askonlinesolutions.wengage.Adapter.VenueCategoryAdapter;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventListResponse;
import com.askonlinesolutions.wengage.Fragment.Main.venue.details.RestaurantDetails;
import com.askonlinesolutions.wengage.Fragment.Main.vo.VeneuHomeListResponse;
import com.askonlinesolutions.wengage.Fragment.RestaurantVenueDetails;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Model.CategoryList;
import com.askonlinesolutions.wengage.Model.Response.CategoryResponse;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.ApiInterface;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Progress;
import com.askonlinesolutions.wengage.utils.Utility;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.mukesh.tinydb.TinyDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class VenueHomeFragment extends Fragment implements View.OnClickListener, AdapterHomeViewPager.Interface_Home,
        CompoundButton.OnCheckedChangeListener, VenueCategoryAdapter.CategoryItemClickListner,
        OnResponseInterface, AdapterHomeList.HomeVenuListInterface {

    private UserAllListResposne allUsersResponse;
    List<UserAllListResposne.UsersListBean.OnlineUsersBean> onlineUsers/* = new ArrayList<>()*/;
    List<UserAllListResposne.UsersListBean.InfluencerBean> influenceUsers/* = new ArrayList<>()*/;
    private RecyclerView rv_top_profiles, rv_list, venue_category_recycler_View;
    private TextView tv_title;
    //    private ProgressBar progressBar;
    private String type = "venue";
    private Place place;
    public static final int PLACE_PICKER_VENUE_REQUEST = 1001;
    private GoogleApiClient mClient;
    private LinearLayout layout_top_switch;
    int page = 1;
    int reachMax;
    int user_id;
    private TinyDB tinyDB;
    private ImageView iv;
    private boolean arrow_status = true;
    AdapterHomeList adapterHomeList;
    int page_number = 1;
    private Dialog errorDialog;
    private boolean userScrolled = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean visible = false;
    private String isPageUpdate;
    LinearLayoutManager layoutManagaer;
    private String lat, longi;
    private TextView countryName;
    private Progress progress;
    private LinearLayout locationLayout;
    private List<CategoryList> categoryList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (Utility.isNetworkConnected(getActivity())) {
            getCategory(type);
            callApiUsers();
            callHomeVenueListApi(page, user_id);
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }


    @SuppressLint("SetTextI18n")
    private void init(View view) {
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        mClient = new GoogleApiClient
                .Builder(getActivity())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();

        tinyDB = new TinyDB(getContext());
        user_id = tinyDB.getInt(Constants.USER_ID);
        allUsersResponse = new UserAllListResposne();
//        progressBar = view.findViewById(R.id.progress_bar);
        locationLayout = view.findViewById(R.id.locationLayout);
        layout_top_switch = view.findViewById(R.id.home_top_profile_layout);
        iv = view.findViewById(R.id.home_image_arrow);
        LinearLayout iv_arrow = view.findViewById(R.id.home_user_list_top_arrow);
        tv_title = view.findViewById(R.id.home_title);
        countryName = view.findViewById(R.id.countryName);
        venue_category_recycler_View = view.findViewById(R.id.venue_category_recycler);
        countryName.setText(tinyDB.getString(Constants.CITY));
        SwitchCompat sw = view.findViewById(R.id.home_top_profile_switch);
        sw.setChecked(true);

        rv_top_profiles = view.findViewById(R.id.recycler_top_profiles);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_top_profiles.setLayoutManager(horizontalLayoutManagaer);

        rv_list = view.findViewById(R.id.recycler_home_list);

        layoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_list.setLayoutManager(layoutManagaer);
        tv_title.setText("VENUES");

        sw.setOnCheckedChangeListener(this);
        iv_arrow.setOnClickListener(this);
        locationLayout.setOnClickListener(this);
        countryName.setOnClickListener(this);
        rv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    userScrolled = true;
                    visibleItemCount = layoutManagaer.getChildCount();
                    totalItemCount = layoutManagaer.getItemCount();
                    pastVisiblesItems = layoutManagaer
                            .findFirstVisibleItemPosition();
                    if (userScrolled
                            && (visibleItemCount + pastVisiblesItems) == totalItemCount) {
                        if (!visible) {
                            isPageUpdate = "1";
                            userScrolled = false;
                            page_number++;
                            performPaging(page_number);
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = layoutManagaer.getChildCount();
                totalItemCount = layoutManagaer.getItemCount();
                pastVisiblesItems = layoutManagaer
                        .findFirstVisibleItemPosition();
                if (userScrolled
                        && (visibleItemCount + pastVisiblesItems) == totalItemCount) {

                    if (!visible) {
//                        progressBar.setVisibility(View.VISIBLE);
                        isPageUpdate = "1";
                        userScrolled = false;
                        page_number++;
                        performPaging(page_number);
                    }
                }
//                performPaging(page_number);
            }
        });

    }

    private void performPaging(int page_number) {
        if (Utility.isNetworkConnected(getActivity())) {
            ApiInterface apiService = new APIClient().getClient().create(ApiInterface.class);
            Call<VeneuHomeListResponse> call = apiService.getHomeVenueList(page_number, user_id, lat, longi);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("URL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }

    private void callHomeVenueListApi(int page, int user_id) {
        if (!progress.isShowing()) {
            progress.show();
        }
        isPageUpdate = "0";
//        progressBar.setVisibility(View.VISIBLE);
        if (page == 1) {
            venuesHomeLists.clear();
        }
        ApiInterface apiService = new APIClient().getClient().create(ApiInterface.class);
        Call<VeneuHomeListResponse> call = apiService.getHomeVenueList(page, user_id, lat, longi);
        new ResponseListner(this).getResponse(getContext(), call);
        Log.e("URL", call.request().url().toString());
    }

    private void SetVenueAdapter(List<VeneuHomeListResponse.VenuesHomeList> venuesList,
                                 List<EventListResponse.EventsListBean> eventsList, String type) {

        adapterHomeList = new AdapterHomeList(getActivity(), venuesList, this,
                eventsList, type);
        rv_list.setAdapter(adapterHomeList);
    }

    @Override
    public void viewDetails(int pos) {
        RestaurantVenueDetails fragment = new RestaurantVenueDetails();
        Bundle bundle = new Bundle();
        bundle.putString("venue_id", venuesHomeLists.get(pos).getVenueId());
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fade_out,
                0,
                R.animator.fragment_slide_right_exit)
                .replace(R.id.fragment_venue_home_fragment_container, fragment).addToBackStack(RestaurantDetails.class.getName()).commit();
    }

    private void getCategory(String page) {

        ApiInterface apiService = new APIClient().getClient().create(ApiInterface.class);

        Call<CategoryResponse> call = apiService.getAllCategories(page);
        Log.d("MyUrl", call.request().url().toString());

        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(@NonNull Call<CategoryResponse> call, @NonNull Response<CategoryResponse> response) {

//                Log.d("MyResponse", response.body().getMessage());

                getData(response.body());
//                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(@NonNull Call<CategoryResponse> call, @NonNull Throwable t) {

//                progressBar.setVisibility(View.GONE);

            }
        });

    }

    private void callApiUsers() {

        //  user_id = 165;
        Call<UserAllListResposne> call = APIClient.getInstance().getApiInterface().getAllUsers(user_id);
        new ResponseListner(this).getResponse(getContext(), call);
        Log.e("URL", call.request().url().toString());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.home_user_list_top_arrow:
                if (arrow_status) {
                    iv.setImageResource(R.drawable.arrow_up);
                    layout_top_switch.setVisibility(View.VISIBLE);
                    rv_top_profiles.setVisibility(View.VISIBLE);
                    setTopAdapter("1");

                    tv_title.setVisibility(View.GONE);
                    arrow_status = false;
                } else {
                    iv.setImageResource(R.drawable.arrow_down);
                    rv_top_profiles.setVisibility(View.GONE);
                    layout_top_switch.setVisibility(View.GONE);
//                    rv_top_profiles.animate().translationY(0);
                    tv_title.setVisibility(View.VISIBLE);
                    arrow_status = true;
                }
                break;
            case R.id.locationLayout:
                Intent intent = null;
                try {
                    intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(getActivity());
                    getActivity().startActivityForResult(intent, PLACE_PICKER_VENUE_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        int id = compoundButton.getId();
        if (id == R.id.home_top_profile_switch) {
            if (b) {
                setTopAdapter("1");
            } else setTopAdapter("2");

        }
    }


    public void getData(Object response) {
        if (categoryList != null) {
            categoryList.clear();
        }
        CategoryResponse categoryResponse = (CategoryResponse) response;
        if (categoryResponse.getStatus() == 1) {
//            progressBar.setVisibility(View.GONE);
            categoryList.addAll(categoryResponse.getCategoryList());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.HORIZONTAL,
                    false);
            venue_category_recycler_View.setLayoutManager(gridLayoutManager);
            VenueCategoryAdapter venueCategoryAdapter = new VenueCategoryAdapter(getActivity(),
                    categoryList, this);
            venue_category_recycler_View.setAdapter(venueCategoryAdapter);

        } else {
            new BaseClass(getActivity()).showToast(categoryResponse.getMessage());
        }
    }

    @Override
    public void onItemClick(int pos) {
        VenueSubCatFragment fragment = new VenueSubCatFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("categoryId", categoryList.get(pos).getCategoryId());
        bundle.putString("categoryName", categoryList.get(pos).getCategoryName());
        bundle.putString("FromDashBoard", "0");
        fragment.setArguments(bundle);
        ((HomeActivity) getActivity()).replaceVenueSubCatFragment(fragment);

    }

    private List<VeneuHomeListResponse.VenuesHomeList> venuesHomeLists = new ArrayList<>();
    private List<EventListResponse.EventsListBean> eventsListBeans = new ArrayList<>();

    @Override
    public void onApiResponse(Object response) {
        progress.dismiss();
//        progressBar.setVisibility(View.GONE);
        /*if (page == 0) {
            venuesHomeLists.clear();
        }*/
//        Log.d("MyResponse", response.toString());
        if (response != null) {
            try {
                if (response instanceof VeneuHomeListResponse) {
                    VeneuHomeListResponse veneuHomeListResponse = (VeneuHomeListResponse) response;
                    if (veneuHomeListResponse.getStatus() == 1) {
                        reachMax = veneuHomeListResponse.getTotalPages();
                        if (veneuHomeListResponse.getVenuesList() != null)
                            venuesHomeLists.addAll(veneuHomeListResponse.getVenuesList());
                        if (isPageUpdate.equals("0")) {
                            SetVenueAdapter(venuesHomeLists, eventsListBeans, type);
                        } else {
                            adapterHomeList.notifyDataSetChanged();
                        }

                    } else {
                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, veneuHomeListResponse.getMessage());
                        errorDialog.show();
//                        new BaseClass(getActivity()).showToast(veneuHomeListResponse.getMessage());
                    }
                } else if (response instanceof UserAllListResposne) {
                    allUsersResponse = (UserAllListResposne) response;
                    Log.d(TAG, "onApiResponse: " + allUsersResponse.getUsersList().getInfluencer());
                    if (allUsersResponse.getStatus() == 1) {
                        onlineUsers = allUsersResponse.getUsersList().getOnlineUsers();
                        influenceUsers = allUsersResponse.getUsersList().getInfluencer();
                        /*setTopAdapter(onlineUsers,influenceUsers,"1");*/
                    } else {

                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, allUsersResponse.getMessage());
                        errorDialog.show();
                    }
                } else if (response instanceof EventListResponse) {
                    EventListResponse eventListResponse = (EventListResponse) response;
                    if (eventListResponse.getStatus() == 1) {
                        SetVenueAdapter(venuesHomeLists, eventListResponse.getEventsList(), type);
                    } else {

                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, eventListResponse.getMessage());
                        errorDialog.show();
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, "onApiResponse: " + e.getMessage());
            }
        }

    }

    private void setTopAdapter(String s) {
        if (onlineUsers != null && influenceUsers != null) {
            AdapterTopProfiles adapter = new AdapterTopProfiles(getContext(), onlineUsers,
                    influenceUsers, s);
            rv_top_profiles.setAdapter(adapter);
        }
    }

    private String TAG = VenueHomeFragment.class.getName();

    @Override
    public void onApiFailure(String message) {
//        progressBar.setVisibility(View.GONE);
//        if (!message.equals("") && message != null) {
////            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, message);
////            errorDialog.show();
//        }

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            page = 1;
            if (user_id > 0)
                callHomeVenueListApi(page, user_id);
        }
    }

    @Override
    public void click_interface_home(int position, String name) {

    }

    public void setLocation(Place place) {
        StringBuilder stBuilder = new StringBuilder();

        String placename = String.format("%s", place.getName());
        tinyDB.putDouble(Constants.LATITUDE, place.getLatLng().latitude);
        tinyDB.putDouble(Constants.LONGITUDE, place.getLatLng().longitude);
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

        countryName.setText(placename);
        lat = String.valueOf(place.getLatLng().latitude);
        longi = String.valueOf(place.getLatLng().longitude);
        if (Utility.isNetworkConnected(getActivity())) {
            callHomeVenueListApiss(page, user_id);
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

//                textView.setText(stBuilder.toString());
    }

    private void callHomeVenueListApiss(int page, int user_id) {
        isPageUpdate = "0";
//        progressBar.setVisibility(View.VISIBLE);
        if (page == 1) {
            venuesHomeLists.clear();
        }
        ApiInterface apiService = new APIClient().getClient().create(ApiInterface.class);
        Call<VeneuHomeListResponse> call = apiService.getHomeVenueList(page, user_id, lat, longi);
        new ResponseListner(this).getResponse(getContext(), call);
        Log.e("URL", call.request().url().toString());
    }

}