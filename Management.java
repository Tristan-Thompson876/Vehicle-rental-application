
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Management {
    private ArrayList<Vehicle> vehicles;
    private ArrayList<User> users;
    Date date;
    private String whosloggedin;


    public Management(){
        vehicles = new ArrayList<Vehicle>();
        users = new ArrayList<User>();
       
    }

    public ArrayList<Vehicle> getVehicles(){
        return vehicles;
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    //adds a vehicle to arraylist
    public void addVehicle(Vehicle v){
        vehicles.add(v);
    }

    //removes a vehicle from arraylist
    public void removeVehicle(Vehicle v){
        vehicles.remove(v);
    }

    // Find a specific vehicle by reference
    public Vehicle findVehicle(Vehicle v){
        for(Vehicle vehicle : vehicles){
            if(vehicle.equals(v)){
                return vehicle;
            }
        }
        return null;
    }

    // List all vehicles
    public void listAllVehicles(){
        for(Vehicle vehicle : vehicles){
            System.out.println(vehicle);
        }
    }

    //Gets RentalPrice
    public int rentalPrice(Date start, Date end,Vehicle vehicle){
        int duration = date.calculateDuration(start, end);
        int rentalPrice= vehicle.getRentalPrice() * duration;
        return rentalPrice;
    }


    // Rent a vehicle
    public void rentVehicle(Date startDate, Date endDate, Vehicle vehicle) {
        if (vehicle.isAvailable()) { // Check if the vehicle is available
            // Calculate rental price
            //int rentalPrice = vehicle.getRentalPrice(startDate, endDate);

            // Update vehicle availability
            vehicle.setAvailable(false);

            // Perform other rental-related operations (e.g., update database, create rental record)
            // For example, you can add the rented vehicle to a list of rented vehicles for further tracking

            System.out.println("Vehicle rented successfully:");
            System.out.println(vehicle);
           // System.out.println("Rental Price: " + rentalPrice);
        } else {
            System.out.println("Sorry, the vehicle is not available for rent.");
        }
    }





    // Sort list of vehicles by cost high to low
    public void sortByPriceHighLow(){
        Collections.sort(vehicles, Comparator.comparingDouble(Vehicle::getRentalPrice).reversed());
    }

    // Sort list of vehicles by cost low to high
    public void sortByPriceLowHigh(){
        Collections.sort(vehicles, Comparator.comparingDouble(Vehicle::getRentalPrice));
    }

    // Sort list vehicles by size largest to smallest
    public void sortBySizeLargestSmall(){
        Collections.sort(vehicles, Comparator.comparingInt(Vehicle::getSeats).reversed());
    }

    // Sort list to include only costs lesser or equal to minimum 
    public void sortByMinimumCost(int minimum){
       vehicles.removeIf(vehicle -> vehicle.getRentalPrice() > minimum);
    }

    // Sort list to include only costs greater or equal to maximum 
    public void sortByMaximumCost(int maximum){
        vehicles.removeIf(vehicle -> vehicle.getRentalPrice() < maximum);
    }

    public void isLoggedIn(){

    }

    public boolean isAUsser(String name){
        for(User user: users){
            if(user.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}

