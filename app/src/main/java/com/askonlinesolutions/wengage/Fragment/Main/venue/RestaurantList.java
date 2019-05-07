package com.askonlinesolutions.wengage.Fragment.Main.venue;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Adapter.AdapterCategoryListBottom1;
import com.askonlinesolutions.wengage.Adapter.AdapterCategoryListBottom1Events;
import com.askonlinesolutions.wengage.Adapter.AdapterCategoryListTop;
import com.askonlinesolutions.wengage.Adapter.AdapterTopProfiles;
import com.askonlinesolutions.wengage.Fragment.Main.vo.VeneuHomeListResponse;
import com.askonlinesolutions.wengage.Fragment.RestaurantVenueDetails;
import com.askonlinesolutions.wengage.Fragment.Sub.Filter;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Model.TagModal;
import com.askonlinesolutions.wengage.Networks.NetworkConstants;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.ApiResponse;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Progress;
import com.askonlinesolutions.wengage.utils.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mukesh.tinydb.TinyDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantList extends Fragment implements View.OnClickListener,
        AdapterCategoryListTop.InterfaceCategoryListTop, CompoundButton.OnCheckedChangeListener,
        AdapterCategoryListBottom1.Interface_RestaurantList1, AdapterCategoryListBottom1Events
                .Interface_RestaurantListEvents, OnResponseInterface {


    AdapterTopProfiles adapter_top_profiles;
    boolean arrow_status = true;
    private String cat_name, selected_pref, main_cat_name, subCatName, type;
    private int categoryId, subCategoryId, pagenum = 1, user_id;
    private TinyDB tinyDB;
    private RecyclerView rv_top, rv_bottom, rv_top_profiles, rv_list_events;
    private TextView tv_title, tv_language;
    private LinearLayout iv_arrow;
    private ArrayList<String> cat_top = new ArrayList<>();
    private ImageView iv, filter_icon, iv_table;
    private ArrayList<String> bottom_list_bookmarks_final_venues = new ArrayList<>();
    private ArrayList<String> bottom_list_interested_final_venues = new ArrayList<>();
    private LinearLayout layout_top_switch;
    private SwitchCompat sw;
    private Progress progress;
    private RecyclerView tagRecyclerView;
    private TagModal tagModal;
    private TagAdapter tagAdapter;
    private Dialog errorDialog;
    private String bottom_type;
    //    private LinearLayout layput_top_all;
//    private TextView tv_all;
    private List<VeneuHomeListResponse.VenuesHomeList> venuesList = new ArrayList<>();
    private AdapterCategoryListBottom1 adapte1;
    private ArrayList<String> arr_pref_click = new ArrayList<>();
    private AdapterCategoryListTop adapter;
    private LinearLayout layout_filter;
    private UserAllListResposne allUsersResponse;
    //    private TextView allTv, todayTv, thisWeakTv, thisMonthTv;
    private List<UserAllListResposne.UsersListBean.OnlineUsersBean> onlineUsers/* = new ArrayList<>()*/;
    private List<UserAllListResposne.UsersListBean.InfluencerBean> influenceUsers/* = new ArrayList<>()*/;
    private String TAG = RestaurantList.class.getName();
    private AdapterTopProfiles adapterTopProfiles;

    private boolean userScrolled = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean visible = false;
    LinearLayoutManager layoutManagaer;
    private String isPageUpdate;
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
        return inflater.inflate(R.layout.fragment_restaurant_venue_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getIntentData();
        init();

    }

    private void getIntentData() {
        if (getArguments() != null) {
            categoryId = getArguments().getInt("categoryId");
            subCategoryId = getArguments().getInt("subCategoryId");
            cat_name = getArguments().getString("catName");
            main_cat_name = getArguments().getString("main_category");
            selected_pref = getArguments().getString("selected_pref");
            subCatName = getArguments().getString("subCatName");
            type = getArguments().getString("type");
        }


    }


    private void init() {
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        tagRecyclerView = getView().findViewById(R.id.tagRecyclerView);
        layout_filter = getView().findViewById(R.id.restaurant_list_filter_layout);
        tinyDB = new TinyDB(getContext());

        lati = String.valueOf(tinyDB.getDouble(Constants.LATITUDE, 0.0));
        longi = String.valueOf(tinyDB.getDouble(Constants.LONGITUDE, 0.0));
        user_id = tinyDB.getInt(Constants.USER_ID);
        bottom_type = type;
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

        rv_list_events = getView().findViewById(R.id.recycler_restaurant_list_events);
        LinearLayoutManager lay
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_list_events.setLayoutManager(lay);

        getTagsAPI();
        callApi(pagenum);
        callSubCatByUser(categoryId, subCategoryId);

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
                            progress.show();
                            userScrolled = false;
                            pagenum++;
                            isPageUpdate = "1";
                            performPaging(pagenum);
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
                        userScrolled = false;
                        pagenum++;
                        isPageUpdate = "1";
                        performPaging(pagenum);
                    }
                }
            }
        });
        //  setMyAdapter();
    }

    private void performPaging(int pagenum) {
        if (Utility.isNetworkConnected(getActivity())){
            lati = String.valueOf(tinyDB.getDouble(Constants.LATITUDE, 0.0));
            longi = String.valueOf(tinyDB.getDouble(Constants.LONGITUDE, 0.0));
            if (lati.equals("0.0") && longi.equals("0.0")) {
                Call<VeneuHomeListResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatSubVenueListWithoutLatLong(pagenum, user_id, categoryId, subCategoryId);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.e("URL", call.request().url().toString());
            } else {

                Call<VeneuHomeListResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatSubVenueList(pagenum, user_id, categoryId, subCategoryId, lati, longi);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.e("URL", call.request().url().toString());
            }
        }else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }


    }

    private void getTagsAPI() {
        if (Utility.isNetworkConnected(getActivity())) {
            progress.show();
            Ion.with(this)
                    .load("GET", NetworkConstants.GET_TAGS + categoryId)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            progress.dismiss();
                            if (result != null) {
                                tagModal = new Gson().fromJson(result, TagModal.class);
                                if (tagModal.getStatus() == 1) {
                                    if (tagModal.getTags().size() > 0) {
                                        for (int i = 0; i < tagModal.getTags().size(); i++) {
                                            tagModal.getTags().get(i).setSelected(false);
                                      /*  if (i == 0) {
                                            tagModal.getTags().get(i).setSelected(true);
                                        } else {
                                            tagModal.getTags().get(i).setSelected(false);
                                        }*/
                                        }
                                        LinearLayoutManager horizontalLayoutManagaer
                                                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                                        tagRecyclerView.setLayoutManager(horizontalLayoutManagaer);

                                        tagAdapter = new TagAdapter();
                                        tagRecyclerView.setAdapter(tagAdapter);
                                    }
                                }
                            }


                        }
                    });
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }

    private void callSubCatByUser(int categoryId, int subCategoryId) {
        if (Utility.isNetworkConnected(getActivity())){
            progress.show();
            lati = String.valueOf(tinyDB.getDouble(Constants.LATITUDE, 0.0));
            longi = String.valueOf(tinyDB.getDouble(Constants.LONGITUDE, 0.0));
            if (lati.equals("0.0") && longi.equals("0.0")) {
                Call<UserAllListResposne> call = APIClient.getInstance().getApiInterface()
                        .getUsersBySubCategoryWithoutLatLong(user_id, categoryId, subCategoryId);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.e("URL", call.request().url().toString());
            } else {
                Call<UserAllListResposne> call = APIClient.getInstance().getApiInterface()
                        .getUsersBySubCategory(user_id, categoryId, subCategoryId, lati, longi);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.e("URL", call.request().url().toString());
            }
        }else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }


    }

    private void getVenueListData(VeneuHomeListResponse venueListResponse) {
        adapte1 = new AdapterCategoryListBottom1(getContext(), venuesList, "venues", this);
        rv_bottom.setAdapter(adapte1);
        rv_bottom.setVisibility(View.VISIBLE);
    }

    private void callApi(int pagenum) {
        if (Utility.isNetworkConnected(getActivity())){
            isPageUpdate = "0";
            progress.show();

            lati = String.valueOf(tinyDB.getDouble(Constants.LATITUDE, 0.0));
            longi = String.valueOf(tinyDB.getDouble(Constants.LONGITUDE, 0.0));
            if (lati.equals("0.0") && longi.equals("0.0")) {
                Call<VeneuHomeListResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatSubVenueListWithoutLatLong(pagenum, user_id, categoryId, subCategoryId);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.e("URL", call.request().url().toString());
            } else {
                Call<VeneuHomeListResponse> call = APIClient.getInstance().getApiInterface()
                        .getSubCatSubVenueList(pagenum, user_id, categoryId, subCategoryId, lati, longi);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.e("URL", call.request().url().toString());
            }
        }else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }


    }

    @Override
    public void viewItemDetails(int pos) {
        RestaurantVenueDetails fragment = new RestaurantVenueDetails();
        Bundle bundle = new Bundle();
        bundle.putString("venue_id", venuesList.get(pos).getVenueId());
        bundle.putString("type", tv_title.getText().toString());
        fragment.setArguments(bundle);
        ((HomeActivity) getActivity()).replaceRestaurantVenueDetails(fragment);
        /*getFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fade_out,
                0,
                R.animator.fragment_slide_right_exit)
                .replace(R.id.fragment_venue_home_fragment_container, fragment)
                .addToBackStack(RestaurantDetails.class.getName()).commit();*/
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

                break;
            default:
                break;
        }
    }

    @Override
    public void click_CategoryListTop(int position) {

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
        if (!_clicked) {
//            tv_all.setTextColor(getResources().getColor(R.color.white));
//            layput_top_all.setSelected(true);
        } else {
//            tv_all.setTextColor(getResources().getColor(R.color.colorAccent));
//            layput_top_all.setSelected(false);
        }

//        tv_all.setTextColor(getResources().getColor(R.color.colorAccent));
//        layput_top_all.setSelected(false);
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

        if (venuesList.get(position).getVenueId() != null && venuesList.get(position).getVenueId().length() > 0) {
            String venue_id = venuesList.get(position).getVenueId();
            if (type.equals("bookmarks")) {
                venuesList.get(position).setIsBookmarked(status);
                setBookmark(venue_id, status);
            } else if (type.equals("interested")) {
                venuesList.get(position).setIsInterested(status);
                setinterest(venue_id, status);
            } else {

            }
        }
        if (adapte1 != null)
            adapte1.notifyDataSetChanged();
    }

    private void setBookmark(String venue_id, int status) {
        if (Utility.isNetworkConnected(getActivity())){
            progress.show();
            Call<ApiResponse> call = APIClient.getInstance().getApiInterface()
                    .markVenueBookmarked(tinyDB.getInt(Constants.USER_ID), venue_id, String.valueOf(status));
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("URL", call.request().url().toString());
        }else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }

    private void setinterest(String venue_id, int status) {
        if (Utility.isNetworkConnected(getActivity())){

            Call<ApiResponse> call = APIClient
                    .getInstance()
                    .getApiInterface()
                    .markVenueInterest(tinyDB.getInt(Constants.USER_ID), venue_id, String.valueOf(status));

            progress.show();
            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                    progress.hide();

                    if (response != null) {
                        if (response.body().getStatus() == 1) {
                            new BaseClass(getActivity()).showToast(response.body().message);
                        } else {
                            new BaseClass(getActivity()).showToast(response.body().message);
                        }
                    } else {
                        progress.hide();
                        new BaseClass(getActivity()).showToast("Data not found");
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {

                }
            });
        }else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    @Override
    public void click_interface_restaurant_list_events(int position, String type) {
        if (type.equals("bookmarks")) {

            if (bottom_list_bookmarks_final_venues.get(position).equals("0")) {
                bottom_list_bookmarks_final_venues.set(position, "1");
            } else {
                bottom_list_bookmarks_final_venues.set(position, "0");
            }
            adapte1.notifyDataSetChanged();
        } else {
            if (bottom_list_interested_final_venues.get(position).equals("0")) {
                bottom_list_interested_final_venues.set(position, "1");
            } else {
                bottom_list_interested_final_venues.set(position, "0");
            }
            adapte1.notifyDataSetChanged();
        }
    }

    @Override
    public void onApiResponse(Object response) {
        progress.hide();
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
                } else if (response instanceof VeneuHomeListResponse) {
                    VeneuHomeListResponse venueListResponse = (VeneuHomeListResponse) response;
                    if (venueListResponse.getStatus() == 1) {
                        venuesList.addAll(venueListResponse.getVenuesList());
                        if (isPageUpdate.equals("0")) {
                            getVenueListData(venueListResponse);
                        } else {
                            adapte1.notifyDataSetChanged();
                        }

                    } else {
                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, allUsersResponse.getMessage());
                        errorDialog.show();
                    }
                } else if (response instanceof ApiResponse) {
                    ApiResponse apiResponse = (ApiResponse) response;
                    if (apiResponse.getStatus() == 1) {
                        new BaseClass(getActivity()).showToast(apiResponse.message);
                    } else {

                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, allUsersResponse.getMessage());
                        errorDialog.show();
                    }
                }

            } catch (Exception e) {
                Log.d(TAG, "onApiResponse: " + e.getMessage());
            }
        }
    }

    @Override
    public void onApiFailure(String message) {
        progress.hide();
       /* if (!message.equals("") && message != null && !message.equals("null")) {
//            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, message);
//            errorDialog.show();
        }*/

    }

    private void setTopAdapter(String s) {
        if (onlineUsers != null && influenceUsers != null) {
            adapterTopProfiles = new AdapterTopProfiles(getContext(), onlineUsers,
                    influenceUsers, s);
            rv_top_profiles.setAdapter(adapterTopProfiles);
        }
    }

    class TagAdapter extends RecyclerView.Adapter<TagAdapter.MyViewHolder> {

        @Override
        public TagAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.tag_adapter_item, parent, false);

            return new TagAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final TagAdapter.MyViewHolder holder, final int position) {
            if (tagModal.getTags().get(position).isSelected()) {
                holder.allTv.setBackground(getResources().getDrawable(R.drawable.select_drawable));
                holder.allTv.setTextColor(getResources().getColor(R.color.white));
            } else {
                holder.allTv.setBackground(getResources().getDrawable(R.drawable.shape_round_category_top));
                holder.allTv.setTextColor(getResources().getColor(R.color.text_color_meg));

            }
            holder.allTv.setText(tagModal.getTags().get(position).getTagName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < tagModal.getTags().size(); i++) {
                        if (tagModal.getTags().get(position).isSelected()) {
                            if (i == position) {
                                tagModal.getTags().get(i).setSelected(true);
                            } else {
                                tagModal.getTags().get(i).setSelected(false);
                            }
                        } else {
                            if (i == position) {
                                tagModal.getTags().get(i).setSelected(true);
                            } else {
                                tagModal.getTags().get(i).setSelected(false);
                            }
                        }
                    }
                    tagAdapter.notifyDataSetChanged();
                    callWithTagIdAPI(pagenum, tagModal.getTags().get(position).getTagId());
                  /*  if (tagModal.getTags().get(position).isSelected()){

                    }
                    for (int i = 1; i <= tagModal.getTags().size(); i++) {
                        if (position == i) {
                            tagModal.getTags().get(i).setSelected(true);
                        } else {
                            tagModal.getTags().get(i).setSelected(false);
                        }
                    }*/
//                    TagAdapter tagAdapter = new TagAdapter();
//                    tagRecyclerView.setAdapter(tagAdapter);

                }
            });
        }


        @Override
        public int getItemCount() {
            return tagModal.getTags().size();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView allTv;

            public MyViewHolder(View view) {
                super(view);
                allTv = view.findViewById(R.id.allTv);
            }
        }
    }

    private void callWithTagIdAPI(int pagenum, int tagId) {
        if (Utility.isNetworkConnected(getActivity())){
            progress.show();
            Call<VeneuHomeListResponse> call = APIClient.getInstance().getApiInterface()
                    .getSubCatSubVenueListWithTag(pagenum, user_id, categoryId, subCategoryId,
                            tinyDB.getDouble(Constants.LATITUDE, 28.5355),
                            tinyDB.getDouble(Constants.LONGITUDE, 77.3910), tagId);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("URL", call.request().url().toString());
        }else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }
}