package ru.alfabank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.alfabank.service.IExchangeRateService;


@RestController
@RequestMapping()
public class ExchangeController {

    @Autowired
    private IExchangeRateService rateService;

    @GetMapping("/gif/{code}")
    public ModelAndView getfiphy(@PathVariable String code, RedirectAttributes attributes){
        return new ModelAndView("redirect:"+rateService.getRates(code));
    }

}
