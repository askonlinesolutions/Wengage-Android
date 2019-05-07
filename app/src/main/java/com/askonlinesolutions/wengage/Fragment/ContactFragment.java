package com.askonlinesolutions.wengage.Fragment;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Model.GetContactModal;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
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
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment implements View.OnClickListener {
    private Progress progress;
    private TinyDB tinyDB;
    private LinearLayout fragment_chat_layout_arrow;
    private Gson gson = new Gson();
    private RecyclerView search_rv, selected_rv;
    private LoginResponse loginResponse;
    public static GetContactModal studentModal;
    private Dialog errorDialog;
    private TextView logIn_button;
    private EditText search_main;
    public static JsonArray jsonObject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.contact_fragment, container, false);
        tinyDB = new TinyDB(getActivity());
        loginResponse = gson.fromJson(tinyDB.getString("login_data"), LoginResponse.class);
        initUI(view);
        if (studentModal == null) {
            if (Utility.isNetworkConnected(getActivity())) {
                callGetContactApi(loginResponse.getProfileInfo().getUserId());
            } else {
                Dialog errorDialog = null;
                errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
                errorDialog.show();
            }
        } else {
            LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            search_rv.setLayoutManager(linearLayoutManagers);
            search_rv.setAdapter(new AdapterMyContacts());
        }

        return view;
    }

    void filter(String text) {
        ArrayList<GetContactModal.ContactsListBean.MyContactsBean> temp = new ArrayList();
        for (GetContactModal.ContactsListBean.MyContactsBean d : studentModal.getContactsList().getMyContacts()) {
            if (d.getFullName().toLowerCase().contains(text.toLowerCase())) {
                temp.add(d);
            }
        }
        LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        search_rv.setLayoutManager(linearLayoutManagers);
        AdapterMyNewContacts adapterMyNewContacts = new AdapterMyNewContacts(temp);
        search_rv.setAdapter(adapterMyNewContacts);

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
        search_main = view.findViewById(R.id.search_main);
        fragment_chat_layout_arrow = view.findViewById(R.id.fragment_chat_layout_arrow);
        logIn_button = view.findViewById(R.id.logIn_button);
        logIn_button.setOnClickListener(this);
        fragment_chat_layout_arrow.setOnClickListener(this);
        search_main.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logIn_button:
                jsonObject = new JsonArray();
                for (int i = 0; i < studentModal.getContactsList().getMyContacts().size(); i++) {
                    if (studentModal.getContactsList().getMyContacts().get(i).isSelected()) {
                        jsonObject.add(studentModal.getContactsList().getMyContacts().get(i).getUserId());
                    }
                }
                if (jsonObject.size() > 0) {
                    getActivity().onBackPressed();
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
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.checkBox.isChecked()) {
                        studentModal.getContactsList().getMyContacts().get(position).setSelected(true);
                    } else {
                        studentModal.getContactsList().getMyContacts().get(position).setSelected(false);
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
    class AdapterMyNewContacts extends RecyclerView.Adapter<AdapterMyNewContacts.MyViewHolder> {
        List<GetContactModal.ContactsListBean.MyContactsBean> myContactsBean;

        public AdapterMyNewContacts(ArrayList<GetContactModal.ContactsListBean.MyContactsBean> temp) {
            this.myContactsBean=temp;
        }

        @Override
        public AdapterMyNewContacts.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_my_contact, parent, false);

            return new AdapterMyNewContacts.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final AdapterMyNewContacts.MyViewHolder holder, final int position) {
            holder.profileName.setText(myContactsBean.get(position).getFullName());
            Glide.with(getActivity()).load(myContactsBean.get(position).getPhotoURL()).into(holder.profileImege);
            if (myContactsBean.get(position).isSelected()) {
                holder.checkBox.setChecked(true);
            } else {
                holder.checkBox.setChecked(false);
            }
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.checkBox.isChecked()) {
                        myContactsBean.get(position).setSelected(true);
                    } else {
                        myContactsBean.get(position).setSelected(false);
                    }
                }


            });
        }

        boolean flage=true;
        @Override
        public int getItemCount() {
            return myContactsBean.size();
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

}
