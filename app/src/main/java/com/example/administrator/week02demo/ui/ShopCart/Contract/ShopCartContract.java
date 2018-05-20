package com.example.administrator.week02demo.ui.ShopCart.Contract;

import com.example.administrator.week02demo.bean.GetCartsBean;
import com.example.administrator.week02demo.bean.SellerBean;
import com.example.administrator.week02demo.ui.base.BaseContract;

import java.util.List;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface ShopCartContract {
   interface View extends BaseContract.BaseView{
       void showCartList(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList);

       void updateCartsSuccess(String msg);

       void deleteCartSuccess(String msg);


   }
   interface Presenter extends BaseContract.BasePresenter<View>{
       void getCarts(String uid, String token);

       void updateCarts(String uid, String sellerid, String pid, String num, String selected, String token);

       void deleteCart(String uid, String pid, String token);
   }

}
