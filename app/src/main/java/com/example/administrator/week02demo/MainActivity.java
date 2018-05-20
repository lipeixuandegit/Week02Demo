package com.example.administrator.week02demo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.administrator.week02demo.Component.DaggerHttpComponent;
import com.example.administrator.week02demo.Module.HttpModule;
import com.example.administrator.week02demo.bean.GetCartsBean;
import com.example.administrator.week02demo.bean.SellerBean;
import com.example.administrator.week02demo.ui.ShopCart.Contract.ShopCartContract;
import com.example.administrator.week02demo.ui.ShopCart.Presenter.ShopCartPresenter;
import com.example.administrator.week02demo.ui.ShopCart.adapter.ShopCartsAdapter;
import com.example.administrator.week02demo.ui.base.BaseActivity;
import com.example.administrator.week02demo.utils.DialogUtil;

import java.util.List;

public class MainActivity extends BaseActivity<ShopCartPresenter> implements ShopCartContract.View {

    private ProgressDialog progressDialog;
    private ExpandableListView mElv;
    /**
     * 全选
     */
    private CheckBox mCbAll;
    /**
     * 合计：
     */
    private TextView mTvMoney;
    /**
     * 去结算：
     */
    private TextView mTvTotal;
    private ShopCartsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        initView();
        progressDialog = DialogUtil.getProgressDialog(this);
        mPresenter.getCarts("14381", "491DD2892EEBAA6C42B869F30AF027AF");
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);

    }

    @Override
    public void showCartList(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList) {
        mCbAll.setChecked(isSellerAddSelected(groupList));
        adapter = new ShopCartsAdapter(this, groupList, childList, mPresenter,progressDialog);
        mElv.setAdapter(adapter);
        //获取数量和总价
        String[] strings = adapter.computeMoneyAndNum();
        mTvMoney.setText("总计：" + strings[0] + "元");
        mTvTotal.setText("去结算("+strings[1]+"个)");
        for (int i = 0; i < groupList.size(); i++) {
            mElv.expandGroup(i);
        }
        //关闭进度条
        progressDialog.dismiss();
    }

    @Override
    public void updateCartsSuccess(String msg) {
        if (adapter!=null){
            adapter.updataSuccess();
        }
    }

    @Override
    public void deleteCartSuccess(String msg) {
        if (adapter!=null){
            adapter.delSuccess();
        }

    }

    private void initView() {
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mCbAll = (CheckBox) findViewById(R.id.cbAll);
        mTvMoney = (TextView) findViewById(R.id.tvMoney);
        mTvTotal = (TextView) findViewById(R.id.tvTotal);
        mCbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter != null) {
                    progressDialog.show();
                    adapter.changeAllState(mCbAll.isChecked());
                }
            }
        });
    }
    private boolean isSellerAddSelected(List<SellerBean> groupList) {
        for (int i = 0; i < groupList.size(); i++) {
            SellerBean sellerBean = groupList.get(i);
            if (!sellerBean.isSelected()) {
                return false;
            }
        }
        return true;
    }
}
