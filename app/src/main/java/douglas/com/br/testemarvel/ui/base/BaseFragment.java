package douglas.com.br.testemarvel.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import douglas.com.br.testemarvel.MyApplication;

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

    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();

    }
}
