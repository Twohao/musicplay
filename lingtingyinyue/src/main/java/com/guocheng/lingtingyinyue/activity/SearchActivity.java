package com.guocheng.lingtingyinyue.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.guocheng.lingtingyinyue.R;
import com.guocheng.lingtingyinyue.bean.ArtistInfo;
import com.guocheng.lingtingyinyue.constant.UrlConstant;
import com.guocheng.lingtingyinyue.flowlayout.FlowLayout;
import com.guocheng.lingtingyinyue.http.IOkCallBack;
import com.guocheng.lingtingyinyue.http.OkHttpTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    private List<ArtistInfo.DataEntity> hotWordsList = new ArrayList<>();

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.et_input_edit_text_search)
    EditText etInputSearch;
    @Bind(R.id.iv_clear_edit_text_search)
    ImageView ivClearSearch;
    @Bind(R.id.flowLayout)
    FlowLayout mFlowLayout;
    private TextView tvHotWords;
    private List<View> viewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        getDataHttp();
        initListener();

    }

    private void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });
        ivClearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etInputSearch.setText("");
            }
        });
        etInputSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“GO”键*/
                if (actionId ==EditorInfo.IME_ACTION_SEARCH) {
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("searchWords", etInputSearch.getEditableText().toString());
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

    }

    private void getDataHttp() {
        OkHttpTool.newInstance(this).okGet(UrlConstant.MUSICLIB_HOT_SEARCH_URL, ArtistInfo.class, new IOkCallBack<ArtistInfo>() {
            @Override
            public void onSucess(ArtistInfo resultInfo) {
                hotWordsList = resultInfo.getData();
                addViewFlowLayout();
            }
        }, 5);
    }

    private void addViewFlowLayout() {
        MarginLayoutParams lp = new MarginLayoutParams(
                LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,5,5,10);
        for(int i = 0; i < hotWordsList.size(); i ++){
            tvHotWords = new TextView(this);
            tvHotWords.setText(hotWordsList.get(i).getVal());
            tvHotWords.setTextColor(Color.GRAY);
            tvHotWords.setBackgroundResource(R.drawable.search_hot_text_bg);
            mFlowLayout.addView(tvHotWords, lp);
            viewList.add(tvHotWords);
        }

        for(int i=0;i<viewList.size();i++){
            final int finalI = i;
            viewList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("searchWords", hotWordsList.get(finalI).getVal());
                    startActivity(intent);
                }
            });
        }

    }
}
