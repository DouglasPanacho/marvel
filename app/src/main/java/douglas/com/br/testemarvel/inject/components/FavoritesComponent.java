package douglas.com.br.testemarvel.inject.components;

import dagger.Component;
import douglas.com.br.testemarvel.inject.UserScope;
import douglas.com.br.testemarvel.inject.modules.FavoritesModule;
import douglas.com.br.testemarvel.inject.modules.HeroesListModule;
import douglas.com.br.testemarvel.ui.favorites_fragment.FavoritesFragment;

/**
 * Created by douglaspanacho on 03/12/2017.
 */
@UserScope
@Component(dependencies = {MyApplicationComponent.class}, modules = {FavoritesModule.class, HeroesListModule.class})
public interface FavoritesComponent {

    void inject(FavoritesFragment fragment);
}
