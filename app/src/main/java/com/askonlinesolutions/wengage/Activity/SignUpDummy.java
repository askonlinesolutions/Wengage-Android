package com.askonlinesolutions.wengage.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.SignupActivity;
import com.askonlinesolutions.wengage.R;

public class SignUpDummy extends AppCompatActivity {
private TextView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_dummy);
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }
}
