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
public class ListUsersCommandTest {

    @Mock
    private DBManager dbManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private ListUsersCommand listUsersCommand;

    @Before
    public void setUp() throws Exception {
        listUsersCommand = new ListUsersCommand(dbManager);
        when(request.getSession()).thenReturn(session);
    }


    @Test
    public void testExecuteCheckDBManagerUpdateTruckById() throws Exception {
        listUsersCommand.execute(request, response);

        // check the method call
        verify(dbManager).getAllUsers();
    }

    @Test
    public void testExecuteCorrectForward() throws Exception {
        String result = listUsersCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.PAGE_LIST_USERS, result);
    }
}