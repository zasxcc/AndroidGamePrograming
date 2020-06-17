package com.example.androidgame01.game.obj;


import com.example.androidgame01.framework.main.GameTimer;
import com.example.androidgame01.framework.obj.AnimObject;
import com.example.androidgame01.framework.res.bitmap.FrameAnimationBitmap;

import kr.ac.kpu.game.scgyong.gameskeleton.R;

public class Enemy1 extends AnimObject {
    private float dx, dy;

    private FrameAnimationBitmap fabNormal;
    private FrameAnimationBitmap fabAttack;
    private FrameAnimationBitmap fabIdle;
    private FrameAnimationBitmap fabDeath;
    public AnimState state;
    public int HP = 100;

    public Enemy1(float x, float y, float dx, float dy) {
        super(x, y, 0, 0, R.mipmap.monster1_move, 12, 6);
        this.dx = dx;
        this.dy = dy;
        state = AnimState.normal;
        fabNormal = new FrameAnimationBitmap(R.mipmap.monster1_move, 12, 6);
        fabAttack = new FrameAnimationBitmap(R.mipmap.monster1_attack, 14, 7);
        fabIdle = new FrameAnimationBitmap(R.mipmap.monster1_idle, 16, 8);
        fabDeath = new FrameAnimationBitmap(R.mipmap.monster1_dead, 20, 10);
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