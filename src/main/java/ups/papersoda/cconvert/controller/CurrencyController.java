package ups.papersoda.cconvert.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ups.papersoda.cconvert.data.response.GetAllCurrenciesResponse;
import ups.papersoda.cconvert.integrations.service.CurrencyIntegrationService;
import ups.papersoda.cconvert.service.CurrencyService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController()
@RequestMapping(path = "currency")
public class CurrencyController {
    private CurrencyService currencyService;
    private CurrencyIntegrationService currencyIntegrationService;

    @Autowired
    public CurrencyController(
            CurrencyService currencyService,
            CurrencyIntegrationService currencyIntegrationService
    ) {
        this.currencyService = currencyService;
        this.currencyIntegrationService = currencyIntegrationService;
    }

    @GetMapping(produces = {"application/json"})
    public GetAllCurrenciesResponse getCurrencies()
            throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException
    {
        var currencies = currencyIntegrationService.getCurrencies(
                "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU",
                5
        );
       return new GetAllCurrenciesResponse(currencyService.getCurrencyNames(currencies));
    }
}
