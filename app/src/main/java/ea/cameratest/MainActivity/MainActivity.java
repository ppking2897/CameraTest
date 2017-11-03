package ea.cameratest.MainActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ea.cameratest.CameraPermission;
import ea.cameratest.EnterPictureActivity;
import ea.cameratest.R;
import ea.cameratest.di.DaggerMainActivityComponent;
import ea.cameratest.di.MainActivityModule;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback
        , SurfaceHolder.Callback  {
    @Inject
    CameraPermission cameraPermission;
    @BindView(R.id.mySurfaceView)
    SurfaceView mySurfaceView;

    private Camera camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule(this, this)).build().inject(this);

        if (cameraPermission.checkPermission()) {


            SurfaceHolder surfaceHolder = mySurfaceView.getHolder();
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

            Log.v("ppking" ,getFilesDir()+"" );

            mySurfaceView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    camera.autoFocus(null);
                }
            });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "請允許權限,否則無法拍照", Toast.LENGTH_SHORT).show();
            } else {

            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if(camera == null){
            camera = Camera.open();
            camera.setDisplayOrientation(90);


            try {
                camera.setPreviewDisplay(surfaceHolder);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        Log.v("ppking" , " surfaceDestroyed ");

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera = null;

    }

    public void exit(View view) {
        Intent it = new Intent(this , EnterPictureActivity.class);
        startActivity(it);
        finish();
    }


    //---------------------take picture

    public void picture(View view) {
        camera.takePicture(null , null ,jpeg);
        camera.stopPreview();
    }


    private Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() {
        @Override
        public void onShutter() {
            Log.v("ppking" , " picture !!");

        }
    };

    private Camera.PictureCallback raw = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {

        }
    };

    private Camera.PictureCallback jpeg = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {

            Log.v("ppking" , " callBack !!");
            Bitmap bm  = BitmapFactory.decodeByteArray( bytes , 0 , bytes.length);

            File file = new File(getFilesDir().getPath()+"/date.jpg");

            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bm.compress(Bitmap.CompressFormat.JPEG,100,bos);
                bos.flush();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };



    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

}
