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

public class ContactRequestAdapter extends RecyclerView.Adapter<ContactRequestAdapter.MyViewHolder> {

    ArrayList<Integer> profile_image;
    ArrayList<String> profile_name;
    ArrayList<Integer> status;



    public ContactRequestAdapter(ArrayList<Integer> profile_image,ArrayList<String> profile_name,ArrayList<Integer> status)
    {
        this.profile_image=profile_image;
         this.profile_name=profile_name;
         this.status=status;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_request,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactRequestAdapter.MyViewHolder holder, int position) {

        if (status.get(position) == 1) {
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.pending.setVisibility(View.GONE);
        } else {
            holder.linearLayout.setVisibility(View.GONE);
            holder.pending.setVisibility(View.VISIBLE);

        }
        holder.profileImege.setImageResource(profile_image.get(position));
        holder.profileName.setText(profile_name.get(position));

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImege;
        TextView profileName;
        Button accept, reject, pending;
        LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            profileImege = view.findViewById(R.id.item_call_image);
            profileName = view.findViewById(R.id.item_call_name);
            accept = view.findViewById(R.id.accept_button);
            reject = view.findViewById(R.id.decline_button);
            pending = view.findViewById(R.id.request_pending);
            linearLayout = view.findViewById(R.id.button_layout);
        }

    }
}
