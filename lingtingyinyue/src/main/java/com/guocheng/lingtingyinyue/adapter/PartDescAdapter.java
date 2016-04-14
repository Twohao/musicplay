package com.guocheng.lingtingyinyue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.guocheng.lingtingyinyue.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/19.
 */
public class PartDescAdapter extends BaseAdapter {
    private Context context;
    private String[] title = {"热门榜单","精选专题","新歌上架","MV库"};
    private String[] subtitle = {"掌握最新乐坛流行动向","热辣HIGH歌","王力宏新歌首发","最新、最热，海量MV"};
    private int[] icon = {R.drawable.online_icon_list_normal,R.drawable.online_icon_subject_normal
            ,R.drawable.online_icon_special_normal,R.drawable.ic_list_dropdown_play_normal,};

    public PartDescAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.musiclib_partdesc_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ivPartDescImage.setImageResource(icon[position]);
        holder.tvPartDescTitle.setText(""+title[position]);
        holder.tvPartDescSubTitle.setText(""+subtitle[position]);
        return convertView;
    }

    class ViewHolder{
        @Bind(R.id.iv_partdesc_image)
        ImageView ivPartDescImage;
        @Bind(R.id.tv_partdesc_title)
        TextView tvPartDescTitle;
        @Bind(R.id.tv_partdesc_subtitle)
        TextView tvPartDescSubTitle;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

}
