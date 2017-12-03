package douglas.com.br.testemarvel.ui.favorites_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import douglas.com.br.testemarvel.MyApplication;
import douglas.com.br.testemarvel.data.AppDatabase;
import douglas.com.br.testemarvel.data.HeroDatabaseHelper;
import douglas.com.br.testemarvel.data.local.Hero;
import douglas.com.br.testemarvel.data.local.HeroDao;
import douglas.com.br.testemarvel.ui.heroeslist_fragment.HeroesListFragment;
import io.reactivex.FlowableSubscriber;
import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by douglaspanacho on 02/12/2017.
 */

public class FavoritesFragment extends Fragment {

    @Inject
    FavoritesPrensenter mPresenter;
    @Inject
    AppDatabase mDataBase;

    public static FavoritesFragment newInstance() {
        FavoritesFragment favoritesFragment = new FavoritesFragment();
        return favoritesFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MyApplication) getActivity().getApplication()).getmFavoritesComponent().inject(this);
//        mDb = AppDatabase.getAppDatabase(getContext());
//        HeroDatabaseHelper.getHeroes(mDb).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MaybeObserver<List<Hero>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onSuccess(List<Hero> heroes) {
//                heroes.toString();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.toString();
//            }
//
//            @Override
//            public void onComplete() {
//                Log.i("teste", "teste");
//            }
//        });
//        HeroDatabaseHelper.getHero(mDataBase, 2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MaybeObserver<Hero>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onSuccess(Hero heroe) {
//                heroe.toString();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.toString();
//            }
//
//            @Override
//            public void onComplete() {
//                Log.i("teste", "teste");
//            }
//        });
        HeroDatabaseHelper.getHeroflow(mDataBase, 2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new FlowableSubscriber<Hero>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Hero hero) {
                hero.toString();
            }

            @Override
            public void onError(Throwable t) {
                t.toString();
            }

            @Override
            public void onComplete() {

            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
