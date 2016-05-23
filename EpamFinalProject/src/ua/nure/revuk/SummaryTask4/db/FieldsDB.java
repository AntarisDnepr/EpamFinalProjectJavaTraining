package ua.nure.revuk.SummaryTask4.db;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 02.02.2016
 * <p>
 * Holder for fields names of DB tables and beans.
 */
public final class FieldsDB {

    // entities
    public static final String ENTITY_ID = "id";

    public static final String USER_LOGIN = "user_login";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_FIRST_NAME = "user_first_name";
    public static final String USER_LAST_NAME = "user_last_name";
    public static final String USER_PHOTO_LINK = "user_photo_link";
    public static final String USER_MAIL = "user_mail";
    public static final String USER_ROLE_ID = "user_role_id";

    public static final String FLIGHT_NUMBER = "flight_number";
    public static final String FLIGHT_DATE_CREATION = "flight_date_creation";
    public static final String FLIGHT_DATE_DEPARTURE = "flight_date_departure";
    public static final String FLIGHT_DESTINATION = "flight_destination";
    public static final String FLIGHT_DISTANCE = "flight_distance";
    public static final String FLIGHT_STATUS_ID = "flight_status_id";
    public static final String FLIGHT_TRUCK_ID = "flight_truck_id";
    public static final String FLIGHT_DRIVER_ID = "flight_driver_id";
    public static final String FLIGHT_DISPATCHER_ID_CREATE = "flight_dispatcher_id_create";
    public static final String FLIGHT_DISPATCHER_ID_APPROVE = "flight_dispatcher_id_approve";

    public static final String REQUEST_FLIGHT_ID = "request_flight_id";
    public static final String REQUEST_CARRYING = "request_carrying";
    public static final String REQUEST_CAPACITY = "request_capacity";
    public static final String REQUEST_LENGTH = "request_length";
    public static final String REQUEST_LORRY_WITH_SIDES = "request_lorry_with_sides";
    public static final String REQUEST_REFRIGERATOR = "request_refrigerator";
    public static final String REQUEST_DRIVER_ID = "request_driver_id";

    public static final String TRUCK_NAME = "truck_name";
    public static final String TRUCK_CARRYING = "truck_carrying";
    public static final String TRUCK_CAPACITY = "truck_capacity";
    public static final String TRUCK_LENGTH = "truck_length";
    public static final String TRUCK_LORRY_WITH_SIDES = "truck_lorry_with_sides";
    public static final String TRUCK_REFRIGERATOR = "truck_refrigerator";
    public static final String TRUCK_SERVICEABLE = "truck_serviceable";
    public static final String TRUCK_PHOTO_LINK = "truck_photo_link";
    public static final String TRUCK_TRUCK_COUNT_FLIGHTS = "truck_count_flights";

    public static final String FLIGHT_USER_TRUCK_BEAN_DISPATCHER_CREATE_FIRST_NAME = "dispatcher_create_first_name";
    public static final String FLIGHT_USER_TRUCK_BEAN_DISPATCHER_CREATE_LAST_NAME = "dispatcher_create_last_name";
    public static final String FLIGHT_USER_TRUCK_BEAN_DISPATCHER_CREATE_PHOTO_LINK = "dispatcher_create_photo";
    public static final String FLIGHT_USER_TRUCK_BEAN_DISPATCHER_CREATE_ROLE_ID = "dispatcher_create_id";

    public static final String FLIGHT_USER_TRUCK_BEAN_DISPATCHER_APPROVE_FIRST_NAME = "dispatcher_approve_first_name";
    public static final String FLIGHT_USER_TRUCK_BEAN_DISPATCHER_APPROVE_LAST_NAME = "dispatcher_approve_last_name";
    public static final String FLIGHT_USER_TRUCK_BEAN_DISPATCHER_APPROVE_PHOTO_LINK = "dispatcher_approve_photo";
    public static final String FLIGHT_USER_TRUCK_BEAN_DISPATCHER_APPROVE_ROLE_ID = "dispatcher_approve_id";



}