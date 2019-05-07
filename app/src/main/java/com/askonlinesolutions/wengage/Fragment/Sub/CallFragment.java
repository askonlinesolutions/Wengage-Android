package com.askonlinesolutions.wengage.Fragment.Sub;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Model.InvitationModal;
import com.askonlinesolutions.wengage.Networks.NetworkConstants;
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

import java.util.Calendar;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallFragment extends Fragment implements OnResponseInterface {


    private Progress progress;
    private InvitationModal invitationModal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        if (Utility.isNetworkConnected(getActivity())) {
            callApiForChatList();
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();

        }
    }

    private void callApiForChatList() {
        TinyDB tinyDB = new TinyDB(getActivity());
        progress.show();

        Ion.with(this)
                .load(NetworkConstants.ALL_INVITATION + "?" + "userId=" + tinyDB.getInt(Constants.USER_ID)
                        + "&&" + "pageNum=" + 1)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        progress.dismiss();

                        invitationModal = new Gson().fromJson(result, InvitationModal.class);
                        if (invitationModal.getStatus() == 1) {
                            rv.setAdapter(new AdapterCall());
                        } else {
                            Toast.makeText(getActivity(), "You don't have any invitation", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
//        startActivity(new Intent(CompleteProfile2Details.this, CustomizedProfileActivity.class));
    }


    private RecyclerView rv;

    private void init() {
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        rv = getView().findViewById(R.id.fragment_call_recycler);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onApiResponse(Object response) {
        progress.dismiss();
        if (Utility.isNetworkConnected(getActivity())) {
            callApiForChatList();
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }

    @Override
    public void onApiFailure(String message) {
        progress.dismiss();
    }


    class AdapterCall extends RecyclerView.Adapter<AdapterCall.MyViewHolder> {


        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView profileImege;
            TextView profileName, eventName, eventDate, pendingTv;
            Button pending;
            ImageView accept, reject;
            LinearLayout linearLayout;

            public MyViewHolder(View view) {
                super(view);
                profileImege = view.findViewById(R.id.item_call_image);
                profileName = view.findViewById(R.id.item_call_name);
                pendingTv = view.findViewById(R.id.pending);
                eventDate = view.findViewById(R.id.event_date);
                eventName = view.findViewById(R.id.event_name);
                accept = view.findViewById(R.id.accept_button);
                reject = view.findViewById(R.id.decline_button);
                pending = view.findViewById(R.id.request_pending);
                linearLayout = view.findViewById(R.id.button_layout);
            }

        }

        @Override
        public AdapterCall.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_call, parent, false);

            return new AdapterCall.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final AdapterCall.MyViewHolder holder, final int position) {
            holder.profileName.setText(invitationModal.getEventInvitations().get(position).getUserName());
            holder.eventName.setText(invitationModal.getEventInvitations().get(position).getEventName());
            holder.eventDate.setText(invitationModal.getEventInvitations().get(position).getStartDate());
            Glide.with(getActivity()).load(invitationModal.getEventInvitations().get(position).getPhotoURL()).into(holder.profileImege);
            holder.accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String status = "A";
                    String contactId = String.valueOf(invitationModal.getEventInvitations().get(position).getInviteId());
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setType("vnd.android.cursor.item/event");

                    Calendar cal = Calendar.getInstance();
                    long startTime = cal.getTimeInMillis();
//                    long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;

                    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, invitationModal.getEventInvitations().get(position).getStartDate());
                    intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

                    intent.putExtra(CalendarContract.Events.TITLE, invitationModal.getEventInvitations().get(position).getEventName());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, invitationModal.getEventInvitations().get(position).getAddress());

                    startActivity(intent);
                    callAcceptMethod(contactId, status);
                }
            });
            holder.reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String status = "R";
                    String contactId = String.valueOf(invitationModal.getEventInvitations().get(position).getInviteId());
                    callAcceptMethod(contactId, status);
                }
            });
            TinyDB tinyDB=new TinyDB(getActivity());
            if (tinyDB.getInt(Constants.USER_ID)==(invitationModal.getEventInvitations().get(position).getInvitedById())){
                holder.pendingTv.setVisibility(View.VISIBLE);
                holder.linearLayout.setVisibility(View.GONE);
            }else {
                holder.pendingTv.setVisibility(View.GONE);
                holder.linearLayout.setVisibility(View.VISIBLE);
            }
           /* if (invitationModal.getEventInvitations().get(position).getInviteStatus().equals("P")) {
                holder.pendingTv.setVisibility(View.VISIBLE);
                holder.linearLayout.setVisibility(View.GONE);
            } else {
                holder.pendingTv.setVisibility(View.GONE);
                holder.linearLayout.setVisibility(View.VISIBLE);
            }*/
            holder.pendingTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callDeleteInviteApi(invitationModal.getEventInvitations().get(position).getInviteId());
                }
            });
        }

        @Override
        public int getItemCount() {
            return invitationModal.getEventInvitations().size();
        }
    }

    private void callDeleteInviteApi(String inviteId) {
        progress.show();
        TinyDB tinyDB = new TinyDB(getActivity());
        Ion.with(getActivity())
                .load("DELETE", "http://107.21.193.184/deleteInvite")
                .setBodyParameter("userId", String.valueOf(tinyDB.getInt(Constants.USER_ID)))
                .setBodyParameter("inviteId", inviteId)
                .asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                callApiForChatList();
            }
        });
    }

    private void callAcceptMethod(String contactId, String status) {
        if (Utility.isNetworkConnected(getActivity())) {
            TinyDB tinyDB = new TinyDB(getActivity());
            progress.show();
            int userId = tinyDB.getInt(Constants.USER_ID);
            Call call = APIClient.getInstance().getApiInterface().respondToInvites(userId, contactId, status);
            new ResponseListner(this).getResponse(getActivity(), call);
        } else {
            Dialog errorDialog = null;
            errorDialog = Utility.createErrorDialog(getActivity(), errorDialog, "Network error");
            errorDialog.show();
        }
    }
}
