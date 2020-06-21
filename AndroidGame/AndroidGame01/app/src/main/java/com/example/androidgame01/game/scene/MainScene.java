package com.example.androidgame01.game.scene;

import com.example.androidgame01.framework.main.GameScene;
import com.example.androidgame01.framework.main.UiBridge;
import com.example.androidgame01.framework.obj.BitmapObject;
import com.example.androidgame01.framework.obj.ui.Button;

import kr.ac.kpu.game.scgyong.gameskeleton.R;

public class MainScene extends GameScene {
    private static final String TAG = MainScene.class.getSimpleName();
    public static MainScene mContext;

    public enum Layer {
        bg, enemy, player, ui, COUNT,
    }

    private Button start;
    private Button exit;
    private Button logo;

    @Override
    protected int getLayerCount() {
        return Layer.COUNT.ordinal();
    }

    @Override
    public void update() {
        super.update();
        if(start.pressed == true) {
            FirstScene firstScene = new FirstScene();
            firstScene.run();
        }
        else if(exit.pressed == true) {
//            FirstScene firstScene = new FirstScene();
//            firstScene.run();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
//        Log.d(TAG, "score" + score);
    }


    @Override
    public void enter() {
        initObjects();
    }

    private void initObjects() {
        mContext = this;

        BitmapObject title = new BitmapObject(UiBridge.metrics.center.x, UiBridge.y(100), -150, -180, R.mipmap.bg);
        gameWorld.add(FirstScene.Layer.bg.ordinal(), title);

        BitmapObject titleLogo = new BitmapObject(UiBridge.metrics.center.x, UiBridge.y(100), -150, -180, R.mipmap.title);
        gameWorld.add(FirstScene.Layer.bg.ordinal(), titleLogo);

        int cx = UiBridge.metrics.center.x;
        int cy = UiBridge.metrics.center.y;


        start = new Button(cx - 200, cy + 300, R.mipmap.start_btn, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        gameWorld.add(Layer.ui.ordinal(), start);

        exit = new Button(cx + 200, cy + 300, R.mipmap.exit_btn, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        gameWorld.add(Layer.ui.ordinal(), exit);
    }
}
