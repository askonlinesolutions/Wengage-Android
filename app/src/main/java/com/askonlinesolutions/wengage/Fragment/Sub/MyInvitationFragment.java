package com.askonlinesolutions.wengage.Fragment.Sub;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.askonlinesolutions.wengage.Adapter.AdapterMyInvitation;
import com.askonlinesolutions.wengage.Helper.CustomLayoutManager;
import com.askonlinesolutions.wengage.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyInvitationFragment extends Fragment {
    private ArrayList<Integer> profile_images = new ArrayList<>();
    private ArrayList<String> profile_names = new ArrayList<>();

    public MyInvitationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_invitation, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private RecyclerView rv;

    private void init(){

        createList();


        rv = getView().findViewById(R.id.fragment_my_inviitation_recycler);
        CustomLayoutManager manager1 = new CustomLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new AdapterMyInvitation(profile_images,profile_names));

    }

    private void createList() {

        profile_images.clear();
        profile_images.add(R.drawable.pink_one);
        profile_images.add(R.drawable.pink_two);
        profile_images.add(R.drawable.pink_three);
        profile_images.add(R.drawable.pink_four);
        profile_images.add(R.drawable.pink_five);

        profile_names.clear();
        profile_names.add("Kelly");
        profile_names.add("Lia");
        profile_names.add("Sandra");
        profile_names.add("Sarah");
        profile_names.add("Jennifer");
    }
}
