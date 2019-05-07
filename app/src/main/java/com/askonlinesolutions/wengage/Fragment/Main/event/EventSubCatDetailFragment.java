package com.askonlinesolutions.wengage.Fragment.Main.event;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Adapter.AdapterCategoryListBottom1;
import com.askonlinesolutions.wengage.Adapter.AdapterCategoryListBottom1Events;
import com.askonlinesolutions.wengage.Adapter.AdapterCategoryListTop;
import com.askonlinesolutions.wengage.Adapter.AdapterTopProfiles;
import com.askonlinesolutions.wengage.Fragment.Main.venue.RestaurantList;
import com.askonlinesolutions.wengage.Fragment.Main.venue.UserAllListResposne;
import com.askonlinesolutions.wengage.Fragment.Sub.Filter;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.ApiResponse;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Utility;
import com.mukesh.tinydb.TinyDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventSubCatDetailFragment extends Fragment implements View.OnClickListener,
        AdapterCategoryListTop.InterfaceCategoryListTop, CompoundButton.OnCheckedChangeListener,
        AdapterCategoryListBottom1.Interface_RestaurantList1, AdapterCategoryListBottom1Events
                .Interface_RestaurantListEvents, OnResponseInterface, EventSubCatListAdapter.Interface_RestaurantList1 {

    public EventSubCatDetailFragment() {
        // Required empty public constructor
    }

    private TextView allTv, todayTv, thisWeakTv, thisMonthTv;

    private String cat_name, selected_pref, main_cat_name, subCatName, type;
    private int categoryId, subCategoryId, pagenum = 1, user_id;
    ProgressDialog progressDialog;
    private ProgressBar progressBar;
    private TinyDB tinyDB;
    private RecyclerView rv_top, rv_bottom, rv_top_profiles, rv_list_events;
    private TextView tv_title, tv_language;
    private LinearLayout iv_arrow;
    private ArrayList<String> cat_top = new ArrayList<>();
    private ImageView iv, filter_icon, iv_table;
    private boolean comingfromserver;
    LinearLayoutManager layoutManagaer;
    private LinearLayout layout_top_switch;
    private SwitchCompat sw;
    private String bottom_type;
    private Dialog errorDialog;
    int page_number = 1;
    private boolean userScrolled = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean visible = false;
    private String isPageUpdate;
    private EventSubCatListAdapter adapte1;
    private String lati, longi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tinyDB = new TinyDB(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getIntentData();
        if (Utility.isNetworkConnected(getActivity())) {
            init();
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }

    private void getIntentData() {
        if (getArguments() != null) {
            categoryId = getArguments().getInt("categoryId");
            subCategoryId = getArguments().getInt("subCategoryId");
            comingfromserver = getArguments().getBoolean("responseComing");
            cat_name = getArguments().getString("catName");
            main_cat_name = getArguments().getString("main_category");
            selected_pref = getArguments().getString("selected_pref");
            subCatName = getArguments().getString("subCatName");
            type = getArguments().getString("type");
        }


    }


    private void init() {

        thisMonthTv = getView().findViewById(R.id.thisMonthTv);
        thisWeakTv = getView().findViewById(R.id.thisWeakTv);
        todayTv = getView().findViewById(R.id.todayTv);
        allTv = getView().findViewById(R.id.allTv);
//        tv_all = getView().findViewById(R.id.restaurant_list_top_tv_all);

        progressDialog = new BaseClass(getActivity()).creatProgressDialog();


        layout_filter = getView().findViewById(R.id.restaurant_list_filter_layout);
        tinyDB = new TinyDB(getContext());

        lati = String.valueOf(tinyDB.getDouble(Constants.LATITUDE, 0.0));
        longi = String.valueOf(tinyDB.getDouble(Constants.LONGITUDE, 0.0));
        user_id = tinyDB.getInt(Constants.USER_ID);
        bottom_type = type;
        progressBar = getView().findViewById(R.id.progress_bar);
        layout_top_switch = getView().findViewById(R.id.restaurant_list_top_profile_layout);
        sw = getView().findViewById(R.id.restaurant_list_top_profile_switch);
        sw.setChecked(true);
        sw.setOnCheckedChangeListener(this);

        iv = getView().findViewById(R.id.category_list_image_arrow);
        iv_table = getView().findViewById(R.id.restaurant_details_table);
        filter_icon = getView().findViewById(R.id.filter_icon_id);
        filter_icon.setOnClickListener(this);

        rv_top_profiles = getView().findViewById(R.id.recycler_top_profiles_2);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_top_profiles.setLayoutManager(horizontalLayoutManagaer);

        tv_title = getView().findViewById(R.id.category_list_title);
        tv_language = getView().findViewById(R.id.category_list_language);
        tv_language.setText(subCatName);
        iv_arrow = getView().findViewById(R.id.fragment_res_list_arrow_user);
        iv_arrow.setOnClickListener(this);

        tv_title.setText(cat_name);

        //    tv_title.setText(main_cat_name);
        tv_title.setOnClickListener(this);

        rv_top = getView().findViewById(R.id.recycler_category_top);
        LinearLayoutManager horizontalManagaer
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_top.setLayoutManager(horizontalManagaer);

        rv_bottom = getView().findViewById(R.id.recycler_category_bottom);

        layoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_bottom.setLayoutManager(layoutManagaer);
        rv_bottom.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        isPageUpdate = "1";
                        userScrolled = false;
                        page_number++;
                        performPaging(page_number);
                    }


                }
            }
        });

        rv_list_events = getView().findViewById(R.id.recycler_restaurant_list_events);
        LinearLayoutManager lay = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_list_events.setLayoutManager(lay);
        allTv.setOnClickListener(this);
        todayTv.setOnClickListener(this);
        thisWeakTv.setOnClickListener(this);
        thisMonthTv.setOnClickListener(this);


        allTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));
        todayTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));
        thisWeakTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));
        thisMonthTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));

        allTv.setTextColor(getResources().getColor(R.color.text_color_meg));
        todayTv.setTextColor(getResources().getColor(R.color.text_color_meg));
        thisWeakTv.setTextColor(getResources().getColor(R.color.text_color_meg));
        thisMonthTv.setTextColor(getResources().getColor(R.color.text_color_meg));
       /* if (comingfromserver) {
            if (venueListResponse != null)
                getVenueListData(venueListResponse);
        } else {
            callApi(pagenum);
        }*/

        callApi(pagenum);
        callSubCatByUser(categoryId, subCategoryId);


        //  setMyAdapter();
    }

    private void performPaging(int page_number) {
        if (Utility.isNetworkConnected(getActivity())) {
            if (lati.equals("0.0") && longi.equals("0.0")) {
                Call<EventSubCatDetailResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatSubEventListWithoutLatLong(page_number, user_id, categoryId, subCategoryId);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.e("URL", call.request().url().toString());
            } else {
                Call<EventSubCatDetailResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatSubEventList(page_number, user_id, categoryId, subCategoryId,
                                lati, longi);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.e("URL", call.request().url().toString());
            }
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }

    private void callSubCatByUser(int categoryId, int subCategoryId) {
        if (Utility.isNetworkConnected(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);
            Call<UserAllListResposne> call = APIClient.getInstance().getApiInterface()
                    .getUsersBySubCategory(user_id, categoryId, subCategoryId,
                            String.valueOf(tinyDB.getDouble(Constants.LATITUDE, 28.5355)),
                            String.valueOf(tinyDB.getDouble(Constants.LONGITUDE, 77.3910)));
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("URL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    private List<EventSubCatDetailResponse.EventsListBean> eventsListBeans = new ArrayList<>();

    private void getVenueListData(EventSubCatDetailResponse eventSubCatDetailResponse) {

        adapte1 = new EventSubCatListAdapter(getContext(), this, eventsListBeans);
        rv_bottom.setAdapter(adapte1);
        rv_bottom.setVisibility(View.VISIBLE);
    }

    private void callApi(int pagenum) {
        if (Utility.isNetworkConnected(getActivity())) {
            isPageUpdate = "0";
            progressBar.setVisibility(View.VISIBLE);
            if (lati.equals("0.0") && longi.equals("0.0")) {
                Call<EventSubCatDetailResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatSubEventListWithoutLatLong(pagenum, user_id, categoryId, subCategoryId);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.e("URL", call.request().url().toString());
            } else {
                Call<EventSubCatDetailResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatSubEventList(pagenum, user_id, categoryId, subCategoryId, lati, longi);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.e("URL", call.request().url().toString());
            }
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }

    AdapterTopProfiles adapter_top_profiles;

    private ArrayList<String> arr_pref = new ArrayList<>();
    private ArrayList<String> arr_pref_click = new ArrayList<>();
    private AdapterCategoryListTop adapter;

    private LinearLayout layout_filter;


    @Override
    public void viewItemDetails(int pos) {
        EventDetailsFragment fragment = new EventDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("event_id", eventsListBeans.get(pos).getEventId());
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        ((HomeActivity) getActivity()).replaceEventDetailFragment(fragment);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (bottom_type.equals("venues")) {
            rv_bottom.setVisibility(View.VISIBLE);
            rv_list_events.setVisibility(View.GONE);
        } else {
            rv_bottom.setVisibility(View.GONE);
            rv_list_events.setVisibility(View.VISIBLE);
        }
        if (selected_pref != null && !selected_pref.equals("French")) {
            rv_bottom.setVisibility(View.GONE);
//            rv_top_profiles.setVisibility(View.GONE);
        }
    }


    boolean arrow_status = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_res_list_arrow_user:

                if (arrow_status) {
                    iv.setImageResource(R.drawable.arrow_up);
                    layout_top_switch.setVisibility(View.VISIBLE);
                    rv_top_profiles.setVisibility(View.VISIBLE);
                    setTopAdapter("1");

                    tv_title.setVisibility(View.GONE);
                    tv_language.setVisibility(View.GONE);
                    arrow_status = false;
                } else {
                    iv.setImageResource(R.drawable.arrow_down);
                    rv_top_profiles.setVisibility(View.GONE);
                    layout_top_switch.setVisibility(View.GONE);
//                    rv_top_profiles.animate().translationY(0);
                    tv_title.setVisibility(View.VISIBLE);
                    tv_language.setVisibility(View.VISIBLE);
                    arrow_status = true;
                }
                break;
            case R.id.category_list_title:
                getActivity().onBackPressed();
                break;
            case R.id.filter_icon_id:
                new BaseClass(getContext()).callFragment1(new Filter(), Filter.class.getName(), getFragmentManager());
/*
                Filter fragment = new Filter();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_up, 0,
                        0,R.animator.slide_down).
                        replace(R.id.frame, fragment).addToBackStack(fragment.getClass().getName()).commit();
*/

                break;
            case R.id.restaurant_list_top_layout_all:
//                tv_all.setTextColor(getResources().getColor(R.color.white));

                for (int i = 0; i < arr_pref_click.size(); i++) {
                    arr_pref_click.set(i, "0");
                }

//                adapter.notifyDataSetChanged();
                break;

            case R.id.allTv:
                allTv.setBackground(getResources().getDrawable(R.drawable.select_drawable));
                todayTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));
                thisWeakTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));
                thisMonthTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));

                allTv.setTextColor(getResources().getColor(R.color.white));
                todayTv.setTextColor(getResources().getColor(R.color.text_color));
                thisWeakTv.setTextColor(getResources().getColor(R.color.text_color));
                thisMonthTv.setTextColor(getResources().getColor(R.color.text_color));

                break;
            case R.id.todayTv:

                allTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));
                todayTv.setBackground(getResources().getDrawable(R.drawable.select_drawable));
                thisWeakTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));
                thisMonthTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));

                allTv.setTextColor(getResources().getColor(R.color.text_color));
                todayTv.setTextColor(getResources().getColor(R.color.white));
                thisWeakTv.setTextColor(getResources().getColor(R.color.text_color));
                thisMonthTv.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.thisWeakTv:

                allTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));
                todayTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));
                thisWeakTv.setBackground(getResources().getDrawable(R.drawable.select_drawable));
                thisMonthTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));

                allTv.setTextColor(getResources().getColor(R.color.text_color));
                todayTv.setTextColor(getResources().getColor(R.color.text_color));
                thisWeakTv.setTextColor(getResources().getColor(R.color.white));
                thisMonthTv.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.thisMonthTv:

                allTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));
                todayTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));
                thisWeakTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));
                thisMonthTv.setBackground(getResources().getDrawable(R.drawable.select_drawable));

                allTv.setTextColor(getResources().getColor(R.color.text_color));
                todayTv.setTextColor(getResources().getColor(R.color.text_color));
                thisWeakTv.setTextColor(getResources().getColor(R.color.text_color));
                thisMonthTv.setTextColor(getResources().getColor(R.color.white));
                break;
            default:
                break;
        }
    }

    @Override
    public void click_CategoryListTop(int position) {

/*        if (position == 0) {
            for (int i = 0; i < arr_pref_click.size(); i++) {
                if (i == 0) {
                    arr_pref_click.set(i, "1");
                } else {
                    arr_pref_click.set(i, "0");
                }
            }
        } else {
            arr_pref_click.set(0, "0");
            if(arr_pref_click.get(position).equals("1")){
               arr_pref_click.set(position, "0");
            } else{
                arr_pref_click.set(position, "1");
            }
        }*/
        for (int i = 0; i < arr_pref_click.size(); i++) {
            if (i == position) {
                if (arr_pref_click.get(position).equals("1")) {
                    arr_pref_click.set(position, "0");
                } else {
                    arr_pref_click.set(position, "1");
                }
            } else {
                arr_pref_click.set(i, "0");
            }
        }

        boolean _clicked = false;
        for (int i = 0; i < arr_pref_click.size(); i++) {
            if (arr_pref_click.get(i).equals("1")) {
                _clicked = true;
            }
        }
      /*  if (!_clicked) {
            tv_all.setTextColor(getResources().getColor(R.color.white));
            layput_top_all.setSelected(true);
        } else {
            tv_all.setTextColor(getResources().getColor(R.color.colorAccent));
            layput_top_all.setSelected(false);
        }*/

//        tv_all.setTextColor(getResources().getColor(R.color.colorAccent));
//        layput_top_all.setSelected(false);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        int id = compoundButton.getId();
        if (id == R.id.restaurant_list_top_profile_switch) {
            if (b) {
                setTopAdapter("1");
            } else setTopAdapter("2");

        }
    }

    @Override
    public void click_interface_restaurant_list_1(int position, String type, int status) {

        if (eventsListBeans.get(position).getVenueId() != null && eventsListBeans.get(position).getVenueId().length() > 0) {
            String venue_id = eventsListBeans.get(position).getVenueId();
            if (type.equals("bookmarks")) {
                eventsListBeans.get(position).setIsBookmarked(status);
                setBookmark(venue_id, status);
            } else if (type.equals("interested")) {
                eventsListBeans.get(position).setIsInterested(status);
                setinterest(venue_id, status);
            } else {

            }
        }/*
        if (adapte1!=null)
        adapte1.notifyDataSetChanged();*/
    }

    private void setBookmark(String venue_id, int status) {
        if (Utility.isNetworkConnected(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);
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
            Call<ApiResponse> call = APIClient
                    .getInstance()
                    .getApiInterface()
                    .markVenueInterest(tinyDB.getInt(Constants.USER_ID), venue_id, String.valueOf(status));

            progressBar.setVisibility(View.VISIBLE);
            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                    progressBar.setVisibility(View.GONE);

                    if (response != null) {
                        if (response.body().getStatus() == 1) {
                            new BaseClass(getActivity()).showToast(response.body().message);
                        } else {
                            new BaseClass(getActivity()).showToast(response.body().message);
                        }
                    } else {
                        progressDialog.hide();
                        new BaseClass(getActivity()).showToast("Data not found");
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {

                }
            });
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }


    }

    @Override
    public void click_interface_restaurant_list_events(int position, String type) {
    }

    private UserAllListResposne allUsersResponse;

    private List<UserAllListResposne.UsersListBean.OnlineUsersBean> onlineUsers/* = new ArrayList<>()*/;
    private List<UserAllListResposne.UsersListBean.InfluencerBean> influenceUsers/* = new ArrayList<>()*/;

    @Override
    public void onApiResponse(Object response) {
        progressBar.setVisibility(View.GONE);
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
                } else if (response instanceof EventSubCatDetailResponse) {
                    EventSubCatDetailResponse eventSubCatDetailResponse = (EventSubCatDetailResponse) response;
                    eventsListBeans.addAll(eventSubCatDetailResponse.getEventsList());
                    if (eventSubCatDetailResponse.getStatus() == 1) {
                        if (isPageUpdate.equals("0")) {
                            getVenueListData(eventSubCatDetailResponse);
                        } else {
                            adapte1.notifyDataSetChanged();
                        }

                    } else {
                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, eventSubCatDetailResponse.getMessage());
                        errorDialog.show();
                    }
                } else if (response instanceof ApiResponse) {
                    ApiResponse apiResponse = (ApiResponse) response;
                    if (apiResponse.getStatus() == 1) {

                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, apiResponse.getMessage());
                        errorDialog.show();
                    } else {

                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, apiResponse.getMessage());
                        errorDialog.show();
                    }
                }

            } catch (Exception e) {
                Log.d(TAG, "onApiResponse: " + e.getMessage());
            }
        }
    }

    private String TAG = RestaurantList.class.getName();

    @Override
    public void onApiFailure(String message) {
        progressBar.setVisibility(View.GONE);
    }

    private AdapterTopProfiles adapterTopProfiles;

    private void setTopAdapter(String s) {
        if (onlineUsers != null && influenceUsers != null) {
            adapterTopProfiles = new AdapterTopProfiles(getContext(), onlineUsers,
                    influenceUsers, s);
            rv_top_profiles.setAdapter(adapterTopProfiles);
        }
    }

}