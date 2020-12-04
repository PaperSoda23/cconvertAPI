package ups.papersoda.cconvert.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.scheduling.annotation.Scheduled;
import ups.papersoda.cconvert.dao.CurrencyDAO;
import ups.papersoda.cconvert.integrations.service.CurrencyIntegrationService;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class CurrencyRefresherService {
    private final CurrencyDAO currencyDAO;
    private final CurrencyIntegrationService currencyIntegrationService;

    private CurrencyRefresherService(
            CurrencyDAO currencyDAO,
            CurrencyIntegrationService currencyIntegrationService
    ) {
        this.currencyDAO = currencyDAO;
        this.currencyIntegrationService = currencyIntegrationService;
    }

    /**
     * Refreshes currencies every day at 4am
     */
    @Scheduled(cron = "0 0 4 * * *", zone = "Europe/Paris")
    private void refreshCurrency()
            throws SQLException, InterruptedException, ExecutionException, TimeoutException, JsonProcessingException
    {
        currencyDAO.deleteAllInBatch();
        if (currencyDAO.count() != 0) throw new SQLException("table is not empty before currency rate update");
        final var newCurrencies = currencyIntegrationService.getCurrencies(
                "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU",
                5
        );
        currencyDAO.saveAll(newCurrencies);
    }
}
