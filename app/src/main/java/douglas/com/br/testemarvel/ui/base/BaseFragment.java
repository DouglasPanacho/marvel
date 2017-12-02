package douglas.com.br.testemarvel.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import douglas.com.br.testemarvel.MyApplication;
import douglas.com.br.testemarvel.inject.components.HeroesListComponent;

/**
 * Created by douglaspanacho on 02/12/2017.
 */

public class BaseFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public MyApplication getApplication() {
        return ((MyApplication) getActivity().getApplication());
    }

}
