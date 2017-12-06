package douglas.com.br.testemarvel.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by douglaspanacho on 02/12/2017.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager mFragmentManager;
    private List<Fragment> mItems = new ArrayList<>();

    @Inject
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }


    public void addFragment(Fragment fragment) {
        mItems.add(fragment);
    }


    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mItems.get(position);
    }
}

