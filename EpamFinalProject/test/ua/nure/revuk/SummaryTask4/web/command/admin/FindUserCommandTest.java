package ua.nure.revuk.SummaryTask4.web.command.admin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;

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
public class FindUserCommandTest {

    @Mock
    private DBManager dbManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private FindUserCommand findUserCommand;

    @Before
    public void setUp() throws Exception {
        findUserCommand = new FindUserCommand(dbManager);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testExecuteNoParameters() throws Exception {
        String result = findUserCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.PAGE_FIND_USER, result);
    }

    @Test
    public void testExecuteIncorrectParameterUserId() throws Exception {
        when(request.getParameter("findUsers")).thenReturn("findUsers");
        when(request.getParameter("searchParameter")).thenReturn("searchParameter");
        String result = findUserCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.PAGE_LIST_USERS, result);
    }
}