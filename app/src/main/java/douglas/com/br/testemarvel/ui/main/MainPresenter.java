package douglas.com.br.testemarvel.ui.main;

import javax.inject.Inject;

import douglas.com.br.testemarvel.data.AppDatabase;
import douglas.com.br.testemarvel.data.remote.models.response.CharactersResponse;
import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;
import douglas.com.br.testemarvel.ui.base.BasePresenter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by douglaspanacho on 26/11/2017.
 */

public class MainPresenter extends BasePresenter<MainMvpView> {

    /**
     * Both HeroesDataManager and AppDatabase are setted using Dagger
     */
    HeroesDataManager mDataManager;
    AppDatabase mDataBase;
    private MainMvpView mMvpView;
    private Disposable mDisposable;


    @Inject
    public MainPresenter(HeroesDataManager dataManager, AppDatabase database) {
        this.mDataManager = dataManager;
        this.mDataBase = database;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    /**
     * Gets the hero by name
     */
    public void getHeroesByName(int offset, String name) {
        mDataManager.getCharacters(offset, name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CharactersResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(CharactersResponse charactersResponse) {
                mMvpView.setResult(charactersResponse);
            }

            @Override
            public void onError(Throwable e) {
                mMvpView.showError();
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
