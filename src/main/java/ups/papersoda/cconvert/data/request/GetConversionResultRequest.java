package ups.papersoda.cconvert.data.request;

public class GetConversionResultRequest {
    private String fromCurrency;
    private String toCurrency;
    private double amountToConvert;

    public String getFromCurrency() {
        return fromCurrency;
    }
    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }
    public String getToCurrency() {
        return toCurrency;
    }
    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }
    public double getAmountToConvert() {
        return amountToConvert;
    }
    public void setAmountToConvert(double amountToConvert) {
        this.amountToConvert = amountToConvert;
    }
}
