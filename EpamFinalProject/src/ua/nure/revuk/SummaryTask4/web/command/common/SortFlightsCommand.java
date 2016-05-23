package ua.nure.revuk.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.Role;
import ua.nure.revuk.SummaryTask4.db.Status;
import ua.nure.revuk.SummaryTask4.db.bean.FlightUserTruckBean;
import ua.nure.revuk.SummaryTask4.db.entity.User;
import ua.nure.revuk.SummaryTask4.exception.AppException;
import ua.nure.revuk.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Sort flights.
 */
public class SortFlightsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(SortFlightsCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Sort flights Commands starts");
        // the default path of transition in case of success
        String forward = Path.PAGE_LIST_FLIGHTS;

        // the approve list flights path of transition if has flag
        String approve = request.getParameter("approve");
        if (approve != null && approve.equals("approve")) {
            forward = Path.PAGE_LIST_FLIGHTS_APPROVE;
            LOG.debug("forward -> approve list flights");
        }

        // get this session
        HttpSession session = request.getSession();

        String sortFlightsType = request.getParameter("sortFlightsType");
        LOG.debug("Sort flights Type --> " + sortFlightsType);

        if (SortFlightsTypesContainer.isCorrectTypeOfSort(sortFlightsType)) {

            LOG.trace("parameter of type sort correct");
            List<FlightUserTruckBean> allFlights = (ArrayList<FlightUserTruckBean>) session.getAttribute("allFlights");
            LOG.trace("Attribute allFlights --> " + allFlights);
            User user = (User) session.getAttribute("user");
            LOG.trace("Attribute user --> " + user);

            if (Role.getRole(user) == Role.DRIVER) {

                LOG.info("Role user DRIVER");
                forward = Path.PAGE_LIST_FLIGHTS_DRIVER;
                List<FlightUserTruckBean> allOpenFlightsFromDrivers = new ArrayList<>();

                for (FlightUserTruckBean flight : allFlights) {
                    if (flight.getBeanStatusName().equals(Status.OPEN)) {
                        allOpenFlightsFromDrivers.add(flight);
                    }
                }
                allFlights = allOpenFlightsFromDrivers;
            }

            // sort flights by date creation
            if (sortFlightsType.equals("sortFlightsTypeDateCreation")) {

                LOG.trace("sort flights by date creation");
                Collections.sort(allFlights, new Comparator<FlightUserTruckBean>() {
                    public int compare(FlightUserTruckBean o1, FlightUserTruckBean o2) {
                        if (o1.getBeanDateCreation() == null || o2.getBeanDateCreation() == null) {
                            return 0;
                        }
                        return o1.getBeanDateCreation().compareTo(o2.getBeanDateCreation());
                    }
                });
            }

            // sort flights by date Departure
            if (sortFlightsType.equals("sortFlightsTypeDateDeparture")) {

                LOG.trace("sort flights by date Departure");
                Collections.sort(allFlights, new Comparator<FlightUserTruckBean>() {
                    public int compare(FlightUserTruckBean o1, FlightUserTruckBean o2) {
                        if (o1.getBeanDateDeparture() == null || o2.getBeanDateDeparture() == null) {
                            return 0;
                        }
                        return o1.getBeanDateDeparture().compareTo(o2.getBeanDateDeparture());
                    }
                });
            }

            // sort flights by Flight Number
            if (sortFlightsType.equals("sortFlightsTypeNumberFlight")) {

                LOG.trace("sort flights by Flight Number");
                Collections.sort(allFlights, new Comparator<FlightUserTruckBean>() {
                    public int compare(FlightUserTruckBean o1, FlightUserTruckBean o2) {
                        return o1.getBeanFlightNumber() - o2.getBeanFlightNumber();
                    }
                });
            }

            // sort flights by status
            if (sortFlightsType.equals("sortFlightsTypeStatus")) {

                LOG.trace("sort flights by status");
                Collections.sort(allFlights, new Comparator<FlightUserTruckBean>() {
                    public int compare(FlightUserTruckBean o1, FlightUserTruckBean o2) {
                        return o1.getBeanStatusId() - o2.getBeanStatusId();
                    }
                });

            }

            // sort flights by distance
            if (sortFlightsType.equals("sortFlightsTypeDistance")) {

                LOG.trace("sort flights by distance");
                Collections.sort(allFlights, new Comparator<FlightUserTruckBean>() {
                    public int compare(FlightUserTruckBean o1, FlightUserTruckBean o2) {
                        return (int) (o1.getBeanDistance() - o2.getBeanDistance());
                    }
                });

            }

            // sort flights by distance
            if (sortFlightsType.equals("sortFlightsTypeDestination")) {

                LOG.trace("sort flights by destination");
                Collections.sort(allFlights, new Comparator<FlightUserTruckBean>() {
                    public int compare(FlightUserTruckBean o1, FlightUserTruckBean o2) {
                        return nullSafeStringComparator(o1.getBeanDestination(), o2.getBeanDestination());
                    }
                });

            }

            // sort flights by first name
            if (sortFlightsType.equals("sortFlightsTypeFirstName")) {

                LOG.trace("sort flights by first name");
                Collections.sort(allFlights, new Comparator<FlightUserTruckBean>() {
                    public int compare(FlightUserTruckBean o1, FlightUserTruckBean o2) {
                        return nullSafeStringComparator(o1.getBeanDriverFirstName(), o2.getBeanDriverFirstName());
                    }
                });

            }

            // sort flights by last name
            if (sortFlightsType.equals("sortFlightsTypeLastName")) {

                LOG.trace("sort flights by last name");
                Collections.sort(allFlights, new Comparator<FlightUserTruckBean>() {
                    public int compare(FlightUserTruckBean o1, FlightUserTruckBean o2) {
                        return nullSafeStringComparator(o1.getBeanDriverLastName(), o2.getBeanDriverLastName());
                    }
                });
            }

            // put all flights list to request
            session.setAttribute("allFlights", allFlights);
            LOG.trace("Set the request attribute: allFlights");

        } else {
            LOG.error("No parameter of type sort!");
            throw new AppException("No parameter of type sort!");
        }

        LOG.debug("Sort Flights Commands finished");
        return forward;
    }

    public static int nullSafeStringComparator(final String one, final String two) {
        if (one == null ^ two == null) {
            return (one == null) ? -1 : 1;
        }

        if (one == null && two == null) {
            return 0;
        }

        return one.compareToIgnoreCase(two);
    }
}