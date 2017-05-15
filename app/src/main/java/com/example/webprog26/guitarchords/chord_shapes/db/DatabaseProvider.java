package com.example.webprog26.guitarchords.chord_shapes.db;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.BarChordShape;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.Note;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.StringMutedHolder;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.ChordsLoadedToDatabaseEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by webpr on 15.05.2017.
 */

public class DatabaseProvider {

    private static final String TAG = "DatabaseProvider";

    private static final String C = "C";
    //Todo add all the chords titles

    private static final String CHORD_ALTERATION_ORDINARY = "ordinary";
    private static final String CHORD_ALTERATION_SHARP = "sharp";
    private static final String CHORD_ALTERATION_FLAT = "flat";

    private static final String CHORD_TYPE_MAJ = "maj";
    //Todo add all the chords types

    private final DatabaseHelper mDatabaseHelper;

    public DatabaseProvider(Context context) {
        this.mDatabaseHelper = new DatabaseHelper(context);
    }

    public void addChordsToDB(ArrayList<Chord> chords){
        for(Chord chord: chords){
            insertChordToDb(chord);
        }
    }

    public DatabaseHelper getDatabaseHelper() {
        return mDatabaseHelper;
    }

    private long insertChordToDb(Chord chord){
        Log.i(TAG, "insertChordToDb() " + chord.getChordTitle());
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CHORD_TITLE, chord.getChordTitle());
        contentValues.put(DatabaseHelper.CHORD_SECOND_TITLE, chord.getChordSecondTitle());
        contentValues.put(DatabaseHelper.CHORD_TYPE, chord.getChordType());
        contentValues.put(DatabaseHelper.CHORD_ALTERATION, chord.getChordAlteration());

        String chordShapesTableTitle = getChordShapesTable(chord);

        if(chordShapesTableTitle != null){

            for(ChordShape chordShape: chord.getChordShapes()){
                insertChordShapeToDb(chordShapesTableTitle, chordShape);
            }
        }
        EventBus.getDefault().post(new ChordsLoadedToDatabaseEvent());
        return getDatabaseHelper().getWritableDatabase().insert(DatabaseHelper.CHORDS_TABLE, null, contentValues);
    }


    private void insertChordShapeToDb(String shapesTableTitle, ChordShape chordShape){
        Log.i(TAG, "insertChordShapeToDb() " + chordShape.getPosition() + " to table " + shapesTableTitle);
        for(int i = 0; i < chordShape.getNotes().size(); i++){
            Note note = chordShape.getNotes().get(i);
            Point noteCoordinates = note.getNoteCoordinates();
            StringMutedHolder stringMutedHolder = chordShape.getStringMutedHolder();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.SHAPE_POSITION, chordShape.getPosition());
            contentValues.put(DatabaseHelper.SHAPE_START_FRET_NUMBER, chordShape.getStartFretNumber());
            contentValues.put(DatabaseHelper.SHAPE_NOTE_TITLE, note.getNoteTitle());
            contentValues.put(DatabaseHelper.SHAPE_NOTE_X, noteCoordinates.x);
            contentValues.put(DatabaseHelper.SHAPE_NOTE_Y, noteCoordinates.y);
            contentValues.put(DatabaseHelper.SHAPE_IMAGE_TITLE, chordShape.getImageTitle());

            if(chordShape.isHasMutedStrings()){
                contentValues.put(DatabaseHelper.SHAPE_SIXTH_STRING_MUTED, String.valueOf(stringMutedHolder.isSixthStringMuted()));
                contentValues.put(DatabaseHelper.SHAPE_FIFTH_STRING_MUTED, String.valueOf(stringMutedHolder.isFifthStringMuted()));
                contentValues.put(DatabaseHelper.SHAPE_FOURTH_STRING_MUTED, String.valueOf(stringMutedHolder.isFourthStringMuted()));
                contentValues.put(DatabaseHelper.SHAPE_THIRD_STRING_MUTED, String.valueOf(stringMutedHolder.isThirdStringMuted()));
                contentValues.put(DatabaseHelper.SHAPE_SECOND_STRING_MUTED, String.valueOf(stringMutedHolder.isSecondStringMuted()));
                contentValues.put(DatabaseHelper.SHAPE_FIRST_STRING_MUTED, String.valueOf(stringMutedHolder.isFirstStringMuted()));
            }

            if(chordShape.isHasBar()){
                Point startBarPoint = ((BarChordShape)chordShape).getBarStartPoint();
                Point endBarPoint = ((BarChordShape)chordShape).getBarEndPoint();

                contentValues.put(DatabaseHelper.SHAPE_HAS_BAR, String.valueOf(chordShape.isHasBar()));
                contentValues.put(DatabaseHelper.SHAPE_BAR_START_X, startBarPoint.x);
                contentValues.put(DatabaseHelper.SHAPE_BAR_START_Y, startBarPoint.y);
                contentValues.put(DatabaseHelper.SHAPE_BAR_END_X, endBarPoint.x);
                contentValues.put(DatabaseHelper.SHAPE_BAR_END_Y, endBarPoint.y);
            }
            getDatabaseHelper().getWritableDatabase().insert(shapesTableTitle, null, contentValues);
        }
    }

    private String getChordShapesTable(Chord chord){


        switch (chord.getChordTitle()){
            case C:

                switch (chord.getChordType()){
                    case CHORD_TYPE_MAJ:

                        switch (chord.getChordAlteration()){
                            case CHORD_ALTERATION_ORDINARY:
                                    return DatabaseHelper.C_MAJ_ORDINARY;
                        }

                        break;
                }

                break;
        }
        return null;
    }
}
