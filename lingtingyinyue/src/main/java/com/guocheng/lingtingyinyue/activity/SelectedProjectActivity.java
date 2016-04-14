package com.guocheng.lingtingyinyue.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.bean.SelectedInfo;
import com.guocheng.lingtingyinyue.constant.UrlConstant;
import com.guocheng.lingtingyinyue.http.IOkCallBack;
import com.guocheng.lingtingyinyue.http.OkHttpTool;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SelectedProjectActivity extends AppCompatActivity {
    private List<SelectedInfo.ContentEntity> albumListEntityList = new ArrayList<>();
    private List<SelectedInfo.ContentEntity> allAlbumListEntityList = new ArrayList<>();
    private SelectedProjectAdapter selectedProjectAdapter;
    private int i = 1;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.pull_refresh_grid)
    PullToRefreshGridView mGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_project);
        ButterKnife.bind(this);

        initData();
        mGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                new GetDataTask().execute();
            }
        });
        initListener();

    }

    private void initData() {
        mGridView.setMode(PullToRefreshBase.Mode.BOTH);
        mGridView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载...");
        mGridView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        mGridView.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载更多...");
        // 设置PullRefreshListView下拉加载时的加载提示
        mGridView.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新...");
        mGridView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在加载...");
        mGridView.getLoadingLayoutProxy(true, false).setReleaseLabel("松开加载更多...");
        getHttpData();
    }

    private void getHttpData() {
        OkHttpTool.newInstance(this).okGet(UrlConstant.MUSICLIB_ALBUMLIST_URL+i, SelectedInfo.class, new IOkCallBack<SelectedInfo>() {
            @Override
            public void onSucess(SelectedInfo resultInfo) {
                albumListEntityList = resultInfo.getContent();
                allAlbumListEntityList.addAll(albumListEntityList);
                if(i==1){
                    selectedProjectAdapter = new SelectedProjectAdapter(SelectedProjectActivity.this,allAlbumListEntityList);
                    mGridView.setAdapter(selectedProjectAdapter);
                }
                selectedProjectAdapter.notifyDataSetChanged();
                initListener();
            }
        },3);
    }

    private void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedProjectActivity.this.finish();
            }
        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SelectedProjectActivity.this,GedanInfoActivity.class);
                intent.putExtra("listId",albumListEntityList.get(position).getListid());
                startActivity(intent);
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
            mGridView.onRefreshComplete();

            super.onPostExecute(strings);
        }
    }

}
