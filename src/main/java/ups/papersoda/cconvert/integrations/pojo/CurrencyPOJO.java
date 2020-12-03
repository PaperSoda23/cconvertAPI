package ups.papersoda.cconvert.integrations.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "FxRate")
public class CurrencyPOJO {
    @JacksonXmlProperty(localName = "Tp")
    String type;
    @JacksonXmlProperty(localName = "Dt")
    Date date;
    @JacksonXmlProperty(localName = "CcyAmt")
    @JacksonXmlElementWrapper(useWrapping = false)
    List<RatePOJO> ratePOJOS;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public List<RatePOJO> getRates() {
        return ratePOJOS;
    }
    public void setRates(List<RatePOJO> ratePOJOS) {
        this.ratePOJOS = ratePOJOS;
    }
}
