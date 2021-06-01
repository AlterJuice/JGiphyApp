package com.alterjuice.jgiphy.model.giphy;

/**
 * GifView is interface for giphy.Gif classes to get properly data
 *
 */
public interface GifView {
    /**
     * @return Returns boolean true if gif. user != null.
     * It helps to check user and get his profileUrl and other data
     */
    boolean hasUser();

    /**
     * @return Returns the fixedHeight image for portrait use;
     * Default is gif.images.fixedWidth;
     */
    Image getImageForPortrait();

    /**
     * @return Returns the fixedHeight image for landscape use;
     * Default is gif.images.fixedHeight
     */
    Image getImageForLandscape();

    /**
     * @return Better to return the thumbnail(originalStill) Image or 2sec preview;
     * Default is gif.images.originalStill
     */
    Image getImageForPreview();

    /**
     * @return Returns the original Image (Full sized) to put it in detailed frame
     * Default is gif.images.original
     */
    Image getImageForOriginal();

    /**
     * @param isPortrait the parameter if current app orientation == portrait
     * @return Returns the properly image depending on orientation
     * ? is it needed at all?
     */
    default Image getImageFor(boolean isPortrait){
        if (isPortrait)
            return getImageForPortrait();
        return getImageForLandscape();
    }
}
