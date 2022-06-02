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
import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeService implements IExchangeService {

    @Autowired
    private ExchangeClient exchangeClient;

    @Value("${ru.alfabank.exchange}")
    private String exchangeApiId;

    @Override
    public List<ExchangeRatesDto> getCurrentAndLatterDateCurrency() {
        List<ExchangeRatesDto> list = new ArrayList<>();

        ExchangeRatesDto toDayCurrency = exchangeClient.getRates(exchangeApiId);
        ExchangeRatesDto earlyCurrency = exchangeClient.getEarlyRate(exchangeApiId, getDateEarly());

        list.add(toDayCurrency);
        list.add(earlyCurrency);

        return list;
    }

    private String getDateEarly(){
        StringBuilder date = new StringBuilder();
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate earlyDate = currentDate.minus(Period.ofDays(1));

        date.append(earlyDate.format(formatter));
        return date.toString();
    }
}
