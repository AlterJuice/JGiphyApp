package com.alterjuice.jgiphy.model.giphy.response;

import com.alterjuice.jgiphy.model.giphy.Pagination;
import com.google.gson.annotations.SerializedName;

/**
 * SearchResponse and TrendingResponse have the same data taking from server: Data(Array of Gifs), Meta and Pagination.
 * So that is why Search and Trend responses becomes as one thing.
 */
public class SearchTrendingResponse extends Response {
    @SerializedName("pagination")
    public Pagination pagination;
}
