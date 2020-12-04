package ups.papersoda.cconvert.service;

import org.springframework.stereotype.Service;
import ups.papersoda.cconvert.data.model.Currency;
import ups.papersoda.cconvert.exception.CurrencyNotFoundException;
import ups.papersoda.cconvert.helper.NumberHelper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyService {
    public List<String> getCurrencyNames(final List<Currency> currencies) {
        return currencies
                .stream()
                .map(Currency::getName)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param currencyName name of currency
     * @param currencies currency list
     * @return  conversion rate
     * @throws CurrencyNotFoundException when currency name is not found
     */
    private double getCurrencyRate(final String currencyName, final List<Currency> currencies)
            throws CurrencyNotFoundException, NullPointerException
    {
        if (currencyName.equals("EUR")) return 1d;

        Optional<Currency> fromCurrency = currencies
                .stream()
                .filter(c -> c.getName().equals(currencyName))
                .findFirst();
        if (fromCurrency.isEmpty())
            throw new CurrencyNotFoundException(String.format("currency name %s was not found", currencyName));

        return fromCurrency.get().getPrice();
    }

    public double convertCurrency(
            final String from,
            final String to,
            final double amountToConvert,
            final List<Currency> currencies
    ) throws CurrencyNotFoundException, IllegalArgumentException, ArithmeticException
    {
        if (from.equals(to)) return amountToConvert;
        if (NumberHelper.isNegative(amountToConvert))
            throw new IllegalArgumentException("amount should be a positive number");

        final double toRate = getCurrencyRate(to, currencies);
        final double fromRate = getCurrencyRate(from, currencies);
        final double conversionResult = (toRate / fromRate) * amountToConvert;

        return conversionResult;
    }
}
