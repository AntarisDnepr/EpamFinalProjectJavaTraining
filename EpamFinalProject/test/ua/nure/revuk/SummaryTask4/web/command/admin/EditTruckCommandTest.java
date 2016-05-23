package ua.nure.revuk.SummaryTask4.web.command.admin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.entity.Truck;
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
public class EditTruckCommandTest {

    @Mock
    private DBManager dbManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private EditTruckCommand editTruckCommand;
    private Truck editTruck;

    @Before
    public void setUp() throws Exception {
        editTruckCommand = new EditTruckCommand(dbManager);
        when(request.getSession()).thenReturn(session);
        editTruck = new Truck();
    }

    @Test(expected = AppException.class)
    public void testExecuteNoParameters() throws Exception {
        editTruckCommand.execute(request, response);
    }

    @Test(expected = AppException.class)
    public void testExecuteIncorrectParameters() throws Exception {
        when(request.getParameter("insertToBD")).thenReturn("insertToBD");
        when(request.getParameter("editTruckId")).thenReturn("1");
        when(request.getParameter("length")).thenReturn("z");
        when(session.getAttribute("editTruck")).thenReturn(new Truck());
        editTruckCommand.execute(request, response);
    }

    @Test
    public void testExecuteCheckDBManagerUpdateTruckById() throws Exception {
        when(request.getParameter("insertToBD")).thenReturn("insertToBD");
        when(request.getParameter("editTruckId")).thenReturn("1");
        when(session.getAttribute("editTruck")).thenReturn(editTruck);
        editTruckCommand.execute(request, response);

        // check the method call
        verify(dbManager).updateTruckById(editTruck);
    }

    @Test
    public void testExecuteCorrectForwardAfterInsertBD() throws Exception {
        when(request.getParameter("insertToBD")).thenReturn("insertToBD");
        when(request.getParameter("editTruckId")).thenReturn("1");
        when(session.getAttribute("editTruck")).thenReturn(editTruck);
        String result = editTruckCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.COMMAND_LIST_TRUCKS, result);
    }

    @Test
    public void testExecuteNotInsertInBD() throws Exception {
        when(request.getParameter("editTruckId")).thenReturn("1");
        editTruckCommand.execute(request, response);

        // check the method call
        verify(dbManager).getTruckById(1);
    }
}