package com.askonlinesolutions.wengage.Fragment;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Model.GetContactModal;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
import com.askonlinesolutions.wengage.Model.UpdatedModal;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.utils.Progress;
import com.askonlinesolutions.wengage.utils.Utility;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mukesh.tinydb.TinyDB;

import java.util.ArrayList;

import static com.askonlinesolutions.wengage.Fragment.ContactFragment.jsonObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateGroupIndividualFragment extends Fragment implements View.OnClickListener {
    private Progress progress;
    private TinyDB tinyDB;
    private LinearLayout fragment_chat_layout_arrow;
    private Gson gson = new Gson();
    private RecyclerView search_rv, selected_rv;
    private LoginResponse loginResponse;
    private GetContactModal studentModal;
    private Dialog errorDialog;
    private TextView logIn_button;
    private JsonArray jsonObject;
    AdapterMyContactss adapterMyContactss;
    private UpdatedModal studentUpdateModal;
    private ArrayList<UpdatedModal> getContactModalArrayList;
    private ArrayList<UpdatedModal> getContactModalUpdatedArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.create_group_individual_fragment, container, false);
        tinyDB = new TinyDB(getActivity());
        loginResponse = gson.fromJson(tinyDB.getString("login_data"), LoginResponse.class);
        initUI(view);
        if (Utility.isNetworkConnected(getActivity())) {
            callGetContactApi(loginResponse.getProfileInfo().getUserId());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

        if (getContactModalArrayList != null && getContactModalArrayList.size() > 0) {
            LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            selected_rv.setLayoutManager(linearLayoutManagers);

            adapterMyContactss = new AdapterMyContactss();
            selected_rv.setAdapter(adapterMyContactss);
        }

        return view;
    }

    private void callGetContactApi(int userId) {
        progress.show();
        Ion.with(getActivity())
                .load("http://107.21.193.184/contact/getAllInvitationsAndContacts/" + userId)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        progress.dismiss();
                        if (result == null) {
                            Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
                        } else {
                            studentModal = new Gson().fromJson(result, GetContactModal.class);
                            if (studentModal.getContactsList().getMyContacts().size() > 0) {
                                for (int i = 0; i < studentModal.getContactsList().getMyContacts().size(); i++) {
                                    studentModal.getContactsList().getMyContacts().get(i).setSelected(false);
                                }
                                LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                                search_rv.setLayoutManager(linearLayoutManagers);
                                search_rv.setAdapter(new AdapterMyContacts());
                            } else {
                                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "You don't have any contact");
                                errorDialog.show();
                            }
                        }
                    }
                });
    }

    private void initUI(View view) {
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        search_rv = view.findViewById(R.id.search_rv);
        selected_rv = view.findViewById(R.id.selected_rv);
        fragment_chat_layout_arrow = view.findViewById(R.id.fragment_chat_layout_arrow);
        logIn_button = view.findViewById(R.id.logIn_button);
        logIn_button.setOnClickListener(this);
        fragment_chat_layout_arrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logIn_button:
                jsonObject = new JsonArray();
                for (int i = 0; i < getContactModalUpdatedArrayList.size(); i++) {
                    jsonObject.add(getContactModalUpdatedArrayList.get(i).getContactId());

                }
                if (jsonObject.size() > 0) {
                    if (Utility.isNetworkConnected(getActivity())) {
                        callCreateGroupApi();

                    } else {
                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
                        errorDialog.show();
                    }

                } else {
                    errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Please select any contact from list");
                    errorDialog.show();
                }

                break;
            case R.id.fragment_chat_layout_arrow:
                getActivity().onBackPressed();
                break;
        }
    }

    private void callCreateGroupApi() {

        progress.show();
        JsonObject jsonObjects = new JsonObject();
        jsonObjects.addProperty("userId", loginResponse.getProfileInfo().getUserId());
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

    class AdapterMyContacts extends RecyclerView.Adapter<AdapterMyContacts.MyViewHolder> {


        @Override
        public AdapterMyContacts.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_my_contact, parent, false);

            return new AdapterMyContacts.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final AdapterMyContacts.MyViewHolder holder, final int position) {
            holder.profileName.setText(studentModal.getContactsList().getMyContacts().get(position).getFullName());
            Glide.with(getActivity()).load(studentModal.getContactsList().getMyContacts().get(position).getPhotoURL()).into(holder.profileImege);
            if (studentModal.getContactsList().getMyContacts().get(position).isSelected()) {
                holder.checkBox.setChecked(true);
            } else {
                holder.checkBox.setChecked(false);
            }
            getContactModalArrayList = new ArrayList<>();
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    studentUpdateModal = new UpdatedModal();
                    if (holder.checkBox.isChecked()) {

                        studentModal.getContactsList().getMyContacts().get(position).setSelected(true);
                        studentUpdateModal.setContactId(String.valueOf(studentModal.getContactsList().getMyContacts().get(position).getUserId()));
                        studentUpdateModal.setFullName(studentModal.getContactsList().getMyContacts().get(position).getFullName());
                        studentUpdateModal.setPhotoURL(studentModal.getContactsList().getMyContacts().get(position).getPhotoURL());
                        studentUpdateModal.setSelected(studentModal.getContactsList().getMyContacts().get(position).isSelected());
                        getContactModalArrayList.add(studentUpdateModal);
                        callAdapter();

                    } else {

                        studentModal.getContactsList().getMyContacts().get(position).setSelected(false);
                        for (int j = 0; j < getContactModalArrayList.size(); j++) {
                            if (getContactModalArrayList.get(j).getContactId().equals(String.valueOf(studentModal.getContactsList().getMyContacts().get(position).getUserId()))) {
                                getContactModalArrayList.remove(getContactModalArrayList.get(j));
                            }
                        }
                        callAdapter();
                    }
                }


            });
        }

        @Override
        public int getItemCount() {
            return studentModal.getContactsList().getMyContacts().size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView profileImege;
            TextView profileName;
            CheckBox checkBox;

            public MyViewHolder(View view) {
                super(view);
                profileImege = view.findViewById(R.id.item_my_contacts_image);
                profileName = view.findViewById(R.id.item_my_contacts_name);
                checkBox = view.findViewById(R.id.checkBox);
            }
        }
    }

    private void callAdapter() {
        getContactModalUpdatedArrayList = new ArrayList<>();
        for (int i = 0; i < getContactModalArrayList.size(); i++) {
            UpdatedModal updatedModal = new UpdatedModal();
            if (getContactModalArrayList.get(i).isSelected()) {
                updatedModal.setPhotoURL(getContactModalArrayList.get(i).getPhotoURL());
                updatedModal.setFullName(getContactModalArrayList.get(i).getFullName());
                updatedModal.setContactId(getContactModalArrayList.get(i).getContactId());
                getContactModalUpdatedArrayList.add(updatedModal);
            }
        }
        LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        selected_rv.setLayoutManager(linearLayoutManagers);
        adapterMyContactss = new AdapterMyContactss();
        selected_rv.setAdapter(adapterMyContactss);
    }

    class AdapterMyContactss extends RecyclerView.Adapter<AdapterMyContactss.MyViewHolder> {


        @Override
        public AdapterMyContactss.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_select_contact, parent, false);

            return new AdapterMyContactss.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final AdapterMyContactss.MyViewHolder holder, final int position) {
            holder.profileName.setText(getContactModalUpdatedArrayList.get(position).getFullName());

            Glide.with(getActivity()).load(getContactModalUpdatedArrayList.get(position).getPhotoURL()).into(holder.profileImege);

        }

        @Override
        public int getItemCount() {
            return getContactModalUpdatedArrayList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView profileImege;
            TextView profileName;

            public MyViewHolder(View view) {
                super(view);
                profileImege = view.findViewById(R.id.item_my_contacts_image);
                profileName = view.findViewById(R.id.item_my_contacts_name);
            }
        }
    }

}
