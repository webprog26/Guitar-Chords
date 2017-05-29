package com.example.webprog26.guitarchords.chord_shapes.note;

import android.graphics.drawable.Drawable;

/**
 * Created by webpr on 29.05.2017.
 */

public abstract class SuperNote {

    private Drawable mNoteMainDrawable;

    public abstract boolean isBarredNote();

    public Drawable getNoteMainDrawable() {
        return mNoteMainDrawable;
    }

    public void setNoteMainDrawable(Drawable mNoteTitleDrawable) {
        this.mNoteMainDrawable = mNoteTitleDrawable;
    }
}
