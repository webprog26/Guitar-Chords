package com.example.webprog26.guitarchords;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

public class PlayChordActivity extends AppCompatActivity {

    public static final String CHORD_TO_PLAY = "chord_to_play";

    private static final String TAG = "PlayActivity_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_chord);

        if(getIntent() != null){
            final Chord chordToPlay = (Chord) getIntent().getSerializableExtra(CHORD_TO_PLAY);
            if(chordToPlay != null){
                Log.i(TAG, chordToPlay.toString());
            }
        }
    }
}
