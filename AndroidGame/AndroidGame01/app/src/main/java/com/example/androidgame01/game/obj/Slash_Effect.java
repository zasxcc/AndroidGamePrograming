package com.example.androidgame01.game.obj;


import com.example.androidgame01.framework.main.GameTimer;
import com.example.androidgame01.framework.obj.AnimObject;
import com.example.androidgame01.framework.res.bitmap.FrameAnimationBitmap;

import kr.ac.kpu.game.scgyong.gameskeleton.R;



public class Slash_Effect extends AnimObject {
    private float dx, dy;
    private boolean fabStart = false;
    private FrameAnimationBitmap fabSlash;
    public Slash_Effect(float x, float y, float dx, float dy) {
        super(x, y, 0, 0, R.mipmap.slasheffect, 20, 5);
        fabSlash = new FrameAnimationBitmap(R.mipmap.slasheffect, 20, 5);
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public float getRadius() {
        return this.width / 2;
    }

    public void update() {
        float seconds = GameTimer.getTimeDiffSeconds();
        x += dx * seconds;
        if(fabStart){
            fab = fabSlash;
            fab.reset();
            fabStart = false;
        }

        if(fab.done() && fabStart == false)
        {
            this.destroy();
        }
    }
    public void positionUpdate(float x, float y, float dx)
    {
        this.x = x;
        this.y = y;
        this.dx = dx;
    }
    public void destroy() {
        this.y = 9999;
        this.x = -9999;
        this.dx = 0;
    }
    public void setPosition(float x, float y)
    {
        this.x = x;
        this.y = y;
        fabStart = true;
    }
}
