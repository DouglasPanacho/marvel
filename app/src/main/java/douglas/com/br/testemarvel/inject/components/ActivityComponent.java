package douglas.com.br.testemarvel.inject.components;

import android.support.v4.view.PagerAdapter;

import dagger.Component;
import dagger.Module;
import douglas.com.br.testemarvel.inject.ActivityContext;
import douglas.com.br.testemarvel.inject.PerActivity;
import douglas.com.br.testemarvel.inject.modules.MainActivityModule;
import douglas.com.br.testemarvel.ui.main.MainActivity;
import douglas.com.br.testemarvel.ui.main.MainPagerAdapter;

/**
 * Created by douglaspanacho on 02/12/2017.
 */
@PerActivity
@Component(modules = {MainActivityModule.class})
public interface ActivityComponent {
    void inject(MainActivity activity);

}
