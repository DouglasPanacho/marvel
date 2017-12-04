package douglas.com.br.testemarvel.ui.heroeslist_fragment;

import java.util.List;

import douglas.com.br.testemarvel.data.AppDatabase;
import douglas.com.br.testemarvel.data.local.Hero;
import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;
import douglas.com.br.testemarvel.data.remote.models.response.CharactersResponse;
import douglas.com.br.testemarvel.ui.base.BasePresenter;
import io.reactivex.MaybeObserver;
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
    AppDatabase mDataBase;

    private Disposable mDisposable;

    public HeroesListPresenter(HeroesDataManager dataManager, AppDatabase database) {
        this.mDataManager = dataManager;
        this.mDataBase = database;
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
                mMvpView.hideProgress();
                mMvpView.setResult(charactersResponse);
            }

            @Override
            public void onError(Throwable e) {
                mMvpView.hideProgress();
            }

            @Override
            public void onComplete() {

            }
        });
    }

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

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void addHeroDataBase(Hero hero) {
        mDataBase.userDao().insertAll(new Hero(hero.getId()));
    }

    public void deleteHeroDataBase(int heroId) {
        mDataBase.userDao().deleteHero(heroId);
    }


    public void getFavoriteHeroes() {
        mDataBase.userDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MaybeObserver<List<Hero>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Hero> heroes) {

            }

            @Override
            public void onError(Throwable e) {
                e.toString();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getFavoriteHeroesIds() {
        mDataBase.userDao().getAllIds().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MaybeObserver<List<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Integer> heroes) {
                mMvpView.setFavoritesResult(heroes);
            }

            @Override
            public void onError(Throwable e) {
                e.toString();
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
