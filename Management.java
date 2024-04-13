import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Management {
    private ArrayList<Vehicle> vehicles;
    private ArrayList<User> users;

    public Management(ArrayList<User> users, ArrayList<Vehicle> vehicles){
        this.users = users;
        this.vehicles = vehicles;
    }

    public ArrayList<Vehicle> getVehicles(){
        return vehicles;
    }

    public ArrayList<User> getUsers(){
        return users;
        
    }

    //creates a new user
    public void setUser(String uname, String password){

    }
    
    //returns who is logged in
    //public getWhoIsLoggedIn (){}

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
    public void getRentalPrice(){

    }

    // Rent a vehicle
    public void rentVehicle(Date date, Vehicle vehicle){
        // Implementation depends on your requirements
    }

    // Sort list of vehicles by cost high to low
    public void sortByPriceHighLow(){
        //Collections.sort(vehicles, Comparator.comparingDouble(Vehicle::getRentalPrice).reversed());
    }

    // Sort list of vehicles by cost low to high
    public void sortByPriceLowHigh(){
        //Collections.sort(vehicles, Comparator.comparingDouble(Vehicle::getRentalPrice));
    }

    // Sort list vehicles by size largest to smallest
    public void sortBySizeLargestSmall(){
        Collections.sort(vehicles, Comparator.comparingInt(Vehicle::getSize).reversed());
    }

    // Sort list to include only costs lesser or equal to minimum 
    public void sortByMinimumCost(int minimum){
       // vehicles.removeIf(vehicle -> vehicle.getRentalPrice() > minimum);
    }

    // Sort list to include only costs greater or equal to maximum 
    public void sortByMaximumCost(int maximum){
        //vehicles.removeIf(vehicle -> vehicle.getRentalPrice() < maximum);
    }
}