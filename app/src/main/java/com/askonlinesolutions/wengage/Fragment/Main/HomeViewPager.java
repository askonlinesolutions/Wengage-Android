package com.askonlinesolutions.wengage.Fragment.Main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.askonlinesolutions.wengage.Adapter.AdapterHomeCategory;
import com.askonlinesolutions.wengage.Adapter.AdapterTopProfiles;
import com.askonlinesolutions.wengage.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeViewPager extends Fragment {

    public HomeViewPager() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_view_pager, container, false);
    }

    private RecyclerView rv;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rv = (RecyclerView) getView().findViewById(R.id.recycler_category);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        rv.setLayoutManager(gridLayoutManager);

        setMyAdapter();

    }
    private void setMyAdapter(){
        AdapterHomeCategory adapter = new AdapterHomeCategory(getContext());
        rv.setAdapter(adapter);
    }
}
