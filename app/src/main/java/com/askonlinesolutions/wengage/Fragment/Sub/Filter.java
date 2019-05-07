
package com.askonlinesolutions.wengage.Fragment.Sub;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.askonlinesolutions.wengage.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Filter extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {


    public Filter() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    RadioGroup radioGroup1, radioGroup2;
    LinearLayout layout1, layout2;
    ImageView imageView1, imageView2;
    TextView textView1, textView2;
    private Animation animShow, animHide;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();;
    }

    private void init(){
        radioGroup1 = (RadioGroup) getView().findViewById(R.id.filter_radio_group_select_distance);
        radioGroup2 = (RadioGroup) getView().findViewById(R.id.filter_radio_group_sort_by);

//        radioGroup1.setAlpha(0.0f);
        radioGroup1.setOnCheckedChangeListener(this);
        radioGroup2.setOnCheckedChangeListener(this);

        layout1 = (LinearLayout) getView().findViewById(R.id.filter_layout_select_distance);
        layout2 = (LinearLayout) getView().findViewById(R.id.filter_layout_sort_by);

        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);

        imageView1 = (ImageView) getView().findViewById(R.id.filter_select_distance_image_arrow);
        imageView2 = (ImageView) getView().findViewById(R.id.filter_sort_by_image_arrow);

        textView1 = (TextView) getView().findViewById(R.id.filter_text_select_distance);
        textView2 = (TextView) getView().findViewById(R.id.filter_text_sort_by);

        animShow = AnimationUtils.loadAnimation( getContext(), R.anim.view_show);
        animHide = AnimationUtils.loadAnimation( getContext(), R.anim.view_hide);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (group.getId()){
            case R.id.filter_radio_group_select_distance:
                RadioButton radioButton1 = (RadioButton) getView().findViewById(checkedId);
                textView1.setText(radioButton1.getText().toString());
                radioGroup1.setVisibility(View.GONE);
                imageView1.setImageResource(R.drawable.arrow_down);

                break;
            case R.id.filter_radio_group_sort_by:
                RadioButton radioButton2 = (RadioButton) getView().findViewById(checkedId);
                textView2.setText(radioButton2.getText().toString());
                radioGroup2.setVisibility(View.GONE);
                imageView2.setImageResource(R.drawable.arrow_down);
                break;
            default:
                break;
        }
    }

    boolean status1 = false;
    boolean status2 = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.filter_layout_select_distance:
                if (!status1){
                    radioGroup1.setVisibility(View.VISIBLE);
//                    radioGroup1.setAlpha(0.0f);
                    //radioGroup1.animate().translationY(radioGroup1.getHeight());
                    //radioGroup1.startAnimation(animShow);
                    imageView1.setImageResource(R.drawable.arrow_up);
                    status1 = true;
                } else{
/*
                    TranslateAnimation animate = new TranslateAnimation(0,0,radioGroup1.getHeight(), 0);
                    animate.setDuration(500);
                    animate.setFillAfter(true);
                    radioGroup1.startAnimation(animate);
*/
                    //radioGroup1.startAnimation(animHide);
                    radioGroup1.setVisibility(View.GONE);
                    //radioGroup1.setVisibility(View.GONE);
                    //radioGroup1.animate().translationY(0);
                    imageView1.setImageResource(R.drawable.arrow_down);
                    status1 = false;
                }
                break;
            case R.id.filter_layout_sort_by:
                if (!status2){
                    status2 = true;
/*
                    TranslateAnimation animate = new TranslateAnimation(0,0,0, radioGroup2.getHeight());
                    animate.setDuration(5000);
                    animate.setFillAfter(true);
                    radioGroup1.startAnimation(animate);
*/

                    //radioGroup2.animate().translationY(radioGroup2.getHeight());
                    radioGroup2.setVisibility(View.VISIBLE);
                    imageView2.setImageResource(R.drawable.arrow_up);
                } else{
                    status2 = false;
                    radioGroup2.setVisibility(View.GONE);
                    //x radioGroup2.animate().translationY(0);
                    imageView2.setImageResource(R.drawable.arrow_down);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
//        getActivity().findViewById(R.id.navigation).setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        getActivity().findViewById(R.id.navigation).setVisibility(View.VISIBLE);
    }


    @Override
    public void onResume() {
        super.onResume();
//        getActivity().findViewById(R.id.navigation).setVisibility(View.GONE);

        radioGroup1.check(R.id.filter_radio_1);
        radioGroup2.check(R.id.filter_radio_2);

        textView1.setText("Best Match");
        textView2.setText("Best Match");

        radioGroup1.setVisibility(View.GONE);
        radioGroup2.setVisibility(View.GONE);

        status1 = false;
        status2 = false;

        imageView1.setImageResource(R.drawable.arrow_down);
        imageView2.setImageResource(R.drawable.arrow_down);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        getActivity().findViewById(R.id.navigation).setVisibility(View.VISIBLE);
    }
}
