package com.example.androidgame01.game.obj;


import android.util.Log;
import android.view.MotionEvent;

import com.example.androidgame01.framework.iface.Touchable;
import com.example.androidgame01.framework.main.GameTimer;
import com.example.androidgame01.framework.main.UiBridge;
import com.example.androidgame01.framework.obj.AnimObject;
import com.example.androidgame01.framework.res.bitmap.FrameAnimationBitmap;

import kr.ac.kpu.game.scgyong.gameskeleton.R;

public class Player extends AnimObject implements Touchable {
    private float dx, dy;

    private FrameAnimationBitmap fabNormal;
    private FrameAnimationBitmap fabAttack;
    private static final float GRAVITY_SPEED = 1000;
    private static final float JUMP_POWER = -500;
    private boolean jumping;
    private float speed;
    private float base;

    public Player(float x, float y) {
        super(x, y, 0, 0, R.mipmap.ryu, 8, 0);
        base = y;

        fabNormal = new FrameAnimationBitmap(R.mipmap.ryu, 10, 0);
        fabAttack = new FrameAnimationBitmap(R.mipmap.ryu_1, 10, 5);
    }

    public enum AnimState{
        normal, attack
    }
    public void setAimState(AnimState state)
    {
        if(state == AnimState.normal){
            fab = fabNormal;
        }
        else
        {
            fab = fabAttack;
        }

    }

    public void Guard()
    {
        fab = new FrameAnimationBitmap(R.mipmap.def, 8, 4);

    }
    public void Attack(){
        fab = new FrameAnimationBitmap(R.mipmap.attack, 30, 6);
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
//        float seconds = GameTimer.getTimeDiffSeconds();
//        x += dx * seconds;
//        float radius = getRadius();
//        int screenWidth = UiBridge.metrics.size.x;
//        int screenHeight = UiBridge.metrics.size.y;

        if(jumping)
        {
            setAimState(AnimState.attack);
            float timeDiffeSeconds = GameTimer.getTimeDiffSeconds();
            y += speed * timeDiffeSeconds;
            speed += GRAVITY_SPEED * timeDiffeSeconds;
            if(y >= base)
            {
                jumping = false;
                setAimState(AnimState.normal);
                y = base;
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Log.d("asd","asd");
        if(e.getAction() != MotionEvent.ACTION_DOWN)
        {
            return false;
        }
        float tx = e.getX();
        if(tx < UiBridge.metrics.center.x)
        {
            //jump
            if(!jumping) {
                jumping = true;
                speed = JUMP_POWER;
            }
        }
        else
        {
            //slide
        }
        return false;
    }
}
