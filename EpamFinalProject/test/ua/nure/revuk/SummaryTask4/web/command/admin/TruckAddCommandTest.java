package ua.nure.revuk.SummaryTask4.web.command.admin;

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
public class TruckAddCommandTest {

    @Mock
    private DBManager dbManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private TruckAddCommand truckAddCommand;

    @Before
    public void setUp() throws Exception {
        truckAddCommand = new TruckAddCommand(dbManager);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testExecuteNoParameters() throws Exception {
        String result = truckAddCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.PAGE_TRUCK_ADD, result);
    }

    @Test(expected = AppException.class)
    public void testExecuteInsertToBDNoValidationParametersTruck() throws Exception {
        when(request.getParameter("insertToBD")).thenReturn("insertToBD");
        truckAddCommand.execute(request, response);
    }

    @Test
    public void testExecuteCheckInsertTruckInBD() throws Exception {
        when(request.getParameter("insertToBD")).thenReturn("insertToBD");
        when(request.getParameter("truckName")).thenReturn("truck");
        when(request.getParameter("carrying")).thenReturn("1");
        when(request.getParameter("capacity")).thenReturn("1");
        when(request.getParameter("length")).thenReturn("1");
        when(request.getParameter("lorryWithSides")).thenReturn("true");
        when(request.getParameter("refrigerator")).thenReturn("true");
        when(request.getParameter("serviceable")).thenReturn("true");

        String result = truckAddCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.COMMAND_LIST_TRUCKS, result);
    }
}