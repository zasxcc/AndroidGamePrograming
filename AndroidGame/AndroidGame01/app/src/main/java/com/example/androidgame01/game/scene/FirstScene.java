package com.example.androidgame01.game.scene;

import android.graphics.RectF;

import com.example.androidgame01.framework.main.GameScene;
import com.example.androidgame01.framework.main.GameTimer;
import com.example.androidgame01.framework.main.UiBridge;
import com.example.androidgame01.framework.obj.ScoreObject;
import com.example.androidgame01.framework.obj.ui.Button;
import com.example.androidgame01.game.obj.Ball;
import com.example.androidgame01.game.obj.Boss1;
import com.example.androidgame01.game.obj.Enemy1;
import com.example.androidgame01.game.obj.Player;
import com.example.androidgame01.game.obj.Slash;

import java.util.Random;

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
    private Enemy1 enemy1;
    private Boss1 boss1;
    private ScoreObject scoreObject;
    private GameTimer timer;
    private Button attack;
    private Button shield;
    private int attackCheckCount = 0;
    private int slashCount = 0;
    private boolean IsAttackcount = false;

    @Override
    protected int getLayerCount() {
        return Layer.COUNT.ordinal();
    }

    @Override
    public void update() {
        super.update();
//        Log.d(TAG, "Score: " + timer.getRawIndex());
        if (timer.done()) {
            scoreObject.add(100);
            timer.reset();
        }

        //실드 버튼 눌렀을때
        if(shield.pressed == true)
        {
            player.Guard();
            shield.capturing = false;
        }
        //실드 버튼 땟을떄
        else if(shield.pressed == false && shield.capturing == false)
        {
            player.idle();
            shield.capturing = true;
        }
        //어택 버튼 눌럿을때
        if(attack.pressed == true)
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
        if(enemy1.getX() - 200 < player.getX() && enemy1.bAttack == false)
        {
            enemy1.setAimState(Enemy1.AnimState.attack);
            enemy1.setMove(false);
        }

        //Slash 적 충돌
        if(slash != null){
            for(int i = 0; i<15; ++i)
            {
                if(slash[i].getX() > enemy1.getX())
                    slash[i].destroy();
            }
        }
    }

    @Override
    public void enter() {
        initObjects();
    }

    private void initObjects() {
        Random rand = new Random();
        int mdpi_100 = UiBridge.x(100);
        //공움직임
        for (int i = 0; i < 1; i++) {
            int dx = rand.nextInt(2 * mdpi_100) - 1 * mdpi_100;
            if (dx >= 0) dx++;
            int dy = rand.nextInt(2 * mdpi_100) - 1 * mdpi_100;
            if (dy >= 0) dy++;
            ball = new Ball(mdpi_100, mdpi_100, dx, dy);
            gameWorld.add(Layer.enemy.ordinal(), ball);
        }

        player = new Player(300,800);
        enemy1 = new Enemy1(1000, 800, -50, 0);
        //boss1 = new Boss1(1200, 800, -80, 0);

        gameWorld.add(Layer.player.ordinal(), player);
        gameWorld.add(Layer.enemy.ordinal(), enemy1);
        //gameWorld.add(Layer.enemy.ordinal(), boss1);
        //gameWorld.add(Layer.bg.ordinal(), new CityBackground());
        int screenWidth = UiBridge.metrics.size.x;
        RectF rbox = new RectF(UiBridge.x(-52), UiBridge.y(20), UiBridge.x(-20), UiBridge.y(62));
        scoreObject = new ScoreObject(R.mipmap.number_64x84, rbox);
        gameWorld.add(Layer.ui.ordinal(), scoreObject);
        //BitmapObject title = new BitmapObject(UiBridge.metrics.center.x, UiBridge.y(160), -150, -150, R.mipmap.이미지);
        //gameWorld.add(Layer.ui.ordinal(), title);
        timer = new GameTimer(2, 1);

        int cx = UiBridge.metrics.center.x;
        int cy = UiBridge.metrics.center.y;

        attack = new Button(cx + 500, cy + 300, R.mipmap.attack_button, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        gameWorld.add(Layer.ui.ordinal(), attack);

        shield = new Button(cx + 750, cy + 300, R.mipmap.shield_button, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        gameWorld.add(Layer.ui.ordinal(), shield);

    }
}
