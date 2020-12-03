package ups.papersoda.cconvert.exception;

/**
 * Exception exists because currency name should have been found
 * Exception names are served from database, so they should be accurate
 * Occurrence of this error may indicate problems with database
 */
public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException(String message) {
        super(message);
    }
}
