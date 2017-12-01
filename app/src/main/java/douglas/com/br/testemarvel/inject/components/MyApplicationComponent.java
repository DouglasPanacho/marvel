package douglas.com.br.testemarvel.inject.components;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import douglas.com.br.testemarvel.MyApplication;
import douglas.com.br.testemarvel.inject.modules.MyApplicationModule;

/**
 * Created by douglaspanacho on 26/11/2017.
 */
@Component(modules = { AndroidInjectionModule.class, MyApplicationModule.class})
public interface MyApplicationComponent extends AndroidInjector<MyApplication> {
}