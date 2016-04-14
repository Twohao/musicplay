package com.guocheng.lingtingyinyue.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.adapter.BillListAdapter;
import com.guocheng.lingtingyinyue.bean.BillListInfo;
import com.guocheng.lingtingyinyue.bean.SongUrlInfo;
import com.guocheng.lingtingyinyue.constant.UrlConstant;
import com.guocheng.lingtingyinyue.http.IOkCallBack;
import com.guocheng.lingtingyinyue.http.OkHttpTool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class BillListFragment extends Fragment {
    public static final String TAG = BillListFragment.class.toString();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private BillListAdapter billListAdapter;
    private List<BillListInfo.SongListEntity> billSongList = new ArrayList<>();
    private List<SongUrlInfo.BitrateEntity> SongUrlList = new ArrayList<>();
    private SongUrlInfo.SonginfoEntity songinfoEntitys;
    private NotificationManager manager;
    @Bind(R.id.lv_bill_list)
    ListView mListView;

    public BillListFragment() {
        // Required empty public constructor
    }

    public static BillListFragment newInstance(String param1, String param2) {
        BillListFragment fragment = new BillListFragment();
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
        View view = inflater.inflate(R.layout.fragment_bill_list, container, false);
        ButterKnife.bind(this,view);

        manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        Log.d(TAG,""+mParam1);
        getHttpData();
        return view;
    }

    private void getHttpData() {
        OkHttpTool.newInstance(getActivity()).okGet(UrlConstant.MUSICLIB_BILLLIST_URL + mParam1, BillListInfo.class, new IOkCallBack<BillListInfo>() {
            @Override
            public void onSucess(BillListInfo resultInfo) {
                billSongList = resultInfo.getSong_list();

                TextView tvMUsicNum = new TextView(getActivity());
                tvMUsicNum.setTextSize(15);
                tvMUsicNum.setTextColor(R.color.subblack);
                tvMUsicNum.setText(billSongList.size() + "首歌曲");

                mListView.addFooterView(tvMUsicNum);

                billListAdapter = new BillListAdapter(getActivity(), billSongList);
                mListView.setAdapter(billListAdapter);

                initListener();
            }
        }, 2);
    }

    private void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                getSongUrl(position);

            }
        });
    }

    private void getSongUrl(final int position) {
        String songId = billSongList.get(position).getSong_id();
        Log.d("songId","-->"+songId+"< <--");
        OkHttpTool.newInstance(getActivity()).okGet(UrlConstant.MUSICLIB_SONG_URL+songId, SongUrlInfo.class, new IOkCallBack<SongUrlInfo>() {
            @Override
            public void onSucess(SongUrlInfo resultInfo) {
                SongUrlList = resultInfo.getBitrate();
                songinfoEntitys = resultInfo.getSonginfo();

                if(SongUrlList!=null){
                    Log.d("歌曲播放资源",""+SongUrlList.get(0).getFile_link());
                    Log.d("歌曲播放资源", "" + billSongList.get(position).getTitle() + "-" + billSongList.get(position).getAuthor());
                    Intent intent = new Intent("wyf.ytl.control");//创建Intent
                    intent.putExtra("ACTION", 1);//存放数据
                    intent.putExtra("path", SongUrlList.get(0).getFile_link());
                    getActivity().sendBroadcast(intent);//发送广播*//**//*

                    Intent sendIntent = new Intent("wyf.ytl.update");
                    sendIntent.putExtra("update", 6);
                    sendIntent.putExtra("billsonginfo", (Serializable) songinfoEntitys);
                    getActivity().sendBroadcast(sendIntent);

                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity());
                    mBuilder.setSmallIcon(R.drawable.default_share);
                    mBuilder.setContentTitle("" + songinfoEntitys.getTitle());
                    mBuilder.setContentText("" + songinfoEntitys.getAuthor());
                    mBuilder.setTicker("" + songinfoEntitys.getTitle());
                    mBuilder.setWhen(System.currentTimeMillis());
                    Notification notification = mBuilder.build();
                    notification.flags = Notification.FLAG_NO_CLEAR;
                    manager.notify(14, notification);

                }else{
                    Toast.makeText(getActivity(),"供应方已经不给我们提供该歌曲的资源了！！！",Toast.LENGTH_LONG).show();
                }


            }
        },4);
    }



}
