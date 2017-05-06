package com.example.msi95o.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Weather> cities;
    private RecyclerView recyclerView;
    private WeatherAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        initDataset();
        mAdapter = new WeatherAdapter(cities);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public void initDataset(){
        cities = new ArrayList <>();
        cities.add(new Weather("Belawa",-3.987414, 119.956033));
        cities.add(new Weather("Bola",-4.212259, 120.243931));
        cities.add(new Weather("Gilireng", -3.887359, 120.197005));
        cities.add(new Weather("Keera",-3.801936, 120.293356));
        cities.add(new Weather("Majauleng",4.048552, 120.148244));
        cities.add(new Weather("Maniang Pajo", -3.907165, 120.080547));
        cities.add(new Weather("Pammana",-4.202832, 120.085741));
        cities.add(new Weather("Penrang",-4.076809, 120.246121));
        cities.add(new Weather("Pitumpanua",-3.716672, 120.365846));
        cities.add(new Weather("Sabbang Paru",-3.716672, 120.365846));
        cities.add(new Weather("Sajoanging",-3.716672, 120.365846));
        cities.add(new Weather("Takkalalla",-3.716672, 120.365846));
        cities.add(new Weather("Tana Sitolo",4.047617, 120.056749));
        cities.add(new Weather("Tempe",-4.134586, 120.045464));
    }
}
