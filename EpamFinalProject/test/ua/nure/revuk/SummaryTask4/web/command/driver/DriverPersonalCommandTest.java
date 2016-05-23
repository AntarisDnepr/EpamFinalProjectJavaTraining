package ua.nure.revuk.SummaryTask4.web.command.driver;

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
public class DriverPersonalCommandTest {

    @Mock
    private DBManager dbManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private DriverPersonalCommand driverPersonalCommand;
    private User user;

    @Before
    public void setUp() throws Exception {
        driverPersonalCommand = new DriverPersonalCommand(dbManager);
        when(request.getSession()).thenReturn(session);
        user = new User();
        user.setId(1);
    }

    @Test(expected = AppException.class)
    public void testExecuteNoParameter() throws Exception {
        driverPersonalCommand.execute(request, response);
    }

    @Test
    public void testExecuteDriverPersonal() throws Exception {
        when(session.getAttribute("user")).thenReturn(user);

        String result = driverPersonalCommand.execute(request, response);

        // check Correct Forward
        assertEquals(Path.PAGE_DRIVER_PERSONAL, result);
    }
}