package com.askonlinesolutions.wengage.Activity.Main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.SignUpDummy;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.R;

public class FirstIntroScreen extends AppCompatActivity {

    TextView next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first_intro_screen);
        next = findViewById(R.id.next);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            BaseClass.setLightStatusBar(getWindow().getDecorView(), FirstIntroScreen.this);
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstIntroScreen.this, SignUpDummy.class);
                startActivity(intent);
            }
        });
    }
}
