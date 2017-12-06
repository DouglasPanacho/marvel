package douglas.com.br.testemarvel;

import org.junit.Test;
import org.mockito.Mock;

import douglas.com.br.testemarvel.data.AppDatabase;
import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;
import douglas.com.br.testemarvel.ui.heroeslist_fragment.HeroesListMvpView;
import douglas.com.br.testemarvel.ui.heroeslist_fragment.HeroesListPresenter;

/**
 * Created by douglaspanacho on 06/12/2017.
 */

public class HeroListPresenterTest {

    @Mock
    HeroesListMvpView mvpView;
    @Mock
    AppDatabase mAppDataBase;
    @Mock
    HeroesDataManager mDataManager;

    @Test
    public void testPresenter() {

    }
}
