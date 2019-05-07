package com.askonlinesolutions.wengage.CustomTexts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by lenovo on 4/13/2018.
 */

public class CustomTextViewHairLine extends android.support.v7.widget.AppCompatTextView {

    public CustomTextViewHairLine(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextViewHairLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextViewHairLine(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Hairline.ttf");
        setTypeface(tf ,1);

    }

}

