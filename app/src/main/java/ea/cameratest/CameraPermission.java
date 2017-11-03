package ea.cameratest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;


public class CameraPermission {

    private Activity mActivity;
    private Context mContext;
    private String [] chteckItem = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE ,
            //Manifest.permission.READ_EXTERNAL_STORAGE //api need 16 , default api 15,
            Manifest.permission.CAMERA
            };
    private int requestCode = 123;

    public CameraPermission(Activity activity , Context context){
        this.mActivity = activity;
        this.mContext = context;

    }
    public boolean checkPermission(){
        int cameraPermission = ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA);

        if (cameraPermission!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(mActivity, chteckItem , requestCode);
            return false;

        }else{
            return true;
        }
    }
}
