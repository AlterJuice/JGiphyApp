package com.alterjuice.jgiphy.viewmodel;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alterjuice.jgiphy.Repo;
import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.model.giphy.response.SearchTrendingResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GifListViewModel extends ViewModel {
    String TAG = "GifListViewModel";
    private static final int LIMIT = 50;

    private final MutableLiveData<List<Gif>> liveGifs = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<String> gifSearchQuery = new MutableLiveData<>();
    public LiveData<List<Gif>> gifs = liveGifs;

    public GifListViewModel() {
        loadMoreGifs();
    }

    public MutableLiveData<String> getGifSearchQuery() {
        return gifSearchQuery;
    }


    public void loadMoreGifs() {
        int offset = liveGifs.getValue().size();
        if (TextUtils.isEmpty(getGifSearchQuery().getValue())) {
            loadMoreWithTrends(offset, LIMIT);
        } else {
            loadMoreWithSearch(getGifSearchQuery().getValue(), offset, LIMIT);
        }
    }
    public void loadWithClear(){
        liveGifs.setValue(Collections.emptyList());
        loadMoreGifs();
    }

    private final Callback<SearchTrendingResponse> searchTrendingResponseCallback = new Callback<SearchTrendingResponse>() {
        @Override
        public void onResponse(Call<SearchTrendingResponse> call, Response<SearchTrendingResponse> response) {
            if (response.body() != null) {
                List<Gif> x = liveGifs.getValue();
                if (x == null)
                    x = Collections.emptyList();
                x.addAll(response.body().data);
                liveGifs.setValue(x);
            }
        }
        @Override
        public void onFailure(Call<SearchTrendingResponse> call, Throwable t) { }
    };


    private void loadMoreWithSearch(String searchQuery, Integer offset, Integer count) {
        Repo.getGifsWithSearch(searchQuery, offset, count, searchTrendingResponseCallback);
    }

    private void loadMoreWithTrends(Integer offset, Integer count) {
        Repo.getGifsWithTrends(offset, count, searchTrendingResponseCallback);
    }

}
