package douglas.com.br.testemarvel.inject.components;

import javax.inject.Singleton;

import dagger.Component;
import douglas.com.br.testemarvel.inject.modules.GeneralModule;
import douglas.com.br.testemarvel.inject.modules.MyApplicationModule;
import douglas.com.br.testemarvel.ui.main.MainActivity;

/**
 * Created by douglaspanacho on 26/11/2017.
 */
@Singleton
@Component(modules = {MyApplicationModule.class, GeneralModule.class})
public interface MyApplicationComponent {
    void inject(MainActivity activity);

    // void inject(MyFragment fragment);
    // void inject(MyService service);

}