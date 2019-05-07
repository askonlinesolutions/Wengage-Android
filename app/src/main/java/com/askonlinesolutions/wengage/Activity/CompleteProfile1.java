package com.askonlinesolutions.wengage.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Activity.Main.VerifyGenderActivity;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Model.CityModal;
import com.askonlinesolutions.wengage.Model.CountryModal;
import com.askonlinesolutions.wengage.Model.Request.UpdateProfileRequest;
import com.askonlinesolutions.wengage.Model.Response.UpdateProfileResponse;
import com.askonlinesolutions.wengage.Networks.NetworkConstants;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.utils.ChoosePhoto;
import com.askonlinesolutions.wengage.utils.Progress;
import com.askonlinesolutions.wengage.utils.Utility;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mukesh.tinydb.TinyDB;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.askonlinesolutions.wengage.Activity.LanguageActivity.languageModal;

public class CompleteProfile1 extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {
    private TextView name, dob, gender, genderTv, incomeTv, countryTv, languageTv, cityTv;
    private RelativeLayout genderLayout, incomeLayout, countryLayout, cityLayout, languageLayout;
    private ChoosePhoto choosePhoto = null;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private Address user_location;
    private UpdateProfileRequest updateProfileRequest;
    TextView next;
    private LinearLayout text_layout;
    DatePickerDialog.OnDateSetListener date;
    private String placename;
    private Calendar myCalendar;
    CountryModal countryModal;
    JsonArray jsonElementsLanguage;
    private Dialog countryDialog;
    CityModal cityModal;
    ArrayList<String> strings;
    private Place place;
    private Progress progress;
    TinyDB tinyDB;
    //    Spinner gender_spinner, income_level_spinner, country_spinner, city_spinner, language_spinner;
    ProgressDialog progressDialog;
    String gender_value, income;
    private ImageView imgBack;
    private static final int PLACE_PICKER_REQUEST = 1000;
    private GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            BaseClass.setLightStatusBar(getWindow().getDecorView(), CompleteProfile1.this);
        }
        myCalendar = Calendar.getInstance();
        init();

    }

    private void callGetCountriesAPI() {
        progress.show();
        Ion.with(this)
                .load("GET", NetworkConstants.GET_COUNTRIES)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        progress.dismiss();
                        countryModal = new Gson().fromJson(result, CountryModal.class);
                        if (countryModal.getCountries().size() > 0) {
                            openCountryDialog();
                        } else {
                            Toast.makeText(CompleteProfile1.this, "We don't have countries", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void init() {
        mClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        progress = new Progress(this);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
//        imgBack = findViewById(R.id.back);
        text_layout = findViewById(R.id.text_layout);
        cityTv = findViewById(R.id.cityTv);
        name = findViewById(R.id.full_name);
        next = findViewById(R.id.next);
        languageTv = findViewById(R.id.languageTv);
        countryTv = findViewById(R.id.countryTv);
        genderLayout = findViewById(R.id.genderLayout);
        incomeTv = findViewById(R.id.incomeTv);
        genderTv = findViewById(R.id.genderTv);
        incomeLayout = findViewById(R.id.incomeLayout);
        countryLayout = findViewById(R.id.countryLayout);
        cityLayout = findViewById(R.id.cityLayout);
        languageLayout = findViewById(R.id.languageLayout);
        dob = findViewById(R.id.dob);
        tinyDB = new TinyDB(getApplicationContext());
        progressDialog = new BaseClass(CompleteProfile1.this).creatProgressDialog();

        dob.setInputType(InputType.TYPE_NULL);
        dob.setOnClickListener(this);
        genderLayout.setOnClickListener(this);
        incomeLayout.setOnClickListener(this);
        countryLayout.setOnClickListener(this);
        cityLayout.setOnClickListener(this);
        languageLayout.setOnClickListener(this);
        next.setOnClickListener(this);
        text_layout.setOnClickListener(this);

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar = Calendar.getInstance();
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dob.setText(sdf.format(myCalendar.getTime()));
    }


    private void chooseDate() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


       /* DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Calendar c = Calendar.getInstance();
                        c.set(2017, 0, 1);//Year,Mounth -1,Day
//                        datePickerDialog.setMaxDate(c.getTimeInMillis());
                        dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();*/
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, date, 2019, Calendar.MONTH, Calendar.DAY_OF_MONTH);  //date is dateSetListener as per your code in question
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_layout:

                break;
            case R.id.dob:
                chooseDate();
                break;
            case R.id.genderLayout:
                openGenderDialog();
                break;
            case R.id.incomeLayout:
                openIncomeDialog();
                break;
            case R.id.countryLayout:
                if (Utility.isNetworkConnected(getApplicationContext())) {
                    callGetCountriesAPI();
                } else {
                    Dialog errorDialog = null;
                    errorDialog = Utility.createErrorDialog(getApplicationContext(), errorDialog, "Network error");
                    errorDialog.show();
                }


                break;
            case R.id.cityLayout:
                Intent intent = null;
                try {
                    intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(CompleteProfile1.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.languageLayout:
                startActivity(new Intent(getApplicationContext(), LanguageActivity.class));
//                openLanguageDialog();
                break;
            case R.id.back:
                finish();
                break;
            case R.id.next:
                if (checkAllFields()) {
                    String full_name = name.getText().toString().trim();
                    String date_birth = dob.getText().toString().trim();
                    String gen = genderTv.getText().toString().trim();
                    String address = cityTv.getText().toString().trim();
                    String loc = placename;
                    String income_level = incomeTv.getText().toString().trim();

//                    public UpdateProfileRequest(String knownByName, String DOB, String gender, String city, String photo,
//                                String incomeLevel, int online)
                    if (gen.equalsIgnoreCase("male")) {
                        Toast.makeText(this, "This App only for Female so you can't register .", Toast.LENGTH_SHORT).show();
                    } else {
                        if (place.getId() == null) {
                            Toast.makeText(this, "Please enter your address", Toast.LENGTH_SHORT).show();
                        } else {
                            updateProfileRequest = new UpdateProfileRequest(full_name, date_birth, gen, loc,
                                    "", income_level, address, place.getId());
                            Intent intents = new Intent(CompleteProfile1.this, VerifyGenderActivity.class);
                            intents.putExtra("firstScreenDetails", updateProfileRequest);
                            intents.putExtra("languageJson", jsonElementsLanguage.toString());
                            startActivity(intents);
                        }

                    }
                }
                break;
        }
    }

    private void openCityDialog() {
        final Dialog dialog = new Dialog(CompleteProfile1.this);
        dialog.setContentView(R.layout.country_dialog);
        dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        RecyclerView countryRecyclerView = dialog.findViewById(R.id.countryRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        countryRecyclerView.setLayoutManager(linearLayoutManager);
        CountryAdapter countryAdapter = new CountryAdapter();
        countryRecyclerView.setAdapter(countryAdapter);
        dialog.show();
    }

    private void openCountryDialog() {

        countryDialog = new Dialog(CompleteProfile1.this);
        countryDialog.setContentView(R.layout.country_dialog);
        countryDialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        RecyclerView countryRecyclerView = countryDialog.findViewById(R.id.countryRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        if (countryModal.getCountries().size() > 0) {
            countryRecyclerView.setLayoutManager(linearLayoutManager);
            CountryAdapter countryAdapter = new CountryAdapter();
            countryRecyclerView.setAdapter(countryAdapter);
            countryDialog.show();
        } else {
            Toast.makeText(this, "We don't have countries", Toast.LENGTH_SHORT).show();
        }


    }

    private void openIncomeDialog() {
        final Dialog dialog = new Dialog(CompleteProfile1.this);
        dialog.setContentView(R.layout.income_dialog);
        dialog.setTitle("Title...");
        // set the custom dialog components - text, image and button
        TextView first = dialog.findViewById(R.id.first);
        TextView second = dialog.findViewById(R.id.second);
        TextView third = dialog.findViewById(R.id.third);
        TextView fourth = dialog.findViewById(R.id.fourth);
        TextView fifith = dialog.findViewById(R.id.fifith);
        TextView sixth = dialog.findViewById(R.id.sixth);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                incomeTv.setText("$0 - $50");
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                incomeTv.setText("$50 - $100K");
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                incomeTv.setText("$100 - $200K");
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                incomeTv.setText("$200 +");
            }
        });
        fifith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                incomeTv.setText("Doesn't matter");
            }
        });
        sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                incomeTv.setText("Rather not say");
            }
        });
        dialog.show();
    }

    private void openGenderDialog() {
        final Dialog dialog = new Dialog(CompleteProfile1.this);
        dialog.setContentView(R.layout.language_dialog);
        dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        TextView male = dialog.findViewById(R.id.male);
        TextView female = dialog.findViewById(R.id.female);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                genderTv.setText("Male");
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                genderTv.setText("Female");
            }
        });
        dialog.show();
    }

    private boolean checkAllFields() {
        if (name.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (dob.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter your dob", Toast.LENGTH_SHORT).show();
            return false;
        } else if (genderTv.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter gender", Toast.LENGTH_SHORT).show();
            return false;
        } else if (incomeTv.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter your income", Toast.LENGTH_SHORT).show();
            return false;
        } else if (cityTv.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter your address", Toast.LENGTH_SHORT).show();
            return false;
        } else if (languageTv.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter your languages", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }/*else if (cityTv.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter your country", Toast.LENGTH_SHORT).show();
            return false;
        }*/
       /* if (!name.getText().toString().equalsIgnoreCase("") ||
                !dob.getText().toString().equalsIgnoreCase("") ||
                !genderTv.getText().toString().equalsIgnoreCase(""))

        return false;*/
    }

    public void onClickBack(View view) {
        finish();
    }

    public Address getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address = null;
        Address location = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (address != null && address.size() > 0) {
            location = address.get(0);
            location.getLatitude();
            location.getLongitude();

        }
        return location;

    }

    @Override
    public void onApiResponse(Object response) {
        UpdateProfileResponse updateProfileResponse;
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
            if (response != null && response instanceof UpdateProfileResponse) {
                updateProfileResponse = (UpdateProfileResponse) response;
                if (updateProfileResponse.getStatus() == 1) {
                    tinyDB.putString("update_profile", new Gson().toJson(updateProfileResponse));
                    new BaseClass(CompleteProfile1.this).showToast(updateProfileResponse.getMessage());
                } else {
                    new BaseClass(CompleteProfile1.this).showToast(updateProfileResponse.getMessage());
                }
            }

        }

    }

    @Override
    public void onApiFailure(String message) {
        new BaseClass(CompleteProfile1.this).showToast(message);
    }

    class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

        @Override
        public CountryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.country_adapter_item, parent, false);

            return new CountryAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final CountryAdapter.MyViewHolder holder, final int position) {
            holder.item.setText(countryModal.getCountries().get(position).getCountryName());
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utility.isNetworkConnected(getApplicationContext())) {
                        countryTv.setText(countryModal.getCountries().get(position).getCountryName());
                        countryDialog.dismiss();
                        callGetCityAPI(countryModal.getCountries().get(position).getCountryId());
                    } else {
                        Dialog errorDialog = null;
                        errorDialog = Utility.createErrorDialog(getApplicationContext(), errorDialog, "Network error");
                        errorDialog.show();
                    }
                }
            });

        }


        @Override
        public int getItemCount() {
            return countryModal.getCountries().size();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView item;

            public MyViewHolder(View view) {
                super(view);
                item = view.findViewById(R.id.item);
            }
        }
    }

    private void callGetCityAPI(int countryId) {


        Ion.with(this)
                .load("GET", NetworkConstants.GET_CITIES + countryId)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        cityModal = new Gson().fromJson(result, CityModal.class);
                        cityModal.getCities().size();

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        callResumeMethod();
    }

    private void callResumeMethod() {
        strings = new ArrayList<>();
        if (languageModal != null) {
            jsonElementsLanguage = new JsonArray();
            for (int i = 0; i < languageModal.getLanguages().size(); i++) {
                if (languageModal.getLanguages().get(i).isSelected()) {
                    jsonElementsLanguage.add(languageModal.getLanguages().get(i).getLanguageId());
                    strings.add(languageModal.getLanguages().get(i).getLanguageTitle());

                }
            }
            String s = String.valueOf(strings);
            String second = s.substring(1, s.length() - 1);
            languageTv.setText(second);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {

                place = PlacePicker.getPlace(data, this);
                Log.e("PlaceId", place.getId());
                StringBuilder stBuilder = new StringBuilder();

                placename = String.format("%s", place.getName());
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                String address = String.format("%s", place.getAddress());
                stBuilder.append("Name: ");
                stBuilder.append(placename);
                stBuilder.append("\n");
                stBuilder.append("Latitude: ");
                stBuilder.append(latitude);
                stBuilder.append("\n");
                stBuilder.append("Logitude: ");
                stBuilder.append(longitude);
                stBuilder.append("\n");
                stBuilder.append("Address: ");
                stBuilder.append(address);
                cityTv.setText(address);
//                textView.setText(stBuilder.toString());
            }
        }
    }
}
