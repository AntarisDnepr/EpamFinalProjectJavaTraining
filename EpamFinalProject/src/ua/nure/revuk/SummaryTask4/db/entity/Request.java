package ua.nure.revuk.SummaryTask4.db.entity;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 02.02.2016
 */
public class Request extends Entity {

    private static final long serialVersionUID = 8267592736936987334L;

    private int flightId;
    private double carrying;
    private double capacity;
    private double length;
    private boolean lorryWithSides = false;
    private boolean refrigerator = false;
    private int driverId;

    @Override
    public String toString() {
        return "Request{" +
                "flight Id=" + flightId +
                ", carrying=" + carrying +
                ", capacity=" + capacity +
                ", length=" + length +
                ", lorryWithSides=" + lorryWithSides +
                ", refrigerator=" + refrigerator +
                ", driver id=" + driverId +
                '}';
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public double getCarrying() {
        return carrying;
    }

    public void setCarrying(double carrying) {
        this.carrying = carrying;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public boolean isLorryWithSides() {
        return lorryWithSides;
    }

    public void setLorryWithSides(boolean lorryWithSides) {
        this.lorryWithSides = lorryWithSides;
    }

    public boolean isRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(boolean refrigerator) {
        this.refrigerator = refrigerator;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
}