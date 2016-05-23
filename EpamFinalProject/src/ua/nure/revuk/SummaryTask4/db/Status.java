package ua.nure.revuk.SummaryTask4.db;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 02.02.2016
 */
public enum Status {
    OPEN, IN_PROGRESS, CLOSED, CANCELED;

    public static Status getStatus(int statusId) {
        return Status.values()[statusId - 1];
    }

    public String getName() {
        return name().replace("_", " ").toLowerCase();
    }
}