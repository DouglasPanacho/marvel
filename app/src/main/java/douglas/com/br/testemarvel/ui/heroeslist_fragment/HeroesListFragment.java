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
import douglas.com.br.testemarvel.utils.helpers.PaginationScrollControl;

/**
 * Created by douglaspanacho on 30/11/2017.
 */

public class HeroesListFragment extends BaseFragment implements HeroesListMvpView, SwipeRefreshLayout.OnRefreshListener {


    private List<CharactersResponse.Result> mItems = new ArrayList<>();
    private List<Integer> mFavoriteHeroes = new ArrayList<>();
    private PaginationScrollControl mPaginationHelper;
    private LinearLayoutManager mLinearLayoutManager;
    private int mPageCount = 0;

    @Inject
    HeroesListPresenter mPresenter;
    @Inject
    AppDatabase mDatabase;
    @Inject
    HeroListAdapter mAdapter;
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
        setupAdapter();
        mSwipeRefresh.setOnRefreshListener(this);
        initializeRecyclerView();
        initializePaginationHelper();
        mPresenter.getFavoriteHeroes();
        mPresenter.getHeroes(0);
        mPresenter.attachView(this);
        return view;
    }

    public void initializeRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mHeroesRv.setLayoutManager(mLinearLayoutManager);
    }

    private void initializePaginationHelper() {
        mPaginationHelper = new PaginationScrollControl(mAdapter, mLinearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                getHeroes(mPageCount * 20);
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                mAdapter.showLoader();
                return false;
            }
        };
        mHeroesRv.addOnScrollListener(mPaginationHelper);
    }

    private void setupAdapter() {
        mAdapter.setListener(new CustomListeners.OnHeroClicked() {
            @Override
            public void OnHeroClicked(CharactersResponse.Result hero) {

            }

            @Override
            public void OnHeroFavorited(CharactersResponse.Result hero, boolean isFavorite) {
                if (isFavorite) {
                    mDatabase.userDao().insertAll(new Hero(hero.getId()));
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
        mHeroesRv.setAdapter(mAdapter);
    }

    private void getHeroes(int offset) {
        mPresenter.getHeroes(offset);
    }

    private void setItemsAdapter(List<CharactersResponse.Result> mItems) {
        mAdapter.updateItems(mItems, mFavoriteHeroes);
    }


    public void clearSearch() {
        mAdapter.updateItems(mItems, mFavoriteHeroes);
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

    public void startLoading() {
        mAdapter.clearItems();
    }

    public <T> void setSearchResult(T result) {
        setItemsAdapter(((CharactersResponse) result).getData().getResults());

    }

    @Override
    public <T> void setResult(T result) {
        mPaginationHelper.setmLastPageCount(mPageCount);
        if (result instanceof CharactersResponse) {
            mAdapter.removeLoader();
            mPageCount++;
            mPaginationHelper.setLoading(false);
            mPaginationHelper.setmPageCount(mPageCount);
            mItems.addAll(((CharactersResponse) result).getData().getResults());
            setItemsAdapter(mItems);
        }
    }

    @Override
    public void onRefresh() {
        getHeroes(mPageCount * 20);
    }

    @Override
    public void setFavoritesResult(List<Hero> items) {
        for (Hero item : items
                ) {
            mFavoriteHeroes.add(item.getId());
        }

    }
}
