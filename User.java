import java.io.Serializable;

/**
 * Represents a user with a name, password, and budget.
 * This class provides methods to manage user information such as updating
 * the user's name, password, and budget, as well as methods for authenticating
 * and displaying the user's profile.
 */


public class User implements Serializable{
    private String name;
    private String password;
    private int budget;

    /**
     * Initializes a new instance of the User class with specified name, password, and budget.
     * 
     * @param name The name of the user.
     * @param password The password for user authentication.
     * @param budget The budget of the user.*/

    public User(String name, String password, int budget){
        this.name = name;
        this.password = password;
        this.budget = budget;
    }

        /**
     * Retrieves the name of the user.
     * 
     * @return The current name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the password of the user.
     * 
     * @return The current password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the budget of the user.
     * 
     * @return The current budget of the user.
     */
    public int getBudget() {
        return budget;
    }

    /**
     * Updates the name of the user.
     * 
     * @param name The new name for the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Updates the password of the user.
     * 
     * @param password The new password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Updates the budget of the user.
     * 
     * @param budget The new budget for the user.
     */
    public void setBudget(int budget) {
        this.budget = budget;
    }

     /**
     * Authenticates the user based on the entered password.
     * 
     * @param enteredPassword The password to authenticate against.
     * @return true if the entered password matches the user's password, otherwise false.
     */
    public boolean authenticate(String enteredPassword) {
        return password.equals(enteredPassword);
    }

   
    /**
     * Changes the password of the user.
     * 
     * @param newPassword The new password to set for the user.
     */
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

     /**
     * Updates the user's profile with new name and password if they are not empty.
     * 
     * @param newName The new name to set, if it is not null and empty.
     * @param newPassword The new password to set, if it not null and empty.
     */
    public void updateProfile(String newName, String newPassword) {
        if (newName != null && !newName.isEmpty()) {
            this.name = newName;
        }
        if (newPassword != null && !newPassword.isEmpty()) {
            this.password = newPassword;
        }
    }

    /**
     * Displays the current profile information of the user.
     */
    public void displayProfile() {
        System.out.println("Name: " + name);
        System.out.println("Budget: " + budget);
    }
}
