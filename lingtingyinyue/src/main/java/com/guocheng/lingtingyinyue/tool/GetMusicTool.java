package com.guocheng.lingtingyinyue.tool;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.guocheng.lingtingyinyue.bean.Mp3Info;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/16.
 */
public class GetMusicTool {

    public static List<Mp3Info> getMusicList(Context context){
        List<Mp3Info> musicInfos = new ArrayList<Mp3Info>();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        for (int i = 0; i < cursor.getCount(); i++) {
            Mp3Info mp3Info = new Mp3Info();
            cursor.moveToNext();
            long id = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media._ID));   //音乐id
            String title = cursor.getString((cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE)));//音乐标题
            String artist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST));//艺术家
            String album = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM));	//专辑
            long duration = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DURATION));//时长
            long size = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.SIZE));  //文件大小
            String path = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA));//文件路径
            String displayname = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));

            int album_id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));

            int isMusic = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));//是否为音乐
            if (isMusic != 0) {     //只把音乐添加到集合当中
                mp3Info.setId(id);
                mp3Info.setTitle(title);
                mp3Info.setArtist(artist);
                mp3Info.setAlbum(album);

                Date date = new Date(duration);
                SimpleDateFormat format = new SimpleDateFormat("mm:ss");
                mp3Info.setDuration(format.format(date));

                mp3Info.setSize(size);
                mp3Info.setPath(path);
                mp3Info.setDisplayname(displayname);
                mp3Info.setAlbum_id(album_id);

                musicInfos.add(mp3Info);
            }
        }

        return musicInfos;
    }



}
