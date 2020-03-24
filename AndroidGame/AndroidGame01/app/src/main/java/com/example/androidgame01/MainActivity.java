package com.example.androidgame01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    //CamelCase
    //상수 : 전부 대문자
    //Upper : 함수 이름
    //Lower : 변수 이름
    //어떤 폴더에 넣냐에 따라 크기가 달라진다. (mipmap_hdpi, mipmap_mdpi 등)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Message from Oncreat()");
        TextView tv = findViewById(R.id.textView3);
        tv.setText("Launched");
    }

    public void onBtnFirst(View v){
        TextView tv = findViewById(R.id.textViewMessage);
        tv.setText("First Button Pressed");

        ImageView iv = findViewById(R.id.catImageView);
        iv.setImageResource(R.mipmap.cat);
    }

    public void onBtnSecond(View view) {
        ImageView iv = findViewById(R.id.catImageView);
        iv.setImageResource(R.mipmap.cats);

        Random random = new Random();
        final int value = random.nextInt(100) + 1; //1~100
        final TextView tv = findViewById(R.id.textViewMessage);
        tv.setText("Random number : " + value);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv.setText("Timer" + (value + 100));
            }
        }, 1000);
    }

    public void onBtnThird(View view) {
        TextView tv = findViewById(R.id.textView4);
        int count = 0;
        try{
            count = Integer.parseInt((String)tv.getText());
        } catch(Exception e){

        }
        count++;
        tv.setText(String.valueOf(count));

        new AlertDialog.Builder(this).
                setTitle("Hello").
                setMessage("World").
                setPositiveButton("haha", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tv = findViewById(R.id.textViewMessage);
                        tv.setText("hahaha dialog button pressed");
                }
        });

    }
}
