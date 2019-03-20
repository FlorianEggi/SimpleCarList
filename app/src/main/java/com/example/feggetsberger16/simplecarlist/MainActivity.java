package com.example.feggetsberger16.simplecarlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.opengl.EGLExt;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    List<Car> cars;
    int herstellerCounter = 0;
    int modelCounter = 0;
    List<String> hersteller;
    List<Car> backupCars;
    Button buttonFilter;
    List<Car> carsFilter;
    ListViewAdapter listViewAdapter;
    View view;
    Spinner spinnerBrand;
    Spinner spinnerModel;
    TextView textViewFirstName;
    TextView textViewLastName;
    Button buttonAdd;
    FloatingActionButton fab;
    List<String> models;
    ArrayAdapter<String> ada1;
    ArrayAdapter<String> ada2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView);
        cars = new ArrayList<>();
        readDataFromCSV();
        view = View.inflate(this,R.layout.activity_dialog,null);
        spinnerBrand = findViewById(R.id.brand);
        models = new ArrayList<>();
        hersteller = createHersteller();
        spinnerModel = findViewById(R.id.model);
        models = createModel();

        textViewFirstName = findViewById(R.id.first_name);
        textViewLastName = findViewById(R.id.last_name);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonFilter = findViewById(R.id.buttonFilter);

        listViewAdapter = new ListViewAdapter(this, R.layout.list_view_layout, cars);
        lv.setAdapter(listViewAdapter);
        final Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,hersteller);
        spinner.setAdapter(spinnerAdapter);
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brand = spinner.getSelectedItem().toString();
                filterByBrand(brand);
            }
        });
        /*if("".equals(spinner.getSelectedItem().toString()))
        {
            String brand = spinner.getSelectedItem().toString();
            filterByBrand(brand);
        }*/
        ada1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,hersteller);
        spinnerBrand.setAdapter(ada1);
        ada2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,models);
        spinnerModel.setAdapter(ada2);


        fab = findViewById(R.id.addButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });

    }

    public void dialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(view).setCancelable(false);
        alert.show();
    }

    public void onClickAdd(View view)
    {
        Car c1 = new Car(textViewFirstName.toString(),textViewLastName.toString(),spinnerBrand.toString(),spinnerModel.toString());
        cars.add(c1);
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
        backupCars = cars;
    }

    private List<String> createHersteller()
    {

        List<String> list = new ArrayList<>();
        if(herstellerCounter == 0)
        {
            list.add("");
        }
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
                    String brand = arr[11];
                    if(!list.contains(brand)) {
                        list.add(brand);
                    }
                    lineCounter++;
                }

            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        Collections.sort(list);
        herstellerCounter++;
        return list;
    }

    private List<String> createModel()
    {

        List<String> list = new ArrayList<>();
        if(modelCounter == 0)
        {
            list.add("");
        }
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
                    String model = arr[12];
                    if(!list.contains(model)) {
                        list.add(model);
                    }
                    lineCounter++;
                }

            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        Collections.sort(list);
        modelCounter++;
        return list;
    }

    private void filterByBrand(String brand)
    {
        cars = backupCars;
        carsFilter = new ArrayList<>();
        for (int i = 0; i < cars.size(); i++) {
            if(cars.get(i).getMake().equals(brand))
            {
                carsFilter.add(cars.get(i));
            }
        }
        cars = carsFilter;
        lv.setAdapter(listViewAdapter);
    }
}
