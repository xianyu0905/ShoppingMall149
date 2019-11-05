package com.zww149.shoppingmall149.community.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zww149.shoppingmall149.base.BaseFragment;



/**
 * 作用：发现的Fragment
 */
public class CommunityFragment extends BaseFragment {
    private static final String TAG = CommunityFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public void initData() {
        super.initData();
        textView.setText("发现");
    }

    @Override
    public View initView() {
        Log.e(TAG,"发现的Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }
}
