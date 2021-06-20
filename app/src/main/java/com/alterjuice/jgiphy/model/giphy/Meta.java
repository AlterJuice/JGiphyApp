package com.alterjuice.jgiphy.model.giphy;

import com.alterjuice.jgiphy.Consts;
import com.google.gson.annotations.SerializedName;

/**
 *
 * The Meta Object
 * The Meta Object contains basic information regarding the response and its status.
 * You can learn more about the different types of response codes
 * the API might give you in the Response Codes section
 * (https://developers.giphy.com/branch/master/docs/api/#response-codes).
 *
 * https://developers.giphy.com/docs/api/schema#meta-object
 *
 */
public class Meta {

    /* HTTP Response Message.(required) "OK" */
    @SerializedName(Consts.apiKeyMetaMsg) public String msg;

    /* HTTP Response Code. (required) 200 */
    @SerializedName(Consts.apiKeyMetaStatus) public Integer status;

    /* A unique ID paired with this response from the API. "57eea03c72381f86e05c35d2" */
    @SerializedName(Consts.apiKeyMetaResponseId) public String responseID;
}
