package com.alterjuice.jgiphy.model.giphy.response;

import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.model.giphy.Meta;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Response {

    @SerializedName("data") public List<Gif> data;

    @SerializedName("meta") public Meta meta;

    public Response(){ }

}
