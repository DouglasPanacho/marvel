package douglas.com.br.testemarvel.ui.hero_detail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import douglas.com.br.testemarvel.Constants;
import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.data.remote.models.response.CharactersResponse;
import douglas.com.br.testemarvel.ui.base.BaseActivity;

/**
 * Created by douglaspanacho on 02/12/2017.
 */

public class HeroDetailActivity extends BaseActivity {

    @BindView(R.id.hero_detail_im)
    ImageView mHeroIm;
    @BindView(R.id.hero_detail_description_tv)
    TextView mHeroDescriptionTv;

    private CharactersResponse.Result mHeroItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);
        ButterKnife.bind(this);
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


}
