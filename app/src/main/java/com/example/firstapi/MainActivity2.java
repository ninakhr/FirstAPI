package com.example.firstapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EditText city2 = findViewById(R.id.city2);
        city2.setText(getIntent().getExtras().get("Key").toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")//базовая часть адреса
.addConverterFactory(GsonConverterFactory.create())//конвертер
                .build();
        WeatherOneDayAPI weatherOneDayApi;
        weatherOneDayApi=retrofit.create(WeatherOneDayAPI.class);
        weatherOneDayApi.getWeatherByCityName(city2.getText().toString(),"3d822b9dce4e57f12b9b3400d480a358").
        enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()) {
                    Log.i("Jane", response.body().getWind().getSpeed().toString());

                    Log.i("Jane", "OK");

                    EditText wind = findViewById(R.id.wind);
                    wind.setText( response.body().getWind().getSpeed().toString());
                    EditText temp = findViewById(R.id.temp);
                    temp.setText(response.body().getMain().getTemp().toString());

                } else Log.i("Jane", "no reponse");
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.i("Jane","Failure "+t);
            }
        });
    }


}