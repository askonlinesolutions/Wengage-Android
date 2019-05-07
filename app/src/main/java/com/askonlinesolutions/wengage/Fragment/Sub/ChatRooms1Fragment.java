package com.askonlinesolutions.wengage.Fragment.Sub;


import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.InputType;
import android.text.Spanned;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Activity.Main.MainActivity;
import com.askonlinesolutions.wengage.Adapter.EmoticonsGridAdapter;
import com.askonlinesolutions.wengage.Adapter.EmoticonsPagerAdapter;
import com.askonlinesolutions.wengage.Fragment.Main.ChatFragment;
import com.askonlinesolutions.wengage.Helper.BaseClass;
import com.askonlinesolutions.wengage.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatRooms1Fragment extends Fragment implements View.OnClickListener, EmoticonsGridAdapter.KeyClickListener {

    public ChatRooms1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_rooms_1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN );
        init();
    }

    private LinearLayout layout_bottom_linear
            , layout_image_bookmark, layout_image_emoji;
    private RelativeLayout layout_bottom_relative;
    private EditText edt_message;


    private LinearLayout emoticonsCover;
    private PopupWindow popupWindow;
    private int keyboardHeight;
    private RelativeLayout parentLayout;
    private boolean isKeyBoardVisible;
    private Bitmap[] emoticons;
    private View popUpView;
    private static final int NO_OF_EMOTICONS = 54;


    private void init(){


        parentLayout = getView().findViewById(R.id.fragment_chat_rooms_1_parent_layout);
        layout_bottom_relative = getView().findViewById(R.id.fragment_chat_rooms_1_layout_relative);
        layout_bottom_linear = getView().findViewById(R.id.fragment_chat_rooms_1_layout_linear);
        layout_image_bookmark = getView().findViewById(R.id.fragment_chat_rooms_1_image_bookmark);
        layout_image_emoji = getView().findViewById(R.id.fragment_chat_rooms_1_image_emoji);

        edt_message = getView().findViewById(R.id.fragment_chat_rooms_1_edit_text);

        layout_bottom_linear.setOnClickListener(this);
//        layout_image_bookmark.setOnClickListener(this);
//        layout_image_emoji.setOnClickListener(this);
        edt_message.setOnClickListener(this);


        emoticonsCover = getView().findViewById(R.id.footer_for_emoticons);
        popUpView = getLayoutInflater().inflate(R.layout.emoticons_popup, null);

        // Defining default height of keyboard which is equal to 230 dip
        final float popUpheight = getResources().getDimension(
                R.dimen.keyboard_height);
        changeKeyboardHeight((int) popUpheight);

        layout_image_emoji.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

           //     layout_bottom_linear.setVisibility(View.GONE);

                edt_message.setInputType(InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE);
           /*     if (!popupWindow.isShowing()) {

                    popupWindow.setHeight((int) (keyboardHeight));

                    if (isKeyBoardVisible) {
                        emoticonsCover.setVisibility(LinearLayout.GONE);
                    } else {
                        emoticonsCover.setVisibility(LinearLayout.VISIBLE);
                    }
                    popupWindow.showAtLocation(parentLayout, Gravity.BOTTOM, 0, 0);

                } else {
                    popupWindow.dismiss();
                }*/

            }
        });

        layout_image_bookmark.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                emoticonsCover.setVisibility(View.GONE);
                if(popupWindow.isShowing()){
                    popupWindow.dismiss();
                }


                    if (isKeyBoardVisible) {
                        BaseClass.hideSoftKeyboard(getActivity());
                    } else {
                    }
//                    popupWindow.showAtLocation(parentLayout, Gravity.BOTTOM, 0, 0);
                layout_bottom_linear.setVisibility(LinearLayout.VISIBLE);


            }
        });


        readEmoticons();
      //  enablePopUpView();
        checkKeyboardHeight(parentLayout);
//        enableFooterView();

    }

    private void readEmoticons () {

        emoticons = new Bitmap[NO_OF_EMOTICONS];
        for (short i = 0; i < NO_OF_EMOTICONS; i++) {
            emoticons[i] = getImage((i+1) + ".png");
        }

    }

    /**
     * Checking keyboard height and keyboard visibility
     */
    int previousHeightDiffrence = 0;
    private void checkKeyboardHeight(final View parentLayout) {

        parentLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {

                        Rect r = new Rect();
                        parentLayout.getWindowVisibleDisplayFrame(r);

                        int screenHeight = parentLayout.getRootView()
                                .getHeight();
                        int heightDifference = screenHeight - (r.bottom);

                        if (previousHeightDiffrence - heightDifference > 50) {
                            popupWindow.dismiss();
                        }

                        previousHeightDiffrence = heightDifference;
                        if (heightDifference > 100) {

                            isKeyBoardVisible = true;
                            changeKeyboardHeight(heightDifference);

                        } else {

                            isKeyBoardVisible = false;

                        }

                    }
                });
    }

    /**
     * change height of emoticons keyboard according to height of actual
     * keyboard
     *
     * @param height
     *            minimum height by which we can make sure actual keyboard is
     *            open or not
     */
    private void changeKeyboardHeight(int height) {

        if (height > 100) {
            keyboardHeight = height;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, keyboardHeight);
            emoticonsCover.setLayoutParams(params);
            layout_bottom_linear.setLayoutParams(params);
        }
    }

    /**
     * Defining all components of emoticons keyboard
     */
    private void enablePopUpView() {

        ViewPager pager = (ViewPager) popUpView.findViewById(R.id.emoticons_pager);
        pager.setOffscreenPageLimit(3);

        ArrayList<String> paths = new ArrayList<String>();

        for (short i = 1; i <= NO_OF_EMOTICONS; i++) {
            paths.add(i + ".png");
        }

        EmoticonsPagerAdapter adapter = new EmoticonsPagerAdapter(getActivity(), paths, this);
        pager.setAdapter(adapter);

        // Creating a pop window for emoticons keyboard
        popupWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT,
                (int) keyboardHeight, false);

        TextView backSpace = (TextView) popUpView.findViewById(R.id.back);
        backSpace.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
                edt_message.dispatchKeyEvent(event);
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                emoticonsCover.setVisibility(LinearLayout.GONE);
            }
        });
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

    @Override
    public void keyClickedIndex(final String index) {

        Html.ImageGetter imageGetter = new Html.ImageGetter() {
            public Drawable getDrawable(String source) {
                StringTokenizer st = new StringTokenizer(index, ".");
                Drawable d = new BitmapDrawable(getResources(),emoticons[Integer.parseInt(st.nextToken()) - 1]);
                d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                return d;
            }
        };

        Spanned cs = Html.fromHtml("<img src ='"+ index +"'/>", imageGetter, null);

        int cursorPosition = edt_message.getSelectionStart();
        edt_message.getText().insert(cursorPosition, cs);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.fragment_chat_rooms_1_image_bookmark){

            emoticonsCover.setVisibility(LinearLayout.GONE);

                if (isKeyBoardVisible) {
                    layout_bottom_linear.setVisibility(LinearLayout.GONE);
                } else {
                    layout_bottom_linear.setVisibility(LinearLayout.VISIBLE);
                }


//            layout_bottom_relative.setVisibility(View.GONE);
//            layout_bottom_linear.setVisibility(View.VISIBLE);
        } else if(id == R.id.fragment_chat_rooms_1_layout_linear){
            layout_bottom_linear.setVisibility(View.GONE);
            layout_bottom_relative.setVisibility(View.VISIBLE);
        } else if(id == R.id.fragment_chat_rooms_1_edit_text){
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
            layout_bottom_linear.setVisibility(View.GONE);
        } else {}
    }

    @Override
    public void onResume() {
        super.onResume();

        ChatFragment.tv_top_name.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();

        ChatFragment.tv_top_name.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();

        ChatFragment.tv_top_name.setVisibility(View.GONE);
    }


}