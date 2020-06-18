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
    private FrameAnimationBitmap fabDeath;
    public AnimState state;
    public int HP = 300;

    public Enemy3(float x, float y, float dx, float dy) {
        super(x, y, 0, 0, R.mipmap.monster3_move, 16, 8);
        this.dx = dx;
        this.dy = dy;
        state = AnimState.normal;
        fabNormal = new FrameAnimationBitmap(R.mipmap.monster3_move, 16, 8);
        fabAttack = new FrameAnimationBitmap(R.mipmap.monster3_attack2, 4, 11);
        fabIdle = new FrameAnimationBitmap(R.mipmap.monster3_idle, 14, 7);
        fabDeath = new FrameAnimationBitmap(R.mipmap.monster3_dead, 12, 6);
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
        if(respawnCount == 200)
        {
            initState();
            setAimState(AnimState.normal);
            isDeath = false;
            respawnCount = 0;
            this.x = 1300;
            this.y = 800;
            this.dx = -400;
            this.HP = 200;
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
        else if(this.state == AnimState.death)
        {
            fab = fabDeath;
            fab.reset();
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

        if(HP <= 0 && isDeath == false)
        {
            if(isScoreAdd == false)
            {
                isScoreAdd = true;
            }
            isDeath = true;
            setAimState(AnimState.death);
            this.dx = 0;

        }
        if(respawnCount > 9)
        {
            isScoreAdd = false;
        }
        if(isDeath)
        {
            if(fab.done()){
                this.x = 11500;
                this.y = -500;
            }
            enemyDeath();
        }
    }
}