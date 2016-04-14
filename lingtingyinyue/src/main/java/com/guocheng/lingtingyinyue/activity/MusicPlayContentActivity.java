package com.guocheng.lingtingyinyue.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.bean.Mp3Info;
import com.guocheng.lingtingyinyue.service.PlayerService;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MusicPlayContentActivity extends AppCompatActivity {

    private static final String TAG = MusicPlayContentActivity.class.toString();
    private List<Mp3Info> musicInfos = new ArrayList<>();
    @Bind(R.id.iv_back)
    ImageView ivBack;  //返回
    @Bind(R.id.tv_music_title)
    TextView tvMusicTitle;  //音乐名
    @Bind(R.id.tv_music_artist)
    TextView tvMusicArtist;   //歌手
    @Bind(R.id.iv_paly_prev_song)
    ImageView ivPlayPrevMusic;  //上一曲
    @Bind(R.id.iv_play_pause_song)
    ImageView ivPlayPauseMusic;   //播放/暂停
    @Bind(R.id.iv_play_next_song)
    ImageView ivPlayNextMusic;   //下一曲
    @Bind(R.id.setting_view_bright_seekbar)
    SeekBar mSeekBar;   //进度条
    @Bind(R.id.tv_start_time)
    TextView tvStartTime;
    @Bind(R.id.tv_end_time)
    TextView tvEndTime;
    private int songIndex=0; //媒体音乐所播放的位置
    private NotificationManager manager;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play_content);
        ButterKnife.bind(this);

        manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        intent = getIntent();
        songIndex = intent.getIntExtra("songIndex",0);
        musicInfos = (List<Mp3Info>) intent.getSerializableExtra("music");
        initData();

        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0);
                }
            }
        }.start();


        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler1.sendEmptyMessage(1);
                }
            }
        }.start();

        if(PlayerService.mp.isPlaying()){
            ivPlayPauseMusic.setSelected(true);
        }else{
            ivPlayPauseMusic.setSelected(false);
        }

        initListener();

        if(PlayerService.mp.isPlaying()){
            PlayerService.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    playNextMusic();
                }
            });
        }
    }

    private void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicPlayContentActivity.this.finish();
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int dest = seekBar.getProgress();
                int time = PlayerService.mp.getDuration();
                int max = seekBar.getMax();
                int progress = time * dest / max;
                if (PlayerService.mp.isPlaying()) {
                    PlayerService.mp.seekTo(progress);
                }
            }
        });
        ivPlayPauseMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PlayerService.mp.isPlaying()) {
                    Intent intent = new Intent("wyf.ytl.control");//创建Intent
                    intent.putExtra("ACTION", 2);//存放数据
                    intent.putExtra("path", musicInfos.get(songIndex).getPath());
                    sendBroadcast(intent);//发送广播
                    ivPlayPauseMusic.setSelected(false);
                } else {
                    Intent intent = new Intent("wyf.ytl.control");//创建Intent
                    intent.putExtra("ACTION", 1);//存放数据
                    intent.putExtra("path", musicInfos.get(songIndex).getPath());
                    Log.d(TAG, "" + musicInfos.get(songIndex).getPath());
                    sendBroadcast(intent);//发送广播
                    ivPlayPauseMusic.setSelected(true);
                }
            }
        });
        ivPlayPrevMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPlayPauseMusic.setSelected(true);
                songIndex = songIndex - 1;
                if (songIndex > 0) {
                    Intent intent = new Intent("wyf.ytl.control");//创建Intent
                    intent.putExtra("ACTION", 0);//存放数据
                    intent.putExtra("path", musicInfos.get(songIndex).getPath());
                    Log.d("上一曲", "" + musicInfos.get(songIndex).getPath());
                    sendBroadcast(intent);//发送广播

                    tvMusicTitle.setText(musicInfos.get(songIndex).getTitle());
                    tvMusicArtist.setText(musicInfos.get(songIndex).getArtist());
                    tvEndTime.setText("" + musicInfos.get(songIndex).getDuration());
                    Intent sendIntent = new Intent("wyf.ytl.update");
                    sendIntent.putExtra("update", 4);//存放数据
                    sendIntent.putExtra("musicIndex", songIndex);
                    sendBroadcast(sendIntent);//发送广播
                    getNotification(songIndex);

                } else {
                    songIndex = musicInfos.size() - 1;
                    Intent intent = new Intent("wyf.ytl.control");//创建Intent
                    intent.putExtra("ACTION", 0);//存放数据
                    intent.putExtra("path", musicInfos.get(songIndex).getPath());
                    Log.d("上一曲", "" + musicInfos.get(songIndex).getPath());
                    sendBroadcast(intent);//发送广播

                    tvMusicTitle.setText(musicInfos.get(songIndex).getTitle());
                    tvMusicArtist.setText(musicInfos.get(songIndex).getArtist());
                    tvEndTime.setText("" + musicInfos.get(songIndex).getDuration());
                    Intent sendIntent = new Intent("wyf.ytl.update");
                    sendIntent.putExtra("update", 4);//存放数据
                    sendIntent.putExtra("musicIndex", songIndex);
                    sendBroadcast(sendIntent);//发送广播
                    getNotification(songIndex);

                }
            }
        });
        ivPlayNextMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPlayPauseMusic.setSelected(true);
                playNextMusic();
            }
        });
    }

    private void playNextMusic() {
        songIndex++;
        if(songIndex<musicInfos.size()){
            Intent intent = new Intent("wyf.ytl.control");//创建Intent
            intent.putExtra("ACTION", 0);//存放数据
            intent.putExtra("path", musicInfos.get(songIndex).getPath());
            Log.d("下一曲", "" + musicInfos.get(songIndex).getPath());
            sendBroadcast(intent);//发送广播

            tvMusicTitle.setText(musicInfos.get(songIndex).getTitle());
            tvMusicArtist.setText(musicInfos.get(songIndex).getArtist());
            tvEndTime.setText(""+musicInfos.get(songIndex).getDuration());
            Intent sendIntent = new Intent("wyf.ytl.update");
            sendIntent.putExtra("update", 4);//存放数据
            sendIntent.putExtra("musicIndex", songIndex);
            sendBroadcast(sendIntent);//发送广播
            getNotification(songIndex);

        }else {
            songIndex = 0;
            Intent intent = new Intent("wyf.ytl.control");//创建Intent
            intent.putExtra("ACTION", 0);//存放数据
            intent.putExtra("path", musicInfos.get(songIndex).getPath());
            Log.d("下一曲", "" + musicInfos.get(songIndex).getPath());
            sendBroadcast(intent);//发送广播

            tvMusicTitle.setText(musicInfos.get(songIndex).getTitle());
            tvMusicArtist.setText(musicInfos.get(songIndex).getArtist());
            tvEndTime.setText(""+musicInfos.get(songIndex).getDuration());
            Intent sendIntent = new Intent("wyf.ytl.update");
            sendIntent.putExtra("update", 4);//存放数据
            sendIntent.putExtra("musicIndex", songIndex);
            sendBroadcast(sendIntent);//发送广播
            getNotification(songIndex);

        }
    }

    private void getNotification(int songIndex){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MusicPlayContentActivity.this);
        mBuilder.setSmallIcon(R.drawable.default_share);
        mBuilder.setContentTitle(musicInfos.get(songIndex).getTitle());
        mBuilder.setContentText(musicInfos.get(songIndex).getArtist());
        mBuilder.setTicker("" + musicInfos.get(songIndex).getTitle());
        mBuilder.setWhen(System.currentTimeMillis());
        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_NO_CLEAR;
        manager.notify(14,notification);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:
                    //更新进度
                    int position = PlayerService.mp.getCurrentPosition();
                    int time = PlayerService.mp.getDuration();
                    int max = mSeekBar.getMax();
                    mSeekBar.setProgress(position*max/time);

                    break;
                default:
                    break;
            }

        }
    };

    Handler handler1 = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    if(PlayerService.mp.isPlaying()){
                        int position = (PlayerService.mp.getCurrentPosition())/1000;
                        long minute = position%3600/60;
                        long second = position%60;
                        if(second<10){
                            tvStartTime.setText("0"+minute+":"+"0"+second);
                        }else{
                            if(minute<10){
                                tvStartTime.setText("0"+minute+":"+second);
                            }else{
                                tvStartTime.setText(minute+":"+second);
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void initData() {
        if(musicInfos!=null){
            tvMusicTitle.setText(""+intent.getStringExtra("songTitle"));
            tvMusicArtist.setText(""+intent.getStringExtra("songAuthor"));
            tvStartTime.setText("00:00");
            tvEndTime.setText(""+musicInfos.get(songIndex).getDuration());
            //tvEndTime.setText(""+PlayerService.mp.getDuration());
        }
    }

}
