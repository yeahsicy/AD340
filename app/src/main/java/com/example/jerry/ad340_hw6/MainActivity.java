package com.example.jerry.ad340_hw6;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.zip.Inflater;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    LiveCams liveCams = new LiveCams();
    ArrayList<OutputItem> outputItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // check Network Connectivity
        setContentView(R.layout.activity_main);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (!isConnected) {
            Toast.makeText(getApplicationContext(), "network disconnected please check", Toast.LENGTH_LONG).show();
            return;
        }
        // get json text
        String jsonStr = "";
        try {
            jsonStr = new DownloadTask().execute(new URL("https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2")).get();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // get liveCams obj
        Gson gson = new Gson();
        liveCams = gson.fromJson(jsonStr, LiveCams.class);
        //transformation
        outputItems = BindingUtil.getOutputItems(liveCams);
        //set recyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.per_item, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                OutputItem item = outputItems.get(position);
                TextView textView = (TextView) holder.itemView.findViewById(R.id.item_name);
                textView.setText(item.name);
                ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.item_pic);
                Glide.with(getApplicationContext()).
                        load(item.Uri).into(imageView);
            }

            @Override
            public int getItemCount() {
                return outputItems.size();
            }
        });
    }
}
