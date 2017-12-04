package douglas.com.br.testemarvel.ui.hero_detail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
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
import douglas.com.br.testemarvel.ui.base.BaseActivity;

/**
 * Created by douglaspanacho on 02/12/2017.
 */

public class HeroDetailActivity extends BaseActivity implements HeroDetailMvpView {

    @Inject
    HeroDetailPresenter mPresenter;

    @BindView(R.id.hero_detail_im)
    ImageView mHeroIm;
    @BindView(R.id.hero_detail_description_tv)
    TextView mHeroDescriptionTv;

    private CharactersResponse.Result mHeroItem;
    private MenuItem mFavoriteMenuItem;
    private boolean isDatabaseUpdated = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplication()).getmHeroDetailComponent().inject(this);
        setContentView(R.layout.activity_hero_detail);
        ButterKnife.bind(this);
        mPresenter.attachView(this);
        getExtras();
    }

    public void getExtras() {
        if (getIntent().hasExtra(Constants.HERO_EXTRA)) {
            mHeroItem = getIntent().getParcelableExtra(Constants.HERO_EXTRA);
            bind();
        }
    }


    public void bind() {
        Glide.with(this).load(mHeroItem.getThumbnail().getFullPath()).apply(new RequestOptions().dontTransform()).into(mHeroIm);
        setToolbar(mHeroItem.getName(), true);
        mHeroDescriptionTv.setText(mHeroItem.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hero_detail_menu, menu);
        mFavoriteMenuItem = menu.findItem(R.id.favorite);
        mPresenter.verifyIsHeroFavorite(mHeroItem.getId());
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
    public void finish() {
        if (isDatabaseUpdated) {
            setResult(RESULT_OK);
        }
        super.finish();
    }
}
