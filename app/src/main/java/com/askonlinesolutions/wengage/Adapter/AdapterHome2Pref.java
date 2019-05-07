package com.askonlinesolutions.wengage.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Model.Response.SubCategoryResponse;
import com.askonlinesolutions.wengage.R;

import java.util.List;

/**
 * Created by Muhib.
 * Contact Number : +91 9796173066
 */
public class AdapterHome2Pref extends RecyclerView.Adapter<AdapterHome2Pref.MyViewHolder> {

    private List<SubCategoryResponse.PreferencesBean> preferences;
    private Interface_Home_2_Pref click;
    private Context context;

    public AdapterHome2Pref(List<SubCategoryResponse.PreferencesBean> preferences,
                            Interface_Home_2_Pref click, Context context) {
        this.preferences = preferences;
        this.click = click;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;

        public MyViewHolder(View view) {
            super(view);

            tv = view.findViewById(R.id.item_home_2_pref_tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.click_interface_home_2(getAdapterPosition());
                }
            });

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_2_pref, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tv.setText(preferences.get(position).getSubCategoryName());
        /*holder.tv.setBackgroundResource(R.drawable.shape_round_bg_2);*/
        holder.tv.setTextColor(context.getResources().getColor(R.color.text_color));
        Log.d("MyAdapter", position + "");
      /*  if(arr_clicked_position.get(position).equals("yes")) {
            holder.tv.setBackgroundResource(R.drawable.shape_round_accent);
            holder.tv.setTextColor(context.getResources().getColor(R.color.white));
            Log.d("MyAdapter", position + "");
        } else {
            holder.tv.setBackgroundResource(R.drawable.shape_round_bg_2);
            holder.tv.setTextColor(context.getResources().getColor(R.color.text_color));
        }*/
    }

    @Override
    public int getItemCount() {
        return preferences.size();
    }

    public interface Interface_Home_2_Pref {
        void click_interface_home_2(int position);
    }
}