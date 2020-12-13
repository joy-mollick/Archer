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
import android.widget.Toast;
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

public class TigerRun extends View {
    /// we will store two pics of fish to make us pretend to be flying
    private Bitmap fish[] = new Bitmap[2];
    private Bitmap arrw[] = new Bitmap[2];
    private Bitmap bird[] = new Bitmap[4];
    private int fishX = 10;
    private int fishY;
    private int fishSpeed;
    private int arrowX=30,arrowY,arrowSpeed=28;
    private int birdX,birdY,birdspeed=12;
    private boolean Touch=false;

    private int yellowX, yellowY, yellowSpeed = 16;
    private Paint yellowPaint = new Paint();

    private int greenX, greenY, greenSpeed = 20;
    private Paint greenPaint = new Paint();

    private int redX, redY, redSpeed = 25;
    private Paint redPaint = new Paint();


    private int score, lifeCounterOfFish;


    private boolean touch = false;

    /// background image in the form of bitmap
    private Bitmap backgroundImage;

    private Bitmap tiger[]=new Bitmap[4];
    private int tigerX,tigerY,tigerSpeed=13;

    private Paint scorePaint = new Paint();

    /// one for red heart and another one for broken heart
    private Bitmap life[] = new Bitmap[2];

    private Bitmap arch;

    private int canvasWidth ,canvasHeight;

    private int maxFishY;

    boolean one=false,two=false;

    int total=15,ordh;

    /// constructor of our this class
    public  TigerRun(Context context)
    {
        super(context);

        /// we will store those two pics from drawable
        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);

        arrw[0]=BitmapFactory.decodeResource(getResources(),R.drawable.arrow);
        arrw[1]=BitmapFactory.decodeResource(getResources(),R.drawable.arrow);

        /// Bird storage
        bird[0]=BitmapFactory.decodeResource(getResources(),R.drawable.bird1);
        bird[1]=BitmapFactory.decodeResource(getResources(),R.drawable.bird2);
        bird[2]=BitmapFactory.decodeResource(getResources(),R.drawable.bird3);
        bird[3]=BitmapFactory.decodeResource(getResources(),R.drawable.bird4);


        /// Cat Storage
        tiger[0]=BitmapFactory.decodeResource(getResources(),R.drawable.tiger1);
        tiger[1]=BitmapFactory.decodeResource(getResources(),R.drawable.tiger2);
        tiger[2]=BitmapFactory.decodeResource(getResources(),R.drawable.tiger3);
        tiger[3]=BitmapFactory.decodeResource(getResources(),R.drawable.tiger4);



        /// archer
        arch=BitmapFactory.decodeResource(getResources(),R.drawable.archer);

        /// setting background
        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        /// In which type the score will appear in the form of text
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(50);
        scorePaint.setTypeface(Typeface.DEFAULT);
        scorePaint.setAntiAlias(true);

        /// taking from drawable
        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);

        arrowY=arrw[0].getHeight();
        birdY=1370;
        birdX=bird[2].getWidth();
        tigerX=0;
        score = 0;
        lifeCounterOfFish = 3;
    }


    /// this is for drawing some thing on content
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        /// put background image
        canvas.drawBitmap(backgroundImage, 0, 0, null);

        /// getting minimum Fish height
        /// Here height is exactly from up to down
        int minFishY = fish[0].getHeight();
        maxFishY = canvasHeight - fish[0].getHeight() * 1;


        birdY=maxFishY;
        tigerY=maxFishY;
        /// it will be changed from another method ontouchevent.
        if(touch)
        {
            arrowY = arrw[0].getHeight();
            canvas.drawBitmap(arrw[1], arrowX, arrowY, null);
            touch = false;
        }
        else
        {
            arrowY=arrowY+arrowSpeed;
            canvas.drawBitmap(arrw[1],arrowX,arrowY,null);
        }

        if(hit(arrowY,arrowX))
        {
            birdX=0;
            tigerX=0;
            score=score+1;
            Toast.makeText(getContext(),"দুর্দান্ত তীর ছুড়েছ ।",Toast.LENGTH_LONG).show();
        }

        /// bird calculation
        /// cat calculation

        Random rand = new Random(); //instance of random class
        int upperbound = 2;
        //generate random values from 0-24
        int int_random = rand.nextInt(upperbound);

       /* if(int_random==0){
        if(birdX>canvasWidth) {birdX=0;}
        birdX=birdX+16;
        generate(canvas);}
        */

        if (tigerX > canvasWidth) {
            tigerX = 0;
        }
        tigerX = tigerX + 16;
        Tiger(canvas);

        yellowX = yellowX - yellowSpeed;
        if(hitBallChecker(yellowX, yellowY))
        {
            score = score + 10;
            yellowX = -100;
        }

        if(yellowX < 0)
        {
            yellowX = canvasWidth + 21;
            yellowY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY ;
        }
        canvas.drawCircle(yellowX, yellowY, 25, yellowPaint);

        greenX = greenX- greenSpeed;
        if(hitBallChecker(greenX, greenY))
        {
            score = score + 20;
            greenX = -100;
        }

        if(greenX < 0)
        {
            greenX = canvasWidth + 21;
            greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY ;
        }
        canvas.drawCircle(greenX, greenY, 35, greenPaint);



        redX = redX- redSpeed;
        if(hitBallChecker(redX, redY))
        {
            redX = -100;
            lifeCounterOfFish--;
        }

        if(redX < 0)
        {
            redX = canvasWidth + 21;
            redY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY ;
        }

        canvas.drawCircle(redX, redY, 30, redPaint);

        /// putting score of the player in the style of scorepaint at the co-ordinate
        if(total>0)
            canvas.drawText("শিকার সংখ্যা:" + score+",  তীরের সংখ্যা: "+total, 20, 60, scorePaint);
        else canvas.drawText("শিকার সংখ্যা:" + score+",  আর তীর নেই ! "+total, 20, 60, scorePaint);

        Intent i=new Intent(getContext(),MainActivity.class);
    }


    public boolean hitBallChecker(int x, int y)
    {
        if(fishX < x && x < (fishX + fish[0].getWidth()) && fishY < y && y < (fishY + fish[0].getHeight()))
        {
            return true;
        }
        return false;
    }

    public boolean hit(int yy,int xx)
    {
        if(xx>=birdX && xx<=(birdX+bird[2].getWidth()) && yy+arrw[1].getHeight()>=maxFishY && yy+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        else return false;
    }

    /// it will control action of touch event
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(arrowY>maxFishY) {
                total--;
                arrowX = (int) event.getX();
                touch = true;
                Touch=false;
            }
            else
            {
                Touch=true;
                Toast.makeText(getContext(),"তুমি ইতিমধ্যে একটি তীর ছুড়েছো ।",Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    public void Tiger(Canvas canvas)
    {
        if ((canvasWidth / 8) >= tigerX) canvas.drawBitmap(tiger[0], tigerX, tigerY, null);
        if ((canvasWidth / 8) < tigerX && 2 * (canvasWidth / 8) >= tigerX)
            canvas.drawBitmap(tiger[1], tigerX, tigerY, null);
        if (2 * (canvasWidth / 8) < tigerX && 3 * (canvasWidth / 8) >= tigerX)
            canvas.drawBitmap(tiger[2], tigerX, tigerY, null);
        if (3 * (canvasWidth / 8) < tigerX && 4 * (canvasWidth / 8) >= tigerX)
            canvas.drawBitmap(tiger[3], tigerX, tigerY, null);

        if (4 * (canvasWidth / 8) < tigerX && 5 * (canvasWidth / 8) >= tigerX)
            canvas.drawBitmap(tiger[0], tigerX, tigerY, null);

        if (5 * (canvasWidth / 8) < tigerX && 6 * (canvasWidth / 8) >= tigerX)
            canvas.drawBitmap(tiger[1], tigerX, tigerY, null);

        if (6 * (canvasWidth / 8) < tigerX && 7 * (canvasWidth / 8) >= tigerX)
            canvas.drawBitmap(tiger[2], tigerX, tigerY, null);

        if (7 * (canvasWidth / 8) < tigerX && (canvasWidth) >= tigerX)
            canvas.drawBitmap(tiger[3], tigerX, tigerY, null);
    }
}
