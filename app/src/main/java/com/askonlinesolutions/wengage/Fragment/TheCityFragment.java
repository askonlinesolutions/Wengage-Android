package com.askonlinesolutions.wengage.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.utils.Constants;
import com.mukesh.tinydb.TinyDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class TheCityFragment extends Fragment implements View.OnClickListener {
    private RecyclerView theCityRecycler, categoryRecycler;
    private TextView titleTv;
    private LinearLayout home3_cross;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.the_city_fragment, container, false);
        TinyDB tinyDB = new TinyDB(getActivity());
        titleTv = view.findViewById(R.id.titleTv);
        if (tinyDB.getString(Constants.FROM_COMING).equals("TheCity")) {
            titleTv.setText("The city");
        } else {
            titleTv.setText("Dining");
        }

        home3_cross = view.findViewById(R.id.home3_cross);
        theCityRecycler = view.findViewById(R.id.theCityRecycler);
        theCityRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        EditPreferenceAdapter dayWiseAdapter = new EditPreferenceAdapter();
        theCityRecycler.setAdapter(dayWiseAdapter);
        categoryRecycler = view.findViewById(R.id.categoryRecycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        categoryRecycler.setLayoutManager(gridLayoutManager);
        home3_cross.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        getActivity().onBackPressed();
    }

    class EditPreferenceAdapter extends RecyclerView.Adapter<EditPreferenceAdapter.ViewHolder> {


        @Override
        public EditPreferenceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.the_city_adapter, parent, false);
            return new EditPreferenceAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final EditPreferenceAdapter.ViewHolder holder, final int position) {


        }


        @Override
        public int getItemCount() {
            return 10;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private RecyclerView sub_recycler_view;

            public ViewHolder(View itemView) {

                super(itemView);
                sub_recycler_view = itemView.findViewById(R.id.sub_recycler_view);

            }
        }
    }

}
