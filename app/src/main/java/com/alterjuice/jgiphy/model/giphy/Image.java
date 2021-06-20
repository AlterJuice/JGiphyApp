package com.alterjuice.jgiphy.model.giphy;

import com.alterjuice.jgiphy.Consts;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 * The Images Object
 *
 * The Images Object found in the GIF Object contains a series of Rendition Objects.
 * These Rendition Objects includes the URLs and sizes
 * for the many different renditions we offer for each GIF.
 * For more information and best practices on selecting
 * the best image format for your needs, please see our Rendition Guide.
 * (Please note that some GIFs don’t have every property available.
 *
 * https://developers.giphy.com/docs/api/schema/#image-object
 *
 */
public class Image implements ImageView, Serializable {

    // NotUsed to save memory. You can comment out for usage
    // /* The number of frames in this GIF. "15" */
    // @SerializedName(Consts.apiKeyImageFrames) public String frames;

    /* The publicly-accessible direct URL for this GIF for this size of the GIF. "https://media1.giphy.com/media/cZ7rmKfFYOvYI/200.gif" */
    @SerializedName(Consts.apiKeyImageUrl) public String url;

    /* The width of this GIF in pixels. "320" */
    @SerializedName(Consts.apiKeyImageWidth) public String width;

    /* The height of this GIF in pixels. "200" */
    @SerializedName(Consts.apiKeyImageHeight) public String height;

    /* The size of this GIF in bytes. "32381" */
    @SerializedName(Consts.apiKeyImageSize) public String size;

    // NotUsed to save memory. You can comment out for usage
    // /* The URL for this GIF in .MP4 format. "https://media1.giphy.com/media/cZ7rmKfFYOvYI/200.mp4" */
    // @SerializedName(Consts.apiKeyImageMp4) public String mp4Url;

    // NotUsed to save memory. You can comment out for usage
    // /* The size in bytes of the .MP4 file corresponding to this GIF. "25123" */
    // @SerializedName(Consts.apiKeyImageMp4Size) public String mp4Size;

    // NotUsed to save memory. You can comment out for usage
    // /* The URL for this GIF in .webp format. "https://media1.giphy.com/media/cZ7rmKfFYOvYI/200.webp" */
    // @SerializedName(Consts.apiKeyImageWebp) public String webpUrl;

    // NotUsed to save memory. You can comment out for usage
    // /* The size in bytes of the .webp file corresponding to this GIF. "12321" */
    // @SerializedName(Consts.apiKeyImageWebpSize) public String webpSize;


    @Override
    public String getImageRatio() {
        return String.format("%s:%s", width, height);
    }

}
