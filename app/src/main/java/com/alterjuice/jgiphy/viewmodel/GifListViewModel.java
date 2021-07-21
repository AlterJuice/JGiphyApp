package com.alterjuice.jgiphy.viewmodel;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alterjuice.jgiphy.Consts;
import com.alterjuice.jgiphy.Repo;
import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.model.giphy.response.SearchTrendingResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GifListViewModel extends ViewModel {

    private final MutableLiveData<List<Gif>> mutableLiveGifs = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<String> mutableGifSearchQuery = new MutableLiveData<>();
    public final LiveData<List<Gif>> gifs = mutableLiveGifs;
    public final LiveData<String> gifSearchQuery = mutableGifSearchQuery;


    public void setSearchQuery(String query){
        mutableGifSearchQuery.setValue(query);
    }

    public void loadMoreGifs() {
        int offset = 0;
        List<Gif> liveGifValue = mutableLiveGifs.getValue();
        if (liveGifValue != null)
            offset = liveGifValue.size();
        String gifSearchQueryValue = mutableGifSearchQuery.getValue();
        if (TextUtils.isEmpty(gifSearchQueryValue)) {
            loadMoreWithTrends(offset, Consts.countGifsPerRequestLimit);
        } else {
            loadMoreWithSearch(gifSearchQueryValue, offset, Consts.countGifsPerRequestLimit);
        }
    }

    public void loadWithClear() {
        mutableLiveGifs.setValue(new ArrayList<>());
        loadMoreGifs();
    }

    private final Callback<SearchTrendingResponse> searchTrendingResponseCallback = new Callback<SearchTrendingResponse>() {
        @Override
        public void onResponse(Call<SearchTrendingResponse> call, Response<SearchTrendingResponse> response) {
            if (response.body() != null) {
                List<Gif> x = mutableLiveGifs.getValue();
                Collection<Gif> responseList = response.body().data;
                if (x!=null)
                    x.addAll(responseList);
                else
                    x = new ArrayList<>(responseList);
                mutableLiveGifs.setValue(x);
            }
        }

        @Override
        public void onFailure(Call<SearchTrendingResponse> call, Throwable t) {
        }
    };


    private void loadMoreWithSearch(String searchQuery, Integer offset, Integer count) {
        Repo.getGifsWithSearch(searchQuery, offset, count, searchTrendingResponseCallback);
    }

    private void loadMoreWithTrends(Integer offset, Integer count) {
        Repo.getGifsWithTrends(offset, count, searchTrendingResponseCallback);
    }

}
