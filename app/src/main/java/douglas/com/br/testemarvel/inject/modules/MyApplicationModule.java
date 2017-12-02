package douglas.com.br.testemarvel.inject.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import douglas.com.br.testemarvel.inject.ApplicationContext;


/**
 * Created by douglaspanacho on 26/11/2017.
 */

@Module
public class MyApplicationModule {

    protected final Application mApplication;

    public MyApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }


}