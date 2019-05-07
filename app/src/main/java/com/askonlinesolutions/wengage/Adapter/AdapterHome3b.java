package com.askonlinesolutions.wengage.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.askonlinesolutions.wengage.R;

import java.util.ArrayList;


public class AdapterHome3b extends RecyclerView.Adapter<AdapterHome3b.MyViewHolder> {

    ArrayList<Integer> restImages = new ArrayList<>();
    Context context;
    private FragmentManager manager;
    private String type;

    public AdapterHome3b(Context context, ArrayList<Integer> personImages, FragmentManager manager, String type) {
        this.context = context;
        this.restImages = personImages;
        this.manager = manager;
        this.type = type;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;

        public MyViewHolder(View view) {
            super(view);

            image = (ImageView) itemView.findViewById(R.id.image2);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_profile2, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.image.setImageResource(restImages.get(position));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                RestaurantDetails fragment = new RestaurantDetails();
                Bundle bundle = new Bundle();
                if (type.equals("venues")){
                    bundle.putInt("image", R.drawable.img6);
                    bundle.putInt("image2", R.drawable.res_1);
                    bundle.putString("circle", "yes");
                    bundle.putString("ad", "yes");
                    bundle.putString("name", "Chez Restaurant");
                    bundle.putString("review", "30");
                    bundle.putString("km", "2.8");
                    bundle.putString("butterfly", "yes");
                    bundle.putString("check", "yes");
                    bundle.putString("type", type);
                } else{
                    bundle.putInt("image", R.drawable.events_list_1);
                    bundle.putInt("image2", R.drawable.events_list_1);
                    bundle.putString("circle", "yes");
                    bundle.putString("ad", "yes");
                    bundle.putString("name", "Phantom of the Opera");
                    bundle.putString("review", "30");
                    bundle.putString("km", "2.8");
                    bundle.putString("butterfly", "yes");
                    bundle.putString("check", "yes");
                    bundle.putString("type", type);
                }
                fragment.setArguments(bundle);
                new BaseClass(context).callFragment1(fragment, fragment.getClass().getName(), manager);
           */ }
        });
    }

    @Override
    public int getItemCount() {
        return restImages.size();
    }


}

