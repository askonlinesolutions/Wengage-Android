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
public class AdapterChatRooms extends RecyclerView.Adapter<AdapterChatRooms.MyViewHolder> {

    private ArrayList<Integer> arr_image = new ArrayList<>();
    private ArrayList<String> arr_title = new ArrayList<>();
    private ArrayList<String> arr_time = new ArrayList<>();
    private ArrayList<String> active_members = new ArrayList<>();

    private Interface_AdapterChatRooms click;

    public AdapterChatRooms(ArrayList<Integer> arr_image, ArrayList<String> arr_title, ArrayList<String> arr_time,ArrayList<String> active_members,
                            Interface_AdapterChatRooms click) {

        this.arr_image = arr_image;
        this.arr_title = arr_title;
        this.arr_time = arr_time;
        this.click = click;
        this.active_members=active_members;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_title, tv_time,tv_active_members;
        public ImageView iv_image;

        public MyViewHolder(View view) {
            super(view);

            tv_title = view.findViewById(R.id.item_chat_rooms_title);
            tv_time = view.findViewById(R.id.item_chat_rooms_time);
            tv_active_members=view.findViewById(R.id.item_chat_rooms_active_member);
            iv_image = view.findViewById(R.id.item_chat_rooms_image);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    click.method_AdapterChatRooms(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_rooms, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.iv_image.setImageResource(arr_image.get(position));
        holder.tv_title.setText(arr_title.get(position));
        holder.tv_time.setText(arr_time.get(position));
        holder.tv_active_members.setText("Active Members :"+active_members.get(position));

    }

    @Override
    public int getItemCount() {
        return arr_title.size();
    }

    public interface Interface_AdapterChatRooms{

        public void method_AdapterChatRooms(int pos);
    }

}