package com.guocheng.lingtingyinyue.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.fragment.HottestFragment;
import com.guocheng.lingtingyinyue.fragment.LatestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MVLibActivity extends AppCompatActivity {

    @Bind(R.id.mv_tablayout)
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
        setContentView(R.layout.activity_mv_lib);
        ButterKnife.bind(this);
        setupTabLayout();
    }



    private void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MVLibActivity.this.finish();
            }
        });
    }

    /**
     * 设置TabLayou参数
     */
    private void setupTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        fragmentList.add(LatestFragment.newInstance(null, null));
        fragmentList.add(HottestFragment.newInstance(null, null));
        setupViewPager();
        initListener();
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
                    return "每日一发";
                case 1:
                    return "最热MV";
            }
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
