package com.example.jerry.ad340_hw3;

import android.content.Intent;
import android.content.UriPermission;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        int id = getIntent().getIntExtra(MovieActivity.A_INDEX, -1);
        String[] movie_info = MovieActivity.movies[id];
        TextView dTitle = (TextView) findViewById(R.id.textView_dTitle);
        dTitle.setText(movie_info[0]);
        TextView dYear = (TextView) findViewById(R.id.textView_dYear);
        dYear.setText(movie_info[1]);
        TextView director = (TextView) findViewById(R.id.textView_director);
        director.setText(movie_info[2]);
        TextView desc = (TextView) findViewById(R.id.textView_desc);
        desc.setText(movie_info[4]);
        desc.setMovementMethod(new ScrollingMovementMethod());
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        /*try {
            imageView.setImageBitmap(BitmapFactory.decodeStream(new URL(movie_info[3]).openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //imageView.setImageURI(Uri.parse(movie_info[3]));
    }
}
