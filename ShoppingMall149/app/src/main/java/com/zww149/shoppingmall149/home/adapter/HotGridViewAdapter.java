package com.zww149.shoppingmall149.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zww149.shoppingmall149.R;
import com.zww149.shoppingmall149.home.bean.ResultBeanData;
import com.zww149.shoppingmall149.utils.Constants;

import java.util.List;

public class HotGridViewAdapter extends BaseAdapter {
    private final List<ResultBeanData.ResultBean.HotInfoBean> datas;
    private Context mContext;

    public HotGridViewAdapter(Context mContext, List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {

        this.mContext = mContext;
        this.datas = hot_info;

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hot_grid_view, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_hot = convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        //设置位置得到对应的数据
        ResultBeanData.ResultBean.HotInfoBean hotInfoBean = datas.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + hotInfoBean.getFigure())
                .into(viewHolder.iv_hot);
      //  Log.e("hdsafjhjkdshajkfhkjshjkfhdjkhkjfhjkdhsjkhjkhgkds",hotInfoBean.getFigure());
        viewHolder.tv_name.setText(hotInfoBean.getName());
        viewHolder.tv_price.setText("￥" + hotInfoBean.getCover_price());
        return convertView;
    }

    static class ViewHolder {
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
