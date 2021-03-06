package com.example.webprog26.guitarchords.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.webprog26.guitarchords.R;
import com.example.webprog26.guitarchords.app.GuitarChordsApp;
import com.example.webprog26.guitarchords.chord_shapes.db.DatabaseProvider;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;
import com.example.webprog26.guitarchords.guitar_chords_engine.adapter.ChordsShapesAdapter;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.ChordsShapesReadyWithImagesEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.FillChordWithDataEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.LoadShapesFromDatabaseEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.SetChordSecondTitleEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.ShapesLoadedFromDatabaseEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.managers.ChordFragmentShapesManager;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment to host {@link RecyclerView} with chosen chord shapes
 */

public class ChordFragment extends Fragment {

    private static final String TAG = "ChordFragment";

    public static final String CHORD = "chord";


    @BindView(R.id.rv_chord_images)
    RecyclerView mRvChordImages;

    private Unbinder unbinder;
    private ChordsShapesAdapter mChordsShapesAdapter;
    private ChordFragmentShapesManager mChordFragmentShapesManager;


    /**
     * Initializes {@link ChordFragment} instance with chosen {@link Chord}
     * @param chord {@link Chord}
     * @return ChordFragment
     */
    public static ChordFragment newInstance(final Chord chord){
        Bundle args = new Bundle();
        args.putSerializable(CHORD, chord);
        ChordFragment chordFragment = new ChordFragment();
        chordFragment.setArguments(args);

        return chordFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        if(getArguments() != null){
            //Getting Chord instance with user params
            final Chord chord = (Chord) getArguments().getSerializable(CHORD);

            if(chord != null){

                mChordFragmentShapesManager = new ChordFragmentShapesManager(chord, getActivity().getAssets(), getActivity());
                mChordFragmentShapesManager.fillChordWithData();


                //Initialing adapter with empty data
                mChordsShapesAdapter = new ChordsShapesAdapter(mChordFragmentShapesManager.getChord().getChordShapes(), getActivity());

                //Asking DatabaseProvider for the chord shapes
                Log.i(TAG, "Asking DatabaseProvider for the chord shapes");
                mChordFragmentShapesManager.loadShapesFromSQLiteDb();
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chord_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        //Initialing RecyclerView
        initImagesRecyclerView();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Handles {@link LoadShapesFromDatabaseEvent}. Loads shapes from local {@link android.database.sqlite.SQLiteDatabase}
     * @param loadShapesFromDatabaseEvent {@link LoadShapesFromDatabaseEvent}
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoadShapesFromDatabaseEvent(LoadShapesFromDatabaseEvent loadShapesFromDatabaseEvent){
        Log.i(TAG, "onLoadShapesFromDatabaseEvent");
        mChordFragmentShapesManager.setChordShapes(getDatabaseProvider().getChordShapes(loadShapesFromDatabaseEvent.getChord()));

        if(mChordFragmentShapesManager.getChordShapes().size() > 0){
            mChordFragmentShapesManager.addChordShapesToShapesHolder();
        }
        EventBus.getDefault().post(new ShapesLoadedFromDatabaseEvent());
    }

    /**
     * Handles {@link ShapesLoadedFromDatabaseEvent}. Adds bitmap chord shape image to every {@link ChordShape}
     * @param shapesLoadedFromDatabaseEvent {@link ShapesLoadedFromDatabaseEvent}
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onShapesLoadedFromDatabaseEvent(ShapesLoadedFromDatabaseEvent shapesLoadedFromDatabaseEvent){

        mChordFragmentShapesManager.addShapesBitmapsImages();
        EventBus.getDefault().post(new ChordsShapesReadyWithImagesEvent(mChordFragmentShapesManager.getChordShapes()));
    }

    /**
     * Handles {@link ChordsShapesReadyWithImagesEvent}. Updates {@link ChordsShapesAdapter} with shapes
     * loaded from {@link android.database.sqlite.SQLiteDatabase}
     * @param chordsShapesReadyWithImagesEvent {@link ChordsShapesReadyWithImagesEvent}
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChordsShapesReadyWithImagesEvent(ChordsShapesReadyWithImagesEvent chordsShapesReadyWithImagesEvent){
        getChordsShapesAdapter().updateAdapterData(chordsShapesReadyWithImagesEvent.getChordShapes());
    }

    /**
     * Handles {@link FillChordWithDataEvent}. Notifies {@link com.example.webprog26.guitarchords.MainActivity}
     * (parent activity of the fragment) to store chord data
     * via {@link com.example.webprog26.guitarchords.guitar_chords_engine.managers.ChordsManager}
     * @param fillChordWithDataEvent {@link FillChordWithDataEvent}
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onFillChordWithDataEvent(FillChordWithDataEvent fillChordWithDataEvent){
        final Chord chordWithSecondTitle = getDatabaseProvider().getChordWithSecondTitle(fillChordWithDataEvent.getChord());
        EventBus.getDefault().post(new SetChordSecondTitleEvent(chordWithSecondTitle));
    }

    /**
     * Initializes chord images {@link RecyclerView} with necessary params
     */
    private void initImagesRecyclerView(){
        RecyclerView rvChordImages = getRvChordImages();
        rvChordImages.setHasFixedSize(true);
        rvChordImages.setItemAnimator(new DefaultItemAnimator());
        rvChordImages.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvChordImages.setAdapter(getChordsShapesAdapter());
    }



    private DatabaseProvider getDatabaseProvider() {
        return GuitarChordsApp.getDatabaseProvider();
    }

    private ChordsShapesAdapter getChordsShapesAdapter() {
        return mChordsShapesAdapter;
    }

    private RecyclerView getRvChordImages() {
        return mRvChordImages;
    }
}
