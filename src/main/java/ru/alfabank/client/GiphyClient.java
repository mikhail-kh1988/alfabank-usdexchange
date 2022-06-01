package ru.alfabank.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alfabank.dto.GiphyDto;

@FeignClient(value = "giphy", url = "https://api.giphy.com/v1/gifs/search")
public interface GiphyClient {

    @GetMapping(value = "?api_key={apiKey}&q={query}&limit=1")
    GiphyDto getGif(@PathVariable String apiKey, @PathVariable String query);

}
