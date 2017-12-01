package douglas.com.br.testemarvel.ui.heroeslist_fragment;

import douglas.com.br.testemarvel.ui.base.BasePresenter;
import io.reactivex.disposables.Disposable;

/**
 * Created by douglaspanacho on 01/12/2017.
 */

public class HeroesListPresenter extends BasePresenter<HeroesListMvpView> {

    private Disposable mDisposable;



    @Override
    public void attachView(HeroesListMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }
}
