package com.alterjuice.jgiphy.model.giphy;

/**
 * ImageView interface for giphy.Image class to get properly data
 */
public interface ImageView {
    /**
     * @return Returns the image ratio.
     * Examples: 1:1, 4:3, 16:9
     * Default returns String "width:height"
     */
    String getImageRatio();
}
