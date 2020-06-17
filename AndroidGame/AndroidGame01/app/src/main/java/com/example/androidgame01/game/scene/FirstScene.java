package com.example.androidgame01.game.scene;

import android.graphics.RectF;
import android.util.Log;

import com.example.androidgame01.framework.main.GameScene;
import com.example.androidgame01.framework.main.GameTimer;
import com.example.androidgame01.framework.main.UiBridge;
import com.example.androidgame01.framework.obj.AnimObject;
import com.example.androidgame01.framework.obj.BitmapObject;
import com.example.androidgame01.framework.obj.ScoreObject;
import com.example.androidgame01.framework.obj.ui.Button;
import com.example.androidgame01.game.obj.Ball;
import com.example.androidgame01.game.obj.Boss1;
import com.example.androidgame01.game.obj.Enemy1;
import com.example.androidgame01.game.obj.Enemy2;
import com.example.androidgame01.game.obj.Enemy3;
import com.example.androidgame01.game.obj.Player;
import com.example.androidgame01.game.obj.Slash;

import kr.ac.kpu.game.scgyong.gameskeleton.R;

public class FirstScene extends GameScene {
    private static final String TAG = FirstScene.class.getSimpleName();

    public enum Layer {
        bg, enemy, player, ui, COUNT,
    }
    private Ball ball;
    private Slash[] slash = {
            new Slash(0,0,0,0), new Slash(0,0,0,0), new Slash(0,0,0,0),
            new Slash(0,0,0,0), new Slash(0,0,0,0), new Slash(0,0,0,0),
            new Slash(0,0,0,0), new Slash(0,0,0,0), new Slash(0,0,0,0),
            new Slash(0,0,0,0), new Slash(0,0,0,0), new Slash(0,0,0,0),
            new Slash(0,0,0,0), new Slash(0,0,0,0), new Slash(0,0,0,0)
    };
    private Player player;
    private AnimObject[] enemy = {
            new Enemy1(1000, 800, -100, 0), new Enemy1(1400, 800, -100, 0), new Enemy1(1000, 800, -100, 0),
            new Enemy2(1000, 800, -100, 0), new Enemy2(1000, 800, -100, 0), new Enemy2(1000, 800, -100, 0),
            new Enemy3(1000, 800, -100, 0), new Enemy3(1000, 800, -100, 0), new Enemy3(1000, 800, -100, 0)
    };
    private Boss1 boss1;
    private ScoreObject scoreObject;
    private GameTimer timer;
    private Button attack;
    private Button shield;
    private int attackCheckCount = 0;
    private int slashCount = 0;
    private boolean IsAttackcount = false;
    private boolean isPerfectGuard = true;

    int ENEMY1_MINATTACKFRAME = 20;
    int ENEMY1_MAXATTACKFRAME = 50;
    int ENEMY1_ATTACKTRIGGER = 55;

    @Override
    protected int getLayerCount() {
        return Layer.COUNT.ordinal();
    }

    @Override
    public void update() {
        super.update();
//        Log.d(TAG, "Score: " + timer.getRawIndex());
        if (timer.done()) {
            //scoreObject.add(100);
            timer.reset();
        }


        //실드 버튼 눌렀을때
        //for 문돌리면 이상하게 안댐.. 그래서 일단 하드코딩으로 임시방편
        //적들이 공격하는 타이밍에 맞춰 실드를 누른다면
        if (shield.pressed == true && (
                (enemy[0].attackFrame > ENEMY1_MINATTACKFRAME && enemy[0].attackFrame < ENEMY1_MAXATTACKFRAME) || (enemy[1].attackFrame > ENEMY1_MINATTACKFRAME && enemy[1].attackFrame < ENEMY1_MAXATTACKFRAME) ||
                (enemy[2].attackFrame > ENEMY1_MINATTACKFRAME && enemy[2].attackFrame < ENEMY1_MAXATTACKFRAME) || (enemy[3].attackFrame > ENEMY1_MINATTACKFRAME && enemy[3].attackFrame < ENEMY1_MAXATTACKFRAME) ||
                (enemy[4].attackFrame > ENEMY1_MINATTACKFRAME && enemy[4].attackFrame < ENEMY1_MAXATTACKFRAME) || (enemy[5].attackFrame > ENEMY1_MINATTACKFRAME && enemy[5].attackFrame < ENEMY1_MAXATTACKFRAME) ||
                (enemy[6].attackFrame > ENEMY1_MINATTACKFRAME && enemy[6].attackFrame < ENEMY1_MAXATTACKFRAME) || (enemy[7].attackFrame > ENEMY1_MINATTACKFRAME && enemy[7].attackFrame < ENEMY1_MAXATTACKFRAME) ||
                (enemy[8].attackFrame > ENEMY1_MINATTACKFRAME && enemy[8].attackFrame < ENEMY1_MAXATTACKFRAME)) && isPerfectGuard == true) {
            player.PerfectGuard();
            for(int i = 0 ; i<9; ++i)
            {
                if(enemy[i].getX() - 400 < player.getX())
                {
                    enemy[i].calculateDamage(100);
                }
            }
            shield.capturing = false;
        }
        else if (shield.pressed == false && ((enemy[0].attackFrame == ENEMY1_ATTACKTRIGGER) || (enemy[1].attackFrame == ENEMY1_ATTACKTRIGGER) ||
                (enemy[2].attackFrame == ENEMY1_ATTACKTRIGGER) || (enemy[3].attackFrame == ENEMY1_ATTACKTRIGGER ) ||
                (enemy[4].attackFrame == ENEMY1_ATTACKTRIGGER) || (enemy[5].attackFrame == ENEMY1_ATTACKTRIGGER ) ||
                (enemy[6].attackFrame == ENEMY1_ATTACKTRIGGER) || (enemy[7].attackFrame == ENEMY1_ATTACKTRIGGER ) ||
                (enemy[8].attackFrame == ENEMY1_ATTACKTRIGGER) )) {
            player.calculateDamage(10);
            Log.d("asd","Player HP : " + player.HP);
        }
        //그냥 실드 눌럿을 경우
        else if (shield.pressed == true) {
            player.Guard();
            shield.capturing = false;
            isPerfectGuard = false;
        }
        //실드 버튼 땟을떄
        else if (shield.pressed == false && shield.capturing == false) {
            player.idle();
            shield.capturing = true;
            isPerfectGuard = true;
        }


        //어택 버튼 눌럿을때
        if(attack.pressed == true && IsAttackcount == false)
        {
            player.Attack();
            if(attackCheckCount == 0) {
                slashCount++;
                if(slashCount == 14)
                    slashCount = 0;
                slash[slashCount].positionUpdate(400, 800, 500);
                gameWorld.add(Layer.player.ordinal(), slash[slashCount]);
            }
            IsAttackcount = true;
            attackCheckCount = 0;
        }
        //어택 버튼 누르면 카운트 시작
        if(IsAttackcount) {

            attackCheckCount++;
            if (attackCheckCount == 10) {
                attackCheckCount = 0;
                IsAttackcount = false;
                player.idle();
            }
        }

        //플레이어 적 충돌
        for(int i = 0; i<9; ++i) {
            if (enemy[i].getX() - 200 < player.getX() && enemy[i].bAttack == false) {
                enemy[i].setAimState(AnimObject.AnimState.attack);
                enemy[i].setMove(false);
            }
        }

        for(int i = 0; i<15; ++i) {
            //Slash 적 충돌
            if (slash != null) {
                for (int j = 0; j < 9; ++j) {
                    if (slash[i].getX() > enemy[j].getX()) {
                        slash[i].destroy();
                        enemy[j].calculateDamage(50);

                    }
                }
            }
        }
        for(int i = 0; i<9; i++)
        {
            if(enemy[i].isScoreAdd == true)
            {
                scoreObject.add(10);
            }
        }



    }

    @Override
    public void enter() {
        initObjects();
    }

    private void initObjects() {
        //Random rand = new Random();
        int mdpi_100 = UiBridge.x(100);
        //공움직임
//        for (int i = 0; i < 1; i++) {
//            int dx = rand.nextInt(2 * mdpi_100) - 1 * mdpi_100;
//            if (dx >= 0) dx++;
//            int dy = rand.nextInt(2 * mdpi_100) - 1 * mdpi_100;
//            if (dy >= 0) dy++;
//            ball = new Ball(mdpi_100, mdpi_100, dx, dy);
//            gameWorld.add(Layer.enemy.ordinal(), ball);
//        }

        player = new Player(300,800);

        //boss1 = new Boss1(1200, 800, -80, 0);

        gameWorld.add(Layer.player.ordinal(), player);
        gameWorld.add(Layer.enemy.ordinal(), enemy[0]);
        //gameWorld.add(Layer.enemy.ordinal(), enemy[1]);
        gameWorld.add(Layer.enemy.ordinal(), enemy[3]);
       // gameWorld.add(Layer.enemy.ordinal(), enemy[3]);
        gameWorld.add(Layer.enemy.ordinal(), enemy[6]);

        BitmapObject title = new BitmapObject(UiBridge.metrics.center.x, UiBridge.y(100), -150, -180, R.mipmap.bg);
        gameWorld.add(Layer.bg.ordinal(), title);
        //gameWorld.add(Layer.bg.ordinal(), new CityBackground());
        int screenWidth = UiBridge.metrics.size.x;
        RectF rbox = new RectF(UiBridge.x(-52), UiBridge.y(20), UiBridge.x(-20), UiBridge.y(62));
        scoreObject = new ScoreObject(R.mipmap.number_64x84, rbox);
        gameWorld.add(Layer.ui.ordinal(), scoreObject);

        timer = new GameTimer(2, 1);

        int cx = UiBridge.metrics.center.x;
        int cy = UiBridge.metrics.center.y;

        attack = new Button(cx + 500, cy + 300, R.mipmap.attack_button, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        gameWorld.add(Layer.ui.ordinal(), attack);

        shield = new Button(cx + 750, cy + 300, R.mipmap.shield_button, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        gameWorld.add(Layer.ui.ordinal(), shield);

    }
}
