package douglas.com.br.testemarvel.inject.modules;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.data.AppDatabase;
import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;
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


    @Singleton
    @Provides
    HeroesDataManager provideHeroesDataManager() {
        return new HeroesDataManager();
    }

    @Singleton
    @Provides
    AppDatabase provideHeroesDataBaseHelper() {
        return Room.databaseBuilder(mApplication.getApplicationContext(), AppDatabase.class, mApplication.getString(R.string.heroes_database_name))
                // allow queries on the main thread.
                // Don't do this on a real app! See PersistenceBasicSample for an example.
                .allowMainThreadQueries()
                .build();
    }



}