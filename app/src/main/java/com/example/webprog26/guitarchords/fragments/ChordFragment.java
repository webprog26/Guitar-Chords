package com.example.webprog26.guitarchords.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;
import com.example.webprog26.guitarchords.guitar_chords_engine.helpers.LoadBitmapsFromAssetsHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

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

                //Asking DatabaseProvider for Chord's second title and chord shapes table title
                EventBus.getDefault().post(new FillChordWithDataEvent(chord));

                //Initialing adapter with empty data
                mChordsShapesAdapter = new ChordsShapesAdapter(chord.getChordShapes(), getActivity());

                //Asking DatabaseProvider for the chord shapes
                EventBus.getDefault().post(new LoadShapesFromDatabaseEvent(chord));
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
        final ArrayList<ChordShape> chordShapes = getDatabaseProvider().getChordShapes(loadShapesFromDatabaseEvent.getChord());
                EventBus.getDefault().post(new ShapesLoadedFromDatabaseEvent(chordShapes));
    }

    /**
     * Handles {@link ShapesLoadedFromDatabaseEvent}. Adds bitmap chord shape image to every {@link ChordShape}
     * @param shapesLoadedFromDatabaseEvent {@link ShapesLoadedFromDatabaseEvent}
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onShapesLoadedFromDatabaseEvent(ShapesLoadedFromDatabaseEvent shapesLoadedFromDatabaseEvent){
        ArrayList<ChordShape> chordShapes = shapesLoadedFromDatabaseEvent.getChordShapes();
        for(ChordShape chordShape: chordShapes){
            Bitmap shapeImage = LoadBitmapsFromAssetsHelper.loadBitmapFromAssets(getActivity().getAssets(),
                    chordShape.getImagePath());
            if(shapeImage != null){
                chordShape.setShapeImage(shapeImage);
            }
        }
        EventBus.getDefault().post(new ChordsShapesReadyWithImagesEvent(chordShapes));
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
     * via {@link com.example.webprog26.guitarchords.guitar_chords_engine.manager.ChordsManager}
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

    public DatabaseProvider getDatabaseProvider() {
        return GuitarChordsApp.getDatabaseProvider();
    }

    public ChordsShapesAdapter getChordsShapesAdapter() {
        return mChordsShapesAdapter;
    }

    public RecyclerView getRvChordImages() {
        return mRvChordImages;
    }
}
