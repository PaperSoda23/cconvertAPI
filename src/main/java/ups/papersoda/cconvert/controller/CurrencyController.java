package ups.papersoda.cconvert.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    @GetMapping(produces = {"application/json"})
    public GetAllCurrenciesResponse getCurrencies()
            throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException
    {
        var cis = new CurrencyIntegrationService();
        var currencies = cis.getCurrencies(
                "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU",
                5
        );
       var currencyService = new CurrencyService();
       return new GetAllCurrenciesResponse(currencyService.getCurrencyNames(currencies));
    }
}
