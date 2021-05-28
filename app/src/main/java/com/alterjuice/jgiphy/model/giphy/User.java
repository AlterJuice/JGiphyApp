package com.alterjuice.jgiphy.model.giphy;

import com.google.gson.annotations.SerializedName;

/**
 *
 * The User Object
 * The User Object contains information about the user
 * associated with a GIF and URLs to assets such as
 * that user's avatar image, GIPHY profile, and more.
 *
 * https://developers.giphy.com/docs/api/schema/#user-object
 */
public class User {

    public @SerializedName("avatar_url")
    /* The URL for this user's avatar image. "https://media1.giphy.com/avatars/election2016/XwYrZi5H87o6.gif" */
    String urlAvatar;

    public @SerializedName("banner_url")
    /* The URL for the banner image that appears atop this user's profile page. "https://media4.giphy.com/avatars/cheezburger/XkuejOhoGLE6.jpg" */
    String urlBanner;

    public @SerializedName("profile_url")
    /* The URL for this user's GIPHY profile. "https://giphy.com/cheezburger/" */
    String urlProfile;

    public @SerializedName("username")
    /* The username associated with this user. "joecool4000" */
    String username;

    public @SerializedName("display_name")
    /* The display name associated with this user (contains formatting the base username might not). "JoeCool4000" */
    String displayName;

}
