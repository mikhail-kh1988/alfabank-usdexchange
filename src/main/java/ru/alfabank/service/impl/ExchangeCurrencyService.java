package ru.alfabank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alfabank.dto.ExchangeRatesDto;
import ru.alfabank.service.ICurrencyService;
import ru.alfabank.service.IExchangeCurrencyService;
import ru.alfabank.service.IExchangeService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;


@Service
public class ExchangeCurrencyService implements IExchangeCurrencyService {

    @Autowired
    private IExchangeService exchangeService;

    private ICurrencyService currencyService;

    @Autowired
    private RichCurrencyService richService;

    @Autowired
    private BrockCurrencyService brockService;

    @Override
    public String getUrlCurrency(String money) {

        Map<String, ExchangeRatesDto> list = exchangeService.getCurrentAndLatterDateCurrency();

        currencyService = getCurrencyService(list, money);

        return currencyService.getGifUrl();
    }

    private ICurrencyService getCurrencyService(Map<String, ExchangeRatesDto> dto, String moneyCode){
        ExchangeRatesDto toDay = dto.get("toDay");
        ExchangeRatesDto early = dto.get("early");

        BigDecimal earlyPrice = new BigDecimal(early.getRates().get(moneyCode.toUpperCase()).toString()).setScale(12, RoundingMode.HALF_UP);
        BigDecimal todayPrice = new BigDecimal(toDay.getRates().get(moneyCode.toUpperCase()).toString()).setScale(12, RoundingMode.HALF_UP);

        if(earlyPrice.compareTo(todayPrice) > 0){
            return brockService;
        }else {
            return richService;
        }
    }
}
