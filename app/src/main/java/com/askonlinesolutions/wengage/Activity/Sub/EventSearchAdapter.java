package com.askonlinesolutions.wengage.Activity.Sub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.vo.GeneralSearchResponse;
import com.askonlinesolutions.wengage.R;

import java.util.List;

public class EventSearchAdapter extends RecyclerView.Adapter<EventSearchAdapter.MyViewHolder> {

    List<GeneralSearchResponse.SearchedDataList> searchedDataLists;
    private String[] dataSource;
    private OnItemClickListener onItemClickListener;
    Context context;

    public EventSearchAdapter(Context context, List<GeneralSearchResponse.SearchedDataList> searchListResponses,OnItemClickListener onItemClickListener) {
//        dataSource = dataArgs;
        this.searchedDataLists = searchListResponses;
        this.context = context;
        this.onItemClickListener=onItemClickListener;
    }


    @Override
    public EventSearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item_list, parent, false);

        return new EventSearchAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(EventSearchAdapter.MyViewHolder holder, final int position) {
        //  holder.textView.setText(dataSource[position]);
        if (searchedDataLists != null) {
            holder.searchDataTv.setText(searchedDataLists.get(position).getName());
            holder.typeTv.setText("("+(searchedDataLists.get(position).getType())+")");

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position,(searchedDataLists.get(position).getType()));
            }
        });
//        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        //return dataSource.length;
        return searchedDataLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView searchDataTv, typeTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            searchDataTv = (TextView) itemView.findViewById(R.id.search_data_tv);
            typeTv = (TextView) itemView.findViewById(R.id.type_tv);
        }

    }

    public interface OnItemClickListener {

//        void viewDetails(int pos);

        void onItemClick(int position, String type);
    }

}
