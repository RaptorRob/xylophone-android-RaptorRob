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

    private int soundArrayNumber;
    private boolean soundPoolReady;
    private AudioAttributes.Builder mAAB;
    private SoundPool.Builder mSPB;
    private SoundPool mSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create AudioAttribute.Builder and set attributes
        mAAB = new AudioAttributes.Builder();
        mAAB.setUsage(AudioAttributes.USAGE_MEDIA);
        mAAB.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
        AudioAttributes audioAttributes = mAAB.build();


        // TODO: Create a new SoundPoolBuilder and SoundPool
        mSPB = new SoundPool.Builder();
        mSPB.setMaxStreams(NR_OF_SIMULTANEOUS_SOUNDS);
        mSPB.setAudioAttributes(audioAttributes);
        mSP = mSPB.build(); //SoundPool object

        mSP.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPoolReady = true;
            }
        });

    }

    // TODO: Add the play methods triggered by the buttons
    public void playSoundFromSoundPool(View v)throws Exception{
        soundArrayNumber = Integer.parseInt(v.getTag().toString());
        Log.d("This app", "playSoundFromSoundPool: " + soundArrayNumber);
        mSP.load(getApplicationContext(), soundArray[soundArrayNumber], PRIORITY);
        if(soundPoolReady){
            Log.d("ss", "playSoundFromSoundPool: " + soundPoolReady);
            mSP.play(soundArray[soundArrayNumber],LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
        }

    }



}
