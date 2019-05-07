package com.askonlinesolutions.wengage.Activity.Main;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Model.Request.SignupRequest;
import com.askonlinesolutions.wengage.Model.Response.SignupResponse;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.ChoosePhoto;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Utility;
import com.google.gson.Gson;
import com.mukesh.tinydb.TinyDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface,
        AdapterView.OnItemSelectedListener {

    EditText userName, password, confirm_password, email, phone, full_name;
    Button signup;
    private TextView countryCodeTv;
    ImageView back, profile_image;
    // LoginResponse.ProfileInfo profileInfo;
    ProgressDialog progressDialog;
    Context context;
    RelativeLayout profile_layout;
    boolean isCaptured = false;
    private int pos;
    private String CountryZipCode = "";
    ChoosePhoto choosePhoto;
    public static int SELECT_PICTURE_CAMERA = 103;
    public static int CAMERA_REQUEST = 1;
    private Uri final_uri;
    Bitmap bitmap;
    String bitmapString;
    Map<String, String> country_list = new HashMap<>();
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        GetCountryZipCode();
        init();
//        setCountryCode();
    }

    private Spinner sp;
    private ArrayList<String> arr_country_code = new ArrayList<>();
    private EditText edt_email, edt_full_name, edt_user_name, edt_mobile, edt_password,
            edt_confirm_password;
    private TextView btn;
    private String str_email, str_full_name, str_user_name, str_mobile, str_password, str_confirm_password, str_code;

    private Dialog dialog_error;
    private TextView dialog_error_tv, dialog_error_btn;

    private void init() {

        tinyDB = new TinyDB(getApplicationContext());
        edt_email = findViewById(R.id.signup_edt_email);
//        countryCodeTv = findViewById(R.id.countryCodeTv);
        edt_full_name = findViewById(R.id.signup_edt_full_name);
        edt_user_name = findViewById(R.id.signup_edt_user_name);
        edt_mobile = findViewById(R.id.signup_edt_mobile);
        edt_password = findViewById(R.id.signup_edt_password);
        edt_confirm_password = findViewById(R.id.signup_edt_confirm_password);
        edt_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                edt_user_name.setText(edt_email.getText().toString());
            }
        });
//        countryCodeTv.setText("+"+CountryZipCode);
//        countryCodeTv.setVisibility(View.VISIBLE);

        btn = findViewById(R.id.signup_button);
        btn.setOnClickListener(this);
        sp = findViewById(R.id.signup_spinner);

        sp.setOnItemSelectedListener(this);
//        sp.setVisibility(View.GONE);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        sp.setSelection(getIndex(sp, CountryZipCode));
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };

//        edt_email.setFilters(new InputFilter[]{filter});
//        edt_user_name.setFilters(new InputFilter[]{filter});
        setCountryCode();
        CountryZipCode = "+" + CountryZipCode;
        for (int i = 0; i < arr_country_code.size(); i++) {
            if (arr_country_code.get(i).equals(CountryZipCode)) {
                pos = i;
            }
        }

        sp.setSelection(pos);
    }

    private void setCountryCode() {
        arr_country_code.clear();
        arr_country_code.add("Code");
        arr_country_code.add("+1");
        arr_country_code.add("+7");
        arr_country_code.add("+91");
        arr_country_code.add("+61");
        arr_country_code.add("+32");
        arr_country_code.add("+55");
        arr_country_code.add("+86");
        arr_country_code.add("+33");
        arr_country_code.add("+49");
        arr_country_code.add("+62");
        arr_country_code.add("+98");
        arr_country_code.add("+39");
        arr_country_code.add("+81");
        arr_country_code.add("+60");
        arr_country_code.add("+95");
        arr_country_code.add("+31");
        arr_country_code.add("+64");
        arr_country_code.add("+63");
        arr_country_code.add("+65");
        arr_country_code.add("+27");
        arr_country_code.add("+34");
        arr_country_code.add("+94");
        arr_country_code.add("+46");
        arr_country_code.add("+41");
        arr_country_code.add("+66");
        arr_country_code.add("+44");
        arr_country_code.add("+223");
        arr_country_code.add("+230");
        arr_country_code.add("+353");
        arr_country_code.add("+852");
        arr_country_code.add("+960");
        arr_country_code.add("+965");
        arr_country_code.add("+968");
        arr_country_code.add("+971");
        arr_country_code.add("+972");
        arr_country_code.add("+973");
        arr_country_code.add("+977");

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, arr_country_code) {
            @Override
            public boolean isEnabled(int position) {
                /*if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }*/
                return true;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
//                    tv.setTextColor(getResources().getColor(R.color.hint_color));

                } else {
                    tv.setTextColor(getResources().getColor(R.color.text_color));
                }
                return view;
            }
        };
        sp.setAdapter(spinnerArrayAdapter);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SELECT_PICTURE_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                errorDialog = Utility.createErrorDialog(this, errorDialog, "camera permission granted");
                errorDialog.show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                errorDialog = Utility.createErrorDialog(this, errorDialog, "camera permission denied");
                errorDialog.show();
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
            //enableFields(isCaptured);
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.signup_button) {
            if (Utility.isNetworkConnected(getApplicationContext())) {
                checkValidations();
            } else {
                Dialog errorDialog = null;
                errorDialog = Utility.createErrorDialog(getApplicationContext(), errorDialog, "Network error");
                errorDialog.show();
            }

        } else if (view.getId() == R.id.dialog_error_btn) {
            click_retry();
        } else if (view.getId() == R.id.back) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finishAffinity();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finishAffinity();
    }

    private void checkValidations() {
        str_email = edt_email.getText().toString().trim();
        str_full_name = edt_full_name.getText().toString().trim();
        str_user_name = edt_user_name.getText().toString().trim();
        str_mobile = edt_mobile.getText().toString().trim();
        str_password = edt_password.getText().toString().trim();
        str_confirm_password = edt_confirm_password.getText().toString().trim();
//        str_code

        if (!edt_email.getText().toString().trim().isEmpty()) {
            if (!str_email.contains(" ")) {
                if (Utility.checkemail(edt_email.getText().toString().trim())) {
                    if (!edt_full_name.getText().toString().trim().isEmpty()) {
                        if (!edt_user_name.getText().toString().trim().isEmpty()) {
                            if (!str_code.equals("Code")) {
                                if (!edt_mobile.getText().toString().trim().isEmpty()) {
                                    if (edt_mobile.getText().toString().length() > 5 && edt_mobile.getText().toString().length() < 11) {


                                        if (!edt_password.getText().toString().trim().isEmpty()) {
                                            if (edt_password.getText().toString().trim().length() >= 6) {
                                                if (!edt_confirm_password.getText().toString().trim().isEmpty()) {
                                                    if (edt_password.getText().toString().trim()
                                                            .equals(edt_confirm_password.getText().toString().trim())) {
                                                        if (Utility.isNetworkConnected(getApplicationContext())) {
                                                            signup();
                                                        } else {
                                                            errorDialog = Utility.createErrorDialog(this, errorDialog, getResources().getString(R.string.no_internet));
                                                            errorDialog.show();
                                                        }
                                                    } else {
                                                        errorDialog = Utility.createErrorDialog(this, errorDialog, "Confirm Password does not match");
                                                        errorDialog.show();

                                                    }

                                                } else {
                                                    errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter Confirm Password");
                                                    errorDialog.show();
                                                }
                                            } else {
                                                errorDialog = Utility.createErrorDialog(this, errorDialog, "Password should be minimum 6 character long");
                                                errorDialog.show();
                                            }

                                        } else {
                                            errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter Password");
                                            errorDialog.show();
                                        }
                                    } else {
                                        errorDialog = Utility.createErrorDialog(this, errorDialog, "Mobile number should be between 9 to 11 digits");
                                        errorDialog.show();
                                    }
                                } else {
                                    errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter Mobile");
                                    errorDialog.show();
                                }
                            } else {
                                errorDialog = Utility.createErrorDialog(this, errorDialog, "Please select country code");
                                errorDialog.show();
                            }
                        } else {
                            errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter User name");
                            errorDialog.show();
                        }

                    } else {
                        errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter Full name");
                        errorDialog.show();
                    }

                } else {
                    errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter a valid email");
                    errorDialog.show();
                }
            } else {
                errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter a valid email");
                errorDialog.show();
            }

        } else {
            errorDialog = Utility.createErrorDialog(this, errorDialog, "Please enter email");
            errorDialog.show();
        }
    }

    private Dialog errorDialog;

    private void signup() {
        Double latitude = tinyDB.getDouble(Constants.LATITUDE, 28.5555);
        Double longitude = tinyDB.getDouble(Constants.LONGITUDE, 35.5555);
        String cityLocation = tinyDB.getString(Constants.CITY);
        Utility.showProgressDialog(SignupActivity.this);
        SignupRequest signupRequest = new SignupRequest(edt_email.getText().toString().trim(),
                edt_full_name.getText().toString().trim(),
                edt_user_name.getText().toString().trim(),
                edt_password.getText().toString().trim(),
                edt_mobile.getText().toString().trim(),
                sp.getSelectedItem().toString(),
                latitude,
                longitude,
                cityLocation
        );
        Log.e("MyRequest", new Gson().toJson(signupRequest) + "");

        Call<SignupResponse> call = APIClient.getInstance().getApiInterface().getSignUp(signupRequest);
        new ResponseListner(this).getResponse(SignupActivity.this, call);
    }

    private void createDailogError() {
        if (dialog_error == null) {
            dialog_error = new Dialog(SignupActivity.this);
            dialog_error.setContentView(R.layout.dialog_error);
            dialog_error.setCancelable(true);
            dialog_error.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog_error.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

            dialog_error_tv = dialog_error.findViewById(R.id.dialog_error_text);
            dialog_error_btn = dialog_error.findViewById(R.id.dialog_error_btn);

            dialog_error_btn.setOnClickListener(this);
            dialog_error.show();
        } else {
            dialog_error.show();
        }
    }

    private void click_retry() {
        dialog_error.dismiss();
        if (Utility.isNetworkConnected(getApplicationContext())) {
            signup();
        } else {
            createDailogError();
            dialog_error_tv.setText(getResources().getString(R.string.no_internet));
        }

    }

    private void enableFields(boolean status) {
        if (bitmapString != null && bitmapString.length() > 0) {
            if (status) {
                userName.setEnabled(true);
                password.setEnabled(true);
                confirm_password.setEnabled(true);
                email.setEnabled(true);
            }
        }

    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    public static boolean isValidPassword(String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }

    @Override
    public void onApiResponse(Object response) {
        Utility.hideDialog();
        try {

            if (response instanceof SignupResponse) {
                SignupResponse signupResponse = (SignupResponse) response;
                Log.e("MyResponse", new Gson().toJson(signupResponse));
                if (signupResponse.getStatus() == 1) {
                    tinyDB.putInt(Constants.USER_ID, signupResponse.getProfileInfo().getUserId());
                    Utility.showToast(getApplicationContext(), signupResponse.getMessage());
                    errorDialog = Utility.createErrorDialog(this, errorDialog, signupResponse.getMessage());
                    errorDialog.show();
                    Intent intent = new Intent(SignupActivity.this, VerifyCodeActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                    errorDialog = Utility.createErrorDialog(this, errorDialog, signupResponse.getMessage());
                    errorDialog.show();
//                    new BaseClass(SignupActivity.this).showToast(signupResponse.getMessage());
                }
            }

        } catch (Exception e) {
//            Log.e("MyException", e.getMessage());

            errorDialog = Utility.createErrorDialog(this, errorDialog, e.getMessage());
            errorDialog.show();
//            createDailogError();
//            dialog_error_tv.setText(getResources().getString(R.string.some_exception_occured));
        }
    }

    @Override
    public void onApiFailure(String message) {
        Utility.hideDialog();
//        errorDialog = Utility.createErrorDialog(this, errorDialog, message);
//        errorDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0) {
            TextView textView = (TextView) view;
            textView.setTextColor(getResources().getColor(R.color.hint_color));
        }
        str_code = adapterView.getItemAtPosition(i).toString();
        Log.e("MyCode", str_code);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public String GetCountryZipCode() {
        String CountryID = "";


        TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        //getNetworkCountryIso
        CountryID = manager.getSimCountryIso().toUpperCase();
        String[] rl = this.getResources().getStringArray(R.array.CountryCodes);
        for (int i = 0; i < rl.length; i++) {
            String[] g = rl[i].split(",");
            if (g[1].trim().equals(CountryID.trim())) {
                CountryZipCode = g[0];
                break;
            }
        }
        return CountryZipCode;
    }

    private int getIndex(Spinner spinner, String myString) {

        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(myString)) {
                index = i;
            }
        }
        return index;
    }
}