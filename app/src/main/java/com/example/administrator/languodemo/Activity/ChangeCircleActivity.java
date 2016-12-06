package com.example.administrator.languodemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.languodemo.R;

public class ChangeCircleActivity extends AppCompatActivity {

    private EditText et_number;
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_circle);

        et_number = (EditText)findViewById(R.id.change_et_number);

    }

    public void changeNumber(View view) {
        Intent back = new Intent(ChangeCircleActivity.this,CurveActivity.class);
        number = et_number.getText().toString().trim();
        Log.i("Number","+++" + number);
        back.putExtra("number",number);
        startActivity(back);
        finish();
    }
}
