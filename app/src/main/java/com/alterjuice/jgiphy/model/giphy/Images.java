package com.alterjuice.jgiphy.model.giphy;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Images implements Serializable {

    /*
     https://developers.giphy.com/docs/api/schema#image-object

     fixed_height:             Data on versions of this GIF with a fixed height of 200 pixels. Good for mobile use.
     fixed_height_still:       Data on a static image of this GIF with a fixed height of 200 pixels.
     fixed_height_downsampled: Data on versions of this GIF with a fixed height of 200 pixels and the number of frames reduced to 6.
     fixed_width:              Data on versions of this GIF with a fixed width of 200 pixels. Good for mobile use.
     fixed_width_still:        Data on a static image of this GIF with a fixed width of 200 pixels.
     fixed_width_downsampled:  Data on versions of this GIF with a fixed width of 200 pixels and the number of frames reduced to 6.
     fixed_height_small:       Data on versions of this GIF with a fixed height of 100 pixels. Good for mobile keyboards.
     fixed_height_small_still: Data on a static image of this GIF with a fixed height of 100 pixels.
     fixed_width_small:        Data on versions of this GIF with a fixed width of 100 pixels. Good for mobile keyboards.
     fixed_width_small_still:  Data on a static image of this GIF with a fixed width of 100 pixels.
     downsized:                Data on a version of this GIF downsized to be under 2mb.
     downsized_still:          Data on a static preview image of the downsized version of this GIF.
     downsized_large:          Data on a version of this GIF downsized to be under 8mb.
     downsized_medium:         Data on a version of this GIF downsized to be under 5mb.
     downsized_small:          Data on a version of this GIF downsized to be under 200kb.
     original:                 Data on the original version of this GIF. Good for desktop use.
     original_still:           Data on a static preview image of the original GIF.
     looping:                  Data on the 15 second version of the GIF looping.
     preview:                  Data on a version of this GIF in .MP4 format limited to 50kb that displays the first 1-2 seconds of the GIF.
     preview_gif:              Data on a version of this GIF limited to 50kb that displays the first 1-2 seconds of the GIF.
    */

    @SerializedName("fixed_height")
    /* Data on versions of this GIF with a fixed height of 200 pixels. Good for mobile use. */
    public Image fixedHeight;

    @SerializedName("fixed_height_still")
    /* Data on a static image of this GIF with a fixed height of 200 pixels. */
    public Image fixedHeightStill;

    @SerializedName("fixed_height_downsampled")
    /* Data on versions of this GIF with a fixed height of 200 pixels and the number of frames reduced to 6. */
    public Image fixedHeightDownsampled;

    @SerializedName("fixed_width")
    /* Data on versions of this GIF with a fixed width of 200 pixels. Good for mobile use. */
    public Image fixedWidth;

    @SerializedName("fixed_width_still")
    /* Data on a static image of this GIF with a fixed width of 200 pixels. */
    public Image fixedWidthStill;

    @SerializedName("fixed_width_downsampled")
    /* Data on versions of this GIF with a fixed width of 200 pixels and the number of frames reduced to 6. */
    public Image fixedWidthDownsampled;

    @SerializedName("fixed_height_small")
    /* Data on versions of this GIF with a fixed height of 100 pixels. Good for mobile keyboards. */
    public Image fixedHeightSmall;

    @SerializedName("fixed_height_small_still")
    /* Data on a static image of this GIF with a fixed height of 100 pixels. */
    public Image fixedHeightSmallStill;

    @SerializedName("fixed_width_small")
    /* Data on versions of this GIF with a fixed width of 100 pixels. Good for mobile keyboards. */
    public Image fixedWidthSmall;

    @SerializedName("fixed_width_small_still")
    /* Data on a static image of this GIF with a fixed width of 100 pixels. */
    public Image fixedWidthSmallStill;

    @SerializedName("downsized")
    /* Data on a version of this GIF downsized to be under 2mb. */
    public Image downsized;

    @SerializedName("downsized_still")
    /* Data on a static preview image of the downsized version of this GIF. */
    public Image downsizedStill;

    @SerializedName("downsized_large")
    /* Data on a version of this GIF downsized to be under 8mb. */
    public Image downsizedLarge;

    @SerializedName("downsized_medium")
    /* Data on a version of this GIF downsized to be under 5mb. */
    public Image downsizedMedium;

    @SerializedName("downsized_small")
    /* Data on a version of this GIF downsized to be under 200kb. */
    public Image downsizedSmall;

    @SerializedName("original")
    /* Data on the original version of this GIF. Good for desktop use. */
    public Image original;

    @SerializedName("original_still")
    /* Data on a static preview image of the original GIF. */
    public Image originalStill;

    @SerializedName("looping")
    /* Data on the 15 second version of the GIF looping. */
    public Image looping;

    @SerializedName("preview")
    /* Data on a version of this GIF in .MP4 format limited to 50kb that displays the first 1-2 seconds of the GIF. */
    public Image preview;

    @SerializedName("preview_gif")
    /* Data on a version of this GIF limited to 50kb that displays the first 1-2 seconds of the GIF. */
    public Image previewGif;


}
