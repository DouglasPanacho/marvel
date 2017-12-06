package douglas.com.br.testemarvel.ui.hero_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import douglas.com.br.testemarvel.Constants;
import douglas.com.br.testemarvel.MyApplication;
import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.data.local.Hero;
import douglas.com.br.testemarvel.data.remote.models.response.CharactersResponse;
import douglas.com.br.testemarvel.data.remote.models.response.GeneralResponse;
import douglas.com.br.testemarvel.data.remote.models.response.HeaderModel;
import douglas.com.br.testemarvel.data.remote.models.response.HeroDetailsModel;
import douglas.com.br.testemarvel.ui.base.BaseActivity;
import douglas.com.br.testemarvel.ui.hero_item_info_dialog.HeroItemInfoDialog;
import douglas.com.br.testemarvel.utils.helpers.CustomListeners;

/**
 * Created by douglaspanacho on 02/12/2017.
 */

public class HeroDetailActivity extends BaseActivity implements HeroDetailMvpView, CustomListeners.OnItemClickedListener {

    @Inject
    HeroDetailPresenter mPresenter;
    @Inject
    HeroItemsAdapter mAdapter;
    @BindView(R.id.hero_detail_im)
    ImageView mHeroIm;
    @BindView(R.id.hero_detail_description_tv)
    TextView mHeroDescriptionTv;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.nestedscrollview)
    NestedScrollView mNestedScrollview;
    @BindView(R.id.linearlayout_container)
    LinearLayout mLinearLayoutContainer;
    @BindView(R.id.progressbar)
    ProgressBar mProgressBar;
    @BindView(R.id.hero_detail_description_container)
    CardView mHeroDescriptionCv;

    private CharactersResponse.Result mHeroItem;
    private MenuItem mFavoriteMenuItem;
    private boolean isDatabaseUpdated = false;
    private boolean isProgressEnabled = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplication()).getmHeroDetailComponent().inject(this);
        setContentView(R.layout.activity_hero_detail);
        ButterKnife.bind(this);
        mPresenter.attachView(this);
        mRecyclerView.setNestedScrollingEnabled(false);
        showProgress();
        getExtras();
    }

    //verify if has some extra to set the hero image and des
    public void getExtras() {
        if (getIntent().hasExtra(Constants.HERO_EXTRA)) {
            mHeroItem = getIntent().getParcelableExtra(Constants.HERO_EXTRA);
            doRequests();
            bind();
        }
    }

    //get all the heroe detail like comics, series, events and stories
    public void doRequests() {
        mPresenter.getInfoDetails(mHeroItem.getId(), 3);
    }

    //bind all the view with de hero data
    public void bind() {
        Glide.with(this).load(mHeroItem.getThumbnail().getFullPath()).apply(new RequestOptions().dontTransform()).into(mHeroIm);
        setToolbar(mHeroItem.getName(), true);
        if (!mHeroItem.getDescription().isEmpty()) {
            mHeroDescriptionCv.setVisibility(View.VISIBLE);
            mHeroDescriptionTv.setText(mHeroItem.getDescription());
        } else {
            mHeroDescriptionCv.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hero_detail_menu, menu);
        mFavoriteMenuItem = menu.findItem(R.id.favorite);
        mPresenter.verifyIsHeroFavorite(mHeroItem.getId());
        return true;
    }


    //sets my adapter and defines de spansizelookup acording to the view, header or not
    public void setupAdapter(final HeroDetailsModel item) {
        if (item.getResult().size() > 0) {
            mLinearLayoutContainer.setVisibility(View.VISIBLE);
            hideProgress();
            mAdapter.updateItems(item);
            mAdapter.setmListener(this);
            GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 3);
            mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(final int position) {
                    if (item.getResult().get(position) instanceof HeaderModel) {
                        return 3;
                    } else return 1;
                }
            });
            mRecyclerView.setLayoutManager(mGridLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item == mFavoriteMenuItem) {
            if (!mFavoriteMenuItem.isChecked()) {
                isItemChecked(true);
                mFavoriteMenuItem.setChecked(true);
                mPresenter.addHeroDataBase(new Hero(mHeroItem.getId()));
                isDatabaseUpdated = true;
            } else {
                isItemChecked(false);
                mFavoriteMenuItem.setChecked(false);
                mPresenter.deleteHeroDataBase(mHeroItem.getId());
                isDatabaseUpdated = true;
            }
        } else if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        isProgressEnabled = false;
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        showToast(getString(R.string.placeholder_no_results_label));
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public <T> void setResult(T result) {
        if (((HeroDetailsModel) result).getResult().size() > 0) {
            setupAdapter((HeroDetailsModel) result);
        } else {
            showToast(getString(R.string.placeholder_no_results_label));
        }
    }

    @Override
    public void isHeroFavorite() {
        mFavoriteMenuItem.setChecked(true);
        isItemChecked(true);
    }


    //used to change icon
    public void isItemChecked(boolean isChecked) {
        if (!isChecked) {
            mFavoriteMenuItem.setIcon(R.drawable.ic_empty_star_white);
        } else {
            mFavoriteMenuItem.setIcon(R.drawable.ic_fill_star);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!isProgressEnabled) {
            hideProgress();
        }
    }

    @Override
    public void finish() {
        if (isDatabaseUpdated) {
            setResult(RESULT_OK);
        }
        super.finish();
    }


    @Override
    public void OnItemClicked(GeneralResponse.Result item) {
        HeroItemInfoDialog mDialog = new HeroItemInfoDialog(this, item);
        mDialog.show();
    }
}
