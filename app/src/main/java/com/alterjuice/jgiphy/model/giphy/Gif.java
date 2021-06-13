package com.alterjuice.jgiphy.model.giphy;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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
public class Gif implements GifView, Serializable {
    // GSON naming: https://github.com/google/gson/blob/master/UserGuide.md#json-field-naming-support

    // NotUsed to save memory. You can comment out for usage
    // /* By default, this is almost always GIF. "gif" */
    // @SerializedName("type") public String type;

    /* The title that appears on giphy.com for this GIF. "Happy Dancing GIF" */
    @SerializedName("title") public String title;

    /* This GIF's unique ID "YsTs5ltWtEhnq" */
    @SerializedName("id") public String uniqueID;

    // NotUsed to save memory. You can comment out for usage
    // /* The unique slug used in this GIF's URL "confused-flying-YsTs5ltWtEhnq" */
    // @SerializedName("slug") public String slug;

    /* The unique URL for this GIF "http://giphy.com/gifs/confused-flying-YsTs5ltWtEhnq" */
    @SerializedName("url") public String url;

    // NotUsed to save memory. You can comment out for usage
    // /* The unique bit.ly URL for this GIF "http://gph.is/1gsWDcL" */
    // @SerializedName("bitly_url") public String urlBitly;

    // NotUsed to save memory. You can comment out for usage
    // /* A URL used for embedding this GIF "http://giphy.com/embed/YsTs5ltWtEhnq" */
    // @SerializedName("embed_url") public String urlEmbed;

    /* The username this GIF is attached to, if applicable "JoeCool4000" */
    @SerializedName("username") public String username;

    // NotUsed to save memory. You can comment out for usage
    // /* The page on which this GIF was found "http://www.reddit.com/r/reactiongifs/comments/1xpyaa/superman_goes_to_hollywood/" */
    // @SerializedName("source") public String source;

    // NotUsed to save memory. You can comment out for usage@SerializedName("rating")
    // /* The MPAA-style rating for this content. Examples include Y, G, PG, PG-13 and R.	"g"
    // * https://developers.giphy.com/docs/optional-settings/#rating */
    // public String rating;

    // Currently unused by Giphy
    // @SerializedName("content_url") public String urlContent;

    /* An object containing data about the user associated with this GIF, if applicable. */
    @SerializedName("user") public User user;

    // NotUsed to save memory. You can comment out for usage
    // /* The top level domain of the source URL. "cheezburger.com" */
    // @SerializedName("source_tld") public String sourceTLD;

    // NotUsed to save memory. You can comment out for usage
    // /* The URL of the webpage on which this GIF was found. "http://cheezburger.com/5282328320" */
    // @SerializedName("source_post_url") public String sourcePostUrl;

    // NotUsed to save memory. You can comment out for usage
    // /* The date on which this GIF was last updated. "2013-08-01 12:41:48" */
    // @SerializedName("update_datetime") public String datetimeUpdate;

    // NotUsed to save memory. You can comment out for usage
    // /* The date this GIF was added to the GIPHY database. "2013-08-01 12:41:48" */
    // @SerializedName("create_datetime") public String datetimeCreate;

    // NotUsed to save memory. You can comment out for usage
    // /* The creation or upload date from this GIF's source. "2013-08-01 12:41:48" */
    // @SerializedName("import_datetime") public String datetimeImport;

    // NotUsed to save memory. You can comment out for usage
    // /* The date on which this gif was marked trending, if applicable. "2013-08-01 12:41:48" */
    // @SerializedName("trending_datetime") public String datetimeTrending;

    /* An object containing data for various available formats and sizes of this GIF. */
    @SerializedName("images") public Images images;

    @Override
    public boolean hasUser() {
        return user != null;
    }

    @Override
    public Image getImageForPortrait() {
        return images.fixedWidth;
    }

    @Override
    public Image getImageForLandscape() {
        return images.fixedHeight;
    }

    @Override
    public Image getStillImageForPreview() {
        return images.originalStill;
    }

    @Override
    public Image getGifImageForPreview() {
        return images.previewGif;
    }

    @Override
    public Image getImageForOriginal() {
        return images.original;
    }

}
