package com.example.androidgame01.framework.res.sound;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

import kr.ac.kpu.game.scgyong.gameskeleton.R;

public class SoundEffects {
    private static final String TAG = SoundEffects.class.getSimpleName();
    private static SoundEffects singleton;
    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundIdMap = new HashMap<>();
    private static final int[] SOUND_IDS = {
            R.raw.slash, R.raw.bgm, R.raw.boss1_dead, R.raw.boss2_dead,
            R.raw.monster1_dead, R.raw.monster2_dead, R.raw.monster3_dead,
            R.raw.sword,
    };

    public static SoundEffects get() {
        if (singleton == null) {
            singleton = new SoundEffects();
        }
        return singleton;
    }
    private SoundEffects() {
        AudioAttributes audioAttributes;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            this.soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(3)
                    .build();
        } else {
            this.soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }
    }
    public void loadAll(Context context) {
        for (int resId: SOUND_IDS) {
            int soundId = soundPool.load(context, resId, 1);
            soundIdMap.put(resId, soundId);
        }
    }

    public int play(int resId) {
        int soundId = soundIdMap.get(resId);
        int streamId = soundPool.play(soundId, 1f, 1f, 1, 0, 1f);
        return streamId;
    }
}
