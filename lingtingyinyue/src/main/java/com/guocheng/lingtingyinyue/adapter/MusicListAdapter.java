package com.guocheng.lingtingyinyue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.bean.Mp3Info;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/16.
 */
public class MusicListAdapter extends BaseAdapter {

    private List<Mp3Info> musicInfos = new ArrayList<>();
    private LayoutInflater inflater;

    public MusicListAdapter(Context context,List<Mp3Info> musicInfos){
        this.musicInfos = musicInfos;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return musicInfos == null ? 0:musicInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return musicInfos == null ? null : musicInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.music_list_item_layout,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.tvMusicTitle.setText(musicInfos.get(position).getTitle());
        holder.tvMusicArtist.setText(musicInfos.get(position).getArtist());
        holder.tvMusicDuration.setText(musicInfos.get(position).getDuration());
        return convertView;
    }

    static class ViewHolder{
        @Bind(R.id.tv_music_title)
        TextView tvMusicTitle;
        @Bind(R.id.tv_music_artist)
        TextView tvMusicArtist;
        @Bind(R.id.tv_music_duration)
        TextView tvMusicDuration;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

}
