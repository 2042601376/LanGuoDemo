package com.example.administrator.languodemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.languodemo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button toBaidu,toCurve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        toBaidu = (Button)findViewById(R.id.main_bt_baidu);
        toCurve = (Button)findViewById(R.id.main_bt_curve);

        toBaidu.setOnClickListener(this);
        toCurve.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_bt_baidu:
                Intent toBaiDuIntent = new Intent(MainActivity.this,BaiDuActivity.class);
                startActivity(toBaiDuIntent);
                break;
            case R.id.main_bt_curve:
                Intent toCurveIntent = new Intent(MainActivity.this,CurveActivity.class);
                startActivity(toCurveIntent);
                break;
        }
    }
}
