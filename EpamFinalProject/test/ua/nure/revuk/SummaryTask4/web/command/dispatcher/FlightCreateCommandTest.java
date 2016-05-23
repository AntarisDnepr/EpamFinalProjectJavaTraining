package ua.nure.revuk.SummaryTask4.web.command.dispatcher;

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
public class FlightCreateCommandTest {

    @Mock
    private DBManager dbManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private FlightCreateCommand flightCreateCommand;
    private User user;

    @Before
    public void setUp() throws Exception {
        flightCreateCommand = new FlightCreateCommand(dbManager);
        when(request.getSession()).thenReturn(session);
        user = new User();
        user.setRoleId(1);
    }

    @Test(expected = AppException.class)
    public void testExecuteNoUserParameter() throws Exception {
        flightCreateCommand.execute(request, response);
    }

    @Test
    public void testExecuteNoInsertToBD() throws Exception {
        when(session.getAttribute("user")).thenReturn(user);

        String result = flightCreateCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.PAGE_FLIGHT_CREATE, result);
    }

    @Test
    public void testExecuteInsertToBD() throws Exception {
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("insertToBD")).thenReturn("insertToBD");
        when(request.getParameter("flightNumber")).thenReturn("2");
        when(request.getParameter("dateDeparture")).thenReturn("2099-12-01");
        when(request.getParameter("destination")).thenReturn("destination");
        when(request.getParameter("distance")).thenReturn("2");

        String result = flightCreateCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.COMMAND_LIST_FLIGHTS, result);
    }
}