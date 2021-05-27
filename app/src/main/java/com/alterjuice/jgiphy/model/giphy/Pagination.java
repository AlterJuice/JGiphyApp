package com.alterjuice.jgiphy.model.giphy;

import com.google.gson.annotations.SerializedName;

/**
 *
 * The Pagination Object
 * The Pagination Object contains information relating to the number
 * of total results available as well as the number
 * of results fetched and their relative positions.
 *
 * https://developers.giphy.com/docs/api/schema/#pagination-object
 *
 */
public class Pagination {
    @SerializedName("offset") // Position in pagination. 2591
    public Integer offset;

    @SerializedName("total_count") // Total number of items available (not returned on every endpoint). 250
    public Integer countTotal;

    @SerializedName("count") // Total number of items returned. 25
    public Integer count;

}
