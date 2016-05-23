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
public class DeleteUserCommandTest {

    @Mock
    private DBManager dbManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private DeleteUserCommand deleteUserCommand;

    @Before
    public void setUp() throws Exception {
        deleteUserCommand = new DeleteUserCommand(dbManager);
        when(request.getSession()).thenReturn(session);
    }

    @Test(expected = AppException.class)
    public void testExecuteNoParameters() throws Exception {
        deleteUserCommand.execute(request, response);
    }

    @Test
    public void testExecuteCheckDBManagerDeleteUser() throws Exception {
        when(request.getParameter("deleteUserId")).thenReturn("1");
        deleteUserCommand.execute(request, response);

        // check the method call
        verify(dbManager).deleteUser(1);
    }

    @Test
    public void testExecuteCorrectForward() throws Exception {
        when(request.getParameter("deleteUserId")).thenReturn("1");
        String result = deleteUserCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.COMMAND_LIST_USERS, result);
    }
}
