package com.zww149.shoppingmall149.app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.zww149.shoppingmall149.R;
import com.zww149.shoppingmall149.home.adapter.HomeFragmentAdapter;
import com.zww149.shoppingmall149.home.bean.GoodBean;
import com.zww149.shoppingmall149.utils.Constants;

public class GoodsInfoActivity extends Activity implements View.OnClickListener {
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;

    private TextView tvmoreshare;
    private TextView tvmoresearch;
    private TextView tvmorehome;
    private Button btnmore;
    private GoodBean goodBean;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-11-12 21:52:20 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        ibGoodInfoBack = (ImageButton) findViewById(R.id.ib_good_info_back);
        ibGoodInfoMore = (ImageButton) findViewById(R.id.ib_good_info_more);
        ivGoodInfoImage = (ImageView) findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = (TextView) findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = (TextView) findViewById(R.id.tv_good_info_price);
        tvGoodInfoStore = (TextView) findViewById(R.id.tv_good_info_store);
        tvGoodInfoStyle = (TextView) findViewById(R.id.tv_good_info_style);
        wbGoodInfoMore = (WebView) findViewById(R.id.wb_good_info_more);
        llGoodsRoot = (LinearLayout) findViewById(R.id.ll_goods_root);
        //联系客服
        tvGoodInfoCallcenter = (TextView) findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = (TextView) findViewById(R.id.tv_good_info_collection);
        tvGoodInfoCart = (TextView) findViewById(R.id.tv_good_info_cart);
        btnGoodInfoAddcart = (Button) findViewById(R.id.btn_good_info_addcart);

        tvmoreshare = findViewById(R.id.tv_more_share);
        tvmoresearch = findViewById(R.id.tv_more_search);
        tvmorehome = findViewById(R.id.tv_more_home);
        btnmore = findViewById(R.id.btn_more);

        //点击监听事件
        ibGoodInfoBack.setOnClickListener(this);
        ibGoodInfoMore.setOnClickListener(this);
        btnGoodInfoAddcart.setOnClickListener(this);

        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);

        tvmoreshare.setOnClickListener(this);
        tvmoresearch.setOnClickListener(this);
        tvmorehome.setOnClickListener(this);

    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-11-12 21:52:20 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == ibGoodInfoBack) {
            //返回把它关闭
            finish();
        } else if (v == ibGoodInfoMore) {
            //更多
            Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
        } else if (v == btnGoodInfoAddcart) {
            //添加到购物车
            Toast.makeText(this, "加入购物车", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCallcenter) {
            //添加到购物车
            Toast.makeText(this, "联系客服", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCollection) {
            //添加到购物车
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCart) {
            //添加到购物车
            Toast.makeText(this, "购物车", Toast.LENGTH_SHORT).show();
        } else if (v == tvmoreshare) {
            //添加到购物车
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        } else if (v == tvmoresearch) {
            //添加到购物车
            Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
        } else if (v == tvmorehome) {
            //添加到购物车
            Toast.makeText(this, "主页面", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();

        //接收数据  Intent
        goodBean = (GoodBean) getIntent().getSerializableExtra("goodBean");
        //如果不为空，就说明成功！
        if (goodBean != null) {
            // Toast.makeText(this,"goodBean=="+goodBean,Toast.LENGTH_SHORT).show();
            setDataForView(goodBean);

        }
    }

    /**
     * 设置数据
     *
     * @param goodBean
     */
    private void setDataForView(GoodBean goodBean) {

        //设置图片

        //iv_good_info_image
        Glide.with(this).load(Constants.BASE_URL_IMAGE + goodBean.getFigure())
                .into(ivGoodInfoImage);
        //设置文本
        //tv_good_info_name
        tvGoodInfoName.setText(goodBean.getName());

        //设置价格
        tvGoodInfoPrice.setText("¥" + goodBean.getCover_price());

        //根据产品号查找对应的网页
        setWebViewData(goodBean.getProduct_id());
    }

    private void setWebViewData(String product_id) {
        if (product_id != null) {
            wbGoodInfoMore.loadUrl("http://www.atguigu.com");
            //设置JavaScript
            WebSettings webSettings = wbGoodInfoMore.getSettings();
            webSettings.setUseWideViewPort(true);//支持双击页面变大变小
            webSettings.setJavaScriptEnabled(true);//设置支持JavaScript
            //优先使用缓存
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


            wbGoodInfoMore.setWebViewClient(new WebViewClient() {
                //低版本

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }

                /*@Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.loadUrl(request.getUrl().toString());
                    }
                    return true;
                }*/
            });
        }
    }
}
