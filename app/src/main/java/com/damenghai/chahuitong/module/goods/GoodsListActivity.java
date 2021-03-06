package com.damenghai.chahuitong.module.goods;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.GoodsCardViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.damenghai.chahuitong.widget.ExpandTabView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(GoodsListPresenter.class)
public class GoodsListActivity extends BaseListActivity<GoodsListPresenter> {

    @Bind(R.id.expand_goods_list_order)
    ExpandTabView mTabView;

    @Bind(R.id.rl_goods_search)
    RelativeLayout mRlSearch;

    @Bind(R.id.et_search_title)
    EditText mEtSearch;

    @Bind(R.id.btn_search_done)
    Button mBtnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        mEtSearch.setOnKeyListener((view, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_ENTER) {
                getPresenter().search(mEtSearch);
                return true;
            }
            return false;
        });
        mBtnDone.setOnClickListener(v -> getPresenter().search(mEtSearch));
    }

    public void setKeyword(CharSequence keyword) {
        mRlSearch.setVisibility(View.VISIBLE);
        mEtSearch.setText(keyword);
    }

    @Override
    protected int getLayout() {
        return R.layout.goods_activity_list;
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new GoodsCardViewHolder(parent);
    }

    public void setCategory(String[] list) {
        mTabView.setData(0, list);
        mTabView.setOnItemSelectedListener(getPresenter());
    }

    public void setTabText(int position, String text) {
        mTabView.setText(position, text);
    }

}
