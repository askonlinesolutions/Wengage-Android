package com.askonlinesolutions.wengage.Fragment.Sub;


import android.app.Dialog;
import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Adapter.AdapterYourInterest;
import com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.vo.InterestedEventsResponse;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.ApiInterface;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Progress;
import com.askonlinesolutions.wengage.utils.Utility;
import com.mukesh.tinydb.TinyDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfluencePickFragment extends Fragment implements AdapterYourInterest.Interface_AdapterYourInterest, OnResponseInterface {


    private static final String TAG = InfluencePickFragment.class.getName();
    private TinyDB tinyDB;
    String type;
    private int user_id;

    private AdapterYourInterest adapterYourInterest;
    private RecyclerView rv;
    private TextView noData;

    private int pageCount;
    private Progress progress;
    private Dialog errorDialog;

    private LinearLayout layout_top_switch;
    private SwitchCompat sw;
    int page = 1, subcategory_page = 1, reachMax;
    private List<InterestedEventsResponse.EventsListResponse> eventsListResponses = new ArrayList<>();


    public InfluencePickFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_influence_pick, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tinyDB = new TinyDB(getContext());
        type = tinyDB.getString("bottom_click");
        user_id = tinyDB.getInt(Constants.USER_ID);
        init();
        callInfluencerPicFragmentApi(page, user_id);


    }


    private void init() {

        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        rv = getView().findViewById(R.id.influence_pick_recycler);
        noData = getView().findViewById(R.id.nodata_tv);
        LinearLayoutManager layoutManagaer
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManagaer);


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        tinyDB = new TinyDB(getContext());
//        type = tinyDB.getString("bottom_click");
//        user_id = tinyDB.getInt(Constants.USER_ID);
//
//        callInfluencerPicFragmentApi(page, user_id);
    }


    private void callInfluencerPicFragmentApi(int page, int user_id) {
        progress.show();
//        user_id = 165;
        ApiInterface apiService = new APIClient().getClient().create(ApiInterface.class);
        Call<InterestedEventsResponse> call = apiService.getInfluencerPick(page, user_id);
        new ResponseListner(this).getResponse(getContext(), call);
        Log.e("pickURL", call.request().url().toString());

    }

    @Override
    public void onApiResponse(Object response) {
        progress.dismiss();
//        if (page == 0) {
//            eventsListResponses.clear();
//        }

        Log.d("MyResponse", response.toString());
        if (response != null) {
            try {
                if (response instanceof InterestedEventsResponse) {
                    InterestedEventsResponse eventsResponse = (InterestedEventsResponse) response;
                    if (eventsResponse.getStatus() == 1) {
                        reachMax = eventsResponse.getTotalPages();
                        if (eventsResponse.getEventsList() != null)
                            if (eventsResponse.getEventsList().size() == 0) {
//                                Toast.makeText(getActivity(), "You don't have any event on this section", Toast.LENGTH_SHORT).show();
                            } else {
                                eventsListResponses.addAll(eventsResponse.getEventsList());
                                Log.d(TAG, "onApiResponse: " + eventsListResponses.size());
                                SetInfluencerPickAdapter(eventsListResponses);
                            }

                    } else {

                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, eventsResponse.getMessage());
                        errorDialog.show();
                    }
                } else {

                }
            } catch (Exception e) {
                Log.d(TAG, "onApiResponse: " + e.getMessage());
            }
        }

    }

    @Override
    public void onApiFailure(String message) {
        progress.dismiss();
        if (!message.equals("") && message!=null){
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, message);
            errorDialog.show();
        }
    }

    private void SetInfluencerPickAdapter(List<InterestedEventsResponse.EventsListResponse> eventsListResponses) {

        adapterYourInterest = new AdapterYourInterest(getContext(), eventsListResponses, this);
        rv.setAdapter(adapterYourInterest);
    }

    @Override
    public void lastIndex(int pos) {
//        page++;
//        if (page < reachMax)
//            callInfluencerPicFragmentApi(page, user_id);
    }


    @Override
    public void viewDetails(int pos) {

    }


    @Override
    public void method_AdapterYourInterest(int position, String type) {
        if (type.equals("bookmarks")) {
            Toast.makeText(getContext(), type, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), type, Toast.LENGTH_SHORT).show();
        }

        if (type.equals("interested")) {
            Toast.makeText(getContext(), type, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), type, Toast.LENGTH_SHORT).show();
        }

        if (type.equals("check")) {
            Toast.makeText(getContext(), type, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), type, Toast.LENGTH_SHORT).show();
        }

    }


}
