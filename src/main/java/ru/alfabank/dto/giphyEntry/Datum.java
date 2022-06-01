package ru.alfabank.dto.giphyEntry;

import lombok.Getter;
import lombok.Setter;
import ru.alfabank.dto.GiphyDto;

@Getter
@Setter
public class Datum {
    private String type;
    private String id;
    private String url;
    private String slug;
    private String bitly_gif_url;
    private String bitly_url;
    private String embed_url;
    private String username;
    private String source;
    private String title;
    private String rating;
    private String content_url;
    private String source_tld;
    private String source_post_url;
    private int is_sticker;
    private String import_datetime;
    private String trending_datetime;
    private Images images;
    private String analytics_response_payload;
    private Analytics analytics;
}
