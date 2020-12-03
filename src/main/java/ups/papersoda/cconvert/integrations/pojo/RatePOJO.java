package ups.papersoda.cconvert.integrations.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "CcyAmt")
public class RatePOJO {
    @JacksonXmlProperty(localName = "Ccy")
    String name;
    @JacksonXmlProperty(localName = "Amt")
    double price;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
