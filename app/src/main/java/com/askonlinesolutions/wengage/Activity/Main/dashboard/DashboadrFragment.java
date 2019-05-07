package com.askonlinesolutions.wengage.Activity.Main.dashboard;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.dashboard_adapters.DashBoardNewsAdapter;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.dashboard_adapters.DashboardChatsAdapter;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.dashboard_adapters.DashboardInfluencerAdapter;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.dashboard_adapters.DashboardVenueAdapter;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.InfluencerResponse;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.InviteResponse;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.NotificationCountResponse;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.NotificationReadResponse;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.VenueResponse;
import com.askonlinesolutions.wengage.CallBacks.OnItemClickListener;
import com.askonlinesolutions.wengage.Fragment.Main.ChatFragment;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventDetailsFragment;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventSubCatFragment;
import com.askonlinesolutions.wengage.Fragment.Main.venue.VenueSubCatFragment;
import com.askonlinesolutions.wengage.Fragment.RestaurantVenueDetails;
import com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.vo.InterestedEventsResponse;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
import com.askonlinesolutions.wengage.Model.Response.NotificationModal;
import com.askonlinesolutions.wengage.Networks.NetworkConstants;
import com.askonlinesolutions.wengage.R;
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
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mukesh.tinydb.TinyDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static com.wassa.noyau.debug.KLog.e;

public class DashboadrFragment extends Fragment implements View.OnClickListener, OnResponseInterface, OnItemClickListener {

    private static final String TAG = DashboadrFragment.class.getName();
    private RecyclerView newsRV, inviteRV, chatsRV, venueAsctivityRV, influencerRV;
    private TextView newsTV, inviteTV, chatsTV, venueTV, influencerTV, txtName, invite_friend_tv, txtProfilePer,
            theCityTv, diningTV, noNotification, newsCountTv, inviteCountTV, chatCountTv, venueCountTv, influenserCountTv;
    private View newsLine, inviteLine, chatLine, venueLine, influencerLine;
    private ImageView imgProfile, imgProgress;
    private DashBoardNewsAdapter newsAdapter;
    private DashboardChatsAdapter chatsAdapter;
    private DashboardVenueAdapter venueAdapter;
    private DashboardInfluencerAdapter influencerAdapter;
    private LoginResponse loginResponse;
    private Gson gson = new Gson();
    private TinyDB tinyDB;
    private Progress progress;
    private int user_id, notificationId;
    private ImageView home_image_arrow;
    private ProgressBar completProgressBar;
    int page_number = 1;
    private boolean userScrolled = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean visible = false;
    private String isPageUpdate = "1";
    GridLayoutManager gridLayoutManager;
    private List<NotificationModal.NotificationsBean> newNotificationList = new ArrayList<>();
    private List<InviteResponse.NotificationsEntity> inviteNotificationList = new ArrayList<>();
    private List<VenueResponse.NotificationsEntity> venueNotificationList = new ArrayList<>();
    private List<InfluencerResponse.NotificationsEntity> influenserList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        tinyDB = new TinyDB(getContext());
        progress = new Progress(getActivity());
        init(view);
        if (Utility.isNetworkConnected(getActivity())) {
            if (tinyDB.getString(Constants.IS_CALL).equals("1")) {
            } else {
                if (tinyDB.getString(Constants.CITY).equals("")) {
                    callUpdateLocationApi("Toronto", loginResponse.getProfileInfo().getUserId());
                } else {
                    callUpdateLocationApi(tinyDB.getString(Constants.CITY), loginResponse.getProfileInfo().getUserId());
                }
            }
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }


        return view;
    }

    private void callUpdateLocationApi(String city, int userId) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("currentLocationCity", city);
        Ion.with(this)
                .load("PUT", NetworkConstants.PUT_UPDATE_CURRENT_CITY + userId)
                .setJsonObjectBody(jsonObject).asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        tinyDB.putDouble(Constants.LATITUDE, 0.0);
                        tinyDB.putDouble(Constants.LONGITUDE, 0.0);
//                        Toast.makeText(getActivity(), "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void init(View view) {

        loginResponse = gson.fromJson(tinyDB.getString("login_data"), LoginResponse.class);
        user_id = loginResponse.getProfileInfo().getUserId();
//        user_id=tinyDB.getInt(Constants.USER_ID);

        diningTV = view.findViewById(R.id.diningTV);
        theCityTv = view.findViewById(R.id.theCityTv);

        txtProfilePer = view.findViewById(R.id.fragment_dash_profile_per);
        invite_friend_tv = view.findViewById(R.id.invite_friend_tv);
        home_image_arrow = view.findViewById(R.id.home_image_arrow);
        completProgressBar = view.findViewById(R.id.activeProgress);
        imgProfile = view.findViewById(R.id.fragment_dashboard_img_profile);
        imgProgress = view.findViewById(R.id.fragment_dashboard_img_progress);
        newsRV = view.findViewById(R.id.news_rv);
        inviteRV = view.findViewById(R.id.invites_rv);
        chatsRV = view.findViewById(R.id.chats_rv);
        venueAsctivityRV = view.findViewById(R.id.venue_activity_rv);
        influencerRV = view.findViewById(R.id.influencer_rv);
        txtName = view.findViewById(R.id.fragment_dashboard_name);
        newsTV = view.findViewById(R.id.new_btn_tv);
        inviteTV = view.findViewById(R.id.invites_btn_tv);
        chatsTV = view.findViewById(R.id.chats_btn_tv);
        venueTV = view.findViewById(R.id.venue_btn_tv);
        influencerTV = view.findViewById(R.id.influencer_btn_tv);
        noNotification = view.findViewById(R.id.noNotification);

        newsCountTv = view.findViewById(R.id.news_count);
        inviteCountTV = view.findViewById(R.id.invite_count);
        chatCountTv = view.findViewById(R.id.chat_count);
        venueCountTv = view.findViewById(R.id.venue_count);
        influenserCountTv = view.findViewById(R.id.influencer_count);

        newsLine = view.findViewById(R.id.news_line);
        inviteLine = view.findViewById(R.id.invite_line);
        chatLine = view.findViewById(R.id.chat_line);
        venueLine = view.findViewById(R.id.venue_line);
        influencerLine = view.findViewById(R.id.influencer_line);

        txtName.setText("Hi" + " " + loginResponse.getProfileInfo().getKnownByName());
        imgProgress.setVisibility(View.VISIBLE);
        Glide.with(getActivity()).asGif().load(R.drawable.ic_loading).into(imgProgress);
        /*if (loginResponse.getProfileInfo().getInfluencer() == 0) {
            imgProfile.setBackgroundResource(R.drawable.pink_circle);
        } else imgProfile.setBackgroundResource(R.drawable.blue_circle);*/

        Glide.with(this)
                .load(loginResponse.getProfileInfo().getPhotoURL())
                .apply(RequestOptions.circleCropTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        imgProgress.setVisibility(View.GONE);

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        imgProgress.setVisibility(View.GONE);
                        imgProfile.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(imgProfile);

        setUpNewsRecyclerView();
        setUpInviteRecyclerView();
//        setUpChatsRecyclerView();
        setUpInfluencerRecyclerView();
        setUpVenueRecyclerView();
        diningTV.setOnClickListener(this);
        theCityTv.setOnClickListener(this);
        newsTV.setOnClickListener(this);
        inviteTV.setOnClickListener(this);
        chatsTV.setOnClickListener(this);
        venueTV.setOnClickListener(this);
        influencerTV.setOnClickListener(this);
        invite_friend_tv.setOnClickListener(this);
        home_image_arrow.setOnClickListener(this);

       String venueId = tinyDB.getString("FROM");
        if (!venueId.equals("")) {
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
            tinyDB.putString(Constants.FROM, "");
//            venueId = null;
        }

        if (Utility.isNetworkConnected(getActivity())) {
            getProfilePer();
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
        String Type = "news";
        getNotification(page_number, Type);

        callNotificationCountApi();

    }

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

 /*   private void setUpChatsRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        chatsRV.setLayoutManager(gridLayoutManager);
        chatsAdapter = new DashboardChatsAdapter(getActivity(), inviteNotificationList);
        chatsRV.setAdapter(chatsAdapter);

    }*/

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

                        getNotification(page_number, type);
                    }


                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.invite_friend_tv:
//            fdgdfgdfg
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 1);
                break;
            case R.id.theCityTv:
//                tinyDB.putString(Constants.FROM_COMING, "TheCity");
                EventSubCatFragment fragments = new EventSubCatFragment();
                Bundle bundles = new Bundle();
                bundles.putInt("categoryId", 1);
                bundles.putString("categoryName", "The City");
                bundles.putString("FromDashBoard", "1");
                fragments.setArguments(bundles);
                ((HomeActivity) getActivity()).replaceEventSubCatFragment(fragments);

                break;
            case R.id.diningTV:

                VenueSubCatFragment fragment = new VenueSubCatFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("categoryId", 2);

                bundle.putString("categoryName", "dining TV");
                bundle.putString("FromDashBoard", "1");
                fragment.setArguments(bundle);
                ((HomeActivity) getActivity()).replaceVenueSubCatFragment(fragment);
              /*  tinyDB.putString(Constants.FROM_COMING, "Dining");
                ((HomeActivity) getActivity()).replaceTheCityFragment();*/

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
                chatsRV.setVisibility(View.GONE);
                venueAsctivityRV.setVisibility(View.GONE);
                influencerRV.setVisibility(View.GONE);

                newsLine.setVisibility(View.GONE);
                inviteLine.setVisibility(View.GONE);
                chatLine.setVisibility(View.VISIBLE);
                venueLine.setVisibility(View.GONE);
                influencerLine.setVisibility(View.GONE);

                ((HomeActivity) getActivity()).replaceChatFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new ChatFragment()).addToBackStack(null).commit();


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

    private void callNotificationCountApi() {
        Call<NotificationCountResponse> call = APIClient.getInstance().getApiInterface().getNotifiationCount(user_id);
        new ResponseListner(this).getResponse(getContext(), call);
        Log.e("URL", call.request().url().toString());
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
        progress.show();
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
        progress.show();
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
        isPageUpdate = "0";
        progress.show();
        Call<NotificationModal> call = APIClient.getInstance().getApiInterface().getNotifications(user_id, type, pageNum);
        new ResponseListner(this).getResponse(getContext(), call);
        Log.e("URL", call.request().url().toString());
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onApiResponse(Object response) {
        if (response != null) {
            try {
                if (response instanceof ProfilePerResposne) {
                    ProfilePerResposne profilePerResposne = (ProfilePerResposne) response;
                    if (profilePerResposne.getStatus() == 1) {
                        completProgressBar.setProgress(profilePerResposne.getProfileComplete());
                        txtProfilePer.setText(profilePerResposne.getProfileComplete() + "%");
                    } else new BaseClass(getActivity()).showToast(profilePerResposne.getMessage());
                } else if (response instanceof NotificationModal) {
                    progress.dismiss();
                    NotificationModal loginResponse = (NotificationModal) response;
                    if (loginResponse.getStatus() == 1) {
                        if (loginResponse.getNotifications().size() > 0) {

                            for (int i = 0; i < loginResponse.getNotifications().size(); i++) {
                                if (loginResponse.getNotifications().get(i).getIsRead() == 0) {
                                    newsRV.setVisibility(View.VISIBLE);
                                    noNotification.setVisibility(View.GONE);
                                    newNotificationList.add(loginResponse.getNotifications().get(i));
                                    Log.d(TAG, "onApiResponse: " + newNotificationList.size());
                                }
                            }
                            if (isPageUpdate.equals("0")) {
                                if (newNotificationList.size() > 0) {
                                    setNewsAdapter(newNotificationList);
                                } else {
                                    newsRV.setVisibility(View.GONE);
                                    noNotification.setVisibility(View.VISIBLE);
                                }

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


                            for (int i = 0; i < inviteResponse.getNotifications().size(); i++) {
                                if (inviteResponse.getNotifications().get(i).getIsRead() == 0) {
                                    inviteRV.setVisibility(View.VISIBLE);
                                    noNotification.setVisibility(View.GONE);
                                    inviteNotificationList.add(inviteResponse.getNotifications().get(i));
                                    Log.d(TAG, "onApiResponse: " + inviteNotificationList.size());
                                }
                            }
//                            inviteRV.setVisibility(View.VISIBLE);
//                            noNotification.setVisibility(View.GONE);
//                            inviteNotificationList.addAll(inviteResponse.getNotifications());
//                            Log.d(TAG, "onApiResponse: " + inviteNotificationList.size());

                            if (isPageUpdate.equals("0")) {
                                if (inviteNotificationList.size() > 0) {
                                    setInviteAdapter(inviteNotificationList);
                                } else {
                                    inviteRV.setVisibility(View.GONE);
                                    noNotification.setVisibility(View.VISIBLE);
                                }

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

                        }
                    }
                } else if (response instanceof VenueResponse) {
                    progress.dismiss();
                    VenueResponse venueResponse = (VenueResponse) response;
                    if (venueResponse.getStatus() == 1) {

                        if (venueResponse.getNotifications().size() > 0) {

                            for (int i = 0; i < venueResponse.getNotifications().size(); i++) {
                                if (venueResponse.getNotifications().get(i).getIsRead() == 0) {
                                    venueAsctivityRV.setVisibility(View.VISIBLE);
                                    noNotification.setVisibility(View.GONE);
                                    venueNotificationList.add(venueResponse.getNotifications().get(i));
                                    Log.d(TAG, "onApiResponse: " + venueNotificationList.size());
                                }
                            }
                            if (isPageUpdate.equals("0")) {
                                if (venueNotificationList.size() > 0) {
                                    setVenuewAdapter(venueNotificationList);
                                } else {
                                    venueAsctivityRV.setVisibility(View.GONE);
                                    noNotification.setVisibility(View.VISIBLE);
                                }

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
                } else if (response instanceof NotificationCountResponse) {
                    progress.dismiss();
                    NotificationCountResponse countResponse = (NotificationCountResponse) response;
                    if (countResponse.getStatus() == 1) {

                        if (countResponse.getNotificationsCount() != null) {

                            if (countResponse.getNotificationsCount().getTotalUnreadChatNotifications() > 0) {
                                chatCountTv.setVisibility(View.VISIBLE);
                                chatCountTv.setText(String.valueOf(countResponse.getNotificationsCount().getTotalUnreadChatNotifications()));
                            } else {
                                chatCountTv.setVisibility(View.GONE);
                                chatCountTv.setText(String.valueOf(countResponse.getNotificationsCount().getTotalUnreadChatNotifications()));
                            }
                            if (countResponse.getNotificationsCount().getTotalUnreadVenueNotifications() > 0) {
                                venueCountTv.setVisibility(View.VISIBLE);
                                venueCountTv.setText(String.valueOf(countResponse.getNotificationsCount().getTotalUnreadVenueNotifications()));
                            } else {
                                venueCountTv.setVisibility(View.GONE);
                                venueCountTv.setText(String.valueOf(countResponse.getNotificationsCount().getTotalUnreadVenueNotifications()));
                            }

                            if (countResponse.getNotificationsCount().getTotalUnreadInviteNotifications() > 0) {
                                inviteCountTV.setVisibility(View.VISIBLE);
                                inviteCountTV.setText(String.valueOf(countResponse.getNotificationsCount().getTotalUnreadInviteNotifications()));
                            } else {
                                inviteCountTV.setVisibility(View.GONE);
                                inviteCountTV.setText(String.valueOf(countResponse.getNotificationsCount().getTotalUnreadInviteNotifications()));
                            }

                            if (countResponse.getNotificationsCount().getTotalUnreadInfluencerNotifications() > 0) {
                                influenserCountTv.setVisibility(View.VISIBLE);
                                influenserCountTv.setText(String.valueOf(countResponse.getNotificationsCount().getTotalUnreadInfluencerNotifications()));
                            } else {
                                influenserCountTv.setVisibility(View.GONE);
                                influenserCountTv.setText(String.valueOf(countResponse.getNotificationsCount().getTotalUnreadInfluencerNotifications()));
                            }
                            if (countResponse.getNotificationsCount().getTotalUnreadNewsNotifications() > 0) {
                                newsCountTv.setVisibility(View.VISIBLE);
                                newsCountTv.setText(String.valueOf(countResponse.getNotificationsCount().getTotalUnreadNewsNotifications()));
                            } else {
                                newsCountTv.setVisibility(View.GONE);
                                newsCountTv.setText(String.valueOf(countResponse.getNotificationsCount().getTotalUnreadNewsNotifications()));
                            }

                        }

                    }
                } else if (response instanceof NotificationReadResponse) {
                    progress.dismiss();
                    NotificationReadResponse readResponse = (NotificationReadResponse) response;
                    if (readResponse.getStatus() == 1) {

                        if (readResponse.getNotificationData() != null) {
                            String type = "invite";
                            inviteNotificationList.clear();
                            callInviteNotificationApi(page_number, type);
//                            Toast.makeText(getActivity(), "Notification Readed", Toast.LENGTH_SHORT).show();

                        } else {

                        }


                    }
                }
                else if (response instanceof InfluencerResponse) {
                    progress.dismiss();
                    InfluencerResponse influencerResponse = (InfluencerResponse) response;
                    if (influencerResponse.getStatus() == 1) {

                        if (influencerResponse.getNotifications().size() > 0) {

                            for (int i = 0; i < influencerResponse.getNotifications().size(); i++) {
                                if (influencerResponse.getNotifications().get(i).getIsRead() == 0) {
                                    influencerRV.setVisibility(View.VISIBLE);
                                    noNotification.setVisibility(View.GONE);
                                    influenserList.add(influencerResponse.getNotifications().get(i));
                                    Log.d(TAG, "onApiResponse: " + influenserList.size());
                                }
                            }
                            if (isPageUpdate.equals("0")) {
                                if (influenserList.size() > 0) {
                                    setInfluenserAdapter(influenserList);
                                } else {
                                    influencerRV.setVisibility(View.GONE);
                                    noNotification.setVisibility(View.VISIBLE);
                                }

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
        chatsAdapter = new DashboardChatsAdapter(getActivity(), inviteNotificationList, this);
        inviteRV.setAdapter(chatsAdapter);
    }

    String read;

    private void setNewsAdapter(List<NotificationModal.NotificationsBean> newNotificationList) {
        newsAdapter = new DashBoardNewsAdapter(getActivity(), newNotificationList);
        newsRV.setAdapter(newsAdapter);
    }

    private void setVenuewAdapter(List<VenueResponse.NotificationsEntity> venueNotificationList) {
        venueAdapter = new DashboardVenueAdapter(getActivity(), venueNotificationList, this);
        venueAsctivityRV.setAdapter(venueAdapter);
    }

    @Override
    public void onApiFailure(String message) {

    }

    @Override
    public void onItemClick(int pos, String tag) {
        if (tag.equals("invite")) {
            notificationId = inviteNotificationList.get(pos).getNotificationId();
            callNotificationReadApi();
        } else if (tag.equals("venue")) {
            tinyDB.putString(Constants.FROM, "FROM");
            RestaurantVenueDetails fragment = new RestaurantVenueDetails();
            Bundle bundle = new Bundle();
            bundle.putString("venue_id", venueNotificationList.get(pos).getSourceId() /*String.valueOf(venueNotificationList.get(pos).getNotificationId())*/);
            bundle.putInt("notificationId", venueNotificationList.get(pos).getNotificationId());
            fragment.setArguments(bundle);
            ((HomeActivity) getActivity()).replaceVenueDetailFragment(fragment);
        }

    }

    private void callNotificationReadApi() {
        progress.show();
        Call<NotificationReadResponse> call = APIClient.getInstance().getApiInterface().getReadNotification(user_id, notificationId);
        Log.e("MyUrl", call.request().url().toString());
        new ResponseListner(this).getResponse(getActivity(), call);

    }
}

