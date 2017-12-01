package douglas.com.br.testemarvel.ui.base;

/**
 * Created by douglaspanacho on 26/11/2017.
 */

public interface Presenter <V extends MvpView>{
    void attachView(V mvpView);

    void detachView();

}
