package com.example.msi95o.weather;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by MSI 95O on 5/6/2017.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private ArrayList<Weather> weatherList;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView cityName;
        public RelativeLayout layout;

        public ViewHolder(View view) {
            super(view);
            layout = (RelativeLayout) view.findViewById(R.id.layout);
            cityName = (TextView) view.findViewById(R.id.city_name);
        }
    }


    public WeatherAdapter(ArrayList<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_list_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Weather weather = weatherList.get(position);
        holder.cityName.setText(weather.getCity());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(),DetailActivity.class).putExtra("latitude",weather.getLatitude()).putExtra("longitude",weather.getLongitude()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
