package com.askonlinesolutions.wengage.Fragment.Sub.eventsInYourCity;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Adapter.AdapterYourInterest;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventDetailsFragment;
import com.askonlinesolutions.wengage.Fragment.Sub.eventsInYourCity.vo.BookmarkRequest;
import com.askonlinesolutions.wengage.Fragment.Sub.eventsInYourCity.vo.InterestedRequest;
import com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.YourInterestFragment;
import com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.vo.InterestedEventsResponse;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.ApiInterface;
import com.askonlinesolutions.wengage.network.ApiResponse;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Progress;
import com.askonlinesolutions.wengage.utils.Utility;
import com.google.gson.Gson;
import com.mukesh.tinydb.TinyDB;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeeAllFragment extends Fragment implements OnResponseInterface {


    private static final String TAG = SeeAllFragment.class.getName();
    private TinyDB tinyDB;
    String type;
    private int user_id;
    private int pageCount;
    private Dialog errorDialog;
    private AdapterYourInterest adapterYourInterest;
    private boolean status_desc = false;
    private String eventId;
    private boolean status_bookmark_venues = true, status_interested_venues = false;
    private boolean status_bookmark_events = false, status_interested_events = false;
    private int str_image, str_image_2, status_bookmark;
    LinearLayoutManager layoutManagaer;
    private RecyclerView rv;
    private LinearLayout layout_top_switch;
    private SwitchCompat sw;
    int page = 1, subcategory_page = 1, reachMax;
    private List<InterestedEventsResponse.EventsListResponse> eventsListResponses = new ArrayList<>();
    private Progress progress;

    int page_number = 1;
    private boolean userScrolled = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean visible = false;
    private String isPageUpdate;

    public SeeAllFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_see_all, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tinyDB = new TinyDB(getContext());
        type = tinyDB.getString("bottom_click");
        user_id = tinyDB.getInt(Constants.USER_ID);
        init();
        callSeeAllFragmentApi(page, user_id);
    }

    private void init() {
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        rv = getView().findViewById(R.id.see_all_recycler);

        layoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManagaer);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

                // Now check if userScrolled is true and also check if
                // the item is end then update recycler view and set
                // userScrolled to false
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
    }

    private void performPaging(int page_number) {
        if (Utility.isNetworkConnected(getActivity())) {
            ApiInterface apiService = new APIClient().getClient().create(ApiInterface.class);
            Call<InterestedEventsResponse> call = apiService.getEventList(page_number, user_id);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("SeeAllURL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    private void callSeeAllFragmentApi(int page, int user_id) {
        if (Utility.isNetworkConnected(getActivity())) {
            isPageUpdate = "0";
            progress.show();
//        if (page == 0) {
//            eventsListResponses.clear();
//
//        }
//          user_id = 165;
            ApiInterface apiService = new APIClient().getClient().create(ApiInterface.class);
            Call<InterestedEventsResponse> call = apiService.getEventList(page, user_id);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("SeeAllURL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }


    @Override
    public void onApiResponse(Object response) {
        progress.dismiss();
//        progressBar.setVisibility(View.GONE);
//        if (page == 0) {
//            eventsListResponses.clear();
//        }

//        Log.d("MyResponse", response.toString());
        if (response != null) {
//            progressBar.setVisibility(View.GONE);

            try {
                if (response instanceof InterestedEventsResponse) {
                    InterestedEventsResponse eventsResponse = (InterestedEventsResponse) response;
                    if (eventsResponse.getStatus() == 1) {
                        reachMax = eventsResponse.getTotalPages();
                        eventsListResponses.addAll(eventsResponse.getEventsList());
                        Log.d(TAG, "onApiResponse: " + eventsListResponses.size());
                        if (eventsResponse.getEventsList().size() > 0) {
                            if (isPageUpdate.equals("0")) {
                                SetSeeAllEventsAdapter(eventsListResponses);
                            } else {
                                adapterYourInterest.notifyDataSetChanged();
                            }

                        } else {
//                            Toast.makeText(getActivity(), "You don't have any event on this section", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, eventsResponse.getMessage());
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

    @Override
    public void onApiFailure(String message) {
        progress.dismiss();
//        if (!message.equals("") && message != null) {
//            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, message);
//            errorDialog.show();
//        }


    }


    private void SetSeeAllEventsAdapter(List<InterestedEventsResponse.EventsListResponse> eventsListResponses) {
        adapterYourInterest = new AdapterYourInterest();
        rv.setAdapter(adapterYourInterest);

    }

    private void setinterest(String eventId, int status_bookmark) {
        if (Utility.isNetworkConnected(getActivity())) {
            InterestedRequest interestedRequest = new InterestedRequest();
            interestedRequest.setEventId(eventId);
            interestedRequest.setUserId(user_id);
            interestedRequest.setIsInterested(status_bookmark);
            Log.d(TAG, "MyRequest: " + new Gson().toJson(interestedRequest));
            Call<ApiResponse> call = APIClient.getInstance().getApiInterface().eventInterested(interestedRequest);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("InterestedURL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }

    private void setBookmark(String eventId, int status) {
        if (Utility.isNetworkConnected(getActivity())) {
//        progressBar.setVisibility(View.VISIBLE);
            BookmarkRequest bookmarkRequest = new BookmarkRequest();
            bookmarkRequest.setEventId(eventId);
            bookmarkRequest.setUserId(user_id);
            bookmarkRequest.setIsBookmarked(status);

//        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            Call<ApiResponse> call = APIClient.getInstance().getApiInterface().eventBookmarked(bookmarkRequest);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.e("URL", call.request().url().toString());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }

    public interface UpdateUi {
        void upDateApiCall(String pos);


    }
    class AdapterYourInterest extends RecyclerView.Adapter<AdapterYourInterest.MyViewHolder> {



        public class MyViewHolder extends RecyclerView.ViewHolder {

            public ImageView iv_image, /*iv_circle,*/
                    iv_butterfly, iv_check, iv_bookmarks;
            public TextView tv_name, tv_review, tv_interested;
            RatingBar ratingBar;

            public MyViewHolder(View view) {
                super(view);
                ratingBar = view.findViewById(R.id.event_rating_bar);
                iv_image = view.findViewById(R.id.item_your_interest_list_image);
                iv_check = view.findViewById(R.id.item_your_interest_list_check);
                iv_butterfly = view.findViewById(R.id.item_your_interest_list_butterfly);
//            iv_circle = view.findViewById(R.id.iyic);
                iv_bookmarks = view.findViewById(R.id.item_your_interest_list_bookmarks);
                tv_name = view.findViewById(R.id.item_your_interest_list_name);
                tv_review = view.findViewById(R.id.item_your_interest_list_reviews);
                tv_interested = view.findViewById(R.id.item_your_interest_list_interested);


        }}

        @Override
        public AdapterYourInterest.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_your_interest_list, parent, false);

            return new AdapterYourInterest.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final AdapterYourInterest.MyViewHolder holder, final int position) {
            if (eventsListResponses.size() != 0) {
                if (!eventsListResponses.get(position).getImageURL().equalsIgnoreCase("")) {
                    Picasso.with(getActivity()).load(eventsListResponses.get(position).getImageURL()).into(holder.iv_image);
                }

                holder.tv_name.setText(eventsListResponses.get(position).getName());

                if (eventsListResponses.get(position).getReviewCount() != 0)
                    holder.tv_review.setText(eventsListResponses.get(position).getReviewCount() + "Reviews");
                else holder.tv_review.setText("");

                if (eventsListResponses.get(position).getAvgRating() != null && !eventsListResponses.get(position).getAvgRating().equals("0"))
                    holder.ratingBar.setRating(Float.parseFloat(eventsListResponses.get(position).getAvgRating()));

                if (eventsListResponses.get(position).getIsBookmarked() == 1) {
                    status_bookmark_events = true;
                    holder.iv_bookmarks.setImageResource(R.drawable.ic_favorites);
                } else {
                    status_bookmark_events = false;
                    holder.iv_bookmarks.setImageResource(R.drawable.ic_favorites_1);
                }
                if (eventsListResponses.get(position).getIsInterested() == 1) {
                    holder.tv_interested.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
                } else {
                    holder.tv_interested.setTextColor(getActivity().getResources().getColor(R.color.text_color));
                }

                holder.iv_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        type = "event";
                        EventDetailsFragment fragment = new EventDetailsFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("event_id", eventsListResponses.get(position).getEventId());
                        bundle.putString("type", type);
                        fragment.setArguments(bundle);
                        ((HomeActivity) getActivity()).replaceEventDetailsFragment(fragment);
                    }
                });
                holder.iv_bookmarks.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!status_bookmark_events) {
                            holder.iv_bookmarks.setImageResource(R.drawable.ic_favorites);
                            status_bookmark_events = true;
                            status_bookmark = 1;
                            setBookmark(eventsListResponses.get(position).getEventId(),status_bookmark);

                        } else {
                            holder.iv_bookmarks.setImageResource(R.drawable.ic_favorites_1);
                            status_bookmark_events = false;
                            status_bookmark = 0;
                            setBookmark(eventsListResponses.get(position).getEventId(),status_bookmark);
                        }

//                    click.method_AdapterYourInterest(getAdapterPosition(), "bookmarks");
                    }
                });

                holder.tv_interested.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!status_interested_events) {
                            holder.tv_interested.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
                            status_interested_events = true;
                            status_bookmark = 1;
                            setinterest(eventsListResponses.get(position).getEventId(),status_bookmark);
                        } else {
                            holder.tv_interested.setTextColor(getActivity().getResources().getColor(R.color.text_color));
                            status_interested_events = false;
                        status_bookmark = 0;
//                        setinterest(venueId,status_bookmark);
                            setinterest(eventsListResponses.get(position).getEventId(),status_bookmark);
                        }

//                    click.method_AdapterYourInterest(getAdapterPosition(), "interested");
                    }
                });

                holder.iv_check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

//                    click.method_AdapterYourInterest(getAdapterPosition(), "check");
                    }
                });
//click.upDateApiCall();




            }
            if (position == eventsListResponses.size() - 1) {
//            click.lastIndex(position);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


//                RestaurantDetails fragment = new RestaurantDetails();
//                Bundle bundle = new Bundle();
//                bundle.putString("image", eventList.get(position).getImageURL());
////                bundle.putInt("image2", bottom_list_image_2.get(position));
////                bundle.putString("circle", bottom_list_circle.get(position));
////                bundle.putString("ad", bottom_list_ad.get(position));
//                bundle.putString("name", eventList.get(position).getName());
//                bundle.putInt("review", eventList.get(position).getReviewCount());
////                bundle.putString("km", bottom_list_distance.get(position));
////                bundle.putString("butterfly", bottom_list_butterfly.get(position));
////                bundle.putString("check", bottom_list_check.get(position));
////                bundle.putString("type", type);
//                fragment.setArguments(bundle);
//                new BaseClass(context).callFragment1(fragment, fragment.getClass().getName(), manager);

                }
            });
        }

        @Override
        public int getItemCount() {
            return eventsListResponses.size();
        }

}}
