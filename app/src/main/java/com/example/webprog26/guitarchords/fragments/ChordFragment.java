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
 * Fragment to host {@link RecyclerView} with chosen chord images
 */

public class ChordFragment extends Fragment {

    private static final String TAG = "ChordFragment";

    public static final String CHORD = "chord";


    @BindView(R.id.rv_chord_images)
    RecyclerView mRvChordImages;

    private Unbinder unbinder;
    private DatabaseProvider mDatabaseProvider;
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
        mDatabaseProvider = GuitarChordsApp.getDatabaseProvider();
        if(getArguments() != null){
            final Chord chord = (Chord) getArguments().getSerializable(CHORD);

            EventBus.getDefault().post(new FillChordWithDataEvent(chord));

            mChordsShapesAdapter = new ChordsShapesAdapter(chord.getChordShapes(), getActivity());

            EventBus.getDefault().post(new LoadShapesFromDatabaseEvent(chord));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chord_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoadShapesFromDatabaseEvent(LoadShapesFromDatabaseEvent loadShapesFromDatabaseEvent){
        final ArrayList<ChordShape> chordShapes = mDatabaseProvider.getChordShapes(loadShapesFromDatabaseEvent.getChord());
                EventBus.getDefault().post(new ShapesLoadedFromDatabaseEvent(chordShapes));
    }

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChordsShapesReadyWithImagesEvent(ChordsShapesReadyWithImagesEvent chordsShapesReadyWithImagesEvent){

        mChordsShapesAdapter.updateAdapterData(chordsShapesReadyWithImagesEvent.getChordShapes());

        initImagesRecyclerView(mChordsShapesAdapter);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onFillChordWithDataEvent(FillChordWithDataEvent fillChordWithDataEvent){
        final Chord chordWithSecondTitle = mDatabaseProvider.getChordWithSecondTitle(fillChordWithDataEvent.getChord());
        EventBus.getDefault().post(new SetChordSecondTitleEvent(chordWithSecondTitle));
    }

    /**
     * Initializes chord images {@link RecyclerView} with necessary params
     */
    private void initImagesRecyclerView(ChordsShapesAdapter adapter){
        RecyclerView rvChordImages = getRvChordImages();
        rvChordImages.setHasFixedSize(true);
        rvChordImages.setItemAnimator(new DefaultItemAnimator());
        rvChordImages.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvChordImages.setAdapter(adapter);
    }

    public RecyclerView getRvChordImages() {
        return mRvChordImages;
    }
}
