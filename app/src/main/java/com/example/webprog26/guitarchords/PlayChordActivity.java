package com.example.webprog26.guitarchords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.webprog26.guitarchords.guitar_chords_engine.helpers.ChordTitleFromPlayableShapeGetter;
import com.example.webprog26.guitarchords.guitar_chords_engine.interfaces.PlayChordActivityControlsEnabler;
import com.example.webprog26.guitarchords.guitar_chords_engine.listeners.ShapesControlButtonsListener;
import com.example.webprog26.guitarchords.guitar_chords_engine.managers.PlayableShapeManager;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.ChordTitlesHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Apps {@link PlayChordActivity}
 */

public class PlayChordActivity extends AppCompatActivity implements PlayChordActivityControlsEnabler{

    public static final String ACTIVITY_PLAYABLE_SHAPE_POSITION = "activity_playable_shape_position";
    public static final String ACTIVITY_CHORD_TITLE = "activity_chord_title";

    private static final String TAG = "PlayActivity_TAG";

    @BindView(R.id.tv_chord_title)
    TextView mTvChordTitle;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(R.id.btn_previous)
    Button mBtnPrevious;

    private PlayableShapeManager mPlayableShapeManager;
    private ShapesControlButtonsListener mShapesControlButtonsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_chord);
        ButterKnife.bind(this);

        Intent receivedIntent = getIntent();

        if(receivedIntent != null){

           final int playableShapePosition = receivedIntent.getIntExtra(ACTIVITY_PLAYABLE_SHAPE_POSITION, -1);
           final ChordTitlesHolder chordTitlesHolder = (ChordTitlesHolder) receivedIntent.getSerializableExtra(ACTIVITY_CHORD_TITLE);
           if(chordTitlesHolder != null){

               if(playableShapePosition != -1){
                   getTvChordTitle().setText(new ChordTitleFromPlayableShapeGetter(this).getChordTitleFromPlayableShape(chordTitlesHolder));
                   mPlayableShapeManager = new PlayableShapeManager(getSupportFragmentManager(),
                           R.id.play_chord_activity_content,
                           this,
                           playableShapePosition,
                           this);

                   Log.i(TAG, "playableShapePosition: " + playableShapePosition);

                   if(savedInstanceState == null) {
                       mPlayableShapeManager.setPlayableShapeFragment();
                   }
               }
           }
        }

        if(mPlayableShapeManager != null){
            mShapesControlButtonsListener = new ShapesControlButtonsListener(mPlayableShapeManager);
            getBtnNext().setOnClickListener(mShapesControlButtonsListener);
            getBtnPrevious().setOnClickListener(mShapesControlButtonsListener);
        }
    }

    private TextView getTvChordTitle() {
        return mTvChordTitle;
    }

    private Button getBtnNext() {
        return mBtnNext;
    }

    private Button getBtnPrevious() {
        return mBtnPrevious;
    }

    @Override
    public void setNextPlayableShapeButtonEnabled(boolean enabled) {
        getBtnNext().setEnabled(enabled);
    }

    @Override
    public void setPreviousPlayableShapeButtonEnabled(boolean enabled) {
        getBtnPrevious().setEnabled(enabled);
    }
}

