package ups.papersoda.cconvert.service;

import ups.papersoda.cconvert.data.model.Currency;

import java.util.List;
import java.util.stream.Collectors;

public class CurrencyService {
    public List<String> getCurrencyNames(final List<Currency> currencies) {
        return currencies
                .stream()
                .map(Currency::getName)
                .collect(Collectors.toList());
    }
}
