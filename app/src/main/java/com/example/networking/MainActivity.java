package com.example.networking;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "HTTPS_URL_TO_JSON_DATA_CHANGE_THIS_URL";
    private final String JSON_FILE = "mountains.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JsonFile(this, this).execute(JSON_FILE);

        ArrayList<Mountain> mountains = new ArrayList<>(Arrays.asList(
                new Mountain("Kebnekaise","Sweden",2096),
                new Mountain("Fuji","Japan",3776),
                new Mountain("Matterhorn","Switzerland",4478),
                new Mountain("Mont Blanc","Italy",4809),
                new Mountain("Denali","USA",6190),
                new Mountain("K2","Pakistan",8611)));

        ArrayList<RecyclerViewItem> items = new ArrayList<>();
        for (Mountain mountain : mountains){
            items.add(new RecyclerViewItem(mountain.getName()));
        }


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();

            }
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
    }

}
