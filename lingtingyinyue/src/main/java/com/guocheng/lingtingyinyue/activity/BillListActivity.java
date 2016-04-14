package com.guocheng.lingtingyinyue.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.guocheng.lingtingyinyue.BaseActivity;
import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.fragment.BillListFragment;
import com.guocheng.lingtingyinyue.http.OkHttpTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/19.
 */
public class BillListActivity extends BaseActivity {

    @Bind(R.id.billlist_tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.vp_billlist)
    ViewPager mViewPager;
    @Bind(R.id.iv_back)
    ImageView ivBack;

    private ContentPagerAdapter mContentPagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billboard_billlist);
        ButterKnife.bind(this);

        setupTabLayout();
    }

    /**
     * 设置TabLayou参数
     */
    private void setupTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        fragmentList.add(BillListFragment.newInstance("" + 1, null));
        fragmentList.add(BillListFragment.newInstance(""+2, null));
        fragmentList.add(BillListFragment.newInstance(""+8, null));
        fragmentList.add(BillListFragment.newInstance("" + 18, null));
        fragmentList.add(BillListFragment.newInstance("" + 6, null));

        setupViewPager();
        initListener();
    }

    private void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillListActivity.this.finish();
            }
        });
    }

    /**
     * 设置ViewPager
     */
    private void setupViewPager() {
        //ViewPager关联适配器
        mContentPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mContentPagerAdapter);
        //ViewPager和TabLayout关联
        mTabLayout.setupWithViewPager(mViewPager);
    }

    //ViewPager的适配器
    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ?0:fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "新歌榜";
                case 1:
                    return "热歌榜";
                case 2:
                    return "Billboard";
                case 3:
                    return "Hito中文榜";
                case 4:
                    return "KTV热歌榜";
            }
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpTool.newInstance(this).cancel(this);
    }
}
