package com.askonlinesolutions.wengage.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Model.Search;
import com.askonlinesolutions.wengage.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhib.
 * Contact Number : +91 9796173066
 */
public class SearchAdapter extends ArrayAdapter<Search> {

    private Context context;
    //    private String list[];
//    LayoutInflater inflater;
    private ArrayList<Search> list = new ArrayList<>();
    private ArrayList<Search> listAll = new ArrayList<>();
    private int layout;

    public SearchAdapter(Context context, int layout, ArrayList<Search> list) {
        super(context, layout, list);

        this.context = context;
        this.list = list;
        this.listAll = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Search getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        convertView = inflater.inflate(R.layout.layout_search, null);
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
        }

        TextView tv = convertView.findViewById(R.id.search_text);
        tv.setText(list.get(position).getText());


        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ((Search) resultValue).getText();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                List<Search> list1 = new ArrayList<>();
                if (constraint != null) {
                    for (Search search : listAll) {
                        if (search.getText().toLowerCase().startsWith(constraint.toString()))
                            list1.add(search);
                    }
                    filterResults.values = list1;
                    filterResults.count = list1.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                list.clear();
                if (results != null && results.count > 0) {
                    // avoids unchecked cast warning when using mDepartments.addAll((ArrayList<Department>) results.values);
                    for (Object object : (List<?>) results.values) {
                        if (object instanceof Search) {
                            list.add((Search) object);
                        }
                    }
                    notifyDataSetChanged();
                } else if (constraint == null) {
                    // no filter, add entire original list back in
                    list.addAll(listAll);
                    notifyDataSetInvalidated();
                }
            }
        };
    }
}