package com.example.earthquakereport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private ArrayList<Module> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        data.add(new Module(R.string.san_francisco));
        data.add(new Module(R.string.london));
        data.add(new Module(R.string.tokyo));
        data.add(new Module(R.string.mexico_city));
        data.add(new Module(R.string.moscow));
        data.add(new Module(R.string.rio_de_janeiro));
        data.add(new Module(R.string.paris));

        adapter = new CustomAdapter(getApplicationContext(), data);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
