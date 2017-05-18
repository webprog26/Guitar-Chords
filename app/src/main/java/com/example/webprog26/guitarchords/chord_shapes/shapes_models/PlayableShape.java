package com.example.webprog26.guitarchords.chord_shapes.shapes_models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by webpr on 17.05.2017.
 */

public class PlayableShape implements Serializable{

    private final int mPosition;
    private final int mStartFretNumber;
    private final ArrayList<Note> mNotes;
    private final boolean hasBar;
    private final StringMutedHolder mStringMutedHolder;
    private final boolean hasMutedStrings;

    public PlayableShape(final ChordShape chordShape) {
        this.mPosition = chordShape.getPosition();
        this.mStartFretNumber = chordShape.getStartFretNumber();
        this.mNotes = chordShape.getNotes();
        this.hasBar = chordShape.isHasBar();
        this.mStringMutedHolder = chordShape.getStringMutedHolder();
        this.hasMutedStrings = chordShape.isHasMutedStrings();
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

    @Override
    public String toString() {
        return "Shape position " + getPosition() + "\n"
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
