package com.zww149.shoppingmall149.shoppingcart.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.zww149.shoppingmall149.R;
import com.zww149.shoppingmall149.home.bean.GoodBean;
import com.zww149.shoppingmall149.shoppingcart.utils.CartStorage;
import com.zww149.shoppingmall149.shoppingcart.view.AddSubView;
import com.zww149.shoppingmall149.utils.Constants;

import java.util.List;



/**
 * 购物车适配器的构造方法
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {


    private final Context mContext;
    private final List<GoodBean> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    //完成状态下的删除
    private final CheckBox cbAll;


    public ShoppingCartAdapter(Context mContext, List<GoodBean> goodBeanList, TextView tvShopcartTotal,
                               CheckBox checkboxAll, CheckBox cbAll) {
        this.mContext = mContext;
        this.datas = goodBeanList;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.cbAll= cbAll;

        showTotalPrice();

        //设置点击事件
        setListener();

        //校验是否全选
        checkAll();
    }

    private void setListener() {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //传的是位置
                //1.根据位置找到对应的Bea对象
                GoodBean goodBean = datas.get(position);
                //2.设置取反状态
                goodBean.setSelected(!goodBean.isSelected());
                //3.刷新状态
                //notifyItemChanged(position);
                //notifyItemChanged(position);
                //notifyRemoveChang(position);
                notifyItemRemoved(position);
                //4.重新计算总价格
                showTotalPrice();
            }
        });
    }

    /**
     * 计算总价值
     *
     * @return
     */
    private double getTotalPrice() {
        double totalPrice = 0.0;
        if (datas != null && datas.size() > 0) {

            for (int i = 0; i < datas.size(); i++) {
                GoodBean goodBean = datas.get(i);
                if (goodBean.isSelected()) {
                    totalPrice = totalPrice + Double.valueOf(goodBean.getNumber()) *
                            Double.valueOf(goodBean.getCover_price());

                }
            }
        }
        return totalPrice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_shop_cart, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //1.根据位置得到对应的Bean对象
        final GoodBean goodBean = datas.get(position);
        //2.设置数据
        holder.cb_gov.setChecked(goodBean.isSelected());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + goodBean.getFigure())
                .into(holder.iv_gov);
        holder.tv_desc_gov.setText(goodBean.getName());
        holder.tv_price_gov.setText("¥" + goodBean.getCover_price());
        holder.ddSubView.setValue(goodBean.getNumber());

        //设置最大值和最小值
        holder.ddSubView.setMinValue(1);
        holder.ddSubView.setMaxValue(8);

        //设置商品数量的变化
        holder.ddSubView.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            @Override
            public void onNumberChange(int value) {
                //1.当前列表内存中
                goodBean.setNumber(value);
                // 2.本地要更新
                CartStorage.getInstance().updateData(goodBean);
                //3.刷新适配器
                //notifyItemChanged(position);
                notifyItemRemoved(position);
                //4.校验是否全选
                checkAll();
                //5.再次计算总价格
                showTotalPrice();
            }
        });

        //设置全选或非全选
        //CheckBox的点击事件
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.得到状态
                boolean isCheck = checkboxAll.isChecked();
                //2.根据状态设置我们的全选和非全选
                checkAll_none(isCheck);
                //3.计算总价格
                showTotalPrice();
            }
        });

        //cbAll的点击事件
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.得到状态
                boolean isCheck = cbAll.isChecked();
                //2.根据状态设置我们的全选和非全选
                checkAll_none(isCheck);

            }
        });
    }

    /**
     * 设置全选和非全选
     * @param isCheck
     */
    public void checkAll_none(boolean isCheck) {
        if (datas!=null&& datas.size()>0){

            for (int i=0;i<datas.size();i++){
                GoodBean goodBean = datas.get(i);
                goodBean.setSelected(isCheck);
                //notifyItemChanged(i);
                notifyItemRemoved(i);
            }

        }
    }

    public void checkAll() {
        if (datas!=null&& datas.size()>0){
            int number =0;
            for (int i=0;i<datas.size();i++){
                GoodBean goodBean = datas.get(i);
                if (!goodBean.isSelected()){
                    //非全选
                    checkboxAll.setChecked(false);
                    cbAll.setChecked(false);
                }else {
                    //选中
                    number++;
                }
            }
            if (number == datas.size()){
                //全选
                checkboxAll.setChecked(true);
                cbAll.setChecked(true);
            }
        }else {
            checkboxAll.setChecked(false);
            cbAll.setChecked(false);
        }
    }

    public void showTotalPrice() {
        tvShopcartTotal.setText("合计：" + getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void deleteData() {
        if (datas!=null&& datas.size()>0){
            //删除选中
            for (int i = 0;i<datas.size();i++){
                //删除选中的
                GoodBean goodBean = datas.get(i);
                if (goodBean.isSelected()){
                    //内存-移除的
                    datas.remove(goodBean);
                    //保持到本地
                    CartStorage.getInstance().deleteData(goodBean);
                    //刷新
                   // notifyItemChanged(i);
                    notifyItemRemoved(i);
                    i--;
                }
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private AddSubView ddSubView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cb_gov = itemView.findViewById(R.id.cb_gov);
            iv_gov = itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = itemView.findViewById(R.id.tv_price_gov);
            ddSubView = itemView.findViewById(R.id.ddSubView);

            //设置item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener!=null){
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    /**
     * 点击item的监听者
     */
    //自己定义个接口可以调用
    public interface OnItemClickListener{
        /**
         * 当点击某条的时候被回调
         * @param position
         */
        public void onItemClick(int position);

    }
    private OnItemClickListener onItemClickListener;

    /**
     * 设置item的监听
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
