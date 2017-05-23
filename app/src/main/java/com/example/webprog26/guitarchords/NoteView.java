package com.example.webprog26.guitarchords;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by webpr on 22.05.2017.
 */

public class NoteView extends AppCompatImageView {

    private Bitmap fingerIndexBitmap;

    public NoteView(Context context) {
        super(context);
    }

    public NoteView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public Bitmap getFingerIndexBitmap() {
        return fingerIndexBitmap;
    }

    public void setFingerIndexBitmap(Bitmap fingerIndexBitmap) {
        this.fingerIndexBitmap = fingerIndexBitmap;
    }
}
