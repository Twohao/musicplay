package com.guocheng.lingtingyinyue.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/3/16.
 */
public class PlayerService extends Service {
    public static MediaPlayer mp;
    private ServiceReceiver serviceReceiver;
    int status = 1;//当前的状态,1没有声音播放 ,2 正在播放声音,3暂停
    private int curPos = 0;
    //定义线程池（同时只能有一个线程运行）
    ExecutorService es = Executors.newSingleThreadExecutor();
    private int songIndex = 0;

    @Override
    public IBinder onBind(Intent intent) {//重写的onBind方法
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void onCreate() {//重写的onCreate方法
        // TODO Auto-generated method stub
        status = 1;
        mp = new MediaPlayer();
        serviceReceiver = new ServiceReceiver();//创建BroadcastReceiver
        IntentFilter filter = new IntentFilter();//创建过滤器
        filter.addAction("wyf.ytl.control");//添加Action
        registerReceiver(serviceReceiver, filter);//注册BroadcastReceiver

        super.onCreate();
    }

    @Override
    public void onDestroy() {//重写的onDestroy方法
        // TODO Auto-generated method stub
        unregisterReceiver(serviceReceiver);//取消注册
        super.onDestroy();
    }

    private void play(String path) throws IOException {
        mp.reset();
        mp.setDataSource(path);
        mp.prepare();
        mp.start();
        Intent sendIntent = new Intent("wyf.ytl.update");
        sendIntent.putExtra("update", 2);
        sendBroadcast(sendIntent);
    }

    public class ServiceReceiver extends BroadcastReceiver {//自定义BroadcastReceiver
        @Override
        public void onReceive(Context context, Intent intent) {//重写的响应方法
            songIndex = intent.getIntExtra("songIndex",0);
            int action = intent.getIntExtra("ACTION", -1);//得带需要的数据
            String path = intent.getStringExtra("path");

            switch (action){
                case 0:
                    mp.stop();
                    mp = new MediaPlayer();
                    try {
                        play(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                        if(mp.isPlaying()){
                            mp.stop();
                            mp = new MediaPlayer();
                            try {
                                play(path);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            if(curPos>0){
                                mp.seekTo(curPos);
                                mp.start();
                                Intent sendIntent = new Intent("wyf.ytl.update");
                                sendIntent.putExtra("update", 2);
                                sendBroadcast(sendIntent);
                            }else{
                                try {
                                    play(path);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        break;
                    case 2:
                        if( mp.isPlaying()) {
                            mp.pause();
                            //获取mediaPlayer的播放位置
                            curPos = mp.getCurrentPosition();
                            Intent sendIntent = new Intent("wyf.ytl.update");
                            sendIntent.putExtra("update", 3);
                            sendBroadcast(sendIntent);
                        }
                        break;
                    case 3:
                        if(mp.isPlaying()) {
                            mp.stop();
                            //表示播放停止
                            curPos = -1;
                            Intent sendIntent = new Intent("wyf.ytl.update");
                            sendIntent.putExtra("update", 1);//存放数据
                            sendBroadcast(sendIntent);//发送广播
                        }
                        break;
                }

        }
    }

}
