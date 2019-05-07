package com.askonlinesolutions.wengage.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Model.EventCategoryModal;
import com.askonlinesolutions.wengage.Model.Response.SubCategoryResponse;
import com.askonlinesolutions.wengage.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    List<EventCategoryModal> completeProfile2Model;
    Context context;
    List<String> list;
    LinearLayoutManager linearLayoutManager;
    public static SubDetailAdapter subDetailAdapter;

    public EventAdapter(Context context, List<EventCategoryModal> completeProfile2Model) {
        this.context = context;
        this.completeProfile2Model = completeProfile2Model;
        this.list = list;
    }

    @Override
    public EventAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_layout, parent, false);

        return new EventAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventAdapter.MyViewHolder holder, int position) {

        //   holder.image.setImageResource();
        for (int i = 0; i > completeProfile2Model.size(); i++) {
            if (completeProfile2Model.get(i).getIsSelected() == 1) {
                holder.category_name.setText(list.get(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        return completeProfile2Model.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RecyclerView sub_recyclerView, sub_event_recycler_view;
        TextView category_name;
        ImageView image;

        public MyViewHolder(View view) {
            super(view);

            sub_recyclerView = view.findViewById(R.id.sub_recycler_view);
            sub_event_recycler_view = view.findViewById(R.id.sub_event_recycler_view);
            category_name = view.findViewById(R.id.category_name);
            image = view.findViewById(R.id.category_image);


        }
    }
}
