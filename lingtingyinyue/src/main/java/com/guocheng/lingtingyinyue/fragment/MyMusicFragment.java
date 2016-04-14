package com.guocheng.lingtingyinyue.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.activity.MyMusicListActivity;
import com.guocheng.lingtingyinyue.bean.Mp3Info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class MyMusicFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private  ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
    private List<Mp3Info> musicInfos = new ArrayList<>();

    @Bind(R.id.rl_user_login)
    RelativeLayout rlUserLogin;
    @Bind(R.id.gridView)
    GridView mGridView;

    public MyMusicFragment() {
    }

    public static MyMusicFragment newInstance(List<Mp3Info> musicInfos) {
        MyMusicFragment fragment = new MyMusicFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) musicInfos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            musicInfos = (List<Mp3Info>) getArguments().getSerializable(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_music, container, false);
        ButterKnife.bind(this,view);

        for(int i=0;i<musicInfos.size();i++){
            Log.d("MyMusicFragment", "-->" + musicInfos.get(i).getTitle());
            Log.d("MyMusicFragment","-->"+musicInfos.get(i).getArtist());
            Log.d("MyMusicFragment","-->"+musicInfos.get(i).getDuration());
        }

        initData();
        initAdapter();
        initListener();
        return view;
    }

    private void initListener() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(getActivity(), MyMusicListActivity.class);
                        intent.putExtra("music", (Serializable) musicInfos);
                        startActivity(intent);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;

                }

                //startActivity(intent);
            }
        });
    }

    private void initAdapter() {
        SimpleAdapter saImageItems = new SimpleAdapter(getActivity(),
                lstImageItem,
                R.layout.mymusic_item,
                new String[] {"ItemImage","ItemText","ItemNum"},
                new int[] {R.id.iv_mymusic_pic,R.id.tv_mymusic_text,R.id.tv_mymusic_num});
        mGridView.setAdapter(saImageItems);
    }

    private void initData() {
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("ItemImage", R.drawable.mymusic_localmusic);//添加图像资源的ID
        map1.put("ItemText", "本地音乐");//按序号做ItemText
        map1.put("ItemNum",musicInfos.size()+"首");
        lstImageItem.add(map1);
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("ItemImage", R.drawable.mymusic_cloudcollect);//添加图像资源的ID
        map2.put("ItemText", "我的云音乐");//按序号做ItemText
        map2.put("ItemNum","");
        lstImageItem.add(map2);
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("ItemImage", R.drawable.mymusic_mydownload);//添加图像资源的ID
        map3.put("ItemText", "我的下载");//按序号做ItemText
        map3.put("ItemNum","");
        lstImageItem.add(map3);
        HashMap<String, Object> map4 = new HashMap<String, Object>();
        map4.put("ItemImage", R.drawable.mymusic_recently);//添加图像资源的ID
        map4.put("ItemText", "最近播放");//按序号做ItemText
        map4.put("ItemNum","");
        lstImageItem.add(map4);
    }


}
