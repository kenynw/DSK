package com.damenghai.chahuitong.module.user;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ForgotPresenter extends Presenter<ForgotActivity> {

    public void sendCode(CharSequence mobile) {
        ServiceClient.getServices().sendCaptcha(mobile)
                .compose(new DefaultTransform<>())
                .subscribe(new ServiceResponse<Response>() {
                    @Override
                    public void onNext(Response response) {
                        super.onNext(response);
                        LUtils.toastLong(response.getMsg());
                    }
                });
    }

    public void commit(CharSequence mobile, CharSequence code, CharSequence password) {
        ServiceClient.getServices().resetPassword(mobile, code, password)
                .compose(new DefaultTransform<>())
                .subscribe(new ServiceResponse<Response>() {
                    @Override
                    public void onNext(Response response) {
                        super.onNext(response);
                        LUtils.toast(R.string.toast_operate_success);
                        getView().finish();
                    }
                });
    }

}
