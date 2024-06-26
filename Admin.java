
/**
 * The Admin class represents an administrative user with special access , extending the User class.
 */
public class Admin extends User {
    
    boolean access; // Indicates whether the admin has access
    
    /**
     * Constructs a new Admin object with the specified name, password, and budget, and grants access.
     *
     * @param name     the name of the admin user
     * @param password the password of the admin user
     * @param budget   the budget of the admin user
     */
    public Admin(String name, String password, int budget, Boolean access) {
        super(name, password, budget, true);
        this.access = true;
    }

    public Boolean getAcces(){
        return access;
    }
}
