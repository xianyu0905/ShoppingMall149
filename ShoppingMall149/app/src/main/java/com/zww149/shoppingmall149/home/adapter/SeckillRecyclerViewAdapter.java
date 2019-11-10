package com.zww149.shoppingmall149.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zww149.shoppingmall149.R;
import com.zww149.shoppingmall149.home.bean.ResultBeanData;
import com.zww149.shoppingmall149.utils.Constants;

import java.util.List;

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter
        <SeckillRecyclerViewAdapter.ViewHolder> {
    private final List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list;
    private final Context mContext;


    public SeckillRecyclerViewAdapter(Context mContext,
                                      List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_seckill,null);
        return new ViewHolder(itemView);
    }
    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //1.根据位置得到对应的数据
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean  listBean = list.get(position);

        //2.绑定数据
        //设置图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+listBean.getFigure())
                .into(holder.iv_figure);
        holder.tv_cover_price.setText(listBean.getCover_price());
        holder.tv_origin_price.setText(listBean.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //传到这里，进行实例化
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_figure = itemView.findViewById(R.id.iv_figure);
            tv_cover_price = itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = itemView.findViewById(R.id.tv_origin_price);
        }
    }
}
