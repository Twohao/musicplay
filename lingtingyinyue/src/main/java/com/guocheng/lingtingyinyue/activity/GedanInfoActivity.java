package com.guocheng.lingtingyinyue.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.adapter.GedanInfoAdapter;
import com.guocheng.lingtingyinyue.bean.GedanMusicInfo;
import com.guocheng.lingtingyinyue.bean.SongUrlInfo;
import com.guocheng.lingtingyinyue.constant.UrlConstant;
import com.guocheng.lingtingyinyue.http.IOkCallBack;
import com.guocheng.lingtingyinyue.http.OkHttpTool;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GedanInfoActivity extends AppCompatActivity {

    private List<GedanMusicInfo.ContentEntity> contentEntityList = new ArrayList<>();
    private NoScrollListView mListView;
    private TextView tvGedanCount;
    private Toolbar mToolbar;
    private ImageView ivGedanPic;
    private String listId;
    private GedanInfoAdapter gedanInfoAdapter;
    private List<SongUrlInfo.BitrateEntity> SongUrlList = new ArrayList<>();
    private SongUrlInfo.SonginfoEntity songinfoEntitys;
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gedan_info);

        Intent intent = getIntent();
        listId = intent.getStringExtra("listId");

        mToolbar = (Toolbar) findViewById(R.id.behavior_toolbar);
        mListView = (NoScrollListView) findViewById(R.id.lv_gedan_musicinfo);
        tvGedanCount = (TextView) findViewById(R.id.tv_gedan_count);
        ivGedanPic = (ImageView) findViewById(R.id.iv_gedan_pic);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        initHttpData();
        initListener();
    }

    private void initHttpData() {
        OkHttpTool.newInstance(this).okGet(UrlConstant.MUSICLIB_GEDAN_MUSIC_URL + listId, GedanMusicInfo.class, new IOkCallBack<GedanMusicInfo>() {
            @Override
            public void onSucess(GedanMusicInfo resultInfo) {
                contentEntityList = resultInfo.getContent();

                Picasso.with(GedanInfoActivity.this).load(resultInfo.getPic_300()).into(ivGedanPic);
                mToolbar.setTitle("" + resultInfo.getTitle());
                mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                tvGedanCount.setText("共有" + contentEntityList.size() + "首歌曲");
                gedanInfoAdapter = new GedanInfoAdapter(GedanInfoActivity.this, contentEntityList);
                mListView.setAdapter(gedanInfoAdapter);
                initListener();
            }
        }, 10);
    }

    private void initListener() {
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case android.R.id.home:
                        GedanInfoActivity.this.finish();
                        break;
                }
                return true;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String songId = contentEntityList.get(position).getSong_id();
                OkHttpTool.newInstance(GedanInfoActivity.this).okGet(UrlConstant.MUSICLIB_SONG_URL + songId, SongUrlInfo.class, new IOkCallBack<SongUrlInfo>() {
                    @Override
                    public void onSucess(SongUrlInfo resultInfo) {
                        SongUrlList = resultInfo.getBitrate();
                        songinfoEntitys = resultInfo.getSonginfo();

                        if (SongUrlList != null) {
                            Intent intent = new Intent("wyf.ytl.control");//创建Intent
                            intent.putExtra("ACTION", 1);//存放数据
                            intent.putExtra("path", SongUrlList.get(0).getFile_link());
                            sendBroadcast(intent);//发送广播*//**//*

                            Intent sendIntent = new Intent("wyf.ytl.update");
                            sendIntent.putExtra("update", 6);
                            sendIntent.putExtra("billsonginfo", (Serializable) songinfoEntitys);
                            sendBroadcast(sendIntent);

                            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(GedanInfoActivity.this);
                            mBuilder.setSmallIcon(R.drawable.default_share);
                            mBuilder.setContentTitle("" + songinfoEntitys.getTitle());
                            mBuilder.setContentText("" + songinfoEntitys.getAuthor());
                            mBuilder.setTicker("" + songinfoEntitys.getTitle());
                            mBuilder.setWhen(System.currentTimeMillis());
                            Notification notification = mBuilder.build();
                            notification.flags = Notification.FLAG_NO_CLEAR;
                            manager.notify(14, notification);

                        } else {
                            Toast.makeText(GedanInfoActivity.this, "供应方已经不给我们提供该歌曲的资源了！！！", Toast.LENGTH_LONG).show();
                        }
                    }
                }, 4);
            }
        });
    }

}
