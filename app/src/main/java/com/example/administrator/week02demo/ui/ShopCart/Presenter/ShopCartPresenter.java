package com.example.administrator.week02demo.ui.ShopCart.Presenter;

import com.example.administrator.week02demo.bean.BaseBean;
import com.example.administrator.week02demo.bean.GetCartsBean;
import com.example.administrator.week02demo.bean.SellerBean;
import com.example.administrator.week02demo.net.DeleteCartApi;
import com.example.administrator.week02demo.net.GetCartApi;
import com.example.administrator.week02demo.net.UpdateCartApi;
import com.example.administrator.week02demo.ui.ShopCart.Contract.ShopCartContract;
import com.example.administrator.week02demo.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/19.
 */

public class ShopCartPresenter extends BasePresenter<ShopCartContract.View> implements ShopCartContract.Presenter {
    private GetCartApi getCartApi;
    private DeleteCartApi deleteCartApi;
    private UpdateCartApi updateCartApi;
    @Inject
    public ShopCartPresenter(GetCartApi getCartApi, DeleteCartApi deleteCartApi, UpdateCartApi updateCartApi) {
        this.getCartApi = getCartApi;
        this.deleteCartApi = deleteCartApi;
        this.updateCartApi = updateCartApi;
    }

    private boolean isSellerProductAllSelect(GetCartsBean.DataBean dataBean){
        List<GetCartsBean.DataBean.ListBean> list = dataBean.getList();
        for (int i=0;i<list.size();i++){
            GetCartsBean.DataBean.ListBean listBean = list.get(i);
            if (0==listBean.getSelected()){
                return false;
            }
        }
        return true;

    }

    @Override
    public void getCarts(String uid, String token) {
        getCartApi.getCatagory(uid,token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GetCartsBean>() {
                    @Override
                    public void accept(GetCartsBean getCartsBean) throws Exception {
                        List<SellerBean> groupList=new ArrayList<>();
                        List<List<GetCartsBean.DataBean.ListBean>> childList=new ArrayList<>();
                        List<GetCartsBean.DataBean> data = getCartsBean.getData();
                        if (data==null){
                            return;
                        }
                        for (int i = 0; i < data.size(); i++) {
                            GetCartsBean.DataBean dataBean = data.get(i);
                            SellerBean sellerBean = new SellerBean();
                            sellerBean.setSellerName(dataBean.getSellerName());
                            sellerBean.setSellerid(dataBean.getSellerid());
                            sellerBean.setSelected(isSellerProductAllSelect(dataBean));
                            groupList.add(sellerBean);
                            List<GetCartsBean.DataBean.ListBean> list = dataBean.getList();
                            childList.add(list);

                        }
                        if (mView != null) {
                            mView.showCartList(groupList, childList);
                        }

                    }
                });
    }

    @Override
    public void updateCarts(String uid, String sellerid, String pid, String num, String selected, String token) {
        updateCartApi.updateCarts(uid, sellerid, pid, num, selected, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView != null) {
                    mView.updateCartsSuccess(s);
                }
            }

        });
    }

    @Override
    public void deleteCart(String uid, String pid, String token) {
        deleteCartApi.deleteCart(uid, pid, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView != null) {
                    mView.updateCartsSuccess(s);
                }
            }
        });
    }
}
