package com.example.androidgame01.game.scene;

import android.graphics.RectF;

import com.example.androidgame01.framework.main.GameScene;
import com.example.androidgame01.framework.main.GameTimer;
import com.example.androidgame01.framework.main.UiBridge;
import com.example.androidgame01.framework.obj.AnimObject;
import com.example.androidgame01.framework.obj.BitmapObject;
import com.example.androidgame01.framework.obj.ScoreObject;
import com.example.androidgame01.framework.obj.ui.Button;
import com.example.androidgame01.game.UI.HPbar;
import com.example.androidgame01.game.obj.Boss1;
import com.example.androidgame01.game.obj.Boss2;
import com.example.androidgame01.game.obj.Enemy1;
import com.example.androidgame01.game.obj.Enemy2;
import com.example.androidgame01.game.obj.Enemy3;
import com.example.androidgame01.game.obj.Player;
import com.example.androidgame01.game.obj.RevengeSlash;
import com.example.androidgame01.game.obj.Slash;
import com.example.androidgame01.game.obj.Slash_Effect;

import kr.ac.kpu.game.scgyong.gameskeleton.R;

public class FirstScene extends GameScene {
    private static final String TAG = FirstScene.class.getSimpleName();
    public static FirstScene mContext;
    public enum Layer {
        bg, enemy, player, ui, COUNT,
    }
    private HPbar hPbar;
    private Slash[] slash = {
            new Slash(0,0,0,0), new Slash(0,0,0,0), new Slash(0,0,0,0),
            new Slash(0,0,0,0), new Slash(0,0,0,0), new Slash(0,0,0,0),
            new Slash(0,0,0,0), new Slash(0,0,0,0), new Slash(0,0,0,0),
            new Slash(0,0,0,0), new Slash(0,0,0,0), new Slash(0,0,0,0),
            new Slash(0,0,0,0), new Slash(0,0,0,0), new Slash(0,0,0,0)
    };
    private RevengeSlash[] revengeSlash = {
            new RevengeSlash(0,0,0,0), new RevengeSlash(0,0,0,0),
            new RevengeSlash(0,0,0,0), new RevengeSlash(0,0,0,0),
            new RevengeSlash(0,0,0,0), new RevengeSlash(0,0,0,0)
    };

    private Player player;
    private AnimObject[] enemy = {
            new Enemy1(113000, 800, -150, 0), new Enemy1(113000, 800, -150, 0), new Enemy1(113000, 800, -150, 0),
            new Enemy2(113000, 800, -100, 0), new Enemy2(113000, 800, -100, 0), new Enemy2(113000, 800, -100, 0),
            new Enemy3(113000, 800, -400, 0), new Enemy3(113000, 800, -400, 0), new Enemy3(113000, 800, -400, 0),
            new Boss1(113000, 800, -100, 0), new Boss2(113000, 800, -100, 0)
    };

    private Slash_Effect[] slash_effect = {
            new Slash_Effect(100, 300, 0, 0), new Slash_Effect(9999, 9999, 0, 0), new Slash_Effect(9999, 9999, 0, 0),
            new Slash_Effect(9999, 9999, 0, 0), new Slash_Effect(9999, 9999, 0, 0), new Slash_Effect(9999, 9999, 0, 0),
            new Slash_Effect(9999, 9999, 0, 0), new Slash_Effect(9999, 9999, 0, 0), new Slash_Effect(9999, 9999, 0, 0),
            new Slash_Effect(9999, 9999, 0, 0), new Slash_Effect(9999, 9999, 0, 0), new Slash_Effect(9999, 9999, 0, 0),
            new Slash_Effect(9999, 9999, 0, 0), new Slash_Effect(9999, 9999, 0, 0), new Slash_Effect(9999, 9999, 0, 0)
    };

    public static final String PREFS_NAME = "Prefs";
    public static final String PREF_KEY_HIGHSCORE = "highScore";
    private ScoreObject scoreObject;
    private GameTimer timer;
    private Button attack;
    private Button shield;
    private int attackCheckCount = 0;
    private int slashCount = 0;
    private int revengeSlashCount = 0;
    private boolean IsAttackcount = false;
    private boolean isPerfectGuard = true;

    boolean gameLevel_A = false;
    boolean gameLevel_B = false;
    boolean gameLevel_C = false;
    boolean gameLevel_D = false;
    boolean gameLevel_E = false;
    boolean gameLevel_F = false;
    boolean gameLevel_G = false;
    boolean bRevengeSlash = true;


    int ENEMY1_MINATTACKFRAME = 20;
    int ENEMY1_MAXATTACKFRAME = 40;
    int ENEMY1_ATTACKTRIGGER = 41;

    int ENEMY2_MINATTACKFRAME = 50;
    int ENEMY2_MAXATTACKFRAME = 65;
    int ENEMY2_ATTACKTRIGGER = 66;

    int ENEMY3_MINATTACKFRAME = 60;
    int ENEMY3_MAXATTACKFRAME = 75;
    int ENEMY3_ATTACKTRIGGER = 81;

    //임시로 숫자 대입해논거
    int BOSS1_MINATTACKFRAME = 50;
    int BOSS1_MAXATTACKFRAME = 70;
    int BOSS1_ATTACKTRIGGER = 71;

    int BOSS2_MINATTACKFRAME = 50;
    int BOSS2_MAXATTACKFRAME = 70;
    int BOSS2_ATTACKTRIGGER = 71;

    int slashEffect_Count = 0;

    @Override
    protected int getLayerCount() {
        return Layer.COUNT.ordinal();
    }

    @Override
    public void update() {
        super.update();
        if (timer.done()) {
            timer.reset();
        }


        //실드 버튼 눌렀을때
        //for 문돌리면 이상하게 안댐.. 그래서 일단 하드코딩으로 임시방편
        //적들이 공격하는 타이밍에 맞춰 실드를 누른다면
        if (shield.pressed == true && (
                (enemy[0].attackFrame > ENEMY1_MINATTACKFRAME && enemy[0].attackFrame < ENEMY1_MAXATTACKFRAME) || (enemy[1].attackFrame > ENEMY1_MINATTACKFRAME && enemy[1].attackFrame < ENEMY1_MAXATTACKFRAME) ||
                (enemy[2].attackFrame > ENEMY1_MINATTACKFRAME && enemy[2].attackFrame < ENEMY1_MAXATTACKFRAME) || (enemy[3].attackFrame > ENEMY2_MINATTACKFRAME && enemy[3].attackFrame < ENEMY2_MAXATTACKFRAME) ||
                (enemy[4].attackFrame > ENEMY2_MINATTACKFRAME && enemy[4].attackFrame < ENEMY2_MAXATTACKFRAME) || (enemy[5].attackFrame > ENEMY2_MINATTACKFRAME && enemy[5].attackFrame < ENEMY2_MAXATTACKFRAME) ||
                (enemy[6].attackFrame > ENEMY3_MINATTACKFRAME && enemy[6].attackFrame < ENEMY3_MAXATTACKFRAME) || (enemy[7].attackFrame > ENEMY3_MINATTACKFRAME && enemy[7].attackFrame < ENEMY3_MAXATTACKFRAME) ||
                (enemy[8].attackFrame > ENEMY3_MINATTACKFRAME && enemy[8].attackFrame < ENEMY3_MAXATTACKFRAME) || (enemy[9].attackFrame > BOSS1_MINATTACKFRAME && enemy[9].attackFrame < BOSS1_MAXATTACKFRAME) ||
                (enemy[10].attackFrame > BOSS2_MINATTACKFRAME && enemy[10].attackFrame < BOSS2_MAXATTACKFRAME))
                && isPerfectGuard == true){
            if(bRevengeSlash) {
                revengeSlashCount++;
                if (revengeSlashCount == 5)
                    revengeSlashCount = 0;
                revengeSlash[revengeSlashCount].positionUpdate(400, 800, 800);
                gameWorld.add(Layer.player.ordinal(), revengeSlash[revengeSlashCount]);
                bRevengeSlash = false;
            }
            player.PerfectGuard();
            shield.capturing = false;
        }
        else if (shield.pressed == false && (
                (enemy[0].attackFrame == ENEMY1_ATTACKTRIGGER && enemy[0].isDeath == false) || (enemy[1].attackFrame == ENEMY1_ATTACKTRIGGER && enemy[1].isDeath == false ) ||
                (enemy[2].attackFrame == ENEMY1_ATTACKTRIGGER && enemy[2].isDeath == false) || (enemy[3].attackFrame == ENEMY2_ATTACKTRIGGER && enemy[3].isDeath == false) ||
                (enemy[4].attackFrame == ENEMY2_ATTACKTRIGGER && enemy[4].isDeath == false) || (enemy[5].attackFrame == ENEMY2_ATTACKTRIGGER && enemy[5].isDeath == false) ||
                (enemy[6].attackFrame == ENEMY3_ATTACKTRIGGER && enemy[6].isDeath == false) || (enemy[7].attackFrame == ENEMY3_ATTACKTRIGGER && enemy[7].isDeath == false) ||
                (enemy[8].attackFrame == ENEMY3_ATTACKTRIGGER && enemy[8].isDeath == false) || (enemy[9].attackFrame == BOSS1_ATTACKTRIGGER && enemy[9].isDeath == false) ||
                (enemy[10].attackFrame == BOSS2_ATTACKTRIGGER && enemy[10].isDeath == false))
        ) {
            player.calculateDamage(10);
            hPbar.setHPstate(player.HP);
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
            bRevengeSlash = true;
        }


        //어택 버튼 눌럿을때
        if(attack.pressed == true && IsAttackcount == false) {
            player.Attack();

            if(attackCheckCount == 0) {
                slashCount++;
                if(slashCount == 14)
                    slashCount = 0;
                slash[slashCount].positionUpdate(400, 800, 800);
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
        for(int i = 0; i<11; ++i) {
            if (enemy[i].getX() - 200 < player.getX() && enemy[i].bAttack == false) {
                enemy[i].setAimState(AnimObject.AnimState.attack);
                enemy[i].setMove(false);
            }
        }

        //Slash 적 충돌
        for(int i = 0; i<15; ++i) {
            if (slash != null) {
                for (int j = 0; j < 11; ++j) {
                    if (slash[i].getX() > enemy[j].getX() && slash[i].getX() < enemy[j].getX() + 100) {
                        slash_effect[slashEffect_Count].setPosition(slash[i].getX(), slash[i].getY());
                        slash[i].destroy();
                        enemy[j].calculateDamage(50);
                        slashEffect_Count++;
                        if(slashEffect_Count == 14)
                        {
                            slashEffect_Count = 0;
                        }
                    }
                }
            }
        }

        //RevengeSlash 적 충돌
        for(int i = 0; i<6; ++i) {
            if (revengeSlash != null) {
                for (int j = 0; j < 11; ++j) {
                    if(revengeSlash[i].getX() > enemy[j].getX() && revengeSlash[i].getX() < enemy[j].getX() + 300 && enemy[j].bDamagedRevengeSlash == false) {
                        enemy[j].calculateDamage(100);
                        enemy[j].bDamagedRevengeSlash = true;
                    }
                }
            }
        }

        //게임 난이도 조절
        if(scoreObject.getScore() > 100 && gameLevel_A == false){
            gameLevel_A = true;
            enemy[1].positionUpdate(1300, 800);
            gameWorld.add(Layer.enemy.ordinal(), enemy[1]);
        }
        else if(scoreObject.getScore() > 300 && gameLevel_B == false){
            gameLevel_B = true;
            enemy[3].positionUpdate(1300, 800);
            gameWorld.add(Layer.enemy.ordinal(), enemy[3]);
        }
        else if(scoreObject.getScore() > 600 && gameLevel_C == false){
            gameLevel_C = true;
            enemy[6].positionUpdate(1300, 800);
            gameWorld.add(Layer.enemy.ordinal(), enemy[6]);
        }
        else if(scoreObject.getScore() > 1000 && gameLevel_D == false){
            gameLevel_D = true;
            enemy[2].positionUpdate(1300, 800);
            gameWorld.add(Layer.enemy.ordinal(), enemy[2]);
            enemy[9].positionUpdate(1300, 800);
            gameWorld.add(Layer.enemy.ordinal(), enemy[9]);
        }
        else if(scoreObject.getScore() > 1500 && gameLevel_E == false){
            gameLevel_E = true;
            enemy[4].positionUpdate(1300, 800);
            gameWorld.add(Layer.enemy.ordinal(), enemy[4]);
        }
        else if(scoreObject.getScore() > 2000 && gameLevel_F == false){
            gameLevel_F = true;
            enemy[5].positionUpdate(1300, 800);
            gameWorld.add(Layer.enemy.ordinal(), enemy[5]);
            enemy[10].positionUpdate(1300, 800);
            gameWorld.add(Layer.enemy.ordinal(), enemy[10]);
        }
        else if(scoreObject.getScore() > 2500 && gameLevel_G == false){
            gameLevel_G = true;
            enemy[7].positionUpdate(1300, 800);
            gameWorld.add(Layer.enemy.ordinal(), enemy[7]);
        }


        for(int i = 0; i<11; i++)
        {
            if(enemy[i].isScoreAdd == true)
            {
                scoreObject.add(10);
            }
        }
    }

    public void endGame() {
        ScoreScene scoreScene = new ScoreScene();
        scoreScene.score = scoreObject.getScore();
        scoreScene.run();

//        int score = scoreObject.getScore();
//
//        SharedPreferences prefs = view.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        int highScore = prefs.getInt(PREF_KEY_HIGHSCORE, 0);
//        if (score > highScore) {
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putInt(PREF_KEY_HIGHSCORE, score);
//            editor.commit();
//        }
    }


    @Override
    public void enter() {
        initObjects();
    }

    private void initObjects() {
        mContext = this;

        player = new Player(300,800);
        hPbar = new HPbar(300, 100,0,0);
        gameWorld.add(Layer.player.ordinal(), hPbar);
        gameWorld.add(Layer.player.ordinal(), player);
        enemy[0].positionUpdate(1300, 800);
        gameWorld.add(Layer.enemy.ordinal(), enemy[0]);

        for(int i = 0; i<15;++i) {
            gameWorld.add(Layer.player.ordinal(), slash_effect[i]);
        }
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

//        SharedPreferences prefs = view.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        int highScore = prefs.getInt(PREF_KEY_HIGHSCORE, 0);
//        highScoreObject.setScore(highScore);

    }
}
