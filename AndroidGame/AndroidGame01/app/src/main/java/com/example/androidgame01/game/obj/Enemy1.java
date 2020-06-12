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
    private static final float GRAVITY_SPEED = 1000;
    private static final float JUMP_POWER = -500;
    private boolean jumping;
    private float speed;
    private float base;

    public Enemy1(float x, float y, float dx, float dy) {
        super(x, y, 0, 0, R.mipmap.monster1_move, 8, 0);
        base = y;
        this.dx = dx;
        this.dy = dy;
        fabNormal = new FrameAnimationBitmap(R.mipmap.monster1_move, 10, 6);
        fabAttack = new FrameAnimationBitmap(R.mipmap.monster1_attack, 4, 7);
        fabIdle = new FrameAnimationBitmap(R.mipmap.monster1_attack, 10, 0);
    }

    public enum AnimState {
        normal, attack, idle
    }

    public void setAimState(AnimState state) {
        if (state == AnimState.normal) {
            fab = fabNormal;
        }
        else if(state == AnimState.attack){
            fab = fabAttack;
        }
        else if(state == AnimState.idle) {
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

    }
}