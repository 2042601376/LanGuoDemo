package com.example.administrator.languodemo.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.languodemo.Activity.BaiDuActivity;
import com.example.administrator.languodemo.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/2.
 */
public class BaiduAdapter extends BaseAdapter {

    private ArrayList<String> nameList;
    private LayoutInflater mInflater;
    private BaiDuActivity mContext;
    private int[] loc = new int[2];//控件的坐标

    public BaiduAdapter(BaiDuActivity context,ArrayList<String> list) {
        mContext = context;
        nameList = list;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null) {
            view = mInflater.inflate(R.layout.itemview,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.bt_food = (Button) view.findViewById(R.id.item_view_bt_food);
            viewHolder.tv_food = (TextView)view.findViewById(R.id.item_view_tv_name);
            viewHolder.iv_food = (ImageView) view.findViewById(R.id.item_view_iv_picture);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tv_food.setText(nameList.get(i));
        viewHolder.bt_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BaiDuActivity activity = mContext;

                Log.i("点击按钮","点击了按钮" + i);
                view.getLocationInWindow(loc);
                Log.i("坐标",loc[0] + "+++" + loc[1]);
                activity.playAnimation(loc);
            }
        });

        return view;
    }

    class ViewHolder{
        Button bt_food;
        ImageView iv_food;
        TextView tv_food;
    }


}
