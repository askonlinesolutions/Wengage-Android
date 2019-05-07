package com.askonlinesolutions.wengage.Fragment.Sub;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.InputType;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.askonlinesolutions.wengage.Activity.Main.HomeActivity;
import com.askonlinesolutions.wengage.Adapter.AdapterCategoryListBottom1;
import com.askonlinesolutions.wengage.Adapter.AdapterCategoryListBottom1Events;
import com.askonlinesolutions.wengage.Adapter.AdapterHome3b;
import com.askonlinesolutions.wengage.Adapter.EmoticonsGridAdapter;
import com.askonlinesolutions.wengage.Adapter.EmoticonsPagerAdapter;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.Helper.CustomLayoutManager;
import com.askonlinesolutions.wengage.Model.ChatModal;
import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.utils.Constants;
import com.askonlinesolutions.wengage.utils.Helper;
import com.askonlinesolutions.wengage.utils.SanImagePicker;
import com.askonlinesolutions.wengage.utils.Sources;
import com.mukesh.tinydb.TinyDB;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import io.reactivex.functions.Consumer;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.askonlinesolutions.wengage.Activity.Main.HomeActivity.bottomTab;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatRoomsFragment2 extends Fragment implements View.OnClickListener,
        EmoticonsGridAdapter.KeyClickListener, AdapterCategoryListBottom1Events
                .Interface_RestaurantListEvents, AdapterCategoryListBottom1.Interface_RestaurantList1 {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_rooms_fragment2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tinyDB = new TinyDB(getContext());
        type = tinyDB.getString("bottom_click");
        bottomTab.setVisibility(View.GONE);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        init();
    }

    private RecyclerView chatRecycler;
    //  public static LinearLayout layout_bottom_bookmark;
    private LinearLayout layout_image_bookmark, createGroup, layout_image_emoji, layout_image_cross, layout_image_camera, fragment_chat_layout_arrow;
    public static ViewPager layout_bottom_emoji;
    private EditText edt_message;
    //    private EmojiconEditText emojiconEditText;
    View rootView;
    int unicode = 0x1F60B;
    private String str_check;
    //    EmojIconActions emojIcon;
    ImageView emoji_imageview;
    private TinyDB tinyDB;
    private RelativeLayout customLayout, createEvnt;
    LinearLayout bottom_bookmark_layout;
    private String type;
    private TextView tab_one, tab_two, hungry_text, fragment_chat_title;
    private LinearLayout tab_three, tab_four;

    ArrayList res_Names = new ArrayList<>(Arrays.asList("LOUNGES", "OPERA & SYMPHONY", "VEGETARIAN", "ITALIAN", "GOLF"));
    //    ArrayList res_City = new ArrayList<>(Arrays.asList("Toronto", "Toronto", "Toronto", "Toronto", "Richmond Hill"));
    ArrayList res_Images = new ArrayList<>(Arrays.asList(R.drawable.ic_lounges, R.drawable.ic_opera,
            R.drawable.ic_vegetarian, R.drawable.ic_ita, R.drawable.ic_golf));

    ArrayList rest_Images_2 = new ArrayList<>(Arrays.asList(R.drawable.rest_one, R.drawable.rest_two,
            R.drawable.rest_three, R.drawable.rest_four, R.drawable.rest_five, R.drawable.rest_six,
            R.drawable.rest_seven, R.drawable.rest_eight, R.drawable.rest_one, R.drawable.rest_two,
            R.drawable.rest_three, R.drawable.rest_four, R.drawable.rest_five, R.drawable.rest_six,
            R.drawable.rest_seven, R.drawable.rest_eight));

    public static boolean isBottomViewUp = false;

    private ChatAdapter chatAdapter;
    private LinearLayout emoticonsCover;
    private PopupWindow popupWindow;
    private int keyboardHeight;
    private RelativeLayout parentLayout;
    private boolean isKeyBoardVisible;
    private Bitmap[] emoticons;
    private LinearLayout fragment_chat_rooms_1_image_send;
    private View popUpView;
    private static final int NO_OF_EMOTICONS = 54;
    private RecyclerView rv_1, rv_heart, rv_venues, rv_events, rv_custom_events;

    private ArrayList<Integer> bottom_list_image = new ArrayList<>();
    private ArrayList<Integer> bottom_list_image_2 = new ArrayList<>();
    private ArrayList<String> bottom_list_circle = new ArrayList<>();
    private ArrayList<String> bottom_list_ad = new ArrayList<>();
    private ArrayList<String> bottom_list_name = new ArrayList<>();
    private ArrayList<String> bottom_list_review = new ArrayList<>();
    private ArrayList<String> bottom_list_distance = new ArrayList<>();
    private ArrayList<String> bottom_list_butterfly = new ArrayList<>();
    private ArrayList<String> bottom_list_check = new ArrayList<>();
    private ArrayList<String> bottom_list_bookmarks = new ArrayList<>();
    private ArrayList<String> bottom_list_interested = new ArrayList<>();


    private ArrayList<Integer> bottom_list_image_events = new ArrayList<>();
    private ArrayList<Integer> bottom_list_image_2_events = new ArrayList<>();
    private ArrayList<String> bottom_list_circle_events = new ArrayList<>();
    private ArrayList<String> bottom_list_ad_events = new ArrayList<>();
    private ArrayList<String> bottom_list_name_events = new ArrayList<>();
    private ArrayList<String> bottom_list_review_events = new ArrayList<>();
    private ArrayList<String> bottom_list_distance_events = new ArrayList<>();
    private ArrayList<String> bottom_list_butterfly_events = new ArrayList<>();
    private ArrayList<String> bottom_list_check_events = new ArrayList<>();
    private ArrayList<String> bottom_list_bookmarks_events = new ArrayList<>();
    private ArrayList<String> bottom_list_interested_events = new ArrayList<>();
    private ArrayList<ChatModal> chatModalArrayList = new ArrayList<>();
    private AdapterCategoryListBottom1 adapte1;
    private AdapterCategoryListBottom1Events adapterCategoryListBottom1Events;


    private void init() {
        str_check = getArguments().getString("str_check");


        chatRecycler = getView().findViewById(R.id.chatRecycler);
        fragment_chat_title = getView().findViewById(R.id.fragment_chat_title);
        fragment_chat_title.setText(tinyDB.getString(Constants.CHAT_USER_NAME));
        parentLayout = getView().findViewById(R.id.fragment_chat_rooms_1_parent_layout);
        //  layout_bottom_bookmark = getView().findViewById(R.id.fragment_chat_rooms_1_layout_bookmarks);
        createGroup = getView().findViewById(R.id.createGroup);
        layout_bottom_emoji = getView().findViewById(R.id.fragment_chat_rooms_1_layout_emoji);

        fragment_chat_rooms_1_image_send = getView().findViewById(R.id.fragment_chat_rooms_1_image_send);
        fragment_chat_layout_arrow = getView().findViewById(R.id.fragment_chat_layout_arrow);
        layout_image_bookmark = getView().findViewById(R.id.fragment_chat_rooms_1_image_bookmark);
        layout_image_emoji = getView().findViewById(R.id.fragment_chat_rooms_1_image_emoji);
        layout_image_cross = getView().findViewById(R.id.fragment_chat_rooms_1_image_cross);
        layout_image_camera = getView().findViewById(R.id.fragment_chat_rooms_1_image_camera);
        emoji_imageview = getView().findViewById(R.id.emoji_imageview);
        createEvnt = getView().findViewById(R.id.createEvnt);
        rv_events = getView().findViewById(R.id.home3_recycler_events);
        rv_heart = getView().findViewById(R.id.home3_recycler_heart);
        rv_events = getView().findViewById(R.id.home3_recycler_events);
        rv_venues = getView().findViewById(R.id.home3_recycler_venues);
        customLayout = getView().findViewById(R.id.customLayout);

//        hungry_text = getView().findViewById(R.id.hungry_chat);

        bottom_bookmark_layout = getView().findViewById(R.id.bottom_bookmark_layout);

        tab_one = getView().findViewById(R.id.fragment_profile_home_txt_venue);
        tab_two = getView().findViewById(R.id.home3_bottom_two);
        tab_three = getView().findViewById(R.id.home3_bottom_three);
        tab_four = getView().findViewById(R.id.home3_bottom_four);
        tab_one.setOnClickListener(this);
        tab_two.setOnClickListener(this);
        tab_three.setOnClickListener(this);
        createGroup.setOnClickListener(this);
        createEvnt.setOnClickListener(this);
        tab_four.setOnClickListener(this);
        fragment_chat_rooms_1_image_send.setOnClickListener(this);
        fragment_chat_layout_arrow.setOnClickListener(this);


        rootView = getView().findViewById(R.id.fragment_chat_rooms_1_parent_layout);
//        emojiconEditText = getView().findViewById(R.id.emoji_editText);


        edt_message = getView().findViewById(R.id.fragment_chat_rooms_1_edit_text);

        //layout_bottom_bookmark.setOnClickListener(this);
        layout_bottom_emoji.setOnClickListener(this);

        layout_image_bookmark.setOnClickListener(this);
        layout_image_emoji.setOnClickListener(this);
        layout_image_cross.setOnClickListener(this);
        layout_image_camera.setOnClickListener(this);

        emoji_imageview.setOnClickListener(this);

        edt_message.setOnClickListener(this);

        getKeyBoardHeight();
        readEmoticons();
        setMyViewPager();

        createMyDialogInvite();
        String text = getEmojiByUnicode(unicode);
//        hungry_text.setText("Now I’m hungry " + text + " .I’m in the city for a few days and hoping to have dinner there.");

//        readEmoticons();
//        enablePopUpView();
//        checkKeyboardHeight(parentLayout);
//        enableFooterView();




  /*      rv_1 = (RecyclerView) getView().findViewById(R.id.home3_favorites);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_1.setLayoutManager(linearLayoutManager);*/

        rv_heart = (RecyclerView) getView().findViewById(R.id.home3_recycler_heart);
        rv_custom_events = (RecyclerView) getView().findViewById(R.id.home3_recycler_custom_events);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        rv_heart.setLayoutManager(gridLayoutManager);
        rv_heart.setHasFixedSize(true);

        rv_venues = (RecyclerView) getView().findViewById(R.id.home3_recycler_venues);
        LinearLayoutManager layoutManagaer
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        CustomLayoutManager manager1 = new CustomLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv_venues.setLayoutManager(manager1);

        rv_events = (RecyclerView) getView().findViewById(R.id.home3_recycler_events);
        LinearLayoutManager gLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        CustomLayoutManager manager2 = new CustomLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv_events.setLayoutManager(manager2);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        chatRecycler.setLayoutManager(linearLayoutManager);

        chatAdapter = new ChatAdapter(chatModalArrayList);
        chatRecycler.setAdapter(chatAdapter);
        setMyAdapter();


    }

    public String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }


    private void setMyViewPager() {
        layout_bottom_emoji.setOffscreenPageLimit(3);
        ArrayList<String> paths = new ArrayList<String>();
        for (short i = 1; i <= NO_OF_EMOTICONS; i++) {
            paths.add(i + ".png");
        }

        EmoticonsPagerAdapter adapter = new EmoticonsPagerAdapter(getActivity(), paths, this);
        layout_bottom_emoji.setAdapter(adapter);
    }

    private void readEmoticons() {

        emoticons = new Bitmap[NO_OF_EMOTICONS];
        for (short i = 0; i < NO_OF_EMOTICONS; i++) {
            emoticons[i] = getImage((i + 1) + ".png");
        }

    }

    /**
     * For loading smileys from assets
     */
    private Bitmap getImage(String path) {
        AssetManager mngr = getActivity().getAssets();
        InputStream in = null;
        try {
            in = mngr.open("emoticons/" + path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bitmap temp = BitmapFactory.decodeStream(in, null, null);
        return temp;
    }

    private void getKeyBoardHeight() {

        Rect r = new Rect();
        parentLayout.getWindowVisibleDisplayFrame(r);

        int screenHeight = parentLayout.getRootView()
                .getHeight();


        keyboardHeight = screenHeight - (r.bottom - r.top);
        Log.d("Keyboard Size", "Size: " + keyboardHeight);

        float density = getActivity().getResources()
                .getDisplayMetrics()
                .density;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, Math.round((float) keyboardHeight * density));

        //layout_bottom_bookmark.setLayoutParams(params);
        layout_bottom_emoji.setLayoutParams(params);

    }

    private Dialog dialog_invite;

    private void createMyDialogInvite() {

        dialog_invite = new Dialog(getContext());
        dialog_invite.setContentView(R.layout.dialog_chat_room_fragment_2_invite);
        dialog_invite.setCancelable(false);
        dialog_invite.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_invite.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        TextView tv_yes = dialog_invite.findViewById(R.id.dialog_invite_yes);
        TextView tv_no = dialog_invite.findViewById(R.id.dialog_invite_no);

        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BaseClass(getContext()).showToast("Yes");
                dialog_invite.dismiss();
            }
        });

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BaseClass(getContext()).showToast("No");
                dialog_invite.dismiss();
            }
        });

    }

    public static final int REQUEST_CAMERA_FOR_CHAT = 111;
    private boolean result;

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.fragment_chat_rooms_1_edit_text) {
            layout_bottom_emoji.setVisibility(View.GONE);
            bottom_bookmark_layout.setVisibility(View.GONE);
            //layout_bottom_bookmark.setVisibility(View.GONE);
            layout_image_cross.setVisibility(View.GONE);
            isBottomViewUp = false;
        } /*else if (id == R.id.fragment_chat_rooms_1_image_emoji) {
         */ else if (id == R.id.fragment_profile_home_txt_venue) {
            //   setBottomColors();
            //   tab_one.setTextColor(getResources().getColor(R.color.colorAccent));
            rv_heart.setVisibility(View.GONE);
            customLayout.setVisibility(View.GONE);
            rv_venues.setVisibility(View.VISIBLE);
            rv_events.setVisibility(View.GONE);
        } else if (id == R.id.fragment_chat_layout_arrow) {
            getActivity().onBackPressed();
        } else if (id == R.id.createEvnt) {
            ((HomeActivity) getActivity()).replaceCustomCreateEventFragment();
        } else if (id == R.id.createGroup) {
            ((HomeActivity) getActivity()).replaceCreateGroupIndividualFragment();
        } else if (id == R.id.fragment_chat_rooms_1_image_send) {
            if (edt_message.getText().toString().trim().isEmpty()) {
                Toast.makeText(getActivity(), "Please enter some text", Toast.LENGTH_SHORT).show();
            } else {
                ChatModal chatModal = new ChatModal();
                chatModal.setMessage(edt_message.getText().toString().trim());
                chatModal.setImage(false);
                chatModalArrayList.add(chatModal);

                edt_message.setText("");
                edt_message.setHint("Enter message");
                chatAdapter.notifyDataSetChanged();
            }
        } else if (id == R.id.home3_bottom_two) {
            customLayout.setVisibility(View.GONE);
            rv_heart.setVisibility(View.GONE);
            rv_events.setVisibility(View.VISIBLE);
            rv_venues.setVisibility(View.GONE);
        } else if (id == R.id.home3_bottom_three) {
            //  setBottomColors();
            // iv_heart.setImageResource(R.drawable.ic_favorites_pink);
            customLayout.setVisibility(View.GONE);
            rv_heart.setVisibility(View.VISIBLE);
            rv_venues.setVisibility(View.GONE);
            rv_events.setVisibility(View.GONE);
        } else if (id == R.id.home3_bottom_four) {
            //  setBottomColors();
            //  iv_recycle.setImageResource(R.drawable.ic_back_1);
//            new BaseClass(getActivity()).callFragment(new CustomEvent(), new CustomEvent().getClass().getName(), getParentFragment().getFragmentManager());
            customLayout.setVisibility(View.VISIBLE);
            bottom_bookmark_layout.setVisibility(View.VISIBLE);
            rv_venues.setVisibility(View.GONE);
            rv_events.setVisibility(View.GONE);
            rv_heart.setVisibility(View.GONE);
        } else if (id == R.id.emoji_imageview) {

            bottom_bookmark_layout.setVisibility(View.GONE);
            edt_message.setVisibility(View.GONE);
//            emojiconEditText.setVisibility(View.VISIBLE);
//            emojIcon = new EmojIconActions(getActivity(), rootView, emojiconEditText, emoji_imageview);
//            emojIcon.ShowEmojIcon();
//            emojIcon.setIconsIds(R.drawable.ic_action_keyboard, R.drawable.smiley);
//
//            emojIcon.setUseSystemEmoji(true);
//            emojIcon.setKeyboardListener(new EmojIconActions.KeyboardListener() {
//                @Override
//                public void onKeyboardOpen() {
//                    Log.e("", "Keyboard opened!");
//                }
//
//                @Override
//                public void onKeyboardClose() {
//                    Log.e("", "Keyboard closed");
//                }
//            });

            //BaseClass.hideSoftKeyboard(getActivity());
            edt_message.setInputType(InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE);

         /*   layout_bottom_emoji.setVisibility(View.VISIBLE);
            layout_bottom_bookmark.setVisibility(View.GONE);
            layout_image_cross.setVisibility(View.VISIBLE);*/
            isBottomViewUp = true;
        } else if (id == R.id.fragment_chat_rooms_1_image_bookmark) {
            BaseClass.hideSoftKeyboard(getActivity());
            bottom_bookmark_layout.setVisibility(View.VISIBLE);
//                layout_bottom_bookmark.setVisibility(View.VISIBLE);
            layout_bottom_emoji.setVisibility(View.GONE);
            layout_image_cross.setVisibility(View.VISIBLE);
            isBottomViewUp = true;
        } else if (id == R.id.fragment_chat_rooms_1_image_cross) {
            bottom_bookmark_layout.setVisibility(View.GONE);
            KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
            edt_message.dispatchKeyEvent(event);

        } /*else if (id == R.id.fragment_chat_rooms_1_layout_bookmarks) {
            dialog_invite.show();
        }*/ else if (id == R.id.fragment_chat_rooms_1_image_camera) {
            bottom_bookmark_layout.setVisibility(View.GONE);
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getContext(), WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getContext(), READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE},
                        1);
            } else {
                showImageChooserDialog();
            }
         /*   if (ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getContext(),
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getContext(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CAMERA_FOR_CHAT);
            } else {
                result = true;
            }


            if (ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CAMERA_FOR_CHAT);
            } else {
                Log.e("DB", "PERMISSION GRANTED");
                result = true;
                cameraIntent();
            }*/
        } else {
        }
    }

    private void showImageChooserDialog() {
        LayoutInflater factory = LayoutInflater.from(getActivity());
        @SuppressLint("InflateParams") final View deleteDialogView = factory.inflate(R.layout.choose_pic, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(getActivity()).create();
        deleteDialog.setView(deleteDialogView);
        deleteDialog.setCancelable(true);
        deleteDialog.show();
        LinearLayout linear_camera = deleteDialogView.findViewById(R.id.linear_camera);
        LinearLayout linear_gallery = deleteDialogView.findViewById(R.id.linear_gallery);

        linear_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraIntent();
                deleteDialog.dismiss();
            }
        });
        linear_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryIntent();
                deleteDialog.dismiss();
            }
        });
        deleteDialog.show();
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
        File imgFile = new File(path);
        String filePath = imgFile.getPath();
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
//        fileName = imgFile.getName ();
//        signUpActivityBinding.licenceName.setText ( fileName );
        if (uri instanceof Bitmap) {
//            iv_place_holders.setVisibility(View.VISIBLE);
//            play_btns.setVisibility(View.GONE);
            ChatModal chatModal = new ChatModal();
            chatModal.setImage(bitmap);
            chatModal.setImage(true);
            chatModalArrayList.add(chatModal);
            chatAdapter.notifyDataSetChanged();
//            completeProfileActivityBinding.userImage.setImageBitmap((Bitmap) uri);
        } else {
//            iv_place_holders.setVisibility(View.VISIBLE);
//            play_btns.setVisibility(View.GONE);
            ChatModal chatModal = new ChatModal();
            chatModal.setImage(bitmap);
            chatModal.setImage(true);
            chatModalArrayList.add(chatModal);
            chatAdapter.notifyDataSetChanged();
           /* Glide.with(this)
                    .load(uri)
                    .into(completeProfileActivityBinding.userImage);*/

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CAMERA_FOR_CHAT:
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {

            if (requestCode == REQUEST_CAMERA_FOR_CHAT) {
                Bitmap image = (Bitmap) data.getExtras().get("data");

//                new BaseClass(getContext()).showToast("Picture has been taken successfully.");
//                onCaptureImageResult(data);
            } else {

            }
        }
    }

    @Override
    public void keyClickedIndex(final String index) {

        Html.ImageGetter imageGetter = new Html.ImageGetter() {
            public Drawable getDrawable(String source) {
                StringTokenizer st = new StringTokenizer(index, ".");
                Drawable d = new BitmapDrawable(getResources(), emoticons[Integer.parseInt(st.nextToken()) - 1]);
                d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                return d;
            }
        };

        Spanned cs = Html.fromHtml("<img src ='" + index + "'/>", imageGetter, null);

        int cursorPosition = edt_message.getSelectionStart();
        edt_message.getText().insert(cursorPosition, cs);
    }


    private void setMyAdapter() {

        /*      AdapterPrefrences adapter = new AdapterPrefrences(getContext(), res_Images, res_Names*//*, res_City*//*);
        rv_1.setAdapter(adapter);*/

        AdapterHome3b adapter2 = new AdapterHome3b(getContext(), rest_Images_2, getFragmentManager(), type);
        rv_heart.setAdapter(adapter2);


        bottom_list_image.clear();
        bottom_list_image_2.clear();
        bottom_list_circle.clear();
        bottom_list_ad.clear();
        bottom_list_name.clear();
        bottom_list_review.clear();
        bottom_list_distance.clear();
        bottom_list_butterfly.clear();
        bottom_list_check.clear();
        bottom_list_bookmarks.clear();
        bottom_list_interested.clear();

        if (str_check.equals("1")) {

            bottom_list_image.add(R.drawable.img6);
            bottom_list_image.add(R.drawable.rest_2);

            bottom_list_image_2.add(R.drawable.res_1);
            bottom_list_image_2.add(R.drawable.res_2);

            bottom_list_circle.add("yes");
            bottom_list_circle.add("yes");

            bottom_list_ad.add("yes");
            bottom_list_ad.add("yes");

            bottom_list_name.add("Chez Restaurant");
            bottom_list_name.add("Le Select Bistro");

            bottom_list_review.add("30");
            bottom_list_review.add("29");

            bottom_list_distance.add("2.8");
            bottom_list_distance.add("4.5");

            bottom_list_butterfly.add("yes");
            bottom_list_butterfly.add("yes");

            bottom_list_check.add("yes");
            bottom_list_check.add("yes");

            bottom_list_bookmarks.add("0");
            bottom_list_bookmarks.add("0");

            bottom_list_interested.add("0");
            bottom_list_interested.add("0");

        } else {
            bottom_list_image.add(R.drawable.rest_3);
            bottom_list_image.add(R.drawable.rest_4);
            bottom_list_image.add(R.drawable.rest_5);

            bottom_list_image_2.add(R.drawable.res_3);
            bottom_list_image_2.add(R.drawable.res_4);
            bottom_list_image_2.add(R.drawable.res_5);

            bottom_list_circle.add("yes");
            bottom_list_circle.add("no");
            bottom_list_circle.add("yes");

            bottom_list_ad.add("no");
            bottom_list_ad.add("no");
            bottom_list_ad.add("no");

            bottom_list_name.add("Alo Restaurant");
            bottom_list_name.add("Scaramouche");
            bottom_list_name.add("Alouette");

            bottom_list_review.add("318");
            bottom_list_review.add("478");
            bottom_list_review.add("119");

            bottom_list_distance.add("11.3");
            bottom_list_distance.add("5.8");
            bottom_list_distance.add("9");

            bottom_list_butterfly.add("yes");
            bottom_list_butterfly.add("no");
            bottom_list_butterfly.add("yes");

            bottom_list_check.add("no");
            bottom_list_check.add("no");
            bottom_list_check.add("no");

            bottom_list_bookmarks.add("0");
            bottom_list_bookmarks.add("0");
            bottom_list_bookmarks.add("0");

            bottom_list_interested.add("0");
            bottom_list_interested.add("0");
            bottom_list_interested.add("0");
        }

     /*   adapte1 = new AdapterCategoryListBottom1(getContext(), getFragmentManager(),
                bottom_list_image, bottom_list_image_2, bottom_list_circle, bottom_list_ad, bottom_list_name,
                bottom_list_review, bottom_list_distance, bottom_list_butterfly, bottom_list_check,
                bottom_list_bookmarks, bottom_list_interested, "venues", this);*/

//        rv_venues.setAdapter(adapte1);


        bottom_list_image_events.clear();
        bottom_list_image_2_events.clear();
        bottom_list_circle_events.clear();
        bottom_list_ad_events.clear();
        bottom_list_name_events.clear();
        bottom_list_review_events.clear();
        bottom_list_distance_events.clear();
        bottom_list_butterfly_events.clear();
        bottom_list_check_events.clear();
        bottom_list_bookmarks_events.clear();
        bottom_list_interested_events.clear();

        if (str_check.equals("1")) {
            bottom_list_image_events.add(R.drawable.events_list_1);
            bottom_list_image_events.add(R.drawable.event_list_3);

            bottom_list_image_2_events.add(R.drawable.events_list_1);
            bottom_list_image_2_events.add(R.drawable.event_list_3);

            bottom_list_circle_events.add("yes");
            bottom_list_circle_events.add("no");

            bottom_list_ad_events.add("yes");
            bottom_list_ad_events.add("no");

            bottom_list_name_events.add("Phantom of the Opera");
            bottom_list_name_events.add("Come From Away");

            bottom_list_review_events.add("30");
            bottom_list_review_events.add("75");

            bottom_list_distance_events.add("2.8");
            bottom_list_distance_events.add("11.3");

            bottom_list_butterfly_events.add("yes");
            bottom_list_butterfly_events.add("no");

            bottom_list_check_events.add("yes");
            bottom_list_check_events.add("yes");

            bottom_list_bookmarks_events.add("0");
            bottom_list_bookmarks_events.add("0");

            bottom_list_interested_events.add("0");
            bottom_list_interested_events.add("0");
        } else {
            bottom_list_image_events.add(R.drawable.event_list_2);
            bottom_list_image_events.add(R.drawable.event_list_4);

            bottom_list_image_2_events.add(R.drawable.event_list_2);
            bottom_list_image_2_events.add(R.drawable.event_list_4);

            bottom_list_circle_events.add("no");
            bottom_list_circle_events.add("no");

            bottom_list_ad_events.add("yes");
            bottom_list_ad_events.add("no");

            bottom_list_name_events.add("Wicked");
            bottom_list_name_events.add("Wicked");

            bottom_list_review_events.add("15");
            bottom_list_review_events.add("30");

            bottom_list_distance_events.add("4.5");
            bottom_list_distance_events.add("5.8");

            bottom_list_butterfly_events.add("yes");
            bottom_list_butterfly_events.add("no");

            bottom_list_check_events.add("no");
            bottom_list_check_events.add("no");

            bottom_list_bookmarks_events.add("0");
            bottom_list_bookmarks_events.add("0");

            bottom_list_interested_events.add("0");
            bottom_list_interested_events.add("0");
        }

     /*   adapterCategoryListBottom1Events = new AdapterCategoryListBottom1Events(getContext(), getFragmentManager(),
                bottom_list_image_events, bottom_list_image_2_events, bottom_list_circle_events, bottom_list_ad_events,
                bottom_list_name_events, bottom_list_review_events, bottom_list_distance_events,
                bottom_list_butterfly_events, bottom_list_check_events, bottom_list_bookmarks_events,
                bottom_list_interested_events, "events", this);

        rv_events.setAdapter(adapterCategoryListBottom1Events);*/


//        AdapterCategoryListBottom adapte1 = new AdapterCategoryListBottom(getContext(), getFragmentManager());




        /*AdapterCategoryListBottomEvents adapte12 =
                new AdapterCategoryListBottomEvents(getContext(), getFragmentManager());

        rv_venues.setAdapter(adapte1);
        rv_events.setAdapter(adapte12);*/
/*        if(type.equals("venues")) {
            rv_venues.setAdapter(adapte1);
            rv_events.setAdapter(adapte1);
        } else {
            rv_venues.setAdapter(adapte12);
            rv_events.setAdapter(adapte12);
        }*/
    }


    @Override
    public void click_interface_restaurant_list_1(int position, String type, int status) {

        if (type.equals("bookmarks")) {
            if (bottom_list_bookmarks.get(position).equals("0")) {
                bottom_list_bookmarks.set(position, "1");
            } else {
                bottom_list_bookmarks.set(position, "0");
            }
            adapte1.notifyDataSetChanged();
        } else {
            if (bottom_list_interested.get(position).equals("0")) {
                bottom_list_interested.set(position, "1");
            } else {
                bottom_list_interested.set(position, "0");
            }
            adapte1.notifyDataSetChanged();
        }

    }

    @Override
    public void viewItemDetails(int pos) {

    }

    @Override
    public void click_interface_restaurant_list_events(int position, String type) {

        if (type.equals("bookmarks")) {

            if (bottom_list_bookmarks_events.get(position).equals("0")) {
                bottom_list_bookmarks_events.set(position, "1");
            } else {
                bottom_list_bookmarks_events.set(position, "0");
            }
            adapterCategoryListBottom1Events.notifyDataSetChanged();
        } else {
            if (bottom_list_interested_events.get(position).equals("0")) {
                bottom_list_interested_events.set(position, "1");
            } else {
                bottom_list_interested_events.set(position, "0");
            }
            adapterCategoryListBottom1Events.notifyDataSetChanged();
        }
    }


    public void setProfilePic(Bitmap data) {


        ChatModal chatModal = new ChatModal();
        chatModal.setImage(data);
        chatModal.setImage(true);
        chatModalArrayList.add(chatModal);
        chatAdapter.notifyDataSetChanged();
    }

    class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
        ArrayList<ChatModal> chatModals;

        public ChatAdapter(ArrayList<ChatModal> chatModalArrayList) {
            this.chatModals = chatModalArrayList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_items, viewGroup, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
            LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(textParam);
            params.gravity = Gravity.LEFT;
//            if (chatList.get(i).getFrommsgby_status().equals(user_id)) {
//                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams( textParam);
//                params.gravity = Gravity.RIGHT;
//                myViewHolder.layout.setLayoutParams(params);
//                myViewHolder.layout.setBackgroundResource(R.drawable.button_blue_chat2);
//                myViewHolder.commingTimeTv.setText(chatList.get(i).getUpdated_at());
//                myViewHolder.msg.setText(chatList.get(i).getMessage());
//
//            } else {
//
//                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(textParam );
//                params.gravity =Gravity.LEFT;
//                myViewHolder.layout.setLayoutParams(params);
//                myViewHolder.commingTimeTv.setText(chatList.get(i).getUpdated_at());
//                myViewHolder.layout.setBackgroundResource(R.drawable.button_gray_chat1);
//                myViewHolder.msg.setText(chatList.get(i).getMessage());
//
//                updatedAt=chatList.get(i).getUpdated_at();
//                chatAdapterInterface.click(getItemViewType(i),updatedAt);
//            }
            if (chatModals.get(i).isImage()) {
                holder.imagelayout.setVisibility(View.VISIBLE);
                holder.textLayout.setVisibility(View.GONE);
            } else {

                holder.imagelayout.setVisibility(View.GONE);
                holder.textLayout.setVisibility(View.VISIBLE);
            }
            holder.msg.setText(chatModals.get(i).getMessage());
//            Uri imageUri = data.getData();
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            holder.imageView1.setImageBitmap((chatModals.get(i).getImage()));

        }


        @Override
        public int getItemCount() {
            return chatModals.size();
//            return 10;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView sendMsgTv, commingMsgTv, sendTimeTv, commingTimeTv, msg;
            LinearLayout layout, parent_layout, imagelayout, textLayout;
            private ImageView imageView1;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                textLayout = itemView.findViewById(R.id.textLayout);
                imagelayout = itemView.findViewById(R.id.imagelayout);
                imageView1 = itemView.findViewById(R.id.imageView1);
                msg = itemView.findViewById(R.id.message_text);
                layout = itemView.findViewById(R.id.bubble_layout);
                commingTimeTv = itemView.findViewById(R.id.comming_msg_time);

            }
        }


    }
}