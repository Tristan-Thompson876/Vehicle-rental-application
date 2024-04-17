
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * The Management class represents a system for managing vehicles and users.
 * It provides functionalities to add, remove, and manipulate vehicles and users.
 */

public class Management {
    private ArrayList<Vehicle> vehicles;
    private ArrayList<User> users;
    Date date;
    private User whosloggedin;


     /**
     * Constructs a Management object.
     * Initializes empty lists of vehicles and users, and sets the currently logged-in user to null.
     * Upon creation, it loads existing user data from a file and populates the users list.
     * Additionally, it loads existing vehicle data from a file and populates the vehicles list.
     */
    
    public Management(){
        vehicles = new ArrayList<Vehicle>();
        users = new ArrayList<User>();  
    }

    public User getWhosloggedin() {
        return whosloggedin;
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
        return null; 
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
            File myObj = new File("USERS_FILE.txt");
            FileInputStream fileIn = new FileInputStream("USERS_FILE.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            users = (ArrayList<User>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
    public void createVehicle(String makeModel, String quality, int seats, int rentalPrice, boolean available) {
        Vehicle vehicle = new Vehicle(makeModel, quality, seats, rentalPrice, available);
        addVehicle(vehicle);
        saveVehicles();
    }
    
 
     // Save vehicles to a file
     private void saveVehicles() {
         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("vehicle.txt"))) {
             oos.writeObject(vehicles);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     public ArrayList<Vehicle> getVehicles() {
        System.out.println("vehicles");
        vehicles = loadVehicles("vehicle.txt");
        System.out.println("Number of vehicles loaded: " + vehicles.size());
        listAllVehicles();
        return vehicles;
    }
    
     // Load vehicles from a file
     /* 
     public static ArrayList<Vehicle> loadVehicles(String filePath) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[Vehicle]")) {
                    String makeModel = reader.readLine().split(": ")[1];
                    String quality = reader.readLine().split(": ")[1];
                    int seats = Integer.parseInt(reader.readLine().split(": ")[1]);
                    int rentalPrice = Integer.parseInt(reader.readLine().split(": ")[1]);
                    boolean available = Boolean.parseBoolean(reader.readLine().split(": ")[1]);
                    vehicles.add(new Vehicle(makeModel, quality, seats, rentalPrice, available));
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions properly in your application
        }
        return vehicles;
    }*/
    public static ArrayList<Vehicle> loadVehicles(String filePath) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[Vehicle]")) {
                    String makeModel = null;
                    String quality = null;
                    int seats = 0;
                    int rentalPrice = 0;
                    boolean available = false;
                    
                    // Read each attribute of the vehicle
                    while ((line = reader.readLine()) != null && !line.isEmpty()) {
                        String[] parts = line.split(": ");
                        switch (parts[0]) {
                            case "MakeModel":
                                makeModel = parts[1];
                                break;
                            case "Quality":
                                quality = parts[1];
                                break;
                            case "Seats":
                                seats = Integer.parseInt(parts[1]);
                                break;
                            case "RentalPrice":
                                rentalPrice = Integer.parseInt(parts[1]);
                                break;
                            case "Available":
                                available = Boolean.parseBoolean(parts[1]);
                                break;
                            default:
                                break;
                        }
                    }
                    
                    // Create and add the vehicle to the list
                    Vehicle v = new Vehicle(makeModel, quality, seats, rentalPrice, available); 
                    vehicles.add(v);
                    System.out.println(vehicles);
                    //System.out.println(v);  
                    System.out.println(vehicles.size());

                }
                       
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
    
    
    //Gets RentalPrice
    public int rentalPrice(Date startDate, Date endDate,Vehicle vehicle){
        int duration = date.calculateDuration(startDate, endDate);
        int rentalPrice= vehicle.getRentalPrice() * duration;
        return rentalPrice;
    }


    // Rent a vehicle
    public void rentVehicle(Date startDate, Date endDate, Vehicle vehicle) {
        if (vehicle.isAvailable()) { 
            vehicle.setAvailable(false);
            int rentalPrice = rentalPrice(startDate, endDate, vehicle); 
            System.out.println("Vehicle rented successfully:");
            System.out.println(vehicle);
            System.out.println("Rental Price: " + rentalPrice);
        } else {
            System.out.println("Sorry, the vehicle is not available for rent.");
        }
    }

    // Sort list of vehicles by cost high to low
    public ArrayList<Vehicle> sortByPriceHighLow(){
        //ArrayList<Vehicle> vehicles = Collections.sort(vehicles, Comparator.comparingDouble(Vehicle::getRentalPrice).reversed());
        Collections.sort(vehicles, Comparator.comparingDouble(Vehicle::getRentalPrice).reversed());
        return vehicles;
    }

    // Sort list of vehicles by cost low to high
    public ArrayList<Vehicle> sortByPriceLowHigh(){
        Collections.sort(vehicles, Comparator.comparingDouble(Vehicle::getRentalPrice));
        return vehicles;
    }

    // Sort list vehicles by size largest to smallest
    public ArrayList<Vehicle> sortBySizeLargestSmall(){
        Collections.sort(vehicles, Comparator.comparingInt(Vehicle::getSeats).reversed());
        return vehicles;
    }

    // Sort list vehicles by size largest to smallest
    public ArrayList<Vehicle> sortBySizeSmallLargest(){
        Collections.sort(vehicles, Comparator.comparingInt(Vehicle::getSeats));
        return vehicles;
    }


    public void sortBy(String sortBy, String quality) {
        switch (sortBy) {
            case "PriceHighLow":
            Collections.sort(vehicles, Comparator.comparingDouble(Vehicle::getRentalPrice).reversed());
            break;
        case "PriceLowHigh":
            Collections.sort(vehicles, Comparator.comparingDouble(Vehicle::getRentalPrice));
            break;
        case "SizeLargestSmall":
            Collections.sort(vehicles, Comparator.comparingInt(Vehicle::getSeats).reversed());
            break;
        case "SizeSmallLargest":
            Collections.sort(vehicles, Comparator.comparingInt(Vehicle::getSeats));
            break;
        
    }
     //updateFeedPanel();
    }

    public void rentVehicle(java.util.Date date1, java.util.Date date2, Vehicle vehicle) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rentVehicle'");
    }
}
//