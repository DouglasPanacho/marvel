package douglas.com.br.testemarvel.inject.components;

import dagger.Component;
import douglas.com.br.testemarvel.inject.UserScope;
import douglas.com.br.testemarvel.inject.modules.MainActivityModule;
import douglas.com.br.testemarvel.ui.main.MainActivity;

/**
 * Created by douglaspanacho on 02/12/2017.
 */
@UserScope
@Component(modules = {MainActivityModule.class}, dependencies = {MyApplicationComponent.class})
public interface MainActvityComponent {
    void inject(MainActivity activity);

}
