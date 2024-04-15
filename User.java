import java.io.Serializable;

public class User implements Serializable{
    private String name;
    private String password;
    private int budget;

    public User(String name, String password, int budget){
        this.name = name;
        this.password = password;
        this.budget = budget;
    }

    // Getter method to retrieve the user's name
    public String getName(){
        return name;
    }

    // Getter method to retrieve the user's password
    public String getPassword(){
        return password;
    }

    // Getter method to retrieve the user's budget
    public int getBudget(){
        return budget;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }
    
    // Setter method to update the user's budget
    public void setBudget(int budget){
        this.budget = budget;
    }

    // Method to authenticate user
    public boolean authenticate(String enteredPassword) {
        return password.equals(enteredPassword);
    }

    // Method to change user's password
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    // Method to update user's profile information
    public void updateProfile(String newName, String newPassword) {
        if (newName != null && !newName.isEmpty()) {
            this.name = newName;
        }
        if (newPassword != null && !newPassword.isEmpty()) {
            this.password = newPassword;
        }
    }

    // Method to display user's profile information
    public void displayProfile() {
        System.out.println("Name: " + name);
        System.out.println("Budget: " + budget);
    }
}
