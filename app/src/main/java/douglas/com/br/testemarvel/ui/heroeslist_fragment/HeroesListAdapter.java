package douglas.com.br.testemarvel.ui.heroeslist_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.data.remote.services.response.CharactersResponse;

/**
 * Created by douglaspanacho on 02/12/2017.
 */

public class HeroesListAdapter extends RecyclerView.Adapter {

    private List<CharactersResponse.Result> mItems;
    private int TYPE_LEFT = 0, TYPE_RIGHT = 1, TYPE_EMPTY = 2, TYPE_LOADING = 3;


    public HeroesListAdapter(List<CharactersResponse.Result> mItems) {
        this.mItems = mItems;
    }

    //update mitems and notify that the data has changed
    public void updateItems(List<CharactersResponse.Result> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void clearItems() {
        mItems = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.size() == 0) {
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
        //todo colocar viewtyw
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

        public HeroesListAdapteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            Glide.with(itemView.getContext()).load(mItems.get(position).getThumbnail().getFullPath()).into(mHeroIm);
            mHeroNameTv.setText(mItems.get(position).getName());
        }
    }

    //todo colcar em uma clase separa, erro tambem
    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
