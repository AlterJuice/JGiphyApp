package com.alterjuice.jgiphy;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alterjuice.jgiphy.adapters.GifAdapter;
import com.alterjuice.jgiphy.adapters.OnBottomReachedListener;
import com.alterjuice.jgiphy.adapters.OnGifClickedListener;
import com.alterjuice.jgiphy.databinding.ActivityMainBinding;
import com.alterjuice.jgiphy.model.APIService;
import com.alterjuice.jgiphy.model.TestConfig;
import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.model.giphy.response.SearchResponse;
import com.alterjuice.jgiphy.model.giphy.response.TrendingResponse;
import com.alterjuice.jgiphy.ui.GifFragment;

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
    public static TrendingResponse trending;

    public GifAdapter prepareAdapter(){
        GifAdapter adapter = new GifAdapter();
        adapter.onBottomReachedListener = new OnBottomReachedListener() {
            @Override
            public void onBottomReached() {

            }
        };
        adapter.onGifClickedListener = new OnGifClickedListener() {
            @Override
            public void onGifClicked(Gif gif, int position) {
                getSupportFragmentManager().beginTransaction()
                        .add(GifFragment.newInstance(gif), "Tag").commit();
            }
        };
        return adapter;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = prepareAdapter();
        binding.recyclerGifs.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerGifs.setAdapter(adapter);

        binding.menu.getMenu().getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (trending.data.size() != 0)
                    Log.d("xx", "YY");
                return true;
            }
        });

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
                trending = response.body();
                adapter.update(trending.data);
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