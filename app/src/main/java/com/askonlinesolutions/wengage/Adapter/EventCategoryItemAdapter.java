package com.askonlinesolutions.wengage.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.CompleteProfile2;
import com.askonlinesolutions.wengage.Model.EventCategoryModal;
import com.askonlinesolutions.wengage.Model.ItemClickStatusModel;
import com.askonlinesolutions.wengage.Model.Response.SubCategoryResponse;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.wengageInterface.CategoryItemClickListner;

import java.util.List;

public class EventCategoryItemAdapter extends RecyclerView.Adapter<EventCategoryItemAdapter.MyViewHolder> {

    List<EventCategoryModal> subCategoryLists;
    Context context;
    CategoryItemClickListner categoryItemClickListner;
    private boolean isSelected = false;
    List<ItemClickStatusModel> itemClickStatusModelList;


    public EventCategoryItemAdapter(Context context, List<EventCategoryModal> subCategoryList,
                                    List<ItemClickStatusModel> itemClickStatusModelList,
                                    CompleteProfile2 categoryItemClickListner) {
        this.subCategoryLists = subCategoryList;
        this.context = context;
        this.categoryItemClickListner = categoryItemClickListner;
        this.itemClickStatusModelList = itemClickStatusModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcategory_item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (subCategoryLists.get(position).getSubCategoryName() != null) {
            holder.textView.setText(subCategoryLists.get(position).getSubCategoryName());
        }

        holder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (subCategoryLists.get(position).getIsSelected() == 1) {
                    holder.item_layout.setBackground(context.getResources().getDrawable(R.drawable.shape_border_grey));
                    holder.textView.setTextColor(context.getResources().getColor(R.color.black));
                    itemClickStatusModelList.get(position).setStatus(false);
                    categoryItemClickListner.onSubCategoryItemClickWithName(subCategoryLists.get(position).getCategoryName(),
                            subCategoryLists.get(position).getSubCategoryName(), itemClickStatusModelList.get(position).isStatus());

                    categoryItemClickListner.onSubCategoryItemClick(subCategoryLists.get(position).getCategoryId(),
                            subCategoryLists.get(position).getSubCategoryId(),
                            itemClickStatusModelList.get(position).isStatus());
                } else {

                    subCategoryLists.get(position).setIsSelected(1);
                    holder.item_layout.setBackground(context.getResources().getDrawable(R.drawable.shape_filled_pink));
                    holder.textView.setTextColor(context.getResources().getColor(R.color.white));
                    itemClickStatusModelList.get(position).setStatus(true);
                    categoryItemClickListner.onSubCategoryItemClickWithName(subCategoryLists.get(position).getCategoryName(),
                            subCategoryLists.get(position).getSubCategoryName(),
                            itemClickStatusModelList.get(position).isStatus());

                    categoryItemClickListner.onSubCategoryItemClick(subCategoryLists.get(position).getCategoryId(),
                            subCategoryLists.get(position).getSubCategoryId(),
                            itemClickStatusModelList.get(position).isStatus());
                }
              /*  if (!itemClickStatusModelList.get(position).isStatus()) {


                } else {

                }*/

            }
        });


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return subCategoryLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout item_layout;

        public MyViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.sub_category_name);
            item_layout = view.findViewById(R.id.item_layout);
        }
    }
}
