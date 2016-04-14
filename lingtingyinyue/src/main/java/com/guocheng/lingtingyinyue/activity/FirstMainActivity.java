package com.guocheng.lingtingyinyue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.guocheng.lingtingyinyue.BaseActivity;
import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.bean.Mp3Info;
import com.guocheng.lingtingyinyue.tool.GetMusicTool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FirstMainActivity extends BaseActivity {

    private Handler handler;
    private List<Mp3Info> musicInfos = new ArrayList<Mp3Info>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_main);

        musicInfos = GetMusicTool.getMusicList(this);
        Timer();
    }



    private void Timer() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(FirstMainActivity.this, WelcomeActivity.class);
                intent.putExtra("music", (Serializable) musicInfos);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }


}
