package ua.nure.revuk.SummaryTask4.db.bean;

import org.junit.Test;
import ua.nure.revuk.SummaryTask4.db.Status;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 23.02.2016
 */
public class FlightUserTruckBeanTest {

    FlightUserTruckBean flightUserTruckBean = new FlightUserTruckBean();
    Date date = new Date();

    @Test
    public void testFlightUserTruckBeanGetSetId() throws Exception {
        flightUserTruckBean.setId(1);
        assertTrue(flightUserTruckBean.getId() == 1);
    }

    @Test
    public void testFlightUserTruckBeanGetSetFlightNumber() throws Exception {
        flightUserTruckBean.setBeanFlightNumber(1);
        assertTrue(flightUserTruckBean.getBeanFlightNumber() == 1);
    }

    @Test
    public void testFlightUserTruckBeanGetSetDistance() throws Exception {
        flightUserTruckBean.setBeanDistance(1.1);
        assertTrue(flightUserTruckBean.getBeanDistance() == 1.1);
    }

    @Test
    public void testFlightUserTruckBeanGetSetStatusName() throws Exception {
        flightUserTruckBean.setBeanStatusName(1);
        assertEquals(flightUserTruckBean.getBeanStatusName(), Status.OPEN);
    }

    @Test
    public void testFlightUserTruckBeanGetSetDestination() throws Exception {
        flightUserTruckBean.setBeanDestination("Destination");
        assertEquals(flightUserTruckBean.getBeanDestination(), "Destination");
    }

    @Test
    public void testFlightUserTruckBeanGetSetStatusId() throws Exception {
        flightUserTruckBean.setBeanStatusId(2);
        assertTrue(flightUserTruckBean.getBeanStatusId() == 2);
    }

    @Test
    public void testFlightUserTruckBeanGetSetTruckId() throws Exception {
        flightUserTruckBean.setBeanTruckId(3);
        assertTrue(flightUserTruckBean.getBeanTruckId() == 3);
    }

    @Test
    public void testFlightUserTruckBeanGetSetDriverId() throws Exception {
        flightUserTruckBean.setBeanDriverId(4);
        assertTrue(flightUserTruckBean.getBeanDriverId() == 4);
    }

    @Test
    public void testFlightUserTruckBeanGetSetDispatcherIdCreate() throws Exception {
        flightUserTruckBean.setBeanDispatcherIdCreate(5);
        assertTrue(flightUserTruckBean.getBeanDispatcherIdCreate() == 5);
    }

    @Test
    public void testFlightUserTruckBeanGetSetDispatcherIdApprove() throws Exception {
        flightUserTruckBean.setBeanDispatcherIdApprove(6);
        assertTrue(flightUserTruckBean.getBeanDispatcherIdApprove() == 6);
    }

    @Test
    public void testFlightUserTruckBeanGetSetDriverFirstName() throws Exception {
        flightUserTruckBean.setBeanDriverFirstName("DriverFirstName");
        assertEquals(flightUserTruckBean.getBeanDriverFirstName(), "DriverFirstName");
    }

    @Test
    public void testFlightUserTruckBeanGetSetDriverLastName() throws Exception {
        flightUserTruckBean.setBeanDriverLastName("LastName");
        assertEquals(flightUserTruckBean.getBeanDriverLastName(), "LastName");
    }

    @Test
    public void testFlightUserTruckBeanGetSetDriverPhotoLink() throws Exception {
        flightUserTruckBean.setBeanDriverPhotoLink("PhotoLink");
        assertEquals(flightUserTruckBean.getBeanDriverPhotoLink(), "PhotoLink");
    }

    @Test
    public void testFlightUserTruckBeanGetSetRoleUserId() throws Exception {
        flightUserTruckBean.setBeanRoleUserId(7);
        assertTrue(flightUserTruckBean.getBeanRoleUserId() == 7);
    }

    @Test
    public void testFlightUserTruckBeanGetSetDriverTruckPhotoLink() throws Exception {
        flightUserTruckBean.setBeanTruckPhotoLink("TruckPhotoLink");
        assertEquals(flightUserTruckBean.getBeanTruckPhotoLink(), "TruckPhotoLink");
    }

    @Test
    public void testFlightUserTruckBeanToString() throws Exception {
        assertNotNull(flightUserTruckBean.toString());
    }
}