package com.example.msi95o.weather;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    private final String API_KEY = "d60802d2761e2c6699c15a93b3af0622";
    private TextView cityname;
    private TextView weather;
    private TextView temperature;
    private TextView humidity;
    private TextView pressure;
    private JSONObject json = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        double latitude = getIntent().getDoubleExtra("latitude",0);
        double longitude = getIntent().getDoubleExtra("longitude",0);
        final String URL = WeatherURLBuilder(latitude,longitude);

        cityname = (TextView) findViewById(R.id.city);
        weather = (TextView) findViewById(R.id.weather);
        temperature = (TextView) findViewById(R.id.temperature);
        humidity = (TextView) findViewById(R.id.humidity);
        pressure = (TextView) findViewById(R.id.pressure);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Log.d("enter","masuk1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("enter","masuk2");
                    json = getJSONObjectFromURL(URL);
                    Log.d("enter","masuk3");
                    cityname.setText(cityname.getText().toString() + json.getString("name"));
                    weather.setText(weather.getText().toString() + json.getJSONArray("weather").getJSONObject(0).getString("description"));
                    temperature.setText(temperature.getText().toString() + json.getJSONObject("main").getDouble("temp") + " Kelvin");
                    humidity.setText(humidity.getText().toString() + json.getJSONObject("main").getDouble("humidity"));
                    pressure.setText(pressure.getText().toString() + json.getJSONObject("main").getDouble("pressure"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).run();
    }

    public String WeatherURLBuilder(double latitude, double longitude){
        return "http://api.openweathermap.org/data/2.5/weather?lat="
                + String.valueOf(latitude)
                + "&lon=" + String.valueOf(longitude)
                + "&appid=" + API_KEY;
    }

    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);

        urlConnection.setDoOutput(true);

        urlConnection.connect();

        BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

        char[] buffer = new char[1024];

        String jsonString = new String();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();

        jsonString = sb.toString();

        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }
}
