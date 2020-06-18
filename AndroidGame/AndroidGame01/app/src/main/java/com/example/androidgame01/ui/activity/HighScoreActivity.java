package com.example.androidgame01.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidgame01.game.scene.FirstScene;

import kr.ac.kpu.game.scgyong.gameskeleton.R;


public class HighScoreActivity extends AppCompatActivity {

    private TextView highScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        highScoreTextView = findViewById(R.id.highScoreTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getSharedPreferences(FirstScene.PREFS_NAME, Context.MODE_PRIVATE);
        int highScore = prefs.getInt(FirstScene.PREF_KEY_HIGHSCORE, 0);
        highScoreTextView.setText(String.valueOf(highScore));
    }
}
