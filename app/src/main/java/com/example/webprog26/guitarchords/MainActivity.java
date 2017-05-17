package com.example.webprog26.guitarchords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;

import com.example.webprog26.guitarchords.chord_shapes.db.ShapesTableTitleHelper;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.PlayableShape;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.ChordImageClickEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.SetChordSecondTitleEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.listeners.SpinnerListener;
import com.example.webprog26.guitarchords.guitar_chords_engine.managers.ChordsManager;
import com.example.webprog26.guitarchords.interfaces.SpinnerReseter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Apps {@link MainActivity}
 */

public class MainActivity extends AppCompatActivity implements SpinnerReseter{

    private static final String TAG = "MainActivity_TAG";

    private static final int SPINNER_DEFAULT_POSITION = 0;

    @BindView(R.id.sp_chords_titles)
    AppCompatSpinner mSpChordsTitles;

    @BindView(R.id.sp_chords_types)
    AppCompatSpinner mSpChordsTypes;

    @BindView(R.id.sp_chords_params)
    AppCompatSpinner mSpChordsParams;

    private ChordsManager mChordsManager;
    private SpinnerListener mSpinnerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Initializing ChordsManager instance
        mChordsManager = new ChordsManager(getSupportFragmentManager(),
                                           R.id.fl_content,
                                           MainActivity.this);

        //Initializing SpinnerListener instance
        mSpinnerListener = new SpinnerListener(getChordsManager(), this);

        getSpChordsTitles().setOnItemSelectedListener(mSpinnerListener);
        getSpChordsTypes().setOnItemSelectedListener(mSpinnerListener);
        getSpChordsParams().setOnItemSelectedListener(mSpinnerListener);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    /**
     * Handles {@link ChordImageClickEvent}. Starts {@link PlayChordActivity}
     * with current chord as a parameter
     * @param chordImageClickEvent {@link ChordImageClickEvent}
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChordImageClickEvent(ChordImageClickEvent chordImageClickEvent){
        Intent playChordIntent = new Intent(MainActivity.this, PlayChordActivity.class);

        ChordShape chordShape = chordImageClickEvent.getChordShape();
        ChordsManager chordsManager = getChordsManager();

        Log.i(TAG, chordShape.toString());
        Log.i(TAG, "shapes table title " + ShapesTableTitleHelper.getChordShapesTableTitle(chordsManager.getCurrentChord()));

        PlayableShape playableShape = new PlayableShape(
                chordsManager.getCurrentChord().getChordTitle(),
                chordsManager.getChordSecondTitle(),
                ShapesTableTitleHelper.getChordShapesTableTitle(chordsManager.getCurrentChord()),
                chordShape.getPosition()
        );

        playChordIntent.putExtra(PlayChordActivity.ACTIVITY_PLAYABLE_SHAPE, playableShape);
        startActivity(playChordIntent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSetChordSecondTitleEvent(SetChordSecondTitleEvent setChordSecondTitleEvent){
        Log.i(TAG, "onSetChordSecondTitleEvent()");
        mChordsManager.setChordSecondTitle(setChordSecondTitleEvent.getChord().getChordSecondTitle());
    }

    public AppCompatSpinner getSpChordsTitles() {
        return mSpChordsTitles;
    }

    public AppCompatSpinner getSpChordsTypes() {
        return mSpChordsTypes;
    }

    public AppCompatSpinner getSpChordsParams() {
        return mSpChordsParams;
    }

    public ChordsManager getChordsManager() {
        return mChordsManager;
    }

    public SpinnerListener getSpinnerListener() {
        return mSpinnerListener;
    }

    @Override
    public void resetChordsTitleSpinner() {
        getSpChordsTitles().setSelection(SPINNER_DEFAULT_POSITION);
    }

    @Override
    public void resetChordsTypesSpinner() {
        getSpChordsTypes().setSelection(SPINNER_DEFAULT_POSITION);
    }

    @Override
    public void resetChordsParamsSpinner() {
        getSpChordsParams().setSelection(SPINNER_DEFAULT_POSITION);
    }

    @Override
    public void setChordsTitleSpinnerPosition(int position) {
        getSpChordsTitles().setSelection(position);
    }
}
