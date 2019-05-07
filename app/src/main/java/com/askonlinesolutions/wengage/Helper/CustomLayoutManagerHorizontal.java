package com.askonlinesolutions.wengage.Helper;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class CustomLayoutManagerHorizontal extends LinearLayoutManager{

    private boolean isScrollEnabled = true;

    public CustomLayoutManagerHorizontal(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollHorizontally() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollHorizontally();
    }
}
