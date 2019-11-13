package com.zww149.shoppingmall149.shoppingcart.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zww149.shoppingmall149.base.BaseFragment;
import com.zww149.shoppingmall149.home.bean.GoodBean;
import com.zww149.shoppingmall149.shoppingcart.utils.CartStorage;

import java.util.List;


/**
 * 作用：购物车的Fragment
 */
public class ShoppingCartFragment extends BaseFragment {
    private static final String TAG = ShoppingCartFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public void initData() {
        super.initData();
        textView.setText("购物车");

        List<GoodBean> goodBeanList =CartStorage.getInstance().getAllData();
        for (int i = 0;i<goodBeanList.size();i++){
            Log.e("TAG",goodBeanList.get(i).toString());
        }
    }

    @Override
    public View initView() {
        Log.e(TAG,"购物车的Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }
}
