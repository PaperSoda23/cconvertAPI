package ups.papersoda.cconvert.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "FxRate")
public class Currency {
    @JacksonXmlProperty(localName = "Tp")
    String type;
    @JacksonXmlProperty(localName = "Dt")
    Date date;
    @JacksonXmlProperty(localName = "CcyAmt")
    @JacksonXmlElementWrapper(useWrapping = false)
    List<Rate> rates;
}
