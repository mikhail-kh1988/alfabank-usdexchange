package ru.alfabank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alfabank.service.ICurrencyService;
import ru.alfabank.service.IGifService;

@Service
public class BrockCurrencyService implements ICurrencyService {
    @Autowired
    private IGifService gifService;

    @Override
    public String getGifUrl() {
        return gifService.getGif("brock");
    }
}
