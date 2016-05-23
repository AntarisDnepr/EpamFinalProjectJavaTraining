package ua.nure.revuk.SummaryTask4.web.command;

import ua.nure.revuk.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 05.02.2016
 * <p>
 * Main interface for the Command pattern implementation.
 */
public abstract class Command {

    /**
     * Execution method for command.
     *
     * @return Address to go once the command is executed.
     */
    public abstract String execute(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException, ServletException, AppException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}