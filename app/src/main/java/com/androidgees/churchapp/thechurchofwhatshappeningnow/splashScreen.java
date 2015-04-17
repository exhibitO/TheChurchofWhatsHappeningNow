package com.androidgees.churchapp.thechurchofwhatshappeningnow; /**
 * Created by OLAJUWON on 8/14/2014.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class splashScreen extends Activity {
    private Thread mSplashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.splash_layout);
        final splashScreen sPlashScreen = this;

        mSplashThread =  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){

                        wait(2500);
                    }
                }
                catch(InterruptedException ex){
                }

                finish();

                Intent intent = new Intent();
                intent.setClass(sPlashScreen, MainActivity.class);
                startActivity(intent);

            }
        };

        mSplashThread.start();
    }


    @Override

    public boolean onTouchEvent(MotionEvent evt)
    {
        if(evt.getAction() == MotionEvent.ACTION_DOWN)
        {
            synchronized(mSplashThread){
                mSplashThread.notifyAll();
            }
        }
        return true;
    }
}
