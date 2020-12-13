package com.example.archer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class WarActivity extends View {

    private Paint scorePaint = new Paint();
    private Paint scorepant=new Paint();
    private Bitmap run_noyan[]=new Bitmap[6];
    private Bitmap walk_noyan[]=new Bitmap[7];
    private Bitmap horse_run[]=new Bitmap[4];
    private Bitmap archer[]=new Bitmap[4];
    private int tirondaj,ghor;

    private int walk_noyanX,walk_noyanY;
    private int run_noyanX,run_noyanY;
    private int horseX,horseY;
    private Bitmap arrw,arrw1,arrw2,arrw3;
    private int arrowX,arrowY;
    private int arrow1X,arrow1Y;
    private int arrow2X,arrow2Y;
    private int arrow3X,arrow3Y;
    private int arropX,arropY;
    private Bitmap fish,arrp;
    private int fishX,fishY;
    private int canvaswidth,canvasheight;
    private Bitmap backgrnd;
    private int scor;
    private int arcX,arcY;
    private int total=60;
    private int fitness=100;
    private int boiler=20;
    private int oppo=0;
    boolean touch=false,touch1=false,touch2=false,touch3=false;
    Random rand = new Random(); //instance of random class
    Paint yellowPaint =new Paint();

    public WarActivity(Context context)
    {
        super(context);

        ///noyan storage
        walk_noyan[0]= BitmapFactory.decodeResource(getResources(),R.drawable.walking_noyan_1);
        walk_noyan[1]=BitmapFactory.decodeResource(getResources(),R.drawable.walking_noyan_2);
        walk_noyan[2]=BitmapFactory.decodeResource(getResources(),R.drawable.walking_noyan_3);
        walk_noyan[3]=BitmapFactory.decodeResource(getResources(),R.drawable.walking_noyan_4);
        walk_noyan[4]=BitmapFactory.decodeResource(getResources(),R.drawable.walking_noyan_5);
        walk_noyan[5]=BitmapFactory.decodeResource(getResources(),R.drawable.walking_noyan_6);
        walk_noyan[6]=BitmapFactory.decodeResource(getResources(),R.drawable.wlaking_noyan_7);

        /// arrow of me
        arrw=BitmapFactory.decodeResource(getResources(),R.drawable.arrww);
        arrw1=BitmapFactory.decodeResource(getResources(),R.drawable.arr);
        arrw2=BitmapFactory.decodeResource(getResources(),R.drawable.arr);
        arrw3=BitmapFactory.decodeResource(getResources(),R.drawable.arr);

        /// arrow of opponent
        arrp=BitmapFactory.decodeResource(getResources(),R.drawable.arrow);

        /// archer
        archer[0]=BitmapFactory.decodeResource(getResources(),R.drawable.arc_1);
        archer[1]=BitmapFactory.decodeResource(getResources(),R.drawable.arc_2);
        archer[2]=BitmapFactory.decodeResource(getResources(),R.drawable.arc_3);
        archer[3]=BitmapFactory.decodeResource(getResources(),R.drawable.arc_4);

        /// horse run
        horse_run[0]=BitmapFactory.decodeResource(getResources(),R.drawable.horse_rid1);
        horse_run[1]=BitmapFactory.decodeResource(getResources(),R.drawable.horse_rid2);
        horse_run[2]=BitmapFactory.decodeResource(getResources(),R.drawable.horse_rid3);
        horse_run[3]=BitmapFactory.decodeResource(getResources(),R.drawable.horse_rid4);

        ///noyan run storage
        run_noyan[0]= BitmapFactory.decodeResource(getResources(),R.drawable.running_noyan_1);
        run_noyan[1]=BitmapFactory.decodeResource(getResources(),R.drawable.running_noyan_2);
        run_noyan[2]=BitmapFactory.decodeResource(getResources(),R.drawable.running_noyan_3);
        run_noyan[3]=BitmapFactory.decodeResource(getResources(),R.drawable.running_noyan_4);
        run_noyan[4]=BitmapFactory.decodeResource(getResources(),R.drawable.running_noyan_5);
        run_noyan[5]=BitmapFactory.decodeResource(getResources(),R.drawable.running_noyan_6);


        /// background
        backgrnd=BitmapFactory.decodeResource(getResources(),R.drawable.background);

        ///
        /// In which type the score will appear in the form of text
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(30);
        scorePaint.setTypeface(Typeface.DEFAULT);
        scorePaint.setAntiAlias(true);

        /// score card
        scorepant.setColor(Color.WHITE);
        scorepant.setTextSize(30);
        scorepant.setTypeface(Typeface.DEFAULT);
        scorepant.setAntiAlias(true);

        ///border area
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        /// Fish
        fish=BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if(total==0||boiler==0) {
            Intent gameover = new Intent(getContext(), GameOverActivity.class);
            //Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK
            /// finishAffinity();
            gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getContext().startActivity(gameover);
        }

        fishX=fish.getWidth();
        fishY=fish.getHeight();

        canvaswidth=canvas.getWidth();
        canvasheight=canvas.getHeight();

        canvas.drawBitmap(backgrnd,0,0,null);


        if(walk_noyanY>canvasheight)
        {
            walk_noyanY=fish.getHeight();
            int upperbound = canvaswidth-walk_noyan[0].getWidth();
            //generate random values from 0-24
            int int_random = rand.nextInt(upperbound);
            walk_noyanX=int_random;
        }
        walk_noyanY=walk_noyanY+5;
        if(scor<17){
        Noyan_generate(canvas);}

        else {walk_noyanY=canvasheight+1;walk_noyanX=canvasheight+1;}

        if(run_noyanY>canvasheight)
        {
            run_noyanY=fish.getHeight();
            while(true)
            {
                int upperbound = canvaswidth-walk_noyan[0].getWidth();
                int int_random = rand.nextInt(upperbound);
                if((int_random<=walk_noyanX-(arrw.getHeight()/2))||(int_random>=walk_noyanX+(arrw.getHeight()/2)))
                {
                    run_noyanX=int_random;
                    break;
                }
            }
        }
        run_noyanY=run_noyanY+7;
        if(scor>5){
        run_noyan_generate(canvas);}

        else {run_noyanY=canvasheight+100;run_noyanX=canvasheight+100;}

        /// horse

        if(horseY>canvasheight)
        {
            horseY=fish.getHeight();
                int upperbound = canvaswidth-walk_noyan[0].getWidth();
                int int_random = rand.nextInt(upperbound);
                horseX=int_random;
        }
        horseY=horseY+9;
        if(scor>=7&&scor<30) {
            horse_generate(canvas);
        }

        else {horseY=canvasheight+100;horseX=canvasheight+100;}

        int height=canvasheight/6;
        if(arcY>height)
        {
                arcY=0;
                int upperbound = canvaswidth-walk_noyan[0].getWidth();
                int int_random = rand.nextInt(upperbound);
                arcX=int_random;
        }
        arcY=arcY+3;
        if(scor>3&&tirondaj<10)
            archer_generate(height,canvas);

        else {arcY=canvasheight+100;arcX=canvasheight+100;}

        if(arropY>canvasheight)
        {
            arropY=arcY+archer[3].getHeight();
            arropX=arcX+archer[0].getHeight()-(archer[0].getHeight()/6);
            fitness-=10;
        }
        arropY+=19;
        canvas.drawBitmap(arrp,arropX,arropY,null);

        /// me throw 1
        if(touch)
        {
                total--;
                arrowY=canvasheight-arrw.getHeight();
                canvas.drawBitmap(arrw,arrowX,arrowY,null);
                touch=false;
        }

        else
        {
                arrowY=arrowY-19;
                canvas.drawBitmap(arrw,arrowX,arrowY,null);
        }

        /// me throw 2

        if(touch1)
        {
            total--;
            arrow1Y=canvasheight-arrw.getHeight();
            canvas.drawBitmap(arrw1,arrow1X,arrow1Y,null);
            touch1=false;
        }

        else
        {
            arrow1Y=arrow1Y-19;
            canvas.drawBitmap(arrw1,arrow1X,arrow1Y,null);
        }


        /// me throw 3

        if(touch2)
        {
            total--;
            arrow2Y=canvasheight-arrw.getHeight();
            canvas.drawBitmap(arrw2,arrow2X,arrow2Y,null);
            touch2=false;
        }

        else
        {
            arrow2Y=arrow2Y-19;
            canvas.drawBitmap(arrw2,arrow2X,arrow2Y,null);
        }

        /// me throw 4
        if(touch3)
        {
            total--;
            arrow3Y=canvasheight-arrw.getHeight();
            canvas.drawBitmap(arrw3,arrow3X,arrow3Y,null);
            touch3=false;
        }

        else
        {
            arrow3Y=arrow3Y-19;
            canvas.drawBitmap(arrw3,arrow3X,arrow3Y,null);
        }

        if(hit_run_noyan( arrowX, arrowY, arrow1X, arrow1Y, arrow2X, arrow2Y, arrow3X, arrow3Y))
        {
            run_noyanY=canvasheight+100;
            Toast.makeText(getContext(),"সাবাস ! একজন নাইট সৈন্য মেরেছো",Toast.LENGTH_SHORT).show();
            scor++;
        }
        if(hit_walk_noyan(arrowX, arrowY, arrow1X, arrow1Y, arrow2X, arrow2Y, arrow3X, arrow3Y))
        {
            walk_noyanY=canvasheight+100;
            Toast.makeText(getContext(),"সাবাস ! একজন নাইট সৈন্য মেরেছো",Toast.LENGTH_SHORT).show();
            scor++;
        }

        if(hit_horse(arrowX, arrowY, arrow1X, arrow1Y, arrow2X, arrow2Y, arrow3X, arrow3Y))
        {
            ghor++;
            horseY=canvasheight+100;
            Toast.makeText(getContext(),"সাবাস ! একজন নাইট ঘোড় সৈন্য মেরেছো",Toast.LENGTH_SHORT).show();
            scor++;
        }

        if(hit_archer(arrowX, arrowY, arrow1X, arrow1Y, arrow2X, arrow2Y, arrow3X, arrow3Y))
        {
            scor++;
            tirondaj++;
            arcY=canvasheight+100;
            Toast.makeText(getContext(),"সাবাস !একজন নাইট তীরন্দাজ কে  মেরেছো",Toast.LENGTH_SHORT).show();
        }

        if(cross())
        {
            boiler--;
            Toast.makeText(getContext(),"তারা তোমার সিমানায় ঢুকে পড়েছে, ঠেকাও !!",Toast.LENGTH_SHORT).show();
            fitness-=7;
            oppo++;
            if((arcY+arrp.getHeight()<=canvasheight && arcY+arrp.getHeight()>=(canvasheight-(canvasheight/4))))  {Toast.makeText(getContext(),"তোমার সৈন্য তীরবিদ্ধ হয়েছে ,বোকাচোদা ,তীরন্দাজ মারো !!",Toast.LENGTH_SHORT).show();}
            if((horseY+horse_run[0].getHeight()<=canvasheight && horseY+horse_run[0].getHeight()>=(canvasheight-(canvasheight/4)))){horseY = canvasheight+100;}
            if((walk_noyanY+walk_noyan[0].getHeight()<=canvasheight && walk_noyanY+walk_noyan[0].getHeight()>=(canvasheight-(canvasheight/4)))){walk_noyanY=canvasheight+100;}
            if((run_noyanY+run_noyan[0].getHeight()<=canvasheight && run_noyanY+run_noyan[0].getHeight()>=(canvasheight-(canvasheight/4)))) {run_noyanY=canvasheight+100;}
        }

        canvas.drawLine(0,canvasheight-canvasheight/4,canvaswidth,canvasheight-canvasheight/4,yellowPaint);
        canvas.drawText("শত্রু পক্ষের মৃত্যু সংখ্যা:" + scor+",তোমার তীরের সংখ্যা: "+total+" তোমার সীমানায় ঢুকে পড়া সৈন্যঃ "+oppo+" ", 20, 60, scorePaint);
        if(boiler>0)
        canvas.drawText("তীরন্দাজের মৃত্যু সংখ্যা:" + scor+", ঘোরসাওয়ার মৃত্যু সংখ্যা:"+ghor+"তোমার সৈন্য সংখ্যাঃ "+boiler+" ", 20, 100, scorePaint);
        else
            canvas.drawText("তীরন্দাজের মৃত্যু সংখ্যা:" + scor+", ঘোরসাওয়ার মৃত্যু সংখ্যা:"+ghor+"তোমার আর সৈন্য সংখ্যা নেই , তুমি শেষ !! পরাজিত"+boiler+" ", 20, 100, scorePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction()== MotionEvent.ACTION_DOWN && arrowY<=0)
        {
            touch=true;
            arrowX=(int) event.getX();
        }
        else if(event.getAction()== MotionEvent.ACTION_DOWN && arrow1Y<=0)
        {
            touch1=true;
            arrow1X=(int)event.getX();
        }
        else if(event.getAction()== MotionEvent.ACTION_DOWN && arrow2Y<=0)
        {
            touch2=true;
            arrow2X=(int)event.getX();
        }
        else if(event.getAction()== MotionEvent.ACTION_DOWN && arrow3Y<=0)
        {
            touch3=true;
            arrow3X=(int)event.getX();
        }
        return true;
    }

    public void Noyan_generate(Canvas canvas)
    {
        if ((canvasheight / 24) >= walk_noyanY) canvas.drawBitmap(walk_noyan[0], walk_noyanX, walk_noyanY, null);
        if ((canvasheight / 24) < walk_noyanY && 2 * (canvasheight / 24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[1], walk_noyanX, walk_noyanY, null);
        if (2 * (canvasheight / 24) < walk_noyanY && 3 * (canvasheight / 24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[2], walk_noyanX, walk_noyanY, null);
        if (3 * (canvasheight / 24) < walk_noyanY && 4 * (canvasheight / 24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[3], walk_noyanX, walk_noyanY, null);

        if (4 * (canvasheight / 24) < walk_noyanY && 5 * (canvasheight / 24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[4], walk_noyanX, walk_noyanY, null);
        if (5 * (canvasheight / 24) < walk_noyanY && 6 * (canvasheight / 24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[5], walk_noyanX, walk_noyanY, null);
        if (6 * (canvasheight / 24) < walk_noyanY && 7 * (canvasheight / 24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[6], walk_noyanX, walk_noyanY, null);
        if (7 * (canvasheight / 24) < walk_noyanY && 8*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[0], walk_noyanX, walk_noyanY, null);
        if (8 * (canvasheight / 24) < walk_noyanY && 9*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[1], walk_noyanX, walk_noyanY, null);
        if (9 * (canvasheight / 24) < walk_noyanY && 10*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[2], walk_noyanX, walk_noyanY, null);
        if (10 * (canvasheight / 24) < walk_noyanY && 11*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[3], walk_noyanX, walk_noyanY, null);
        if (11 * (canvasheight / 24) < walk_noyanY && 12*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[4], walk_noyanX, walk_noyanY, null);
        if (12 * (canvasheight / 24) < walk_noyanY && 13*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[5], walk_noyanX, walk_noyanY, null);
        if (13 * (canvasheight / 24) < walk_noyanY && 14*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[6], walk_noyanX, walk_noyanY, null);


        if (14 * (canvasheight / 24) < walk_noyanY && 15*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[0], walk_noyanX, walk_noyanY, null);
        if (15 * (canvasheight / 24) < walk_noyanY && 16*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[1],walk_noyanX, walk_noyanY, null);
        if (16 * (canvasheight / 24) < walk_noyanY && 17*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[2], walk_noyanX, walk_noyanY, null);
        if (17 * (canvasheight / 24) < walk_noyanY && 18*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[3],walk_noyanX, walk_noyanY, null);
        if (18 * (canvasheight / 24) < walk_noyanY && 19*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[4], walk_noyanX, walk_noyanY, null);
        if (19 * (canvasheight / 24) < walk_noyanY && 20*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[0], walk_noyanX, walk_noyanY, null);
        if (20 * (canvasheight / 24) < walk_noyanY && 21*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[1], walk_noyanX, walk_noyanY, null);
        if (21 * (canvasheight / 24) < walk_noyanY && 22*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[2], walk_noyanX, walk_noyanY, null);
        if (22 * (canvasheight / 24) < walk_noyanY && 23*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[3], walk_noyanX, walk_noyanY, null);
        if (23 * (canvasheight / 24) < walk_noyanY && 24*(canvasheight/24) >= walk_noyanY)
            canvas.drawBitmap(walk_noyan[4],walk_noyanX, walk_noyanY, null);


    }

    public void run_noyan_generate(Canvas canvas)
    {
        if ((canvasheight / 24) >= run_noyanY) canvas.drawBitmap(run_noyan[0], run_noyanX, run_noyanY, null);
        if ((canvasheight / 24) < run_noyanY && 2 * (canvasheight / 24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[1], run_noyanX, run_noyanY, null);
        if (2 * (canvasheight / 24) < run_noyanY && 3 * (canvasheight / 24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[2], run_noyanX, run_noyanY, null);
        if (3 * (canvasheight / 24) < run_noyanY && 4 * (canvasheight / 24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[3], run_noyanX, run_noyanY, null);
        if (4 * (canvasheight / 24) < run_noyanY && 5 * (canvasheight / 24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[4], run_noyanX, run_noyanY, null);
        if (5 * (canvasheight / 24) < run_noyanY && 6 * (canvasheight / 24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[5], run_noyanX, run_noyanY, null);
        if (6 * (canvasheight / 24) < run_noyanY && 7 * (canvasheight / 24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[0], run_noyanX, run_noyanY, null);
        if (7 * (canvasheight / 24) < run_noyanY && 8*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[1], run_noyanX, run_noyanY, null);
        if (8 * (canvasheight / 24) < run_noyanY && 9*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[2], run_noyanX, run_noyanY, null);
        if (9 * (canvasheight / 24) < run_noyanY && 10*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[3], run_noyanX, run_noyanY, null);
        if (10 * (canvasheight / 24) < run_noyanY && 11*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[4], run_noyanX, run_noyanY, null);
        if (11 * (canvasheight / 24) < run_noyanY && 12*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[5], run_noyanX, run_noyanY, null);
        if (12 * (canvasheight / 24) < run_noyanY && 13*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[0], run_noyanX, run_noyanY, null);
        if (13 * (canvasheight / 24) < run_noyanY && 14*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[1], run_noyanX, run_noyanY, null);

        if (14 * (canvasheight / 24) < run_noyanY && 15*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[2], run_noyanX, run_noyanY, null);
        if (15 * (canvasheight / 24) < run_noyanY && 16*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[3], run_noyanX, run_noyanY, null);
        if (16 * (canvasheight / 24) < run_noyanY && 17*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[4], run_noyanX, run_noyanY, null);
        if (17 * (canvasheight / 24) < run_noyanY && 18*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[5], run_noyanX, run_noyanY, null);
        if (18 * (canvasheight / 24) < run_noyanY && 19*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[0], run_noyanX, run_noyanY, null);
        if (19 * (canvasheight / 24) < run_noyanY && 20*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[1], run_noyanX, run_noyanY, null);
        if (20 * (canvasheight / 24) < run_noyanY && 21*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[1], run_noyanX, run_noyanY, null);
        if (21 * (canvasheight / 24) < run_noyanY && 22*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[2], run_noyanX, run_noyanY, null);
        if (22 * (canvasheight / 24) < run_noyanY && 23*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[3], run_noyanX, run_noyanY, null);
        if (23 * (canvasheight / 24) < run_noyanY && 24*(canvasheight/24) >= run_noyanY)
            canvas.drawBitmap(run_noyan[4], run_noyanX, run_noyanY, null);

    }

    public boolean hit_run_noyan(int arrowx,int arrowy,int arrow1x,int arrow1y,int arrow2x,int arrow2y,int arrow3x,int arrow3y)
    {
        int width,height;
        width=run_noyan[0].getWidth();
        height=run_noyan[0].getHeight();
        if( (arrowx>=run_noyanX && arrowx<=run_noyanX+width) && (arrowy+arrw.getHeight()>=(run_noyanY) && (arrowy+arrw.getHeight()<=run_noyanY+height) ) )
        {
           arrowY=0;
            return true;
        }


        if( (arrow1x>=run_noyanX && arrow1x<=run_noyanX+width) && (arrow1y+arrw.getHeight()>=(run_noyanY) && (arrow1y+arrw.getHeight()<=run_noyanY+height) ) )
        {
            arrow1Y=0;
            return true;
        }


        if( (arrow2x>=run_noyanX && arrow2x<=run_noyanX+width) && (arrow2y+arrw.getHeight()>=(run_noyanY) && (arrow2y+arrw.getHeight()<=run_noyanY+height) ) )
        {
            arrow2Y=0;
            return true;
        }


        if( (arrow3x>=run_noyanX && arrow3x<=run_noyanX+width) && (arrow3y+arrw.getHeight()>=(run_noyanY) && (arrow3y+arrw.getHeight()<=run_noyanY+height) ) )
        {
            arrow3Y=0;
            return true;
        }

        return false;
    }

    public boolean hit_walk_noyan(int arrowx,int arrowy,int arrow1x,int arrow1y,int arrow2x,int arrow2y,int arrow3x,int arrow3y)
    {
          int width,height;
          width=walk_noyan[0].getWidth();
          height=walk_noyan[0].getHeight();
         if( (arrowx>=walk_noyanX && arrowx<=walk_noyanX+width) && (arrowy+arrw.getHeight()>=(walk_noyanY) && (arrowy+arrw.getHeight()<=walk_noyanY+height) ) )
         {
             arrowY=0;
             return true;
         }


        if( (arrow1x>=walk_noyanX && arrow1x<=walk_noyanX+width) && (arrow1y+arrw.getHeight()>=(walk_noyanY) && (arrow1y+arrw.getHeight()<=walk_noyanY+height) ) )
        {
            arrow1Y=0;
            return true;
        }


        if( (arrow2x>=walk_noyanX && arrow2x<=walk_noyanX+width) && (arrow2y+arrw.getHeight()>=(walk_noyanY) && (arrow2y+arrw.getHeight()<=walk_noyanY+height) ) )
        {
            arrow2Y=0;
            return true;
        }


        if( (arrow3x>=walk_noyanX && arrow3x<=walk_noyanX+width) && (arrow3y+arrw.getHeight()>=(walk_noyanY) && (arrow3y+arrw.getHeight()<=walk_noyanY+height) ) )
        {
            arrow3Y=0;
            return true;
        }

         return false;
    }

    public boolean hit_horse(int arrowx,int arrowy,int arrow1x,int arrow1y,int arrow2x,int arrow2y,int arrow3x,int arrow3y)
    {
        int width,height;
        width=horse_run[0].getWidth();
        height=horse_run[0].getHeight();
        if( (arrowx>=horseX && arrowx<=horseX+width) && (arrowy+arrw.getHeight()>=(horseY) && (arrowy+arrw.getHeight()<=horseY+height) ) )
        {
            arrowY=0;
            return true;
        }


        if( (arrow1x>=horseX && arrow1x<=horseX+width) && (arrow1y+arrw.getHeight()>=(horseY) && (arrow1y+arrw.getHeight()<=horseY+height) ) )
        {
            arrow1Y=0;
            return true;
        }


        if( (arrow2x>=horseX && arrow2x<=horseX+width) && (arrow2y+arrw.getHeight()>=(horseY) && (arrow2y+arrw.getHeight()<=horseY+height) ) )
        {
            arrow2Y=0;
            return true;
        }


        if( (arrow3x>=horseX && arrow3x<=horseX+width) && (arrow3y+arrw.getHeight()>=(horseY) && (arrow3y+arrw.getHeight()<=horseY+height) ) )
        {
            arrow3Y=0;
            return true;
        }

        return false;
    }

    public boolean hit_archer(int arrowx,int arrowy,int arrow1x,int arrow1y,int arrow2x,int arrow2y,int arrow3x,int arrow3y)
    {
        int width,height;
        width=archer[0].getWidth();
        height=archer[0].getHeight();
        if( (arrowx>=arcX && arrowx<=arcX+width) && (arrowy+arrw.getHeight()>=(arcY) && (arrowy+arrw.getHeight()<=arcY+height) ) )
        {
            arrowY=0;
            return true;
        }


        if( (arrow1x>=arcX && arrow1x<=arcX+width) && (arrow1y+arrw.getHeight()>=(arcY) && (arrow1y+arrw.getHeight()<=arcY+height) ) )
        {
            arrow1Y=0;
            return true;
        }


        if( (arrow2x>=arcX && arrow2x<=arcX+width) && (arrow2y+arrw.getHeight()>=(arcY) && (arrow2y+arrw.getHeight()<=arcY+height) ) )
        {
            arrow2Y=0;
            return true;
        }


        if( (arrow3x>=arcX && arrow3x<=arcX+width) && (arrow3y+arrw.getHeight()>=(arcY) && (arrow3y+arrw.getHeight()<=arcY+height) ) )
        {
            arrow3Y=0;
            return true;
        }

        return false;
    }


    public void horse_generate(Canvas canvas)
    {
        if ((canvasheight / 24) >= horseY) canvas.drawBitmap(horse_run[0], horseX, horseY, null);
        if ((canvasheight / 24) < horseY && 2 * (canvasheight / 24) >= horseY)
            canvas.drawBitmap(horse_run[1], horseX, horseY, null);
        if (2 * (canvasheight / 24) < horseY && 3 * (canvasheight / 24) >= horseY)
            canvas.drawBitmap(horse_run[2], horseX, horseY, null);
        if (3 * (canvasheight / 24) < horseY && 4 * (canvasheight / 24) >= horseY)
            canvas.drawBitmap(horse_run[3], horseX, horseY, null);
        if (4 * (canvasheight / 24) < horseY && 5 * (canvasheight / 24) >= horseY)
            canvas.drawBitmap(horse_run[0], horseX, horseY, null);
        if (5 * (canvasheight / 24) < horseY && 6 * (canvasheight / 24) >= horseY)
            canvas.drawBitmap(horse_run[1], horseX, horseY, null);
        if (6 * (canvasheight / 24) < horseY && 7 * (canvasheight / 24) >= horseY)
            canvas.drawBitmap(horse_run[2], horseX, horseY, null);
        if (7 * (canvasheight / 24) < horseY && 8*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[3], horseX, horseY, null);
        if (8 * (canvasheight / 24) < horseY && 9*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[0], horseX, horseY, null);
        if (9 * (canvasheight / 24) < horseY && 10*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[1], horseX, horseY, null);
        if (10 * (canvasheight / 24) < horseY && 11*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[2], horseX, horseY, null);
        if (11 * (canvasheight / 24) < horseY && 12*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[3], horseX, horseY, null);
        if (12 * (canvasheight / 24) < horseY && 13*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[0], horseX, horseY, null);
        if (13 * (canvasheight / 24) < horseY && 14*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[1], horseX, horseY, null);

        if (14 * (canvasheight / 24) < horseY && 15*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[2], horseX, horseY, null);
        if (15 * (canvasheight / 24) < horseY && 16*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[3], horseX, horseY, null);
        if (16 * (canvasheight / 24) < horseY && 17*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[0], horseX, horseY, null);
        if (17 * (canvasheight / 24) < horseY && 18*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[1], horseX, horseY, null);
        if (18 * (canvasheight / 24) < horseY && 19*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[2], horseX, horseY, null);
        if (19 * (canvasheight / 24) < horseY && 20*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[3], horseX, horseY, null);
        if (20 * (canvasheight / 24) < horseY && 21*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[0], horseX, horseY, null);
        if (21 * (canvasheight / 24) < horseY && 22*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[1], horseX, horseY, null);
        if (22 * (canvasheight / 24) < horseY && 23*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[2], horseX, horseY, null);
        if (23 * (canvasheight / 24) < horseY && 24*(canvasheight/24) >= horseY)
            canvas.drawBitmap(horse_run[3], horseX, horseY, null);

    }

    public void archer_generate(int height,Canvas canvas)
    {
        if ((height / 10) >= arcY) canvas.drawBitmap(archer[0], arcX, arcY, null);
        if ((height / 10) < arcY && 2 * (height / 10) >= arcY)
            canvas.drawBitmap(archer[1], arcX, arcY, null);
        if (2 * (height / 10) < arcY && 3 * (height / 10) >= arcY)
            canvas.drawBitmap(archer[2], arcX, arcY, null);
        if (3 * (height / 10) < arcY && 4 * (height / 10) >= arcY)
            canvas.drawBitmap(archer[3], arcX, arcY, null);
        if (4 * (height / 10) < arcY && 5 * (height / 10) >= arcY)
            canvas.drawBitmap(archer[0], arcX, arcY, null);
        if (5 * (height / 10) < arcY && 6 * (height / 10) >= arcY)
            canvas.drawBitmap(archer[1], arcX, arcY, null);
        if (6 *(height / 10) < arcY && 7 * (height / 10) >= arcY)
            canvas.drawBitmap(archer[2], arcX, arcY, null);
        if (7 * (height / 10) < arcY && 8*(height / 10)>= arcY){
            canvas.drawBitmap(archer[3], arcX, arcY, null);
        }
        if (8 *(height / 10) < arcY && 9*(height / 10) >= arcY)
            canvas.drawBitmap(archer[0], arcX, arcY, null);
        if (9 *(height / 10) < arcY && 10*(height / 10) >= arcY)
            canvas.drawBitmap(archer[1], arcX, arcY, null);
    }

    public boolean cross()
    {
        if( (horseY+horse_run[0].getHeight()<=canvasheight && horseY+horse_run[0].getHeight()>=(canvasheight-(canvasheight/4))) ||  (walk_noyanY+walk_noyan[0].getHeight()<=canvasheight && walk_noyanY+walk_noyan[0].getHeight()>=(canvasheight-(canvasheight/4)))  || (run_noyanY+run_noyan[0].getHeight()<=canvasheight && run_noyanY+run_noyan[0].getHeight()>=(canvasheight-(canvasheight/4))) )
        {
            return true;
        }
        else return false;
    }

}
