package com.example.jerry.ad340_hw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MsgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(getLocalClassName(),"onCreate starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        String stringExtra = getIntent().getStringExtra(MainActivity.MSG_EXTRA);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(stringExtra);
        Log.d(getLocalClassName(),"onCreate ends");
    }
}
