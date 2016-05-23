package ua.nure.revuk.SummaryTask4.web.command.common;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

    /**
     * Epam Final Project
     * Revuk Group Java 2-1
     *
     * @author Revuk Oleksandr
     * @version 1.0
     * @since 21.02.2016
     */

    @RunWith(MockitoJUnitRunner.class)
    public class SortFlightsCommandTest {

        @Mock
        private HttpServletRequest request;
        @Mock
        private HttpServletResponse response;
        @Mock
        private HttpSession session;

        private SortFlightsCommand sortFlightsCommand;
        private List allFlights;
        private User user;

        @Before
        public void setUp() throws Exception {
            sortFlightsCommand = new SortFlightsCommand();
            allFlights = new ArrayList<>();
            user = new User();
            when(request.getSession()).thenReturn(session);
            when(session.getAttribute("allFlights")).thenReturn(allFlights);
            when(session.getAttribute("user")).thenReturn(user);
        }


        @Test(expected = Exception.class)
        public void testExecuteNoParameters() throws Exception {
            sortFlightsCommand.execute(request, response);
        }

        @Test
        public void testExecuteSortFlightsDriver() throws Exception {
            user.setRoleId(2);
            when(request.getParameter("sortFlightsType")).thenReturn("sortFlightsTypeStatus");

            String result = sortFlightsCommand.execute(request, response);

            // check Correct Forward
            assertEquals(Path.PAGE_LIST_FLIGHTS_DRIVER, result);
        }

        @Test
        public void testExecuteSortFlightsTypeStatus() throws Exception {
            user.setRoleId(1);
            when(request.getParameter("sortFlightsType")).thenReturn("sortFlightsTypeStatus");

            String result = sortFlightsCommand.execute(request, response);

            // check Correct Forward
            assertEquals(Path.PAGE_LIST_FLIGHTS, result);
        }
    }