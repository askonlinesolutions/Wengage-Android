package com.askonlinesolutions.wengage.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Fragment.Main.venue.UserAllListResposne;
import com.askonlinesolutions.wengage.Fragment.UserProfileFragment;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.utils.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.mukesh.tinydb.TinyDB;

import java.util.List;

/**
 * Created by Rakhi.
 * Contact Number : +91 9958187463
 */
public class AdapterTopProfiles extends RecyclerView.Adapter<AdapterTopProfiles.MyViewHolder> {

    private Context context;
    private List<UserAllListResposne.UsersListBean.InfluencerBean> influenceUsers;
    private List<UserAllListResposne.UsersListBean.OnlineUsersBean> onlineUsers;
    private String st = "";

    public AdapterTopProfiles(Context context, List<UserAllListResposne.UsersListBean.OnlineUsersBean> onlineUsers,
                              List<UserAllListResposne.UsersListBean.InfluencerBean> influenceUsers,
                              String st) {
        this.context = context;
        this.onlineUsers = onlineUsers;
        this.influenceUsers = influenceUsers;
        this.st = st;

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
                .load(R.drawable.ic_loading)
                .into(holder.imgBack);
        holder.imgBack.setVisibility(View.VISIBLE);
        if (st.equals("1")) {
            if (onlineUsers.get(position).getPhotoURL() != null)
                Glide.with(context)
                        .load(onlineUsers.get(position).getPhotoURL())
                        .apply(RequestOptions.circleCropTransform())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                        Target<Drawable> target, boolean isFirstResource) {
                                holder.imgBack.setVisibility(View.GONE);

                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                           DataSource dataSource, boolean isFirstResource) {
                                holder.imgBack.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .into(holder.image);
            Log.d("TAG", "onBindViewHolder: " + onlineUsers.get(position).getPhotoURL());
            holder.image.setBackgroundResource(R.drawable.pink_circle);
            if (onlineUsers.get(position).getUserName() != null) {
                holder.tv_name.setText(onlineUsers.get(position).getKnownByName());
            }
            if (onlineUsers.get(position).getCity() != null) {
                holder.tv_country.setText(onlineUsers.get(position).getCity());
            }

            holder.iv_check.setImageResource(R.drawable.ic_circle);

        } else {
            if (influenceUsers.get(position).getPhotoURL() != null &&
                    !influenceUsers.get(position).getPhotoURL().equals("")) {

                Glide.with(context)
                        .load(influenceUsers.get(position).getPhotoURL())
                        .apply(RequestOptions.circleCropTransform())
                        .apply(RequestOptions.placeholderOf(R.drawable.profileg))
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                        Target<Drawable> target, boolean isFirstResource) {
                                holder.imgBack.setVisibility(View.GONE);

                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                           DataSource dataSource, boolean isFirstResource) {
                                holder.imgBack.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .into(holder.image);
            } else {
                holder.image.setImageResource(R.drawable.profileg);
            }
            holder.image.setBackgroundResource(R.drawable.pink_circle);
            if (influenceUsers.get(position).getUserName() != null) {
                holder.tv_name.setText(influenceUsers.get(position).getUserName());
            }
            if (influenceUsers.get(position).getCity() != null) {
                holder.tv_country.setText(influenceUsers.get(position).getCity());
            }
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TinyDB tinyDB = new TinyDB(context);
                tinyDB.putInt(Constants.VENUES_USER_ID, onlineUsers.get(position).getUserId());
                UserProfileFragment fragment = new UserProfileFragment();
                ((HomeActivity) context).replaceUserProfileFragment(fragment);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (st.equals("1")) {
            return onlineUsers.size();
        } else {
            return influenceUsers.size();
        }
    }
}