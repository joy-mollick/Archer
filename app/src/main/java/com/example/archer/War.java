package com.example.archer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class War extends AppCompatActivity {

    private WarActivity gameView;
    private Handler handler = new Handler();
    private final static long Interval = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new WarActivity(this);
        /// We filled this activity layout with gameview in substitution of activity_main
        setContentView(gameView);

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
                        Random rand = new Random(); //instance of random class
                        int upperbound = 2;
                        //generate random values from 0-24
                        int int_random = rand.nextInt(upperbound);
                        if(int_random==0)
                            gameView.invalidate();
                        else {gameView.invalidate();}
                    }
                });
            }
        }, 0, Interval);
    }
}