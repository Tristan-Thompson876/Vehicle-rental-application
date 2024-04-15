
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Management {
    private ArrayList<Vehicle> vehicles;
    private ArrayList<User> users;
    Date date;
    private User whosloggedin;

    public Management(){
        vehicles = new ArrayList<Vehicle>();
        users = new ArrayList<User>();
       
    }

    public User getWhosloggedin() {
        return whosloggedin;
    }

    public ArrayList<Vehicle> getVehicles(){
        return vehicles;
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public void createUser(String uname, String pword){
        User user = new User(uname, pword, 0);
        addUser(user);
        saveUsers();
    }

    public void addUser(User u){
        users.add(u);
    }

    public User findUser(String uname) {
        for (User user : users) {
            if (user.getName().equals(uname)) {
                return user;
            }
        }
        return null; // Moved outside the loop
    }

     // Save users to a file
     private void saveUsers() {
        try {
            FileOutputStream fileOut = new FileOutputStream(new File("USERS_FILE.txt"));
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(users);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load users from a file
    @SuppressWarnings("unchecked") // Suppressing unchecked cast warning
    private void loadUsers() {
        try {
            //File myObj = new File("USERS_FILE.txt");
            FileInputStream fileIn = new FileInputStream("USERS_FILE.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            users = (ArrayList<User>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    
    // Create a new vehicle and add it to the list
    public void createVehicle(String makeModel,String quality, int seats, int rentalPrice, boolean available) {
        Vehicle vehicle = new Vehicle(makeModel,quality, seats, rentalPrice, available);
         addVehicle(vehicle);
         saveVehicles();
     }
 
     // Save vehicles to a file
     private void saveVehicles() {
         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("vehicles.ser"))) {
             oos.writeObject(vehicles);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     // Load vehicles from a file
     private void loadVehicles() {
         try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("vehicles.ser"))) {
             vehicles = (ArrayList<Vehicle>) ois.readObject();
         } catch (IOException | ClassNotFoundException e) {
             // If file not found or class not found, it's okay, just initialize an empty list
             vehicles = new ArrayList<>();
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
    public boolean isPasswordCorrect(String uname, String pword){
        User u = findUser(uname);
        if(u.getPassword().equals(pword)){
            return true;
        }
        return false;

    }
    public void login(String uname, String pword){
        if(isAUser(uname)){
            if(isPasswordCorrect(uname, pword)){
               User u = findUser(uname);
               if(isPasswordCorrect(uname, pword)){
                whosloggedin = u;
               }
            }
        }

    }

    public boolean isAUser(String name){
        for(User user: users){
            if(user.getName().equals(name)){
                return true;
            }
        }
        return false;
    }


}