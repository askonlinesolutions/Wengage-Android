package com.askonlinesolutions.wengage.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.R;

import java.util.ArrayList;

/**
 * Created by Muhib.
 * Contact Number : +91 9796173066
 */
public class AdapterCall extends RecyclerView.Adapter<AdapterCall.MyViewHolder> {


    ArrayList<Integer> profileImageList;
    ArrayList<String> profileNameList;
    ArrayList<String> event_name, date;
    ArrayList<Integer> status;

    public AdapterCall(ArrayList<Integer> profileImageList, ArrayList<String> profileNameList, ArrayList<String> event_name,
                       ArrayList<String> date, ArrayList<Integer> status) {
        this.profileImageList = profileImageList;
        this.profileNameList = profileNameList;
        this.status = status;
        this.event_name = event_name;
        this.date = date;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImege;
        TextView profileName, eventName, eventDate;
        Button pending;
        ImageView accept, reject;
        LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            profileImege = view.findViewById(R.id.item_call_image);
            profileName = view.findViewById(R.id.item_call_name);
            eventDate = view.findViewById(R.id.event_date);
            eventName = view.findViewById(R.id.event_name);
            accept = view.findViewById(R.id.accept_button);
            reject = view.findViewById(R.id.decline_button);
            pending = view.findViewById(R.id.request_pending);
            linearLayout = view.findViewById(R.id.button_layout);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_call, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (status.get(position) == 1) {
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.pending.setVisibility(View.GONE);
        } else {
            holder.linearLayout.setVisibility(View.GONE);
            holder.pending.setVisibility(View.VISIBLE);

        }
        holder.profileImege.setImageResource(profileImageList.get(position));
        holder.profileName.setText(profileNameList.get(position));
        holder.eventName.setText(event_name.get(position));
        holder.eventDate.setText(date.get(position));
    }

    @Override
    public int getItemCount() {
        return profileImageList.size();
    }
}