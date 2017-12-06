package douglas.com.br.testemarvel.inject.modules;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import douglas.com.br.testemarvel.ui.main.MainPagerAdapter;

/**
 * Created by douglaspanacho on 02/12/2017.
 * Used to provide Activity
 */

@Module
public class MainActivityModule {

    AppCompatActivity mMainActivity;

    public MainActivityModule(AppCompatActivity activity) {
        this.mMainActivity = activity;
    }

    @Provides
    MainPagerAdapter providePagerAdapter() {
        return new MainPagerAdapter(mMainActivity.getSupportFragmentManager());
    }

    @Provides
    AppCompatActivity provideMainActivity() {
        return mMainActivity;
    }

    @Provides
    FragmentManager provideFragmentManager() {
        return mMainActivity.getSupportFragmentManager();
    }

}
