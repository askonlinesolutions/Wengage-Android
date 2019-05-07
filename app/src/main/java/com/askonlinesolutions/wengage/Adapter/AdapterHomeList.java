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
import android.widget.RatingBar;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Fragment.Main.event.EventListResponse;
import com.askonlinesolutions.wengage.Fragment.Main.vo.VeneuHomeListResponse;
import com.askonlinesolutions.wengage.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by Rakhi.
 * Contact Number : +91 9958187463
 */
public class AdapterHomeList extends RecyclerView.Adapter<AdapterHomeList.MyViewHolder> {

    private Context context;
    private List<VeneuHomeListResponse.VenuesHomeList> venuesHomeLists;
    private HomeVenuListInterface homeVenuListInterface;
    private List<EventListResponse.EventsListBean> eventsList;
    private String type;

    public AdapterHomeList(Context context, List<VeneuHomeListResponse.VenuesHomeList> venuesHomeLists,
                           HomeVenuListInterface homeVenuListInterface, List<EventListResponse.EventsListBean> eventsList,
                           String type) {
        this.context = context;
        this.venuesHomeLists = venuesHomeLists;
        this.homeVenuListInterface = homeVenuListInterface;
        this.eventsList = eventsList;
        this.type = type;
    }

    public interface HomeVenuListInterface {
        //        void lastIndex(int pos);
        void viewDetails(int pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv, img_progress, greenDot, butterfly;
        TextView txtTitle, txtReview;
        RatingBar ratingBar;

        public MyViewHolder(View view) {
            super(view);
            img_progress = view.findViewById(R.id.item_home_list_progress);
            greenDot = view.findViewById(R.id.greenDot);
            iv = view.findViewById(R.id.home_venue_image);
            txtTitle = view.findViewById(R.id.venue_city_name);
            txtReview = view.findViewById(R.id.venue_review_count);
            ratingBar = view.findViewById(R.id.venue_rating);
            butterfly = view.findViewById(R.id.butterfly);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (type != null) {
                        if (type.equals("event")) {
                            homeVenuListInterface.viewDetails(getAdapterPosition());

                        } else if (type.equals("venue")) {
                            homeVenuListInterface.viewDetails(getAdapterPosition());
                        }
                    } else {
                        homeVenuListInterface.viewDetails(getAdapterPosition());
                    }
                }
            });

            Glide.with(context).asGif()
                    .load(R.drawable.load)
                    .into(img_progress);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        if (type != null) {
            if (type.equals("venue")) {
                if (venuesHomeLists.size() != 0) {
                    if (venuesHomeLists.get(position).getRecommendByWengage() == 1) {
                        holder.butterfly.setVisibility(View.VISIBLE);
                    } else {
                        holder.butterfly.setVisibility(View.GONE);
                    }
                    if (!venuesHomeLists.get(position).getImageURL().equalsIgnoreCase("")) {
                        Glide.with(context)
                                .load(venuesHomeLists.get(position).getImageURL())
                                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                                .apply(RequestOptions.skipMemoryCacheOf(true))
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                                Target<Drawable> target, boolean isFirstResource) {
                                        holder.img_progress.setVisibility(View.GONE);

                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                                   DataSource dataSource, boolean isFirstResource) {
                                        holder.img_progress.setVisibility(View.GONE);
                                        return false;
                                    }
                                })
                                .into(holder.iv);

                    }
                    if (venuesHomeLists.get(position).getReviewCount() != null && !venuesHomeLists.get(position).getReviewCount().equals("0"))
                        holder.txtReview.setText(venuesHomeLists.get(position).getReviewCount() + "Reviews");
                    else holder.txtReview.setText("");
                    holder.txtTitle.setText(venuesHomeLists.get(position).getName());
                    if (venuesHomeLists.get(position).getAvgRating() != null && !venuesHomeLists.get(position).getAvgRating().equals("0"))
                        holder.ratingBar.setRating(Float.parseFloat(venuesHomeLists.get(position).getAvgRating()));

                    if (venuesHomeLists.get(position).getIsInterested() == 1) {
                        holder.greenDot.setVisibility(View.VISIBLE);

                    } else
                        holder.greenDot.setVisibility(View.GONE);
                }
                venuesHomeLists.size();
            } else if (type.equals("event")) {
                if (eventsList.size() != 0) {
                    if (eventsList.get(position).getRecommendByWengage() == 1) {
                        holder.butterfly.setVisibility(View.VISIBLE);
                    } else {
                        holder.butterfly.setVisibility(View.GONE);
                    }
                    if (!eventsList.get(position).getImageURL().equalsIgnoreCase("")) {
                        Glide.with(context)
                                .load(eventsList.get(position).getImageURL())
                                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                                .apply(RequestOptions.skipMemoryCacheOf(true))
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                                Target<Drawable> target, boolean isFirstResource) {
                                        holder.img_progress.setVisibility(View.GONE);

                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                                   DataSource dataSource, boolean isFirstResource) {
                                        holder.img_progress.setVisibility(View.GONE);
                                        return false;
                                    }
                                })
                                .into(holder.iv);
                    }

                    if (eventsList.get(position).getReviewCount() != null && !eventsList.get(position).getReviewCount().equals("0"))
                        holder.txtReview.setText(eventsList.get(position).getReviewCount() + "Reviews");
                    else holder.txtReview.setText("");
                    holder.txtTitle.setText(eventsList.get(position).getName());
                    if (eventsList.get(position).getAvgRating() != null && !eventsList.get(position).getAvgRating().equals("0"))
                        holder.ratingBar.setRating(Float.parseFloat(eventsList.get(position).getAvgRating()));
                }
                if (eventsList.get(position).getIsInterested() == 1) {
                    holder.greenDot.setVisibility(View.VISIBLE);

                } else
                    holder.greenDot.setVisibility(View.GONE);
                eventsList.size();
            }
        }
    }

    @Override
    public int getItemCount() {
        if (type != null && type.equals("event")) {
            return eventsList.size();
        } else if (type != null && type.equals("venue")) {
            return venuesHomeLists.size();
        } else return venuesHomeLists.size();
    }
}