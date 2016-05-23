package ua.nure.revuk.SummaryTask4.db;

import org.junit.Test;
import ua.nure.revuk.SummaryTask4.db.entity.User;

import static org.junit.Assert.assertEquals;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 21.02.2016
 */
public class RoleTest {

    @Test
    public void testGetRole() throws Exception {
        assertEquals(Role.getRole(1), Role.ADMIN);
    }

    @Test
    public void testGetRoleByUser() throws Exception {
        User user = new User();
        user.setRoleId(1);

        assertEquals(Role.getRole(user), Role.ADMIN);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(Role.getRole(1).getName(), "admin");
    }
}