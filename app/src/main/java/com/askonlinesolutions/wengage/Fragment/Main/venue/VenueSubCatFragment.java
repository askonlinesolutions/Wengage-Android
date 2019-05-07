package com.askonlinesolutions.wengage.Fragment.Main.venue;


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
import com.askonlinesolutions.wengage.Adapter.AdapterHome2Pref;
import com.askonlinesolutions.wengage.Adapter.AdapterHomeList;
import com.askonlinesolutions.wengage.Adapter.AdapterHomeViewPager2;
import com.askonlinesolutions.wengage.Adapter.AdapterTopProfiles;
import com.askonlinesolutions.wengage.Adapter.SubCategoryAdapter;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventListResponse;
import com.askonlinesolutions.wengage.Fragment.Main.vo.VeneuHomeListResponse;
import com.askonlinesolutions.wengage.Fragment.RestaurantVenueDetails;
import com.askonlinesolutions.wengage.Model.CategoryList;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
import com.askonlinesolutions.wengage.Model.Response.SubCategoryResponse;
import com.askonlinesolutions.wengage.Model.VenueSubCategoryListBean;
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
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.gson.Gson;
import com.mukesh.tinydb.TinyDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class VenueSubCatFragment extends Fragment implements View.OnClickListener, AdapterHomeViewPager2
        .Interface_Home_2, AdapterHome2Pref.Interface_Home_2_Pref, CompoundButton.OnCheckedChangeListener,
        SubCategoryAdapter.SubCategoryItemClickListner, OnResponseInterface, AdapterHomeList.HomeVenuListInterface {

    public VenueSubCatFragment() {
        // Required empty public constructor
    }


    String cat_name;
    String selected_pref = "French";
    private Progress progress;
    public static final int PLACE_PICKER_VENUE_SUB_REQUEST = 1005;
    private int category_id, sub_cat;
    private List<UserAllListResposne.UsersListBean.InfluencerBean> influencerList;
    private List<UserAllListResposne.UsersListBean.OnlineUsersBean> onlineUserList;
    private List<VeneuHomeListResponse.VenuesHomeList> venuesHomeLists = new ArrayList<>();
    private RecyclerView rv_top_profiles, rv_list, subCatecory_recycler, subCatVenueListRecyclerView, preference_recycler;
    private ViewPager viewPager;
    //    private TextView tv_pref_1, tv_pref_2, tv_pref_3, tv_pref_4, tv_pref_5, tv_pref_6, tv_pref_7, tv_pref_8;
    private Gson gson = new Gson();
    private TextView tv_title, tv_message;
    private LinearLayout iv_arrow;
    private Dialog errorDialog;
    private ImageView iv;
    private int user_id;
    private TinyDB tinyDB;
    private String str;
    private NestedScrollView nestedScrollView;
    private LoginResponse loginResponse;
    private double lat, lang;
    private List<SubCategoryResponse.VenuePreferencesBean> preferencesBeans;
    private ImageView iv_blog_1, iv_blog_2;
    private TextView tv_blog_1_text_1, tv_blog_1_text_2, tv_blog_2_text_1, tv_blog_2_text_2, tv_blog_2_text_3;
    private RelativeLayout bottom_layout_venues, bottom_layout_events;
    private DatabaseHelper db;
    private List<CategoryList> mainCategoryModalList = new ArrayList<>();
    List<VenueSubCategoryListBean> venueModalList = new ArrayList<>();
    List<VenueSubCategoryListBean> venueModalLists = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager1;
    int pagenum = 1;
    private boolean isDataLoading;
    private boolean userScrolled = true;
    private String lati, longi;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean visible = false;
    private String isPageUpdate, fromDashBoard;
    private AdapterHomeList adapterHomeList;
    private RecyclerView rv_pref;
    private ArrayList<String> arr_pref = new ArrayList<>();
    private ArrayList<String> arr_pref_clicked_positon = new ArrayList<>();
    private LinearLayout layout_top_switch, remove;
    private SwitchCompat sw;
    private TextView tv_pref;
    private TextView categoryName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home2, container, false);
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        tinyDB = new TinyDB(getContext());
        user_id = tinyDB.getInt(Constants.USER_ID);
        lati = String.valueOf(tinyDB.getDouble(Constants.LATITUDE, 0.0));
        longi = String.valueOf(tinyDB.getDouble(Constants.LONGITUDE, 0.0));
        loginResponse = new LoginResponse();
        loginResponse = gson.fromJson(tinyDB.getString("login_data"), LoginResponse.class);
        getMyInternt();
        db = DatabaseHelper.getInstance(getActivity());
        mainCategoryModalList = db.getCategoryList();

        venueModalList = db.getVenueList(String.valueOf(category_id));
        init(view);
        if (Utility.isNetworkConnected(getActivity())) {
            callCatByUser(category_id);
            callSubCatVenueListApi();
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

        return view;
    }

    private void getMyInternt() {

        if (getArguments() != null) {
            cat_name = getArguments().getString("category");
            category_id = getArguments().getInt("categoryId");
            fromDashBoard = getArguments().getString("FromDashBoard");

            callSubCatApi();
        }
    }


    private void init(View view) {
        nestedScrollView = view.findViewById(R.id.nestedScrollView);
        tv_pref = view.findViewById(R.id.home_2_pref_text);
        categoryName = view.findViewById(R.id.categoryName);
        remove = view.findViewById(R.id.remove);
        preference_recycler = view.findViewById(R.id.preference_recycler);
        preference_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        if (venueModalList.size() > 0) {
//
            for (int i = 0; i < venueModalList.size(); i++) {
                if (venueModalList.get(i).getIsSelected() == 1) {
                    venueModalLists.add(venueModalList.get(i));
                }
            }
            setAdapter(0);
        }


        str = tinyDB.getString("bottom_click");

        user_id = tinyDB.getInt(Constants.USER_ID);

        bottom_layout_venues = view.findViewById(R.id.home_2_bottom_layout_venues);
        bottom_layout_events = view.findViewById(R.id.home_2_bottom_layout_events);
        tv_message = view.findViewById(R.id.not_available_text);
        subCatVenueListRecyclerView = view.findViewById(R.id.fragment_home2_recycler_venue);
        iv_blog_1 = view.findViewById(R.id.home_2_blog_1_image);
        iv_blog_2 = view.findViewById(R.id.home_2_blog_2_image);
        tv_blog_1_text_1 = view.findViewById(R.id.home_2_blog_1_text_1);
        tv_blog_1_text_2 = view.findViewById(R.id.home_2_blog_1_text_2);
        tv_blog_2_text_1 = view.findViewById(R.id.home_2_blog_2_text_1);
        tv_blog_2_text_2 = view.findViewById(R.id.home_2_blog_2_text_2);
        tv_blog_2_text_3 = view.findViewById(R.id.home_2_blog_2_text_3);

        subCatecory_recycler = view.findViewById(R.id.venue_subcategory_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        subCatecory_recycler.setLayoutManager(linearLayoutManager);
        subCatecory_recycler.setNestedScrollingEnabled(false);

        linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        subCatVenueListRecyclerView.setLayoutManager(linearLayoutManager1);
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
        sw = view.findViewById(R.id.home_2_top_profile_switch);
        sw.setChecked(true);
        sw.setOnCheckedChangeListener(this);

        iv = view.findViewById(R.id.home2_image_arrow);
        tv_title = view.findViewById(R.id.home2_title);
        iv_arrow = view.findViewById(R.id.home2_arrow);
        iv_arrow.setOnClickListener(this);

        tv_title.setText(getArguments().getString("categoryName"));
        tv_title.setOnClickListener(this);
        rv_top_profiles = view.findViewById(R.id.recycler_top_profiles_2);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_top_profiles.setLayoutManager(horizontalLayoutManagaer);

        rv_pref = view.findViewById(R.id.home_2_pref_recycler);
        LinearLayoutManager horizontalLayoutManagaers
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_pref.setLayoutManager(horizontalLayoutManagaers);
        rv_pref.setNestedScrollingEnabled(false);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                View view = (View) v.getChildAt(v.getChildCount() - 1);
                int diff = (view.getBottom() - (v.getHeight() + v.getScrollY()));
                if (diff == 0) {
                    visibleItemCount = linearLayoutManager1.getChildCount();
                    totalItemCount = linearLayoutManager1.getItemCount();
                    pastVisiblesItems = linearLayoutManager1
                            .findFirstVisibleItemPosition();
                    if (scrollY > oldScrollY) {
                        userScrolled = true;
                        if (userScrolled
                                && (visibleItemCount + pastVisiblesItems) == totalItemCount) {
//                        if (!visible) {
                            userScrolled = false;

//                            Toast.makeText(getActivity(), pagenum+"", Toast.LENGTH_SHORT).show();
                            isPageUpdate = "1";
                            if (!isDataLoading) {
                                progress.show();
                                new PagingAsync().execute();
                            }

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
                    getActivity().startActivityForResult(intent, PLACE_PICKER_VENUE_SUB_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        if (fromDashBoard.equals("1")) {
            remove.setVisibility(View.GONE);
        } else {
            remove.setVisibility(View.VISIBLE);
        }
    }

    private void performPaging(int pagenum) {
        if (Utility.isNetworkConnected(getActivity())) {
            if (lati.equals("0.0") && longi.equals("0.0")) {
                Call<VeneuHomeListResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatVenueListWithoutLatLong(pagenum, user_id, category_id, "venue");
                Log.e("MyUrl", call.request().url().toString());
                new ResponseListner(this).getResponse(getActivity(), call);
            } else {
                Call<VeneuHomeListResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatVenueList(pagenum, user_id, category_id, "venue", lati, longi);
                Log.e("MyUrl", call.request().url().toString());
                new ResponseListner(this).getResponse(getActivity(), call);
            }
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }


    }

    private void setAdapter(int i) {
        if (i == 0) {
            SubCategoryItemAdapter subCategoryItemAdapter = new SubCategoryItemAdapter();
            preference_recycler.setAdapter(subCategoryItemAdapter);
        } else {
            AdapterHome2Pref subCategoryItemAdapter = new AdapterHome2Pref();
            preference_recycler.setAdapter(subCategoryItemAdapter);
        }
    }


    private void setVenueListAdapter() {

        adapterHomeList = new AdapterHomeList();
        subCatVenueListRecyclerView.setAdapter(adapterHomeList);
    }

    private List<VenueSubCategoryListBean> subCategoryListBeanList = new ArrayList<>();

    private void setSubCatAdapter(SubCategoryResponse subCategoryResponse) {
        if (subCategoryListBeanList != null) {
            subCategoryListBeanList.clear();
        }
        subCategoryListBeanList.addAll(subCategoryResponse.getVenueSubCategoryList());
        SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(getActivity(),
                subCategoryListBeanList, this, category_id);
        subCatecory_recycler.setAdapter(subCategoryAdapter);
    }

    private void callCatByUser(int catId) {
        if (Utility.isNetworkConnected(getActivity())) {
            Call<UserAllListResposne> call = APIClient.getInstance().getApiInterface()
                    .getUsersByCategory(user_id, "venue", catId);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("URL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
//        progressBar.setVisibility(View.VISIBLE);

    }

    private String TAG = VenueSubCatFragment.class.getName();

    private void callSubCatApi() {
        if (Utility.isNetworkConnected(getActivity())) {
            int user_id = loginResponse.getProfileInfo().getUserId();
//        progressBar.setVisibility(View.VISIBLE);
            Call<SubCategoryResponse> call = APIClient.getInstance().getApiInterface()
                    .getAllSubCategories(category_id, user_id, "venue", String.valueOf(tinyDB.getDouble(Constants.LATITUDE, 28.5355))
                            , String.valueOf(tinyDB.getDouble(Constants.LONGITUDE, 77.3910))
                    );
            Log.e("URL", call.request().url().toString());
            new ResponseListner(this).getResponse(getActivity(), call);
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }

    private void callSubCatVenueListApi() {
        if (Utility.isNetworkConnected(getActivity())) {
//        progress.show();
            isPageUpdate = "0";
//        progressBar.setVisibility(View.VISIBLE);
            if (lati.equals("0.0") && longi.equals("0.0")) {
                Call<VeneuHomeListResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatVenueListWithoutLatLong(pagenum, user_id, category_id, "venue");
                Log.e("MyUrl", call.request().url().toString());
                new ResponseListner(this).getResponse(getActivity(), call);
            } else {
                Call<VeneuHomeListResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatVenueList(pagenum, user_id, category_id, "venue", lati, longi);
                Log.e("MyUrl", call.request().url().toString());
                new ResponseListner(this).getResponse(getActivity(), call);
            }
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }

    private AdapterHome2Pref adapter;
//    private int clicked_position = 99;

    private void setPrefrences(List<SubCategoryResponse.VenuePreferencesBean> preferences) {
        if (preferences != null && preferences.size() > 0) {
            LinearLayoutManager horizontalLayoutManagaers
                    = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            rv_pref.setLayoutManager(horizontalLayoutManagaers);
            adapter = new AdapterHome2Pref();
            rv_pref.setAdapter(adapter);
        } else {
            tv_pref.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void viewDetails(int pos) {
    }

    @Override
    public void onResume() {
        super.onResume();

//        TextView tv = (TextView) view.findViewById(id);
//        tv.setBackground(getResources().getDrawable(R.drawable.shape_round_accent));
//        tv.setTextColor(getResources().getColor(R.color.white));
    }

    private ArrayList<String> cat_names = new ArrayList<>();
    private ArrayList<Integer> cat_images = new ArrayList<>();
    private ArrayList<String> view_pager_page_item = new ArrayList<>();


    private void setMyViewPager() {

        AdapterHomeViewPager2 adapter = new AdapterHomeViewPager2(getContext(), getFragmentManager(),
                cat_images, cat_names, cat_name, selected_pref,
                view_pager_page_item, this);
        viewPager = (ViewPager) getView().findViewById(R.id.home2_viewPager);
        viewPager.setAdapter(adapter);

        if (cat_names.size() == 0) {
            viewPager.setVisibility(View.GONE);
        }

    }

    private boolean arrow_status = true;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.home2_arrow:
                if (arrow_status) {
                    iv.setImageResource(R.drawable.arrow_up);
                    if (onlineUserList != null && onlineUserList.size() != 0) {
                        rv_top_profiles.setVisibility(View.VISIBLE);
                    } else {
                        tv_message.setVisibility(View.GONE);
                    }
                    setTopAdapter("1");
                    layout_top_switch.setVisibility(View.VISIBLE);
                    tv_title.setVisibility(View.GONE);
                    arrow_status = false;
                } else {
                    rv_top_profiles.setVisibility(View.GONE);
                    layout_top_switch.setVisibility(View.GONE);
                    iv.setImageResource(R.drawable.arrow_down);
                    tv_title.setVisibility(View.VISIBLE);
                    arrow_status = true;
                }
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

        if (savedInstanceState != null) {
        }
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

//        Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
      /*  if(arr_pref_clicked_positon.get(position).equals("yes")){
            arr_pref_clicked_positon.set(position, "no"); //this.clicked_position = position;
        } else
            arr_pref_clicked_positon.set(position, "yes"); //this.clicked_position = position;
        adapter.notifyDataSetChanged();*/

      /*  final RestaurantList fragment = new RestaurantList();
        Bundle bundle = new Bundle();
        bundle.putString("main_category", cat_name);
        bundle.putString("category", arr_pref.get(position));
        bundle.putString("selected_pref", selected_pref);
        fragment.setArguments(bundle);
        new BaseClass(getContext()).callFragment(fragment, fragment.getClass().getName(), getFragmentManager());
*/
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
        final RestaurantList fragment = new RestaurantList();
        Bundle bundle = new Bundle();
        bundle.putInt("categoryId", subCategoryListBeanList.get(pos).getCategoryId());
        bundle.putInt("subCategoryId", subCategoryListBeanList.get(pos).getSubCategoryId());
        bundle.putString("subCatName", subCategoryListBeanList.get(pos).getSubCategoryName());
        bundle.putString("catName", subCategoryListBeanList.get(pos).getCategoryName());
        bundle.putString("type", "venue");
        fragment.setArguments(bundle);
        ((HomeActivity) getActivity()).replaceRestaurantListFragment(fragment);

    }


    private void setTopAdapter(String s) {
        if (onlineUserList != null && influencerList != null) {
            AdapterTopProfiles adapter = new AdapterTopProfiles(getContext(), onlineUserList,
                    influencerList, s);
            rv_top_profiles.setAdapter(adapter);
        }
    }

    private List<EventListResponse.EventsListBean> eventsListBeans = new ArrayList<>();

    @Override
    public void onApiResponse(Object response) {
        progress.hide();
//        progressBar.setVisibility(View.GONE);
//        if (page == 1) {
//            venuesHomeLists.clear();
//        }
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


                        preferencesBeans = new ArrayList<>();
                        setSubCatAdapter(subCategoryResponse);
                        for (int i = 0; i < subCategoryResponse.getVenuePreferences().size(); i++) {
                            if (preferencesBeans.size() < 8) {
                                preferencesBeans.add(subCategoryResponse.getVenuePreferences().get(i));
                            }
                        }
                        if (preferencesBeans.size() > 0) {
                            if (venueModalList.size() < 1) {
                                setAdapter(1);
                            }

                        }

//                        setPrefrences(preferencesBeans);
                    } else {

                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, subCategoryResponse.getMessage());
                        errorDialog.show();
                    }
                } else if (response instanceof VeneuHomeListResponse) {
                    pagenum++;
                    isDataLoading = false;
                    VeneuHomeListResponse veneuHomeListResponse = (VeneuHomeListResponse) response;
                    if (veneuHomeListResponse.getStatus() == 1) {
                        if (veneuHomeListResponse.getVenuesList() != null) {
                            venuesHomeLists.addAll(veneuHomeListResponse.getVenuesList());
                            if (isPageUpdate.equals("0")) {
                                setVenueListAdapter();
                            } else {
                                adapterHomeList.notifyDataSetChanged();
                            }

                        }
                    } else {

                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, veneuHomeListResponse.getMessage());
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
    }
//
//    @Override
//    public void lastIndex(int pos) {
//        page++;
//        if (page < reachMax)
//            callSubCatVenueListApi();
//    }

    class SubCategoryItemAdapter extends RecyclerView.Adapter<SubCategoryItemAdapter.MyViewHolder> {

        @Override
        public SubCategoryItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.subcategory_item_layout, parent, false);

            return new SubCategoryItemAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final SubCategoryItemAdapter.MyViewHolder holder, final int position) {
            if (venueModalLists.get(position).getIsSelected() == 1) {
                holder.textView.setText(venueModalLists.get(position).getSubCategoryName());
            }


        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return venueModalLists.size();
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

    class AdapterHomeList extends RecyclerView.Adapter<AdapterHomeList.MyViewHolder> {


        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView iv, img_progress, greenDot, butterfly;
            TextView txtTitle, txtReview;
            RatingBar ratingBar;
            private RelativeLayout venueLayout;

            public MyViewHolder(View view) {
                super(view);
                butterfly = view.findViewById(R.id.butterfly);
                img_progress = view.findViewById(R.id.item_home_list_progress);
                greenDot = view.findViewById(R.id.greenDot);
                iv = view.findViewById(R.id.home_venue_image);
                venueLayout = view.findViewById(R.id.venueLayout);
                txtTitle = view.findViewById(R.id.venue_city_name);
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


            if (!venuesHomeLists.get(position).getImageURL().equalsIgnoreCase("")) {
                Glide.with(getActivity())
                        .load(venuesHomeLists.get(position).getImageURL())
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
            if (venuesHomeLists.get(position).getIsInterested() == 1) {
                holder.greenDot.setVisibility(View.VISIBLE);
            } else {
                holder.greenDot.setVisibility(View.GONE);
            }
            if (venuesHomeLists.get(position).getRecommendByWengage() == 1) {
                holder.butterfly.setVisibility(View.VISIBLE);
            } else {
                holder.butterfly.setVisibility(View.GONE);
            }
            if (venuesHomeLists.get(position).getReviewCount() != null && !venuesHomeLists.get(position).getReviewCount().equals("0"))
                holder.txtReview.setText(venuesHomeLists.get(position).getReviewCount() + "Reviews");
            else holder.txtReview.setText("");
            holder.txtTitle.setText(venuesHomeLists.get(position).getName());
            if (venuesHomeLists.get(position).getAvgRating() != null && !venuesHomeLists.get(position).getAvgRating().equals("0"))
                holder.ratingBar.setRating(Float.parseFloat(venuesHomeLists.get(position).getAvgRating()));
            holder.venueLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    RestaurantVenueDetails fragment = new RestaurantVenueDetails();
                    Bundle bundle = new Bundle();
                    bundle.putString("venue_id", venuesHomeLists.get(position).getVenueId());
                    bundle.putString("type", tv_title.getText().toString());
                    fragment.setArguments(bundle);
                    ((HomeActivity) getActivity()).replaceRestaurantVenueDetails(fragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return venuesHomeLists.size();
        }
    }


    class PagingAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            isDataLoading = true;
            performPaging(pagenum);
            return null;
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
        callSubCatVenueListApi();
//                textView.setText(stBuilder.toString());
    }

}
