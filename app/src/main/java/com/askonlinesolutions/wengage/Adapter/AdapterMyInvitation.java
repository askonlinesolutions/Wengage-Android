package com.askonlinesolutions.wengage.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.askonlinesolutions.wengage.R;

import java.util.ArrayList;

/**
 * Created by Muhib.
 * Contact Number : +91 9796173066
 */
public class AdapterMyInvitation extends RecyclerView.Adapter<AdapterMyInvitation.MyViewHolder> {

    ArrayList<Integer> profileImageList;
    ArrayList<String> profileNameList;

    public AdapterMyInvitation(ArrayList<Integer> profileImageList, ArrayList<String> profileNameList) {
        this.profileImageList = profileImageList;
        this.profileNameList = profileNameList;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView profileImege;
        TextView profileName;

        public MyViewHolder(View view) {
            super(view);
            profileImege = view.findViewById(R.id.item_my_invitation_image);
            profileName = view.findViewById(R.id.item_my_invitation_name);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_invitation, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.profileImege.setImageResource(profileImageList.get(position));
        holder.profileName.setText(profileNameList.get(position));

    }

    @Override
    public int getItemCount() {
        return profileImageList.size();
    }
}