package douglas.com.br.testemarvel.ui.favorites_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import douglas.com.br.testemarvel.Constants;
import douglas.com.br.testemarvel.MyApplication;
import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.data.AppDatabase;
import douglas.com.br.testemarvel.data.HeroDatabaseHelper;
import douglas.com.br.testemarvel.data.local.Hero;
import douglas.com.br.testemarvel.data.local.HeroDao;
import douglas.com.br.testemarvel.data.remote.models.response.CharactersResponse;
import douglas.com.br.testemarvel.ui.adapters.HeroesAdapter;
import douglas.com.br.testemarvel.ui.hero_detail.HeroDetailActivity;
import douglas.com.br.testemarvel.ui.heroeslist_fragment.HeroesListFragment;
import douglas.com.br.testemarvel.utils.helpers.CustomListeners;
import io.reactivex.FlowableSubscriber;
import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by douglaspanacho on 02/12/2017.
 */

public class FavoritesFragment extends Fragment implements FavoritesMvpView, SwipeRefreshLayout.OnRefreshListener {

    private int mCurrentOffset = 0;
    private List<CharactersResponse.Result> mItems = new ArrayList<>();
    private boolean isSearching = false;

    @Inject
    FavoritesPrensenter mPresenter;
    @Inject
    AppDatabase mDatabase;
    @Inject
    HeroesAdapter mAdapter;
    @Inject
    AppDatabase mDataBase;
    @BindView(R.id.swiperefesh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recyclerview)
    RecyclerView mHeroesRv;


    public static FavoritesFragment newInstance() {
        FavoritesFragment favoritesFragment = new FavoritesFragment();
        return favoritesFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        ButterKnife.bind(this, view);
        ((MyApplication) getActivity().getApplication()).getmFavoritesComponent().inject(this);
        setupAdapter();
        mSwipeRefresh.setOnRefreshListener(this);
        mPresenter.attachView(this);
        mPresenter.getHeroes();
        return view;
    }

    private void setupAdapter() {
        mAdapter.setListener(new CustomListeners.OnHeroClicked() {
            @Override
            public void OnHeroClicked(CharactersResponse.Result hero) {

            }

            @Override
            public void OnHeroFavorited(CharactersResponse.Result hero, boolean isFavorite) {
                if (!isFavorite) {
                    mDatabase.userDao().deleteHero(hero.getId());
                }
            }


            @Override
            public void OnHeroClicked(CharactersResponse.Result hero, View image, View name) {
                Intent intent = new Intent(getActivity(), HeroDetailActivity.class);
                intent.putExtra(Constants.HERO_EXTRA, hero);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(), image, getString(R.string.transition_name));
                startActivity(intent, options.toBundle());

            }
        });
        mHeroesRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mHeroesRv.setAdapter(mAdapter);
    }


//    private void getHeroes(int offset) {
//        mPresenter.getHeroes(offset);
//    }

    private void setItemsAdapter(List<CharactersResponse.Result> mItems) {
        mAdapter.updateItems(mItems);
    }


    @Override

    public void showProgress() {
        mSwipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public <T> void setResult(T result) {
        createCharactersResponse((List<Hero>) result);
//            mItems.addAll(((CharactersResponse) result).getData().getResults());
//            setItemsAdapter(mItems);
//        } else {
//            setItemsAdapter(((CharactersResponse) result).getData().getResults());
//        }

    }

    public void createCharactersResponse(List<Hero> items) {
        mItems.clear();
        for (Hero item : items
                ) {
            mItems.add(new CharactersResponse.Result(item.getId(), item.getName(),
                    item.getDescription(), new CharactersResponse.Thumbnail(item.getPath(), item.getExtension())));
        }
        setItemsAdapter(mItems);
    }

    @Override
    public void onRefresh() {
        mPresenter.getHeroes();
    }
}
