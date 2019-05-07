package com.askonlinesolutions.wengage.Fragment.Main.venue;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.askonlinesolutions.wengage.R;

/**
 * Created by Rakhi.
 * Contact Number : +91 9958187463
 */
public class SubCatVenueHomeAdapter extends RecyclerView.Adapter<SubCatVenueHomeAdapter.MyViewHolder> {

    private Context context;


    public SubCatVenueHomeAdapter(Context context) {
        this.context = context;
    }

    public interface HomeVenuListInterface{
        void lastIndex(int pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv;
        TextView txtTitle, txtReview;
        RatingBar ratingBar;

        public MyViewHolder(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.home_venue_image);
            txtTitle = view.findViewById(R.id.venue_city_name);
            txtReview = view.findViewById(R.id.venue_review_count);
            ratingBar = view.findViewById(R.id.venue_rating);
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


    }

    @Override
    public int getItemCount() {
        return 10;
    }
}