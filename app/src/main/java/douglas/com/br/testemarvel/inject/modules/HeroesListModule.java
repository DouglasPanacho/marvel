package douglas.com.br.testemarvel.inject.modules;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import douglas.com.br.testemarvel.data.AppDatabase;
import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;
import douglas.com.br.testemarvel.data.remote.models.response.CharactersResponse;
import douglas.com.br.testemarvel.inject.UserScope;
import douglas.com.br.testemarvel.ui.heroeslist_fragment.HeroListAdapter;
import douglas.com.br.testemarvel.ui.heroeslist_fragment.HeroesListPresenter;

/**
 * Created by douglaspanacho on 02/12/2017.
 */

@Module
public class HeroesListModule {

    @Provides
    @UserScope
    HeroesListPresenter providesPresenter(HeroesDataManager dataManager, AppDatabase database) {
        return new HeroesListPresenter(dataManager, database);
    }

    @Provides
    @UserScope
    HeroListAdapter providesAdapter() {
        return new HeroListAdapter(new ArrayList<CharactersResponse.Result>());
    }
}
