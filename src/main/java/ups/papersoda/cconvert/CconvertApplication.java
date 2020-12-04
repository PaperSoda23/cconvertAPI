package ups.papersoda.cconvert;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ups.papersoda.cconvert.dao.CurrencyDAO;
import ups.papersoda.cconvert.integrations.service.CurrencyIntegrationService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@EnableScheduling
public class CconvertApplication {
    public static void main(String[] args) {
        SpringApplication.run(CconvertApplication.class, args);
    }

    @Bean
    ApplicationRunner initialize(CurrencyDAO currencyDAO, CurrencyIntegrationService currencyIntegrationService) {
        return (ApplicationArguments args) -> obtainData(currencyDAO, currencyIntegrationService);
    }

    public void obtainData(CurrencyDAO currencyDAO, CurrencyIntegrationService currencyIntegrationService)
            throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException
    {
        if (currencyDAO.count() != 0) return;
        var currencies = currencyIntegrationService.getCurrencies(
                "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU",
                5
        );
        currencyDAO.saveAll(currencies);
    }


}
