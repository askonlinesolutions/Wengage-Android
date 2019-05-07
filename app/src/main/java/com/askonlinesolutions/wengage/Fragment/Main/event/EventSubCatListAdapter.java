package com.askonlinesolutions.wengage.Fragment.Main.event;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.askonlinesolutions.wengage.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Rakhi.
 * Contact Number : +91 9958187463
 * Date: 12/10/2018
 */
public class EventSubCatListAdapter extends RecyclerView.Adapter<EventSubCatListAdapter.MyViewHolder> {

    private Context context;
    private Interface_RestaurantList1 click;
    private List<EventSubCatDetailResponse.EventsListBean> eventsListBeans;
    private String type;

    public EventSubCatListAdapter(Context context, Interface_RestaurantList1 click,
                                  List<EventSubCatDetailResponse.EventsListBean> eventsListBeans) {
        this.context = context;
        this.click = click;
        this.eventsListBeans = eventsListBeans;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_image, iv_circle, iv_butterfly, iv_check, iv_bookmarks;
        TextView tv_ad, tv_name, tv_review, /*tv_distance*/
                tv_interested, no_venue;
        LinearLayout restaurant_layout;
        int isBookMarked = 0, isInterested = 0;
        RatingBar ratingBar;

        public MyViewHolder(View view) {
            super(view);
            ratingBar = view.findViewById(R.id.item_res_list_rating);
            iv_image = view.findViewById(R.id.restaurant_list_events_image);
            iv_check = view.findViewById(R.id.restaurant_list_events_check);
            iv_butterfly = view.findViewById(R.id.restaurant_list_events_butterfly);
            iv_circle = view.findViewById(R.id.restaurant_list_events_circle);
            iv_bookmarks = view.findViewById(R.id.restaurant_list_events_bookmarks);
            tv_name = view.findViewById(R.id.restaurant_list_events_name);
            tv_review = view.findViewById(R.id.restaurant_list_events_reviews);
            tv_interested = view.findViewById(R.id.restaurant_list_events_interested);

            iv_bookmarks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (eventsListBeans.get(getAdapterPosition()).getIsBookmarked() == 1) {
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
                    if (eventsListBeans.get(getAdapterPosition()).getIsInterested() == 1) {
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
                .inflate(R.layout.item_restaurant_list_events, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (eventsListBeans.size() != 0) {
            if (!eventsListBeans.get(position).getImageURL().equalsIgnoreCase("")) {
                Glide.with(context)
                        .load(eventsListBeans.get(position).getImageURL())
                        .into(holder.iv_image);
            }
            if (eventsListBeans.get(position).getIsInterested() == 1) {
                holder.iv_circle.setVisibility(View.VISIBLE);
            } else {
                holder.iv_circle.setVisibility(View.GONE);
            }
            if (eventsListBeans.get(position).getRecommendByWengage() == 1) {
                holder.iv_butterfly.setVisibility(View.VISIBLE);
            } else {
                holder.iv_butterfly.setVisibility(View.GONE);
            }
            holder.tv_name.setText(eventsListBeans.get(position).getName());

            if (eventsListBeans.get(position).getInfluencerPick() == 0) {
                holder.iv_check.setVisibility(View.GONE);
            } else if (eventsListBeans.get(position).getInfluencerPick() == 1)
                holder.iv_check.setVisibility(View.VISIBLE);

            if (eventsListBeans.get(position).getRecommendByWengage() == 0) {
                holder.iv_butterfly.setVisibility(View.GONE);
            } else if (eventsListBeans.get(position).getRecommendByWengage() == 1)
                holder.iv_butterfly.setVisibility(View.VISIBLE);

            if (eventsListBeans.get(position).getIsBookmarked() == 1) {
                holder.iv_bookmarks.setImageResource(R.drawable.ic_fav);
            } else {
                holder.iv_bookmarks.setImageResource(R.drawable.ic_fav_1);
            }
            if (eventsListBeans.get(position).getIsBookmarked() == 1)
                holder.tv_interested.setTextColor(context.getResources().getColor(R.color.colorAccent));
            else
                holder.tv_interested.setTextColor(context.getResources().getColor(R.color.text_color));

            if (eventsListBeans.get(position).getReviewCount() > 0)
                holder.tv_review.setText(eventsListBeans.get(position).getReviewCount() + " Reviews");
            else holder.tv_review.setText("");

           /* if (eventsListBeans.get(position).getDistance()!=null&&eventsListBeans.get(position).getDistance().length()>0)
                holder.tv_distance.setText(eventsListBeans.get(position).getDistance() + "km from you");
            else {
                holder.tv_distance.setText("");
            }
*/
            if (eventsListBeans.get(position).getAvgRating() != null && eventsListBeans.get(position).getAvgRating().length() > 0) {
                holder.ratingBar.setRating(Float.parseFloat(eventsListBeans.get(position).getAvgRating()));
            }
        } else {
         /*   holder.restaurant_layout.setVisibility(View.GONE);
            holder.no_venue.setVisibility(View.VISIBLE);*/
        }


    }

    @Override
    public int getItemCount() {
        return eventsListBeans.size();
    }

    public interface Interface_RestaurantList1 {
        void click_interface_restaurant_list_1(int position, String type, int status);

        void viewItemDetails(int pos);
    }

}