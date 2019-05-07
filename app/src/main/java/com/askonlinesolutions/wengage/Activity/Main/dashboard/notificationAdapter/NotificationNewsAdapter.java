package com.askonlinesolutions.wengage.Activity.Main.dashboard.notificationAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.dashboard.dashboard_adapters.DashBoardNewsAdapter;
import com.askonlinesolutions.wengage.Model.Response.NotificationModal;
import com.askonlinesolutions.wengage.R;

import java.util.List;

public class NotificationNewsAdapter extends RecyclerView.Adapter<NotificationNewsAdapter.MyViewHolder> {


    private List<NotificationModal.NotificationsBean> newList;
    private Context context;

    public NotificationNewsAdapter(Context context, List<NotificationModal.NotificationsBean> newList) {
//        dataSource = dataArgs;
        this.newList = newList;
        this.context = context;
    }


    @Override
    public NotificationNewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_item, parent, false);

        return new NotificationNewsAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(NotificationNewsAdapter.MyViewHolder holder, int position) {
        //  holder.textView.setText(dataSource[position]);
//        if (newList.get(position).getIsRead()==0)
//        {
        holder.nameTV.setText(newList.get(position).getTitle());
        holder.news_tv.setText(newList.get(position).getContent());
//        }else {
//
//        }



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

        public MyViewHolder(View itemView) {
            super(itemView);
            news_tv = itemView.findViewById(R.id.news_tv1);
            nameTV = itemView.findViewById(R.id.title);
//            addressTv = itemView.findViewById(R.id.address);
//            img = itemView.findViewById(R.id.img);
        }

    }

}
