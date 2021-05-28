package com.alterjuice.jgiphy;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.alterjuice.jgiphy.adapters.GifAdapter;
import com.alterjuice.jgiphy.databinding.ActivityMainBinding;
import com.alterjuice.jgiphy.model.APIService;
import com.alterjuice.jgiphy.model.TestConfig;
import com.alterjuice.jgiphy.model.giphy.response.SearchResponse;
import com.alterjuice.jgiphy.model.giphy.response.TrendingResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://api.giphy.com/";

    APIService apiService;
    GifAdapter adapter;
    ActivityMainBinding binding;

    public SearchResponse search;
    public TrendingResponse trending;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new GifAdapter();
        binding.recyclerGifs.setAdapter(adapter);
        // binding.menu.getMenu().getItem(1).setOnMenuItemClickListener()

    }

    @Override
    protected void onStart() {
        super.onStart();
        test();
    }

    public void test(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        apiService = retrofit.create(APIService.class);
        apiService.getTrends(TestConfig.apiKey).enqueue(new Callback<TrendingResponse>() {
            @Override
            public void onResponse(Call<TrendingResponse> call, Response<TrendingResponse> response) {
                if (response.body() == null) {
                    Log.d("MainActivity", "Response Trending is null");
                    return;
                }
                Log.d("RESPONSE Trending", response.toString());
                adapter.setItems(response.body().data);
                trending = response.body();
            }

            @Override
            public void onFailure(Call<TrendingResponse> call, Throwable t) {
                Log.d("FAILURE Trending", t.getMessage());
            }
        });

        apiService.searchGif(TestConfig.apiKey, "good morning").enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                Log.d("RESPONSE", response.toString());
                search = response.body();
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d("FAILURE", t.getMessage() + "");

            }
        });
    }



}