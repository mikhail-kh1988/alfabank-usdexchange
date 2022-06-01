package ru.alfabank.dto.giphyEntry;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.alfabank.dto.GiphyDto;

@Getter
@Setter
public class Images {
    private Original original;
    private Downsized downsized;
    private DownsizedLarge downsized_large;
    private DownsizedMedium downsized_medium;
    private DownsizedSmall downsized_small;
    private DownsizedStill downsized_still;
    private FixedHeight fixed_height;
    private FixedHeightDownsampled fixed_height_downsampled;
    private FixedHeightSmall fixed_height_small;
    private FixedHeightSmallStill fixed_height_small_still;
    private FixedHeightStill fixed_height_still;
    private FixedWidth fixed_width;
    private FixedWidthDownsampled fixed_width_downsampled;
    private FixedWidthSmall fixed_width_small;
    private FixedWidthSmallStill fixed_width_small_still;
    private FixedWidthStill fixed_width_still;
    private Looping looping;
    private OriginalStill original_still;
    private OriginalMp4 original_mp4;
    private Preview preview;
    private PreviewGif preview_gif;
    private PreviewWebp preview_webp;
    @JsonProperty("480w_still")
    private  _480wStill _480w_still;
}
