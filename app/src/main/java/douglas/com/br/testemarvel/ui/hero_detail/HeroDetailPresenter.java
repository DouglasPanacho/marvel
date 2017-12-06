package douglas.com.br.testemarvel.ui.hero_detail;

import java.util.ArrayList;
import java.util.List;

import douglas.com.br.testemarvel.data.AppDatabase;
import douglas.com.br.testemarvel.data.local.Hero;
import douglas.com.br.testemarvel.data.remote.models.response.GeneralResponse;
import douglas.com.br.testemarvel.data.remote.models.response.HeroDetailsModel;
import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;
import douglas.com.br.testemarvel.ui.base.BasePresenter;
import douglas.com.br.testemarvel.utils.helpers.RxUtil;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by douglaspanacho on 02/12/2017.
 */

public class HeroDetailPresenter extends BasePresenter<HeroDetailMvpView> {


    HeroesDataManager mDataManager;
    AppDatabase mDataBase;

    private List<Disposable> mDisposable = new ArrayList<>();
    private HeroDetailMvpView mMvpView;

    public HeroDetailPresenter(HeroesDataManager dataManager, AppDatabase database) {
        this.mDataManager = dataManager;
        this.mDataBase = database;
    }

    @Override
    public void attachView(HeroDetailMvpView mvpView) {
        this.mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        super.detachView();
        RxUtil.dispose(mDisposable);
    }

    public void verifyIsHeroFavorite(int id) {
        mDataBase.userDao().getHero(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MaybeObserver<Hero>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable.add(d);
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

    public void getInfoDetails(int id, int limit) {
        Observable.zip(mDataManager.getComics(id, limit), mDataManager.getEvents(id, limit), mDataManager.getStories(id, limit), mDataManager.getSeries(id, limit),
                new Function4<GeneralResponse, GeneralResponse, GeneralResponse, GeneralResponse, HeroDetailsModel>() {
                    @Override
                    public HeroDetailsModel apply(GeneralResponse generalResponse, GeneralResponse generalResponse2, GeneralResponse generalResponse3, GeneralResponse generalResponse4) throws Exception {
                        HeroDetailsModel heroDetalModel = new HeroDetailsModel();
                        heroDetalModel.addItem(generalResponse.getData().getResults(), 0);
                        heroDetalModel.addItem(generalResponse2.getData().getResults(), 1);
                        heroDetalModel.addItem(generalResponse3.getData().getResults(), 2);
                        heroDetalModel.addItem(generalResponse4.getData().getResults(), 3);
                        return heroDetalModel;
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HeroDetailsModel>() {

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable.add(d);
            }

            @Override
            public void onNext(HeroDetailsModel items) {
                mMvpView.setResult(items);
                mMvpView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                mMvpView.hideProgress();
                mMvpView.showError();
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
