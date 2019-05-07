package com.askonlinesolutions.wengage.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.ProfilePerResposne;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.dashboard_adapters.DashBoardNewsAdapter;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.dashboard_adapters.DashboardChatsAdapter;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.dashboard_adapters.DashboardInfluencerAdapter;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.dashboard_adapters.DashboardVenueAdapter;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.notificationAdapter.NotificationInviteAdapter;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.notificationAdapter.NotificationNewsAdapter;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.notificationAdapter.NotificationVenueAdapter;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.InfluencerResponse;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.InviteResponse;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.VenueResponse;
import com.askonlinesolutions.wengage.CallBacks.OnItemClickListener;
import com.askonlinesolutions.wengage.Fragment.Main.vo.VeneuHomeListResponse;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
import com.askonlinesolutions.wengage.Model.Response.NotificationModal;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Progress;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.mukesh.tinydb.TinyDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class NotificationFragment extends Fragment implements View.OnClickListener, OnResponseInterface, OnItemClickListener {
    private static final String TAG = NotificationFragment.class.getName();
    private RecyclerView newsRV, inviteRV, chatsRV, venueAsctivityRV, influencerRV;
    private TextView newsTV, inviteTV, chatsTV, venueTV, influencerTV, txtName, txtProfilePer, noNotification;
    private View newsLine, inviteLine, chatLine, venueLine, influencerLine;
    private NotificationNewsAdapter newsAdapter;
    private NotificationInviteAdapter chatsAdapter;
    private NotificationVenueAdapter venueAdapter;
    private DashboardInfluencerAdapter influencerAdapter;
    private LoginResponse loginResponse;
    private Gson gson = new Gson();
    private TinyDB tinyDB;
    private FrameLayout notificationLayout;
    private int user_id;
    private LinearLayout home3_cross;
    private Progress progress;
    private String type = "WengageAdmin";
    //    private int pageNum = 1;
    GridLayoutManager gridLayoutManager;
    private List<NotificationModal.NotificationsBean> newNotificationList = new ArrayList<>();
    private List<InviteResponse.NotificationsEntity> inviteNotificationList = new ArrayList<>();
    private List<VenueResponse.NotificationsEntity> venueNotificationList = new ArrayList<>();
    private List<InfluencerResponse.NotificationsEntity> influenserList = new ArrayList<>();
    int page_number = 1;
    private boolean userScrolled = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean visible = false;
    private String isPageUpdate = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        tinyDB = new TinyDB(getContext());
        user_id = tinyDB.getInt(Constants.USER_ID);
        initUI(view);

        String Type = "news";
        getNotification(page_number, Type);
        return view;
    }

//    private void getNotification(String type) {
//        progress.show();
//        Call<NotificationModal> call = APIClient.getInstance().getApiInterface()
//                .getNotifications(user_id, type, pageNum);
//        new ResponseListner(this).getResponse(getContext(), call);
//        Log.e("URL", call.request().url().toString());
//    }

    private void initUI(View view) {
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        loginResponse = gson.fromJson(tinyDB.getString("login_data"), LoginResponse.class);
        user_id = loginResponse.getProfileInfo().getUserId();
//        user_id=tinyDB.getInt(Constants.USER_ID);
        home3_cross = view.findViewById(R.id.home3_crosss);
        home3_cross.setOnClickListener(this);
        notificationLayout = view.findViewById(R.id.notificationLayout);
        noNotification = view.findViewById(R.id.noNotification);
        txtProfilePer = view.findViewById(R.id.fragment_dash_profile_per);
//        completProgressBar = view.findViewById(R.id.activeProgress);
        newsRV = (RecyclerView) view.findViewById(R.id.news_rv);
        inviteRV = (RecyclerView) view.findViewById(R.id.invites_rv);
        chatsRV = (RecyclerView) view.findViewById(R.id.chats_rv);
        venueAsctivityRV = (RecyclerView) view.findViewById(R.id.venue_activity_rv);
        influencerRV = (RecyclerView) view.findViewById(R.id.influencer_rv);
//        txtName = view.findViewById(R.id.fragment_dashboard_name);
        newsTV = (TextView) view.findViewById(R.id.new_btn_tv);
        inviteTV = (TextView) view.findViewById(R.id.invites_btn_tv);
        chatsTV = (TextView) view.findViewById(R.id.chats_btn_tv);
        venueTV = (TextView) view.findViewById(R.id.venue_btn_tv);
        influencerTV = (TextView) view.findViewById(R.id.influencer_btn_tv);

        newsLine = (View) view.findViewById(R.id.news_line);
        inviteLine = (View) view.findViewById(R.id.invite_line);
        chatLine = (View) view.findViewById(R.id.chat_line);
        venueLine = (View) view.findViewById(R.id.venue_line);
        influencerLine = (View) view.findViewById(R.id.influencer_line);

//        txtName.setText(loginResponse.getProfileInfo().getKnownByName());
//        Glide.with(getActivity()).asGif().load(R.drawable.load).into(imgProgress);
        /*if (loginResponse.getProfileInfo().getInfluencer() == 0) {
            imgProfile.setBackgroundResource(R.drawable.pink_circle);
        } else imgProfile.setBackgroundResource(R.drawable.blue_circle);*/


        setUpNewsRecyclerView();
        setUpInviteRecyclerView();
//        setUpChatsRecyclerView();
        setUpInfluencerRecyclerView();
        setUpVenueRecyclerView();

        newsTV.setOnClickListener(this);
        inviteTV.setOnClickListener(this);
        chatsTV.setOnClickListener(this);
        venueTV.setOnClickListener(this);
        influencerTV.setOnClickListener(this);

        venueId = tinyDB.getString("venue_id");
        if (venueId != "") {
            newsRV.setVisibility(View.GONE);
            inviteRV.setVisibility(View.GONE);
            chatsRV.setVisibility(View.GONE);
            venueAsctivityRV.setVisibility(View.VISIBLE);
            influencerRV.setVisibility(View.GONE);

            newsLine.setVisibility(View.GONE);
            inviteLine.setVisibility(View.GONE);
            chatLine.setVisibility(View.GONE);
            venueLine.setVisibility(View.VISIBLE);
            influencerLine.setVisibility(View.GONE);

            String typeV = "venueactivity";
            callVenueNotificationApi(page_number, typeV);
            tinyDB.putString(Constants.VENUE_ID, "");
            venueId = null;
        }

//        home_image_arrow.setOnClickListener(this);
    }

   /* private void setUpVenueRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        venueAsctivityRV.setLayoutManager(gridLayoutManager);
//        venueAdapter = new DashboardVenueAdapter(getActivity());
//        venueAsctivityRV.setAdapter(venueAdapter);

    }

    private void setUpInfluencerRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        influencerRV.setLayoutManager(gridLayoutManager);
//        influencerAdapter = new DashboardInfluencerAdapter(getActivity());
//        influencerRV.setAdapter(influencerAdapter);


    }

    private void setUpChatsRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        chatsRV.setLayoutManager(gridLayoutManager);
//        chatsAdapter = new DashboardChatsAdapter(getActivity());
//        chatsRV.setAdapter(chatsAdapter);

    }

    private void setUpInviteRecyclerView() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        inviteRV.setLayoutManager(gridLayoutManager);
//        newsAdapter = new DashBoardNewsAdapter(getActivity());
//        inviteRV.setAdapter(newsAdapter);


    }

    private void setUpNewsRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        newsRV.setLayoutManager(gridLayoutManager);
//        newsAdapter = new DashBoardNewsAdapter(getActivity());
//        newsRV.setAdapter(newsAdapter);

    }
*/

    private void setUpVenueRecyclerView() {
        gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        venueAsctivityRV.setLayoutManager(gridLayoutManager);
//        venueAdapter = new DashboardVenueAdapter(getActivity());
//        venueAsctivityRV.setAdapter(venueAdapter);
        final String type = "venueactivity";
//        layoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        rv.setLayoutManager(layoutManagaer);
        newsRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    userScrolled = true;
                    visibleItemCount = gridLayoutManager.getChildCount();
                    totalItemCount = gridLayoutManager.getItemCount();
                    pastVisiblesItems = gridLayoutManager
                            .findFirstVisibleItemPosition();
                    if (userScrolled
                            && (visibleItemCount + pastVisiblesItems) == totalItemCount) {


                        if (!visible) {
                            isPageUpdate = "1";
                            userScrolled = false;
                            page_number++;
//                            performPaging(page_number);
                            callVenueNotificationApi(page_number, type);
                        }


                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = gridLayoutManager.getChildCount();
                totalItemCount = gridLayoutManager.getItemCount();
                pastVisiblesItems = gridLayoutManager
                        .findFirstVisibleItemPosition();

                // Now check if userScrolled is true and also check if
                // the item is end then update recycler view and set
                // userScrolled to false
                if (userScrolled
                        && (visibleItemCount + pastVisiblesItems) == totalItemCount) {


                    if (!visible) {
                        isPageUpdate = "1";
                        userScrolled = false;
                        page_number++;
//                        performPaging(page_number);

                        callVenueNotificationApi(page_number, type);
                    }


                }
            }
        });


    }

    private void setUpInfluencerRecyclerView() {
        gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        influencerRV.setLayoutManager(gridLayoutManager);
//        influencerAdapter = new DashboardInfluencerAdapter(getActivity());
//        influencerRV.setAdapter(influencerAdapter);
        final String type = "influencer";
        influencerRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    userScrolled = true;
                    visibleItemCount = gridLayoutManager.getChildCount();
                    totalItemCount = gridLayoutManager.getItemCount();
                    pastVisiblesItems = gridLayoutManager
                            .findFirstVisibleItemPosition();
                    if (userScrolled
                            && (visibleItemCount + pastVisiblesItems) == totalItemCount) {


                        if (!visible) {
                            isPageUpdate = "1";
                            userScrolled = false;
                            page_number++;
//                            performPaging(page_number);
                            callInfluencerNotificationApi(page_number, type);
                        }


                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = gridLayoutManager.getChildCount();
                totalItemCount = gridLayoutManager.getItemCount();
                pastVisiblesItems = gridLayoutManager
                        .findFirstVisibleItemPosition();

                // Now check if userScrolled is true and also check if
                // the item is end then update recycler view and set
                // userScrolled to false
                if (userScrolled
                        && (visibleItemCount + pastVisiblesItems) == totalItemCount) {


                    if (!visible) {
                        isPageUpdate = "1";
                        userScrolled = false;
                        page_number++;
//                        performPaging(page_number);
                        callInfluencerNotificationApi(page_number, type);
                    }


                }
            }
        });


    }

//    private void setUpChatsRecyclerView() {
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
//        chatsRV.setLayoutManager(gridLayoutManager);
//        chatsAdapter = new DashboardChatsAdapter(getActivity(), inviteNotificationList);
//        chatsRV.setAdapter(chatsAdapter);
//
//    }

    private void setUpInviteRecyclerView() {

        gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        inviteRV.setLayoutManager(gridLayoutManager);
//        newsAdapter = new DashBoardNewsAdapter(getActivity(), newNotificationList);
//        inviteRV.setAdapter(newsAdapter);

        final String type = "invite";
//        layoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        rv.setLayoutManager(layoutManagaer);
        inviteRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    userScrolled = true;
                    visibleItemCount = gridLayoutManager.getChildCount();
                    totalItemCount = gridLayoutManager.getItemCount();
                    pastVisiblesItems = gridLayoutManager
                            .findFirstVisibleItemPosition();
                    if (userScrolled
                            && (visibleItemCount + pastVisiblesItems) == totalItemCount) {


                        if (!visible) {
                            isPageUpdate = "1";
                            userScrolled = false;
                            page_number++;
//                            performPaging(page_number);
                            callInviteNotificationApi(page_number, type);
                        }


                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = gridLayoutManager.getChildCount();
                totalItemCount = gridLayoutManager.getItemCount();
                pastVisiblesItems = gridLayoutManager
                        .findFirstVisibleItemPosition();

                // Now check if userScrolled is true and also check if
                // the item is end then update recycler view and set
                // userScrolled to false
                if (userScrolled
                        && (visibleItemCount + pastVisiblesItems) == totalItemCount) {


                    if (!visible) {
                        isPageUpdate = "1";
                        userScrolled = false;
                        page_number++;
//                        performPaging(page_number);

                        callInviteNotificationApi(page_number, type);
                    }


                }
            }
        });


    }

    private void setUpNewsRecyclerView() {
        gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        newsRV.setLayoutManager(gridLayoutManager);
        final String type = "news";
//        layoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        rv.setLayoutManager(layoutManagaer);
        newsRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    userScrolled = true;
                    visibleItemCount = gridLayoutManager.getChildCount();
                    totalItemCount = gridLayoutManager.getItemCount();
                    pastVisiblesItems = gridLayoutManager
                            .findFirstVisibleItemPosition();
                    if (userScrolled
                            && (visibleItemCount + pastVisiblesItems) == totalItemCount) {


                        if (!visible) {
                            isPageUpdate = "1";
                            userScrolled = false;
                            page_number++;
//                            performPaging(page_number);
                            getNotification(page_number, type);
                        }


                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = gridLayoutManager.getChildCount();
                totalItemCount = gridLayoutManager.getItemCount();
                pastVisiblesItems = gridLayoutManager
                        .findFirstVisibleItemPosition();

                // Now check if userScrolled is true and also check if
                // the item is end then update recycler view and set
                // userScrolled to false
                if (userScrolled
                        && (visibleItemCount + pastVisiblesItems) == totalItemCount) {


                    if (!visible) {
                        isPageUpdate = "1";
                        userScrolled = false;
                        page_number++;
//                        performPaging(page_number);

                        getNotification(page_number, type);
                    }


                }
            }
        });


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home3_crosss:
                getActivity().onBackPressed();

                break;
            case R.id.home_image_arrow:
                ((HomeActivity) getActivity()).replaceNotificationFragment();

                break;
            case R.id.new_btn_tv:
                newsRV.setVisibility(View.VISIBLE);
                inviteRV.setVisibility(View.GONE);
                chatsRV.setVisibility(View.GONE);
                venueAsctivityRV.setVisibility(View.GONE);
                influencerRV.setVisibility(View.GONE);

                newsLine.setVisibility(View.VISIBLE);
                inviteLine.setVisibility(View.GONE);
                chatLine.setVisibility(View.GONE);
                venueLine.setVisibility(View.GONE);
                influencerLine.setVisibility(View.GONE);

                String Type = "news";
                getNotification(page_number, Type);
                break;

            case R.id.invites_btn_tv:
                newsRV.setVisibility(View.GONE);
                inviteRV.setVisibility(View.VISIBLE);
                chatsRV.setVisibility(View.GONE);
                venueAsctivityRV.setVisibility(View.GONE);
                influencerRV.setVisibility(View.GONE);

                newsLine.setVisibility(View.GONE);
                inviteLine.setVisibility(View.VISIBLE);
                chatLine.setVisibility(View.GONE);
                venueLine.setVisibility(View.GONE);
                influencerLine.setVisibility(View.GONE);

                String type = "invite";
                callInviteNotificationApi(page_number, type);
                break;

            case R.id.chats_btn_tv:
                newsRV.setVisibility(View.GONE);
                inviteRV.setVisibility(View.GONE);
                chatsRV.setVisibility(View.VISIBLE);
                venueAsctivityRV.setVisibility(View.GONE);
                influencerRV.setVisibility(View.GONE);

                newsLine.setVisibility(View.GONE);
                inviteLine.setVisibility(View.GONE);
                chatLine.setVisibility(View.VISIBLE);
                venueLine.setVisibility(View.GONE);
                influencerLine.setVisibility(View.GONE);

//                getNotification(type);
                break;

            case R.id.venue_btn_tv:
                newsRV.setVisibility(View.GONE);
                inviteRV.setVisibility(View.GONE);
                chatsRV.setVisibility(View.GONE);
                venueAsctivityRV.setVisibility(View.VISIBLE);
                influencerRV.setVisibility(View.GONE);

                newsLine.setVisibility(View.GONE);
                inviteLine.setVisibility(View.GONE);
                chatLine.setVisibility(View.GONE);
                venueLine.setVisibility(View.VISIBLE);
                influencerLine.setVisibility(View.GONE);

                String typeV = "venueactivity";
                callVenueNotificationApi(page_number, typeV);

                break;
            case R.id.influencer_btn_tv:
                newsRV.setVisibility(View.GONE);
                inviteRV.setVisibility(View.GONE);
                chatsRV.setVisibility(View.GONE);
                venueAsctivityRV.setVisibility(View.GONE);
                influencerRV.setVisibility(View.VISIBLE);

                newsLine.setVisibility(View.GONE);
                inviteLine.setVisibility(View.GONE);
                chatLine.setVisibility(View.GONE);
                venueLine.setVisibility(View.GONE);
                influencerLine.setVisibility(View.VISIBLE);
                String typein = "influencer";

                callInfluencerNotificationApi(page_number, typein);
                break;

        }
    }


    private void callInfluencerNotificationApi(int page_number, String typein) {
        if (page_number == 1 && influenserList.size() > 0) {
            influenserList.clear();
        }
        progress.show();
        isPageUpdate = "0";
        Call<InfluencerResponse> call = APIClient.getInstance().getApiInterface().getInfluenserNotifiation(user_id, typein, page_number);
        new ResponseListner(this).getResponse(getContext(), call);
        Log.e("URL", call.request().url().toString());
    }

    private void callVenueNotificationApi(int page_number, String type) {
        if (page_number == 1 && venueNotificationList.size() > 0) {
            venueNotificationList.clear();
        }

        isPageUpdate = "0";
        Call<VenueResponse> call = APIClient.getInstance().getApiInterface().getVenueNotifiation(user_id, type, page_number);
        new ResponseListner(this).getResponse(getContext(), call);
        Log.e("URL", call.request().url().toString());

    }

    private void callInviteNotificationApi(int pageNum, String type) {

        if (pageNum == 1 && inviteNotificationList.size() > 0) {
            inviteNotificationList.clear();
        }
        isPageUpdate = "0";
        Call<InviteResponse> call = APIClient.getInstance().getApiInterface().getInviteNotifiation(user_id, type, pageNum);
        new ResponseListner(this).getResponse(getContext(), call);
        Log.e("URL", call.request().url().toString());
    }

    private void getProfilePer() {
        //  progressBar.setVisibility(View.VISIBLE);
        Call<ProfilePerResposne> call = APIClient.getInstance().getApiInterface()
                .getProfilePer(user_id);
        Log.e("MyUrl", call.request().url().toString());
        new ResponseListner(this).getResponse(getActivity(), call);
    }

    private void getNotification(int pageNum, String type) {

        if (page_number == 1 && newNotificationList.size() > 0) {
            newNotificationList.clear();
        }
        isPageUpdate = "0";
        progress.show();
        Call<NotificationModal> call = APIClient.getInstance().getApiInterface().getNotifications(user_id, type, pageNum);
        new ResponseListner(this).getResponse(getContext(), call);
        Log.e("URL", call.request().url().toString());
    }




/*
    @Override
    public void onApiResponse(Object response) {
        progress.dismiss();
        NotificationModal loginResponse = (NotificationModal) response;
        if (loginResponse.getStatus() == 1) {
            if (loginResponse.getNotifications().size() > 0) {
            } else {
                notificationLayout.setVisibility(View.GONE);
                noNotification.setVisibility(View.VISIBLE);
            }

        }
    }*/

    @SuppressLint("SetTextI18n")
    @Override
    public void onApiResponse(Object response) {
        if (response != null) {
            try {
                if (response instanceof NotificationModal) {
                    progress.dismiss();
                    NotificationModal loginResponse = (NotificationModal) response;
                    if (loginResponse.getStatus() == 1) {

                        if (loginResponse.getNotifications().size() > 0) {
                            newsRV.setVisibility(View.VISIBLE);
                            noNotification.setVisibility(View.GONE);

                            newNotificationList.addAll(loginResponse.getNotifications());
                            Log.d(TAG, "onApiResponse: " + newNotificationList.size());

                            if (isPageUpdate.equals("0")) {
                                setNewsAdapter(newNotificationList);
                            } else {
                                newsAdapter.notifyDataSetChanged();
                            }

                        } else {
                            if (loginResponse.getTotalCount() > 0) {
                                newsRV.setVisibility(View.VISIBLE);
                                noNotification.setVisibility(View.GONE);
                            } else {
                                newsRV.setVisibility(View.GONE);
                                noNotification.setVisibility(View.VISIBLE);
                            }

                        }

                    }
                } else if (response instanceof InviteResponse) {
                    progress.dismiss();
                    InviteResponse inviteResponse = (InviteResponse) response;
                    if (inviteResponse.getStatus() == 1) {

                        if (inviteResponse.getNotifications().size() > 0) {
                            inviteRV.setVisibility(View.VISIBLE);
                            noNotification.setVisibility(View.GONE);
                            inviteNotificationList.addAll(inviteResponse.getNotifications());
                            Log.d(TAG, "onApiResponse: " + inviteNotificationList.size());

                            if (isPageUpdate.equals("0")) {
                                setInviteAdapter(inviteNotificationList);
                            } else {
                                chatsAdapter.notifyDataSetChanged();
                            }

                        } else/* if (inviteResponse.getNotifications().size() == 0) */ {

                            if (inviteResponse.getTotalCount() > 0) {
                                inviteRV.setVisibility(View.VISIBLE);
                                noNotification.setVisibility(View.GONE);
                            } else {
                                inviteRV.setVisibility(View.GONE);
                                noNotification.setVisibility(View.VISIBLE);
                            }

//                            Toast.makeText(getActivity(), "You don't have any Notification.!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (response instanceof VenueResponse) {
                    progress.dismiss();
                    VenueResponse venueResponse = (VenueResponse) response;
                    if (venueResponse.getStatus() == 1) {

                        if (venueResponse.getNotifications().size() > 0) {

                            venueAsctivityRV.setVisibility(View.VISIBLE);
                            noNotification.setVisibility(View.GONE);
                            venueNotificationList.addAll(venueResponse.getNotifications());
                            Log.d(TAG, "onApiResponse: " + venueNotificationList.size());

                            if (isPageUpdate.equals("0")) {
                                setVenuewAdapter(venueNotificationList);
                            } else {
                                venueAdapter.notifyDataSetChanged();
                            }

                        } else {
                            if (venueResponse.getTotalCount() > 0) {
                                venueAsctivityRV.setVisibility(View.VISIBLE);
                                noNotification.setVisibility(View.GONE);
                            } else {
                                venueAsctivityRV.setVisibility(View.GONE);
                                noNotification.setVisibility(View.VISIBLE);
                            }

                        }

                    }
                } else if (response instanceof InfluencerResponse) {
                    progress.dismiss();
                    InfluencerResponse influencerResponse = (InfluencerResponse) response;
                    if (influencerResponse.getStatus() == 1) {

                        if (influencerResponse.getNotifications().size() > 0) {

                            influencerRV.setVisibility(View.VISIBLE);
                            noNotification.setVisibility(View.GONE);
                            influenserList.addAll(influencerResponse.getNotifications());
                            Log.d(TAG, "onApiResponse: " + influenserList.size());

                            if (isPageUpdate.equals("0")) {
                                setInfluenserAdapter(influenserList);
                            } else {
                                influencerAdapter.notifyDataSetChanged();
                            }

                        } else {
                            if (influencerResponse.getTotalCount() > 0) {
                                influencerRV.setVisibility(View.VISIBLE);
                                noNotification.setVisibility(View.GONE);
                            } else {
                                influencerRV.setVisibility(View.GONE);
                                noNotification.setVisibility(View.VISIBLE);
                            }

                        }
                    }
                }


            } catch (Exception e) {
                Log.d("TAG", "onApiResponse: " + e.getMessage());
            }
        }
    }

    private void setInfluenserAdapter(List<InfluencerResponse.NotificationsEntity> influenserList) {

        influencerAdapter = new DashboardInfluencerAdapter(getActivity(), influenserList, this);
        influencerRV.setAdapter(influencerAdapter);

    }

    private void setInviteAdapter(List<InviteResponse.NotificationsEntity> inviteNotificationList) {
        chatsAdapter = new NotificationInviteAdapter(getActivity(), inviteNotificationList, this);
        inviteRV.setAdapter(chatsAdapter);
    }


    private void setNewsAdapter(List<NotificationModal.NotificationsBean> newNotificationList) {
        newsAdapter = new NotificationNewsAdapter(getActivity(), newNotificationList);
        newsRV.setAdapter(newsAdapter);
    }

    private void setVenuewAdapter(List<VenueResponse.NotificationsEntity> venueNotificationList) {
        venueAdapter = new NotificationVenueAdapter(getActivity(), venueNotificationList, this);
        venueAsctivityRV.setAdapter(venueAdapter);
    }

    String venue_id, venueId;

    @Override
    public void onApiFailure(String message) {
        progress.dismiss();
    }


    @Override
    public void onItemClick(int pos, String tag) {

        if (tag.equals("invite")) {
            callNotificationReadApi();
        }

       /* if (tag.equals("invite")) {
            notificationId = inviteNotificationList.get(pos).getNotificationId();
            callNotificationReadApi();
        } */
        else if (tag.equals("venue")) {
            venue_id = venueNotificationList.get(pos).getSourceId();
            tinyDB.putString(Constants.VENUE_ID, venue_id);
            RestaurantVenueDetails fragment = new RestaurantVenueDetails();
            Bundle bundle = new Bundle();
            bundle.putString("venue_id", venueNotificationList.get(pos).getSourceId() /*String.valueOf(venueNotificationList.get(pos).getNotificationId())*/);
            bundle.putInt("notificationId", venueNotificationList.get(pos).getNotificationId());
            fragment.setArguments(bundle);
            ((HomeActivity) getActivity()).replaceVenueDetailFragment(fragment);
//            getSupportFragmentManager().popBackStack();

        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("venue", venue_id);
    }

    private void callNotificationReadApi() {

        Toast.makeText(getActivity(), "Notification Read", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            // Retrieve the user email value from bundle.
            venueId = savedInstanceState.getString("venue");

        }
    }
}
