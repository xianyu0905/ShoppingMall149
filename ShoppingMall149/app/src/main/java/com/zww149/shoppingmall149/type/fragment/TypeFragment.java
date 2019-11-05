package com.zww149.shoppingmall149.type.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zww149.shoppingmall149.base.BaseFragment;



/**
 * 作用：分类的Fragment
 */
public class TypeFragment extends BaseFragment {
    private static final String TAG = TypeFragment.class.getSimpleName();
    private TextView textView;


    @Override
    public void initData() {
        super.initData();
        textView.setText("分类");
    }

    @Override
    public View initView() {
        Log.e(TAG,"分类的Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }
}
