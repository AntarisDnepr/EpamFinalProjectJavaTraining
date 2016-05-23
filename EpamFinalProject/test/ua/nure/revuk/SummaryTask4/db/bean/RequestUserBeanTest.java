package ua.nure.revuk.SummaryTask4.db.bean;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 23.02.2016
 */
public class RequestUserBeanTest {
    RequestUserBean requestUserBean = new RequestUserBean();
    Date date = new Date();

    @Test
    public void testRequestUserBeanToString() throws Exception {
        assertNotNull(requestUserBean.toString());
    }

    @Test
    public void testRequestUserBeanGetSetId() throws Exception {
        requestUserBean.setId(1);
        assertTrue(requestUserBean.getId() == 1);
    }

    @Test
    public void testRequestUserBeanGetSetFlightId() throws Exception {
        requestUserBean.setBeanFlightId(1);
        assertTrue(requestUserBean.getBeanFlightId() == 1);
    }

    @Test
    public void testRequestUserBeanGetSetCarrying() throws Exception {
        requestUserBean.setBeanCarrying(1.1);
        assertTrue(requestUserBean.getBeanCarrying() == 1.1);
    }

    @Test
    public void testRequestUserBeanGetSetCapacity() throws Exception {
        requestUserBean.setBeanCapacity(1.2);
        assertTrue(requestUserBean.getBeanCapacity() == 1.2);
    }

    @Test
    public void testRequestUserBeanGetSetLength() throws Exception {
        requestUserBean.setBeanLength(1.3);
        assertTrue(requestUserBean.getBeanLength() == 1.3);
    }

    @Test
    public void testRequestUserBeanLorryWithSides() throws Exception {
        requestUserBean.setBeanLorryWithSides(false);
        assertFalse(requestUserBean.isBeanLorryWithSides());
    }

    @Test
    public void testRequestUserBeanRefrigerator() throws Exception {
        requestUserBean.setBeanRefrigerator(false);
        assertFalse(requestUserBean.isBeanRefrigerator());
    }

    @Test
    public void testRequestUserBeanGetSetDriverId() throws Exception {
        requestUserBean.setBeanDriverId(2);
        assertTrue(requestUserBean.getBeanDriverId() == 2);
    }

    @Test
    public void testRequestUserBeanGetSetDriverFirstName() throws Exception {
        requestUserBean.setBeanDriverFirstName("DriverFirstName");
        assertEquals(requestUserBean.getBeanDriverFirstName(), "DriverFirstName");
    }

    @Test
    public void testRequestUserBeanGetSetDriverLastName() throws Exception {
        requestUserBean.setBeanDriverLastName("LastName");
        assertEquals(requestUserBean.getBeanDriverLastName(), "LastName");
    }

    @Test
    public void testRequestUserBeanGetSetDriverPhotoLink() throws Exception {
        requestUserBean.setBeanDriverPhotoLink("PhotoLink");
        assertEquals(requestUserBean.getBeanDriverPhotoLink(), "PhotoLink");
    }

    @Test
    public void testRequestUserBeanGetSetRoleId() throws Exception {
        requestUserBean.setBeanRoleId(3);
        assertTrue(requestUserBean.getBeanRoleId() == 3);
    }

    @Test
    public void testRequestUserBeanGetSetFlightNumber() throws Exception {
        requestUserBean.setBeanFlightNumber(4);
        assertTrue(requestUserBean.getBeanFlightNumber() == 4);
    }

    @Test
    public void testRequestUserBeanGetSetDestination() throws Exception {
        requestUserBean.setBeanDestination("Destination");
        assertEquals(requestUserBean.getBeanDestination(), "Destination");
    }
}