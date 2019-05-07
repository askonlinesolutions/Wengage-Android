package com.askonlinesolutions.wengage.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Model.EventCategoryModal;
import com.askonlinesolutions.wengage.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SubCategoryEventAdapter extends RecyclerView.Adapter<SubCategoryEventAdapter.MyViewHolder> {
    Context context;
    List<EventCategoryModal> categoryList;
    SubCategoryItemClickListner subCategoryItemClickListner;
    int categoryId;

    public SubCategoryEventAdapter(Context activity, List<EventCategoryModal> categoryList,
                                   SubCategoryItemClickListner subCategoryItemClickListner, int category_id) {

        context = activity;
        this.categoryList = categoryList;
        this.subCategoryItemClickListner = subCategoryItemClickListner;
        this.categoryId = category_id;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if (categoryList.get(position).getIcon() != null && !categoryList.get(position).getIcon().equalsIgnoreCase("")) {
            Picasso.with(context).load(categoryList.get(position).getIcon()).placeholder(R.drawable.ic_loading)
                    .into(holder.imageView);
        }

        //   Picasso.with(context).load(categoryList.get(position).getIcon()).placeholder(R.drawable.ic_tea).into(holder.imageView);
        holder.textView.setText(categoryList.get(position).getSubCategoryName());

    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        View bottom_line;
        LinearLayout item_layout;

        public MyViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.category_image);
            textView = view.findViewById(R.id.category_name);
            bottom_line = view.findViewById(R.id.item_bottom_line);
            item_layout = view.findViewById(R.id.item_layout);
            item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    subCategoryItemClickListner.onSubCategoryItemClick(getAdapterPosition());
                }
            });

        }
    }

    public interface SubCategoryItemClickListner {
        void onSubCategoryItemClick(int pos);
    }
}

