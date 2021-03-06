package com.alterjuice.jgiphy.model.giphy;

import com.alterjuice.jgiphy.Consts;
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
    /* Position in pagination. 2591 */
    @SerializedName(Consts.apiKeyPaginationOffset) public Integer offset;

    /* Total number of items available (not returned on every endpoint). 250 */
    @SerializedName(Consts.apiKeyPaginationTotalCount) public Integer countTotal;

    /* Total number of items returned. 25 */
    @SerializedName(Consts.apiKeyPaginationCount) public Integer count;

    @Override
    public String toString() {
        return "Pagination{" +
                "offset=" + offset +
                ", countTotal=" + countTotal +
                ", count=" + count +
                '}';
    }
}
