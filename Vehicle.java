import java.util.Date;

public class Vehicle {
    private String makeModel;
    private String quality;
    private int seats;
    private int rentalPrice;
    private boolean available;

    public Vehicle(String makeModel, String quality, int seats, int rentalPrice, boolean available) {
        this.makeModel = makeModel;
        this.quality = quality;
        this.seats = seats;
        this.rentalPrice = rentalPrice;
        this.available = available;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

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


