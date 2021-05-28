package com.alterjuice.jgiphy.model;


import com.alterjuice.jgiphy.model.giphy.response.SearchResponse;
import com.alterjuice.jgiphy.model.giphy.response.TrendingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("http://api.giphy.com/v1/gifs/search")
    Call<SearchResponse> searchGif(@Query("api_key") String apiKey, @Query("q") String searchQuery,
                                   @Query("limit") Integer limit, @Query("offset") Integer offset,
                                   @Query("rating") String rating, @Query("lang") String lang,
                                   @Query("random_id") String random_id
    );

    @GET("http://api.giphy.com/v1/gifs/search")
    Call<SearchResponse> searchGif(@Query("api_key") String apiKey, @Query("q") String searchQuery);


    @GET("http://api.giphy.com/v1/gifs/trending")
    Call<TrendingResponse> getTrends(@Query("api_key") String apiKey);

}
