package ups.papersoda.cconvert.data.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyInfo {
    private long id;
    private float basePrice;
    private String baseCurrency;
}
