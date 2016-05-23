package ua.nure.revuk.SummaryTask4.db.entity;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 02.02.2016
 */
public class Truck extends Entity {

    private static final long serialVersionUID = 179976440494112830L;

    private String truckName = "no name";
    private double carrying;
    private double capacity;
    private double length;
    private boolean lorryWithSides = false;
    private boolean refrigerator = false;
    private boolean serviceable = true;
    private String photoLink = "truck.jpg";
    private int truckCountFlights;


    @Override
    public String toString() {
        return "Truck{" +
                "truck Name='" + truckName + '\'' +
                ", carrying=" + carrying +
                ", capacity=" + capacity +
                ", length=" + length +
                ", lorry With Sides=" + lorryWithSides +
                ", refrigerator=" + refrigerator +
                ", serviceable=" + serviceable +
                ", photo link='" + photoLink + '\'' +
                ", truck count flights='" + truckCountFlights + '\'' +
                '}';
    }

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
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

    public boolean isServiceable() {
        return serviceable;
    }

    public void setServiceable(boolean serviceable) {
        this.serviceable = serviceable;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public int getTruckCountFlights() {
        return truckCountFlights;
    }

    public void setTruckCountFlights(int truckCountFlights) {
        this.truckCountFlights = truckCountFlights;
    }
}
