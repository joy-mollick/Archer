package com.example.archer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity
{
    private WarActivity gameView;
    private FlyingFishView gameView1;
    public static int score;
    public static int fitns;
    private Handler handler = new Handler();
    private final static long Interval = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new WarActivity(this);
        gameView1=new FlyingFishView(this);
        /// We filled this activity layout with gameview in substitution of activity_main
        setContentView(gameView1);

        /// this is for doing everything slowly
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            {
                handler.post(new Runnable() {
                    @Override
                    public void run()
                    {
                            gameView1.invalidate();
                    }
                });
            }
        }, 0, Interval);
    }
}