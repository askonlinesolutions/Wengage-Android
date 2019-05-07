package com.askonlinesolutions.wengage.Fragment.Main;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Activity.Sub.AdapterSearchEvent;
import com.askonlinesolutions.wengage.Activity.Sub.EventSearchAdapter;
import com.askonlinesolutions.wengage.Activity.Sub.Search;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventDetailsFragment;
import com.askonlinesolutions.wengage.Fragment.Main.venue.details.RestaurantDetails;
import com.askonlinesolutions.wengage.Fragment.Main.vo.EventsSearchResponse;
import com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.vo.GeneralSearchResponse;
import com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.vo.InterestedEventsResponse;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.ApiInterface;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Progress;
import com.askonlinesolutions.wengage.utils.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static com.askonlinesolutions.wengage.Helper.BaseClass.hideSoftKeyboard;

public class SearchFragment extends Fragment implements View.OnClickListener, EventSearchAdapter.OnItemClickListener, OnResponseInterface {

    private ImageView search_cross, searchBarIv, backBtnIv;
    private EventSearchAdapter searchAdapter;
    private AdapterSearchEvent adapterSearchEvent;
    private RecyclerView searchRv;
    private Gson gson = new Gson();
    private LinearLayoutManager layoutManager;
    private FrameLayout frameLayout;
    private String keyword;
    private Progress progress;
    String name;
    private AutoCompleteTextView search_main;
    private String TAG = Search.class.getName();
    private List<GeneralSearchResponse.SearchedDataList> searchListResponses = new ArrayList<>();
    private List<EventsSearchResponse.EventsListBean> eventsearchList = new ArrayList<>();
    int page_number = 1;
    private boolean userScrolled = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean visible = false;
    private String isPageForSearch, isPageForEventSearch;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        search_cross = view.findViewById(R.id.search_cross);
        searchBarIv = view.findViewById(R.id.search_bar);
        backBtnIv = view.findViewById(R.id.back_iv);
        searchRv = view.findViewById(R.id.search_rv);
        frameLayout = view.findViewById(R.id.search_fragment_cuntainer);
        search_cross.setOnClickListener(this);
        search_main = view.findViewById(R.id.search_main);
        search_main.setOnClickListener(this);
        backBtnIv.setOnClickListener(this);
        layoutManager = new LinearLayoutManager(getContext());
        searchRv.setLayoutManager(layoutManager);
        init();
        return view;
    }

    private void init() {
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        searchRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    userScrolled = true;
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager
                            .findFirstVisibleItemPosition();
                    if (userScrolled
                            && (visibleItemCount + pastVisiblesItems) == totalItemCount) {


                        if (!visible) {
                            if (name != null && name.equals("inTheKnow")) {
                                isPageForEventSearch = "1";
                                performpPagingEvent();
                            } else {
                                isPageForSearch = "1";
                                PerformingPaging();
                            }
                        }


                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisiblesItems = layoutManager
                        .findFirstVisibleItemPosition();
                if (userScrolled
                        && (visibleItemCount + pastVisiblesItems) == totalItemCount) {

                    if (!visible) {
//                        progressBar.setVisibility(View.VISIBLE);
                        if (name != null && name.equals("inTheKnow")) {
                            isPageForEventSearch = "1";
                            performpPagingEvent();
                        } else {
                            isPageForSearch = "1";
                            PerformingPaging();
                        }
                    }
                }
            }
        });
        if (getArguments() != null) {
            name = getArguments().getString("name");

        }
        search_main.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.equals("")) {
                } else {
                    if (name != null && name.equals("inTheKnow")) {
                        isPageForEventSearch = "0";
//                    if (name.equals("inTheKnow")) {
                        callEventSearchApi();
                    } else {
                        isPageForSearch = "0";
                        callSearchApi();
//                    }
                    }
                }

            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        search_main.setThreshold(2);//will start working from first character
//        setMyAdapter();
        setUpSearchRecyclerView();

    }

    private void performpPagingEvent() {
        if (Utility.isNetworkConnected(getActivity())) {
            progress.show();
            Call<EventsSearchResponse> call = APIClient.getInstance().getApiInterface().getEventSearchesList(keyword);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.d("TAG", "EventSearchUrl: " + call.request().url());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    private void PerformingPaging() {
        if (Utility.isNetworkConnected(getActivity())) {
            progress.show();
            Call<GeneralSearchResponse> call = APIClient.getInstance().getApiInterface().getSearchesList(keyword, page_number);
            new ResponseListner(this).getResponse(getContext(), call);
            Log.d("TAG", "SEARCHUrl: " + call.request().url());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_cross:
                search_main.setText("");
                hideSoftKeyboard(getActivity());
                break;
            case R.id.search_main:

                search_main.showDropDown();
                search_main.enoughToFilter();
                search_main.requestFocus();
                break;


            case R.id.back_iv:

                getFragmentManager().popBackStackImmediate();
                break;
        }

    }

    private void setUpSearchRecyclerView() {

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        searchRv.setLayoutManager(layoutManager);

    }

    private void callEventSearchApi() {

        keyword = search_main.getText().toString().trim();
        if (keyword.equals("")) {
            Toast.makeText(getActivity(), "Please give some input", Toast.LENGTH_SHORT).show();
        } else {
            if (Utility.isNetworkConnected(getActivity())) {
                progress.show();
                Call<EventsSearchResponse> call = APIClient.getInstance().getApiInterface().getEventSearchesList(keyword);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.d("TAG", "EventSearchUrl: " + call.request().url());
            } else {
                Dialog errorDialog = null;
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
                errorDialog.show();
            }
        }


    }

    private void callSearchApi() {
        page_number = 1;
        keyword = search_main.getText().toString().trim();
        if (keyword.equals("")) {
            Toast.makeText(getActivity(), "Please give some input", Toast.LENGTH_SHORT).show();
        } else {
            if (Utility.isNetworkConnected(getActivity())) {
                progress.show();
                Call<GeneralSearchResponse> call = APIClient.getInstance().getApiInterface().getSearchesList(keyword, page_number);
                new ResponseListner(this).getResponse(getContext(), call);
                Log.d("TAG", "SEARCHUrl: " + call.request().url());
            } else {
                Dialog errorDialog = null;
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
                errorDialog.show();
            }
        }

    }

    @Override
    public void onApiResponse(Object response) {
        progress.dismiss();
        if (response != null) {
            if (response instanceof GeneralSearchResponse) {
                /*if (searchListResponses != null) {
                    searchListResponses.clear();
                }*/

                GeneralSearchResponse eventsResponse = (GeneralSearchResponse) response;
                if (eventsResponse.getStatus() == 1) {
                    searchRv.setVisibility(View.VISIBLE);
                    if (eventsResponse.getSearchedData() != null) {
                        /*if (searchListResponses != null) {
                            searchListResponses.clear();
                        }*/
                        searchListResponses.addAll(eventsResponse.getSearchedData());
                        if (isPageForSearch.equals("0")) {
                            SetSearchAdapter(searchListResponses);
                        } else {
                            searchAdapter.notifyDataSetChanged();
                        }

//                        searchAdapter.notifyDataSetChanged();
                    }
                } else {
//                    new BaseClass(getContext()).showToast(eventsResponse.getMessage());
                    searchRv.setVisibility(View.GONE);
//                    searchAdapter.notifyDataSetChanged();
                }

            } else if (response instanceof EventsSearchResponse) {
                /*if (eventsearchList != null) {
                    eventsearchList.clear();
                }*/

                EventsSearchResponse searchResponse = (EventsSearchResponse) response;
                if (searchResponse.getStatus() == 1) {
                    searchRv.setVisibility(View.VISIBLE);
                    if (searchResponse.getEventsList() != null) {
                       /* if (eventsearchList != null) {
                            eventsearchList.clear();
                        }*/
                        eventsearchList.addAll(searchResponse.getEventsList());
                        if (isPageForEventSearch.equals("0")) {
                            SetEventSearchAdapter(eventsearchList);
                        } else {
                            adapterSearchEvent.notifyDataSetChanged();
                        }

//                        searchAdapter.notifyDataSetChanged();
                    }
                } else {
//                    new BaseClass(getContext()).showToast(searchResponse.getMessage());
                    searchRv.setVisibility(View.GONE);
//                    searchAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void SetEventSearchAdapter(List<EventsSearchResponse.EventsListBean> eventsearchList) {
        adapterSearchEvent = new AdapterSearchEvent(getActivity(), eventsearchList, this);
        searchRv.setAdapter(adapterSearchEvent);
    }

    private void SetSearchAdapter(List<GeneralSearchResponse.SearchedDataList> searchListResponses) {
        searchAdapter = new EventSearchAdapter(getActivity(), searchListResponses, this);
        searchRv.setAdapter(searchAdapter);
    }

    @Override
    public void onApiFailure(String message) {
//        new BaseClass(getActivity()).showToast(message);
    }

    @Override
    public void onItemClick(int position, String type) {
        frameLayout.setVisibility(View.VISIBLE);
        hideSoftKeyboard(getActivity());
        if (type.equals("EVENT")) {

            EventDetailsFragment fragment = new EventDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("event_id", searchListResponses.get(position).getEventId());
//            bundle.putString("venue_id", searchListResponses.get(position).getVenueId());
            fragment.setArguments(bundle);
            getFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter,
                    R.animator.fade_out,
                    0,
                    R.animator.fragment_slide_right_exit)
                    .replace(R.id.search_fragment_cuntainer, fragment).
                    addToBackStack(EventDetailsFragment.class.getName()).commit();

            Toast.makeText(getContext(), position + type, Toast.LENGTH_SHORT).show();
        }
        if (type.equals("EVENTSEARCH")) {

            EventDetailsFragment fragment = new EventDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("event_id", eventsearchList.get(position).getEventId());
//            bundle.putString("venue_id", searchListResponses.get(position).getVenueId());
            fragment.setArguments(bundle);
            getFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter,
                    R.animator.fade_out,
                    0,
                    R.animator.fragment_slide_right_exit)
                    .replace(R.id.search_fragment_cuntainer, fragment).
                    addToBackStack(EventDetailsFragment.class.getName()).commit();
        } else if (type.equals("VENUE")) {

            RestaurantDetails fragment = new RestaurantDetails();
            Bundle bundle = new Bundle();
//          /  bundle.putInt("categoryId", categoryList.get(pos).getCategoryId());
            bundle.putString("venue_id", searchListResponses.get(position).getVenueId());
            fragment.setArguments(bundle);
            getFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter,
                    R.animator.fade_out,
                    0,
                    R.animator.fragment_slide_right_exit)
                    .replace(R.id.search_fragment_cuntainer, fragment).
                    addToBackStack(RestaurantDetails.class.getName()).commit();


            Toast.makeText(getContext(), position + type, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "no data available", Toast.LENGTH_SHORT).show();
        }

    }


}
