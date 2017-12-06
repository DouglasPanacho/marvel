package douglas.com.br.testemarvel;

import android.app.Application;

import douglas.com.br.testemarvel.inject.components.DaggerHeroDetailComponent;
import douglas.com.br.testemarvel.inject.components.DaggerHeroesListComponent;
import douglas.com.br.testemarvel.inject.components.DaggerMyApplicationComponent;
import douglas.com.br.testemarvel.inject.components.HeroDetailComponent;
import douglas.com.br.testemarvel.inject.components.HeroesListComponent;
import douglas.com.br.testemarvel.inject.components.MyApplicationComponent;
import douglas.com.br.testemarvel.inject.modules.MyApplicationModule;

/**
 * Created by douglaspanacho on 26/11/2017.
 */

public class MyApplication extends Application {

    private MyApplicationComponent myApplicationComponent;
    private HeroesListComponent mHeroListComponent;
    private HeroDetailComponent mHeroDetailComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        myApplicationComponent = DaggerMyApplicationComponent.builder().myApplicationModule(new MyApplicationModule(this)).build();
        mHeroListComponent = DaggerHeroesListComponent.builder().myApplicationComponent(myApplicationComponent).build();
        mHeroDetailComponent = DaggerHeroDetailComponent.builder().myApplicationComponent(myApplicationComponent).build();
    }

    public MyApplicationComponent getMyApplicationComponent() {
        return myApplicationComponent;
    }

    public HeroesListComponent getmHeroListComponent() {
        return mHeroListComponent;
    }

    public HeroDetailComponent getmHeroDetailComponent() {
        return mHeroDetailComponent;
    }
}