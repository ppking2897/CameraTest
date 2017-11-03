package ea.cameratest;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ea.cameratest.MainActivity.MainActivity;


public class EnterPictureActivity extends AppCompatActivity {

    private CountTime countTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_picture);

        countTime = new CountTime(5000 , 1000 , this);
        countTime.start();

    }

    public void enter(View view) {
        Intent it = new Intent(this , MainActivity.class);
        startActivity(it);
        finish();
    }

    public class CountTime extends CountDownTimer{
        private Context context;

        public CountTime(long millisInFuture, long countDownInterval , Context context) {
            super(millisInFuture, countDownInterval);
            this.context = context;
        }

        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            Intent it = new Intent(context , MainActivity.class);
        }
    }
}
