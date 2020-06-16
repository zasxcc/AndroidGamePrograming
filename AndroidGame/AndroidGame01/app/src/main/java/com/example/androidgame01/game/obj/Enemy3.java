package com.example.androidgame01.game.obj;


import com.example.androidgame01.framework.main.GameTimer;
import com.example.androidgame01.framework.obj.AnimObject;
import com.example.androidgame01.framework.res.bitmap.FrameAnimationBitmap;

import kr.ac.kpu.game.scgyong.gameskeleton.R;

public class Enemy3 extends AnimObject {
    private float dx, dy;

    private FrameAnimationBitmap fabNormal;
    private FrameAnimationBitmap fabAttack;
    private FrameAnimationBitmap fabIdle;
    public AnimState state;
    public int HP = 100;

    public Enemy3(float x, float y, float dx, float dy) {
        super(x, y, 0, 0, R.mipmap.monster3_move, 8, 8);
        this.dx = dx;
        this.dy = dy;
        state = AnimState.normal;
        fabNormal = new FrameAnimationBitmap(R.mipmap.monster3_move, 10, 8);
        fabAttack = new FrameAnimationBitmap(R.mipmap.monster3_attack1, 4, 9);
        fabIdle = new FrameAnimationBitmap(R.mipmap.monster3_idle, 10, 6);
    }
    @Override
    public void calculateDamage(int damage)
    {
        HP = HP-damage;
    }

    @Override
    public void enemyDeath()
    {
        respawnCount++;
        if(respawnCount == 100)
        {
            isDeath = false;
            initState();
            setAimState(AnimState.normal);
            respawnCount = 0;
            this.x = 1000;
            this.y = 800;
            this.dx = -100;
            this.HP = 100;
        }
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
            isDeath = true;
            this.x = 11500;
            this.y = -500;
            this.dx = 0;
        }

        if(isDeath)
        {
            enemyDeath();
        }
    }
}