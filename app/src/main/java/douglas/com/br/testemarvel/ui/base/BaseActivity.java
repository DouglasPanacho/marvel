package douglas.com.br.testemarvel.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import douglas.com.br.testemarvel.inject.components.ActivityComponent;
import douglas.com.br.testemarvel.inject.modules.MainActivityModule;

/**
 * Created by douglaspanacho on 26/11/2017.
 */

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mComponent = DaggerActivityComponent.builder().mainActivityModule(new MainActivityModule(this)).build();
    }

    public ActivityComponent getComponent() {
        return mComponent;
    }
}
