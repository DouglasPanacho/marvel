package douglas.com.br.testemarvel.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;
import douglas.com.br.testemarvel.inject.components.DaggerActivityComponent;
import douglas.com.br.testemarvel.inject.modules.MainActivityModule;
import douglas.com.br.testemarvel.ui.base.BaseActivity;

import douglas.com.br.testemarvel.ui.favorites_fragment.FavoritesFragment;
import douglas.com.br.testemarvel.ui.heroeslist_fragment.HeroesListFragment;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private HeroesListFragment mHeroesListFragment;
    private FavoritesFragment mFavoritesListFragment;
    @Inject
     MainPagerAdapter mMainPagerAdapter;

    @Inject
    HeroesDataManager mDataManager;
    @BindView(R.id.main_bottom_navigation)
    BottomNavigationView mBotttomNavigationView;
    @BindView(R.id.main_viewpager)
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerActivityComponent.builder().mainActivityModule(new MainActivityModule(this)).build().inject(this);
        setupViewPagerAdapter();
        mBotttomNavigationView.setOnNavigationItemSelectedListener(this);
    }


    /**
     * used to setup my fragments inside viewpager
     */
    public void setupViewPagerAdapter() {
        if (mHeroesListFragment == null) {
            mHeroesListFragment = HeroesListFragment.newInstance();
        }
        if (mFavoritesListFragment == null) {
            mFavoritesListFragment = FavoritesFragment.newInstance();
        }
        mMainPagerAdapter.addFragment(mHeroesListFragment);
        mMainPagerAdapter.addFragment(mFavoritesListFragment);
        mViewPager.setAdapter(mMainPagerAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.heroes_list:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.favorites_list:
                mViewPager.setCurrentItem(1);
                break;
        }
        return true;
    }
}
