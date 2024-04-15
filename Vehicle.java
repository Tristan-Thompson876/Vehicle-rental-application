/**
 * The Vehicle class represents a vehicle with various attributes such as make and model, quality, number of seats,
 * rental price, and availability.
 */
public class Vehicle {
    
    private String makeModel; // The make and model of the vehicle
    private String quality; // The quality of the vehicle
    private int seats; // The number of seats in the vehicle
    private int rentalPrice; // The rental price of the vehicle
    private boolean available; // Indicates whether the vehicle is available for rental or not
    
    /**
     * Constructs a new Vehicle object with the specified parameters.
     *
     * @param makeModel   the make and model of the vehicle
     * @param quality     the quality of the vehicle
     * @param seats       the number of seats in the vehicle
     * @param rentalPrice the rental price of the vehicle
     * @param available   indicates whether the vehicle is available for rental or not
     */
    public Vehicle(String makeModel, String quality, int seats, int rentalPrice, boolean available) {
        this.makeModel = makeModel;
        this.quality = quality;
        this.seats = seats;
        this.rentalPrice = rentalPrice;
        this.available = available;
    }

    /**
     * Returns the make and model of the vehicle.
     *
     * @return the make and model of the vehicle
     */
    public String getMakeModel() {
        return makeModel;
    }

    /**
     * Sets the make and model of the vehicle.
     *
     * @param makeModel the make and model of the vehicle
     */
    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    /**
     * Returns the quality of the vehicle.
     *
     * @return the quality of the vehicle
     */
    public String getQuality() {
        return quality;
    }

    /**
     * Sets the quality of the vehicle.
     *
     * @param quality the quality of the vehicle
     */
    public void setQuality(String quality) {
        this.quality = quality;
    }

    /**
     * Returns the number of seats in the vehicle.
     *
     * @return the number of seats in the vehicle
     */
    public int getSeats() {
        return seats;
    }

    /**
     * Sets the number of seats in the vehicle.
     *
     * @param seats the number of seats in the vehicle
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * Returns the rental price of the vehicle.
     *
     * @return the rental price of the vehicle
     */
    public int getRentalPrice() {
        return rentalPrice;
    }

    /**
     * Sets the rental price of the vehicle.
     *
     * @param rentalPrice the rental price of the vehicle
     */
    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    /**
     * Returns true if the vehicle is available for rental, false otherwise.
     *
     * @return true if the vehicle is available for rental, false otherwise
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the availability status of the vehicle.
     *
     * @param available true if the vehicle is available for rental, false otherwise
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Returns a string representation of the Vehicle object.
     *
     * @return a string representation of the Vehicle object
     */
    @Override
    public String toString() {
        return "Vehicle{" +
                "makeModel='" + makeModel + '\'' +
                ", quality='" + quality + '\'' +
                ", seats=" + seats +
                ", rentalPrice=" + rentalPrice +
                ", available=" + available +
                '}';
    }
}
