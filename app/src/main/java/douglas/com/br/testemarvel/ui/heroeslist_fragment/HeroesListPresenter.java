package douglas.com.br.testemarvel.ui.heroeslist_fragment;

import javax.inject.Inject;

import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;
import douglas.com.br.testemarvel.data.remote.services.response.CharactersResponse;
import douglas.com.br.testemarvel.ui.base.BasePresenter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by douglaspanacho on 01/12/2017.
 */

public class HeroesListPresenter extends BasePresenter<HeroesListMvpView> {

    private HeroesListMvpView mMvpView;

    HeroesDataManager mDataManager;

    private Disposable mDisposable;

    public HeroesListPresenter(HeroesDataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(HeroesListMvpView mvpView) {
        super.attachView(mvpView);
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }


    public void getHeroes(int offset) {
        mDataManager.getCharacters(offset).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CharactersResponse>() {
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

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
