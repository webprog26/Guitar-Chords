package com.example.webprog26.guitarchords.chord_shapes.shapes_models;

import android.graphics.Point;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by webpr on 17.05.2017.
 */

public class PlayableShape {

    public static final int UNEXISTING_COORDINATE = -1;

    private final int mPosition;
    private final int mStartFretNumber;
    private final ArrayList<Note> mNotes;
    private final boolean hasBar;
    private final StringMutedHolder mStringMutedHolder;
    private final boolean hasMutedStrings;
    private final Point barStartPoint;
    private final Point barEndPoint;

    public PlayableShape(final ChordShape chordShape) {
        this.mPosition = chordShape.getPosition();
        this.mStartFretNumber = chordShape.getStartFretNumber();
        this.mNotes = chordShape.getNotes();
        this.hasBar = chordShape.isHasBar();
        this.mStringMutedHolder = chordShape.getStringMutedHolder();
        this.hasMutedStrings = chordShape.isHasMutedStrings();

        if(isHasBar()){
            BarChordShape barChordShape = (BarChordShape)chordShape;
            barStartPoint = barChordShape.getBarStartPoint();
            barEndPoint = barChordShape.getBarEndPoint();
        } else {
            barStartPoint = new Point(UNEXISTING_COORDINATE, UNEXISTING_COORDINATE);
            barEndPoint = new Point(UNEXISTING_COORDINATE, UNEXISTING_COORDINATE);
        }
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

    public StringMutedHolder getStringMutedHolder() {
        return mStringMutedHolder;
    }

    public boolean isHasMutedStrings() {
        return hasMutedStrings;
    }

    public Point getBarStartPoint() {
        return barStartPoint;
    }

    public Point getBarEndPoint() {
        return barEndPoint;
    }

    @Override
    public String toString() {
        return "Shape position " + getPosition() + "\n"
                + " has " + getNotes().size() + " notes" + "\n"
                + " starts from fret " + getStartFretNumber()  + "\n"
                + " has bar " + isHasBar()  + "\n"
                + " has muted strings " + isHasMutedStrings()  + "\n"
                + " first " + getStringMutedHolder().isFirstStringMuted()  + "\n"
                + " second " + getStringMutedHolder().isSecondStringMuted() + "\n"
                + " third " + getStringMutedHolder().isThirdStringMuted() + "\n"
                + " fourth " + getStringMutedHolder().isFourthStringMuted() + "\n"
                + " fifth " + getStringMutedHolder().isFifthStringMuted() + "\n"
                + " sixth " + getStringMutedHolder().isSixthStringMuted();
    }
}
