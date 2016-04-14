package com.guocheng.lingtingyinyue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.bean.HottestVideoInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/24.
 */
public class HottestVideoAdapter extends BaseAdapter {
    private List<HottestVideoInfo.DataEntity> hottestVideoList = new ArrayList<>();
    private Context context;
    public HottestVideoAdapter(Context context,List<HottestVideoInfo.DataEntity> hottestVideoList){
        this.context = context;
        this.hottestVideoList = hottestVideoList;
    }

    @Override
    public int getCount() {
        return hottestVideoList == null ? 0 : hottestVideoList.size();
    }

    @Override
    public Object getItem(int position) {
        return hottestVideoList == null ? null : hottestVideoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item_hottest_mv,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(""+hottestVideoList.get(position).getPicUrl()).into(holder.ivVideoPic);
        holder.tvVideoName.setText(hottestVideoList.get(position).getVideoName()+" - "+hottestVideoList.get(position).getSingerName());


        return convertView;
    }

    class ViewHolder{
        @Bind(R.id.iv_mv_videoPic)
        ImageView ivVideoPic;
        @Bind(R.id.tv_mv_videoName)
        TextView tvVideoName;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
