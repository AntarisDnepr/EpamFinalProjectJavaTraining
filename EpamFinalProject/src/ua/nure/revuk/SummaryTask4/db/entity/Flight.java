package ua.nure.revuk.SummaryTask4.db.entity;

import ua.nure.revuk.SummaryTask4.db.Status;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 02.02.2016
 */
public class Flight extends Entity {

    private static final long serialVersionUID = 2729829938052138364L;

    private int flightNumber;
    private Date dateCreation;
    private Date dateDeparture;
    private String destination;
    private double distance;
    private Status statusName;
    private int truckId;
    private int driverId;
    private int dispatcherIdCreate;
    private int dispatcherIdApprove;

    @Override
    public String toString() {
        return "Flight{" +
                "flight Number=" + flightNumber +
                ", date Creation=" + dateCreation +
                ", date Departure=" + dateDeparture +
                ", destination='" + destination + '\'' +
                ", distance=" + distance +
                ", status Name=" + statusName +
                ", truck Id=" + truckId +
                ", driver Id=" + driverId +
                ", dispatcher Id who Created=" + dispatcherIdCreate +
                ", driver Id who Approve=" + dispatcherIdApprove +
                '}';
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDateCreation() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(dateCreation.getTime()));
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = new Date(dateCreation.getTime());
    }

    public String getDateDeparture() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(dateDeparture.getTime()));
    }

    public void setDateDeparture(Date dateDeparture) {
        this.dateDeparture = new Date(dateDeparture.getTime());
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Status getStatusName() {
        return statusName;
    }

    public void setStatusName(int statusId) {
        this.statusName = Status.getStatus(statusId);
    }

    public int getTruckId() {
        return truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getDispatcherIdCreate() {
        return dispatcherIdCreate;
    }

    public void setDispatcherIdCreate(int dispatcherIdCreate) {
        this.dispatcherIdCreate = dispatcherIdCreate;
    }

    public int getDispatcherIdApprove() {
        return dispatcherIdApprove;
    }

    public void setDispatcherIdApprove(int dispatcherIdApprove) {
        this.dispatcherIdApprove = dispatcherIdApprove;
    }


}
