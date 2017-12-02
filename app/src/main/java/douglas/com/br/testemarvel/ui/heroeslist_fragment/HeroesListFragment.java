package douglas.com.br.testemarvel.ui.heroeslist_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import douglas.com.br.testemarvel.MyApplication;
import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;
import douglas.com.br.testemarvel.data.remote.services.response.CharactersResponse;
import douglas.com.br.testemarvel.ui.base.BaseFragment;
import douglas.com.br.testemarvel.ui.main.MainPagerAdapter;

/**
 * Created by douglaspanacho on 30/11/2017.
 */

public class HeroesListFragment extends BaseFragment implements HeroesListMvpView, SwipeRefreshLayout.OnRefreshListener {


    private int mCurrentOffset = 0;

    @Inject
    HeroesListPresenter mPresenter;
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
        mHeroesRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mHeroesRv.setAdapter(mAdapter);
    }

    private void getHeroes(int offset) {
        mPresenter.getHeroes(offset);
    }

    private void setItemsAdapter(CharactersResponse mItems) {
        mAdapter.updateItems(mItems.getData().getResults());
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
        setItemsAdapter((CharactersResponse) result);

    }

    @Override
    public void onRefresh() {
        mCurrentOffset++;
        getHeroes(mCurrentOffset);
    }
}
