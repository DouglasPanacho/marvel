package douglas.com.br.testemarvel.ui.main;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {
    @Inject
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_main);
    }
}
