package douglas.com.br.testemarvel.ui.hero_detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.data.remote.models.response.GeneralResponse;
import douglas.com.br.testemarvel.data.remote.models.response.HeaderModel;
import douglas.com.br.testemarvel.data.remote.models.response.HeroDetailsModel;
import douglas.com.br.testemarvel.utils.helpers.CustomListeners;

/**
 * Created by douglaspanacho on 04/12/2017.
 */

public class HeroItemsAdapter extends RecyclerView.Adapter {

    private int TYPE_ITEM = 0, TYPE_HEADER = 1;

    private HeroDetailsModel mItems;
    private CustomListeners.OnItemClickedListener mListener;

    public void updateItems(HeroDetailsModel items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void setmListener(CustomListeners.OnItemClickedListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.getResult().get(position) instanceof GeneralResponse.Result) {
            return TYPE_ITEM;
        } else {
            return TYPE_HEADER;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_hero_detail, parent, false);
            return new HeroItemsAdapterViewHolder(view);
        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
            return new HeroItemsAdapterHeaderViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeroItemsAdapterViewHolder) {
            ((HeroItemsAdapterViewHolder) holder).bind(position);
        } else if (holder instanceof HeroItemsAdapterHeaderViewHolder) {
            ((HeroItemsAdapterHeaderViewHolder) holder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.getResult().size();
    }

    public class HeroItemsAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_hero_detail_im)
        ImageView mItemIm;
        @BindView(R.id.item_hero_detail_title)
        TextView mItemTitleTv;

        public HeroItemsAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /**
         * Bind all my info
         */
        public void bind(final int position) {
            if (mItems.getResult().get(position) instanceof GeneralResponse.Result) {
                Glide.with(itemView.getContext()).load(((GeneralResponse.Result) mItems.getResult().get(position)).getThumbnail().getFullPath())
                        .apply(new RequestOptions().placeholder(R.drawable.placeholder_im)).into(mItemIm);
                mItemTitleTv.setText(((GeneralResponse.Result) mItems.getResult().get(position)).getTitle());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.OnItemClicked((GeneralResponse.Result) mItems.getResult().get(position));
                    }
                });
            }
        }
    }

    public class HeroItemsAdapterHeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_header_title_tv)
        TextView mTitleTv;

        public HeroItemsAdapterHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /**
         * Bind all my info
         */
        public void bind(int position) {
            if (mItems.getResult().get(position) instanceof HeaderModel) {
                mTitleTv.setText(((HeaderModel) mItems.getResult().get(position)).getSectionName());
            }
        }
    }
}
