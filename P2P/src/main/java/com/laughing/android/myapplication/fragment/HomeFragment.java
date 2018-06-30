package com.laughing.android.myapplication.fragment;

import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.laughing.android.myapplication.R;
import com.laughing.android.myapplication.bean.Image;
import com.laughing.android.myapplication.bean.Index;
import com.laughing.android.myapplication.bean.Product;
import com.laughing.android.myapplication.common.AppNetConfig;
import com.laughing.android.myapplication.utils.ToastUtils;
import com.laughing.android.myapplication.view.RoundProgress;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.List;

import static com.laughing.android.myapplication.R.id.title_tv;


public class HomeFragment extends BaseFragment {

    private ImageView mTitle_right;
    private TextView mTitle_tv;
    private ImageView mTitle_left;
    private Index index;//实体类
    private ViewPager vp_barner;
    private CirclePageIndicator circle_barner;
    private AsyncHttpClient client = new AsyncHttpClient();
    private RoundProgress p_progresss;
    private int mTotalProgress;

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mTitle_left = (ImageView) mView.findViewById(R.id.title_left);
        mTitle_tv = (TextView) mView.findViewById(title_tv);
        mTitle_right = (ImageView) mView.findViewById(R.id.title_right);
        mTitle_tv.setText("首页");
        mTitle_left.setVisibility(View.INVISIBLE);//隐藏后退键
        mTitle_right.setVisibility(View.INVISIBLE);//隐藏设置键
        vp_barner = (ViewPager) mView.findViewById(R.id.vp_barner);
        circle_barner = (CirclePageIndicator) mView.findViewById(R.id.circle_barner);
        p_progresss = (RoundProgress) mView.findViewById(R.id.p_progresss);
    }

    @Override
    protected void initData(String content) {
        if (!TextUtils.isEmpty(content)) {
            index = new Index();
            ToastUtils.showToastPlus(getActivity(), "onSuccess");
            JSONObject jsonObject = JSON.parseObject(content);
            String proInfo = jsonObject.getString("proInfo");
            Product product = JSON.parseObject(proInfo, Product.class);
            String imageArr = jsonObject.getString("imageArr");
            List<Image> imageList = JSON.parseArray(imageArr, Image.class);
            index.imageList = imageList;
            index.product = product;
            vp_barner.setAdapter(new MyAdapter());
            //viewpager交给指示器
            circle_barner.setViewPager(vp_barner);
            mTotalProgress = Integer.parseInt(index.product.progress);
//                p_progresss.setProgress(mTotalProgress);
//                ToastUtils.showToastPlus(getActivity(),mTotalProgress+"");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int tempProgress = 0;
                    try {
                        while (tempProgress <= mTotalProgress) {
                            p_progresss.setProgress(tempProgress);
                            tempProgress++;
//                                Thread.sleep(100);
                            SystemClock.sleep(100);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = UIUtils.getXmlView(R.layout.fragment_home);
//        initView(view);
//        initData();
//
//        return view;
//    }

    //    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_home;
//    }
//
//    @Override
//    protected void initView() {
//        mTitle_left = (ImageView) mView.findViewById(R.id.title_left);
//        mTitle_tv = (TextView) mView.findViewById(R.id.title_tv);
//        mTitle_right = (ImageView) mView.findViewById(title_right);
//        mTitle_left.setVisibility(View.INVISIBLE);//隐藏后退键
//        mTitle_right.setVisibility(View.INVISIBLE);//隐藏设置键
//        vp_barner = (ViewPager) mView.findViewById(R.id.vp_barner);
//        circle_barner = (CirclePageIndicator) mView.findViewById(R.id.circle_barner);
//        p_progresss = (RoundProgress) mView.findViewById(R.id.p_progresss);
//    }
//    @Override
//
//    protected void initData() {
//        index = new Index();
//        client.post(AppNetConfig.INDEX, new AsyncHttpResponseHandler() {
//
//
//            @Override
//            public void onSuccess(String content) {
//                ToastUtils.showToastPlus(getActivity(), "onSuccess");
//                JSONObject jsonObject = JSON.parseObject(content);
//                String proInfo = jsonObject.getString("proInfo");
//                Product product = JSON.parseObject(proInfo, Product.class);
//                String imageArr = jsonObject.getString("imageArr");
//                List<Image> imageList = JSON.parseArray(imageArr, Image.class);
//                index.imageList = imageList;
//                index.product = product;
//                vp_barner.setAdapter(new MyAdapter());
//                //viewpager交给指示器
//                circle_barner.setViewPager(vp_barner);
//                mTotalProgress = Integer.parseInt(index.product.progress);
////                p_progresss.setProgress(mTotalProgress);
////                ToastUtils.showToastPlus(getActivity(),mTotalProgress+"");
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        int tempProgress = 0;
//                        try {
//                            while (tempProgress <= mTotalProgress) {
//                                p_progresss.setProgress(tempProgress);
//                                tempProgress++;
////                                Thread.sleep(100);
//                                SystemClock.sleep(100);
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
//
//
//           }
//
//            @Override
//            public void onFailure(Throwable error, String content) {
//                super.onFailure(error, content);
//                ToastUtils.showToastPlus(getActivity(), "请求服务器数据异常");
//            }
//        });
//    }
//
//    private void initView(View view) {
//        mTitle_left = (ImageView) view.findViewById(R.id.title_left);
//        mTitle_tv = (TextView) view.findViewById(R.id.title_tv);
//        mTitle_right = (ImageView) view.findViewById(title_right);
//        mTitle_left.setVisibility(View.INVISIBLE);//隐藏后退键
//        mTitle_right.setVisibility(View.INVISIBLE);//隐藏设置键
//        vp_barner = (ViewPager) view.findViewById(R.id.vp_barner);
//        circle_barner = (CirclePageIndicator) view.findViewById(R.id.circle_barner);
//        p_progresss = (RoundProgress) view.findViewById(R.id.p_progresss);
//    }
//
//
    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return index.imageList == null ? 0 : index.imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String imageUrl = index.imageList.get(position).IMAURL;
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(getActivity()).load(imageUrl).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
