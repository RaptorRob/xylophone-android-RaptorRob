package com.londonappbrewery.xylophonepm;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    // Helpful Constants
    private final int NR_OF_SIMULTANEOUS_SOUNDS = 7;
    private final float LEFT_VOLUME = 1.0f;
    private final float RIGHT_VOLUME = 1.0f;
    private final int NO_LOOP = 0;
    private final int PRIORITY = 1;
    private final float NORMAL_PLAY_RATE = 1.0f;

    // TODO: Add member variables here
    final int soundArray [] = {
            R.raw.note1_c,
            R.raw.note2_d,
            R.raw.note3_e,
            R.raw.note4_f,
            R.raw.note5_g,
            R.raw.note6_a,
            R.raw.note7_b};

    private boolean soundPoolReady;
    private AudioAttributes.Builder mAAB;
    private SoundPool.Builder mSPB;
    private SoundPool mSP;

    private int mSoundIdC;
    private int mSoundIdD;
    private int mSoundIdE;
    private int mSoundIdF;
    private int mSoundIdG;
    private int mSoundIdA;
    private int mSoundIdB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create AudioAttribute.Builder and set attributes
        mAAB = new AudioAttributes.Builder();
        mAAB.setUsage(AudioAttributes.USAGE_MEDIA);
        mAAB.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC);
        AudioAttributes audioAttributes = mAAB.build();


        // TODO: Create a new SoundPoolBuilder and SoundPool
        mSPB = new SoundPool.Builder();
        mSPB.setMaxStreams(NR_OF_SIMULTANEOUS_SOUNDS);
        mSPB.setAudioAttributes(audioAttributes);
        mSP = mSPB.build(); //SoundPool object

        mSP.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.d("This app", "Load Complete: ");
                soundPoolReady = true;
            }
        });

        // Load sound in onCreate()
        mSoundIdC = mSP.load(getApplicationContext(), soundArray[0], PRIORITY);
        mSoundIdD = mSP.load(getApplicationContext(), soundArray[1], PRIORITY);
        mSoundIdE = mSP.load(getApplicationContext(), soundArray[2], PRIORITY);
        mSoundIdF = mSP.load(getApplicationContext(), soundArray[3], PRIORITY);
        mSoundIdG = mSP.load(getApplicationContext(), soundArray[4], PRIORITY);
        mSoundIdA = mSP.load(getApplicationContext(), soundArray[5], PRIORITY);
        mSoundIdB = mSP.load(getApplicationContext(), soundArray[6], PRIORITY);

    }

    // TODO: Add the play methods triggered by the buttons
    public void playSoundFromSoundPool(View v)throws Exception{
        String soundIDFromButton = v.getTag().toString();
        int soundToPlay = 1;
        switch (soundIDFromButton){
            case "mSoundIdC" :
                soundToPlay = mSoundIdC;
                break;
            case "mSoundIdD" :
                soundToPlay = mSoundIdD;
                break;
            case "mSoundIdE" :
                soundToPlay = mSoundIdE;
                break;
            case "mSoundIdF" :
                soundToPlay = mSoundIdF;
                break;
            case "mSoundIdG" :
                soundToPlay = mSoundIdG;
                break;
            case "mSoundIdA" :
                soundToPlay = mSoundIdA;
                break;
            case "mSoundIdB" :
                soundToPlay = mSoundIdB;
                break;
        }

        if(soundPoolReady){
            Log.d("This app", "playSoundFromSoundPool: " + soundIDFromButton);
            mSP.play(soundToPlay, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
        }

    }

}