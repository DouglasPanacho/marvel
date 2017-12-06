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


    private int OPEN_HERO_DETAIL_REQUEST = 1;
    private List<CharactersResponse.Result> mItems = new ArrayList<>();
    private List<Integer> mFavoriteHeroes = new ArrayList<>();
    private PaginationScrollControl mPaginationHelper;
    private LinearLayoutManager mLinearLayoutManager;
    private int mPageCount = 0;
    private boolean isRefresh = false;

    @Inject
    HeroesListPresenter mPresenter;
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
        initializeRecyclerView();
        initializePaginationHelper();
        mSwipeRefresh.setOnRefreshListener(this);
        mPresenter.getFavoriteHeroesIds();
        mPresenter.getHeroes(0);
        mPresenter.attachView(this);
        return view;
    }


    public void initializeRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mHeroesRv.setLayoutManager(mLinearLayoutManager);
    }

    /**
     * Initialize the pagination helper that will be used in the recyclerview
     */
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

    /**
     * Sets the adapter and implements the interface
     */
    private void setupAdapter() {
        mAdapter.setListener(new CustomListeners.OnHeroClicked() {
            @Override
            public void OnHeroClicked(CharactersResponse.Result hero) {

            }

            @Override
            public void OnHeroFavorited(CharactersResponse.Result hero, boolean isFavorite) {
                if (isFavorite) {
                    mPresenter.addHeroDataBase(new Hero(hero.getId()));
                } else {
                    mPresenter.deleteHeroDataBase(hero.getId());
                }
            }

            //if a hero is cliked returns hero and views to use as transition
            @Override
            public void OnHeroClicked(CharactersResponse.Result hero, View image, View name) {
                Intent intent = new Intent(getActivity(), HeroDetailActivity.class);
                intent.putExtra(Constants.HERO_EXTRA, hero);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(), image, getString(R.string.transition_name));
                startActivityForResult(intent, OPEN_HERO_DETAIL_REQUEST, options.toBundle());

            }
        });
        mHeroesRv.setAdapter(mAdapter);
    }


    /**
     * Set error viewholder in the recyclerview
     */
    private void setErrorAdapter() {
        mAdapter.showError();
    }

    /**
     * Get all my heroes from api
     */
    private void getHeroes(int offset) {
        mPresenter.getHeroes(offset);
    }

    /**
     * Update the heroes adapter with the response
     */
    private void setItemsAdapter(List<CharactersResponse.Result> mItems) {
        mAdapter.updateItems(mItems, mFavoriteHeroes);
    }

    /**
     * Clear the search and set my current items
     */
    public void clearSearch() {
        if (mItems.size() > 0) {
            mAdapter.updateItems(mItems, mFavoriteHeroes);
        }
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
        if (mItems.size() <= 0) {
            setErrorAdapter();
        } else {
            mAdapter.updateItems(mItems, mFavoriteHeroes);
            showToast(getString(R.string.placeholder_no_network_label));
        }
    }

    @Override
    public void showError(String error) {

    }

    /**
     * Clear the adapter and sets the progress bar in the center of view
     */
    public void startLoading() {
        mAdapter.clearItems();
    }

    /**
     * Set the search result in the adapter
     */
    public <T> void setSearchResult(T result) {
        setItemsAdapter(((CharactersResponse) result).getData().getResults());

    }

    /**
     * Sets the result and update the page count to get the correct offset. update the pagination helper too
     */
    @Override
    public <T> void setResult(T result) {
        mPaginationHelper.setmLastPageCount(mPageCount);
        if (result instanceof CharactersResponse) {
            mPageCount++;
            mPaginationHelper.setLoading(false);
            mPaginationHelper.setmPageCount(mPageCount);
            mItems.addAll(((CharactersResponse) result).getData().getResults());
            setItemsAdapter(mItems);
        }
    }

    /**
     * Get all the heroes again, starting from offset 0
     */
    @Override
    public void onRefresh() {
        isRefresh = true;
        mPageCount = 0;
        mPaginationHelper.setmLastPageCount(mPageCount);
        mPaginationHelper.setLoading(false);
        mPaginationHelper.setmPageCount(mPageCount);
        getHeroes(mPageCount * 20);
    }


    /**
     * Set all the favorites items in the adapter to update the views
     */
    @Override
    public void setFavoritesResult(List<Integer> items) {
        mFavoriteHeroes.clear();
        mFavoriteHeroes.addAll(items);
        mAdapter.updateFavoriteItems(mFavoriteHeroes);
    }

    /**
     * Verify if my favourites have changes, if yes update the items
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_HERO_DETAIL_REQUEST && resultCode == getActivity().RESULT_OK) {
            mPresenter.getFavoriteHeroesIds();
        }
    }

}
