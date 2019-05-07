package com.askonlinesolutions.wengage.Fragment.Main.profile;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Fragment.RestaurantVenueDetails;
import com.askonlinesolutions.wengage.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by Rakhi.
 * Contact Number : +91 9958287463
 */
public class ProfileVenueAdapter extends RecyclerView.Adapter<ProfileVenueAdapter.MyViewHolder> {

    private Context context;
    List<UserProfile.UserDataBean.VenuesBean> venues;

    public ProfileVenueAdapter(Context context, List<UserProfile.UserDataBean.VenuesBean> venues) {
        this.context = context;
        this.venues = venues;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv, imgProgress;
        TextView txtName;
        private RelativeLayout venueLayout;

        public MyViewHolder(View view) {
            super(view);
            txtName = view.findViewById(R.id.venue_city_name);
            venueLayout = view.findViewById(R.id.venueLayout);
            iv = (ImageView) view.findViewById(R.id.home_venue_image);
            imgProgress = view.findViewById(R.id.item_home_list_progress);
            Glide.with(context)
                    .asGif()
                    .load(R.drawable.load)
                    .into(imgProgress);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (venues.size() != 0 && venues.get(position).getImageURL() != null) {
            if (!venues.get(position).getImageURL().equalsIgnoreCase("")) {
                Glide.with(context)
                        .load(venues.get(position).getImageURL())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                        Target<Drawable> target, boolean isFirstResource) {
                                holder.imgProgress.setVisibility(View.GONE);

                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                           DataSource dataSource, boolean isFirstResource) {
                                holder.imgProgress.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .into(holder.iv);


            }
            holder.txtName.setText(venues.get(position).getName());
            holder.venueLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RestaurantVenueDetails fragment = new RestaurantVenueDetails();
                    Bundle bundle = new Bundle();
                    bundle.putString("venue_id", venues.get(position).getVenueId());
                    fragment.setArguments(bundle);
                    ((HomeActivity) context).replaceVenueDetailFragment(fragment);

                }
            });
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }
}