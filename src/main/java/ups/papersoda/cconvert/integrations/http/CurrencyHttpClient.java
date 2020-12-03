package ups.papersoda.cconvert.integrations.http;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CurrencyHttpClient {
    public static Optional<String> getCurrencies(final String uri, int timeoutSeconds)
            throws InterruptedException, ExecutionException, TimeoutException
    {
        java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(uri))
                .build();

        var xml = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .get(timeoutSeconds, TimeUnit.SECONDS);

        return Optional.ofNullable(xml);
    }
}
