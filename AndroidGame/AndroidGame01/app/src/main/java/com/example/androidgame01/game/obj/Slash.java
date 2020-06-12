package com.example.androidgame01.game.obj;


import com.example.androidgame01.framework.main.GameTimer;
import com.example.androidgame01.framework.obj.AnimObject;

import kr.ac.kpu.game.scgyong.gameskeleton.R;


public class Slash extends AnimObject {
    private float dx, dy;
    public Slash(float x, float y, float dx, float dy) {
        super(x, y, 0, 0, R.mipmap.slash, 10, 1);
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public float getRadius() {
        return this.width / 4;
    }

    public void update() {
        float seconds = GameTimer.getTimeDiffSeconds();
        x += dx * seconds;
    }
    public void positionUpdate(float x, float y, float dx)
    {
        this.x = x;
        this.y = y;
        this.dx = dx;
    }
    public void destroy() {
        this.y = 9999;
        this.dx = 0;
    }
}
