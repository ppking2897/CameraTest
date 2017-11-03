package ea.cameratest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ea.cameratest.MainActivity.MainActivity;

public class EnterPictureActivity extends AppCompatActivity {

    private CountdownTime countdownTime;
    private MainActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_picture);

        countdownTime = new CountdownTime(100000 , 1000);

        countdownTime.start();
    }

    public void enter(View view) {
        Intent it = new Intent(this , MainActivity.class);
        startActivity(it);
        finish();
    }

    public void intentActivity(){
        Intent it = new Intent(this , MainActivity.class);
        startActivity(it);
        finish();
    }
}
