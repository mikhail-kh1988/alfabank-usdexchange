package ru.alfabank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alfabank.dto.ExchangeRatesDto;
import ru.alfabank.service.ICurrencyService;
import ru.alfabank.service.IExchangeCurrencyService;
import ru.alfabank.service.IExchangeService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


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

        List<ExchangeRatesDto> list = exchangeService.getCurrentAndLatterDateCurrency();

        currencyService = getCurrencyService(list, money);

        return currencyService.getGifUrl();
    }

    private ICurrencyService getCurrencyService(List<ExchangeRatesDto> dto, String moneyCode){
        ExchangeRatesDto toDay = dto.get(0);
        ExchangeRatesDto early = dto.get(1);

        BigDecimal earlyPrice = new BigDecimal(early.getRates().get(moneyCode.toUpperCase()).toString()).setScale(12, RoundingMode.HALF_UP);
        BigDecimal todayPrice = new BigDecimal(toDay.getRates().get(moneyCode.toUpperCase()).toString()).setScale(12, RoundingMode.HALF_UP);

        if(earlyPrice.longValue() > todayPrice.longValue()){
            return brockService;
        }else {
            return richService;
        }
    }
}
