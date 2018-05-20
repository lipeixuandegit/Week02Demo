package com.example.administrator.week02demo.net;

import com.example.administrator.week02demo.bean.GetCartsBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface GetCartApiService {
    @FormUrlEncoded
    @POST("product/getCarts")
    Observable<GetCartsBean> getCarts(@Field("Uid") String uid, @Field("Token") String token);


}
