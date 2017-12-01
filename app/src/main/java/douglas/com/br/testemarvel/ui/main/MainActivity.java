package douglas.com.br.testemarvel.ui.main;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import douglas.com.br.testemarvel.Constants;
import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.ui.base.BaseActivity;
import douglas.com.br.testemarvel.utils.helpers.Md5HashGenerator;

public class MainActivity extends BaseActivity {
    @Inject
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_main);
        Log.i("teste", Md5HashGenerator.generateMd5(Constants.PRIVATE_KEY + Constants.PUBLIC_KEY));
    }
}
