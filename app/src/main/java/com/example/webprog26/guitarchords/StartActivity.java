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
            //No data in database, read data from JSON
            EventBus.getDefault().post(new ReadJSONDataFromAssetsEvent());
        } else {
            //Data is present in database, make user possible to resume
            getBtnGo().setEnabled(true);
        }
    }

    /**
     * Handles {@link ReadJSONDataFromAssetsEvent}. Starts reading data from JSON file,
     * located in assets directory
     * @param readJSONDataFromAssetsEvent {@link ReadJSONDataFromAssetsEvent}
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onReadJSONDataFromAssetsEvent(ReadJSONDataFromAssetsEvent readJSONDataFromAssetsEvent){
        //Starting reading data from JSON file
        new ReadDataFromJSONCommand(getAssets()).execute();
    }

    /**
     * Handles {@link JSONDataHasBeenReadEvent}. Starts transforming read data into POJOs
     * @param jsonDataHasBeenReadEvent {@link JSONDataHasBeenReadEvent}
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onJSONDataHasBeenReadEvent(JSONDataHasBeenReadEvent jsonDataHasBeenReadEvent){
        Log.i(TAG, "JSONDataHasBeenReadEvent in StartActivity");
        //Starting transforming read data into POJOs
        new TransformJSONDataToPojosCommand(jsonDataHasBeenReadEvent.getJSONDataString()).execute();
    }

    /**
     * Handles {@link DataHasBeenTransformedToPOJOsEvent}. Starts inserting data to local
     * {@link android.database.sqlite.SQLiteDatabase} instance
     * @param dataHasBeenTransformedToPOJOsEvent {@link DataHasBeenTransformedToPOJOsEvent}
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataHasBeenTransformedToPOJOsEvent(DataHasBeenTransformedToPOJOsEvent dataHasBeenTransformedToPOJOsEvent){
        for(Chord chord: dataHasBeenTransformedToPOJOsEvent.getChords()){
            Log.i(TAG, chord.toString());
        }
        EventBus.getDefault().post(new LoadDataToDatabaseEvent(dataHasBeenTransformedToPOJOsEvent.getChords()));
    }

    /**
     * Handles {@link LoadDataToDatabaseEvent}. Starts inserting data to local
     * {@link android.database.sqlite.SQLiteDatabase} instance
     * @param loadDataToDatabaseEvent {@link LoadDataToDatabaseEvent}
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoadDataToDatabaseEvent(LoadDataToDatabaseEvent loadDataToDatabaseEvent){
        //Starting inserting data to local database
        GuitarChordsApp.getDatabaseProvider().addChordsToDB(loadDataToDatabaseEvent.getChords());
    }

    /**
     * Handles {@link ChordsLoadedToDatabaseEvent}. Writes marker to {@link SharedPreferences}
     * and makes resume-app's-work button enabled
     * @param chordsLoadedToDatabaseEvent {@link ChordsLoadedToDatabaseEvent}
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChordsLoadedToDatabaseEvent(ChordsLoadedToDatabaseEvent chordsLoadedToDatabaseEvent){
        Log.i(TAG, "onChordsLoadedToDatabaseEvent");
        //Writing marker to {@link SharedPreferences}
        getSharedPreferences().edit().putBoolean(ARE_CHORDS_SHAPES_BEEN_READ_TAG, true).apply();
        //Making resume-app's-work button enabled
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
