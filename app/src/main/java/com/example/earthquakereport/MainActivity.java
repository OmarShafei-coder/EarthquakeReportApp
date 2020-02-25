package com.example.earthquakereport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String strJson;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    ArrayList<Earthquake> earthquakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    }
}
