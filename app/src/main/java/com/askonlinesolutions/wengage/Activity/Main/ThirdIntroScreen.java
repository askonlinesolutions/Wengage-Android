package com.askonlinesolutions.wengage.Activity.Main;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.R;

public class ThirdIntroScreen extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_intro_screen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            BaseClass.setLightStatusBar(getWindow().getDecorView(), ThirdIntroScreen.this);
        }

    }

    public void nextScreen(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
    }
}
