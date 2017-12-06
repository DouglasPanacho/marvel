package douglas.com.br.testemarvel.utils.helpers;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import douglas.com.br.testemarvel.ui.heroeslist_fragment.HeroListAdapter;

/**
 * Created by douglaspanacho on 03/12/2017.
 */

public abstract class PaginationScrollControl extends RecyclerView.OnScrollListener {

    private boolean isLoading = false;
    private int mPageCount = 0;
    private int mLastPageCount = 0;
    private HeroListAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public PaginationScrollControl(HeroListAdapter mAdapter, LinearLayoutManager mLinearLayoutManager) {
        this.mAdapter = mAdapter;
        this.mLinearLayoutManager = mLinearLayoutManager;
    }

    /**
     * Checks if the end of recyclerview was reached and then tries to get more
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy > 0) {
            if (!isLoading) {
                if (mLinearLayoutManager.findLastVisibleItemPosition() == mAdapter.getmItems().size() - 1) {
                    isLoading = true;
                    if (mLastPageCount != mPageCount) {
                        isLoading();
                        loadMoreItems();
                    }
                }
            }
        }
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setmPageCount(int mPageCount) {
        this.mPageCount = mPageCount;
    }

    public void setmLastPageCount(int mLastPageCount) {
        this.mLastPageCount = mLastPageCount;
    }

    protected abstract void loadMoreItems();


    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}
