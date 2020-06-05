package com.example.androidgame01.game.obj;


import com.example.androidgame01.framework.main.GameTimer;
import com.example.androidgame01.framework.main.UiBridge;
import com.example.androidgame01.framework.obj.AnimObject;
import com.example.androidgame01.framework.res.bitmap.FrameAnimationBitmap;

import kr.ac.kpu.game.scgyong.gameskeleton.R;

public class Player extends AnimObject {
    private float dx, dy;
    public Player(float x, float y, float dx, float dy) {
        super(x, y, 0, 0, R.mipmap.idle, 8, 6);
        this.dx = dx;
        this.dy = dy;

    }

    public void Guard()
    {
        fab = new FrameAnimationBitmap(R.mipmap.def, 8, 4);

    }
    public void Attack(){
        fab = new FrameAnimationBitmap(R.mipmap.attack, 16, 4);
    }
    public void idle()
    {
        fab = new FrameAnimationBitmap(R.mipmap.idle, 8, 6);
    }


    @Override
    public float getRadius() {
        return this.width / 2;
    }

    public void update() {
        float seconds = GameTimer.getTimeDiffSeconds();
        x += dx * seconds;
        float radius = getRadius();
        int screenWidth = UiBridge.metrics.size.x;
        int screenHeight = UiBridge.metrics.size.y;
//        Log.d(TAG, "dx=" + dx + " nanos=" + nanos + " x=" + x);
        if (dx > 0 && x > screenWidth - radius || dx < 0 && x < radius) {
            dx *= -1;
        }
        y += dy * seconds;
        if (dy > 0 && y > screenHeight - radius || dy < 0 && y < radius) {
            dy *= -1;
        }
    }
}
