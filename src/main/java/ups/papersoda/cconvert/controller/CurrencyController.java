package ups.papersoda.cconvert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ups.papersoda.cconvert.dao.CurrencyDAO;
import ups.papersoda.cconvert.data.response.GetAllCurrenciesResponse;
import ups.papersoda.cconvert.data.response.GetConversionResultResponse;
import ups.papersoda.cconvert.service.CurrencyService;


@RestController()
@RequestMapping(path = "currency")
public class CurrencyController {
    private final CurrencyService currencyService;
    private final CurrencyDAO currencyDAO;

    @Autowired
    public CurrencyController(
            CurrencyService currencyService,
            CurrencyDAO currencyDAO
    ) {
        this.currencyService = currencyService;
        this.currencyDAO = currencyDAO;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public GetAllCurrenciesResponse getCurrencyNames()
    {
       return new GetAllCurrenciesResponse(currencyService.getCurrencyNames(currencyDAO.findAll()));
    }

    @GetMapping(value = "convert", produces = {MediaType.APPLICATION_JSON_VALUE})
    public GetConversionResultResponse getConversionResult(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam(name = "amt") double amountToConvert
    )
    {
        return new GetConversionResultResponse(currencyService.convertCurrency(from, to, amountToConvert, currencyDAO.findAll()));
    }
}
