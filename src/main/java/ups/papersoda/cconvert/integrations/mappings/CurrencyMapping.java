package ups.papersoda.cconvert.integrations.mappings;

import ups.papersoda.cconvert.data.model.Currency;
import ups.papersoda.cconvert.integrations.pojo.CurrencyPOJO;

public class CurrencyMapping {
    public static Currency mapCurrency(CurrencyPOJO currencyPOJO) {
        var currency = new Currency();
        currency.setPrice(currencyPOJO.getRates().get(1).getPrice());
        currency.setName(currencyPOJO.getRates().get(1).getName());
        return currency;
    }
}
