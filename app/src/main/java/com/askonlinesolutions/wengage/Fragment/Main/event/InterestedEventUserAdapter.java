package com.askonlinesolutions.wengage.Fragment.Main.event;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Model.EventDetailEventResponse;
import com.askonlinesolutions.wengage.Model.Response.VenueDetailsResponse;
import com.askonlinesolutions.wengage.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by Rakhi.
 * Contact Number : +91 9958187463
 */
public class InterestedEventUserAdapter extends RecyclerView.Adapter<InterestedEventUserAdapter.MyViewHolder> {

    private Context context;
    List<EventDetailEventResponse.EventDataBean.InterestedUsersBean> interestedUsers;

    public InterestedEventUserAdapter(Context context, List<EventDetailEventResponse.EventDataBean.InterestedUsersBean> interestedUsers) {
        this.context = context;
        this.interestedUsers = interestedUsers;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_country;
        public ImageView image, iv_check, imgBack;

        public MyViewHolder(View view) {
            super(view);

            image = view.findViewById(R.id.recycler_top_profiles_image);
            tv_name = view.findViewById(R.id.recycler_top_profiles_names);
            tv_country = view.findViewById(R.id.recycler_top_profiles_country);
            iv_check = view.findViewById(R.id.top_profile_check);
            imgBack = view.findViewById(R.id.recycler_top_profiles_image_back);
            iv_check.setVisibility(View.GONE);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_top_profile, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Glide.with(context)
                .load(interestedUsers.get(position).getPhotoURL())
                .apply(RequestOptions.placeholderOf(R.drawable.load))
                .into(holder.image);
        holder.imgBack.setImageDrawable(context.getResources().getDrawable(R.drawable.pink_circle));
        if (interestedUsers.get(position).getKnownByName() != null) {
            holder.tv_name.setText(interestedUsers.get(position).getKnownByName());
        }
        if (interestedUsers.get(position).getCity() != null) {
            holder.tv_country.setText(interestedUsers.get(position).getCity());
        }


    }

    @Override
    public int getItemCount() {
       return interestedUsers.size();
    }
}