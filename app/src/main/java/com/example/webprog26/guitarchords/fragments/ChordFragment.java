package com.example.webprog26.guitarchords.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.webprog26.guitarchords.R;
import com.example.webprog26.guitarchords.engine.events.ChordsImagesLoadedEvent;
import com.example.webprog26.guitarchords.engine.events.LoadChordsImagesFromAssetsEvent;
import com.example.webprog26.guitarchords.engine.models.Chord;
import com.example.webprog26.guitarchords.engine.helpers.LoadBitmapsFromAssetsHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by webprog26 on 09.05.2017.
 */

public class ChordFragment extends Fragment {

    private static final String TAG = "ChordFragment";

    public static final String CHORD = "chord";


    private static final String MAJ = "maj";
    private static final String ORDINARY = "ordinary";
    private static final String SHARP = "sharp";
    private static final String FLAT = "flat";

    private static final int FINGERING_IMAGES_PER_CHORD_COUNT = 5;

    @BindView(R.id.tv)
    TextView tv;

    private Unbinder unbinder;

    public static ChordFragment newInstance(Chord chord){
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
        if(getArguments() != null){
            Chord chord = (Chord) getArguments().getSerializable(CHORD);
            tv.setText(chord.toString());

            String imagesPath = getImagesPath(chord);

            Log.i(TAG, chord.toString());
            if(imagesPath != null){
                EventBus.getDefault().post(new LoadChordsImagesFromAssetsEvent(imagesPath));
            }
            Log.i(TAG, "imagesPath " + imagesPath);
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
        Log.i(TAG, "onLoadChordsImagesFromAssetsEvent");
        ArrayList<Bitmap> bitmaps = LoadBitmapsFromAssetsHelper.getBitmaps(getActivity().getAssets(), loadChordsImagesFromAssetsEvent.getPathString());
        Log.i(TAG, "onLoadChordsImagesFromAssetsEvent bitmaps.size()" + bitmaps.size());
        EventBus.getDefault().post(new ChordsImagesLoadedEvent(bitmaps));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChordsImagesLoadedEvent(ChordsImagesLoadedEvent chordsImagesLoadedEvent){
        Log.i(TAG, "onChordsImagesLoadedEvent");
        for(Bitmap bitmap: chordsImagesLoadedEvent.getChordsImages()){
            Log.i(TAG, bitmap.toString());
        }
    }

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
}
