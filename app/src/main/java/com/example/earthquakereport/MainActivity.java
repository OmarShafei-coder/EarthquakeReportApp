package com.example.earthquakereport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String strJson;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    ArrayList<Earthquake> earthquakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()){
            //HTTP request
            OkHttpClient client = new OkHttpClient();

            String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-01-31&minmag=6&limit=10";

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        strJson = response.body().string();

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                earthquakes = QueryUtils.extractEarthquakes(strJson);
                                recyclerView = findViewById(R.id.recycler_view);
                                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));

                                adapter = new CustomAdapter(getApplicationContext(), earthquakes);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                                adapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        //animate the clicked item
                                        recyclerView.getChildAt(position).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));

                                        Uri webpage = Uri.parse(earthquakes.get(position).getUrl());
                                        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                                        if (webIntent.resolveActivity(getPackageManager()) != null) {
                                            startActivity(webIntent);
                                        }
                                    }
                                });
                            }
                        });
                    }
                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after one second = 1000ms
                    finish();
                }
            }, 1000);

        }


    }
}
