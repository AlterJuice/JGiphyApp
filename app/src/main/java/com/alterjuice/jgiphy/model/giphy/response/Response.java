package com.alterjuice.jgiphy.model.giphy.response;

import com.alterjuice.jgiphy.Consts;
import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.model.giphy.Meta;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName(Consts.apiKeyData) public List<Gif> data;

    @SerializedName(Consts.apiKeyMeta) public Meta meta;

    public Response(){ }

}
