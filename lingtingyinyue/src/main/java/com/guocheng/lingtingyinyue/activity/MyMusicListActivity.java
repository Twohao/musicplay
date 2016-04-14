package com.guocheng.lingtingyinyue.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.guocheng.lingtingyinyue.BaseActivity;
import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.adapter.MusicListAdapter;
import com.guocheng.lingtingyinyue.bean.Mp3Info;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyMusicListActivity extends BaseActivity {
    public static final String TAG = MyMusicListActivity.class.toString();
    private List<Mp3Info> musicInfos = new ArrayList<>();
    private MusicListAdapter adapter;
    //private Mp3Info mp3Info;

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_more)
    ImageView ivMore;
    @Bind(R.id.tv_top_title)
    TextView tvTopTitle;
    @Bind(R.id.lv_mymusic_list)
    ListView lvMyMusicList;
    //private int songIndex = 0;

    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        //tvTopTitle.setText(intent.getStringExtra("title"));
        musicInfos = (List<Mp3Info>) intent.getSerializableExtra("music");

        adapter = new MusicListAdapter(this,musicInfos);
        lvMyMusicList.setAdapter(adapter);

        manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        Log.d(TAG, "" + musicInfos.get(0).getPath());
        initListener();
    }

    private void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent();
                intent.putExtra("song", mp3Info);
                intent.putExtra("songIndex", songIndex);
                MyMusicListActivity.this.setResult(RESULT_OK,intent);*/
                MyMusicListActivity.this.finish();
            }
        });

        lvMyMusicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(musicInfos != null){
                    Intent intent = new Intent("wyf.ytl.control");//创建Intent
                    intent.putExtra("ACTION", 1);//存放数据
                    intent.putExtra("path", musicInfos.get(position).getPath());
                    sendBroadcast(intent);//发送广播

                    Intent intent2 = new Intent("wyf.ytl.update");
                    intent2.putExtra("update", 4);//存放数据
                    intent2.putExtra("musicIndex", position);
                    sendBroadcast(intent2);//发送广播

                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MyMusicListActivity.this);
                    mBuilder.setSmallIcon(R.drawable.default_share);
                    mBuilder.setContentTitle(musicInfos.get(position).getTitle());
                    mBuilder.setContentText(musicInfos.get(position).getArtist());
                    mBuilder.setTicker(""+musicInfos.get(position).getTitle());
                    mBuilder.setWhen(System.currentTimeMillis());
                    Notification notification = mBuilder.build();
                    notification.flags = Notification.FLAG_NO_CLEAR;
                    manager.notify(14,notification);

                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            /*Intent intent = new Intent();
            intent.putExtra("song", mp3Info);
            intent.putExtra("songIndex", songIndex);
            MyMusicListActivity.this.setResult(RESULT_OK,intent);*/
            MyMusicListActivity.this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
