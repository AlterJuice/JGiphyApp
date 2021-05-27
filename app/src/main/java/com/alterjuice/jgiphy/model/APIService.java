package com.alterjuice.jgiphy.model;

import com.alterjuice.jgiphy.model.giphy.response.Search;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @POST("search")
    Call<Search> search(@Query("s") String searchQuery);

}
