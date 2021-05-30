package com.alterjuice.jgiphy;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.MemoryCategory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://api.giphy.com/";
    String TAG = "MainActivity";
    APIService apiService;
    GifAdapter adapter;
    ActivityMainBinding binding;

    public SearchResponse search;
    public static TrendingResponse trending;
    private Parcelable recyclerViewState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        boolean orientationIsPortrait = getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT;
        adapter = prepareAdapter(orientationIsPortrait);
        binding.recyclerGifs.setAdapter(adapter);
    }

    public GifAdapter prepareAdapter(boolean orientationIsPortrait){
        GifAdapter temp = new GifAdapter(orientationIsPortrait);
        temp.onBottomReachedListener = () -> {

        };
        temp.onGifClickedListener = (gif, position) -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_open_exit,
                            R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
                    .replace(binding.gifFragment.getId(), GifFragment.newInstance(gif), "Tag")
                    .addToBackStack("Tag")
                    .commit();
            turnOnOffHomeButton(true);
        };
        return temp;
    }
    public void turnOnOffHomeButton(boolean show){
        ActionBar bar = getSupportActionBar();
        if (bar != null)
            bar.setDisplayHomeAsUpEnabled(show);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            getSupportFragmentManager().popBackStack();
            turnOnOffHomeButton(false);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        recyclerViewState = binding.recyclerGifs.getLayoutManager().onSaveInstanceState();
        outState.putParcelable("statkey", recyclerViewState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        recyclerViewState = savedInstanceState.getParcelable("statekey");
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (recyclerViewState != null){
            Log.d(TAG, "RecyclerState != null");
            binding.recyclerGifs.getLayoutManager().onRestoreInstanceState(recyclerViewState);
            binding.recyclerGifs.requestLayout();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        test();
    }

    @Override
    protected void onDestroy() {
        new Thread(() -> Glide.get(getApplicationContext()).clearDiskCache()).start();
        Glide.get(this).clearMemory();
        super.onDestroy();
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
                trending = response.body();
                adapter.update(trending.data);
            }

            @Override
            public void onFailure(Call<TrendingResponse> call, Throwable t) { }
        });

        apiService.searchGif(TestConfig.apiKey, "good morning").enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                search = response.body();
            }
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) { }
        });
    }



}