package com.alterjuice.jgiphy.model.giphy.response;

import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.model.giphy.Meta;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Response {
    @SerializedName("data")
    ArrayList<Gif> data;

    @SerializedName("meta")
    Meta meta;

}
