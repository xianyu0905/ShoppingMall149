package com.zww149.shoppingmall149.base;




import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * 作用基类Fragment
 * 首页：HomeFragment
 * 分类：TypeFragement
 * 发现：CommunityFragment
 * 购物车：shoppingCarFragment
 * 用户中心：UserFragment
 * 等待都要继承该类
 */
public abstract class BaseFragment extends Fragment {

    public Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }



    /**
     * 有子类实现，实现特有效果
     */
    public abstract View initView();
    /**
     * 初始化数据
     * @return
     */
    public void initData() {
    }

}
