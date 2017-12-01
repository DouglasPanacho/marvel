package douglas.com.br.testemarvel;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import douglas.com.br.testemarvel.data.local.PreferencesHelper;
import douglas.com.br.testemarvel.inject.components.DaggerMyApplicationComponent;

/**
 * Created by douglaspanacho on 26/11/2017.
 */

public class MyApplication extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerMyApplicationComponent.create().inject(this);
        PreferencesHelper.initializeInstance(getApplicationContext());
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}