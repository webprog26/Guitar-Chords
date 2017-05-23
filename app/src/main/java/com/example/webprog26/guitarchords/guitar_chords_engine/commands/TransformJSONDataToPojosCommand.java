package com.example.webprog26.guitarchords.guitar_chords_engine.commands;

import android.graphics.Point;
import android.util.Log;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.BarChordShape;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.Note;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.StandardChordShape;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.StringMutedHolder;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.DataHasBeenTransformedToPOJOsEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This {@link Command} transforms JSON data to POJO classes
 */

public class TransformJSONDataToPojosCommand implements Command {

    private static final String TAG = "Transformer";

    private static final String CHORDS_ROOT = "chords";
    private static final String CHORD_TITLE = "chord_title";
    private static final String CHORD_SECOND_TITLE = "chord_second_title";
    private static final String CHORD_TYPE = "chord_type";
    private static final String CHORD_ALTERATION = "chord_alteration";

    private static final String CHORD_SHAPES_TABLE = "chord_shapes_table";
    private static final String CHORD_SHAPES = "chord_shapes";

    private static final String SHAPE_POSITION = "shape_position";
    private static final String SHAPE_START_FRET_POSITION = "shape_start_fret_position";
    private static final String SHAPE_IMAGE_TITLE = "shape_image_title";
    private static final String SHAPE_NOTES = "shape_notes";


    private static final String NOTE_TITLE = "note_title";
    private static final String NOTE_X = "note_x";
    private static final String NOTE_Y = "note_y";
    private static final String FINGER_INDEX = "finger_index";
    private static final String NOTE_PLACE = "note_place";

    private static final String HAS_BAR = "has_bar";
    private static final String START_BAR_POINT_X = "start_bar_point_x";
    private static final String START_BAR_POINT_Y = "start_bar_point_y";
    private static final String END_BAR_POINT_X = "end_bar_point_x";
    private static final String END_BAR_POINT_Y = "end_bar_point_y";

    private static final String HAS_MUTED_STRINGS = "has_muted_strings";
    private static final String SIXTH_STRING_MUTED = "sixth_string_muted";
    private static final String FIFTH_STRING_MUTED = "fifth_string_muted";
    private static final String FOURTH_STRING_MUTED = "fourth_string_muted";
    private static final String THIRD_STRING_MUTED = "third_string_muted";
    private static final String SECOND_STRING_MUTED = "second_string_muted";
    private static final String FIRST_STRING_MUTED = "first_string_muted";


    private final String mJsonString;

    public TransformJSONDataToPojosCommand(String mJsonString) {
        this.mJsonString = mJsonString;
    }

    @Override
    public void execute() {
        transformDataToPOJOs();
    }

    private String getJsonString() {
        return mJsonString;
    }

    /**
     * Transforms JSON data to POJO classes
     */
    private void transformDataToPOJOs(){
        Log.i(TAG, "transformDataToPOJOs()");
        ArrayList<Chord> chords = new ArrayList<>();

        if(mJsonString != null){
            JSONObject chordsJsonObject = null;

            try {
                chordsJsonObject = new JSONObject(getJsonString());
            } catch (JSONException jse){
                jse.printStackTrace();
            }

            if(chordsJsonObject != null){
                JSONArray chordsJsonArray = null;

                try {
                    chordsJsonArray = chordsJsonObject.getJSONArray(CHORDS_ROOT);
                } catch (JSONException jse){
                    jse.printStackTrace();
                }

                if(chordsJsonArray != null){
                    JSONObject singleChordsJsonObject;

                    try {
                        for(int i = 0; i < chordsJsonArray.length(); i++){
                            singleChordsJsonObject = chordsJsonArray.getJSONObject(i);

                            Chord chord = null;

                            if(singleChordsJsonObject != null){
                                JSONArray chordShapesJsonArray = singleChordsJsonObject.getJSONArray(CHORD_SHAPES);

                                ArrayList<ChordShape> chordShapes = new ArrayList<>();

                                for(int j = 0; j < chordShapesJsonArray.length(); j++){

                                    JSONObject chordShapeJsonObject = chordShapesJsonArray.getJSONObject(j);

                                    ChordShape chordShape;

                                    JSONArray shapeNotesJsonArray = chordShapeJsonObject.getJSONArray(SHAPE_NOTES);
                                    ArrayList<Note> notes = new ArrayList<>();

                                    for(int k = 0; k < shapeNotesJsonArray.length(); k++){
                                        JSONObject noteJsonObject = shapeNotesJsonArray.getJSONObject(k);
                                        Note note = new Note(
                                                noteJsonObject.getString(NOTE_TITLE),
                                                noteJsonObject.getInt(FINGER_INDEX),
                                                new Point(noteJsonObject.getInt(NOTE_X),
                                                          noteJsonObject.getInt(NOTE_Y)),
                                                noteJsonObject.getInt(NOTE_PLACE)
                                        );
                                        notes.add(note);
                                    }

                                    final String imageTitle = chordShapeJsonObject.getString(SHAPE_IMAGE_TITLE);
                                    final int shapePosition = chordShapeJsonObject.getInt(SHAPE_POSITION);
                                    final int shapeStartFretPosition = chordShapeJsonObject.getInt(SHAPE_START_FRET_POSITION);
                                    final boolean hasMutedStrings = Boolean.parseBoolean(chordShapeJsonObject.getString(HAS_MUTED_STRINGS));

                                    final StringMutedHolder stringMutedHolder = new StringMutedHolder();

                                    stringMutedHolder.setSixthStringMuted(Boolean.
                                            parseBoolean(chordShapeJsonObject.getString(SIXTH_STRING_MUTED)));
                                    stringMutedHolder.setFifthStringMuted(Boolean.
                                            parseBoolean(chordShapeJsonObject.getString(FIFTH_STRING_MUTED)));
                                    stringMutedHolder.setFourthStringMuted(Boolean.
                                            parseBoolean(chordShapeJsonObject.getString(FOURTH_STRING_MUTED)));
                                    stringMutedHolder.setThirdStringMuted(Boolean.
                                            parseBoolean(chordShapeJsonObject.getString(THIRD_STRING_MUTED)));
                                    stringMutedHolder.setSecondStringMuted(Boolean.
                                            parseBoolean(chordShapeJsonObject.getString(SECOND_STRING_MUTED)));
                                    stringMutedHolder.setFirstStringMuted(Boolean.
                                            parseBoolean(chordShapeJsonObject.getString(FIRST_STRING_MUTED)));

                                    if(chordShapeJsonObject.getBoolean(HAS_BAR)){
                                        chordShape = new BarChordShape(
                                                shapePosition,
                                                shapeStartFretPosition,
                                                notes,
                                                true,
                                                imageTitle,
                                                hasMutedStrings,
                                                stringMutedHolder,
                                                new Point(chordShapeJsonObject.getInt(START_BAR_POINT_X),
                                                        chordShapeJsonObject.getInt(START_BAR_POINT_Y)),
                                                new Point(chordShapeJsonObject.getInt(END_BAR_POINT_X),
                                                        chordShapeJsonObject.getInt(END_BAR_POINT_Y)));

                                    } else {
                                        chordShape = new StandardChordShape(shapePosition,
                                                shapeStartFretPosition,
                                                notes,
                                                false,
                                                imageTitle,
                                                hasMutedStrings,
                                                stringMutedHolder);
                                    }
                                    chordShapes.add(chordShape);
                                }



                                chord = new Chord(singleChordsJsonObject.getString(CHORD_TITLE),
                                        singleChordsJsonObject.getString(CHORD_SECOND_TITLE),
                                        singleChordsJsonObject.getString(CHORD_TYPE),
                                        singleChordsJsonObject.getString(CHORD_ALTERATION),
                                        chordShapes,
                                        singleChordsJsonObject.getString(CHORD_SHAPES_TABLE));
                            }
                            if(chord != null){
                                Log.i(TAG, chord.toString());
                                chords.add(chord);
                            }
                        }


                    } catch (JSONException jse){
                        jse.printStackTrace();
                    }
                }
            }
        }
        //Notifies com.example.webprog26.guitarchords.StartActivity that data has been transformed
        EventBus.getDefault().post(new DataHasBeenTransformedToPOJOsEvent(chords));
    }
}
