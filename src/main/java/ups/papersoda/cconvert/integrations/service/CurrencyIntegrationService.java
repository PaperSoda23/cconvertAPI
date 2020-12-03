package ups.papersoda.cconvert.integrations.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ups.papersoda.cconvert.data.model.Currency;
import ups.papersoda.cconvert.integrations.http.CurrencyHttpClient;
import ups.papersoda.cconvert.integrations.mappings.CurrencyMapping;
import ups.papersoda.cconvert.integrations.pojo.CurrenciesPOJO;
import ups.papersoda.cconvert.integrations.pojo.CurrencyPOJO;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class CurrencyIntegrationService {
    public List<Currency> getCurrencies(final String uri, final int timeoutSeconds)
            throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException
    {
        var currencyXml = CurrencyHttpClient.getCurrencies(
                uri,
                timeoutSeconds
        );

        XmlMapper xmlMapper = new XmlMapper();
        List<CurrencyPOJO> pojo = xmlMapper.readValue(currencyXml.get(), CurrenciesPOJO.class).getCurrencies();

        List<Currency> domainCurrencies = pojo
                .stream()
                .map(CurrencyMapping::mapCurrency)
                .collect(Collectors.toList());

        return domainCurrencies;
    }
}
