package com.alterjuice.jgiphy.viewmodel;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alterjuice.jgiphy.Repo;
import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.model.giphy.Pagination;
import com.alterjuice.jgiphy.model.giphy.response.SearchResponse;
import com.alterjuice.jgiphy.model.giphy.response.TrendingResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GifListViewModel extends ViewModel {
    String TAG = "GifListViewModel";
    private final MutableLiveData<List<Gif>> gifs;
    private final MutableLiveData<String> gifSearchQuery;
    private final MutableLiveData<Integer> gifOffset;

    private static final int LIMIT = 25;


    public GifListViewModel() {
        gifSearchQuery = new MutableLiveData<>("");
        gifOffset = new MutableLiveData<>(0);
        gifs = new MutableLiveData<>();
    }

    public MutableLiveData<String> getGifSearchQuery() { return gifSearchQuery; }

    public MutableLiveData<List<Gif>> getGifs(){ return gifs; }

    public MutableLiveData<Integer> getGifOffset() { return gifOffset; }


    public void loadGifs(){
        gifOffset.setValue(0);
        loadMoreGifs();
    }

    public void loadMoreGifs(){
        if (TextUtils.isEmpty(getGifSearchQuery().getValue())) {
            loadMoreWithTrends();
        }else{
            loadMoreWithSearch();
        }
    }

    private void postResponseData(ArrayList<Gif> data, Pagination pagination){
        int offsetWas = gifOffset.getValue();
        int offsetNext = pagination.offset+pagination.count;
        gifOffset.postValue(offsetNext);
        gifs.postValue(data);
        Log.d(TAG, "gifOffset was: "+offsetWas);
        Log.d(TAG, "Pagination: "+pagination.toString());
        Log.d(TAG, "gifOffset for next: "+offsetNext);
    }

    private void loadMoreWithSearch(){
        Repo.getGifsWithSearch(gifSearchQuery.getValue(), gifOffset.getValue(), LIMIT, new Callback<SearchResponse>() {
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

    private void loadMoreWithTrends(){
        Log.d("loadMoreWithTrends", "Why Are u running");
        Repo.getGifsWithTrends(gifOffset.getValue(), LIMIT, new Callback<TrendingResponse>() {
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
