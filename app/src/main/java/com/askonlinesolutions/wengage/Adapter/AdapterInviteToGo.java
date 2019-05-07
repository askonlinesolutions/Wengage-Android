package com.askonlinesolutions.wengage.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.askonlinesolutions.wengage.R;

/**
 * Created by Muhib.
 * Contact Number : +91 9796173066
 */
public class AdapterInviteToGo extends RecyclerView.Adapter<AdapterInviteToGo.MyViewHolder> {

    public AdapterInviteToGo() {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_invite_to_go, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}