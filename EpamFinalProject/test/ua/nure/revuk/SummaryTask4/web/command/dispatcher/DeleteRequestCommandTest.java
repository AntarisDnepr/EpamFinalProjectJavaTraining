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
public class DeleteRequestCommandTest {

    @Mock
    private DBManager dbManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private DeleteRequestCommand deleteRequestCommand;
    private User user;

    @Before
    public void setUp() throws Exception {
        deleteRequestCommand = new DeleteRequestCommand(dbManager);
        when(request.getSession()).thenReturn(session);
        user = new User();
        user.setRoleId(1);
    }

    @Test(expected = AppException.class)
    public void testExecuteNoParameters() throws Exception {
        deleteRequestCommand.execute(request, response);
    }

    @Test(expected = AppException.class)
    public void testExecuteParameterOnlyUser() throws Exception {
        when(session.getAttribute("user")).thenReturn(user);
        deleteRequestCommand.execute(request, response);
    }

    @Test
    public void testExecuteCheckDBManagerAdminAfterDeleteRequest() throws Exception {
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("deleteRequestId")).thenReturn("1");

        deleteRequestCommand.execute(request, response);

        // check the method call
        verify(dbManager).deleteRequest(1);
    }

    @Test
    public void testExecuteCorrectForwardAdminAfterDeleteRequest() throws Exception {
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("deleteRequestId")).thenReturn("1");

        String result = deleteRequestCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.COMMAND_LIST_REQUEST, result);
    }

    @Test
    public void testExecuteCorrectForwardDriverAfterDeleteRequest() throws Exception {
        user.setRoleId(2);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("deleteRequestId")).thenReturn("1");
        when(request.getParameter("flightId")).thenReturn("1");

        String result = deleteRequestCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.COMMAND_DRIVER_PERSONAL, result);
    }
}