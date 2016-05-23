package ua.nure.revuk.SummaryTask4.web.command.dispatcher;

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
public class ListRequestsCommandTest {

    @Mock
    private DBManager dbManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private ListRequestsCommand listRequestsCommand;

    @Before
    public void setUp() throws Exception {
        listRequestsCommand = new ListRequestsCommand(dbManager);
        when(request.getSession()).thenReturn(session);
    }


    @Test
    public void testExecuteCheckDBManagerUpdateTruckById() throws Exception {
        listRequestsCommand.execute(request, response);

        // check the method call
        verify(dbManager).getAllRequests();
    }

    @Test
    public void testExecuteCorrectForward() throws Exception {
        String result = listRequestsCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.PAGE_LIST_REQUESTS, result);
    }
}