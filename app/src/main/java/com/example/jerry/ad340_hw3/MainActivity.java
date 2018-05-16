package com.example.jerry.ad340_hw3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Salute(View view) {
        Button button = (Button) view;
        CharSequence text = button.getText();
        Toast.makeText(getApplicationContext(), "Good " + text, Toast.LENGTH_LONG).show();
    }

    public void GoToMovie(View view) {
        Intent intent = new Intent(getApplicationContext(),MovieActivity.class);
        startActivity(intent);
    }
}
