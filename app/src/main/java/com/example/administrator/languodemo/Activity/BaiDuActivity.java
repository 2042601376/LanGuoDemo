package com.example.administrator.languodemo.Activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.administrator.languodemo.Adapter.BaiduAdapter;
import com.example.administrator.languodemo.R;

import java.util.ArrayList;

public class BaiDuActivity extends AppCompatActivity {

    private ListView lv_baidu;
    private ArrayList<String> nameList;
    private BaiduAdapter mAdapter;
    private ImageView car;
    private ViewGroup anim_mask_layout;
    private Handler mHanlder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_du);

        initData();

        initViews();
    }

    private void initData() {
        nameList = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            nameList.add("汉堡" + i);
        }

        mHanlder = new Handler(getMainLooper());
    }

    private void initViews() {

        lv_baidu = (ListView)findViewById(R.id.baidu_lv_list);

        car = (ImageView)findViewById(R.id.baidu_iv_car);
        anim_mask_layout = (RelativeLayout)findViewById(R.id.anim_mask_layout);
        mAdapter = new BaiduAdapter(this,nameList);
        lv_baidu.setAdapter(mAdapter);
    }

    public void playAnimation(int[] start_location){
        Log.i("开始动画","开始动画");
        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.ball);

        Log.i("image","运动的View1" + img.getId());
        setAnim(img,start_location);
    }

    //创建动画 平移动画直接传递偏移量
    private Animation createAnim(int startX, int startY){
        int[] des = new int[2];
        car.getLocationInWindow(des);

        AnimationSet set = new AnimationSet(false);

        Animation translationX = new TranslateAnimation(0, des[0]-startX, 0, 0);
        //线性插值器 默认就是线性
        translationX.setInterpolator(new LinearInterpolator());
        Animation translationY = new TranslateAnimation(0, 0, 0, des[1]-startY);
        //设置加速插值器
        translationY.setInterpolator(new AccelerateInterpolator());
        Animation alpha = new AlphaAnimation(1,0.5f);

        set.addAnimation(translationX);
        set.addAnimation(translationY);
        set.addAnimation(alpha);
        set.setDuration(500);
        //设置动画保持在结束位置
        set.setFillAfter(true);

        return set;
    }

    //计算动画view在根部局中的坐标 添加到根部局中
    private void addViewToAnimLayout(final ViewGroup vg, final View view,
                                     int[] location) {

        int x = location[0];
        int y = location[1];
        int[] loc = new int[2];
        vg.getLocationInWindow(loc);
        view.setX(x);
        view.setY(y-loc[1]);
        vg.addView(view);
    }

    //设置动画结束移除动画view
    private void setAnim(final View v, int[] start_location) {

        Log.i("开始动画","开始动画2");
        Log.i("image","运动的View1" + v.getId());
        addViewToAnimLayout(anim_mask_layout, v, start_location);
        Animation set = createAnim(start_location[0],start_location[1]);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(final Animation animation) {
                //直接remove可能会因为界面仍在绘制中成而报错
                mHanlder.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        anim_mask_layout.removeView(v);
                        Log.i("image","动画结束");
                    }
                },100);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(set);
    }


}
