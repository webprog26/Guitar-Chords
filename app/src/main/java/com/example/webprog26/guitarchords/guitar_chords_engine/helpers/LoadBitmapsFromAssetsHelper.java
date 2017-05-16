package com.example.webprog26.guitarchords.guitar_chords_engine.helpers;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Helper class to load chord images from specific directory in assets
 */

public class LoadBitmapsFromAssetsHelper {

    private static final String TAG = "BitmapsHelper";

    /**
     * Loads single {@link Bitmap} via given path
     * @param assetManager {@link AssetManager}
     * @param fileName {@link String}
     * @return Bitmap
     */
    public static Bitmap loadBitmapFromAssets(AssetManager assetManager, String fileName){
        Bitmap bitmap = null;
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(fileName);
            Log.i(TAG, "fileName " + fileName);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException ioe){
            ioe.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }

        return bitmap;
    }

    /**
     * Loads ArrayList of chord's images via given path and loadBitmapFromAssets() method
     * @param assetManager {@link AssetManager}
     * @param dirName {@link String}
     * @return ArrayList
     */
    private static ArrayList<Bitmap> getBitmapsFromAssets(AssetManager assetManager, String dirName){

        ArrayList<Bitmap> bitmaps = new ArrayList<>();

        try {
            String[] fileNames = assetManager.list(dirName);

            for(String fileName: fileNames){

                Bitmap bitmap = loadBitmapFromAssets(assetManager, dirName + "/" + fileName);
                if(bitmap != null){
                    bitmaps.add(bitmap);
                }
            }
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

        return bitmaps;
    }

    /**
     * Loads ArrayList of chord's images via given path
     * @param assetManager {@link AssetManager}
     * @param path {@link String}
     * @return ArrayList
     */
    public static ArrayList<Bitmap> getBitmaps(AssetManager assetManager, String path){
        return getBitmapsFromAssets(assetManager, path);
    }

    public static Bitmap getBitmapFromAsset(AssetManager mgr, String path) {
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            is = mgr.open(path);
            bitmap = BitmapFactory.decodeStream(is);
        } catch (final IOException e) {
            bitmap = null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
        }
        return bitmap;
    }
}
