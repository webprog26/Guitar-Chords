package com.example.webprog26.guitarchords;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;

import com.example.webprog26.guitarchords.guitar_chords_engine.events.ChordImageClickEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.listeners.SpinnerListener;
import com.example.webprog26.guitarchords.guitar_chords_engine.manager.ChordsManager;
import com.example.webprog26.guitarchords.interfaces.SpinnerReseter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SpinnerReseter{

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

        mChordsManager = new ChordsManager(getSupportFragmentManager(),
                                           R.id.fl_content,
                                           MainActivity.this);

        mSpinnerListener = new SpinnerListener(getChordsManager(), this);

        getSpChordsTitles().setOnItemSelectedListener(getSpinnerListener());
        getSpChordsTypes().setOnItemSelectedListener(getSpinnerListener());
        getSpChordsParams().setOnItemSelectedListener(getSpinnerListener());

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChordImageClickEvent(ChordImageClickEvent chordImageClickEvent){
        Intent playChordIntent = new Intent(MainActivity.this, PlayChordActivity.class);
        playChordIntent.putExtra(PlayChordActivity.CHORD_TO_PLAY, getChordsManager().getCurrentChord());
        startActivity(playChordIntent);
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
