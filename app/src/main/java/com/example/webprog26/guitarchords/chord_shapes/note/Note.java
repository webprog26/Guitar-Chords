package com.example.webprog26.guitarchords.chord_shapes.note;

import android.graphics.Point;
import android.graphics.drawable.Drawable;


/**
 * Note model
 */

public class Note extends SuperNote{

    private final String mNoteTitle;
    private final int mFingerIndex;
    private final Point mNoteCoordinates;
    private final int mNotePlace;

    private Drawable mNoteFingerIndexDrawable;
    private final String mNoteSoundPath;
    private int mNoteSound = -1;
    private boolean isFingerIndexVisible = false;

    public Note(String noteTitle, int fingerIndex, Point noteCoordinates, int notePlace, String noteSoundPath) {
        this.mNoteTitle = noteTitle;
        this.mFingerIndex = fingerIndex;
        this.mNoteCoordinates = noteCoordinates;
        this.mNotePlace = notePlace;
        this.mNoteSoundPath = noteSoundPath;
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

    public Drawable getNoteFingerIndexDrawable() {
        return mNoteFingerIndexDrawable;
    }

    public void setNoteFingerIndexDrawable(Drawable mNoteFingerIndexDrawable) {
        this.mNoteFingerIndexDrawable = mNoteFingerIndexDrawable;
    }

    public int getNotePlace() {
        return mNotePlace;
    }

    public String getNoteSoundPath() {
        return mNoteSoundPath;
    }

    public int getNoteSound() {
        return mNoteSound;
    }

    public void setNoteSound(int noteSound) {
        this.mNoteSound = noteSound;
    }

    public boolean isFingerIndexVisible() {
        return isFingerIndexVisible;
    }

    public void setFingerIndexVisible(boolean fingerIndexVisible) {
        isFingerIndexVisible = fingerIndexVisible;
    }


    @Override
    public String toString() {
        return "Note " + "\"" + getNoteTitle() + "\""
                + " fingerIndex " + getFingerIndex()
                + " notePlace " + getNotePlace()
                + " noteCoordinates " + getNoteCoordinates().toString();
    }
}
