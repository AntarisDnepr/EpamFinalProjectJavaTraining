package ua.nure.revuk.SummaryTask4.db;

import ua.nure.revuk.SummaryTask4.db.entity.User;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 02.02.2016
 */
public enum Role {
    ADMIN, DRIVER, DISPATCHER;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId - 1];
    }

    public static Role getRole(int roleId) {
        return Role.values()[roleId - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }

}