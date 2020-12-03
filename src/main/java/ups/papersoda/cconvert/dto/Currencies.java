package ups.papersoda.cconvert.dto;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "FxRates")
public class Currencies {
    @JacksonXmlProperty(localName = "FxRate")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Currency> currencies;
}
