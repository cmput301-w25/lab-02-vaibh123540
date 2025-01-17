package com.example.listycity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    int index = -1;
    View lastview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton", "Paris", "London"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        Button addButton = findViewById(R.id.add);
        Button deleteButton = findViewById(R.id.delete);

        EditText cityInput = findViewById(R.id.city_input);
        Button confirmButton = findViewById(R.id.confirm);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (lastview!=null) lastview.setBackgroundColor(Color.WHITE);
                lastview = view;
                index = position;
                lastview.setBackgroundColor(Color.GRAY);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityInput.setText("");
                cityInput.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String newCity = cityInput.getText().toString().trim();
                    if (!newCity.isEmpty()) {
                        dataList.add(newCity);
                        cityAdapter.notifyDataSetChanged();
                    }
                cityInput.setVisibility(View.GONE);
                confirmButton.setVisibility(View.GONE);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index!=-1) {
                    lastview.setBackgroundColor(Color.WHITE);
                    dataList.remove(index);
                    cityAdapter.notifyDataSetChanged();
                    index = -1;
                }
            }
        });
    }

    
}