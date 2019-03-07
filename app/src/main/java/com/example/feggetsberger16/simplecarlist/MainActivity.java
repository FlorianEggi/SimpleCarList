package com.example.feggetsberger16.simplecarlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    List<Car> cars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView);
        fillItemsList(cars);
        ListViewAdapter listViewAdapter = new ListViewAdapter(this, R.layout.list_view_layout, cars);
        lv.setAdapter(listViewAdapter);
    }

    private void fillItemsList(List<Car> list)
    {
        list.add(new Car("Lukas","Foro","VW","Santana"));
        list.add(new Car("Michael","Gahbauer","Audi","100"));
        list.add(new Car("Oliver","Doppelbauer","Fiat","Barchetta"));
    }


}
