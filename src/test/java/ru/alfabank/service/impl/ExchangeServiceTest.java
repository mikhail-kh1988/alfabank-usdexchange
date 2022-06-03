package ru.alfabank.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.alfabank.client.ExchangeClient;
import ru.alfabank.dto.ExchangeRatesDto;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExchangeServiceTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Autowired
    private ExchangeService exchangeService;

    @MockBean
    private ExchangeClient exchangeClient;

    @Test
    void getCurrentAndLatterDateCurrency() {
        Map<String, ExchangeRatesDto> list = new HashMap<>();

        ExchangeRatesDto toDayCurrency = new ExchangeRatesDto();
        toDayCurrency.setBase("USD");
        toDayCurrency.setTimestamp(Long.parseLong(LocalDateTime.now().format(formatter)));

        ExchangeRatesDto earlyCurrency = new ExchangeRatesDto();
        earlyCurrency.setBase("USD");
        earlyCurrency.setTimestamp(Long.parseLong(LocalDateTime.now().format(formatter)));

        list.put("toDay", toDayCurrency);
        list.put("early", earlyCurrency);

        Mockito.doReturn(toDayCurrency)
                .when(exchangeClient)
                .getRates(Mockito.anyString());

        Mockito.doReturn(earlyCurrency)
                .when(exchangeClient)
                .getEarlyRate(Mockito.anyString(), Mockito.anyString());

        Map<String, ExchangeRatesDto> testMap = exchangeService.getCurrentAndLatterDateCurrency();

        assertNotNull(testMap);
    }
}