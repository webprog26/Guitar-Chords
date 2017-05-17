package com.example.webprog26.guitarchords;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.PlayableShape;
import com.example.webprog26.guitarchords.fragments.PlayShapeFragment;
import com.example.webprog26.guitarchords.guitar_chords_engine.managers.PlayableShapeManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Apps {@link PlayChordActivity}
 */

public class PlayChordActivity extends AppCompatActivity {

    public static final String ACTIVITY_PLAYABLE_SHAPE = "activity_playable_shape";

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

           final PlayableShape playableShape = (PlayableShape)receivedIntent.getSerializableExtra(ACTIVITY_PLAYABLE_SHAPE);

           if(playableShape != null){

               getTvChordTitle().setText(getChordTitleFromPlayableShape(playableShape));
               mPlayableShapeManager = new PlayableShapeManager(getSupportFragmentManager(),
                                                                R.id.play_chord_activity_content,
                                                                this,
                                                                playableShape);

               if(savedInstanceState == null) {
                    mPlayableShapeManager.setPlayableShapeFragment();
               }
           }
        }
    }

    private String getChordTitleFromPlayableShape(PlayableShape playableShape){
        if(playableShape.getChordSecondTitle() == null){
            return getString(R.string.chord_with_one_title, playableShape.getChordTitle());
        }
        return getString(R.string.chord_with_two_titles, playableShape.getChordTitle(), playableShape.getChordSecondTitle());
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

