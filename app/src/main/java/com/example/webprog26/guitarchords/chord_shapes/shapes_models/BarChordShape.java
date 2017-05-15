package com.example.webprog26.guitarchords.chord_shapes.shapes_models;

import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by webpr on 15.05.2017.
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

    public void setBarStartPoint(Point barStartPoint) {
        this.mBarStartPoint = barStartPoint;
    }

    public Point getBarEndPoint() {
        return mBarEndPoint;
    }

    public void setBarEndPoint(Point barEndPoint) {
        this.mBarEndPoint = barEndPoint;
    }
}
