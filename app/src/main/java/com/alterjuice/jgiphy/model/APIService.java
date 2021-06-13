package com.alterjuice.jgiphy.model;


import com.alterjuice.jgiphy.model.giphy.response.SearchTrendingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    String BASE_URL = "http://api.giphy.com/";
    String SEARCH_URL = "v1/gifs/search";
    String TREND_URL = "v1/gifs/trending";



    @GET(SEARCH_URL)
    Call<SearchTrendingResponse> searchGif(@Query("api_key") String apiKey,
                                           @Query("q") String searchQuery);

    @GET(SEARCH_URL)
    Call<SearchTrendingResponse> searchGif(@Query("api_key") String apiKey,
                                           @Query("q") String searchQuery,
                                           @Query("offset") Integer offset);

    @GET(SEARCH_URL)
    Call<SearchTrendingResponse> searchGif(@Query("api_key") String apiKey,
                                           @Query("q") String searchQuery,
                                           @Query("offset") Integer offset,
                                           @Query("limit") Integer limit);

    @GET(SEARCH_URL)
    Call<SearchTrendingResponse> searchGif(@Query("api_key") String apiKey, @Query("q") String searchQuery,
                                           @Query("offset") Integer offset, @Query("limit") Integer limit,
                                           @Query("rating") String rating, @Query("lang") String lang,
                                           @Query("random_id") String random_id
    );


    @GET(TREND_URL)
    Call<SearchTrendingResponse> getTrends(@Query("api_key") String apiKey);

    @GET(TREND_URL)
    Call<SearchTrendingResponse> getTrends(@Query("api_key") String apiKey,
                                           @Query("offset") Integer offset);

    @GET(TREND_URL)
    Call<SearchTrendingResponse> getTrends(@Query("api_key") String apiKey,
                                           @Query("offset") Integer offset,
                                           @Query("limit") Integer limit);

}
