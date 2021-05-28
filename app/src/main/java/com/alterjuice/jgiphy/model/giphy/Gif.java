package com.alterjuice.jgiphy.model.giphy;

import com.google.gson.annotations.SerializedName;

/**
 * The model of Gif object from Giphy Schema Definitions:
 *
 * The GIF Object
 * GIF Objects are returned from most of GIPHY API's Endpoints.
 * These objects contain a variety of information,
 * such as the Image Object, which itself includes
 * the URLS for multiple different GIFS formats and sizes.
 *
 * https://developers.giphy.com/docs/api/schema#gif-object
 */
public class Gif {
    // GSON naming: https://github.com/google/gson/blob/master/UserGuide.md#json-field-naming-support

    @SerializedName("type")
    /* By default, this is almost always GIF. "gif" */
    public String type;

    @SerializedName("title")
    /* The title that appears on giphy.com for this GIF. "Happy Dancing GIF" */
    public String title;

    @SerializedName("id")
    /* This GIF's unique ID "YsTs5ltWtEhnq" */
    public String uniqueID;

    @SerializedName("slug")
    /* The unique slug used in this GIF's URL "confused-flying-YsTs5ltWtEhnq" */
    public String slug;

    @SerializedName("url")
    /* The unique URL for this GIF "http://giphy.com/gifs/confused-flying-YsTs5ltWtEhnq" */
    public String url;

    @SerializedName("bitly_url")
    /* The unique bit.ly URL for this GIF "http://gph.is/1gsWDcL" */
    public String urlBitly;

    @SerializedName("embed_url")
    /* A URL used for embedding this GIF "http://giphy.com/embed/YsTs5ltWtEhnq" */
    public String urlEmbed;

    @SerializedName("username")
    /* The username this GIF is attached to, if applicable "JoeCool4000" */
    public String username;

    @SerializedName("source")
    /* The page on which this GIF was found "http://www.reddit.com/r/reactiongifs/comments/1xpyaa/superman_goes_to_hollywood/" */
    public String source;

    @SerializedName("rating")
    /* The MPAA-style rating for this content. Examples include Y, G, PG, PG-13 and R.	"g"
    * https://developers.giphy.com/docs/optional-settings/#rating
    */
    public String rating;

    // @SerializedName("content_url") // Currently unused
    // public String urlContent;

    @SerializedName("user")
    /* An object containing data about the user associated with this GIF, if applicable. */
    public User user;

    @SerializedName("source_tld")
    /* The top level domain of the source URL. "cheezburger.com" */
    public String sourceTLD;

    @SerializedName("source_post_url")
    /* The URL of the webpage on which this GIF was found. "http://cheezburger.com/5282328320" */
    public String sourcePostUrl;

    @SerializedName("update_datetime")
    /* The date on which this GIF was last updated. "2013-08-01 12:41:48" */
    public String datetimeUpdate;

    @SerializedName("create_datetime")
    /* The date this GIF was added to the GIPHY database. "2013-08-01 12:41:48" */
    public String datetimeCreate;

    @SerializedName("import_datetime")
    /* The creation or upload date from this GIF's source. "2013-08-01 12:41:48" */
    public String datetimeImport;

    @SerializedName("trending_datetime")
    /* The date on which this gif was marked trending, if applicable. "2013-08-01 12:41:48" */
    public String datetimeTrending;

    @SerializedName("images")
    /* An object containing data for various available formats and sizes of this GIF. */
    public Images images;
}
