package com.guocheng.lingtingyinyue.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.adapter.NewMusicAdapter;
import com.guocheng.lingtingyinyue.bean.NewMusicInfo;
import com.guocheng.lingtingyinyue.bean.SongUrlInfo;
import com.guocheng.lingtingyinyue.constant.UrlConstant;
import com.guocheng.lingtingyinyue.http.IOkCallBack;
import com.guocheng.lingtingyinyue.http.OkHttpTool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewMusicCourierActivity extends AppCompatActivity {
    private static final int MESSAGETYPE_01 = 0x0001;
    private NewMusicAdapter newMusicAdapter;

    private List<NewMusicInfo.SongListEntity> songListEntityList = new ArrayList<>();
    private List<SongUrlInfo.BitrateEntity> SongUrlList = new ArrayList<>();
    private SongUrlInfo.SonginfoEntity songinfoEntitys;
    @Bind(R.id.list_view)
    ListView mListView;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    private NotificationManager manager;

    //private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_music_courier);
        ButterKnife.bind(this);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        getHttpData();
        initListener();

    }

    private void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewMusicCourierActivity.this.finish();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String songId = songListEntityList.get(position).getSong_id();
                OkHttpTool.newInstance(NewMusicCourierActivity.this).okGet(UrlConstant.MUSICLIB_SONG_URL + songId, SongUrlInfo.class, new IOkCallBack<SongUrlInfo>() {
                    @Override
                    public void onSucess(SongUrlInfo resultInfo) {
                        SongUrlList = resultInfo.getBitrate();
                        songinfoEntitys = resultInfo.getSonginfo();

                        if(SongUrlList!=null){
                            Intent intent = new Intent("wyf.ytl.control");//创建Intent
                            intent.putExtra("ACTION", 1);//存放数据
                            intent.putExtra("path", SongUrlList.get(0).getFile_link());
                            sendBroadcast(intent);//发送广播*//**//*

                            Intent sendIntent = new Intent("wyf.ytl.update");
                            sendIntent.putExtra("update", 6);
                            sendIntent.putExtra("billsonginfo", (Serializable) songinfoEntitys);
                            sendBroadcast(sendIntent);

                            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(NewMusicCourierActivity.this);
                            mBuilder.setSmallIcon(R.drawable.default_share);
                            mBuilder.setContentTitle("" + songinfoEntitys.getTitle());
                            mBuilder.setContentText("" + songinfoEntitys.getAuthor());
                            mBuilder.setTicker("" + songinfoEntitys.getTitle());
                            mBuilder.setWhen(System.currentTimeMillis());
                            Notification notification = mBuilder.build();
                            notification.flags = Notification.FLAG_NO_CLEAR;
                            manager.notify(14, notification);

                        }else{
                            Toast.makeText(NewMusicCourierActivity.this, "供应方已经不给我们提供该歌曲的资源了！！！", Toast.LENGTH_LONG).show();
                        }
                    }
                },4);
            }
        });
    }

    private void getHttpData() {
        OkHttpTool.newInstance(this).okGet(UrlConstant.MUSICLIB_NEW_MUSIC_URL, NewMusicInfo.class, new IOkCallBack<NewMusicInfo>() {
            @Override
            public void onSucess(NewMusicInfo resultInfo) {
                songListEntityList = resultInfo.getSong_list();

                TextView tvMUsicNum = new TextView(NewMusicCourierActivity.this);
                tvMUsicNum.setTextSize(15);
                tvMUsicNum.setTextColor(R.color.subblack);
                tvMUsicNum.setText(songListEntityList.size() + "首歌曲");
                mListView.addFooterView(tvMUsicNum);

                newMusicAdapter = new NewMusicAdapter(NewMusicCourierActivity.this,songListEntityList);
                mListView.setAdapter(newMusicAdapter);
                initListener();
            }
        },9);
    }
}
