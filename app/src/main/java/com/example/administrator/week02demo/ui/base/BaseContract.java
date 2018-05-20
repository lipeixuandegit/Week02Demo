package com.example.administrator.week02demo.ui.base;

/**
 * Created by Administrator on 2018/5/10.
 */

public interface BaseContract {
    interface BasePresenter<T extends BaseView>{
        void attchView(T view);
        void detachView();
    }
    interface BaseView{
        void ShowLoading();
        void dismissLoading();
    }

}
