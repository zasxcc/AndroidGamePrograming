package com.example.androidgame01.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidgame01.framework.res.sound.SoundEffects;

import kr.ac.kpu.game.scgyong.gameskeleton.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onBtnStart(View view) {
        startActivity(new Intent(this, GameActivity.class));
    }
}
