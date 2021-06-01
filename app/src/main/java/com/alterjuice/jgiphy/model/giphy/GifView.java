package com.alterjuice.jgiphy.model.giphy;

public interface GifView {
    boolean hasUser();
    Image getImageForPortrait();
    Image getImageForLandscape();
    Image getImageForPreview();
    Image getImageForOriginal();


}
