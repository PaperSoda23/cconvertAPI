package ups.papersoda.cconvert.data.response;

import java.util.List;

public class GetAllCurrenciesResponse {
    private final List<String> currencyNames;
    // Constructor
    public GetAllCurrenciesResponse(final List<String> currencyNames) {
        this.currencyNames = currencyNames;
    }
    // Getters
    public List<String> getCurrencies() { return currencyNames; }
}
