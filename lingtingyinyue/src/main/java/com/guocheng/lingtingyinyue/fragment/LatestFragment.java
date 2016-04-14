package com.guocheng.lingtingyinyue.fragment;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.bean.VideoInfo;
import com.guocheng.lingtingyinyue.constant.UrlConstant;
import com.guocheng.lingtingyinyue.http.IOkCallBack;
import com.guocheng.lingtingyinyue.http.OkHttpTool;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 *
 */
public class LatestFragment extends Fragment {

    private List<VideoInfo.DataEntity> mvListEntityList = new ArrayList<>();
    private List<VideoInfo.DataEntity> AllmvListEntityList = new ArrayList<>();
    private MvListAdapter mvListAdapter;
    private PullToRefreshListView mListView;
    private int i = 1;
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public LatestFragment() {
        // Required empty public constructor
    }

    public static LatestFragment newInstance(String param1, String param2) {
        LatestFragment fragment = new LatestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest, container, false);

        mListView = (PullToRefreshListView) view.findViewById(R.id.lv_mv_list);
        initData();
        initListener();

        return view;
    }

    private void initData() {
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载...");
        mListView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        mListView.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载更多...");
        // 设置PullRefreshListView下拉加载时的加载提示
        mListView.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新...");
        mListView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在加载...");
        mListView.getLoadingLayoutProxy(true, false).setReleaseLabel("松开加载更多...");
        getHttpData();
    }

    private void initListener() {
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetDataTask().execute();
            }
        });

    }

    private void getHttpData() {
        OkHttpTool.newInstance(getActivity()).okGet(UrlConstant.MUSICLIB_MV_VIDEO_URL + i, VideoInfo.class, new IOkCallBack<VideoInfo>() {
            @Override
            public void onSucess(VideoInfo resultInfo) {
                mvListEntityList = resultInfo.getData();
                AllmvListEntityList.addAll(mvListEntityList);
                if (i == 1) {
                    mvListAdapter = new MvListAdapter();
                    mListView.setAdapter(mvListAdapter);
                }
                mvListAdapter.notifyDataSetChanged();
                initListener();
            }
        }, 7);
    }

    class MvListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return AllmvListEntityList == null ? 0 : AllmvListEntityList.size();
        }

        @Override
        public Object getItem(int position) {
            return AllmvListEntityList == null ? null : AllmvListEntityList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null){
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.mv_lib_item,null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvTime.setText(""+AllmvListEntityList.get(position).getTitle());
            holder.tvMvTitle.setText("" + AllmvListEntityList.get(position).getDesc());

            //holder.mJCVideoPlayer.ivStart.performClick();
            JCVideoPlayer.setThumbImageViewScalType(ImageView.ScaleType.FIT_XY);
            if(AllmvListEntityList.get(position).getMvList()!=null){
                holder.mJCVideoPlayer.setUp(""+AllmvListEntityList.get(position).getMvList().get(0).getUrl(),
                        ""+AllmvListEntityList.get(position).getBigPicUrl(),
                        ""+AllmvListEntityList.get(position).getVideoName());
            }

            return convertView;
        }

        class ViewHolder{
            @Bind(R.id.tv_time)
            TextView tvTime;
            @Bind(R.id.tv_mv_title)
            TextView tvMvTitle;
            @Bind(R.id.videocontroller)
            JCVideoPlayer mJCVideoPlayer;
            public ViewHolder(View view){
                ButterKnife.bind(this, view);
            }

        }
    }


    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            i++;
            getHttpData();
            // 调用刷新完成
            mListView.onRefreshComplete();

            super.onPostExecute(strings);
        }
    }

}
