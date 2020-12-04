package ups.papersoda.cconvert.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ups.papersoda.cconvert.data.model.Currency;

public interface CurrencyDAO extends JpaRepository<Currency, Long> { }
