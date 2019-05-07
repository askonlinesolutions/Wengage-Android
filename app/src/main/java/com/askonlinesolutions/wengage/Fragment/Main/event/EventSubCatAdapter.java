package com.askonlinesolutions.wengage.Fragment.Main.event;

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

import java.util.List;

/**
 * Created by Muhib.
 * Contact Number : +91 9796173066
 */
public class EventSubCatAdapter extends RecyclerView.Adapter<EventSubCatAdapter.MyViewHolder> {

    private Context context;
    private Interface_RestaurantListEvents click;
    private List<VenueDetailsResponse.VenueDataBean.EventsBean> eventsBeanList;

    private String type;


    public EventSubCatAdapter(Context context, Interface_RestaurantListEvents click,
                              List<VenueDetailsResponse.VenueDataBean.EventsBean> eventsBeanList) {
        this.context = context;
        this.click = click;
        this.eventsBeanList = eventsBeanList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_image, iv_circle, iv_butterfly, iv_check, iv_bookmarks;
        public TextView  tv_name, tv_review, tv_interested;
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

//        holder.iv.setImageResource(list_images.get(position));

        Glide.with(context).load(eventsBeanList.get(position).getImageURL()).into(holder.iv_image);
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
//
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

               /* RestaurantDetails fragment = new RestaurantDetails();
                Bundle bundle = new Bundle();
                bundle.putInt("image", bottom_list_image.get(position));
                bundle.putInt("image2", bottom_list_image_2.get(position));
                bundle.putString("circle", bottom_list_circle.get(position));
                bundle.putString("ad", bottom_list_ad.get(position));
                bundle.putString("name", bottom_list_name.get(position));
                bundle.putString("review", bottom_list_review.get(position));
                bundle.putString("km", bottom_list_distance.get(position));
                bundle.putString("butterfly", bottom_list_butterfly.get(position));
                bundle.putString("check", bottom_list_check.get(position));
                bundle.putString("type", type);
                fragment.setArguments(bundle);
                new BaseClass(context).callFragment1(fragment, fragment.getClass().getName(), manager);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsBeanList.size();
    }

    public interface Interface_RestaurantListEvents{
        public void click_interface_restaurant_list_events(int position, String type);
    }
}