package ru.alfabank.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alfabank.dto.ExchangeRatesDto;

@FeignClient(value = "exchange", url = "https://openexchangerates.org/api")
public interface ExchangeClient {

    @GetMapping(value = "/latest.json?app_id={appId}", consumes = "application/json")
    ExchangeRatesDto getRates(@PathVariable String appId);

    @GetMapping(value = "/historical/{date}.json?app_id={code}", consumes = "application/json")
    ExchangeRatesDto getEarlyRate(@PathVariable String code, @PathVariable String date);
}
