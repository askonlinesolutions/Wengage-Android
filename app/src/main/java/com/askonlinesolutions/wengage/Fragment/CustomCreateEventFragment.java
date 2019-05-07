package com.askonlinesolutions.wengage.Fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
import com.askonlinesolutions.wengage.Model.UpdatedModal;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.utils.Constant;
import com.askonlinesolutions.wengage.utils.Helper;
import com.askonlinesolutions.wengage.utils.Progress;
import com.askonlinesolutions.wengage.utils.SanImagePicker;
import com.askonlinesolutions.wengage.utils.Sources;
import com.askonlinesolutions.wengage.utils.Utility;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mukesh.tinydb.TinyDB;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import io.reactivex.functions.Consumer;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.askonlinesolutions.wengage.Activity.Main.HomeActivity.bottomTab;
import static com.askonlinesolutions.wengage.Fragment.ContactFragment.jsonObject;
import static com.askonlinesolutions.wengage.Fragment.ContactFragment.studentModal;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomCreateEventFragment extends Fragment implements View.OnClickListener {
    private EditText etEvent, etPlace, etDescription;
    private TextView tvEventDate, tvDOB, next, etContact;
    DatePickerDialog.OnDateSetListener date;
    private Calendar myCalendar;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private LinearLayout home3_cross;
    private TinyDB tinyDB;
    private Dialog errorDialog;
    private RecyclerView selected_contact_rv;
    private ImageView camera, groupImage;
    public static final int REQUEST_CAMERA_FOR_GROUP = 222;
    private boolean result;
    private File imgFile;
    private LoginResponse loginResponse;
    private Gson gson = new Gson();
    private Progress progress;
    public static UpdatedModal studentUpdateModal;
    private ArrayList<UpdatedModal> getContactModalArrayList;
    private String encoded="";
    private View view;

    public CustomCreateEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.custom_create_event_fragment, container, false);
        tinyDB = new TinyDB(getActivity());
        loginResponse = gson.fromJson(tinyDB.getString("login_data"), LoginResponse.class);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        bottomTab.setVisibility(View.GONE);
        initUI();
        return view;
    }

    private void initUI() {
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        tinyDB = new TinyDB(getActivity());
        camera = view.findViewById(R.id.camera);
        groupImage = view.findViewById(R.id.groupImage);
        home3_cross = view.findViewById(R.id.home3_cross);
        etEvent = view.findViewById(R.id.etEvent);
        etPlace = view.findViewById(R.id.etPlace);
        selected_contact_rv = view.findViewById(R.id.selected_contact_rv);
        etDescription = view.findViewById(R.id.etDescription);
        etContact = view.findViewById(R.id.etContact);
        tvEventDate = view.findViewById(R.id.tvEventDate);
        tvDOB = view.findViewById(R.id.tvDOB);
        next = view.findViewById(R.id.next);

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        if (jsonObject != null && jsonObject.size() > 0) {
            if (studentModal != null) {
                getContactModalArrayList = new ArrayList<>();
                for (int i = 0; i < studentModal.getContactsList().getMyContacts().size(); i++) {
                    studentUpdateModal = new UpdatedModal();
                    if (studentModal.getContactsList().getMyContacts().get(i).isSelected()) {
                        studentUpdateModal.setContactId(studentModal.getContactsList().getMyContacts().get(i).getContactId());
                        studentUpdateModal.setFullName(studentModal.getContactsList().getMyContacts().get(i).getFullName());
                        studentUpdateModal.setPhotoURL(studentModal.getContactsList().getMyContacts().get(i).getPhotoURL());
                        getContactModalArrayList.add(studentUpdateModal);
                    }

                }
                if (getContactModalArrayList.size() > 0) {
                    selected_contact_rv.setVisibility(View.VISIBLE);
                    LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    selected_contact_rv.setLayoutManager(linearLayoutManagers);
                    selected_contact_rv.setAdapter(new AdapterMyContacts());
                } else {
                    selected_contact_rv.setVisibility(View.GONE);
                }

            } else {
                selected_contact_rv.setVisibility(View.GONE);
            }
        } else {
            selected_contact_rv.setVisibility(View.GONE);
        }
        tvEventDate.setOnClickListener(this);
        tvDOB.setOnClickListener(this);
        next.setOnClickListener(this);
        etContact.setOnClickListener(this);
        home3_cross.setOnClickListener(this);
        camera.setOnClickListener(this);
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvEventDate.setText(sdf.format(myCalendar.getTime()));
    }

    //((HomeActivity) getActivity()).replaceCreateContactFragment();
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.etContact:
                tinyDB.putString(Constant.FROM_GROUP, "0");
                ((HomeActivity) getActivity()).replaceCreateContactFragment();
                break;

//
            case R.id.next:
                if (etEvent.getText().toString().trim().isEmpty()) {
                    errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter event title");
                    errorDialog.show();
                } else if (etDescription.getText().toString().trim().isEmpty()) {
                    errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter event description");
                    errorDialog.show();
                } else if (tvEventDate.getText().toString().trim().isEmpty()) {
                    errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter event date & time");
                    errorDialog.show();
                } else if (encoded.equals("") || encoded==null ) {
                    errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please insert event image");
                    errorDialog.show();
                } else {
                    if (Utility.isNetworkConnected(getActivity())) {
                        callCreateCustomEventApi();
                    } else {
                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter username");
                        errorDialog.show();
                    }
                }

                break;
            case R.id.camera:
                if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getContext(), WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getContext(), READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE},
                            1);
                } else {
                    showImageChooserDialog();
                }
                break;
            case R.id.home3_cross:
                getActivity().onBackPressed();
                break;
            case R.id.tvEventDate:
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);  //date is dateSetListener as per your code in question
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
                break;
            case R.id.tvDOB:
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                tvDOB.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
                break;
        }

    }

    private void showImageChooserDialog() {

        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.choose_pic);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        final LinearLayout linear_camera = dialog.findViewById(R.id.linear_camera);
        final LinearLayout linear_gallery = dialog.findViewById(R.id.linear_gallery);

        linear_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraIntent();
                dialog.dismiss();
            }
        });
        linear_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryIntent();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void galleryIntent() {
        pickImageFromSource(Sources.GALLERY);
    }

    private void cameraIntent() {
        pickImageFromSource(Sources.CAMERA);
    }

    @SuppressLint("CheckResult")
    private void pickImageFromSource(Sources camera) {
        SanImagePicker.with(getActivity()).requestImage(camera).subscribe(new Consumer<Uri>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void accept(Uri uri) throws Exception {
                onImagePicked(uri);
                String path = Helper.getPath(getActivity(), uri);
//                File imgFile = new File ( path );
//                fileName = imgFile.getName ();
//                signUpActivityBinding.licenceName.setText ( fileName );
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void onImagePicked(Object uri) {
        String path = Helper.getPath(getActivity(), (Uri) uri);

        imgFile = new File(path);
        String filePath = imgFile.getPath();
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        encoded = BaseClass.BitMapToString(bitmap);
        if (uri instanceof Bitmap) {
//            iv_place_holders.setVisibility(View.VISIBLE);
//            play_btns.setVisibility(View.GONE);

            groupImage.setImageBitmap((Bitmap) uri);
        } else {
//            iv_place_holders.setVisibility(View.VISIBLE);
//            play_btns.setVisibility(View.GONE);

            Glide.with(this)
                    .load(uri)
                    .into(groupImage);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CAMERA_FOR_GROUP:
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(getContext(), "Camera Permission not granted", Toast.LENGTH_SHORT).show();
                } else {
                    result = true;
                    if (result) {
//                        new BaseClass(getContext()).showToast("Picture has been taken successfully.");
//                        cameraIntent();
                    }
                }
                break;
        }
    }

    private void callCreateCustomEventApi() {
        if (jsonObject != null && jsonObject.size() > 0) {
            callCreateCustomEventsApi();
        } else {

            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please select atleast one member for create group");
            errorDialog.show();
        }
//        getActivity().onBackPressed();
    }

    private void callCreateCustomEventsApi() {
        progress.show();
        String image = "/9j/4AAQSkZJRgABAQAASABIAAD/4QBYRXhpZgAATU0AKgAAAAgAAgESAAMAAAAB\n" +
                "\\nAAEAAIdpAAQAAAABAAAAJgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAACvKAD\n" +
                "\\nAAQAAAABAAAB0wAAAAD/7QA4UGhvdG9zaG9wIDMuMAA4QklNBAQAAAAAAAA4QklN\n" +
                "\\nBCUAAAAAABDUHYzZjwCyBOmACZjs+EJ+/8AAEQgB0wK8AwEiAAIRAQMRAf/EAB8A\n" +
                "\\nAAEFAQEBAQEBAAAAAAAAAAABAgMEBQYHCAkKC//EALUQAAIBAwMCBAMFBQQEAAAB\n" +
                "\\nfQECAwAEEQUSITFBBhNRYQcicRQygZGhCCNCscEVUtHwJDNicoIJChYXGBkaJSYn\n" +
                "\\nKCkqNDU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6g4SFhoeI\n" +
                "\\niYqSk5SVlpeYmZqio6Slpqeoqaqys7S1tre4ubrCw8TFxsfIycrS09TV1tfY2drh\n" +
                "\\n4uPk5ebn6Onq8fLz9PX29/j5+v/EAB8BAAMBAQEBAQEBAQEAAAAAAAABAgMEBQYH\n" +
                "\\nCAkKC//EALURAAIBAgQEAwQHBQQEAAECdwABAgMRBAUhMQYSQVEHYXETIjKBCBRC\n" +
                "\\nkaGxwQkjM1LwFWJy0QoWJDThJfEXGBkaJicoKSo1Njc4OTpDREVGR0hJSlNUVVZX\n" +
                "\\nWFlaY2RlZmdoaWpzdHV2d3h5eoKDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKz\n" +
                "\\ntLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uLj5OXm5+jp6vLz9PX29/j5+v/bAEMA\n" +
                "\\nCQkJCQkJEAkJEBYQEBAWHhYWFhYeJh4eHh4eJi4mJiYmJiYuLi4uLi4uLjc3Nzc3\n" +
                "\\nN0BAQEBASEhISEhISEhISP/bAEMBCwwMEhESHxERH0szKjNLS0tLS0tLS0tLS0tL\n" +
                "\\nS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS//dAAQALP/aAAwD\n" +
                "\\nAQACEQMRAD8A8nmljWVZB0dQefUdDVppZnt2JOGUhsnHBGaWSxleLfGuQjYB7E55\n" +
                "\\n/nTraMK0scgK85wfccVzNq1zoSdxzXjG28yPAkbDH1O04P51ajYXiFI8b5Ox6Htx\n" +
                "\\nVDUbaFIYxCAGGScdqqWkxZdqcSryuOM//XqHBNXQ762ZrRRKxkjAxLwMevof6VUu\n" +
                "\\n1cKUmQqAOF9s9RWvA8Opxq27bOgz6E47HFQX8UobZOQSBhSBj+dRGfvag1cw7WUK\n" +
                "\\ngjckq3AI/hNRpFKw2thlZjx3B9auPbbztjGAx78Yx0x2qACSKNorhTg/jk+o/wDr\n" +
                "\\nV0cyexHLbcS1idw0cvCoQNp7knGOabMix7pIAUUNjBOcH0qceVNiaM4YHHPHSho/\n" +
                "\\nPQRxnJJwT6nt0/rRzahbQozbsDYcofmFSQvLYsJXjBDqcZpsIBk2S8beo+lOdyI9\n" +
                "\\nhyyo3Oe2eP6VfkR5koh89UaA/vdo4H45+lZ7A7tpGCODVgx+XEtxGxBJ4p6BHUzz\n" +
                "\\n4KhcZB53HoSP500DKfAPNXV2NbBeNxLH37YqiwoGadiRTz81JzTmAC8UinkA0wCR\n" +
                "\\nQrYFR1dk25wRwRxiqhFCAM08EscCo6cjFSCKAJjlV2+tMCkiptu6MPUTZQ4NIBF4\n" +
                "\\nNXVfHyDr0NUhirdpH5kpQnBwdv1pMaF24JQjJYZ/EdalSIRW32lThs4x1/MduKry\n" +
                "\\nL8gxnK1OkXmWbSoRlCMjvjnOPb1oY0PfybaUAndHLGC+OevPH0NaensZnLRsdijA\n" +
                "\\nBGDjp/8ArrnUAb7x5A4FaumT+QztyRtORjIx3rOpG8dBxep6VZzNcW4diCR8px6i\n" +
                "\\npSKzNLnhDeUnAkGQOwP/ANethlr1MLU9pTT6nBWhyzaIMUmKlxSYroMSLFGKlxSY\n" +
                "\\npDI8UYqTFLigCLFGKlxRigZFijFS4oxQBFtoxUuKNtICLFLtqXbS7aAIdtLtqbbR\n" +
                "\\ntpDIdtLtqbbS7aAsQbaXbU22l20BYg20u2pttLtpXHYg20u2p9tG2gLEO2jbU+2j\n" +
                "\\nbRcLEG2l21Nto20XCxDto21Nto20XAg20ban20baAsQbaTbU+2k20XAh20YqbbSb\n" +
                "\\naBEeKMVJtpdtIZFijFS7aNtAEWKMVLto20ARYoxU22jbQBDijFTbaNtICLFGKl20\n" +
                "\\nYoAjxS4qTFGKAGYpQKdilxSATFLilxSgUDExS0uKMUgP/9DhFtrixaWHf5sTchsc\n" +
                "\\n/Xv0/pWSGmVtoBYgkEnsByMVtXL28dnHfKxXeSpA525HT8qwp5JLVCOhbpk5OD1r\n" +
                "\\nljdnTKyLmoQySRRSoBjHzfXpz+VY0chgmWRV6fqe1bFndefCYWIztyfqD3+tUJYQ\n" +
                "\\nxUxZ+p7EdRTjp7rJlr7yL1m+ZUvlHzZIZfeuhingNy0VwSN/IJA59/qK4y1vngmB\n" +
                "\\nk5Un5h/WuojLXERUNluSmPQ9eawrRtqy4tNFOWzkhuA0PzxykMMHOOetUp57q3mJ\n" +
                "\\nIUq+eT0Pr+NdBZiAxlJGI2L7HB9B3xVfVbB5YVMRGV+b61Eai5uWQ2mkYMVxbyoA\n" +
                "\\n0eJEfdkd81ZSLzrndbtsdhkgjv8AWsrzCG3bdjDg4HX6VClzIsplJyT1zXV7NvYz\n" +
                "\\n5lszRuobhZ2eUbZOcg9z3HFVJYZN+QuN4yQfat1ZI763CSY3BiBz1GM8e/rWVczI\n" +
                "\\nHw+RwMAdBg0Qk9glFblT5liKY6nOD2x6fhTdqylvLOCei461o3Lx+Wsu7cvZRxz3\n" +
                "\\n+lUZ2EcgEQ25wRnr7VpF3IasRiItFuA5BII+neoSpBweCKuCTy7ckj94zYyfQCow\n" +
                "\\nDNGxHVBk/QVaZLRCWyhGO9IAWwBQp28ntSZIO5fWmIDuHenIRghqGO75/Xr9aaQM\n" +
                "\\n/L0oAVkHao/pT2IOAKZ3pgWlxtCngGmSMDjHpipYgZF8vIznjNRgEEj0pAMQ4PNW\n" +
                "\\nEbbz681AAOtORucUhkplXzCpHB706JmgcOVyORz0ORiqnRjVuC4kiUopGCc4Izni\n" +
                "\\nkwQjbN5kiBGDxUkchVjD/DnnjnBpIbkwSMXXcrDkZ/Ue9W5BBNLGPmO/ALAd/T9R\n" +
                "\\nQ5dBpGtYtPZLCz45IxnjOOuDXfW063cAnUYz1HvXntrcRiUQFi6xEZBHBGMHk+9a\n" +
                "\\nS31/bWi/YmAG4ly4Ab8jU0K3spvm2Yq1LnWm52hWm4qrp98l4hR/lmX7ynrjsfxr\n" +
                "\\nQK17EJqS5kedKLi7MhxSYqbbRtqiSLFGKkxRikMjxRipMUuKBkWKXFSbaXbSAixS\n" +
                "\\n7al204LSGRbaXbUuKdigZDtpQtS7adtpDIdtLtqbFLtpAQ7aNtTbaNtAEO2jbU+2\n" +
                "\\njbQBDto21Pto20hkO2jbU22l20AQbaNtT7aXbRcCvto21Y20uyi4WK22k21Z20ba\n" +
                "\\nLhYrbaTbVnbSbKLisVttG2rO2jZRcLFbbRtqxspdlFwsVttLsq1so2UrjsVtlLsq\n" +
                "\\n1so2UrhYq7KTbVzZR5dFwsVNtG2rXl0hSi4WKu2k21Z2UhSi4WK+2jbU22k20CsR\n" +
                "\\nYoxU22k20ARYpcVJto20AR4pcVJtpdtAH//R5oaYBaSQNkvyx9Ou7j34H4VxOpuW\n" +
                "\\nlCHqOfz9K9OigENtbWNwx37CpbscZIwfUcj6VwGrW6SyB0BD7FOPUf8A1q5Kcve1\n" +
                "\\nOma93Qw4JWicMv4j1rdmlDXSPBjbIFOB24GR9c1StLUTAhMK6n5larF67hkuchcE\n" +
                "\\nKR6ECtJWbIjdK42S2imxKSF+bacVqaPNFAoRuTngHpnpWFcy+W5eH5kcde2e/wCN\n" +
                "\\nSTOYlt584yMkf1qJwclylJrU6kiSGdpbbGDjAA7d+O9WJZFhtWZ8cgH8c1nLPKlk\n" +
                "\\n8sLBmJ+XjPXqB9asRbptNMbZXI3YPWuJrZyNFczZHtzK3nFWVjtzjBB7EfhWfcwR\n" +
                "\\nh/syDkDO4DqOtOknEkMkBjG9ABk47GorG42MWkGZF4wT1H0rrUWlcTaehE6tDbK7\n" +
                "\\nthgwKgf5/Onb7e/DArscfdI7/hVi7RWQiU8YypHOCOoqrbQBBIGH7wDg9v8AOK0V\n" +
                "\\nmrkO97CRW6xgrMR8xC/Q1Vki2lnGXXON39auG4EUxWZcggA9iKviG2nfyrd+WAXZ\n" +
                "\\n2Oec0c7WrFy30RlXRZiOMqR8mP1psqmOJWA278nj24qzdWk4Y5JLR8HA449DSTRP\n" +
                "\\nLbLNGMhQN2O2atNaWIaepmkHGaDyfwp4bAIPfimZx0x6GtCBcg5UUkYydvSkXAOT\n" +
                "\\nSp98AUAI6FcHrmmCrDIW+7zUHWmBKhxn1NOYNtDnoaiTrn0pwdiME8DmkA9gMUKp\n" +
                "\\nHzU9SJBgjk85+lNY7TgUhkZ6mlHDYpp7mnDnH60AS8cCrUT+TMEnZkUNk47H/wDV\n" +
                "\\nUKrsO4GmNI0ku9+cmluPYmugqSv5RJQn+tXbWea5jkt2JO5eGzyADnr6etZe3PJ6\n" +
                "\\nGkWUgYXg9Mj0ocboEzrdIvEtlEsx8yaHcAueWX+uOtdzYXi38HnKApBwQDmvMbJL\n" +
                "\\nma4ScKq7Oh6cjp+ZNdXDqMlvaTSWIVXXBYOOv6jnkUUa3s5WexNWkpxutzr8UmK8\n" +
                "\\np/4SLVGnEzyng/dHA/KvS9OvotRtVuY+M8Eehr0oVVJ2OKVJxVy3ik21LijFakEe\n" +
                "\\nKMVJilxSAjxS4qTFLikMZtpQKkxTgtIZHil21JinYpDIttLtqXbS7aQyLbS7al20\n" +
                "\\n7bSuBDtpdtTbaNtFxkO2l21LtpdtAEO2l21NtpdtFwsQbaXbVjbRtpXCxBtpdtT7\n" +
                "\\naXbSuOxBto21Y2UbKLhYr7aTbVnZRsouFitspNlWtlGyi4WKuyjZVrZSbKLhYrbK\n" +
                "\\nXZVnZTtlK47FYJS7Ks7KXZSuFitspdlWdlLspXHYq7KXZVnZS+XRcLFXZR5dXAlG\n" +
                "\\nylcdiiY6aY6v7KaUp8wuUzzHTfLq+UphSncXKUtlGyrRWm7adxWK22jbVjbTdtFx\n" +
                "\\nWIttLgVJto20XCx//9KxbK0kX2m4ABCZXvyRnP5VwOoRBI3VRllcBT6A+9d1NcN9\n" +
                "\\nrEe0eSo+XH0wSf6Vh3tsrQyrCCwkYH/db0rzYOzud0trHJ2jskZlmUM/PPcr3p89\n" +
                "\\nsjWZ8v8AjIYegyOtSFR1bPyclT1OBhqggmkRGji+YMM89Mdh+eK163RHSzOf/fRM\n" +
                "\\nAeh6dx71cn8kpGGyFI6+h9K1720W2njs5F3GQFgB2DdvekWKGDclwMoAM+oAH9Dx\n" +
                "\\nWrnezM1G10QqksNotu3zFsnI7e4rSsnKzcuHJj2HJ75zUcMisBHMMgAFGHXn9Kg2\n" +
                "\\nw78kleoEnvjIzXLLW6ZsrdB11Zhsseq9QOpB/wAKzILNPtKgsylcHJ5znoPyrQkm\n" +
                "\\n84+YT8x4J+lUJWmSVN+Nzccd8dK2p3tZkSS3EuBIscitg7GPToSfaqsEreYZThxj\n" +
                "\\n5hjkAVowTB422jLf3SOff+dUUKxzNCAASCpPr/hWq2aIfcux7Lu2kkXhjzg8k47/\n" +
                "\\nANKd9lxi8tsqw4K9Dz6e1YW4ohUMQQcYHQir1vqMhuFMxAUja2M4xUSpyWsRqae5\n" +
                "\\npSTylsD5ZXUqvPb0qqEbyJH3bdq4dCOQa0br5IYoYcMDnCk5+U/XvU6WyXB8x12P\n" +
                "\\njGezY9f8DWSqJFyjdnLWwjdnV+44qR7ZfOCA7V2hj7etXZ7F7JvNUjGeD6ZqIuty\n" +
                "\\ng3EM/wB3AyCP/rV1KV9UY8ttGZwj3y+Uhz2BppwAB3707aUOYzz096RT5cgLLuHX\n" +
                "\\nB71ZBIrFQSOQRUO0jmroQ/Zt4BCE5x6dqr9fwoTBoixjrUiqpPtTWQnFKn7sk9Qa\n" +
                "\\nYhm/AwOfT2p3zYz1zQQrdBSDKjrxQAdBg0DJIxQAx49aUZXrxQBIRnvxQqnHHrSA\n" +
                "\\njoOlOXcTk0hjQxXjqKYrEHcOop7YA4HShFMjAY5J6UAatvqCwBC4JPKt9D3HvzU8\n" +
                "\\nVw+nSFnO9WCso7Ee/wBec1kKrI58wAleNpqzIkl1G04bPlgAj2HAqHFFJs1L/Tob\n" +
                "\\niIXthzlSzL3469PSodD1dtMuABlkc4de2PX603Qw/wBtAUZUqyt6HI6VqzWdo3+k\n" +
                "\\nIpD5Cng4G4Z7dD2qI1HB2G48yO/hvrG4k8qCZHbrgHmrmK8OafZctJCSBvyCOK9I\n" +
                "\\n0nxLBc4gvSI5OAG7Mf6V6UK99JHFOjbY6rFGKfilxXQZDMU4CnYpwFSA0CnAU7FO\n" +
                "\\nApFDMU4CngU4CkMZtpdtSYpcUgI9tLtqXFLtpXGRbaXbUoWl20XHYi20ban20u2i\n" +
                "\\n4WIdtLtqcJTglK47EGylC1ZCU4JSuOxWC07ZVkJTglTzD5SrspfLq3sp2ylzD5Sn\n" +
                "\\n5dL5dXNlGyjmDlKfl0eXV3ZRspcw+UpeXSeXV3ZRsp8wWKXl0eXVzZS7KXMKxT8u\n" +
                "\\nnBKtbDSbaLjsV9lLsqxtpdtK4WK+yjZVnYfSjYaVx2K22jbVnZRsouFitspClWdt\n" +
                "\\nBSi4WKZSoylXSlMKUXFYolKYUq8UphSquKxS203bVspTdtO5Nitto21Y20mKLhY/\n" +
                "\\n/9OjdzxTQi4XghM8ds9f1FVluBZ6Y8soLAkYYc4IHFYdlFcnT3jc7/LYlB6j/A81\n" +
                "\\nfeWa/wBOeNF2MikFSewOc/XHFefa2h2+ZnWsqyi5vghZcoQByQDwf/riqkSiNlCL\n" +
                "\\ntUSbST0w3OD6eoro9MazisFmhDCOVdki4zhs4z/WsOaPfqzWw4G0CQ9mx91gPyq0\n" +
                "\\n9WS9kTXyQQCKeTc+wEA5yeuRzVG3t/tdk09x/AcL6Af1zXWC3gAubO6BPy74Qe+0\n" +
                "\\ncgfWsy7s4LO3WxWRk35kU/w4z09eKlPohtGY0cEdtgE4UYYDqM+9UWuT5vkzrsSQ\n" +
                "\\ndx3HH61p2qw2umuxBKyHj1yc/wBKz7kpPFvVCDG2Oeeexz6U0k3qJkPmSNDhVKlS\n" +
                "\\nQufeqly7yQZwQ6fe+hq7cxvHbAqeUHPOSSetKsz3EBJ5ZeCP8+tUnbUGr6HPxTSQ\n" +
                "\\nuJEPIq9OfPj+0RLzkbhjp9PY1QlUJIyr0B4rV094whBJBPH+NdEv5jCOuhnTRGMK\n" +
                "\\nw+64yKhUgHmpJxiQ4GAeR9KhqlsQ9zVhvDI2yTkngH3rV82byGjyHJ5Ug45rlvpT\n" +
                "\\n1lkThGIzWUqSexpGpbc7O1ma7tts8eVQ5GOvvVa4090bMCqArbgBwcEZx+lY0N/d\n" +
                "\\n/KkbHPT/APX610FvqF0luy3DGQKcEN2rmcZQd0bJqSMqSS1LfvQEPcL/ACzUS20F\n" +
                "\\nzIWtmG4nIXpj/wCt6Vo3Vjbzr5sDBS5+VP7vc/hWBtKz7Mlctg544z3raDTWjM5K\n" +
                "\\nz1RuRwokL+dnIGX45BHTFYdxC0TjPRvmH0PeumdomtF8ti52KW3dcBu35Vh3UyST\n" +
                "\\n/OAQcDI9B0x7VVNvUJpFQL8hOehqHIOR609hhflOaiU9q2MgOBx1oz37044x0pEI\n" +
                "\\nU5bpTESp83AHJ9KkjieRiiglhxjHeopZFDnyhsUnOAc1s6dJZPGqS5jk3/KydTn1\n" +
                "\\nzWc20rlRVzI2HB3DHrSOApGK6rV7dfs6zsN/C7XH6g1y0q7TsPUUQnzK45R5XYY3\n" +
                "\\nJz60+NcOOccj5vSm4zx+dTZXnB4/nVsklum3uZRznv61AjlUIHAYYP0pXO4gL6c1\n" +
                "\\nGVI+Xv0pW0AvwzyKy+WSoLYKqOuP/wBVbcWor5glZR+82CTrggdWx6isM3LoAEG3\n" +
                "\\naBz70tmIXleS4+6oz1/l71nKCaLUrD77TWs5VjB3blLZ7fh+FQ2cUkt1HHENzMwA\n" +
                "\\nHrmpn1CaGUrA7NGABh+Rjvx711Hh+Wza6iuzGqvhuFHUgcAe5qoOSaUupEurR6Ii\n" +
                "\\nkKA3XHNPxSROsqB1BHsRg1JivVucAgFLinAUoFAxAKeBSgU4CpGIBTgKUCngUiho\n" +
                "\\nFOApwFOxUjGYpwFPC08LRcZGFpwWpQtPC1Nx2IgtPCVKFp4WpuVYhCU8JUwWnBaV\n" +
                "\\nx2IQlPCVMFp4WpuOxAEpwSp9tLtpXHYhCUuypwtOxSuMr7KcEqbbRtouAzy1o2JT\n" +
                "\\nsUYpAM2rTSq1JtoxTAj2rR8tSYpNtAEJFGB6VNto20XEQbaADVjbSbaLjIsGjBqb\n" +
                "\\nbS7aVwINtG2rGyl2UrhYrbaTbVryzR5ZouOxTK00pV3ys00xGi4rFApTSlXjGaYY\n" +
                "\\nzTuFigVqMpitHyTSG3LU+YVjM20m2tdbIdzUgso6OdC5T//UoaT5clusar+7SIEn\n" +
                "\\nBGQw9T6Hpiq2vpHYWkcyKMu3Q9QSMfypRqoSWCzVWCKN0hxgbF9PxH9K5vVxqOq4\n" +
                "\\nvnBZM4XsB9K8+K97U7W9NDW0iRFhjEZIbJ8xR12nncMccfypI/IttWjdF/dkFJCc\n" +
                "\\n7VGflPPrRaX1np8cCyr5crj5hyCCOAT7GtieznltJmtVQGWI5B5z6Af096G9QWxn\n" +
                "\\n+IJvtsMV9YsH8t/LLJ198+xrY1Kxj1XTrYH5HLL7kb+ozXI2kEUFql1GT94I6HjB\n" +
                "\\n6HNd9DA80SSNkCM5HbJ7frUydrDSucpqsUdnG0MYxGilBnoeOf17+9YOmW8ktk4O\n" +
                "\\nCHGRn2rY1m6iur6OCEeYIiWYKegHBBrPsLgfYsOhDPJ8o6DCnt9M1ok+Ui+pRZUS\n" +
                "\\n2RJMrn5T7nOKkt40ihBJKsXI9c7aFRjcedgFZMgrnIBzSX8LRSAoMqgyB7nqalvW\n" +
                "\\n1xnPXvFwV7Dp+PNMimaJGC/xY/StO4tBNIWGORnI55x0NZkIw5B7j0yK64tOJzyT\n" +
                "\\nTHMPOieTGNpBwOgBqtWikLRW8rk/Kw2/jnI4rOqou5MkJRRRVElmG4eA7k/KtiHV\n" +
                "\\nbd1MVzHhW67eR+Rrn804AkEjt1rOdOMtzSM2tjtbVLfck0DBkAwc9Qfaqmo2QTPn\n" +
                "\\nLkZzGc8le49yKw7Cb7PL5hOB0PvXaOiXlkxjXMiAFW+6BnHI9a5Jp05Jm6fMjGWz\n" +
                "\\naCSItlWXt/eFc5OwaRmAxknFdFDdTyyLZX2Qy5AYYHJ9c4/CqOq2iqzSoc88j61t\n" +
                "\\nSk07SImrq6MkE7feoh1p69M0z3rpMCRuOKQjHHemk5ozQAuMinRvsOaQGkpAdxa3\n" +
                "\\nUZaNIguXA3p/C6gdQPUfhWRf2WC8kWSA3TrkHvmsy0mEU6SjOVPHsK3oZ1QGNuVY\n" +
                "\\n7lbPOe3Fc9nB6HRdTWpzjLgZqIcDjrXRXtrEziWEfK33tvT6genrWBIioxTIYdiK\n" +
                "\\n2jK5jKNhFO1gTxUjOokz2qJ5XI2vyKarjcCw3Adqokt5DuQq9eKhbKYVe/JrdsL6\n" +
                "\\nzfdFcQj5gAu0fNn261HqGmyI3nW4LRsu7P8AEPXNZqprZ6FuOl0ZETqpBdcqeDWz\n" +
                "\\nYXRtLgxQ525DKAcfr7ViHCjgYBHNQgnOTVSjclM9z02/FzCWlIUqMn0x+daL3ESO\n" +
                "\\nkZOWk+6BXhkF5cQAGJ2HsD2r1LQp4bmGK5ncI0a7Qo6HPfmuinWaShLcwnTXxI6r\n" +
                "\\nFOApaUCuswACnAUAU8CpKACngUgFSAVJQgFPApQKcBSuMQCngUoFSAVLZSQgWngU\n" +
                "\\noFPAqblCAU8ClAp4FSMQCnhaUCngUrjGgU8CnAUuKkY3FLinYp2KAGYp2KdilxSG\n" +
                "\\nMxRin4pcUAR4oxUmKMUARYpMVNijbQIhxS4qXbS4oGQ7aXbUuKXFAEO2l21NijFI\n" +
                "\\nCLbTgMVJilxQA2jAp+KMUhjAKdTsUYoERkUwip9tJtoGVyKQrVgrTStAFYjFNzir\n" +
                "\\nBWoytAEZlI6VGZXqQrTNtMR//9XOkCW+oLLksFj2f7qk8ZPck9an1JEm04pkqIzu\n" +
                "\\nOzuBzxjvWTrcgWAJbctK21FHt1qLT01CxtfMvQ/EiggnhUOct715+6udu2hz1khv\n" +
                "\\nr8zTnKck7ueMcD69q7fQzNbxNa3g2RnJjJ6pu7fpWZqtoqyRX+mKEeOQBzjAHAwT\n" +
                "\\n7etLrTTzNEYcErkt12nPBx/SnJ82gkrGxqGn+dia2xnIdz2YKf8APNbt9Nv0/bCd\n" +
                "\\npkjJQjqD1qrD5s1haiEHOwhjx06ZOf8ACon8iaa2s1OXTcD6j5v8DWRZyE2mx2dk\n" +
                "\\nl5GxIkVXfPXOc9f881DEblZXtjskeI+YrnnCnqM+/Wus2/aYkgdCSAT6AEdCfqa5\n" +
                "\\ny+0+6i05niULK7eW/rz6Y7Gtb33ItYzrWU3DglBt5z7A9R/PFSSSyxkRSqsihfmU\n" +
                "\\ndVAPB+o/WqFvb3dovnuANxAwvUDpkfjVu0by58yjLsdp9AT60SigiyvCYgS6N5kb\n" +
                "\\nNknpj296jOmqZ2igYFj0XocY7GtOO0treIXkIzucjbnOAOnHvSKsN2ZEibbJ98AD\n" +
                "\\njp29KiU2noHL3OZg4uBbS5aMEjGOapPErAyRchcZ9s12sflSKyzD95sO1unJFctc\n" +
                "\\n2E8MrIcYI3DHQ10U6qbMpxsjLNOKkDNSyRCMDnPv2qRWCgF1GO9b83YxsVB15qRH\n" +
                "\\nZc7TjIwaQ7ccD8aZT3AutsmG5CAwAyDwPwrqNNdmh+yDLrtO5vxyQK45ULdBn2Fd\n" +
                "\\nJYeZHEyg7SD1B4y3ArnrL3bI3pPW5PKtyGEdyu5Q45P3hzwc07VxBBsI6MMgZz+G\n" +
                "\\natPcGRA74AD7H7H/ADxRqEKzaX+94aHO3jqO355rnjL3lc1ktHY4hupx0PSmVITx\n" +
                "\\n70w16JxiUCilFAAKePWmGlUcGgCTODxxWlayiWMxMMFASCKys561oTgCGMxnAdST\n" +
                "\\n74NRJdC49y7ZXkiO1tMu5XOR6g+1Z93ay2xG/BVuVYdDmljRruP5jynAP9DVy1Zr\n" +
                "\\nmIWc5wgPU/w+/wDjU7O5W6sYhpOhrSubDyiSDlQM1nttJ4GK0TT2M2rbiKxDZBrq\n" +
                "\\n9L1FXi8q7JAQgqwODn0/GuUwDT1bjionBSVhxlY6bWrDzP8AT7IZhI+YAYIOeeK5\n" +
                "\\nnpwRXV2F3IIogjHeB8p9QDgg+oHWqeraewL39vho2wWx/CT2/Os6crPkkVKPVGMm\n" +
                "\\n+NsIeeOv6V1Gn3s1jALhmw/mDGR2HUehyK5ONDKcKMnnpXS22yGJctkKS5B5yFGc\n" +
                "\\nY6VdRCie0QOJ4UnUYDgEfjUnFeZtrmoatatb6a7RLGuBjAz2Az1rMsNRudJvFnuG\n" +
                "\\nMmPlYMT3+tdCxa0XU5/YPc9hxThVXT7qO+hEsXOe3cVfMbDtXRGakrozcWnqNFPF\n" +
                "\\nIAacBQwHCnimgU8Ckyhwp4FIBTwKhlIcBTwKQCpAKkoAKeBQBTwKljACnikprSRp\n" +
                "\\nyzAfjSuMlFLWXNqcMZ2x/P79qoPqM8nQ7fpUOaRSizpKXiuVNzK3Vj+dHmOepNT7\n" +
                "\\nQrkOlkuIY/vMKg/tCHtmsIc07bUuoyuRG6L+HHek+3w5xzWMFNSBKXOx8iNb7bH6\n" +
                "\\nGpFu4T1OKyRnGDShaOdhyI20mic4VhU1YQXArQtZNqkOeO1VGd9yZQtsXcUYpA6H\n" +
                "\\noaRpY0GSa0ujOw/FHA61Qa6duF4qPczHLHNZua6FqD6miJI/WpBg9KzhUgzS5x8h\n" +
                "\\nexS4qssjD3q0h3jIq1K5LVgxRipNtLtpkkeKMVJgUuKAIsUYqWigCLbSbampKBkB\n" +
                "\\nSoilWzmoyKAKpSmbatFajK0gP//Wy9a0yeVRd2/zhOCvcAtkkVJHcRppbMzK8aKU\n" +
                "\\nJzyd3Tj1rR0zVra+BRCVcgHa3XqK5rWtOFvfvBD/AKuYhwi9c55P0BrzV2Z3Pui/\n" +
                "\\noLK+mtaXQIMbYbPBw3K1Y1CSF4GMJChTkbuuO4P0OatwabbWtmXj+d05Lk9xzg89\n" +
                "\\nOK5q9ifULLKKd0fOM9ieR/hQrN3B3SOk8P3bXcDrGB+5G0HPZucfpToLm1tr6Kd2\n" +
                "\\nEbkMhDdCWPGD+Fc1oc39loLhTvWXJlHdVX+ID2roLq2tb/UbR0YGJv3nyng4yR+t\n" +
                "\\nEkkwTui/rFxa2BW5ZSDKcZX/AGa43V21S5keC1PERVnwcN0BH4fSur1uRWnNuAcK\n" +
                "\\ni/r1qIQWUWpqqqWmaJAxPbAHb3og7BJXOAg1a7N2q3a+asJIKAcnr3HetCUwTMt2\n" +
                "\\nN6eaeccEdwD/AI0alpen2erqGuAokbcVA6AnuR0qvc200WovaBwPMUbHB6rnK/U8\n" +
                "\\nYrZpPYzTa3Mu9vZYJvIB3EMCw7fhVu0SSWEXAGwg5I9Qcj8DxWTcxxW8oEZ3uwyd\n" +
                "\\n3VWrqp45YUtrRl4cFmYdu9OaVkkKLd3czpZD9mVjkNH1b1Ge/uKbaSyFQZ2D8fdx\n" +
                "\\nkisz7QyzSWoyy5K++B/Wk08Mtwc8r0J9OMj86l0tGHNdmiIILolgMMn3kP8AMVVv\n" +
                "\\nDbrG8caY2sBu7fhWuyuqyTowDxBWbHv94fhTL60iaFGiwOcnjIzj+VQnZpvYbhpo\n" +
                "\\nchjk0EjAHpU0sePnJwT2qS1tPtcphVgCFLDPfHau263Oe2tiGB5ElDRk7s8YrrLi\n" +
                "\\nK3+yreKwSUrkr6kHqP51hm0a0ghvT/FzgnqcmtDTboz+ZBcsBHtbJPBA9vxrCort\n" +
                "\\nSRpDTRmYbiUyPJE5G7LMPeuwsZY57SOOcglhtHoPX65HWsKz0eVp3R+U2Ehh3GMg\n" +
                "\\n/jVhBIkkRRWEUZAx6epP1/pWNZX0iOCa1MLUIEgvJI0HCsR+VUGBFddfiO/mcqmG\n" +
                "\\nBYAjnnqM/Ud65donDlGGCDiuilO8ddyJwsVqBT2TaAT3pAvOK3uQJxU8Me8Mc4wO\n" +
                "\\nPc+lRALgkmnxOR8vTnrSYIUoykqwxirYhaaMEZKxpk+xJqCUsxw3WrUFwyHy1baG\n" +
                "\\nwSfpzUvYpbj7basDRr9/cpwOpANX5BbzEmL5RkIq/wB7/wCvVJZohM25RyMEjtk5\n" +
                "\\npRIYWEjfKEYfKD7Z6+9Q11NE+hfugkdjFcQkFl52nk9e/bFYM0DLtlAO1xn6c1pC\n" +
                "\\n4RnmmfI4G3n8KZbySFo1YEx7SMj/AD2oV0J2ZkZxxSptDcnFWLq3kgc7uhPB9arB\n" +
                "\\nea1vcztY1w1zbwHyRuR1OGx0z1+laWmSxB1SRgRICGXPHPHfjPeqVndvEElJJBYh\n" +
                "\\ng3TaB6VHPDGjfaYT+7YqQO4yefyrBq+jNFpqXEt4NP1loZGLLzjaM9fWsuWcqzIn\n" +
                "\\ny7htP0HpXV6jbqlxFfkY3Jtb/e28fz/SuNuNxYFh+PrVUnzK7JlpoXtOvprKQCE8\n" +
                "\\nOcEfy/KureVNQiaB0UyYG0njLc8H8OlcNEzK4YDoc10+BdXMN3I3lhtrEY7jisq8\n" +
                "\\nFfmKg9LGx4c10WWoCGcbVB2HHoK9ot5BcweZEQ+eRXgkdlCuoySbwFSRgfTmu58J\n" +
                "\\n6zIrfZJjggkfiDRTkrtRC3c9AiMcjeXMhRqnazI5XmlWaOXhxzVoZUeoroVRkuCM\n" +
                "\\n4xFeooCj0rSEgPDdKRoM8oav2hHIUQo74FBkt0/1kir9atEfwTDg965nUvDjOTPa\n" +
                "\\nMWz2JzR7QfIa0mo6fFw0q5/Oqcmu2a/6oMx/KuNlsb2DhkNUy8qnDDFRzsrlR176\n" +
                "\\n5O33Aqj86qvqVzJ1c/hxXPLKanVzSbY7I1vtUrcFj+dKHJ61nK1WFepGXQaeDVdT\n" +
                "\\nU680hky5qZajUVMoqSiVRU6rUS1ZQUAKqVMEpVWplFAEPl0oSrOBTSPSiwEW3ApQ\n" +
                "\\nQKUgmjbQAwkmm4qXbTcUAIKmUVGBUqigCUCn00U8UxAKnjYqfaohTxVIllwMG6U6\n" +
                "\\noI+tT1omZsKKMijNMQtFNzRSuOw6kpKTFFwsLSEUYpc0XAYRTCKkLCoSzZ4pXHY/\n" +
                "\\n/9flbLRZhexm2kZSo+ZiPzxXVakxljRc71kVh78cZFZNi5F0tzgkPEWwThTnjP1H\n" +
                "\\nNV7h9QM6zRhHidtqr/dPTA9q896s7FojFs9QvtMVrVlJhfIyRlc9Mg/XrW9b30RM\n" +
                "\\nd0rDcewH3h9O+K0USI2knmhUVcs6/wB0+o9Rmq1hb2kspVMKUG5W+8fZvw/Ki6Gk\n" +
                "\\nZl3IlgSzrtEkbeWp6neehH0zWnaTJFou+2A324w4HcryeffPXvXN680zmMy4JjLL\n" +
                "\\nkHPGcipvD0MzyTQyJ+4kXDH146U3HS4k9bHQ2+rwaiw8wcgoFJ4yp6kn1BzWnd3U\n" +
                "\\nDX1vcBlWSMkEeqdh+dZOm6TPZXytbyKyBcBH9znH4VpG1+2TteyIqyByG5PQHb+O\n" +
                "\\nazdrlrYyNU0ewFvG8pJLAsZAPm3E98dcVgXehzadaJqKTeaigDjtnp17V08uoy27\n" +
                "\\n/Z7kxsVLKFP55/HOPwpUhjudHNo7HN0rFV7Lgj0q1JolxTPL4yktwZZ2wGbk9eve\n" +
                "\\nu8M9uYN0ro6rHtUA+gzkZ+hrlLjSpLWza5mbG19gXuTnmtONLX+xopWUny95J9zx\n" +
                "\\nz3wM9a3nZ2ZjG60ObtFLXgYdBljn0AyaLW5WOSRcbVlBA749KWN3Fxi2G4sNuPXI\n" +
                "\\nwatzadBHpi38bncG2MpHRucY/DmtG11ISfQ1Yxc2AlvZFMsUi7DjtkcE0/7XaPEc\n" +
                "\\nPuxEoPUZIznj1rNi1O/Nq1vNlonVQ2emAeP5VevmgulS2twsZVucfL/311rBxvoz\n" +
                "\\nZS6oy7uGBo90fO3ng9j/AEqG2s3mVZSSgYkB8fLkdqtajYvaXRgZ0UOq9DxjAI/O\n" +
                "\\npYbj7OP7PuHxEM9Bnk9/wrRNqOhDtfUnup4biK1UAusDYfP8WT1/Liqeo2htbwWr\n" +
                "\\nECP7yNjsa6mC3he0lidAzYB+TrtIxuAPXb1/OufurG9kgEe3c8IICgfNszyfpmoh\n" +
                "\\nNPRDlHqbKmFkjurKTEcClJT+uPpmmyTwSWi3EOVRcqO2R3+nU1haZLFAZILhtolG\n" +
                "\\nOen/AOuthmXTGUzKWtvL8rJ7luScVLjZ2Ki9DJvCfMintT/rcp8p9Pu59DUC3kcj\n" +
                "\\nDzcBjw/HX/61V0lWEsYfut29MHI5rPdgzbl4zWyp6WMuc2nsYb5i1kcBcAg9j/8A\n" +
                "\\nXrLFvMFYAdODUsVy8QGzuR9Tg561vb1utPV0wHTIKj1GP55pNuOj2Cylscw25AUA\n" +
                "\\nxkc5p1vbySElACV5x3P0FTTKqgepNQvmNwytj3Fa9CCYBWZ32lcL0PrkCokjyTzg\n" +
                "\\n8ck96s3srSlZAu0soz7kd/xqBFU53DIx+tJbDZbnIz5KAZUAEjufeqL5K7j+VSRt\n" +
                "\\nkj8qbld23HFCVgbuMLORuds5HStS1YC2eQAlAcEehOOaSC3t2sZCSDK2AARyBntT\n" +
                "\\nNz29u8TDG4Y4745BqZO+iKiraslulkWNYwMgZJP4CstlHABrQkuDIgJyHYbceveq\n" +
                "\\n32aXYZSMBRTjotQkrvQsuhuIEBwu3r784pGcpbmPjsD+BJqS2L7Au4MqfNjsN3FV\n" +
                "\\nUKgSDO4sMfSluF7G9ezvNp0EUYJMrbvU5GfT/OKwrjzCyCUewz7GrkNxIluTJxgA\n" +
                "\\np6nqMZ/EmrEkH27ZJAVBK7yDxjsR9M1EPcVmDV0UrO3FxciPtgnj0Ayf5VsQ3YEc\n" +
                "\\nKEKVT7o69PX8T+lZumO8V8RGccFCT23cGppMpfGRxlVBwMdhkD9RmieujKhojZkW\n" +
                "\\nH+z7sW7F23DJPU4PJ/Wk0q+Mlw8rLtK4wR6jj9cVkWrlLOdd3LMVA+oyT+lRWUpQ\n" +
                "\\nZ9GFTThZsJvY90trvzYVmU845ro7O4EybTXm+i3W6Lyz3FdZp85STFaMRvSfKdpp\n" +
                "\\n0Fxg7GonG5NwrNkYj5x1FIDosLKuDUK7oW2npVW1uQ4BrSIEi1W5JG8Ucg5ArEvd\n" +
                "\\nEt7gHaNrVtqSvympOGGDTC55TfaVcWbkFTiqS8V65LDFOpimGQa5e78NjcWhbA96\n" +
                "\\nRRyCmrCmr0mjXkR4XcPaq32eZDhlIpDJUq2hqqisO1WUBpAWV5qygqugq2lSUSqt\n" +
                "\\nWkWoVYVOtAEwwKkFMAqUCmA4CnbaVRUoFMkiKVGVxVvFNZM0WC5WApSmaftwadig\n" +
                "\\nCttwakFPK03FKw7jhTxTQKeKYhaeKaKeKaESKcVIDmohTxVIlkgp2aYKdTELS0lL\n" +
                "\\nTELSUZpKACkoJpM0hjSKjIqQmoi1JjP/0OWB1K2tkjgcMEZiA39088VNI9zdaVb3\n" +
                "\\nMoVJEl3gdAQDimWUJKxWd7IjswDRkHBUg9P6VHr91HHM6sokJ3CM4wBniuHd2Ot6\n" +
                "\\nak9+iy6fLFF+72guwQkggHkc+9ZFvfT2wEFugDQqVMmeoJzz9Ca09EjzYTzhj5z5\n" +
                "\\nQE84BxgAe5rQk0yzuVjNuVkETeWSOMnjcG+po20Yb6ozBb/a7rbFlldFdWbkfMOQ\n" +
                "\\na2tChjgje6jBEcjYAz3HUgVz9vqUkN0YWUKIQ3yg4yR2PathLeOaJw8jDy13xFeB\n" +
                "\\ntYd/Xg/pUspFa5vLdpzdocBJhnLY29sgf56V2cUXzEqzHoGLdDjmvL4Zra41JlVP\n" +
                "\\nNjm+TDHHU8EemDXYf2hcaVN/ZUx3wMMI564IyVJ/QUpRelhxZleKbdZ9SMiFVKIr\n" +
                "\\nOCcY9/xGBWdbX19p8f2x1+QgKqtyNpySQe1daUtL+9a7miDb1XKdRnGCf0Fc1cXR\n" +
                "\\nivLuyCq8SnADd8kED8KqL0syWtbop2+qNrMzafMmUmdNuP4QpyTz3IrIvYbqGaS3\n" +
                "\\nLN5MbEqOwBq68EEd0skKmCQHd5e7gj/ZJ/kau6hqYgnjljjDKYyvI+VlPUH3H860\n" +
                "\\nvZ+6iGtPeZhRQwNfokG54hgFwCM8ZPvSPl7OWGHPlB9wP6Crur3aJDbraZjJBYjo\n" +
                "\\nRk8VUiLXUQt4k+6hyc4zjnNVf7Qv7pBBHI1vNEeI2G4HtuQZ6/TNV4pAI9mAWPOT\n" +
                "\\nT7hLm2hFtMu3dhuRg9x19KbAQgLSKSdv6VoZt2EupxKsa87kXBJ702ESSP5ijlBk\n" +
                "\\n/QU1trgyY6YH/wCuphCVdY5eN4G3HTn1p7Kwt2dBPdXJtLeVZB5qOSpAOWBwfTt0\n" +
                "\\nqvcanJJJHJuKSxAggdB7j6+lUpDc2Mi20zBlj3bc5288HH1rNODKSn3SazjBFuTO\n" +
                "\\n6DQ6haLDKodhhwwABz3/APriqnicxSFFQn5Rkj07AVB4dZPtLbznCnA/rVXXHdrt\n" +
                "\\nl7YH1xioStOxbd4XOcOAeOlNxU/lnPPFRADJyeK6TnFXaOXq7aXD27PLGeAOh6H0\n" +
                "\\nqoyqzDbwOgzV2W1EdpvVs/Nhhjnjv9KmVnoyo33RCyqBu3HjGfr6VUJLEnFSCTau\n" +
                "\\n3qM1GBkZFUhMsMxMe1uSOKeoA4NMViGHFTNjdnuaQAoVOR1zxUPl8lxVphGAiknO\n" +
                "\\nMk46e1QHaW2k8joPU0XGTPdA26wCMAjqw6k1rX8XmWsMpX5lCjqMcD061gJKAwdT\n" +
                "\\ntI71qzSGW2TzMMRuOF6DPr+IqHHVWGnoypLK3mrOVAJPA9AK1hGt9asIiFIOSGOB\n" +
                "\\nzz+lVpo1ngWZDlgnzAnoQcD86sLZzWtujyZ2Ox3YHQcd+9S9i0QW9tKInhlQqVG4\n" +
                "\\nZ446/wBKowo+4xJgljkflWo9x9njEiOGEyldpOSpA4P4c1lbwjBvwP06VSvZku10\n" +
                "\\nTSBvuNyANw+vetDT541uUc4ARTGFHBO7PNZ9suRIjfeVSV+tSaVtNwxk6BSxPoAO\n" +
                "\\n1TP4WVHdFiykktZZbtwPnyAD0znOatJcpfSTPK22SVdi+/cfliqNzeCUeTAAsXQD\n" +
                "\\nHb1/OqxOCEXjHHFTyX1e4N20QkYMjk5561NGCgNNjG0j2qwRj6YrS+pB1uk3BUr9\n" +
                "\\nK7m2l+YN615lp8hUrXd2cuVFNgj0OB/MhrOl+Vipp+nS7kxReDB3VBZVglMUmO1d\n" +
                "\\nJbShhXISH+IVrWNxnANMk6KRf4hUee9SRtuWomG04qhEpw6+9IPmG1qjRsGnuP4h\n" +
                "\\nQBUcAP5b8HsarugQ/vFBHrVy5j86LI6iqlvN5oMUn3hUsq44QQMN6KD6iqLG2R9r\n" +
                "\\noMVadHgO+PpVWSLz/nXrQBVljjJzEMCowpHWrCqV+VqXb2NFh3I1NW0NV9mDUqVB\n" +
                "\\nReTmpwtRRirIFUhMQCpRSYpwFNEi4pcUClqhETCmYqcimEUh3I8U3FS4ppFIBopa\n" +
                "\\nSikMfSim0uaBDwakBqEGng1QiYGnZqHdS7qdxWJs0ZqHNG6i4WJi1M3VEWpN1Fws\n" +
                "\\nS7qTdUW6kzSuOw8tmm03NGaQH//R5Wzt7fUGhimdllhbYpGOSOSD9DWR4mvpLy/a\n" +
                "\\nApsEPy/U9zW5c2Xl28GpWLbSHZzjkcj0/Clg+zapj7Ug3seuOuetcUXZ3OqSurGT\n" +
                "\\nZFbXRmvopGeVcgITgJnjIHc11Hh6OBbOCN1IKuZJN3G0jnJ+uBiub1yxexuIbW2I\n" +
                "\\nKyYOOMls966y01e3vIHYYDAgSD04/wDrGlLVXHHRmSGtNV1JrpU2xHO7j5sDAyau\n" +
                "\\n/Yg9tczRv5ccsZQYOeEHBHpkU/VVtrf54JPJjuMYdR2xgj8au6Zbm1c287iTuAeC\n" +
                "\\nFI447ZrNvQtLU4TQpIba4MzxmQJ8wYfw47n2rSk1S3v9WeEnbHKVVXHPzKMA8+vS\n" +
                "\\nui8R2p+xbbWJEGcuAACVx246+lc+dLtE0pp0YxhSHDsPmBHbFaJp+8yLNaIffQ3N\n" +
                "\\nhpwnUkyBgoZTxu5yfaqtxGk9qsxdTcs2HVjgk9j71Ot411p8yghUYkk8kbj02jtg\n" +
                "\\n1mXsVrJqcfz7UjCKc8EtnnFJa7lPQ56/M32hllYsQcAk54FdAUEOnG0QskqxmQqT\n" +
                "\\nkEnBJHHTFXda0zc8d1bqzB0XPHCnPesa+vbvyms5lDSR5TzQf4e4H1rVPmSM2uVu\n" +
                "\\n5hKJbmXGSzt61c06cQz+a0mwqvHv7U6wWSB2mZMgIccZ5PQ/nVWaKQSFmAG4BuOm\n" +
                "\\nDWu90ZbanQ6rNb3V35kJEkccaAc5Hv8ArWhH9ngtBcMikRgB/XJ7VyTeXNcCKDKq\n" +
                "\\n+BjtmnBZnm+xozMpbG0Hgn6VPJpYrnIA2xGj7NjP4VtTWsEdnazSSEEq/wAvfIPy\n" +
                "\\n/gaoXemzWkXnS4ALbcZBNXLQQ3kYtpGx5SMyk9z1xTk+qFFdGUrjzjApkJZSdqk+\n" +
                "\\n3JxTHgRbRLhWBYsQU78d6tuXW0gcncAWZUYcY7nPcZqVYbe+gklRdk0YB2r93A6+\n" +
                "\\n+aXNYdrmjokccdnJetguoOPbH+NZty894/mMMsTj8Kji+0WrKSONudrdMMP6inXO\n" +
                "\\np3U5KQARoxzhBj9etJLW421axQlQ5OCOOMCqZxUhLqOBTUUyNtWtUZMEHqcdq0Lh\n" +
                "\\nJFiDqpCDjdnhjj/61UmRxxSCRiNp5pWvqPyG7gB83ccU6NSzYXv60wk9cd6cpwOB\n" +
                "\\n9aokmDZHI6HrUsbDePMPyjqRUKFeuOOh+lLnnb2HNIY6aQPIWxgDoKhUMOe56Gll\n" +
                "\\n+ZjUQYgg+lCBjmTGQPrzT4ndD8nU8Zpgy3fJp4ypyKGBrJctDaBHCsGfOO+B05+t\n" +
                "\\naD3ss1igmXOwlhjGBjA5x7A5rnS37oAc+tSW7gEJLna4wSO1Ry9S+boS3TBtpUY+\n" +
                "\\nXBqlkkbuvatPU5R9r/vKUXB9R61mH5HynIqou6Ja1NhYrmG8Eix4U+vPDD/69MvE\n" +
                "\\nFvO0cf3j97H8qs2moPHbMpXnbgE81mkliXPU1lFNu7NG1bQWNT0qZFHU0xQRyamA\n" +
                "\\nrQgfGq9alYYJXtSKuBz3FPTnNSMt25IAPpXZWMuVFcVEc8V0thJ8oqxHoemTdBWz\n" +
                "\\ndjcma5PTpcMK60nfDUMpGAT1Bp9tIUeop/lkNRhsNmmI7W1l3AVckGRkVz9hNwBX\n" +
                "\\nQIdy0LsDKrHHNTowYYqCQYOKZG+DikBaHynHasi8iaGUTJWseRmmSoJYyppsCKKR\n" +
                "\\nZo93r1qs8ZhfK9DUMDGGTYelahUOu00hlJollXcvWqxQiriZjfBqZ4gw3CgDNC5G\n" +
                "\\nKfGnNWBHg1KEpDHIOKnFMUVIBTQmxadSAU8CmITFOopaYhMU0in0hoAiNNNSGmUh\n" +
                "\\njMUlPNNpDEopKKQx1LmmZozQBJmlzUeaM07iJN1Jmo80maVwsSZpN1Rk03NAyXNG\n" +
                "\\najzRmgCTNN3UzdTSaQH/0uXgnntESO6T9ywwpAyOncdhVqf7LYW8UilcLgjB+8Tz\n" +
                "\\ngVFoepR22kXNzcfM0e0BT9MDH1rJ06S4urpn8tR5u4Rk/dRvUCuG2513K8hub+Ua\n" +
                "\\njLhQG+6x5+XqR3qGyeae6knhAWN5AWXtjmtK8sbeK0KSzDzQpUH1YnPWrehothbL\n" +
                "\\nNIw2HDksOn0PrVX0JtqTy65aXM0cEsYRIR6ZUHjPHtVTzpJdSm1AZCsmI8H74xgc\n" +
                "\\n+oIzWJL5VzelyCkbuSeOgzXS2uoWQuY7B1BjViq4/nUuNloUnd6lvVJVvZUt2JCx\n" +
                "\\nqC+Odx65HsKyZ0jMLQzzOyM27JAwCeAeO1SMLjUbMpGdrIW5H8S5AANZmn2dwJJb\n" +
                "\\nC7Jj8yMnJ7beRipjs9R31NNQsdpFHbL8sUu5m9QATkVRksG1VwyKVWNNu/sx6455\n" +
                "\\n74o0wmznX7XOFXlRnocd/pXQvPcGxZYVBDMu38W9alSlGTGlzbmXpM90tvsMqnL8\n" +
                "\\no33lAGO9ZVtMfIu5WU7BkgE8Z7ge/Iq/r8dzHAkaNG4kY79o5D9cA+lRXNhGFTT4\n" +
                "\\nX2q0YfPvgFv1rSyauyXfYpqQt9DdRqVjES+ZjI525arbvaX+izS4CzQ7VAA5IJ4y\n" +
                "\\naz7ZYoZI47t/mLBcHkYPH4D+lbGprY6YI4kUoZ8mQd1APH1q7rSxPe5xCGSB8rw/\n" +
                "\\n8q3rHTr5Ilu0UeY5YKGODgDOeaLOKPUNUDeWTCrEkLyxXNaskdxb2cwlWRiQBE7d\n" +
                "\\nlPJz7cVcpdCIx6nNu7Tri4O0J90dc+tUmZgxMQ2jt6gVoQM8xORuY+g/lVSUhCR6\n" +
                "\\nDA+tWiGb2kuLi1NnOpkGcIO656mqMdjcC4fy0fyQxBbvtHXn+dZcUj2zpKjYbqDW\n" +
                "\\nr/bM32T7MBgkksfxzxUOMk9C+ZO1xLvU5JpMSqvTaQB/Dn/IqmduC0ZyDnHbioIS\n" +
                "\\nC+WPWrMhMEZGwHf0z2+lWlbREttlbcQnIFOhZQTxyRzSysHjG773UUAhYjjg4qiR\n" +
                "\\nrMGX+dIsfyBqiIAPFW0XfEewoAgeM7gV5WlCluUHA4NTjzBEdvTrimwrLcusUCEs\n" +
                "\\neoFJsBZUQIpjOT1NQ5A+bNW7q1mtcpOpVyARnuPaqPJP4c0lqtB7DlYFtpGSe9RS\n" +
                "\\nKQcZpp608gkc9asQqpyADTWPOAaUk7dvpViWAISFGQowT74pX7hYgwxO0d+1SSxv\n" +
                "\\nEq78HcMjBoifyyZO+Mfn1NRt8xH0pa3DoOMhfBP8KhRU0KLnLcHtUSL8mfWn9aGM\n" +
                "\\nmDHPHFTDFVt3QCpQaVgLC4HB5qwqqBuJqmCBUykYpNDLKjpz2qaAAt83pmqYJ61K\n" +
                "\\njUrDLaYB4rZsmxWEhrVtH5qkI7Gykwwrt7WTfFivO7V8EV2mnS5UClJDRFfLhs1S\n" +
                "\\nDVqXy5XNYoPOKEDNizl2tXV28mQK4iBsNXUWcuQKQGnMMjNUc4bNaJ+ZazpBg02C\n" +
                "\\nLiNkU8cGqcT1ZzkUAU7qLDbxU8D7l56ipHG9arRAq2KQyzIm75hT4umDUi8igLg0\n" +
                "\\n7CGtHSBasYyKMU7CuRBacFp+KMUBcbilpaKYBRSUUALSUUUgGmmU80ykMaabTjTT\n" +
                "\\nSGIabS0lIY3NGaQ02kBJmjNR5ozQA8mmk00mkzQMdmkzTc0ZoAfSE0zNJupAOJpu\n" +
                "\\naYWpu6gZ/9PiJLiK0uRZyruichnXHPByBVy4votPj+0ghmLFkUDbwenHtVKzthMx\n" +
                "\\n1RiWdVL7B229BmuYubma8mMsxya5FFN2OltpXBnklO5zkmuquIbhtNjWSFlMUYx6\n" +
                "\\ncdz+Fc5FbzkB0XA7GvRpL6G7gtcHETv5cg75IOM+2adR7WFTW9zz24uXCj1wDn61\n" +
                "\\nFaXCxXcU7jIVgTW/f6TGYnFud0kTFWX1XGVI/Cq2haYs9y8l2p2RIX2+p7D86pNc\n" +
                "\\nuomnzHTCeFvNggZd+DI3HXpwp/WsNHa71J7iXexLFU28cjgVoyOY7+O6V1cBTuAG\n" +
                "\\nDkDkEd85rlLXU5on5OFY5/OsIxbWhs5LqWNTZridIyp2xrsQDsB/jVzT4LqQNbh3\n" +
                "\\nRBghcnC5PQ1o2mopdXKhEwFHzNiupt4oEnOMM85AP+6OaJtqPKJK7ujkYfs8+tRW\n" +
                "\\ntzkCI5z/AHmHPP5VFdTGCebVPLKL5hWNW7A/4itu50+SC7l1csvkiQqAOo2nGT+N\n" +
                "\\nYl5q7XEzQSRqURsD+6SKErgtDKvVtJ7ZbwEidjgp14xU6LLq8cUcxYPAr7n6/IBk\n" +
                "\\nfj2qOydYLsXc+GGSXPVQDxge/Nbcl/BbWEttZoVmeNWy3UjI4/LnFa3tZIi3VmRb\n" +
                "\\n3UFhZyTQhkuSdqn0Hc/WtTw7cNexz6ZO24y85Y9jw1c5fOsioBwxGXX0P+ean0q5\n" +
                "\\nitLqF2U9cO3+yaqUfdJjLU6OCMaBdNYzFN0il45OuBz1rnLlrW8uWm+75xJA9Oa2\n" +
                "\\nvErpcah8gyFUryey9CK5ERtDLlfm24IpQWlwnpoNlgKSFewOKNrOpdehpzytNlHG\n" +
                "\\nCTTBviBH96tjISKNvMA/Gr8jfaHC/wB0HpVXgsGTgYwaZ5zxsQOtTuVsPYtnDdQc\n" +
                "\\nU2KMysQv61Gr+vJ5/WkSUowKn/CqsSSSIA20HgUo81QFU4GKcVLt1+8Cfxqsd2Mm\n" +
                "\\nhATiZwPcGtrQY1e8DsQFzgk+9c/Eru4WMZY8Cuwt7ONSLRQDJjktkLnvn6/yFYV5\n" +
                "\\nJR5e44m/e6bDrdsfsz4aLOwe/TkehrzjYzyiJVJcnGB616Ba3U+nyGCfAncAhlHy\n" +
                "\\n7TznP06Vc820Nz5sSAucFSFHXvXDSrOleLV10NGrnmMkMsJ2yKVI7EYNNXYFySdx\n" +
                "\\n/IV7bHDb3rM15GpCjguAT6fzrhfEnh5LZTqFmMR5w6f3Se49q2o46M5cklZidO2q\n" +
                "\\nON/d4PWk3ySE88HGfwpOMD0ppwv1rvMxvO73qQYyffimLnG6nR8HkUxEuOKF60E5\n" +
                "\\nOKVR3pDHCnikAqQCgY4ZNOFIOKcKQEisR1qUGoRUgpWGWkatC2b5hWWlXoD8woA6\n" +
                "\\ni3bpXWadLg1xlu3SujspMEU2COouPmT8K59uGrd3boxWJOMPUobJom5rfs5Olc3G\n" +
                "\\na2rQ9KGJHVRtlagmXmkgbipZBkUhlNTg1bRsiqpHNTJQMsCm7Oc0oqSmIetPqMU7\n" +
                "\\nNMQ8GnVHmlzTEPopuaM0ALRTc0ZoGOpKTNJmgBaKTNNzSAU000E00mkMQ000pNMJ\n" +
                "\\npDA03NBNMJpDHZppNNJppNIYpNNzTSaTNAx+aTNNzSZpAOzSZpuaaTQA4tTS1NJp\n" +
                "\\nhNADi1N31ETTM0Af/9Tgrm8exwbZUwwxjJ4/A1nSLbTmN0iZGyC4HKn6YpZ5FlAl\n" +
                "\\nusdNvHf3xUFrHcWkrNjIHp2PrXHFWV+p03b0ZupaoWaHYzbh8q84x7+h9KrXljPb\n" +
                "\\nxN9kUq6EZA+YkeoqvLrd/hlIztYNuHYVU/ty+BLQnbn8aFCV7oiVk9CO2uru3lYS\n" +
                "\\nORuYb93Xiuys7y1nHlRyLvJHAGM46c1wFyZmk33By7DJz1pYmVtqMSOeo7VpOmpI\n" +
                "\\nIztodc5mlmiihVXIlYHPYHH/ANeotV0OKG5jMA+Qqd2Dnkema0baW3tbQ3pbJ3EL\n" +
                "\\nkckkD9TVy2eO+sJZ35cNkex9PpWKbRtZbM520jli2gENbyHa2OD+IraFobN5blvk\n" +
                "\\nSJQYu2T61nhFggfzYw6KwYt6luNo9MVsG+gW3SO4UmM/IwPJXPHP0qakrbIqK03K\n" +
                "\\n2r3KR6LGsTN5jMCFPOSx3Ekd65iSzmuLVNi4bduIIxgdOa0bhfL1cXi4a3jwOvtg\n" +
                "\\n7fer9rewyaosT4Hmo4xnhd3RT+WatOyujN7tMwpH36csWwFoWO7b054BPvU2nW5a\n" +
                "\\nU3t2VZR1DHG7I5/Kpoms2nkigHl8YZCerZIOPoM1VIs4cx2sjAFed39Pequ7NCst\n" +
                "\\nGMhtoL/USbdMRhuFJ6gDkA+9Vx/pN66P+6HzMAewA4FdTp0EVtgyplWw7FuNuBz+\n" +
                "\\nVQ6ncLaN9uTBlPGVAwwPIJz04NZKs3PlSFyvqc7NKLvY0rtvziVj0CjGAPwrOZyS\n" +
                "\\nxizx3rUF5A9rKkkYMspyWAxil0uEyIUAyZFKj09//rV03sjNq+iM+IRuq7mAfnrw\n" +
                "\\nMfWmtJyqMBtXn86sXKmOTy1Tai8D1OOpqrcxlMH1pppiasPYgrtx0GRioFGXLgd+\n" +
                "\\nKj3E9KVSy4bHFUlYVyWVoiMoMHpVapWYEgilyMZxVITH+YGjC/xetQMCOOtOXA61\n" +
                "\\nKcBlYjjHNAGtoEUX9pRefgq";

        JsonObject jsonObjects = new JsonObject();
        jsonObjects.addProperty("userId", loginResponse.getProfileInfo().getUserId());
        jsonObjects.addProperty("name", etEvent.getText().toString().trim());
        jsonObjects.addProperty("place", etPlace.getText().toString().trim());
        jsonObjects.addProperty("description", etDescription.getText().toString().trim());
        jsonObjects.addProperty("startDate", tvEventDate.getText().toString().trim() + " " + tvDOB.getText().toString().trim());
        jsonObjects.addProperty("image", "");
        jsonObjects.add("invitees", jsonObject);
        Ion.with(getActivity())
                .load("POST", "http://107.21.193.184/customEvent")
                .setJsonObjectBody(jsonObjects).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                progress.dismiss();
                if (result == null) {
                    Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
                } else {
                    getActivity().onBackPressed();
                }
            }
        });
    }

    class AdapterMyContacts extends RecyclerView.Adapter<AdapterMyContacts.MyViewHolder> {
        @Override
        public AdapterMyContacts.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_select_contact, parent, false);

            return new AdapterMyContacts.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final AdapterMyContacts.MyViewHolder holder, final int position) {
            holder.profileName.setText(getContactModalArrayList.get(position).getFullName());

            Glide.with(getActivity()).load(getContactModalArrayList.get(position).getPhotoURL()).into(holder.profileImege);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < studentModal.getContactsList().getMyContacts().size(); i++) {
                        if (getContactModalArrayList.get(position).getContactId().equals(studentModal.getContactsList().getMyContacts().get(i).getContactId())) {
                            studentModal.getContactsList().getMyContacts().get(i).setSelected(false);
                        }
                    }
                    initUI();
                }
            });
        }

        @Override
        public int getItemCount() {
            return getContactModalArrayList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView profileImege, delete;
            TextView profileName;

            public MyViewHolder(View view) {
                super(view);
                profileImege = view.findViewById(R.id.item_my_contacts_image);
                profileName = view.findViewById(R.id.item_my_contacts_name);
                delete = view.findViewById(R.id.delete);
            }
        }
    }
}

