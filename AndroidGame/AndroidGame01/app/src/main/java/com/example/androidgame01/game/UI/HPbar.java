package com.example.androidgame01.game.UI;


import android.graphics.Canvas;

import com.example.androidgame01.framework.main.UiBridge;
import com.example.androidgame01.framework.obj.AnimObject;
import com.example.androidgame01.framework.res.bitmap.FrameAnimationBitmap;

import kr.ac.kpu.game.scgyong.gameskeleton.R;

public class HPbar extends AnimObject {
    private float dx, dy;

    private FrameAnimationBitmap fabHP0;
    private FrameAnimationBitmap fabHP1;
    private FrameAnimationBitmap fabHP2;
    private FrameAnimationBitmap fabHP3;
    private FrameAnimationBitmap fabHP4;
    private FrameAnimationBitmap fabHP5;
    private FrameAnimationBitmap fabHP6;
    private FrameAnimationBitmap fabHP7;
    public int hpstate;

    public HPbar(float x, float y, float dx, float dy) {
        super(x, y, 0, 0, R.mipmap.hpbar0, 1, 1);
        hpstate = 70;
        fabHP0 = new FrameAnimationBitmap(R.mipmap.hpbar0, 1, 1);
        fabHP1 = new FrameAnimationBitmap(R.mipmap.hpbar1, 1, 1);
        fabHP2 = new FrameAnimationBitmap(R.mipmap.hpbar2, 1, 1);
        fabHP3 = new FrameAnimationBitmap(R.mipmap.hpbar3, 1, 1);
        fabHP4 = new FrameAnimationBitmap(R.mipmap.hpbar4, 1, 1);
        fabHP5 = new FrameAnimationBitmap(R.mipmap.hpbar5, 1, 1);
        fabHP6 = new FrameAnimationBitmap(R.mipmap.hpbar6, 1, 1);
        fabHP7 = new FrameAnimationBitmap(R.mipmap.hpbar7, 1, 1);
    }

    @Override
    public float getRadius() {
        return this.width / 2;
    }

    public void update() {
        if (hpstate == 70)
            fab = fabHP0;
        else if(hpstate == 60)
            fab = fabHP1;
        else if(hpstate == 50)
            fab = fabHP2;
        else if(hpstate == 40)
            fab = fabHP3;
        else if(hpstate == 30)
            fab = fabHP4;
        else if(hpstate == 20)
            fab = fabHP5;
        else if(hpstate == 10)
            fab = fabHP6;
        else if(hpstate == 0)
            fab = fabHP7;
    }
    public void setHPstate(int state)
    {
        hpstate = state;
    }

    @Override
    public void draw(Canvas canvas) {
        //드로우 할 때 dstRect를 다르게 그리도록 해라
        float width = UiBridge.x(fab.getWidth());
        float height = UiBridge.y(fab.getHeight());

        dstRect.left = x - width + 900;
        dstRect.top = y - height + 130;
        dstRect.right = x + width;
        dstRect.bottom = y + height - 130;
        fab.draw(canvas, dstRect, null);
    }
}