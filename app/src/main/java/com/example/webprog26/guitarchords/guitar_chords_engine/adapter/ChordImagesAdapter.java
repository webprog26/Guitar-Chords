package com.example.webprog26.guitarchords.guitar_chords_engine.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.webprog26.guitarchords.R;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.ChordImageClickEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter for chord images {@link RecyclerView}
 */

public class ChordImagesAdapter extends RecyclerView.Adapter<ChordImagesAdapter.ChordImagesViewholder>{

    private final ArrayList<Bitmap> mChordsImagesList;
    private final Context mContext;

    public ChordImagesAdapter(ArrayList<Bitmap> chordsImagesList, Context context) {
        this.mChordsImagesList = chordsImagesList;
        this.mContext = context;
    }

    private ArrayList<Bitmap> getChordsImagesList() {
        return mChordsImagesList;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public ChordImagesViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChordImagesViewholder(LayoutInflater.from(getContext()).inflate(R.layout.chord_image_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ChordImagesViewholder holder, int position) {
        Bitmap chordImageBitmap = getChordsImagesList().get(position);

        ImageView ivChordImage = holder.getIvChordImage();
        ivChordImage.setImageBitmap(chordImageBitmap);

        ivChordImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ChordImageClickEvent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return getChordsImagesList().size();
    }

    class ChordImagesViewholder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_chord_image)
        ImageView mIvChordImage;

        ChordImagesViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        ImageView getIvChordImage() {
            return mIvChordImage;
        }
    }
}
