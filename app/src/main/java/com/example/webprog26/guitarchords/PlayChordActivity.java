package com.example.webprog26.guitarchords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

/**
 * Apps {@link PlayChordActivity}
 */

public class PlayChordActivity extends AppCompatActivity {

    public static final String SHAPE_TO_PLAY_POSITION = "shape_to_play_position";
    public static final String CHORD_TITLE = "chord_title";
    public static final String CHORD_SECOND_TITLE = "chord_second_title";
    public static final String CHORD_SHAPES_TABLE_TITLE = "chord_shapes_table_title";


    private static final String TAG = "PlayActivity_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_chord);

        Intent receivedIntent = getIntent();

        if(receivedIntent != null){
            final int chordShapeToPlayPosition = receivedIntent.getIntExtra(SHAPE_TO_PLAY_POSITION, -1);
            final String chordTitle = receivedIntent.getStringExtra(CHORD_TITLE);
            final String chordSecondTitle = receivedIntent.getStringExtra(CHORD_SECOND_TITLE);
            final String chordShapesTableTitle = receivedIntent.getStringExtra(CHORD_SHAPES_TABLE_TITLE);

                Log.i(TAG, "shape position " + chordShapeToPlayPosition);


            if(chordTitle != null){
                Log.i(TAG, chordTitle);
            }

            if(chordSecondTitle != null){
                Log.i(TAG, chordSecondTitle);
            }

            if (chordShapesTableTitle != null) {
                Log.i(TAG, chordShapesTableTitle);
            }
        }
    }
}

