package ups.papersoda.cconvert.integrations.pojo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "FxRates")
public class CurrenciesPOJO {
    @JacksonXmlProperty(localName = "FxRate")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CurrencyPOJO> currencies;

    public List<CurrencyPOJO> getCurrencies() {
        return currencies;
    }
    public void setCurrencies(List<CurrencyPOJO> currencies) {
        this.currencies = currencies;
    }
}
