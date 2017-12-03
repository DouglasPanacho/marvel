package douglas.com.br.testemarvel.inject.components;

import javax.inject.Singleton;

import dagger.Component;
import douglas.com.br.testemarvel.data.AppDatabase;
import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;
import douglas.com.br.testemarvel.inject.modules.MyApplicationModule;

/**
 * Created by douglaspanacho on 26/11/2017.
 */
@Singleton
@Component(modules = {MyApplicationModule.class})
public interface MyApplicationComponent {

    // void inject(MyService service);

    HeroesDataManager providesHeroDataMAnager();

    AppDatabase provideHeroesDataBaseHelper();
}