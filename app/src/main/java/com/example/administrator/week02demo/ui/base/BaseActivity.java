package com.example.administrator.week02demo.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.example.administrator.week02demo.inter.IBase;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/10.
 */

public abstract class BaseActivity <T extends BaseContract.BasePresenter> extends AppCompatActivity implements IBase,BaseContract.BaseView {
    @Inject
    protected T mPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        inject();
        if (mPresenter!=null) {
            mPresenter.attchView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void ShowLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
