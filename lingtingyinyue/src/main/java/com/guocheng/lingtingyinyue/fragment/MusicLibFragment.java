package com.guocheng.lingtingyinyue.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.activity.BillListActivity;
import com.guocheng.lingtingyinyue.activity.MVLibActivity;
import com.guocheng.lingtingyinyue.activity.NewMusicCourierActivity;
import com.guocheng.lingtingyinyue.activity.SelectedProjectActivity;
import com.guocheng.lingtingyinyue.adapter.PartDescAdapter;
import com.guocheng.lingtingyinyue.bean.FocusInfo;
import com.guocheng.lingtingyinyue.constant.UrlConstant;
import com.guocheng.lingtingyinyue.http.IOkCallBack;
import com.guocheng.lingtingyinyue.http.OkHttpTool;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**

 */
public class MusicLibFragment extends Fragment {
    public static final String TAG = MusicLibFragment.class.toString();
    @Bind(R.id.lv_partDesc)
    ListView mListView;
    @Bind(R.id.expand_listview_header_convenientBanner)
    ConvenientBanner mConvenientBannner;

    List<FocusInfo.DataEntity.FocusEntity.ResultEntity> focusList = new ArrayList<>();
    private PartDescAdapter partDescAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ArrayList<String> images;

    public MusicLibFragment() {
        // Required empty public constructor
    }


    public static MusicLibFragment newInstance(String param1, String param2) {
        MusicLibFragment fragment = new MusicLibFragment();
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
        View view = inflater.inflate(R.layout.fragment_music_lib, container, false);
        ButterKnife.bind(this, view);

        getHttpData();
        partDescAdapter = new PartDescAdapter(getActivity());
        mListView.setAdapter(partDescAdapter);
        initListener();
        return view;
    }

    private void getHttpData() {
        OkHttpTool.newInstance(getActivity()).okGet(UrlConstant.MUSICLIB_TOP_FOCUS_IMG_URL, FocusInfo.class, new IOkCallBack<FocusInfo>() {
            @Override
            public void onSucess(FocusInfo resultInfo) {
                focusList = resultInfo.getData().getFocus().getResult();

                images = new ArrayList<String>();
                for(int i=0;i<focusList.size();i++){
                    images.add(focusList.get(i).getRandpic());
                }

                mConvenientBannner.setPages(new CBViewHolderCreator() {
                    @Override
                    public Object createHolder() {
                        return new BannerViewHolder();
                    }
                },images).setPageIndicator(new int[]{R.drawable.ic_page_indicator,R.drawable.ic_page_indicator_focused})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            }
        },6);
    }

    class BannerViewHolder implements Holder<String> {

        private ImageView mBannerImageView;

        @Override
        public View createView(Context context) {
            mBannerImageView = new ImageView(getActivity());
            mBannerImageView.setScaleType(ImageView.ScaleType.FIT_XY);


            return mBannerImageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Picasso.with(getActivity()).load(data).into(mBannerImageView);
        }

    }

    private void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,""+position);
                Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(getActivity(), BillListActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), SelectedProjectActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), NewMusicCourierActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getActivity(), MVLibActivity.class);
                        startActivity(intent);
                        break;

                }

            }
        });
    }



}
