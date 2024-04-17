
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
    //private ArrayList<Admin> admins;
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
        String adminName;
        String pass;
        User admin = new User(adminName = "Tristan", pass = "triss", 0, true);
        users.add(admin);
    }
    /**
     * Returns the currently logged-in user.
     * @return the currently logged-in user
     */
    public User getWhosloggedin() {
        return whosloggedin;
    }
    
    /**
     * Returns a list of all users.
     * @return an ArrayList of User objects
     */
    public ArrayList<User> getUsers(){
        return users;
    }
     /**
     * Creates and adds a new user to the list.
     * @param uname the username of the new user
     * @param pword the password of the new user
     */
    public void createUser(String uname, String pword){
        User user = new User(uname, pword, 0, false);
        addUser(user);
        saveUsers();
    }
    /**
     * Adds a user to the list and saves the list to a file.
     * @param u the User to add
     */
    public void addUser(User u){
        users.add(u);
    }
    /**
     * Finds a user by username.
     * @param uname the username to search for
     * @return the User object if found, null otherwise
     */
    public User findUser(String uname) {
        for (User user : users) {
            if (user.getName().equals(uname)) {
                return user;
            }
        }
        return null; 
    }
     /**
     * Saves the list of users to a file.
     */
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

  
    /**
     * Loads the list of users from a file.
     */
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

    /**
     * Checks if the provided username and password match for a user.
     * @param uname the username
     * @param pword the password
     * @return true if credentials match, false otherwise
     */
    public boolean isPasswordCorrect(String uname, String pword){
        User u = findUser(uname);
        if(u.getPassword().equals(pword)){
            return true;
        }
        return false;

    }
    /**
     * Logs in a user if the username exists and the password matches.
     * @param uname the username
     * @param pword the password
     */
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
 /**
 * Checks if a user exists in the user list.
 * @param name the username to check.
 * @return true if the user exists, false otherwise.
 */
    public boolean isAUser(String name){
        for(User user: users){
            if(user.getName().equals(name)){
                return true;
            }
        }
        return false;
    }



    /**
 * Adds a vehicle to the vehicle list.
 * @param v the vehicle to add.
 */
    public void addVehicle(Vehicle v){
        vehicles.add(v);
    }

   /**
 * Removes a vehicle from the vehicle list.
 * @param v the vehicle to remove.
 */
    public void removeVehicle(Vehicle v){
        vehicles.remove(v);
    }

   /**
 * Finds and returns a vehicle by reference.
 * @param v the vehicle to find.
 * @return the vehicle if found, null otherwise.
 */
    public Vehicle findVehicle(Vehicle v){
        for(Vehicle vehicle : vehicles){
            if(vehicle.equals(v)){
                return vehicle;
            }
        }
        return null;
    }

/**
 * Prints all vehicles in the vehicle list to the console.
 */
    public void listAllVehicles(){
        for(Vehicle vehicle : vehicles){
            System.out.println(vehicle);
        }
    }

    
  /**
 * Creates a new vehicle and adds it to the list of vehicles.
 * @param makeModel the make and model of the vehicle.
 * @param quality the quality of the vehicle.
 * @param seats the number of seats in the vehicle.
 * @param rentalPrice the rental price per day.
 * @param available the availability status of the vehicle.
 */
    public void createVehicle(String makeModel, String quality, int seats, int rentalPrice, boolean available) {
        Vehicle vehicle = new Vehicle(makeModel, quality, seats, rentalPrice, available);
        addVehicle(vehicle);
        saveVehicles();
    }
    
 
/**
 * Saves the current list of vehicles to a file.
 */
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

/**
 * Loads vehicles from a specified file.
 * @param filePath the path to the file from which vehicles are loaded.
 * @return a list of loaded vehicles.
 */
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
    
    
/**
 * Calculates the total rental price based on the start and end dates and the daily rental price of the vehicle.
 * @param startDate the start date of the rental period.
 * @param endDate the end date of the rental period.
 * @param vehicle the vehicle being rented.
 * @return the total rental price.
 */
    public int rentalPrice(Date startDate, Date endDate,Vehicle vehicle){
        int duration = date.calculateDuration(startDate, endDate);
        int rentalPrice= vehicle.getRentalPrice() * duration;
        return rentalPrice;
    }


    /**
     * Rents a vehicle if it is available, marking it as unavailable and printing the rental details.
     *
     * @param startDate The start date of the rental period.
     * @param endDate The end date of the rental period.
     * @param vehicle The vehicle to rent.
     */
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

    /**
     * Sorts the list of vehicles by rental price in descending order (high to low).
     *
     * @return An ArrayList of vehicles sorted by price from highest to lowest.
     */
    public ArrayList<Vehicle> sortByPriceHighLow(){
        //ArrayList<Vehicle> vehicles = Collections.sort(vehicles, Comparator.comparingDouble(Vehicle::getRentalPrice).reversed());
        Collections.sort(vehicles, Comparator.comparingDouble(Vehicle::getRentalPrice).reversed());
        return vehicles;
    }

        /**
     * Sorts the list of vehicles by rental price in ascending order (low to high).
     *
     * @return An ArrayList of vehicles sorted by price from lowest to highest.
     */
    public ArrayList<Vehicle> sortByPriceLowHigh(){
        Collections.sort(vehicles, Comparator.comparingDouble(Vehicle::getRentalPrice));
        return vehicles;
    }

    //
    /**
     * Sorts the list of vehicles by the number of seats in descending order (largest to smallest).
     *
     * @return An ArrayList of vehicles sorted by the number of seats from most to fewest.
     */
    public ArrayList<Vehicle> sortBySizeLargestSmall(){
        Collections.sort(vehicles, Comparator.comparingInt(Vehicle::getSeats).reversed());
        return vehicles;
    }
 /**
     * Sorts the list of vehicles by the number of seats in ascending order (smallest to largest).
     *
     * @return An ArrayList of vehicles sorted by the number of seats from fewest to most.
     */
    public ArrayList<Vehicle> sortBySizeSmallLargest(){
        Collections.sort(vehicles, Comparator.comparingInt(Vehicle::getSeats));
        return vehicles;
    }

    
    /**
     * Sorts the list of vehicles based on the specified criteria.
     *
     * @param sortBy The criterion to sort by (e.g., "PriceHighLow", "PriceLowHigh", "SizeLargestSmall", "SizeSmallLargest").
     * @param quality A filter to apply based on the quality (not used in current implementation).
     */
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

   /* spublic void loadAdmin(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Read the name and password from alternating lines
                String name = line.trim(); // Trim to remove any leading or trailing whitespace
                String password = reader.readLine().trim(); // Read the next line for the password

                // Create an Admin object and add it to the admins ArrayList
                Admin admin = new Admin(name, password, 1000000, true);
                admins.add(admin);
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }*/
     /**
     * Checks if a specified username belongs to an admin user.
     *
     * @param uname The username to check.
     * @return true if the user with the given username has admin privileges, false otherwise.
     */
    public boolean findAdmin(String uname) {
        //loadAdmin("Admin.txt");

        for(User u : users){
            u.getAccess().equals(true);
            return true;
        }
        return false;
    }
}
//