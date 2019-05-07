package com.askonlinesolutions.wengage.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Fragment.Main.vo.VeneuHomeListResponse;
import com.askonlinesolutions.wengage.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by Rakhi.
 * Contact Number : +91 9958187463
 * Date: 12/10/2018
 */
public class AdapterCategoryListBottom1 extends RecyclerView.Adapter<AdapterCategoryListBottom1.MyViewHolder> {

    private Context context;
    private Interface_RestaurantList1 click;
    private List<VeneuHomeListResponse.VenuesHomeList> venuesList;
    private String type;

    public AdapterCategoryListBottom1(Context context, List<VeneuHomeListResponse.VenuesHomeList> venuesList,
                                      String venues, Interface_RestaurantList1 click) {

        this.venuesList = venuesList;
        this.context = context;
        this.click = click;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_image, imgProgress, iv_circle, iv_butterfly, iv_check, iv_bookmarks;
        TextView tv_ad, tv_name, tv_review, tv_distance, tv_interested, no_venue;
        LinearLayout restaurant_layout;
        int isBookMarked = 0, isInterested = 0;
        RatingBar ratingBar;

        public MyViewHolder(View view) {
            super(view);
            ratingBar = view.findViewById(R.id.restorent_venue_rating);
            iv_image = view.findViewById(R.id.home_list_image);
            iv_check = view.findViewById(R.id.cat_list_bottom_check);
            imgProgress = view.findViewById(R.id.home_list_progress_img);
            iv_butterfly = view.findViewById(R.id.cat_list_bottom_butterfly);
            iv_circle = view.findViewById(R.id.cat_list_bottom_circle);
            tv_ad = view.findViewById(R.id.cat_list_bottom_ad);
            tv_name = view.findViewById(R.id.cat_list_bottom_name);
            tv_review = view.findViewById(R.id.cat_list_bottom_review);
            tv_distance = view.findViewById(R.id.cat_list_bottom_distance);
            iv_bookmarks = view.findViewById(R.id.item_restaurant_details_bookmarks);
            tv_interested = view.findViewById(R.id.item_restaurant_details_interested);
            restaurant_layout = view.findViewById(R.id.restaurant_layout);
            no_venue = view.findViewById(R.id.no_vanue);

            Glide.with(context)
                    .asGif()
                    .load(R.drawable.load)
                    .into(imgProgress);

            iv_bookmarks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (venuesList.get(getAdapterPosition()).getIsBookmarked() == 1) {
                        isBookMarked = 0;
                    } else {
                        isBookMarked = 1;
                    }
                    click.click_interface_restaurant_list_1(getAdapterPosition(), "bookmarks", isBookMarked);
                }
            });

            tv_interested.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (venuesList.get(getAdapterPosition()).getIsInterested() == 1) {
                        isInterested = 0;
                    } else {
                        isInterested = 1;
                    }
                    click.click_interface_restaurant_list_1(getAdapterPosition(), "interested", isInterested);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.viewItemDetails(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_list_bottom, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (venuesList.size() != 0) {
            if (venuesList.get(position).getIsInterested() == 1) {
                holder.iv_circle.setVisibility(View.VISIBLE);
            } else {
                holder.iv_circle.setVisibility(View.GONE);
            }
            if (venuesList.get(position).getRecommendByWengage() == 1) {
                holder.iv_butterfly.setVisibility(View.VISIBLE);
            } else {
                holder.iv_butterfly.setVisibility(View.GONE);
            }
            if (!venuesList.get(position).getImageURL().equalsIgnoreCase("")) {
                Glide.with(context)
                        .load(venuesList.get(position).getImageURL())
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
                        .into(holder.iv_image);
            }

            holder.tv_name.setText(venuesList.get(position).getName());

            if (venuesList.get(position).getInfluencerPick() == 0) {
                holder.iv_check.setVisibility(View.GONE);
            } else if (venuesList.get(position).getInfluencerPick() == 1)
                holder.iv_check.setVisibility(View.VISIBLE);

            if (venuesList.get(position).getRecommendByWengage() == 0) {
                holder.iv_butterfly.setVisibility(View.GONE);
            } else if (venuesList.get(position).getRecommendByWengage() == 1)
                holder.iv_butterfly.setVisibility(View.VISIBLE);

            if (venuesList.get(position).getIsBookmarked() == 1) {
                holder.iv_bookmarks.setImageResource(R.drawable.ic_fav);
            } else {
                holder.iv_bookmarks.setImageResource(R.drawable.ic_fav_1);
            }
            if (venuesList.get(position).getIsInterested() == 1)
                holder.tv_interested.setTextColor(context.getResources().getColor(R.color.colorAccent));
            else
                holder.tv_interested.setTextColor(context.getResources().getColor(R.color.text_color));

            if (venuesList.get(position).getReviewCount() != null && venuesList.get(position).getReviewCount().length() > 0
                    && !venuesList.get(position).getReviewCount().equals("0"))
                holder.tv_review.setText(venuesList.get(position).getReviewCount() + " Reviews");
            else holder.tv_review.setText("");

            if (venuesList.get(position).getDistance() != null && venuesList.get(position).getDistance().length() > 0)
                holder.tv_distance.setText(venuesList.get(position).getDistance() + "km from you");
            else {
                holder.tv_distance.setText("");
            }

            if (venuesList.get(position).getAvgRating() != null && venuesList.get(position).getAvgRating().length() > 0) {
                holder.ratingBar.setRating(Float.parseFloat(venuesList.get(position).getAvgRating()));
            }
        } else {
            holder.restaurant_layout.setVisibility(View.GONE);
            holder.no_venue.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return venuesList.size();
    }

    public interface Interface_RestaurantList1 {
        void click_interface_restaurant_list_1(int position, String type, int status);

        void viewItemDetails(int pos);
    }

}