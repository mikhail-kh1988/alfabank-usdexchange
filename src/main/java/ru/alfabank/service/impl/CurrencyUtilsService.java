package ru.alfabank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfabank.client.ExchangeClient;
import ru.alfabank.dto.ExchangeRatesDto;
import ru.alfabank.service.ICurrencyUtilsService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
public class CurrencyUtilsService implements ICurrencyUtilsService {

    private static final String RICH = "rich";
    private static final String BROCK = "brock";

    @Autowired
    private ExchangeClient exchangeClient;

    @Value("${ru.alfabank.exchange}")
    private String exchangeApiId;

    @Override
    public String getDateEarly(){
        StringBuilder date = new StringBuilder();
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate earlyDate = currentDate.minus(Period.ofDays(1));

        date.append(earlyDate.format(formatter));
        return date.toString();
    }

    @Override
    public String defineCurrency(String code){
        ExchangeRatesDto early = exchangeClient.getRates(exchangeApiId);
        ExchangeRatesDto toDay = exchangeClient.getEarlyRate(exchangeApiId, this.getDateEarly());

        BigDecimal earlyPrice = new BigDecimal(early.getRates().get(code).toString()).setScale(12, RoundingMode.CEILING);
        BigDecimal todayPrice = new BigDecimal(toDay.getRates().get(code).toString()).setScale(12, RoundingMode.CEILING);

        if(earlyPrice.longValue() > todayPrice.longValue()){
            return BROCK;
        }
        return RICH;
    }
}
