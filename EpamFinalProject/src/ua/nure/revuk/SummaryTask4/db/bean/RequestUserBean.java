package ua.nure.revuk.SummaryTask4.db.bean;


import ua.nure.revuk.SummaryTask4.db.entity.Entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Epam Final Project Revuk
 * Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 02.02.2016
 * <p>
 * Provide records for virtual table:
 */
public class RequestUserBean extends Entity {

    private static final long serialVersionUID = 960817035920564608L;

    private int beanFlightId;
    private double beanCarrying;
    private double beanCapacity;
    private double beanLength;
    private boolean beanLorryWithSides = false;
    private boolean beanRefrigerator = false;
    private int beanDriverId;
    private String beanDriverFirstName;
    private String beanDriverLastName;
    private String beanDriverPhotoLink = "user.jpg";
    private int beanRoleId;
    private int beanFlightNumber;
    private Date beanDateDeparture;
    private String beanDestination;

    @Override
    public String toString() {
        return "RequestUserBean{" +
                "beanFlightId=" + beanFlightId +
                ", beanCarrying=" + beanCarrying +
                ", beanCapacity=" + beanCapacity +
                ", beanLength=" + beanLength +
                ", beanLorryWithSides=" + beanLorryWithSides +
                ", beanRefrigerator=" + beanRefrigerator +
                ", beanDriverId=" + beanDriverId +
                ", beanDriverFirstName='" + beanDriverFirstName + '\'' +
                ", beanDriverLastName='" + beanDriverLastName + '\'' +
                ", beanDriverPhotoLink='" + beanDriverPhotoLink + '\'' +
                ", beanRoleId=" + beanRoleId +
                ", beanFlightNumber=" + beanFlightNumber +
                ", beanDateDeparture=" + beanDateDeparture +
                ", beanDestination='" + beanDestination + '\'' +
                '}';
    }

    public int getBeanFlightId() {
        return beanFlightId;
    }

    public void setBeanFlightId(int beanFlightId) {
        this.beanFlightId = beanFlightId;
    }

    public double getBeanCarrying() {
        return beanCarrying;
    }

    public void setBeanCarrying(double beanCarrying) {
        this.beanCarrying = beanCarrying;
    }

    public double getBeanCapacity() {
        return beanCapacity;
    }

    public void setBeanCapacity(double beanCapacity) {
        this.beanCapacity = beanCapacity;
    }

    public double getBeanLength() {
        return beanLength;
    }

    public void setBeanLength(double beanLength) {
        this.beanLength = beanLength;
    }

    public boolean isBeanLorryWithSides() {
        return beanLorryWithSides;
    }

    public void setBeanLorryWithSides(boolean beanLorryWithSides) {
        this.beanLorryWithSides = beanLorryWithSides;
    }

    public boolean isBeanRefrigerator() {
        return beanRefrigerator;
    }

    public void setBeanRefrigerator(boolean beanRefrigerator) {
        this.beanRefrigerator = beanRefrigerator;
    }

    public int getBeanDriverId() {
        return beanDriverId;
    }

    public void setBeanDriverId(int beanDriverId) {
        this.beanDriverId = beanDriverId;
    }

    public String getBeanDriverFirstName() {
        return beanDriverFirstName;
    }

    public void setBeanDriverFirstName(String beanDriverFirstName) {
        this.beanDriverFirstName = beanDriverFirstName;
    }

    public String getBeanDriverLastName() {
        return beanDriverLastName;
    }

    public void setBeanDriverLastName(String beanDriverLastName) {
        this.beanDriverLastName = beanDriverLastName;
    }

    public String getBeanDriverPhotoLink() {
        return beanDriverPhotoLink;
    }

    public void setBeanDriverPhotoLink(String beanDriverPhotoLink) {
        this.beanDriverPhotoLink = beanDriverPhotoLink;
    }

    public int getBeanRoleId() {
        return beanRoleId;
    }

    public void setBeanRoleId(int beanRoleId) {
        this.beanRoleId = beanRoleId;
    }

    public int getBeanFlightNumber() {
        return beanFlightNumber;
    }

    public void setBeanFlightNumber(int beanFlightNumber) {
        this.beanFlightNumber = beanFlightNumber;
    }

    public String getBeanDateDeparture() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(beanDateDeparture.getTime()));
    }

    public void setBeanDateDeparture(Date beanDateDeparture) {
        this.beanDateDeparture = new Date(beanDateDeparture.getTime());
    }

    public String getBeanDestination() {
        return beanDestination;
    }

    public void setBeanDestination(String beanDestination) {
        this.beanDestination = beanDestination;
    }
}