package com.askonlinesolutions.wengage.Fragment.Sub;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.askonlinesolutions.wengage.Adapter.AdapterInviteToGo;
import com.askonlinesolutions.wengage.Helper.CustomLayoutManager;
import com.askonlinesolutions.wengage.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InviteToGoFragment extends Fragment {

    public InviteToGoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invite_to_go, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private RecyclerView rv;
    private void init(){

        rv = getView().findViewById(R.id.fragment_invite_to_go_recycler);
        CustomLayoutManager manager1 = new CustomLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new AdapterInviteToGo());
    }
}
