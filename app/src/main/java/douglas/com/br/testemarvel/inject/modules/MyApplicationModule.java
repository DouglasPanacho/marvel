package douglas.com.br.testemarvel.inject.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;
import douglas.com.br.testemarvel.data.remote.services.HeroesService;
import douglas.com.br.testemarvel.ui.main.MainActivity;

/**
 * Created by douglaspanacho on 26/11/2017.
 */

@Module
public abstract class MyApplicationModule {
    @ContributesAndroidInjector
    abstract MainActivity contributeActivityInjector();





}