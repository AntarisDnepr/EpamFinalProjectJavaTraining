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
public class RegistrationUserCommandTest {

    @Mock
    private DBManager dbManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private RegistrationUserCommand registrationUserCommand;

    @Before
    public void setUp() throws Exception {
        registrationUserCommand = new RegistrationUserCommand(dbManager);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testExecuteNoParameters() throws Exception {
        String result = registrationUserCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.PAGE_REGISTRATION_USER, result);
    }

    @Test(expected = AppException.class)
    public void testExecuteInsertToBDNoValidationParametersUser() throws Exception {
        when(request.getParameter("insertToBD")).thenReturn("insertToBD");
        registrationUserCommand.execute(request, response);
    }

    @Test
    public void testExecuteCheckInsertUserInBD() throws Exception {

        when(request.getParameter("insertToBD")).thenReturn("insertToBD");
        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("test");
        when(request.getParameter("firstName")).thenReturn("test");
        when(request.getParameter("lastName")).thenReturn("test");
        when(request.getParameter("mail")).thenReturn("mail@test.com");
        when(request.getParameter("roleId")).thenReturn("1");

        String result = registrationUserCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.COMMAND_LIST_USERS, result);
    }
}