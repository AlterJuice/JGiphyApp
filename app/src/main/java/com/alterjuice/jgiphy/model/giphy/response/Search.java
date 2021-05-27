package com.alterjuice.jgiphy.model.giphy.response;

import com.alterjuice.jgiphy.model.giphy.Pagination;
import com.google.gson.annotations.SerializedName;

public class Search extends Response{

    @SerializedName("pagination")
    public Pagination pagination;

}
