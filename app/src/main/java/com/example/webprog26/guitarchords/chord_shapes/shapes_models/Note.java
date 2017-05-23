package com.example.webprog26.guitarchords.chord_shapes.shapes_models;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;


/**
 * Note model
 */

public class Note {

    private String mNoteTitle;
    private int mFingerIndex;
    private Point mNoteCoordinates;
    private int mNotePlace;
    private Drawable mNoteTitleDrawable;
    private Drawable mNoteFingerIndexDrawable;

    public Note(String noteTitle, int fingerIndex, Point noteCoordinates, int notePlace) {
        this.mNoteTitle = noteTitle;
        this.mFingerIndex = fingerIndex;
        this.mNoteCoordinates = noteCoordinates;
        this.mNotePlace = notePlace;
    }

    public String getNoteTitle() {
        return mNoteTitle;
    }

    public void setNoteTitle(String mNoteTitle) {
        this.mNoteTitle = mNoteTitle;
    }

    public int getFingerIndex() {
        return mFingerIndex;
    }

    public void setFingerIndex(int fingerIndex) {
        this.mFingerIndex = fingerIndex;
    }

    public Point getNoteCoordinates() {
        return mNoteCoordinates;
    }

    public void setNoteCoordinates(Point noteCoordinates) {
        this.mNoteCoordinates = noteCoordinates;
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

    public void setNotePlace(int notePlace) {
        this.mNotePlace = notePlace;
    }

    @Override
    public String toString() {
        return "Note " + "\"" + getNoteTitle() + "\""
                + " fingerIndex " + getFingerIndex()
                + " notePlace " + getNotePlace()
                + " noteCoordinates " + getNoteCoordinates().toString();
    }
}
