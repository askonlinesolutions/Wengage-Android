package com.askonlinesolutions.wengage.Activity.Main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.utils.Constants;
import com.bumptech.glide.Glide;
import com.mukesh.tinydb.TinyDB;

public class SplashActivity extends AppCompatActivity {

    TinyDB tinyDB;
    private RelativeLayout splashLayout;
    private ImageView gifImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
//       Double protein = new Double("1.1");
        tinyDB = new TinyDB(getApplicationContext());


        splashLayout = findViewById(R.id.splashLayout);
        gifImage = findViewById(R.id.gifImage);

        new CountDownTimer(2000, 1000) { // 5000 = 5 sec

            public void onTick(long millisUntilFinished) {
                splashLayout.setVisibility(View.VISIBLE);
                gifImage.setVisibility(View.GONE);
            }

            public void onFinish() {
                splashLayout.setVisibility(View.GONE);
                gifImage.setVisibility(View.VISIBLE);
                Glide.with(SplashActivity.this).asGif().load(R.drawable.load_and).into(gifImage);
                callNextActivityMethod();
            }
        }.start();

    }


    private void callNextActivityMethod() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (tinyDB.getString(Constants.IS_LOGIN).equals("true")) {
                    tinyDB.putString(Constants.IS_CALL, "0");
//                    tinyDB.putString(Constants.IS_CALL,"1");
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                } else {
                    tinyDB.putString(Constants.IS_CALL, "0");
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }

            }
        }, 20000);
    }

}
