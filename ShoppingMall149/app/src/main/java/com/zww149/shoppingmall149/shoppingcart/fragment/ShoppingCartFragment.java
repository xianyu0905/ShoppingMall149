package com.zww149.shoppingmall149.shoppingcart.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zww149.shoppingmall149.R;
import com.zww149.shoppingmall149.base.BaseFragment;
import com.zww149.shoppingmall149.home.bean.GoodBean;
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


    private static final String TAG = ShoppingCartFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public void initData() {
        super.initData();
       // textView.setText("购物车");

        List<GoodBean> goodBeanList =CartStorage.getInstance().getAllData();
        for (int i = 0;i<goodBeanList.size();i++){
            Log.e("TAG",goodBeanList.get(i).toString());
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
