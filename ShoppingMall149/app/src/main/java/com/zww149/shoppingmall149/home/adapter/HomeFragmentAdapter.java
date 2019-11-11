package com.zww149.shoppingmall149.home.adapter;

import android.content.Context;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;
import com.zww149.shoppingmall149.R;
import com.zww149.shoppingmall149.app.GoodsInfoActivity;
import com.zww149.shoppingmall149.home.bean.ResultBeanData;

import com.zww149.shoppingmall149.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HomeFragmentAdapter extends RecyclerView.Adapter {
    /**
     * 广告条幅类型
     */
    public static final int BANNER = 0;
    /**
     * 频道类型
     */
    public static final int CHANNEL = 1;

    /**
     * 活动类型
     */
    public static final int ACT = 2;
    /**
     * 秒杀类型
     */
    public static final int SECKILL = 3;
    /**
     * 推荐类型
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖类型
     */
    public static final int HOT = 5;
    private final Context mContext;
    private final ResultBeanData.ResultBean resultBean;
    private final LayoutInflater mLayoutInflater;
    /**
     * 当前类型
     */
    private int currentType = 0;

    public HomeFragmentAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    /**
     * 相当于getview 创建部分的ViewHolder
     * 创建viewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext,
                    mLayoutInflater.inflate(R.layout.banner_viewpager, null));//传布局文件
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext,
                    mLayoutInflater.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(mContext,
                    mLayoutInflater.inflate(R.layout.act_item, null));
        }else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext,
                    mLayoutInflater.inflate(R.layout.seckill_item, null));
        }else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext,
                    mLayoutInflater.inflate(R.layout.recommend_item, null));
        }else if (viewType == HOT) {
            return new HotViewHolder(mContext,
                    mLayoutInflater.inflate(R.layout.hot_item, null));
        }
        return null;
    }

    /**
     * 相当于getview中的绑定数据模块
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(resultBean.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(resultBean.getSeckill_info());
        }else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        }else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(resultBean.getHot_info());
        }

    }

    //创建BANNER
    class BannerViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;

        //把banner实例化出来
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.banner = itemView.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //设置Banner的数据

            //得到图片集合的地址
            List<String> imagesUrl = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imageUrl = banner_info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }

            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            banner.setImages(imagesUrl);
            //设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
            //banner设置方法全部调用完毕后才调用
            banner.start();


            //设置item的点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {

                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();

                    startGoodsInfoActivity();

                }
            });

        }

    }

    /**
     * 启动商品信息列表页面
     */
    private void startGoodsInfoActivity(){
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        mContext.startActivity(intent);
    }
    /**
     * 得到的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    private class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context mContext, Object url, ImageView view) {
            //联网请求图片-Glide
            //Glide 加载图片的简单用法
            Glide.with(mContext).load(Constants.BASE_URL_IMAGE + url).into(view);
        }
    }


    private class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView gv_channel;

        private ChannelAdapter adapter;


        public ChannelViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            gv_channel = itemView.findViewById(R.id.gv_channel);
            //设置item的点击事件
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                    //startGoodsInfoActivity();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            //得到数据了
            //设置GridView的适配器
            adapter = new ChannelAdapter(mContext, channel_info);
            gv_channel.setAdapter(adapter);


        }
    }

    /**
     * 总共多少个item
     *
     * @return
     */
    @Override
    public int getItemCount() {
        //开发过程中从1-->2
        return 6;
    }

    private class ActViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager act_viewpager;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = itemView.findViewById(R.id.act_viewpager);

        }


        public void setData(final List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            //设置下图片的间距
            act_viewpager.setPageMargin(30);
            act_viewpager.setOffscreenPageLimit(3);//>=3
            //setPageTransformer 决定动画效果
            act_viewpager.setPageTransformer(true, new
                    AlphaPageTransformer(new ScaleInTransformer()));
            //1.有数据了
            //2.设置适配器
            act_viewpager.setAdapter(new PagerAdapter() {
                //放回总个数
                @Override
                public int getCount() {
                    return act_info.size();
                }

                /**
                 *
                 * @param view 页面
                 * @param object  instantiateItem 方法返回的值
                 * @return
                 */
                @Override
                public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                    return view == object;

                }

                /**
                 *
                 * @param container viewpager
                 * @param position 对应页面的位置
                 * @return
                 */
                @NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup container, final int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE +
                            act_info.get(position).getIcon_url()).into(imageView);
                    //添加到容器中
                    container.addView(imageView);

                    //设置点击事件
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext,"position==" + position, Toast.LENGTH_SHORT)
                                    .show();
                            startGoodsInfoActivity();
                        }
                    });
                    return imageView;
                }

                /**
                 * 销毁
                 * @param container
                 * @param position
                 * @param object
                 */
                @Override
                public void destroyItem(@NonNull ViewGroup container, int position,
                                        @NonNull Object object) {

                    super.destroyItem(container, position, object);
                }
            });
        }
    }

    private class SeckillViewHolder extends RecyclerView.ViewHolder {
        private  final Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_move_seckill;
        private RecyclerView rv_seckill;
        private SeckillRecyclerViewAdapter seckilladapter;

        /**
         * 相差多少时间--毫秒
         */
        private Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                dt = dt-1000;
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                String time = formatter.format(new Date(dt));
                tv_time_seckill.setText(time);

                //发之前要把消息移除一下
                handler.removeCallbacksAndMessages(null);
                //减一次后再发消息

                handler.sendEmptyMessageDelayed(0,1000);
                if (dt <=0){
                    //把消息移除
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };


        public SeckillViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_time_seckill = itemView.findViewById(R.id.tv_time_seckill);
            tv_move_seckill = itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill = itemView.findViewById(R.id.rv_seckill);
        }

        /**
         * 相差多少时间--毫秒
         */
        private long dt =0;


        /**
         *
         * @param seckill_info
         */

        public void setData(ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {
            //1.得到数据了
            //2.设置数据：文本和RecyclerView的数据
            seckilladapter = new SeckillRecyclerViewAdapter(mContext,
                    seckill_info.getList());
            rv_seckill.setAdapter(seckilladapter);

            //设置布局管理器
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,
                    LinearLayoutManager.HORIZONTAL,
                    false));//第二个设置方向：水平方向，第三是否倒叙-否

            //设置item的点击事件
            seckilladapter.setOnSeckillRecyclerView(
                    new SeckillRecyclerViewAdapter.OnSeckillRecyclerView() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext,"秒杀="+position,
                             Toast.LENGTH_SHORT).show();

                    startGoodsInfoActivity();
                }
            });
            //秒杀倒计时--毫秒
            dt = Integer.valueOf(seckill_info.getEnd_time())-Integer.valueOf(seckill_info.getStart_time());



            handler.sendEmptyMessageDelayed(0,1000);
        }
    }


    private class RecommendViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private TextView tv_motr_recommend;
        private GridView gv_recommend;
        private RecommendGridViewAdapter recommendadapter;

        public RecommendViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_motr_recommend = itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = itemView.findViewById(R.id.gv_recommend);

            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    Toast.makeText(mContext,"podition=="+position,Toast.LENGTH_SHORT).show();
                    startGoodsInfoActivity();
                }
            });


        }

        public void setData(List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
            //1.有数据了
            //2.设置Recommend适配器
            recommendadapter = new RecommendGridViewAdapter(mContext,recommend_info);
            gv_recommend.setAdapter(recommendadapter);

        }
    }

    //热卖
    private class HotViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView tv_more_hot;
        private GridView gv_hot;
        private HotGridViewAdapter hotAdapter;

        public HotViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext=mContext;
            tv_more_hot = itemView.findViewById(R.id.tv_more_hot);
            gv_hot = itemView.findViewById(R.id.gv_hot);
            //设置热卖中的item的监听
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext,"position=="+position,Toast.LENGTH_SHORT).show();
                    startGoodsInfoActivity();
                }
            });

        }

        public void setData(List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {

            //1.有数据了
            //2.设置"热卖"GridView适配器
            hotAdapter = new HotGridViewAdapter(mContext,hot_info);
            gv_hot.setAdapter(hotAdapter);

        }
    }
}
