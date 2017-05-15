package com.example.webprog26.guitarchords;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.webprog26.guitarchords.app.GuitarChordsApp;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;
import com.example.webprog26.guitarchords.guitar_chords_engine.commands.ReadDataFromJSONCommand;
import com.example.webprog26.guitarchords.guitar_chords_engine.commands.TransformJSONDataToPojosCommand;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.ChordsLoadedToDatabaseEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.DataHasBeenTransformedToPOJOsEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.JSONDataHasBeenReadEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.LoadDataToDatabaseEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.ReadJSONDataFromAssetsEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "StartActivity_TAG";

    @BindView(R.id.btn_go)
    Button mBtnGo;

    private static final String ARE_CHORDS_SHAPES_BEEN_READ_TAG = "are_chords_chapes_been_read_tag";

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        mBtnGo.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!getSharedPreferences().getBoolean(ARE_CHORDS_SHAPES_BEEN_READ_TAG, false)){
            EventBus.getDefault().post(new ReadJSONDataFromAssetsEvent());
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onReadJSONDataFromAssetsEvent(ReadJSONDataFromAssetsEvent readJSONDataFromAssetsEvent){
        new ReadDataFromJSONCommand(getAssets()).execute();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onJSONDataHasBeenReadEvent(JSONDataHasBeenReadEvent jsonDataHasBeenReadEvent){
        Log.i(TAG, "JSONDataHasBeenReadEvent in StartActivity");
        new TransformJSONDataToPojosCommand(jsonDataHasBeenReadEvent.getJSONDataString()).execute();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataHasBeenTransformedToPOJOsEvent(DataHasBeenTransformedToPOJOsEvent dataHasBeenTransformedToPOJOsEvent){
        for(Chord chord: dataHasBeenTransformedToPOJOsEvent.getChords()){
            Log.i(TAG, chord.toString());
        }
       EventBus.getDefault().post(new LoadDataToDatabaseEvent(dataHasBeenTransformedToPOJOsEvent.getChords()));
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoadDataToDatabaseEvent(LoadDataToDatabaseEvent loadDataToDatabaseEvent){
        GuitarChordsApp.getDatabaseProvider().addChordsToDB(loadDataToDatabaseEvent.getChords());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChordsLoadedToDatabaseEvent(ChordsLoadedToDatabaseEvent chordsLoadedToDatabaseEvent){
        Log.i(TAG, "onChordsLoadedToDatabaseEvent");
        getSharedPreferences().edit().putBoolean(ARE_CHORDS_SHAPES_BEEN_READ_TAG, true).apply();
        getBtnGo().setEnabled(true);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(StartActivity.this, MainActivity.class));
        finish();
    }

    public Button getBtnGo() {
        return mBtnGo;
    }

    private SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }
}
