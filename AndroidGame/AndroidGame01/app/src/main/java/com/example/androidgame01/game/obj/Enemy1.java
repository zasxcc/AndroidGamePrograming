package com.example.androidgame01.game.obj;


import android.graphics.Canvas;

import com.example.androidgame01.framework.main.GameTimer;
import com.example.androidgame01.framework.main.UiBridge;
import com.example.androidgame01.framework.obj.AnimObject;
import com.example.androidgame01.framework.res.bitmap.FrameAnimationBitmap;

import kr.ac.kpu.game.scgyong.gameskeleton.R;

public class Enemy1 extends AnimObject {
    private float dx, dy;

    private FrameAnimationBitmap fabNormal;
    private FrameAnimationBitmap fabAttack;
    private FrameAnimationBitmap fabIdle;
    public AnimState state;
    public int HP = 100;

    public Enemy1(float x, float y, float dx, float dy) {
        super(x, y, 0, 0, R.mipmap.monster1_move, 8, 6);
        this.dx = dx;
        this.dy = dy;
        state = AnimState.normal;
        fabNormal = new FrameAnimationBitmap(R.mipmap.monster1_move, 10, 0);
        fabAttack = new FrameAnimationBitmap(R.mipmap.monster1_attack, 4, 7);
        fabIdle = new FrameAnimationBitmap(R.mipmap.monster1_idle, 10, 8);
    }
    @Override
    public void calculateDamage(int damage)
    {
        HP = HP-damage;
    }

    @Override
    public void enemyDeath()
    {
        //죽는거 확인을 위한 임시 코드
        this.x = 900;
        this.y = 900;
    }

    @Override
    public void setAimState(AnimState state) {
        this.state = state;

        if (this.state == AnimState.normal) {
            fab = fabNormal;
        }
        else if(this.state == AnimState.attack){
            bAttack = true;
            isAttackFrame = true;
            fab = fabAttack;
            fab.reset();
        }
        else if(this.state == AnimState.idle) {
            fab = fabIdle;
        }
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
    public float getRadius() {
        return this.width / 2;
    }

    public void update() {
        if (bMove) {
            {
                float seconds = GameTimer.getTimeDiffSeconds();
                x += dx * seconds;
            }
        }
        if(bAttack == true)
        {
            if(this.state == AnimState.idle)
            {
                attackDelay--;
            }

            if(fab.done())
            {
                setAimState(AnimState.idle);
                isAttackFrame = false;
                attackFrame = 0;
            }

            if(attackDelay <= 0)
            {
                setAimState(AnimState.attack);
                isAttackFrame = true;
                attackDelay = 100;
            }
        }

        if(isAttackFrame)
        {
            attackFrame++;
        }

        if(HP <= 0)
        {
            enemyDeath();
        }
    }
}