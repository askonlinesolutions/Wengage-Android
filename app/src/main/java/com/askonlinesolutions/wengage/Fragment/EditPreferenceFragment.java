package com.askonlinesolutions.wengage.Fragment;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Fragment.Main.profile.UserProfile;
import com.askonlinesolutions.wengage.Model.CategoryList;
import com.askonlinesolutions.wengage.Model.EventCategoryBean;
import com.askonlinesolutions.wengage.Model.EventCategoryModal;
import com.askonlinesolutions.wengage.Model.VenueCategoryBean;
import com.askonlinesolutions.wengage.Model.VenueSubCategoryListBean;
import com.askonlinesolutions.wengage.Networks.NetworkConstants;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.database.DatabaseHelper;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Progress;
import com.askonlinesolutions.wengage.utils.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mukesh.tinydb.TinyDB;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditPreferenceFragment extends Fragment implements View.OnClickListener {
    private TextView venueTV, eventTv;
    private RecyclerView recyclerView, eventRecyclerView;
    private LinearLayout home3_cross;
    private TextView updateForVenue, updateForEvent;
    private List<VenueSubCategoryListBean> venueListsDatabase;
    private List<EventCategoryModal> eventListsDatabase;
    JsonObject jsonObjectForVenue;
    JsonArray jsonObjectForVenueCategory;
    private Progress progress;
    JsonObject jsonObjectForEvent;
    JsonArray jsonObjectForEventCategory;
    JsonObject jsonObjectForEventCategorysss = new JsonObject();
    private DatabaseHelper db;
    JsonObject jsonObjectForVenueCategorysss = new JsonObject();
    private List<CategoryList> mainCategoryModalList = new ArrayList<>();
    private UserProfile userProfile = new UserProfile();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.edit_preference_fragment, container, false);
        db = DatabaseHelper.getInstance(getActivity());
        mainCategoryModalList = db.getCategoryList();

        jsonObjectForEvent = new JsonObject();

        jsonObjectForEventCategory = new JsonArray();
        jsonObjectForVenue = new JsonObject();
        jsonObjectForVenueCategory = new JsonArray();
        TinyDB tinyDB = new TinyDB(getActivity());
        userProfile = new Gson().fromJson(tinyDB.getString("edit_preference_data"), UserProfile.class);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        recyclerView = view.findViewById(R.id.recyclerView);
        eventRecyclerView = view.findViewById(R.id.eventRecyclerView);
        home3_cross = view.findViewById(R.id.home3_cross);
        venueTV = view.findViewById(R.id.venueTV);
        updateForVenue = view.findViewById(R.id.updateForVenue);
        updateForEvent = view.findViewById(R.id.updateForEvent);
        eventTv = view.findViewById(R.id.eventTv);
        venueTV.setOnClickListener(this);
        eventTv.setOnClickListener(this);
        updateForVenue.setOnClickListener(this);
        updateForEvent.setOnClickListener(this);
        home3_cross.setOnClickListener(this);
        venueTV.setBackground(getResources().getDrawable(R.drawable.edit_drawable));
        venueTV.setTextColor(getResources().getColor(R.color.text_color));
        eventTv.setBackground(getResources().getDrawable(R.drawable.unselect_drawable));
        eventTv.setTextColor(getResources().getColor(R.color.text_color));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        EditPreferenceAdapter dayWiseAdapter = new EditPreferenceAdapter();
        recyclerView.setAdapter(dayWiseAdapter);
        recyclerView.setVisibility(View.VISIBLE);
        eventRecyclerView.setVisibility(View.GONE);

        updateForVenue.setVisibility(View.VISIBLE);
        updateForEvent.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updateForEvent:
                for (int i = 0; i < userProfile.getUserData().getEventCategory().size(); i++) {
                    JsonArray jsonArrayEvent = new JsonArray();
                    for (int j = 0; j < userProfile.getUserData().getEventCategory().get(i).getSubCategory().size(); j++) {
                        JsonArray jsonObjectForEventCategory = new JsonArray();
                        if (userProfile.getUserData().getEventCategory().get(i).getSubCategory().get(j).getSelected() == 1) {
                            jsonArrayEvent.add(String.valueOf(userProfile.getUserData().getEventCategory().get(i)
                                    .getSubCategory().get(j).getSubCategoryId()));
                        }
                    }
                    jsonObjectForEvent.add(String.valueOf(userProfile.getUserData().getEventCategory().get(i).getCategoryId()), jsonArrayEvent);
                }

                jsonObjectForEventCategory.add(jsonObjectForEvent);
                jsonObjectForEventCategorysss.add("eventCategory", jsonObjectForEventCategory);
                if (Utility.isNetworkConnected(getActivity())) {
                    callUpdateForEventApi();
                } else {
                    Dialog errorDialog = null;
                    errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
                    errorDialog.show();
                }

                break;
            case R.id.updateForVenue:
                for (int i = 0; i < userProfile.getUserData().getVenueCategory().size(); i++) {
                    JsonArray jsonArrayVenue = new JsonArray();
                    JsonArray jsonObjectForVenueCategory = new JsonArray();
                    for (int j = 0; j < userProfile.getUserData().getVenueCategory().get(i).getSubCategory().size(); j++) {
                        jsonObjectForVenueCategory = new JsonArray();
                        if (userProfile.getUserData().getVenueCategory().get(i).getSubCategory().get(j).getSelected() == 1) {
                            jsonArrayVenue.add(String.valueOf(userProfile.getUserData().getVenueCategory().get(i)
                                    .getSubCategory().get(j).getSubCategoryId()));
                        }


                    }
                    jsonObjectForVenue.add(String.valueOf(userProfile.getUserData().getVenueCategory().get(i).getCategoryId()), jsonArrayVenue);

                }
                jsonObjectForVenueCategory.add(jsonObjectForVenue);
                jsonObjectForVenueCategorysss.add("venueCategory", jsonObjectForVenueCategory);
                if (Utility.isNetworkConnected(getActivity())) {
                    callUpdateForVenueApi();
                } else {
                    Dialog errorDialog = null;
                    errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
                    errorDialog.show();
                }

                break;
            case R.id.venueTV:
                updateForVenue.setVisibility(View.VISIBLE);
                updateForEvent.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                eventRecyclerView.setVisibility(View.GONE);
                venueTV.setBackground(getResources().getDrawable(R.drawable.edit_drawable));
                venueTV.setTextColor(getResources().getColor(R.color.text_color));
                eventTv.setBackground(getResources().getDrawable(R.drawable.unselect_drawable));
                eventTv.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.eventTv:

                updateForVenue.setVisibility(View.GONE);
                updateForEvent.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                eventRecyclerView.setVisibility(View.VISIBLE);
                venueTV.setBackground(getResources().getDrawable(R.drawable.unselect_drawable));
                venueTV.setTextColor(getResources().getColor(R.color.text_color));
                eventTv.setBackground(getResources().getDrawable(R.drawable.edit_drawable));
                eventTv.setTextColor(getResources().getColor(R.color.text_color));

                eventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.VERTICAL, false));
                EditEventPreferenceAdapter eventWiseAdapter = new EditEventPreferenceAdapter();
                eventRecyclerView.setAdapter(eventWiseAdapter);
                break;
            case R.id.home3_cross:
                getActivity().onBackPressed();
                break;
        }
    }

    private void callUpdateForEventApi() {
        TinyDB tinyDB = new TinyDB(getActivity());
        progress.show();
        Ion.with(this)
                .load("PUT", NetworkConstants.PUT_UPDATE_VENUE + tinyDB.getInt(Constants.USER_ID))
                .setJsonObjectBody(jsonObjectForEventCategorysss).asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        progress.dismiss();
                        getActivity().onBackPressed();
                    }
                });
    }

    private void callUpdateForVenueApi() {

        TinyDB tinyDB = new TinyDB(getActivity());
        progress.show();
        Ion.with(this)
                .load("PUT", NetworkConstants.PUT_UPDATE_VENUE + tinyDB.getInt(Constants.USER_ID))
                .setJsonObjectBody(jsonObjectForVenueCategorysss).asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        progress.dismiss();
                        getActivity().onBackPressed();
//                        Toast.makeText(getActivity(), "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    class EditPreferenceAdapter extends RecyclerView.Adapter<EditPreferenceAdapter.ViewHolder> {

        @Override
        public EditPreferenceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.edit_preference_adapter, parent, false);
            return new EditPreferenceAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final EditPreferenceAdapter.ViewHolder holder, final int position) {
            holder.category_name.setText(userProfile.getUserData().getVenueCategory().get(position).getCategoryName());
            Picasso.with(getActivity()).load(userProfile.getUserData().getVenueCategory().get(position).getIcon()).into(holder.category_image);
            List<EventCategoryModal> eventModalList = new ArrayList<>();
            List<VenueSubCategoryListBean> venueModalList = new ArrayList<>();

//            venueListsDatabase = new ArrayList<>();
//            venueListsDatabase = db.getVenueList(String.valueOf(mainCategoryModalList.get(position).getCategoryId()));
            if (userProfile.getUserData().getVenueCategory().size() > 0) {
                holder.categoryLayout.setVisibility(View.VISIBLE);
                holder.sub_recycler_view.setVisibility(View.VISIBLE);
                holder.sub_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL, false));
                EditVenueAdapter dayWiseAdapter = new EditVenueAdapter(userProfile.getUserData().getVenueCategory().get(position).getSubCategory());
                holder.sub_recycler_view.setAdapter(dayWiseAdapter);
            } else {
                holder.categoryLayout.setVisibility(View.GONE);
                holder.sub_recycler_view.setVisibility(View.GONE);
            }


        }


        @Override
        public int getItemCount() {
            return userProfile.getUserData().getVenueCategory().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private RecyclerView sub_recycler_view;
            private TextView category_name;
            private ImageView category_image;
            private LinearLayout categoryLayout;

            public ViewHolder(View itemView) {

                super(itemView);
                sub_recycler_view = itemView.findViewById(R.id.sub_recycler_view);
                category_name = itemView.findViewById(R.id.category_name);
                categoryLayout = itemView.findViewById(R.id.categoryLayout);
                category_image = itemView.findViewById(R.id.category_image);

            }
        }
    }

    class EditEventPreferenceAdapter extends RecyclerView.Adapter<EditEventPreferenceAdapter.ViewHolder> {

        @Override
        public EditEventPreferenceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.edit_preference_adapter, parent, false);
            return new EditEventPreferenceAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final EditEventPreferenceAdapter.ViewHolder holder, final int position) {
            holder.category_name.setText(userProfile.getUserData().getEventCategory().get(position).getCategoryName());
            Picasso.with(getActivity()).load(userProfile.getUserData().getEventCategory().get(position).getIcon()).into(holder.category_image);

//            eventListsDatabase = new ArrayList<>();
//            eventListsDatabase = db.getEventList(String.valueOf(mainCategoryModalList.get(position).getCategoryId()));
            if (userProfile.getUserData().getEventCategory().size() > 0) {
                holder.categoryLayout.setVisibility(View.VISIBLE);
                holder.sub_recycler_view.setVisibility(View.VISIBLE);
                holder.sub_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL, false));
                EditEventAdapter eventWiseAdapter = new EditEventAdapter(userProfile.getUserData().getEventCategory().get(position).getSubCategory());
                holder.sub_recycler_view.setAdapter(eventWiseAdapter);
            } else {
                holder.categoryLayout.setVisibility(View.GONE);
                holder.sub_recycler_view.setVisibility(View.GONE);
            }

        }


        @Override
        public int getItemCount() {
            return userProfile.getUserData().getEventCategory().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private RecyclerView sub_recycler_view;
            private TextView category_name;
            private ImageView category_image;
            private LinearLayout categoryLayout;

            public ViewHolder(View itemView) {

                super(itemView);
                sub_recycler_view = itemView.findViewById(R.id.sub_recycler_view);
                category_image = itemView.findViewById(R.id.category_image);
                category_name = itemView.findViewById(R.id.category_name);
                categoryLayout = itemView.findViewById(R.id.categoryLayout);

            }
        }
    }

    class EditEventAdapter extends RecyclerView.Adapter<EditEventAdapter.ViewHolder> {
        List<EventCategoryBean.SubCategoryBean> eventSubCategoryListBeans;

        public EditEventAdapter(List<EventCategoryBean.SubCategoryBean> venueModalList) {
            this.eventSubCategoryListBeans = venueModalList;
        }

        @Override
        public EditEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.edit_venue_adapter, parent, false);
            return new EditEventAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final EditEventAdapter.ViewHolder holder, final int position) {
            final DatabaseHelper db = DatabaseHelper.getInstance(getActivity());
            if (eventSubCategoryListBeans.get(position).getSubCategoryName() != null) {
                holder.textView.setText(eventSubCategoryListBeans.get(position).getSubCategoryName());
            }
            if (eventSubCategoryListBeans.get(position).getSelected() == 1) {
                holder.item_layout.setBackground(getActivity().getResources().getDrawable(R.drawable.shape_filled_pink));
                holder.textView.setTextColor(getActivity().getResources().getColor(R.color.white));
            } else {
                holder.item_layout.setBackground(getActivity().getResources().getDrawable(R.drawable.shape_border_grey));
                holder.textView.setTextColor(getActivity().getResources().getColor(R.color.black));
            }
            holder.item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (eventSubCategoryListBeans.get(position).getSelected() == 1) {
                        eventSubCategoryListBeans.get(position).setSelected(0);
                        db.updateEvent(0, eventSubCategoryListBeans.get(position).getSubCategoryId());

                        holder.item_layout.setBackground(getActivity().getResources().getDrawable(R.drawable.shape_border_grey));
                        holder.textView.setTextColor(getActivity().getResources().getColor(R.color.black));
                    } else {
                        db.updateEvent(1, eventSubCategoryListBeans.get(position).getSubCategoryId());
                        eventSubCategoryListBeans.get(position).setSelected(1);
                        holder.item_layout.setBackground(getActivity().getResources().getDrawable(R.drawable.shape_filled_pink));
                        holder.textView.setTextColor(getActivity().getResources().getColor(R.color.white));

                    }

                }
            });

        }


        @Override
        public int getItemCount() {
            return eventSubCategoryListBeans.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            LinearLayout item_layout;

            public ViewHolder(View itemView) {

                super(itemView);

                textView = itemView.findViewById(R.id.sub_category_name);
                item_layout = itemView.findViewById(R.id.item_layout);

            }
        }
    }

    class EditVenueAdapter extends RecyclerView.Adapter<EditVenueAdapter.ViewHolder> {
        List<VenueCategoryBean.SubCategoryBeanX> venueSubCategoryListBeans;

        public EditVenueAdapter(List<VenueCategoryBean.SubCategoryBeanX> venueModalList) {
            this.venueSubCategoryListBeans = venueModalList;
        }

        @Override
        public EditVenueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.edit_venue_adapter, parent, false);
            return new EditVenueAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final EditVenueAdapter.ViewHolder holder, final int position) {
            final DatabaseHelper db = DatabaseHelper.getInstance(getActivity());
            if (venueSubCategoryListBeans.get(position).getSubCategoryName() != null) {
                holder.textView.setText(venueSubCategoryListBeans.get(position).getSubCategoryName());
            }
            if (venueSubCategoryListBeans.get(position).getSelected() == 1) {
                holder.item_layout.setBackground(getActivity().getResources().getDrawable(R.drawable.shape_filled_pink));
                holder.textView.setTextColor(getActivity().getResources().getColor(R.color.white));
            } else {
                holder.item_layout.setBackground(getActivity().getResources().getDrawable(R.drawable.shape_border_grey));
                holder.textView.setTextColor(getActivity().getResources().getColor(R.color.black));
            }
            holder.item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (venueSubCategoryListBeans.get(position).getSelected() == 1) {
                        venueSubCategoryListBeans.get(position).setSelected(0);
                        db.updateVenues(0, venueSubCategoryListBeans.get(position).getSubCategoryId());

                        holder.item_layout.setBackground(getActivity().getResources().getDrawable(R.drawable.shape_border_grey));
                        holder.textView.setTextColor(getActivity().getResources().getColor(R.color.black));
                    } else {
                        db.updateVenues(1, venueSubCategoryListBeans.get(position).getSubCategoryId());
                        venueSubCategoryListBeans.get(position).setSelected(1);
                        holder.item_layout.setBackground(getActivity().getResources().getDrawable(R.drawable.shape_filled_pink));
                        holder.textView.setTextColor(getActivity().getResources().getColor(R.color.white));

                    }

                }
            });

        }


        @Override
        public int getItemCount() {
            return venueSubCategoryListBeans.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            LinearLayout item_layout;

            ViewHolder(View itemView) {

                super(itemView);

                textView = itemView.findViewById(R.id.sub_category_name);
                item_layout = itemView.findViewById(R.id.item_layout);

            }
        }
    }

}
