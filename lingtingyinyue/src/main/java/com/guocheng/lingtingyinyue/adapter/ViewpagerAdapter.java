package com.guocheng.lingtingyinyue.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by svn on 24/2/16.
 */
public class ViewpagerAdapter extends PagerAdapter {
    private List<View> viewList;
    public ViewpagerAdapter(List<View> viewList){
        this.viewList = viewList;
    }
    @Override
    public int getCount() {
        return viewList == null ? 0 :viewList.size() ;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        if(viewList != null){
            view = viewList.get(position);
            container.addView(view);
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if(viewList != null){
            View view = viewList.get(position);
            container.removeView(view);
        }
    }
}
