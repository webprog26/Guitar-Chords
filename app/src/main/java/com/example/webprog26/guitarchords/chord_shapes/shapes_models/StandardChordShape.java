package com.example.webprog26.guitarchords.chord_shapes.shapes_models;

import com.example.webprog26.guitarchords.chord_shapes.muted_strings_holder.StringMutedHolder;
import com.example.webprog26.guitarchords.chord_shapes.note.Note;

import java.util.ArrayList;

/**
 * Standard chord shape model
 */

public class StandardChordShape extends ChordShape {

    public StandardChordShape(int mPosition,
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
