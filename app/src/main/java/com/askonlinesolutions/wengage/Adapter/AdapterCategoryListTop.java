package com.askonlinesolutions.wengage.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.R;

import java.util.ArrayList;

/**
 * Created by Rakhi.
 * Contact Number : +91 9958187463
 * Date: 12/10/2018
 */
public class AdapterCategoryListTop extends RecyclerView.Adapter<AdapterCategoryListTop.MyViewHolder> {

    private Context context;
    private ArrayList<String> cat_top = new ArrayList<>();
    private ArrayList<String> arr_count = new ArrayList<>();
    private boolean arr[];
    private InterfaceCategoryListTop click;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public LinearLayout layout;

        public MyViewHolder(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.tv_category_top);
            layout = (LinearLayout) view.findViewById(R.id.category_top_layout);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.click_CategoryListTop(getAdapterPosition());
                }
            });
        }
    }

    public AdapterCategoryListTop(Context context, ArrayList<String> cat_top, ArrayList<String> arr_count,
                                  InterfaceCategoryListTop click) {
        this.context = context;
        this.cat_top = cat_top;
        arr = new boolean[cat_top.size()];
        this.click = click;
        this.arr_count = arr_count;
//        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_list_top, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.textView.setText(cat_top.get(position));

        if(arr_count.get(position).equals("0")){
            holder.layout.setSelected(false);
            holder.textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        } else{
            holder.layout.setSelected(true);
            holder.textView.setTextColor(context.getResources().getColor(R.color.white));
        }

/*        holder.layout.setTag(position);
//        holder.textView.setTag(position);

            if(position==0){
//                holder.layout.setBackgroundResource(R.drawable.shape_round_accent);
                holder.layout.setSelected(true);
                holder.textView.setTextColor(context.getResources().getColor(R.color.white));
            }
            else{
                holder.layout.setSelected(false);
                holder.textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }
        for (int i=0; i<cat_top.size(); i++) {
            arr[i] = false;
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!holder.textView.getText().toString().equals("All"))
                {
                    if(!arr[(int) holder.layout.getTag()]){
//                    holder.layout.setBackgroundResource(R.drawable.shape_round_accent);
                        holder.layout.setSelected(true);
                        holder.textView.setTextColor(context.getResources().getColor(R.color.white));
                        arr[(int) holder.layout.getTag()] = true;
                    } else{
//                    holder.layout.setBackgroundResource(R.drawable.shape_round_category_top);
                        holder.layout.setSelected(false);
                        holder.textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                        arr[(int) holder.layout.getTag()] = false;
                    }
                }
                else{
                    notifyDataSetChanged();
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return cat_top.size();
    }

    public interface InterfaceCategoryListTop{
        public void click_CategoryListTop(int position);
    }
}