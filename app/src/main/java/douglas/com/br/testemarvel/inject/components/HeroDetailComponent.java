package douglas.com.br.testemarvel.inject.components;

import dagger.Component;
import douglas.com.br.testemarvel.inject.UserScope;
import douglas.com.br.testemarvel.inject.modules.HeroDetailModule;
import douglas.com.br.testemarvel.ui.hero_detail.HeroDetailActivity;

/**
 * Created by douglaspanacho on 04/12/2017.
 */

@UserScope
@Component(modules = {HeroDetailModule.class}, dependencies = {MyApplicationComponent.class})
public interface HeroDetailComponent {
    void inject(HeroDetailActivity activity);
}
