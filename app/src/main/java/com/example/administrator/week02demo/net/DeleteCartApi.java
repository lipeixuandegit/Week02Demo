package com.example.administrator.week02demo.net;


import com.example.administrator.week02demo.bean.BaseBean;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/5/18.
 */

public class DeleteCartApi {
    private static DeleteCartApi deleteCartApi;
    private DeleteCartApiService deleteCartApiService;

    private DeleteCartApi(DeleteCartApiService deleteCartApiService) {
        this.deleteCartApiService = deleteCartApiService;
    }

    public static DeleteCartApi getDeleteCartApi(DeleteCartApiService deleteCartApiService) {
        if (deleteCartApi == null) {
            deleteCartApi = new DeleteCartApi(deleteCartApiService);
        }
        return deleteCartApi;
    }

    public Observable<BaseBean> deleteCart(String uid, String pid,
                                           String token) {
        return deleteCartApiService.deleteCart(uid, pid, token);
    }
}
