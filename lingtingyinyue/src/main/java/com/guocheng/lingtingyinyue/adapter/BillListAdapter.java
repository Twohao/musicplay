package com.guocheng.lingtingyinyue.adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.bean.BillListInfo;
import com.guocheng.lingtingyinyue.bean.SongUrlInfo;
import com.guocheng.lingtingyinyue.constant.UrlConstant;
import com.guocheng.lingtingyinyue.http.IOkCallBack;
import com.guocheng.lingtingyinyue.http.OkHttpTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/19.
 */
public class BillListAdapter extends BaseAdapter {
    private Context context;
    private List<BillListInfo.SongListEntity> billSongList = new ArrayList<>();
    private List<SongUrlInfo.BitrateEntity> SongUrlList = new ArrayList<>();
    private SongUrlInfo.SonginfoEntity songinfoEntitys;
    public BillListAdapter(Context context,List<BillListInfo.SongListEntity> billSongList){
        this.context = context;
        this.billSongList = billSongList;
    }
    @Override
    public int getCount() {
        return billSongList == null ? 0 : billSongList.size();
    }

    @Override
    public Object getItem(int position) {
        return billSongList == null ? null : billSongList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.billlist_songlist_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if(position<9){
            holder.tvBilllistRanking.setText("0"+(position+1));
        }else {
            holder.tvBilllistRanking.setText(""+(position+1));
        }
        holder.tvMusicTitle.setText(""+billSongList.get(position).getTitle());
        holder.tvMusicArtist.setText(""+billSongList.get(position).getArtist_name());

        holder.ivDownloadMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String songId = billSongList.get(position).getSong_id();
                OkHttpTool.newInstance(context).okGet(UrlConstant.MUSICLIB_SONG_URL + songId, SongUrlInfo.class, new IOkCallBack<SongUrlInfo>() {
                    @Override
                    public void onSucess(SongUrlInfo resultInfo) {
                        SongUrlList = resultInfo.getBitrate();
                        songinfoEntitys = resultInfo.getSonginfo();

                        if(SongUrlList!=null){
                            String url = SongUrlList.get(0).getFile_link();
                            Log.d("歌曲播放资源adapter", "" + SongUrlList.get(0).getFile_link());
                            Log.d("歌曲播放资源adapter", songinfoEntitys.getTitle()+"-" +songinfoEntitys.getAuthor() );

                            DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
                            Uri uri = Uri.parse(url);
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            //设置允许使用的网络类型，这里是移动网络和wifi都可以
                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
                            //不显示下载界面
                            request.setVisibleInDownloadsUi(true);
                            request.setTitle("正在下载: ");
                            request.setDescription(songinfoEntitys.getAuthor() + " - " + songinfoEntitys.getTitle() + ".mp3");
                            request.allowScanningByMediaScanner();
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.setDestinationInExternalPublicDir("/lingtingmusic", songinfoEntitys.getAuthor() + " - " + songinfoEntitys.getTitle() + ".mp3");
                            long id = downloadManager.enqueue(request);
                            Toast.makeText(context, "正在下载: " + songinfoEntitys.getAuthor() + " - " + songinfoEntitys.getTitle() + ".mp3", Toast.LENGTH_LONG).show();
                            //long id = downloadManager.enqueue(request);

                        }else{
                            Toast.makeText(context,"供应方已经不给我们提供该歌曲的资源了！！！",Toast.LENGTH_LONG).show();
                        }



                    }
                },4);
            }
        });

        return convertView;
    }

    class ViewHolder{
        @Bind(R.id.tv_billlist_ranking)
        TextView tvBilllistRanking;
        @Bind(R.id.tv_music_title)
        TextView tvMusicTitle;
        @Bind(R.id.tv_music_artist)
        TextView tvMusicArtist;
        @Bind(R.id.iv_download_music)
        ImageView ivDownloadMusic;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
