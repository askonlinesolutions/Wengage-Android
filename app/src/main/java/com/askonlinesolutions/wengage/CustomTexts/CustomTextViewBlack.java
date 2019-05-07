package com.askonlinesolutions.wengage.CustomTexts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by lenovo on 4/13/2018.
 */

public class CustomTextViewBlack extends android.support.v7.widget.AppCompatTextView {

    public CustomTextViewBlack(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextViewBlack(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextViewBlack(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Black.ttf");
        setTypeface(tf ,1);

    }

}

