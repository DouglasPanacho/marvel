package douglas.com.br.testemarvel.inject.components;

import dagger.Component;
import douglas.com.br.testemarvel.inject.UserScope;
import douglas.com.br.testemarvel.inject.modules.HeroesListModule;
import douglas.com.br.testemarvel.ui.heroeslist_fragment.HeroesListFragment;

/**
 * Created by douglaspanacho on 02/12/2017.
 */

@UserScope
@Component(modules = {HeroesListModule.class}, dependencies = {MyApplicationComponent.class})
public interface HeroesListComponent {

    void inject(HeroesListFragment fragment);

}
