package com.askonlinesolutions.wengage.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.askonlinesolutions.wengage.R;

import java.util.ArrayList;

/**
 * Created by Muhib.
 * Contact Number : +91 9796173066
 */
public class AdapterHome2List extends RecyclerView.Adapter<AdapterHome2List.MyViewHolder> {

    private Context context;
    private ArrayList<Integer> list_images;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv;

        public MyViewHolder(View view) {
            super(view);

            iv = (ImageView) view.findViewById(R.id.home_list_image);
        }
    }


    public AdapterHome2List(Context context/*, ArrayList<Integer> list_images*/) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home2_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

//        holder.iv.setImageResource(list_images.get(position));

    }

    @Override
    public int getItemCount() {
        return 6;
    }
}