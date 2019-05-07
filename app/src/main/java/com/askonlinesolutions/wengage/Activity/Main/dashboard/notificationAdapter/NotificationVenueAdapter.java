package com.askonlinesolutions.wengage.Activity.Main.dashboard.notificationAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.dashboard.dashboard_adapters.DashboardVenueAdapter;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.VenueResponse;
import com.askonlinesolutions.wengage.CallBacks.OnItemClickListener;
import com.askonlinesolutions.wengage.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class NotificationVenueAdapter extends RecyclerView.Adapter<NotificationVenueAdapter.MyViewHolder> {


    private String[] dataSource;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private List<VenueResponse.NotificationsEntity> venueNotificationList;

    public NotificationVenueAdapter(Context context, List<VenueResponse.NotificationsEntity> venueNotificationList, OnItemClickListener onItemClickListener) {
//        dataSource = dataArgs;
        this.context = context;
        this.venueNotificationList = venueNotificationList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public NotificationVenueAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_influencer_list_item, parent, false);
//        dashboard_venue_list_item
        return new NotificationVenueAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationVenueAdapter.MyViewHolder holder, int position) {

//        if (venueNotificationList.get(position).getIsRead() == 0) {

        holder.name.setText(venueNotificationList.get(position).getName());
        holder.distance.setText(venueNotificationList.get(position).getDistance() + "km");
        if (venueNotificationList.get(position).getReviewCount() == 0) {
            holder.reviewCount.setText(/*venueNotificationList.get(position).getReviewCount()+*/"No Reviews");
        } else {
            holder.reviewCount.setText(venueNotificationList.get(position).getReviewCount() + " Reviews");
        }
        if (!venueNotificationList.get(position).getImage().equalsIgnoreCase("") && venueNotificationList.get(position).getImage() != null) {
            Glide.with(context).load(venueNotificationList.get(position).getImage()).into(holder.img);
        }
        holder.ratingBar.setRating(/*Float.valueOf(*/venueNotificationList.get(position).getAvgRating()/*)*/);
//        }else {
//
//        }
    }

    @Override
    public int getItemCount() {
        //return dataSource.length;
        if (venueNotificationList.size() > 0) {
            return venueNotificationList.size();
        } else
            return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, distance, reviewCount, event;
        RatingBar ratingBar;
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.venue_name);
            distance = (TextView) itemView.findViewById(R.id.distance);
            reviewCount = (TextView) itemView.findViewById(R.id.venue_review_count);
            event = (TextView) itemView.findViewById(R.id.event);
            img = itemView.findViewById(R.id.venu_img);
            ratingBar = itemView.findViewById(R.id.venue_rating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition(), "venue");
                }
            });
        }

    }


}
