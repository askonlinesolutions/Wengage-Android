package com.askonlinesolutions.wengage.Fragment.Main;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Adapter.AdapterViewPagerInTheKnow;
import com.askonlinesolutions.wengage.Fragment.Main.vo.EventsSearchResponse;
import com.askonlinesolutions.wengage.Fragment.Sub.InfluencePickFragment;
import com.askonlinesolutions.wengage.Fragment.Sub.eventsInYourCity.SeeAllFragment;
import com.askonlinesolutions.wengage.Fragment.Sub.yourInterestedEvents.YourInterestFragment;
import com.askonlinesolutions.wengage.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InTheKnowFragment extends Fragment {

    TextView eventSearchEt;
    private String keyword;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<EventsSearchResponse.EventsListBean> eventsearchList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_in_the_know, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        eventSearchEt = view.findViewById(R.id.search_et);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
        setupTextListner();
        return view;

    }

    private void setupViewPager(ViewPager viewPager) {
        AdapterViewPagerInTheKnow adapter = new AdapterViewPagerInTheKnow(getChildFragmentManager());
        adapter.addFragment(new YourInterestFragment(), "YOUR \nINTERESTS");
        adapter.addFragment(new SeeAllFragment(), "SEE ALL");
        adapter.addFragment(new InfluencePickFragment(), "INFLUENCE \nPICKS");
        viewPager.setAdapter(adapter);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tab_custom_text_view, null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tv.setLetterSpacing((float) 0.15);
            }
            tabLayout.getTabAt(i).setCustomView(tv);
        }
    }

    interface TabChange {
        void onTabSelected(int pos);
    }

    private void setupTextListner() {

        eventSearchEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                frameLayout.setVisibility(View.VISIBLE);
//            viewPager.setVisibility(View.GONE);
                SearchFragment fragment = new SearchFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", "inTheKnow");
                fragment.setArguments(bundle);
                ((HomeActivity) getActivity()).replaceEventSearchFragment(fragment);
                /*getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter,
                        R.animator.fade_out,
                        0,
                        R.animator.fragment_slide_right_exit)
                        .replace(R.id.frame, fragment).addToBackStack(SearchFragment.class.getName()).commit();*/
//
////                frameLayout.setVisibility(View.VISIBLE);
////                viewPager.setVisibility(View.GONE);
//                SearchFragment fragment = new SearchFragment();
//                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter,
//                        R.animator.fade_out,
//                        0,
//                        R.animator.fragment_slide_right_exit)
//                        .replace(R.id.frame, fragment).addToBackStack(SearchFragment.class.getName()).commit();
               /* Intent intent = new Intent(getActivity(), HomeActivity.class);
                String name = "inTheKnow";
                intent.putExtra("name", name);
                startActivity(intent);*/
            }
        });
    }

}