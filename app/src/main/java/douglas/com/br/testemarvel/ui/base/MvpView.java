package douglas.com.br.testemarvel.ui.base;

/**
 * Created by douglaspanacho on 26/11/2017.
 */

public interface MvpView {

    void showProgress();

    void hideProgress();

    void showError();

    void showError(String error);

    <T> void setResult(T result);


}
