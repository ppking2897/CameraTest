package ea.cameratest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

import ea.cameratest.MainActivity.MainActivity;

/**
 * Created by EA on 2017/11/03.
 */

public class CountdownTime extends CountDownTimer {

    public CountdownTime(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);

    }

    @Override
    public void onTick(long l) {

    }

    @Override
    public void onFinish() {
        Log.v("ppking" , "finish !");


    }

}
