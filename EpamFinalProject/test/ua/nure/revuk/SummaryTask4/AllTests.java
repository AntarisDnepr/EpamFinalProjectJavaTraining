package ua.nure.revuk.SummaryTask4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import ua.nure.revuk.SummaryTask4.db.RoleTest;
import ua.nure.revuk.SummaryTask4.db.StatusTest;
import ua.nure.revuk.SummaryTask4.db.bean.FlightUserTruckBeanTest;
import ua.nure.revuk.SummaryTask4.db.bean.RequestUserBeanTest;
import ua.nure.revuk.SummaryTask4.web.command.admin.*;
import ua.nure.revuk.SummaryTask4.web.command.common.*;
import ua.nure.revuk.SummaryTask4.web.command.dispatcher.ChangeFlightStatusCommandTest;
import ua.nure.revuk.SummaryTask4.web.command.dispatcher.DeleteRequestCommandTest;
import ua.nure.revuk.SummaryTask4.web.command.dispatcher.FlightCreateCommandTest;
import ua.nure.revuk.SummaryTask4.web.command.dispatcher.ListRequestsCommandTest;
import ua.nure.revuk.SummaryTask4.web.command.driver.DriverPersonalCommandTest;
import ua.nure.revuk.SummaryTask4.web.command.driver.FlightCompletedCommandTest;
import ua.nure.revuk.SummaryTask4.web.command.driver.RequestCreateCommandTest;


@RunWith(Suite.class)
@SuiteClasses({DeleteTruckCommandTest.class, DeleteUserCommandTest.class, EditTruckCommandTest.class,
        EditUserCommandTest.class, FindUserCommandTest.class, ListTrucksCommandTest.class, ListUsersCommandTest.class,
        RegistrationUserCommandTest.class, TruckAddCommandTest.class, ChangeLocaleCommandTest.class,
        ListFlightsCommandTest.class, LoginCommandTest.class, LogoutUserCommandTest.class, NoCommandTest.class,
        PageErrorCommandTest.class, SortFlightsCommandTest.class, SortFlightsTypesContainerTest.class,
        ViewSettingsCommandTest.class, ChangeFlightStatusCommandTest.class, DeleteRequestCommandTest.class,
        FlightCreateCommandTest.class, ListRequestsCommandTest.class, DriverPersonalCommandTest.class,
        FlightCompletedCommandTest.class, RequestCreateCommandTest.class, RoleTest.class, StatusTest.class,
        FlightUserTruckBeanTest.class, RequestUserBeanTest.class})
public class AllTests {

}