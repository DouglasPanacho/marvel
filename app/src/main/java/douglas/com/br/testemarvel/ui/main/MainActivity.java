package douglas.com.br.testemarvel.ui.main;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import douglas.com.br.testemarvel.Constants;
import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;
import douglas.com.br.testemarvel.data.remote.services.response.CharactersResponse;
import douglas.com.br.testemarvel.ui.base.BaseActivity;
import douglas.com.br.testemarvel.utils.helpers.Md5HashGenerator;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    @Inject
    MainPresenter mMainPresenter;

    HeroesDataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_main);
    }
}
