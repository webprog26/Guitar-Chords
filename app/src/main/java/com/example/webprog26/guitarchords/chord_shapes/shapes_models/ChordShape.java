package com.example.webprog26.guitarchords.chord_shapes.shapes_models;

import android.graphics.Bitmap;

import com.example.webprog26.guitarchords.chord_shapes.muted_strings_holder.StringMutedHolder;
import com.example.webprog26.guitarchords.chord_shapes.note.Note;

import java.util.ArrayList;

/**
 * Basic chord shape model
 */

public abstract class ChordShape {

    private int mPosition;
    private int mStartFretNumber;
    private ArrayList<Note> mNotes = new ArrayList<>();
    private boolean hasBar;
    private String mImagePath;
    private StringMutedHolder mStringMutedHolder = new StringMutedHolder();
    private boolean hasMutedStrings;
    private Bitmap mShapeImage;

    public ChordShape(int mPosition,
                      int mStartFretNumber,
                      ArrayList<Note> mNotes,
                      boolean hasBar,
                      String mImagePath,
                      boolean hasMutedStrings,
                      StringMutedHolder mStringMutedHolder) {
        this.mPosition = mPosition;
        this.mStartFretNumber = mStartFretNumber;
        this.mNotes = mNotes;
        this.hasBar = hasBar;
        this.mImagePath = mImagePath;
        this.hasMutedStrings = hasMutedStrings;
        this.mStringMutedHolder = mStringMutedHolder;
    }

    public int getPosition() {
        return mPosition;
    }

    public int getStartFretNumber() {
        return mStartFretNumber;
    }

    public ArrayList<Note> getNotes() {
        return mNotes;
    }

    public boolean isHasBar() {
        return hasBar;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public StringMutedHolder getStringMutedHolder() {
        return mStringMutedHolder;
    }

    public boolean isHasMutedStrings() {
        return hasMutedStrings;
    }

    @Override
    public String toString() {
        return "ChordShape position: " + getPosition() + "\n"
                + ", start fret number: " + getStartFretNumber() + "\n"
                + ", has bar: " + isHasBar() + "\n"
                + ", image title: " + getImagePath();
    }

    public Bitmap getShapeImage() {
        return mShapeImage;
    }

    public void setShapeImage(Bitmap shapeImage) {
        this.mShapeImage = shapeImage;
    }
}
