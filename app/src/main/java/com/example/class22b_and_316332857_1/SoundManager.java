package com.example.class22b_and_316332857_1;

import android.content.ContextWrapper;
import android.media.MediaPlayer;

public class SoundManager {

    private MediaPlayer mp;

    public SoundManager() {
        this.mp = new MediaPlayer();
    }


    public MediaPlayer getMp() {
        return this.mp;
    }

    public void setMpAndPlay(ContextWrapper cw, int sample) {
        this.mp = MediaPlayer.create(cw,sample);
        this.mp.start();
    }


}
