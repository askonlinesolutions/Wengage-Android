package com.askonlinesolutions.wengage.Fragment.Main.venue.details.rate.event;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Model.Response.VenueDetailsResponse;
import com.askonlinesolutions.wengage.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by Rakhi.
 * Contact Number : +91 9958187463
 * Date: 12/11/2018
 */
public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.MyViewHolder> {

    private Context context;
    private List<VenueDetailsResponse.VenueDataBean.RatingsBean> ratingsBeans;

    public RatingAdapter(Context context, List<VenueDetailsResponse.VenueDataBean.RatingsBean> ratingsBeans) {
        this.context = context;
        this.ratingsBeans = ratingsBeans;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView  tv_name,review;
        RatingBar ratingBar;
        ImageView imgUser;

        public MyViewHolder(View view) {
            super(view);
            ratingBar = view.findViewById(R.id.rBarFoodQuality);
            tv_name = view.findViewById(R.id.view_rating_card_txt);
            imgUser = view.findViewById(R.id.view_rating_card_img);
            review = view.findViewById(R.id.review);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_rating_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (ratingsBeans.get(position).getKnownByName()!=null)
        holder.tv_name.setText(ratingsBeans.get(position).getKnownByName());
        else holder.tv_name.setText("");
        holder.review.setText(ratingsBeans.get(position).getReview());
        holder.ratingBar.setRating((float) ratingsBeans.get(position).getRating());
        Glide.with(context)
                .load(ratingsBeans.get(position).getPhotoURL())
                .apply(RequestOptions.circleCropTransform())
                .apply(RequestOptions.placeholderOf(R.drawable.load))
                .into(holder.imgUser);

    }

    @Override
    public int getItemCount() {
        return ratingsBeans.size();
    }

}