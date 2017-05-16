package com.example.webprog26.guitarchords.chord_shapes.shapes_models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by webpr on 15.05.2017.
 */

public class StandartChordShape extends ChordShape {

    public StandartChordShape(int mPosition,
                              int mStartFretNumber,
                              ArrayList<Note> mNotes,
                              boolean hasBar,
                              String mImageTitle,
                              boolean hasMutedStrings,
                              StringMutedHolder mStringMutedHolder) {
        super(mPosition,
              mStartFretNumber,
              mNotes,
              hasBar,
              mImageTitle,
              hasMutedStrings,
              mStringMutedHolder);
    }
}
