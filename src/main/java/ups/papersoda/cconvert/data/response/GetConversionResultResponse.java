package ups.papersoda.cconvert.data.response;

public class GetConversionResultResponse {
    private final double conversionResult;
    // Constructor
    public GetConversionResultResponse(double conversionResult) {
        this.conversionResult = conversionResult;
    }
    // Getters
    public double getConversionResult() {
        return conversionResult;
    }
}
