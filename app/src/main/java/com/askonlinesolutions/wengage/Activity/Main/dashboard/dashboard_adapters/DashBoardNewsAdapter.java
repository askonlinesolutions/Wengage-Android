package com.askonlinesolutions.wengage.Activity.Main.dashboard.dashboard_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Model.Response.NotificationModal;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.utils.Image;
import com.bumptech.glide.Glide;

import java.util.List;

public class DashBoardNewsAdapter extends RecyclerView.Adapter<DashBoardNewsAdapter.MyViewHolder> {


    private List<NotificationModal.NotificationsBean> newList;
    private Context context;

    public DashBoardNewsAdapter(Context context, List<NotificationModal.NotificationsBean> newList) {
//        dataSource = dataArgs;
        this.newList = newList;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_item, parent, false);

        return new DashBoardNewsAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //  holder.textView.setText(dataSource[position]);
        if (newList.get(position).getIsRead() == 0) {
            holder.nameTV.setText(newList.get(position).getTitle());
            holder.news_tv.setText(newList.get(position).getContent());
            holder.main_layout.setVisibility(View.VISIBLE);
        } else {
//            newList.remove(position);
//            notifyItemRemoved(position);
//            notifyItemRangeChanged(position,newList.size());
//            notifyDataSetChanged();
//            holder.main_layout.setVisibility(View.GONE);
//            removeAt(position);
        }


    }
    private void removeAt(int position) {
        newList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, newList.size());
    }

    @Override
    public int getItemCount() {
        //return dataSource.length;
        if (newList.size() > 0) {
            return newList.size();
        } else
            return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView news_tv, nameTV, addressTv;
        ImageView img;
        LinearLayout main_layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            news_tv = itemView.findViewById(R.id.news_tv1);
            nameTV = itemView.findViewById(R.id.title);
            main_layout = itemView.findViewById(R.id.main_layout);
//            addressTv = itemView.findViewById(R.id.address);
//            img = itemView.findViewById(R.id.img);
        }

    }
}
