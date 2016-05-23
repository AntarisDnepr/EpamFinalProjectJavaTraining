package ua.nure.revuk.SummaryTask4.exception;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 01.02.2016
 * <p>
 * An exception that provides information on a database access error.
 */
public class DBException extends AppException {

    private static final long serialVersionUID = 9082153673084391328L;

    public DBException() {
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

}
