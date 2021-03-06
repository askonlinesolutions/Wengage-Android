package com.askonlinesolutions.wengage.Activity.Main.dashboard.notificationAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.dashboard.dashboard_adapters.DashboardChatsAdapter;
import com.askonlinesolutions.wengage.Activity.Main.dashboard.vo.InviteResponse;
import com.askonlinesolutions.wengage.CallBacks.OnItemClickListener;
import com.askonlinesolutions.wengage.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class NotificationInviteAdapter  extends RecyclerView.Adapter<NotificationInviteAdapter.MyViewHolder> {



    private String[] dataSource;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private List<InviteResponse.NotificationsEntity> inviteNotificationList;

    public NotificationInviteAdapter(Context context, List<InviteResponse.NotificationsEntity> inviteNotificationList, OnItemClickListener onItemClickListener) {
//        dataSource = dataArgs;
        this.inviteNotificationList = inviteNotificationList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public NotificationInviteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_chats_list_items, parent, false);

        return new NotificationInviteAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(NotificationInviteAdapter.MyViewHolder holder, int position) {

            holder.name.setText(inviteNotificationList.get(position).getName());
            holder.city.setText(inviteNotificationList.get(position).getCity());
            holder.date.setText(inviteNotificationList.get(position).getCreatedAt());
            if (!inviteNotificationList.get(position).getImage().equalsIgnoreCase("") && inviteNotificationList.get(position).getImage() != null) {
                Glide.with(context).load(inviteNotificationList.get(position).getImage()).into(holder.img);
            }
            holder.main_layout.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        //return dataSource.length;
        if (inviteNotificationList.size() > 0) {
            return inviteNotificationList.size();
        } else
            return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, city, msg, date;
        ImageView img;
        LinearLayout main_layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.fragment_dashboard_name);
            city = (TextView) itemView.findViewById(R.id.city_name_tv);
//            msg = (TextView) itemView.findViewById(R.id.msg);
            date = (TextView) itemView.findViewById(R.id.date);
            main_layout = itemView.findViewById(R.id.main_layout);
            img = itemView.findViewById(R.id.recycler_top_profiles_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition(), "invite");
                }
            });
        }

    }




}
