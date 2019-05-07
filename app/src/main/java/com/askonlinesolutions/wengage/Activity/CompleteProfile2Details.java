package com.askonlinesolutions.wengage.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.CustomizedProfileActivity;
import com.askonlinesolutions.wengage.Adapter.SubDetailAdapter;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Model.CategoryList;
import com.askonlinesolutions.wengage.Model.CompleteProfile2Model;
import com.askonlinesolutions.wengage.Model.EventCategoryModal;
import com.askonlinesolutions.wengage.Model.EventDBModal;
import com.askonlinesolutions.wengage.Model.Request.CategoryResponse;
import com.askonlinesolutions.wengage.Model.VenueDBModal;
import com.askonlinesolutions.wengage.Model.VenueSubCategoryListBean;
import com.askonlinesolutions.wengage.Networks.NetworkConstants;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.database.DatabaseHelper;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Progress;
import com.askonlinesolutions.wengage.utils.Utility;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mukesh.tinydb.TinyDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;


public class CompleteProfile2Details extends AppCompatActivity implements OnResponseInterface, View.OnClickListener {

    DetailAdapter detailAdapter;
    CompleteProfile2Model completeProfile2Model;
    RecyclerView main_recyclerView;
    Context context;
    private TextView next;
    private Progress progress;
    //    List<String> list;
    private TinyDB tinyDB;
    private DatabaseHelper db;
    private List<CategoryList> mainCategoryModalList = new ArrayList<>();
    private HashMap<Integer, List<Integer>> data_map = new HashMap<>();
    JsonArray jsonObjectForEvent;
    JsonArray jsonObjectForVenue;
    JsonObject jsonObjects;
    JsonArray jsonObjectForVenueCategory;

    JsonArray jsonObjectForEventCategory;
    //    JsonObject jsonObjectForEventCategorysss = new JsonObject();
//    JsonObject jsonObjectForVenueCategorysss = new JsonObject();
    private JsonArray finalJsonObject;
    private JsonObject UpdatedJsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile2_details);
        context = CompleteProfile2Details.this;
        tinyDB = new TinyDB(context);
        jsonObjects = new JsonObject();
        jsonObjectForEvent = new JsonArray();

        jsonObjectForEventCategory = new JsonArray();
        jsonObjectForVenue = new JsonArray();
        jsonObjectForVenueCategory = new JsonArray();
        UpdatedJsonObject = new JsonObject();
        finalJsonObject = new JsonArray();
        jsonObjectForEvent = new JsonArray();

        db = DatabaseHelper.getInstance(getApplicationContext());
        mainCategoryModalList = db.getCategoryList();

        init();
//        getMyIntent();
        setMyAdapter();

    }

    private void setMyAdapter() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(context);
        main_recyclerView.setLayoutManager(linearLayout);
        detailAdapter = new DetailAdapter();
        main_recyclerView.setAdapter(detailAdapter);
    }

    private void init() {
        progress = new Progress(this);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        main_recyclerView = findViewById(R.id.category_datail_list);
        next = findViewById(R.id.next);
        next.setOnClickListener(this);
    }

    public void onClickBack(View view) {
        finish();
    }


    private JSONObject jsonObject = new JSONObject();

    public void profile_details_next(View view) {
//        updateCat();
        if (Utility.isNetworkConnected(getApplicationContext())) {
            cat();
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getApplicationContext(), errorDialog, "Network error");
            errorDialog.show();
        }

//        startActivity(new Intent(CompleteProfile2Details.this, CustomizedProfileActivity.class));
    }

    private void updateCat() {
        Utility.showProgressDialog(context);
        Call<CategoryResponse> call = APIClient.getInstance().getApiInterface().updateCategory(tinyDB.getInt(Constants.USER_ID),
                jsonObject);
        new ResponseListner(this).getResponse(context, call);
        Log.d("TAG", "updateCat: " + call.request().url());
    }

    public static final MediaType MEDIA_TYPE = MediaType.parse("application/json");
    final OkHttpClient okHttpClient = new OkHttpClient();

    private void cat() {
        String url = "http://54.172.141.210/user/updateProfile/" + tinyDB.getInt(Constants.USER_ID);
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE, jsonObject + "");

        final Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.SECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .readTimeout(30000, TimeUnit.SECONDS)
                .build();

        Utility.showProgressDialog(CompleteProfile2Details.this);
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utility.hideDialog();
                       /* String msg = e.getMessage();
                        Utility.message(getApplicationContext(), "Connection error ");
                        Utility.hidepopup();*/
                        //  new BaseClass(CompleteProfile2Details.this).showToast(categoryResponse.getMessage())
                    }
                });
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Utility.hideDialog();
                if (response != null && response.body().toString().length() > 0) {
                    final String msg = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(msg);
                                if (jsonObject.getInt("status") == 1) {
                                    CategoryResponse categoryResponse = new Gson().fromJson(jsonObject.toString(), CategoryResponse.class);
                                    if (categoryResponse.getStatus() == 1) {
                                        new BaseClass(getApplicationContext()).showToast(categoryResponse.getMessage());
                                        startActivity(new Intent(CompleteProfile2Details.this, CustomizedProfileActivity.class));
                                    } else {
                                        new BaseClass(CompleteProfile2Details.this).showToast(categoryResponse.getMessage());
                                    }

                                } else {
                                    new BaseClass(CompleteProfile2Details.this).showToast(jsonObject.getString("message"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    Log.d("TAG", "onResponse: " + msg);

                }
            }

        });
    }


    @Override
    public void onApiResponse(Object response) {
        Utility.hideDialog();
        Log.d("TAG", "onApiResponse: " + new Gson().toJson(response));
        if (response != null) {
            try {
                if (response instanceof CategoryResponse) {
                    CategoryResponse categoryResponse = (CategoryResponse) response;
                    if (categoryResponse.getStatus() == 1) {
                        new BaseClass(getApplicationContext()).showToast(categoryResponse.getMessage());
                        startActivity(new Intent(CompleteProfile2Details.this, CustomizedProfileActivity.class));
                    } else {
                        new BaseClass(CompleteProfile2Details.this).showToast(categoryResponse.getMessage());
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, "onApiResponse: " + e.getMessage());
            }
        }
    }

    private String TAG = CompleteProfile2Details.class.getName();

    @Override
    public void onApiFailure(String message) {
        new BaseClass(CompleteProfile2Details.this).showToast(message);
    }

    @Override
    public void onClick(View view) {
        progress.show();
        if (Utility.isNetworkConnected(getApplicationContext())) {

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("eventCategory", jsonObjectForEvent);
            jsonObject.add("venueCategory", jsonObjectForVenue);
            Log.e("finalArray", jsonObject + "");
            Ion.with(this)
                    .load("PUT", NetworkConstants.PUT_UPDATE_VENUE + tinyDB.getInt(Constants.USER_ID))
                    .addHeader("Content-Type", "application/json")
                    .setJsonObjectBody(jsonObject).asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            progress.dismiss();
                            startActivity(new Intent(CompleteProfile2Details.this, CustomizedProfileActivity.class));
//                        Toast.makeText(getActivity(), "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getApplicationContext(), errorDialog, "Network error");
            errorDialog.show();
        }
//        startActivity(new Intent(CompleteProfile2Details.this, CustomizedProfileActivity.class));

    }

    class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {

        LinearLayoutManager linearLayoutManager;
        private SubDetailAdapter subDetailAdapter;


        @Override
        public DetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_detail_layout, parent, false);
            return new DetailAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(DetailAdapter.MyViewHolder holder, int position) {


            List<EventCategoryModal> eventModalList = new ArrayList<>();
            List<VenueSubCategoryListBean> venueModalList = new ArrayList<>();
            eventModalList = db.getEventList(String.valueOf(mainCategoryModalList.get(position).getCategoryId()));
            venueModalList = db.getVenueList(String.valueOf(mainCategoryModalList.get(position).getCategoryId()));
//            Picasso.with(getApplicationContext()).load(mainCategoryModalList.get(position).getIcon()).into(holder.image);
            Glide.with(getApplicationContext()).load(mainCategoryModalList.get(position).getIcon()).into(holder.image);

            List<EventDBModal> eventDBModalList = new ArrayList<>();
            List<VenueDBModal> venueDBModalList = new ArrayList<>();
            JsonArray jsonArrayEvent = new JsonArray();
            JsonObject jsonObject = new JsonObject();
            if (eventModalList.size() > 0) {
                for (int i = 0; i < eventModalList.size(); i++) {
                    jsonObjectForEventCategory = new JsonArray();
                    if (eventModalList.get(i).getIsSelected() == 1) {
                        jsonArrayEvent.add(eventModalList.get(i).getSubCategoryId());
                        EventDBModal eventDBModal = new EventDBModal();
                        eventDBModal.setSubCategoryId(eventModalList.get(i).getSubCategoryId());
                        eventDBModal.setSubCategoryName(eventModalList.get(i).getSubCategoryName());
                        eventDBModalList.add(eventDBModal);
                    }
                }
                jsonObject.add(String.valueOf(mainCategoryModalList.get(position).getCategoryId()), jsonArrayEvent);
                jsonObjectForEvent.add(jsonObject);
//                jsonObjectForEvent.add(String.valueOf(mainCategoryModalList.get(position).getCategoryId()), jsonArrayEvent);
                jsonObjectForEventCategory.add(jsonObjectForEvent);
//                jsonObjectForEventCategorysss.add("eventCategory", jsonObjectForEventCategory);
//                Log.e("Array", jsonObjectForEvent + "");
            }

            if (eventDBModalList.size() > 0) {
                holder.eventLayout.setVisibility(View.VISIBLE);
                linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                holder.sub_event_recycler_view.setLayoutManager(linearLayoutManager);
                EventAdapter eventAdapter = new EventAdapter(eventDBModalList);
                holder.sub_event_recycler_view.setAdapter(eventAdapter);
            } else {
                holder.eventLayout.setVisibility(View.GONE);
            }
            JsonArray jsonArrayVenue = new JsonArray();
            JsonObject jsonObjectVenue = new JsonObject();
            if (venueModalList.size() > 0) {
                holder.venueLayout.setVisibility(View.VISIBLE);
                holder.sub_category.setVisibility(View.VISIBLE);
                for (int i = 0; i < venueModalList.size(); i++) {
                    jsonObjectForVenueCategory = new JsonArray();
                    if (venueModalList.get(i).getIsSelected() == 1) {
                        jsonArrayVenue.add(venueModalList.get(i).getSubCategoryId());
                        VenueDBModal venueDBModal = new VenueDBModal();
                        venueDBModal.setSubCategoryId(venueModalList.get(i).getSubCategoryId());
                        venueDBModal.setSubCategoryName(venueModalList.get(i).getSubCategoryName());
                        venueDBModalList.add(venueDBModal);
                    }
                }
                jsonObjectVenue.add(String.valueOf(mainCategoryModalList.get(position).getCategoryId()), jsonArrayVenue);
                jsonObjectForVenue.add(jsonObjectVenue);
//                jsonObjectForVenue.add(String.valueOf(mainCategoryModalList.get(position).getCategoryId()), jsonArrayVenue);
//                jsonObjectForVenueCategory.add(jsonObjectForVenue);
//                jsonObjectForVenueCategorysss.add("venueCategory", jsonObjectForVenueCategory);
            }

            if (venueDBModalList.size() > 0) {
                holder.venueLayout.setVisibility(View.VISIBLE);
                linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                holder.sub_recyclerView.setLayoutManager(linearLayoutManager);
                VenueAdapter venueAdapter = new VenueAdapter(venueDBModalList);
                holder.sub_recyclerView.setAdapter(venueAdapter);
            } else {
                holder.venueLayout.setVisibility(View.GONE);
            }
            if (venueDBModalList.size() > 0 || eventDBModalList.size() > 0) {
                holder.category_name.setVisibility(View.VISIBLE);
                holder.image.setVisibility(View.VISIBLE);
                holder.category_name.setText(mainCategoryModalList.get(position).getCategoryName());
            } else {
                holder.category_name.setVisibility(View.GONE);
                holder.image.setVisibility(View.GONE);
            }
            finalJsonObject.add(jsonObjectForVenueCategory);
            finalJsonObject.add(jsonObjectForEventCategory);

            Log.e("FinalJsonObject:", finalJsonObject + "");

        }

        @Override
        public int getItemCount() {
            return mainCategoryModalList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            RecyclerView sub_recyclerView, sub_event_recycler_view;
            TextView category_name, event_category, sub_category;
            ImageView image;
            LinearLayout venueLayout, eventLayout;

            public MyViewHolder(View view) {
                super(view);

                venueLayout = view.findViewById(R.id.venueLayout);
                eventLayout = view.findViewById(R.id.eventLayout);
                sub_recyclerView = view.findViewById(R.id.sub_recycler_view);
                sub_event_recycler_view = view.findViewById(R.id.sub_event_recycler_view);
                category_name = view.findViewById(R.id.category_name);
                image = view.findViewById(R.id.category_image);
                event_category = view.findViewById(R.id.event_category);
                sub_category = view.findViewById(R.id.sub_category);


            }
        }
    }

    class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.MyViewHolder> {
        List<VenueDBModal> venueSubCategoryListBeans;

        public VenueAdapter(List<VenueDBModal> venueModalList) {
            this.venueSubCategoryListBeans = venueModalList;
        }

        @Override
        public VenueAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.subcategory_item_layout, parent, false);

            return new VenueAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(VenueAdapter.MyViewHolder holder, int position) {
            holder.sub_category_name.setText(venueSubCategoryListBeans.get(position).getSubCategoryName());
            //   holder.image.setImageResource();
           /* if (venueSubCategoryListBeans.get(position).getIsSelected() == 1) {
                holder.sub_category_name.setText(venueSubCategoryListBeans.get(position).getSubCategoryName());
            } else {
                holder.sub_category_name.setVisibility(View.GONE);
            }*/


        }

        @Override
        public int getItemCount() {
            return venueSubCategoryListBeans.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView sub_category_name;

            public MyViewHolder(View view) {
                super(view);

                sub_category_name = view.findViewById(R.id.sub_category_name);


            }
        }
    }

    class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
        List<EventDBModal> eventModalLists;

        public EventAdapter(List<EventDBModal> eventModalList) {
            this.eventModalLists = eventModalList;
        }

        @Override
        public EventAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.subcategory_item_layout, parent, false);

            return new EventAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(EventAdapter.MyViewHolder holder, int position) {
            holder.sub_category_name.setText(eventModalLists.get(position).getSubCategoryName());
            /*if (eventModalLists.get(position).getIsSelected() == 1) {

            } else {
                holder.sub_category_name.setVisibility(View.GONE);
            }*/
        }

        @Override
        public int getItemCount() {
            return eventModalLists.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView sub_category_name;

            public MyViewHolder(View view) {
                super(view);
                sub_category_name = view.findViewById(R.id.sub_category_name);


            }
        }
    }
}