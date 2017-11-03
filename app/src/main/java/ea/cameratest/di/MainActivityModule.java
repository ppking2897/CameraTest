package ea.cameratest.di;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;

import dagger.Module;
import dagger.Provides;
import ea.cameratest.CameraPermission;

@Module
public class MainActivityModule {

    private Activity mActivity;
    private Context mContext;
    private Camera camera;


    public MainActivityModule(Activity activity , Context context){
        this.mActivity = activity;
        this.mContext = context;
    }

    @Provides
    public CameraPermission providesCameraPermission(){
        return new CameraPermission(mActivity , mContext);
    }


}
