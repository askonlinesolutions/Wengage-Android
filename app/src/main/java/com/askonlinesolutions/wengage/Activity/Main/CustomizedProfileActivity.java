
package com.askonlinesolutions.wengage.Activity.Main;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Model.Request.UpdateCustProfileRequest;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Utility;
import com.google.gson.Gson;
import com.mukesh.tinydb.TinyDB;

import retrofit2.Call;

public class CustomizedProfileActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {
    private TextView txtSkip, txtComplete;
    private EditText edtOwnWrod, edtHome, edtFavCity, edtFavRes, edtExtraHr, edtWork, edtSocial;
    private String ownWork, home, favcity, favRes, extrahr, work, social;
    private TinyDB tinyDB;
    private Dialog errorDialog;
    private UpdateCustProfileRequest updateCustProfileRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized_profile);
        updateCustProfileRequest = new UpdateCustProfileRequest();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            BaseClass.setLightStatusBar(getWindow().getDecorView(), CustomizedProfileActivity.this);
        }
        tinyDB = new TinyDB(getApplicationContext());
        init();
    }

    private void init() {
        txtComplete = findViewById(R.id.activity_comtomize_txtComplete);
        txtSkip = findViewById(R.id.activity_comtomize_txtSkip);

        edtOwnWrod = findViewById(R.id.activity_customized_inOwn);
        edtExtraHr = (EditText) findViewById(R.id.activity_customized_extra_hr);
        edtFavCity = (EditText) findViewById(R.id.activity_customized_fav_city);
        edtFavRes = (EditText) findViewById(R.id.activity_customized_fav_res);
        edtWork = (EditText) findViewById(R.id.activity_comtomize_txtyourWork);
        edtSocial = (EditText) findViewById(R.id.activity_comtomize_txtSsocialmendia);
        edtHome = (EditText) findViewById(R.id.activity_customized_home);

//        set listener

        txtSkip.setOnClickListener(this);
        txtComplete.setOnClickListener(this);

    }

    public void onClickBack(View view) {
        finish();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_comtomize_txtComplete:
                ownWork = edtOwnWrod.getText().toString().trim();
                home = edtHome.getText().toString().trim();
                favcity = edtFavCity.getText().toString().trim();
                favRes = edtFavRes.getText().toString().trim();
                extrahr = edtExtraHr.getText().toString().trim();
                work = edtWork.getText().toString().trim();
                social = edtSocial.getText().toString().trim();
                if (ownWork.isEmpty()) {
                    errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter about your self.");
                    errorDialog.show();
                } /*else if (home.isEmpty()) {
                    errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter your home location");
                    errorDialog.show();
                } else if (favcity.isEmpty()) {
                    errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter your fav city");
                    errorDialog.show();
                } else if (extrahr.isEmpty()) {
                    errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter your description");
                    errorDialog.show();
                } else if (work.isEmpty()) {
                    errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter your work");
                    errorDialog.show();
                } else if (social.isEmpty()) {
                    errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter your social media");
                    errorDialog.show();
                } */ else {
                    updateCustProfileRequest.setDescription(ownWork);
                    updateCustProfileRequest.setHometown(home);
                    updateCustProfileRequest.setFavoutiteCity(favcity);
                    updateCustProfileRequest.setFavoutiteRestaurant(favRes);
                    updateCustProfileRequest.setWork(work);
                    updateCustProfileRequest.setSocialMedia(social);
                    if (Utility.isNetworkConnected(getApplicationContext())) {
                        updateProfile();
                    } else {
                        Dialog errorDialog = null;
                        errorDialog = Utility.createErrorDialog(getApplicationContext(), errorDialog, "Network error");
                        errorDialog.show();
                    }

                }

                break;
            case R.id.activity_comtomize_txtSkip:
                startActivity(new Intent(CustomizedProfileActivity.this, CompleteProfilesActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
//                startActivity(new Intent(CustomizedProfileActivity.this, HomeActivity.class)
//                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;
        }
    }

    private void updateProfile() {
        Utility.showProgressDialog(CustomizedProfileActivity.this);
        Call<LoginResponse> call = APIClient.getInstance().getApiInterface()
                .updateCustome(tinyDB.getInt(Constants.USER_ID),
                        updateCustProfileRequest);
        new ResponseListner(this).getResponse(CustomizedProfileActivity.this, call);
        Log.d("TAG", "updateCat: " + call.request().url());
    }

    @Override
    public void onApiResponse(Object response) {
        Utility.hideDialog();
        if (response != null) {
            try {
                LoginResponse customizedProfileResponse = (LoginResponse) response;
                if (customizedProfileResponse.getStatus() == 1) {
                    tinyDB.putString("login_data", new Gson().toJson(customizedProfileResponse));
                    startActivity(new Intent(CustomizedProfileActivity.this, ThankYouScreen.class));
                } else {
                    new BaseClass(CustomizedProfileActivity.this).showToast(customizedProfileResponse.getMessage());
                }
                Log.d(TAG, "MyResposne: " + new Gson().toJson(response));
            } catch (Exception e) {
                Log.d(TAG, "MyResposne: " + e.getMessage());
            }
        }
    }

    private String TAG = CustomizedProfileActivity.class.getName();

    @Override
    public void onApiFailure(String message) {
        Utility.hideDialog();
        new BaseClass(CustomizedProfileActivity.this).showToast(message);
    }
}
