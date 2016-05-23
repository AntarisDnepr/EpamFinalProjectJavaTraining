package ua.nure.revuk.SummaryTask4.web.command.admin;

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
public class EditUserCommandTest {

    @Mock
    private DBManager dbManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private EditUserCommand editUserCommand;

    @Before
    public void setUp() throws Exception {
        editUserCommand = new EditUserCommand(dbManager);
        when(request.getSession()).thenReturn(session);
    }

    @Test(expected = AppException.class)
    public void testExecuteNoParameters() throws Exception {
        editUserCommand.execute(request, response);
    }

    @Test
    public void testExecuteIncorrectParameterUserId() throws Exception {
        when(request.getParameter("editUserId")).thenReturn("1");
        editUserCommand.execute(request, response);

        // check the method call
        verify(dbManager).getUserById(Integer.valueOf("1"));
    }

    @Test(expected = AppException.class)
    public void testExecuteIncorrectParameters() throws Exception {
        when(request.getParameter("insertToBD")).thenReturn("insertToBD");
        when(request.getParameter("editUserId")).thenReturn("1");
        when(session.getAttribute("editUser")).thenReturn(new User());
        editUserCommand.execute(request, response);
    }

    @Test
    public void testExecuteCheckDBManagerUpdateTruckById() throws Exception {
        User user = new User();
        user.setFirstName("x");
        user.setLastName("x");
        user.setLogin("x");
        user.setPassword("x");
        user.setRoleId(2);

        when(request.getParameter("insertToBD")).thenReturn("insertToBD");
        when(request.getParameter("editUserId")).thenReturn("1");
        when(session.getAttribute("editUser")).thenReturn(user);
        editUserCommand.execute(request, response);

        // check the method call
        verify(dbManager).updateUserById(user);
    }

    @Test
    public void testExecuteCorrectForwardAfterInsertBD() throws Exception {
        User user = new User();
        user.setLogin("x");
        user.setPassword("x");
        user.setFirstName("x");
        user.setLastName("x");
        user.setMail("alex.dnepr@i.ua");
        user.setRoleId(2);

        when(request.getParameter("insertToBD")).thenReturn("insertToBD");
        when(request.getParameter("editUserId")).thenReturn("1");
        when(session.getAttribute("editUser")).thenReturn(user);
        String result = editUserCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.COMMAND_LIST_USERS, result);
    }
}