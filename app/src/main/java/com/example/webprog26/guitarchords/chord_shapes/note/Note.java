package com.example.webprog26.guitarchords.chord_shapes.note;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;


/**
 * Note model
 */

public class Note {

    private final String mNoteTitle;
    private final int mFingerIndex;
    private final Point mNoteCoordinates;
    private final int mNotePlace;
    private Drawable mNoteTitleDrawable;
    private Drawable mNoteFingerIndexDrawable;
//    private final String mNoteSoundPath;
    private int mNoteSound = -1;

    public Note(String noteTitle, int fingerIndex, Point noteCoordinates, int notePlace) {
        this.mNoteTitle = noteTitle;
        this.mFingerIndex = fingerIndex;
        this.mNoteCoordinates = noteCoordinates;
        this.mNotePlace = notePlace;
    }

    public String getNoteTitle() {
        return mNoteTitle;
    }

    public int getFingerIndex() {
        return mFingerIndex;
    }

    public Point getNoteCoordinates() {
        return mNoteCoordinates;
    }

    public Drawable getNoteTitleDrawable() {
        return mNoteTitleDrawable;
    }

    public void setNoteTitleDrawable(Drawable mNoteTitleDrawable) {
        this.mNoteTitleDrawable = mNoteTitleDrawable;
    }

    public Drawable getNoteFingerIndexDrawable() {
        return mNoteFingerIndexDrawable;
    }

    public void setNoteFingerIndexDrawable(Drawable mNoteFingerIndexDrawable) {
        this.mNoteFingerIndexDrawable = mNoteFingerIndexDrawable;
    }

    public int getNotePlace() {
        return mNotePlace;
    }

    @Override
    public String toString() {
        return "Note " + "\"" + getNoteTitle() + "\""
                + " fingerIndex " + getFingerIndex()
                + " notePlace " + getNotePlace()
                + " noteCoordinates " + getNoteCoordinates().toString();
    }
}
