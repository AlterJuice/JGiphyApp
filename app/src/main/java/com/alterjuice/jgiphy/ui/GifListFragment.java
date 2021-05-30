package com.alterjuice.jgiphy.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.alterjuice.jgiphy.MainActivity;
import com.alterjuice.jgiphy.R;
import com.alterjuice.jgiphy.adapters.GifAdapter;
import com.alterjuice.jgiphy.adapters.OnBottomReachedListener;
import com.alterjuice.jgiphy.adapters.OnGifClickedListener;
import com.alterjuice.jgiphy.databinding.GifListFragmentBinding;
import com.alterjuice.jgiphy.model.APIService;
import com.alterjuice.jgiphy.model.TestConfig;
import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.model.giphy.response.SearchResponse;
import com.alterjuice.jgiphy.model.giphy.response.TrendingResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;


public class GifListFragment extends Fragment {
    public static final String TAG = "GifListFragment";
    GifListFragmentBinding binding;
    GifAdapter gifAdapter;

    private final OnGifClickedListener onGifClickedListener = (gif, position) -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)){
            ((MainActivity) requireActivity()).showGif(gif);
        }
    };

    private final OnBottomReachedListener onBottomReachedListener = () -> {

    };
    public static TrendingResponse trending;
    APIService apiService;
    public static final String BASE_URL = "http://api.giphy.com/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.gif_list_fragment, container, false);
        gifAdapter = prepareAdapter(getResources().getConfiguration().orientation);
        binding.recyclerGifs.setAdapter(gifAdapter);
        test();
        return binding.getRoot();
    }
    public void test(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService .class);
        apiService.getTrends(TestConfig.apiKey).enqueue(new Callback<TrendingResponse>() {
            @Override
            public void onResponse(Call<TrendingResponse> call, Response<TrendingResponse> response) {
                if (response.body() == null) {
                    Log.d(TAG, "Response Trending is null");
                    return;
                }
                trending = response.body();
                gifAdapter.update(trending.data);
            }

            @Override
            public void onFailure(Call<TrendingResponse> call, Throwable t) { }
        });
        // apiService.searchGif(TestConfig.apiKey, "good morning").enqueue(new Callback<SearchResponse>() {
        //     @Override
        //     public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
        //         search = response.body();
        //     }
        //     @Override
        //     public void onFailure(Call<SearchResponse> call, Throwable t) { }
        // });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public GifAdapter prepareAdapter(int configOrientation){
        boolean orientationIsPortrait = configOrientation == Configuration.ORIENTATION_PORTRAIT;
        return new GifAdapter(orientationIsPortrait, onBottomReachedListener, onGifClickedListener);
    }


    @Override
    public void onDestroyView() {
        binding.unbind();
        binding = null;
        gifAdapter = null;
        super.onDestroyView();
    }
}
