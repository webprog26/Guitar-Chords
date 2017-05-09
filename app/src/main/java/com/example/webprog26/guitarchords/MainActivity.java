package com.example.webprog26.guitarchords;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;

import com.example.webprog26.guitarchords.engine.listeners.SpinnerListener;
import com.example.webprog26.guitarchords.engine.manager.ChordsManager;
import com.example.webprog26.guitarchords.interfaces.SpinnerReseter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SpinnerReseter{

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

        mChordsManager = new ChordsManager(getSupportFragmentManager(), R.id.fl_content);

        mSpinnerListener = new SpinnerListener(getChordsManager(), this);

        getSpChordsTitles().setOnItemSelectedListener(getSpinnerListener());
        getSpChordsTypes().setOnItemSelectedListener(getSpinnerListener());
        getSpChordsParams().setOnItemSelectedListener(getSpinnerListener());

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
        getSpChordsTitles().setSelection(0);
    }

    @Override
    public void resetChordsTypesSpinner() {
        getSpChordsTypes().setSelection(0);
    }

    @Override
    public void resetChordsParamsSpinner() {
        getSpChordsParams().setSelection(0);
    }
}
