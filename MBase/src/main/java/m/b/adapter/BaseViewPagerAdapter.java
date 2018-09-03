package m.b.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by DN on 2018/6/19.
 */

public class BaseViewPagerAdapter extends FragmentPagerAdapter {
    private List<String> mPagerTitles;
    private List<Fragment> mData;

    public BaseViewPagerAdapter(FragmentManager fm, List<String> titles, List<Fragment> lists) {
        super(fm);
        this.mPagerTitles = titles;
        this.mData = lists;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mPagerTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mPagerTitles.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
