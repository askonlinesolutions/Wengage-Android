package com.askonlinesolutions.wengage.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.askonlinesolutions.wengage.R;


public class Progress extends ProgressDialog {

    public Progress(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progres);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
