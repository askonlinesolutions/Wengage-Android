package com.askonlinesolutions.wengage.Fragment.Main.event;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Adapter.AdapterHomeList;
import com.askonlinesolutions.wengage.Adapter.AdapterHomeViewPager;
import com.askonlinesolutions.wengage.Adapter.AdapterTopProfiles;
import com.askonlinesolutions.wengage.Adapter.VenueCategoryAdapter;
import com.askonlinesolutions.wengage.Fragment.Main.venue.UserAllListResposne;
import com.askonlinesolutions.wengage.Fragment.Main.vo.VeneuHomeListResponse;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
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
public class EventHomeFragment extends Fragment implements View.OnClickListener, AdapterHomeViewPager.Interface_Home,
        CompoundButton.OnCheckedChangeListener, VenueCategoryAdapter.CategoryItemClickListner,
        OnResponseInterface, AdapterHomeList.HomeVenuListInterface {

    private UserAllListResposne allUsersResponse;
    List<UserAllListResposne.UsersListBean.OnlineUsersBean> onlineUsers/* = new ArrayList<>()*/;
    List<UserAllListResposne.UsersListBean.InfluencerBean> influenceUsers/* = new ArrayList<>()*/;
    private List<VeneuHomeListResponse.VenuesHomeList> venuesHomeLists = new ArrayList<>();
    private List<EventListResponse.EventsListBean> eventsListBeans = new ArrayList<>();
    private RecyclerView rv_top_profiles, rv_list, venue_category_recycler_View;
    private TextView tv_title;
    private Progress progress;
    public static final int PLACE_PICKER_EVENT_REQUEST = 1002;
    private String type = "event";
    private LinearLayout layout_top_switch;
    int page = 1;
    private Dialog errorDialog;
    private String lati, longi;
    private AdapterHomeList adapterHomeList;
    int user_id;
    int reachMax;
    private TextView countryName;
    private ImageView iv;
    private TinyDB tinyDB;
    private boolean arrow_status = true;
    int page_number = 1;
    private boolean userScrolled = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean visible = false;
    LinearLayoutManager layoutManagaer;
    private String isPageUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        getintentData();
        init(view);
        if (Utility.isNetworkConnected(getActivity())) {
            getCategory(type);
            callApiUsers();
            callHomeEventListApi(page, user_id);
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
        return view;
    }


    @SuppressLint("SetTextI18n")
    private void init(View view) {
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        allUsersResponse = new UserAllListResposne();

        tinyDB = new TinyDB(getContext());
        user_id = tinyDB.getInt(Constants.USER_ID);

        countryName = view.findViewById(R.id.countryName);
        layout_top_switch = view.findViewById(R.id.home_top_profile_layout);
        SwitchCompat sw = view.findViewById(R.id.home_top_profile_switch);
        sw.setChecked(true);
        sw.setOnCheckedChangeListener(this);
        countryName.setText(tinyDB.getString(Constants.CITY));
        iv = view.findViewById(R.id.home_image_arrow);
        //    private ViewPager viewPager;
        //    private RecyclerView.LayoutManager mLayoutManager;
        LinearLayout iv_arrow = view.findViewById(R.id.home_user_list_top_arrow);
        tv_title = view.findViewById(R.id.home_title);
        iv_arrow.setOnClickListener(this);
        rv_top_profiles = view.findViewById(R.id.recycler_top_profiles);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_top_profiles.setLayoutManager(horizontalLayoutManagaer);
        countryName.setOnClickListener(this);
        rv_list = view.findViewById(R.id.recycler_home_list);

        layoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_list.setLayoutManager(layoutManagaer);
        venue_category_recycler_View = view.findViewById(R.id.venue_category_recycler);
        rv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
               /* if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    userScrolled = true;
                    visibleItemCount = layoutManagaer.getChildCount();
                    totalItemCount = layoutManagaer.getItemCount();
                    pastVisiblesItems = layoutManagaer
                            .findFirstVisibleItemPosition();
                    if (userScrolled
                            && (visibleItemCount + pastVisiblesItems) == totalItemCount) {
                        if (!visible) {
                            userScrolled = false;
                            page_number++;
                            isPageUpdate = "1";
                            performPaging(page_number);
                        }
                    }
                }*/
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
                        userScrolled = false;
                        page_number++;
                        isPageUpdate = "1";
                        performPaging(page_number);
                    }
                }
            }
        });
        tv_title.setText("EVENTS");

    }

    private void performPaging(int page) {
        if (Utility.isNetworkConnected(getActivity())) {
//        progress.show();
            ApiInterface apiService = new APIClient().getClient().create(ApiInterface.class);
            Call<EventListResponse> call = apiService.getHomeEventList(page, user_id, lati, longi);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("URL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    private void callHomeEventListApi(int page, int user_id) {
        if (Utility.isNetworkConnected(getActivity())) {
            if (!progress.isShowing()) {
                progress.show();
            }
            isPageUpdate = "0";
            ApiInterface apiService = new APIClient().getClient().create(ApiInterface.class);
            Call<EventListResponse> call = apiService.getHomeEventList(page, user_id, lati, longi);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("URL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    private void SetVenueAdapter() {
        adapterHomeList = new AdapterHomeList();
        rv_list.setAdapter(adapterHomeList);
    }

    @Override
    public void viewDetails(int pos) {
        EventDetailsFragment fragment = new EventDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("event_id", eventsListBeans.get(pos).getEventId());
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        ((HomeActivity) getActivity()).replaceEventDetailsFragment(fragment);
    }

    private void getCategory(String page) {
        if (Utility.isNetworkConnected(getActivity())) {
            ApiInterface apiService = new APIClient().getClient().create(ApiInterface.class);

            Call<CategoryResponse> call = apiService.getAllCategories(page);
            Log.d("MyUrl", call.request().url().toString());

            call.enqueue(new Callback<CategoryResponse>() {
                @Override
                public void onResponse(@NonNull Call<CategoryResponse> call, @NonNull Response<CategoryResponse> response) {

                    Log.d("MyResponse", response.body().getMessage());

                    getData(response.body());
//                progress.hide();

                }

                @Override
                public void onFailure(@NonNull Call<CategoryResponse> call, @NonNull Throwable t) {

                    progress.hide();

                }
            });
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    private void callApiUsers() {
        if (Utility.isNetworkConnected(getActivity())) {
            //  user_id = 165;
            Call<UserAllListResposne> call = APIClient.getInstance().getApiInterface().getAllUsers(user_id);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("URL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
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
            case R.id.countryName:
                Intent intent = null;
                try {
                    intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(getActivity());
                    getActivity().startActivityForResult(intent, PLACE_PICKER_EVENT_REQUEST);
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
    public void click_interface_home(int position, String name) {

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

    private List<CategoryList> categoryList = new ArrayList<>();

    public void getData(Object response) {
        if (categoryList != null) {
            categoryList.clear();
        }
        CategoryResponse categoryResponse = (CategoryResponse) response;
        if (categoryResponse.getStatus() == 1) {
//            progress.hide();
            categoryList.addAll(categoryResponse.getCategoryList());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
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
        EventSubCatFragment fragment = new EventSubCatFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("categoryId", categoryList.get(pos).getCategoryId());
        bundle.putString("categoryName", categoryList.get(pos).getCategoryName());
        bundle.putString("FromDashBoard", "0");

        fragment.setArguments(bundle);
        ((HomeActivity) getActivity()).replaceEventSubCatFragment(fragment);
    }


    @Override
    public void onApiResponse(Object response) {
        progress.dismiss();
//        if (page == 0) {
//            venuesHomeLists.clear();
//        }
//        Log.d("MyResponse", response.toString());
        if (response != null) {
            try {
                if (response instanceof UserAllListResposne) {
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
                        eventsListBeans.addAll(eventListResponse.getEventsList());
                        if (isPageUpdate.equals("0")) {
                            SetVenueAdapter();
                        } else {
                            adapterHomeList.notifyDataSetChanged();
                        }
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

    private String TAG = EventHomeFragment.class.getName();

    @Override
    public void onApiFailure(String message) {
        progress.hide();
        if (!message.equals("") && message != null) {
//            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog,message);
//            errorDialog.show();
        }

    }

//    @Override
//    public void lastIndex(int pos) {
//        page++;
//        if (page < reachMax)
//            callHomeVenueListApi(page, user_id);
//    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
////        if (isVisibleToUser) {
////            if (user_id > 0)
////                callHomeEventListApi(page, user_id);
////        }
//    }

    class AdapterHomeList extends RecyclerView.Adapter<AdapterHomeList.MyViewHolder> {

        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView iv, img_progress, greenDot, butterfly;
            TextView txtTitle, txtReview, date;
            RatingBar ratingBar;
            private RelativeLayout itemLayout;

            public MyViewHolder(View view) {
                super(view);
                img_progress = view.findViewById(R.id.item_home_list_progress);
                butterfly = view.findViewById(R.id.butterfly);
                greenDot = view.findViewById(R.id.greenDot);
                iv = view.findViewById(R.id.home_venue_image);
                txtTitle = view.findViewById(R.id.venue_city_name);
                txtReview = view.findViewById(R.id.venue_review_count);
                date = view.findViewById(R.id.date);
                ratingBar = view.findViewById(R.id.venue_rating);
                itemLayout = view.findViewById(R.id.itemLayout);
                Glide.with(getActivity()).asGif()
                        .load(R.drawable.load)
                        .into(img_progress);
            }
        }


        @Override
        public AdapterHomeList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_home_list, parent, false);

            return new AdapterHomeList.MyViewHolder(itemView);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(final AdapterHomeList.MyViewHolder holder, final int position) {

            holder.itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventDetailsFragment fragment = new EventDetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("event_id", eventsListBeans.get(position).getEventId());
                    bundle.putString("type", type);
                    fragment.setArguments(bundle);
                    ((HomeActivity) getActivity()).replaceEventDetailsFragment(fragment);
                }
            });
            if (eventsListBeans.get(position).getIsInterested() == 1) {
                holder.greenDot.setVisibility(View.VISIBLE);
            } else {
                holder.greenDot.setVisibility(View.GONE);
            }

            if (eventsListBeans.get(position).getRecommendByWengage() == 1) {
                holder.butterfly.setVisibility(View.VISIBLE);
            } else {
                holder.butterfly.setVisibility(View.GONE);
            }

            if (!eventsListBeans.get(position).getImageURL().equalsIgnoreCase("")) {
                Glide.with(getActivity())
                        .load(eventsListBeans.get(position).getImageURL())
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                        .apply(RequestOptions.skipMemoryCacheOf(true))
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                        Target<Drawable> target, boolean isFirstResource) {
                                holder.img_progress.setVisibility(View.GONE);

                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                           DataSource dataSource, boolean isFirstResource) {
                                holder.img_progress.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .into(holder.iv);
            }

            if (eventsListBeans.get(position).getReviewCount() != null && !eventsListBeans.get(position).getReviewCount().equals("0"))
                holder.txtReview.setText(eventsListBeans.get(position).getReviewCount() + "Reviews");
            else holder.txtReview.setText("");
            holder.txtTitle.setText(eventsListBeans.get(position).getName());
            holder.date.setText(eventsListBeans.get(position).getStartDate());
            if (eventsListBeans.get(position).getAvgRating() != null && !eventsListBeans.get(position).getAvgRating().equals("0"))
                holder.ratingBar.setRating(Float.parseFloat(eventsListBeans.get(position).getAvgRating()));

        }

        @Override
        public int getItemCount() {
            return eventsListBeans.size();
        }
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
        lati = String.valueOf(place.getLatLng().latitude);
        longi = String.valueOf(place.getLatLng().longitude);
        callHomeEventListApiss(page, user_id);
//                textView.setText(stBuilder.toString());
    }

    private void callHomeEventListApiss(int page, int user_id) {
        if (Utility.isNetworkConnected(getActivity())) {
            isPageUpdate = "0";
            ApiInterface apiService = new APIClient().getClient().create(ApiInterface.class);
            Call<EventListResponse> call = apiService.getHomeEventList(page, user_id, lati, longi);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("URL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

}