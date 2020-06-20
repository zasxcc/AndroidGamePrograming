package com.example.androidgame01.ui.activity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidgame01.framework.main.UiBridge;
import com.example.androidgame01.framework.res.sound.SoundEffects;
import com.example.androidgame01.framework.view.GameView;
import com.example.androidgame01.game.scene.FirstScene;

import kr.ac.kpu.game.scgyong.gameskeleton.R;

public class GameActivity extends AppCompatActivity {
    private static MediaPlayer mp;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UiBridge.setActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));

        SoundEffects se = SoundEffects.get();
        se.loadAll(this);

        mp = MediaPlayer.create(this, R.raw.bgm);
        mp.setLooping(true);
        mp.start();

        new FirstScene().run();
    }

}
