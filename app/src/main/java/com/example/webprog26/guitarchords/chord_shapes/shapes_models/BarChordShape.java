package com.example.webprog26.guitarchords.chord_shapes.shapes_models;

import android.graphics.Point;

import com.example.webprog26.guitarchords.chord_shapes.muted_strings_holder.StringMutedHolder;
import com.example.webprog26.guitarchords.chord_shapes.note.Note;

import java.util.ArrayList;

/**
 * Bar chord shape model
 */

public class BarChordShape extends ChordShape {

    private Point mBarStartPoint;
    private Point mBarEndPoint;

    public BarChordShape(int mPosition,
                         int mStartFretNumber,
                         ArrayList<Note> mNotes,
                         boolean hasBar,
                         String mImageTitle,
                         boolean hasMutedStrings,
                         StringMutedHolder mStringMutedHolder,
                         Point mBarStartPoint,
                         Point mBarEndPoint) {
        super(mPosition,
              mStartFretNumber,
              mNotes,
              hasBar,
              mImageTitle,
              hasMutedStrings,
              mStringMutedHolder);
        this.mBarStartPoint = mBarStartPoint;
        this.mBarEndPoint = mBarEndPoint;
    }

    public Point getBarStartPoint() {
        return mBarStartPoint;
    }

    public Point getBarEndPoint() {
        return mBarEndPoint;
    }
}
