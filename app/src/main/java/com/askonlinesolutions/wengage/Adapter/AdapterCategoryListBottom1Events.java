package com.askonlinesolutions.wengage.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
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
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by Muhib.
 * Contact Number : +91 9796173066
 */
public class AdapterCategoryListBottom1Events extends RecyclerView.Adapter<AdapterCategoryListBottom1Events.MyViewHolder> {

    private Context context;
    private Interface_RestaurantListEvents click;
    private List<VenueDetailsResponse.VenueDataBean.EventsBean> eventsBeanList;

    private String type;


    public AdapterCategoryListBottom1Events(Context context, Interface_RestaurantListEvents click,
                                            List<VenueDetailsResponse.VenueDataBean.EventsBean> eventsBeanList) {
        this.context = context;
        this.click = click;
        this.eventsBeanList = eventsBeanList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_image, iv_circle, iv_butterfly, iv_check, iv_bookmarks, ivProgress;
        public TextView tv_name, tv_review, tv_interested;
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
            ivProgress = view.findViewById(R.id.restaurant_list_events_img_progress);

            Glide.with(context)
                    .asGif()
                    .load(R.drawable.load)
                    .into(iv_image);

            iv_bookmarks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    click.click_interface_restaurant_list_events(getAdapterPosition(), "bookmarks");
                }
            });

            tv_interested.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    click.click_interface_restaurant_list_events(getAdapterPosition(), "interested");
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

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Glide.with(context)
                .load(eventsBeanList.get(position).getImageURL())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        holder.ivProgress.setVisibility(View.GONE);

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        holder.ivProgress.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.iv_image);



      /*  if(bottom_list_interested.get(position).equals("0"))
            holder.tv_interested.setTextColor(context.getResources().getColor(R.color.text_color));
        else
            holder.tv_interested.setTextColor(context.getResources().getColor(R.color.colorAccent));*/

      /*  if(bottom_list_circle.get(position).equals("yes"))
            holder.iv_circle.setVisibility(View.VISIBLE);
        else
            holder.iv_circle.setVisibility(View.GONE);*/
        holder.tv_name.setText(eventsBeanList.get(position).getName());
        holder.tv_review.setText("");
        holder.ratingBar.setRating(0);

//        if(bottom_list_butterfly.get(position).equals("yes"))
//            holder.iv_butterfly.setVisibility(View.VISIBLE);
//        else
//            holder.iv_butterfly.setVisibility(View.GONE);
//
//        if(bottom_list_check.get(position).equals("yes"))
//            holder.iv_check.setVisibility(View.VISIBLE);
//        else
//            holder.iv_check.setVisibility(View.GONE);
/*
        if(eventsBeanList.get(position).geb.equals("0"))
            holder.iv_bookmarks.setImageResource(R.drawable.ic_favorites_1);
        else
            holder.iv_bookmarks.setImageResource(R.drawable.ic_favorites);

        if(bottom_list_interested.get(position).equals("0"))
            holder.tv_interested.setTextColor(context.getResources().getColor(R.color.text_color));
        else
            holder.tv_interested.setTextColor(context.getResources().getColor(R.color.colorAccent));*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsBeanList.size();
    }

    public interface Interface_RestaurantListEvents {
        void click_interface_restaurant_list_events(int position, String type);
    }
}