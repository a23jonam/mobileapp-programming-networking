package com.example.networking;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";

    private ArrayList<RecyclerViewItem> items = new ArrayList<>();
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            ArrayList<Mountain> mountains = new ArrayList<>(Arrays.asList(
                    new Mountain("Kebnekaise", "Sweden", 2096),
                    new Mountain("Fuji", "Japan", 3776),
                    new Mountain("Mont Blanc", "Italy", 4809),
                    new Mountain("Denali", "USA", 6190),
                    new Mountain("Kilimanjaro", "Tanzania", 5895),
                    new Mountain("K2", "Pakistan", 8611)));

            items = new ArrayList<>();
            for (Mountain mountain : mountains) {
                items.add(new RecyclerViewItem(mountain.getName()));

            }

            adapter = new RecyclerViewAdapter(this, mountains, new RecyclerViewAdapter.OnClickListener() {
                @Override
                public void onClick(Mountain item) {
                    Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
                }
            });

            RecyclerView view = findViewById(R.id.recycler_view);
            view.setLayoutManager(new LinearLayoutManager(this));
            view.setAdapter(adapter);

            new JsonFile(this, this).execute(JSON_FILE);
            //new JsonTask(this).execute(JSON_URL);
    }

    @Override
    public void onPostExecute(String json) {
            Log.d("Json_URL","" + json);
            Gson gson = new Gson();
            Type type = new TypeToken<List<Mountain>>() {
            }.getType();
            List<Mountain> listOfMountains = gson.fromJson(json, type);

            for (Mountain mountain : listOfMountains) {
            }

            adapter.updateData(listOfMountains);

            adapter.notifyDataSetChanged();
    }

}
