package com.zww149.shoppingmall149.home.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zww149.shoppingmall149.base.BaseFragment;



/**
 * 作用：主页面Fragment
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public void initData() {
        super.initData();
        textView.setText("主页");
    }

    @Override
    public View initView() {
        Log.e(TAG,"主页面的Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }
}
