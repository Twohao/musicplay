package com.guocheng.lingtingyinyue.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by svn on 24/2/16.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    public static final String TAG = FragmentAdapter.class.toString();
    private List<Fragment> fragmentList;
    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList == null ? null : fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 :fragmentList.size();
    }

}
