package ua.nure.revuk.SummaryTask4.db.entity;

import ua.nure.revuk.SummaryTask4.db.Role;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 02.02.2016
 */
public class User extends Entity {

    private static final long serialVersionUID = 366788953464127487L;

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String photoLink  = "user.jpg";
    private String mail;
    private Role roleName;
    private int roleId;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", first Name='" + firstName + '\'' +
                ", last Name='" + lastName + '\'' +
                ", photo Link='" + photoLink + '\'' +
                ", e-mail='" + mail + '\'' +
                ", role Id=" + roleId +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Role getRoleName() {
        return roleName;
    }

    public void setRoleName(int roleId ) {
        this.roleName = Role.getRole(roleId);
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
