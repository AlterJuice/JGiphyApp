package com.alterjuice.jgiphy.model.giphy;

import com.alterjuice.jgiphy.Consts;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 * The User Object
 * The User Object contains information about the user
 * associated with a GIF and URLs to assets such as
 * that user's avatar image, GIPHY profile, and more.
 *
 * https://developers.giphy.com/docs/api/schema/#user-object
 */
public class User implements Serializable {

    // NotUsed to save memory. You can comment out for usage
    // /* The URL for this user's avatar image. "https://media1.giphy.com/avatars/election2016/XwYrZi5H87o6.gif" */
    // public @SerializedName(Consts.apiKeyUserAvatarUrl) String urlAvatar;

    // NotUsed to save memory. You can comment out for usage
    // /* The URL for the banner image that appears atop this user's profile page. "https://media4.giphy.com/avatars/cheezburger/XkuejOhoGLE6.jpg" */
    // public @SerializedName(Consts.apiKeyUserBannerUrl) String urlBanner;

    /* The URL for this user's GIPHY profile. "https://giphy.com/cheezburger/" */
    public @SerializedName(Consts.apiKeyUserProfileUrl) String urlProfile;

    // NotUsed to save memory. You can comment out for usage
    // /* The username associated with this user. "joecool4000" */
    // public @SerializedName(Consts.apiKeyUserUsername) String username;

    // NotUsed to save memory. You can comment out for usage
    // /* The display name associated with this user (contains formatting the base username might not). "JoeCool4000" */
    // public @SerializedName(Consts.apiKeyUserDisplayName) String displayName;

}
