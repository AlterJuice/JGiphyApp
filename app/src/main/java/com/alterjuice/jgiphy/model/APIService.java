package com.alterjuice.jgiphy.model;


import com.alterjuice.jgiphy.Consts;
import com.alterjuice.jgiphy.model.giphy.response.SearchTrendingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET(Consts.SEARCH_URL)
    Call<SearchTrendingResponse> searchGif(@Query(Consts.keyParamApiKey) String apiKey,
                                           @Query(Consts.keyParamSearchQuery) String searchQuery);

    @GET(Consts.SEARCH_URL)
    Call<SearchTrendingResponse> searchGif(@Query(Consts.keyParamApiKey) String apiKey,
                                           @Query(Consts.keyParamSearchQuery) String searchQuery,
                                           @Query(Consts.keyParamOffset) Integer offset);

    @GET(Consts.SEARCH_URL)
    Call<SearchTrendingResponse> searchGif(@Query(Consts.keyParamApiKey) String apiKey,
                                           @Query(Consts.keyParamSearchQuery) String searchQuery,
                                           @Query(Consts.keyParamOffset) Integer offset,
                                           @Query(Consts.keyParamLimit) Integer limit);

    @GET(Consts.SEARCH_URL)
    Call<SearchTrendingResponse> searchGif(@Query(Consts.keyParamApiKey) String apiKey, @Query(Consts.keyParamSearchQuery) String searchQuery,
                                           @Query(Consts.keyParamOffset) Integer offset, @Query(Consts.keyParamLimit) Integer limit,
                                           @Query(Consts.keyParamRating) String rating, @Query(Consts.keyParamLanguage) String lang,
                                           @Query(Consts.keyParamRandomId) String random_id
    );


    @GET(Consts.TREND_URL)
    Call<SearchTrendingResponse> getTrends(@Query(Consts.keyParamApiKey) String apiKey);

    @GET(Consts.TREND_URL)
    Call<SearchTrendingResponse> getTrends(@Query(Consts.keyParamApiKey) String apiKey,
                                           @Query(Consts.keyParamOffset) Integer offset);

    @GET(Consts.TREND_URL)
    Call<SearchTrendingResponse> getTrends(@Query(Consts.keyParamApiKey) String apiKey,
                                           @Query(Consts.keyParamOffset) Integer offset,
                                           @Query(Consts.keyParamLimit) Integer limit);

}
