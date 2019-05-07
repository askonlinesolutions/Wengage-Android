package com.askonlinesolutions.wengage.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Model.LanguageModal;
import com.askonlinesolutions.wengage.Model.UserProfileDetailModal;
import com.askonlinesolutions.wengage.Networks.NetworkConstants;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.utils.Progress;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mukesh.tinydb.TinyDB;

import java.util.ArrayList;

public class LanguageActivity extends AppCompatActivity {
    private RecyclerView languageRecyclerView;
    private ImageView back;
    public static LanguageModal languageModal;
    private TinyDB tinyDB;
    private String language = "";
    String languageArray;
    UserProfileDetailModal languageBean;
    ArrayList<String> strings;
    private Progress progress;
    private TextView logIn_button;
    ArrayList<String> languageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_activity);
        tinyDB = new TinyDB(getApplicationContext());
        getIntents();
        initUI();
    }

    private void getIntents() {
        Intent intent = getIntent();
        languageList = intent.getStringArrayListExtra("languageList");
    }


    private void initUI() {
        progress = new Progress(this);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        logIn_button = findViewById(R.id.logIn_button);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        logIn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < strings.size(); i++) {
                    for (int j = 0; j < languageModal.getLanguages().size(); j++) {
                        if (strings.get(i).equals(String.valueOf(languageModal.getLanguages().get(j).getLanguageId()))) {
                            languageModal.getLanguages().get(j).setSelected(true);
                        }
                    }
                }
                onBackPressed();
            }
        });
        languageRecyclerView = findViewById(R.id.languageRecyclerView);
        GridLayoutManager linearLayoutManagerss = new GridLayoutManager(this, 3);
        languageRecyclerView.setLayoutManager(linearLayoutManagerss);
        callLanguageAPi();
    }

    private void callLanguageAPi() {
        progress.show();
        Ion.with(this)
                .load("GET", NetworkConstants.GET_LANGUAGES)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        progress.dismiss();
                        languageModal = new Gson().fromJson(result, LanguageModal.class);
                        if (languageModal.getLanguages().size() > 0) {
                            if (languageList == null) {
                            } else {
                                if (languageList.size() > 0) {
                                    for (int i = 0; i < languageList.size(); i++) {
                                        for (int j = 0; j < languageModal.getLanguages().size(); j++) {
                                            if (languageList.get(i).equals(String.valueOf(languageModal.getLanguages().get(j).getLanguageId()))) {
                                                languageModal.getLanguages().get(j).setSelected(true);
                                            }
                                        }
                                    }
                                }
                            }


                            CountryAdapter countryAdapter = new CountryAdapter();
                            languageRecyclerView.setAdapter(countryAdapter);
                        } else {
//                            Toast.makeText(CompleteProfile1.this, "We don't have countries", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

        @Override
        public CountryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.subcategory_item_layout, parent, false);

            return new CountryAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final CountryAdapter.MyViewHolder holder, final int position) {

            strings = new ArrayList<>();
            holder.sub_category_name.setText(languageModal.getLanguages().get(position).getLanguageTitle());
            if (languageModal.getLanguages().get(position).isSelected()) {
                holder.item_layout.setBackground(getResources().getDrawable(R.drawable.shape_filled_pink));
                holder.sub_category_name.setTextColor(getResources().getColor(R.color.white));
                languageModal.getLanguages().get(position).setSelected(true);
            } else {
                holder.item_layout.setBackground(getResources().getDrawable(R.drawable.shape_border_grey));
                holder.sub_category_name.setTextColor(getResources().getColor(R.color.black));
                languageModal.getLanguages().get(position).setSelected(false);
            }

            holder.sub_category_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.sub_category_name.getCurrentTextColor() == getResources().getColor(R.color.black)) {

                        holder.item_layout.setBackground(getResources().getDrawable(R.drawable.shape_filled_pink));
                        holder.sub_category_name.setTextColor(getResources().getColor(R.color.white));
                        strings.add(String.valueOf(languageModal.getLanguages().get(position).getLanguageId()));
//                        languageModal.getLanguages().get(position).setSelected(true);
//                        holder.language_name.setTextColor(getResources().getColor(R.color.text_color_meg));
                    } else {

                        holder.item_layout.setBackground(getResources().getDrawable(R.drawable.shape_border_grey));
                        holder.sub_category_name.setTextColor(getResources().getColor(R.color.black));
                        strings.remove(String.valueOf(languageModal.getLanguages().get(position).getLanguageId()));
//                        holder.language_name.setTextColor(getResources().getColor(R.color.text_color));
                    }
                }
            });
        }


        @Override
        public int getItemCount() {
            return languageModal.getLanguages().size();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView sub_category_name;
            private LinearLayout item_layout;

            public MyViewHolder(View view) {
                super(view);
                sub_category_name = view.findViewById(R.id.sub_category_name);
                item_layout = view.findViewById(R.id.item_layout);
            }
        }
    }

}
