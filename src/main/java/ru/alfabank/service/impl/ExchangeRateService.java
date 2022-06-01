package ru.alfabank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfabank.client.GiphyClient;
import ru.alfabank.dto.GiphyDto;
import ru.alfabank.dto.giphyEntry.Datum;
import ru.alfabank.service.ICurrencyUtilsService;
import ru.alfabank.service.IExchangeRateService;

@Service
public class ExchangeRateService implements IExchangeRateService {
    @Autowired
    private ICurrencyUtilsService currencyUtilService;

    @Autowired
    private GiphyClient giphyClient;

    @Value("${ru.alfabank.giphy}")
    private String giphyId;


    @Override
    public String getRates(String codeCurrency) {
        return getGiphy(currencyUtilService.defineCurrency(codeCurrency.toUpperCase()));
    }

    private String getGiphy(String emoji){
        String path = "";
        GiphyDto giphyDto = giphyClient.getGif(giphyId, emoji);

        for (Datum d: giphyDto.getData()) {
            path = d.getImages().getOriginal().getUrl();
        }

        return path;
    }
}
