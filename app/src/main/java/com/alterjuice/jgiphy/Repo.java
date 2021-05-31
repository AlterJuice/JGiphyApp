package com.alterjuice.jgiphy;

import com.alterjuice.jgiphy.model.APIService;
import com.alterjuice.jgiphy.model.giphy.response.SearchResponse;
import com.alterjuice.jgiphy.model.giphy.response.TrendingResponse;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repo {

    private static Repo repoInstance;
    private final APIService apiService;
    private static final String giphyApiKey = "";
    /* TODO: Set ApiKey from https://developers.giphy.com/dashboard/?create=true */

    private Repo(){
        Retrofit retrofitInstance = new Retrofit.Builder().baseUrl(APIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        apiService = retrofitInstance.create(APIService.class);
    }

    public static Repo getInstance(){
        if (repoInstance == null){
            repoInstance = new Repo();
        }
        return repoInstance;
    }

    public static APIService getApiService(){
        return getInstance().apiService;
    }


    public static void getGifsWithSearch(String query, Integer offset, Integer limit, Callback<SearchResponse> callback){
        getApiService().searchGif(giphyApiKey, query, offset, limit).enqueue(callback);
    }

    public static void getGifsWithTrends(Integer offset, Integer limit, Callback<TrendingResponse> callback){
        getApiService().getTrends(giphyApiKey, offset, limit).enqueue(callback);
    }

}
