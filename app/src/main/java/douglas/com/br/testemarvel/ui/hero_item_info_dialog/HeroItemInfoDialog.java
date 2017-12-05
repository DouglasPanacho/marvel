package douglas.com.br.testemarvel.ui.hero_item_info_dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.data.remote.models.response.GeneralResponse;

/**
 * Created by douglaspanacho on 04/12/2017.
 */

public class HeroItemInfoDialog extends Dialog {

    private GeneralResponse.Result mItem;

    @BindView(R.id.item_detail_title_tv)
    TextView mTitleTv;
    @BindView(R.id.item_detail_creators_tv)
    TextView mCreatorsTv;
    @BindView(R.id.item_detail_description_tv)
    TextView mDescriptionTv;
    @BindView(R.id.item_detail_im)
    ImageView mDetailIm;
    @BindView(R.id.item_detail_text_tv)
    TextView mTextTv;
    @BindView(R.id.item_detail_creators_ll)
    LinearLayout mCreatorsContainerll;
    @BindView(R.id.item_detail_description_ll)
    LinearLayout mDescriptionContainerll;


    public HeroItemInfoDialog(@NonNull Context context, GeneralResponse.Result item) {
        super(context);
        this.mItem = item;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_hero_item_detail);
        ButterKnife.bind(this);
        setCancelable(true);
        if (mItem != null) {
            bind();
        } else {
            dismiss();
        }
    }

    public void bind() {
        Glide.with(getContext()).load(mItem.getThumbnail().getFullPath()).apply(new RequestOptions().placeholder(R.drawable.placeholder_im)).into(mDetailIm);
        if (mItem.getTitle() != null) {
            mTitleTv.setText(mItem.getTitle());
        } else {
            mCreatorsContainerll.setVisibility(View.GONE);
        }
        if (mItem.getCreators() != null) {
            mCreatorsTv.setText(mItem.getCreators().getCreatorsName());
        } else {
            mCreatorsContainerll.setVisibility(View.GONE);
        }
        if (mItem.getDescription() != null && !mItem.getDescription().isEmpty()) {
            mDescriptionTv.setText(mItem.getDescription());
        } else {
            mDescriptionContainerll.setVisibility(View.GONE);
        }
        if (mItem.getTextObjects() != null && mItem.getTextObjects().size() > 0) {
            mTextTv.setText(mItem.getTextObjects().get(0).getText());
        }
    }


}
