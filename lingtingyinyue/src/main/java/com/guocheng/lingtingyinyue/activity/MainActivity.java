package com.guocheng.lingtingyinyue.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guocheng.lingtingyinyue.BaseActivity;
import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.adapter.FragmentAdapter;
import com.guocheng.lingtingyinyue.bean.Mp3Info;
import com.guocheng.lingtingyinyue.bean.QueryInfo;
import com.guocheng.lingtingyinyue.bean.SongUrlInfo;
import com.guocheng.lingtingyinyue.fragment.MusicLibFragment;
import com.guocheng.lingtingyinyue.fragment.MyMusicFragment;
import com.guocheng.lingtingyinyue.service.PlayerService;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.toString();
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;
    private List<Mp3Info> musicInfos = new ArrayList<>();
    private QueryInfo.DataEntity searchList;
    private SongUrlInfo.SonginfoEntity songinfoEntitys;

    @Bind(R.id.rb_music_lib)
    RadioButton rbMusicLib;
    @Bind(R.id.rb_my_music)
    RadioButton rbMyMusic;
    @Bind(R.id.vp_music_content)
    ViewPager vpMusicContent;

    @Bind(R.id.iv_search_music)
    ImageView ivSearchMusic;
    @Bind(R.id.iv_song_image)
    ImageView ivSongImage;  //图片
    @Bind(R.id.tv_song_title)
    TextView tvSongTitle;  //歌曲名
    @Bind(R.id.tv_song_author)
    TextView tvSongAuthor;  //歌手
    @Bind(R.id.iv_play_pause_song)
    ImageView ivPlayMusic;  //播放、暂停音乐
    @Bind(R.id.iv_play_next_song)
    ImageView ivNextMusic;  //下一曲
    @Bind(R.id.rl_bottom_bar_music_play)
    RelativeLayout mRelativeLayout;

    //private Mp3Info mp3Infos;
    private Intent serviceIntent = new Intent();

    ActivityReceiver activityReceiver;
    private int status = 1;//当前的状态,1没有声音播放 ,2 正在播放声音,3暂停
    private int songIndex = 0;
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = getIntent();
        musicInfos = (List<Mp3Info>) intent.getSerializableExtra("music");

        for(int i=0;i<musicInfos.size();i++){
            Log.d("MainActivity", "-->" + musicInfos.get(i).getTitle());
            Log.d("MainActivity","-->"+musicInfos.get(i).getArtist());
            Log.d("MainActivity","-->"+musicInfos.get(i).getDuration());
        }
        setViewPager();
        rbMyMusic.setChecked(true);
        rbMusicLib.setOnClickListener(this);
        rbMyMusic.setOnClickListener(this);

        Intent intentService = new Intent(this,PlayerService.class);//创建Intent
        startService(intentService);//启动后台Service

        activityReceiver = new ActivityReceiver();//创建BroadcastReceiver
        IntentFilter filter = new IntentFilter();//创建IntentFilter过滤器
        filter.addAction("wyf.ytl.update");//添加Action
        registerReceiver(activityReceiver, filter);//注册监听

        tvSongTitle.setText(musicInfos.get(songIndex).getTitle());
        tvSongAuthor.setText(musicInfos.get(songIndex).getArtist());


        initListener();
        /*if(PlayerService.mp.isPlaying()){
            PlayerService.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    playNextMusic();
                }
            });
        }*/
    }

    private void initListener() {
        vpMusicContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rbMusicLib.setChecked(true);
                        break;
                    case 1:
                        rbMyMusic.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ivPlayMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PlayerService.mp.isPlaying()) {
                    Intent intent = new Intent("wyf.ytl.control");//创建Intent
                    intent.putExtra("ACTION", 2);//存放数据
                    intent.putExtra("path", musicInfos.get(songIndex).getPath());
                    sendBroadcast(intent);//发送广播
                } else {
                    Intent intent = new Intent("wyf.ytl.control");//创建Intent
                    intent.putExtra("ACTION", 1);//存放数据
                    intent.putExtra("path", musicInfos.get(songIndex).getPath());
                    Log.d(TAG, "" + musicInfos.get(songIndex).getPath());
                    sendBroadcast(intent);//发送广播
                }

            }
        });
        ivNextMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playNextMusic();
            }
        });
        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MusicPlayContentActivity.class);
                intent.putExtra("songTitle",tvSongTitle.getText().toString());
                intent.putExtra("songAuthor", tvSongAuthor.getText().toString());
                intent.putExtra("songIndex",songIndex);
                intent.putExtra("music", (Serializable) musicInfos);
                startActivity(intent);
            }
        });
        ivSearchMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    private void playNextMusic() {
        songIndex++;
        Log.d(TAG, "歌曲songIndex:" + songIndex);
        if(songIndex<musicInfos.size()){
             Intent intent = new Intent("wyf.ytl.control");//创建Intent
             intent.putExtra("ACTION", 0);//存放数据
             intent.putExtra("path", musicInfos.get(songIndex).getPath());
             Log.d("下一曲", "" + musicInfos.get(songIndex).getPath());
             sendBroadcast(intent);//发送广播

             tvSongTitle.setText(musicInfos.get(songIndex).getTitle());
             tvSongAuthor.setText(musicInfos.get(songIndex).getArtist());
             ivSongImage.setImageResource(R.drawable.default_share);
             getNotification(songIndex);
        }else {
            songIndex = 0;
            Intent intent = new Intent("wyf.ytl.control");//创建Intent
            intent.putExtra("ACTION", 0);//存放数据
            intent.putExtra("path", musicInfos.get(songIndex).getPath());
            Log.d("下一曲", "" + musicInfos.get(songIndex).getPath());
            sendBroadcast(intent);//发送广播

            tvSongTitle.setText(musicInfos.get(songIndex).getTitle());
            tvSongAuthor.setText(musicInfos.get(songIndex).getArtist());
            ivSongImage.setImageResource(R.drawable.default_share);
            getNotification(songIndex);
        }
    }

    private void getNotification(int songIndex){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);
        mBuilder.setSmallIcon(R.drawable.default_share);
        mBuilder.setContentTitle(musicInfos.get(songIndex).getTitle());
        mBuilder.setContentText(musicInfos.get(songIndex).getArtist());
        mBuilder.setTicker(""+musicInfos.get(songIndex).getTitle());
        mBuilder.setWhen(System.currentTimeMillis());
        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_NO_CLEAR;
        manager.notify(14, notification);
    }

    public class ActivityReceiver extends BroadcastReceiver {//自定义的BroadcastReceiver
        @Override
        public void onReceive(Context context, Intent intent) {//重写的onReceive方法
            // TODO Auto-generated method stub
            int update = intent.getIntExtra("update", -1);//得到intent中的数据
            int musicIndex = intent.getIntExtra("musicIndex",0);
            searchList = (QueryInfo.DataEntity) intent.getSerializableExtra("searchsonginfo");
            songinfoEntitys = (SongUrlInfo.SonginfoEntity) intent.getSerializableExtra("billsonginfo");
            switch(update){//分支判断
                case 1://没有声音播放
                    status = 1; //设置当前状态
                    break;
                case 2://正在播放声音
                    ivPlayMusic.setImageResource(R.drawable.nowplaying_bar_pause_n);//更换图片
                    status = 2; //设置当前状态
                    break;
                case 3://暂停中
                    ivPlayMusic.setImageResource(R.drawable.nowplaying_bar_play_n);//更换图片
                    status = 3; //设置当前状态
                    break;
                case 4:
                    songIndex = musicIndex;
                    tvSongTitle.setText(musicInfos.get(musicIndex).getTitle());
                    tvSongAuthor.setText(musicInfos.get(musicIndex).getArtist());
                    ivSongImage.setImageResource(R.drawable.default_share);
                    break;
                case 5:
                    Picasso.with(MainActivity.this).load(""+searchList.getPicUrl()).into(ivSongImage);
                    tvSongTitle.setText("" + searchList.getName());
                    tvSongAuthor.setText(""+searchList.getSingerName());
                    break;
                case 6:
                    Picasso.with(MainActivity.this).load(""+songinfoEntitys.getPic_small()).into(ivSongImage);
                    tvSongTitle.setText("" + songinfoEntitys.getTitle());
                    tvSongAuthor.setText(""+songinfoEntitys.getAuthor());
                    break;
            }
        }
    }

    private void setViewPager() {
        fragmentList.add(MusicLibFragment.newInstance(null, null));
        fragmentList.add(MyMusicFragment.newInstance(musicInfos));

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        vpMusicContent.setAdapter(fragmentAdapter);
        vpMusicContent.setCurrentItem(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_music_lib:
                vpMusicContent.setCurrentItem(0);
                break;
            case R.id.rb_my_music:
                vpMusicContent.setCurrentItem(1);
                break;
        }
    }

    @Override
    protected void onDestroy() {//释放时被调用
        unregisterReceiver(activityReceiver);
        Intent intent = new Intent(this, PlayerService.class);//创建Intent
        stopService(intent);//停止后台的Service
        super.onDestroy();
    }
}
