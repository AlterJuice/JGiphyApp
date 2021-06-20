package com.alterjuice.jgiphy.model.giphy;

import com.alterjuice.jgiphy.Consts;
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


    /* Data on versions of this GIF with a fixed height of 200 pixels. Good for mobile use. */
    @SerializedName(Consts.apiKeyImagesFixedHeight) public Image fixedHeight;

    // NotUsed to save memory. You can comment out for usage
    // /* Data on a static image of this GIF with a fixed height of 200 pixels. */
    // @SerializedName(Consts.apiKeyImagesFixedHeightStill) public Image fixedHeightStill;

    // NotUsed to save memory. You can comment out for usage
    // /* Data on versions of this GIF with a fixed height of 200 pixels and the number of frames reduced to 6. */
    // @SerializedName(Consts.apiKeyImagesFixedHeightDownsampled) public Image fixedHeightDownsampled;


    /* Data on versions of this GIF with a fixed width of 200 pixels. Good for mobile use. */
    @SerializedName(Consts.apiKeyImagesFixedWidth) public Image fixedWidth;

    // NotUsed to save memory. You can comment out for usage
    // /* Data on a static image of this GIF with a fixed width of 200 pixels. */
    // @SerializedName(Consts.apiKeyImagesFixedWidthStill) public Image fixedWidthStill;

    // NotUsed to save memory. You can comment out for usage
    // /* Data on versions of this GIF with a fixed width of 200 pixels and the number of frames reduced to 6. */
    // @SerializedName(Consts.apiKeyImagesFixedWidthDownsampled) public Image fixedWidthDownsampled;

    // NotUsed to save memory. You can comment out for usage
    // /* Data on versions of this GIF with a fixed height of 100 pixels. Good for mobile keyboards. */
    // @SerializedName(Consts.apiKeyImagesFixedHeightSmall) public Image fixedHeightSmall;

    // NotUsed to save memory. You can comment out for usage
    // /* Data on a static image of this GIF with a fixed height of 100 pixels. */
    // @SerializedName(Consts.apiKeyImagesFixedHeightSmallStill) public Image fixedHeightSmallStill;

    // NotUsed to save memory. You can comment out for usage
    // /* Data on versions of this GIF with a fixed width of 100 pixels. Good for mobile keyboards. */
    // @SerializedName(Consts.apiKeyImagesFixedWidthSmall) public Image fixedWidthSmall;

    // NotUsed to save memory. You can comment out for usage
    // /* Data on a static image of this GIF with a fixed width of 100 pixels. */
    // @SerializedName(Consts.apiKeyImagesFixedWidthSmallStill) public Image fixedWidthSmallStill;

    // NotUsed to save memory. You can comment out for usage
    // /* Data on a version of this GIF downsized to be under 2mb. */
    // @SerializedName(Consts.apiKeyImagesDownsized) public Image downsized;

    // NotUsed to save memory. You can comment out for usage
    // /* Data on a static preview image of the downsized version of this GIF. */
    // @SerializedName(Consts.apiKeyImagesDownsizedStill) public Image downsizedStill;

    // NotUsed to save memory. You can comment out for usage
    // /* Data on a version of this GIF downsized to be under 8mb. */
    // @SerializedName(Consts.apiKeyImagesDownsizedLarge) public Image downsizedLarge;

    // NotUsed to save memory. You can comment out for usage
    // /* Data on a version of this GIF downsized to be under 5mb. */
    // @SerializedName(Consts.apiKeyImagesDownsizedMedium) public Image downsizedMedium;

    // NotUsed to save memory. You can comment out for usage
    // /* Data on a version of this GIF downsized to be under 200kb. */
    // @SerializedName(Consts.apiKeyImagesDownsizedSmall) public Image downsizedSmall;


    /* Data on the original version of this GIF. Good for desktop use. */
    @SerializedName(Consts.apiKeyImagesOriginal) public Image original;


    /* Data on a static preview image of the original GIF. */
    @SerializedName(Consts.apiKeyImagesOriginalStill) public Image originalStill;

    // NotUsed to save memory. You can comment out for usage
    // /* Data on the 15 second version of the GIF looping. */
    // @SerializedName(Consts.apiKeyImagesLooping) public Image looping;


    /* Data on a version of this GIF in .MP4 format limited to 50kb that displays the first 1-2 seconds of the GIF. */
    @SerializedName(Consts.apiKeyImagesPreview) public Image preview;


    /* Data on a version of this GIF limited to 50kb that displays the first 1-2 seconds of the GIF. */
    @SerializedName(Consts.apiKeyImagesPreviewGif) public Image previewGif;


}
