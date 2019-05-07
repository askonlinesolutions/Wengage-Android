package com.askonlinesolutions.wengage.Fragment.Main.profile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.R;

/**
 * Created by Rakhi.
 * Contact Number : +91 9958187463
 */
public class ProfileCategoryListAdapter extends RecyclerView.Adapter<ProfileCategoryListAdapter.MyViewHolder> {





    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_image, iv_circle, iv_butterfly, iv_check, iv_bookmarks/*, star_1, star_2, star_3, star_4, star_5*/;
        public TextView tv_ad, tv_name, tv_review, tv_distance, tv_interested,no_venue;
        LinearLayout restaurant_layout;

        public MyViewHolder(View view) {
            super(view);

            iv_image = view.findViewById(R.id.home_list_image);
            iv_check = view.findViewById(R.id.cat_list_bottom_check);
            iv_butterfly = view.findViewById(R.id.cat_list_bottom_butterfly);
            iv_circle = view.findViewById(R.id.cat_list_bottom_circle);
            tv_ad = view.findViewById(R.id.cat_list_bottom_ad);
            tv_name = view.findViewById(R.id.cat_list_bottom_name);
            tv_review = view.findViewById(R.id.cat_list_bottom_review);
            tv_distance = view.findViewById(R.id.cat_list_bottom_distance);
            iv_bookmarks = view.findViewById(R.id.item_restaurant_details_bookmarks);
            tv_interested = view.findViewById(R.id.item_restaurant_details_interested);
            restaurant_layout=view.findViewById(R.id.restaurant_layout);
            no_venue=view.findViewById(R.id.no_vanue);
       /*     star_1 = view.findViewById(R.id.star_1);
            star_2 = view.findViewById(R.id.star_2);
            star_3 = view.findViewById(R.id.star_3);
            star_4 = view.findViewById(R.id.star_4);
            star_5 = view.findViewById(R.id.star_5);*/


        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_list_bottom, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


    }

    @Override
    public int getItemCount() {
       return 1;
    }


}