package com.example.administrator.week02demo.net;

import com.example.administrator.week02demo.bean.GetCartsBean;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/5/19.
 */

public class GetCartApi {
    private static GetCartApi getCartApi;
    private GetCartApiService getCartApiService;

    private GetCartApi(GetCartApiService getCartApiService) {
        this.getCartApiService = getCartApiService;
    }

    public static GetCartApi getGetCartApi(GetCartApiService getCartApiService) {
        if (getCartApi == null) {
            getCartApi = new GetCartApi(getCartApiService);
        }
        return getCartApi;
    }

    public Observable<GetCartsBean> getCatagory(String uid, String token) {
        return getCartApiService.getCarts(uid,token);
    }
}
