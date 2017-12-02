package douglas.com.br.testemarvel.inject.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;

/**
 * Created by douglaspanacho on 01/12/2017.
 */

@Module
public class GeneralModule {

    @Singleton
    @Provides
    HeroesDataManager provideHeroesDataManager() {
        return new HeroesDataManager();
    }


}
