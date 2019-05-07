package com.askonlinesolutions.wengage.Fragment.Main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.R;


public class CustomEvent extends Fragment implements View.OnClickListener{


    private EditText name,date,time,location;
    private Button submit;
    private ImageView edit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_event, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {

        name=getView().findViewById(R.id.event_name);
        date=getView().findViewById(R.id.event_date);
        time=getView().findViewById(R.id.event_time);
        location=getView().findViewById(R.id.location);
        submit=getView().findViewById(R.id.submit_button);

        setData();
        edit=getView().findViewById(R.id.edit);
        name.setOnClickListener(this);
        date.setOnClickListener(this);
        time.setOnClickListener(this);
        edit.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    private void setData() {

        name.setText("Phantom of the Opera");
        date.setText("21-10-2018");
        time.setText("9:00 PM");
        location.setText("New York");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.edit)
        {
            name.setEnabled(true);
            name.setFocusable(true);
            date.setEnabled(true);
            time.setEnabled(true);
            location.setEnabled(true);
            name.setText("");
            date.setText("");
            time.setText("");
            location.setText("");
        }
        else if(view.getId()==R.id.submit_button)
        {
            Toast.makeText(getActivity(), "Information will be sent through chat.", Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
        }

    }
}
