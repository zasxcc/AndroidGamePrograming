package com.example.androidgame01.game.scene;

import android.graphics.RectF;

import com.example.androidgame01.framework.main.GameScene;
import com.example.androidgame01.framework.main.GameTimer;
import com.example.androidgame01.framework.main.UiBridge;
import com.example.androidgame01.framework.obj.ScoreObject;
import com.example.androidgame01.framework.obj.ui.Button;

import kr.ac.kpu.game.scgyong.gameskeleton.R;

public class ScoreScene extends GameScene {
    private static final String TAG = ScoreScene.class.getSimpleName();
    public static ScoreScene mContext;
    public enum Layer {
        bg, enemy, player, ui, COUNT,
    }

    public int score;


    public static final String PREFS_NAME = "Prefs";
    public static final String PREF_KEY_HIGHSCORE = "highScore";
    private ScoreObject scoreObject;
    private GameTimer timer;
    private Button attack;
    private Button shield;

    @Override
    protected int getLayerCount() {
        return Layer.COUNT.ordinal();
    }

    @Override
    public void update() {
        super.update();
        if(attack.pressed == true) {
            FirstScene firstScene = new FirstScene();
            firstScene.run();
        }

    }


    @Override
    public void enter() {
        initObjects();
    }

    private void initObjects() {
        mContext = this;

        RectF rbox = new RectF(UiBridge.x(-52), UiBridge.y(20), UiBridge.x(-20), UiBridge.y(62));
        scoreObject = new ScoreObject(R.mipmap.number_64x84, rbox);
        gameWorld.add(Layer.ui.ordinal(), scoreObject);

        int cx = UiBridge.metrics.center.x;
        int cy = UiBridge.metrics.center.y;

        attack = new Button(cx + 500, cy + 300, R.mipmap.attack_button, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        gameWorld.add(Layer.ui.ordinal(), attack);
    }
}
