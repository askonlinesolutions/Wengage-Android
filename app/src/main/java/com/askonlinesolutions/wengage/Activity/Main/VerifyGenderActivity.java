package com.askonlinesolutions.wengage.Activity.Main;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Model.Request.UpdateProfileRequest;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.PermissionUtil;
import com.askonlinesolutions.wengage.utils.Utility;
import com.google.gson.Gson;
import com.mukesh.tinydb.TinyDB;

import org.json.JSONArray;
import org.json.JSONException;

import retrofit2.Call;

public class VerifyGenderActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {

    TextView take_photo, next;
    ImageView profile_image;
    public static int SELECT_PICTURE_CAMERA = 103;
    public static int CAMERA_REQUEST = 1;
    Bitmap bitmap;
    String bitmapString;
    boolean isCaptured = false;
    TinyDB tinyDB;
    UpdateProfileRequest updateProfileRequest;
    ProgressDialog progressDialog;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_gender);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            BaseClass.setLightStatusBar(getWindow().getDecorView(), VerifyGenderActivity.this);
        }
        take_photo = findViewById(R.id.take_photo);
        profile_image = findViewById(R.id.profile_image);
        next = findViewById(R.id.next);
        take_photo.setOnClickListener(this);
        next.setOnClickListener(this);
        tinyDB = new TinyDB(getApplicationContext());
        progressDialog = new BaseClass(VerifyGenderActivity.this).creatProgressDialog();

        getIntentData();

        checkUserData();
    }


    public void onClickBack(View view) {
        finish();
    }

    private void getIntentData() {

        if (getIntent() != null) {
            updateProfileRequest = (UpdateProfileRequest) getIntent().getSerializableExtra("firstScreenDetails");
            String json = getIntent().getStringExtra("languageJson");
            try {

                jsonArray = new JSONArray(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkUserData() {

        Bitmap bitmap = tinyDB.getImage(Constants.IMAGE_BITMAP);
        if (bitmap != null) {
            profile_image.setImageBitmap(bitmap);
            take_photo.setText("Edit Photo");
            next.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.take_photo) {
            takePhoto();
        } else if (view.getId() == R.id.next) {
            if (bitmapString != null && !bitmapString.equalsIgnoreCase("")) {
                updateProfileRequest.setPhoto(bitmapString);
                if (Utility.isNetworkConnected(getApplicationContext())) {
                    sendProfileData();
                } else {
                    Dialog errorDialog = null;
                    errorDialog = Utility.createErrorDialog(getApplicationContext(), errorDialog, "Network error");
                    errorDialog.show();
                }

            } else {
                Dialog errorDialog = null;
                errorDialog = Utility.createErrorDialog(getApplicationContext(), errorDialog, "Please upload image");
                errorDialog.show();
//                new BaseClass(VerifyGenderActivity.this).showToast("Please upload image");
            }
        }
    }

    private void sendProfileData() {
        Call<LoginResponse> call;
        updateProfileRequest.setLanguage(jsonArray);
        Log.d("TAG", "MyRequest : " + new Gson().toJson(updateProfileRequest));
        if (tinyDB.getInt(Constants.USER_ID) != 0) {
            call = APIClient.getInstance().getApiInterface().updateProfile(tinyDB.getInt(Constants.USER_ID), updateProfileRequest);
        } else {
            call = APIClient.getInstance().getApiInterface().updateProfile(1, updateProfileRequest);
        }
        new ResponseListner(this).getResponse(VerifyGenderActivity.this, call);
        progressDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SELECT_PICTURE_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            profile_image.setImageBitmap(photo);
            bitmapString = BaseClass.BitMapToString(photo);
            Log.e("bitmap64", bitmapString);
            isCaptured = true;
            //   tinyDB.putObject(Constants.IMAGE_BITMAP,photo);
//            updateProfileRequest.setPhoto(bitmapString);
            tinyDB.putImage(Constants.IMAGE_BITMAP, "profile_pic", photo);
            tinyDB.putBoolean(Constants.PROFILE_PIC, isCaptured);
            take_photo.setText("Edit Photo");
        }
    }

    private void takePhoto() {

        PermissionUtil permissionUtil = new PermissionUtil();
        if (permissionUtil.checkMarshMellowPermission()) {
            if (permissionUtil.verifyPermissions(VerifyGenderActivity.this, permissionUtil.getCameraPermissions()) && permissionUtil.verifyPermissions(VerifyGenderActivity.this, permissionUtil.getGalleryPermissions())) {
                //showAlertDialog();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                ActivityCompat.requestPermissions(VerifyGenderActivity.this, permissionUtil.getCameraPermissions(), SELECT_PICTURE_CAMERA);
            }
        } else {
            // showAlertDialog();
        }
    }

    @Override
    public void onApiResponse(Object response) {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
        if (response != null) {
            if (response instanceof LoginResponse) {
                LoginResponse updateProfileResponse = (LoginResponse) response;
                if (updateProfileResponse.getStatus() == 1) {
                    tinyDB.putInt(Constants.PROFILE_COMPLETE, 1);
                    tinyDB.putString("login_data", new Gson().toJson(updateProfileResponse));
                    new BaseClass(VerifyGenderActivity.this).showToast(updateProfileResponse.getMessage());
                    Intent intent = new Intent(VerifyGenderActivity.this, ControlActivity.class);
                    startActivity(intent);
                } else {
                    new BaseClass(VerifyGenderActivity.this).showToast(updateProfileResponse.getMessage());
                }
            }
        }
    }

    @Override
    public void onApiFailure(String message) {
        progressDialog.hide();
        new BaseClass(VerifyGenderActivity.this).showToast(message);
    }
}