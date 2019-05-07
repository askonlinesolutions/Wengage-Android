package com.askonlinesolutions.wengage.Activity.Main;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.ApiResponse;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Utility;

import retrofit2.Call;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {
    private EditText email_id;
    private TextView reset;
    ProgressDialog progressDialog;
    ImageView back;
    private Dialog dialog_error;
    private TextView dialog_error_tv, dialog_error_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            BaseClass.setLightStatusBar(getWindow().getDecorView(), ForgotPassword.this);
        }
        init();

    }

    private void click_retry() {
        dialog_error.dismiss();
        fetchResetPasswordData();
    }

    private void init() {

        email_id = findViewById(R.id.email_id);
        reset = findViewById(R.id.reset_password);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        reset.setOnClickListener(this);
        progressDialog = new BaseClass(ForgotPassword.this).creatProgressDialog();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            finish();
        }
        if (view.getId() == R.id.dialog_error_btn) {
            click_retry();
//            dialog_error_tv.setText(getResources().getString(R.string.something_went_wrong));
        }
        if (view.getId() == R.id.reset_password) {
            fetchResetPasswordData();
        }

    }

    private void fetchResetPasswordData() {
        if (Utility.checkemail(email_id.getText().toString().trim())) {
            if (Utility.isNetworkConnected(ForgotPassword.this)) {
                Call<ApiResponse> call = APIClient.getInstance().getApiInterface().getNewPassword(email_id.getText().toString().trim());
                new ResponseListner(this).getResponse(ForgotPassword.this, call);
                progressDialog.show();
            } else {
                errorDialog = Utility.createErrorDialog(this, errorDialog, "Please check your internet connection");
                errorDialog.show();
            }

        } else {
            errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter valid email id");
            errorDialog.show();
        }
    }

    Dialog errorDialog;

    @Override
    public void onApiResponse(Object response) {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
            if (response instanceof ApiResponse) {
                ApiResponse apiResponse = (ApiResponse) response;
                if (apiResponse.getStatus() == 1) {
                    new BaseClass(ForgotPassword.this).showToast(apiResponse.getMessage());
                    Intent intent = new Intent(ForgotPassword.this, LoginActivity.class);
                    startActivity(intent);
                } else {

                    errorDialog = Utility.createErrorDialog(this, errorDialog, apiResponse.getMessage());
                    errorDialog.show();
                }
            }
        }


    }

    @Override
    public void onApiFailure(String message) {

        progressDialog.dismiss();
        errorDialog = Utility.createErrorDialog(this, errorDialog, message);
        errorDialog.show();
    }

    private void createDailogError() {
        if (dialog_error == null) {
            dialog_error = new Dialog(ForgotPassword.this);
            dialog_error.setContentView(R.layout.dialog_error);

            dialog_error.setCancelable(true);
            dialog_error.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog_error.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        dialog_error.show();

            dialog_error_tv = dialog_error.findViewById(R.id.dialog_error_text);
            dialog_error_btn = dialog_error.findViewById(R.id.dialog_error_btn);

//            dialog_error_tv.setTe

            dialog_error_btn.setOnClickListener(this);
            dialog_error.show();
        } else {
            dialog_error.show();
        }
    }

}
