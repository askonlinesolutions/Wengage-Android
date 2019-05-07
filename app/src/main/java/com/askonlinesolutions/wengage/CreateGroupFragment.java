package com.askonlinesolutions.wengage;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
import com.askonlinesolutions.wengage.Model.UpdatedModal;
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
import java.util.ArrayList;

import io.reactivex.functions.Consumer;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.askonlinesolutions.wengage.Fragment.ContactFragment.jsonObject;
import static com.askonlinesolutions.wengage.Fragment.ContactFragment.studentModal;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateGroupFragment extends Fragment implements View.OnClickListener {
    private EditText groupName;
    private ImageView camera, groupImage;
    private TextView selectContact, createGroup;
    private Dialog errorDialog;
    private TinyDB tinyDB;
    public static final int REQUEST_CAMERA_FOR_GROUP = 222;
    private boolean result;
    private LoginResponse loginResponse;
    private Gson gson = new Gson();
    private LinearLayout fragment_chat_layout_arrow;
    private RecyclerView selected_contact_rv;
    private File imgFile;
    private Progress progress;
    private View view;
    public static UpdatedModal studentUpdateModal;
    private ArrayList<UpdatedModal> getContactModalArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.create_group_fragment, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        tinyDB = new TinyDB(getActivity());
        loginResponse = gson.fromJson(tinyDB.getString("login_data"), LoginResponse.class);
        initUI();
        return view;
    }

    private void initUI() {
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        groupName = view.findViewById(R.id.groupName);

        selected_contact_rv = view.findViewById(R.id.selected_contact_rv);
        fragment_chat_layout_arrow = view.findViewById(R.id.fragment_chat_layout_arrow);
        camera = view.findViewById(R.id.camera);
        groupImage = view.findViewById(R.id.groupImage);
        selectContact = view.findViewById(R.id.selectContact);
        createGroup = view.findViewById(R.id.createGroup);

        camera.setOnClickListener(this);
        fragment_chat_layout_arrow.setOnClickListener(this);
        selectContact.setOnClickListener(this);
        createGroup.setOnClickListener(this);
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


    }

    public void setProfilePic(Bitmap data) {
        groupImage.setImageBitmap(data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_chat_layout_arrow:
                getActivity().onBackPressed();
                break;
            case R.id.createGroup:
                callCreateGroupMethod();
                break;
            case R.id.selectContact:
                tinyDB.putString(Constant.FROM_GROUP, "1");
                ((HomeActivity) getActivity()).replaceCreateContactFragment();
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
        }
    }

    private void callCreateGroupMethod() {
        if (groupName.getText().toString().trim().isEmpty()) {
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please enter group name");
            errorDialog.show();
        } else if (imgFile == null) {
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please click group image");
            errorDialog.show();
        } else {
            if (Utility.isNetworkConnected(getActivity())) {
                if (jsonObject != null && jsonObject.size() > 0) {
                    callCreateGroupApi();
                } else {

                    errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please select atlearm one member for create group");
                    errorDialog.show();
                }

            } else {
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
                errorDialog.show();
            }

        }
    }

    private void callCreateGroupApi() {
        progress.show();
        JsonObject jsonObjects = new JsonObject();
        jsonObjects.addProperty("userId", loginResponse.getProfileInfo().getUserId());
        jsonObjects.addProperty("chatTitle", groupName.getText().toString().trim());
        jsonObjects.addProperty("chatIcon", "");
        jsonObjects.add("members", jsonObject);
        Ion.with(getActivity())
                .load("http://107.21.193.184/createChatGroup").setJsonObjectBody(jsonObjects)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
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
//        fileName = imgFile.getName ();
//        signUpActivityBinding.licenceName.setText ( fileName );
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
