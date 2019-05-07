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
public class AdapterMyChat extends RecyclerView.Adapter<AdapterMyChat.MyViewHolder> {

    private ArrayList<Integer> arr_image = new ArrayList<>();
    private ArrayList<String> arr_number = new ArrayList<>();
    private ArrayList<String> arr_name = new ArrayList<>();
    private ArrayList<String> arr_city = new ArrayList<>();
    private ArrayList<String> arr_time = new ArrayList<>();
    private ArrayList<String> arr_text = new ArrayList<>();

    private Interface_AdapterMyChat click;

    public AdapterMyChat(ArrayList<Integer> arr_image, ArrayList<String> arr_number, ArrayList<String> arr_name,
                         ArrayList<String> arr_city, ArrayList<String> arr_time, ArrayList<String> arr_text,
                         Interface_AdapterMyChat click) {

        this.arr_image = arr_image;
        this.arr_number = arr_number;
        this.arr_name = arr_name;
        this.arr_city = arr_city;
        this.arr_time = arr_time;
        this.arr_text = arr_text;
        this.click = click;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_name, tv_city, tv_time, tv_text, tv_count;
        public ImageView iv_image;

        public MyViewHolder(View view) {
            super(view);

            tv_name = view.findViewById(R.id.item_adapter_my_chat_name);
            tv_city = view.findViewById(R.id.item_adapter_my_chat_city);
            tv_time = view.findViewById(R.id.item_adapter_my_chat_time);
            tv_text = view.findViewById(R.id.item_adapter_my_chat_text);
            tv_count = view.findViewById(R.id.item_adapter_my_chat_count);

            iv_image = view.findViewById(R.id.item_adapter_my_chat_image);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    click.method_AdapterMyChat(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_adapter_my_chat, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.iv_image.setImageResource(arr_image.get(position));
        holder.tv_name.setText(arr_name.get(position) + " ");
        holder.tv_city.setText(arr_city.get(position));
        holder.tv_time.setText(arr_time.get(position));
        holder.tv_text.setText(arr_text.get(position));

        if(!arr_number.get(position).equals("0")){
            holder.tv_count.setText(arr_number.get(position));
            holder.tv_count.setVisibility(View.VISIBLE);
        } else{
            holder.tv_count.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return arr_name.size();
    }

    public interface Interface_AdapterMyChat{

        public void method_AdapterMyChat(int pos);
    }

}