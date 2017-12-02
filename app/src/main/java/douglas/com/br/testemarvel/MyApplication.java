package douglas.com.br.testemarvel;

import android.app.Application;

import douglas.com.br.testemarvel.inject.components.DaggerMyApplicationComponent;
import douglas.com.br.testemarvel.inject.components.MyApplicationComponent;

/**
 * Created by douglaspanacho on 26/11/2017.
 */

public class MyApplication extends Application {

    private MyApplicationComponent myApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplicationComponent = DaggerMyApplicationComponent.create();
    }

    public MyApplicationComponent getMyApplicationComponent() {
        return myApplicationComponent;
    }
}