package ups.papersoda.cconvert.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ups.papersoda.cconvert.data.response.GetAllCurrenciesResponse;
import ups.papersoda.cconvert.data.response.GetConversionResultResponse;
import ups.papersoda.cconvert.integrations.service.CurrencyIntegrationService;
import ups.papersoda.cconvert.service.CurrencyService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController()
@RequestMapping(path = "currency")
public class CurrencyController {
    private final CurrencyService currencyService;
    private final CurrencyIntegrationService currencyIntegrationService;

    @Autowired
    public CurrencyController(
            CurrencyService currencyService,
            CurrencyIntegrationService currencyIntegrationService
    ) {
        this.currencyService = currencyService;
        this.currencyIntegrationService = currencyIntegrationService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public GetAllCurrenciesResponse getCurrencyNames()
            throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException
    {
        var currencies = currencyIntegrationService.getCurrencies(
                "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU",
                5
        );
       return new GetAllCurrenciesResponse(currencyService.getCurrencyNames(currencies));
    }

    @GetMapping(value = "convert", produces = {MediaType.APPLICATION_JSON_VALUE})
    public GetConversionResultResponse getConversionResult(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam(name = "amt") double amountToConvert
    ) throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException
    {
        var currencies = currencyIntegrationService.getCurrencies(
                "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU",
                5
        );
        return new GetConversionResultResponse(currencyService.convertCurrency(from, to, amountToConvert, currencies));
    }
}
