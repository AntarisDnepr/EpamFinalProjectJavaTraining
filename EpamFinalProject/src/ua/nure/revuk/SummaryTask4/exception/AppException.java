package ua.nure.revuk.SummaryTask4.exception;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 01.02.2016
 * <p>
 * An exception that provides information on an application error.
 */
public class AppException extends Exception {

    private static final long serialVersionUID = -154808010449666166L;

    public AppException() {
        super();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }

}
