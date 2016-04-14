package com.guocheng.lingtingyinyue.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.adapter.HottestVideoAdapter;
import com.guocheng.lingtingyinyue.bean.HottestVideoInfo;
import com.guocheng.lingtingyinyue.constant.UrlConstant;
import com.guocheng.lingtingyinyue.http.IOkCallBack;
import com.guocheng.lingtingyinyue.http.OkHttpTool;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCFullScreenActivity;

/**

 */
public class HottestFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private List<HottestVideoInfo.DataEntity> hottestVideoList = new ArrayList<>();
    private List<HottestVideoInfo.DataEntity> AllhottestVideoList = new ArrayList<>();
    private HottestVideoAdapter hottestVideoAdapter;


    //private GridView mGridView;
    private PullToRefreshGridView mPullRefreshGridView;

    private int i = 1;

    public HottestFragment() {
        // Required empty public constructor
    }

    public static HottestFragment newInstance(String param1, String param2) {
        HottestFragment fragment = new HottestFragment();
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
        View view = inflater.inflate(R.layout.fragment_hottest, container, false);

        mPullRefreshGridView = (PullToRefreshGridView) view.findViewById(R.id.pull_refresh_grid);
        //mGridView = mPullRefreshGridView.getRefreshableView();

        initData();
        //initListener();

        mPullRefreshGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                new GetDataTask().execute();
            }
        });

        return view;
    }

    private void initData() {
        mPullRefreshGridView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshGridView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载...");
        mPullRefreshGridView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        mPullRefreshGridView.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载更多...");
        // 设置PullRefreshListView下拉加载时的加载提示
        mPullRefreshGridView.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新...");
        mPullRefreshGridView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在加载...");
        mPullRefreshGridView.getLoadingLayoutProxy(true, false).setReleaseLabel("松开加载更多...");
        getHttpData();
    }

    private void getHttpData() {
        OkHttpTool.newInstance(getActivity()).okGet(UrlConstant.MUSICLIB_HOT_MV_VIDEO_URL + i, HottestVideoInfo.class, new IOkCallBack<HottestVideoInfo>() {
            @Override
            public void onSucess(HottestVideoInfo resultInfo) {
                hottestVideoList = resultInfo.getData();
                AllhottestVideoList.addAll(hottestVideoList);
                if(i==1){
                    hottestVideoAdapter = new HottestVideoAdapter(getActivity(), AllhottestVideoList);
                    mPullRefreshGridView.setAdapter(hottestVideoAdapter);
                }
                hottestVideoAdapter.notifyDataSetChanged();
                initListener();
            }
        }, 8);
    }

    private void initListener() {
        mPullRefreshGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(AllhottestVideoList.get(position).getPicUrl() == null){
                    Toast.makeText(getActivity(),"该资源已失效！",Toast.LENGTH_LONG).show();
                }else{
                    String videoUrl = AllhottestVideoList.get(position).getMvList().get(0).getUrl();
                    String videoTitle = AllhottestVideoList.get(position).getVideoName()+" - "+hottestVideoList.get(position).getSingerName();
                    JCFullScreenActivity.toActivity(getActivity(), "" + videoUrl, "", "" + videoTitle);
                }
            }
        });
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
            mPullRefreshGridView.onRefreshComplete();

            super.onPostExecute(strings);
        }
    }


}
