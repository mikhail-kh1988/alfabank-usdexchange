package ru.alfabank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfabank.client.GiphyClient;
import ru.alfabank.dto.GiphyDto;
import ru.alfabank.dto.giphyEntry.Datum;
import ru.alfabank.service.IGifService;

@Service
public class GifService implements IGifService {

    @Autowired
    private GiphyClient giphyClient;

    @Value("${ru.alfabank.giphy}")
    private String giphyId;

    @Override
    public String getGif(String emoji) {
        String path = "";

        GiphyDto giphyDto = giphyClient.getGif(giphyId, emoji);

        for (Datum d: giphyDto.getData()) {
            path = d.getImages().getOriginal().getUrl();
        }

        return path;
    }
}
