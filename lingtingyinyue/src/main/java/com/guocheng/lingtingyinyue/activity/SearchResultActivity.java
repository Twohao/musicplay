package com.guocheng.lingtingyinyue.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.adapter.SearchAdapter;
import com.guocheng.lingtingyinyue.bean.QueryInfo;
import com.guocheng.lingtingyinyue.http.IOkCallBack;
import com.guocheng.lingtingyinyue.http.OkHttpTool;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResultActivity extends AppCompatActivity {
    public static final String TAG = SearchResultActivity.class.toString();

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_search_result_count)
    TextView tvSearchCount;
    private String searchWords;
    private String encodeStr;
    private List<QueryInfo.DataEntity> searchList = new ArrayList<>();
    private List<QueryInfo.DataEntity> allSearchList = new ArrayList<>();
    private SearchAdapter searchAdapter;
    private PullToRefreshListView mListVIew;
    private int i = 1;
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        mListVIew = (PullToRefreshListView) findViewById(R.id.lv_search_show);

        manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = getIntent();
        searchWords = intent.getStringExtra("searchWords");
        Log.d(TAG, "" + searchWords);
        try {
            encodeStr = URLEncoder.encode(searchWords, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        initData();

        mListVIew.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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

    private void initData() {
        mListVIew.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        mListVIew.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载...");
        mListVIew.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        mListVIew.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载更多...");
        // 设置PullRefreshListView下拉加载时的加载提示
        mListVIew.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新...");
        mListVIew.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在加载...");
        mListVIew.getLoadingLayoutProxy(true, false).setReleaseLabel("松开加载更多...");
        getHttpData();
    }

    private void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchResultActivity.this.finish();
            }
        });
        mListVIew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = "";
                if(searchList.get(position-1).getUrlList()!=null){
                    path = searchList.get(position-1).getAuditionList().get(1).getUrl();
                }
                Intent intent = new Intent("wyf.ytl.control");//创建Intent
                intent.putExtra("ACTION", 1);//存放数据
                intent.putExtra("path", path);
                sendBroadcast(intent);//发送广播*//**//*

                Intent sendIntent = new Intent("wyf.ytl.update");
                sendIntent.putExtra("update", 5);
                sendIntent.putExtra("searchsonginfo", (Serializable) searchList.get(position-1));
                sendBroadcast(sendIntent);

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(SearchResultActivity.this);
                mBuilder.setSmallIcon(R.drawable.default_share);
                mBuilder.setContentTitle(searchList.get(position-1).getName());
                mBuilder.setContentText(searchList.get(position-1).getSingerName());
                mBuilder.setTicker("" + searchList.get(position-1).getName());
                mBuilder.setWhen(System.currentTimeMillis());
                Notification notification = mBuilder.build();
                notification.flags = Notification.FLAG_NO_CLEAR;
                manager.notify(14, notification);
            }
        });

    }

    private void getHttpData() {
        OkHttpTool.newInstance(this).okGet("http://search.dongting.com/song/search?&page="+i+"&size=50&q=" + encodeStr, QueryInfo.class, new IOkCallBack<QueryInfo>() {
            @Override
            public void onSucess(QueryInfo resultInfo) {
                searchList = resultInfo.getData();
                allSearchList.addAll(searchList);

                tvSearchCount.setText("找到相关结果" + resultInfo.getTotalCount() + "条");
                if(i==1){
                    searchAdapter = new SearchAdapter(SearchResultActivity.this, allSearchList);
                    mListVIew.setAdapter(searchAdapter);
                }
                searchAdapter.notifyDataSetChanged();

                initListener();
            }
        }, 20);
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
            mListVIew.onRefreshComplete();

            super.onPostExecute(strings);
        }
    }

}
