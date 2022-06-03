package ru.alfabank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfabank.client.ExchangeClient;
import ru.alfabank.dto.ExchangeRatesDto;
import ru.alfabank.service.IExchangeService;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeService implements IExchangeService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private ExchangeClient exchangeClient;

    @Value("${ru.alfabank.exchange}")
    private String exchangeApiId;

    @Override
    public Map<String, ExchangeRatesDto> getCurrentAndLatterDateCurrency() {
        Map<String, ExchangeRatesDto> list = new HashMap<>();

        ExchangeRatesDto toDayCurrency = exchangeClient.getRates(exchangeApiId);
        ExchangeRatesDto earlyCurrency = exchangeClient.getEarlyRate(exchangeApiId, getDateEarly());

        list.put("toDay", toDayCurrency);
        list.put("early", earlyCurrency);

        return list;
    }

    private String getDateEarly(){
        return LocalDate.now()
                .minus(Period.ofDays(1))
                .format(formatter);
    }
}
