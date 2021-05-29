package com.alterjuice.jgiphy.model.giphy;

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
public class Image implements ImageModel, Serializable {

    @SerializedName("frames")
    /* The number of frames in this GIF. "15" */
    public String frames;

    @SerializedName("url")
    /* The publicly-accessible direct URL for this GIF for this size of the GIF. "https://media1.giphy.com/media/cZ7rmKfFYOvYI/200.gif" */
    public String url;

    @SerializedName("width")
    /* The width of this GIF in pixels. "320" */
    public String width;

    @SerializedName("height")
    /* The height of this GIF in pixels. "200" */
    public String height;

    @SerializedName("size")
    /* The size of this GIF in bytes. "32381" */
    public String size;

    @SerializedName("mp4")
    /* The URL for this GIF in .MP4 format. "https://media1.giphy.com/media/cZ7rmKfFYOvYI/200.mp4" */
    public String mp4Url;

    @SerializedName("mp4_size")
    /* The size in bytes of the .MP4 file corresponding to this GIF. "25123" */
    public String mp4Size;

    @SerializedName("webp")
    /* The URL for this GIF in .webp format. "https://media1.giphy.com/media/cZ7rmKfFYOvYI/200.webp" */
    public String webpUrl;

    @SerializedName("webp_size")
    /* The size in bytes of the .webp file corresponding to this GIF. "12321" */
    public String webpSize;


    @Override
    public String getImageRatio() {
        return String.format("%s:%s", width, height);
    }
}
