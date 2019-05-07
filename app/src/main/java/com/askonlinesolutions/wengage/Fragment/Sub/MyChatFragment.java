package com.askonlinesolutions.wengage.Fragment.Sub;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Adapter.AdapterMyChat;
import com.askonlinesolutions.wengage.Fragment.Main.ChatFragment;
import com.askonlinesolutions.wengage.Model.ChatListModal;
import com.askonlinesolutions.wengage.Networks.NetworkConstants;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Progress;
import com.askonlinesolutions.wengage.utils.Utility;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mukesh.tinydb.TinyDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyChatFragment extends Fragment implements AdapterMyChat.Interface_AdapterMyChat, View.OnClickListener {
    private Progress progress;
    private ChatListModal chatListModal;
    ImageView createGroup;

    public MyChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_chat, container, false);
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
                .load(NetworkConstants.MY_CHATS + "?" + "userId=" + tinyDB.getInt(Constants.USER_ID))
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        progress.dismiss();

                        chatListModal = new Gson().fromJson(result, ChatListModal.class);
                        if (chatListModal != null) {
                            if (chatListModal.getStatus() == 1) {
                                if (chatListModal.getMyChats().size() > 0) {
                                    AdapterMyChat adapterMyChat = new AdapterMyChat();
                                    rv.setAdapter(adapterMyChat);
                                } else {
                                }
                            } else {
                                Toast.makeText(getActivity(), "You don't have any chat list", Toast.LENGTH_SHORT).show();

                            }

                        }

//                        startActivity(new Intent(CompleteProfile2Details.this, CustomizedProfileActivity.class));
//                        Toast.makeText(getActivity(), "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                    }
                });
//        startActivity(new Intent(CompleteProfile2Details.this, CustomizedProfileActivity.class));
    }

    private RecyclerView rv;

    private void init() {
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        createGroup = getView().findViewById(R.id.createGroup);
        progress.setCanceledOnTouchOutside(false);
        //RecyclerView
        rv = getView().findViewById(R.id.fragment_my_chat_recycler);
        LinearLayoutManager lay
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lay);
        createGroup.setOnClickListener(this);
    }


    @Override
    public void method_AdapterMyChat(int pos) {

        ChatFragment.viewPager.setCurrentItem(2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createGroup:
                ((HomeActivity) getActivity()).replaceCreateGroupFragment();
                break;
        }
    }

    class AdapterMyChat extends RecyclerView.Adapter<AdapterMyChat.MyViewHolder> {

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView tv_name, tv_city, tv_time, tv_text, tv_count;
            public ImageView iv_image;
            private LinearLayout layout;

            public MyViewHolder(View view) {
                super(view);

                tv_name = view.findViewById(R.id.item_adapter_my_chat_name);
                tv_city = view.findViewById(R.id.item_adapter_my_chat_city);
                tv_time = view.findViewById(R.id.item_adapter_my_chat_time);
                tv_text = view.findViewById(R.id.item_adapter_my_chat_text);
                tv_count = view.findViewById(R.id.item_adapter_my_chat_count);
                layout = view.findViewById(R.id.layout);

                iv_image = view.findViewById(R.id.item_adapter_my_chat_images);

            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_adapter_my_chat, parent, false);

            return new AdapterMyChat.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final AdapterMyChat.MyViewHolder holder, final int position) {
            Glide.with(getActivity()).load(chatListModal.getMyChats().get(position).getChatIcon()).into(holder.iv_image);
            holder.tv_name.setText(chatListModal.getMyChats().get(position).getChatTitle());
            holder.tv_city.setText(chatListModal.getMyChats().get(position).getChatTitle());
            holder.tv_time.setText(chatListModal.getMyChats().get(position).getLastMsgTime());
            holder.tv_text.setText(chatListModal.getMyChats().get(position).getLastMsg());
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TinyDB tinyDB = new TinyDB(getActivity());
                    tinyDB.putString(Constants.CHAT_USER_NAME, chatListModal.getMyChats().get(position).getChatTitle());
                    Bundle bundle = new Bundle();
                    bundle.putString("str_check", "1");
                    ChatRoomsFragment2 fragment = new ChatRoomsFragment2();
                    fragment.setArguments(bundle);
                    ((HomeActivity) getActivity()).replaceChatsFragment(fragment);
                }
            });

        }

        @Override
        public int getItemCount() {
            return chatListModal.getMyChats().size();
        }

    }
}