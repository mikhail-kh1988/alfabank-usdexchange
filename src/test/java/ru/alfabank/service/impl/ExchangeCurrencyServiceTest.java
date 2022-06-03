package ru.alfabank.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.alfabank.dto.ExchangeRatesDto;
import ru.alfabank.service.IExchangeService;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ExchangeCurrencyServiceTest {

    @Autowired
    private ExchangeCurrencyService currencyService;

    @MockBean
    private IExchangeService exchangeService;

    @Test
    void getUrlCurrency() {
        String tempCurrencyMoney = "USD";

        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put("USD", new BigDecimal(1.001));

        ExchangeRatesDto toDay = new ExchangeRatesDto();
        toDay.setRates(rates);

        ExchangeRatesDto early = new ExchangeRatesDto();
        early.setRates(rates);

        Map<String, ExchangeRatesDto> dto = new HashMap<>();
        dto.put("toDay", toDay);
        dto.put("early", early);

        Mockito.doReturn(dto)
                .when(exchangeService)
                .getCurrentAndLatterDateCurrency();

        String testUrl = currencyService.getUrlCurrency(tempCurrencyMoney);

        assertNotNull(testUrl);
    }
}