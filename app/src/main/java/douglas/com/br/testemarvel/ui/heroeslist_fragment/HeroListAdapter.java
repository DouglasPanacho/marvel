package douglas.com.br.testemarvel.ui.heroeslist_fragment;

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
    private boolean hasError = false;
    private int TYPE_LEFT = 0, TYPE_RIGHT = 1, TYPE_ERROR = 2, TYPE_LOADING = 3;


    public HeroListAdapter(List<CharactersResponse.Result> mItems, List<Integer> favoriteItemsIds) {
        this.mItems = mItems;
        this.mFavoriteItemsIds = favoriteItemsIds;
    }

    public List<CharactersResponse.Result> getmItems() {
        return mItems;
    }

    /**
     * Used to show loader in the end of page
     */
    public void showLoader() {
        hasError = false;
        isLoading = true;
        mItems.add(new CharactersResponse.Result());
        notifyDataSetChanged();
    }

    /**
     * Used to show error in the view if something is wrong
     */
    public void showError() {
        hasError = true;
        mItems.clear();
        mItems.add(new CharactersResponse.Result());
        notifyDataSetChanged();
    }

    public void setListener(CustomListeners.OnHeroClicked listener) {
        mListener = listener;
    }

    /**
     * Update all items, both heroes and favorites and notify if the data has changed
     */
    public void updateItems(List<CharactersResponse.Result> items, List<Integer> favoriteItemsIds) {
        isLoading = false;
        hasError = false;
        mItems.clear();
        mItems.addAll(items);
        mFavoriteItemsIds = favoriteItemsIds;
        notifyDataSetChanged();
    }

    /**
     * Update favorites and notify if the data has changed
     */
    public void updateFavoriteItems(List<Integer> mFavoriteItemsIds) {
        this.mFavoriteItemsIds.addAll(mFavoriteItemsIds);
        notifyDataSetChanged();
    }

    /**
     * Clear all items from adapter
     */
    public void clearItems() {
        isLoading = true;
        mItems = new ArrayList<>();
        notifyDataSetChanged();
    }

    /**
     * Get the correct view type
     */
    @Override
    public int getItemViewType(int position) {
        if (hasError) {
            return TYPE_ERROR;
        } else if (isLoading && mItems.size() - 1 == position || isLoading && mItems.size() == 0) {
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
        } else if (viewType == TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_placeholder, parent, false);
            return new LoadingViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_error, parent, false);
            return new ErrorViewHolder(view);
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

    /**
     * Returns the mitems size if > 0, else returns 1 to use this item as loader or error
     */
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

        /**
         * Bind all my info
         */
        public void bind(final int position) {
            Glide.with(itemView.getContext()).load(mItems.get(position).getThumbnail().getFullPath()).into(mHeroIm);
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

    public class ErrorViewHolder extends RecyclerView.ViewHolder {

        public ErrorViewHolder(View itemView) {
            super(itemView);
        }

    }
}
