package douglas.com.br.testemarvel.utils.helpers;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by douglaspanacho on 05/12/2017.
 * Used to help in the dispose process
 */

public class RxUtil {


    public static void dispose(List<Disposable> disposable) {
        for (Disposable item : disposable
                ) {
            item.dispose();
            ;
        }
    }

    public static void dispose(Disposable disposable) {
        disposable.dispose();
    }
}
