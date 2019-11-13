package com.zww149.shoppingmall149.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zww149.shoppingmall149.MyApplication;
import com.zww149.shoppingmall149.home.bean.GoodBean;
import com.zww149.shoppingmall149.utils.CacheUtils;

import java.util.ArrayList;
import java.util.List;

public class CartStorage {
    public static final String JSON_CART = "json_cart";
    private static CartStorage instance;
    private final Context mContext;
    //android引入控件
    //SparseArray的性能优于HashMap
    private SparseArray<GoodBean> sparseArray;

    private CartStorage(Context context) {
        this.mContext = context;
        //把之前存储的数据读取出来
        sparseArray = new SparseArray<>(100);

        listToSparseArray();
    }

    /**
     * 从本地读取的数据加入到SparseArray中
     */
    private void listToSparseArray() {
        List<GoodBean> goodBeanList = getAllData();
        //把List数据转换成SparseArray
        for (int i =0 ;i<goodBeanList.size();i++){
            GoodBean goodBean = goodBeanList.get(i);
            sparseArray.put(Integer.parseInt(goodBean.getProduct_id()),goodBean);
        }


    }

    /**
     * 获取本地所有的数据
     *
     * @return
     */
    public List<GoodBean> getAllData() {
        //创建列表
        List<GoodBean> goodBeansList = new ArrayList<>();
        //先 从缓存中获取，1.本地获取

        String json = CacheUtils.getString(mContext, JSON_CART);
        //2.使用Gson转换列表
        //判断是否为空的时候执行
        if (!TextUtils.isEmpty(json)) {
            //把String转换成List
            goodBeansList = new Gson().fromJson(json,new TypeToken<List<GoodBean>>(){}.getType());
        }
        return goodBeansList;
    }

    /**
     * 得到购物车实例
     *
     * @return
     */
    public static CartStorage getInstance() {
        if (instance == null) {
            instance = new CartStorage(MyApplication.getContext());
        }
        return instance;
    }

    //做增删改查
    public void addData(GoodBean goodBean){
        //1.添加到内存中SparseArray

        //如果当前数据已经存在，就修改成number递增
        GoodBean tempData = sparseArray.get(Integer.parseInt(goodBean.getProduct_id()));
        if (tempData!=null){
            //内存中有了这条数据
            tempData.setNumber(tempData.getNumber()+1);
        }else {
            tempData = goodBean;
            tempData.setNumber(1);
        }
        //同步到内存
        sparseArray.put(Integer.parseInt(tempData.getProduct_id()),tempData);
        //2.同步到本地
        saveLocal();

    }
    /**
     * 删除数据
     */
    public void deleteData(GoodBean goodBean){
        //1.在内存中删除
        sparseArray.delete(Integer.parseInt(goodBean.getProduct_id()));
        //2.把内存的保持到本地
        saveLocal();
    }
    /**
     * 更新数据
     */
    public void updateData(GoodBean goodBean){
        //1.在内存中更新
        sparseArray.put(Integer.parseInt(goodBean.getProduct_id()),goodBean);
        //2.把内存的保持到本地
        saveLocal();
    }

    /**
     * 保存数据到本地
     */
    private void saveLocal() {
        //1.SparseArray转换成List
        List<GoodBean> goodBeanList = sparseToList();

        //2.把列表转换成String类型
        String json = new Gson().toJson(goodBeanList);
        //3.把String数据保存
        CacheUtils.saveString(mContext,JSON_CART,json);
    }

    private List<GoodBean> sparseToList() {
        List<GoodBean> goodBeanList = new ArrayList<>();
        for (int i = 0;i<sparseArray.size();i++){
            GoodBean goodBean = sparseArray.valueAt(i);
            goodBeanList.add(goodBean);
        }
        return goodBeanList;
    }
}
