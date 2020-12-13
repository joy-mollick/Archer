package com.example.archer;

import android.app.Activity;
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

/// Actually we will draw feed of fish and other things so we have to extend the view class
public class FlyingFishView extends View {

    /// we will store two pics of fish to make us pretend to be flying
    private Bitmap fish[] = new Bitmap[2];
    private Bitmap arrw[] = new Bitmap[4];
    private Bitmap bird[] = new Bitmap[4];
    private Bitmap cat[]=   new Bitmap[8];
    private Bitmap deer[]=  new Bitmap[4];
    private Bitmap dove[]=  new Bitmap[4];

    private int fishX = 10;
    private int fishY;
    private int fishSpeed;
    private int arrowX=30,arrowY,arrowSpeed=30;
    private int birdX,birdY,birdspeed=15;
    private boolean Touch=false;

    private int yellowX, yellowY, yellowSpeed = 16;
    private Paint yellowPaint = new Paint();

    private int greenX, greenY, greenSpeed = 20;
    private Paint greenPaint = new Paint();

    private int redX, redY, redSpeed = 25;
    private Paint redPaint = new Paint();


    int scor=MainActivity.score;

    /// background image in the form of bitmap
    private Bitmap backgroundImage;

    private Bitmap tiger[]=new Bitmap[4];
    private int tigerX,tigerY,tigerSpeed=13;

    private Paint scorePaint = new Paint();
    private Paint loading=new Paint();
    /// one for red heart and another one for broken heart
    private Bitmap life[] = new Bitmap[2];

    private Bitmap arch;

    private int catX,catY,catSpeed=18;
    private int deerX,deerY,deerSpeed=17;
    private int doveX,doveY,doveSpeed=19;
    private int arrow1X,arrow1Y;
    private int arrow2X,arrow2Y;
    private int arrow3X,arrow3Y;

   private int canvasWidth ,canvasHeight;

   private int maxFishY;

   int maxtigerY;

   int total=50;

    boolean touch=false,touch1=false,touch2=false,touch3=false;

    /// constructor of our this class
    public FlyingFishView(Context context)
    {
        super(context);


        arrw[0]=BitmapFactory.decodeResource(getResources(),R.drawable.arrow);
        arrw[1]=BitmapFactory.decodeResource(getResources(),R.drawable.arrow);
        arrw[2]=BitmapFactory.decodeResource(getResources(),R.drawable.arrow);
        arrw[3]=BitmapFactory.decodeResource(getResources(),R.drawable.arrow);

        /// Bird storage
        bird[0]=BitmapFactory.decodeResource(getResources(),R.drawable.bird1);
        bird[1]=BitmapFactory.decodeResource(getResources(),R.drawable.bird2);
        bird[2]=BitmapFactory.decodeResource(getResources(),R.drawable.bird3);
        bird[3]=BitmapFactory.decodeResource(getResources(),R.drawable.bird4);


        /// tiger Storage
        tiger[0]=BitmapFactory.decodeResource(getResources(),R.drawable.tiger1);
        tiger[1]=BitmapFactory.decodeResource(getResources(),R.drawable.tiger2);
        tiger[2]=BitmapFactory.decodeResource(getResources(),R.drawable.tiger3);
        tiger[3]=BitmapFactory.decodeResource(getResources(),R.drawable.tiger4);

        ///swor storage
        cat[0]=BitmapFactory.decodeResource(getResources(),R.drawable.cat1);
        cat[1]=BitmapFactory.decodeResource(getResources(),R.drawable.cat2);
        cat[2]=BitmapFactory.decodeResource(getResources(),R.drawable.cat3);
        cat[3]=BitmapFactory.decodeResource(getResources(),R.drawable.cat4);
        cat[4]=BitmapFactory.decodeResource(getResources(),R.drawable.cat5);
        cat[5]=BitmapFactory.decodeResource(getResources(),R.drawable.cat6);
        cat[6]=BitmapFactory.decodeResource(getResources(),R.drawable.cat7);
        cat[7]=BitmapFactory.decodeResource(getResources(),R.drawable.cat8);

        /// Deer storage
        deer[0]=BitmapFactory.decodeResource(getResources(),R.drawable.deer_1);
        deer[1]=BitmapFactory.decodeResource(getResources(),R.drawable.deer_2);
        deer[2]=BitmapFactory.decodeResource(getResources(),R.drawable.deer_3);
        deer[3]=BitmapFactory.decodeResource(getResources(),R.drawable.deer_4);

        /// Dove Storage
        dove[0]=BitmapFactory.decodeResource(getResources(),R.drawable.dove_1);
        dove[1]=BitmapFactory.decodeResource(getResources(),R.drawable.dove_2);
        dove[2]=BitmapFactory.decodeResource(getResources(),R.drawable.dove_3);
        dove[3]=BitmapFactory.decodeResource(getResources(),R.drawable.dove_4);



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

        loading.setColor(Color.WHITE);
        loading.setTextSize(45);
        loading.setTypeface(Typeface.DEFAULT);
        loading.setAntiAlias(true);


        arrowY=arrw[0].getHeight();
        birdY=1370;
        birdX=bird[2].getWidth();
        tigerX=canvasWidth;
        arrowX=50;
        catX=0;
        MainActivity.score = 0;
    }


   /// this is for drawing some thing on content
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if(total==0) {
            Intent gameover = new Intent(getContext(), GameOverActivity.class);
            //Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK
           /// finishAffinity();
            gameover.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getContext().startActivity(gameover);
        }

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        fish[0]=BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        /// put background image
        canvas.drawBitmap(backgroundImage, 0, 0, null);

        /// getting minimum Fish height
        /// Here height is exactly from up to down
        int minFishY = fish[0].getHeight();
         maxFishY = canvasHeight - fish[0].getHeight() * 1;
         maxtigerY = canvasHeight - fish[0].getHeight() * 2;

        tigerY=maxFishY;
        birdY=maxFishY;
        catY=maxFishY;
        deerY=maxFishY;
        doveY=maxFishY;
        /// it will be changed from another method ontouchevent.

        /// me throw 1
        if(touch)
        {
            total--;
            arrowY=arrw[1].getHeight();
            canvas.drawBitmap(arrw[0],arrowX,arrowY,null);
            touch=false;
        }

        else
        {
            arrowY=arrowY+24;
            canvas.drawBitmap(arrw[0],arrowX,arrowY,null);
        }

        /// me throw 2

        if(touch1)
        {
            total--;
            arrow1Y=arrw[1].getHeight();
            canvas.drawBitmap(arrw[1],arrow1X,arrow1Y,null);
            touch1=false;
        }

        else
        {
            arrow1Y=arrow1Y+24;
            canvas.drawBitmap(arrw[1],arrow1X,arrow1Y,null);
        }


        /// me throw 3

        if(touch2)
        {
            total--;
            arrow2Y=arrw[2].getHeight();
            canvas.drawBitmap(arrw[2],arrow2X,arrow2Y,null);
            touch2=false;
        }

        else
        {
            arrow2Y=arrow2Y+24;
            canvas.drawBitmap(arrw[2],arrow2X,arrow2Y,null);
        }

        /// me throw 4
        if(touch3)
        {
            total--;
            arrow3Y=arrw[2].getHeight();
            canvas.drawBitmap(arrw[3],arrow3X,arrow3Y,null);
            touch3=false;
        }

        else
        {
            arrow3Y=arrow3Y+24;
            canvas.drawBitmap(arrw[3],arrow3X,arrow3Y,null);
        }
        /// bird calculation
        /// cat calculation

        if(scor<3 && total>0) {
            tigerX=canvasWidth+canvasWidth/2;tigerY=canvasHeight+canvasHeight/2;
            catX=canvasWidth+canvasWidth/2;catY=canvasHeight+canvasHeight/2;
            deerX=canvasWidth+canvasWidth/2;deerY=canvasHeight+canvasHeight/2;
            doveX=canvasWidth+canvasWidth/2;doveY=canvasHeight+canvasHeight/2;
            if (birdX > canvasWidth) {
                birdX = 0;
            }
            birdX = birdX + 16;
            generate(canvas);
        }

        if(scor>=3&&scor<=6&& total>0) {
            doveX=canvasWidth+canvasWidth/2;doveY=canvasHeight+canvasHeight/2;
            birdX=canvasWidth+canvasWidth/2;birdY=canvasHeight+canvasHeight/2;
            catX=canvasWidth+canvasWidth/2;catY=canvasHeight+canvasHeight/2;
            deerX=canvasWidth+canvasWidth/2;deerY=canvasHeight+canvasHeight/2;
            if (tigerX > canvasWidth) {
                tigerX = 0;
            }
            tigerX = tigerX + 19;
            Tiger(canvas);
        }

        if(scor>6&&scor<=8&& total>0)
        {
            doveX=canvasWidth+canvasWidth/2;doveY=canvasHeight+canvasHeight/2;
            tigerX=canvasWidth+canvasWidth/2;tigerY=canvasHeight+canvasHeight/2;
            birdX=canvasWidth+canvasWidth/2;birdY=canvasHeight+canvasHeight/2;
            deerX=canvasWidth+canvasWidth/2;deerY=canvasHeight+canvasHeight/2;
            if(catX>canvasWidth)
            {
                catX=0;
            }
            catX=catX+20;
            Cat(canvas);
        }

        if(scor>8 && scor<10&& total>0)
        {
            doveX=canvasWidth+canvasWidth/2;doveY=canvasHeight+canvasHeight/2;
            tigerX=canvasWidth+canvasWidth/2;tigerY=canvasHeight+canvasHeight/2;
            catX=canvasWidth+canvasWidth/2;catY=canvasHeight+canvasHeight/2;
            deerX=canvasWidth+canvasWidth/2;deerY=canvasHeight+canvasHeight/2;
            if (birdX > canvasWidth) {
                birdX = 0;
            }
            birdX = birdX + 19;
            generate(canvas);
        }

        if(scor>=10 && scor<=14&& total>0)
        {
            doveX=canvasWidth+canvasWidth/2;doveY=canvasHeight+canvasHeight/2;
            tigerX=canvasWidth+canvasWidth/2;tigerY=canvasHeight+canvasHeight/2;
            birdX=canvasWidth+canvasWidth/2;birdY=canvasHeight+canvasHeight/2;
            catX=canvasWidth+canvasWidth/2;catY=canvasHeight+canvasHeight/2;
            if (deerX > canvasWidth) {
                deerX = 0;
            }
            deerX = deerX + 20;
            Deer(canvas);
        }

        if(scor>14 && scor<=17&& total>0)
        {
            deerX=canvasWidth+canvasWidth/2;deerY=canvasHeight+canvasHeight/2;
            tigerX=canvasWidth+canvasWidth/2;tigerY=canvasHeight+canvasHeight/2;
            birdX=canvasWidth+canvasWidth/2;birdY=canvasHeight+canvasHeight/2;
            catX=canvasWidth+canvasWidth/2;catY=canvasHeight+canvasHeight/2;
            if (doveX > canvasWidth) {
                doveX = 0;
            }
            doveX = doveX + 19;
            Dove(canvas);
        }


        if(hitBird(arrowX, arrowY, arrow1X, arrow1Y, arrow2X, arrow2Y, arrow3X, arrow3Y))
        {
            birdX=0;
            arrowY=canvasHeight+1;
            scor=scor+1;
            Toast.makeText(getContext(),"দুর্দান্ত তীর ছুড়েছ !",Toast.LENGTH_SHORT).show();
        }

        if(hitTiger(arrowX, arrowY, arrow1X, arrow1Y, arrow2X, arrow2Y, arrow3X, arrow3Y))
        {
            tigerX=0;
            arrowY=canvasHeight+1;
            scor=scor+1;
            Toast.makeText(getContext(),"সিংহ শিকার , দুরন্ত তীর ছুটেছো!",Toast.LENGTH_SHORT).show();
        }

        if(hitCat(arrowX, arrowY, arrow1X, arrow1Y, arrow2X, arrow2Y, arrow3X, arrow3Y))
        {
            catX=0;
            arrowY=canvasHeight+1;
            scor=scor+1;
            Toast.makeText(getContext(),"বন্য বিড়াল শিকার, নিরভুল লক্ষভেদ !",Toast.LENGTH_SHORT).show();
        }

        if(hitDeer(arrowX, arrowY, arrow1X, arrow1Y, arrow2X, arrow2Y, arrow3X, arrow3Y))
        {
            deerX=0;
            arrowY=canvasHeight+1;
            scor=scor+1;
            Toast.makeText(getContext(),"হরিণ শিকার, কি দারুন !",Toast.LENGTH_SHORT).show();
        }

        if(hitDove(arrowX, arrowY, arrow1X, arrow1Y, arrow2X, arrow2Y, arrow3X, arrow3Y))
        {
            doveX=0;
            arrowY=canvasHeight+1;
            scor=scor+1;
            Toast.makeText(getContext(),"ঘুঘু শিকার করেছো, বাহ শিকারি !",Toast.LENGTH_SHORT).show();
        }



        /// putting score of the player in the style of scorepaint at the co-ordinate
        if(total>0)
        {
            canvas.drawText("শিকার সংখ্যা:" + scor+",  তীরের সংখ্যা: "+total, 20, 60, scorePaint);
            MainActivity.score=scor;

        }
        else
            {
                deerX=canvasWidth+canvasWidth/2;deerY=canvasHeight+canvasHeight/2;
                tigerX=canvasWidth+canvasWidth/2;tigerY=canvasHeight+canvasHeight/2;
                birdX=canvasWidth+canvasWidth/2;birdY=canvasHeight+canvasHeight/2;
                catX=canvasWidth+canvasWidth/2;catY=canvasHeight+canvasHeight/2;
                doveX=canvasWidth+canvasWidth/2;doveY=canvasHeight+canvasHeight/2;
                arrowX=canvasWidth+canvasWidth/2;arrowY=canvasHeight+canvasHeight/2;
                canvas.drawText(" আর তীর নেই ! "+" একটু অপেক্ষা করো ,লোডিং ...", 20, 60, loading);
        }

    }



    public boolean hitBird(int arrowx,int arrowy,int arrow1x,int arrow1y,int arrow2x,int arrow2y,int arrow3x,int arrow3y)
    {
        if(arrowx>=birdX+(bird[2].getWidth()/4) && arrowx<=(birdX+bird[2].getWidth()-(bird[2].getWidth()/4)) && arrowy+arrw[1].getHeight()>=maxFishY && arrowy+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        if(arrow1x>=birdX+(bird[2].getWidth()/4) && arrow1x<=(birdX+bird[2].getWidth()-(bird[2].getWidth()/4)) && arrow1y+arrw[1].getHeight()>=maxFishY && arrow1y+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        if(arrow2x>=birdX+(bird[2].getWidth()/4) && arrow2x<=(birdX+bird[2].getWidth()-(bird[2].getWidth()/4)) && arrow2y+arrw[1].getHeight()>=maxFishY && arrow2y+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        if(arrow3x>=birdX+(bird[2].getWidth()/4) && arrow3x<=(birdX+bird[2].getWidth()-(bird[2].getWidth()/4)) && arrow3y+arrw[1].getHeight()>=maxFishY && arrow3y+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }

        else return false;
    }

    public boolean hitTiger(int arrowx,int arrowy,int arrow1x,int arrow1y,int arrow2x,int arrow2y,int arrow3x,int arrow3y)
    {
        if(arrowx>=tigerX+(tiger[2].getWidth()/6) && arrowx<=(tigerX+tiger[2].getWidth()-(tiger[2].getWidth()/6)) && arrowy+arrw[1].getHeight()>=maxFishY && arrowy+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        if(arrow1x>=tigerX+(tiger[2].getWidth()/6) && arrow1x<=(tigerX+tiger[2].getWidth()-(tiger[2].getWidth()/6)) && arrow1y+arrw[1].getHeight()>=maxFishY && arrow1y+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        if(arrow2x>=tigerX+(tiger[2].getWidth()/6) && arrow2x<=(tigerX+tiger[2].getWidth()-(tiger[2].getWidth()/6)) && arrow2y+arrw[1].getHeight()>=maxFishY && arrow2y+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        if(arrow3x>=tigerX+(tiger[2].getWidth()/6) && arrow3x<=(tigerX+tiger[2].getWidth()-(tiger[2].getWidth()/6)) && arrow3y+arrw[1].getHeight()>=maxFishY && arrow3y+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        else return false;
    }

    private boolean hitCat(int arrowx,int arrowy,int arrow1x,int arrow1y,int arrow2x,int arrow2y,int arrow3x,int arrow3y)
    {
        if(arrowx>=catX+(cat[2].getWidth()/4) && arrowx<=(catX+cat[2].getWidth()-(cat[2].getWidth()/3)) && arrowy+arrw[1].getHeight()>=maxFishY && arrowy+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        if(arrow1x>=catX+(cat[2].getWidth()/4) && arrow1x<=(catX+cat[2].getWidth()-(cat[2].getWidth()/3)) && arrow1y+arrw[1].getHeight()>=maxFishY && arrow1y+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        if(arrow2x>=catX+(cat[2].getWidth()/4) && arrow2x<=(catX+cat[2].getWidth()-(cat[2].getWidth()/3)) && arrow2y+arrw[1].getHeight()>=maxFishY && arrow2y+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        if(arrow3x>=catX+(cat[2].getWidth()/4) && arrow3x<=(catX+cat[2].getWidth()-(cat[2].getWidth()/3)) && arrow3y+arrw[1].getHeight()>=maxFishY && arrow3y+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        else return false;
    }

    public boolean hitDeer(int arrowx,int arrowy,int arrow1x,int arrow1y,int arrow2x,int arrow2y,int arrow3x,int arrow3y)
    {
        if(arrowx>=deerX+(deer[2].getWidth()/4) && arrowx<=(deerX+deer[2].getWidth()-(deer[2].getWidth()/3)) && arrowy+arrw[1].getHeight()>=maxFishY && arrowy+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        if(arrow1x>=deerX+(deer[2].getWidth()/4) && arrow1x<=(deerX+deer[2].getWidth()-(deer[2].getWidth()/3)) && arrow1y+arrw[1].getHeight()>=maxFishY && arrow1y+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        if(arrow2x>=deerX+(deer[2].getWidth()/4) && arrow2x<=(deerX+deer[2].getWidth()-(deer[2].getWidth()/3)) && arrow2y+arrw[1].getHeight()>=maxFishY && arrow2y+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        if(arrow3x>=deerX+(deer[2].getWidth()/4) && arrow3x<=(deerX+deer[2].getWidth()-(deer[2].getWidth()/3)) && arrow3y+arrw[1].getHeight()>=maxFishY && arrow3y+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        else return false;
    }

    public boolean hitDove(int arrowx,int arrowy,int arrow1x,int arrow1y,int arrow2x,int arrow2y,int arrow3x,int arrow3y)
    {

        if(arrowx>=doveX+(dove[2].getWidth()/4) && arrowx<=(doveX+dove[2].getWidth()-(dove[2].getWidth()/4)) && arrowy+arrw[1].getHeight()>=maxFishY && arrowy+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        if(arrow1x>=doveX+(dove[2].getWidth()/4) && arrow1x<=(doveX+dove[2].getWidth()-(dove[2].getWidth()/4)) && arrow1y+arrw[1].getHeight()>=maxFishY && arrow1y+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        if(arrow2x>=doveX+(dove[2].getWidth()/4) && arrow2x<=(doveX+dove[2].getWidth()-(dove[2].getWidth()/4)) && arrow2y+arrw[1].getHeight()>=maxFishY && arrow2y+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }
        if(arrow3x>=doveX+(dove[2].getWidth()/4) && arrow3x<=(doveX+dove[2].getWidth()-(dove[2].getWidth()/4)) && arrow3y+arrw[1].getHeight()>=maxFishY && arrow3y+arrw[1].getHeight()<=canvasHeight)
        {
            return true;
        }

        else return false;
    }

    /// it will control action of touch event
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_DOWN && arrowY>=canvasHeight)
        {
            touch=true;
            arrowX=(int) event.getX();
        }
        else if(event.getAction()== MotionEvent.ACTION_DOWN && arrow1Y>=canvasHeight)
        {
            touch1=true;
            arrow1X=(int)event.getX();
        }
        else if(event.getAction()== MotionEvent.ACTION_DOWN && arrow2Y>=canvasHeight)
        {
            touch2=true;
            arrow2X=(int)event.getX();
        }
        else if(event.getAction()== MotionEvent.ACTION_DOWN && arrow3Y>=canvasHeight)
        {
            touch3=true;
            arrow3X=(int)event.getX();
        }
        return true;
    }

    public void generate(Canvas canvas)
    {

                if ((canvasWidth / 8) >= birdX) canvas.drawBitmap(bird[0], birdX, birdY, null);
                if ((canvasWidth / 8) < birdX && 2 * (canvasWidth / 8) >= birdX)
                    canvas.drawBitmap(bird[1], birdX, birdY, null);
                if (2 * (canvasWidth / 8) < birdX && 3 * (canvasWidth / 8) >= birdX)
                    canvas.drawBitmap(bird[2], birdX, birdY, null);
                if (3 * (canvasWidth / 8) < birdX && 4 * (canvasWidth / 8) >= birdX)
                    canvas.drawBitmap(bird[3], birdX, birdY, null);

                if (4 * (canvasWidth / 8) < birdX && 5 * (canvasWidth / 8) >= birdX)
                    canvas.drawBitmap(bird[0], birdX, birdY, null);
                if (5 * (canvasWidth / 8) < birdX && 6 * (canvasWidth / 8) >= birdX)
                    canvas.drawBitmap(bird[1], birdX, birdY, null);
                if (6 * (canvasWidth / 8) < birdX && 7 * (canvasWidth / 8) >= birdX)
                    canvas.drawBitmap(bird[2], birdX, birdY, null);
                if (7 * (canvasWidth / 8) < birdX && (canvasWidth) >= birdX)
                    canvas.drawBitmap(bird[3], birdX, birdY, null);

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

    public void Cat(Canvas canvas)
    {
        if ((canvasWidth / 8) >= catX) canvas.drawBitmap(cat[0], catX, catY, null);
        if ((canvasWidth / 8) < catX && 2 * (canvasWidth / 8) >= catX)
            canvas.drawBitmap(cat[1], catX, catY, null);
        if (2 * (canvasWidth / 8) < catX && 3 * (canvasWidth / 8) >= catX)
            canvas.drawBitmap(cat[2], catX, catY, null);
        if (3 * (canvasWidth / 8) < catX && 4 * (canvasWidth / 8) >= catX)
            canvas.drawBitmap(cat[3], catX, catY, null);

        if (4 * (canvasWidth / 8) < catX && 5 * (canvasWidth / 8) >= catX)
            canvas.drawBitmap(cat[4], catX, catY, null);

        if (5 * (canvasWidth / 8) < catX && 6 * (canvasWidth / 8) >= catX)
            canvas.drawBitmap(cat[5], catX, catY, null);

        if (6 * (canvasWidth / 8) < catX && 7 * (canvasWidth / 8) >= catX)
            canvas.drawBitmap(cat[6], catX, catY, null);

        if (7 * (canvasWidth / 8) < catX && (canvasWidth) >= catX)
            canvas.drawBitmap(cat[7], catX, catY, null);
    }

    private void Deer(Canvas canvas)
    {
        if ((canvasWidth / 8) >= deerX) canvas.drawBitmap(deer[0], deerX, deerY, null);
        if ((canvasWidth / 8) < deerX && 2 * (canvasWidth / 8) >= deerX)
            canvas.drawBitmap(deer[1], deerX, deerY, null);
        if (2 * (canvasWidth / 8) < deerX && 3 * (canvasWidth / 8) >= deerX)
            canvas.drawBitmap(deer[2], deerX, deerY, null);
        if (3 * (canvasWidth / 8) < deerX && 4 * (canvasWidth / 8) >= deerX)
            canvas.drawBitmap(deer[3], deerX, deerY, null);

        if (4 * (canvasWidth / 8) < deerX && 5 * (canvasWidth / 8) >= deerX)
            canvas.drawBitmap(deer[0], deerX, deerY, null);

        if (5 * (canvasWidth / 8) < deerX && 6 * (canvasWidth / 8) >= deerX)
            canvas.drawBitmap(deer[1], deerX, deerY, null);

        if (6 * (canvasWidth / 8) < deerX && 7 * (canvasWidth / 8) >= deerX)
            canvas.drawBitmap(deer[2], deerX, deerY, null);

        if (7 * (canvasWidth / 8) < deerX && (canvasWidth) >= deerX)
            canvas.drawBitmap(deer[3], deerX, deerY, null);
    }

    private void Dove(Canvas canvas)
    {
        if ((canvasWidth / 8) >= doveX) canvas.drawBitmap(dove[0], doveX, doveY, null);
        if ((canvasWidth / 8) < doveX && 2 * (canvasWidth / 8) >= doveX)
            canvas.drawBitmap(dove[1], doveX, doveY, null);
        if (2 * (canvasWidth / 8) < doveX && 3 * (canvasWidth / 8) >= doveX)
            canvas.drawBitmap(dove[2], doveX, doveY, null);
        if (3 * (canvasWidth / 8) < doveX && 4 * (canvasWidth / 8) >= doveX)
            canvas.drawBitmap(dove[3], doveX, doveY, null);

        if (4 * (canvasWidth / 8) < doveX && 5 * (canvasWidth / 8) >= doveX)
            canvas.drawBitmap(dove[0], doveX, doveY, null);

        if (5 * (canvasWidth / 8) < doveX && 6 * (canvasWidth / 8) >= doveX)
            canvas.drawBitmap(dove[1], doveX, doveY, null);

        if (6 * (canvasWidth / 8) < doveX && 7 * (canvasWidth / 8) >= doveX)
            canvas.drawBitmap(dove[2], doveX, doveY, null);

        if (7 * (canvasWidth / 8) < doveX && (canvasWidth) >= doveX)
            canvas.drawBitmap(dove[3], doveX, doveY, null);
    }
}
