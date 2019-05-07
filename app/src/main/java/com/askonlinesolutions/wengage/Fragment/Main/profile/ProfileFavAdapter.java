package com.askonlinesolutions.wengage.Fragment.Main.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.askonlinesolutions.wengage.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rakhi.
 * Contact Number : +91 9958287463
 */
public class ProfileFavAdapter extends RecyclerView.Adapter<ProfileFavAdapter.MyViewHolder> {

    private Context context;
    List<UserProfile.UserDataBean.FavouritesBean> venues;

    public ProfileFavAdapter(Context context, List<UserProfile.UserDataBean.FavouritesBean> venues) {
        this.context = context;
        this.venues = venues;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv;

        public MyViewHolder(View view) {
            super(view);

            iv = (ImageView) view.findViewById(R.id.home_list_image);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_list3, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (venues.size() != 0 && venues.get(position).getImageURL() != null) {
            if (!venues.get(position).getImageURL().equalsIgnoreCase("")) {
                Picasso.with(context).load(venues.get(position).getImageURL())
                        .placeholder(R.drawable.ic_loading).into(holder.iv);
            }

        } else {

        }
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }
}