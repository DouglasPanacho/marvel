package douglas.com.br.testemarvel.ui.heroeslist_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import douglas.com.br.testemarvel.data.remote.services.HeroesDataManager;

/**
 * Created by douglaspanacho on 30/11/2017.
 */

public class HeroesListFragment extends Fragment {

    @Inject
    HeroesDataManager mDataManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
