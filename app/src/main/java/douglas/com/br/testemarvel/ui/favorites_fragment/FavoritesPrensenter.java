package douglas.com.br.testemarvel.ui.favorites_fragment;

import android.util.Log;

import java.util.List;

import douglas.com.br.testemarvel.data.AppDatabase;
import douglas.com.br.testemarvel.data.HeroDatabaseHelper;
import douglas.com.br.testemarvel.data.local.Hero;
import douglas.com.br.testemarvel.ui.base.BasePresenter;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by douglaspanacho on 03/12/2017.
 */

public class FavoritesPrensenter extends BasePresenter<FavoritesMvpView> {

    AppDatabase mDatabase;

    private FavoritesMvpView mMvpView;

    @Override
    public void attachView(FavoritesMvpView mvpView) {
        this.mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public FavoritesPrensenter(AppDatabase mDatabase) {
        this.mDatabase = mDatabase;
    }

    public void getHeroes() {
        HeroDatabaseHelper.getHeroes(mDatabase).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MaybeObserver<List<Hero>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Hero> heroes) {
                mMvpView.setResult(heroes);
                mMvpView.hideProgress();

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
}
