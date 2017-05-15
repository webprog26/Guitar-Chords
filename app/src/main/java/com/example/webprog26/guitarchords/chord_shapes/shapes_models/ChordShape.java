package com.example.webprog26.guitarchords.chord_shapes.shapes_models;

import java.util.ArrayList;

/**
 * Created by webpr on 15.05.2017.
 */

public abstract class ChordShape {

    private int mPosition;
    private int mStartFretNumber;
    private ArrayList<Note> mNotes = new ArrayList<>();
    private boolean hasBar;
    private String mImageTitle;
    private StringMutedHolder mStringMutedHolder = new StringMutedHolder();
    private boolean hasMutedStrings;

    public ChordShape(int mPosition,
                      int mStartFretNumber,
                      ArrayList<Note> mNotes,
                      boolean hasBar,
                      String mImageTitle,
                      boolean hasMutedStrings,
                      StringMutedHolder mStringMutedHolder) {
        this.mPosition = mPosition;
        this.mStartFretNumber = mStartFretNumber;
        this.mNotes = mNotes;
        this.hasBar = hasBar;
        this.mImageTitle = mImageTitle;
        this.hasMutedStrings = hasMutedStrings;
        this.mStringMutedHolder = mStringMutedHolder;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        this.mPosition = position;
    }

    public int getStartFretNumber() {
        return mStartFretNumber;
    }

    public void setStartFretNumber(int startFretNumber) {
        this.mStartFretNumber = startFretNumber;
    }

    public ArrayList<Note> getNotes() {
        return mNotes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.mNotes = notes;
    }

    public boolean isHasBar() {
        return hasBar;
    }

    public void setHasBar(boolean hasBar) {
        this.hasBar = hasBar;
    }

    public String getImageTitle() {
        return mImageTitle;
    }

    public void setImageTitle(String mImageTitle) {
        this.mImageTitle = mImageTitle;
    }

    public StringMutedHolder getStringMutedHolder() {
        return mStringMutedHolder;
    }

    public void setStringMutedHolder(StringMutedHolder stringMutedHolder) {
        this.mStringMutedHolder = stringMutedHolder;
    }

    public boolean isHasMutedStrings() {
        return hasMutedStrings;
    }

    public void setHasMutedStrings(boolean hasMutedStrings) {
        this.hasMutedStrings = hasMutedStrings;
    }

    @Override
    public String toString() {
        return "ChordShape position: " + getPosition() + "\n"
                + ", start fret number: " + getStartFretNumber() + "\n"
                + ", has bar: " + isHasBar() + "\n"
                + ", image title: " + getImageTitle();
    }
}
