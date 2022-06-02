package ru.alfabank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.alfabank.service.IExchangeCurrencyService;


@RestController
@RequestMapping()
public class ExchangeController {

    @Autowired
    private IExchangeCurrencyService currencyService;

    @GetMapping("/gif/{code}")
    public ModelAndView getGiphy(@PathVariable String code){
        return new ModelAndView("redirect:"+currencyService.getUrlCurrency(code));
    }

}
