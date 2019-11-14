package com.zww149.shoppingmall149.shoppingcart.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zww149.shoppingmall149.R;
import com.zww149.shoppingmall149.base.BaseFragment;
import com.zww149.shoppingmall149.home.bean.GoodBean;
import com.zww149.shoppingmall149.shoppingcart.adapter.ShoppingCartAdapter;
import com.zww149.shoppingmall149.shoppingcart.utils.CartStorage;

import java.util.List;


/**
 * 作用：购物车的Fragment
 */
public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener{

    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;
    private LinearLayout ll_empty_shopcart;
    private ShoppingCartAdapter adapter;


    private static final String TAG = ShoppingCartFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"购物车的Fragment的数据被初始化了");

        showData();

    }

    /**
     * 显示数据
     */
    private void showData() {
        List<GoodBean> goodBeanList =CartStorage.getInstance().getAllData();

        if (goodBeanList!=null&&goodBeanList.size()>0){
            //有数据

            // 把当没有数据显示的布局--隐藏
            ll_empty_shopcart.setVisibility(View.GONE);
            //设置适配器
            adapter = new ShoppingCartAdapter(mContext,goodBeanList,tvShopcartTotal,checkboxAll);
            recyclerview.setAdapter(adapter);
            //设置布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext,
                    LinearLayoutManager.VERTICAL,false));
        }else {
            //没有数据集
            //显示数据为空的布局
            ll_empty_shopcart.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View initView() {
        Log.e(TAG,"购物车的Fragment的UI被初始化了");

        View view = View.inflate(mContext, R.layout.fragment_shopping_cart,null);
        tvShopcartEdit = view.findViewById( R.id.tv_shopcart_edit );
        recyclerview = view.findViewById( R.id.recyclerview );
        llCheckAll = view.findViewById(R.id.ll_check_all);
        checkboxAll = view.findViewById( R.id.checkbox_all );
        tvShopcartTotal = view.findViewById( R.id.tv_shopcart_total );
        btnCheckOut = view.findViewById( R.id.btn_check_out );
        llDelete = view.findViewById( R.id.ll_delete );
        cbAll = view.findViewById( R.id.cb_all );
        btnDelete = view.findViewById( R.id.btn_delete );
        btnCollection = view.findViewById( R.id.btn_collection );
        ivEmpty = view.findViewById( R.id.iv_empty );
        tvEmptyCartTobuy = view.findViewById( R.id.tv_empty_cart_tobuy );
        ll_empty_shopcart = view.findViewById(R.id.ll_empty_shopcart);

        btnCheckOut.setOnClickListener( this );
        btnDelete.setOnClickListener( this );
        btnCollection.setOnClickListener( this );

        return view;
    }
    @Override
    public void onClick(View v) {
        if ( v == btnCheckOut ) {
            // Handle clicks for btnCheckOut
        } else if ( v == btnDelete ) {
            // Handle clicks for btnDelete
        } else if ( v == btnCollection ) {
            // Handle clicks for btnCollection
        }
    }

}
