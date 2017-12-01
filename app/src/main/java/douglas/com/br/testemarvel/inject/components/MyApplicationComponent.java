package douglas.com.br.testemarvel.inject.components;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import douglas.com.br.testemarvel.MyApplication;
import douglas.com.br.testemarvel.inject.modules.MyApplicationModule;
import douglas.com.br.testemarvel.ui.heroeslist_fragment.HeroesListFragment;

/**
 * Created by douglaspanacho on 26/11/2017.
 */
@Component(modules = {AndroidInjectionModule.class, MyApplicationModule.class})
public interface MyApplicationComponent extends AndroidInjector<MyApplication> {

}