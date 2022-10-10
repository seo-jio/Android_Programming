package com.example.surfaceviewexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MySurfaceView(this));
    }
}

class Ball{
    int x, y, xInc = 1, yInc = 1;
    int diameter;
    static int WIDTH = 1080, HEIGHT = 1920;

    public Ball(int d){
        this.diameter = d;

        x = (int)(Math.random() * (WIDTH - d) + 3);
        y = (int)(Math.random() * (HEIGHT - d) + 3);

        xInc = (int)(Math.random()*30+1);
        yInc = (int)(Math.random()*30+1);
    }

    public void paint(Canvas g) {
        Paint paint = new Paint();

        if (x < diameter || x > (WIDTH - diameter)){
            xInc = -xInc;
        }
        if (y < diameter || y > (HEIGHT - diameter)){
            yInc = -yInc;
        }

        x += xInc;
        y += yInc;

        paint.setColor(Color.RED);
        g.drawCircle(x, y, diameter, paint);
    }
}

class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public Ball basket[] = new Ball[10];
    private MyThread thread;

    public MySurfaceView(Context context) {
        super(context);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        thread = new MyThread(holder);
        for (int i = 0; i < 10; i++){
            basket[i] = new Ball(20);
        }
    }

    public MyThread getThread(){
        return thread;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;

        thread.setRunning(false);
        while(retry){
            try{
                thread.join();
                retry = false;
            }catch(InterruptedException e){

            }
        }
    }

    class MyThread extends Thread {
        private boolean mRun = false;
        private SurfaceHolder mSurfaceHolder;

        public MyThread(SurfaceHolder surfaceHolder) {
            mSurfaceHolder = surfaceHolder;
        }

        public void run() {
            while (mRun) {
                Canvas c = null;
                try {
                    c = mSurfaceHolder.lockCanvas(null);
                    c.drawColor(Color.BLACK);
                    synchronized (mSurfaceHolder) {
                        for (Ball b : basket) {
                            b.paint(c);
                        }
                    }
                } finally {
                    if (c != null) {
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }

        public void setRunning(boolean b) {
            mRun = b;
        }
    }
}
