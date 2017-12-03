package douglas.com.br.testemarvel.ui.heroeslist_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import douglas.com.br.testemarvel.Constants;
import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.data.AppDatabase;
import douglas.com.br.testemarvel.data.local.Hero;
import douglas.com.br.testemarvel.data.remote.models.response.CharactersResponse;
import douglas.com.br.testemarvel.ui.base.BaseFragment;
import douglas.com.br.testemarvel.ui.hero_detail.HeroDetailActivity;
import douglas.com.br.testemarvel.utils.helpers.CustomListeners;

/**
 * Created by douglaspanacho on 30/11/2017.
 */

public class HeroesListFragment extends BaseFragment implements HeroesListMvpView, SwipeRefreshLayout.OnRefreshListener {


    private int mCurrentOffset = 0;
    private List<CharactersResponse.Result> mItems = new ArrayList<>();
    private boolean isSearching = false;

    @Inject
    HeroesListPresenter mPresenter;
    @Inject
    AppDatabase mDatabase;
    @Inject
    HeroesListAdapter mAdapter;
    @BindView(R.id.swiperefesh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recyclerview)
    RecyclerView mHeroesRv;

    public static HeroesListFragment newInstance() {
        HeroesListFragment heroesListFragment = new HeroesListFragment();
        return heroesListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        ButterKnife.bind(this, view);
        getApplication().getmHeroListComponent().inject(this);
        mSwipeRefresh.setOnRefreshListener(this);
        mPresenter.attachView(this);
        setupAdapter();
        mPresenter.getHeroes(mCurrentOffset);
        return view;
    }

    private void setupAdapter() {
        mAdapter.setListener(new CustomListeners.OnHeroClicked() {
            @Override
            public void OnHeroClicked(CharactersResponse.Result hero) {

            }

            @Override
            public void OnHeroFavorited(CharactersResponse.Result hero, boolean isFavorite) {
                if (isFavorite) {
                    mDatabase.userDao().insertAll(new Hero(hero.getId(), hero.getName(), hero.getThumbnail().getFullPath(), hero.getDescription()));
                } else {
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

    private void getHeroes(int offset) {
        mPresenter.getHeroes(offset);
    }

    private void setItemsAdapter(List<CharactersResponse.Result> mItems) {
        mAdapter.updateItems(mItems);
    }

    public void searchHero(String name) {
        isSearching = true;
        mPresenter.getHeroesByName(0, name);
    }

    public void clearSearch() {
        isSearching = false;
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
        if (!isSearching) {
            mItems.addAll(((CharactersResponse) result).getData().getResults());
            setItemsAdapter(mItems);
        } else {
            setItemsAdapter(((CharactersResponse) result).getData().getResults());
        }

    }

    @Override
    public void onRefresh() {
        mCurrentOffset++;
        getHeroes(mCurrentOffset);
    }
}
