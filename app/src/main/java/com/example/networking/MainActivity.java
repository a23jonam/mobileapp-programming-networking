package com.example.networking;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

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
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
    }

}
