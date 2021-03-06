package com.askonlinesolutions.wengage.Activity.Main.dashboard.dashboard_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.InfluencerResponse;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.VenueResponse;
import com.askonlinesolutions.wengage.CallBacks.OnItemClickListener;
import com.askonlinesolutions.wengage.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class DashboardInfluencerAdapter extends RecyclerView.Adapter<DashboardInfluencerAdapter.MyViewHolder> {

    private String[] dataSource;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private List<InfluencerResponse.NotificationsEntity> influenserList;


    public DashboardInfluencerAdapter(Context context, List<InfluencerResponse.NotificationsEntity> influenserList, OnItemClickListener onItemClickListener) {
//        dataSource = dataArgs;
        this.context = context;
        this.influenserList = influenserList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public DashboardInfluencerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_influencer_list_item, parent, false);
//        dashboard_venue_list_item
        return new DashboardInfluencerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DashboardInfluencerAdapter.MyViewHolder holder, int position) {


            holder.name.setText(influenserList.get(position).getName());
            holder.distance.setText(influenserList.get(position).getDistance() + "km");
            if (influenserList.get(position).getReviewCount() == 0) {
                holder.reviewCount.setText(/*venueNotificationList.get(position).getReviewCount()+*/"No Reviews");
            } else {
                holder.reviewCount.setText(influenserList.get(position).getReviewCount() + " Reviews");
            }
            if (!influenserList.get(position).getImage().equalsIgnoreCase("") && influenserList.get(position).getImage() != null) {
                Glide.with(context).load(influenserList.get(position).getImage()).into(holder.img);
            }
            holder.ratingBar.setRating(/*Float.valueOf(*/influenserList.get(position).getAvgRating()/*)*/);
            holder.main_layout.setVisibility(View.VISIBLE);



    }


    @Override
    public int getItemCount() {
        //return dataSource.length;
        if (influenserList.size() > 0) {
            return influenserList.size();
        } else
            return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, distance, reviewCount, event;
        RatingBar ratingBar;
        ImageView img;
        LinearLayout main_layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.venue_name);
            distance = (TextView) itemView.findViewById(R.id.distance);
            reviewCount = (TextView) itemView.findViewById(R.id.venue_review_count);
            event = (TextView) itemView.findViewById(R.id.event);
            img = itemView.findViewById(R.id.venu_img);
            ratingBar = itemView.findViewById(R.id.venue_rating);
            main_layout = itemView.findViewById(R.id.main_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition(), "influensr");
                }
            });
        }

    }

}
