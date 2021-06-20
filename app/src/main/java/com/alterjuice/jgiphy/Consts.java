package com.alterjuice.jgiphy;

public @interface Consts {
    String BASE_URL = "http://api.giphy.com/";
    String SEARCH_URL = "v1/gifs/search";
    String TREND_URL = "v1/gifs/trending";

    Integer countGifsPerRequestLimit = 50;


    String apiKeyData = "data";
    String apiKeyMeta = "meta";
    String apiKeyPagination = "pagination";

    String keyParamApiKey = "api_key";
    String keyParamSearchQuery = "q";
    String keyParamOffset = "offset";
    String keyParamLimit = "limit";
    String keyParamRating = "rating";
    String keyParamLanguage = "lang";
    String keyParamRandomId = "random_id";

    String apiKeyGifType = "type";
    String apiKeyGifTitle = "title";
    String apiKeyGifId = "id";
    String apiKeyGifSlug = "slug";
    String apiKeyGifUrl = "url";
    String apiKeyGifBitlyUrl = "bitly_url";
    String apiKeyGifEmbedUrl = "embed_url";
    String apiKeyGifUsername = "username";
    String apiKeyGifSource = "source";
    String apiKeyGifRating = "rating";
    String apiKeyGifContentUrl = "content_url";
    String apiKeyGifUser = "user";
    String apiKeyGifSourceTld = "source_tld";
    String apiKeyGifSourcePostUrl = "source_post_url";
    String apiKeyGifUpdateDatetime = "update_datetime";
    String apiKeyGifCreateDatetime = "create_datetime";
    String apiKeyGifImportDatetime = "import_datetime";
    String apiKeyGifTrendingDatetime = "trending_datetime";
    String apiKeyGifImages = "images";

    String apiKeyImageFrames = "frames";
    String apiKeyImageUrl = "url";
    String apiKeyImageWidth = "width";
    String apiKeyImageHeight = "height";
    String apiKeyImageSize = "size";
    String apiKeyImageMp4 = "mp4";
    String apiKeyImageMp4Size = "mp4_size";
    String apiKeyImageWebp = "webp";
    String apiKeyImageWebpSize = "webp_size";

    String apiKeyImagesFixedHeight = "fixed_height";
    String apiKeyImagesFixedHeightStill = "fixed_height_still";
    String apiKeyImagesFixedHeightDownsampled = "fixed_height_downsampled";
    String apiKeyImagesFixedWidth = "fixed_width";
    String apiKeyImagesFixedWidthStill = "fixed_width_still";
    String apiKeyImagesFixedWidthDownsampled = "fixed_width_downsampled";
    String apiKeyImagesFixedHeightSmall = "fixed_height_small";
    String apiKeyImagesFixedHeightSmallStill = "fixed_height_small_still";
    String apiKeyImagesFixedWidthSmall = "fixed_width_small";
    String apiKeyImagesFixedWidthSmallStill = "fixed_width_small_still";
    String apiKeyImagesDownsized = "downsized";
    String apiKeyImagesDownsizedStill = "downsized_still";
    String apiKeyImagesDownsizedLarge = "downsized_large";
    String apiKeyImagesDownsizedMedium = "downsized_medium";
    String apiKeyImagesDownsizedSmall = "downsized_small";
    String apiKeyImagesOriginal = "original";
    String apiKeyImagesOriginalStill = "original_still";
    String apiKeyImagesLooping = "looping";
    String apiKeyImagesPreview = "preview";
    String apiKeyImagesPreviewGif = "preview_gif";

    String apiKeyUserAvatarUrl = "avatar_url";
    String apiKeyUserBannerUrl = "banner_url";
    String apiKeyUserProfileUrl = "profile_url";
    String apiKeyUserUsername = "username";
    String apiKeyUserDisplayName = "display_name";

    String apiKeyMetaMsg = "msg";
    String apiKeyMetaStatus = "status";
    String apiKeyMetaResponseId = "response_id";

    String apiKeyPaginationOffset = "offset";
    String apiKeyPaginationTotalCount = "total_count";
    String apiKeyPaginationCount = "count";



}
