package com.askonlinesolutions.wengage.Fragment.Main.event;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
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
import com.askonlinesolutions.wengage.Adapter.AdapterHome2Prefs;
import com.askonlinesolutions.wengage.Adapter.AdapterHomeList;
import com.askonlinesolutions.wengage.Adapter.AdapterHomeViewPager2;
import com.askonlinesolutions.wengage.Adapter.AdapterTopProfiles;
import com.askonlinesolutions.wengage.Adapter.SubCategoryEventAdapter;
import com.askonlinesolutions.wengage.Fragment.Main.venue.UserAllListResposne;
import com.askonlinesolutions.wengage.Fragment.Main.vo.VeneuHomeListResponse;
import com.askonlinesolutions.wengage.Model.CategoryList;
import com.askonlinesolutions.wengage.Model.EventCategoryModal;
import com.askonlinesolutions.wengage.Model.Response.SubCategoryResponse;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.database.DatabaseHelper;
import com.askonlinesolutions.wengage.network.APIClient;
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
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.mukesh.tinydb.TinyDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventSubCatFragment extends Fragment implements View.OnClickListener, AdapterHomeViewPager2
        .Interface_Home_2, AdapterHome2Prefs.Interface_Home_2_Pref, CompoundButton.OnCheckedChangeListener,
        SubCategoryEventAdapter.SubCategoryItemClickListner, OnResponseInterface, AdapterHomeList.HomeVenuListInterface {


    public EventSubCatFragment() {
        // Required empty public constructor
    }

    String cat_name;
    private AdapterHomeList adapterHomeList;
    String selected_pref = "French";
    private int category_id, sub_cat;
    private FlexboxLayoutManager flexboxLayoutManager;
    private List<UserAllListResposne.UsersListBean.InfluencerBean> influencerList;
    private List<UserAllListResposne.UsersListBean.OnlineUsersBean> onlineUserList;
    private List<VeneuHomeListResponse.VenuesHomeList> venuesHomeLists = new ArrayList<>();
    private RecyclerView rv_top_profiles, rv_list, subCatecory_recycler, subCatVenueListRecyclerView;
    private ViewPager viewPager;
    //    private TextView tv_pref_1, tv_pref_2, tv_pref_3, tv_pref_4, tv_pref_5, tv_pref_6, tv_pref_7, tv_pref_8;
    private List<SubCategoryResponse.EventPreferencesBean> preferencesBeans;
    private TextView tv_title, categoryName;
    private ImageView iv;
    private int user_id;
    private Dialog errorDialog;
    public static final int PLACE_PICKER_EVENT_SUB_REQUEST = 1006;
    int pagenum = 1;
    private Progress progress;
    private double lat, lang;
    List<EventCategoryModal> eventCategoryModalList = new ArrayList<>();
    List<EventCategoryModal> eventCategoryModalLists = new ArrayList<>();
    LinearLayoutManager linearLayoutManager1;
    private boolean isDataLoading;
    private boolean userScrolled = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean visible = false;
    private LinearLayout remove;
    LinearLayoutManager layoutManagaer;
    private String isPageUpdate;
    private String lati, longi, FromDashBoard;
    private NestedScrollView nestedScrollView;
    TinyDB tinyDB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home2, container, false);


        DatabaseHelper db = DatabaseHelper.getInstance(getActivity());
        category_id = getArguments().getInt("categoryId");
        List<CategoryList> mainCategoryModalList = db.getCategoryList();
        eventCategoryModalList = db.getEventList(String.valueOf(category_id));
        getMyInternt();

        if (Utility.isNetworkConnected(getActivity())) {
            init(view);
            callSubCatApi();
            callCatByUser(category_id);
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

        return view;
    }

    private void getMyInternt() {

        if (getArguments() != null) {
            cat_name = getArguments().getString("categoryName");
            category_id = getArguments().getInt("categoryId");
            FromDashBoard = getArguments().getString("FromDashBoard");


        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private RecyclerView rv_pref;
    private LinearLayout layout_top_switch;

    @SuppressLint("SetTextI18n")
    private void init(View view) {

        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        TextView tv_pref = view.findViewById(R.id.home_2_pref_text);


        tinyDB = new TinyDB(getContext());
        lati = String.valueOf(tinyDB.getDouble(Constants.LATITUDE, 0.0));
        longi = String.valueOf(tinyDB.getDouble(Constants.LONGITUDE, 0.0));
        String str = tinyDB.getString("bottom_click");
        user_id = tinyDB.getInt(Constants.USER_ID);

        RelativeLayout bottom_layout_venues = view.findViewById(R.id.home_2_bottom_layout_venues);
        RelativeLayout bottom_layout_events = view.findViewById(R.id.home_2_bottom_layout_events);
        TextView tv_message = view.findViewById(R.id.not_available_text);
        nestedScrollView = view.findViewById(R.id.nestedScrollView);
        categoryName = view.findViewById(R.id.categoryName);
        remove = view.findViewById(R.id.remove);
        subCatVenueListRecyclerView = view.findViewById(R.id.fragment_home2_recycler_venue);
        ImageView iv_blog_1 = view.findViewById(R.id.home_2_blog_1_image);
        ImageView iv_blog_2 = view.findViewById(R.id.home_2_blog_2_image);
        TextView tv_blog_1_text_1 = view.findViewById(R.id.home_2_blog_1_text_1);
        TextView tv_blog_1_text_2 = view.findViewById(R.id.home_2_blog_1_text_2);
        TextView tv_blog_2_text_1 = view.findViewById(R.id.home_2_blog_2_text_1);
        TextView tv_blog_2_text_2 = view.findViewById(R.id.home_2_blog_2_text_2);
        TextView tv_blog_2_text_3 = view.findViewById(R.id.home_2_blog_2_text_3);


        subCatecory_recycler = view.findViewById(R.id.venue_subcategory_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        subCatecory_recycler.setLayoutManager(linearLayoutManager);

        linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        subCatVenueListRecyclerView.setLayoutManager(linearLayoutManager1);
        subCatecory_recycler.setNestedScrollingEnabled(false);
        subCatVenueListRecyclerView.setNestedScrollingEnabled(false);
        iv_blog_1.setImageResource(R.drawable.home_2_blog_1);
        iv_blog_2.setImageResource(R.drawable.home_2_blog_2);

        tv_blog_1_text_1.setText("Food Blogger");
        tv_blog_1_text_2.setText(R.string.blog_text_1);

        tv_blog_2_text_1.setText(R.string.blog_title_2);
        tv_blog_2_text_3.setText("Chloe Dubois");

        //    bottom_layout_venues.setVisibility(View.VISIBLE);
        bottom_layout_events.setVisibility(View.GONE);

        layout_top_switch = view.findViewById(R.id.home_2_top_profile_layout);
        SwitchCompat sw = view.findViewById(R.id.home_2_top_profile_switch);
        sw.setChecked(true);
        sw.setOnCheckedChangeListener(this);

        iv = view.findViewById(R.id.home2_image_arrow);
        tv_title = view.findViewById(R.id.home2_title);
        LinearLayout iv_arrow = view.findViewById(R.id.home2_arrow);
        iv_arrow.setOnClickListener(this);

        tv_title.setText(getArguments().getString("categoryName"));
        tv_title.setOnClickListener(this);
        rv_top_profiles = view.findViewById(R.id.recycler_top_profiles_2);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_top_profiles.setLayoutManager(horizontalLayoutManagaer);
        rv_top_profiles.setNestedScrollingEnabled(false);
        rv_pref = view.findViewById(R.id.preference_recycler);
        rv_pref.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        if (eventCategoryModalList.size() > 0) {
//            eventCategoryModalList
            for (int i = 0; i < eventCategoryModalList.size(); i++) {
                if (eventCategoryModalList.get(i).getIsSelected() == 1) {
                    eventCategoryModalLists.add(eventCategoryModalList.get(i));
                }
            }
            setAdapter(0);
        }
        rv_pref.setNestedScrollingEnabled(false);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                visibleItemCount = linearLayoutManager1.getChildCount();
                totalItemCount = linearLayoutManager1.getItemCount();
                pastVisiblesItems = linearLayoutManager1
                        .findFirstVisibleItemPosition();
                View view = (View) v.getChildAt(v.getChildCount() - 1);
                int diff = (view.getBottom() - (v.getHeight() + v.getScrollY()));
                if (diff == 0) {
                    if (scrollY > oldScrollY) {

                        userScrolled = true;
                        if (userScrolled
                                && (visibleItemCount + pastVisiblesItems) == totalItemCount) {
                            userScrolled = false;
                            isPageUpdate = "1";
//
                            if (!isDataLoading)
                                progress.show();
                            new PagingEventAsync().execute();
//                        }
                        }
                    }
                }

            }
        });
        categoryName.setText(getArguments().getString("categoryName") + "+" + tinyDB.getString(Constants.CITY));
        categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                try {
                    intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(getActivity());
                    getActivity().startActivityForResult(intent, PLACE_PICKER_EVENT_SUB_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        if (FromDashBoard.equals("1")) {
            remove.setVisibility(View.GONE);
        } else {
            remove.setVisibility(View.VISIBLE);
        }
        callHomeEventListApi(pagenum, user_id);

    }

    private void performPaging(int pagenum) {
        if (Utility.isNetworkConnected(getActivity())) {
//        progress.show();
            if (lati.equals("0.0") && longi.equals("0.0")) {
                Call<EventListResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatSubEventListsWithoutLatLong(pagenum, user_id, category_id);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.e("URL", call.request().url().toString());
            } else {
                Call<EventListResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatSubEventLists(pagenum, user_id, category_id, lati, longi);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.e("URL", call.request().url().toString());
            }
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }

    private void callHomeEventListApi(int page, int user_id) {
        if (Utility.isNetworkConnected(getActivity())) {
            isPageUpdate = "0";
//        progress.show();
            if (lati.equals("0.0") && longi.equals("0.0")) {
                Call<EventListResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatSubEventListsWithoutLatLong(page, user_id, category_id);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.e("URL", call.request().url().toString());
            } else {
                Call<EventListResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatSubEventLists(page, user_id, category_id, lati, longi);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.e("URL", call.request().url().toString());
            }
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }


    private void setVenueListAdapter() {

        adapterHomeList = new AdapterHomeList();
        subCatVenueListRecyclerView.setAdapter(adapterHomeList);
    }


    private List<EventCategoryModal> subCategoryListBeanList = new ArrayList<>();

    private void setSubCatAdapter(SubCategoryResponse subCategoryResponse) {
        if (subCategoryListBeanList != null) {
            subCategoryListBeanList.clear();
        }
        subCategoryListBeanList.addAll(subCategoryResponse.getEventSubCategoryList());
        SubCategoryEventAdapter subCategoryAdapter = new SubCategoryEventAdapter(getActivity(),
                subCategoryListBeanList, this, category_id);
        subCatecory_recycler.setAdapter(subCategoryAdapter);
    }

    private void callCatByUser(int catId) {
        if (Utility.isNetworkConnected(getActivity())) {
//        progress.show();
            Call<UserAllListResposne> call = APIClient.getInstance().getApiInterface().getUsersByCategory(user_id, "event", catId);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("URL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    private String TAG = EventSubCatFragment.class.getName();

    private void callSubCatApi() {
        if (Utility.isNetworkConnected(getActivity())) {
//        progress.show();
            Call<SubCategoryResponse> call = APIClient.getInstance().getApiInterface()
                    .getAllSubCategories(category_id, user_id, "event",
                            String.valueOf(tinyDB.getDouble(Constants.LATITUDE, 28.5355)),
                            String.valueOf(tinyDB.getDouble(Constants.LONGITUDE, 77.3910)));
            Log.e("URL", call.request().url().toString());
            new ResponseListner(this).getResponse(getActivity(), call);
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    private AdapterHome2Prefs adapter;
//    private int clicked_position = 99;

//    private void setPrefrences(List<EventCategoryModal> preferences) {
//        if (preferences != null && preferences.size() > 0) {
//            adapter = new AdapterHome2Prefs(preferences, this, getContext());
//            rv_pref.setAdapter(adapter);
//        } else {
//            tv_pref.setVisibility(View.VISIBLE);
//        }
//
//    }

    @Override
    public void viewDetails(int pos) {
        EventDetailsFragment fragment = new EventDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("event_id", eventsListBeans.get(pos).getEventId());
        bundle.putString("type", "event");
        fragment.setArguments(bundle);
        ((HomeActivity) getActivity()).replaceEventDetailFragment(fragment);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private boolean arrow_status = true;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.home2_arrow:
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
               /* if (arrow_status) {
                    iv.setImageResource(R.drawable.arrow_up);
                    if (onlineUserList != null && onlineUserList.size() != 0) {
                        rv_top_profiles.setVisibility(View.VISIBLE);
                    } else {
                        tv_message.setVisibility(View.VISIBLE);
                    }
                    setTopAdapter("1");
                    layout_top_switch.setVisibility(View.VISIBLE);
                    tv_title.setVisibility(View.GONE);
                    arrow_status = false;
                } else {
                    rv_top_profiles.setVisibility(View.GONE);
                    layout_top_switch.setVisibility(View.GONE);
                    iv.setImageResource(R.drawable.arrow_down);
                    tv_title.setVisibility(View.GONE);
                    arrow_status = true;
                }*/
                break;
            case R.id.home2_title:
                getActivity().onBackPressed();
                break;
            default:
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

//        outState.putInt("selectef_pref", id);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        //            id = savedInstanceState.getInt("selected_pref");
    }

    @Override
    public void click_interface_home_2(int position, String name) {

/*
        if (!name.equals("More")) {
//        new BaseClass(getContext()).showToast(name);
            final RestaurantList fragment = new RestaurantList();
            Bundle bundle = new Bundle();
            bundle.putString("main_category", cat_name);
            bundle.putString("category", name);
            bundle.putString("selected_pref", selected_pref);
            fragment.setArguments(bundle);
            new BaseClass(getContext()).callFragment(fragment, fragment.getClass().getName(), getFragmentManager());
        }
*/
    }

    @Override
    public void click_interface_home_2(int position) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        int id = compoundButton.getId();
        if (id == R.id.home_2_top_profile_switch) {
            if (b) {
                setTopAdapter("1");
            } else setTopAdapter("2");

        }
    }
    /*categoryId,categoryList.get(position).getSubCategoryId()*/

    @Override
    public void onSubCategoryItemClick(int pos) {
        final EventSubCatDetailFragment fragment = new EventSubCatDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("categoryId", subCategoryListBeanList.get(pos).getCategoryId());
        bundle.putInt("subCategoryId", subCategoryListBeanList.get(pos).getSubCategoryId());
        bundle.putString("subCatName", subCategoryListBeanList.get(pos).getSubCategoryName());
        bundle.putString("catName", subCategoryListBeanList.get(pos).getCategoryName());
        bundle.putString("type", "event");

        fragment.setArguments(bundle);
        ((HomeActivity) getActivity()).replaceEventSubCatDetailFragment(fragment);


    }

    private List<EventListResponse.EventsListBean> eventsListBeans = new ArrayList<>();

    private void setTopAdapter(String s) {
        if (onlineUserList != null && influencerList != null) {
            AdapterTopProfiles adapter = new AdapterTopProfiles(getContext(), onlineUserList,
                    influencerList, s);
            rv_top_profiles.setAdapter(adapter);
        }
    }

    private int page = 1;

    @Override
    public void onApiResponse(Object response) {
        progress.hide();
      /*  if (page == 0 && eventsListBeans != null) {
            eventsListBeans.clear();
        }*/
        if (response != null) {
            try {
                if (response instanceof UserAllListResposne) {
                    UserAllListResposne allUsersResponse = (UserAllListResposne) response;
                    Log.d(TAG, "onApiResponse: " + allUsersResponse.getUsersList().getInfluencer());
                    if (allUsersResponse.getStatus() == 1) {
                        onlineUserList = allUsersResponse.getUsersList().getOnlineUsers();
                        influencerList = allUsersResponse.getUsersList().getInfluencer();
                        /*setTopAdapter(onlineUsers,influenceUsers,"1");*/
                    } else {
                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, allUsersResponse.getMessage());
                        errorDialog.show();
                    }
                } else if (response instanceof SubCategoryResponse) {
                    SubCategoryResponse subCategoryResponse = (SubCategoryResponse) response;
                    if (subCategoryResponse.getStatus() == 1) {
//                        List<EventCategoryModal> preferencesBeans = new ArrayList<>();
                        setSubCatAdapter(subCategoryResponse);
                        preferencesBeans = new ArrayList<>();
                       /* for (int i = 0; i < subCategoryResponse.getEventSubCategoryList().size(); i++) {
                            if (preferencesBeans.size() < 8) {
                                preferencesBeans.add(subCategoryResponse.getEventSubCategoryList().get(i));
                            }
                        }*/
                        for (int i = 0; i < subCategoryResponse.getEventPreferences().size(); i++) {
                            if (preferencesBeans.size() < 8) {
                                preferencesBeans.add(subCategoryResponse.getEventPreferences().get(i));
                            }
                        }
                        if (preferencesBeans.size() > 0) {
                            if (eventCategoryModalLists.size() < 1) {
                                setAdapter(1);
                            }

                        }
//                        setPrefrences(preferencesBeans);
                    } else {

                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, subCategoryResponse.getMessage());
                        errorDialog.show();
                    }  //                        new BaseClass(getActivity()).showToast(subCategoryResponse.getMessage());

                } else if (response instanceof EventListResponse) {

                    pagenum++;
//                    Toast.makeText(getActivity(), pagenum+"", Toast.LENGTH_SHORT).show();
                    isDataLoading = false;
                    EventListResponse eventListResponse = (EventListResponse) response;
                    if (eventListResponse.getStatus() == 1) {
                        int reachMax = eventListResponse.getTotalPages();
                        eventsListBeans.addAll(eventListResponse.getEventsList());
                        if (isPageUpdate.equals("0")) {
                            setVenueListAdapter();
                        } else {
                            adapterHomeList.notifyDataSetChanged();
                        }

                    } else {
                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, eventListResponse.getMessage());
                        errorDialog.show();
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, "Failure: " + e.getMessage());
            }
        }

    }

    @Override
    public void onApiFailure(String message) {
        progress.hide();
//        if (!message.equals("") && message != null) {
////            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, message);
////            errorDialog.show();
//        }

//        new BaseClass(getActivity()).showToast(message);
    }

    /* @Override
     public void lastIndex(int pos) {
         page++;
         if (page < reachMax)
             callHomeEventListApi(page, user_id);
//     }*/
    private void callAdapter(int i) {
//        SubCategoryItemAdapter subCategoryItemAdapter = new SubCategoryItemAdapter();
//        rv_pref.setAdapter(subCategoryItemAdapter);
    }


    private void setAdapter(int i) {
        if (i == 0) {
            SubCategoryItemAdapter subCategoryItemAdapter = new SubCategoryItemAdapter();
            rv_pref.setAdapter(subCategoryItemAdapter);
        } else {
            AdapterHome2Pref subCategoryItemAdapter = new AdapterHome2Pref();
            rv_pref.setAdapter(subCategoryItemAdapter);
        }
    }

    class SubCategoryItemAdapter extends RecyclerView.Adapter<SubCategoryItemAdapter.MyViewHolder> {

        @Override
        public SubCategoryItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.subcategory_item_layout, parent, false);

            return new SubCategoryItemAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final SubCategoryItemAdapter.MyViewHolder holder, final int position) {
            holder.textView.setText(eventCategoryModalLists.get(position).getSubCategoryName());


        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return eventCategoryModalLists.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            LinearLayout item_layout;

            public MyViewHolder(View view) {
                super(view);
                textView = view.findViewById(R.id.sub_category_name);
                item_layout = view.findViewById(R.id.item_layout);
            }
        }
    }

    class AdapterHome2Pref extends RecyclerView.Adapter<AdapterHome2Pref.MyViewHolder> {


        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView tv;

            public MyViewHolder(View view) {
                super(view);

                tv = view.findViewById(R.id.sub_category_name);


            }
        }

        @Override
        public AdapterHome2Pref.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.subcategory_item_layout, parent, false);

            return new AdapterHome2Pref.MyViewHolder(itemView);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onBindViewHolder(final AdapterHome2Pref.MyViewHolder holder, final int position) {
            holder.tv.setText(preferencesBeans.get(position).getSubCategoryName());

        }

        @Override
        public int getItemCount() {
            return preferencesBeans.size();
        }

    }

    class PagingEventAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            isDataLoading = true;
            performPaging(pagenum);
            return null;
        }
    }

    class AdapterHomeList extends RecyclerView.Adapter<AdapterHomeList.MyViewHolder> {


        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView iv, img_progress, greenDot, butterfly;
            TextView txtTitle, txtReview, date;
            RatingBar ratingBar;

            public MyViewHolder(View view) {
                super(view);
                img_progress = view.findViewById(R.id.item_home_list_progress);
                butterfly = view.findViewById(R.id.butterfly);
                greenDot = view.findViewById(R.id.greenDot);
                iv = view.findViewById(R.id.home_venue_image);
                txtTitle = view.findViewById(R.id.venue_city_name);
                date = view.findViewById(R.id.date);
                txtReview = view.findViewById(R.id.venue_review_count);
                ratingBar = view.findViewById(R.id.venue_rating);
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


            if (eventsListBeans.size() != 0) {
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
                holder.date.setText(eventsListBeans.get(position).getStartDate());
                if (eventsListBeans.get(position).getReviewCount() != null && !eventsListBeans.get(position).getReviewCount().equals("0"))
                    holder.txtReview.setText(eventsListBeans.get(position).getReviewCount() + "Reviews");
                else holder.txtReview.setText("");
                holder.txtTitle.setText(eventsListBeans.get(position).getName());
                if (eventsListBeans.get(position).getAvgRating() != null && !eventsListBeans.get(position).getAvgRating().equals("0"))
                    holder.ratingBar.setRating(Float.parseFloat(eventsListBeans.get(position).getAvgRating()));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EventDetailsFragment fragment = new EventDetailsFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("event_id", eventsListBeans.get(position).getEventId());
                        bundle.putString("type", "event");
                        fragment.setArguments(bundle);
                        ((HomeActivity) getActivity()).replaceEventDetailFragment(fragment);
                    }
                });
            }


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

        categoryName.setText(getArguments().getString("categoryName") + "+" + placename);
        lati = String.valueOf(place.getLatLng().latitude);
        longi = String.valueOf(place.getLatLng().longitude);

        callHomeEventListApi(pagenum, user_id);
//                textView.setText(stBuilder.toString());
    }

}
