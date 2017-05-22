package com.example.webprog26.guitarchords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;

import com.example.webprog26.guitarchords.guitar_chords_engine.events.ChordImageClickEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.SetChordSecondTitleEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.interfaces.SpinnerReseter;
import com.example.webprog26.guitarchords.guitar_chords_engine.listeners.SpinnerListener;
import com.example.webprog26.guitarchords.guitar_chords_engine.managers.ChordsManager;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.ChordTitlesHolder;

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

    private static final int DEFAULT_SPINNER_POSITION = 0;

    @BindView(R.id.sp_chord_title)
    AppCompatSpinner mSpChordTitle;

    @BindView(R.id.sp_chord_type)
    AppCompatSpinner mSpChordType;

    @BindView(R.id.sp_chord_alteration)
    AppCompatSpinner mSpChordAlteration;

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
        getSpChordsTitle().setOnItemSelectedListener(mSpinnerListener);
        getSpChordsType().setOnItemSelectedListener(mSpinnerListener);
        getSpChordsAlteration().setOnItemSelectedListener(mSpinnerListener);

        getSpChordsTitle().setOnTouchListener(mSpinnerListener);
        getSpChordsType().setOnTouchListener(mSpinnerListener);
        getSpChordsAlteration().setOnTouchListener(mSpinnerListener);

        //Todo load last used chord
        Chord chord = new Chord();
        chord.setChordTitle("C");
        chord.setChordType(Chord.NO_TYPE);
        chord.setChordAlteration(Chord.NO_ALTERATION);

        mChordsManager.setFragmentWithListOfChordImages(chord);
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


        playChordIntent.putExtra(PlayChordActivity.ACTIVITY_PLAYABLE_SHAPE_POSITION, chordImageClickEvent.getClickedChordShapePosition());
        playChordIntent.putExtra(PlayChordActivity.ACTIVITY_CHORD_TITLE, new ChordTitlesHolder(getChordsManager().getCurrentChord()));
        startActivity(playChordIntent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSetChordSecondTitleEvent(SetChordSecondTitleEvent setChordSecondTitleEvent){
        Log.i(TAG, "onSetChordSecondTitleEvent()");
        mChordsManager.setChordSecondTitle(setChordSecondTitleEvent.getChord().getChordSecondTitle());
    }


    public AppCompatSpinner getSpChordsTitle() {
        return mSpChordTitle;
    }

    public AppCompatSpinner getSpChordsType() {
        return mSpChordType;
    }

    public AppCompatSpinner getSpChordsAlteration() {
        return mSpChordAlteration;
    }

    public ChordsManager getChordsManager() {
        return mChordsManager;
    }

    public SpinnerListener getSpinnerListener() {
        return mSpinnerListener;
    }

    @Override
    public void resetChordsParamsSpinner() {
        getSpChordsAlteration().setSelection(DEFAULT_SPINNER_POSITION);
    }

    @Override
    public void setChordsTitleSpinnerPosition(int position) {
        getSpChordsTitle().setSelection(position);
    }
}
