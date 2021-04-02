package com.example.firstapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText city = findViewById(R.id.editCity);
        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView=findViewById(R.id.imageView);
                new
                        ImageDownloader(imageView).execute("https://i.ebayimg.com/images/g/fkwAAMXQya1Q7h1o/s-l400.jpg ");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("Key", city.getText().toString());
                startActivity(intent);
            }
        });
        Log.i("main", "create");

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        MessageAPI messageAPI=retrofit.create(MessageAPI.class);
        Call<String> message=messageAPI.message();
        message.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Nina",""+response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("Nina","Failure"+t);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.wtf("main2", "start");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.wtf("main2", "restart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.wtf("main2", "resume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.wtf("main2", "restart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.wtf("main2", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.wtf("main2", "destroy");

    }

    public interface MessageAPI{
        @GET("https://api.openweathermap.org/data/2.5/weather?q=London&appid=5dafc77d0c777a4c154b939829654b18")
        Call<String> message();
    }



}