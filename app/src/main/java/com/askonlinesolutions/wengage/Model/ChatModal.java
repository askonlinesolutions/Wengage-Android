package com.askonlinesolutions.wengage.Model;

import android.graphics.Bitmap;

public class ChatModal {
    private String message;
    private Bitmap image;
    private boolean isImage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setImage(boolean image) {
        isImage = image;
    }


}
