package com.example.webprog26.guitarchords.chord_shapes.note;

import android.graphics.drawable.Drawable;

/**
 * Superclass for Notes
 */

public abstract class SuperNote {

    private Drawable mNoteMainDrawable;

    public Drawable getNoteMainDrawable() {
        return mNoteMainDrawable;
    }

    public void setNoteMainDrawable(Drawable mNoteTitleDrawable) {
        this.mNoteMainDrawable = mNoteTitleDrawable;
    }
}
