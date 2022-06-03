package ru.alfabank.service;

import ru.alfabank.dto.ExchangeRatesDto;
import java.util.Map;

public interface IExchangeService {

    Map<String, ExchangeRatesDto> getCurrentAndLatterDateCurrency();

}
