package com.example.jerry.ad340_hw4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle("Info");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),"You clicked Settings",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
