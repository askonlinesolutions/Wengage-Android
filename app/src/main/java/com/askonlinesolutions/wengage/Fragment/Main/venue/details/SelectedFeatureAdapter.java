package com.askonlinesolutions.wengage.Fragment.Main.venue.details;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Model.Response.VenueDetailsResponse;
import com.askonlinesolutions.wengage.R;

import java.util.List;

/**
 * Created by Rakhi.
 * Contact Number : +91 9958187463
 * Date : 12/11/2018
 */

public class SelectedFeatureAdapter extends RecyclerView.Adapter<SelectedFeatureAdapter.MyViewHolder> {

    private List<VenueDetailsResponse.VenueDataBean.FavouriteUsersBean> favouriteUsers;
    private Context context;

    public SelectedFeatureAdapter(List<VenueDetailsResponse.VenueDataBean.FavouriteUsersBean> favouriteUsers, Context context) {
        this.favouriteUsers = favouriteUsers;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;

        public MyViewHolder(View view) {
            super(view);

            tv = view.findViewById(R.id.item_home_2_pref_tv);


        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_2_pref, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tv.setText(favouriteUsers.get(position).getKnownByName());
        holder.tv.setBackgroundResource(R.drawable.shape_round_bg_2);
        holder.tv.setTextColor(context.getResources().getColor(R.color.text_color));
    }

    @Override
    public int getItemCount() {
        return favouriteUsers.size();
    }

}