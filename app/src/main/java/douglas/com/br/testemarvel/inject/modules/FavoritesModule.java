package douglas.com.br.testemarvel.inject.modules;

import dagger.Module;
import dagger.Provides;
import douglas.com.br.testemarvel.data.AppDatabase;
import douglas.com.br.testemarvel.inject.UserScope;
import douglas.com.br.testemarvel.ui.favorites_fragment.FavoritesPrensenter;

/**
 * Created by douglaspanacho on 03/12/2017.
 */

@Module
public class FavoritesModule {

    @UserScope
    @Provides
    FavoritesPrensenter providesFavoritesPresenter(AppDatabase db) {
        return new FavoritesPrensenter(db);
    }
}
