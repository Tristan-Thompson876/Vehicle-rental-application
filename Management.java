import java.util.ArrayList;

public class Management {
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    private ArrayList<User> users = new ArrayList<User>();

    public Management(ArrayList users, ArrayList vehicles){
        this.users = users;
        this.vehicles = vehicles;
    }

    public ArrayList getVehicles(){
        return vehicles;
    }

    public ArrayList getUsers(){
        return users;
    }


    //adds a vehicle to arraylist
    public void addVehicle(Vehicle v){

    }

    //removes a vehicle from arraylist
    public void removeVehicle(Vehicle v){

    }

    public void findVehicle(Vehicle v){

    }

    //list all vehicles
    public void listAllvehicles(){

    }

    
    //this method should check if vehicle 
    public void rentVehicle(Date date, Vehicle vehicle){

    }

    // sort list of vehicles by cost high to low
    public void sortByPrice_high_low(){

    }

    // sort list of vehicles by cost low to high
    public void sortByPrice_low_high(){

    }

    //sort list vehicles by size largest to smallest
    public void sortBySize_largest_small(){

    }

    //sort list to include only costs lesser or equal to minimum 
    public void sortByMinimum_Cost(int minimum){

    }

    //sort list to include only costs greater or equal to maximum 
    public void sortByMaximum_Cost(int maximun){
        
    }


}
