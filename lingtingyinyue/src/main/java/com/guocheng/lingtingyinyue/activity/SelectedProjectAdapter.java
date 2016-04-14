package com.guocheng.lingtingyinyue.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.bean.SelectedInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/19.
 */
public class SelectedProjectAdapter extends BaseAdapter {
    private Context context;
    private List<SelectedInfo.ContentEntity> albumListEntityList = new ArrayList<>();
    public SelectedProjectAdapter(Context context,List<SelectedInfo.ContentEntity> albumListEntityList){
        this.context = context;
        this.albumListEntityList = albumListEntityList;
    }
    @Override
    public int getCount() {
        return albumListEntityList == null ? 0: albumListEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return albumListEntityList == null ? null : albumListEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.selected_album_list,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(albumListEntityList.get(position).getPic_w300()).into(holder.ivGedanPic);
        holder.tvGedanTitle.setText("" + albumListEntityList.get(position).getTitle());
        holder.tvGedanTag.setText(""+albumListEntityList.get(position).getTag());
        return convertView;
    }

    class ViewHolder{
        @Bind(R.id.iv_gedan_pic)
        ImageView ivGedanPic;
        @Bind(R.id.tv_gedan_title)
        TextView tvGedanTitle;
        @Bind(R.id.tv_gedan_tag)
        TextView tvGedanTag;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

}
