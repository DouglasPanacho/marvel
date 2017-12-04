package douglas.com.br.testemarvel.ui.hero_detail;

import douglas.com.br.testemarvel.data.AppDatabase;
import douglas.com.br.testemarvel.data.local.Hero;
import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;
import douglas.com.br.testemarvel.ui.base.BasePresenter;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by douglaspanacho on 02/12/2017.
 */

public class HeroDetailPresenter extends BasePresenter<HeroDetailMvpView> {


    HeroesDataManager mDataManager;
    AppDatabase mDataBase;

    private Disposable mDisposable;
    private HeroDetailMvpView mMvpView;

    public HeroDetailPresenter(HeroesDataManager dataManager, AppDatabase database) {
        this.mDataManager = dataManager;
        this.mDataBase = database;
    }

    @Override
    public void attachView(HeroDetailMvpView mvpView) {
        this.mMvpView = mvpView;
    }

    public void verifyIsHeroFavorite(int id) {
        mDataBase.userDao().getHero(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MaybeObserver<Hero>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Hero hero) {
                mMvpView.isHeroFavorite();
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

}
