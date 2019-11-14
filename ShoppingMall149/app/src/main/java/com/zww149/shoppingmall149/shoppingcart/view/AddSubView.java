package com.zww149.shoppingmall149.shoppingcart.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zww149.shoppingmall149.R;

/**
 * 自定义删除增加按钮
 */
public class AddSubView extends LinearLayout implements View.OnClickListener {
    private ImageView iv_sub;
    private ImageView iv_add;
    private TextView tv_value;
    //加跟减都有个最大值和最小值
    private int value = 1;
    private int minValue = 1;
    private int maxValue = 5;
    private Context mContext;


    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        //把布局文件实例化，并且加载到AddSubView类中
        View.inflate(context, R.layout.add_sub_view, this);
        iv_sub = findViewById(R.id.iv_sub);
        iv_add = findViewById(R.id.iv_add);
        tv_value = findViewById(R.id.tv_value);

        int value = getValue();
        setValue(value);

        //设置点击事件
        iv_sub.setOnClickListener(this);
        iv_add.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_sub://减
                subNumber();
                break;
            case R.id.iv_add://加
                addNumber();
                break;

        }
    }

    //减
    private void subNumber() {
        if (value > minValue) {
            value--;
        }
        setValue(value);

        if (onNumberChangerListener!=null){
            onNumberChangerListener.onNumberChange(value);
        }
  //      Toast.makeText(mContext,"当前商品数=="+value,Toast.LENGTH_SHORT).show();
    }

    //加
    private void addNumber() {
        if (value < maxValue) {
            value++;
        }
        setValue(value);
        if (onNumberChangerListener!=null){
            onNumberChangerListener.onNumberChange(value);
        }
   //     Toast.makeText(mContext,"当前商品数=="+value,Toast.LENGTH_SHORT).show();

    }

    public int getValue() {
        String valueStr = tv_value.getText().toString().trim();
        if (!TextUtils.isEmpty(valueStr)) {
            value = Integer.parseInt(valueStr);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_value.setText(value + "");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    //监听数据的方法

    /**
     * 当数量发生变化的时候回调
     */
    public interface OnNumberChangerListener{
        /**
         * 当数据发生变化的时候回调
         * @param value
         */
        public void onNumberChange(int value);
    }
    private OnNumberChangerListener onNumberChangerListener;

    //由外界实例化过来

    /**
     * 设置数量变化的监听
     * @param onNumberChangerListener
     */
    public void setOnNumberChangerListener(OnNumberChangerListener onNumberChangerListener) {
        this.onNumberChangerListener = onNumberChangerListener;
    }
}
