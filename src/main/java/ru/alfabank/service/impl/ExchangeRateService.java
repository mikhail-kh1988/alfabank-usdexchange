package ru.alfabank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfabank.client.ExchangeClient;
import ru.alfabank.client.GiphyClient;
import ru.alfabank.dto.ExchangeRatesDto;
import ru.alfabank.dto.GiphyDto;
import ru.alfabank.dto.giphyEntry.Datum;
import ru.alfabank.service.IExchangeRateService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ExchangeRateService implements IExchangeRateService {

    private static final String RICH = "rich";
    private static final String BROCK = "brock";

    @Autowired
    private ExchangeClient exchangeClient;

    @Autowired
    private GiphyClient giphyClient;

    @Value("${ru.alfabank.exchange}")
    private String exchangeApiId;

    @Value("${ru.alfabank.giphy}")
    private String giphyId;


    @Override
    public String getRates(String codeCurrency) {
        return getGiphy(defineCurrency(codeCurrency.toUpperCase()));
    }

    private String defineCurrency(String code){
        ExchangeRatesDto early = exchangeClient.getRates(exchangeApiId);
        ExchangeRatesDto toDay = exchangeClient.getEarlyRate(exchangeApiId, this.getDateEarly());

        BigDecimal earlyPrice = new BigDecimal(early.getRates().get(code).toString()).setScale(12, RoundingMode.CEILING);
        BigDecimal todayPrice = new BigDecimal(toDay.getRates().get(code).toString()).setScale(12, RoundingMode.CEILING);

        if(earlyPrice.longValue() < todayPrice.longValue()){
            return BROCK;
        }
        return RICH;
    }

    private String getDateEarly(){
        StringBuilder date = new StringBuilder();
        LocalDate localDate = LocalDate.now();

        if(localDate.getDayOfMonth() == 1){
            date.append(localDate.getYear());
            date.append("-");
            date.append("05");
            date.append("-");
            date.append(31);
        }else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
            date.append(localDate.format(formatter));
            date.append("-");
            date.append("0"+(localDate.getDayOfMonth()-1));
        }

        return date.toString();
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
