package douglas.com.br.testemarvel.ui.heroeslist_fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.data.local.Hero;
import douglas.com.br.testemarvel.data.remote.models.response.CharactersResponse;
import douglas.com.br.testemarvel.utils.helpers.CustomListeners;

/**
 * Created by douglaspanacho on 03/12/2017.
 */

public class HeroListAdapter extends RecyclerView.Adapter {

    private List<CharactersResponse.Result> mItems;
    private List<Integer> mFavoriteItemsIds;
    private CustomListeners.OnHeroClicked mListener;
    private boolean isLoading = true;
    private int TYPE_LEFT = 0, TYPE_RIGHT = 1, TYPE_EMPTY = 2, TYPE_LOADING = 3;


    public HeroListAdapter(List<CharactersResponse.Result> mItems, List<Integer> favoriteItemsIds) {
        this.mItems = mItems;
        this.mFavoriteItemsIds = favoriteItemsIds;
    }

    public List<CharactersResponse.Result> getmItems() {
        return mItems;
    }

    //used to show loader in the end of page
    public void showLoader() {
        mItems.add(new CharactersResponse.Result());
        isLoading = true;
        notifyDataSetChanged();
    }

    public void setListener(CustomListeners.OnHeroClicked listener) {
        mListener = listener;
    }

    //update mitems and notify that the data has changed
    public void updateItems(List<CharactersResponse.Result> items, List<Integer> favoriteItemsIds) {
        isLoading = false;
        mItems.clear();
        mItems.addAll(items);
        mFavoriteItemsIds = favoriteItemsIds;
        notifyDataSetChanged();
    }

    public void updateFavoriteItems(List<Integer> mFavoriteItemsIds) {
        this.mFavoriteItemsIds = mFavoriteItemsIds;
    }

    //clear all items
    public void clearItems() {
        isLoading = true;
        mItems = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoading && mItems.size() - 1 == position || isLoading && mItems.size() == 0) {
            return TYPE_LOADING;
        } else {
            if (position % 2 == 0) {
                return TYPE_LEFT;
            } else {
                return TYPE_RIGHT;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LEFT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_heroes_left, parent, false);
            return new HeroesListAdapteViewHolder(view);
        } else if (viewType == TYPE_RIGHT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_heroes_right, parent, false);
            return new HeroesListAdapteViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_placeholder, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeroesListAdapteViewHolder) {
            ((HeroesListAdapteViewHolder) holder).bind(position);
        } else if (holder instanceof LoadingViewHolder) {
            ((LoadingViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size() == 0 ? 1 : mItems.size();
    }

    public class HeroesListAdapteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_hero_im)
        ImageView mHeroIm;
        @BindView(R.id.item_hero_name)
        TextView mHeroNameTv;
        @BindView(R.id.item_hero_description)
        TextView mHeroDescriptionTv;
        @BindView(R.id.item_hero_favorite_im)
        ImageView mFavoriteIm;

        public HeroesListAdapteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        //bind all my info
        public void bind(final int position) {
            Glide.with(itemView.getContext()).load(mItems.get(position).getThumbnail().getFullPath()).thumbnail(0.1f).into(mHeroIm);
            mHeroNameTv.setText(mItems.get(position).getName());
            if (!mItems.get(position).getDescription().isEmpty()) {
                mHeroDescriptionTv.setVisibility(View.VISIBLE);
                mHeroDescriptionTv.setText(mItems.get(position).getDescription());
            } else {
                mHeroDescriptionTv.setVisibility(View.GONE);
            }
            if (mFavoriteItemsIds.contains(mItems.get(position).getId())) {
                mFavoriteIm.setSelected(true);
            } else {
                mFavoriteIm.setSelected(false);
            }
            mFavoriteIm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mFavoriteIm.isSelected()) {
                        mListener.OnHeroFavorited(mItems.get(position), false);
                        mFavoriteIm.setSelected(false);
                    } else {
                        mListener.OnHeroFavorited(mItems.get(position), true);
                        mFavoriteIm.setSelected(true);
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.OnHeroClicked(mItems.get(position), mHeroIm, mHeroNameTv);
                }
            });
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.loading_container_ll)
        LinearLayout mContainerll;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind() {
            if (mItems.size() > 0) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, (int) itemView.getContext().getResources().getDimension(R.dimen.vertical_margin), 0, 0);
                mContainerll.setLayoutParams(layoutParams);
            }
        }

    }
}
