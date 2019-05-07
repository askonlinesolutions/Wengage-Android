package com.askonlinesolutions.wengage.Fragment.Main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Adapter.AdapterTopProfiles;
import com.askonlinesolutions.wengage.Adapter.AdapterViewPagerInTheKnow;
import com.askonlinesolutions.wengage.Fragment.Sub.CallFragment;
import com.askonlinesolutions.wengage.Fragment.Sub.ChatRoomsFragment;
import com.askonlinesolutions.wengage.Fragment.Sub.ContactsFragment;
import com.askonlinesolutions.wengage.Fragment.Sub.MyChatFragment;
import com.askonlinesolutions.wengage.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment implements View.OnClickListener {

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    public static ViewPager viewPager;
    public static TabLayout tabLayout;
    private LinearLayout layout_call, layout_arrow;
    private ImageView iv_arrow;

    private RecyclerView rv_top_profiles;
    private AdapterTopProfiles adapter_top_profiles;
    private ArrayList<Integer> profile_images = new ArrayList<>();
    private ArrayList<Integer> profile_images_2 = new ArrayList<>();
    private ArrayList<String> profile_names = new ArrayList<>();
    private ArrayList<String> profile_country = new ArrayList<>();
    private ArrayList<String> profile_check = new ArrayList<>();

    public static TextView tv_top_name;
    public static int page_position = 0;

    private void init() {

        tv_top_name = getView().findViewById(R.id.fragment_chat_top_names);

        // ViewPager & TabLayout
        viewPager = getView().findViewById(R.id.fragment_chat_view_pager);
        tabLayout = getView().findViewById(R.id.fragment_chat_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

        //Top arrow and call
        layout_call = getView().findViewById(R.id.fragment_chat_layout_call);
        layout_arrow = getView().findViewById(R.id.fragment_chat_layout_arrow);
        iv_arrow = getView().findViewById(R.id.fragment_chat_arrow);
        layout_call.setOnClickListener(this);
        layout_arrow.setOnClickListener(this);

        //Top RecyclerView
        rv_top_profiles = (RecyclerView) getView().findViewById(R.id.fragment_chat_recycler_top_profiles);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                false);
        rv_top_profiles.setLayoutManager(horizontalLayoutManagaer);

        setProfileAdapter();
    }

    public static AdapterViewPagerInTheKnow adapter;

    private void setupViewPager(ViewPager viewPager) {

        adapter = new AdapterViewPagerInTheKnow(getChildFragmentManager());
        adapter.addFragment(new MyChatFragment(), "MY CHATS");
        adapter.addFragment(new CallFragment(), "INVITATIONS");
        adapter.addFragment(new ChatRoomsFragment(), "CHAT ROOMS");
        adapter.addFragment(new ContactsFragment(), "CONTACTS");

        viewPager.setAdapter(adapter);

/*        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tab_custom_text_view,null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //    tv.setLetterSpacing((float) 0.15);
            }
            tabLayout.getTabAt(i).setCustomView(tv);
        }*/

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                page_position = position;

                if (position == 2) {
/*
                    adapter.replaceFragment(new ChatRoomsFragment(), "CHAT ROOMS", 2);
                    adapter.notifyDataSetChanged();
*/

                    tv_top_name.setVisibility(View.VISIBLE);
                } else {
                    tv_top_name.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

                page_position = position;
                if (position == 2) {
//                    adapter.replaceFragment(new ChatRoomsFragment(), "CHAT ROOMS", 2);
//                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setProfileAdapter() {
        profile_images.clear();
        profile_images.add(R.drawable.pink_one);
        profile_images.add(R.drawable.pink_two);
        profile_images.add(R.drawable.pink_three);
        profile_images.add(R.drawable.pink_four);
        profile_images.add(R.drawable.pink_five);

        profile_images_2.clear();
        profile_images_2.add(R.drawable.profile1);
        profile_images_2.add(R.drawable.profile2);
        profile_images_2.add(R.drawable.profileg);
        profile_images_2.add(R.drawable.profile3);
        profile_images_2.add(R.drawable.profile4);

        profile_names.clear();
        profile_names.add("Kelly");
        profile_names.add("Lia");
        profile_names.add("Sandra");
        profile_names.add("Sarah");
        profile_names.add("Jennifer");

        profile_country.clear();
        profile_country.add("Toronto");
        profile_country.add("Rome");
        profile_country.add("Peterborough");
        profile_country.add("Winnipeg");
        profile_country.add("Montreal");

        profile_check.clear();
        profile_check.add("0");
        profile_check.add("0");
        profile_check.add("0");
        profile_check.add("0");
        profile_check.add("0");

     /*   adapter_top_profiles = new InterestedUserAdapter(getContext(), profile_images, profile_images_2, profile_names,
                profile_country, profile_check, getFragmentManager());
        rv_top_profiles.setAdapter(adapter_top_profiles);*/
    }

    boolean status_arrow = false;

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fragment_chat_layout_call) {

        } else if (id == R.id.fragment_chat_layout_arrow) {

          /*  if(!status_arrow){
                status_arrow = true;
                iv_arrow.setImageResource(R.drawable.arrow_up);
                tabLayout.setVisibility(View.GONE);
                rv_top_profiles.setVisibility(View.VISIBLE);
            } else{
                status_arrow = false;
                iv_arrow.setImageResource(R.drawable.arrow_down);
                tabLayout.setVisibility(View.VISIBLE);
                rv_top_profiles.setVisibility(View.GONE);
            }*/

        } else {
        }
    }
}
