package com.askonlinesolutions.wengage.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Model.CategoryList;
import com.askonlinesolutions.wengage.Model.CompleteProfile2Model;
import com.askonlinesolutions.wengage.Model.EventCategoryModal;
import com.askonlinesolutions.wengage.Model.ItemClickStatusModel;
import com.askonlinesolutions.wengage.Model.Response.CategoryResponse;
import com.askonlinesolutions.wengage.Model.Response.SubCategoryResponse;
import com.askonlinesolutions.wengage.Model.VenueSubCategoryListBean;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.database.DatabaseHelper;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Utility;
import com.askonlinesolutions.wengage.wengageInterface.CategoryItemClickListner;
import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mukesh.tinydb.TinyDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class CompleteProfile2 extends AppCompatActivity implements View.OnClickListener, OnResponseInterface, CategoryItemClickListner {
    RecyclerView category_recycler_view, subcategory_recycler_view, event_recycler;
    ImageView profile_pic;
    private TextView yes, no, eventText;
    private View view1;
    private SubCategoryResponse subCategoryResponse;
    ProgressDialog progressDialog;
    CategoryAdapter categoryAdapter;
    private int categoryId = 0;
    TextView next;
    private List<CategoryList> categoryListsDatabase;
    private EventCategoryItemAdapter subCategoryItemAdapter;
    private List<VenueSubCategoryListBean> venueListsDatabase;
    private List<EventCategoryModal> eventListDatabase;
    LinearLayoutManager linearLayoutManager, sub_linearLayoutManager;
    FlexboxLayoutManager flexboxLayoutManager;
    Context context;
    TinyDB tinyDB;
    private CategoryResponse categoryResponse;
    int page_num = 1;
    private RelativeLayout continueLayout;
    CompleteProfile2Model completeProfile2Model = new CompleteProfile2Model();
    List<ItemClickStatusModel> itemClickStatusModelList = new ArrayList<>();
    List<String> list = new ArrayList<>();
    List<Integer> integers = new ArrayList<>();
    JsonArray jsonArray;
    JsonObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile2);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            BaseClass.setLightStatusBar(getWindow().getDecorView(), CompleteProfile2.this);
        }

        context = CompleteProfile2.this;
        init();

        getAllCategory();

    }

    private void getAllCategory() {
        if (Utility.isNetworkConnected(getApplicationContext())) {
            Call<CategoryResponse> call = APIClient.getInstance().getApiInterface().getAllCategories("");
            new ResponseListner(this).getResponse(context, call);
            progressDialog.show();
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getApplicationContext(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    public void onClickBack(View view) {
        finish();
    }

    private void init() {
        continueLayout = findViewById(R.id.continueLayout);
        category_recycler_view = findViewById(R.id.category_recycler);
        no = findViewById(R.id.no);
        yes = findViewById(R.id.yes);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        eventText = findViewById(R.id.eventText);
        event_recycler = findViewById(R.id.event_recycler);
        subcategory_recycler_view = findViewById(R.id.subCategory_recycler);
        profile_pic = findViewById(R.id.profile_image);
        view1 = findViewById(R.id.view1);
        view1.setVisibility(View.GONE);
        tinyDB = new TinyDB(getApplicationContext());
        progressDialog = new BaseClass(context).creatProgressDialog();
        next = findViewById(R.id.next);
        next.setOnClickListener(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        continueLayout.setVisibility(View.GONE);
        flexboxLayoutManager = new FlexboxLayoutManager(getApplicationContext());
        // Set flex direction.
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);

        sub_linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        new BaseClass(context).creatProgressDialog();
        continueLayout.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.next) {

            callNextActivityMethod();

        } else if (view.getId() == R.id.yes) {

            tinyDB.putInt(Constants.CATEGORY_COMPLETE, 1);

            completeProfile2Model.getData_map_with_name().size();
            continueLayout.setVisibility(View.GONE);
            Log.d("TAG", "Rakhi: " + new Gson().toJson(subCategoryResponse));
            Intent intent = new Intent(CompleteProfile2.this, CompleteProfile2Details.class);
            startActivity(intent);
        } else if (view.getId() == R.id.no) {

            continueLayout.setVisibility(View.GONE);
        }
    }

    private void callNextActivityMethod() {
        DatabaseHelper db;
        db = DatabaseHelper.getInstance(getApplicationContext());
        List<CategoryList> mainCategoryModalList = new ArrayList<>();
//        Toast.makeText(context, categoryListsDatabase + "", Toast.LENGTH_SHORT).show();
        List<EventCategoryModal> eventModalList = new ArrayList<>();
        List<VenueSubCategoryListBean> venueModalList = new ArrayList<>();
        mainCategoryModalList = db.getCategoryList();
        ArrayList<String> stringsEvent = new ArrayList<>();
        ArrayList<String> stringsVenue = new ArrayList<>();
        JsonObject jsonObjectEvent = new JsonObject();
        JsonObject jsonObjectVenue = new JsonObject();

        boolean isVenue = false, isEvent = false, isAllEmptyEvent = true, isAllEmptyVenue = true;
        for (int i = 0; i < mainCategoryModalList.size(); i++) {
            JsonArray jsonArrayEvent = new JsonArray();
            JsonArray jsonArrayVenue = new JsonArray();
            eventModalList = db.getEventList(String.valueOf(mainCategoryModalList.get(i).getCategoryId()));
            venueModalList = db.getVenueList(String.valueOf(mainCategoryModalList.get(i).getCategoryId()));
            for (int j = 0; j < eventModalList.size(); j++) {
                if (eventModalList.get(j).getIsSelected() == 1) {
                    jsonArrayEvent.add(eventModalList.get(j).getSubCategoryId());
                }
            }
            for (int j = 0; j < venueModalList.size(); j++) {
                if (venueModalList.get(j).getIsSelected() == 1) {
                    jsonArrayVenue.add(venueModalList.get(j).getSubCategoryId());
                }
            }
            jsonObjectVenue.add(String.valueOf(mainCategoryModalList.get(i).getCategoryId()), jsonArrayVenue);
            jsonObjectEvent.add(String.valueOf(mainCategoryModalList.get(i).getCategoryId()), jsonArrayEvent);
            if (((JsonArray) jsonObjectEvent.get(String.valueOf(mainCategoryModalList.get(i).getCategoryId()))).size() == 0 &&
                    ((JsonArray) jsonObjectVenue.get(String.valueOf(mainCategoryModalList.get(i).getCategoryId()))).size() == 0) {
                isVenue = true;
            }
        }
        for (int k = 0; k < jsonObjectEvent.size(); k++) {
            if (((JsonArray) jsonObjectEvent.get(String.valueOf(mainCategoryModalList.get(k).getCategoryId()))).size() > 0) {
                for (int l = 0; l < ((JsonArray) jsonObjectEvent.get(String.valueOf(mainCategoryModalList.get(k).getCategoryId()))).size(); l++) {
                    if (((JsonArray) jsonObjectEvent.get(String.valueOf(mainCategoryModalList.get(l).getCategoryId()))).size() > 0) {
                        isAllEmptyEvent = false;
                    }
                }
            }
            if (((JsonArray) jsonObjectVenue.get(String.valueOf(mainCategoryModalList.get(k).getCategoryId()))).size() > 0) {
                for (int l = 0; l < ((JsonArray) jsonObjectVenue.get(String.valueOf(mainCategoryModalList.get(k).getCategoryId()))).size(); l++) {
                    if (((JsonArray) jsonObjectVenue.get(String.valueOf(mainCategoryModalList.get(l).getCategoryId()))).size() > 0) {
                        isAllEmptyVenue = false;
                    }
                }
            }
           /* if (((JsonArray) jsonObjectEvent.get(String.valueOf(mainCategoryModalList.get(k).getCategoryId()))).size() == 0) {
                isAllEmpty = false;
            }
            if (((JsonArray) jsonObjectVenue.get(String.valueOf(mainCategoryModalList.get(k).getCategoryId()))).size() == 0) {
                isAllEmpty = false;
            }*/
        }
        if (!isAllEmptyEvent || !isAllEmptyVenue) {
            if (isVenue) {
                callDialog();
            } else {
                tinyDB.putInt(Constants.CATEGORY_COMPLETE, 1);
                completeProfile2Model.getData_map_with_name().size();
                continueLayout.setVisibility(View.GONE);
                Log.d("TAG", "Rakhi: " + new Gson().toJson(subCategoryResponse));
                Intent intent = new Intent(CompleteProfile2.this, CompleteProfile2Details.class);
                startActivity(intent);
            }
        } else {
            callSelectItemDialog();
        }

    }

    private void callSelectItemDialog() {
        Dialog errorDialog = null;
        errorDialog = Utility.createErrorDialog(this, errorDialog, "You can not process further without any selection.");
        errorDialog.show();
    }

    private void callDialog() {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.preference_dialog);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        final TextView no = dialog.findViewById(R.id.no);
        final TextView yes = dialog.findViewById(R.id.yes);
        final Dialog finalDialog = dialog;
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalDialog.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalDialog.dismiss();
                tinyDB.putInt(Constants.CATEGORY_COMPLETE, 1);

                completeProfile2Model.getData_map_with_name().size();
                continueLayout.setVisibility(View.GONE);
                Log.d("TAG", "Rakhi: " + new Gson().toJson(subCategoryResponse));
                Intent intent = new Intent(CompleteProfile2.this, CompleteProfile2Details.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    //        yes.setOnClickListener(this);
//        no.setOnClickListener(this);
    private void alertDialog() {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setMessage("Do you wish to confirm other interests?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        tinyDB.putInt(Constants.CATEGORY_COMPLETE, 1);

                        completeProfile2Model.getData_map_with_name().size();

                        Log.d("TAG", "Rakhi: " + new Gson().toJson(subCategoryResponse));
                        Intent intent = new Intent(CompleteProfile2.this, CompleteProfile2Details.class);
                        startActivity(intent);
//                        intent.putExtra("selectedData", subCategoryResponse);
//                        intent.putExtra("selectedDataId", completeProfile2Model);
                      /*  if (list.size() > 0) {
//                            intent.putStringArrayListExtra("categoryList", (ArrayList<String>) list);
                            startActivity(intent);
                        } else {
                            Toast.makeText(context, "Please select atleast one category", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onApiResponse(Object response) {


        progressDialog.hide();
        DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
        if (response instanceof CategoryResponse) {

            categoryResponse = (CategoryResponse) response;
            if (categoryResponse.getStatus() == 1) {

//                DatabaseHelper db = new DatabaseHelper(getApplicationContext(), "wengage_db", 1);
                //////For SQLite Database///////
                if (categoryResponse.getCategoryList().size() > 0) {
//naPFYpGT
                    db.insertCategory(categoryResponse.getCategoryList());
                }
                category_recycler_view.setLayoutManager(linearLayoutManager);
//                categoryAdapter = new CategoryAdapter(context, categoryResponse.getCategoryList(), this);
                categoryListsDatabase = new ArrayList<>();
                categoryListsDatabase = db.getCategoryList();
                categoryAdapter = new CategoryAdapter();
                category_recycler_view.setAdapter(categoryAdapter);
                view1.setVisibility(View.VISIBLE);
//                Call<SubCategoryResponse> call = APIClient.getInstance().getApiInterface()
//                        .getAllSubCategories(categoryResponse.getCategoryList().get(0).getCategoryId(), page_num, "");

                if (Utility.isNetworkConnected(getApplicationContext())) {
                    Call<SubCategoryResponse> call = APIClient.getInstance().getApiInterface()
                            .getAllSubCategories(categoryResponse.getCategoryList().get(0).getCategoryId(),
                                    tinyDB.getInt(Constants.USER_ID), "",
                                    String.valueOf(tinyDB.getDouble(Constants.LATITUDE, 28.5355)),
                                    String.valueOf(tinyDB.getDouble(Constants.LONGITUDE, 77.3910)));
                    new ResponseListner(this).getResponse(context, call);
                    progressDialog.show();
                } else {
                    Dialog errorDialog = null;
                    errorDialog = Utility.createErrorDialog(getApplicationContext(), errorDialog, "Network error");
                    errorDialog.show();
                }
            } else {
                new BaseClass(context).showToast(categoryResponse.getMessage());
            }
        }

        if (response != null && response instanceof SubCategoryResponse) {
            subCategoryResponse = (SubCategoryResponse) response;
            if (subCategoryResponse.getStatus() == 1) {
                subcategory_recycler_view.setVisibility(View.VISIBLE);
//                subcategory_recycler_view.setLayoutManager(linearLayoutManager);
                if (subCategoryResponse.getVenueSubCategoryList() == null) {
                } else {

                    ////For database/////
                    if (categoryResponse.getCategoryList().size() > 0) {
                        if (categoryId == 0) {
                            categoryId = categoryResponse.getCategoryList().get(0).getCategoryId();
                            db.insertVenue(subCategoryResponse.getVenueSubCategoryList(), categoryId);
                        } else {
                            db.insertVenue(subCategoryResponse.getVenueSubCategoryList(), categoryId);
                        }

                    }
                    venueListsDatabase = new ArrayList<>();
                    venueListsDatabase = db.getVenueList(String.valueOf(categoryId));
                    for (int i = 0; i < subCategoryResponse.getVenuePreferences().size(); i++) {
                        if (subCategoryResponse.getVenuePreferences().get(i).getCategoryId() == categoryId) {
                            for (int j = 0; j < subCategoryResponse.getVenuePreferences().size(); j++) {
                                if (subCategoryResponse.getVenuePreferences().get(i).getSubCategoryId()
                                        == venueListsDatabase.get(j).getSubCategoryId()) {
                                    db.updateVenues(1, venueListsDatabase.get(j).getSubCategoryId());
                                }
                            }
                        }
                    }

                    venueListsDatabase = new ArrayList<>();
                    venueListsDatabase = db.getVenueList(String.valueOf(categoryId));
                    if (subCategoryResponse.getVenueSubCategoryList().size() > 0) {
                        for (int i = 0; i < subCategoryResponse.getVenueSubCategoryList().size(); i++) {
                            subCategoryResponse.getVenueSubCategoryList().get(i).setIsSelected(0);
                            itemClickStatusModelList.add(new ItemClickStatusModel(subCategoryResponse.getVenueSubCategoryList().get(i).getSubCategoryName(), false));
                        }
                        GridLayoutManager linearLayoutManagers = new GridLayoutManager(this, 3);
                        SubCategoryItemAdapter subCategoryItemAdapter = new SubCategoryItemAdapter();
                        subcategory_recycler_view.setLayoutManager(linearLayoutManagers);
                        if (venueListsDatabase.size() > 0) {
                            subcategory_recycler_view.setVisibility(View.VISIBLE);
                            subcategory_recycler_view.setAdapter(subCategoryItemAdapter);
                        } else {
                            subcategory_recycler_view.setVisibility(View.GONE);
                        }

//                        subCategoryItemAdapter.notifyDataSetChanged();
                    } else {
                        subcategory_recycler_view.setVisibility(View.GONE);
                    }
                }
                event_recycler.setVisibility(View.VISIBLE);
                eventText.setVisibility(View.VISIBLE);
                linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

                if (subCategoryResponse.getEventSubCategoryList() == null) {
                } else {
                    if (subCategoryResponse.getEventSubCategoryList().size() > 0) {
                        if (categoryId == 0) {
                            categoryId = categoryResponse.getCategoryList().get(0).getCategoryId();
                            db.insetEvent(subCategoryResponse.getEventSubCategoryList(), categoryId);
                        } else {
                            db.insetEvent(subCategoryResponse.getEventSubCategoryList(), categoryId);
                        }
                        eventListDatabase = new ArrayList<>();
                        eventListDatabase = db.getEventList(String.valueOf(categoryId));
                        for (int i = 0; i < subCategoryResponse.getEventPreferences().size(); i++) {
                            if (subCategoryResponse.getEventPreferences().get(i).getCategoryId() == categoryId) {
                                for (int j = 0; j < subCategoryResponse.getEventPreferences().size(); j++) {
                                    if (subCategoryResponse.getEventPreferences().get(i).getSubCategoryId()
                                            == eventListDatabase.get(j).getSubCategoryId()) {
                                        db.updateEvent(1, eventListDatabase.get(j).getSubCategoryId());
                                    }
                                }
                            }
                        }

                        eventListDatabase = new ArrayList<>();
                        eventListDatabase = db.getEventList(String.valueOf(categoryId));
                        for (int i = 0; i < subCategoryResponse.getEventSubCategoryList().size(); i++) {
                            subCategoryResponse.getEventSubCategoryList().get(i).setIsSelected(0);
                            itemClickStatusModelList.add(new ItemClickStatusModel(subCategoryResponse.getEventSubCategoryList().get(i).getSubCategoryName(), false));
                        }
                        GridLayoutManager linearLayoutManagerss = new GridLayoutManager(this, 3);
                        event_recycler.setLayoutManager(linearLayoutManagerss);

                        subCategoryItemAdapter = new EventCategoryItemAdapter();
                        if (eventListDatabase.size() > 0) {
                            eventText.setVisibility(View.VISIBLE);
                            event_recycler.setVisibility(View.VISIBLE);
                            event_recycler.setAdapter(subCategoryItemAdapter);
                        } else {
                            event_recycler.setVisibility(View.GONE);
                            eventText.setVisibility(View.GONE);
                        }

//                        subCategoryItemAdapter.notifyDataSetChanged();
                    } else {
                        event_recycler.setVisibility(View.GONE);
                        eventText.setVisibility(View.GONE);
                    }
                }


            }
        }

    }

    @Override
    public void onApiFailure(String message) {
        new BaseClass(CompleteProfile2.this).showToast(message);
    }

    @Override
    public void onCategoryItemClick(int catId) {

    }

    @Override
    public void onSubCategoryItemClick(int catId, int subCatId, boolean isSelected) {
        if (!integers.contains(catId)) {
            integers.add(catId);
        }
        if (completeProfile2Model.getData_map().get(catId) == null) {
            completeProfile2Model.getData_map().put(catId, new ArrayList<Integer>());
        }
        if (isSelected) {
            if (completeProfile2Model.getData_map().get(catId) != null &&
                    !completeProfile2Model.getData_map().get(catId).contains(subCatId)) {
                completeProfile2Model.getData_map().get(catId).add(subCatId);
            }
        } else {
            completeProfile2Model.getData_map().get(catId).remove(Integer.valueOf(subCatId));
        }
    }

    @Override
    public void onSubCategoryItemClickWithName(String catName, String subCatName, boolean isSelected) {

        if (!list.contains(catName)) {
            list.add(catName);
        }
        if (completeProfile2Model.getData_map_with_name().get(catName) == null) {
            completeProfile2Model.getData_map_with_name().put(catName, new ArrayList<String>());
        }

        if (isSelected) {
            if (completeProfile2Model.getData_map_with_name().get(catName) != null &&
                    !completeProfile2Model.getData_map_with_name().get(catName).contains(subCatName)) {
                completeProfile2Model.getData_map_with_name().get(catName).add(subCatName);
            }
        } else {
            completeProfile2Model.getData_map_with_name().get(catName).remove(subCatName);
        }

    }

    class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

        //        List<CategoryResponse.CategoryList> categoryList;
        CategoryItemClickListner categoryItemClickListner;
        int selectedItemPosition = -1;

        @Override
        public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.category_item, parent, false);

            return new CategoryAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final CategoryAdapter.MyViewHolder holder, final int position) {

       /* if (position == 0) {
            holder.bottom_line.setVisibility(View.VISIBLE);
        }*/
            holder.bottom_line.setVisibility(View.VISIBLE);
            if (categoryListsDatabase.get(position).getIcon() != null && !categoryListsDatabase.get(position).getIcon().equalsIgnoreCase("")) {
                Glide.with(getApplicationContext()).load(categoryListsDatabase.get(position).getIcon()).into(holder.imageView);

            }
            if (categoryListsDatabase.get(position).getCategoryName() != null)
                holder.textView.setText(categoryListsDatabase.get(position).getCategoryName());

            if (selectedItemPosition == position) {
                holder.bottom_line.setVisibility(View.VISIBLE);
            } else {
                holder.bottom_line.setVisibility(View.GONE);
            }

            holder.item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedItemPosition = position;
                    notifyDataSetChanged();
                    view1.setVisibility(View.GONE);
                    if (Utility.isNetworkConnected(getApplicationContext())) {
                        callApiWithCatId(position);
                    } else {
                        Dialog errorDialog = null;
                        errorDialog = Utility.createErrorDialog(getApplicationContext(), errorDialog, "Network error");
                        errorDialog.show();
                    }


//        completeProfile2Model2.categoryId = catId;

//                    categoryItemClickListner.onCategoryItemClick(categoryResponse.getCategoryList().get(position).getCategoryId());
                }
            });


        }


        @Override
        public int getItemCount() {
            return categoryResponse.getCategoryList().size();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;
            View bottom_line;
            LinearLayout item_layout;

            public MyViewHolder(View view) {
                super(view);
                imageView = view.findViewById(R.id.category_image);
                textView = view.findViewById(R.id.category_name);
                bottom_line = view.findViewById(R.id.item_bottom_line);
                item_layout = view.findViewById(R.id.item_layout);
            }
        }
    }

    private void callApiWithCatId(int position) {
        String signup = "1";
        categoryId = categoryResponse.getCategoryList().get(position).getCategoryId();
        Call<SubCategoryResponse> call = APIClient.getInstance().getApiInterface()
                .getAllSubCategories(categoryResponse.getCategoryList().get(position).getCategoryId(), page_num, signup,
                        String.valueOf(tinyDB.getDouble(Constants.LATITUDE, 28.5355)),
                        String.valueOf(tinyDB.getDouble(Constants.LONGITUDE, 77.3910)));
        new ResponseListner(this).getResponse(context, call);
        progressDialog.show();
    }

    class SubCategoryItemAdapter extends RecyclerView.Adapter<SubCategoryItemAdapter.MyViewHolder> {

        @Override
        public SubCategoryItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.subcategory_item_layout, parent, false);

            return new SubCategoryItemAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final SubCategoryItemAdapter.MyViewHolder holder, final int position) {
            final DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
            if (venueListsDatabase.get(position).getSubCategoryName() != null) {
                holder.textView.setText(venueListsDatabase.get(position).getSubCategoryName());
            }
            if (venueListsDatabase.get(position).getIsSelected() == 1) {
                holder.item_layout.setBackground(context.getResources().getDrawable(R.drawable.shape_filled_pink));
                holder.textView.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                holder.item_layout.setBackground(context.getResources().getDrawable(R.drawable.shape_border_grey));
                holder.textView.setTextColor(context.getResources().getColor(R.color.black));
            }
            holder.item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (venueListsDatabase.get(position).getIsSelected() == 1) {
                        if (subCategoryResponse.getMapping().size() > 0) {
                            for (int i = 0; i < subCategoryResponse.getMapping().size(); i++) {
                                if (venueListsDatabase.get(position).getSubCategoryId()
                                        == subCategoryResponse.getMapping().get(i).getVenueSubCategoryId()) {
                                    eventListDatabase.get(i).setIsSelected(0);
                                    db.updateVenues(0, venueListsDatabase.get(i).getSubCategoryId());
                                    db.updateEvent(0, eventListDatabase.get(i).getSubCategoryId());
                                }
                            }
                        }
                        venueListsDatabase.get(position).setIsSelected(0);
                        db.updateVenues(0, venueListsDatabase.get(position).getSubCategoryId());
                        holder.item_layout.setBackground(context.getResources().getDrawable(R.drawable.shape_border_grey));
                        holder.textView.setTextColor(context.getResources().getColor(R.color.black));
                    } else {
                        if (subCategoryResponse.getMapping().size() > 0) {
                            for (int i = 0; i < subCategoryResponse.getMapping().size(); i++) {
                                if (venueListsDatabase.get(position).getSubCategoryId()
                                        == subCategoryResponse.getMapping().get(i).getVenueSubCategoryId()) {
                                    eventListDatabase.get(i).setIsSelected(1);
                                    db.updateVenues(1, venueListsDatabase.get(i).getSubCategoryId());
                                    db.updateEvent(1, eventListDatabase.get(i).getSubCategoryId());
                                }
                            }
                        }

                        db.updateVenues(1, venueListsDatabase.get(position).getSubCategoryId());
                        venueListsDatabase.get(position).setIsSelected(1);
                        holder.item_layout.setBackground(context.getResources().getDrawable(R.drawable.shape_filled_pink));
                        holder.textView.setTextColor(context.getResources().getColor(R.color.white));
                    }
                    subCategoryItemAdapter.notifyDataSetChanged();

                }
            });


        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return venueListsDatabase.size();
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

    class EventCategoryItemAdapter extends RecyclerView.Adapter<EventCategoryItemAdapter.MyViewHolder> {

        @Override
        public EventCategoryItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.subcategory_item_layout, parent, false);

            return new EventCategoryItemAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final EventCategoryItemAdapter.MyViewHolder holder, final int position) {
            final DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
            if (eventListDatabase.get(position).getSubCategoryName() != null) {
                holder.textView.setText(eventListDatabase.get(position).getSubCategoryName());
            }
            if (eventListDatabase.get(position).getIsSelected() == 1) {
                holder.item_layout.setBackground(context.getResources().getDrawable(R.drawable.shape_filled_pink));
                holder.textView.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                holder.item_layout.setBackground(context.getResources().getDrawable(R.drawable.shape_border_grey));
                holder.textView.setTextColor(context.getResources().getColor(R.color.black));
            }
            holder.item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (eventListDatabase.get(position).getIsSelected() == 1) {
                        if (subCategoryResponse.getMapping().size() > 0) {
                            for (int i = 0; i < subCategoryResponse.getMapping().size(); i++) {
                                if (eventListDatabase.get(position).getSubCategoryId()
                                        == subCategoryResponse.getMapping().get(i).getEventSubCategoryId()) {
                                    venueListsDatabase.get(position).setIsSelected(0);
                                    db.updateVenues(0, venueListsDatabase.get(position).getSubCategoryId());
                                    db.updateEvent(0, eventListDatabase.get(position).getSubCategoryId());
                                }
                            }
                        }
                        eventListDatabase.get(position).setIsSelected(0);
                        db.updateEvent(0, eventListDatabase.get(position).getSubCategoryId());
                        holder.item_layout.setBackground(context.getResources().getDrawable(R.drawable.shape_border_grey));
                        holder.textView.setTextColor(context.getResources().getColor(R.color.black));
                    } else {
                        if (subCategoryResponse.getMapping().size() > 0) {
                            for (int i = 0; i < subCategoryResponse.getMapping().size(); i++) {
                                if (eventListDatabase.get(position).getSubCategoryId()
                                        == subCategoryResponse.getMapping().get(i).getEventSubCategoryId()) {
                                    venueListsDatabase.get(position).setIsSelected(1);
                                    db.updateVenues(1, venueListsDatabase.get(position).getSubCategoryId());
                                    db.updateEvent(1, eventListDatabase.get(position).getSubCategoryId());
                                }
                            }
                        }
                        db.updateEvent(1, eventListDatabase.get(position).getSubCategoryId());
                        eventListDatabase.get(position).setIsSelected(1);
                        holder.item_layout.setBackground(context.getResources().getDrawable(R.drawable.shape_filled_pink));
                        holder.textView.setTextColor(context.getResources().getColor(R.color.white));
                    }
                    SubCategoryItemAdapter subCategoryItemAdapter = new SubCategoryItemAdapter();
                    subcategory_recycler_view.setAdapter(subCategoryItemAdapter);
                }
            });


        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return subCategoryResponse.getEventSubCategoryList().size();
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
}
