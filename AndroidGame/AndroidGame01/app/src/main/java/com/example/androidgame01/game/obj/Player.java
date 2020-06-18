package com.example.androidgame01.game.obj;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.androidgame01.framework.iface.Touchable;
import com.example.androidgame01.framework.main.UiBridge;
import com.example.androidgame01.framework.obj.AnimObject;
import com.example.androidgame01.framework.obj.ScoreObject;
import com.example.androidgame01.framework.res.bitmap.FrameAnimationBitmap;
import com.example.androidgame01.game.scene.FirstScene;

import kr.ac.kpu.game.scgyong.gameskeleton.R;

public class Player extends AnimObject implements Touchable {
    private float dx, dy;

    private FrameAnimationBitmap fabNormal;
    private FrameAnimationBitmap fabAttack;
    private FrameAnimationBitmap fabDeath;
    private static final float GRAVITY_SPEED = 1000;
    private static final float JUMP_POWER = -500;
    private boolean jumping;
    private float speed;
    private float base;
    public int HP = 70;
    public boolean isDeath = false;


    public Player(float x, float y) {
        super(x, y, 0, 0, R.mipmap.idle, 8, 6);
        base = y;

        fabNormal = new FrameAnimationBitmap(R.mipmap.idle, 8, 6);

        fabAttack = new FrameAnimationBitmap(R.mipmap.attack, 30, 6);
        fabDeath = new FrameAnimationBitmap(R.mipmap.die, 8, 9);

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

    public void PerfectGuard()
    {
        fab = new FrameAnimationBitmap(R.mipmap.perdef, 8, 0);
    }

    public void Attack(){
        fab = fabAttack;
    }
    public void idle()
    {
        fab = fabNormal;
    }


    @Override
    public float getRadius() {
        return this.width / 2;
    }

    public void update() {
//        if(jumping)
//        {
//            setAimState(AnimState.attack);
//            float timeDiffeSeconds = GameTimer.getTimeDiffSeconds();
//            y += speed * timeDiffeSeconds;
//            speed += GRAVITY_SPEED * timeDiffeSeconds;
//            if(y >= base)
//            {
//                jumping = false;
//                setAimState(AnimState.normal);
//                y = base;
//            }
//        }
        if(fab.done() && fab == fabDeath)
        {
            this.x = -10000;
        }
    }

    public void calculateDamage(int damage)
    {
        HP = HP-damage;
        if(HP <= 0)
            isDeath = true;

        if(isDeath){
            playerDeath();
            isDeath = false;
        }
    }
    public void playerDeath()
    {
        //여기다 죽으면 어떻게될지 코드


        fab = fabDeath;
        fab.reset();
    }

    public void draw(Canvas canvas) {
        float width = UiBridge.x(fab.getWidth());
        float height = UiBridge.y(fab.getHeight());

        float halfWidth = width / 2;
        float halfHeight = height / 2;
        dstRect.left = x - halfWidth;
        dstRect.top = y - halfHeight;
        dstRect.right = x + halfWidth;
        dstRect.bottom = y + halfHeight;
        fab.draw(canvas, dstRect, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
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
