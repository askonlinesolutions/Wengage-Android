package com.askonlinesolutions.wengage.Activity.Sub;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Activity.Main.MainActivity;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.dashboard_adapters.DashboardChatsAdapter;
import com.askonlinesolutions.wengage.Adapter.SearchAdapter;
import com.askonlinesolutions.wengage.Fragment.Main.ChatFragment;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventSubCatFragment;
import com.askonlinesolutions.wengage.Fragment.Main.profile.ProfileHome;
import com.askonlinesolutions.wengage.Fragment.Main.venue.details.RestaurantDetails;
import com.askonlinesolutions.wengage.Fragment.Main.vo.EventsSearchResponse;
import com.askonlinesolutions.wengage.Fragment.Sub.Filter;
import com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.vo.GeneralSearchResponse;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;

import android.app.Fragment;

import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.FCViewPager;
import com.google.gson.Gson;
import com.mukesh.tinydb.TinyDB;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static com.askonlinesolutions.wengage.Helper.BaseClass.hideSoftKeyboard;

public class Search extends AppCompatActivity implements View.OnClickListener, OnResponseInterface, EventSearchAdapter.OnItemClickListener {


    private ImageView search_cross, searchBarIv;
    private EventSearchAdapter searchAdapter;
    private AdapterSearchEvent adapterSearchEvent;
    private RecyclerView searchRv;
    private Gson gson = new Gson();
    private FrameLayout frameLayout;
    private String keyword;
    private AutoCompleteTextView search_main;
    private String TAG = Search.class.getName();
    private List<GeneralSearchResponse.SearchedDataList> searchListResponses = new ArrayList<>();
    private List<EventsSearchResponse.EventsListBean> eventsearchList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();

        handleIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
        }
        inIt();
        Intent intent1 = getIntent();
        String name = intent1.getStringExtra("name");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }

    @Override
    public boolean onSearchRequested() {
        return super.onSearchRequested();
    }

    private void inIt() {
        search_cross = (ImageView) findViewById(R.id.search_cross);
        searchBarIv = (ImageView) findViewById(R.id.search_bar);
        searchRv = (RecyclerView) findViewById(R.id.search_rv);
        frameLayout = findViewById(R.id.search_fragment_cuntainer);
        search_cross.setOnClickListener(this);
        search_main = (AutoCompleteTextView) findViewById(R.id.search_main);
        search_main.setOnClickListener(this);

        Intent intent1 = getIntent();
        final String name = intent1.getStringExtra("name");


        search_main.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (name != null && name.equals("inTheKnow")) {
//                    if (name.equals("inTheKnow")) {
                    callEventSearchApi();
                } else {
                    callSearchApi();
//                    }
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_cross:
                search_main.setText("");
                hideSoftKeyboard(this);
                break;
            case R.id.search_main:

                search_main.showDropDown();
                search_main.enoughToFilter();
                search_main.requestFocus();
                break;
        }

    }

    private void setUpSearchRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        searchRv.setLayoutManager(layoutManager);

    }

    private void callEventSearchApi() {

        keyword = search_main.getText().toString().trim();
        Call<EventsSearchResponse> call = APIClient.getInstance().getApiInterface().getEventSearchesList(keyword);
        new ResponseListner(this).getResponse(getApplicationContext(), call);
        Log.d("TAG", "EventSearchUrl: " + call.request().url());

    }

    private void callSearchApi() {
        keyword = search_main.getText().toString().trim();
        Call<GeneralSearchResponse> call = APIClient.getInstance().getApiInterface().getSearchesList(keyword,1);
        new ResponseListner(this).getResponse(getApplicationContext(), call);
        Log.d("TAG", "SEARCHUrl: " + call.request().url());
    }

    @Override
    public void onApiResponse(Object response) {
        if (response != null) {
            if (response instanceof GeneralSearchResponse) {
                if (searchListResponses != null) {
                    searchListResponses.clear();
                }

                GeneralSearchResponse eventsResponse = (GeneralSearchResponse) response;
                if (eventsResponse.getStatus() == 1) {
                    searchRv.setVisibility(View.VISIBLE);
                    if (eventsResponse.getSearchedData() != null) {
                        if (searchListResponses != null) {
                            searchListResponses.clear();
                        }
                        searchListResponses.addAll(eventsResponse.getSearchedData());
                        SetSearchAdapter(searchListResponses);
//                        searchAdapter.notifyDataSetChanged();
                    }
                } else {
                    new BaseClass(this).showToast(eventsResponse.getMessage());
                    searchRv.setVisibility(View.GONE);
//                    searchAdapter.notifyDataSetChanged();
                }

            } else if (response instanceof EventsSearchResponse) {
                if (eventsearchList != null) {
                    eventsearchList.clear();
                }

                EventsSearchResponse searchResponse = (EventsSearchResponse) response;
                if (searchResponse.getStatus() == 1) {
                    searchRv.setVisibility(View.VISIBLE);
                    if (searchResponse.getEventsList() != null) {
                        if (eventsearchList != null) {
                            eventsearchList.clear();
                        }
                        eventsearchList.addAll(searchResponse.getEventsList());
                        SetEventSearchAdapter(eventsearchList);
//                        searchAdapter.notifyDataSetChanged();
                    }
                } else {
                    new BaseClass(getApplicationContext()).showToast(searchResponse.getMessage());
                    searchRv.setVisibility(View.GONE);
//                    searchAdapter.notifyDataSetChanged();
                }

            }


        }
    }

    //
    private void SetEventSearchAdapter(List<EventsSearchResponse.EventsListBean> eventsearchList) {
        adapterSearchEvent = new AdapterSearchEvent(getApplicationContext(), eventsearchList, this);
        searchRv.setAdapter(adapterSearchEvent);
    }

    private void SetSearchAdapter(List<GeneralSearchResponse.SearchedDataList> searchListResponses) {
        searchAdapter = new EventSearchAdapter(getApplicationContext(), searchListResponses, this);
        searchRv.setAdapter(searchAdapter);
    }

    @Override
    public void onApiFailure(String message) {
        new BaseClass(getApplicationContext()).showToast(message);
    }

    @Override
    public void onItemClick(int position, String type) {
        frameLayout.setVisibility(View.VISIBLE);
        if (type.equals("EVENT")) {

//            RestaurantDetails fragment = new RestaurantDetails();
//            Bundle bundle = new Bundle();
//            bundle.putString("event_Id", searchListResponses.get(position).getEventId());
////            bundle.putString("venue_id", searchListResponses.get(position).getVenueId());
//            fragment.setArguments(bundle);
//            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter,
//                    R.animator.fade_out,
//                    0,
//                    R.animator.fragment_slide_right_exit)
//                    .replace(R.id.search_fragment_cuntainer, fragment).
//                    addToBackStack(EventSubCatFragment.class.getName()).commit();

            Toast.makeText(getApplicationContext(), position + type, Toast.LENGTH_SHORT).show();
        } else if (type.equals("VENUE")) {

            RestaurantDetails fragment = new RestaurantDetails();
            Bundle bundle = new Bundle();
//          /  bundle.putInt("categoryId", categoryList.get(pos).getCategoryId());
            bundle.putString("venue_id", searchListResponses.get(position).getVenueId());
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter,
                    R.animator.fade_out,
                    0,
                    R.animator.fragment_slide_right_exit)
                    .replace(R.id.search_fragment_cuntainer, fragment).
                    addToBackStack(EventSubCatFragment.class.getName()).commit();


            Toast.makeText(getApplicationContext(), position + type, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "no data available", Toast.LENGTH_SHORT).show();
        }

    }
}
