package com.askonlinesolutions.wengage.Activity.Main;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.CompleteProfile1;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Model.Request.CodeVerifyRequest;
import com.askonlinesolutions.wengage.Model.Response.ApiResponse;
import com.askonlinesolutions.wengage.Model.Response.CodeVerifyResponse;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Utility;
import com.google.gson.Gson;
import com.mukesh.tinydb.TinyDB;

import retrofit2.Call;

public class VerifyCodeActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {

    private EditText place_1, place_2, place_3, place_4;
    private TextView continue_button;
    private TinyDB tinyDB;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        init();
    }

    private TextView tv_resend_code;

    private void init() {

        tinyDB = new TinyDB(getApplicationContext());

        tv_resend_code = findViewById(R.id.resend_code);
        tv_resend_code.setOnClickListener(this);
        place_1 = findViewById(R.id.place_1);
        place_2 = findViewById(R.id.place_2);
        place_3 = findViewById(R.id.place_3);
        place_4 = findViewById(R.id.place_4);
//        back =findViewById(R.id.back);
//        back.setOnClickListener(this);
        continue_button = findViewById(R.id.continue_button);
        continue_button.setOnClickListener(this);
        tinyDB = new TinyDB(getApplicationContext());
        progressDialog = new BaseClass(VerifyCodeActivity.this).creatProgressDialog();
        ontextChangedMethod();
    }

    private void ontextChangedMethod() {
        place_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (place_1.getText().toString().length() == 1) //size as per your requirement
                {
                    place_2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        place_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (place_2.getText().toString().length() == 1) //size as per your requirement
                {
                    place_3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        place_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (place_3.getText().toString().length() == 1) //size as per your requirement
                {
                    place_4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        place_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (place_4.getText().toString().length() == 1) //size as per your requirement
                {
                    place_4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.continue_button) {
            String code = getCode();
            if (code.length() == 4) {
                if (Utility.isNetworkConnected(getApplicationContext())) {
                    CodeVerifyRequest codeVerifyRequest = new CodeVerifyRequest(String.valueOf(tinyDB.getInt(Constants.USER_ID)), code);
                    Call call = APIClient.getInstance().getApiInterface().getVerify(codeVerifyRequest);
                    new ResponseListner(this).getResponse(VerifyCodeActivity.this, call);
                    progressDialog.show();
                } else {
                    Dialog errorDialog = null;
                    errorDialog = Utility.createErrorDialog(getApplicationContext(), errorDialog, "Network error");
                    errorDialog.show();
                }
            }
        } else if (view.getId() == R.id.resend_code) {
            if (Utility.isNetworkConnected(getApplicationContext())) {
                Call call = APIClient.getInstance().getApiInterface().resendCode(tinyDB.getInt(Constants.USER_ID) + "");
                new ResponseListner(this).getResponse(VerifyCodeActivity.this, call);
            } else {
                Dialog errorDialog = null;
                errorDialog = Utility.createErrorDialog(getApplicationContext(), errorDialog, "Network error");
                errorDialog.show();
            }

        } else {

        }
    }

    private String getCode() {

        return place_1.getText().toString() +
                place_2.getText().toString() +
                place_3.getText().toString() +
                place_4.getText().toString();
    }

    @Override
    public void onApiResponse(Object response) {
        Log.e("MyResponse", new Gson().toJson(response));
        CodeVerifyResponse codeVerifyResponse;

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
        if (response instanceof CodeVerifyResponse) {
            codeVerifyResponse = (CodeVerifyResponse) response;
            if (codeVerifyResponse.getStatus() == 1) {
                new BaseClass(VerifyCodeActivity.this).showToast(codeVerifyResponse.getMessage());
                Intent intent = new Intent(VerifyCodeActivity.this, CompleteProfile1.class);
                startActivity(intent);
                finish();

            } else {
                new BaseClass(VerifyCodeActivity.this).showToast(codeVerifyResponse.getMessage());
            }
        } else if (response instanceof ApiResponse) {
            ApiResponse apiResponse = (ApiResponse) response;
            if (apiResponse.getStatus().equals("1")) {
                Utility.showToast(getApplicationContext(), ((ApiResponse) response).getMessage());
            } else {
                Utility.showToast(getApplicationContext(), ((ApiResponse) response).getMessage());
            }
        } else if (new Gson().toJson(response).equals("{\"message\":\"OTP Sent\",\"status\":1}")) {
            Utility.showToast(getApplicationContext(), "OTP Sent");

        }
    }

    @Override
    public void onApiFailure(String message) {


        new BaseClass(VerifyCodeActivity.this).showToast(message);
    }


}
