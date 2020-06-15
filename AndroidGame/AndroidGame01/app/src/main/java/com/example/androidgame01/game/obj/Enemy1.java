package com.example.androidgame01.game.obj;


import com.example.androidgame01.framework.main.GameTimer;
import com.example.androidgame01.framework.obj.AnimObject;
import com.example.androidgame01.framework.res.bitmap.FrameAnimationBitmap;

import kr.ac.kpu.game.scgyong.gameskeleton.R;

public class Enemy1 extends AnimObject {
    private float dx, dy;
    private boolean bMove = true;
    private FrameAnimationBitmap fabNormal;
    private FrameAnimationBitmap fabAttack;
    private FrameAnimationBitmap fabIdle;
    private AnimState state;
    private int attackDelay = 100;
    public boolean bAttack = false;

    public Enemy1(float x, float y, float dx, float dy) {
        super(x, y, 0, 0, R.mipmap.monster1_move, 8, 0);
        this.dx = dx;
        this.dy = dy;
        state = AnimState.normal;
        fabNormal = new FrameAnimationBitmap(R.mipmap.monster1_move, 10, 6);
        fabAttack = new FrameAnimationBitmap(R.mipmap.monster1_attack, 4, 7);
        fabIdle = new FrameAnimationBitmap(R.mipmap.monster1_idle, 10, 0);
    }

    public enum AnimState {
        normal, attack, idle
    }

    public void setAimState(AnimState state) {
        this.state = state;

        if (this.state == AnimState.normal) {
            fab = fabNormal;
        }
        else if(this.state == AnimState.attack){
            bAttack = true;
            fab = fabAttack;
            fab.reset();
        }
        else if(this.state == AnimState.idle) {
            fab = fabIdle;
        }
    }

    public void setMove(boolean move) {
        bMove = move;

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
            }

            if(attackDelay <= 0)
            {
                setAimState(AnimState.attack);
                attackDelay = 100;
            }

        }
    }
}