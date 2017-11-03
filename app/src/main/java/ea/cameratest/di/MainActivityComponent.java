package ea.cameratest.di;

import dagger.Component;
import ea.cameratest.MainActivity.MainActivity;

/**
 * Created by EA on 2017/11/02.
 */

@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
