package douglas.com.br.testemarvel.ui.heroeslist_fragment;

import java.util.List;

import douglas.com.br.testemarvel.ui.base.MvpView;

/**
 * Created by douglaspanacho on 01/12/2017.
 */

public interface HeroesListMvpView extends MvpView {

    void setFavoritesResult(List<Integer> items);
}
