package ua.nure.revuk.SummaryTask4.db.util;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.exception.Messages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 03.02.2015
 */
public final class JdbcUtil {

    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);

    /**
     * Closes resources.
     */
    public static void close(Connection con, Statement stmt, ResultSet rs) {
        closeQuietly(rs);
        closeQuietly(stmt);
        closeQuietly(con);
    }

    /**
     * Closes a connection.
     *
     * @param connection Connection to be closed.
     */

    public static void closeQuietly(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                LOG.trace("connection close");
            } catch (SQLException e) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, e);
            }
        }
    }

    /**
     * Closes a Statement.
     *
     * @param statement Connection to be closed.
     */

    public static void closeQuietly(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
                LOG.trace("statement close");
            } catch (SQLException e) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, e);
            }
        }
    }

    /**
     * Closes a ResultSet.
     *
     * @param resultSet Connection to be closed.
     */

    public static void closeQuietly(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
                LOG.trace("resultSet close");
            } catch (SQLException e) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, e);
            }
        }
    }

    /**
     * Rollbacks a connection.
     *
     * @param connection Connection to be rollbacked.
     */

    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
                LOG.trace("connection rollback");
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }
}
