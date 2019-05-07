package com.askonlinesolutions.wengage.Fragment.Main.profile;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.askonlinesolutions.wengage.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AdapterPrefrences extends RecyclerView.Adapter<AdapterPrefrences.MyViewHolder> {
    List<UserProfile.UserDataBean.PreferencesBean> preferences;
    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);

            name = (TextView) itemView.findViewById(R.id.category_name);
            image = (ImageView) itemView.findViewById(R.id.category_image);
        }
    }

    public AdapterPrefrences(List<UserProfile.UserDataBean.PreferencesBean> preferences, Context context) {
        this.preferences = preferences;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_profile1, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (preferences.size() != 0) {
            Picasso.with(context)
                    .load(preferences.get(position).getIcon())
                    .placeholder(context.getDrawable(R.drawable.ic_loading))
                    .into(holder.image);
            holder.name.setText(preferences.get(position).getSubCategoryName());
        }
    }

    @Override
    public int getItemCount() {
        return preferences.size();
    }


}
