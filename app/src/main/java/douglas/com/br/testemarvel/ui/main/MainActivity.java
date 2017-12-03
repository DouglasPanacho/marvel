package douglas.com.br.testemarvel.ui.main;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import douglas.com.br.testemarvel.MyApplication;
import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;
import douglas.com.br.testemarvel.inject.components.DaggerMainActvityComponent;
import douglas.com.br.testemarvel.inject.modules.MainActivityModule;
import douglas.com.br.testemarvel.ui.base.BaseActivity;

import douglas.com.br.testemarvel.ui.base.MvpView;
import douglas.com.br.testemarvel.ui.heroeslist_fragment.HeroesListFragment;


public class MainActivity extends BaseActivity implements MainMvpView, BottomNavigationView.OnNavigationItemSelectedListener {


    private HeroesListFragment mHeroesListFragment;

    @Inject
    MainPagerAdapter mMainPagerAdapter;
    @Inject
    MainPresenter mPresenter;
    @Inject
    HeroesDataManager mDataManager;
    @BindView(R.id.main_bottom_navigation)
    BottomNavigationView mBotttomNavigationView;
    @BindView(R.id.main_viewpager)
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainActvityComponent.builder().myApplicationComponent(((MyApplication) getApplication()).getMyApplicationComponent())
                .mainActivityModule(new MainActivityModule(this)).build().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar("TesteMarvel");
        setupViewPagerAdapter();
        mPresenter.attachView(this);
        mBotttomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mHeroesListFragment.startLoading();
                mPresenter.getHeroesByName(0, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                mHeroesListFragment.clearSearch();
                return true;
            }
        });
        return true;
    }

    /**
     * used to setup my fragments inside viewpager
     */
    public void setupViewPagerAdapter() {
        if (mHeroesListFragment == null) {
            mHeroesListFragment = HeroesListFragment.newInstance();
        }
        mMainPagerAdapter.addFragment(mHeroesListFragment);
        mViewPager.setAdapter(mMainPagerAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.heroes_list:
                mViewPager.setCurrentItem(0);
                break;
        }
        return true;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public <T> void setResult(T result) {
        mHeroesListFragment.setSearchResult(result);
    }
}
