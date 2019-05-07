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
import com.askonlinesolutions.wengage.Model.Response.CategoryResponse;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.wengageInterface.CategoryItemClickListner;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    List<CategoryList> categoryList;
    Context context;
    CategoryItemClickListner categoryItemClickListner;
    int selectedItemPosition = -1;

    public CategoryAdapter(Context context, List<CategoryList> categoryList, CategoryItemClickListner categoryItemClickListner) {
        this.categoryList = categoryList;
        this.context = context;
        this.categoryItemClickListner = categoryItemClickListner;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

       /* if (position == 0) {
            holder.bottom_line.setVisibility(View.VISIBLE);
        }*/
        holder.bottom_line.setVisibility(View.VISIBLE);
        if (categoryList.get(position).getIcon() != null && !categoryList.get(position).getIcon().equalsIgnoreCase("")) {
            Picasso.with(context).load(categoryList.get(position).getIcon()).into(holder.imageView);
        }
        if (categoryList.get(position).getCategoryName() != null)
            holder.textView.setText(categoryList.get(position).getCategoryName());

        if (selectedItemPosition == position) {
            holder.bottom_line.setVisibility(View.VISIBLE);
        } else {
            holder.bottom_line.setVisibility(View.GONE);
        }

        holder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItemPosition = position;
                notifyDataSetChanged();
                categoryItemClickListner.onCategoryItemClick(categoryList.get(position).getCategoryId());
            }
        });


    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
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
        }
    }
}
