package com.askonlinesolutions.wengage.Fragment.Sub;


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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Model.GetContatctModal;
import com.askonlinesolutions.wengage.Model.Response.LoginResponse;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.network.APIClient;
import com.askonlinesolutions.wengage.network.OnResponseInterface;
import com.askonlinesolutions.wengage.network.ResponseListner;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Progress;
import com.askonlinesolutions.wengage.utils.Utility;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mukesh.tinydb.TinyDB;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment implements OnResponseInterface {

    //    private ArrayList<Integer> profile_images = new ArrayList<>();
//    private ArrayList<String> profile_names = new ArrayList<>();
//    private ArrayList<Integer> status = new ArrayList<>();
//    private ViewPager viewPager;
//    private TabLayout tabLayout;
    private GetContatctModal studentModal;
    private RecyclerView fragment_contacts_recycler;
    //    private AppBarLayout appBarLayout;
    FrameLayout invitationRvLayout;
    private TinyDB tinyDB;
    private Gson gson = new Gson();
    private RecyclerView invitationRecyclerView;
    private LoginResponse loginResponse;
    private String contactIds;
    private Progress progress;

    private Dialog dialog_error;
    private TextView dialog_error_tv, dialog_error_btn,noData,invitation_header;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        tinyDB = new TinyDB(getActivity());
        loginResponse = gson.fromJson(tinyDB.getString("login_data"), LoginResponse.class);
//        createList();
        init(view);
        if (Utility.isNetworkConnected(getActivity())) {
            callGetContactApi(loginResponse.getProfileInfo().getUserId());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
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

                            studentModal = new Gson().fromJson(result, GetContatctModal.class);
                            if (studentModal.getContactsList().getMyInvitations().size() > 0) {
//                                invitationRecyclerView.setVisibility(View.VISIBLE);
//                                noData.setVisibility(View.GONE);
                                invitation_header.setVisibility(View.VISIBLE);
                                invitationRvLayout.setVisibility(View.VISIBLE);
                            } else {
                                invitation_header.setVisibility(View.GONE);
                                invitationRvLayout.setVisibility(View.GONE);
//                                invitationRecyclerView.setVisibility(View.INVISIBLE);
//                                noData.setVisibility(View.VISIBLE);
                            }
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            invitationRecyclerView.setLayoutManager(linearLayoutManager);
                            invitationRecyclerView.setAdapter(new ContactInviteAdapter());

                            LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            fragment_contacts_recycler.setLayoutManager(linearLayoutManagers);
                            fragment_contacts_recycler.setAdapter(new AdapterMyContacts());
                        }

//                        Toast.makeText(getActivity(), studentModal + "", Toast.LENGTH_SHORT).show();
                        // do stuff with the result or error
                    }
                });
    }


    private void init(View view) {
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        invitationRecyclerView = view.findViewById(R.id.invitationRecyclerView);
        fragment_contacts_recycler = view.findViewById(R.id.fragment_contacts_recycler);
        invitation_header = view.findViewById(R.id.invitation_header);
        invitationRvLayout = view.findViewById(R.id.invitation_rv_layout);

        noData = view.findViewById(R.id.no_data);

    }

    @Override
    public void onApiResponse(Object response) {
        progress.dismiss();
        if (Utility.isNetworkConnected(getActivity())) {
            callGetContactApi(loginResponse.getProfileInfo().getUserId());
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }

    }

    @Override
    public void onApiFailure(String message) {
        progress.dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


    private void showDialog() {
        if (dialog_error == null) {
            dialog_error = new Dialog(getActivity());
            dialog_error.setContentView(R.layout.delete_dialog);

            dialog_error.setCancelable(true);
            dialog_error.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog_error.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        dialog_error.show();

            dialog_error_tv = dialog_error.findViewById(R.id.yes);
            dialog_error_btn = dialog_error.findViewById(R.id.no);

//            dialog_error_tv.setTe

            dialog_error_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utility.isNetworkConnected(getActivity())) {
                        dialog_error.cancel();
                        callDeleteApis();
                    } else {
                        Dialog errorDialog = null;
                        errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
                        errorDialog.show();
                    }
                }
            });
            dialog_error_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog_error.cancel();
                }
            });
            dialog_error.show();
        } else {
            dialog_error.show();
        }
    }

    private void callDeleteApis() {
        progress.show();
        int userId = tinyDB.getInt(Constants.USER_ID);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userId);
        jsonObject.addProperty("contactId", contactIds);
        Ion.with(getContext())
                .load("DELETE", "http://107.21.193.184/contact/deleteRequestOrContact")
                .setJsonObjectBody(jsonObject)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        progress.dismiss();
                        callGetContactApi(loginResponse.getProfileInfo().getUserId());
                        Toast.makeText(getActivity(), "Your request deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                });
//        Call call = APIClient.getInstance().getApiInterface().respondToRequestDelete(userId, contactIds);
//        new ResponseListner(this).getResponse(getActivity(), call);
    }

    private void callAcceptMethod(String contactId, String status) {
        if (Utility.isNetworkConnected(getActivity())) {

            progress.show();
            int userId = tinyDB.getInt(Constants.USER_ID);
            Call call = APIClient.getInstance().getApiInterface().respondToRequest(userId, contactId, status);
            new ResponseListner(this).getResponse(getActivity(), call);
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    class ContactInviteAdapter extends RecyclerView.Adapter<ContactInviteAdapter.MyViewHolder> {

        @Override
        public ContactInviteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.invitation_item, parent, false);
            return new ContactInviteAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ContactInviteAdapter.MyViewHolder holder, final int position) {
            if (studentModal.getContactsList().getMyInvitations().get(position).getPendingReqStatus().equals("SENT")) {
                holder.reject.setVisibility(View.GONE);
                holder.aceept.setText("Pending");
            } else {
            }
            holder.name.setText(studentModal.getContactsList().getMyInvitations().get(position).getFullName());
            Glide.with(getActivity()).load(studentModal.getContactsList().getMyInvitations().get(position).getPhotoURL()).into(holder.inviteUserImage);
            holder.aceept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.aceept.getText().toString().equalsIgnoreCase("accept")) {
                        String status = "A";
                        String contactId = studentModal.getContactsList().getMyInvitations().get(position).getContactId();
                        callAcceptMethod(contactId, status);
                    } else {

                        contactIds = studentModal.getContactsList().getMyInvitations().get(position).getContactId();
                        showDialog();
                    }

                }
            });
            holder.reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String status = "R";
                    String contactId = studentModal.getContactsList().getMyInvitations().get(position).getContactId();
                    callAcceptMethod(contactId, status);
                }
            });
        }


        @Override
        public int getItemCount() {
            return studentModal.getContactsList().getMyInvitations().size();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView inviteUserImage;
            TextView name, aceept, reject;
            View bottom_line;
            RelativeLayout mainLayout;

            public MyViewHolder(View view) {
                super(view);
                mainLayout = view.findViewById(R.id.mainLayout);
                reject = view.findViewById(R.id.reject);
                aceept = view.findViewById(R.id.aceept);
                name = view.findViewById(R.id.name);
                inviteUserImage = view.findViewById(R.id.inviteUserImage);
            }
        }
    }

    class AdapterMyContacts extends RecyclerView.Adapter<AdapterMyContacts.MyViewHolder> {


        @Override
        public AdapterMyContacts.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_my_contacts, parent, false);

            return new AdapterMyContacts.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final AdapterMyContacts.MyViewHolder holder, final int position) {
            holder.profileName.setText(studentModal.getContactsList().getMyContacts().get(position).getFullName());
            Glide.with(getActivity()).load(studentModal.getContactsList().getMyContacts().get(position).getPhotoURL()).into(holder.profileImege);

        }

        @Override
        public int getItemCount() {
            return studentModal.getContactsList().getMyContacts().size();
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