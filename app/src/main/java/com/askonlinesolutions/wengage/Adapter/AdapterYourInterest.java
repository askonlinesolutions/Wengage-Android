package com.askonlinesolutions.wengage.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Fragment.Main.event.EventDetailsFragment;
import com.askonlinesolutions.wengage.Fragment.Sub.eventsInYourCity.SeeAllFragment;
import com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.YourInterestFragment;
import com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.vo.InterestedEventsResponse;
import com.askonlinesolutions.wengage.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Muhib.
 * Contact Number : +91 9796173066
 */
public class AdapterYourInterest extends RecyclerView.Adapter<AdapterYourInterest.MyViewHolder> {


    private boolean status_desc = false;
    private int str_image, str_image_2, status_bookmark;
    private boolean status_bookmark_venues = true, status_interested_venues = false;
    private boolean status_bookmark_events = false, status_interested_events = false;
    private Context context;
    FragmentManager manager;

    //    private Interface_AdapterYourInterest click;
    private List<InterestedEventsResponse.EventsListResponse> eventList;
    private String type;

    public AdapterYourInterest(Context context, List<InterestedEventsResponse.EventsListResponse> eventsListResponses,
            /*EventListInterface eventListInterfstIntace,*/Interface_AdapterYourInterest click) {

        this.context = context;
        this.manager = manager;
        this.type = type;
        this.eventList = eventsListResponses;
//        this.click = click;
    }

    public AdapterYourInterest(Context context, List<InterestedEventsResponse.EventsListResponse> eventsListResponses, YourInterestFragment yourInterestFragment) {
        this.context = context;
        this.eventList = eventsListResponses;
//        this.click = click;
    }

    public AdapterYourInterest(Context context, List<InterestedEventsResponse.EventsListResponse> eventsListResponses, SeeAllFragment seeAllFragment) {

        this.context = context;
        this.eventList = eventsListResponses;
    }

//    public interface EventListInterface {
//        void lastIndex(int pos);
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_image, /*iv_circle,*/
                iv_butterfly, iv_check, iv_bookmarks;
        public TextView tv_name, tv_review, tv_interested;
        RatingBar ratingBar;

        public MyViewHolder(View view) {
            super(view);
            ratingBar = view.findViewById(R.id.event_rating_bar);
            iv_image = view.findViewById(R.id.item_your_interest_list_image);
            iv_check = view.findViewById(R.id.item_your_interest_list_check);
            iv_butterfly = view.findViewById(R.id.item_your_interest_list_butterfly);
//            iv_circle = view.findViewById(R.id.iyic);
            iv_bookmarks = view.findViewById(R.id.item_your_interest_list_bookmarks);
            tv_name = view.findViewById(R.id.item_your_interest_list_name);
            tv_review = view.findViewById(R.id.item_your_interest_list_reviews);
            tv_interested = view.findViewById(R.id.item_your_interest_list_interested);

            iv_bookmarks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!status_bookmark_events) {
                        iv_bookmarks.setImageResource(R.drawable.ic_favorites);
                        status_bookmark_events = true;
                        status_bookmark = 1;

                    } else {
                        iv_bookmarks.setImageResource(R.drawable.ic_favorites_1);
                        status_bookmark_events = false;
                        status_bookmark = 0;
                    }

//                    click.method_AdapterYourInterest(getAdapterPosition(), "bookmarks");
                }
            });

            tv_interested.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!status_interested_events) {
                        tv_interested.setTextColor(context.getResources().getColor(R.color.colorAccent));
                        status_interested_events = true;
//                        status_bookmark = 1;
//                        setinterest(venueId,status_bookmark);
                    } else {
                        tv_interested.setTextColor(context.getResources().getColor(R.color.text_color));
                        status_interested_events = false;
//                        status_bookmark = 0;
//                        setinterest(venueId,status_bookmark);

                    }

//                    click.method_AdapterYourInterest(getAdapterPosition(), "interested");
                }
            });

            iv_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    click.method_AdapterYourInterest(getAdapterPosition(), "check");
                }
            });
//click.upDateApiCall();
            iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    click.method_AdapterYourInterest(getAdapterPosition(),"item_view");
                }
            });

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_your_interest_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (eventList.size() != 0) {
            if (!eventList.get(position).getImageURL().equalsIgnoreCase("")) {
                Picasso.with(context).load(eventList.get(position).getImageURL()).into(holder.iv_image);
            }

            holder.tv_name.setText(eventList.get(position).getName());

            if (eventList.get(position).getReviewCount() != 0)
                holder.tv_review.setText(eventList.get(position).getReviewCount() + "Reviews");
            else holder.tv_review.setText("");

            if (eventList.get(position).getAvgRating() != null && !eventList.get(position).getAvgRating().equals("0"))
                holder.ratingBar.setRating(Float.parseFloat(eventList.get(position).getAvgRating()));
//            if (eventList.get(position).getAvgRating() != 0)
//                holder.ratingBar.setRating(Float.parseFloat(String.valueOf(eventList.get(position).getAvgRating())));


            if (eventList.get(position).getIsBookmarked() == 1) {
                status_bookmark_events = true;
                holder.iv_bookmarks.setImageResource(R.drawable.ic_favorites);
            } else {
                status_bookmark_events = false;
                holder.iv_bookmarks.setImageResource(R.drawable.ic_favorites_1);
            }
            if (eventList.get(position).getIsInterested() == 1) {
                holder.tv_interested.setTextColor(context.getResources().getColor(R.color.colorAccent));
            } else {
                holder.tv_interested.setTextColor(context.getResources().getColor(R.color.text_color));
            }

            holder.iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "event";
                    EventDetailsFragment fragment = new EventDetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("event_id", eventList.get(position).getEventId());
                    bundle.putString("type", type);
                    fragment.setArguments(bundle);
                    ((HomeActivity) context).replaceEventDetailsFragment(fragment);
                }
            });
//            notifyDataSetChanged();

//            holder.iv_image.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    click.viewDetails(getItemViewType(position));
////                    RestaurantDetails fragment = new RestaurantDetails();
////                    Bundle bundle = new Bundle();
////                    bundle.putInt("image", eventList.get(position).getCategoryId());
////                    fragment.setArguments(bundle);
////                    new BaseClass(context).callFragment1(fragment, fragment.getClass().getName(), manager);
//
//
//                }
//            });
        }
        if (position == eventList.size() - 1) {
//            click.lastIndex(position);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                RestaurantDetails fragment = new RestaurantDetails();
//                Bundle bundle = new Bundle();
//                bundle.putString("image", eventList.get(position).getImageURL());
////                bundle.putInt("image2", bottom_list_image_2.get(position));
////                bundle.putString("circle", bottom_list_circle.get(position));
////                bundle.putString("ad", bottom_list_ad.get(position));
//                bundle.putString("name", eventList.get(position).getName());
//                bundle.putInt("review", eventList.get(position).getReviewCount());
////                bundle.putString("km", bottom_list_distance.get(position));
////                bundle.putString("butterfly", bottom_list_butterfly.get(position));
////                bundle.putString("check", bottom_list_check.get(position));
////                bundle.putString("type", type);
//                fragment.setArguments(bundle);
//                new BaseClass(context).callFragment1(fragment, fragment.getClass().getName(), manager);

            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public interface Interface_AdapterYourInterest {
        void lastIndex(int pos);

        //        void upDateApiCall();
        void viewDetails(int pos);

        void method_AdapterYourInterest(int position, String type);
    }
}