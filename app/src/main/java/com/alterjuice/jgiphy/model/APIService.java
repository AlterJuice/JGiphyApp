package com.alterjuice.jgiphy.model;


import com.alterjuice.jgiphy.model.giphy.response.SearchResponse;
import com.alterjuice.jgiphy.model.giphy.response.TrendingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    String BASE_URL = "http://api.giphy.com/";

    @GET("http://api.giphy.com/v1/gifs/search")
    Call<SearchResponse> searchGif(@Query("api_key") String apiKey, @Query("q") String searchQuery,
                                   @Query("offset") Integer offset, @Query("limit") Integer limit,
                                   @Query("rating") String rating, @Query("lang") String lang,
                                   @Query("random_id") String random_id
    );

    @GET("v1/gifs/search")
    Call<SearchResponse> searchGif(@Query("api_key") String apiKey, @Query("q") String searchQuery);
    @GET("v1/gifs/search")
    Call<SearchResponse> searchGif(@Query("api_key") String apiKey, @Query("q") String searchQuery, @Query("offset") Integer offset);

    @GET("v1/gifs/search")
    Call<SearchResponse> searchGif(@Query("api_key") String apiKey, @Query("q") String searchQuery, @Query("offset") Integer offset, @Query("limit") Integer limit);


    @GET("v1/gifs/trending")
    Call<TrendingResponse> getTrends(@Query("api_key") String apiKey);
    @GET("v1/gifs/trending")
    Call<TrendingResponse> getTrends(@Query("api_key") String apiKey, @Query("offset") Integer offset);
    @GET("v1/gifs/trending")
    Call<TrendingResponse> getTrends(@Query("api_key") String apiKey, @Query("offset") Integer offset, @Query("limit") Integer limit);

}
