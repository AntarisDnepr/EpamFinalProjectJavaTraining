package ua.nure.revuk.SummaryTask4.db;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.db.bean.FlightUserTruckBean;
import ua.nure.revuk.SummaryTask4.db.bean.RequestUserBean;
import ua.nure.revuk.SummaryTask4.db.entity.Flight;
import ua.nure.revuk.SummaryTask4.db.entity.Request;
import ua.nure.revuk.SummaryTask4.db.entity.Truck;
import ua.nure.revuk.SummaryTask4.db.entity.User;
import ua.nure.revuk.SummaryTask4.db.util.JdbcUtil;
import ua.nure.revuk.SummaryTask4.exception.DBException;
import ua.nure.revuk.SummaryTask4.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Epam Final Project Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 02.02.2016
 */
public class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);

    // //////////////////////////////////////////////////////////
    // singleton
    // //////////////////////////////////////////////////////////

    private static DBManager instance;

    public static synchronized DBManager getInstance() throws DBException {
        if (instance == null) {
            instance = new DBManager();
            LOG.info("Initialization instance DBManager");
        }
        return instance;
    }

    private DBManager() throws DBException {
        try {
            Context initContext = new InitialContext();
            LOG.info("Initialization context DBManager");
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/epam");

        } catch (NamingException ex) {
            LOG.error("Cannot obtain the data source", ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    private DataSource ds;

    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////

    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE user_login = ?";

    private static final String SQL_FIND_ALL_USER = "SELECT * FROM users";

    private static final String SQL_FIND_ALL_USER_BY_PARAMETERS = "SELECT * FROM users WHERE user_login LIKE ? " +
            "OR user_first_name LIKE ? OR user_last_name LIKE ? OR user_mail LIKE ?";

    private static final String SQL_INSERT_USER = "INSERT INTO users (user_login, user_password, user_first_name, " +
            "user_last_name, user_photo_link, user_mail, user_role_id) VALUES (?,?,?,?,?,?,?)";

    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id=?";

    private static final String SQL_UPDATE_USER_BY_ID = "UPDATE users SET " +
            "user_login = ?, user_password = ?, user_first_name = ?, user_last_name = ?, user_photo_link = ?, " +
            "user_mail = ?, user_role_id = ? WHERE id = ?";

    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id = ?";

    private static final String SQL_FIND_ALL_EMAILS = "SELECT user_mail FROM users WHERE user_mail IS NOT NULL " +
            "AND user_role_id = 2 GROUP BY user_mail HAVING COUNT(*)>0";

    private static final String SQL_FIND_ALL_FLIGHT = "SELECT f.*, u.user_first_name, u.user_last_name, " +
            "u.user_photo_link, u.user_role_id, t.truck_photo_link, u2.user_first_name AS dispatcher_create_first_name, " +
            "u2.user_last_name AS dispatcher_create_last_name, u2.user_photo_link AS dispatcher_create_photo, " +
            "u2.user_role_id AS dispatcher_create_id, u3.user_first_name AS dispatcher_approve_first_name, " +
            "u3.user_last_name AS dispatcher_approve_last_name, u3.user_photo_link AS dispatcher_approve_photo, " +
            "u3.user_role_id AS dispatcher_approve_id FROM flights f LEFT JOIN users u ON f.flight_driver_id = u.id " +
            "LEFT JOIN trucks t ON t.id = f.flight_truck_id LEFT JOIN users u2 ON f.flight_dispatcher_id_create = u2.id " +
            "LEFT JOIN users u3 ON f.flight_dispatcher_id_approve = u3.id ORDER BY flight_date_departure";

    private static final String SQL_FIND_ALL_FLIGHT_FROM_DRIVER = "SELECT * FROM flights WHERE id NOT IN (" +
            "SELECT flights_users_flight_id FROM flights_users WHERE  flights_users_driver_id = ?) " +
            "AND flight_status_id = 1 ORDER BY flight_date_departure";

    private static final String SQL_FIND_ALL_FLIGHT_FROM_DRIVER_ID = "SELECT f.*, t.truck_photo_link  FROM flights f " +
            "LEFT JOIN trucks t ON t.id = f.flight_truck_id WHERE  f.flight_driver_id = ? AND flight_status_id = 2 " +
            "ORDER BY flight_date_departure";

    private static final String SQL_INSERT_FLIGHT = "INSERT INTO flights (flight_number, flight_date_creation, " +
            "flight_date_departure, flight_destination, flight_distance, flight_status_id, flight_dispatcher_id_create)" +
            " VALUES (?,?,?,?,?,?,?)";

    private static final String SQL_UPDATE_FLIGHT_STATUS = "UPDATE flights SET flight_status_id = ? WHERE id = ?";

    private static final String SQL_UPDATE_FLIGHT_APPROVE_REQUEST = "UPDATE flights SET flight_driver_id = ?, " +
            "flight_truck_id = ?, flight_status_id = 2, flight_dispatcher_id_approve = ? WHERE id = ?";

    private static final String SQL_DELETE_FLIGHT = "DELETE FROM flights WHERE id=?";

    private static final String SQL_DELETE_FLIGHT_IN_FLIGHTS_USERS_TABLE =
            "DELETE FROM flights_users WHERE flights_users_flight_id=?";

    private static final String SQL_DELETE_FLIGHT_IN_REQUESTS_TABLE = "DELETE FROM requests WHERE request_flight_id=?";

    private static final String SQL_DELETE_FLIGHTS_USERS_APPROVE_REQUEST = "DELETE FROM flights_users WHERE " +
            "flights_users_flight_id = ?";

    private static final String SQL_DELETE_FLIGHTS_USERS_REQUEST = "DELETE FROM flights_users WHERE " +
            "flights_users_flight_id = ? AND flights_users_driver_id = ?";

    private static final String SQL_DELETE_REQUEST = "DELETE FROM requests WHERE id=?";

    private static final String SQL_DELETE_REQUEST_BY_DRIVER = "DELETE FROM requests WHERE id=? AND request_driver_id=?";

    private static final String SQL_DELETE_ALL_REQUEST_BY_FLIGHT_ID = "DELETE FROM requests WHERE request_flight_id=?";

    private static final String SQL_INSERT_REQUEST = "INSERT INTO requests (request_flight_id, request_carrying, " +
            "request_capacity, request_length, request_lorry_with_sides, request_refrigerator, request_driver_id) " +
            "VALUES (?,?,?,?,?,?,?)";

    private static final String SQL_INSERT_REQUEST_IN_FLIGHTS_USERS = "INSERT INTO flights_users " +
            "(flights_users_flight_id, flights_users_driver_id) VALUES (?,?)";

    private static final String SQL_FIND_ALL_REQUEST = "SELECT r.*, u.user_first_name, u.user_last_name, " +
            "u.user_photo_link, u.user_role_id, f.flight_number, flight_date_departure, flight_destination " +
            "FROM requests r, users u, flights f WHERE u.id = r.request_driver_id AND f.id = r.request_flight_id " +
            "ORDER BY flight_date_departure";

    private static final String SQL_FIND_ALL_REQUEST_FROM_DRIVER = "SELECT r.*, u.user_first_name, u.user_last_name, " +
            "u.user_photo_link, u.user_role_id, f.flight_number, flight_date_departure, flight_destination " +
            "FROM requests r, users u, flights f WHERE u.id = r.request_driver_id AND f.id = r.request_flight_id  " +
            "AND r.request_driver_id = ? ORDER BY flight_date_departure";

    private static final String SQL_INSERT_TRUCK = "INSERT INTO trucks (truck_name, truck_carrying, truck_capacity, " +
            "truck_length, truck_lorry_with_sides, truck_refrigerator, truck_serviceable, truck_photo_link, truck_count_flights) " +
            "VALUES (?,?,?,?,?,?,?,?,0)";

    private static final String SQL_FIND_ALL_TRUCKS = "SELECT * FROM trucks";

    private static final String SQL_FIND_ALL_NOT_SERVICEABLE_TRUCKS = "SELECT * FROM trucks WHERE truck_serviceable = FALSE";

    private static final String SQL_FIND_ALL_REFRIGERATOR_TRUCKS = "SELECT * FROM trucks WHERE truck_refrigerator = TRUE";

    private static final String SQL_FIND_ALL_LORRY_WITH_SIDES_TRUCKS = "SELECT * FROM trucks WHERE truck_lorry_with_sides = TRUE";

    private static final String SQL_FIND_TRUCK_BY_ID = "SELECT * FROM trucks WHERE id = ?";

    private static final String SQL_FIND_ALL_TRUCKS_WITH_PARAMETERS = "SELECT * FROM trucks WHERE truck_carrying >= ? " +
            "AND truck_capacity >= ? AND truck_length >= ? AND truck_lorry_with_sides = ? AND truck_refrigerator = ? " +
            "AND truck_serviceable = 'true'";

    private static final String SQL_UPDATE_TRUCK_STATUS_SERVICEABLE = "UPDATE trucks SET truck_serviceable = ? WHERE id = ?";

    private static final String SQL_UPDATE_TRUCK_FLIGHTS = "UPDATE trucks SET truck_count_flights = " +
            "truck_count_flights + 1 WHERE id = ?";

    private static final String SQL_DELETE_TRUCK = "DELETE FROM trucks WHERE id=?";

    private static final String SQL_UPDATE_TRUCK_BY_ID = "UPDATE trucks SET " +
            "truck_name = ?, truck_carrying = ?, truck_capacity = ?, truck_length = ?, truck_lorry_with_sides = ?, " +
            "truck_refrigerator = ?, truck_serviceable = ?, truck_photo_link = ? WHERE id = ?";


    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in
     * your WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return DB connection.
     */
    public Connection getConnection() throws DBException {
        Connection con;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return con;
    }

    public List<User> getAllUsers() throws DBException {
        List<User> users = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_USER);
            while (rs.next()) {
                users.add(DBExtractEntity.extractUser(rs));
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USERS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USERS, ex);
        } finally {
            JdbcUtil.close(con, stmt, rs);
        }
        return users;
    }

    public List<User> getUsersByParameters(String searchParameter) throws DBException {
        List<User> users = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ALL_USER_BY_PARAMETERS);
            int count = 1;

            pstmt.setString(count++, searchParameter);
            pstmt.setString(count++, searchParameter);
            pstmt.setString(count++, searchParameter);
            pstmt.setString(count++, searchParameter);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                users.add(DBExtractEntity.extractUser(rs));
            }

        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USERS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USERS, ex);
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return users;
    }

    public User getUserByLogin(String login) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user = DBExtractEntity.extractUser(rs);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return user;
    }

    public int insertUser(User user) throws DBException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(SQL_INSERT_USER);
            int count = 1;

            preparedStatement.setString(count++, user.getLogin());
            preparedStatement.setString(count++, user.getPassword());
            preparedStatement.setString(count++, user.getFirstName());
            preparedStatement.setString(count++, user.getLastName());
            preparedStatement.setString(count++, user.getPhotoLink());
            preparedStatement.setString(count++, user.getMail());
            preparedStatement.setInt(count++, user.getRoleId());

            preparedStatement.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            JdbcUtil.rollback(con);
            LOG.error(Messages.ERR_CANNOT_INSERT_USER, ex);
            throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
        } finally {
            JdbcUtil.closeQuietly(preparedStatement);
            JdbcUtil.closeQuietly(con);
        }
        return 1;
    }

    public int deleteUser(Integer userId) throws DBException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = getConnection();

            preparedStatement = con.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            JdbcUtil.rollback(con);
            LOG.error(Messages.ERR_CANNOT_DELETE_USER, ex);
            throw new DBException(Messages.ERR_CANNOT_DELETE_USER, ex);
        } finally {
            JdbcUtil.closeQuietly(preparedStatement);
            JdbcUtil.closeQuietly(con);
        }
        return 1;
    }

    public int updateUserById(User user) throws DBException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(SQL_UPDATE_USER_BY_ID);
            int count = 1;

            preparedStatement.setString(count++, user.getLogin());
            preparedStatement.setString(count++, user.getPassword());
            preparedStatement.setString(count++, user.getFirstName());
            preparedStatement.setString(count++, user.getLastName());
            preparedStatement.setString(count++, user.getPhotoLink());
            preparedStatement.setString(count++, user.getMail());
            preparedStatement.setInt(count++, user.getRoleId());
            preparedStatement.setInt(count++, user.getId());

            preparedStatement.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            JdbcUtil.rollback(con);
            LOG.error(Messages.ERR_CANNOT_UPDATE_USER, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
        } finally {
            JdbcUtil.closeQuietly(preparedStatement);
            JdbcUtil.closeQuietly(con);
        }
        return 1;
    }

    public User getUserById(Integer userId) throws DBException {
        User user = new User();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user = DBExtractEntity.extractUser(rs);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_FIND_USER_BY_ID, ex);
            throw new DBException(Messages.ERR_CANNOT_FIND_USER_BY_ID, ex);
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return user;
    }

    public List<String> getAllEmails() throws DBException {
        List<String> emails = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_EMAILS);
            while (rs.next()) {
                emails.add(rs.getString(FieldsDB.USER_MAIL));
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_FIND_ALL_EMAILS, ex);
            throw new DBException(Messages.ERR_CANNOT_FIND_ALL_EMAILS, ex);
        } finally {
            JdbcUtil.close(con, stmt, rs);
        }
        return emails;
    }

    public List<FlightUserTruckBean> getAllFlight() throws DBException {
        List<FlightUserTruckBean> flightUserTruckBean = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_FLIGHT);
            while (rs.next()) {
                flightUserTruckBean.add(DBExtractEntity.extractFlightUserTruckBean(rs));
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_FLIGHT, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_FLIGHT, ex);
        } finally {
            JdbcUtil.close(con, stmt, rs);
        }
        return flightUserTruckBean;
    }

    public List<Flight> getAllFlightsFromDriver(Integer driverId) throws DBException {
        List<Flight> flights = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ALL_FLIGHT_FROM_DRIVER);
            pstmt.setDouble(1, driverId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                flights.add(DBExtractEntity.extractFlight(rs));
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_FLIGHT, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_FLIGHT, ex);
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return flights;
    }

    public List<FlightUserTruckBean> getAllFlightFromDriverId(Integer userId) throws DBException {
        List<FlightUserTruckBean> allFlightFromDriverId = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            stmt = con.prepareStatement(SQL_FIND_ALL_FLIGHT_FROM_DRIVER_ID);

            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                allFlightFromDriverId.add(DBExtractEntity.extractFlightTruckBeanFromDriver(rs));
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_FLIGHT_FROM_DRIVER, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_FLIGHT_FROM_DRIVER, ex);
        } finally {
            JdbcUtil.close(con, stmt, rs);
        }
        return allFlightFromDriverId;
    }

    public int insertFlight(Flight flight, Integer dispatcherId) throws DBException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(SQL_INSERT_FLIGHT);
            int count = 1;

            preparedStatement.setInt(count++, flight.getFlightNumber());
            preparedStatement.setDate(count++, Date.valueOf(flight.getDateCreation()));
            preparedStatement.setDate(count++, Date.valueOf(flight.getDateDeparture()));
            preparedStatement.setString(count++, flight.getDestination());
            preparedStatement.setDouble(count++, flight.getDistance());
            preparedStatement.setInt(count++, 1);
            preparedStatement.setInt(count++, dispatcherId);

            preparedStatement.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            JdbcUtil.rollback(con);
            LOG.error(Messages.ERR_CANNOT_INSERT_FLIGHT, ex);
            throw new DBException(Messages.ERR_CANNOT_INSERT_FLIGHT, ex);
        } finally {
            JdbcUtil.closeQuietly(preparedStatement);
            JdbcUtil.closeQuietly(con);
        }
        return 1;
    }

    public int updateFlightStatus(Integer flightId, Integer flightStatusId) throws DBException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(SQL_UPDATE_FLIGHT_STATUS);
            int count = 1;

            preparedStatement.setInt(count++, flightStatusId);
            preparedStatement.setInt(count++, flightId);

            preparedStatement.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            JdbcUtil.rollback(con);
            LOG.error(Messages.ERR_CANNOT_UPDATE_FLIGHT_STATUS, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT_STATUS, ex);
        } finally {
            JdbcUtil.closeQuietly(preparedStatement);
            JdbcUtil.closeQuietly(con);
        }
        return 1;
    }

    public int updateFlightCompleted(Integer flightId, Integer truckId, Boolean isTruckServiceable) throws DBException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(SQL_UPDATE_FLIGHT_STATUS);
            preparedStatement.setInt(1, 3);
            preparedStatement.setInt(2, flightId);
            preparedStatement.executeUpdate();

            preparedStatement = con.prepareStatement(SQL_UPDATE_TRUCK_STATUS_SERVICEABLE);
            preparedStatement.setBoolean(1, isTruckServiceable);
            preparedStatement.setInt(2, truckId);
            preparedStatement.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            JdbcUtil.rollback(con);
            LOG.error(Messages.ERR_CANNOT_UPDATE_FLIGHT, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT, ex);
        } finally {
            JdbcUtil.closeQuietly(preparedStatement);
            JdbcUtil.closeQuietly(con);
        }
        return 1;
    }

    public int updateFlightApproveRequest(Integer flightId, Integer flightDriverId, Integer flightTruckId,
                                          Integer dispatcherId) throws DBException {
        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = getConnection();

            preparedStatement = con.prepareStatement(SQL_UPDATE_TRUCK_FLIGHTS);
            preparedStatement.setInt(1, flightTruckId);
            preparedStatement.executeUpdate();

            preparedStatement = con.prepareStatement(SQL_DELETE_ALL_REQUEST_BY_FLIGHT_ID);
            preparedStatement.setInt(1, flightId);
            preparedStatement.executeUpdate();

            preparedStatement = con.prepareStatement(SQL_DELETE_FLIGHTS_USERS_APPROVE_REQUEST);
            preparedStatement.setInt(1, flightId);
            preparedStatement.executeUpdate();

            preparedStatement = con.prepareStatement(SQL_UPDATE_FLIGHT_APPROVE_REQUEST);
            int count = 1;
            preparedStatement.setInt(count++, flightDriverId);
            preparedStatement.setInt(count++, flightTruckId);
            preparedStatement.setInt(count++, dispatcherId);
            preparedStatement.setInt(count++, flightId);
            preparedStatement.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            JdbcUtil.rollback(con);
            LOG.error(Messages.ERR_CANNOT_UPDATE_FLIGHT_APPROVE_REQUEST, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT_APPROVE_REQUEST, ex);
        } finally {
            JdbcUtil.closeQuietly(preparedStatement);
            JdbcUtil.closeQuietly(con);
        }
        return 1;
    }

    public int deleteFlight(Integer flightId) throws DBException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = getConnection();

            preparedStatement = con.prepareStatement(SQL_DELETE_FLIGHT_IN_REQUESTS_TABLE);
            preparedStatement.setInt(1, flightId);
            preparedStatement.executeUpdate();

            preparedStatement = con.prepareStatement(SQL_DELETE_FLIGHT_IN_FLIGHTS_USERS_TABLE);
            preparedStatement.setInt(1, flightId);
            preparedStatement.executeUpdate();

            preparedStatement = con.prepareStatement(SQL_DELETE_FLIGHT);
            preparedStatement.setInt(1, flightId);
            preparedStatement.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            JdbcUtil.rollback(con);
            LOG.error(Messages.ERR_CANNOT_DELETE_TRUCK, ex);
            throw new DBException(Messages.ERR_CANNOT_DELETE_TRUCK, ex);
        } finally {
            JdbcUtil.closeQuietly(preparedStatement);
            JdbcUtil.closeQuietly(con);
        }
        return 1;
    }

    public List<RequestUserBean> getAllRequests() throws DBException {
        List<RequestUserBean> requests = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_REQUEST);
            while (rs.next()) {
                requests.add(DBExtractEntity.extractRequestUserBean(rs));
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_REQUESTS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_REQUESTS, ex);
        } finally {
            JdbcUtil.close(con, stmt, rs);
        }
        return requests;
    }

    public List<RequestUserBean> getAllRequestsFromDriver(Integer driverId) throws DBException {
        List<RequestUserBean> requests = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ALL_REQUEST_FROM_DRIVER);
            pstmt.setInt(1, driverId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                requests.add(DBExtractEntity.extractRequestUserBean(rs));
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_REQUESTS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_REQUESTS, ex);
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return requests;
    }

    public int insertRequest(Request request) throws DBException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = getConnection();

            preparedStatement = con.prepareStatement(SQL_INSERT_REQUEST_IN_FLIGHTS_USERS);
            preparedStatement.setInt(1, request.getFlightId());
            preparedStatement.setDouble(2, request.getDriverId());
            preparedStatement.executeUpdate();

            int count = 1;
            preparedStatement = con.prepareStatement(SQL_INSERT_REQUEST);
            preparedStatement.setInt(count++, request.getFlightId());
            preparedStatement.setDouble(count++, request.getCarrying());
            preparedStatement.setDouble(count++, request.getCapacity());
            preparedStatement.setDouble(count++, request.getLength());
            preparedStatement.setBoolean(count++, request.isLorryWithSides());
            preparedStatement.setBoolean(count++, request.isRefrigerator());
            preparedStatement.setInt(count++, request.getDriverId());
            preparedStatement.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            JdbcUtil.rollback(con);
            LOG.error(Messages.ERR_CANNOT_INSERT_REQUESTS, ex);
            throw new DBException(Messages.ERR_CANNOT_INSERT_REQUESTS, ex);
        } finally {
            JdbcUtil.closeQuietly(preparedStatement);
            JdbcUtil.closeQuietly(con);
        }
        return 1;
    }

    public int deleteRequest(Integer requestId) throws DBException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = getConnection();

            preparedStatement = con.prepareStatement(SQL_DELETE_REQUEST);
            preparedStatement.setInt(1, requestId);
            preparedStatement.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            JdbcUtil.rollback(con);
            LOG.error(Messages.ERR_CANNOT_DELETE_REQUEST, ex);
            throw new DBException(Messages.ERR_CANNOT_DELETE_REQUEST, ex);
        } finally {
            JdbcUtil.closeQuietly(preparedStatement);
            JdbcUtil.closeQuietly(con);
        }
        return 1;
    }

    public int deleteRequestByDriver(Integer requestId, Integer driverId, Integer flightId) throws DBException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = getConnection();

            preparedStatement = con.prepareStatement(SQL_DELETE_FLIGHTS_USERS_REQUEST);
            preparedStatement.setInt(1, flightId);
            preparedStatement.setInt(2, driverId);
            preparedStatement.executeUpdate();

            preparedStatement = con.prepareStatement(SQL_DELETE_REQUEST_BY_DRIVER);
            preparedStatement.setInt(1, requestId);
            preparedStatement.setInt(2, driverId);
            preparedStatement.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            JdbcUtil.rollback(con);
            LOG.error(Messages.ERR_CANNOT_DELETE_REQUEST_BY_DRIVER, ex);
            throw new DBException(Messages.ERR_CANNOT_DELETE_REQUEST_BY_DRIVER, ex);
        } finally {
            JdbcUtil.closeQuietly(preparedStatement);
            JdbcUtil.closeQuietly(con);
        }
        return 1;
    }

    public List<Truck> getAllTrucks() throws DBException {
        List<Truck> trucks = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_TRUCKS);
            while (rs.next()) {
                trucks.add(DBExtractEntity.extractTruck(rs));
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_TRUCKS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_TRUCKS, ex);
        } finally {
            JdbcUtil.close(con, stmt, rs);
        }
        return trucks;
    }

    public List<Truck> getAllNotServiceableTrucks() throws DBException {
        List<Truck> trucks = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_NOT_SERVICEABLE_TRUCKS);
            while (rs.next()) {
                trucks.add(DBExtractEntity.extractTruck(rs));
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_TRUCKS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_TRUCKS, ex);
        } finally {
            JdbcUtil.close(con, stmt, rs);
        }
        return trucks;
    }

    public List<Truck> getAllRefrigeratorTrucks() throws DBException {
        List<Truck> trucks = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_REFRIGERATOR_TRUCKS);
            while (rs.next()) {
                trucks.add(DBExtractEntity.extractTruck(rs));
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_TRUCKS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_TRUCKS, ex);
        } finally {
            JdbcUtil.close(con, stmt, rs);
        }
        return trucks;
    }

    public List<Truck> getAllLorryWithSidesTrucks() throws DBException {
        List<Truck> trucks = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_LORRY_WITH_SIDES_TRUCKS);
            while (rs.next()) {
                trucks.add(DBExtractEntity.extractTruck(rs));
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_TRUCKS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_TRUCKS, ex);
        } finally {
            JdbcUtil.close(con, stmt, rs);
        }
        return trucks;
    }

    public List<Truck> getAllTrucksWithParameters(Double carrying, Double capacity, Double length,
                                                  Boolean isLorryWithSides, Boolean isRefrigerator) throws DBException {
        List<Truck> trucks = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ALL_TRUCKS_WITH_PARAMETERS);
            int count = 1;

            pstmt.setDouble(count++, carrying);
            pstmt.setDouble(count++, capacity);
            pstmt.setDouble(count++, length);
            pstmt.setBoolean(count++, isLorryWithSides);
            pstmt.setBoolean(count++, isRefrigerator);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                trucks.add(DBExtractEntity.extractTruck(rs));
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_TRUCKS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_TRUCKS, ex);
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return trucks;
    }

    public int insertTruck(Truck truck) throws DBException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(SQL_INSERT_TRUCK);
            int count = 1;

            preparedStatement.setString(count++, truck.getTruckName());
            preparedStatement.setDouble(count++, truck.getCarrying());
            preparedStatement.setDouble(count++, truck.getCapacity());
            preparedStatement.setDouble(count++, truck.getLength());
            preparedStatement.setBoolean(count++, truck.isLorryWithSides());
            preparedStatement.setBoolean(count++, truck.isRefrigerator());
            preparedStatement.setBoolean(count++, truck.isServiceable());
            preparedStatement.setString(count++, truck.getPhotoLink());

            preparedStatement.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            JdbcUtil.rollback(con);
            LOG.error(Messages.ERR_CANNOT_INSERT_TRUCK, ex);
            throw new DBException(Messages.ERR_CANNOT_INSERT_TRUCK, ex);
        } finally {
            JdbcUtil.closeQuietly(preparedStatement);
            JdbcUtil.closeQuietly(con);
        }
        return 1;
    }

    public int deleteTruck(Integer truckId) throws DBException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = getConnection();

            preparedStatement = con.prepareStatement(SQL_DELETE_TRUCK);
            preparedStatement.setInt(1, truckId);
            preparedStatement.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            JdbcUtil.rollback(con);
            LOG.error(Messages.ERR_CANNOT_DELETE_TRUCK, ex);
            throw new DBException(Messages.ERR_CANNOT_DELETE_TRUCK, ex);
        } finally {
            JdbcUtil.closeQuietly(preparedStatement);
            JdbcUtil.closeQuietly(con);
        }
        return 1;
    }

    public Truck getTruckById(Integer truckId) throws DBException {
        Truck truck = new Truck();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_TRUCK_BY_ID);
            pstmt.setInt(1, truckId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                truck = DBExtractEntity.extractTruck(rs);
            }
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_TRUCK_BY_ID, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_TRUCK_BY_ID, ex);
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return truck;
    }

    public int updateTruckById(Truck truck) throws DBException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(SQL_UPDATE_TRUCK_BY_ID);
            int count = 1;

            preparedStatement.setString(count++, truck.getTruckName());
            preparedStatement.setDouble(count++, truck.getCarrying());
            preparedStatement.setDouble(count++, truck.getCapacity());
            preparedStatement.setDouble(count++, truck.getLength());
            preparedStatement.setBoolean(count++, truck.isLorryWithSides());
            preparedStatement.setBoolean(count++, truck.isRefrigerator());
            preparedStatement.setBoolean(count++, truck.isServiceable());
            preparedStatement.setString(count++, truck.getPhotoLink());
            preparedStatement.setInt(count++, truck.getId());

            preparedStatement.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            JdbcUtil.rollback(con);
            LOG.error(Messages.ERR_CANNOT_UPDATE_TRUCK, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_TRUCK, ex);
        } finally {
            JdbcUtil.closeQuietly(preparedStatement);
            JdbcUtil.closeQuietly(con);
        }
        return 1;
    }
}