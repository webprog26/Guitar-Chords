package com.example.webprog26.guitarchords.fragments;

import android.graphics.Bitmap;
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
import com.example.webprog26.guitarchords.guitar_chords_engine.adapter.ChordImagesAdapter;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.ChordsImagesLoadedEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.LoadChordsImagesFromAssetsEvent;
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


    private static final String MAJ = "maj";
    private static final String ORDINARY = "ordinary";
    private static final String SHARP = "sharp";
    private static final String FLAT = "flat";

    @BindView(R.id.rv_chord_images)
    RecyclerView mRvChordImages;

    private Unbinder unbinder;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chord_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initImagesRecyclerView();

        if(getArguments() != null){
            Chord chord = (Chord) getArguments().getSerializable(CHORD);

            String imagesPath = getImagesPath(chord);

            if(imagesPath != null){
                EventBus.getDefault().post(new LoadChordsImagesFromAssetsEvent(imagesPath));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoadChordsImagesFromAssetsEvent(LoadChordsImagesFromAssetsEvent loadChordsImagesFromAssetsEvent){

        ArrayList<Bitmap> bitmaps = LoadBitmapsFromAssetsHelper.getBitmaps(getActivity().getAssets(),
                                                                           loadChordsImagesFromAssetsEvent.getPathString());

        EventBus.getDefault().post(new ChordsImagesLoadedEvent(bitmaps));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChordsImagesLoadedEvent(ChordsImagesLoadedEvent chordsImagesLoadedEvent){

        ChordImagesAdapter imagesAdapter = new ChordImagesAdapter(chordsImagesLoadedEvent.getChordsImages(),
                                                                  getActivity());

        getRvChordImages().setAdapter(imagesAdapter);
    }

    /**
     * Creates path to chosen chord images directory depending on chord params
     * @param chord {@link Chord}
     * @return String
     */
    private String getImagesPath(final Chord chord){

        String imagesPath = chord.getChordTitle().toLowerCase();

        if(!chord.getChordType().equalsIgnoreCase(Chord.NO_TYPE)){
            imagesPath += "/" + chord.getChordType();
        } else {
            imagesPath += "/" + MAJ;
        }

        if(!chord.getChordParam().equalsIgnoreCase(Chord.NO_PARAM)){
            switch (chord.getChordParam()){
                case "b":
                    imagesPath += "/" + FLAT;
                    break;
                case "#":
                    imagesPath += "/" + SHARP;
                    break;
            }
        } else {
            imagesPath += "/" + ORDINARY;
        }

        return imagesPath;
    }

    /**
     * Initializes chord images {@link RecyclerView} with necessary params
     */
    private void initImagesRecyclerView(){
        RecyclerView rvChordImages = getRvChordImages();
        rvChordImages.setHasFixedSize(true);
        rvChordImages.setItemAnimator(new DefaultItemAnimator());
        rvChordImages.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public RecyclerView getRvChordImages() {
        return mRvChordImages;
    }
}
