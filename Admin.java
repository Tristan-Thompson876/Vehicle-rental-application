public class Admin extends User {
    private boolean access;
    public Admin(String name, String password, int budget) {
        super(name, password, budget);
        this.access = true;
    }
}
