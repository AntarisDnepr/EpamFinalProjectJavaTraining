package ua.nure.revuk.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Sort Flights Container
 */
public class SortFlightsTypesContainer {

    private static final Logger LOG = Logger.getLogger(SortFlightsTypesContainer.class);
    private static List<String> typeSort = new ArrayList<>();

    static {
        // type sorts
        typeSort.add("sortFlightsTypeDateCreation");
        typeSort.add("sortFlightsTypeDateDeparture");
        typeSort.add("sortFlightsTypeNumberFlight");
        typeSort.add("sortFlightsTypeStatus");
        typeSort.add("sortFlightsTypeDistance");
        typeSort.add("sortFlightsTypeDestination");
        typeSort.add("sortFlightsTypeFirstName");
        typeSort.add("sortFlightsTypeLastName");

        LOG.debug("Sort Flights container was successfully initialized");
        LOG.trace("Number of sorts --> " + typeSort.size());
    }

    /**
     * Check correct type of sort
     *
     * @param typeSortFlights type of sort
     * @return contain or not this type
     */
    public static boolean isCorrectTypeOfSort(String typeSortFlights) {

        if (typeSortFlights == null || !typeSort.contains(typeSortFlights)) {
            LOG.debug("Sort flights container not found type sort of --> " + typeSortFlights);
            return false;
        }

        LOG.trace("type sort --> " + typeSortFlights);
        return true;
    }
}