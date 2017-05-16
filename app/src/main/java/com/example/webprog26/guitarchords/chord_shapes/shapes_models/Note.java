package com.example.webprog26.guitarchords.chord_shapes.shapes_models;

import android.graphics.Point;

import java.io.Serializable;

/**
 * Created by webpr on 15.05.2017.
 */

public class Note {

    private String mNoteTitle;
    private int mFingerIndex;
    private Point mNoteCoordinates;

    public Note(String noteTitle, int fingerIndex, Point noteCoordinates) {
        this.mNoteTitle = noteTitle;
        this.mFingerIndex = fingerIndex;
        this.mNoteCoordinates = noteCoordinates;
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
}