package ua.nure.revuk.SummaryTask4.web.command.common;

import org.junit.Test;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 21.02.2016
 */

public class SortFlightsTypesContainerTest {

    @Test
    public void testExecuteIsCorrectTypeOfSort() throws Exception {
        assert (SortFlightsTypesContainer.isCorrectTypeOfSort("sortFlightsTypeStatus"));
    }
}