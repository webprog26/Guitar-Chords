package com.example.webprog26.guitarchords.chord_shapes.shapes_models;

import java.io.Serializable;

/**
 * Created by webpr on 17.05.2017.
 */

public class PlayableShape implements Serializable{

    private final String mChordTitle;
    private final String mChordSecondTitle;
    private final String mShapesTableTitle;
    private final int mShapePosition;

    public PlayableShape(String chordTitle, String secondChordTitle, String shapesTableTitle, int shapePosition) {
        this.mChordTitle = chordTitle;
        this.mChordSecondTitle = secondChordTitle;
        this.mShapesTableTitle = shapesTableTitle;
        this.mShapePosition = shapePosition;
    }

    public String getChordTitle() {
        return mChordTitle;
    }

    public String getChordSecondTitle() {
        return mChordSecondTitle;
    }

    public String getShapesTableTitle() {
        return mShapesTableTitle;
    }

    public int getShapePosition() {
        return mShapePosition;
    }
}
