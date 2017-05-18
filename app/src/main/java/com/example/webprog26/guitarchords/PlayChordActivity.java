package com.example.webprog26.guitarchords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.webprog26.guitarchords.guitar_chords_engine.managers.PlayableShapeManager;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.ChordTitlesHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Apps {@link PlayChordActivity}
 */

public class PlayChordActivity extends AppCompatActivity {

    public static final String ACTIVITY_PLAYABLE_SHAPE_POSITION = "activity_playable_shape_position";
    public static final String ACTIVITY_CHORD_TITLE = "activity_chord_title";

    private static final String TAG = "PlayActivity_TAG";

    private static final int SHAPES_PER_CHORD_COUNT = 5;

    @BindView(R.id.tv_chord_title)
    TextView mTvChordTitle;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(R.id.btn_previous)
    Button mBtnPrevious;

    private PlayableShapeManager mPlayableShapeManager;

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
                   getTvChordTitle().setText(getChordTitleFromPlayableShape(chordTitlesHolder));
                   mPlayableShapeManager = new PlayableShapeManager(getSupportFragmentManager(),
                           R.id.play_chord_activity_content,
                           this,
                           playableShapePosition);

                   if(savedInstanceState == null) {
                       mPlayableShapeManager.setPlayableShapeFragment();
                   }
               }
           }
        }
    }

    private String getChordTitleFromPlayableShape(ChordTitlesHolder chordTitlesHolder){
        if(chordTitlesHolder.getChordSecondTitle() == null){
            return getString(R.string.chord_with_one_title, chordTitlesHolder.getChordTitle());
        }
        return getString(R.string.chord_with_two_titles, chordTitlesHolder.getChordTitle(), chordTitlesHolder.getChordSecondTitle());
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
}

