package com.alterjuice.jgiphy.viewmodel;

import android.text.TextUtils;
import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.alterjuice.jgiphy.Repo;
import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.model.giphy.Pagination;
import com.alterjuice.jgiphy.model.giphy.response.SearchResponse;
import com.alterjuice.jgiphy.model.giphy.response.TrendingResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GifListViewModel extends ViewModel {
    String TAG = "GifListViewModel";
    private final MutableLiveData<List<Gif>> gifs;
    private final MutableLiveData<String> gifSearchQuery;
    private final MutableLiveData<Integer> gifLastOffset;

    private static final int LIMIT = 50;

    public GifListViewModel() {
        gifSearchQuery = new MutableLiveData<>();

        gifLastOffset = new MutableLiveData<>();
        gifs = new MutableLiveData<>();
    }

    public MutableLiveData<String> getGifSearchQuery() { return gifSearchQuery; }

    public MutableLiveData<List<Gif>> getGifs(){ return gifs; }

    public MutableLiveData<Integer> getGifLastOffset() { return gifLastOffset; }

    public void loadGifs(){
        getGifLastOffset().setValue(0);
        loadMoreGifs();
    }

    public void loadMoreGifs(){
        if (TextUtils.isEmpty(getGifSearchQuery().getValue())) {
            loadMoreWithTrends(getGifLastOffset().getValue(), LIMIT);
        }else{
            loadMoreWithSearch(getGifSearchQuery().getValue(), getGifLastOffset().getValue(), LIMIT);
        }
    }

    // public void loadPreviousGifs(){
    //     int offset = Math.max(getGifLastOffset().getValue()-LIMIT, 0);
    //     int limit =  (getGifLastOffset().getValue() - offset) - LIMIT;
    //     Log.d(TAG, offset + " " + limit);
    //     if (TextUtils.isEmpty(getGifSearchQuery().getValue())) {
    //         loadMoreWithTrends(offset, limit);
    //     }else{
    //         loadMoreWithSearch(offset, limit);
    //     }
    // }

    private void postResponseData(ArrayList<Gif> data, Pagination pagination){
        getGifs().postValue(data);
        getGifLastOffset().postValue(pagination.offset+pagination.count);
        Log.d(TAG, "Pagination: "+pagination.toString());
    }

    private void loadMoreWithSearch(String searchQuery, Integer offset, Integer count){
        Repo.getGifsWithSearch(searchQuery, offset, count, new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.body() != null) {
                    postResponseData(response.body().data, response.body().pagination);
                }
            }
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) { }
        });
    }

    private void loadMoreWithTrends(Integer offset, Integer count){
        Log.d("loadMoreWithTrends", "Why Are u running");
        Repo.getGifsWithTrends(offset, count, new Callback<TrendingResponse>() {
            @Override
            public void onResponse(Call<TrendingResponse> call, Response<TrendingResponse> response) {
                if (response.body() != null) {
                    postResponseData(response.body().data, response.body().pagination);
                }
            }
            @Override
            public void onFailure(Call<TrendingResponse> call, Throwable t) { }
        });
    }

}
