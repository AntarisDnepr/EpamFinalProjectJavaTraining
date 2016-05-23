package ua.nure.revuk.SummaryTask4.web.command.common;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.entity.User;

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
public class LoginCommandTest {

    @Mock
    private DBManager dbManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private LoginCommand loginCommand;

    @Before
    public void setUp() throws Exception {
        loginCommand = new LoginCommand(dbManager);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testExecuteNoParameters() throws Exception {
        String result = loginCommand.execute(request, response);

        // check Correct Forward
        assertEquals("/", result);
    }

    @Test
    public void testExecuteNoUserIbDB() throws Exception {
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");

        String result = loginCommand.execute(request, response);

        // check Correct Forward
        assertEquals("/", result);
    }

    @Test
    public void testExecuteNoRoleByUser() throws Exception {
        User user = new User();
        user.setRoleId(1);
        user.setPassword("password");
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
        when(dbManager.getUserByLogin("login")).thenReturn(user);

        String result = loginCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.COMMAND_LIST_FLIGHTS, result);
    }
}