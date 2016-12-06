package com.example.administrator.languodemo.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;

import com.beiing.leafchart.LeafLineChart;
import com.beiing.leafchart.bean.Axis;
import com.beiing.leafchart.bean.AxisValue;
import com.beiing.leafchart.bean.Line;
import com.beiing.leafchart.bean.PointValue;
import com.example.administrator.languodemo.R;
import com.example.administrator.languodemo.View.CirclePercentView;

import java.util.ArrayList;
import java.util.List;

public class CurveActivity extends AppCompatActivity implements View.OnClickListener{

    private LeafLineChart lineChart;
    private CirclePercentView circlePercentView;
    private Button bt_circle;
    private int number = 50;
    private HorizontalScrollView scrollView;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curve);

        Intent i = getIntent();
        //判断是否有返回值
        if(i.getStringExtra("number") != null) {
            //获取修改后的数值
            Log.i("intent0","intent" + " :" + number + "+++" + i.getStringExtra("number"));
            number = Integer.valueOf(i.getStringExtra("number")) + 1;
            Log.i("intent0","intent" + " :" + number);
        }

        initViews();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initLineChart();
            }
        }, 2000);

    }


    private void initViews() {
        lineChart = (LeafLineChart)findViewById(R.id.leaf_chart);
        circlePercentView = (CirclePercentView)findViewById(R.id.curve_circleView);
        circlePercentView.setPercent(number);
        bt_circle = (Button)findViewById(R.id.curve_bt_changeCircle);
        //定义点击事件
        bt_circle.setOnClickListener(this);

    }

    //初始化表格
    private void initLineChart() {
        Axis axisX = new Axis(getAxisValuesX());
        axisX.setAxisColor(Color.parseColor("#33B5E5")).setTextColor(Color.DKGRAY).setHasLines(true);
        Axis axisY = new Axis(getAxisValuesY());
        axisY.setAxisColor(Color.parseColor("#33B5E5")).setTextColor(Color.DKGRAY).setHasLines(true).setShowText(true);
        lineChart.setAxisX(axisX);
        lineChart.setAxisY(axisY);
        List<Line> lines = new ArrayList<>();
        lines.add(getFoldLine());
        lines.add(getCompareLine());
        lineChart.setChartData(lines);

        lineChart.showWithAnimation(3000);

    }

    /**
     * 绘制X轴
     */
    private List<AxisValue> getAxisValuesX(){
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            AxisValue value = new AxisValue();
            value.setLabel(i + "");
            axisValues.add(value);
        }
        return axisValues;
    }

    /**
     * 绘制Y轴
     * @return
     */
    private List<AxisValue> getAxisValuesY(){
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            AxisValue value = new AxisValue();
            value.setLabel(String.valueOf(i * 10));
            axisValues.add(value);
        }
        return axisValues;
    }

    /**
     * 获取第一条曲线值
     * @return
     */
    private Line getFoldLine(){
        List<PointValue> pointValues = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            PointValue pointValue = new PointValue();
            pointValue.setX( (i - 1) / 11f);
            int var = (int) (Math.random() * 100);
            pointValue.setLabel(String.valueOf(var));
            pointValue.setY(var / 100f);
            pointValues.add(pointValue);
        }

        Line line = new Line(pointValues);
        line.setLineColor(Color.parseColor("#33B5E5"))
                .setLineWidth(3)
                .setPointColor(Color.YELLOW)
                .setCubic(true)
                .setPointRadius(3)
                .setFill(false)
                .setHasLabels(true)
                .setLabelColor(Color.parseColor("#33B5E5"));
        return line;
    }

    /**
     * 获取第二条曲线的值
     * @return
     */
    private Line getCompareLine(){
        List<PointValue> pointValues = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            PointValue pointValue = new PointValue();
            pointValue.setX( (i - 1) / 11f);
            int var = (int) (Math.random() * 100);
            pointValue.setLabel(String.valueOf(var));
            pointValue.setY(var / 100f);
            pointValues.add(pointValue);
        }

        Line line = new Line(pointValues);
        line.setLineColor(Color.MAGENTA)
                .setLineWidth(3)
                .setPointColor(Color.MAGENTA)
                .setCubic(true)
                .setPointRadius(3)
                .setFill(false)
                .setHasLabels(false);
        return line;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //修改圆环
            case R.id.curve_bt_changeCircle:
                Intent toChangeIntent = new Intent(CurveActivity.this,ChangeCircleActivity.class);
                startActivity(toChangeIntent);
                finish();
                break;
        }
    }
}
