package com.damenghai.chahuitong.expansion.overlay;

import android.app.ProgressDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.BeamBaseActivity;

import me.zhanghai.android.materialprogressbar.IndeterminateProgressDrawable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class DefaultViewExpansionDelegate extends ViewExpansionDelegate {

    private ProgressDialog mProgressDialog;

    private TextView mTvEmpty;

    private View mContent;

    public DefaultViewExpansionDelegate(BeamBaseActivity activity) {
        super(activity);
    }

    @Override
    public void showProgressBar() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        mContent = getContainer().findViewById(R.id.layout_content);
        if (mContent != null) mContent.setVisibility(View.INVISIBLE);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("请稍等");
        mProgressDialog.show();
//            mProgressDialog.setIndeterminateDrawable(new IndeterminateProgressDrawable(getActivity()));
//            getContainer().addView(mProgressDialog, getLayoutParams());
    }

    @Override
    public void hideProgressBar() {
        if (mProgressDialog != null) mProgressDialog.dismiss();
        if (mContent != null) mContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorPage() {
        super.showErrorPage();
    }

    @Override
    public void hideErrorPage() {
        super.hideErrorPage();
    }

    @Override
    public void showEmptyPage() {
        if (mTvEmpty == null) {
            mTvEmpty = new TextView(getActivity());
            mTvEmpty.setText(R.string.text_order_empty);
            mTvEmpty.setTextSize(R.dimen.text_size_body_material);
            mTvEmpty.setTextColor(getActivity().getResources().getColor(R.color.text_black_primary));
            getContainer().addView(mTvEmpty, getLayoutParams());
        } else {
            mTvEmpty.setVisibility(View.VISIBLE);
        }
    }

    private FrameLayout.LayoutParams getLayoutParams() {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        return lp;
    }

}
