package com.example.jerry.ad340_hw2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String MSG_EXTRA = "MSG_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Send_message(View view) {
        Intent intent = new Intent(getApplicationContext(), MsgActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String s = editText.getText().toString();
        intent.putExtra(MSG_EXTRA, s);
        startActivity(intent);
    }
}
