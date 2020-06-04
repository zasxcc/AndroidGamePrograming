package com.example.androidgame01.ui.activity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidgame01.framework.main.UiBridge;
import com.example.androidgame01.framework.view.GameView;
import com.example.androidgame01.game.scene.FirstScene;

public class GameActivity extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UiBridge.setActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));

        new FirstScene().run();
    }
}
