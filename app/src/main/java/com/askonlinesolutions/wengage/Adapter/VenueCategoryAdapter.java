package com.askonlinesolutions.wengage.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Model.CategoryList;
import com.askonlinesolutions.wengage.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VenueCategoryAdapter extends RecyclerView.Adapter<VenueCategoryAdapter.MyViewHolder> {
    Context context;
    List<CategoryList> categoryList;
    CategoryItemClickListner categoryItemClickListner;

    public VenueCategoryAdapter(Context activity, List<CategoryList> categoryList, CategoryItemClickListner categoryItemClickListner) {

        context = activity;
        this.categoryList = categoryList;
        //categoryItemClickListner = (CategoryItemClickListner) context;
        this.categoryItemClickListner = categoryItemClickListner;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (categoryList.get(position).getIcon() != null && !categoryList.get(position).getIcon().equalsIgnoreCase("")) {
            Picasso.with(context).load(categoryList.get(position).getIcon())
                    .placeholder(R.drawable.ic_loading).into(holder.imageView);
        }
        holder.textView.setText(categoryList.get(position).getCategoryName());
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
                    categoryItemClickListner.onItemClick(getAdapterPosition());
                }
            });

        }
    }

    public interface CategoryItemClickListner {
        public void onItemClick(int categoryId);
    }
}
