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
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Adapter.AdapterChatRooms;
import com.askonlinesolutions.wengage.Model.ChatRoomModal;
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
public class ChatRoomsFragment extends Fragment implements AdapterChatRooms.Interface_AdapterChatRooms, View.OnClickListener {
    private Progress progress;
    private RecyclerView rv;
    private ChatRoomModal chatRoomModal;
    ImageView createGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_rooms, container, false);
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


    private void init() {
        progress = new Progress(getActivity());
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        createGroup = getView().findViewById(R.id.createGroup);
        rv = getView().findViewById(R.id.fragment_chat_rooms_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false);
        rv.setLayoutManager(linearLayoutManager);

        createGroup.setOnClickListener(this);
//        rv.setAdapter(new AdapterChatRooms());
    }

    private void callApiForChatList() {
        TinyDB tinyDB = new TinyDB(getActivity());
        progress.show();
        Ion.with(this)
                .load(NetworkConstants.CHAT_ROOMS + "?" + "userId=" + tinyDB.getInt(Constants.USER_ID))
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        progress.dismiss();

                        chatRoomModal = new Gson().fromJson(result, ChatRoomModal.class);
                        if (chatRoomModal.getStatus() == 1) {
                            rv.setAdapter(new AdapterChatRooms());
                        } else {
                            Toast.makeText(getActivity(), "You don't have any invitation", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
//        startActivity(new Intent(CompleteProfile2Details.this, CustomizedProfileActivity.class));
    }

    @Override
    public void method_AdapterChatRooms(int pos) {


        // new BaseClass(getActivity()).callFragment(fragment, fragment.getClass().getName(), getParentFragment().getFragmentManager());


        //    ChatFragment.adapter.replaceFragment(fragment, "CHAT ROOMS", 2);

  /*      ChatFragment.tabLayout.removeAllTabs();
        ChatFragment.adapter = new AdapterViewPagerInTheKnow(getChildFragmentManager());
        ChatFragment.adapter.addFragment(new MyChatFragment(), "MY CHATS");
        ChatFragment.adapter.addFragment(new CallFragment(), "INVITATIONS");
        ChatFragment.adapter.addFragment(new ChatRoomsFragment(), "CHAT ROOMS");
        ChatFragment.adapter.addFragment(new ContactsFragment(), "CONTACTS");//        C
        ChatFragment.viewPager.setAdapter(ChatFragment.adapter);*/

     /*   for (int i = 0; i < ChatFragment.tabLayout.getTabCount(); i++) {
            TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tab_custom_text_view,null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //    tv.setLetterSpacing((float) 0.15);
            }
            ChatFragment.tabLayout.getTabAt(i).setCustomView(tv);
        }
*/
//       ChatFragment.adapter.replaceFragment(fragment, "CHAT ROOMS",2);
//      ChatFragment.adapter.notifyDataSetChanged();

//        new BaseClass(getContext()).callFragment(new ChatRooms1Fragment(), new ChatRoomsFragment()
//        .getClass().getName(), getParentFragment().getFragmentManager());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createGroup:
                ((HomeActivity) getActivity()).replaceCreateGroupFragment();
                break;
        }
    }

    class AdapterChatRooms extends RecyclerView.Adapter<AdapterChatRooms.MyViewHolder> {


        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView tv_title, tv_time, tv_active_members;
            public ImageView iv_image;

            public MyViewHolder(View view) {
                super(view);

                tv_title = view.findViewById(R.id.item_chat_rooms_title);
                tv_time = view.findViewById(R.id.item_chat_rooms_time);
                tv_active_members = view.findViewById(R.id.item_chat_rooms_active_member);
                iv_image = view.findViewById(R.id.item_chat_rooms_image);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("str_check", "1");

                        ChatRoomsFragment2 fragment = new ChatRoomsFragment2();
                        fragment.setArguments(bundle);
                        ((HomeActivity) getActivity()).replaceChatsFragment(fragment);

//                        click.method_AdapterChatRooms(getAdapterPosition());
                    }
                });
            }
        }

        @Override
        public AdapterChatRooms.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_chat_rooms, parent, false);
            return new AdapterChatRooms.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final AdapterChatRooms.MyViewHolder holder, final int position) {
            Glide.with(getActivity()).load(chatRoomModal.getChatRooms().get(position).getChatIcon()).into(holder.iv_image);
            holder.tv_title.setText(chatRoomModal.getChatRooms().get(position).getChatTitle());
            holder.tv_time.setText(chatRoomModal.getChatRooms().get(position).getLastMsgTime());
            holder.tv_active_members.setText("Active Members :" + chatRoomModal.getChatRooms().get(position).getMembers().size());

        }

        @Override
        public int getItemCount() {
            return chatRoomModal.getChatRooms().size();
        }

    }
}
