package ua.nure.revuk.SummaryTask4.web.command.driver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
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
public class FlightCompletedCommandTest {

    @Mock
    private DBManager dbManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private FlightCompletedCommand flightCompletedCommand;

    @Before
    public void setUp() throws Exception {
        flightCompletedCommand = new FlightCompletedCommand(dbManager);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testExecuteNoParameter() throws Exception {
        String result = flightCompletedCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.PAGE_ERROR, result);
    }

    @Test(expected = AppException.class)
    public void testExecuteDriverPersonalIncorrectParameters() throws Exception {
        when(request.getParameter("insertToBD")).thenReturn("insertToBD");

        flightCompletedCommand.execute(request, response);
    }


    @Test
    public void testExecuteDriverPersonal() throws Exception {
        when(request.getParameter("insertToBD")).thenReturn("insertToBD");
        when(request.getParameter("flightId")).thenReturn("1");
        when(request.getParameter("truckId")).thenReturn("1");
        when(request.getParameter("isTruckServiceable")).thenReturn("true");

        String result = flightCompletedCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.COMMAND_DRIVER_PERSONAL, result);
    }
}