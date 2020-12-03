package ups.papersoda.cconvert.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "CcyAmt")
public class Rate {
    @JacksonXmlProperty(localName = "Ccy")
    String name;
    @JacksonXmlProperty(localName = "Amt")
    double price;
}
