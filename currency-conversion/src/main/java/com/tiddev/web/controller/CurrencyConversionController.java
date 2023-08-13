package com.tiddev.web.controller;


import com.tiddev.clients.currencyExchange.CurrencyExchangeClient;
import com.tiddev.clients.currencyExchange.dto.CurrencyConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {

    private final CurrencyExchangeClient currencyExchangeClient;
    private final RestTemplate restTemplate;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversion> responseEntity =
                restTemplate.getForEntity("http://currency-exchange/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversion.class, uriVariables);

        CurrencyConversion currencyConversion = responseEntity.getBody();
        currencyConversion.setTotalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(quantity));
        return currencyConversion;
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,
                                                               @PathVariable String to,
                                                               @PathVariable BigDecimal quantity) {


        CurrencyConversion currencyConversion = currencyExchangeClient.retrieveExchangeValue(from, to);


        currencyConversion.setTotalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(quantity));
        return currencyConversion;
    }
}
