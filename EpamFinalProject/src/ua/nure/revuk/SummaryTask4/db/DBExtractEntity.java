package ua.nure.revuk.SummaryTask4.db;

import ua.nure.revuk.SummaryTask4.db.bean.FlightUserTruckBean;
import ua.nure.revuk.SummaryTask4.db.bean.RequestUserBean;
import ua.nure.revuk.SummaryTask4.db.entity.Flight;
import ua.nure.revuk.SummaryTask4.db.entity.Truck;
import ua.nure.revuk.SummaryTask4.db.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 04.02.2016
 */
public class DBExtractEntity {

    private static String defaultPhotoUser = "user.jpg";
    private static String defaultPhotoTruck = "truck.jpg";

    /**
     * Extracts a user entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return User entity
     */
    public static User extractUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getInt(FieldsDB.ENTITY_ID));
        user.setLogin(rs.getString(FieldsDB.USER_LOGIN));
        user.setPassword(rs.getString(FieldsDB.USER_PASSWORD));
        user.setFirstName(rs.getString(FieldsDB.USER_FIRST_NAME));
        user.setLastName(rs.getString(FieldsDB.USER_LAST_NAME));
        String photoLinkUser = rs.getString(FieldsDB.USER_PHOTO_LINK);
        if (photoLinkUser == null) {
            photoLinkUser = defaultPhotoUser;
        }
        user.setPhotoLink(photoLinkUser);
        user.setMail(rs.getString(FieldsDB.USER_MAIL));
        user.setRoleId(rs.getInt(FieldsDB.USER_ROLE_ID));
        user.setRoleName(rs.getInt(FieldsDB.USER_ROLE_ID));

        return user;
    }


    /**
     * Extracts a FlightUserTruckBean entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return FlightUserTruckBean
     */
    public static FlightUserTruckBean extractFlightUserTruckBean(ResultSet rs) throws SQLException {
        FlightUserTruckBean flightUserTruckBean = new FlightUserTruckBean();

        flightUserTruckBean.setId(rs.getInt(FieldsDB.ENTITY_ID));
        flightUserTruckBean.setBeanFlightNumber(rs.getInt(FieldsDB.FLIGHT_NUMBER));
        flightUserTruckBean.setBeanDateCreation(rs.getDate(FieldsDB.FLIGHT_DATE_CREATION));
        flightUserTruckBean.setBeanDateDeparture(rs.getDate(FieldsDB.FLIGHT_DATE_DEPARTURE));
        flightUserTruckBean.setBeanDestination(rs.getString(FieldsDB.FLIGHT_DESTINATION));
        flightUserTruckBean.setBeanDistance(rs.getDouble(FieldsDB.FLIGHT_DISTANCE));
        flightUserTruckBean.setBeanStatusName(rs.getInt(FieldsDB.FLIGHT_STATUS_ID));
        flightUserTruckBean.setBeanStatusId(rs.getInt(FieldsDB.FLIGHT_STATUS_ID));
        flightUserTruckBean.setBeanTruckId(rs.getInt(FieldsDB.FLIGHT_TRUCK_ID));
        flightUserTruckBean.setBeanDriverId(rs.getInt(FieldsDB.FLIGHT_DRIVER_ID));
        flightUserTruckBean.setBeanDispatcherIdCreate(rs.getInt(FieldsDB.FLIGHT_DISPATCHER_ID_CREATE));
        flightUserTruckBean.setBeanDispatcherIdApprove(rs.getInt(FieldsDB.FLIGHT_DISPATCHER_ID_APPROVE));

        flightUserTruckBean.setBeanDriverFirstName(rs.getString(FieldsDB.USER_FIRST_NAME));
        flightUserTruckBean.setBeanDriverLastName(rs.getString(FieldsDB.USER_LAST_NAME));
        String driverPhotoLink = rs.getString(FieldsDB.USER_PHOTO_LINK);
        if (driverPhotoLink == null) {
            driverPhotoLink = defaultPhotoUser;
        }
        flightUserTruckBean.setBeanDriverPhotoLink(driverPhotoLink);
        flightUserTruckBean.setBeanRoleUserId(rs.getInt(FieldsDB.USER_ROLE_ID));

        String truckPhotoLink = rs.getString(FieldsDB.TRUCK_PHOTO_LINK);
        if (truckPhotoLink == null) {
            truckPhotoLink = defaultPhotoTruck;
        }
        flightUserTruckBean.setBeanTruckPhotoLink(truckPhotoLink);

        // who created flight
        flightUserTruckBean.setBeanDispatcherCreateFirstName(rs.getString(
                FieldsDB.FLIGHT_USER_TRUCK_BEAN_DISPATCHER_CREATE_FIRST_NAME));
        flightUserTruckBean.setBeanDispatcherCreateLastName(rs.getString(
                FieldsDB.FLIGHT_USER_TRUCK_BEAN_DISPATCHER_CREATE_LAST_NAME));
        String dispatcherCreatePhotoLink = rs.getString(FieldsDB.FLIGHT_USER_TRUCK_BEAN_DISPATCHER_CREATE_PHOTO_LINK);
        if (dispatcherCreatePhotoLink == null) {
            dispatcherCreatePhotoLink = defaultPhotoUser;
        }
        flightUserTruckBean.setBeanDispatcherCreatePhotoLink(dispatcherCreatePhotoLink);
        flightUserTruckBean.setBeanDispatcherCreateRoleId(rs.getInt(
                FieldsDB.FLIGHT_USER_TRUCK_BEAN_DISPATCHER_CREATE_ROLE_ID));
        flightUserTruckBean.setBeanDispatcherCreateRoleName(rs.getInt(
                FieldsDB.FLIGHT_USER_TRUCK_BEAN_DISPATCHER_CREATE_ROLE_ID));

        // who approved flight
        flightUserTruckBean.setBeanDispatcherApproveFirstName(rs.getString(
                FieldsDB.FLIGHT_USER_TRUCK_BEAN_DISPATCHER_APPROVE_FIRST_NAME));
        flightUserTruckBean.setBeanDispatcherApproveLastName(rs.getString(
                FieldsDB.FLIGHT_USER_TRUCK_BEAN_DISPATCHER_APPROVE_LAST_NAME));
        String dispatcherApprovePhotoLink = rs.getString(FieldsDB.FLIGHT_USER_TRUCK_BEAN_DISPATCHER_APPROVE_PHOTO_LINK);
        if (dispatcherApprovePhotoLink == null) {
            dispatcherApprovePhotoLink = defaultPhotoUser;
        }
        flightUserTruckBean.setBeanDispatcherApprovePhotoLink(dispatcherApprovePhotoLink);

        int dispatcherApproveRoleId = rs.getInt(FieldsDB.FLIGHT_USER_TRUCK_BEAN_DISPATCHER_APPROVE_ROLE_ID);
        flightUserTruckBean.setBeanDispatcherApproveRoleId(dispatcherApproveRoleId);
        if (dispatcherApproveRoleId != 0) {
            flightUserTruckBean.setBeanDispatcherApproveRoleName(dispatcherApproveRoleId);
        }

        return flightUserTruckBean;
    }


    /**
     * Extracts a RequestUserBean entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return RequestUserBean
     */
    public static RequestUserBean extractRequestUserBean(ResultSet rs) throws SQLException {
        RequestUserBean requestUserBean = new RequestUserBean();

        requestUserBean.setId(rs.getInt(FieldsDB.ENTITY_ID));
        requestUserBean.setBeanFlightId(rs.getInt(FieldsDB.REQUEST_FLIGHT_ID));
        requestUserBean.setBeanCarrying(rs.getDouble(FieldsDB.REQUEST_CARRYING));
        requestUserBean.setBeanCapacity(rs.getDouble(FieldsDB.REQUEST_CAPACITY));
        requestUserBean.setBeanLength(rs.getDouble(FieldsDB.REQUEST_LENGTH));
        requestUserBean.setBeanLorryWithSides(rs.getBoolean(FieldsDB.REQUEST_LORRY_WITH_SIDES));
        requestUserBean.setBeanRefrigerator(rs.getBoolean(FieldsDB.REQUEST_REFRIGERATOR));
        requestUserBean.setBeanDriverId(rs.getInt(FieldsDB.REQUEST_DRIVER_ID));

        requestUserBean.setBeanDriverFirstName(rs.getString(FieldsDB.USER_FIRST_NAME));
        requestUserBean.setBeanDriverLastName(rs.getString(FieldsDB.USER_LAST_NAME));
        String driverPhotoLink = rs.getString(FieldsDB.USER_PHOTO_LINK);
        if (driverPhotoLink == null) {
            driverPhotoLink = defaultPhotoUser;
        }
        requestUserBean.setBeanDriverPhotoLink(driverPhotoLink);
        requestUserBean.setBeanRoleId(rs.getInt(FieldsDB.USER_ROLE_ID));

        requestUserBean.setBeanFlightNumber(rs.getInt(FieldsDB.FLIGHT_NUMBER));
        requestUserBean.setBeanDateDeparture(rs.getDate(FieldsDB.FLIGHT_DATE_DEPARTURE));
        requestUserBean.setBeanDestination(rs.getString(FieldsDB.FLIGHT_DESTINATION));

        return requestUserBean;
    }


    /**
     * Extracts a Truck entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return Truck entity
     */
    public static Truck extractTruck(ResultSet rs) throws SQLException {
        Truck truck = new Truck();

        truck.setId(rs.getInt(FieldsDB.ENTITY_ID));
        truck.setTruckName(rs.getString(FieldsDB.TRUCK_NAME));
        truck.setCarrying(rs.getDouble(FieldsDB.TRUCK_CARRYING));
        truck.setCapacity(rs.getDouble(FieldsDB.TRUCK_CAPACITY));
        truck.setLength(rs.getDouble(FieldsDB.TRUCK_LENGTH));
        truck.setLorryWithSides(rs.getBoolean(FieldsDB.TRUCK_LORRY_WITH_SIDES));
        truck.setRefrigerator(rs.getBoolean(FieldsDB.TRUCK_REFRIGERATOR));
        truck.setServiceable(rs.getBoolean(FieldsDB.TRUCK_SERVICEABLE));
        String truckPhotoLink = rs.getString(FieldsDB.TRUCK_PHOTO_LINK);
        if (truckPhotoLink == null) {
            truckPhotoLink = defaultPhotoTruck;
        }
        truck.setPhotoLink(truckPhotoLink);
        truck.setTruckCountFlights(rs.getInt(FieldsDB.TRUCK_TRUCK_COUNT_FLIGHTS));

        return truck;
    }

    /**
     * Extracts a FlightUserTruckBean entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return FlightUserTruckBean
     */
    public static FlightUserTruckBean extractFlightTruckBeanFromDriver(ResultSet rs) throws SQLException {
        FlightUserTruckBean flightUserTruckBeanFromDriver = new FlightUserTruckBean();

        flightUserTruckBeanFromDriver.setId(rs.getInt(FieldsDB.ENTITY_ID));
        flightUserTruckBeanFromDriver.setBeanFlightNumber(rs.getInt(FieldsDB.FLIGHT_NUMBER));
        flightUserTruckBeanFromDriver.setBeanDateCreation(rs.getDate(FieldsDB.FLIGHT_DATE_CREATION));
        flightUserTruckBeanFromDriver.setBeanDateDeparture(rs.getDate(FieldsDB.FLIGHT_DATE_DEPARTURE));
        flightUserTruckBeanFromDriver.setBeanDestination(rs.getString(FieldsDB.FLIGHT_DESTINATION));
        flightUserTruckBeanFromDriver.setBeanDistance(rs.getDouble(FieldsDB.FLIGHT_DISTANCE));
        flightUserTruckBeanFromDriver.setBeanStatusName(rs.getInt(FieldsDB.FLIGHT_STATUS_ID));
        flightUserTruckBeanFromDriver.setBeanStatusId(rs.getInt(FieldsDB.FLIGHT_STATUS_ID));
        flightUserTruckBeanFromDriver.setBeanTruckId(rs.getInt(FieldsDB.FLIGHT_TRUCK_ID));
        flightUserTruckBeanFromDriver.setBeanDriverId(rs.getInt(FieldsDB.FLIGHT_DRIVER_ID));

        String truckPhotoLink = rs.getString(FieldsDB.TRUCK_PHOTO_LINK);
        if (truckPhotoLink == null) {
            truckPhotoLink = defaultPhotoTruck;
        }
        flightUserTruckBeanFromDriver.setBeanTruckPhotoLink(truckPhotoLink);

        return flightUserTruckBeanFromDriver;
    }

    /**
     * Extracts a Flight entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return Flight entity
     */
    public static Flight extractFlight(ResultSet rs) throws SQLException {
        Flight flight = new Flight();

        flight.setId(rs.getInt(FieldsDB.ENTITY_ID));
        flight.setFlightNumber(rs.getInt(FieldsDB.FLIGHT_NUMBER));
        flight.setDateCreation(rs.getDate(FieldsDB.FLIGHT_DATE_CREATION));
        flight.setDateDeparture(rs.getDate(FieldsDB.FLIGHT_DATE_DEPARTURE));
        flight.setDestination(rs.getString(FieldsDB.FLIGHT_DESTINATION));
        flight.setDistance(rs.getDouble(FieldsDB.FLIGHT_DISTANCE));
        flight.setStatusName(rs.getInt(FieldsDB.FLIGHT_STATUS_ID));
        flight.setTruckId(rs.getInt(FieldsDB.FLIGHT_TRUCK_ID));
        flight.setDriverId(rs.getInt(FieldsDB.FLIGHT_DRIVER_ID));
        flight.setDispatcherIdCreate(rs.getInt(FieldsDB.FLIGHT_DISPATCHER_ID_CREATE));
        flight.setDispatcherIdApprove(rs.getInt(FieldsDB.FLIGHT_DISPATCHER_ID_APPROVE));

        return flight;
    }
}
