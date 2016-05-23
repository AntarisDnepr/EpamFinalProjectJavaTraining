package ua.nure.revuk.SummaryTask4.db;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 21.02.2016
 */
public class StatusTest {

    @Test
    public void testGetStatus() throws Exception {
        assertEquals(Status.getStatus(1), Status.OPEN);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(Status.getStatus(1).getName(), "open");
    }
}