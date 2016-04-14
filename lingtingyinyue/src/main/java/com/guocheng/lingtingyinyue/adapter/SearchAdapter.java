package com.guocheng.lingtingyinyue.adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.bean.QueryInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/22.
 */
public class SearchAdapter extends BaseAdapter {
    private Context context;
    private List<QueryInfo.DataEntity> searchList = new ArrayList<>();
    public SearchAdapter(Context context,List<QueryInfo.DataEntity> searchList){
        this.context = context;
        this.searchList = searchList;
    }
    @Override
    public int getCount() {
        return searchList == null ? 0 : searchList.size();
    }

    @Override
    public Object getItem(int position) {
        return searchList == null ? null : searchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.show_search_result_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvMusicTitle.setText(""+searchList.get(position).getName());
        holder.tvMusicArtist.setText(""+searchList.get(position).getSingerName());
        holder.imDownloadMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = searchList.get(position).getUrlList().get(1).getUrl();
                DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(url);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                //设置允许使用的网络类型，这里是移动网络和wifi都可以
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                //不显示下载界面
                request.setVisibleInDownloadsUi(false);
                request.setDestinationInExternalPublicDir("/lingtingmusic", searchList.get(position).getSingerName()+ " - " + searchList.get(position).getName() + ".mp3");
                downloadManager.enqueue(request);
                Toast.makeText(context, "正在下载: " + searchList.get(position).getSingerName() + " - " + searchList.get(position).getName() + ".mp3", Toast.LENGTH_LONG).show();
                //long id = downloadManager.enqueue(request);
            }
        });

        return convertView;
    }

    class ViewHolder{
        @Bind(R.id.tv_music_title)
        TextView tvMusicTitle;
        @Bind(R.id.tv_music_artist)
        TextView tvMusicArtist;
        @Bind(R.id.iv_download_music)
        ImageView imDownloadMusic;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

}
