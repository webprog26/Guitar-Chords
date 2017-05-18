package com.example.webprog26.guitarchords.chord_shapes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Point;
import android.util.Log;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.BarChordShape;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.Note;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.StandardChordShape;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.StringMutedHolder;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.ChordsUploadedToDatabaseEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Chords and chords shapes database provider
 */

public class DatabaseProvider {

    private static final String TAG = "DatabaseProvider";

    private static final String SHAPE_NOTES = "shape_notes";

    private static final String SHAPE_NOTE_TITLE = "shape_note_title";
    private static final String SHAPE_NOTE_FINGER_INDEX = "shape_note_finger_index";
    private static final String SHAPE_NOTE_X = "shape_note_x";
    private static final String SHAPE_NOTE_Y = "shape_note_y";

    private final DatabaseHelper mDatabaseHelper;

    public DatabaseProvider(Context context) {
        this.mDatabaseHelper = new DatabaseHelper(context);
    }

    public void addChordsToDB(ArrayList<Chord> chords){
        for(Chord chord: chords){
            insertChordToDb(chord);
        }
    }

    private DatabaseHelper getDatabaseHelper() {
        return mDatabaseHelper;
    }

    /**
     * Inserts {@link Chord} to local {@link android.database.sqlite.SQLiteDatabase}
     * @param chord {@link Chord}
     * @return long
     */
    private long insertChordToDb(Chord chord){
        Log.i(TAG, "insertChordToDb() " + chord.getChordTitle());
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CHORD_TITLE, chord.getChordTitle());
        contentValues.put(DatabaseHelper.CHORD_SECOND_TITLE, chord.getChordSecondTitle());
        contentValues.put(DatabaseHelper.CHORD_TYPE, chord.getChordType());
        contentValues.put(DatabaseHelper.CHORD_ALTERATION, chord.getChordAlteration());

        String chordShapesTableTitle = chord.getChordShapesTable();

        if(chordShapesTableTitle != null){

            contentValues.put(DatabaseHelper.CHORD_SHAPES_TABLE, chord.getChordShapesTable());

            for(ChordShape chordShape: chord.getChordShapes()){
                insertChordShapeToDb(chordShapesTableTitle, chordShape);
            }
        }
        EventBus.getDefault().post(new ChordsUploadedToDatabaseEvent());
        return getDatabaseHelper().getWritableDatabase().insert(DatabaseHelper.CHORDS_TABLE, null, contentValues);
    }


    /**
     * Inserts {@link ChordShape} to local {@link android.database.sqlite.SQLiteDatabase}
     * @param shapesTableTitle {@link String}
     * @param chordShape {@link ChordShape}
     */
    private void insertChordShapeToDb(String shapesTableTitle, ChordShape chordShape){
        Log.i(TAG, "insertChordShapeToDb() " + chordShape.getPosition() + " to table " + shapesTableTitle);
            Log.i(TAG, "shape has bar " + chordShape.isHasBar());

            Point startBarPoint, endBarPoint;

            StringMutedHolder stringMutedHolder = chordShape.getStringMutedHolder();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.SHAPE_POSITION, chordShape.getPosition());
            contentValues.put(DatabaseHelper.SHAPE_START_FRET_NUMBER, chordShape.getStartFretNumber());



        String notesListString = getNotesArrayToString(chordShape.getNotes());

        if(notesListString != null && notesListString.length() > 0){
            contentValues.put(DatabaseHelper.SHAPE_NOTES_LIST, notesListString);
        }

        contentValues.put(DatabaseHelper.SHAPE_IMAGE_TITLE, chordShape.getImagePath());


            contentValues.put(DatabaseHelper.SHAPE_HAS_MUTED_STRINGS, String.valueOf(chordShape.isHasMutedStrings()));

                contentValues.put(DatabaseHelper.SHAPE_SIXTH_STRING_MUTED, String.valueOf(stringMutedHolder.isSixthStringMuted()));
                contentValues.put(DatabaseHelper.SHAPE_FIFTH_STRING_MUTED, String.valueOf(stringMutedHolder.isFifthStringMuted()));
                contentValues.put(DatabaseHelper.SHAPE_FOURTH_STRING_MUTED, String.valueOf(stringMutedHolder.isFourthStringMuted()));
                contentValues.put(DatabaseHelper.SHAPE_THIRD_STRING_MUTED, String.valueOf(stringMutedHolder.isThirdStringMuted()));
                contentValues.put(DatabaseHelper.SHAPE_SECOND_STRING_MUTED, String.valueOf(stringMutedHolder.isSecondStringMuted()));
                contentValues.put(DatabaseHelper.SHAPE_FIRST_STRING_MUTED, String.valueOf(stringMutedHolder.isFirstStringMuted()));

                if(chordShape.isHasBar()){
                   startBarPoint = ((BarChordShape)chordShape).getBarStartPoint();
                   endBarPoint = ((BarChordShape)chordShape).getBarEndPoint();
                } else {
                    startBarPoint = new Point(-1, -1);
                    endBarPoint = new Point(-1, -1);
                }

                contentValues.put(DatabaseHelper.SHAPE_HAS_BAR, String.valueOf(chordShape.isHasBar()));
                contentValues.put(DatabaseHelper.SHAPE_BAR_START_X, startBarPoint.x);
                contentValues.put(DatabaseHelper.SHAPE_BAR_START_Y, startBarPoint.y);
                contentValues.put(DatabaseHelper.SHAPE_BAR_END_X, endBarPoint.x);
                contentValues.put(DatabaseHelper.SHAPE_BAR_END_Y, endBarPoint.y);
            Log.i(TAG, "contentValues.size(): " + contentValues.size());
            getDatabaseHelper().getWritableDatabase().insert(shapesTableTitle, null, contentValues);
    }

    /**
     * Returns {@link ArrayList} of {@link ChordShape} instances
     * stored in local to local {@link android.database.sqlite.SQLiteDatabase}
     * @param chord {@link Chord}
     * @return ArrayList
     */
    public ArrayList<ChordShape> getChordShapes(final Chord chord){
        String chordShapesTableTitle = ShapesTableTitleHelper.getChordShapesTableTitle(chord);
        Log.i(TAG, "getChordShapes ");

        ArrayList<ChordShape> chordShapes = new ArrayList<>();
        ChordShape chordShape;

        Cursor cursor = getDatabaseHelper().getReadableDatabase().query(chordShapesTableTitle,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        null,
                                                                        DatabaseHelper.SHAPE_ID);
        while(cursor.moveToNext()){
            boolean hasBar = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SHAPE_HAS_BAR)));
            int shapePosition = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.SHAPE_POSITION));
            int startFretNumber = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.SHAPE_START_FRET_NUMBER));

            ArrayList<Note> notes = getNotes(chordShapesTableTitle, shapePosition);


            String imageTitle = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SHAPE_IMAGE_TITLE));

            boolean hasMutedStrings = getBoolean(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SHAPE_HAS_MUTED_STRINGS)));

            StringMutedHolder stringMutedHolder = new StringMutedHolder();
            stringMutedHolder.setSixthStringMuted(getBoolean(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SHAPE_SIXTH_STRING_MUTED))));
            stringMutedHolder.setFifthStringMuted(getBoolean(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SHAPE_FIFTH_STRING_MUTED))));
            stringMutedHolder.setFourthStringMuted(getBoolean(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SHAPE_FOURTH_STRING_MUTED))));
            stringMutedHolder.setThirdStringMuted(getBoolean(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SHAPE_THIRD_STRING_MUTED))));
            stringMutedHolder.setSecondStringMuted(getBoolean(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SHAPE_SECOND_STRING_MUTED))));
            stringMutedHolder.setFirstStringMuted(getBoolean(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SHAPE_FIRST_STRING_MUTED))));

            if(hasBar){
                chordShape = new BarChordShape(
                                shapePosition,
                                startFretNumber,
                                notes,
                                true,
                                imageTitle,
                                hasMutedStrings,
                                stringMutedHolder,
                                new Point(
                                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.SHAPE_BAR_START_X)),
                                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.SHAPE_BAR_START_Y))),
                                new Point(
                                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.SHAPE_BAR_END_X)),
                                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.SHAPE_BAR_END_Y)))

                );
            } else {
                chordShape = new StandardChordShape(shapePosition,
                        startFretNumber,
                        notes,
                        false,
                        imageTitle,
                        hasMutedStrings,
                        stringMutedHolder);
            }
            Log.i(TAG, "shape has bar " + chordShape.isHasBar());
            chordShapes.add(chordShape);
        }
        cursor.close();
        return chordShapes;
    }

    /**
     * Returns {@link ArrayList} of {@link ChordShape} {@link Note} instances
     * @param tableTitle {@link String}
     * @param shapePosition int
     * @return ArrayList
     */
    private ArrayList<Note> getNotes(String tableTitle, int shapePosition){
        ArrayList<Note> notes = new ArrayList<>();

        String whereClause = DatabaseHelper.SHAPE_POSITION + " = ?";
        String[] whereArgs = new String[]{String.valueOf(shapePosition)};

        Cursor cursor = getDatabaseHelper().getReadableDatabase().query(tableTitle,
                                                                        null,
                                                                        whereClause,
                                                                        whereArgs,
                                                                        null,
                                                                        null,
                                                                        null);

        while(cursor.moveToNext()){
            JSONObject jsonNotes = getJSONNotesObject(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SHAPE_NOTES_LIST)));

            if(jsonNotes != null){
                Log.i(TAG, jsonNotes.toString());
                JSONArray jsonArray = jsonNotes.optJSONArray(SHAPE_NOTES);
                for(int i = 0; i < jsonArray.length(); i++){
                    Note note = null;
                    JSONObject arrayObject = null;

                    try {
                        arrayObject = jsonArray.getJSONObject(i);
                    } catch (JSONException e){
                        e.printStackTrace();
                    }

                    if(arrayObject != null){
                       note = getNoteFromJSONObject(arrayObject);
                    }


                    if(note != null){
                        notes.add(note);
                    }
                }
            }
        }
        cursor.close();
        for(Note note: notes){
            Log.i(TAG, "getNote " + note);
        }
        return notes;
    }

    /**
     * Transorms {@link String} value from {@link android.database.sqlite.SQLiteDatabase} to boolean
     * @param s {@link String}
     * @return boolean
     */
    private boolean getBoolean(String s){
        return Boolean.parseBoolean(s);
    }

    /**
     * Fills {@link Chord} instance with it'second title
     * @param chord {@link Chord}
     * @return Chord
     */
    public Chord getChordWithSecondTitle(Chord chord){

        String selection = DatabaseHelper.CHORD_TITLE + " = ? AND "
        + DatabaseHelper.CHORD_TYPE + " = ? AND " + DatabaseHelper.CHORD_ALTERATION + " = ?";
        String[] selectionArgs = new String[]{chord.getChordTitle(), chord.getChordType(), chord.getChordAlteration()};

        Cursor cursor = getDatabaseHelper().getReadableDatabase().query(DatabaseHelper.CHORDS_TABLE, null, selection, selectionArgs, null, null, DatabaseHelper.CHORD_ID);

        while(cursor.moveToNext()){
            String chordSecondTitle = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CHORD_SECOND_TITLE));
            if(chordSecondTitle.equalsIgnoreCase("null")){
                chord.setChordSecondTitle(null);
            } else {
                chord.setChordSecondTitle(chordSecondTitle);
            }
        }
        cursor.close();
        return chord;
    }

    private String getNotesArrayToString(ArrayList<Note> notes){
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for(Note note: notes){
            JSONObject singleNoteJsonObject = new JSONObject();

            try {
                singleNoteJsonObject.put(SHAPE_NOTE_TITLE, note.getNoteTitle());
                singleNoteJsonObject.put(SHAPE_NOTE_FINGER_INDEX, note.getFingerIndex());
                singleNoteJsonObject.put(SHAPE_NOTE_X, note.getNoteCoordinates().x);
                singleNoteJsonObject.put(SHAPE_NOTE_Y, note.getNoteCoordinates().y);
            } catch (JSONException e){
                e.printStackTrace();
            }

            jsonArray.put(singleNoteJsonObject);

        }

        try {
            json.put(SHAPE_NOTES, jsonArray);
            Log.i(TAG, json.toString());
        } catch (JSONException e){
            e.printStackTrace();
        }

        return json.toString();
    }

    private Note getNoteFromJSONObject(JSONObject noteJsonObject){
        Note note = null;
        if(noteJsonObject != null){
            try {
                note = new Note(
                        noteJsonObject.getString(SHAPE_NOTE_TITLE),
                        noteJsonObject.getInt(SHAPE_NOTE_FINGER_INDEX),
                        new Point(
                                noteJsonObject.getInt(SHAPE_NOTE_X),
                                noteJsonObject.getInt(SHAPE_NOTE_Y)
                        )
                );
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return note;
    }

    private JSONObject getJSONNotesObject(String jsonNotesString){
        JSONObject jsonNotes = null;
        try {
            jsonNotes = new JSONObject(jsonNotesString);
        } catch (JSONException e){
            e.printStackTrace();
        }
        return jsonNotes;
    }
}
