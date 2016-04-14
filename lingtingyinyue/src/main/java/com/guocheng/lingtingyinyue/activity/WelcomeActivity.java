package com.guocheng.lingtingyinyue.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guocheng.lingtingyinyue.BaseActivity;
import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.adapter.ViewpagerAdapter;
import com.guocheng.lingtingyinyue.bean.Mp3Info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {

    @Bind(R.id.vp_welcome) ViewPager vpWelcome;
    @Bind(R.id.ll_search ) LinearLayout ll_search;

    private List<View> viewList;
    private ViewpagerAdapter adapter;
    private TextView tvEnterMain;
    private SharedPreferences preferences;
    private List<Mp3Info> musicInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        musicInfos = (List<Mp3Info>) intent.getSerializableExtra("music");

        initGulder();
        addAllView();
        initAdapter();
        initListener();
    }

    private void initGulder() {
        //读取SharedPreferences中需要的数据
        preferences = getSharedPreferences("count", Context.MODE_PRIVATE);
        int count = preferences.getInt("count", 0);
        //判断程序与第几次运行，如果是第一次运行则跳转到引导页面
        if (count != 0) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("music", (Serializable) musicInfos);
            startActivity(intent);
            finish();
        }

        SharedPreferences.Editor editor = preferences.edit();
        //存入数据
        editor.putInt("count", ++count);
        //提交修改
        editor.commit();
    }

    private void initListener() {
        vpWelcome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ll_search.getChildCount(); i++) {
                    if (i == position) {
                        ll_search.getChildAt(i).setBackgroundResource(R.drawable.guide_dot_select);
                    } else {
                        ll_search.getChildAt(i).setBackgroundResource(R.drawable.guide_dot_nomal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tvEnterMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                intent.putExtra("music", (Serializable) musicInfos);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initAdapter() {
        adapter = new ViewpagerAdapter(viewList);
        vpWelcome.setAdapter(adapter);
        vpWelcome.setCurrentItem(0);
    }

    private void addAllView() {
        viewList = new ArrayList<>();
        viewList.add(LayoutInflater.from(this).inflate(R.layout.welcome_1,null));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.welcome_2,null));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.welcome_3,null));
        View welcomeView = LayoutInflater.from(this).inflate(R.layout.welcome_4, null);
        tvEnterMain = (TextView)welcomeView.findViewById(R.id.tv_enter_main);
        viewList.add(welcomeView);
    }
}
