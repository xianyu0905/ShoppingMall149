package com.zww149.shoppingmall149.user.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zww149.shoppingmall149.base.BaseFragment;



/**
 * 作用：用户的Fragment
 */
public class UserFragment extends BaseFragment {
    private static final String TAG = UserFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public void initData() {
        super.initData();
        textView.setText("用户");
    }

    @Override
    public View initView() {
        Log.e(TAG,"用户的Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }
}
