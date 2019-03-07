package com.example.feggetsberger16.simplecarlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    List<Car> cars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView);
        cars = new ArrayList<>();
        readDataFromCSV();
        ListViewAdapter listViewAdapter = new ListViewAdapter(this, R.layout.list_view_layout, cars);
        lv.setAdapter(listViewAdapter);
    }

    private void readDataFromCSV()
    {
        int lineCounter = 0;
        try {
            BufferedReader bin = new BufferedReader(new InputStreamReader(getAssets().open("cars.csv")));
            while(true)
            {

                    String line = bin.readLine();
                    if(line==null){
                        break;
                    }
                    if(!line.contains("id")) {
                        String[] arr = line.split(",");
                        String first_name = arr[1];
                        String last_name = arr[2];
                        String brand = arr[11];
                        String model = arr[12];
                        cars.add(new Car(first_name, last_name, brand, model));
                        lineCounter++;
                    }

            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
