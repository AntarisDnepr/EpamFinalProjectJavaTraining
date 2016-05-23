package ua.nure.revuk.SummaryTask4.web.command.common;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.entity.User;
import ua.nure.revuk.SummaryTask4.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
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
public class ListFlightsCommandTest {

    @Mock
    private DBManager dbManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private ListFlightsCommand listFlightsCommand;
    private User user;

    @Before
    public void setUp() throws Exception {
        listFlightsCommand = new ListFlightsCommand(dbManager);
        when(request.getSession()).thenReturn(session);
        user = new User();
    }

    @Test(expected = AppException.class)
    public void testExecuteNoParameters() throws Exception {
        listFlightsCommand.execute(request, response);
    }

    @Test(expected = Exception.class)
    public void testExecuteNoRoleByUser() throws Exception {
        when(session.getAttribute("user")).thenReturn(user);

        listFlightsCommand.execute(request, response);
    }

    @Test
    public void testExecuteRoleDriverDBAllFlights() throws Exception {
        user.setRoleId(2);
        user.setId(1);
        when(session.getAttribute("user")).thenReturn(user);

        listFlightsCommand.execute(request, response);
        // check the method call
        verify(dbManager).getAllFlightsFromDriver(1);
    }

    @Test
    public void testExecuteRoleDriverCorrectForward() throws Exception {
        user.setRoleId(2);
        user.setId(1);
        when(session.getAttribute("user")).thenReturn(user);

        String result = listFlightsCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.PAGE_LIST_FLIGHTS_DRIVER, result);
    }

    @Test
    public void testExecuteRoleAdminDBAllFlights() throws Exception {
        user.setRoleId(1);
        when(session.getAttribute("user")).thenReturn(user);

        listFlightsCommand.execute(request, response);

        // check the method call
        verify(dbManager).getAllFlight();
    }

    @Test
    public void testExecuteRoleAdminCorrectForward() throws Exception {
        user.setRoleId(1);
        when(session.getAttribute("user")).thenReturn(user);

        String result = listFlightsCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.PAGE_LIST_FLIGHTS, result);
    }
}