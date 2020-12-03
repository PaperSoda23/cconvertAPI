package ups.papersoda.cconvert.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ups.papersoda.cconvert.dto.Currencies;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController()
@RequestMapping(path = "currency")
public class CurrencyController {

    @GetMapping(produces = {"application/json"})
    public Currencies getCurrencies() throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU"))
                .build();

        var xml = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);

        var xmlMapper = new XmlMapper();
        var pojo = xmlMapper.readValue(xml.get(5, TimeUnit.SECONDS), Currencies.class);
        return pojo;
    }
}
