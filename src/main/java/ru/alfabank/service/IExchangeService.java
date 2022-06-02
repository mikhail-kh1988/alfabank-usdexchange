package ru.alfabank.service;

import ru.alfabank.dto.ExchangeRatesDto;
import java.util.List;

public interface IExchangeService {

    List<ExchangeRatesDto> getCurrentAndLatterDateCurrency();

}
