package com.tiddev.clients.currencyExchange;

import com.tiddev.clients.currencyExchange.dto.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "currency-exchange")
public interface CurrencyExchangeClient {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
